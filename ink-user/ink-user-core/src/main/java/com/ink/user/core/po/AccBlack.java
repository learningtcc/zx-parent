package com.ink.user.core.po;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AccBlack implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 3115266874512606032L;

	/** 数据库表主键 */
	private String id;

	/** 版本号 */
	private Integer version;

	/** 开户日期 */
	private Date openDate;

	/** 销户日期 */
	private Date closeDate;

	/** 状态 1-启用 2-停用 9-注销 */
	private Integer status;

	/** 删除标识 0-正常 1-删除 */
	private String delFlag;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 账户id */
	private Long accId;

	/** 账户类型id */
	private String accTypeId;

	/** 交易码 */
	private String txnCode;

	/** 账户托管类型编码 */
	private String accDepositType;

	/** 拉入黑名单原因 */
	private String blackWhy;

	/** 当天禁止开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date blackStartDate;

	/** 当天禁止结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date blackEndDate;

	/** 禁止周期开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date blackStartTime;

	/** 禁止周期结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date blackEndTime;

	/** 黑名单白名单0是黑名单，1是白名单 */
	private String blackType;

	/** 限制/允许访问ip */
	private String accessIp;
	/**姓名*/
	private String custName;
	/** 联系移动电话 */
    private String mblNo;
    /** 平台用户唯一标识 */
    private Long uid;
    /** 证件号码 */
    private String idNo;

	public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getBlackStartDate() {
		return blackStartDate;
	}

	public void setBlackStartDate(Date blackStartDate) {
		this.blackStartDate = blackStartDate;
	}

	public Date getBlackEndDate() {
		return blackEndDate;
	}

	public void setBlackEndDate(Date blackEndDate) {
		this.blackEndDate = blackEndDate;
	}

	public Date getBlackStartTime() {
		return blackStartTime;
	}

	public void setBlackStartTime(Date blackStartTime) {
		this.blackStartTime = blackStartTime;
	}

	public Date getBlackEndTime() {
		return blackEndTime;
	}

	public void setBlackEndTime(Date blackEndTime) {
		this.blackEndTime = blackEndTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public String getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(String accTypeId) {
		this.accTypeId = accTypeId;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode == null ? null : txnCode.trim();
	}

	public String getAccDepositType() {
		return accDepositType;
	}

	public void setAccDepositType(String accDepositType) {
		this.accDepositType = accDepositType == null ? null : accDepositType
				.trim();
	}

	public String getBlackWhy() {
		return blackWhy;
	}

	public void setBlackWhy(String blackWhy) {
		this.blackWhy = blackWhy == null ? null : blackWhy.trim();
	}

	public String getBlackType() {
		return blackType;
	}

	public void setBlackType(String blackType) {
		this.blackType = blackType == null ? null : blackType.trim();
	}

	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp == null ? null : accessIp.trim();
	}

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMblNo() {
        return mblNo;
    }

    public void setMblNo(String mblNo) {
        this.mblNo = mblNo;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccBlack [id=");
		builder.append(id);
		builder.append(", version=");
		builder.append(version);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", closeDate=");
		builder.append(closeDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", accId=");
		builder.append(accId);
		builder.append(", accTypeId=");
		builder.append(accTypeId);
		builder.append(", txnCode=");
		builder.append(txnCode);
		builder.append(", accDepositType=");
		builder.append(accDepositType);
		builder.append(", blackWhy=");
		builder.append(blackWhy);
		builder.append(", blackStartDate=");
		builder.append(blackStartDate);
		builder.append(", blackEndDate=");
		builder.append(blackEndDate);
		builder.append(", blackStartTime=");
		builder.append(blackStartTime);
		builder.append(", blackEndTime=");
		builder.append(blackEndTime);
		builder.append(", blackType=");
		builder.append(blackType);
		builder.append(", accessIp=");
		builder.append(accessIp);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", mblNo=");
		builder.append(mblNo);
		builder.append(", uid=");
		builder.append(uid);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append("]");
		return builder.toString();
	}
	
}