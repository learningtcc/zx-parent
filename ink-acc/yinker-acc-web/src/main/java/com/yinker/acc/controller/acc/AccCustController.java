
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.acc.controller.acc;

import java.util.Date;
import java.util.List;

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
import com.yinker.user.core.po.AccCust;
import com.yinker.user.core.query.AccCustQuery;
import com.yinker.user.core.service.IAccCustManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AccCust")
@Controller
public class AccCustController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/AccCust/list";
	protected static final String CREATE_JSP = "/AccCust/create";
	protected static final String EDIT_JSP = "/AccCust/edit";
	protected static final String SHOW_JSP = "/AccCust/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAccCustManager accCustManager;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	private YinkerLogger logger = YinkerLogger.getLogger(AccCustController.class);
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AccCustQuery query = newQuery(AccCustQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag(0);
		Page page = accCustManager.findPage(query);
		
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
	public String save(AccCust accCust, HttpSession session) {
		accCust.setId(idCodeGenerator.getId());
		Date date = new Date();
		accCust.setCreateTime(date);
		accCust.setLastUpdateTime(date);
		accCustManager.save(accCust);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"新增用户,custId = "+accCust.getCustId()+",操作人: "+ session.getAttribute("userName"));
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccCust accCust) {
		AccCust model = accCustManager.getById(Long.valueOf(accCust.getId()));
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccCust accCust) {
		AccCust model = accCustManager.getById(Long.valueOf(accCust.getId()));
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccCust accCust, HttpSession session) {
		accCust.setLastUpdateTime(new Date());
		accCustManager.update(accCust);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新用户,custId = "+accCust.getCustId()+",操作人: "+ session.getAttribute("userName"));
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items, HttpSession session) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				AccCust accCust = accCustManager.getById(Long.parseLong(id));
				accCust.setDelFlag(1);
				accCustManager.update(accCust);
				logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除用户,custId = "+accCust.getCustId()+",操作人: "+ session.getAttribute("userName"));
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("/checkExist")
	@ResponseBody
	public String checkExist(){
		AccCustQuery query = newQuery(AccCustQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag(0);
		List<AccCust> list = accCustManager.find(query);
		
		if(list.size()>0){
			return writeAjaxResponse("1");
		}else{
			return writeAjaxResponse("0");
		}
	}
}
