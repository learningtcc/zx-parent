package com.ink.route.api.enums;


import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 路由业务类型
 * Created by huohb on 2016/6/2.
 */
public enum RouteBusinessType {

    //支持的支付方式，转化为二进制，第一位为1代表支持代收，第二位为1代表支付代付，第三位快捷，第四位认证无短信，第五位认证有短信，第六位鉴权
    COLLECT("01", "代收", 0),
    PAYMENT("02", "代付", 1),
    QUICKPAY("03", "快捷支付", 2),
    CERTIFIEDPAY("04", "认证支付无短信", 3),
    CERTIFIEDPAY_SMS("05", "认证支付有短信", 4),
    AUTH("06", "鉴权", 5);

    // key为code,value为index
    private static Map<String, Integer> indexMap = new HashMap<String, Integer>();
    // key为index,value为code
    private static Map<Integer, String> codeMap = new HashMap<Integer, String>();

    static {
        for (RouteBusinessType routeBusinessType : RouteBusinessType.values()) {
            indexMap.put(routeBusinessType.getCode(), routeBusinessType.getIndex());
            codeMap.put(routeBusinessType.getIndex(), routeBusinessType.getCode());
        }
    }

    private String code;

    private String value;

    private int index;

    RouteBusinessType(String code, String value, int index) {
        this.code = code;
        this.value = value;
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static String getValueByCode(String code) {
		for (RouteBusinessType type : RouteBusinessType.values()) {
			if (code.equals(type.getCode())) {
				return type.getValue();
			}
		}
		return "";
	}
    
    public static List<Map<String, String>> getRouteBusinessTypeList() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (RouteBusinessType e : EnumSet.allOf(RouteBusinessType.class)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("code", e.getCode());
            map.put("value", e.getValue());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据code数组转换为二进制
     *
     * @param codes
     * @return
     */
    public static int code2Binary(String[] codes) {
        if (codes == null || codes.length == 0) {
            return 0;
        }
        int result = 0;
        // 从枚举中根据code找到index,然后计算十进制
        for (String code : codes) {
            Integer index = indexMap.get(code);
            if (index != null) {
                result += Math.pow(2, index);
            }
        }
        return result;
    }

    /**
     * 根据二进制结果转换为code数组
     *
     * @param result
     * @return
     */
    public static String[] binary2Code(int result) {
        List<String> codeList = new ArrayList<String>();
        String binaryString = Integer.toBinaryString(result);
        // 循环二进制，找出二进制位的下标所对应的code
        for (int i = binaryString.length() - 1, j = 0; i >= 0; i--, j++) {
            if ('1' == binaryString.charAt(i)) {
                codeList.add(codeMap.get(j));
            }
        }
        return codeList.toArray(new String[codeList.size()]);
    }
    
    public static String[] binary2CodeGetValue(int result) {
        List<String> codeList = new ArrayList<String>();
        String binaryString = Integer.toBinaryString(result);
        // 循环二进制，找出二进制位的下标所对应的code
        for (int i = binaryString.length() - 1, j = 0; i >= 0; i--, j++) {
            if ('1' == binaryString.charAt(i)) {
                codeList.add(getValueByCode(codeMap.get(j)));
            }
        }
        return codeList.toArray(new String[codeList.size()]);
    }

}
