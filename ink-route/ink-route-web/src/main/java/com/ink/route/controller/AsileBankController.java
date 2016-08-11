
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.controller;

import com.alibaba.fastjson.JSONArray;
import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.enums.RouteBusinessType;
import com.ink.route.api.model.in.AsileBankQueryInput;
import com.ink.route.api.model.po.AsileBank;
import com.ink.route.api.model.po.AsileInfo;
import com.ink.route.api.model.po.BasicBank;
import com.ink.route.api.service.platform.IAsileBankService;
import com.ink.route.api.service.platform.IAsileInfoService;
import com.ink.route.api.service.platform.IBasicBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AsileBank")
@Controller
public class AsileBankController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/AsileBank/list";
	protected static final String CREATE_JSP = "/AsileBank/create";
	protected static final String EDIT_JSP = "/AsileBank/edit";
	protected static final String SHOW_JSP = "/AsileBank/show";
	protected static final String PRIORITY_JSP="/AsileBank/prioritylist";
	//redirect paths,startWith: !
	
	@Autowired
	private IAsileBankService asileBankService;
	@Autowired
	private IAsileInfoService asileInfoService;
	@Autowired
	private IBasicBankService basicBankService;
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		TagDataInfo routeBusinessType=new TagDataInfo();
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		AsileBankQueryInput query = newQuery(AsileBankQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AsileBank>> ret = asileBankService.list(query);
		if(0!=ret.getCode()){
			throw new RuntimeException(ret.getMessage());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.addObject("routeBusinessType",routeBusinessType);
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		List<AsileInfo> asileList=asileInfoService.getAll();
		List<BasicBank> basicBankList=basicBankService.getAll();
		TagDataInfo routeBusinessType=new TagDataInfo();
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		TagDataInfo asileInfos=new TagDataInfo();
		asileInfos.setJsonArray(asileList);
		TagDataInfo basicBanks=new TagDataInfo();
		basicBanks.setJsonArray(basicBankList);
		ModelAndView model=new ModelAndView();
		model.addObject("routeBusinessType",routeBusinessType);
		model.addObject("asileInfos",asileInfos);
		model.addObject("basicBanks",basicBanks);
		model.setViewName(CREATE_JSP);
		return model;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AsileBank asileBank,long asileId,long bankId) {
		AsileInfo asileinfo=asileInfoService.getById(asileId).getBussinessObj();
		BasicBank basicbank=basicBankService.getById(bankId).getBussinessObj();
		String userName = this.getLoginUser();
		asileBank.setCreaterName(userName);
		asileBank.setUpdaterName(userName);
		AsileBankQueryInput input=new AsileBankQueryInput();
		input.setBankShort(basicbank.getBankShortName());
		input.setAsilePayType(asileBank.getAsilePayType());
		CommonResult<Page<AsileBank>> ret1=asileBankService.list(input);
		int priority= ret1.getBussinessObj().getTotalCount();
		asileBank.setPriority(priority+1);
		asileBank.setCreateTime(new Date());
		asileBank.setUpdateTime(new Date());
		asileBank.setAsileCode(asileinfo.getAsileCode());
		asileBank.setAsileName(asileinfo.getAsileName());
		asileBank.setBankName(basicbank.getBankName());
		asileBank.setBankShort(basicbank.getBankShortName());
		asileBank.setBankCode(String.valueOf(bankId));
		CommonResult<Object> ret = asileBankService.save(asileBank);
		if(0!=ret.getCode()){
			return writeAjaxResponse(ret.getMessage());
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AsileBank asileBank) {
		TagDataInfo routeBusinessType=new TagDataInfo();
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		CommonResult<AsileBank> ret = asileBankService.getById(asileBank.getId());
		if(0!=ret.getCode()){
			throw new RuntimeException(ret.getMessage());
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		mav.addObject("routeBusinessType",routeBusinessType);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AsileBank asileBank) {
		List<AsileInfo> asileList=asileInfoService.getAll();
		List<BasicBank> basicBankList=basicBankService.getAll();
		TagDataInfo asileInfos=new TagDataInfo();
		TagDataInfo routeBusinessType=new TagDataInfo();
		TagDataInfo basicBanks=new TagDataInfo();
		basicBanks.setJsonArray(basicBankList);
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		asileInfos.setJsonArray(asileList);
		CommonResult<AsileBank> ret = asileBankService.getById(asileBank.getId());
		if(0!=ret.getCode()){
			throw new RuntimeException(ret.getMessage());
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("routeBusinessType",routeBusinessType);
		mav.addObject("model", ret.getBussinessObj());
		mav.addObject("asileInfos",asileInfos);
		mav.addObject("basicBanks",basicBanks);
		mav.setViewName(EDIT_JSP);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AsileBank asileBank,String bankCode,String asileCode) {
		List<AsileInfo> asileinfos=asileInfoService.getAll();
		List<BasicBank> basicbanks=basicBankService.getAll();
		String userName = this.getLoginUser();
		asileBank.setUpdaterName(userName);
		for(AsileInfo asileInfo:asileinfos){
			if (asileInfo.getAsileCode().equals(asileCode)){
				asileBank.setAsileCode(asileInfo.getAsileCode());
				asileBank.setAsileName(asileInfo.getAsileName());
				break;
			}
		}
		for(BasicBank basicbank:basicbanks){
			if(basicbank.getBankShortName().equals(bankCode)){
				asileBank.setBankName(basicbank.getBankName());
				asileBank.setBankShort(basicbank.getBankShortName());
				asileBank.setBankCode(String.valueOf(basicbank.getId()));
				break;
			}
		}
		asileBank.setUpdateTime(new Date());
		asileBankService.update(asileBank);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				asileBankService.deleteById(Long.valueOf(id));
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	/**
	 * 
	 * @Description 获取登录用户
	 * @author xuguoqi
	 * @date 2016年7月20日 下午4:55:43
	 * @return
	 */
	private String getLoginUser(){
		return "admin";
	}
	
	@RequestMapping(value="/priority")
	public ModelAndView priority() {
		List<BasicBank> basicBankList=basicBankService.getAll();
		TagDataInfo routeBusinessType=new TagDataInfo();
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		TagDataInfo basicBanks=new TagDataInfo();
		basicBanks.setJsonArray(basicBankList);
		CommonResult<Page<AsileBank>> ret = new CommonResult<Page<AsileBank>>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(PRIORITY_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.addObject("routeBusinessType",routeBusinessType);
		modelAndView.addObject("basicBanks",basicBanks);
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/prioritylist")
	public ModelAndView prioritylist() {
		List<BasicBank> basicBankList=basicBankService.getAll();
		TagDataInfo routeBusinessType=new TagDataInfo();
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		TagDataInfo basicBanks=new TagDataInfo();
		basicBanks.setJsonArray(basicBankList);
		AsileBankQueryInput query = newQuery(AsileBankQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AsileBank>> ret = asileBankService.list(query);
		if(0!=ret.getCode()){
			throw new RuntimeException(ret.getMessage());
		}
		Collections.sort(ret.getBussinessObj().getResult());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(PRIORITY_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.addObject("routeBusinessType",routeBusinessType);
		modelAndView.addObject("basicBanks",basicBanks);
		return modelAndView;
	}
	@RequestMapping(value="/degree")
	public void degree(String asileBankJson)
	{   
	List<AsileBank> list=(List<AsileBank>)JSONArray.parseArray(asileBankJson,AsileBank.class);
		for(AsileBank obj:list){
			AsileBank entity=new AsileBank();
			entity.setId(Long.valueOf(obj.getId()));
			entity.setPriority(Integer.valueOf(obj.getPriority()));
		}
		//整体更新优先级字段
		asileBankService.updateList(list);
	}
}
