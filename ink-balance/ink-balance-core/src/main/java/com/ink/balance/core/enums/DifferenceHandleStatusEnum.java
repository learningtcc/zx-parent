package com.ink.balance.core.enums;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年5月12日 上午11:24:59
 * @description 描述：差异处理状态枚举
 * 处理状态 0、待处理 1、处理完成 2、挂起
 */
public enum DifferenceHandleStatusEnum {

    UNHANDLE(0, "待处理"), HANDLED(1, "处理完成"), HANGUP(2, "挂起");

    private int code;
    private String name;

    DifferenceHandleStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (DifferenceHandleStatusEnum e : DifferenceHandleStatusEnum.values()) {
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
