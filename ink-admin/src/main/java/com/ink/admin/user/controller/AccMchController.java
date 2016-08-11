
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
import com.ink.user.core.po.AccMch;
import com.ink.user.core.query.AccMchQuery;
import com.ink.user.core.service.IAccMchManager;
import com.ink.user.core.service.redis.AccMchCacheService;
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
 
@RequestMapping("user/AccMch")
@Controller
public class AccMchController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/user/AccMch/list";
	protected static final String CREATE_JSP = "/user/AccMch/create";
	protected static final String EDIT_JSP = "/user/AccMch/edit";
	protected static final String SHOW_JSP = "/user/AccMch/show";
	//redirect paths,startWith: !

	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private DubboBaseService userDubboBaseService;

	private YinkerLogger logger = YinkerLogger.getLogger(AccMchController.class);
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception {
		
		AccMchQuery query = newQuery(AccMchQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag(0);
		Page page = userDubboBaseService.findPage("accMchManager",query);

		
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
	public String save(AccMch accMch, HttpSession session) throws Exception {
		//accMchManager.save(accMch);
		accMch.setId(idCodeGenerator.getId());
		userDubboBaseService.executeDynamicMethod("accMchManager","saveMchAndAcc", accMch);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"新增商户,mchId = "+accMch.getMchId()+",操作人: "+ session.getAttribute("userName"));
		
		//新增到缓存
		userDubboBaseService.executeDynamicMethod("accMchCacheService","setAccMchCache",accMch);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccMch accMch) throws Exception {
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		if(StringUtils.isNotBlank(accMch.getId())){
			AccMch model = (AccMch) userDubboBaseService.getById("accMchManager",Long.parseLong(accMch.getId()));
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccMch());
		}
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccMch accMch) throws Exception {
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		if(StringUtils.isNotBlank(accMch.getId())){
			AccMch model = (AccMch) userDubboBaseService.getById("accMchManager",Long.valueOf(accMch.getId()));
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccMch());
		}
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccMch accMch, HttpSession session) throws Exception {
		accMch.setVersion(accMch.getVersion()+1);
		accMch.setLastUpdateTime(new Date());
		userDubboBaseService.update("accMchManager",accMch);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新商户,mchId = "+accMch.getMchId()+",操作人: "+ session.getAttribute("userName"));
		
		//更新到缓存
		userDubboBaseService.executeDynamicMethod("accMchCacheService","setAccMchCache",accMch);
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
					AccMch accMch = (AccMch) userDubboBaseService.getById("accMchManager",Long.parseLong(id));
					accMch.setDelFlag(true);
					userDubboBaseService.update("accMchManager",accMch);
					logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除商户,mchId = "+accMch.getMchId()+",操作人: "+ session.getAttribute("userName"));
					//从缓存删除
					userDubboBaseService.executeDynamicMethod("accMchCacheService","removeAccMchCache",accMch.getMchId());
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("/checkExist")
	@ResponseBody
	public String checkExist() throws Exception {
		AccMchQuery query = newQuery(AccMchQuery.class,DEFAULT_SORT_COLUMNS);
		List<AccMch> list = (List<AccMch>) userDubboBaseService.executeDynamicMethod("accMchManager","find",query);

		if(list.size()>0){
			return writeAjaxResponse("1");
		}else{
			return writeAjaxResponse("0");
		}
	}
}
