package com.ink.route.manager.impl;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.route.api.model.po.AsileBank;
import com.ink.route.api.model.query.AsileBankQuery;
import com.ink.route.dao.IAsileBankDao;
import com.ink.route.manager.IAsileBankManager;

@Service("asileBankManager")
@Transactional
public class AsileBankManagerImpl extends BaseManager<AsileBank, Long>
        implements IAsileBankManager {

    private static final YinkerLogger logger = YinkerLogger.getLogger(AsileBankManagerImpl.class);
    @Autowired
    private IAsileBankDao asileBankDao;
//    @Autowired
    private Yedis redisClient;

    private static final String cacheKey = "route.bankSupport.all";

    @Override
    protected EntityDao<AsileBank, Long> getEntityDao() {
        return asileBankDao;
    }

    @Override
    public List<AsileBank> findBankByLimitAndCodeAndTime(
            AsileBank record, Date tradeDate) {
        return asileBankDao.findBankByLimitAndCodeAndTime(record,
                tradeDate);
    }

    @Override
    public AsileBank findAsileBankByAsileCode(String asileCode, String bankCode,String payType) {
        return asileBankDao.findAsileBankByAsileCode(asileCode, bankCode,payType);
    }

    @Override
    public long findAsileBankPriorityMaxSum(String bankCode) {
        return asileBankDao.findAsileBankPriorityMaxSum(bankCode);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
    public int update(AsileBank record) {
        int affectRow = asileBankDao.update(record);
        getFromDB();
        return affectRow;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
    public int updateNotNull(AsileBank record) {
        int affectRow = asileBankDao.updateNotNull(record);
        getFromDB();
        return affectRow;
    }
    
    @Override
    public AsileBank getAsileBankBybankShort(String bankShort) {

        List<AsileBank> list = asileBankDao.getAsileBankBybankShort(bankShort);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public List<AsileBank> getAsileBankByBankShortAndAmtLimit(AsileBank asileBank) {
        CacheObject cache = redisClient.getObject("AsileBank-", cacheKey, String.class, new Reader<Object>() {
            @Override
            public String readerFromDatabase() {
                return JSONObject.toJSONString(asileBankDao.getAll());
            }
        });
        List<AsileBank> asileBanks = JSON.parseArray(cache.getValue().toString(), AsileBank.class);
        if(CollectionUtils.isEmpty(asileBanks)){
            return null;
        }
        // 过滤
        Iterator<AsileBank> iterator = asileBanks.iterator();
        while (iterator.hasNext()) {
            AsileBank temp = iterator.next();
            /**
             * 路由逻辑，保留符合以下条件的记录
             * 1、银行编码相同
             * 2、在限额以内
             * 3、在服务时间内
             * 4、支付类型符合
             */
            if (!(asileBank.getBankShort().equals(temp.getBankShort())// 银行编码
                    && asileBank.getAsileCrashLimit().compareTo(temp.getAsileCrashLimit()) != 1// 限额
                    && asileBank.getCreateTime().compareTo(temp.getAsileServiceBeginTime()) != -1 // 服务时间
                    && asileBank.getCreateTime().compareTo(temp.getAsileServiceEndTime()) != 1
                    && asileBank.getAsilePayType().equals(temp.getAsilePayType()))// 支付类型
                    ) {
                iterator.remove();
            }
        }
        return asileBanks;
    }


    private List<AsileBank> getFromDB() {
        List<AsileBank> list = asileBankDao.getAll();
        redisClient.cacheObject("AsileBank-", cacheKey, JSONObject.toJSONString(list));
        return list;
    }

    @Override
    public void deleteByEntity(AsileBank entity) throws DataAccessException {
        super.deleteByEntity(entity);
        getFromDB();
    }

    @Override
    public int removeById(Long id) throws DataAccessException {
    	//查询删除通道银行所在业务组（支付类型和银行）
    	AsileBank asileBank=this.getById(id);
        int affectRow = super.removeById(id);
		AsileBankQuery query=new AsileBankQuery();
        query.setBankShort(asileBank.getBankShort());
        query.setAsilePayType(asileBank.getAsilePayType());
        List<AsileBank> asileBanks=this.find(query);
        //按优先级排序
        Collections.sort(asileBanks);
        int initPriority=1;
        //重新设置路由级别
        for(AsileBank bank:asileBanks){
        	bank.setPriority(initPriority);
        	initPriority++;
        }
        //更新优先级字段
        this.updateList(asileBanks);
        getFromDB();
        return affectRow;
    }

    @Override
    public int saveBatch(List<AsileBank> list) throws DataAccessException {
        int affectRow = super.saveBatch(list);
        getFromDB();
        return affectRow;
    }

    @Override
    public int save(AsileBank entity) throws DataAccessException {
        int affectRow = super.save(entity);
        getFromDB();
        return affectRow;
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 10)
	@Override
	public void updateList(List<AsileBank> records) {
		for(AsileBank record:records){
		  asileBankDao.updateNotNull(record);
		}
	    getFromDB();
		
	}


}
