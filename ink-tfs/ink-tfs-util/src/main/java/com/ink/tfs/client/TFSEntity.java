package com.ink.tfs.client;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author haoyunfeng
 * @date 2016/5/17
 */
public class TFSEntity implements Serializable{

    private String fileName;
    private String source;
    private String module;
    private byte[] content;
    private InputStream inputStream;
    private String uploader;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
