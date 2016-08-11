package com.ink.cert.api.client;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IpUtils;
import com.ink.cert.api.constant.CertConfigConstant;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.dubbo.DataSecretService;
import com.ink.cert.api.module.MsgOutput;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据安全调用端
 *   提供数据加解密服务，数据加解密分为2方式
 *      |-默认访问证书中心提供的dubbo服务进行数据加解密,可在配置中心配置
 *      |-在证书中心提供dubbo出现问题的情况下，访问本地提供加解密服务
 * Created by aiyungui on 2016/7/5.
 */
@Component("dataSecurityClient")
public class DataSecurityClient {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(DataSecurityClient.class);
    @Autowired
    private DataSecretService dataSecretService;
    @Autowired
    private CustomizedPropertyConfigurer customizedPropertyConfigurer;
    @Autowired
    private LocaleDataSecretService localeDataSecretService;

     /**
     * 数据加密
     * @param merchantCode 商户编号
     * @param certCode 证书编号
     * @param message 报文
     * @return 消息处理结果
     */
     public MsgOutput dataEncrypt(String merchantCode, String certCode, String message){

        MsgOutput msgOutput = null;
      try{
          //获取参数判断使用的加密方式
          boolean useFlag = checkDataSecretFlag();
          if (useFlag){
              try{
                  yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE,"连接证书中心加密");
                  msgOutput = dataSecretService.dataEncrypt(merchantCode,certCode,message);
              }catch (Exception e){
                  yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE,
                          "证书加密异常，将使用本地证书加密方式。MerchantCode:"+merchantCode+",certCode:" + certCode+",message:" + message,e,null);
                  msgOutput = localeDataSecretService.dataEncrypt(merchantCode,certCode,message);
              }
          }else{
              yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE,"使用本地加密");
              msgOutput = localeDataSecretService.dataEncrypt(merchantCode,certCode,message);
          }
      }catch (Exception e1){
          msgOutput = new MsgOutput();
          msgOutput.setResultCode(ResultConstant.EXECUTE_FAILURE);
          msgOutput.setResultMsg("证书加密异常");
          msgOutput.setMessage(message);
          yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE,
                  "证书加密异常，MerchantCode:"+merchantCode+",certCode:" + certCode+",message:" + message,e1,null);
      }

       return msgOutput;
    }

    /**
     * 数据解密
     * @param merchantCode 商户编号
     * @param certCode 证书编号
     * @param message 报文
     * @return 消息处理结果
     * */
    public MsgOutput dataDecrypt(String merchantCode,String certCode,String message){

        //初步参数验证
        MsgOutput msgOutput = null;
        try{
            //获取参数判断使用的解密方式
            boolean useFlag = checkDataSecretFlag();
            if (useFlag){
                try{
                    yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE,"连接证书中心解密");
                    msgOutput = dataSecretService.dataDecrypt(merchantCode, certCode, message);
                }catch (Exception e){
                    yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE,
                            "证书加密异常，将使用本地证书解密方式。MerchantCode:"+merchantCode+",certCode:" + certCode+",message:" + message,e,null);
                    msgOutput = localeDataSecretService.dataDecrypt(merchantCode, certCode, message);
                }
            }else{
                yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE,"使用本地解密");
                msgOutput = localeDataSecretService.dataDecrypt(merchantCode,certCode,message);
            }
        }catch (Exception e1){
            msgOutput = new MsgOutput();
            msgOutput.setResultCode(ResultConstant.EXECUTE_FAILURE);
            msgOutput.setResultMsg("证书解密异常");
            msgOutput.setMessage(message);
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_DECRYPT_CODE,
                    "证书解密异常，MerchantCode:"+merchantCode+",certCode:" + certCode+",message:" + message,e1,null);
        }

        return msgOutput;
    }

    /**
     * 检查证书使用方式
     * @return
     */
    private boolean checkDataSecretFlag() {
        //是否全部都使用证书中心加解密 0表示是1表示否 默认为0
        String ifAllUsedCertCenter = customizedPropertyConfigurer.getProperty(CertConfigConstant.IF_ALL_USED_CERT_CENTER);
        if (StringUtils.isBlank(ifAllUsedCertCenter)){
            return true;
        }
        if ("1".equals(ifAllUsedCertCenter)){
            return false;
        }else{
            //判断当前服务器ip是否使用证书中心加解密
            String serviceIp = IpUtils.getLocalAddress();
            String filterIps = customizedPropertyConfigurer.getProperty(CertConfigConstant.FILTER_IPS);
            String[] ips = filterIps.split(",");
            for (String ip: ips){
                if (serviceIp.equals(ip)){
                    return false;
                }
            }
        }
        return true;
    }
}
