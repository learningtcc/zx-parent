/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年5月3日 下午3:25:16
 */
package com.ink.trade.core.query;

import java.io.Serializable;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @Description 鉴权信息查询
 * @author xuguoqi
 * @date 2016年5月3日 下午3:25:16
 */
public class AuthQuery extends BaseQuery implements Serializable {

    /**
     * @since JDK 1.7
     */

    private static final long serialVersionUID = -586510187906502481L;

    /** 主键 */
    private Long id;

    /** 银行卡号 */
    private String cardNo;

    /** 银行卡类型 */
    private String cardType;

    /** 手机号 */
    private String phoneNo;

    /** 证件类型 */
    private String idType;

    /** 身份证号 */
    private String idNo;

    /** 姓名 */
    private String userName;

    /** 所属银行编号 */
    private String bankShort;

    /** 所属银行 */
    private String bankName;

    /** 状态 */
    private Integer status;

    /** 版本号 */
    private Integer version;

    /** 删除标识 */
    private Integer isDelete;

    /** 备注 */
    private String remark;

    /** 商户号 */
    private String mchId;

    /** 最后修改时间 */
    private Date lastupdateTimeBegin;

    private Date lastupdateTimeEnd;

    /** 请求流水号 */
    private String reqId;

    /** 客户号 */
    private String userId;
    private Date createTime;
    private String payType;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getBankShort() {
        return bankShort;
    }

    public void setBankShort(String bankShort) {
        this.bankShort = bankShort == null ? null : bankShort.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public Date getLastupdateTimeBegin() {
        return lastupdateTimeBegin;
    }

    public void setLastupdateTimeBegin(Date lastupdateTimeBegin) {
        this.lastupdateTimeBegin = lastupdateTimeBegin;
    }

    public Date getLastupdateTimeEnd() {
        return lastupdateTimeEnd;
    }

    public void setLastupdateTimeEnd(Date lastupdateTimeEnd) {
        this.lastupdateTimeEnd = lastupdateTimeEnd;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public AuthQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AuthQuery(Long id, String cardNo, String cardType, String phoneNo, String idType, String idNo,
                    String userName, String bankShort, String bankName, Integer status, Integer version,
                    Integer isDelete, String remark, String mchId, Date lastupdateTimeBegin, Date lastupdateTimeEnd,
                    String reqId, String userId) {
        super();
        this.id = id;
        this.cardNo = cardNo;
        this.cardType = cardType;
        this.phoneNo = phoneNo;
        this.idType = idType;
        this.idNo = idNo;
        this.userName = userName;
        this.bankShort = bankShort;
        this.bankName = bankName;
        this.status = status;
        this.version = version;
        this.isDelete = isDelete;
        this.remark = remark;
        this.mchId = mchId;
        this.lastupdateTimeBegin = lastupdateTimeBegin;
        this.lastupdateTimeEnd = lastupdateTimeEnd;
        this.reqId = reqId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AuthQuery [id=" + id + ", cardNo=" + cardNo + ", cardType=" + cardType + ", phoneNo=" + phoneNo
                        + ", idType=" + idType + ", idNo=" + idNo + ", userName=" + userName + ", bankShort="
                        + bankShort + ", bankName=" + bankName + ", status=" + status + ", version=" + version
                        + ", isDelete=" + isDelete + ", remark=" + remark + ", mchId=" + mchId
                        + ", lastupdateTimeBegin=" + lastupdateTimeBegin + ", lastupdateTimeEnd=" + lastupdateTimeEnd
                        + ", reqId=" + reqId + ", userId=" + userId + "]";
    }

}
