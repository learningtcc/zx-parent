package com.ink.admin.msgcenter.controller.channel;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.core.po.SmsAnalyze;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.query.SmsAnalyzeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("msgcenter/SmsAnalyze")
@Controller
public class SmsAnalyzeController extends BaseController {
	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null;

	// forward paths
	protected static final String LIST_JSP = "/msgcenter/channel/SmsAnalyze/list";
	protected static final String CREATE_JSP = "/msgcenter/channel/SmsAnalyze/create";
	protected static final String EDIT_JSP = "/msgcenter/channel/SmsAnalyze/edit";
	protected static final String SHOW_JSP = "/msgcenter/channel/SmsAnalyze/show";
	// redirect paths,startWith: !
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;

	@RequestMapping(value = "/list")
	public ModelAndView list() {

		SmsAnalyzeQuery query = newQuery(SmsAnalyzeQuery.class, DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("smsAnalyzeManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);

		return modelAndView;
	}

	/** 进入新增页面 */
	@RequestMapping(value = "/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}

	/** 保存新增对象 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(SmsAnalyze smsAnalyze) {
		try {
			msgcenterDubboBaseService.save("smsAnalyzeManager",smsAnalyze);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}

	/** 查看对象 */
	@RequestMapping(value = "/show")
	public ModelAndView show(SmsAnalyze smsAnalyze) {
		SmsAnalyze model = null;
		try {
			model = (SmsAnalyze) msgcenterDubboBaseService.getById("smsAnalyzeManager",smsAnalyze.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}

	/** 进入更新页面 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(SmsAnalyze smsAnalyze) {
		SmsAnalyze model = null;
		try {
			model = (SmsAnalyze) msgcenterDubboBaseService.getById("smsAnalyzeManager",smsAnalyze.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}

	/** 保存更新对象 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(SmsAnalyze smsAnalyze) {
		try {
			msgcenterDubboBaseService.update("smsAnalyzeManager",smsAnalyze);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}

	/** 删除对象 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(String items) {
		if (items != null) {
			String[] idArray = items.split(",");
			for (String id : idArray) {
				try {
					msgcenterDubboBaseService.removeById("smsAnalyzeManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return writeAjaxResponse("1");
	}

	@RequestMapping("statistics")
	public ModelAndView statistics(String chnId) {

		ModelAndView modelAndView = new ModelAndView();
		SmsChannel smsChn = null;
		SmsAnalyze sms = null;
		try {
			smsChn = (SmsChannel) msgcenterDubboBaseService.getById("smsChannelManager",Long.valueOf(chnId));
			sms = (SmsAnalyze) msgcenterDubboBaseService.executeDynamicMethod("smsAnalyzeManager","getByChnId",chnId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			BigDecimal dayCount = revString(sms.getSendCount());
			BigDecimal daySuccess = revString(sms.getSuccessCount());
			BigDecimal dayFail = revString(sms.getFailCount());
			BigDecimal dayDelive = revString(sms.getDeliveCount());
			modelAndView.addObject("dayEma", sms);
			modelAndView.addObject("daySuccess", div(daySuccess, dayCount));
			modelAndView.addObject("dayFail", div(dayFail, dayCount));
			modelAndView.addObject("dayDelive", div(dayDelive, dayCount));
		} catch (Exception e) {
			modelAndView.addObject("daySuccess", 0);
			modelAndView.addObject("dayFail", 0);
			modelAndView.addObject("dayDelive", 0);
		}

		try {
			Map<String, Object> liema = (Map<String, Object>) msgcenterDubboBaseService.executeDynamicMethod("smsAnalyzeManager","getTotalByChnId",chnId);
			Map<String, String> map = (Map) liema.get(null);
			BigDecimal total = revString(map.get("total"));
			BigDecimal success = revString(map.get("success"));
			BigDecimal fail = revString(map.get("fail"));
			BigDecimal delive = revString(map.get("delive"));
			modelAndView.addObject("liema", map);
			modelAndView.addObject("success", div(success, total));
			modelAndView.addObject("fail", div(fail, total));
			modelAndView.addObject("delive", div(delive, total));
		} catch (Exception e) {
			modelAndView.addObject("success", 0);
			modelAndView.addObject("fail", 0);
			modelAndView.addObject("delive", 0);
		}
		modelAndView.setViewName("/msgcenter/channel/sms/chnStatistics");
		modelAndView.addObject("date", new Date());
		modelAndView.addObject("smsChannel", smsChn);
		return modelAndView;
	}

	private BigDecimal revString(Object obj) throws Exception {
		BigDecimal res = BigDecimal.ZERO;
		if (null == obj){
			return res;
		}
		String str = String.valueOf(obj);
		if (str != null) {
			res = new BigDecimal(str);
		}
		return res;
	}

	public static double div(BigDecimal v1, BigDecimal v2) {
		BigDecimal a1 = v1.multiply(new BigDecimal(100));
		return a1.divide(v2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
