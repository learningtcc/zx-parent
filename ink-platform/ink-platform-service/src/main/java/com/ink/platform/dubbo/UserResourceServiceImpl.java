package com.ink.platform.dubbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.dubbo.IUserResourceService;
import com.ink.platform.api.dubbo.UserCacheService;
import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecResourceService;
import com.ink.platform.api.service.ISecRoleService;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.core.dao.SecUserDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class UserResourceServiceImpl implements IUserResourceService {
	YinkerLogger logg = YinkerLogger.getLogger(UserResourceServiceImpl.class);

	@Autowired
	private SecUserDao  secUserDao;

	@Autowired
	private ISecResourceService secResourceService;
	@Autowired
	private  ISecRoleService  iSecRoleService;
	@Autowired
	private  UserCacheService  userCacheService;
	@Autowired
	@Qualifier("jedisPool")
	private JedisPool jedisPool;
	private static String resourceKey ="platform-shiro-resourcekey";
	private static String roleKey ="platform-shiro-roleKey";
	@Override
	public SecUser loginServeice(String loginName,String password){
		logg.info(loginName, "登录服务调用",password);
		if(StringUtils.isBlank(loginName) ||StringUtils.isBlank(password)){
			return null;
		}
		SecUser user =userCacheService.getCacheUser(loginName);
		password =DigestUtils.md5Hex(password);
		if(password.equals(user.getPassword())){
			return user;
		}
		logg.info(loginName, "登录服务调用"+user);
		return null;

	}

	@Override
	public List<SecResource> resourceService( String loginName, String sysCode) {
		logg.info(loginName, "查询资源：参数","平台标识"+sysCode);
		
		String  keyId =loginName+'-'+sysCode;
		String  keyRole ="";
		//查询缓存角色
		Jedis jedis = jedisPool.getResource();
		String json = (String) jedis.hget(roleKey, keyId);
		logg.info(loginName, "redis+角色信息:"+json);
		//查询用户角色
		List<SecRole>  roleList  = new ArrayList<>();
		List<SecResource> re = new  ArrayList<>();
		List<SecResource> reList = new  ArrayList<>();
		if(StringUtils.isNotBlank(json)){
			roleList = JSON.parseArray(json, SecRole.class);
			for(SecRole role :roleList){
				//查询角色资源
				keyRole =role.getRoleName()+'-'+sysCode;
				String resource = (String) jedis.hget(resourceKey, keyRole);
				if(StringUtils.isNotBlank(resource)){
					re=JSON.parseArray(resource, SecResource.class);
					reList.addAll(re);
				}
			}
			logg.info(loginName, "redis+资源信息："+reList);
			return reList;
		}else{
			HashMap<Object, Object> um  = new HashMap<>();
			um.put("loginName", loginName);
			SecUser dbuser = secUserDao.queryUserByMap(um);
			//reList=secResourceService.getResourceList(userId,sysCode);
			Map<Object, Object> maps  = new HashMap<>();
			maps.put("userId", dbuser.getId());
			maps.put("delFlag", SecurityConstant.Delete_Type.No);
			maps.put("status", SecurityConstant.Status_Type.Enable_Status);
			List<SecRole>  roles  = iSecRoleService.selectUserRole(maps);
			logg.info(loginName, "db角色信息："+roles);
			if(roles.size()>0){
				//缓存存用户角色
				jedis.hset(roleKey, keyId, JSON.toJSONString(roles));
				for(SecRole role :roles){
					//根据角色存资源信息
					String roleId =String.valueOf(role.getId());
					Map<Object, Object> roleMap  = new HashMap<>();
					roleMap.put("roleId", roleId);
					roleMap.put("sysCode", sysCode);
					keyRole =role.getRoleName()+'-'+sysCode;
					re = secResourceService.getRoleResourceList(roleMap);
					if(re.size()>0){
						jedis.hset(resourceKey, keyRole, JSON.toJSONString(re));
						reList.addAll(re);
					}
				}
			}
			logg.info(loginName, "db资源信息："+reList);
			return reList;
		}
	}


}
