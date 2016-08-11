package com.ink.cert.api.constant;

/**
 * 证书配置常量
 * Created by aiyungui on 2016/7/6.
 */
public class CertConfigConstant {

    //证书中心文件存储路径
    public static  final String CERT_STORE_PATH = "cert.store.path";
    //本地调用证书中心文件存储路径
    public static  final String LOCAL_CERT_STORE_PATH = "common.local.cert.store.path";
    //不走证书中心加解密的服务器ip
    public static  final String FILTER_IPS = "common.filter.ips";
    //是否全部使用证书中心加解密,0表示是，1表示否
    public static  final String IF_ALL_USED_CERT_CENTER = "common.if.all.used.certCenter";
    //配置文件存储路径
    public static  final String PROPERTIES_STORE_PATH = "common.properties.store.path";
}
