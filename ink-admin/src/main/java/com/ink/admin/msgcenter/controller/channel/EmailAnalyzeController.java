package com.ink.admin.msgcenter.controller.channel;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.core.po.EmailAnalyze;
import com.ink.msgcenter.core.po.EmailChannel;
import com.ink.msgcenter.core.query.EmailAnalyzeQuery;
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
 
@RequestMapping("msgcenter/EmailAnalyze")
@Controller
public class EmailAnalyzeController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/channel/EmailAnalyze/list";
	protected static final String CREATE_JSP = "/msgcenter/channel/EmailAnalyze/create";
	protected static final String EDIT_JSP = "/msgcenter/channel/EmailAnalyze/edit";
	protected static final String SHOW_JSP = "/msgcenter/channel/EmailAnalyze/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService msgcenterDubboBaseService;

	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		EmailAnalyzeQuery query = newQuery(EmailAnalyzeQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("emailAnalyzeManager",query);
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
	public String save(EmailAnalyze emailAnalyze) {
		try {
			msgcenterDubboBaseService.save("emailAnalyzeManager",emailAnalyze);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(EmailAnalyze emailAnalyze) {
		EmailAnalyze model = null;
		try {
			model = (EmailAnalyze) msgcenterDubboBaseService.getById("emailAnalyzeManager",emailAnalyze.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(EmailAnalyze emailAnalyze) {
		EmailAnalyze model = null;
		try {
			model = (EmailAnalyze) msgcenterDubboBaseService.getById("emailAnalyzeManager",emailAnalyze.getId());
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
	public String update(EmailAnalyze emailAnalyze) {
		try {
			msgcenterDubboBaseService.update("emailAnalyzeManager",emailAnalyze);
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
					msgcenterDubboBaseService.removeById("emailAnalyzeManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	@RequestMapping("statistics")
	public ModelAndView statistics(String chnId){
		ModelAndView modelAndView = new ModelAndView();
		EmailChannel emailChn = null;
		try {
			emailChn = (EmailChannel) msgcenterDubboBaseService.getById("emailChannelManager",Long.valueOf(chnId));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EmailAnalyze ema = (EmailAnalyze) msgcenterDubboBaseService.executeDynamicMethod("emailAnalyzeManager","getByChnId",chnId);
			BigDecimal dayCount = revString(ema.getSendCount());
			BigDecimal daySuccess = revString(ema.getSuccessCount()); 
			BigDecimal dayFail = revString(ema.getFailCount()); 
			modelAndView.addObject("dayEma", ema);
			modelAndView.addObject("daySuccess",div(daySuccess,dayCount));
			modelAndView.addObject("dayFail",div(dayFail,dayCount));
		} catch (Exception e) {
			modelAndView.addObject("dayEma", new EmailAnalyze());
			modelAndView.addObject("daySuccess",0);
			modelAndView.addObject("dayFail",0);
		}
		
		try {
			Map<String, Object> liema = (Map<String, Object>) msgcenterDubboBaseService.executeDynamicMethod("emailAnalyzeManager","getTotalByChnId",chnId);
			Map<String, String> map = (Map) liema.get(null);
			BigDecimal total = revString(map.get("total")); 
			BigDecimal success = revString(map.get("success")); 
			BigDecimal fail = revString(map.get("fail"));
			modelAndView.addObject("liema", map);
			modelAndView.addObject("success",div(success,total));
			modelAndView.addObject("fail",div(fail,total));
		} catch (Exception e) {
			modelAndView.addObject("success",0);
			modelAndView.addObject("fail",0);
		}
		modelAndView.setViewName("/msgcenter/channel/email/chnStatistics");
		modelAndView.addObject("date", new Date());
		modelAndView.addObject("emailChannel", emailChn);
		return modelAndView;
	}
	
	private BigDecimal revString(Object obj) throws Exception{
		BigDecimal res = BigDecimal.ZERO;
		String str = String.valueOf(obj);
		if(str!=null){
			res = new BigDecimal(str);
		}
		return res;
	}
	
	public static double div(BigDecimal v1, BigDecimal v2) {
		BigDecimal a1 = v1.multiply(new BigDecimal(100));
		return a1.divide(v2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
