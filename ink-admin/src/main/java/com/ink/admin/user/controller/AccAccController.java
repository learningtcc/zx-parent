
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
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.query.AccAccQuery;
import com.ink.user.core.service.IAccAccManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("user/AccAcc")
@Controller
public class AccAccController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/user/AccAcc/list";
	protected static final String CREATE_JSP = "/user/AccAcc/create";
	protected static final String EDIT_JSP = "/user/AccAcc/edit";
	protected static final String SHOW_JSP = "/user/AccAcc/show";
	//redirect paths,startWith: !
	@Autowired
	private DubboBaseService userDubboBaseService;
	
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	private YinkerLogger logger = YinkerLogger.getLogger(AccAccController.class);
	
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception {
		
		AccAccQuery query = newQuery(AccAccQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = userDubboBaseService.findPage("accAccManager",query);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/custList")
	public ModelAndView custList() throws Exception {
		
		AccAccQuery query = newQuery(AccAccQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = (Page) userDubboBaseService.executeDynamicMethod("accAccManager","findCustPage",query);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/AccAcc/custList");
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/mchList")
	public ModelAndView mchList(HttpSession session) throws Exception {
		
		AccAccQuery query = newQuery(AccAccQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = (Page) userDubboBaseService.executeDynamicMethod("accAccManager","findMchPage",query);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/AccAcc/mchList");
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
	public ModelAndView show(AccAcc accAcc) throws Exception {
		AccAcc model = (AccAcc) userDubboBaseService.getById("accAccManager",accAcc.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping("/initAccMacPage")
	public ModelAndView initAccMacPage(){
		ModelAndView mav = new ModelAndView("/user/AccAcc/initAccMac");
		return mav;
	}

	@RequestMapping("/initAccMac")
	@ResponseBody
	public int initAccMac() throws Exception {
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"初始化导入数据!");
		int i = (int) userDubboBaseService.executeDynamicMethod("accAccManager","initAccMac");
		return i;
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
