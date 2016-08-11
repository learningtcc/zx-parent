package com.ink.balance.core.enums;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年5月27日 上午11:24:59
 * @description 描述：调账表的调账状态
 * 调账状态（1、调账成功 2、调账失败）
 */
public enum CheckBalanceStatusEnum {

    BALANCE_SUCCESS(1, "调账成功"), BALANCE_FAIL(2, "调账失败");

    private int code;
    private String name;

    CheckBalanceStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (CheckBalanceStatusEnum e : CheckBalanceStatusEnum.values()) {
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
