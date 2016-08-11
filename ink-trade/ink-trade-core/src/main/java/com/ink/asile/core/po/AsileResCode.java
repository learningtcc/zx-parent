/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.po;

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

public class AsileResCode extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    //alias
    public static final String TABLE_ALIAS = "AsileResCode";
//  
//  public static final String ALIAS_ID = "主键";
//  
//  public static final String ALIAS_ASILE_NAME = "通道名称";
//  
//  public static final String ALIAS_ASILE_CODE = "通道id";
//  
//  public static final String ALIAS_ASILE_PAY_TYPE = "通道支付方式";
//  
//  public static final String ALIAS_TRANS_TYPE = "交易类型";
//  
//  public static final String ALIAS_ASILE_RES_CODE = "通道响应码";
//  
//  public static final String ALIAS_ASILE_RES_MSG = "通道响应信息";
//  
//  public static final String ALIAS_RES_CODE = "平台响应码";
//  
//  public static final String ALIAS_RES_MSG = "平台响应信息";
//  
//  public static final String ALIAS_REMARK = "备注";
//  
//  public static final String ALIAS_CREATE_TIME = "创建时间";
//  
//  public static final String ALIAS_CREATER_ID = "创建人id";
//  
//  public static final String ALIAS_CREATER_NAME = "创建人";
//  
//  public static final String ALIAS_UPDATE_TIME = "更新时间";
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
    //通道名称
    private String asileName;
    //通道id
    private String asileCode;
    //通道支付方式
    private String asilePayType;
    //交易类型
    private String transType;
    //通道响应码
    private String asileResCode;
    //通道响应信息
    private String asileResMsg;
    //平台响应码
    private String resCode;
    //平台响应信息
    private String resMsg;
    //备注
    private String remark;
    //创建时间
    private java.util.Date createTime;
    //创建人id
    private Long createrId;
    //创建人
    private String createrName;
    //更新时间
    private java.util.Date updateTime;
    //updaterId
    private Long updaterId;
    //updaterName
    private String updaterName;
    //是否删除
    private Integer isDelete;
    //columns END

    public AsileResCode(){
    }

    public AsileResCode(
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
    public void setAsileName(java.lang.String value) {
        this.asileName = value;
    }
    
    public java.lang.String getAsileName() {
        return this.asileName;
    }
    public void setAsileCode(java.lang.String value) {
        this.asileCode = value;
    }
    
    public java.lang.String getAsileCode() {
        return this.asileCode;
    }
    public void setAsilePayType(java.lang.String value) {
        this.asilePayType = value;
    }
    
    public java.lang.String getAsilePayType() {
        return this.asilePayType;
    }
    public void setTransType(java.lang.String value) {
        this.transType = value;
    }
    
    public java.lang.String getTransType() {
        return this.transType;
    }
    public void setAsileResCode(java.lang.String value) {
        this.asileResCode = value;
    }
    
    public java.lang.String getAsileResCode() {
        return this.asileResCode;
    }
    public void setAsileResMsg(java.lang.String value) {
        this.asileResMsg = value;
    }
    
    public java.lang.String getAsileResMsg() {
        return this.asileResMsg;
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
    public void setIsDelete(java.lang.Integer value) {
        this.isDelete = value;
    }
    
    public java.lang.Integer getIsDelete() {
        return this.isDelete;
    }
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("Id",getId())
            .append("AsileName",getAsileName())
            .append("AsileCode",getAsileCode())
            .append("AsilePayType",getAsilePayType())
            .append("TransType",getTransType())
            .append("AsileResCode",getAsileResCode())
            .append("AsileResMsg",getAsileResMsg())
            .append("ResCode",getResCode())
            .append("ResMsg",getResMsg())
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
            sb.append("通道名称:").append(asileName).append(separator);
            sb.append("通道id:").append(asileCode).append(separator);
            sb.append("通道支付方式:").append(asilePayType).append(separator);
            sb.append("交易类型:").append(transType).append(separator);
            sb.append("通道响应码:").append(asileResCode).append(separator);
            sb.append("通道响应信息:").append(asileResMsg).append(separator);
            sb.append("平台响应码:").append(resCode).append(separator);
            sb.append("平台响应信息:").append(resMsg).append(separator);
            sb.append("备注:").append(remark).append(separator);
            sb.append("创建时间:").append(getCreateTimeString()).append(separator);
            sb.append("创建人id:").append(createrId).append(separator);
            sb.append("创建人:").append(createrName).append(separator);
            sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
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
        if(obj instanceof AsileResCode == false) return false;
        if(this == obj) return true;
        AsileResCode other = (AsileResCode)obj;
        return new EqualsBuilder()
            .append(getId(),other.getId())
            .isEquals();
    }
}

