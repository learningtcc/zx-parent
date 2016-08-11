package com.ink.trade.core.manager;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.Auth;

/**
 * Created by huohb on 2016/5/3.
 */
public interface IAuthManager extends IBaseManager<Auth, Long> {
    /**
     * 根据用户号和卡号查询用户的绑卡关系
     * @param auth
     * @return
     */
    Auth getByUserIdAndCardNo(Auth auth);
    int saveOrUpdate(Auth auth);
    public int updateNotNull(Auth auth);
}
