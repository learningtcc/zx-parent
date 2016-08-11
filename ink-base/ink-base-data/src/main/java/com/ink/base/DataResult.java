package com.ink.base;


/**
 * 类Result.java的实现描述：物流系统返回结果
 *
 */
public class DataResult extends BaseResult {
	private Object data;

	@Override
    public String toString() {
        return "DataResult [getStatus()=" + getStatus() + ", getMessage()="
                + getMessage() + ", data=" + data + "]";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
