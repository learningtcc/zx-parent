package com.yinker.acc.controller.acc;
//
///**    
// * <p> Copyright (c) 2015-2025 银客网.</p>
// * <p>All Rights Reserved. 保留所有权利. </p>
// */
//package com.yinker.msgcenter.controller;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.yinker.base.BaseController;
//import com.yinker.base.page.Page;
//import com.yinker.user.core.po.AccPac;
//import com.yinker.user.core.query.AccPacQuery;
//import com.yinker.user.core.service.IAccPacManager;
//
///**
// * @author
// * @version 1.0
// * @since 1.0
// */
// 
//@RequestMapping("AccPac")
//@Controller
//public class AccPacController extends BaseController {
//	//默认多列排序,example: username desc,createTime asc
//	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
//	
//	//forward paths
//	protected static final String LIST_JSP= "/AccPac/list";
//	protected static final String CREATE_JSP = "/AccPac/create";
//	protected static final String EDIT_JSP = "/AccPac/edit";
//	protected static final String SHOW_JSP = "/AccPac/show";
//	//redirect paths,startWith: !
//	
//	@Autowired
//	private IAccPacManager accPacManager;
//	
//	@RequestMapping(value="/list")
//	public ModelAndView list() {
//		
//		AccPacQuery query = newQuery(AccPacQuery.class,DEFAULT_SORT_COLUMNS);
//		Page page = accPacManager.findPage(query);
//		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName(LIST_JSP);
//		modelAndView.addObject("page", page);
//		
//		return modelAndView;
//	}
//	
//	/** 进入新增页面*/
//	@RequestMapping(value="/create")
//	public ModelAndView create() {
//		return new ModelAndView(CREATE_JSP);
//	}
//	
//	/** 保存新增对象 */
//	@RequestMapping(value="/save")
//	@ResponseBody
//	public String save(AccPac accPac, HttpSession session) {
//		Date date = new Date();
//		accPac.setCreateTime(date);
//		accPac.setLastUpdateTime(date);
//		accPac.setVersion(1);
//		accPac.setOwner(String.valueOf(session.getAttribute("userName")));
//		accPac.setOwnerGroup("");
//		accPacManager.save(accPac);
//		return writeAjaxResponse("1");
//	}
//	
//	/** 查看对象*/
//	@RequestMapping(value="/show")
//	public ModelAndView show(AccPac accPac) {
//		AccPac model = accPacManager.getById(accPac.getId());
//		ModelAndView mav = new ModelAndView(SHOW_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**进入更新页面*/
//	@RequestMapping(value="/edit")
//	public ModelAndView edit(AccPac accPac) {
//		AccPac model = accPacManager.getById(accPac.getId());
//		ModelAndView mav = new ModelAndView(EDIT_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**保存更新对象*/
//	@RequestMapping(value="/update")
//	@ResponseBody
//	public String update(AccPac accPac) {
//		accPac.setLastUpdateTime(new Date());
//		accPac.setVersion(accPac.getVersion()+1);
//		accPacManager.update(accPac);
//		return writeAjaxResponse("1");
//	}
//	
//	
//	/**删除对象*/
//	@RequestMapping(value="/delete")
//	@ResponseBody
//	public String delete(String items) {
//		if(items!=null){
//			String[] idArray = items.split(",");
//			for(String id: idArray){
//				AccPac ap = accPacManager.getById(Long.valueOf(id));
//				ap.setStatus(9);
//				accPacManager.update(ap);
//			}
//		}
//		return writeAjaxResponse("1");
//	}
//}
