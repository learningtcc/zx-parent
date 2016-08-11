package com.ink.admin.monitor.controller.dict;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.SystermSource;
import com.ink.monitor.core.query.SystermSourceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("monitor/dict/source")
@Controller
public class SystermSourceController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/dict/source/list";
	protected static final String CREATE_JSP = "/monitor/dict/source/create";
	protected static final String EDIT_JSP = "/monitor/dict/source/edit";
	protected static final String SHOW_JSP = "/monitor/dict/source/show";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService monitorDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		SystermSourceQuery query = newQuery(SystermSourceQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("systermSourceManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}

	/**
	 * ajax获取所有系统编码
	 * @return json 数据
	 */
	@ResponseBody
	@RequestMapping(value="/listForAjax",method = RequestMethod.GET)
	public List<SystermSource> listForAjax() {

		SystermSourceQuery systermSourceQuery = new SystermSourceQuery();
		systermSourceQuery.setStatus("0");
		List<SystermSource> systermSourceList = null;
		try {
			systermSourceList = monitorDubboBaseService.find("systermSourceManager",systermSourceQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systermSourceList;
	}

	/**
	 * ajax 根据编码获取系统信息
	 * @return json 数据
	 */
	@ResponseBody
	@RequestMapping(value="/findByCode",method = RequestMethod.GET)
	public List<SystermSource> findByCode(String code) {
		SystermSourceQuery systermSourceQuery = new SystermSourceQuery();
		systermSourceQuery.setCode(code);
		List<SystermSource> systermSourceList = null;
		try {
			systermSourceList = monitorDubboBaseService.find("systermSourceManager",systermSourceQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systermSourceList;
	}
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(SystermSource systermSource,HttpServletRequest request) {

		try{
			SystermSourceQuery sourceQuery = new SystermSourceQuery();
			sourceQuery.setCode(systermSource.getCode());
			List list = monitorDubboBaseService.find("systermSourceManager",sourceQuery);
			if (!(null == list || list.isEmpty())){
				return writeAjaxResponse("0");
			}
			String userId = (String) request.getSession().getAttribute("SESSION_USERID");

			systermSource.setOpUser(userId);
			systermSource.setCreateTime(new Date());
			systermSource.setUpdateTime(new Date());
			monitorDubboBaseService.save("systermSourceManager",systermSource);
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("2");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(SystermSource systermSource) {
		SystermSource model = null;
		try {
			model = (SystermSource) monitorDubboBaseService.getById("systermSourceManager",systermSource.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(SystermSource systermSource) {
		SystermSource model = null;
		try {
			model = (SystermSource) monitorDubboBaseService.getById("systermSourceManager",systermSource.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(SystermSource systermSource,HttpServletRequest request) {

		try{
			String userId = (String) request.getSession().getAttribute("SESSION_USERID");
			systermSource.setOpUser(userId);
			systermSource.setUpdateTime(new Date());
			monitorDubboBaseService.executeDynamicMethod("systermSourceManager","updateInfo",systermSource);
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items,String status) {

		try{
			if(items!=null){
				String[] idArray = items.split(",");
				for(String id: idArray){
					monitorDubboBaseService.executeDynamicMethod("systermSourceManager","deleteInfo",Integer.valueOf(id),status);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
}
