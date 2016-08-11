package com.ink.cert.api.util.secret;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ink.cert.api.constant.SecretConstant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 联动优势
 * Created by aiyungui on 2016/7/5.
 */
public class Ldys {

    /**
     * 把json对象解析成map对象
     * @param message
     * @return
     */
    public static Map<String, Map<String, Object>> getSecretParam(String message) {

        JSONObject jsonObject = JSON.parseObject(message);
        JSONObject msgJson = jsonObject.getJSONObject("ldysParam");
        JSONObject certInfoJson = jsonObject.getJSONObject("certInfo");

        HashMap<String,Object> msgMap = JSON.parseObject(msgJson.toJSONString(), HashMap.class);
        HashMap<String,Object> certInfoMap = JSON.parseObject(certInfoJson.toJSONString(), HashMap.class);

        Map<String, Map<String, Object>> encryptParamMap = Maps.newHashMap();
        encryptParamMap.put("msgMap",msgMap);
        encryptParamMap.put("certInfoMap",certInfoMap);
        return encryptParamMap;
    }

    /**
     * 创建联动优势配置文件
     * @param certInfoMap
     * @param msgMap
     * @throws IOException
     */
    public static void createProperties(Map<String, Object> certInfoMap,Map<String,Object> msgMap) throws IOException {
        URL base = Ldys.class.getResource("/");
        String ldysPropertiesPath = base.getPath() + File.separator + "SignVerProp.properties";
        List<String> lines = Lists.newArrayList();
        lines.add("#生产环境URL");
        lines.add("plat.url="+ certInfoMap.get("plat.url"));
        lines.add("#商户私钥配置路径");
        lines.add(msgMap.get("mer_id")+".mer.prikey.path=" + certInfoMap.get("p8FilePath"));
        lines.add("#UMPAY平台证书路径（必须为绝对路径）");
        lines.add("plat.cert.path="+ certInfoMap.get("certPath"));
        lines.add("plat.pay.product.name="+ certInfoMap.get("plat.pay.product.name"));
        lines.add("#需要进行RSA加密的参数，无需修改");
        lines.add("Encrypt.Paramters="+ certInfoMap.get("Encrypt.Paramters"));
        FileUtils.writeLines(new File(ldysPropertiesPath), SecretConstant.ENCODE, lines);
    }

    /**
     * 检查当前商户号的p8证书key是否存在，不存在则添加
     * @param certInfoMap
     * @param msgMap
     * @throws IOException
     */
    public static void addPropertiesForP8cert(Map<String, Object> certInfoMap,Map<String,Object> msgMap) throws IOException {
        if (StringUtils.isBlank((String) msgMap.get("mer_id"))){
            return ;
        }
        URL base = Ldys.class.getResource("/");
        String propertiesPath = base.getPath() + File.separator + "SignVerProp.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesPath));
        String p8Key = msgMap.get("mer_id")+".mer.prikey.path";
        String p8Path = properties.getProperty(p8Key);
        if (StringUtils.isBlank(p8Path)){
            List<String> lines = Lists.newArrayList();
            String line = p8Key + "="+ certInfoMap.get("p8FilePath");
//            lines.add("\n");
            lines.add(line);
            FileUtils.writeLines(new File(propertiesPath), lines, true);
        }
    }
}
