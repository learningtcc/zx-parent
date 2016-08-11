
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.trade.api.enums.AuthOrderStatus;
import com.ink.trade.api.platform.asile.model.base.AsileInfoEntity;
import com.ink.trade.api.platform.asile.service.IAsileInfoService;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.AuthOrderEntity;
import com.ink.trade.api.platform.trade.model.in.AuthOrderQueryInput;
import com.ink.trade.api.platform.trade.service.IAuthOrderService;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/AuthOrder")
@Controller
public class AuthOrderController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/AuthOrder/list";
	protected static final String CREATE_JSP = "/trade/AuthOrder/create";
	protected static final String EDIT_JSP = "/trade/AuthOrder/edit";
	protected static final String SHOW_JSP = "/trade/AuthOrder/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAuthOrderService authOrderService;
	
	@Autowired
	private IAsileInfoService asileInfoService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AuthOrderQueryInput query = newQuery(AuthOrderQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AuthOrderEntity>> ret = authOrderService.findPage(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.addObject("statusData", this.getStatusData());
		modelAndView.addObject("asileData", this.getAllAsileInfo());
		
		return modelAndView;
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
	
	private TagDataInfo getStatusData(){
		TagDataInfo statusData = new TagDataInfo();
		statusData.setJsonArray(AuthOrderStatus.getAuthOrderEnum());
		return statusData;
		
	}
	
	
//	/** 进入新增页面*/
//	@RequestMapping(value="/create")
//	public ModelAndView create() {
//		return new ModelAndView(CREATE_JSP);
//	}
//	
//	/** 保存新增对象 */
//	@RequestMapping(value="/save")
//	@ResponseBody
//	public String save(AuthOrder authOrder) {
//		authOrderManager.save(authOrder);
//		return writeAjaxResponse("1");
//	}
//	
//	/** 查看对象*/
//	@RequestMapping(value="/show")
//	public ModelAndView show(AuthOrder authOrder) {
//		AuthOrder model = authOrderManager.getById(authOrder.getId());
//		ModelAndView mav = new ModelAndView(SHOW_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**进入更新页面*/
//	@RequestMapping(value="/edit")
//	public ModelAndView edit(AuthOrder authOrder) {
//		AuthOrder model = authOrderManager.getById(authOrder.getId());
//		ModelAndView mav = new ModelAndView(EDIT_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**保存更新对象*/
//	@RequestMapping(value="/update")
//	@ResponseBody
//	public String update(AuthOrder authOrder) {
//		authOrderManager.update(authOrder);
//		return writeAjaxResponse("1");
//	}
//	
//	
//	/**删除对象*/
//	@RequestMapping(value="/delete")
//	@ResponseBody
//	public String delete(String items) {
//		if(items!=null){
//			String[] idArray = items.split(",");
//			for(String id: idArray){
//				authOrderManager.removeById(Integer.valueOf(id));
//			}
//		}
//		
//		return writeAjaxResponse("1");
//	}
}
