package com.yinker.tfs.core.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author haoyunfeng
 * @date 2016/5/5
 */
public interface TfsService {

    public String saveFile(byte[] fileBytes,String fileName,String source,String module,String uploader) throws Exception;

    public String saveFile(InputStream inputStream, String fileName, String source, String module,String uploader) throws Exception;

    public String saveLargeFile(InputStream inputStream, String fileName, String source, String module,String uploader) throws Exception;

    public String saveLocalFile(String localFileName,String fileName,String source,String module,String uploader) throws Exception;

    public String saveLocalLargeFile(String localFileName,String fileName,String source,String module,String uploader) throws Exception;

    public OutputStream getFile(String tfsFileName,String suffix) throws Exception;

    public byte[] getByteFile(String tfsFileName,String suffix);

    public boolean deleteTfsFile(Long id);

//    public boolean deleteTfsFile(Long id);

//    private boolean reveal(Long id);

//    public int state(String tfsFileName);
}
