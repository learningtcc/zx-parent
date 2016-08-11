package com.ink.trade.core.enums;
/**
 * 删除状态枚举类
 *<pre>
 *<b>类描述:</b>()
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月26日 下午1:43:43
 *</pre>
 */
public enum IsDelete {
    NO(0),YES(1);
    private int value;
    
    private IsDelete(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
