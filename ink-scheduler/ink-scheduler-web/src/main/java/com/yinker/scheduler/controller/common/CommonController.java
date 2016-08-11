package com.yinker.scheduler.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yinker.platform.api.dubbo.IUserResourceService;
import com.yinker.platform.api.model.SecResource;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yinker.base.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("common")
@Controller
public class CommonController extends BaseController {

	@Autowired
	private IUserResourceService userResourceService;
	
	/**
	 * 访问JSP页面
	 * @param path
	 * @return
	 */
	@RequestMapping("/location")
	public ModelAndView location(String path){
		return new ModelAndView(path);
	}
	
	/**
	 * 访问JSP页面
	 * @return
	 */
	@RequestMapping(value="/menu")
	@ResponseBody
	public Object loadMenu(HttpSession session){
		String ctx = getContext();

		//客户
		List<Map<String, Object>> urlList = new ArrayList<Map<String,Object>>();

		//取权限
		String userName = "";
		Assertion assertion = session != null ? (Assertion) session.getAttribute("yinker_sso_user") : null;
		if(assertion!= null){
			userName = assertion.getPrincipal().getName();
		}

		//遍历资源列表
		Map<String, Object> map = null;
		List<SecResource> resources = userResourceService.resourceService(userName, "yinker-scheduler-web");
		for(SecResource r : resources){
			map = new HashMap<String, Object>();
			map.put("id", r.getId());
			map.put("pid", r.getPid());
			map.put("srcurl", ctx+r.getPermission());
			map.put("name", r.getResourceName());
			urlList.add(map);
		}

		return urlList;
		
	}

	/** 框架页面*/
	@RequestMapping(value="/frame")
	public ModelAndView frame(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.addObject("loginOutService", session.getAttribute("service"));
		return new ModelAndView("/login/frame");
	}

}
