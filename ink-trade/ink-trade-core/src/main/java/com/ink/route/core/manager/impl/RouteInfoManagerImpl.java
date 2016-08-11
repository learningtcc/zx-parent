package com.ink.route.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.route.core.manager.IRouteInfoManager;
import com.ink.route.core.po.RouteInfo;

public class RouteInfoManagerImpl extends BaseManager<RouteInfo, Long> implements IRouteInfoManager{

    @Override
    protected EntityDao<RouteInfo, Long> getEntityDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
