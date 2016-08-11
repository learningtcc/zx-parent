package com.ink.balance.core.enums;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：渠道、平台单条数据的对账状态
 * 对账状态 0：初始 1：未匹配 2：已匹配  3、差错 4、金额调整后匹配'
 */
public enum DetailCheckStatusEnum {
    INIT(0, "初始"), UNMATCH(1, "未匹配"), MATCH(2, "匹配"), DIFF(3, "差错"), AMT2MATCH(4, "金额调整后匹配");

    private int code;
    private String name;

    DetailCheckStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (DetailCheckStatusEnum e : DetailCheckStatusEnum.values()) {
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
