
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
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.query.TnsTxnQuery;
import com.ink.user.core.service.redis.TnsTxnCacheService;
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
 
@RequestMapping("user/TnsTxn")
@Controller
public class TnsTxnController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/user/TnsTxn/list";
	protected static final String CREATE_JSP = "/user/TnsTxn/create";
	protected static final String EDIT_JSP = "/user/TnsTxn/edit";
	protected static final String SHOW_JSP = "/user/TnsTxn/show";
	//redirect paths,startWith: !

	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private DubboBaseService userDubboBaseService;
	private YinkerLogger logger = YinkerLogger.getLogger(TnsTxnController.class);
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception {
		
		TnsTxnQuery query = newQuery(TnsTxnQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = userDubboBaseService.findPage("tnsTxnService",query);
		
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
	public String save(TnsTxn tnsTxn, HttpSession session) throws Exception {
		tnsTxn.setId(idCodeGenerator.getId());
		Date date = new Date();
		tnsTxn.setCreateTime(date);
		tnsTxn.setLastUpdateTime(date);
		tnsTxn.setVersion(1);
		userDubboBaseService.save("tnsTxnService", tnsTxn);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"增加交易代码,txnCode = "+tnsTxn.getTxnCode()+",操作人: "+ session.getAttribute("userName"));
		//新增到缓存
		userDubboBaseService.executeDynamicMethod("tnsTxnCacheService", "setTnsTxnCache", tnsTxn);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(TnsTxn tnsTxn) throws Exception {
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		if(StringUtils.isNotBlank(tnsTxn.getId())){
			TnsTxn model = (TnsTxn) userDubboBaseService.getById("tnsTxnService",Long.parseLong(tnsTxn.getId()));
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new TnsTxn());
		}

		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(TnsTxn tnsTxn) throws Exception {
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		if(StringUtils.isNotBlank(tnsTxn.getId())){
			TnsTxn model = (TnsTxn) userDubboBaseService.getById("tnsTxnService", Long.parseLong(tnsTxn.getId()));
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new TnsTxn());
		}
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(TnsTxn tnsTxn, HttpSession session) throws Exception {
		tnsTxn.setLastUpdateTime(new Date());
		tnsTxn.setVersion(tnsTxn.getVersion()+1);
		userDubboBaseService.update("tnsTxnService",tnsTxn);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新交易代码,txnCode = "+tnsTxn.getTxnCode()+",操作人: "+ session.getAttribute("userName"));
		
		//更新到缓存
		userDubboBaseService.executeDynamicMethod("tnsTxnCacheService", "setTnsTxnCache", tnsTxn);
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
					TnsTxn tt = (TnsTxn) userDubboBaseService.getById("tnsTxnService",Long.valueOf(id));
					tt.setStatus(9);
					userDubboBaseService.update("tnsTxnService",tt);
					logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除交易代码,txnCode = "+tt.getTxnCode()+",操作人: "+ session.getAttribute("userName"));

					//从缓存中删除
					userDubboBaseService.executeDynamicMethod("tnsTxnCacheService", "removeTnsTxnCache", tt.getTxnCode());
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("/checkExist")
	@ResponseBody
	public String checkExist() throws Exception {
		TnsTxnQuery query = newQuery(TnsTxnQuery.class,DEFAULT_SORT_COLUMNS);
		List<TnsTxn> list = (List<TnsTxn>) userDubboBaseService.executeDynamicMethod("tnsTxnService","find",query);
		
		if(list.size()>0){
			return writeAjaxResponse("1");
		}else{
			return writeAjaxResponse("0");
		}
	}
}
