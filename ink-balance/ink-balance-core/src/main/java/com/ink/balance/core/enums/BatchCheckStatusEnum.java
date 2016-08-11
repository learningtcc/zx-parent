package com.ink.balance.core.enums;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：对账总表的对账状态
 */
public enum BatchCheckStatusEnum {
    MATCH(1, "匹配"), UNMATCH(2, "未匹配配"), EXCEPTION(3, "数据异常");

    private int code;
    private String name;

    BatchCheckStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (BatchCheckStatusEnum e : BatchCheckStatusEnum.values()) {
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
