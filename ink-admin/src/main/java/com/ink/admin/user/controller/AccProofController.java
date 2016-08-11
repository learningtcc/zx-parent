
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.user.controller;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.core.po.AccProof;
import com.ink.user.core.query.AccProofQuery;
import com.ink.user.core.service.IAccProofManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("user/AccProof")
@Controller
public class AccProofController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = " last_update_time desc "; 
	
	//forward paths
	protected static final String LIST_JSP= "/user/AccProof/list";
	protected static final String CREATE_JSP = "/user/AccProof/create";
	protected static final String EDIT_JSP = "/user/AccProof/edit";
	protected static final String SHOW_JSP = "/user/AccProof/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private DubboBaseService userDubboBaseService;
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception {
		
		AccProofQuery query = newQuery(AccProofQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = userDubboBaseService.findPage("accProofManager",query);

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
	public String save(AccProof accProof) throws Exception {
		accProof.setId(Long.parseLong(idCodeGenerator.getId()));
		userDubboBaseService.save("accProofManager",accProof);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AccProof accProof) throws Exception {
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		if(accProof.getId() != null && accProof.getId() != 0){
			AccProof model = (AccProof) userDubboBaseService.getById("accProofManager",accProof.getId());
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccProof());
		}
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AccProof accProof) throws Exception {
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		if(accProof.getId() != null && accProof.getId() != 0){
			AccProof model = (AccProof) userDubboBaseService.getById("accProofManager",accProof.getId());
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccProof());
		}

		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AccProof accProof) throws Exception {
		userDubboBaseService.update("accProofManager",accProof);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) throws Exception {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				if(StringUtils.isNotBlank(id)){
					userDubboBaseService.removeById("accProofManager",Long.valueOf(id));
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
}
