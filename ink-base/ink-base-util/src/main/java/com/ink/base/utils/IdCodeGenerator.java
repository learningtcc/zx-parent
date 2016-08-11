package com.ink.base.utils;

import com.ink.base.utils.logUtil.CodeGenerator;

import java.io.Serializable;
import java.util.Map;

/**
 * User: kingstar
 * Date: 16-5-31
 * Time: 上午11:43
 */
//@Repository("idCodeGenerator")
public class IdCodeGenerator implements Serializable {


    /**
     * ipMapsConfig 生产服务机器IP CODE列表
     */
 //   @Value("#{ipMapsConfig}")
    private Map<String,String> ipMapsConfig;
    /**
     *  购建生产器
     */
    final static CodeGenerator id=CodeGenerator.instanceCodeGenerator();
    /**
     * 本机IP
     */
    private static String ip= IpUtils.getLocalAddress();

    /**
     * 生成唯一ID  18位存数字
     * IP 没在ZK配置 默认CODE 为 999
     * @return
     */
    public String getId(){
        /**
         * 获取ipCode
         */
        String ipCode=null;
        if(ipMapsConfig!=null)ipCode=ipMapsConfig.get(ip);
        /**
         * 生成唯一ID
         */
        String generateId= id.generateCode( ipCode);
         return generateId;
    }

    public Map<String, String> getIpMapsConfig() {
        return ipMapsConfig;
    }

    public void setIpMapsConfig(Map<String, String> ipMapsConfig) {
        this.ipMapsConfig = ipMapsConfig;
    }
}
