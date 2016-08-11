package com.ink.admin.monitor.controller.user;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.base.service.impl.DynamicDataManagerImpl;
import com.ink.monitor.core.po.MonitorUser;
import com.ink.monitor.core.po.MonitorUserRule;
import com.ink.monitor.core.po.MonitorUserService;
import com.ink.monitor.core.query.MonitorUserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("monitor/user")
@Controller
public class UserController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "updateTime desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/user/list";
	protected static final String CREATE_JSP = "/monitor/user/create";
	protected static final String EDIT_JSP = "/monitor/user/edit";
	protected static final String SHOW_JSP = "/monitor/user/show";

	@Autowired
	private DubboBaseService monitorDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() throws InterruptedException {
		
		MonitorUserQuery query = newQuery(MonitorUserQuery.class,DEFAULT_SORT_COLUMNS);
		query.setStatusArray(new String[]{"0","1"});
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("monitorUserManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}
	
	/** 保存新增对象 
	 * @throws IOException */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(MonitorUser monitorUser) throws IOException {
		
		try {
			MonitorUserQuery query = new MonitorUserQuery();
			query.setUserName(monitorUser.getUserName());
			query.setStatusArray(new String[]{"0","1"});
			List<MonitorUser> queryList = null;
			queryList = monitorDubboBaseService.find("monitorUserManager",query);
			if(queryList != null && queryList.size() > 0){
				//账号已存在
				return writeAjaxResponse("0-1");
			}
			monitorUser.setCreateTime(new Date());
			monitorUser.setUpdateTime(new Date());
			monitorUser.setStatus("0");
			monitorDubboBaseService.save("monitorUserManager",monitorUser);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}

		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MonitorUser monitorUser) {
		MonitorUser model = null;
		try {
			model = (MonitorUser) monitorDubboBaseService.getById("monitorUserManager",monitorUser.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MonitorUser monitorUser) {
		MonitorUser model = null;
		try {
			model = (MonitorUser) monitorDubboBaseService.getById("monitorUserManager",monitorUser.getUserId());
		} catch (Exception e) {

		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(MonitorUser monitorUser) {

		try {
			MonitorUserQuery query = new MonitorUserQuery();
			query.setUserName(monitorUser.getUserName());
			query.setStatusArray(new String[]{"0","1"});
			List<MonitorUser> queryList = null;
			queryList = monitorDubboBaseService.find("monitorUserManager",query);
			if(queryList != null && queryList.size() > 0 && !(queryList.get(0).getUserId()==monitorUser.getUserId())){
				//账号已存在
				return writeAjaxResponse("0-1");
			}

			monitorUser.setUpdateTime(new Date());
			monitorDubboBaseService.update("monitorUserManager",monitorUser);
			//放在数据库更新之后 更新redis
			monitorDubboBaseService.executeDynamicMethod("monitorUserManager","flushRedisUserRule",monitorUser);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}

		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				try{
					MonitorUser monitorUser = (MonitorUser) monitorDubboBaseService.getById("monitorUserManager",Integer.valueOf(id));
					monitorDubboBaseService.removeById("monitorUserManager",Integer.valueOf(id));
					//删除用户与功能监控中间表的数据信息
					MonitorUserRule monitorUserRule = new MonitorUserRule();
					monitorUserRule.setUserId(monitorUser.getUserId());
					monitorDubboBaseService.executeDynamicMethod("monitorUserRuleManager","deleteMonitorUserRule",monitorUserRule);
					//删除用户与服务监控中间表的数据信息
					MonitorUserService monitorUserService = new MonitorUserService();
					monitorUserService.setUserId(monitorUser.getUserId());
					monitorDubboBaseService.executeDynamicMethod("monitorUserServiceManager","deleteMonitorUserService",monitorUserService);
					//更新redis
					monitorDubboBaseService.executeDynamicMethod("monitorUserManager","flushRedisUserRule",monitorUser);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}

	/**进入更新页面*/
	@RequestMapping(value="/editPassword")
	public ModelAndView editPassword() {
		ModelAndView mav = new ModelAndView("/user/editPassword");
		return mav;
	}

	/**保存更新对象*/
	@RequestMapping(value="/updatePassword")
	@ResponseBody
	public String updatePassword(String oldPassword,String newPassword) {

		try{
			if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)){
				return writeAjaxResponse("3");
			}
			String userName = (String) getRequest().getSession().getAttribute("SESSION_USERNAME");

			MonitorUserQuery query = new MonitorUserQuery();
			query.setUserName(userName);
			query.setPassword(oldPassword);
			List<MonitorUser> queryList = monitorDubboBaseService.find("monitorUserManager",query);

			if(queryList ==null || queryList.isEmpty()){
				//当前登录密码输入错误
				return writeAjaxResponse("0");
			}
			MonitorUser monitorUser = new MonitorUser();
			monitorUser.setUpdateTime(new Date());
			monitorUser.setUserName(userName);
			monitorUser.setPassword(newPassword);
			monitorDubboBaseService.executeDynamicMethod("monitorUserManager","updatePassword",monitorUser);
		}catch (Exception e){
			e.printStackTrace();
		}

		return writeAjaxResponse("1");
	}

	/**保存更新对象*/
	@RequestMapping(value="/resetPassword")
	@ResponseBody
	public String resetPassword(String userName,String password){

		try{
			MonitorUser monitorUser = new MonitorUser();
			monitorUser.setUpdateTime(new Date());
			monitorUser.setUserName(userName);
			monitorUser.setPassword(password);
			monitorDubboBaseService.executeDynamicMethod("monitorUserManager","updatePassword",monitorUser);
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
}
