package com.ink.admin.msgcenter.controller.merchant;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.redis.client.Yedis;
import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.api.model.input.EmailInput;
import com.ink.msgcenter.api.model.input.SmsExtInput;
import com.ink.msgcenter.api.model.input.SmsInput;
import com.ink.msgcenter.api.model.input.SmsMassInput;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.service.EmailService;
import com.ink.msgcenter.api.service.SmsService;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.query.MerchantInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("msgcenter/merchant/merchantInfo")
@Controller
public class MerchantInfoController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/merchant/merchantInfo/list";
	protected static final String CREATE_JSP = "/msgcenter/merchant/merchantInfo/create";
	protected static final String EDIT_JSP = "/msgcenter/merchant/merchantInfo/edit";
	protected static final String SHOW_JSP = "/msgcenter/merchant/merchantInfo/show";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	@Autowired
	private Yedis yedis;
	@Autowired
	private SmsService smsService;
	@Autowired
	private EmailService emailService;

	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		MerchantInfoQuery query = newQuery(MerchantInfoQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("merchantInfoManager",query);
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
	public String save(MerchantInfo merchantInfo,HttpServletRequest request) {

		MerchantInfoQuery query = new MerchantInfoQuery();
		query.setSn(merchantInfo.getSn());
		List dataList = null;
		try {
			dataList = msgcenterDubboBaseService.find("merchantInfoManager",query);
			if (!(null == dataList || dataList.isEmpty())){
				return writeAjaxResponse("0");
			}

			String userId = (String) request.getSession().getAttribute("SESSION_USERID");
			String fullName = (String) request.getSession().getAttribute("SESSION_FULLNAME");
			merchantInfo.setCreatorId(userId);
			merchantInfo.setCreatorName(fullName);
			merchantInfo.setCreateTime(new Date());
			msgcenterDubboBaseService.save("merchantInfoManager",merchantInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}

		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MerchantInfo merchantInfo) {
		MerchantInfo model = null;
		try {
			model = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",merchantInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MerchantInfo merchantInfo) {
		MerchantInfo model = null;
		try {
			model = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",merchantInfo.getId());
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
	public String update(MerchantInfo merchantInfo,HttpServletRequest request) {

		try {
			String userId = (String) request.getSession().getAttribute("SESSION_USERID");
			String fullName = (String) request.getSession().getAttribute("SESSION_FULLNAME");
			merchantInfo.setUpdatorId(userId);
			merchantInfo.setUpdatorName(fullName);
			merchantInfo.setUpdateTime(new Date());
			msgcenterDubboBaseService.update("merchantInfoManager",merchantInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}

		deleteCache(merchantInfo);
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
					msgcenterDubboBaseService.removeById("merchantInfoManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}

	/**删除对象*/
	@RequestMapping(value="/updateStatus")
	@ResponseBody
	public String updateStatus(String items,String status,HttpServletRequest request) {

		String userId = (String) request.getSession().getAttribute("SESSION_USERID");
		String fullName = (String) request.getSession().getAttribute("SESSION_FULLNAME");

		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				try {
					MerchantInfo merchantInfo1 = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",Long.valueOf(id));
					deleteCache(merchantInfo1);

					MerchantInfo merchantInfo = new MerchantInfo();
					merchantInfo.setId(Long.valueOf(id));
					merchantInfo.setStatus(status);
					merchantInfo.setUpdateTime(new Date());
					merchantInfo.setUpdatorId(userId);
					merchantInfo.setUpdatorName(fullName);
					msgcenterDubboBaseService.executeDynamicMethod("merchantInfoManager","updateStatus",merchantInfo);
				} catch (Exception e) {
					e.printStackTrace();
					return writeAjaxResponse("-1");
				}
			}
		}

		return writeAjaxResponse("1");
	}

	private void deleteCache(MerchantInfo merchantInfo){
		yedis.deleteObject(CacheConstant.MSG_MERCT,merchantInfo.getSn());
	}
	
	@RequestMapping("get")
	@ResponseBody
	public MerchantInfo get(String id){
		MerchantInfo merchantInfo = null;
		try {
			merchantInfo = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return merchantInfo;
	}

	/**进入发送邮件页面*/
	@RequestMapping(value="/goSendEmail")
	public ModelAndView goSendEmail() {
		ModelAndView mav = new ModelAndView("/msgcenter/merchant/merchantInfo/sendEmail");
		return mav;
	}

	/**进入发送短信页面*/
	@RequestMapping(value="/goSendSms")
	@ResponseBody
	public ModelAndView goSendSms() {
		ModelAndView mav = new ModelAndView("/msgcenter/merchant/merchantInfo/sendSms");
		return mav;
	}

	/**发送邮件*/
	@RequestMapping(value="/sendEmail")
	@ResponseBody
	public MsgOutput sendEmail(EmailInput emailInput) {
		MsgOutput msgOutput = new MsgOutput();
		try{
			msgOutput = emailService.sendEmail(emailInput);
		}catch (Exception e){
			e.printStackTrace();
		}

		return msgOutput;
	}

	/**发送短信*/
	@RequestMapping(value="/sendSms")
	@ResponseBody
	public MsgOutput sendSms(HttpServletRequest request) {
		MsgOutput msgOutput = new MsgOutput();

		try {
			SmsExtInput smsExtInput = new SmsExtInput();
			Map params = WebUtils.getParametersStartingWith(request, "");
			String sendFlag = (String) params.get("sendFlag");
			smsExtInput.setMerctCode((String) params.get("merctCode"));
			smsExtInput.setTempId((String) params.get("tempId"));
			smsExtInput.setBizId((String) params.get("bizId"));
			smsExtInput.setMobile((String) params.get("mobile"));
			smsExtInput.setParamJson((String) params.get("paramJson"));
			smsExtInput.setExtNo((String) params.get("extNo"));
			smsExtInput.setExtInfo((String) params.get("extInfo"));
			smsExtInput.setReportUrl((String) params.get("reportUrl"));
			smsExtInput.setChnCode((String) params.get("chnCode"));
			smsExtInput.setUpUrl((String) params.get("upUrl"));
			String sendTime = (String) params.get("sendTime");
			if (StringUtils.isNotBlank(sendTime)){
				smsExtInput.setSendTime(DateUtil.formatToyyyyMMddHHmmss(sendTime));
			}
			if ("1".equals(sendFlag)){//单发短息
				SmsInput smsInput = new SmsInput();
				smsInput.setMerctCode(smsExtInput.getMerctCode());
				smsInput.setBizId(smsExtInput.getBizId());
				smsInput.setTempId(smsExtInput.getTempId());
				smsInput.setMobile(smsExtInput.getMobile());
				smsInput.setParamJson(smsExtInput.getParamJson());
				smsInput.setSendTime(smsExtInput.getSendTime());
				smsInput.setReportUrl(smsExtInput.getReportUrl());
				smsInput.setChnCode(smsExtInput.getChnCode());
				smsInput.setReportUrl(smsExtInput.getUpUrl());
				msgOutput = smsService.sendSms(smsInput);
			}else if ("2".equals(sendFlag)){//单发短信(含接口)
				msgOutput = smsService.sendSmsWithExt(smsExtInput);
			}else  if ("3".equals(sendFlag)){//群发短信
				SmsMassInput massInput = new SmsMassInput();
				massInput.setMerctCode(smsExtInput.getMerctCode());
				massInput.setBizId(smsExtInput.getBizId());
				massInput.setTempId(smsExtInput.getTempId());
				massInput.setMobileList(Arrays.asList(smsExtInput.getMobile().split(",")));
				massInput.setParamJson(smsExtInput.getParamJson());
				massInput.setSendTime(smsExtInput.getSendTime());
				massInput.setReportUrl(smsExtInput.getReportUrl());
				massInput.setChnCode(smsExtInput.getChnCode());
				msgOutput = smsService.sendMassSms(massInput);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return msgOutput;
	}


}
