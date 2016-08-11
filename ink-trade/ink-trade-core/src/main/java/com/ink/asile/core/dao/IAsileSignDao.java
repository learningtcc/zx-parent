package com.ink.asile.core.dao;

import com.ink.asile.core.po.AsileSign;
import com.ink.base.EntityDao;

public interface IAsileSignDao extends EntityDao<AsileSign, Long> {
    /**
     * 根据渠道号查询用户的签约信息
     * @param asileSign
     * @return
     */
    AsileSign selectSignIdByChannel(AsileSign asileSign);
    
    public int updateNotNull(AsileSign asileSign);
}
