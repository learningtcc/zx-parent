package com.ink.cert.secret;

import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.module.MsgOutput;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 数据加解密测试
 * Created by aiyungui on 2016/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-applicationContext.xml")
public class DataSecretTest {

    @Autowired
    private DataSecurityClient dataSecurityClient;

    @Test
    public void md5Test(){
       String message = "aiyungui";
        String merchantCode = "1001";
        String certCode = "1001001";

        MsgOutput msgOutput = dataSecurityClient.dataEncrypt(merchantCode, certCode, message);

        System.out.println(msgOutput);
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void desEncryptTest(){
        String message = "aiyungui";
        String merchantCode = "1001";
        String certCode = "1001004";

        MsgOutput msgOutput = dataSecurityClient.dataEncrypt(merchantCode, certCode, message);

        System.out.println(msgOutput);
    }
    @Test
    public void desDecryptTest(){
        String message = "xNr5bJ5C9zAJXIHmt/M63Q==";
        String merchantCode = "1001";
        String certCode = "1001004";

        MsgOutput msgOutput = dataSecurityClient.dataDecrypt(merchantCode, certCode, message);

        System.out.println(msgOutput);
    }
    @Test
    public void base64EncryptTest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("member_id","100000276");
        jsonObject.put("orig_trans_id","CF300003485111466653235545");
        jsonObject.put("terminal_id","100000994");
        jsonObject.put("trade_date","20160624160849");
        jsonObject.put("trans_serial_no","999122405294070000");
        jsonObject.put("txn_sub_type","06");

        String message = jsonObject.toString();
//        String message = "{\"bind_id\":\"201604271949318660\",\"member_id\":\"100000276\",\"sms_code\"" +
//                ":\"\",\"terminal_id\":\"100000994\",\"trade_date\":\"20160621160312\",\"trans_id\":\"11211980" +
//                "9827290000\",\"trans_serial_no\":\"112119810037000000\",\"txn_amt\":\"1\",\"txn_sub_type\":\"04\"}";
        String merchantCode = "1001";
        String certCode = "1001002";

        MsgOutput msgOutput = dataSecurityClient.dataEncrypt(merchantCode, certCode, message);

        System.out.println(msgOutput);
    }

    @Test
    public void base64DecryptTest(){
        String message = "eyJiaW5kX2lkIjoiMjAxNjA0MjcxOTQ5MzE4NjYwIiwibWVtYmVyX2lkIjoiMTAwMDAwMjc2Iiwi\n" +
                "c21zX2NvZGUiOiIiLCJ0ZXJtaW5hbF9pZCI6IjEwMDAwMDk5NCIsInRyYWRlX2RhdGUiOiIyMDE2\n" +
                "MDYyMTE2MDMxMiIsInRyYW5zX2lkIjoiMTEyMTE5ODA5ODI3MjkwMDAwIiwidHJhbnNfc2VyaWFs\n" +
                "X25vIjoiMTEyMTE5ODEwMDM3MDAwMDAwIiwidHhuX2FtdCI6IjEiLCJ0eG5fc3ViX3R5cGUiOiIw\n" +
                "NCJ9";
        String merchantCode = "1001";
        String certCode = "1001002";

        MsgOutput msgOutput = dataSecurityClient.dataDecrypt(merchantCode, certCode, message);

        System.out.println(msgOutput);
    }

    @Test
    public void shaTest(){
//        String message = "aiyungui";
        String message = "9da98175a5cc912a23bdd23c59a93e808459531fb0a33d621d3f5126c136dfd4";
        String merchantCode = "1001";
        String certCode = "1001003";

//        MsgOutput msgOutput = dataSecurityClient.dataEncrypt(merchantCode, certCode, message);
        MsgOutput msgOutput = dataSecurityClient.dataEncrypt(merchantCode, certCode, message);
        System.out.println(msgOutput);
    }

    @Test
    public void certEncryptTest(){
        String message = "aiyungui";
        String merchantCode = "1002";
        String certCode = "1002003";

        MsgOutput msgOutput = dataSecurityClient.dataEncrypt(merchantCode, certCode, message);

        System.out.println(msgOutput);
    }

    @Test
    public void certDecryptTest(){
        String message = "5054c24ec9729f7fbdfce6fcbd5d2bf3c5ec4fcb3117c1a2545d5ac8250317f3ded256e6d40e954a9b2a167d412" +
                "bb1823502f3b2eadd1eeb779d582d75fbfea17e281842b3671d274c72bc2d446a45d652748e22d8621767bbfe168065a5ce" +
                "3c55b6a81490a49e6c6fcf56f8195ada390342fb169d076118fc38e02a49cda5577055370b8e29fd1ecc2ff9625736405dae08" +
                "f4fcebcb6dbf9bf94d69b05b138000c56546c0cc8f999f49f74da59c89370a22b4df70606dfdbda67dcdbdf8971fa82b2e271340" +
                "fce6cadad7bd34a947ef0c605888d241f1bfe868e97de18d97d3afd6f4c694be87bf5204643ff8524d052419ab7c25e75eeaede9" +
                "a4eddf6baf2f17685675d9aafb96cb9a38b11b484cfc42a43a7c640f7f258afc90fe3d34fb97525c798ad0a59090d3101a13280b" +
                "8919fe322b693d319877491cc0bbd93df32dfc1c39944241cf8f5e7cad3e3304e71ccaf7e4aa109a1b9019a737414d60eb7de7b" +
                "2b9eee7b85199438eb0ffe136f8210f464cd6162a4d690aa65702957639fc3db63b4114dd120c330023bd41cd3b6609ea84775" +
                "03630446a7c4fcf889c3fbcc891edb7319ce986b9e36b7ccba2268588b090731987d1690dcd12cfec943cfea59547d56bde281e" +
                "08a3188ed3f33120f8cc5f10cedc81e94d2c3b5ed3bf3a973180f6d84d7afa3c5cc1bee01159a6162c342bb9ebeb0dc236ddb307ab1c35f8";
        String merchantCode = "1002";
        String certCode = "1002001";

        MsgOutput msgOutput = dataSecurityClient.dataDecrypt(merchantCode, certCode, message);

        System.out.println(msgOutput);
    }

    private Object base64(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("member_id","100000276");
        jsonObject.put("orig_trans_id","CF300003485111466653235545");
        jsonObject.put("terminal_id","100000994");
        jsonObject.put("trade_date","20160624160849");
        jsonObject.put("trans_serial_no","999122405294070000");
        jsonObject.put("txn_sub_type","06");

        String message = jsonObject.toString();

        String merchantCode = "1001";
        String certCode = "1001002";

        MsgOutput msgOutput = dataSecurityClient.dataEncrypt(merchantCode, certCode, message);
        return msgOutput.getMessage();
    }


}
