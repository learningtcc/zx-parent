
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.acc.controller.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yinker.base.BaseController;
import com.yinker.base.page.Page;
import com.yinker.base.utils.IdCodeGenerator;
import com.yinker.user.core.query.AccProofQuery;
import com.yinker.user.core.po.AccProof;
import com.yinker.user.core.service.IAccProofManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AccProof")
@Controller
public class AccProofController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = " last_update_time desc "; 
	
	//forward paths
	protected static final String LIST_JSP= "/AccProof/list";
	protected static final String CREATE_JSP = "/AccProof/create";
	protected static final String EDIT_JSP = "/AccProof/edit";
	protected static final String SHOW_JSP = "/AccProof/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAccProofManager accProofManager;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AccProofQuery query = newQuery(AccProofQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = accProofManager.findPage(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AccProof accProof) {
		accProof.setId(Long.parseLong(idCodeGenerator.getId()));
		accProofManager.save(accProof);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccProof accProof) {
		AccProof model = accProofManager.getById(accProof.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccProof accProof) {
		AccProof model = accProofManager.getById(accProof.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccProof accProof) {
		accProofManager.update(accProof);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				accProofManager.removeById(Long.valueOf(id));
			}
		}
		
		return writeAjaxResponse("1");
	}
}
