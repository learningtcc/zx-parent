package com.ink.admin.msgcenter.controller.merchant;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.redis.client.Yedis;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.core.po.EmailChannel;
import com.ink.msgcenter.core.po.MerchantChn;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.query.MerchantChnQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("msgcenter/merchant/merchantChn")
@Controller
public class MerchantChnController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/merchant/merchantChn/list";
	protected static final String CREATE_JSP = "/msgcenter/merchant/merchantChn/create";
	protected static final String EDIT_JSP = "/msgcenter/merchant/merchantChn/edit";
	protected static final String SHOW_JSP = "/msgcenter/merchant/merchantChn/show";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	@Autowired
	private Yedis yedis;

	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		MerchantChnQuery query = newQuery(MerchantChnQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("merchantChnManager",query);
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
	public String save(MerchantChn merchantChn) {
		try {
			msgcenterDubboBaseService.save("merchantChnManager",merchantChn);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MerchantChn merchantChn) {
		MerchantChn model = null;
		try {
			model = (MerchantChn) msgcenterDubboBaseService.getById("merchantChnManager",merchantChn.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MerchantChn merchantChn) {
		MerchantChn model = null;
		try {
			model = (MerchantChn) msgcenterDubboBaseService.getById("merchantChnManager",merchantChn.getId());
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
	public String update(MerchantChn merchantChn) {
		try {
			msgcenterDubboBaseService.update("merchantChnManager",merchantChn);
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
					msgcenterDubboBaseService.removeById("merchantChnManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}

	/**
	 * 选择邮件通道
	 * @return
	 */
	@RequestMapping(value="/selectEmailChannelList")
	public ModelAndView selectEmailChannelList(String id,String sn) {

		List emailChannelList = null;
		try {
			emailChannelList = (List) msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","selectEmailChannelList",Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/msgcenter/merchant/merchantChn/selectEmailChannelList");
		modelAndView.addObject("dataList",emailChannelList);
		modelAndView.addObject("id",id);
		modelAndView.addObject("sn",sn);
		return modelAndView;
	}

	/**
	 * 选择短息通道
	 * @return
	 */
	@RequestMapping(value="/selectSmsChannelList")
	public ModelAndView selectSmsChannelList(String id,String sn) {

		List smsChannelList = null;
		try {
			smsChannelList = (List) msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","selectSmsChannelList",Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/msgcenter/merchant/merchantChn/selectSmsChannelList");
		modelAndView.addObject("dataList",smsChannelList);
		modelAndView.addObject("id",id);
		modelAndView.addObject("sn",sn);
		return modelAndView;
	}

	/**
	 * 保存选择的邮件通道
	 * @return
	 */
	@RequestMapping(value="/saveEmailChannelList")
	@ResponseBody
	public String saveEmailChannelList(String id,String sn ,String ids,HttpServletRequest request) {

		String userId = (String) request.getSession().getAttribute("SESSION_USERID");
		String fullName = (String) request.getSession().getAttribute("SESSION_FULLNAME");
		Date date = new Date();
		try{
			msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","deleteForMerchantCode",sn,"2");
			if(ids!=null){
				String[] idArray = ids.split(",");
				for(String channelId: idArray){
					EmailChannel emailChannel = (EmailChannel) msgcenterDubboBaseService.getById("emailChannelManager",Long.valueOf(channelId));
					MerchantChn merchantChn = new MerchantChn();
					merchantChn.setChnId(emailChannel.getId());
					merchantChn.setChnCode(emailChannel.getChnCode());
					merchantChn.setChnType("2");
					merchantChn.setMerctId(Long.valueOf(id));
					merchantChn.setMerctCode(sn);
					merchantChn.setStatus("0");
					merchantChn.setCreatorId(userId);
					merchantChn.setCreatorName(fullName);
					merchantChn.setCreateTime(date);
					msgcenterDubboBaseService.save("merchantChnManager",merchantChn);

				}
				msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","createTableForEmailMerchantLog",sn);
			}
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}finally {
			yedis.deleteObject(CacheConstant.MSG_MERCT,sn);
		}

		return writeAjaxResponse("1");
	}

	/**
	 * 保存选择的短信通道
	 * @return
	 */
	@RequestMapping(value="/saveSmsChannelList")
	@ResponseBody
	public String saveSmsChannelList(String id,String sn ,String ids,HttpServletRequest request) {

		String userId = (String) request.getSession().getAttribute("SESSION_USERID");
		String fullName = (String) request.getSession().getAttribute("SESSION_FULLNAME");
		Date date = new Date();
		try{
			msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","deleteForMerchantCode",sn,"1");
			if(ids!=null){
				String[] idArray = ids.split(",");
				for(String channelId: idArray){
					SmsChannel smsChannel = (SmsChannel) msgcenterDubboBaseService.getById("smsChannelManager",Long.valueOf(channelId));
					MerchantChn merchantChn = new MerchantChn();
					merchantChn.setChnId(smsChannel.getId());
					merchantChn.setChnCode(smsChannel.getChnCode());
					merchantChn.setChnType("1");
					merchantChn.setMerctId(Long.valueOf(id));
					merchantChn.setMerctCode(sn);
					merchantChn.setStatus("0");
					merchantChn.setCreatorId(userId);
					merchantChn.setCreatorName(fullName);
					merchantChn.setCreateTime(date);
					msgcenterDubboBaseService.save("merchantChnManager",merchantChn);
				}
				msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","createTableForSmsMerchantLog",sn);
			}
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}finally {
			yedis.deleteObject(CacheConstant.MSG_MERCT,sn);
		}

		return writeAjaxResponse("1");
	}

	@RequestMapping("findChannels")
	@ResponseBody
	public List<MerchantChn> findChannels(MerchantChn mc){
		List<MerchantChn> list = null;
		try {
			list = (List<MerchantChn>) msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","findChannels",mc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  list;
	}
	
	@RequestMapping("findEmailsByMerctId")
	@ResponseBody
	public List findEmailChannelsByMerchId(String merctId){//
		List list = null;
		try {
			list = (List) msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","findEmailChannelsByMerchId",Long.valueOf(merctId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list ;
	}
	
	@RequestMapping("findSmsByMerctId")
	@ResponseBody
	public List findSmsChannelsByMerchId(String merctId){//
		List list = null;
		try {
			list = (List) msgcenterDubboBaseService.executeDynamicMethod("merchantChnManager","findSmsChannelsByMerchId",Long.valueOf(merctId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list ;
	}
}
