package com.umpay.api.util;

import com.umpay.api.common.ReqRule;
import com.umpay.api.exception.ParameterCheckException;
import com.umpay.api.log.ILogger;
import com.umpay.api.log.LogManager;
import com.umpay.api.util.DataUtil;
import com.umpay.api.util.ServiceMapUtil;
import com.umpay.api.util.SignUtil;
import com.umpay.api.util.StringUtil;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PlainUtil {
    private static ILogger log = LogManager.getLogger();

    public PlainUtil() {
    }

    public static Map getPlain(Object obj) throws ParameterCheckException {
        Map map = DataUtil.getData(obj);
        HashMap returnMap = new HashMap();
        if(validate(map)) {
            log.info("数据必填字段校验通过");
            returnMap.putAll(getPlanAndCheckRule(map));
            log.info("校验规则正确");
            log.info("PlainUtil.getPlain 返回的map为:" + returnMap);
            return returnMap;
        } else {
            throw new ParameterCheckException("请求信息" + map + "在进行必填字段校验发生异常");
        }
    }

    public static Map getPlainNocheck(Object obj) throws ParameterCheckException {
        Map map = DataUtil.getData(obj);
        HashMap returnMap = new HashMap();
        returnMap.putAll(getPlanNoCheck(map));
        log.info("PlainUtil.getPlain 返回的map为:" + returnMap);
        return returnMap;
    }

    public static Map notifyPlain(Object obj, boolean b) throws ParameterCheckException {
        HashMap map = new HashMap();
        Map reqMap = DataUtil.getData(obj);
        if(b) {
            map.putAll(conMeta(reqMap));
        } else {
            map.putAll(merNotifyPlain(reqMap));
        }

        return map;
    }

    public static Map getResPlain(String res) throws ParameterCheckException {
        String[] str = null;
        HashMap map = new HashMap();

        try {
            str = res.split("&");
            Arrays.sort(str);
            map.putAll(resPlain(str));
            return map;
        } catch (Exception var4) {
            throw new ParameterCheckException("平台返回信息" + res + "解析异常");
        }
    }

    private static boolean validate(Map map) throws ParameterCheckException {
        if(!map.containsKey("service")) {
            log.info("请求字段中不包含service字段");
            throw new ParameterCheckException("请求字段中不包含service字段");
        } else {
            String service = map.get("service").toString();
            Object object = ServiceMapUtil.getServiceRule().get(service);
            if(object == null) {
                log.info("请求字段中service字段的不做字段校验！");
                return true;
            } else {
                String rule = object.toString();
                String[] str = rule.split(",");
                String key = null;
                Object value = null;

                for(int i = 0; i < str.length; ++i) {
                    key = str[i];
                    value = map.get(key);
                    if(value == null || "".equals(value.toString())) {
                        String message = "service=" + map.get("service").toString() + "请求中" + key + "字段不能为空";
                        log.info(message);
                        throw new ParameterCheckException(message);
                    }
                }

                return true;
            }
        }
    }

    private static Map resPlain(String[] str) throws ParameterCheckException {
        HashMap retMap = new HashMap();
        StringBuffer plainBuffer = new StringBuffer();
        new HashMap();

        for(int plain = 0; plain < str.length; ++plain) {
            if(!str[plain].startsWith("sign")) {
                plainBuffer.append(str[plain]).append("&");
            }

            Map map = split(str[plain], 2);
            retMap.putAll(map);
        }

        String var5 = plainBuffer.substring(0, plainBuffer.length() - 1).toString();
        retMap.put("plain", var5);
        log.info("平台返回信息解析获得的map为：" + retMap);
        return retMap;
    }

    private static Map split(String s, int limit) throws ParameterCheckException {
        HashMap map = new HashMap();
        String[] str = null;

        try {
            str = s.split("=", limit);
        } catch (Exception var5) {
            log.info("分解平台返回信息" + s + "发生异常！" + var5);
        }

        if(str.length == 1) {
            map.put(str[0], "");
        } else {
            if(str.length != 2) {
                throw new ParameterCheckException("分解平台返回信息" + s + "发生异常");
            }

            map.put(str[0], str[1]);
        }

        return map;
    }

    private static Map merNotifyPlain(Map map) throws ParameterCheckException {
        Object[] obj = map.keySet().toArray();
        Arrays.sort(obj);
        String plain = "";
        String str = "";
        String value = "";
        StringBuffer signString = new StringBuffer();

        for(int returnMap = 0; returnMap < obj.length; ++returnMap) {
            str = obj[returnMap].toString();
            value = map.get(str).toString();
            if(!str.startsWith("sign")) {
                signString.append(str).append("=").append(value).append("&");
            }
        }

        plain = signString.subSequence(0, signString.length() - 1).toString();
        log.info("请求数据获得的明文串为：" + plain);
        HashMap var7 = new HashMap();
        var7.put("plain", plain);
        return var7;
    }

    private static Map conMeta(Map map) throws ParameterCheckException {
        log.info("商户请求map:" + map);
        Object[] obj = map.keySet().toArray();
        Object object = null;
        Map ruleMap = ServiceMapUtil.getReqRule();
        Arrays.sort(obj);
        String value = null;
        StringBuffer plainString = new StringBuffer();
        StringBuffer signString = new StringBuffer();
        ReqRule rq = null;

        String plain;
        String sign;
        try {
            for(int returnMap = 0; returnMap < obj.length; ++returnMap) {
                String str = obj[returnMap].toString();
                value = map.get(str).toString();
                object = ruleMap.get(str);
                if(object == null) {
                    log.info("商户请求的字段名：" + str + "在规范文档中不存在！");
                    plainString.append(str).append("=").append(value).append("&");
                    signString.append(str).append("=").append(value).append("&");
                } else {
                    rq = (ReqRule)object;
                    if(!"".equals(rq.getRegex()) && !Pattern.matches(rq.getRegex(), value)) {
                        log.info("请求字段中" + str + "字段的值不满足字段规则" + rq.getRegex() + "字段值为：" + value);
                        throw new ParameterCheckException("请求字段" + str + "在进行规则校验发生异常");
                    }

                    if(rq.getLength() > 0 && value.length() > rq.getLength()) {
                        log.info("请求字段中" + str + "字段的值不满足字段长度规则：" + rq.getLength());
                        throw new ParameterCheckException("请求字段" + str + "在进行规则校验发生异常");
                    }

                    if("sign_type".equals(str)) {
                        plainString.append(str).append("=").append(value).append("&");
                    } else {
                        plainString.append(str).append("=").append(value).append("&");
                        signString.append(str).append("=").append(value).append("&");
                    }
                }
            }

            plain = plainString.subSequence(0, plainString.length() - 1).toString();
            sign = signString.substring(0, signString.length() - 1).toString();
            log.info("请求数据获得的加密明文串为：" + sign);
            sign = SignUtil.sign(sign, StringUtil.trim(map.get("mer_id")));
            log.info("请求数据获得的加密串为：" + sign);
        } catch (Exception var12) {
            log.info("进行字段规则校验发生异常" + var12);
            throw new ParameterCheckException("组织商户返回平台数据发生异常" + var12);
        }

        HashMap var13 = new HashMap();
        var13.put("plain", plain);
        var13.put("sign", sign);
        return var13;
    }

    private static Map getPlanAndCheckRule(Map map) throws ParameterCheckException {
        log.info("商户请求map:" + map);
        Object[] obj = map.keySet().toArray();
        Object object = null;
        Map ruleMap = ServiceMapUtil.getReqRule();
        Arrays.sort(obj);
        String valueEncoder = null;
        StringBuffer plainString = new StringBuffer();
        StringBuffer signString = new StringBuffer();
        ReqRule rq = null;

        String plain;
        String sign;
        try {
            for(int returnMap = 0; returnMap < obj.length; ++returnMap) {
                String str = obj[returnMap].toString();
                String value = map.get(str).toString().trim();
                object = ruleMap.get(str);
                if(object == null) {
                    log.info("商户请求的字段名：" + str + "在规范文档中不存在！");
                    plainString.append(str).append("=").append(value).append("&");
                    signString.append(str).append("=").append(value).append("&");
                } else {
                    rq = (ReqRule)object;
                    if(!"".equals(value.trim()) && !"".equals(rq.getRegex()) && !Pattern.matches(rq.getRegex(), value)) {
                        log.info("请求字段中" + str + "字段的值不满足字段规则" + rq.getRegex() + "字段值为：" + value);
                        throw new ParameterCheckException("请求字段" + str + "在进行规则校验发生异常");
                    }

                    if(rq.getLength() > 0 && value.length() > rq.getLength()) {
                        log.info("请求字段中" + str + "字段的值不满足字段长度规则：" + rq.getLength());
                        throw new ParameterCheckException("请求字段" + str + "在进行规则校验发生异常");
                    }

                    if("sign_type".equals(str)) {
                        if(rq.isEncode()) {
                            value = URLEncoder.encode(value, "UTF-8");
                        }

                        plainString.append(str).append("=").append(value).append("&");
                    } else {
                        if(rq.isEncode()) {
                            valueEncoder = URLEncoder.encode(value, "UTF-8");
                        } else {
                            valueEncoder = value;
                        }

                        plainString.append(str).append("=").append(valueEncoder).append("&");
                        signString.append(str).append("=").append(value).append("&");
                    }
                }
            }

            plain = plainString.subSequence(0, plainString.length() - 1).toString();
            sign = signString.substring(0, signString.length() - 1).toString();
            log.info("请求数据获得的签名明文串为：" + sign);
            sign = SignUtil.sign(sign, StringUtil.trim(map.get("mer_id")));
            log.info("请求数据获得的签名串为：" + sign);
        } catch (Exception var13) {
            log.info("进行字段规则校验发生异常" + var13);
            throw new ParameterCheckException("请求字段在进行规则校验发生异常" + var13.getMessage());
        }

        HashMap var14 = new HashMap();
        var14.put("plain", plain);
        var14.put("sign", sign);
        return var14;
    }

    private static Map getPlanNoCheck(Map map) throws ParameterCheckException {
        Object[] obj = map.keySet().toArray();
        Arrays.sort(obj);
        String valueEncoder = null;
        StringBuffer plainString = new StringBuffer();
        StringBuffer signString = new StringBuffer();

        String plain;
        String sign;
        try {
            int returnMap = 0;

            while(true) {
                if(returnMap >= obj.length) {
                    plain = plainString.subSequence(0, plainString.length() - 1).toString();
                    sign = signString.substring(0, signString.length() - 1).toString();
                    log.info("请求数据获得的签名明文串为：" + sign);
                    String var11 = StringUtil.isNotEmpty(StringUtil.trim(map.get("mer_id")))?StringUtil.trim(map.get("mer_id")):StringUtil.trim(map.get("merId"));
                    sign = SignUtil.sign(sign, StringUtil.trim(var11));
                    log.info("请求数据获得的签名串为：" + sign);
                    break;
                }

                String str = obj[returnMap].toString();
                String value = StringUtil.trim(map.get(str).toString());
                if(!StringUtil.isEmpty(value)) {
                    if(!"sign_type".equals(str) && !"signType".equals(str)) {
                        valueEncoder = URLEncoder.encode(value, "UTF-8");
                        plainString.append(str).append("=").append(valueEncoder).append("&");
                        signString.append(str).append("=").append(value).append("&");
                    } else {
                        value = URLEncoder.encode(value, "UTF-8");
                        plainString.append(str).append("=").append(value).append("&");
                    }
                }

                ++returnMap;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            log.info("进行字段规则校验发生异常" + var10);
            throw new ParameterCheckException("请求字段在进行规则校验发生异常" + var10.getMessage());
        }

        HashMap var12 = new HashMap();
        var12.put("plain", plain);
        var12.put("sign", sign);
        return var12;
    }
}