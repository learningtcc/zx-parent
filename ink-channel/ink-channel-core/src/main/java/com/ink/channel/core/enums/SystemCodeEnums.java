package com.ink.channel.core.enums;
/**
 * 系统码  （成功码、错误码等。）
 * @author ZYC7-DZ-057
 *
 */
public enum SystemCodeEnums {
	
	SUCCESS_CODE("000000","成功"),FAILE_CODE("EB0003","失败"),
	/**民生*/
	CMBC_SUCCESS_CODE("C000000000","成功"),CMBC_FAILE_CODE("E999999999","渠道系统异常"),
	/**宝付*/
	BF_SUCCESS_CODE("0000","成功"),BF_FAILE_CODE("BF00100","渠道系统异常"),
	/**京东*/
	JD_SUCCESS_CODE("0000","成功"),JD_FAILE_CODE("EEE0001","渠道系统异常"),
	/**联动优势*/
	LDYS_SUCCESS_CODE("0000","成功"),LDYS_FAILE_CODE("00060999","渠道系统异常")
	;
	
	private String code;
	private String msg;
	
	private SystemCodeEnums(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
