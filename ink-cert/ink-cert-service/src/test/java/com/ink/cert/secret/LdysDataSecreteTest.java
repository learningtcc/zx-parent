package com.ink.cert.secret;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ink.cert.api.module.MsgOutput;
import com.ink.cert.api.dubbo.DataSecretService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 联动优势加解密测试
 * Created by aiyungui on 2016/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-applicationContext.xml")
public class LdysDataSecreteTest {

    @Autowired
    private DataSecretService dataSecretService;

    /**
     * 联动优势加密
     */
    @Test
    public void encryptTest(){

        Map<String,Object> msgMap = new HashMap<>();
        msgMap.put("service", "pay_confirm");//接口名称
        msgMap.put("charset", "UTF-8");//参数字符编码集
        msgMap.put("mer_id", "100311");//商户编号
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

        String merchantCode = "1002";
        String certCode = "1002005";

       MsgOutput msgOutput = dataSecretService.dataEncrypt(merchantCode, certCode, message);
        System.out.println(msgOutput);
    }

    @Test
    public void decryptTest(){

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
        msgMap.put("mer_id", "100311");//参数字符编码集

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

//        String message = JSON.toJSON(paramMap).toString();
        String merchantCode = "1002";
        String certCode = "1002005";
        String message = "{\"certInfo\":{\"Encrypt.Paramters\":\"card_id,valid_date,cvv2,pass_wd,identity_code,card_holder,recv_account,recv_user_name,identity_holder,identityCode,cardHolder,mer_cust_name,account_name,bank_account,endDate\",\"crtCertCode\":\"ldyscrt01\",\"crtMerchantCode\":\"1000000000002\",\"decryptFlag\":\"platNotifyData\",\"plat.pay.product.name\":\"spay\",\"plat.url\":\"http://pay.soopay.net\"},\"ldysParam\":{\"mer_id\":\"1000000000002\",\"message\":{\"amount\":\"1\",\"amt_type\":\"RMB\",\"charset\":\"UTF-8\",\"error_code\":\"0000\",\"media_id\":\"13520535920\",\"media_type\":\"MOBILE\",\"mer_date\":\"20160712\",\"mer_id\":\"3660\",\"order_id\":\"101137887913130000\",\"pay_date\":\"20160712\",\"pay_seq\":\"4403940112758590\",\"pay_type\":\"DEBITCARD\",\"service\":\"pay_result_notify\",\"settle_date\":\"20160712\",\"sign\":\"noLIKPFHJVtJT37lN0KkzmGSYnqmRLfLaIfWyZhrcIT3q8gQFSx3EqMHPlYedGrccwUdsBDlMFlZh7bPJ0l9CvDebOkPgDu7gsQySdVob2YkXe/e/4+l3uSaJXpjQSyyUlAahVFLNaOlr+gwU1TfXn2S93HIRUQKX7NfK5WHT68=\",\"sign_type\":\"RSA\",\"trade_no\":\"3607121413736172\",\"trade_state\":\"TRADE_SUCCESS\",\"version\":\"4.0\"}}}";
        MsgOutput msgOutput = dataSecretService.dataDecrypt(merchantCode, certCode, message);
        System.out.println(msgOutput);
    }

}
