package com.ink.user.core.po.mongo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value="tns_ace_log",noClassnameStored=true)
public class TnsAceLog implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1185363322213052624L;
	
	@Id
	private ObjectId _id;
	
	/** 数据库表主键 */
	@Property("id")
	@Indexed(value = IndexDirection.ASC, unique = true)
	private Long id;

	/** 创建人 */
	@Property("owner")
	private String owner;

	/** 创建人所属机构 */
	@Property("ownerGroup")
	private String ownerGroup;

	/** 创建时间 */
	@Property("create_time")
	private String createTime;

	/** 最后修改时间 */
	@Property("last_update_time")
	private String lastUpdateTime;

	/** 会计分录表主键 */
	@Property("tns_ace_Id")
	private Long tnsAceId;

	/** 交易流水表主键 */
	@Property("tns_log_id")
	private Long tnsLogId;

	/** 联机交易代码表主键 */
	@Property("tns_txn_id")
	private Long tnsTxnId;

	/** 交易代码 */
	@Property("txn_code")
	private String txnCode;

	/** 分录组号 */
	@Property("ace_group")
	private String aceGroup;

	/** 交易金额方向 D-借 C-贷 */
	@Property("dir")
	private String dir;

	/** 记帐日期 */
	@Property("acc_date")
	private String accDate;

	/** 交易渠道 */
	@Property("channel")
	private String channel;

	/** 开销户标识 N-无开销户 O-开户 C-销户 */
	@Property("open_close_flg")
	private String openCloseFlg;

	/** 账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	@Property("acc_type")
	private String accType;

	/** 账户号 客户账户填写客户号, 商户账户填写商户编号, 机构账户填写机构编号, 科目账户填写科目号 */
	@Property("acc_id")
	private String accId;

	/** 账户名称 客户账户填写姓名, 商户账户填写商户名称, 机构账户填写机构名称, 科目账户填写科目名称 */
	@Property("acc_name")
	private String accName;

	/** 账户资金号 客户账户填写主账号，商户账户、机构账户和科目账户填写内部账户号 */
	@Property("acc_amt_id")
	private String accAmtId;

	/** 账户号的子帐户类型, 记录该交易访问了哪些子帐户, 每4位表示一种子帐户类型, 按借贷子帐户顺序记录 */
	@Property("acc_sub_type")
	private String accSubType;

	/** 账户币种 */
	@Property("acc_cur")
	private String accCur;

	/** 账户上级科目号 */
	@Property("acc_up_item_id")
	private String accUpItemId;

	/** 对方账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	@Property("aga_type")
	private String agaType;

	/** 对方账户号 客户账户填写客户号, 商户账户填写商户编号, 机构账户填写机构编号, 科目账户填写科目号 */
	@Property("aga_id")
	private String agaId;

	/** 对方账户名称 客户账户填写姓名, 商户账户填写商户名称, 机构账户填写机构名称, 科目账户填写科目名称 */
	@Property("aga_name")
	private String agaName;

	/** 对方账户资金号 客户账户填写主账号，商户账户、机构账户和科目账户填写内部账户号 */
	@Property("aga_amt_id")
	private String agaAmtId;

	/** 对方账户号的子帐户类型, 记录该交易访问了哪些子帐户, 每4位表示一种子帐户类型, 按借贷子帐户顺序记录 */
	@Property("aga_sub_type")
	private String agaSubType;

	/** 对方账户币种 */
	@Property("aga_cur")
	private String agaCur;

	/** 对方账户上级科目号 */
	@Property("aga_up_item_id")
	private String agaUpItemId;

	/** 交易金额 */
	@Property("amt")
	private String amt;

	/** 余额 交易后的账户余额 */
	@Property("bal")
	private String bal;

	/** 冲正标志 N-正常 Y-红字 B-蓝字 */
	@Property("rev_flg")
	private String revFlg;

	/**  */
	@Version
	@Property("version")
	private Long version;
	
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
	

	public TnsAceLog() {
		this.id = 01l;
		this.owner = "";
		this.ownerGroup = "";
		this.createTime = "";
		this.lastUpdateTime = "";
		this.tnsAceId = 01l;
		this.tnsLogId = 01l;
		this.tnsTxnId = 01l;
		this.txnCode = "";
		this.aceGroup = "";
		this.dir = "";
		this.accDate = "";
		this.channel = "";
		this.openCloseFlg = "";
		this.accType = "";
		this.accId = "";
		this.accName = "";
		this.accAmtId = "";
		this.accSubType = "";
		this.accCur = "";
		this.accUpItemId = "";
		this.agaType = "";
		this.agaId = "";
		this.agaName = "";
		this.agaAmtId = "";
		this.agaSubType = "";
		this.agaCur = "";
		this.agaUpItemId = "";
		this.amt = "";
		this.bal = "";
		this.revFlg = "";
		this.errorCode = "";
		this.errorMsg = "";
		this.tsnCode="";
		this.operationType = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	public String getOwner() {
		return owner;
	}




	public void setOwner(String owner) {
		this.owner = owner;
	}




	public String getOwnerGroup() {
		return ownerGroup;
	}




	public void setOwnerGroup(String ownerGroup) {
		this.ownerGroup = ownerGroup;
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




	public Long getTnsAceId() {
		return tnsAceId;
	}




	public void setTnsAceId(Long tnsAceId) {
		this.tnsAceId = tnsAceId;
	}




	public Long getTnsLogId() {
		return tnsLogId;
	}




	public void setTnsLogId(Long tnsLogId) {
		this.tnsLogId = tnsLogId;
	}




	public Long getTnsTxnId() {
		return tnsTxnId;
	}




	public void setTnsTxnId(Long tnsTxnId) {
		this.tnsTxnId = tnsTxnId;
	}




	public String getTxnCode() {
		return txnCode;
	}




	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}




	public String getAceGroup() {
		return aceGroup;
	}




	public void setAceGroup(String aceGroup) {
		this.aceGroup = aceGroup;
	}




	public String getDir() {
		return dir;
	}




	public void setDir(String dir) {
		this.dir = dir;
	}




	public String getAccDate() {
		return accDate;
	}




	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}




	public String getChannel() {
		return channel;
	}




	public void setChannel(String channel) {
		this.channel = channel;
	}




	public String getOpenCloseFlg() {
		return openCloseFlg;
	}




	public void setOpenCloseFlg(String openCloseFlg) {
		this.openCloseFlg = openCloseFlg;
	}




	public String getAccType() {
		return accType;
	}




	public void setAccType(String accType) {
		this.accType = accType;
	}




	public String getAccId() {
		return accId;
	}




	public void setAccId(String accId) {
		this.accId = accId;
	}




	public String getAccName() {
		return accName;
	}




	public void setAccName(String accName) {
		this.accName = accName;
	}




	public String getAccAmtId() {
		return accAmtId;
	}




	public void setAccAmtId(String accAmtId) {
		this.accAmtId = accAmtId;
	}




	public String getAccSubType() {
		return accSubType;
	}




	public void setAccSubType(String accSubType) {
		this.accSubType = accSubType;
	}




	public String getAccCur() {
		return accCur;
	}




	public void setAccCur(String accCur) {
		this.accCur = accCur;
	}




	public String getAccUpItemId() {
		return accUpItemId;
	}

	public void setAccUpItemId(String accUpItemId) {
		this.accUpItemId = accUpItemId;
	}

	public String getAgaType() {
		return agaType;
	}

	public void setAgaType(String agaType) {
		this.agaType = agaType;
	}

	public String getAgaId() {
		return agaId;
	}

	public void setAgaId(String agaId) {
		this.agaId = agaId;
	}

	public String getAgaName() {
		return agaName;
	}

	public void setAgaName(String agaName) {
		this.agaName = agaName;
	}

	public String getAgaAmtId() {
		return agaAmtId;
	}

	public void setAgaAmtId(String agaAmtId) {
		this.agaAmtId = agaAmtId;
	}

	public String getAgaSubType() {
		return agaSubType;
	}

	public void setAgaSubType(String agaSubType) {
		this.agaSubType = agaSubType;
	}

	public String getAgaCur() {
		return agaCur;
	}

	public void setAgaCur(String agaCur) {
		this.agaCur = agaCur;
	}

	public String getAgaUpItemId() {
		return agaUpItemId;
	}

	public void setAgaUpItemId(String agaUpItemId) {
		this.agaUpItemId = agaUpItemId;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getBal() {
		return bal;
	}

	public void setBal(String bal) {
		this.bal = bal;
	}

	public String getRevFlg() {
		return revFlg;
	}

	public void setRevFlg(String revFlg) {
		this.revFlg = revFlg;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
}
