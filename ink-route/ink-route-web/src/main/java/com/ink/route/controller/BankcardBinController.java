
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.controller;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.enums.BankCardbinEnum;
import com.ink.route.api.model.in.BankcardBinQueryInput;
import com.ink.route.api.model.po.BankcardBin;
import com.ink.route.api.service.platform.IBankcardBinService;
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
 
@RequestMapping("BankcardBin")
@Controller
public class BankcardBinController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/BankcardBin/list";
	protected static final String CREATE_JSP = "/BankcardBin/create";
	protected static final String EDIT_JSP = "/BankcardBin/edit";
	protected static final String SHOW_JSP = "/BankcardBin/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IBankcardBinService bankcardBinService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		BankcardBinQueryInput query = newQuery(BankcardBinQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<BankcardBin>> ret = bankcardBinService.list(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		TagDataInfo cardbinData=new TagDataInfo();
		cardbinData.setJsonArray(BankCardbinEnum.getBankCardbinEnum());
		modelAndView.addObject("cardbinData", cardbinData);
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView(CREATE_JSP);
		TagDataInfo cardbinData=new TagDataInfo();
		cardbinData.setJsonArray(BankCardbinEnum.getBankCardbinEnum());
		modelAndView.addObject("cardbinData", cardbinData);
		return modelAndView;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(BankcardBin bankcardBinEntity) {
		bankcardBinEntity.setCreateTime(new Date());
		bankcardBinService.save(bankcardBinEntity);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(BankcardBin bankcardBinEntity) {
		CommonResult<BankcardBin> ret = bankcardBinService.getById(bankcardBinEntity.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		TagDataInfo cardbinData=new TagDataInfo();
		cardbinData.setJsonArray(BankCardbinEnum.getBankCardbinEnum());
		mav.addObject("cardbinData", cardbinData);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(BankcardBin bankcardBin) {
		CommonResult<BankcardBin> ret = bankcardBinService.getById(bankcardBin.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		TagDataInfo cardbinData=new TagDataInfo();
		cardbinData.setJsonArray(BankCardbinEnum.getBankCardbinEnum());
		mav.addObject("cardbinData", cardbinData);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(BankcardBin bankcardBin) {
		bankcardBinService.update(bankcardBin);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				bankcardBinService.deleteById(Long.valueOf(id));
			}
		}
		
		return writeAjaxResponse("1");
	}
}
