package com.ink.admin.msgcenter.controller;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.core.po.EmailLog;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.query.EmailLogQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("msgcenter/EmailLog")
@Controller
public class EmailLogController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = " submit_time desc, send_time desc";
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/log/emailLog/list";
	protected static final String CREATE_JSP = "/msgcenter/log/emailLog/create";
	protected static final String EDIT_JSP = "/msgcenter/log/emailLog/edit";
	protected static final String SHOW_JSP = "/msgcenter/log/emailLog/show";
	protected static final String SHOW_EMAIL_JSP= "/msgcenter/merchant/merchantInfo/showEmailLog";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	
	private YinkerLogger logger = YinkerLogger.getLogger(getClass());
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		EmailLogQuery query = newQuery(EmailLogQuery.class,DEFAULT_SORT_COLUMNS);
		//
		try {
			if(StringUtils.isNotBlank(query.getMerctCode())){
				Page page = msgcenterDubboBaseService.findPage("emailLogManager",query);
				modelAndView.addObject("page", page);
			}else if(StringUtils.isNotBlank(query.getMerctId())){
				MerchantInfo merchantInfo = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",Long.valueOf(query.getMerctId()));
				if(merchantInfo!=null){
					query.setMerctCode(merchantInfo.getSn());
					Page page = msgcenterDubboBaseService.findPage("emailLogManager",query);
					modelAndView.addObject("page", page);
				}
			}else{
				logger.info("邮件发送日志查询商户ID或Code为空!");
			}
		} catch (Exception e) {
			logger.error("邮件发送日志查询商户错误!",e);
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/showEmailLog")
	public ModelAndView showEmailLog() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(SHOW_EMAIL_JSP);
		EmailLogQuery query = newQuery(EmailLogQuery.class,DEFAULT_SORT_COLUMNS);
		//
		try {
			if(StringUtils.isNotBlank(query.getMerctCode())){
				Page page = msgcenterDubboBaseService.findPage("emailLogManager",query);
				modelAndView.addObject("page", page);
			}else if(StringUtils.isNotBlank(query.getMerctId())){
				EmailLog log = (EmailLog) msgcenterDubboBaseService.getById("emailLogManager",Long.valueOf(query.getMerctId()));
				if(log!=null){
					query.setMerctCode(log.getMerctCode());
					Page page = msgcenterDubboBaseService.findPage("emailLogManager",query);
					modelAndView.addObject("page", page);
				}
			}else{
				logger.info("邮件发送日志查询商户ID或Code为空!");
			}
		} catch (Exception e) {
			logger.info("邮件发送日志查询商户未创建日志!");
		}
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
	public String save(EmailLog emailLog) {
		try {
			msgcenterDubboBaseService.save("emailLogManager",emailLog);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(EmailLog emailLog) {
		EmailLog model = null;
		try {
			model = (EmailLog) msgcenterDubboBaseService.executeDynamicMethod("emailLogManager","getEmailLogById",emailLog.getId(), emailLog.getMerctCode());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(EmailLog emailLog) {
		EmailLog model = null;
		try {
			model = (EmailLog) msgcenterDubboBaseService.getById("emailLogManager",emailLog.getId());
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
	public String update(EmailLog emailLog) {
		try {
			msgcenterDubboBaseService.update("emailLogManager",emailLog);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
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
					msgcenterDubboBaseService.removeById("emailLogManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	/** 
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 
	* 定精度，以后的数字四舍五入。 
	* @param v1 被除数 
	* @param v2 除数 
	* @param scale 表示表示需要精确到小数点以后几位。 
	* @return 两个参数的商 
	*/  
	public double div(double v1, double v2, int scale) {  
	   if (scale < 0) {  
	    throw new IllegalArgumentException(  
	      "The scale must be a positive integer or zero");  
	   }  
	   BigDecimal b1 = new BigDecimal(Double.toString(v1));  
	   BigDecimal b2 = new BigDecimal(Double.toString(v2));  
	   return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
	}  
}
