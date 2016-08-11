/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.basic.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class BasicResCode extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    //alias
    public static final String TABLE_ALIAS = "BasicResCode";
//  
//  public static final String ALIAS_ID = "主键";
//  
//  public static final String ALIAS_RES_CODE = "平台响应码";
//  
//  public static final String ALIAS_RES_MSG = "平台响应信息";
//  
//  public static final String ALIAS_IS_CUST_REASON = "是否客户原因";
//  
//  public static final String ALIAS_IS_AGAIN = "是否可以重提";
//  
//  public static final String ALIAS_REMARK = "描述";
//  
//  public static final String ALIAS_CREATE_TIME = "创建时间";
//  
//  public static final String ALIAS_CREATER_ID = "createrId";
//  
//  public static final String ALIAS_CREATER_NAME = "创建人";
//  
//  public static final String ALIAS_UPDATE_TIME = "updateTime";
//  
//  public static final String ALIAS_UPDATER_ID = "updaterId";
//  
//  public static final String ALIAS_UPDATER_NAME = "updaterName";
//  
//  public static final String ALIAS_IS_DELETE = "是否删除";
//  
    
    //date formats
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
    
    //columns START
    //主键
    private Long id;
    //平台响应码
    private String resCode;
    //平台响应信息
    private String resMsg;
    //是否客户原因
    private String isCustReason;
    //是否可以重提
    private String isAgain;
    //描述
    private String remark;
    //创建时间
    private java.util.Date createTime;
    //createrId
    private Long createrId;
    //创建人
    private String createrName;
    //updateTime
    private java.util.Date updateTime;
    //updaterId
    private Long updaterId;
    //updaterName
    private String updaterName;
    //是否删除
    private String isDelete;
    //columns END

    public BasicResCode(){
    }

    public BasicResCode(
        java.lang.Long id
    ){
        this.id = id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }
    
    public java.lang.Long getId() {
        return this.id;
    }
    public void setResCode(java.lang.String value) {
        this.resCode = value;
    }
    
    public java.lang.String getResCode() {
        return this.resCode;
    }
    public void setResMsg(java.lang.String value) {
        this.resMsg = value;
    }
    
    public java.lang.String getResMsg() {
        return this.resMsg;
    }
    public void setIsCustReason(java.lang.String value) {
        this.isCustReason = value;
    }
    
    public java.lang.String getIsCustReason() {
        return this.isCustReason;
    }
    public void setIsAgain(java.lang.String value) {
        this.isAgain = value;
    }
    
    public java.lang.String getIsAgain() {
        return this.isAgain;
    }
    public void setRemark(java.lang.String value) {
        this.remark = value;
    }
    
    public java.lang.String getRemark() {
        return this.remark;
    }
    public String getCreateTimeString() {
        return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
    }
    public void setCreateTimeString(String value) {
        setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
    }
    
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
    
    public java.util.Date getCreateTime() {
        return this.createTime;
    }
    public void setCreaterId(java.lang.Long value) {
        this.createrId = value;
    }
    
    public java.lang.Long getCreaterId() {
        return this.createrId;
    }
    public void setCreaterName(java.lang.String value) {
        this.createrName = value;
    }
    
    public java.lang.String getCreaterName() {
        return this.createrName;
    }
    public String getUpdateTimeString() {
        return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
    }
    public void setUpdateTimeString(String value) {
        setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
    }
    
    public void setUpdateTime(java.util.Date value) {
        this.updateTime = value;
    }
    
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdaterId(java.lang.Long value) {
        this.updaterId = value;
    }
    
    public java.lang.Long getUpdaterId() {
        return this.updaterId;
    }
    public void setUpdaterName(java.lang.String value) {
        this.updaterName = value;
    }
    
    public java.lang.String getUpdaterName() {
        return this.updaterName;
    }
    public void setIsDelete(java.lang.String value) {
        this.isDelete = value;
    }
    
    public java.lang.String getIsDelete() {
        return this.isDelete;
    }
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("Id",getId())
            .append("ResCode",getResCode())
            .append("ResMsg",getResMsg())
            .append("IsCustReason",getIsCustReason())
            .append("IsAgain",getIsAgain())
            .append("Remark",getRemark())
            .append("CreateTime",getCreateTime())
            .append("CreaterId",getCreaterId())
            .append("CreaterName",getCreaterName())
            .append("UpdateTime",getUpdateTime())
            .append("UpdaterId",getUpdaterId())
            .append("UpdaterName",getUpdaterName())
            .append("IsDelete",getIsDelete())
            .toString();
    }
    
    public String toString(String separator) {
        StringBuffer sb = new StringBuffer();

            sb.append("主键:").append(id).append(separator);
            sb.append("平台响应码:").append(resCode).append(separator);
            sb.append("平台响应信息:").append(resMsg).append(separator);
            sb.append("是否客户原因:").append(isCustReason).append(separator);
            sb.append("是否可以重提:").append(isAgain).append(separator);
            sb.append("描述:").append(remark).append(separator);
            sb.append("创建时间:").append(getCreateTimeString()).append(separator);
            sb.append("createrId:").append(createrId).append(separator);
            sb.append("创建人:").append(createrName).append(separator);
            sb.append("updateTime:").append(getUpdateTimeString()).append(separator);
            sb.append("updaterId:").append(updaterId).append(separator);
            sb.append("updaterName:").append(updaterName).append(separator);
            sb.append("是否删除:").append(isDelete).append(separator);
        
        return sb.toString();
    }
    
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
    
    public boolean equals(Object obj) {
        if(obj instanceof BasicResCode == false) return false;
        if(this == obj) return true;
        BasicResCode other = (BasicResCode)obj;
        return new EqualsBuilder()
            .append(getId(),other.getId())
            .isEquals();
    }
}

