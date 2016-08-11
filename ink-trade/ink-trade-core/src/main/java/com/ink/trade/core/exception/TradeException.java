package com.ink.trade.core.exception;
/**
 * 
 *<pre>
 *<b>类描述:</b>(交易系统异常处理)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月18日 下午6:02:27
 *</pre>
 */
public class TradeException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -5898977195111236698L;

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public TradeException(){
        super();
    }
    
    public TradeException(String message){
        super(message);
        this.message = message;
    }
    
    public TradeException(final String code, final String message){
        super();
        this.code = code;
        this.message = message;
    }
}
