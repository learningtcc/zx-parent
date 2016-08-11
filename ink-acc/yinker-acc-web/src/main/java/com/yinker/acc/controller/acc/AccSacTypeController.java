
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.acc.controller.acc;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yinker.base.BaseController;
import com.yinker.base.log.util.YinkerLogger;
import com.yinker.base.page.Page;
import com.yinker.base.utils.IdCodeGenerator;
import com.yinker.user.core.po.AccSacType;
import com.yinker.user.core.query.AccSacTypeQuery;
import com.yinker.user.core.service.IAccSacTypeManager;
import com.yinker.user.core.service.redis.AccSacTypeCacheService;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AccSacType")
@Controller
public class AccSacTypeController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/AccSacType/list";
	protected static final String CREATE_JSP = "/AccSacType/create";
	protected static final String EDIT_JSP = "/AccSacType/edit";
	protected static final String SHOW_JSP = "/AccSacType/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAccSacTypeManager accSacTypeManager;
	@Autowired
	private AccSacTypeCacheService accSacTypeCacheService;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	
	private YinkerLogger logger = YinkerLogger.getLogger(AccSacTypeController.class);
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AccSacTypeQuery query = newQuery(AccSacTypeQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = accSacTypeManager.findPage(query);
		
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
	public String save(AccSacType accSacType , HttpSession session) {
		accSacType.setId(idCodeGenerator.getId());
		Date date = new Date();
		accSacType.setCreateTime(date);
		accSacType.setLastUpdateTime(date);
		accSacType.setVersion(1);
		accSacTypeManager.save(accSacType);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"新增子账户类型,sacType = "+accSacType.getSacType()+",操作人: "+ session.getAttribute("userName"));
		
		//新增到缓存
		accSacTypeCacheService.setAccSacTypeCache(accSacType);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccSacType accSacType) {
		AccSacType model = accSacTypeManager.getById(Long.parseLong(accSacType.getId()));
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccSacType accSacType) {
		AccSacType model = accSacTypeManager.getById(Long.parseLong(accSacType.getId()));
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccSacType accSacType, HttpSession session) {
		accSacType.setLastUpdateTime(new Date());
		accSacType.setVersion(accSacType.getVersion()+1);
		accSacTypeManager.update(accSacType);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新子账户类型,sacType = "+accSacType.getSacType()+",操作人: "+ session.getAttribute("userName"));
		
		//更新到缓存
		accSacTypeCacheService.setAccSacTypeCache(accSacType);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items, HttpSession session) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				AccSacType type = accSacTypeManager.getById(Long.parseLong(id));
				type.setStatus(9);
				accSacTypeManager.update(type);
				logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除子账户类型,sacType = "+type.getSacType()+",操作人: "+ session.getAttribute("userName"));
				
				//从缓存删除
				accSacTypeCacheService.removeAccSacTypeCache(type.getSacType());
			}
		}
		
		return writeAjaxResponse("1");
	}
}
