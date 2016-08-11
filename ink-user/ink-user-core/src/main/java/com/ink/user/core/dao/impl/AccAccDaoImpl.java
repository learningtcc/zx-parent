package com.ink.user.core.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.user.core.po.AccAcc;
import com.ink.base.utils.dateUtil.DateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccAccDao;
import com.ink.user.core.po.AccCust;

/**
 * 账户表DAO
 * @author yangchen
 * @date 2016年5月24日 下午5:12:39
 */
@Repository("accAccDao")
public class AccAccDaoImpl extends BaseIbatisDao<AccAcc,Long> implements IAccAccDao{

	private YinkerLogger logger = YinkerLogger.getLogger(AccAccDaoImpl.class);
	@Autowired
    private IdCodeGenerator idCodeGenerator;

	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccAcc";
	}
	
	@Override
	protected void prepareObjectForSave(AccAcc entity) {
        if( entity.getId()==null) {
            entity.setId(IdGenerator.genUUIDStr().longValue());
        }
	}
	
	/**
	 * 根据主键删除
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("AccAcc.delete",id);
	}

	/**
	 * 插入数据
	 */
	@Override
	public int insert(AccAcc record) {
		return getSqlSession().insert("AccAcc.insert",record);
	}

	/**
	 * 根据主键更新（行锁）
	 */
//	@Override
//	public int updateByPrimaryKeySelective(AccAcc record) {
//		return getSqlSession().update("AccAcc.updateByPrimaryKeySelective",record);
//	}

	/**
	 * 根据主键更新
	 */
	@Override
	public int updateByPrimaryKey(AccAcc record) {
		return getSqlSession().update("AccAcc.updateByPrimaryKey",record);
	}

//	@Override
//	public int updateAccAccByPacIdAndSacType(AccAcc accacc) {
//		return getSqlSession().update("AccAcc.updateAccAccByPacIdAndSacType",accacc);
//	}

	/**
	 * 根据客户类型，证件类型，证件号查询
	 */
	@Override
	public List<AccAcc> selectAccAccByCustTypeAndIdTypeAndIdNo(
			Integer custType, Integer idType, String idNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("custType", custType);
		map.put("idType", idType);
		map.put("idNo", idNo);
		return getSqlSessionSlave().selectList("AccAcc.selectAccAccByCustTypeAndIdTypeAndIdNo",map);
	}

	/**
	 * 开户
	 */
	@Override
	public AccAcc createAccAcc(AccCust accCust, String sacType) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		Date now = new Date();
		String nowStr = sdf.format(now);
		now = sdf.parse(nowStr);
		AccAcc accAcc = new AccAcc();
		BigDecimal initAmt = new BigDecimal(0.00).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		Integer initVersion = new Integer(1);
		accAcc.setId(Long.valueOf(idCodeGenerator.getId()));//IdWorker.getNextId());
		accAcc.setCreateTime(now);
		accAcc.setLastUpdateTime(now);
		String sacId = accCust.getPacId().toString() + sacType;
		accAcc.setSacId(sacId);
		accAcc.setAccSacId(0l);
		accAcc.setPacId(accCust.getPacId());
		accAcc.setAccPacId(0l);
		accAcc.setUid(accCust.getUid());
		accAcc.setSacType(sacType);
		accAcc.setCur("CNY");
		accAcc.setLstBal(initAmt);
		accAcc.setCurBal(initAmt);
		accAcc.setTmpBal(initAmt);
		accAcc.setFrozenAmt(initAmt);
		accAcc.setOpenDate(now);
		accAcc.setStatus(1);
		accAcc.setDelFlag(0);
		accAcc.setVersion(initVersion);
		accAcc.setAccMac(DigestUtils.md5Hex(accAcc.getCurBal().toString().trim() + nowStr));
		logger.info("创建账户MD5，curBal : " + accAcc.getCurBal().toString()
					+ ", date : " + sdf.format(accAcc.getLastUpdateTime())
					+ ", md5 : " + DigestUtils.md5Hex(accAcc.getCurBal().toString().trim() + nowStr)
					+ ", accMac : " + accAcc.getAccMac());
		getSqlSession().insert("AccAcc.insert", accAcc);
		return accAcc;
	}

	/**
	 * 根据主账户号和账户类型查
	 */
	@Override
	public AccAcc selectAccAccByPacIdAndSacType(Long pacId, String sacType)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pacId", pacId);
		map.put("sacType", sacType);
		return (AccAcc)getSqlSessionSlave().selectOne("AccAcc.selectAccAccByPacIdAndSacType",map);
	}

	/**
	 * 查询加行锁
	 */
	@Override
	public AccAcc selectAccAccByPacIdAndSacTypeWithBLOBs(Long pacId,
			String sacType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pacId", pacId);
		map.put("sacType", sacType);
		return getSqlSession().selectOne("AccAcc.selectAccAccByPacIdAndSacTypeWithBLOBs",map);
	}

	@Override
	public int updateAccAcc(AccAcc accAcc, BigDecimal amt) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//把记录锁定
		accAcc = getSqlSession().selectOne("AccAcc.forUpdateByPrimaryKeyWithBLOBs", accAcc.getId());
		String md5 = DigestUtils.md5Hex(accAcc.getCurBal().toString().trim() + sdf.format(accAcc.getLastUpdateTime()));
		if(!md5.equals(accAcc.getAccMac())){
			logger.error(UserLoggerCnst.USER_RECHARGE_MOUDLE, "md5校验失败， sacId : " + accAcc.getSacId()
					+ "curBal : " + accAcc.getCurBal().toString().trim()
					+ ", date : " + sdf.format(accAcc.getLastUpdateTime())
					+ ", md5 : " + md5
					+ ", accMac : " + accAcc.getAccMac());
			throw new AtpBusinessException(RespCodeConstant.RespCode_300013, RespCodeConstant.RespCode_300013Desc);
		}
		//乘100四舍五入后计算
		BigDecimal amtPow = new BigDecimal(100);
		Long longAmt = amt.multiply(amtPow)
				.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
		Long longBal = accAcc.getCurBal().multiply(amtPow)
				.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();

		longBal = longBal + longAmt;

		if (longBal < 0) {
			logger.error("账户[" + accAcc.getSacId() + "]余额不可为负！");
			throw new AtpBusinessException(RespCodeConstant.RespCode_ETNS0033,
					RespCodeConstant.RespCode_ETNS0033Desc);
		}
		accAcc.setLstBal(accAcc.getCurBal());
		accAcc.setTmpBal(amt);
		accAcc.setCurBal(new BigDecimal(longBal).divide(amtPow, 2,
				BigDecimal.ROUND_HALF_UP));
		Date now = new Date();
		String nowStr = sdf.format(now);
		accAcc.setLastUpdateTime(sdf.parse(nowStr));

		accAcc.setAccMac(DigestUtils.md5Hex(accAcc.getCurBal().toString().trim() + nowStr));
		
		logger.info("md5校验，sacId : " + accAcc.getSacId() + ",curBal : " + accAcc.getCurBal().toString().trim()
					+ ", date : " + sdf.format(accAcc.getLastUpdateTime())
					+ ", accMac : " + accAcc.getAccMac());
		// 更新余额
//		long start = System.currentTimeMillis();
		int rs = getSqlSession().update("AccAcc.updateByPrimaryKeyWithBLOBs",accAcc);
//		long end = System.currentTimeMillis();
//		logger.debug("充值更新账户余额时间：" + (end - start));
		return rs;
	}

	@Override
	public AccAcc checkByPacIdAndSacType(AccCust accCust,String sacType) throws Exception {
		AccAcc accAcc = selectAccAccByPacIdAndSacTypeWithBLOBs(
				accCust.getPacId(), sacType);
		if (accAcc == null) {
			throw new AtpBusinessException(RespCodeConstant.RespCode_300003,
					RespCodeConstant.RespCode_300003Desc + "pacId : " + accCust.getPacId() 
					+ ", sacType : " + sacType);
		}
		return accAcc;
	}
	
	@Override
	public int updateForFrozenByPrimaryKeyWithBLOBs(AccAcc accAcc, BigDecimal amt) throws Exception {
		// 检查金额是否被非法篡改
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String md5 = DigestUtils.md5Hex(accAcc.getCurBal().toString().trim() + sdf.format(accAcc.getLastUpdateTime()));
		if(!md5.equals(accAcc.getAccMac())){
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "md5校验失败，curBal : " + accAcc.getCurBal().toString()
					+ ", date : " + sdf.format(accAcc.getLastUpdateTime())
					+ ", md5 : " + md5
					+ ", accMac : " + accAcc.getAccMac());
			throw new AtpBusinessException(RespCodeConstant.RespCode_300013, RespCodeConstant.RespCode_300013Desc);
		}
		Date now = new Date();
		String nowStr = sdf.format(now);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", accAcc.getId());
		map.put("lstBal", accAcc.getLstBal());//上一次
		map.put("amt", amt);
		map.put("tmpbal",accAcc.getTmpBal());
		map.put("lastUpdateTime", sdf.parse(nowStr));
		map.put("lastUpdateTimeStr", nowStr);
//		long start = System.currentTimeMillis();
		int rs = getSqlSession().update("AccAcc.updateForFrozenByPrimaryKeyWithBLOBs",map);
//		long end = System.currentTimeMillis();
//		logger.debug("提现申请更新账户余额时间消耗：" + (end - start));
		return rs;
	}
	
	@Override
	public int updateForWithdrawAcceptByPrimaryKeyWithBLOBs(AccAcc accAcc, BigDecimal amt) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String md5 = DigestUtils.md5Hex(accAcc.getCurBal().toString().trim() + sdf.format(accAcc.getLastUpdateTime()));
		if(!md5.equals(accAcc.getAccMac())){
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "md5校验失败，curBal : " + accAcc.getCurBal().toString()
					+ ", date : " + sdf.format(accAcc.getLastUpdateTime())
					+ ", md5 : " + md5
					+ ", accMac : " + accAcc.getAccMac());
			throw new AtpBusinessException(RespCodeConstant.RespCode_300013, RespCodeConstant.RespCode_300013Desc);
		}
		Date now = new Date();
		String nowStr = sdf.format(now);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", accAcc.getId());
		map.put("amt", amt);
		map.put("lastUpdateTime", sdf.parse(nowStr));
		map.put("lastUpdateTimeStr", nowStr);
//		long start = System.currentTimeMillis();
		int rs = getSqlSession().update("AccAcc.updateForWithdrawAcceptByPrimaryKeyWithBLOBs",map);
//		long end = System.currentTimeMillis();
//		logger.debug("提现受理更新账户余额时间消耗：" + (end - start));
		return rs;

	}
	
	@Override
	public List<AccAcc> selectListByPacIdAndSacType(Long pacId, String sacType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pacId", pacId);
		map.put("sacType", sacType);
		return getSqlSessionSlave().selectList("AccAcc.selectListByPacIdAndSacType",map);
	}

    @Override
    public List<AccAcc> getAccAccByPacId(String pacId) {
    	return getSqlSessionSlave().selectList("AccAcc.getAccAccByPacId",pacId);
    };
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
    @Override
    public int insertSelective(AccAcc record) {
    	return getSqlSession().insert("AccAcc.insertSelective",record);
    }

	@SuppressWarnings("unchecked")
	public Page<AccAcc> findCustPage(@SuppressWarnings("rawtypes") PageRequest query) {
        return pageQuery("AccAcc.findCustPage", query);
    }
	
	@SuppressWarnings("unchecked")
	public Page<AccAcc> findMchPage(@SuppressWarnings("rawtypes") PageRequest query) {
        return pageQuery("AccAcc.findMchPage", query);
    }

	@Override
	public int updateMchAccount(BigDecimal amt, Long mchId, String type) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String nowStr = sdf.format(now);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pacId", mchId);
		map.put("amt", amt);
		map.put("sacType", type);
		map.put("lastUpdateTime", sdf.parse(nowStr));
		map.put("lastUpdateTimeStr", nowStr);
		return getSqlSession().update("AccAcc.updateMchAccount",map);

	}

	@Override
	public int initLastUpdateTime(Date date) {
		int i = getSqlSession().update(getIbatisSqlMapNamespace()+".initUpdateLastTime", date);
		return i;
	}

	@Override
	public int initAccMac(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(date);
		int j = getSqlSession().update(getIbatisSqlMapNamespace()+".initAccMac", dateStr);
		return j;
	}
}
