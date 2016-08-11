package com.ink.admin.cert.controller.dubbo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.module.MsgOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 证书服务测试
 * Created by aiyungui on 2016/6/27.
 */
@RequestMapping("certService")
@Controller("certDubboService")
public class CertServiceController extends BaseController {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(CertServiceController.class);
    @Autowired
    private DataSecurityClient dataSecurityClient;

    /**
     *证书加密
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/encryptService")
    public MsgOutput encryptService(String merchantCode,String certCode){

        long starTime = System.currentTimeMillis();
        String message = "message";
        MsgOutput msgOutput= dataSecurityClient.dataEncrypt(merchantCode,certCode,message);
        long endTime = System.currentTimeMillis();
        yinkerLogger.info(merchantCode + ":" + certCode +"加密消耗时间:"+(endTime - starTime));
        return msgOutput;
    }

    /**
     * 证书解密
     * @param merchantCode
     * @param certCode
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/decryptService")
    public MsgOutput decryptService(String merchantCode,String certCode,String message){
        long starTime = System.currentTimeMillis();
        MsgOutput msgOutput = dataSecurityClient.dataDecrypt(merchantCode,certCode,message);
        long endTime = System.currentTimeMillis();
        yinkerLogger.info(merchantCode + ":" + certCode +"加密消耗时间:"+(endTime - starTime));
        return msgOutput;
    }

    /**
     * pfx证书加密
     * @param merchantCode
     * @param certCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/pfxEncryptService")
    public MsgOutput pfxEncryptService(String merchantCode,String certCode){
        long starTime = System.currentTimeMillis();
        String message = "bWVzc2FnZQ==";
        MsgOutput msgOutput1= dataSecurityClient.dataEncrypt(merchantCode,certCode,message);
        long endTime = System.currentTimeMillis();
        yinkerLogger.info(merchantCode + ":" + certCode +"加密消耗时间:"+(endTime - starTime));
        return msgOutput1;
    }

    @ResponseBody
    @RequestMapping(value="/ldysEncryptService")
    public MsgOutput ldysEncryptService(String merchantCode,String certCode){
        Map<String,Object> msgMap = new HashMap<>();
        msgMap.put("service", "pay_confirm");//接口名称
        msgMap.put("charset", "UTF-8");//参数字符编码集
        msgMap.put("mer_id", "1003");//商户编号
        msgMap.put("sign_type", "RSA");//签名方式暂只支持RSA必须大写
        msgMap.put("res_format", "STRING"); // 响应数据格式
        msgMap.put("version", "4.0");// 版本号
        msgMap.put("trade_no", "1234567890");// U付交易号
        msgMap.put("pay_category", "02");// 支付类别 02 借记卡
        msgMap.put("verify_code", "3897");//验证码
        msgMap.put("media_id", "18618180101");//  媒介值  用户在银行预留手机号码
        msgMap.put("media_type", "MOBILE");//媒介类型
        msgMap.put("card_id", "123455667788");//  卡号
        msgMap.put("identity_type", "IDENTITY_CARD");//  证件类型   IDENTITY_CARD（身份证）
        msgMap.put("identity_code", "321312312");// 证件号
        msgMap.put("card_holder", "ZHANGSAN");//  持卡人姓名
        msgMap.put("user_ip", "123.123.123.123");// 用户IP地址

        Map<String,Object> certInfoMap = new HashMap<>();
        certInfoMap.put("plat.url","http://pay.soopay.net");
        certInfoMap.put("plat.pay.product.name","spay");
        certInfoMap.put("Encrypt.Paramters","card_id,valid_date,cvv2,pass_wd,identity_code,card_holder,recv_account,recv_user_name,identity_holder,identityCode,cardHolder,mer_cust_name,account_name,bank_account,endDate");
        certInfoMap.put("crtMerchantCode","1002");
        certInfoMap.put("crtCertCode","1002004");
        certInfoMap.put("encryptFlag", "makeReqDataByPost");//接口名称  makeReqDataByPost|merNotifyResData

        Map<String, Map<String, Object>> paramMap = Maps.newHashMap();
        paramMap.put("ldysParam",msgMap);
        paramMap.put("certInfo",certInfoMap);

        String message = JSON.toJSON(paramMap).toString();

        MsgOutput msgOutput = dataSecurityClient.dataEncrypt(merchantCode, certCode, message);
        System.out.println(msgOutput);
        return msgOutput;
    }

    @ResponseBody
    @RequestMapping(value="/ldysDecryptService")
    public MsgOutput ldysDecryptService(String merchantCode,String certCode){
        String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" +
                "<html>\n" +
                "  <head>\n" +
                "\t<META NAME=\"MobilePayPlatform\" CONTENT=\"amount=&amt_type=&mer_date=20160229&mer_id=3660&order_id=20160627151724dsaasd121&pay_date=&ret_code=00060760&ret_msg=订单不存在&settle_date=&sign_type=RSA&trade_no=&trade_state=&version=4.0&sign=kdAcpARXeglvqY6BY9cuXnaYsVj/XjHNf7NVlBiqMCUfZGnjuil4ezLzh4+d8phkop5V+bHmFIemhtCCJm75ymEMsxZBIoUn0MwE0Lr5WvKZVZSXXC1p2PV4HJKGopjM9c8+DuP2uGACQj4xcoWCBtABFSWk0ktwxi0nqHp/5ew=\">\n" +
                "  </head>\n" +
                "  <body>\n" +
                "  </body>\n" +
                "</html>";

        Map<String,Object> msgMap = new HashMap<>();
        msgMap.put("message", html);//参数字符编码集

        Map<String,Object> certInfoMap = new HashMap<>();
        certInfoMap.put("plat.url","http://pay.soopay.net");
        certInfoMap.put("plat.pay.product.name","spay");
        certInfoMap.put("Encrypt.Paramters","card_id,valid_date,cvv2,pass_wd,identity_code,card_holder,recv_account,recv_user_name,identity_holder,identityCode,cardHolder,mer_cust_name,account_name,bank_account,endDate");
        certInfoMap.put("crtMerchantCode","1002");
        certInfoMap.put("crtCertCode","1002004");
        certInfoMap.put("decryptFlag", "resData");//接口名称  resData|resDataByMeta|platNotifyData

        Map<String, Map<String, Object>> paramMap = Maps.newHashMap();
        paramMap.put("ldysParam",msgMap);
        paramMap.put("certInfo",certInfoMap);

        String message = JSON.toJSON(paramMap).toString();

        MsgOutput msgOutput = dataSecurityClient.dataDecrypt(merchantCode, certCode, message);
        return msgOutput;
    }

    /**
     * certTest
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/certTest")
    public String certTest(){

        return "1";
    }
}
