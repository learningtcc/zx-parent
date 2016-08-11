package com.ink.user.core.po.mongo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * 错误日志bean
 * @author yangchen
 * @date 2016年1月30日 下午5:30:17
 */
@Entity(value="exception_log",noClassnameStored=true)
public class ExceptionLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1073188092914583907L;



	@Id
	private ObjectId _id;
	
	@Property("module_name")
	private String moduleName;
	
	@Property("id_key")
	private String idKey;
	
	@Property("method_name")
	private String methodName;
	
	@Property("args")
	private String args;
	
	@Property("target")
	private String target;
	
	@Property("message")
	private String message;
	
	@Property("cause")
	private String cause;
	
	@Property("create_time")
	private Date createTime;
	
	public ExceptionLog(){};
	
	public ExceptionLog(String moduleName, String idKey,String methodName, String args, String target,
			String message, String cause) {
		this.moduleName = moduleName;
		this.idKey = idKey;
		this.methodName = methodName;
		this.args = args;
		this.target = target;
		this.message = message;
		this.cause = cause;
		this.createTime = new Date();
	}

	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getIdKey() {
		return idKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExceptionLog [moduleName=");
		builder.append(moduleName);
		builder.append(", idKey=");
		builder.append(idKey);
		builder.append(", methodName=");
		builder.append(methodName);
		builder.append(", args=");
		builder.append(args);
		builder.append(", target=");
		builder.append(target);
		builder.append(", message=");
		builder.append(message);
		builder.append(", cause=");
		builder.append(cause);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
