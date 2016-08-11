
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.controller;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.enums.RouteBusinessType;
import com.ink.route.api.model.in.AsileBusinessSupportQueryInput;
import com.ink.route.api.model.po.AsileBusinessSupport;
import com.ink.route.api.model.po.AsileInfo;
import com.ink.route.api.service.platform.IAsileBusinessSupportService;
import com.ink.route.api.service.platform.IAsileInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AsileBusinessSupport")
@Controller
public class AsileBusinessSupportController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/AsileBusinessSupport/list";
	protected static final String CREATE_JSP = "/AsileBusinessSupport/create";
	protected static final String EDIT_JSP = "/AsileBusinessSupport/edit";
	protected static final String SHOW_JSP = "/AsileBusinessSupport/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAsileBusinessSupportService asileBusinessSupportService;
	
	@Autowired
	private IAsileInfoService asileInfoService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AsileBusinessSupportQueryInput query = newQuery(AsileBusinessSupportQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AsileBusinessSupport>> ret = asileBusinessSupportService.list(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		List<AsileBusinessSupport> absList = ret.getBussinessObj().getResult();
		TagDataInfo payTypeDate = this.getAllPayType(absList);
		modelAndView.addObject("payTypeDate", payTypeDate);
		modelAndView.addObject("asileData", this.getAllAsileInfo());
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView(CREATE_JSP);
		List<Map<String, String>> routeBusinessTypeList = RouteBusinessType.getRouteBusinessTypeList();
		TagDataInfo payTypeDate = new TagDataInfo();
		payTypeDate.setJsonArray(routeBusinessTypeList);
		modelAndView.addObject("payTypeDate", payTypeDate);
		modelAndView.addObject("asileData", this.getAllAsileInfo());
		return modelAndView;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AsileBusinessSupport asileBusinessSupport) {
		if(ArrayUtils.isEmpty(asileBusinessSupport.getPayTypeArr())){
			return writeAjaxResponse("支持方式不能为空");
		}
		int payType = RouteBusinessType.code2Binary(asileBusinessSupport.getPayTypeArr());
		asileBusinessSupport.setPayType(payType);
		asileBusinessSupport.setCreateTime(new Date());
		asileBusinessSupport.setLastupdateTime(new Date());
		CommonResult<Object> ret = asileBusinessSupportService.save(asileBusinessSupport);
		if(0!=ret.getCode()){
			return writeAjaxResponse(ret.getMessage());
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AsileBusinessSupport asileBusinessSupport) {
		CommonResult<AsileBusinessSupport> ret = asileBusinessSupportService.getById(asileBusinessSupport.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		List<AsileBusinessSupport> absList = new ArrayList<>();
		absList.add(ret.getBussinessObj());
		TagDataInfo payTypeDate = this.getAllPayType(absList);
		mav.addObject("payTypeDate", payTypeDate);
		mav.addObject("asileData", this.getAllAsileInfo());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AsileBusinessSupport asileBusinessSupport) {
		CommonResult<AsileBusinessSupport> ret = asileBusinessSupportService.getById(asileBusinessSupport.getId());
		AsileBusinessSupport supportEntity = ret.getBussinessObj();
		
		String[] binary2Code = RouteBusinessType.binary2Code(supportEntity.getPayType());
		supportEntity.setPayTypeArrString(StringUtils.join(binary2Code, ","));
		
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", supportEntity);
		List<Map<String, String>> routeBusinessTypeList = RouteBusinessType.getRouteBusinessTypeList();
		TagDataInfo payTypeDate = new TagDataInfo();
		payTypeDate.setJsonArray(routeBusinessTypeList);
		mav.addObject("payTypeDate", payTypeDate);
		mav.addObject("asileData", this.getAllAsileInfo());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AsileBusinessSupport asileBusinessSupport) {
		if(ArrayUtils.isEmpty(asileBusinessSupport.getPayTypeArr())){
			return writeAjaxResponse("支持方式不能为空");
		}
		int payType = RouteBusinessType.code2Binary(asileBusinessSupport.getPayTypeArr());
		asileBusinessSupport.setPayType(payType);
		asileBusinessSupportService.update(asileBusinessSupport);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				asileBusinessSupportService.deleteById(Integer.valueOf(id));
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	/**
	 * 
	 * @Description 获取所有支付渠道信息集合并封装都标签信息类中
	 * @author xuguoqi
	 * @date 2016年7月26日 下午1:52:04
	 * @return
	 */
	private TagDataInfo getAllAsileInfo(){
		TagDataInfo AsileInfoData = new TagDataInfo();
		List<AsileInfo> list = asileInfoService.getAll();
		AsileInfoData.setJsonArray(list);
		return AsileInfoData;
	}
	
	/**
	 * 
	 * @Description 获取支付方式含义
	 * @author xuguoqi
	 * @date 2016年7月22日 下午4:06:39
	 * @param absList
	 * @return
	 */
	private TagDataInfo getAllPayType(List<AsileBusinessSupport> absList){
		TagDataInfo payTypeInfo = new TagDataInfo();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(CollectionUtils.isEmpty(absList)){
			return payTypeInfo;
		}
		for (AsileBusinessSupport entity : absList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String[] binary2Code = RouteBusinessType.binary2CodeGetValue(entity.getPayType());
			map.put("code", entity.getPayType());
			map.put("value", StringUtils.join(binary2Code, ","));
			list.add(map);
		}
		payTypeInfo.setJsonArray(list);
		return payTypeInfo;
		
	}
}
