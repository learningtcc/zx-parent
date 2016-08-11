package com.ink.balance.core.enums;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：差错表差错类型
 */
public enum DifferenceSourceEnum {

    CHANNEL(1, "渠道"), PLATFORM(2, "平台");

    private int code;
    private String name;

    DifferenceSourceEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (DifferenceSourceEnum e : DifferenceSourceEnum.values()) {
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
