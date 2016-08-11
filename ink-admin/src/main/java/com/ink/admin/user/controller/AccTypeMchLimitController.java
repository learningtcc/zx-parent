
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
import com.ink.user.core.po.AccTypeMchLimit;
import com.ink.user.core.query.AccTypeMchLimitQuery;
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
 
@RequestMapping("user/AccTypeMchLimit")
@Controller
public class AccTypeMchLimitController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/user/AccTypeMchLimit/list";
	protected static final String CREATE_JSP = "/user/AccTypeMchLimit/create";
	protected static final String EDIT_JSP = "/user/AccTypeMchLimit/edit";
	protected static final String SHOW_JSP = "/user/AccTypeMchLimit/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private DubboBaseService userDubboBaseService;
	private YinkerLogger logger = YinkerLogger.getLogger(AccTypeMchLimitController.class);
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception {
		
		AccTypeMchLimitQuery query = newQuery(AccTypeMchLimitQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag("0");
		Page page = userDubboBaseService.findPage("accTypeMchLimitManager",query);
		
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
	public String save(AccTypeMchLimit accTypeMchLimit, HttpSession session) {
		Date date = new Date();
		accTypeMchLimit.setId(Long.parseLong(idCodeGenerator.getId()));
		accTypeMchLimit.setCreateTime(date);
		accTypeMchLimit.setLastUpdateTime(date);
		accTypeMchLimit.setVersion(1l);
		accTypeMchLimit.setStatus(1);
		accTypeMchLimit.setDelFlag(0);
		try {
			userDubboBaseService.save("accTypeMchLimitManager",accTypeMchLimit);
			logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"增加商户子账户限额,sacType = "+accTypeMchLimit.getSacType()+",操作人: "+ session.getAttribute("userName"));
		} catch (Exception e) {
			return writeAjaxResponse("0");
		}
		
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccTypeMchLimit accTypeMchLimit) throws Exception {
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		if(accTypeMchLimit.getId() != null && accTypeMchLimit.getId() != 0){
			AccTypeMchLimit model = (AccTypeMchLimit) userDubboBaseService.getById("accTypeMchLimitManager",accTypeMchLimit.getId());
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccTypeMchLimit());
		}
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccTypeMchLimit accTypeMchLimit) throws Exception {
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		if(accTypeMchLimit.getId() != null && accTypeMchLimit.getId() != 0){
			AccTypeMchLimit model = (AccTypeMchLimit) userDubboBaseService.getById("accTypeMchLimitManager",accTypeMchLimit.getId());
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccTypeMchLimit());
		}
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccTypeMchLimit accTypeMchLimit, HttpSession session) {
		accTypeMchLimit.setLastUpdateTime(new Date());
		accTypeMchLimit.setVersion(accTypeMchLimit.getVersion()+1);
		try {
			userDubboBaseService.update("accTypeMchLimitManager",accTypeMchLimit);
			logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新商户子账户限额,accTypeMchLimit = "+accTypeMchLimit.getSacType()+",操作人: "+ session.getAttribute("userName"));
		}catch (Exception e){
			return writeAjaxResponse("0");
		}
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
					//userDubboBaseService.removeById(Long.valueOf(id));
					AccTypeMchLimit model = (AccTypeMchLimit) userDubboBaseService.getById("accTypeMchLimitManager",Long.valueOf(id));
					model.setLastUpdateTime(new Date());
					model.setVersion(model.getVersion()+1);
					model.setDelFlag(1);
					userDubboBaseService.update("accTypeMchLimitManager",model);
					logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除商户子账户限额,accTypeMchLimit = "+model.getSacType()+",操作人: "+ session.getAttribute("userName"));
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("/checkExist")
	@ResponseBody
	public String checkExist() throws Exception {
		AccTypeMchLimitQuery query = newQuery(AccTypeMchLimitQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag("0");
		List<AccTypeMchLimit> list = (List<AccTypeMchLimit>) userDubboBaseService.executeDynamicMethod("accTypeMchLimitManager","find",query);
		
		if(list.size()>0){
			return writeAjaxResponse("1");
		}else{
			return writeAjaxResponse("0");
		}
	}
}
