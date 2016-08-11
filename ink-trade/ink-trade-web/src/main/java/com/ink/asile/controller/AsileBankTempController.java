
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.trade.api.enums.RouteBusinessType;
import com.ink.trade.api.platform.asile.model.base.AsileBankTempEntity;
import com.ink.trade.api.platform.asile.model.base.AsileInfoEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBankTempQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileBankTempService;
import com.ink.trade.api.platform.asile.service.IAsileInfoService;
import com.ink.trade.api.platform.basic.model.base.BasicBankEntity;
import com.ink.trade.api.platform.basic.service.IBasicBankService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AsileBankTemp")
@Controller
public class AsileBankTempController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/AsileBankTemp/list";
	protected static final String CREATE_JSP = "/AsileBankTemp/create";
	protected static final String EDIT_JSP = "/AsileBankTemp/edit";
	protected static final String SHOW_JSP = "/AsileBankTemp/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAsileBankTempService asileBankTempService;
	@Autowired
	private IAsileInfoService asileInfoService;
	@Autowired
	private IBasicBankService basicBankService;
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AsileBankTempQueryInput query = newQuery(AsileBankTempQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AsileBankTempEntity>> ret = asileBankTempService.list(query);
		TagDataInfo routeBusinessType=new TagDataInfo();
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.addObject("routeBusinessType",routeBusinessType);
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		List<AsileInfoEntity> asileList=asileInfoService.getAll();
		List<BasicBankEntity> basicBankList=basicBankService.getAll();
		TagDataInfo asileInfos=new TagDataInfo();
		TagDataInfo routeBusinessType=new TagDataInfo();
		TagDataInfo basicBanks=new TagDataInfo();
		basicBanks.setJsonArray(basicBankList);
		asileInfos.setJsonArray(asileList);
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		ModelAndView model=new ModelAndView();
		model.addObject("basicBanks",basicBanks);
		model.addObject("asileInfos",asileInfos);
		model.addObject("routeBusinessType",routeBusinessType);
		model.setViewName(CREATE_JSP);
		return model;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AsileBankTempEntity asileBankTemp,Long asileId,Long bankId) {
		AsileInfoEntity asileinfo=asileInfoService.getById(asileId).getBussinessObj();
		BasicBankEntity basicbank=basicBankService.getById(bankId).getBussinessObj();
		String userName = this.getLoginUser();
		asileBankTemp.setCreaterName(userName);
		asileBankTemp.setUpdaterName(userName);
		asileBankTemp.setAsileCode(asileinfo.getAsileCode());
		asileBankTemp.setAsileName(asileinfo.getAsileName());
		asileBankTemp.setBankName(basicbank.getBankName());
		asileBankTemp.setBankCode(basicbank.getBankShortName());
		asileBankTemp.setCreateTime(new Date());
		asileBankTemp.setUpdateTime(new Date());
		asileBankTempService.save(asileBankTemp);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AsileBankTempEntity asileBankTemp) {
		TagDataInfo routeBusinessType=new TagDataInfo();
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		CommonResult<AsileBankTempEntity> ret = asileBankTempService.getById(asileBankTemp.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		mav.addObject("routeBusinessType",routeBusinessType);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AsileBankTempEntity asileBankTemp) {
		List<AsileInfoEntity> asileList=asileInfoService.getAll();
		List<BasicBankEntity> basicBankList=basicBankService.getAll();
		TagDataInfo asileInfos=new TagDataInfo();
		TagDataInfo routeBusinessType=new TagDataInfo();
		TagDataInfo basicBanks=new TagDataInfo();
		basicBanks.setJsonArray(basicBankList);
		asileInfos.setJsonArray(asileList);
		routeBusinessType.setJsonArray(RouteBusinessType.getRouteBusinessTypeList());
		ModelAndView model=new ModelAndView();
		CommonResult<AsileBankTempEntity> ret = asileBankTempService.getById(asileBankTemp.getId());
		model.addObject("basicBanks",basicBanks);
		model.addObject("asileInfos",asileInfos);
		model.addObject("routeBusinessType",routeBusinessType);
		model.addObject("model", ret.getBussinessObj());
		model.setViewName(EDIT_JSP);
		return model;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AsileBankTempEntity asileBankTemp,String asileCode,String bankCode) {
		List<AsileInfoEntity> asileinfos=asileInfoService.getAll();
		List<BasicBankEntity> basicbanks=basicBankService.getAll();
		String userName = this.getLoginUser();
		asileBankTemp.setUpdaterName(userName);
		for(AsileInfoEntity asileInfo:asileinfos){
			if (asileInfo.getAsileCode().equals(asileCode)){
				asileBankTemp.setAsileCode(asileInfo.getAsileCode());
				asileBankTemp.setAsileName(asileInfo.getAsileName());
				break;
			}
		}
		for(BasicBankEntity basicbank:basicbanks){
			if(basicbank.getBankShortName().equals(bankCode)){
				asileBankTemp.setBankName(basicbank.getBankName());
				asileBankTemp.setBankCode(basicbank.getBankShortName());
				break;
			}
		}
		asileBankTemp.setUpdateTime(new Date());
		asileBankTempService.update(asileBankTemp);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				asileBankTempService.deleteById(Long.valueOf(id));
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
	public String getLoginUser(){
		HttpSession loginSession = super.getLoginSession();
		String userName = "";
		Assertion assertion = loginSession != null ? (Assertion) loginSession.getAttribute("ink_sso_user") : null;
		if(assertion!= null){
			userName = assertion.getPrincipal().getName();
		}
		return userName;
	}
}
