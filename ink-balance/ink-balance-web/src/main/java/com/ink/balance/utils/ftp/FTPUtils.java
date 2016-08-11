/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年5月13日 下午5:02:23
 */
package com.ink.balance.utils.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @Description ftp工具类
 * @author xuguoqi
 * @date 2016年5月13日 下午5:02:23
 */
public class FTPUtils {

    /**
     *
     * @Description 文件上传
     * @author xuguoqi
     * @date 2016年5月13日 下午5:10:03
     * @param input
     * @param fileName
     * @param hostname
     * @param username
     * @param password
     * @param port
     */
    public static void uploadFile(InputStream input, String fileName, String hostname, String username, String password, int port) {
        // 创建ftp客户端
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            // 链接ftp服务器
            ftpClient.connect(hostname, port);
            // 登录ftp
            ftpClient.login(username, password);
            int reply = ftpClient.getReplyCode();
            System.out.println(reply);
            // 如果reply返回230就算成功了，如果返回530密码用户名错误或当前用户无权限下面有详细的解释。
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                input.close();
                throw new RuntimeException("登录ftp服务失败");
            }
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            // ftpClient.makeDirectory("path");//在root目录下创建文件夹
            //String remoteFileName = System.currentTimeMillis() + "_" + fileName;
            ftpClient.storeFile(fileName, input);// 文件你若是不指定就会上传到root目录下
            input.close();
            ftpClient.logout();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

        }
    }

}
