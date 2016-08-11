
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.user.controller;

import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.core.po.AccSacType;
import com.ink.user.core.query.AccSacTypeQuery;
import com.ink.user.core.service.IAccSacTypeManager;
import com.ink.user.core.service.redis.AccSacTypeCacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("user/AccSacType")
@Controller
public class AccSacTypeController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/user/AccSacType/list";
	protected static final String CREATE_JSP = "/user/AccSacType/create";
	protected static final String EDIT_JSP = "/user/AccSacType/edit";
	protected static final String SHOW_JSP = "/user/AccSacType/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private DubboBaseService userDubboBaseService;
	
	private YinkerLogger logger = YinkerLogger.getLogger(AccSacTypeController.class);
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception {
		
		AccSacTypeQuery query = newQuery(AccSacTypeQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = userDubboBaseService.findPage("accSacTypeManager", query);
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
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AccSacType accSacType , HttpSession session) throws Exception {
		accSacType.setId(idCodeGenerator.getId());
		Date date = new Date();
		accSacType.setCreateTime(date);
		accSacType.setLastUpdateTime(date);
		accSacType.setVersion(1);
		userDubboBaseService.save("accSacTypeManager",accSacType);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"新增子账户类型,sacType = "+accSacType.getSacType()+",操作人: "+ session.getAttribute("userName"));
		
		//新增到缓存
		userDubboBaseService.executeDynamicMethod("accSacTypeCacheService","setAccSacTypeCache",accSacType);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccSacType accSacType) throws Exception {
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		if(StringUtils.isNotBlank(accSacType.getId())){
			AccSacType model = (AccSacType) userDubboBaseService.getById("accSacTypeManager",Long.parseLong(accSacType.getId()));
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccSacType());
		}

		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccSacType accSacType) throws Exception {
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		if(StringUtils.isNotBlank(accSacType.getId())){
			AccSacType model = (AccSacType) userDubboBaseService.getById("accSacTypeManager",Long.parseLong(accSacType.getId()));
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccSacType());
		}
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccSacType accSacType, HttpSession session) throws Exception {
		accSacType.setLastUpdateTime(new Date());
		accSacType.setVersion(accSacType.getVersion()+1);
		userDubboBaseService.update("accSacTypeManager",accSacType);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新子账户类型,sacType = "+accSacType.getSacType()+",操作人: "+ session.getAttribute("userName"));
		
		//更新到缓存
		userDubboBaseService.executeDynamicMethod("accSacTypeCacheService", "setAccSacTypeCache", accSacType);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items, HttpSession session) throws Exception {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				if(StringUtils.isNotBlank(id)){
					AccSacType type = (AccSacType) userDubboBaseService.getById("accSacTypeManager", Long.parseLong(id));
					type.setStatus(9);
					userDubboBaseService.update("accSacTypeManager",type);
					logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除子账户类型,sacType = "+type.getSacType()+",操作人: "+ session.getAttribute("userName"));

					//从缓存删除
					userDubboBaseService.executeDynamicMethod("accSacTypeCacheService","removeAccSacTypeCache",type.getSacType());
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
}
