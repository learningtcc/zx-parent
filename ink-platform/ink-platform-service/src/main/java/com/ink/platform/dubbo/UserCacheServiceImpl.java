package com.ink.platform.dubbo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.dubbo.UserCacheService;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.core.dao.SecUserDao;

@Service("userCacheService")
public class UserCacheServiceImpl  extends BaseCache implements UserCacheService{
	YinkerLogger logg = YinkerLogger.getLogger(UserCacheServiceImpl.class);


	@Autowired
	private  SecUserDao secUserDao;
	@Override
	protected Map<String, String> readFromDataBase() {
		Map<Object, Object> map= new HashMap<>();
		map.put("statusA", "1");
		List<SecUser> list = secUserDao.selectAllUser(map);
		Map<String, String> caMap = new HashMap<String, String>();
		for(SecUser user : list){
			caMap.put(user.getLoginName(), JSON.toJSONString(user));
		}
		return caMap;
	}
	@Override
	public SecUser getCacheUser(String loginName) {
		Object obj = getCache(SecurityConstant.Key.userKey, loginName, SecUser.class);
		if(obj !=null){
			return (SecUser) obj;
		}
		return null;
	}
	@Override
	public void setCacheUser(String loginName) {
		setCache( SecurityConstant.Key.userKey,loginName,SecUser.class );
		
	}
	@Override
	public void removeCacheUser(String loginName) {
		removeCache( SecurityConstant.Key.userKey, loginName);
	}
	

}
