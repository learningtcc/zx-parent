package com.taobao.common.tfs;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;


public class TfsLoadResource  {

    private final static Document tfsTestConf = initConf();
    private static Document initConf ()  {
        try {
            File f = new File("src/test/resources/test_conf.xml");
            SAXReader saxReader = new SAXReader();
            return saxReader.read(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getString(String nodeName, String attrName) {
        Node node = tfsTestConf.selectSingleNode("//resources/resource[@id='" + nodeName + "']");
        return node.valueOf(attrName);
    }

    public static int getInt(String nodeName, String attrName) {
        return (int)Integer.valueOf(getString(nodeName, attrName)).longValue();
    }
}

