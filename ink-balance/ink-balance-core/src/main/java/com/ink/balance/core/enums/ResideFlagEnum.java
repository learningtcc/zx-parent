package com.ink.balance.core.enums;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：驻留标识枚举
 */
public enum ResideFlagEnum {

    UNRESIDE(0, "非驻留"), RESIDE(1, "驻留");

    private int code;
    private String name;

    ResideFlagEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (ResideFlagEnum e : ResideFlagEnum.values()) {
            if (e.getCode() == code) {
                return e.name;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
