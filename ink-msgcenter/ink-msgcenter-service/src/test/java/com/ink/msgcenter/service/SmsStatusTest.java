package com.ink.msgcenter.service;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aiyungui on 2016/5/20.
 */
public class SmsStatusTest {

    @Test
    public void matchesTest(){

        String mobile  = "授予{先生},姓名${name},性别${sex}";
        String[] params = new String[2];
        params[0] = "aiyungui";
        params[1] = "man";

        Pattern pattern = Pattern.compile("\\$\\{");
        Matcher matcher =  pattern.matcher(mobile);
        int count = 0;
        char[] chars = mobile.toCharArray();
        while (matcher.find()){
//            String str = matcher.group();
            int start = matcher.start();

//            System.out.println(str);
            boolean isEnd = false;
            int mark;
            StringBuilder sb = new StringBuilder();
            for(mark = start; mark < chars.length;mark++){
                sb.append(chars[mark]);
                if (chars[mark] == '}'){
                    isEnd = true;
                    break;
                }
            }
            if (isEnd){
                mobile = mobile.replace(sb.toString(),params[count++]);
            }
        }

        System.out.println(mobile);
    }
}
