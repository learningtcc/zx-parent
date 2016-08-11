
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
import com.ink.user.core.po.AccCust;
import com.ink.user.core.query.AccCustQuery;
import com.ink.user.core.service.IAccCustManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("user/AccCust")
@Controller
public class AccCustController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/user/AccCust/list";
	protected static final String CREATE_JSP = "/user/AccCust/create";
	protected static final String EDIT_JSP = "/user/AccCust/edit";
	protected static final String SHOW_JSP = "/user/AccCust/show";
	//redirect paths,startWith: !
	@Autowired
	private DubboBaseService userDubboBaseService;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	private YinkerLogger logger = YinkerLogger.getLogger(AccCustController.class);
	
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception {
		
		AccCustQuery query = newQuery(AccCustQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag(0);
		Page page = userDubboBaseService.findPage("accCustManager",query);

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
	public String save(AccCust accCust, HttpSession session) throws Exception {
		accCust.setId(idCodeGenerator.getId());
		Date date = new Date();
		accCust.setCreateTime(date);
		accCust.setLastUpdateTime(date);

		userDubboBaseService.save("accCustManager",accCust);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"新增用户,custId = "+accCust.getCustId()+",操作人: "+ session.getAttribute("userName"));
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccCust accCust) throws Exception {
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		if(StringUtils.isNotBlank(accCust.getId())){
			AccCust model = (AccCust) userDubboBaseService.getById("accCustManager",Long.valueOf(accCust.getId()));

			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccCust());
		}
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccCust accCust) throws Exception {
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		if(StringUtils.isNotBlank(accCust.getId())){
			AccCust model = (AccCust) userDubboBaseService.getById("accCustManager", Long.valueOf(accCust.getId()));

			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccCust());
		}
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccCust accCust, HttpSession session) throws Exception {
		accCust.setLastUpdateTime(new Date());
		userDubboBaseService.update("accCustManager", accCust);
		logger.info(AccWebConstants.USER_WEB_MOUDLE, AccWebConstants.USER_WEB, "更新用户,custId = " + accCust.getCustId() + ",操作人: " + session.getAttribute("userName"));
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
					AccCust accCust = (AccCust) userDubboBaseService.getById("accCustManager", Long.parseLong(id));
					accCust.setDelFlag(1);
					userDubboBaseService.update("accCustManager", accCust);
					logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除用户,custId = "+accCust.getCustId()+",操作人: "+ session.getAttribute("userName"));

				}
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("/checkExist")
	@ResponseBody
	public String checkExist() throws Exception {
		AccCustQuery query = newQuery(AccCustQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag(0);
		List<AccCust> list = userDubboBaseService.find("accCustManager", query);
		
		if(list.size()>0){
			return writeAjaxResponse("1");
		}else{
			return writeAjaxResponse("0");
		}
	}
}
