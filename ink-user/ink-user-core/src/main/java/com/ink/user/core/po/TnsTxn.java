package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.util.DateConvertUtils;
import com.ink.user.common.constant.PatsAtpConstant;

public class TnsTxn implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2568249195979927811L;
	
	// date formats
	public static final String FORMAT_CREATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_UPDATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;

	/** 数据库表主键 */
	private String id;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 交易代码 */
	private String txnCode;

	/** 交易名称 */
	private String txnName;

	/** 交易性质 1-充值 2-消费 3-资金转入 4-资金转出 5-汇入 6-汇出 7-手续费 8-取现 9-其他 */
	private Integer txnNature;

	/** 运行级别 1-联机 2-批处理 3-联机/批处理 */
	private Integer runLv;

	/** 交易金额方向 D-借 C-贷 O-其他 */
	private String dir;

	/** 冲正标识 0-不允许 1-允许 */
	private Integer revAllowFlg;

	/** 手续费收取标识 0-不允许 1-允许 */
	private Integer feeFlg;

	/** 最小交易金额 不含手续费 */
	private BigDecimal minAmt;

	/** 最大交易金额 不含手续费 */
	private BigDecimal maxAmt;

	/** 允许的交易渠道 每4位表示一种交易渠道，如果是全部，填"0000" */
	private String channel;

	/** 默认子帐户顺序 每4位表示一种子帐户类型，表示交易默认借贷子帐户顺序 */
	private String accOrder;

	/** 允许的账户状态, 从左到右依次填入, 0-全部 1-启用 2-停用 3-待审核 4-审核拒绝 9-注销 */
	private String accStatus;

	/** 状态 1-启用 2-停用 9-注销 */
	private Integer status;

	/**  */
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode == null ? null : txnCode.trim();
	}

	public String getTxnName() {
		return txnName;
	}

	public void setTxnName(String txnName) {
		this.txnName = txnName == null ? null : txnName.trim();
	}

	public Integer getTxnNature() {
		return txnNature;
	}

	public void setTxnNature(Integer txnNature) {
		this.txnNature = txnNature;
	}

	public Integer getRunLv() {
		return runLv;
	}

	public void setRunLv(Integer runLv) {
		this.runLv = runLv;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir == null ? null : dir.trim();
	}

	public Integer getRevAllowFlg() {
		return revAllowFlg;
	}

	public void setRevAllowFlg(Integer revAllowFlg) {
		this.revAllowFlg = revAllowFlg;
	}

	public Integer getFeeFlg() {
		return feeFlg;
	}

	public void setFeeFlg(Integer feeFlg) {
		this.feeFlg = feeFlg;
	}

	public BigDecimal getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(BigDecimal minAmt) {
		this.minAmt = minAmt;
	}

	public BigDecimal getMaxAmt() {
		return maxAmt;
	}

	public void setMaxAmt(BigDecimal maxAmt) {
		this.maxAmt = maxAmt;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel == null ? null : channel.trim();
	}

	public String getAccOrder() {
		return accOrder;
	}

	public void setAccOrder(String accOrder) {
		this.accOrder = accOrder == null ? null : accOrder.trim();
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus == null ? null : accStatus.trim();
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
	
	public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}
	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,java.util.Date.class));
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TnsTxn [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", txnCode=");
		builder.append(txnCode);
		builder.append(", txnName=");
		builder.append(txnName);
		builder.append(", txnNature=");
		builder.append(txnNature);
		builder.append(", runLv=");
		builder.append(runLv);
		builder.append(", dir=");
		builder.append(dir);
		builder.append(", revAllowFlg=");
		builder.append(revAllowFlg);
		builder.append(", feeFlg=");
		builder.append(feeFlg);
		builder.append(", minAmt=");
		builder.append(minAmt);
		builder.append(", maxAmt=");
		builder.append(maxAmt);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", accOrder=");
		builder.append(accOrder);
		builder.append(", accStatus=");
		builder.append(accStatus);
		builder.append(", status=");
		builder.append(status);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}

}