package com.ink.admin.msgcenter.controller.channel;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.redis.client.Yedis;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.core.po.MerchantChn;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.query.MerchantChnQuery;
import com.ink.msgcenter.core.query.SmsChannelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("msgcenter/channel/sms")
@Controller
public class SmsChannelController extends BaseController {

	YinkerLogger loger = YinkerLogger.getLogger(SmsChannelController.class);
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = " priority_level ";
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/channel/sms/list";
	protected static final String CREATE_JSP = "/msgcenter/channel/sms/create";
	protected static final String EDIT_JSP = "/msgcenter/channel/sms/edit";
	protected static final String SHOW_JSP = "/msgcenter/channel/sms/show";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	@Autowired
	private Yedis yedis ;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		SmsChannelQuery query = newQuery(SmsChannelQuery.class,DEFAULT_SORT_COLUMNS);
//		if (StringUtils.isBlank(query.getStatus())){
//			query.setStatus("0");
//		}
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("smsChannelManager",query);
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
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(CREATE_JSP);
		Map<String,Long> codeMap = null;
		try {
			codeMap = (Map<String, Long>) msgcenterDubboBaseService.executeDynamicMethod("smsChannelManager","getMaxSmsChannelCode",null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("codeMap",codeMap);
		return modelAndView;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(SmsChannel smsChannel,HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("SESSION_USERID");
		String fullName = (String) request.getSession().getAttribute("SESSION_FULLNAME");
		smsChannel.setCreateTime(new Date());
		smsChannel.setCreatorId(userId);
		smsChannel.setCreatorName(fullName);
		try {
			msgcenterDubboBaseService.save("smsChannelManager",smsChannel);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(SmsChannel smsChannel) {
		SmsChannel model = null;
		try {
			model = (SmsChannel) msgcenterDubboBaseService.getById("smsChannelManager",smsChannel.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(SmsChannel smsChannel) {
		SmsChannel model = null;
		try {
			model = (SmsChannel) msgcenterDubboBaseService.getById("smsChannelManager",smsChannel.getId());
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
	public String update(SmsChannel smsChannel,HttpServletRequest request) {

		String userId = (String) request.getSession().getAttribute("SESSION_USERID");
		String fullName = (String) request.getSession().getAttribute("SESSION_FULLNAME");
		smsChannel.setUpdateTime(new Date());
		smsChannel.setUpdatorId(userId);
		smsChannel.setUpdatorName(fullName);

		try {
			msgcenterDubboBaseService.update("smsChannelManager",smsChannel);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}

		deleteCache(smsChannel);
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
					msgcenterDubboBaseService.removeById("smsChannelManager",Long.valueOf(id));
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
					SmsChannel smsChannel1 = (SmsChannel) msgcenterDubboBaseService.getById("smsChannelManager",Long.valueOf(id));
					deleteCache(smsChannel1);

					MerchantChnQuery chnQuery = new MerchantChnQuery();
					chnQuery.setChnCode(smsChannel1.getChnCode());
					chnQuery.setChnType("1");
					List<MerchantChn> chnList = msgcenterDubboBaseService.find("merchantChnManager",chnQuery);
					for (int i = 0; i < chnList.size(); i++) {
						yedis.deleteObject(CacheConstant.MSG_MERCT,chnList.get(i).getMerctCode());
					}
					SmsChannel smsChannel = new SmsChannel();
					smsChannel.setId(Long.valueOf(id));
					smsChannel.setStatus(status);
					smsChannel.setUpdateTime(new Date());
					smsChannel.setUpdatorId(userId);
					smsChannel.setUpdatorName(fullName);
					msgcenterDubboBaseService.executeDynamicMethod("smsChannelManager","updateStatus",smsChannel);
				} catch (Exception e) {
					e.printStackTrace();
					loger.info("更新邮件通道状态失败");
					return writeAjaxResponse("-1");
				}
			}
		}

		return writeAjaxResponse("1");
	}

	/**进入优先级调整页面*/
	@RequestMapping(value="/levelOrder")
	public ModelAndView levelOrder() {
		ModelAndView mav = new ModelAndView("/msgcenter/channel/sms/levelOrder");
		return mav;
	}

	/**查询监控业务对象——树形结构输出*/
	@RequestMapping(value="/findMonitorServiceTree")
	@ResponseBody
	public Object findEmailChannelTree() {

		List emailChannelList = new ArrayList();
		try{
			emailChannelList = (List) msgcenterDubboBaseService.executeDynamicMethod("smsChannelManager","findSmsChannelTree",null);
		}catch (Exception e){
			e.printStackTrace();
		}

		Map parentMap = new HashMap();
		parentMap.put("id",0);
		parentMap.put("pid","001");
		parentMap.put("name","邮件通道");
		parentMap.put("open",true);
		parentMap.put("childOuter",false);
		emailChannelList.add(parentMap);

		return emailChannelList;
	}

	/**保存更新优先级调整对象*/
	@RequestMapping(value="/saveLevelOrder")
	@ResponseBody
	public String saveLevelOrder(String channelLevel,HttpServletRequest request) {
		SmsChannel smsChannel =  new SmsChannel();
		String userId = (String) request.getSession().getAttribute("SESSION_USERID");
		String fullName = (String) request.getSession().getAttribute("SESSION_FULLNAME");
		smsChannel.setUpdateTime(new Date());
		smsChannel.setUpdatorId(userId);
		smsChannel.setUpdatorName(fullName);

		try {
			msgcenterDubboBaseService.executeDynamicMethod("smsChannelManager","saveLevelOrder",smsChannel, channelLevel);
		} catch (Exception e) {
			writeAjaxResponse("0");
			e.printStackTrace();
		}
		return writeAjaxResponse("1");
	}

	private void deleteCache(SmsChannel smsChannel){
		yedis.deleteObject(CacheConstant.MSG_CHANNEL,smsChannel.getChnCode());
	}
}
