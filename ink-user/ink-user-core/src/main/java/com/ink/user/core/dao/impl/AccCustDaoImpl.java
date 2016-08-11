package com.ink.user.core.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.base.utils.IdCodeGenerator;
import com.yinker.pats.global.key.enums.GlobalKey;
import com.yinker.pats.global.key.service.factory.GlobalKeyFactory;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.OpenAccountInput;
import com.ink.user.core.dao.IAccAccDao;
import com.ink.user.core.dao.IAccCardDao;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccCard;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.util.DateUtils;
import com.ink.user.util.SeqUtils;
import com.ink.user.util.SeqUtils.SerialNumber;

@Repository("accCustDao")
public class AccCustDaoImpl extends BaseIbatisDao<AccCust, Long> implements
		IAccCustDao {


	@Autowired
	private IAccCardDao accCardDao;
	@Autowired
	private IAccAccDao accAccDao;
	@Autowired
	private GlobalKeyFactory globalKeyFactory;
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccCust";
	}

	@Override
	protected void prepareObjectForSave(AccCust entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().toString());
		}
	}

	@Override
	public List<AccCust> selectAccCustInfo(String idType, String idNo) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idType", idType);
		map.put("idNo", idNo);
		return getSqlSessionSlave().selectList("AccCust.selectAccCustInfo", map);
	}

	@Override
	public AccCust selectAccCustByCustIdAndMchId(Long mchId, String custId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mchId", mchId);
		map.put("custId", custId);
		return getSqlSessionSlave().selectOne(
				"AccCust.selectAccCustByCustIdAndMchId", map);
	}

	@Override
	public AccCust checkAccCust(Long mchId, String custId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mchId", mchId);
		map.put("custId", custId);
		AccCust accCust = getSqlSessionSlave().selectOne(
				"AccCust.selectAccCustByCustIdAndMchId", map);
		if (null == accCust) {
			logger.error("根据客户Id查询客户信息为空！custId=【" + custId + "】");
			throw new AtpBusinessException(RespCodeConstant.RespCode_300001,
					RespCodeConstant.RespCode_300001Desc
					+ "custId ："+ custId
					+ "mchId : " + mchId);
		}
		return accCust;
	}

	@Override
	public AccCust existCheckAccCust(Long mchId, String custId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mchId", mchId);
		map.put("custId", custId);
		return getSqlSessionSlave().selectOne(
				"AccCust.selectAccCustByCustIdAndMchId", map);
	}

	@Override
	public AccCust insertAccCust(ReqLog reqLog, Object dto) throws Exception {
		logger.info("进去AccCustServiceImpl的insertAccCust()方法进行新增客户.....");
		AccCust accCust = new AccCust();
		OpenAccountInput input = (OpenAccountInput) dto;
		
		BeanCopier copier = BeanCopier.create(ReqLog.class, AccCust.class,
				false);
		copier.copy(reqLog, accCust, null);

		BeanCopier dtoCopier = BeanCopier.create(OpenAccountInput.class,
				AccCust.class, false);
		dtoCopier.copy(dto, accCust, null);

		accCust.setId(idCodeGenerator.getId());// 设置主键(需要主键生成规则)
		accCust.setUid(globalKeyFactory.getKeyService(GlobalKey.cust)
				.getKeyNext());// 用户唯一标识
		accCust.setPacId(globalKeyFactory.getKeyService(GlobalKey.account)
				.getKeyNext());// 主账号
		if(input.getBirthday() != null && !input.getBirthday().trim().equals("")){
			accCust.setBirthday(DateUtils.getDate(input.getBirthday()));
		}
		accCust.setStatus(1);// 1:启用状态
		accCust.setCustType(0);// 0-个人
		accCust.setDelFlag(0);
		
		Date date = new Date();
		accCust.setCreateTime(date);
		accCust.setLastUpdateTime(date);
		getSqlSession().insert("AccCust.insertSelective", accCust);
		return accCust;
	}

	@Override
	public void updateAccCust(AccCust accCust) {
		accCust.setLastUpdateTime(new Date());
		int i = getSqlSession().update("AccCust.updateByPrimaryKeySelective",
				accCust);
		if (i == 0) {
			logger.error("更新客户信息失败！accCust=【" + accCust.toString() + "】");
			throw new AtpBusinessException(RespCodeConstant.ErrorCode_00005,
					RespCodeConstant.ErrorCode_00005Desc + ", accCust : " + accCust);
		}
	}

	@Override
	public AccCust selectByPrimaryKey(Long id) {
		return getSqlSessionSlave().selectOne("AccCust.selectByPrimaryKey", id);
	}

	@Override
	public AccCust selectAccCustByCustTypeAndCustIdAndMchId(Integer custType,
			String mchId, String custId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custType", custType);
		map.put("mchId", mchId);
		map.put("custId", custId);
		return getSqlSessionSlave().selectOne(
				"AccCust.selectAccCustByCustTypeAndCustIdAndMchId", map);
	}

	@Override
	public AccCust selectByuid(Long uid) {
		return getSqlSessionSlave().selectOne("AccCust.selectByuid", uid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
	@Override
	public int updateByPrimaryKey(AccCust record) {
		return getSqlSession().update("AccCust.updateByPrimaryKey", record);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
	@Override
	public int insertSelective(AccCust record) {
		return getSqlSession().insert("insertSelective", record);
	}

	public void add(AccCust accCust, String cardNo, String cardType)
			throws Exception {
		// 设置Id
		String id = SeqUtils.getId(SerialNumber.MERCHANT).toString();
		String cardid = SeqUtils.getId(SerialNumber.MERCHANT).toString();
		Long accId = SeqUtils.getId(SerialNumber.INTERNALACC);
		// 初始化各户信息
		accCust.setId(id);
		accCust.setLastUpdateTime(new Date());
		accCust.setVersion(1);
		accCust.setUid(globalKeyFactory.getNextGlobalKey(GlobalKey.cust));
		// 初始化主账户信息
		AccAcc accAcc = new AccAcc();
		accAcc.setId(accId);
		accAcc.setVersion(1);
		accAcc.setUid(accCust.getUid());
		accAcc.setPacId(globalKeyFactory.getNextGlobalKey(GlobalKey.account));
		accCust.setPacId(accAcc.getPacId());
		accAcc.setCur("CNY");
		accAcc.setOpenDate(new Date());
		accAcc.setStatus(1);
		accAcc.setDelFlag(0);
		accAcc.setLastUpdateTime(new Date());
		// 初始化银行卡信息
		AccCard card = new AccCard();
		card.setCardType(cardType);
		card.setId(cardid);
		card.setCardNo(cardNo);
		card.setUid(accCust.getUid());
		card.setCustType(accCust.getCustType());
		card.setMchId(accCust.getMchId());
		card.setCreateTime(new Date());
		card.setLastUpdateTime(new Date());
		card.setIdNo(accCust.getIdNo());
		card.setCustName(accCust.getCustName());
		card.setBankMblNo(accCust.getMblNo());
		card.setStatus(1);
		card.setMchId(accCust.getMchId());
		accCust.setCustType(1);
		card.setCustType(1);
		// 保存信息

		accCardDao.insertSelective(card);
		getSqlSession().insert("AccCust.insertSelective", accCust);
		accAccDao.insertSelective(accAcc);
	}

	@Override
	public AccCust selectByMchIdAndCustType(Long mchId, int custType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mchId", mchId);
		map.put("custType", custType);
		return getSqlSessionSlave().selectOne("AccCust.selectByMchIdAndCustType",
				map);
	};

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
	@Override
	public int updateByPrimaryKeySelective(AccCust record) {
		return getSqlSession().update("AccCust.updateByPrimaryKeySelective",
				record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("AccCust.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(AccCust record) {
		return getSqlSession().insert("AccCust.insert", record);
	}

	@Override
	public List<AccCust> selectAccCustAllInfo(AccCust accCust) {
		return getSqlSessionSlave().selectList("AccCust.selectAccCustAllInfo",
				accCust);
	}
}
