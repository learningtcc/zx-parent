package com.yinker.tfs.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yinker.base.log.util.YinkerLogger;
import com.yinker.tfs.client.TFSEntity;
import com.yinker.tfs.core.po.TfsFileName;
import com.yinker.tfs.core.service.ITfsFileNameManager;
import com.yinker.tfs.core.service.TfsService;
import com.yinker.tfs.core.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/tfs")
public class TfsOperateController {

    @Autowired
    private TfsService tfsService;

    @Autowired
    private ITfsFileNameManager tfsFileNameManager;

    protected static final String SUCCESS_JSP= "success";
    private YinkerLogger loger=  YinkerLogger.getLogger(TfsOperateController.class) ;

    @RequestMapping("/toDownload" )
    public String toDownload() {
        return "/download";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,String> uploadByte(@RequestBody byte[] byteArray){
        Map<String,String> resultMap = new HashMap<String, String>();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            TFSEntity tfsEntity = (TFSEntity) objectInputStream.readObject();
            String tfsName = tfsService.saveFile(tfsEntity.getContent(), tfsEntity.getFileName(), tfsEntity.getSource(), tfsEntity.getModule(),tfsEntity.getUploader());
            resultMap.put("result","true");
            resultMap.put("tfsName",tfsName);
            resultMap.put("fileName",tfsEntity.getFileName());
        }catch (IOException e){
            loger.error("tfs","读取tfs字节流失败",e);
            resultMap.put("result","false");
            resultMap.put("errorMsg","读取tfs字节流失败");
        }catch (ClassNotFoundException e){
            loger.error("tfs","tfs文件对象反序列化失败",e);
            resultMap.put("result","false");
            resultMap.put("errorMsg","tfs文件对象反序列化失败");
        }catch (Exception e){
            loger.error("tfs","上传tfs文件失败 ",e);
            resultMap.put("result","false");
            resultMap.put("errorMsg","上传tfs文件失败");
        }
        return resultMap;
    }

    @RequestMapping("/uploadLarge")
    @ResponseBody
    public Map<String,String> uploadLarge(
          InputStream inputStream,
          @RequestHeader String fileName,
          @RequestHeader String sourceCode,
          @RequestHeader String moduleCode,
          @RequestHeader String uploader){
        Map<String,String> resultMap = new HashMap<String, String>();
        try {
            String tfsName = tfsService.saveFile(inputStream, fileName, sourceCode, moduleCode,uploader);
            resultMap.put("result","true");
            resultMap.put("tfsName",tfsName);
            resultMap.put("fileName",fileName);
        }catch (IOException e){
            loger.error("tfs","读取tfs字节流失败",e);
            resultMap.put("result","false");
            resultMap.put("errorMsg","读取tfs字节流失败");
        }catch (ClassNotFoundException e){
            loger.error("tfs","tfs文件对象反序列化失败",e);
            resultMap.put("result","false");
            resultMap.put("errorMsg","tfs文件对象反序列化失败");
        }catch (Exception e){
            loger.error("tfs","上传tfs文件失败 ",e);
            resultMap.put("result","false");
            resultMap.put("errorMsg","上传tfs文件失败");
        }
        return resultMap;
    }


    /**
     * 根据文件名下载文件
     * @param fileName
     * @param sourceCode
     * @param moduleCode
     * @return
     * @throws IOException
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String fileName,String sourceCode,String moduleCode) throws IOException {
        TfsFileName tfsFileName = tfsFileNameManager.findByFileName(FileUtil.getFileName(fileName),FileUtil.getExtensionName(fileName),sourceCode,moduleCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        byte[] content = tfsService.getByteFile(tfsFileName.getTfsName(),FileUtil.getExtensionName(fileName));
        return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
    }


    /**
     * 根据文件名下载文件字节流
     * @param fileName
     * @param sourceCode
     * @param moduleCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/downloadByte")
    public byte[] downloadByte(String fileName,String sourceCode,String moduleCode){
        TfsFileName tfsFileName = tfsFileNameManager.findByFileName(FileUtil.getFileName(fileName),FileUtil.getExtensionName(fileName),sourceCode,moduleCode);
        if( tfsFileName != null){
            return tfsService.getByteFile(tfsFileName.getTfsName(),tfsFileName.getSuffix());
        }
        return "".getBytes();
    }

    /**
     * 根据tfsid下载文件字节流
     * @param tfsName
     * @return
     */
    @ResponseBody
    @RequestMapping("/downloadByTfsId")
    public byte[] downloadByTfsId(String tfsName){
//        TfsFileName tfsFileName = tfsFileNameManager.findByFileName(FileUtil.getFileName(fileName),FileUtil.getExtensionName(fileName),sourceCode,moduleCode);
        if( tfsName != null){
            return tfsService.getByteFile(tfsName,"");
        }
        return "".getBytes();
    }

    @RequestMapping("/getTfsByte")
    public byte[] getTfsByte(String tfsFileName ,String fileName,String suffix){
        return tfsService.getByteFile(tfsFileName,suffix);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,String> deleteByName(String fileName,String sourceCode,String moduleCode){
        Map<String,String> resultMap = new HashMap<String, String>();
        TfsFileName tfsFileName = tfsFileNameManager.findByFileName(FileUtil.getFileName(fileName),FileUtil.getExtensionName(fileName),sourceCode,moduleCode);
        boolean deleteRes = tfsService.deleteTfsFile(tfsFileName.getId());
        if(deleteRes){
            resultMap.put("result","true");
        }else{
            resultMap.put("result","true");
        }
        return resultMap;
    }

}