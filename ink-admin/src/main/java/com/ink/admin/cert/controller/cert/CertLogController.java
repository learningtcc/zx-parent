
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.cert.controller.cert;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.cert.core.po.CertLog;
import com.ink.cert.core.query.CertLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("cert/certLog")
@Controller
public class CertLogController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = " operate_Time desc";
	
	//forward paths
	protected static final String LIST_JSP= "/cert/certLog/list";
	protected static final String CREATE_JSP = "/cert/certLog/create";
	protected static final String EDIT_JSP = "/cert/certLog/edit";
	protected static final String SHOW_JSP = "/cert/certLog/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService certDubboBaseService;

	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		CertLogQuery query = newQuery(CertLogQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = certDubboBaseService.findPage("certLogManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public String save(CertLog certLog) {
		try {
			certDubboBaseService.save("certLogManager",certLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(CertLog certLog) {
		CertLog model = null;
		try {
			model = (CertLog) certDubboBaseService.getById("certLogManager",certLog.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(CertLog certLog) {
		CertLog model = null;
		try {
			model = (CertLog) certDubboBaseService.getById("certLogManager",certLog.getId());
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
	public String update(CertLog certLog) {
		try {
			certDubboBaseService.update("certLogManager",certLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				try {
					certDubboBaseService.removeById("certLogManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
}
