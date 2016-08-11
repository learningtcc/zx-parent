
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.asile;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.trade.api.enums.MchAuthStatusEnum;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.platform.asile.model.base.MchAuthEntity;
import com.ink.trade.api.platform.asile.model.in.MchAuthQueryInput;
import com.ink.trade.api.platform.asile.service.IMchAuthService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/MchAuth")
@Controller
public class MchAuthController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/MchAuth/list";
	protected static final String CREATE_JSP = "/trade/MchAuth/create";
	protected static final String EDIT_JSP = "/trade/MchAuth/edit";
	protected static final String SHOW_JSP = "/trade/MchAuth/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IMchAuthService mchAuthService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		MchAuthQueryInput query = newQuery(MchAuthQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<MchAuthEntity>> ret = mchAuthService.list(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		TagDataInfo payTypeData=new TagDataInfo();
		payTypeData.setJsonArray(PayType.getPayType());
		modelAndView.addObject("payTypeData", payTypeData);
		TagDataInfo mchAuthStatusData=new TagDataInfo();
		mchAuthStatusData.setJsonArray(MchAuthStatusEnum.getMchAuthStatusEnum());
		modelAndView.addObject("mchAuthStatusData", mchAuthStatusData);
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView(CREATE_JSP);
		TagDataInfo payTypeData=new TagDataInfo();
		payTypeData.setJsonArray(PayType.getPayType());
		modelAndView.addObject("payTypeData", payTypeData);
		TagDataInfo mchAuthStatusData=new TagDataInfo();
		mchAuthStatusData.setJsonArray(MchAuthStatusEnum.getMchAuthStatusEnum());
		modelAndView.addObject("mchAuthStatusData", mchAuthStatusData);
		return modelAndView;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(MchAuthEntity mchAuth) {
		mchAuth.setCreateTime(new Date());
		mchAuth.setLastupdateTime(new Date());
		mchAuthService.save(mchAuth);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MchAuthEntity mchAuth) {
		CommonResult<MchAuthEntity> ret = mchAuthService.getById(mchAuth.getId());
		ModelAndView modelAndView = new ModelAndView(SHOW_JSP);
		modelAndView.addObject("model", ret.getBussinessObj());
		TagDataInfo payTypeData=new TagDataInfo();
		payTypeData.setJsonArray(PayType.getPayType());
		modelAndView.addObject("payTypeData", payTypeData);
		TagDataInfo mchAuthStatusData=new TagDataInfo();
		mchAuthStatusData.setJsonArray(MchAuthStatusEnum.getMchAuthStatusEnum());
		modelAndView.addObject("mchAuthStatusData", mchAuthStatusData);
		return modelAndView;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MchAuthEntity mchAuth) {
		CommonResult<MchAuthEntity> ret = mchAuthService.getById(mchAuth.getId());
		ModelAndView modelAndView = new ModelAndView(EDIT_JSP);
		modelAndView.addObject("model", ret.getBussinessObj());
		TagDataInfo payTypeData=new TagDataInfo();
		payTypeData.setJsonArray(PayType.getPayType());
		modelAndView.addObject("payTypeData", payTypeData);
		TagDataInfo mchAuthStatusData=new TagDataInfo();
		mchAuthStatusData.setJsonArray(MchAuthStatusEnum.getMchAuthStatusEnum());
		modelAndView.addObject("mchAuthStatusData", mchAuthStatusData);
		return modelAndView;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(MchAuthEntity mchAuth) {
		mchAuth.setLastupdateTime(new Date());
		mchAuthService.update(mchAuth);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				mchAuthService.deleteById(Integer.valueOf(id));
			}
		}
		
		return writeAjaxResponse("1");
	}
}
