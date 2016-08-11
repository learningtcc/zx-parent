package com.ink.test.balance;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mrchen on 2016/5/24.
 */
public class DateTest {

    public static void main(String[] args) {
        String timeStr=System.currentTimeMillis()+"";
        Date day=new Date();    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        System.out.println(Long.parseLong("10160524464092504418"));
        //System.out.println(Long.parseLong(df.format(day)+""+timeStr.substring(1)));
    }
}
