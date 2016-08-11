/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月8日 上午10:09:43
 */
package com.ink.trade.api.platform.basic.model.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月8日 上午10:09:43
 */
public class BasicBankEntity extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 5454155825314635342L;

    // alias
    public static final String TABLE_ALIAS = "BasicBank";
    //
    public static final String ALIAS_ID = "id";

    public static final String ALIAS_IS_DELETE = "isDelete";

    public static final String ALIAS_BANK_NAME = "bankName";

    public static final String ALIAS_BANK_SHORT_NAME = "bankShortName";

    public static final String ALIAS_VERSION = "version";

    public static final String ALIAS_REMARK = "remark";

    public static final String ALIAS_CREATE_TIME = "createTime";

    public static final String ALIAS_CREATER_ID = "createrId";

    public static final String ALIAS_CREATER_NAME = "createrName";

    public static final String ALIAS_UPDATE_TIME = "updateTime";

    public static final String ALIAS_UPDATER_ID = "updaterId";

    public static final String ALIAS_UPDATER_NAME = "updaterName";

    public static final String ALIAS_BANK_CITY_CODE = "bankCityCode";

    public static final String ALIAS_BANK_CCPC_CODE = "bankCcpcCode";

    public static final String ALIAS_BANK_LEVEL = "bankLevel";

    public static final String ALIAS_SUB_BANK_NAME = "subBankName";

    // date formats
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;

    // columns START
    // id
    private Long id;
    // isDelete
    private String isDelete;
    // bankName
    private String bankName;
    // bankShortName
    private String bankShortName;
    // version
    private Integer version;
    // remark
    private String remark;
    // createTime
    private java.util.Date createTime;
    // createrId
    private Long createrId;
    // createrName
    private String createrName;
    // updateTime
    private java.util.Date updateTime;
    // updaterId
    private Long updaterId;
    // updaterName
    private String updaterName;
    // bankCityCode
    private String bankCityCode;
    // bankCcpcCode
    private String bankCcpcCode;
    // bankLevel
    private String bankLevel;
    // subBankName
    private String subBankName;

    // columns END

    public BasicBankEntity() {
    }


    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getId() {
        return this.id;
    }

    public void setIsDelete(java.lang.String value) {
        this.isDelete = value;
    }

    public java.lang.String getIsDelete() {
        return this.isDelete;
    }

    public void setBankName(java.lang.String value) {
        this.bankName = value;
    }

    public java.lang.String getBankName() {
        return this.bankName;
    }

    public void setBankShortName(java.lang.String value) {
        this.bankShortName = value;
    }

    public java.lang.String getBankShortName() {
        return this.bankShortName;
    }

    public void setVersion(java.lang.Integer value) {
        this.version = value;
    }

    public java.lang.Integer getVersion() {
        return this.version;
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
        setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME, java.util.Date.class));
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
        setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME, java.util.Date.class));
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

    public void setBankCityCode(java.lang.String value) {
        this.bankCityCode = value;
    }

    public java.lang.String getBankCityCode() {
        return this.bankCityCode;
    }

    public void setBankCcpcCode(java.lang.String value) {
        this.bankCcpcCode = value;
    }

    public java.lang.String getBankCcpcCode() {
        return this.bankCcpcCode;
    }

    public void setBankLevel(java.lang.String value) {
        this.bankLevel = value;
    }

    public java.lang.String getBankLevel() {
        return this.bankLevel;
    }

    public void setSubBankName(java.lang.String value) {
        this.subBankName = value;
    }

    public java.lang.String getSubBankName() {
        return this.subBankName;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId())
                        .append("IsDelete", getIsDelete()).append("BankName", getBankName())
                        .append("BankShortName", getBankShortName()).append("Version", getVersion())
                        .append("Remark", getRemark()).append("CreateTime", getCreateTime())
                        .append("CreaterId", getCreaterId()).append("CreaterName", getCreaterName())
                        .append("UpdateTime", getUpdateTime()).append("UpdaterId", getUpdaterId())
                        .append("UpdaterName", getUpdaterName()).append("BankCityCode", getBankCityCode())
                        .append("BankCcpcCode", getBankCcpcCode()).append("BankLevel", getBankLevel())
                        .append("SubBankName", getSubBankName()).toString();
    }

    public String toString(String separator) {
        StringBuffer sb = new StringBuffer();

        sb.append("id:").append(id).append(separator);
        sb.append("isDelete:").append(isDelete).append(separator);
        sb.append("bankName:").append(bankName).append(separator);
        sb.append("bankShortName:").append(bankShortName).append(separator);
        sb.append("version:").append(version).append(separator);
        sb.append("remark:").append(remark).append(separator);
        sb.append("createTime:").append(getCreateTimeString()).append(separator);
        sb.append("createrId:").append(createrId).append(separator);
        sb.append("createrName:").append(createrName).append(separator);
        sb.append("updateTime:").append(getUpdateTimeString()).append(separator);
        sb.append("updaterId:").append(updaterId).append(separator);
        sb.append("updaterName:").append(updaterName).append(separator);
        sb.append("bankCityCode:").append(bankCityCode).append(separator);
        sb.append("bankCcpcCode:").append(bankCcpcCode).append(separator);
        sb.append("bankLevel:").append(bankLevel).append(separator);
        sb.append("subBankName:").append(subBankName).append(separator);

        return sb.toString();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }


}
