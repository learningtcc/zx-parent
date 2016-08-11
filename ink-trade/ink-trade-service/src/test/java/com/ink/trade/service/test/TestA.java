package com.ink.trade.service.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.trade.api.model.in.SignApplyInput;

public class TestA {
    @Test
  public void test1() throws IOException{
        File f=new File("d:/cmbc/phoneNo.txt");
        f.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);
      for(Long i=1l;i<1000000;i++){
          Long a=18730000000l;
          System.out.println(a+i);
      }
  }
    
    @Test
  public void testOrderId() throws IOException{
        File f=new File("d:/cmbc/orderid.txt");
        f.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);
      for(Long i=1l;i<1000000;i++){
          String a="000";
          System.out.println(a+i);
      }
  }
    
    @Test
    public void testCardNo() throws IOException{
//          File f=new File("d:/cmbc/cardNo.txt");
//          f.createNewFile();
//          FileOutputStream fileOutputStream = new FileOutputStream(f);
//          PrintStream printStream = new PrintStream(fileOutputStream);
//          System.setOut(printStream);
        for(Long i=1l;i<100;i++){
            Long a=6214830125435303l;
            System.out.println(a+i);
        }
    }
    @Test
    public void testIdNo() throws IOException{
//          File f=new File("d:/cmbc/idNo.txt");
//          f.createNewFile();
//          FileOutputStream fileOutputStream = new FileOutputStream(f);
//          PrintStream printStream = new PrintStream(fileOutputStream);
//          System.setOut(printStream);
        for(Long i=1l;i<100;i++){
            String a="1301521445411224";
            System.out.println(a+i);
        }
    }
    @Test
    public void test2(){
        for(int i=1;i<232;i++){
            System.out.println("zhangsan"+i);
        }
    }
    /**
     * 
     */
    @Test
    public void test3(){
        SignApplyInput a=new SignApplyInput();
        a.setMerchantId("2");
        a.setBankShort("ZSYH");
        a.setTradeCode("d");
        a.setCardNo("100000");
        a.setIdNo("111");
        a.setPayType("d");
        a.setPhoneNo("s");
        a.setRealName("dds");
        a.setSignOrderId("dd");
        a.setUserId("111");
      System.out.println(JSONObject.toJSONString(a));  
    }
    @Test
    public void test4() throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d1 = df.parse("2004-03-26 13:31:40");
//        Date d2 = df.parse("2004-03-26 13:30:24");
//        long diff = d1.getTime() - d2.getTime();
//        long days = diff/1000;
      Date d1 = df.parse("2004-03-26 13:31:40");
      Date d2 = df.parse("2004-03-26 13:30:24");
       // DateUtil.getSenconds(d1, d2);
        System.out.println(DateUtil.getSenconds(d2, d1));
    }
    @Test
    public void test5(){
        for(int i=0;i<100;i++){
            System.out.println("2000061"+i);
        }
    }
}
