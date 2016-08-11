
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.basic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.trade.api.enums.BankCardbinEnum;
import com.ink.trade.api.platform.basic.model.base.BankcardBinEntity;
import com.ink.trade.api.platform.basic.model.in.BankcardBinQueryInput;
import com.ink.trade.api.platform.basic.service.IBankcardBinService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/BankcardBin")
@Controller
public class BankcardBinController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/BankcardBin/list";
	protected static final String CREATE_JSP = "/trade/BankcardBin/create";
	protected static final String EDIT_JSP = "/trade/BankcardBin/edit";
	protected static final String SHOW_JSP = "/trade/BankcardBin/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IBankcardBinService bankcardBinService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		BankcardBinQueryInput query = newQuery(BankcardBinQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<BankcardBinEntity>> ret = bankcardBinService.list(query);
		
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
	public String save(BankcardBinEntity bankcardBinEntity) {
		bankcardBinEntity.setCreateTime(new Date());
		bankcardBinService.save(bankcardBinEntity);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(BankcardBinEntity bankcardBinEntity) {
		CommonResult<BankcardBinEntity> ret = bankcardBinService.getById(bankcardBinEntity.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		TagDataInfo cardbinData=new TagDataInfo();
		cardbinData.setJsonArray(BankCardbinEnum.getBankCardbinEnum());
		mav.addObject("cardbinData", cardbinData);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(BankcardBinEntity bankcardBin) {
		CommonResult<BankcardBinEntity> ret = bankcardBinService.getById(bankcardBin.getId());
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
	public String update(BankcardBinEntity bankcardBin) {
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
