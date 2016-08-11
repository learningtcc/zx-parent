package com.ink.platform.realm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecResourceService;
import com.ink.platform.api.service.ISecRoleService;
import com.ink.platform.api.service.ISecUserService;
import com.ink.platform.api.util.SecurityConstant;


/**
 * 权限框架基础权限服务bean，本类通过xml注入
 * @author lww
 *
 */

public class MyRealm extends AuthorizingRealm  implements Realm {

	@Autowired
	private ISecUserService secUserService;
	@Autowired
	private ISecResourceService iSecResourceService;
	@Autowired
	private  ISecRoleService  iSecRoleService;
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		 SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
    	//若该方法什么都不做直接返回null,自动跳转到unauthorizedUrl指定的地址  
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置  
    	//获取用户名
    	String loginName = (String)super.getAvailablePrincipal(principals); 
    	if(loginName == null){
        	return null;
        }
	        //通过用户昵称查询用户信息，若结果为null则返回令牌为null
    	Map<Object, Object> map =new HashMap<Object,Object>();
		map.put("loginName", loginName);
	    SecUser users =  secUserService.queryUserByMap(map);
	    if(users == null){
	        	return null;  
	        }
	 //   redisCache.put(users.getLoginName(), users);
	    //鉴权当前用户:角色
	    Map<Object, Object> maps  = new HashMap<>();
	    maps.put("userId", users.getId().toString());
	    maps.put("delFlag", SecurityConstant.Delete_Type.No);
	    maps.put("status", SecurityConstant.Status_Type.Enable_Status);
	    List<String > roles = new ArrayList<>();
	    List<String> permissionList = new ArrayList<String>();
	    List<SecRole>  roleList  = iSecRoleService.selectUserRole(maps);
	    
	    List<SecResource>  re = new ArrayList<>();
	    List<SecResource>  prmsnList = new ArrayList<>();
	    for(SecRole role:roleList){
	    	roles.add(role.getRoleName());
	    	 Map<Object, Object> roleMap  = new HashMap<>();
	    	 roleMap.put("roleId", role.getId());
	    	 roleMap.put("sysCode", "shiro_manager");
	    	re =iSecResourceService.getRoleResourceList(roleMap);
	    	prmsnList.addAll(re);
	    }
	    info.addRoles(roles);
	    //鉴权当前用户：权限
 
	  //  List<SecResource>  prmsnList = iSecResourceService.getResourceAndOperation(users.getId().toString());
	  
	    for (SecResource resource: prmsnList) {
	    	permissionList.add(resource.getPermission());
	    }	    
	    info.addStringPermissions(permissionList);
	    return info;  
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		 //获取基于用户名和密码的令牌  
        UsernamePasswordToken loginName = (UsernamePasswordToken)authcToken;  
        //logger.debug("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(userNmPwd, ToStringStyle.MULTI_LINE_STYLE));  
        
        //通过用户昵称查询用户信息，若结果为null则返回令牌为null
        Map<Object, Object> map =new HashMap<Object,Object>();
		map.put("loginName", loginName.getUsername());
	    SecUser users =  secUserService.queryUserByMap(map);
        if(users == null){
        	return null;  
        }
        //返回一个和令牌相关的验证信息 ：用户昵称，数据库中用户密码
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(users.getLoginName().toString(), users.getPassword().toString(), this.getName());
        return authcInfo; 
	}

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        super.clearCachedAuthorizationInfo(principals);
        super.clearCache(principals);
        super.clearCachedAuthenticationInfo(principals);
        super.doClearCache(principals);
    }
    /** 重写退出时缓存处理方法 */  
    protected void doClearCache(PrincipalCollection principalcollection) {  
        Object principal = principalcollection.getPrimaryPrincipal();  
    }  
    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
      // redisCache.clear();
        Cache<Object, AuthenticationInfo> cache = getAuthenticationCache();
        if (null != cache) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
