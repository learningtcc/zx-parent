package com.ink.platform.controller.resource;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ink.platform.controller.BaseController;
import com.ink.platform.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.service.ISecResourceService;
import com.ink.platform.api.util.PlatformLoggerCnst;

/**
 * 权限平台菜单树
 * @author lww
 *
 */
@Controller("menu")
public class ResourceController extends BaseController {

	//通过tld标签页定位到此方法，查询左侧菜单并显示
	@Autowired
	private ISecResourceService secResourceService;
	YinkerLogger logger = YinkerLogger.getLogger(ResourceController.class);

	/**
	 * 管理后台登录用户所拥有的权限资源
	 * @param request
	 * @return
	 */
	
	@RequestMapping("getResource")
	@ResponseBody 
	public String getResource( HttpServletRequest request){

		String userId  =  getSessionAttribute("USER_ID");
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "查询用户权限 userid="+userId);
		
		String sysCode ="shiro_manager";
		if(StringUtils.isBlank(userId)){
			userId  = getSSOUserId(request);
		}
		Map<Object, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("sysCode", sysCode);
		List<SecResource> secResourceList =secResourceService.getResourceList(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "查询用户权限"+userId+"权限"+secResourceList);

		return 	JsonUtil.toCompatibleJSONString(secResourceList);
	}

	/**
	 * 默认加载页：
	 * @return
	 */
	@RequestMapping("main")
	public ModelAndView main() {
		return new ModelAndView("WEB-INF/templates/main");
	}

}
