
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.asile;

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
import com.ink.trade.api.platform.asile.model.base.AsileBankRuntimeEntity;
import com.ink.trade.api.platform.asile.model.base.AsileInfoEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBankRuntimeQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileBankRuntimeService;
import com.ink.trade.api.platform.asile.service.IAsileInfoService;
import com.ink.trade.api.platform.basic.model.base.BasicBankEntity;
import com.ink.trade.api.platform.basic.service.IBasicBankService;
import com.ink.trade.api.platform.common.CommonResult;



/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/AsileBankRuntime")
@Controller
public class AsileBankRuntimeController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/AsileBankRuntime/list";
	protected static final String CREATE_JSP = "/trade/AsileBankRuntime/create";
	protected static final String EDIT_JSP = "/trade/AsileBankRuntime/edit";
	protected static final String SHOW_JSP = "/trade/AsileBankRuntime/show";
	//redirect paths,startWith: !
	@Autowired
	private IBasicBankService basicBankService;
	@Autowired
	private IAsileInfoService asileInfoService;
	@Autowired
	private IAsileBankRuntimeService asileBankRuntimeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AsileBankRuntimeQueryInput query = newQuery(AsileBankRuntimeQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AsileBankRuntimeEntity>> ret = asileBankRuntimeService.list(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		List<AsileInfoEntity> asileList=asileInfoService.getAll();
		List<BasicBankEntity> basicBankList=basicBankService.getAll();
		TagDataInfo asileInfos=new TagDataInfo();
		TagDataInfo basicBanks=new TagDataInfo();
		basicBanks.setJsonArray(basicBankList);
		asileInfos.setJsonArray(asileList);
		ModelAndView model=new ModelAndView();
		model.addObject("asileInfos",asileInfos);
		model.addObject("basicBanks",basicBanks);
		model.setViewName(CREATE_JSP);
		return model;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AsileBankRuntimeEntity asileBankRuntime,long asileId,long bankId ) {
		AsileInfoEntity asileinfo=asileInfoService.getById(asileId).getBussinessObj();
		BasicBankEntity basicbank=basicBankService.getById(bankId).getBussinessObj();
		String userName = this.getLoginUser();
		asileBankRuntime.setCreaterName(userName);
		asileBankRuntime.setUpdaterName(userName);
		asileBankRuntime.setAsileCode(asileinfo.getAsileCode());
		asileBankRuntime.setAsileName(asileinfo.getAsileName());
		asileBankRuntime.setBankName(basicbank.getBankName());
		asileBankRuntime.setBankCode(basicbank.getBankShortName());
		asileBankRuntime.setCreateTime(new Date());
		asileBankRuntime.setUpdateTime(new Date());
		asileBankRuntimeService.save(asileBankRuntime);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AsileBankRuntimeEntity asileBankRuntime) {
		CommonResult<AsileBankRuntimeEntity> ret = asileBankRuntimeService.getById(asileBankRuntime.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AsileBankRuntimeEntity asileBankRuntime) {
		List<AsileInfoEntity> asileList=asileInfoService.getAll();
		List<BasicBankEntity> basicBankList=basicBankService.getAll();
		TagDataInfo asileInfos=new TagDataInfo();
		TagDataInfo basicBanks=new TagDataInfo();
		basicBanks.setJsonArray(basicBankList);
		asileInfos.setJsonArray(asileList);
		ModelAndView model=new ModelAndView();
		CommonResult<AsileBankRuntimeEntity> ret = asileBankRuntimeService.getById(asileBankRuntime.getId());
		model.addObject("asileInfos",asileInfos);
		model.addObject("basicBanks",basicBanks);
		model.addObject("model", ret.getBussinessObj());
		model.setViewName(EDIT_JSP);
		return model;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AsileBankRuntimeEntity asileBankRuntime,String bankCode,String asileCode) {
		List<AsileInfoEntity> asileinfos=asileInfoService.getAll();
		List<BasicBankEntity> basicbanks=basicBankService.getAll();
		String userName = this.getLoginUser();
		asileBankRuntime.setUpdaterName(userName);
		for(AsileInfoEntity asileInfo:asileinfos){
			if (asileInfo.getAsileCode().equals(asileCode)){
				asileBankRuntime.setAsileCode(asileInfo.getAsileCode());
				asileBankRuntime.setAsileName(asileInfo.getAsileName());
				break;
			}
		}
		for(BasicBankEntity basicbank:basicbanks){
			if(basicbank.getBankShortName().equals(bankCode)){
				asileBankRuntime.setBankName(basicbank.getBankName());
				asileBankRuntime.setBankCode(basicbank.getBankShortName());
				break;
			}
		}
		asileBankRuntime.setUpdateTime(new Date());
		asileBankRuntimeService.update(asileBankRuntime);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				asileBankRuntimeService.deleteById(Long.valueOf(id));
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
