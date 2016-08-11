package com.ink.admin.msgcenter.controller;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.alibaba.fastjson.JSON;
import com.ink.admin.msgcenter.util.TemplateCache;
import com.ink.admin.msgcenter.util.TemplateUtil;
import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.redis.client.Yedis;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.api.constants.CommonConstant;
import com.ink.msgcenter.core.annotation.FieldMeta;
import com.ink.msgcenter.core.po.EmailTemplate;
import com.ink.msgcenter.core.po.EmailTemplateLog;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.query.EmailTemplateLogQuery;
import com.ink.msgcenter.core.query.EmailTemplateQuery;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("msgcenter/EmailTemplate")
@Controller
public class EmailTemplateController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "create_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/template/emailTemplate/list";
	protected static final String CREATE_JSP = "/msgcenter/template/emailTemplate/create";
	protected static final String EDIT_JSP = "/msgcenter/template/emailTemplate/edit";
	protected static final String SHOW_JSP = "/msgcenter/template/emailTemplate/show";
	protected static final String PREVIEW_JSP = "/msgcenter/template/emailTemplate/preview";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	@Autowired
	private Yedis yedis;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		EmailTemplateQuery query = newQuery(EmailTemplateQuery.class,DEFAULT_SORT_COLUMNS);
		if(StringUtils.isBlank(query.getStatus())){
			query.setStatus(CommonConstant.EMAILTEMP_STATUS_NORMAL);
		}
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("emailTemplateManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public String save(EmailTemplate emailTemplate, String editorValue, HttpSession session) {

		try {
			emailTemplate.setCreatorId(String.valueOf(session.getAttribute("SESSION_USERID")));
			emailTemplate.setCreatorName(String.valueOf(session.getAttribute("SESSION_USERNAME")));
			emailTemplate.setTempContent(editorValue);
			emailTemplate.setCreateTime(new Date());
			//商户Code
			MerchantInfo info  = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",emailTemplate.getMerctId());
			if(info != null){
				emailTemplate.setMerctCode(info.getSn());
			}
			msgcenterDubboBaseService.save("emailTemplateManager",emailTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(EmailTemplate emailTemplate) {

		ModelAndView mav = new ModelAndView(SHOW_JSP);
		try {
			EmailTemplate model = (EmailTemplate) msgcenterDubboBaseService.getById("emailTemplateManager",emailTemplate.getId());

			EmailTemplateLog log = (EmailTemplateLog) msgcenterDubboBaseService.executeDynamicMethod("emailTemplateLogManager","getLastUpdateContent",model.getId());

			EmailTemplateLogQuery query = newQuery(EmailTemplateLogQuery.class,"operate_time desc");
			query.setId(null);
			query.setTemplateId(model.getId());
			Page page = msgcenterDubboBaseService.findPage("emailTemplateLogManager",query);

			mav.addObject("page", page);
			mav.addObject("model", model);
			mav.addObject("log", log);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mav;
	}
	
	
	@RequestMapping("showLog")
	public ModelAndView showLog(String id){

		ModelAndView mav = new ModelAndView("/msgcenter/template/emailTemplate/showLog");
		try {
			EmailTemplate model = (EmailTemplate) msgcenterDubboBaseService.getById("emailTemplateManager",Long.valueOf(id));

			EmailTemplateLog log = (EmailTemplateLog) msgcenterDubboBaseService.executeDynamicMethod("emailTemplateLogManager","getLastUpdateContent",model.getId());

			mav.addObject("model", model);
			mav.addObject("log",log);
			mav.addObject("showFlag","0");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mav;
	}
	
	@RequestMapping("showHistoryLog")
	public ModelAndView showHistoryLog(String id){
		ModelAndView mav = new ModelAndView("/msgcenter/template/emailTemplate/showLog");
		try{
			EmailTemplateLog log = (EmailTemplateLog) msgcenterDubboBaseService.getById("emailTemplateLogManager",Long.valueOf(id));
			EmailTemplateLog lastLog = (EmailTemplateLog) msgcenterDubboBaseService.executeDynamicMethod("emailTemplateLogManager","getPreviousContent",log);

			if(lastLog != null){
				mav.addObject("log", lastLog);
			}else{
				mav.addObject("log", new EmailTemplateLog());
			}
			mav.addObject("model",log);
			mav.addObject("showFlag","1");
		}catch (Exception e){
			e.printStackTrace();		}
		return mav;
	}
	/** 预览对象*/
	@RequestMapping(value="/preview")
	public ModelAndView preview(EmailTemplate emailTemplate) {
		ModelAndView mav = new ModelAndView(PREVIEW_JSP);

		EmailTemplate model = null;
		try {
			model = (EmailTemplate) msgcenterDubboBaseService.getById("emailTemplateManager",emailTemplate.getId());
			model = getTemplateInfo(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("model", model);
		return mav;
	}

	/** 预览对象*/
	@RequestMapping(value="/findEmailTemplate")
	@ResponseBody
	public EmailTemplate findEmailTemplate(EmailTemplate emailTemplate) {
		EmailTemplate model = null;
		try {
			model = (EmailTemplate) msgcenterDubboBaseService.getById("emailTemplateManager",emailTemplate.getId());
			model = getTemplateInfo(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	/** 预览对象*/
	@RequestMapping(value="/findTemplateText")
	@ResponseBody
	public EmailTemplate findTemplateText(EmailTemplate emailTemplate) {
		EmailTemplate model = null;
		try {
			model = (EmailTemplate) msgcenterDubboBaseService.getById("emailTemplateManager",emailTemplate.getId());
			model.setTempParam(emailTemplate.getTempParam());
			emailTemplate = getTemplateInfo(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return emailTemplate;
	}

	/**
	 * 获取模版展示内容
	 * @param model
	 * @return
	 */
	private EmailTemplate getTemplateInfo(EmailTemplate model) {
		if(model.getParseMethod().equals("1")){
			List<String> params = new ArrayList<String>();
			if (StringUtils.isNotBlank(model.getTempParam())){
				params = (List<String>) JSON.parse(model.getTempParam());
			}
			TemplateCache template = new TemplateCache();
			template.setTempContent(model.getTempContent());
			model.setTempContent(TemplateUtil.renderContentForSeq(template, params));
			model.setTempParam(JSON.toJSONString(params));
		}else{
			TemplateCache template = new TemplateCache();
			String test = StringEscapeUtils.unescapeHtml(model.getTempContent());
			test = test.replaceAll("<p>", "");
			test = test.replaceAll("</p>", "");
			StringBuilder str = new StringBuilder();
			if(model!=null && test.length() > 0){
				boolean before = false;
				boolean after = false;
				for(char a : test.toCharArray()){

					if(a == '$'){
						before = true;
						continue;
					}else if(a == '{' && before){
						str.append("<font color='red'>${");
						after = true;
					}else if(a == '}' && after == true){
						str.append("}</font>");
						after = false;
					}else{
						str.append(a);
						before = false;
					}
				}
			}
			template.setTempContent(str.toString());
			model.setTempContent(TemplateUtil.renderContentForFreeMarker(template, model.getTempParam()));
		}
		return model;
	}

	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(EmailTemplate emailTemplate) {
		EmailTemplate model = null;
		try {
			model = (EmailTemplate) msgcenterDubboBaseService.getById("emailTemplateManager",emailTemplate.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(EmailTemplate emailTemplate, String editorValue, HttpSession session) {

		try{
			//通过商户ID查询Code保存
			MerchantInfo info = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",emailTemplate.getMerctId());
			if(info!=null){
				emailTemplate.setMerctCode(info.getSn());
			}
			//查询数据库中进行对比
			EmailTemplate email = (EmailTemplate) msgcenterDubboBaseService.getById("emailTemplateManager",emailTemplate.getId());
			emailTemplate.setTempContent(editorValue);
			//修改过的字段(待定)
			String logRemark = contrastObj(emailTemplate,email);
			emailTemplate.setTempContent(editorValue);

			EmailTemplateLog emailLog = new EmailTemplateLog();
			BeanUtils.copyProperties(email, emailLog);
			emailLog.setId(null);
			emailLog.setTemplateId(email.getId());
			emailLog.setLogRemark(logRemark);
			emailLog.setOperatorId("");
			emailLog.setOperatorName("");
			emailLog.setOperateTime(new Date());
			emailLog.setOperatorId(String.valueOf(session.getAttribute("SESSION_USERID")));
			emailLog.setOperatorName(String.valueOf(session.getAttribute("SESSION_USERNAME")));
			msgcenterDubboBaseService.save("emailTemplateLogManager",emailLog);

			msgcenterDubboBaseService.update("emailTemplateManager",emailTemplate);
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}finally {
			//更新缓存
			yedis.deleteObject(CacheConstant.MSG_TEMPLATE, ""+emailTemplate.getId());
		}

		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				try{
					EmailTemplate temp = (EmailTemplate) msgcenterDubboBaseService.getById("emailTemplateManager",Long.valueOf(id));
					temp.setStatus(CommonConstant.EMAILTEMP_STATUS_DELETE);
					//更新缓存
					yedis.deleteObject(CacheConstant.MSG_TEMPLATE, ""+temp.getId());
					msgcenterDubboBaseService.update("emailTemplateManager",temp);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	public static String contrastObj(Object obj1, Object obj2) {
		List<String> list = new ArrayList<String>();
		try {
			Class clazz = obj1.getClass();
			Field[] fields = obj1.getClass().getDeclaredFields();
			for (Field field : fields) {
				if(field.getModifiers() == 2){
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
							clazz);
					Method getMethod = pd.getReadMethod();
					Object o1 = getMethod.invoke(obj1);
					Object o2 = getMethod.invoke(obj2);
					if(o1 == null && o2 == null){
						continue;
					}
					if(o1 == null || o2 == null){
						list.add(field.getName());
						continue;
					}
					if(field.getName().equals("tempContent")){
						o1 = html(o1.toString());
						o2 = html(o2.toString());
					}
					if (!o1.toString().equals(o2.toString())) {
						 //获取字段中包含fieldMeta的注解  
		                FieldMeta meta = field.getAnnotation(FieldMeta.class);  
		                if(meta!=null){  
		                	list.add(meta.value());
		                }  
					}
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuilder res = new StringBuilder();
		for(String str : list){
			if(res.length() < 1){
				res.append(str);
			}else{
				res.append(",");
				res.append(str);
			}
		}
		return res.toString();
	}
	
	public static String html(String content) {
		if (content == null)
			return "";
		String html = content;
		html = StringUtils.replace(html, "<p>", " ");
		html = StringUtils.replace(html, "</p>", " ");
		html = StringUtils.trim(html);
		return html;
	}

	/**
	 * ajax 根据商户号获取模版信息
	 * @return json 数据
	 */
	@ResponseBody
	@RequestMapping(value="/listForMerctCode",method = RequestMethod.GET)
	public List listForMerctCode(String merctCode) {
		List<Map> resultList = null;
		try{
			EmailTemplateQuery emailTemplateQuery = new EmailTemplateQuery();
			emailTemplateQuery.setMerctCode(merctCode);
			emailTemplateQuery.setStatus("0");
			List<EmailTemplate> list = msgcenterDubboBaseService.find("emailTemplateManager",emailTemplateQuery);
			resultList = new ArrayList<Map>();
			for (EmailTemplate emailTemplate : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("code",emailTemplate.getId());
				map.put("name",emailTemplate.getTempName());
				resultList.add(map);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return resultList;
	}
}
