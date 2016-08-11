package com.ink.route.exception;

public class RouteException extends RuntimeException{
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
		public RouteException(String code,String message){
			this.code=code;
			this.message=message;
		}
}
