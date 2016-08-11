package com.ink.balance.utils.ftp;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * FTP配置中心
 * @author bo.chen
 * @date 2016年07月20日 下午12:03:34
 */

@Component("ftpConfigCenter")
public class FTPConfigCenter {
    @Value("#{ftpConfig}")
    private Map<String,String> ftpConfig;

    public Map<String, String> getFtpConfig() {
        return ftpConfig;
    }

    public void setFtpConfig(Map<String, String> ftpConfig) {
        this.ftpConfig = ftpConfig;
    }
}
