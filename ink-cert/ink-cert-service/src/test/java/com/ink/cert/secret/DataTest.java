package com.ink.cert.secret;

import com.ink.cert.api.util.secret.BASE64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;

/**
 * Created by aiyungui on 2016/6/30.
 */
public class DataTest {

    @Test
    public void BASETest(){
        String message  = "Z3Vlc3R8Z3Vlc3R8NjEyMDlkODYwZmJhNDdmNjg4YjUxZWI4MTE2NTg3YTh8MTAuMS41LjE4OXx8eWlua2VyLWNlcnQtd2VifC9jZXJ0L2NlcnRTZXJ2aWNlL3BmeEVuY3J5cHRTZXJ2aWNlLmRvfDF8RDFEOTQ0MzYwMUE3QUUxNDRDODk2MkYyQUVFNDg1RUU";
        try {
            String msg =  BASE64.decrypt(message);
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createFileTest() throws Exception {
        String filePath = "D:\\doc\\nihao.txt";
        File file = new File(filePath);
        FileUtils.writeByteArrayToFile(file,"hello word".getBytes());

    }
}
