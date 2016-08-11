
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.asile;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.trade.api.enums.AsileBussinessStatusEnum;
import com.ink.trade.api.platform.asile.model.base.AsileBusinessEntity;
import com.ink.trade.api.platform.asile.model.base.AsileInfoEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBusinessQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileBusinessService;
import com.ink.trade.api.platform.asile.service.IAsileInfoService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/AsileBusiness")
@Controller
public class AsileBusinessController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/AsileBusiness/list";
	protected static final String CREATE_JSP = "/trade/AsileBusiness/create";
	protected static final String EDIT_JSP = "/trade/AsileBusiness/edit";
	protected static final String SHOW_JSP = "/trade/AsileBusiness/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAsileBusinessService asileBusinessService;
	
	@Autowired
	private IAsileInfoService asileInfoService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AsileBusinessQueryInput query = newQuery(AsileBusinessQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AsileBusinessEntity>> ret = asileBusinessService.list(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.addObject("statusData", this.getStatusData());
		modelAndView.addObject("asileData", this.getAllAsileInfo());
		
		
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		ModelAndView modelAndView =new ModelAndView(CREATE_JSP);
		modelAndView.addObject("statusData", this.getStatusData());
		modelAndView.addObject("asileData", this.getAllAsileInfo());
		return modelAndView;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AsileBusinessEntity asileBusiness) {
		asileBusiness.setCreateTime(new Date());
		asileBusinessService.save(asileBusiness);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AsileBusinessEntity asileBusiness) {
		CommonResult<AsileBusinessEntity> ret = asileBusinessService.getById(asileBusiness.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		mav.addObject("statusData", this.getStatusData());
		mav.addObject("asileData", this.getAllAsileInfo());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AsileBusinessEntity asileBusiness) {
		CommonResult<AsileBusinessEntity> ret = asileBusinessService.getById(asileBusiness.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		mav.addObject("statusData", this.getStatusData());
		mav.addObject("asileData", this.getAllAsileInfo());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AsileBusinessEntity asileBusiness) {
		asileBusinessService.update(asileBusiness);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				asileBusinessService.deleteById(Integer.valueOf(id));
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	/**
	 * 
	 * @Description 获取状态集合并封装标签信息类中
	 * @author xuguoqi
	 * @date 2016年7月22日 上午10:49:36
	 * @return
	 */
	private TagDataInfo getStatusData(){
		TagDataInfo statusData = new TagDataInfo();
		statusData.setJsonArray(AsileBussinessStatusEnum.getAsileBussinessStatusEnum());
		return statusData;
		
	}
	
	/**
	 * 
	 * @Description 获取渠道信息集合并封装标签信息类中
	 * @author xuguoqi
	 * @date 2016年7月22日 上午10:49:40
	 * @return
	 */
	private TagDataInfo getAllAsileInfo(){
		TagDataInfo AsileInfoData = new TagDataInfo();
		List<AsileInfoEntity> list = asileInfoService.getAll();
		AsileInfoData.setJsonArray(list);
		return AsileInfoData;
		
	}
	
}
