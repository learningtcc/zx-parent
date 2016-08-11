
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
import com.yinker.user.core.po.AccMch;
import com.yinker.user.core.query.AccMchQuery;
import com.yinker.user.core.service.IAccMchManager;
import com.yinker.user.core.service.redis.AccMchCacheService;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AccMch")
@Controller
public class AccMchController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/AccMch/list";
	protected static final String CREATE_JSP = "/AccMch/create";
	protected static final String EDIT_JSP = "/AccMch/edit";
	protected static final String SHOW_JSP = "/AccMch/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAccMchManager accMchManager;
	@Autowired
	private AccMchCacheService accMchCacheService;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	private YinkerLogger logger = YinkerLogger.getLogger(AccMchController.class);
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AccMchQuery query = newQuery(AccMchQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag(0);
		Page page = accMchManager.findPage(query);
		
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
	public String save(AccMch accMch, HttpSession session) {
		//accMchManager.save(accMch);
		accMch.setId(idCodeGenerator.getId());
		try {
			accMchManager.saveMchAndAcc(accMch);
			logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"新增商户,mchId = "+accMch.getMchId()+",操作人: "+ session.getAttribute("userName"));
		} catch (Exception e) {
			//创建失败
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		
		//新增到缓存
		accMchCacheService.setAccMchCache(accMch);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccMch accMch) {
		AccMch model = accMchManager.getById(Long.parseLong(accMch.getId()));
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccMch accMch) {
		AccMch model = accMchManager.getById(Long.valueOf(accMch.getId()));
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccMch accMch, HttpSession session) {
		accMch.setVersion(accMch.getVersion()+1);
		accMch.setLastUpdateTime(new Date());
		accMchManager.update(accMch);
		logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新商户,mchId = "+accMch.getMchId()+",操作人: "+ session.getAttribute("userName"));
		
		//更新到缓存
		accMchCacheService.setAccMchCache(accMch);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items, HttpSession session) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				AccMch accMch = accMchManager.getById(Long.parseLong(id));
				accMch.setDelFlag(true);
				accMchManager.update(accMch);
				logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除商户,mchId = "+accMch.getMchId()+",操作人: "+ session.getAttribute("userName"));
				//从缓存删除
				accMchCacheService.removeAccMchCache(accMch.getMchId());
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("/checkExist")
	@ResponseBody
	public String checkExist(){
		AccMchQuery query = newQuery(AccMchQuery.class,DEFAULT_SORT_COLUMNS);
		List<AccMch> list = accMchManager.find(query);
		
		if(list.size()>0){
			return writeAjaxResponse("1");
		}else{
			return writeAjaxResponse("0");
		}
	}
}
