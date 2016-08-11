
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.controller;


import com.yinker.base.BaseController;
import com.yinker.base.page.Page;
import com.yinker.tfs.core.po.SystermSource;
import com.yinker.tfs.core.po.TfsFileName;
import com.yinker.tfs.core.query.SystermSourceQuery;
import com.yinker.tfs.core.query.TfsFileNameQuery;
import com.yinker.tfs.core.service.ISystermSourceManager;
import com.yinker.tfs.core.service.ITfsFileNameManager;
import com.yinker.tfs.core.service.TfsService;
import com.yinker.tfs.core.utils.InitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("tfsManage")
@Controller
public class TfsFileNameController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "tfsManage/list";
	protected static final String CREATE_JSP = "tfsManage/create";
	protected static final String EDIT_JSP = "tfsManage/edit";
	protected static final String SHOW_JSP = "tfsManage/show";

	protected static final String SUCCESS_JSP= "success";
	//redirect paths,startWith: !
	
	@Autowired
	private ITfsFileNameManager tfsFileNameManager;

	@Autowired
	private ISystermSourceManager systermSourceManager;

	@Autowired
	private TfsService tfsService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		TfsFileNameQuery query = newQuery(TfsFileNameQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = tfsFileNameManager.findPage(query);
//		List<SystermSource> sources = systermSourceManager.find(new SystermSourceQuery());

		Map<String,String> sourcesMap = InitParam.getSourceMap();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		modelAndView.addObject("sourcesMap",sourcesMap);
		
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
	public String save(TfsFileName tfsFileName) {
		tfsFileNameManager.save(tfsFileName);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(TfsFileName tfsFileName) {
		TfsFileName model = tfsFileNameManager.getById(tfsFileName.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(TfsFileName tfsFileName) {
		TfsFileName model = tfsFileNameManager.getById(tfsFileName.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(TfsFileName tfsFileName) {
		tfsFileNameManager.update(tfsFileName);
		return writeAjaxResponse("1");
	}

	/**删除对象(逻辑删除)*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				tfsService.deleteTfsFile(Long.parseLong(id));
				//tfsFileNameManager.removeById(Integer.valueOf(id));
			}
		}

		return writeAjaxResponse("1");
	}

	@RequestMapping("/toUpload" )
	public String toUpload() {
		return "/upload";
	}

	/**
	 * 通过字节流上传文件到tfs
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException ,Exception{
		//创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		Map<String,String> tfsMap = new HashMap<String,String>();
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if(file != null){
					//取得当前上传文件的文件名称
					String uploadFileName = file.getOriginalFilename();
					//如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if(uploadFileName.trim() !=""){
//						//发送inputStreame1 = tfsOperator.saveByteToTfsByTime(uploadFileName,file.getBytes(),"TfsDemo","demo","admin");
						String tfsName = tfsService.saveFile(file.getInputStream(),uploadFileName,"manage","manage","admin");
						tfsMap.put(tfsName,uploadFileName);
					}
				}
			}
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(SUCCESS_JSP);
		modelAndView.addObject("tfs",tfsMap);
		return modelAndView;
	}
}
