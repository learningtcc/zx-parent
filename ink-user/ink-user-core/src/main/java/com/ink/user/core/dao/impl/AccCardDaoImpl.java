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
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.BindCardInput;
import com.ink.user.common.constant.PatsAtpConstant;
import com.ink.user.core.dao.IAccCardDao;
import com.ink.user.core.po.AccCard;
import com.ink.user.core.po.AccCust;

@Repository("accCardDao")
public class AccCardDaoImpl extends BaseIbatisDao<AccCard, Integer> implements
		IAccCardDao {

	@Autowired
	private GlobalKeyFactory globalKeyFactory;

	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccCard";
	}

	@Override
	protected void prepareObjectForSave(AccCard entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().toString());
		}
	}

	@Override
	public List<AccCard> getPersonalAccCard(String custType, String custId,
			Long mchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custType", custType);
		map.put("custId", custId);
		map.put("mchId", mchId);
		return getSqlSessionSlave().selectList("AccCard.getPersonalAccCard", map);
	}

	@Override
	public AccCard insertAccCard(Object dto, AccCust accCust) throws Exception {

		AccCard accCard = new AccCard();
		BeanCopier copier = BeanCopier.create(BindCardInput.class,
				AccCard.class, false);
		copier.copy(dto, accCard, null);
		BeanCopier custCopier = BeanCopier.create(AccCust.class, AccCard.class,
				false);
		custCopier.copy(accCust, accCard, null);
		Long accCardId = Long.valueOf(idCodeGenerator.getId());
		accCard.setBindCardId(globalKeyFactory.getKeyService(GlobalKey.card)
				.getKeyNext());// 绑卡的唯一标识
		accCard.setId(accCardId.toString());// 设置主键(需要主键生成规则)
		accCard.setStatus(AtpTnsConstant.ACC_CARD_STATUS_1);
		accCard.setCreateTime(new Date());
		accCard.setLastUpdateTime(new Date());
		accCard.setDelFlag(0);
		accCard.setVersion(1);
		accCard = insertAccCard(accCard);
		return accCard;
	}

	@Override
	public AccCard insertAccCard(AccCard accCard) throws Exception {
		getSqlSession().insert("AccCard.insertSelective", accCard);
		return accCard;
	}

	@Override
	public AccCard checkAccCardIsExist(Long uid, String cardNo) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("cardNo", cardNo);
		map.put("status", PatsAtpConstant.CARD_STATUS_1);
		return getSqlSessionSlave().selectOne(
				"AccCard.selectAccCardByMchIdAndCardNoAndStatus", map);
	}

	@Override
	public void updateAccCardStatus(Long uid, String cardNo, String status,
			int version) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("cardNo", cardNo);
		map.put("status", Integer.parseInt(status));
		map.put("version", version);
		int i = getSqlSession().update("AccCard.updateAccCardStatus", map);
		if (i == 0) {
			logger.error("解绑银行卡失败！【uid:" + uid + "】&【card_no:" + cardNo + "】");
			throw new AtpBusinessException(RespCodeConstant.RespCode_400010,
					RespCodeConstant.RespCode_400010);
		}
	}

	@Override
	public AccCard checkBindCard(Long uid, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("status", status);
		return getSqlSessionSlave().selectOne("AccCard.checkBindCard", map);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class, timeout = 10)
	@Override
	public AccCard selectByPrimaryKey(Long id) {
		return getSqlSessionSlave().selectOne("AccCard.selectByPrimaryKey", id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
	@Override
	public int updateByPrimaryKeySelective(AccCard record) {
		return getSqlSession().update("AccCard.updateByPrimaryKeySelective",
				record);
	}

	@Override
	public AccCard selectByUidAndCustType(String custType, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custType", custType);
		map.put("uid", uid);
		return getSqlSessionSlave().selectOne("AccCard.selectByUidAndCustType", map);

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
	@Override
	public int insertSelective(AccCard record) {
		return getSqlSession().insert("AccCard.insertSelective", record);
	}

	@Override
	public int updateAccCard(AccCard accCard) {
		return getSqlSession().update("AccCard.updateByPrimaryKey", accCard);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("AccCard.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(AccCard record) {
		return getSqlSession().insert("AccCard.insert", record);
	}

	@Override
	public int updateByPrimaryKey(AccCard record) {
		return getSqlSession().update("AccCard.updateByPrimaryKey", record);
	}

	@Override
	public List<AccCard> selectAccCardByMchIdAndCardNo(Long uid, String cardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("cardNo", cardNo);
		return getSqlSessionSlave().selectList(
				"AccCard.selectAccCardByMchIdAndCardNo", map);
	}

	@Override
	public AccCard selectAccCardByMchIdAndCardNoAndStatus(Long uid,
			String cardNo, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("cardNo", cardNo);
		map.put("status", status);
		return getSqlSessionSlave().selectOne(
				"AccCard.selectAccCardByMchIdAndCardNoAndStatus", map);
	}

	@Override
	public int updateAccCardStatus(Long uid, String cardNo, Integer status,
			Integer version) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("cardNo", cardNo);
		map.put("status", status);
		map.put("version", version);
		return getSqlSession().update("AccCard.updateAccCardStatus", map);
	}

	@Override
	public AccCard checkBindCard(Long uid, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("status", status);
		return getSqlSessionSlave().selectOne("AccCard.checkBindCard", map);
	}
}
