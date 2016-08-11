package com.ink.route.exception;

public class RouteFailException extends RouteException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2747914280794170156L;
	 public RouteFailException(String code,String message){
		  super(code, message);
	  }
}
