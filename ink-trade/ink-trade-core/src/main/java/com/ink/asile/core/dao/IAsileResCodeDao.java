package com.ink.asile.core.dao;


import com.ink.asile.core.po.AsileResCode;
import com.ink.base.EntityDao;

public interface IAsileResCodeDao extends EntityDao<AsileResCode, Long> {
    public AsileResCode getChanPlatResCodeRel(String channelId, String resCode);
    public int updateNotNull(AsileResCode asileResCode);
}