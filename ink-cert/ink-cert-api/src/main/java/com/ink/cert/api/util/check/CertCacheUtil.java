package com.ink.cert.api.util.check;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.cert.api.constant.CacheConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.module.CertInfo;
import com.ink.cert.api.util.CertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 证书缓存工具类
 * Created by aiyungui on 2016/6/22.
 */
@Component("certPropertiesCacheUtil")
public class CertCacheUtil {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(CertCacheUtil.class);
    @Autowired
    private Yedis yedis;

    /**
     * 获取证书信息
     * @param merchantCode 商户编号
     * @param certCode 证书编号
     * @return
     */
    public CertInfo getCertInfo(String merchantCode,String certCode,String propertiesStorePath){
        CertInfo resultCertInfo;
        try{
            final String finalMerchantCode = merchantCode;
            final String finalCertCode = certCode;
            final String finalPropertiesStorePath = propertiesStorePath;

            CacheObject cacheObject = yedis.getObject(CacheConstant.MSG_CERT_KEY,
                    CertUtil.getCertCacheKey(merchantCode, certCode), CertInfo.class, new Reader<Object>() {
                        @Override
                        public Object readerFromDatabase() {
                            CertInfo certInfo = null;
                            try {
                                certInfo = findCertForProperties(finalMerchantCode, finalCertCode, finalPropertiesStorePath);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return certInfo;
                        }
                    });
            resultCertInfo = (CertInfo) cacheObject.getValue();
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"获取缓存信息异常：" + e.getMessage(),e);
            resultCertInfo = null;
        }
        return resultCertInfo;
    }

    /**
     * 从配置文件中获取证书相关信息
     * @param merchantCode
     * @param certCode
     * @param propertiesStorePath
     * @return
     */
    private CertInfo findCertForProperties(String merchantCode, String certCode, String propertiesStorePath) throws Exception{

        String propertiesName = merchantCode+"_"+certCode + ".properties";
        String filePath = CertUtil.getFilePath(propertiesStorePath,propertiesName);
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        String certType  = properties.getProperty("certType");
        String endecryType  = properties.getProperty("endecryType");
        String secretKey  = properties.getProperty("secretKey");
        String arithmeticType  = properties.getProperty("arithmeticType");
        String fileName  = properties.getProperty("fileName");
        String certName  = properties.getProperty("certName");
        String certId  = properties.getProperty("certId");

        CertInfo certInfo = new CertInfo();
        certInfo.setMerchatCode(merchantCode);
        certInfo.setCertCode(certCode);
        certInfo.setEndecryType(endecryType);
        certInfo.setCertName(certName);
        certInfo.setFileName(fileName);
        certInfo.setCertType(certType);
        certInfo.setSecretKey(secretKey);
        certInfo.setArithmeticType(arithmeticType);
        certInfo.setCertId(certId);

        yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,"从配置文件获取证书信息:" +certInfo);
        return certInfo;
    }
}
