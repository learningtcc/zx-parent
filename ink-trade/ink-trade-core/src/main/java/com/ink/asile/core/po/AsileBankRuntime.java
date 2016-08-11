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
 * @author zongtt
 * @version 1.0
 * @since 1.0
 */

public class AsileBankRuntime extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    // alias
    public static final String TABLE_ALIAS = "AsileBankRuntime";
    //
    public static final String ALIAS_ID = "主键";

    public static final String ALIAS_ASILE_NAME = "通道名称";

    public static final String ALIAS_ASILE_CODE = "通道id";

    public static final String ALIAS_BANK_NAME = "银行名称";

    public static final String ALIAS_BANK_CODE = "银行id";

    public static final String ALIAS_ASILE_PRODUCT_CODE = "通道产品编码";

    public static final String ALIAS_ASILE_PRODUCT_NAME = "通道产品名称";

    public static final String ALIAS_REMARK = "备注";

    public static final String ALIAS_CREATE_TIME = "创建时间";

    public static final String ALIAS_CREATER_ID = "createrId";

    public static final String ALIAS_CREATER_NAME = "创建人";

    public static final String ALIAS_UPDATE_TIME = "updateTime";

    public static final String ALIAS_UPDATER_ID = "updaterId";

    public static final String ALIAS_UPDATER_NAME = "updaterName";

    public static final String ALIAS_IS_DELETE = "是否删除";

    public static final String ALIAS_VERSION = "版本号";

    public static final String ALIAS_IS_ACTIVE = "isActive";

    public static final String ALIAS_INACTIVA_START_TIME = "不可用开始时间";

    public static final String ALIAS_INACTIVA_END_TIME = "不可用结束时间";

    // date formats
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
    public static final String FORMAT_INACTIVA_START_TIME = DATE_FORMAT;
    public static final String FORMAT_INACTIVA_END_TIME = DATE_FORMAT;

    // columns START
    // 主键
    private Long id;
    // 通道名称
    private String asileName;
    // 通道id
    private String asileCode;
    // 银行名称
    private String bankName;
    // 银行id
    private String bankCode;
    // 通道产品编码
    private String asileProductCode;
    // 通道产品名称
    private String asileProductName;
    // 备注
    private String remark;
    // 创建时间
    private java.util.Date createTime;
    // createrId
    private Long createrId;
    // 创建人
    private String createrName;
    // updateTime
    private java.util.Date updateTime;
    // updaterId
    private Long updaterId;
    // updaterName
    private String updaterName;
    // 是否删除
    private String isDelete;
    // 版本号
    private Integer version;
    // isActive
    private Integer isActive;
    // 不可用开始时间
    private java.util.Date inactivaStartTime;
    // 不可用结束时间
    private java.util.Date inactivaEndTime;

    // columns END

    public AsileBankRuntime() {
    }

    public AsileBankRuntime(java.lang.Long id) {
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

    public void setBankName(java.lang.String value) {
        this.bankName = value;
    }

    public java.lang.String getBankName() {
        return this.bankName;
    }

    public void setBankCode(java.lang.String value) {
        this.bankCode = value;
    }

    public java.lang.String getBankCode() {
        return this.bankCode;
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

    public void setIsDelete(java.lang.String value) {
        this.isDelete = value;
    }

    public java.lang.String getIsDelete() {
        return this.isDelete;
    }

    public void setVersion(java.lang.Integer value) {
        this.version = value;
    }

    public java.lang.Integer getVersion() {
        return this.version;
    }

    public void setIsActive(java.lang.Integer value) {
        this.isActive = value;
    }

    public java.lang.Integer getIsActive() {
        return this.isActive;
    }

    public String getInactivaStartTimeString() {
        return DateConvertUtils.format(getInactivaStartTime(), FORMAT_INACTIVA_START_TIME);
    }

    public void setInactivaStartTimeString(String value) {
        setInactivaStartTime(DateConvertUtils.parse(value, FORMAT_INACTIVA_START_TIME, java.util.Date.class));
    }

    public void setInactivaStartTime(java.util.Date value) {
        this.inactivaStartTime = value;
    }

    public java.util.Date getInactivaStartTime() {
        return this.inactivaStartTime;
    }

    public String getInactivaEndTimeString() {
        return DateConvertUtils.format(getInactivaEndTime(), FORMAT_INACTIVA_END_TIME);
    }

    public void setInactivaEndTimeString(String value) {
        setInactivaEndTime(DateConvertUtils.parse(value, FORMAT_INACTIVA_END_TIME, java.util.Date.class));
    }

    public void setInactivaEndTime(java.util.Date value) {
        this.inactivaEndTime = value;
    }

    public java.util.Date getInactivaEndTime() {
        return this.inactivaEndTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId())
                        .append("AsileName", getAsileName()).append("AsileCode", getAsileCode())
                        .append("BankName", getBankName()).append("BankCode", getBankCode())
                        .append("AsileProductCode", getAsileProductCode())
                        .append("AsileProductName", getAsileProductName()).append("Remark", getRemark())
                        .append("CreateTime", getCreateTime()).append("CreaterId", getCreaterId())
                        .append("CreaterName", getCreaterName()).append("UpdateTime", getUpdateTime())
                        .append("UpdaterId", getUpdaterId()).append("UpdaterName", getUpdaterName())
                        .append("IsDelete", getIsDelete()).append("Version", getVersion())
                        .append("IsActive", getIsActive()).append("InactivaStartTime", getInactivaStartTime())
                        .append("InactivaEndTime", getInactivaEndTime()).toString();
    }

    public String toString(String separator) {
        StringBuffer sb = new StringBuffer();

        sb.append("主键:").append(id).append(separator);
        sb.append("通道名称:").append(asileName).append(separator);
        sb.append("通道id:").append(asileCode).append(separator);
        sb.append("银行名称:").append(bankName).append(separator);
        sb.append("银行id:").append(bankCode).append(separator);
        sb.append("通道产品编码:").append(asileProductCode).append(separator);
        sb.append("通道产品名称:").append(asileProductName).append(separator);
        sb.append("备注:").append(remark).append(separator);
        sb.append("创建时间:").append(getCreateTimeString()).append(separator);
        sb.append("createrId:").append(createrId).append(separator);
        sb.append("创建人:").append(createrName).append(separator);
        sb.append("updateTime:").append(getUpdateTimeString()).append(separator);
        sb.append("updaterId:").append(updaterId).append(separator);
        sb.append("updaterName:").append(updaterName).append(separator);
        sb.append("是否删除:").append(isDelete).append(separator);
        sb.append("版本号:").append(version).append(separator);
        sb.append("isActive:").append(isActive).append(separator);
        sb.append("不可用开始时间:").append(getInactivaStartTimeString()).append(separator);
        sb.append("不可用结束时间:").append(getInactivaEndTimeString()).append(separator);

        return sb.toString();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof AsileBankRuntime == false)
            return false;
        if (this == obj)
            return true;
        AsileBankRuntime other = (AsileBankRuntime) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }
    
}
