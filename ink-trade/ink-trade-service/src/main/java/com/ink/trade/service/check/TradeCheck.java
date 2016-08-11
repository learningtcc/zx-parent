package com.ink.trade.service.check;
/**
 * 
 *<pre>
 *<b>类描述:</b>()
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月14日 下午3:32:44
 *</pre>
 */
public abstract class TradeCheck implements CheckOptions{
    private CheckOptions opt;

    public CheckOptions getOpt() {
        return opt;
    }

    public void setOpt(CheckOptions opt) {
        this.opt = opt;
    }
    
}
