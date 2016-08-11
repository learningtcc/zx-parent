package com.ink.base.exception;

import com.ink.base.cnst.YinkerConst;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;


/**
 * 平台异常父类
 */
public class FreightException extends RuntimeException {
	
    private static final long serialVersionUID = 5690451309757589228L;
    /**
     * 错误码
     */
    private int code = YinkerConst.ALIAS_EXCEPTION_CODE_INIT;
    /**
     * 错误信息
     */
    private String message = YinkerConst.ALIAS_EXCEPTION_MSG_INIT;

    public FreightException() {
        super();
     }
    
    public FreightException(int code, String message) {
       this.code = code;
       this.message = message;
    }
    
    public FreightException(int code) {
        this.code = code;
     }
    
    public FreightException( String message) {
        this.message = message;
     }
    
    /**
     * @param message e.g. "error is occured.type is {},trade code is {} "
     * @param args    e.g. {"app","7011"}
     */
    public FreightException( String message,String ... args) {
        FormattingTuple ft = MessageFormatter.arrayFormat(message, args);
        String formattedMessage = ft.getMessage();
        this.message = formattedMessage;
    }
    
    public FreightException(int code, String message,Throwable couse) {
    	super(couse);
        this.code = code;
        this.message = message;
     }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
