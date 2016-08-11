package com.ink.user.core.service.tns.impl;

import java.util.List;

import com.ink.user.core.dao.ITnsTxnDao;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.redis.TnsTxnCacheService;
import com.ink.user.core.service.tns.ITnsTxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;


@Service("tnsTxnService")
public class TnsTxnServiceImpl extends BaseManager<TnsTxn, Long>  implements ITnsTxnService {

	@Autowired
	private ITnsTxnDao tnsTxnDao;
	@Autowired
	private TnsTxnCacheService tnsTxnCacheService;
	
	@Override
	public TnsTxn checkTnsTxn(String txnCode) {
		TnsTxn tnsTxn = tnsTxnCacheService.getTnsTxnByTxnCode(txnCode);
		if(tnsTxn == null){
//			tnsTxn = tnsTxnDao.findByTxnCode(txnCode);
//			if(tnsTxn != null){
//				tnsTxnCacheService.setTnsTxnCache(tnsTxn);
//			}else{
				throw new AtpBusinessException(RespCodeConstant.RespCode_000009,
						RespCodeConstant.RespCode_000009Desc);
//			}
		}
		return tnsTxn;
	}
    @Override
    public int insertTnsTxn(TnsTxn tnsTxn) {
        return tnsTxnDao.insertSelective(tnsTxn);
    }
    @Override
    public int updateTnsTxn(TnsTxn tnsTxn) {
        return tnsTxnDao.updateByPrimaryKeySelective(tnsTxn);
    }
    @Override
    public TnsTxn selectByPrimaryKey(Long id){
        return tnsTxnDao.selectByPrimaryKey(id);
    }
    @Override
    public List<TnsTxn> selectAll(){
        return tnsTxnDao.selectAll();
    }
	@Override
	protected EntityDao<TnsTxn, Long> getEntityDao() {
		return this.tnsTxnDao;
	}
	
	 public int removeById(Long id) throws DataAccessException{
		 TnsTxn tnsTxn = tnsTxnDao.selectByPrimaryKey(id);
		 tnsTxnCacheService.removeTnsTxnCache(tnsTxn.getTxnCode());
		 return  tnsTxnDao.deleteById(id);
	 }
}
