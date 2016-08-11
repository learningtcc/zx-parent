package com.ink.asile.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.asile.core.dao.IAsileSignDao;
import com.ink.asile.core.manager.IAsileSignManager;
import com.ink.asile.core.po.AsileSign;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
@Service
@Transactional
public class AsileSignManagerImpl extends BaseManager<AsileSign, Long> implements IAsileSignManager {
	@Autowired
	private IAsileSignDao iAsileSignDao;
	
	@Override
	protected EntityDao<AsileSign, Long> getEntityDao() {
		return iAsileSignDao;
	}

	/**
	 * 
	 * @Description 主要根据auth表中cid & channel & payType
	 * @author xuguoqi
	 * @date 2016年6月22日 上午10:49:26
	 * @param asileSign
	 * @return
	 */
    @Override
    public AsileSign selectSignIdByChannel(AsileSign asileSign) {
        return iAsileSignDao.selectSignIdByChannel(asileSign);
    }
    @Override
    public int updateNotNull(AsileSign asileSign){
		return iAsileSignDao.updateNotNull(asileSign);
	}
}
