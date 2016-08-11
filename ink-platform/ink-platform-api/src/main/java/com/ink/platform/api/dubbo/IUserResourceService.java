package com.ink.platform.api.dubbo;

import java.util.List;

import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.model.SecUser;

/**
 * dubbo服务接口
 * @author lww
 *
 */
public interface IUserResourceService {

	/**
	 * 登录调用接口
	 * @param loginName
	 * @param password
	 * @return
	 */
	public SecUser loginServeice(String loginName,String password);
	
	/**
	 * 用户平台权限查询接口
	 * @param loginName
	 * @param sysCode
	 * @return
	 */
	public List<SecResource> resourceService(String loginName,String sysCode);
}
