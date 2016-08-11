package com.ink.user.core.po.mongo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = "acc_acc_log", noClassnameStored = true)
public class AccAccLog implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5988622602917575123L;

	@Id
	private ObjectId _id;

	/** 数据库表主键 */
	@Property("id")
	@Indexed(value = IndexDirection.ASC, unique = true)
	private Long id;

	/** 创建时间 */
	@Property("create_time")
	private String createTime;

	/** 最后修改时间 */
	@Property("last_update_time")
	private String lastUpdateTime;

	/** 子帐户控制表主键 */
	@Property("acc_sac_id")
	private String accSacId;

	/** 子帐户号 */
	@Property("sac_id")
	private String sacId;

	/** 主帐户控制表主键 */
	@Property("acc_pac_id")
	private String accPacId;

	/** 主账号 */
	@Property("pac_id")
	private String pacId;

	/** 子帐户种类 */
	@Property("sac_type")
	private String sacType;

	/** 币种 */
	@Property("cur")
	private String cur;

	/** 昨日余额 日终批处理更新 */
	@Property("lst_bal")
	private String lstBal;

	/** 当前余额 */
	@Property("cur_bal")
	private String curBal;

	/** 临时余额 日终使用 */
	@Property("tmp_bal")
	private String tmpBal;

	/** 冻结金额 */
	@Property("frozen_amt")
	private String frozenAmt;

	/** 开户日期 */
	@Property("open_date")
	private String openDate;

	/** 销户日期 */
	@Property("close_date")
	private String closeDate;

	/** 状态 1-启用 2-停用 9-注销 */
	@Property("status")
	private Integer status;

	/** 删除标识 0-正常 1-删除 */
	@Property("del_flag")
	private String delFlag;

	@Property("uid")
	private String uid;
	/**  */
	@Version
	@Property("version")
	private Long version;

	/** 资金账户认证信息 */
	@Property("acc_mac")
	private String accMac;
	/**错误码*/
	@Property("error_code")
	private String errorCode;
	/**错误描述*/
	@Property("error_msg")
	private String errorMsg;
	/**交易类型**/
	@Property("tsn_code")
	private String tsnCode; 
	/**操作类型*/
	@Property("operation_type")
	private String operationType;
	
	public AccAccLog() {
		this.id = 001l;
		this.createTime = "";
		this.lastUpdateTime = "";
		this.accSacId = "";
		this.sacId = "";
		this.accPacId = "";
		this.pacId = "";
		this.sacType = "";
		this.cur = "";
		this.lstBal = "";
		this.curBal = "";
		this.tmpBal = "";
		this.frozenAmt = "";
		this.openDate = "";
		this.closeDate = "";
		this.status = 0;
		this.delFlag = "";
		this.uid = "";
		this.accMac = "";
		this.errorCode = "";
		this.errorMsg = "";
		this.tsnCode = "";
		this.operationType = "";
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getAccSacId() {
		return accSacId;
	}

	public void setAccSacId(String accSacId) {
		this.accSacId = accSacId;
	}

	public String getSacId() {
		return sacId;
	}

	public void setSacId(String sacId) {
		this.sacId = sacId;
	}

	public String getAccPacId() {
		return accPacId;
	}

	public void setAccPacId(String accPacId) {
		this.accPacId = accPacId;
	}

	public String getPacId() {
		return pacId;
	}

	public void setPacId(String pacId) {
		this.pacId = pacId;
	}

	public String getSacType() {
		return sacType;
	}

	public void setSacType(String sacType) {
		this.sacType = sacType;
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur;
	}

	public String getLstBal() {
		return lstBal;
	}

	public void setLstBal(String lstBal) {
		this.lstBal = lstBal;
	}

	public String getCurBal() {
		return curBal;
	}

	public void setCurBal(String curBal) {
		this.curBal = curBal;
	}

	public String getTmpBal() {
		return tmpBal;
	}

	public void setTmpBal(String tmpBal) {
		this.tmpBal = tmpBal;
	}

	public String getFrozenAmt() {
		return frozenAmt;
	}

	public void setFrozenAmt(String frozenAmt) {
		this.frozenAmt = frozenAmt;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getAccMac() {
		return accMac;
	}

	public void setAccMac(String accMac) {
		this.accMac = accMac;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getTsnCode() {
		return tsnCode;
	}

	public void setTsnCode(String tsnCode) {
		this.tsnCode = tsnCode;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccAccLog [_id=");
		builder.append(_id);
		builder.append(", id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", accSacId=");
		builder.append(accSacId);
		builder.append(", sacId=");
		builder.append(sacId);
		builder.append(", accPacId=");
		builder.append(accPacId);
		builder.append(", pacId=");
		builder.append(pacId);
		builder.append(", sacType=");
		builder.append(sacType);
		builder.append(", cur=");
		builder.append(cur);
		builder.append(", lstBal=");
		builder.append(lstBal);
		builder.append(", curBal=");
		builder.append(curBal);
		builder.append(", tmpBal=");
		builder.append(tmpBal);
		builder.append(", frozenAmt=");
		builder.append(frozenAmt);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", closeDate=");
		builder.append(closeDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", uid=");
		builder.append(uid);
		builder.append(", version=");
		builder.append(version);
		builder.append(", accMac=");
		builder.append(accMac);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMsg=");
		builder.append(errorMsg);
		builder.append(", tsnCode=");
		builder.append(tsnCode);
		builder.append(", operationType=");
		builder.append(operationType);
		builder.append("]");
		return builder.toString();
	}

	

}
