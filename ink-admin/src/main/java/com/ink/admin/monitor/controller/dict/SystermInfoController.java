package com.ink.admin.monitor.controller.dict;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.SystermInfo;
import com.ink.monitor.core.query.SystermInfoQuery;
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
 
@RequestMapping("monitor/dict/info")
@Controller
public class SystermInfoController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/dict/info/list";
	protected static final String CREATE_JSP = "/monitor/dict/info/create";
	protected static final String EDIT_JSP = "/monitor/dict/info/edit";
	protected static final String SHOW_JSP = "/monitor/dict/info/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService monitorDubboBaseService;

	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		SystermInfoQuery query = newQuery(SystermInfoQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("systermInfoManager",query);
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
	@RequestMapping(value="/listAjaxForModuleCode",method = RequestMethod.GET)
	public List<SystermInfo> listAjaxForModuleCode(String moduleCode) {

		SystermInfoQuery systermInfoQuery = new SystermInfoQuery();
		systermInfoQuery.setModuleCode(moduleCode);
		systermInfoQuery.setStatus("0");
		List<SystermInfo> systermInfoList = null;
		try {
			systermInfoList = monitorDubboBaseService.find("systermInfoManager",systermInfoQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systermInfoList;
	}
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(SystermInfo systermInfo,HttpServletRequest request) {

		try{
			SystermInfoQuery infoQuery = new SystermInfoQuery();
			infoQuery.setCode(systermInfo.getCode());
			List list = monitorDubboBaseService.find("systermInfoManager",infoQuery);
			if (!(null == list || list.isEmpty())){
				return writeAjaxResponse("0");
			}

			String userId = (String) request.getSession().getAttribute("SESSION_USERID");
			systermInfo.setOpUser(userId);
			systermInfo.setCreateTime(new Date());
			systermInfo.setUpdateTime(new Date());
			monitorDubboBaseService.save("systermInfoManager",systermInfo);
		}catch (Exception e){
			return writeAjaxResponse("2");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(SystermInfo systermInfo) {
		SystermInfo model = null;
		try {
			model = (SystermInfo) monitorDubboBaseService.getById("systermInfoManager",systermInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(SystermInfo systermInfo) {
		SystermInfo model = null;
		try {
			model = (SystermInfo) monitorDubboBaseService.getById("systermInfoManager",systermInfo.getId());
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
	public String update(SystermInfo systermInfo,HttpServletRequest request) {
		try{
			String userId = (String) request.getSession().getAttribute("SESSION_USERID");
			systermInfo.setOpUser(userId);
			systermInfo.setUpdateTime(new Date());
			monitorDubboBaseService.update("systermInfoManager",systermInfo);
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
					monitorDubboBaseService.executeDynamicMethod("systermInfoManager","deleteInfo",Integer.valueOf(id),status);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		
		return writeAjaxResponse("1");
	}
}
