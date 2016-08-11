package com.ink.admin.monitor.controller.dict;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.SystermModule;
import com.ink.monitor.core.query.SystermModuleQuery;
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
 
@RequestMapping("monitor/dict/module")
@Controller
public class SystermModuleController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/dict/module/list";
	protected static final String CREATE_JSP = "/monitor/dict/module/create";
	protected static final String EDIT_JSP = "/monitor/dict/module/edit";
	protected static final String SHOW_JSP = "/monitor/dict/module/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService monitorDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		SystermModuleQuery query = newQuery(SystermModuleQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("systermModuleManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}

	/**
	 * ajax 根据系统编码获取模块编码
	 * @return json 数据
	 */
	@ResponseBody
	@RequestMapping(value="/listAjaxForSystemCode",method = RequestMethod.GET)
	public List<SystermModule> listAjaxForSystemCode(String systemCode) {

		SystermModuleQuery systermModuleQuery = new SystermModuleQuery();
		systermModuleQuery.setSystermCode(systemCode);
		systermModuleQuery.setStatus("0");
		List<SystermModule> systermSourceList = null;
		try {
			systermSourceList = monitorDubboBaseService.find("systermModuleManager",systermModuleQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systermSourceList;
	}

	/**
	 * ajax 根据编码获取模块信息
	 * @return json 数据
	 */
	@ResponseBody
	@RequestMapping(value="/findByCode",method = RequestMethod.GET)
	public List<SystermModule> findByCode(String code) {

		SystermModuleQuery systermModuleQuery = new SystermModuleQuery();
		systermModuleQuery.setCode(code);
		List<SystermModule> systermSourceList = null;
		try {
			systermSourceList = monitorDubboBaseService.find("systermModuleManager",systermModuleQuery);
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
	public String save(SystermModule systermModule,HttpServletRequest request) {

		try{
			SystermModuleQuery moduleQuery = new SystermModuleQuery();
			moduleQuery.setCode(systermModule.getCode());
			List list = monitorDubboBaseService.find("systermModuleManager",moduleQuery);
			if (!(null == list || list.isEmpty())){
				return writeAjaxResponse("0");
			}

			String userId = (String) request.getSession().getAttribute("SESSION_USERID");
			systermModule.setOpUser(userId);
			systermModule.setCreateTime(new Date());
			systermModule.setUpdateTime(new Date());
			monitorDubboBaseService.save("systermModuleManager",systermModule);
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("2");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(SystermModule systermModule) {
		SystermModule model = null;
		try {
			model = (SystermModule) monitorDubboBaseService.getById("systermModuleManager",systermModule.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(SystermModule systermModule) {
		SystermModule model = null;
		try {
			model = (SystermModule) monitorDubboBaseService.getById("systermModuleManager",systermModule.getId());
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
	public String update(SystermModule systermModule,HttpServletRequest request) {

		try{
			String userId = (String) request.getSession().getAttribute("SESSION_USERID");
			systermModule.setOpUser(userId);
			systermModule.setUpdateTime(new Date());
			monitorDubboBaseService.executeDynamicMethod("systermModuleManager","updateInfo",systermModule);
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
					monitorDubboBaseService.executeDynamicMethod("systermModuleManager","deleteInfo",Integer.valueOf(id),status);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		
		return writeAjaxResponse("1");
	}
}
