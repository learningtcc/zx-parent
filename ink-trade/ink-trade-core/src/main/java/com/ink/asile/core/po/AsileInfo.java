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

public class AsileInfo extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    //alias
    public static final String TABLE_ALIAS = "AsileInfo";
//  
//  public static final String ALIAS_ID = "id";
//  
//  public static final String ALIAS_ASILE_NAME = "asileName";
//  
//  public static final String ALIAS_ASILE_CODE = "asileCode";
//  
//  public static final String ALIAS_ASILE_BANK_TYPE = "asileBankType";
//  
//  public static final String ALIAS_ASILE_FEE_NAME = "费率名称";
//  
//  public static final String ALIAS_ASILE_FEE_NO = "费率编号";
//  
//  public static final String ALIAS_ASILE_STATUS = "asileStatus";
//  
//  public static final String ALIAS_TRANS_TYPE = "transType";
//  
//  public static final String ALIAS_ASILE_PRODUCT_CODE = "asileProductCode";
//  
//  public static final String ALIAS_ASILE_PRODUCT_NAME = "asileProductName";
//  
//  public static final String ALIAS_ASILE_AUDIT_STATUS = "asileAuditStatus";
//  
//  public static final String ALIAS_REMARK = "remark";
//  
//  public static final String ALIAS_CREATE_TIME = "createTime";
//  
//  public static final String ALIAS_CREATER_ID = "createrId";
//  
//  public static final String ALIAS_CREATER_NAME = "createrName";
//  
//  public static final String ALIAS_UPDATE_TIME = "updateTime";
//  
//  public static final String ALIAS_UPDATER_ID = "updaterId";
//  
//  public static final String ALIAS_UPDATER_NAME = "updaterName";
//  
//  public static final String ALIAS_IS_DELETE = "isDelete";
//  
    
    //date formats
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
    
    //columns START
    //id
    private Long id;
    //asileName
    private String asileName;
    //asileCode
    private String asileCode;
    //asileBankType
    private String asileBankType;
    //费率名称
    private Long asileFeeName;
    //费率编号
    private Long asileFeeNo;
    //asileStatus
    private String asileStatus;
    //transType
    private String transType;
    //asileProductCode
    private String asileProductCode;
    //asileProductName
    private String asileProductName;
    //asileAuditStatus
    private String asileAuditStatus;
    //remark
    private String remark;
    //createTime
    private java.util.Date createTime;
    //createrId
    private Long createrId;
    //createrName
    private String createrName;
    //updateTime
    private java.util.Date updateTime;
    //updaterId
    private Long updaterId;
    //updaterName
    private String updaterName;
    //isDelete
    private Integer isDelete;
    //columns END
    //token有效期
    private Integer tokenExpireTime;
    
    public Integer getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Integer tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public AsileInfo(){
    }

    public AsileInfo(
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
    public void setAsileBankType(java.lang.String value) {
        this.asileBankType = value;
    }
    
    public java.lang.String getAsileBankType() {
        return this.asileBankType;
    }
    public void setAsileFeeName(java.lang.Long value) {
        this.asileFeeName = value;
    }
    
    public java.lang.Long getAsileFeeName() {
        return this.asileFeeName;
    }
    public void setAsileFeeNo(java.lang.Long value) {
        this.asileFeeNo = value;
    }
    
    public java.lang.Long getAsileFeeNo() {
        return this.asileFeeNo;
    }
    public void setAsileStatus(java.lang.String value) {
        this.asileStatus = value;
    }
    
    public java.lang.String getAsileStatus() {
        return this.asileStatus;
    }
    public void setTransType(java.lang.String value) {
        this.transType = value;
    }
    
    public java.lang.String getTransType() {
        return this.transType;
    }
    public void setAsileProductCode(java.lang.String value) {
        this.asileProductCode = value;
    }
    
    public java.lang.String getAsileProductCode() {
        return this.asileProductCode;
    }
    public void setAsileProductName(java.lang.String value) {
        this.asileProductName = value;
    }
    
    public java.lang.String getAsileProductName() {
        return this.asileProductName;
    }
    public void setAsileAuditStatus(java.lang.String value) {
        this.asileAuditStatus = value;
    }
    
    public java.lang.String getAsileAuditStatus() {
        return this.asileAuditStatus;
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
            .append("AsileBankType",getAsileBankType())
            .append("AsileFeeName",getAsileFeeName())
            .append("AsileFeeNo",getAsileFeeNo())
            .append("AsileStatus",getAsileStatus())
            .append("TransType",getTransType())
            .append("AsileProductCode",getAsileProductCode())
            .append("AsileProductName",getAsileProductName())
            .append("AsileAuditStatus",getAsileAuditStatus())
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

            sb.append("id:").append(id).append(separator);
            sb.append("asileName:").append(asileName).append(separator);
            sb.append("asileCode:").append(asileCode).append(separator);
            sb.append("asileBankType:").append(asileBankType).append(separator);
            sb.append("费率名称:").append(asileFeeName).append(separator);
            sb.append("费率编号:").append(asileFeeNo).append(separator);
            sb.append("asileStatus:").append(asileStatus).append(separator);
            sb.append("transType:").append(transType).append(separator);
            sb.append("asileProductCode:").append(asileProductCode).append(separator);
            sb.append("asileProductName:").append(asileProductName).append(separator);
            sb.append("asileAuditStatus:").append(asileAuditStatus).append(separator);
            sb.append("remark:").append(remark).append(separator);
            sb.append("createTime:").append(getCreateTimeString()).append(separator);
            sb.append("createrId:").append(createrId).append(separator);
            sb.append("createrName:").append(createrName).append(separator);
            sb.append("updateTime:").append(getUpdateTimeString()).append(separator);
            sb.append("updaterId:").append(updaterId).append(separator);
            sb.append("updaterName:").append(updaterName).append(separator);
            sb.append("isDelete:").append(isDelete).append(separator);
        
        return sb.toString();
    }
    
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
    
    public boolean equals(Object obj) {
        if(obj instanceof AsileInfo == false) return false;
        if(this == obj) return true;
        AsileInfo other = (AsileInfo)obj;
        return new EqualsBuilder()
            .append(getId(),other.getId())
            .isEquals();
    }
}

