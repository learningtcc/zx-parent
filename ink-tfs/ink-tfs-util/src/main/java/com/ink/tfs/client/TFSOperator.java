package com.ink.tfs.client;


import com.ink.base.log.util.YinkerLogger;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haoyunfeng
 * @date 2016/5/17
 */
public class TFSOperator  {

    /** tfs上传url*/
    private String uploadUrl;
    /** tfs下载url*/
    private String downloadUrl;
    /** tfs下载url 根据tfs ID*/
    private String downloadUrlById;
    /** 文档类型 application/octet-stream */
    private String contentType;
    /** 编码格式*/
    private String encoding;
    /** 超时时间*/
    private int timeout;
    /** tfs上传大文件url*/
    private String uploadLargeUrl;
    /** tfs删除文件件url*/
    private String deleteUrl;

    private YinkerLogger loger=  YinkerLogger.getLogger(TFSOperator.class) ;


    /**
     * 发送http请求到tfs服务端，上传文件
     * @param tfsEntity
     * @return
     * @throws Exception
     */
    private Map<String,Object> saveByte(TFSEntity tfsEntity)throws Exception{
        loger.info("tfs","upload","保存文件："+tfsEntity.getFileName());
        //把文件对象序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(tfsEntity);
        }catch (IOException e){
            loger.error("tfs","文件对象序列化失败",e);
            throw new Exception("文件对象序列化失败");
        }
        //调用接口上传文件到tfs服务器
        return HttpClientUtils.execute(getUploadUrl(),getContentType(),getEncoding(),getTimeout(),byteArrayOutputStream.toByteArray());
    }


    /**
     * 上传tfs文件到tfs服务器
     * @param tfsEntity
     * @return 文件名
     * @throws Exception
     */
    private String saveByteToTfs(TFSEntity tfsEntity) throws Exception{

        //调用接口上传文件到tfs服务器
        Map<String,Object> resultMap = saveByte(tfsEntity);
        if(resultMap.get("result").equals("true")){
            return  resultMap.get("fileName").toString();
        }else {
            loger.error("tfs","上传文件到tfs失败");
            throw new Exception("上传文件到tfs失败");
        }
    }

    /**
     * 上传tfs文件到tfs服务器
     * @param tfsEntity
     * @return tfs ID
     * @throws Exception
     */
    private String saveByteToTfsId(TFSEntity tfsEntity) throws Exception{
        Map<String,Object> resultMap = saveByte(tfsEntity);
        if(resultMap.get("result").equals("true")){
            return  resultMap.get("tfsName").toString();
        }else {
            loger.error("tfs","上传文件到tfs失败");
            throw new Exception("上传文件到tfs失败");
        }
    }

    public String saveByteToTfsId(String fileName,byte[] content,String sourceCode,String moduleCode,String uploader) throws Exception{
        TFSEntity tfsEntity = new TFSEntity();
        tfsEntity.setFileName(fileName);
        tfsEntity.setContent(content);
        tfsEntity.setModule(moduleCode);
        tfsEntity.setSource(sourceCode);
        tfsEntity.setUploader(uploader);
        return saveByteToTfsId(tfsEntity);
    }


    /**
     * 上传byte到tfs服务器
     * @param fileName      文件名（包含后缀）
     * @param content       目标文件字节数组
     * @param sourceCode    系统代码
     * @param moduleCode    模块代码
     * @return
     * @throws Exception
     */
    public String saveByteToTfs(String fileName,byte[] content,String sourceCode,String moduleCode,String uploader) throws Exception{
        TFSEntity tfsEntity = new TFSEntity();
        tfsEntity.setFileName(fileName);
        tfsEntity.setContent(content);
        tfsEntity.setModule(moduleCode);
        tfsEntity.setSource(sourceCode);
        tfsEntity.setUploader(uploader);
        return saveByteToTfs(tfsEntity);
    }

    /**
     * 上传byte到tfs服务器
     * @param fileName      文件名（包含后缀）
     * @param content       目标文件字节数组
     * @param sourceCode    系统代码
     * @param moduleCode    模块代码
     * @return
     * @throws Exception
     */
    public String saveByteToTfsByTime(String fileName,byte[] content,String sourceCode,String moduleCode,String uploader) throws Exception{
        return saveByteToTfs(FileUtils.getNameByTime(fileName),content,sourceCode,moduleCode,uploader);
    }


    /**
     * 根据文件路径上传到tfs服务器
     * @param filePath   文件路径
     * @param sourceCode 系统代码
     * @param moduleCode 模块代码
     * @return
     * @throws Exception
     */
    public String saveFilePathToTfs(String fileName,String filePath,String sourceCode,String moduleCode,String uploader){
        loger.info("tfs","upload","按文件路径保存到tfs："+filePath);
        String tfsName = "";
        try {
            tfsName = saveByteToTfs(fileName,FileUtils.toByteArray(filePath),sourceCode,moduleCode,uploader);
        }catch (Exception e){
            loger.error("tfs","保存文件失败",e);
        }
        return tfsName;
    }

    /**
     * 根据文件路径上传到tfs服务器
     * @param filePath   文件路径
     * @param sourceCode 系统代码
     * @param moduleCode 模块代码
     * @return
     * @throws Exception
     */
    public String saveFilePathToTfsByTime(String fileName,String filePath,String sourceCode,String moduleCode,String uploader){
        loger.info("tfs","upload","按文件路径保存到tfs："+filePath);
        return saveFilePathToTfs(FileUtils.getNameByTime(fileName),filePath,sourceCode,moduleCode,uploader);
    }


    /**
     * 上传输入流到tfs服务器
     * @param fileName      文件名（包含后缀）
     * @param inputStream   目标文件输入流
     * @param sourceCode    系统代码
     * @param moduleCode    模块代码
     * @return
     * @throws Exception
     */
    public String saveInputStreamToTfs(String fileName,InputStream inputStream,String sourceCode,String moduleCode,String uploader)throws Exception{
        loger.info("tfs","upload","按文件流格式保存到tfs："+fileName);
        String tfsName = "";
        try {
            tfsName = saveByteToTfs(fileName, IOUtils.toByteArray(inputStream),sourceCode,moduleCode,uploader);
        }catch (Exception e){
            loger.error("tfs","输入流转byte失败",e);
        }
        return tfsName;
    }

    /**
     * 上传输入流到tfs服务器
     * @param fileName      文件名（包含后缀）
     * @param inputStream   目标文件输入流
     * @param sourceCode    系统代码
     * @param moduleCode    模块代码
     * @return
     * @throws Exception
     */
    public String saveInputStream(String fileName,InputStream inputStream,String sourceCode,String moduleCode,String uploader)throws Exception{
        loger.info("tfs","upload","按文件流格式保存到tfs："+fileName);

        TFSEntity tfsEntity = new TFSEntity();
        tfsEntity.setFileName(fileName);
        tfsEntity.setInputStream(inputStream);
        tfsEntity.setModule(moduleCode);
        tfsEntity.setSource(sourceCode);
        tfsEntity.setUploader(uploader);

        Map<String,Object> resultMap = HttpClientUtils.execute(getUploadLargeUrl(),getContentType(),getEncoding(),getTimeout(),tfsEntity);
        inputStream.close();
        if(resultMap.get("result").equals("true")){
            return  resultMap.get("tfsName").toString();
        }else {
            loger.error("tfs","上传文件到tfs失败");
            throw new Exception("上传文件到tfs失败");
        }
    }


    /**
     * 上传输入流到tfs服务器
     * @param fileName      文件名（包含后缀）
     * @param inputStream   目标文件输入流
     * @param sourceCode    系统代码
     * @param moduleCode    模块代码
     * @return
     * @throws Exception
     */
    public String saveInputStreamToTfsByTime(String fileName,InputStream inputStream,String sourceCode,String moduleCode,String uploader)throws Exception{
        loger.info("tfs","upload","按文件流格式保存到tfs："+fileName);
        return saveInputStreamToTfs(FileUtils.getNameByTime(fileName),inputStream,sourceCode,moduleCode,uploader);
    }

    /**
     * 下载文件到字节数组
     * @param fileName      文件名（包含后缀）
     * @param sourceCode    系统代码
     * @param moduleCode    系统代码
     * @return
     * @throws Exception
     */
    public byte[] getTfsByte(String fileName,String sourceCode,String moduleCode){
        loger.info("tfs","download","从tfs下载文件到字节数组："+fileName);
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("fileName",fileName);
        param.put("sourceCode",sourceCode);
        param.put("moduleCode",moduleCode);
        Map<String,Object> resultMap = HttpClientUtils.execute(getDownloadUrl(),param,timeout);
        if(resultMap.get("result").equals("true")){
            return  (byte[]) resultMap.get("resultByte");
        }else {
            loger.error("tfs","上传文件到tfs失败");
            return new byte[0];
        }
    }

    /**
     * 根据tfs ID下载文件到字节流
     * @param tfsName
     * @return
     */
    public byte[] getTfsByteById(String tfsName){
        loger.info("tfs","download","从tfs下载文件到字节数组：ID:"+tfsName);
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("tfsName",tfsName);
        Map<String,Object> resultMap = HttpClientUtils.execute(getDownloadUrlById(),param,timeout);
        if(resultMap.get("result").equals("true")){
            return  (byte[]) resultMap.get("resultByte");
        }else {
            loger.error("tfs","上传文件到tfs失败");
            return new byte[0];
        }
    }


    /**
     * 删除tfs文件
     * @param fileName
     * @param sourceCode
     * @param moduleCode
     * @return
     */
    public String delete(String fileName,String sourceCode,String moduleCode){
        loger.info("tfs","download","从tfs下载文件到字节数组："+fileName);
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("fileName",fileName);
        param.put("sourceCode",sourceCode);
        param.put("moduleCode",moduleCode);
        Map<String,Object> resultMap = HttpClientUtils.execute(getDeleteUrl(),param,timeout);
        if(resultMap.get("result").equals("true")){
            return  "true";
        }else {
            loger.error("tfs","上传文件到tfs失败");
            return "false";
        }
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getUploadLargeUrl() {
        return uploadLargeUrl;
    }

    public void setUploadLargeUrl(String uploadLargeUrl) {
        this.uploadLargeUrl = uploadLargeUrl;
    }

    public String getDownloadUrlById() {
        return downloadUrlById;
    }

    public void setDownloadUrlById(String downloadUrlById) {
        this.downloadUrlById = downloadUrlById;
    }
}
