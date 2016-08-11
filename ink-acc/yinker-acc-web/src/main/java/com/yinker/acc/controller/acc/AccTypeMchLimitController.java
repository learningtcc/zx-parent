
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
import com.yinker.user.core.po.AccTypeMchLimit;
import com.yinker.user.core.query.AccTypeMchLimitQuery;
import com.yinker.user.core.service.IAccTypeMchLimitManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AccTypeMchLimit")
@Controller
public class AccTypeMchLimitController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "last_update_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/AccTypeMchLimit/list";
	protected static final String CREATE_JSP = "/AccTypeMchLimit/create";
	protected static final String EDIT_JSP = "/AccTypeMchLimit/edit";
	protected static final String SHOW_JSP = "/AccTypeMchLimit/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAccTypeMchLimitManager accTypeMchLimitManager;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	private YinkerLogger logger = YinkerLogger.getLogger(AccTypeMchLimitController.class);
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AccTypeMchLimitQuery query = newQuery(AccTypeMchLimitQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag("0");
		Page page = accTypeMchLimitManager.findPage(query);
		
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
			accTypeMchLimitManager.save(accTypeMchLimit);
			logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"增加商户子账户限额,sacType = "+accTypeMchLimit.getSacType()+",操作人: "+ session.getAttribute("userName"));
		} catch (Exception e) {
			return writeAjaxResponse("0");
		}
		
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccTypeMchLimit accTypeMchLimit) {
		AccTypeMchLimit model = accTypeMchLimitManager.getById(accTypeMchLimit.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccTypeMchLimit accTypeMchLimit) {
		AccTypeMchLimit model = accTypeMchLimitManager.getById(accTypeMchLimit.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccTypeMchLimit accTypeMchLimit, HttpSession session) {
		accTypeMchLimit.setLastUpdateTime(new Date());
		accTypeMchLimit.setVersion(accTypeMchLimit.getVersion()+1);
		try {
			accTypeMchLimitManager.update(accTypeMchLimit);
			logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"更新商户子账户限额,accTypeMchLimit = "+accTypeMchLimit.getSacType()+",操作人: "+ session.getAttribute("userName"));
		}catch (Exception e){
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items, HttpSession session) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				//accTypeMchLimitManager.removeById(Long.valueOf(id));
				AccTypeMchLimit model = accTypeMchLimitManager.getById(Long.valueOf(id));
				model.setLastUpdateTime(new Date());
				model.setVersion(model.getVersion()+1);
				model.setDelFlag(1);
				accTypeMchLimitManager.update(model);
				logger.info(AccWebConstants.USER_WEB_MOUDLE,AccWebConstants.USER_WEB,"删除商户子账户限额,accTypeMchLimit = "+model.getSacType()+",操作人: "+ session.getAttribute("userName"));
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("/checkExist")
	@ResponseBody
	public String checkExist(){
		AccTypeMchLimitQuery query = newQuery(AccTypeMchLimitQuery.class,DEFAULT_SORT_COLUMNS);
		query.setDelFlag("0");
		List<AccTypeMchLimit> list = accTypeMchLimitManager.find(query);
		
		if(list.size()>0){
			return writeAjaxResponse("1");
		}else{
			return writeAjaxResponse("0");
		}
	}
}
