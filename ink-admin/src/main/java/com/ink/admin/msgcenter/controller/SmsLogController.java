package com.ink.admin.msgcenter.controller;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.msgcenter.api.constants.CommonConstant;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.po.SmsCode;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.core.query.SmsLogQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("msgcenter/SmsLog")
@Controller
public class SmsLogController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = " submit_time desc, send_time desc";
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/log/smsLog/list";
	protected static final String CREATE_JSP = "/msgcenter/log/smsLog/create";
	protected static final String EDIT_JSP = "/msgcenter/log/smsLog/edit";
	protected static final String SHOW_JSP = "/msgcenter/log/smsLog/show";
	protected static final String SHOW_SMS_JSP= "/msgcenter/merchant/merchantInfo/showSmsLog";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	
	private YinkerLogger logger = YinkerLogger.getLogger(getClass());
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		SmsLogQuery query = newQuery(SmsLogQuery.class,DEFAULT_SORT_COLUMNS);
		try {
			if(StringUtils.isNotBlank(query.getMerctCode())){
				Page page = msgcenterDubboBaseService.findPage("smsLogManager",query);
				modelAndView.addObject("page", page);
			}else if(StringUtils.isNotBlank(query.getMerctId())){
				MerchantInfo merchantInfo = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",Long.valueOf(query.getMerctId()));
				if(merchantInfo!=null){
					query.setMerctCode(merchantInfo.getSn());
					Page page = msgcenterDubboBaseService.findPage("smsLogManager",query);
					modelAndView.addObject("page", page);
				}
			}else{
				logger.info("短信发送日志查询商户Id或Code为空!");
			}
		} catch (Exception e) {
			logger.info("短信发送日志查询商户未创建日志!");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/showSmsLog")
	public ModelAndView showSmsLog() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(SHOW_SMS_JSP);
		SmsLogQuery query = newQuery(SmsLogQuery.class,DEFAULT_SORT_COLUMNS);
		try {
			if(StringUtils.isNotBlank(query.getMerctCode())){
				Page page = msgcenterDubboBaseService.findPage("smsLogManager",query);
				modelAndView.addObject("page", page);
			}else if(StringUtils.isNotBlank(query.getMerctId())){
				SmsLog log = (SmsLog) msgcenterDubboBaseService.getById("smsLogManager",Long.valueOf(query.getMerctId()));
				if(log!=null){
					query.setMerctCode(log.getMerctCode());
					Page page = msgcenterDubboBaseService.findPage("smsLogManager",query);
					modelAndView.addObject("page", page);
				}
			}else{
				logger.info("短信发送日志查询商户Id或Code为空!");
			}
		} catch (Exception e) {
			logger.info("短信发送日志查询商户未创建日志!");
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
	public String save(SmsLog smsLog) {
		try {
			msgcenterDubboBaseService.save("smsLogManager",smsLog);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	/**
	 * 保存上行
	 * @Description: TODO
	 * @param msgId
	 * @param mobile
	 * @param upMsg
	 * @param extInfo
	 * @param upTime
	 * @return
	 * @throws
	 */
	@RequestMapping("/saveUp")
	@ResponseBody
	public String save(String msgId, String mobile, String upMsg, String extInfo, String upTime){
		String result = "success";
		SmsCode record = new SmsCode();
		record.setSmsId(msgId);
		record.setMobile(mobile);
		try {
			SmsCode smsCode = (SmsCode) msgcenterDubboBaseService.executeDynamicMethod("smsCodeManager","getSmsCode",record);
			if(smsCode!=null){
				SmsLog smsLog = new SmsLog();
				smsLog.setSmsId(msgId);
				smsLog.setMobile(mobile);
				smsLog.setSmsMsg(upMsg);
				smsLog.setExtInfo(extInfo);
				smsLog.setMerctCode(smsCode.getMerctCode());
				smsLog.setSmsCode(smsCode.getSmsCode());
				smsLog.setSmsType(CommonConstant.SMS_TYPE_UP);
				smsLog.setSmsType("1");
				smsLog.setMerctId(smsCode.getMerctId());
				try {
					smsLog.setSendTime(DateUtil.formatToyyyyMMddHHmmss(upTime));
				} catch (Exception e) {
					smsLog.setSendTime(new Date());
				}
				msgcenterDubboBaseService.save("smsLogManager",smsLog);
			}
		} catch (Exception e) {
			result = "fail";
		}
		
		return writeAjaxResponse(result);
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(SmsLog smsLog) {
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		if(StringUtils.isBlank(smsLog.getMerctCode())){
			logger.info("短信发送日志查询商户Code为空!");
			return mav;
		}
		try {
			SmsLog model = (SmsLog) msgcenterDubboBaseService.executeDynamicMethod("smsLogManager","getSmsLogById",smsLog.getId(), smsLog.getMerctCode());
			mav.addObject("model", model);
		} catch (Exception e) {
			logger.info("短信发送日志查询商户"+smsLog.getMerctCode()+"未创建日志!");
		}
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(SmsLog smsLog) {
		SmsLog model = null;
		try {
			model = (SmsLog) msgcenterDubboBaseService.getById("smsLogManager",smsLog.getId());
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
	public String update(SmsLog smsLog) {
		try {
			msgcenterDubboBaseService.update("smsLogManager",smsLog);
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
					msgcenterDubboBaseService.removeById("smsLogManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
}
