/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午2:38:14
 */
package com.ink.trade.api.platform.asile.model.out;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;


/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午2:38:14
 */
public class AsileInfoOutput extends BaseEntity implements Serializable{
	/**  
	 * @since JDK 1.7  
	 */  
	
	private static final long serialVersionUID = 3310760725511323467L;
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
            sb.append("createrId:").append(createrId).append(separator);
            sb.append("createrName:").append(createrName).append(separator);
            sb.append("updaterId:").append(updaterId).append(separator);
            sb.append("updaterName:").append(updaterName).append(separator);
            sb.append("isDelete:").append(isDelete).append(separator);
        
        return sb.toString();
    }
    
    
}
