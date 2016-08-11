
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.acc.controller.acc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yinker.base.BaseController;
import com.yinker.base.log.util.YinkerLogger;
import com.yinker.base.page.Page;
import com.yinker.base.utils.IdCodeGenerator;
import com.yinker.user.core.po.TnsTxn;
import com.yinker.user.core.query.TnsTxnQuery;
import com.yinker.user.core.service.redis.TnsTxnCacheService;
import com.yinker.user.core.service.tns.ITnsTxnService;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("TnsTxn")
@Controller
public class TnsTxnController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/TnsTxn/list";
	protected static final String CREATE_JSP = "/TnsTxn/create";
	protected static final String EDIT_JSP = "/TnsTxn/edit";
	protected static final String SHOW_JSP = "/TnsTxn/show";
	//redirect paths,startWith: !
	
	@Autowired
	private ITnsTxnService tnsTxnService;
	@Autowired
	private TnsTxnCacheService tnsTxnCacheService;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	private YinkerLogger logger = YinkerLogger.getLogger(TnsTxnController.class);
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		TnsTxnQuery query = newQuery(TnsTxnQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = tnsTxnService.findPage(query);
		
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
	public String save(TnsTxn tnsTxn, HttpSession session) {
		tnsTxn.setId(idCodeGenerator.getId());
		Date date = new Date();
		tnsTxn.setCreateTime(date);
		tnsTxn.setLastUpdateTime(date);
		tnsTxn.setVersion(1);
		tnsTxnService.save(tnsTxn);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"增加交易代码,txnCode = "+tnsTxn.getTxnCode()+",操作人: "+ session.getAttribute("userName"));
		//新增到缓存
		tnsTxnCacheService.setTnsTxnCache(tnsTxn);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(TnsTxn tnsTxn) {
		TnsTxn model = tnsTxnService.getById(Long.parseLong(tnsTxn.getId()));
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(TnsTxn tnsTxn) {
		TnsTxn model = tnsTxnService.getById(Long.parseLong(tnsTxn.getId()));
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(TnsTxn tnsTxn, HttpSession session) {
		tnsTxn.setLastUpdateTime(new Date());
		tnsTxn.setVersion(tnsTxn.getVersion()+1);
		tnsTxnService.update(tnsTxn);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新交易代码,txnCode = "+tnsTxn.getTxnCode()+",操作人: "+ session.getAttribute("userName"));
		
		//更新到缓存
		tnsTxnCacheService.setTnsTxnCache(tnsTxn);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items, HttpSession session) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				TnsTxn tt = tnsTxnService.getById(Long.valueOf(id));
				tt.setStatus(9);
				tnsTxnService.update(tt);
				logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除交易代码,txnCode = "+tt.getTxnCode()+",操作人: "+ session.getAttribute("userName"));
				
				//从缓存中删除
				tnsTxnCacheService.removeTnsTxnCache(tt.getTxnCode());
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("/checkExist")
	@ResponseBody
	public String checkExist(){
		TnsTxnQuery query = newQuery(TnsTxnQuery.class,DEFAULT_SORT_COLUMNS);
		List<TnsTxn> list = tnsTxnService.find(query);
		
		if(list.size()>0){
			return writeAjaxResponse("1");
		}else{
			return writeAjaxResponse("0");
		}
	}
}
