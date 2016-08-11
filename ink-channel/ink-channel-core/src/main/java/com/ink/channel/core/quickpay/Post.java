package com.ink.channel.core.quickpay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;

public class Post {
    private static KeyStore ks=null;
    /**
     * 向指定URL发送POST方法的请求vpos123
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     * @throws ConnectTimeoutException
     * @throws SocketTimeoutException
     *             ,
     * @throws Exception
     */
    public static String sendPost(String url, String tr1XML) throws ConnectTimeoutException, SocketTimeoutException,
                    Exception {
        System.setProperty("jsse.enableSNIExtension", "false");
        OutputStream out = null;

        String respXml = "";
        String respXmlCut = "";
        String respXmlCut2 = "";
        // 获取证书流
//        InputStream in = Post.class.getClassLoader().getResourceAsStream("quickpay/81231006211010390.jks");

        // 访问Java密钥库，JKS是keytool创建的Java密钥库，保存密钥。
//        KeyStore ks = KeyStore.getInstance("JKS");//
//        ks.load(in, "vpos123".toCharArray());
        KeyStore ks=Post.getKeyStore();
        // 创建用于管理JKS密钥库的密钥管理器
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        // 初始化证书
        kmf.init(ks, "vpos123".toCharArray());

        // 同位体验证信任决策源//同位体验证可信任的证书来源
        TrustManager[] tm = { new MyX509TrustManager() };

        // 初始化安全套接字
        SSLContext sslContext = SSLContext.getInstance("SSL");
        // 初始化SSL环境。第二个参数是告诉JSSE使用的可信任证书的来源，设置为null是从javax.net.ssl.trustStore中获得证书。
        // 第三个参数是JSSE生成的随机数，这个参数将影响系统的安全性，设置为null是个好选择，可以保证JSSE的安全性。
        sslContext.init(kmf.getKeyManagers(), tm, null);

        // 根据上面配置的SSL上下文来产生SSLSocketFactory,与通常的产生方法不同
        SSLSocketFactory factory = sslContext.getSocketFactory();

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
            // 创建安全的连接套接字
            conn.setSSLSocketFactory(factory);
            // 发送POST请求必须设置如下两行,使用 URL 连接进行输出、入
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置URL连接的超时时限
            conn.setReadTimeout(100000);

            // 设置通用的请求属性 812051545110011
            String authString = "812310062110103" + ":" + "vpos123";
            String auth = "Basic " + Base64Binrary.encodeBase64Binrary(authString.getBytes());
            conn.setRequestProperty("Authorization", auth);

            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            // 发送请求参数
            out.write(tr1XML.getBytes());
            // flush 输出流的缓冲
            out.flush();

            // 得到服务端返回
            InputStream is = conn.getInputStream();
            String reqData = "";
            if (is != null && !"".equals(is)) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] receiveBuffer = new byte[2048];// 缓冲区长度
                int readBytesSize = is.read(receiveBuffer);// 读取数据长度，InputStream要读取的数据长度一定要小于等于缓冲区中的字节数
                System.out.println("readBytesSize：" + readBytesSize);

                while (readBytesSize != -1) {// 判断流是否位于文件末尾而没有可用的字节
                    bos.write(receiveBuffer, 0, readBytesSize);// 从receiveBuffer内存处的0偏移开始写，写与readBytesSize长度相等的字节
                    readBytesSize = is.read(receiveBuffer);

                }
                reqData = new String(bos.toByteArray(), "UTF-8");// 编码后的tr2报文
            }
            // System.out.println("tr2报文："+reqData);
            // respXml= ParseUtil.parseXML(reqData);//给解析XML的函数传递快钱返回的TR2的XML数据流
            respXml = reqData.replaceAll("\"", "\'");
            System.out.println("tr2报文转换之后：" + respXml);
            respXmlCut = respXml
                            .replace("<?xml version='1.0' encoding='UTF-8' standalone='yes'?><MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'><version>1.0</version>",
                                            "");
            respXmlCut2 = respXmlCut.replace("</MasMessage>", "");
            // System.out.println("获取应答码："+respXml.substring(respXml.indexOf("<responseCode>")+14,respXml.indexOf("</responseCode>")));
            // System.out.println("tr2报文剪切之后："+respXmlCut2);
        } catch (ConnectTimeoutException connectTimeoutException) {
            throw connectTimeoutException;
        } catch (SocketTimeoutException socketTimeoutException) {
            throw socketTimeoutException;
        } catch (ConnectException connectException) {
            throw new ConnectTimeoutException();
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
            throw e;
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            if (out != null) {
                out.close();
            }
            // if (in != null){in.close();}
        }
        return respXml;
    }

    private static KeyStore getKeyStore()  {
        if (ks == null) {
            synchronized (Post.class) {
                if (ks == null) {
                    // 获取证书流
                    InputStream in = Post.class.getClassLoader().getResourceAsStream("quickpay/81231006211010390.jks");
                    // 访问Java密钥库，JKS是keytool创建的Java密钥库，保存密钥。
                    try {
                        ks = KeyStore.getInstance("JKS");//
                        ks.load(in, "vpos123".toCharArray());
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (CertificateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (KeyStoreException e) {
                        e.printStackTrace();
                    }finally{
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ks;
    }

    public static void main(String[] args) throws Exception {

        // 密码
        String password = "123456";// vpos123
        // 本地起的https服务
        String httpsUrl = "https://sandbox.99bill.com:9445/cnp/purchase";
        // 传输文本
        String xmlStr = "<?xml version='1.0' encoding='UTF-8'?><MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'><version>1.0</version><TxnMsgContent><txnType>PUR</txnType><interactiveStatus>TR1</interactiveStatus><cardNo>4380880000000007</cardNo><expiredDate>0117</expiredDate><cvv2>222</cvv2><amount>100</amount><merchantId>812051545110011</merchantId><terminalId>00006034</terminalId><entryTime>20151015175458</entryTime><idType>0</idType><cardHolderId>320981198207085841</cardHolderId><externalRefNumber>2015101787575489</externalRefNumber><key>cellPhone</key><value>13655163768</value><tr3Url>http://localhost:8090/CNP/resultTr3.jsp</tr3Url></TxnMsgContent></MasMessage>";
        // 发起请求
        Post.sendPost(httpsUrl, xmlStr);
        Post.sendPost(httpsUrl,xmlStr);
    }
}