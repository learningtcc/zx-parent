package com.ink.base;


public class BaseResult {
	private int status=200 ;
	private String message="OK";

	public BaseResult() {
		super();
	}

	public BaseResult(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + "]";
	}

	public int getStatus() {
        return status;
    }

	public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
