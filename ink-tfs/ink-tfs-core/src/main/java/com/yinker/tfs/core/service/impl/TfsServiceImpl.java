package com.yinker.tfs.core.service.impl;

import com.taobao.common.tfs.TfsManager;
import com.taobao.common.tfs.impl.TfsFile;
import com.yinker.base.log.util.YinkerLogger;
import com.yinker.tfs.core.po.TfsFileName;
import com.yinker.tfs.core.service.ITfsFileNameManager;
import com.yinker.tfs.core.utils.FileUtil;
import com.yinker.tfs.core.service.TfsService;
import com.yinker.tfs.core.utils.TfsFileConstant;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haoyunfeng
 * @date 2016/5/5
 */
@Service("tfsService")
public class TfsServiceImpl implements TfsService {

    private YinkerLogger loger=  YinkerLogger.getLogger(TfsServiceImpl.class) ;

    @Autowired
    private TfsManager tfsManager;

    @Autowired
    private ITfsFileNameManager tfsFileNameManager;

    /**
     * 保存文件到tfs(建议文件2m以内)
     * 2m以内按小文件保存。2m到40g以内按大文件保存
     * @param inputStream 输入流
     * @param fileName
     * @return
     * @throws IOException
     * @throws Exception  文件超过40g抛出异常
     */
    @Override
    public String saveFile(InputStream inputStream, String fileName,String source,String module,String uploader)throws Exception {
        try {
            //流转为byte数组
            byte[] fileBytes = IOUtils.toByteArray(inputStream);
            return saveFile(fileBytes,fileName,source,module,uploader);
        }catch (IOException e){
            loger.error("tfs","tfs保存文件失败:输入流读取失败",e);
            return "";
        }finally {
            inputStream.close();
        }
    }

    @Override
    public String saveLargeFile(InputStream inputStream, String fileName, String source, String module, String uploader) throws Exception {
        return saveFile(inputStream, fileName, source, module, uploader);
    }

    /**
     * 保存文件到tfs(建议文件2m以内)
     * 2m以内按小文件保存。2m到40g以内按大文件保存
     * @param fileBytes 字节数组
     * @param fileName
     * @throws Exception  文件超过40g抛出异常
     * @return
     */
    @Override
    public String saveFile(byte[] fileBytes,String fileName,String source,String module,String uploader) throws Exception{
        System.out.println("tfs服务接收时间"+new Date());
        loger.info("tfs","add","保存文件："+fileName);

        //判断该文件在tfs是否已经存在，如果存在，删除； 判断条件为 sourceCode,moduleCode,fileName,suffix
        if(! processExistTfsFile(fileName,source,module)){
            loger.error("tfs","删除重名tfs文件失败");
            throw new Exception("删除重名tfs文件失败");
        }

        //根据文件大小调用不同方法保存tfs文件；小于2m调用saveFile；大于2m小于40g调用saveLargeFile；超过40g抛出异常
        int len = fileBytes.length;
        String tfsName;
        if(len < TfsFile.MAX_LARGE_FILE_LENGTH){        //上传文件大小不得超过40g
            if(len > TfsFile.MAX_SMALL_FILE_LENGTH ){   //大于2M，小于40g大小的文件按大文件保存
                tfsName = tfsManager.saveLargeFile(fileBytes,"",FileUtil.getExtensionName(fileName),source+module+fileName);
            }else {                                     //小于2M，按小文件保存
                tfsName = tfsManager.saveFile(fileBytes,"",FileUtil.getExtensionName(fileName));
            }
        }else{
            loger.error("tfs","上传文件超过40g");
            throw new Exception("上传文件超过40g");
        }
        System.out.println("tfs上传结束时间"+new Date());
        //if tfsName不为空
        if(tfsName != null && !tfsName.equals("")){
            //保存tfs文件名与原始文件名映射关系
            saveTfsFileName(tfsName,fileName,source,module,uploader);
        }
        //返回文件的tfs ID
        return tfsName;
    }

    /**
     * 保存本地文件到tfs
     * 文件大小在2m以内，超过2m调用saveLocalLargeFile。超过2m文件不会报错，但效率较低
     * @param localFileName 文件地址
     * @param fileName
     * @param source
     * @param module
     * @return
     */
    @Override
    @Deprecated
    public String saveLocalFile(String localFileName,String fileName,String source,String module,String uploader){
        loger.info("tfs","add","保存本地文件："+fileName);
        //判断该文件在tfs是否已经存在，如果存在，删除； 判断条件为 sourceCode,moduleCode,fileName,suffix
        processExistTfsFile(fileName,source,module);
        //保存文件到tfs
        String tfsName = tfsManager.saveFile(localFileName,null,FileUtil.getExtensionName(fileName));
        //保存tfs文件名与原始文件名映射关系到数据库
        saveTfsFileName(tfsName,fileName,source,module,uploader);
        //返回文件的tfs ID
        return tfsName;
    }


    /**
     * 保存本地大文件到tfs
     * 文件超过2m调用该方法，最大支持40G
     * @param localFileName  文件本地路径
     * @param fileName
     * @return
     */
    @Override
    public String saveLocalLargeFile(String localFileName, String fileName, String source, String module,String uploader) {
        loger.info("tfs","add","保存本地大文件："+fileName);
        //判断该文件在tfs是否已经存在，如果存在，删除； 判断条件为 sourceCode,moduleCode,fileName,suffix
        processExistTfsFile(fileName,source,module);
        //保存文件到tfs
        String tfsName = tfsManager.saveLargeFile(localFileName,null,FileUtil.getExtensionName(fileName));
        //保存tfs文件名与原始文件名映射关系到数据库
        saveTfsFileName(tfsName,fileName,source,module,uploader);
        //返回文件的tfs ID
        return tfsName;
    }

    /**
     * 读取tfs文件到流
     * @param tfsFileName
     * @return
     */
    @Override
    public OutputStream getFile(String tfsFileName,String suffix)throws Exception{
        loger.info("tfs","read","读取文件："+tfsFileName);
        OutputStream outputStream=new ByteArrayOutputStream();
        Map fileInfor=new HashMap();
        boolean result=tfsManager.fetchFile(tfsFileName,"",outputStream);

        if(result){
            return outputStream;
        }else {
            loger.error("tfs","读取文件失败，文件名"+tfsFileName);
            throw new Exception("读取文件失败，文件名"+tfsFileName);
        }
    }

    /**
     * 读取tfs文件到字节数组
     * @param tfsFileName
     * @param suffix
     * @return
     */
    @Override
    public byte[] getByteFile(String tfsFileName, String suffix) {
        loger.info("tfs","read","读取文件："+tfsFileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean result=tfsManager.fetchFile(tfsFileName,"",outputStream);
        if(result){
            return  outputStream.toByteArray();
        }else {
            loger.error("tfs","读取文件失败，文件名"+tfsFileName);
            return new byte[0];
        }
    }

    /**
     * 逻辑删除tfs文件
     * @param id
     */
    @Override
    public boolean deleteTfsFile(Long id){
        TfsFileName tfsFileName = tfsFileNameManager.getById(id);
        loger.info("tfs","delete","逻辑删除文件："+"sourceCode:"+tfsFileName.getSourceCode()+",moduleCode:"
                +tfsFileName.getModuleCode()+",fileName:"+tfsFileName.getFileName()+"."+tfsFileName.getSuffix());
        //1 隐藏；0显示
        boolean flag=tfsManager.hideFile(tfsFileName.getTfsName(), null, 1);
        if(flag){
            tfsFileName.setStatus(TfsFileConstant.TFS_HIDE);
            tfsFileName.setUpdateTime(new Date());
            tfsFileNameManager.update(tfsFileName);
        }
        return flag;
    }

    /**
     * 保存tfs文件名信息到数据库
     * @param tfsName
     * @param fileName
     * @param source
     * @param module
     * @return
     */
    private int saveTfsFileName(String tfsName,String fileName,String source,String module,String uploader){
        loger.info("tfs","save","保存tfs文件名映射关系到数据库："+fileName);
        TfsFileName tfsFileName = new TfsFileName();
        tfsFileName.setFileName(FileUtil.getFileName(fileName));
        tfsFileName.setSuffix(FileUtil.getExtensionName(fileName));
        tfsFileName.setTfsName(tfsName);
        tfsFileName.setSourceCode(source);
        tfsFileName.setModuleCode(module);
        tfsFileName.setCreateTime(new Date());
        tfsFileName.setStatus(TfsFileConstant.TFS_UN_HIDE);
        tfsFileName.setUploader(uploader);
        return tfsFileNameManager.save(tfsFileName);
    }

    /**
     * tfs已经存在文件处理 如果tfs已存在该文件，调用tfs接口逻辑删除该文件
     * @param fileName
     * @param source
     * @param module
     */
    private boolean processExistTfsFile(String fileName,String source,String module){
        TfsFileName tfsFileName = tfsFileNameManager.findByFileName(FileUtil.getFileName(fileName),FileUtil.getExtensionName(fileName),source,module);
        if(null != tfsFileName){  //if 该文件已存在，在tfs隐藏该文件，从新保存文件到tfs
            //tfs隐藏文件
            return deleteTfsFile(tfsFileName.getId());
        }
        return true;
    }

    /**
     * 反隐藏文件
     * @param id
     * @return

    @Override
    public boolean reveal(Long id) {
        TfsFileName tfsFileName = tfsFileNameManager.getById(id);
        loger.info("tfs","add tfs file","隐藏文件："+tfsFileName.getTfsName());
        //1 隐藏；0显示
        boolean flag=tfsManager.hideFile(tfsFileName.getTfsName(), null, 0);
        if(flag){
            tfsFileName.setStatus(TfsFileConstant.TFS_UN_HIDE);
            tfsFileNameManager.update(tfsFileName);
        }
        return flag;
    }
     */

    /**
     *
     * @return

    @Override
    public int state(String tfsFileName){
        FileInfo fileInfo=tfsManager.statFile(tfsFileName, null);
        int flag=fileInfo.getFlag();
        return flag;
    }
     */

    /**
     * 删除tfs文件
     * @param id
     * @return

    @Override
    public boolean deleteTfsFile(Long id){
        TfsFileName tfsFileName = tfsFileNameManager.getById(id);
        loger.info("tfs","add tfs file","删除文件："+tfsFileName);
        boolean flag=tfsManager.unlinkFile(tfsFileName.getTfsName(), null);
        if(flag){
            tfsFileNameManager.removeById(id);
        }
        return flag;
    }
     */



}
