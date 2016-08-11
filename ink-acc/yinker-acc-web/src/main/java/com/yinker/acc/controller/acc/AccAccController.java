
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.acc.controller.acc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinker.base.BaseController;
import com.yinker.base.log.util.YinkerLogger;
import com.yinker.base.page.Page;
import com.yinker.base.utils.IdCodeGenerator;
import com.yinker.user.core.po.AccAcc;
import com.yinker.user.core.query.AccAccQuery;
import com.yinker.user.core.service.IAccAccManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AccAcc")
@Controller
public class AccAccController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/AccAcc/list";
	protected static final String CREATE_JSP = "/AccAcc/create";
	protected static final String EDIT_JSP = "/AccAcc/edit";
	protected static final String SHOW_JSP = "/AccAcc/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAccAccManager accAccManager;
	
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	private YinkerLogger logger = YinkerLogger.getLogger(AccAccController.class);
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AccAccQuery query = newQuery(AccAccQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = accAccManager.findPage(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/custList")
	public ModelAndView custList() {
		
		AccAccQuery query = newQuery(AccAccQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = accAccManager.findCustPage(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/AccAcc/custList");
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/mchList")
	public ModelAndView mchList(HttpSession session) {
		
		AccAccQuery query = newQuery(AccAccQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = accAccManager.findMchPage(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/AccAcc/mchList");
		modelAndView.addObject("page", page);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"查看账户");
		return modelAndView;
	}
	
//	/** 进入新增页面*/
//	@RequestMapping(value="/create")
//	public ModelAndView create() {
//		return new ModelAndView(CREATE_JSP);
//	}
//	
//	/** 保存新增对象 */
//	@RequestMapping(value="/save")
//	@ResponseBody
//	public String save(AccAcc accAcc) {
//		Date date = new Date();
//		accAcc.setId(Long.parseLong(idCodeGenerator.getId()));
//		accAcc.setCreateTime(date);
//		accAcc.setLastUpdateTime(date);
//		accAcc.setLstBal(BigDecimal.ZERO);
//		accAcc.setCurBal(BigDecimal.ZERO);
//		accAcc.setTmpBal(BigDecimal.ZERO);
//		accAcc.setFrozenAmt(BigDecimal.ZERO);
//		accAccManager.save(accAcc);
//		return writeAjaxResponse("1");
//	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccAcc accAcc) {
		AccAcc model = accAccManager.getById(accAcc.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
//	/**进入更新页面*/
//	@RequestMapping(value="/edit")
//	public ModelAndView edit(AccAcc accAcc) {
//		AccAcc model = accAccManager.getById(accAcc.getId());
//		ModelAndView mav = new ModelAndView(EDIT_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**保存更新对象*/
//	@RequestMapping(value="/update")
//	@ResponseBody
//	public String update(AccAcc accAcc) {
//		accAcc.setLastUpdateTime(new Date());
//		accAcc.setVersion(accAcc.getVersion()+1);
//		accAccManager.update(accAcc);
//		return writeAjaxResponse("1");
//	}
	
	
//	/**删除对象*/
//	@RequestMapping(value="/delete")
//	@ResponseBody
//	public String delete(String items) {
//		if(items!=null){
//			String[] idArray = items.split(",");
//			for(String id: idArray){
//				accAccManager.removeById(Long.parseLong(id));
//			}
//		}
//		
//		return writeAjaxResponse("1");
//	}
}
