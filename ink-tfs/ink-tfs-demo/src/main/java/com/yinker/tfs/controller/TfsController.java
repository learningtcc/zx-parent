package com.yinker.tfs.controller;

import com.yinker.tfs.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author haoyunfeng
 * @date 2016/5/17
 */
@RequestMapping("/tfs")
@Controller
public class TfsController {

    @Autowired
    private TFSOperator tfsOperator;

    protected static final String SUCCESS_JSP= "success";

    @RequestMapping("/toUpload" )
    public String toUpload() {
        return "/upload";
    }

    @RequestMapping("/toDownload" )
    public String toDownload() {
        return "/download";
    }

    @RequestMapping("/toPathUpload" )
    public String toPathUpload() {
        return "/pathUpload";
    }

    /**
     * 通过字节流上传文件到tfs
     * @param request
     * @param response
     * @return
     * @throws IllegalStateException
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping("/uploadByte")
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException ,Exception{
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String,String> tfsMap = new HashMap<String,String>();
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String uploadFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(uploadFileName.trim() !=""){
                        //发送inputStream到tfs服务端
//                        String tfsName = tfsOperator.saveInputStream(uploadFileName,file.getInputStream(),"TfsDemo","demo","admin");
                        //发送byte到tfs服务端
//                        byte[] b = new byte[0];
//                        String tfsName1 = tfsOperator.saveByteToTfsByTime(uploadFileName,file.getBytes(),"TfsDemo","demo","admin");
//                        String tfsName1 = tfsOperator.saveByteToTfsByTime(uploadFileName,file.getBytes(),"TfsDemo","demo","admin");
                        String tfsName = tfsOperator.saveByteToTfsId(uploadFileName,file.getBytes(),"TfsDemo","demo","admin");
                        tfsMap.put(tfsName,uploadFileName);
                    }
                    file.getInputStream().close();
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SUCCESS_JSP);
        modelAndView.addObject("tfs",tfsMap);
        return modelAndView;
    }

    /**
     * 通过文件路径上传到tfs
     * @param filePath
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadFilePath")
    public ModelAndView uploadFilePath(String filePath,String fileName) {
        Map<String,String> tfsMap = new HashMap<String,String>();
        String tfsName = tfsOperator.saveFilePathToTfsByTime(filePath,fileName, "TfsDemo", "demo","admin");
        ModelAndView modelAndView = new ModelAndView();
        if(!tfsName.equals("")){
            tfsMap.put(tfsName,FileUtils.getFileName(filePath));
            modelAndView.setViewName(SUCCESS_JSP);
            modelAndView.addObject("tfs",tfsMap);
        }else {
            //TODO 跳转到错误页面 或者 返回错误信息
        }
        return modelAndView;
    }

    /**
     * 下载文件
     * @param fileName
     * @param sourceCode
     * @param moduleCode
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String fileName , String sourceCode, String moduleCode) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
//        ByteArrayOutputStream outputStream = (ByteArrayOutputStream)tfsService.getFile(tfsFileName,suffix);
        byte[] content = tfsOperator.getTfsByte(fileName,sourceCode,moduleCode);
        return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String fileName , String sourceCode, String moduleCode){
        return tfsOperator.delete(fileName,sourceCode,moduleCode);
    }

}
