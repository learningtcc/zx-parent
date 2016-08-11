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
import com.ink.msgcenter.core.po.EmailTemplateLog;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.po.SmsTemplate;
import com.ink.msgcenter.core.po.SmsTemplateLog;
import com.ink.msgcenter.core.query.SmsTemplateLogQuery;
import com.ink.msgcenter.core.query.SmsTemplateQuery;
import org.apache.commons.lang3.StringUtils;
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
 
@RequestMapping("msgcenter/SmsTemplate")
@Controller
public class SmsTemplateController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "create_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/template/smsTemplate/list";
	protected static final String CREATE_JSP = "/msgcenter/template/smsTemplate/create";
	protected static final String EDIT_JSP = "/msgcenter/template/smsTemplate/edit";
	protected static final String SHOW_JSP = "/msgcenter/template/smsTemplate/show";
	protected static final String PREVIEW_JSP = "/msgcenter/template/smsTemplate/preview";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	@Autowired
	private Yedis yedis;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		SmsTemplateQuery query = newQuery(SmsTemplateQuery.class,DEFAULT_SORT_COLUMNS);
		if(StringUtils.isBlank(query.getStatus())){
			query.setStatus(CommonConstant.SMSTEMP_STATUS_NORMAL);
		}
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("smsTemplateManager",query);
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
	public String save(SmsTemplate smsTemplate, HttpSession session) {
		smsTemplate.setCreateTime(new Date());
		MerchantInfo info = null;
		try {
			info = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",smsTemplate.getMerctId());
			if(info != null){
				smsTemplate.setMerctCode(info.getSn());
			}
			smsTemplate.setCreatorId(String.valueOf(session.getAttribute("SESSION_USERID")));
			smsTemplate.setCreatorName(String.valueOf(session.getAttribute("SESSION_USERNAME")));
			msgcenterDubboBaseService.save("smsTemplateManager",smsTemplate);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}

		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(SmsTemplate smsTemplate) {
		ModelAndView mav = new ModelAndView(SHOW_JSP);

		try{
			SmsTemplate model = (SmsTemplate) msgcenterDubboBaseService.getById("smsTemplateManager",smsTemplate.getId());
			SmsTemplateLog log = (SmsTemplateLog) msgcenterDubboBaseService.executeDynamicMethod("smsTemplateLogManager","getLastUpdateContent",model.getId());
			SmsTemplateLogQuery query = newQuery(SmsTemplateLogQuery.class,"operate_time desc");
			query.setId(null);
			query.setTemplateId(model.getId());
			Page page = msgcenterDubboBaseService.findPage("smsTemplateLogManager",query);

			mav.addObject("model", model);
			mav.addObject("page", page);
			mav.addObject("log",log);
		}catch (Exception e){
			e.printStackTrace();
		}
		return mav;
	}
	
	
	/** 查看对象*/
	@RequestMapping(value="/showLog")
	public ModelAndView show(String id) {
		ModelAndView mav = new ModelAndView("/msgcenter/template/smsTemplate/showLog");
		try{
			SmsTemplate model = (SmsTemplate) msgcenterDubboBaseService.getById("smsTemplateManager",Long.valueOf(id));
			SmsTemplateLog log = (SmsTemplateLog) msgcenterDubboBaseService.executeDynamicMethod("smsTemplateLogManager","getLastUpdateContent",model.getId());

			mav.addObject("model", model);
			mav.addObject("log",log);
			mav.addObject("showFlag","0");
		}catch (Exception e){
			e.printStackTrace();
		}

		return mav;
	}
	
	@RequestMapping("/showHistoryLog")
	public ModelAndView showHistoryLog(String id){
		ModelAndView mav = new ModelAndView("/msgcenter/template/smsTemplate/showLog");
		try{
			SmsTemplateLog log = (SmsTemplateLog) msgcenterDubboBaseService.getById("smsTemplateLogManager",Long.valueOf(id));
			SmsTemplateLog lastLog = (SmsTemplateLog) msgcenterDubboBaseService.executeDynamicMethod("smsTemplateLogManager","getPreviousContent",log);

			if(lastLog != null){
				mav.addObject("log", lastLog);
			}else{
				mav.addObject("log", new EmailTemplateLog());
			}
			mav.addObject("model",log);
			mav.addObject("showFlag","1");
		}catch (Exception e){
			e.printStackTrace();
		}
		return mav;
	}
	
	/** 预览对象*/
	@RequestMapping(value="/preview")
	public ModelAndView preview(SmsTemplate smsTemplate) {
		SmsTemplate model = null;
		try {
			model = (SmsTemplate) msgcenterDubboBaseService.getById("smsTemplateManager",smsTemplate.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(PREVIEW_JSP);

		model = getTemplateInfo(model);

		mav.addObject("model", model);
		return mav;
	}

	/** 预览对象*/
	@RequestMapping(value="/findEmailTemplate")
	@ResponseBody
	public SmsTemplate findEmailTemplate(SmsTemplate emailTemplate) {
		SmsTemplate model = null;
		try {
			model = (SmsTemplate) msgcenterDubboBaseService.getById("smsTemplateManager",emailTemplate.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model = getTemplateInfo(model);

		return model;
	}
	/** 预览对象*/
	@RequestMapping(value="/findTemplateText")
	@ResponseBody
	public SmsTemplate findTemplateText(SmsTemplate emailTemplate) {
		SmsTemplate model = null;
		try {
			model = (SmsTemplate) msgcenterDubboBaseService.getById("smsTemplateManager",emailTemplate.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setTempParam(emailTemplate.getTempParam());
		emailTemplate = getTemplateInfo(model);
		return emailTemplate;
	}
	/**
	 * 获取模版信息
	 * @param model
	 * @return
	 */
	private SmsTemplate getTemplateInfo(SmsTemplate model) {
		try{
			if(model.getParseMethod().equals("1")){
				List<String> params = new ArrayList<String>();
				if (StringUtils.isNotBlank(model.getTempParam())){

					params = (List<String>) JSON.parse(model.getTempParam());
					TemplateCache template = new TemplateCache();
					template.setTempContent(model.getTempContent());
					model.setTempContent(TemplateUtil.renderContentForSeq(template, params));
					model.setTempParam(JSON.toJSONString(params));
				}
			}else{
				TemplateCache template = new TemplateCache();
				StringBuilder str = new StringBuilder();
				if(model!=null && model.getTempContent().length() > 0){
					boolean before = false;
					boolean after = false;
					for(char a : model.getTempContent().toCharArray()){
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
				String result = TemplateUtil.renderContentForFreeMarker(template, model.getTempParam());
				if (StringUtils.isNotBlank(result)){
					model.setTempContent(result);
				}else{
					model.setTempContent(model.getTempContent());
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return model;
	}

	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(SmsTemplate smsTemplate) {
		SmsTemplate model = null;
		try {
			model = (SmsTemplate) msgcenterDubboBaseService.getById("smsTemplateManager",smsTemplate.getId());
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
	public String update(SmsTemplate smsTemplate, HttpSession session) {
		
		try{
			MerchantInfo info = (MerchantInfo) msgcenterDubboBaseService.getById("merchantInfoManager",smsTemplate.getMerctId());
			if(info!=null){
				smsTemplate.setMerctCode(info.getSn());
			}
			SmsTemplate sms = (SmsTemplate) msgcenterDubboBaseService.getById("smsTemplateManager",smsTemplate.getId());
			String logRemark = contrastObj(sms,smsTemplate);

			SmsTemplateLog smsLog = new SmsTemplateLog();
			BeanUtils.copyProperties(sms, smsLog);
			smsLog.setId(null);
			smsLog.setTemplateId(sms.getId());
			smsLog.setLogRemark(logRemark);
			smsLog.setOperateTime(new Date());
			smsLog.setOperatorId(String.valueOf(session.getAttribute("SESSION_USERID")));
			smsLog.setOperatorName(String.valueOf(session.getAttribute("SESSION_USERNAME")));
			msgcenterDubboBaseService.save("smsTemplateLogManager",smsLog);

			msgcenterDubboBaseService.update("smsTemplateManager",smsTemplate);
		}catch (Exception e){
			e.printStackTrace();
			return writeAjaxResponse("0");
		}finally {
		//删除缓存
			yedis.deleteObject(CacheConstant.MSG_TEMPLATE,String.valueOf(smsTemplate.getId()));
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
					SmsTemplate sms = (SmsTemplate) msgcenterDubboBaseService.getById("smsTemplateManager",Long.valueOf(id));
					//删除缓存
					yedis.deleteObject(CacheConstant.MSG_TEMPLATE,String.valueOf(sms.getId()));
					sms.setStatus(CommonConstant.SMSTEMP_STATUS_DELETE);
					msgcenterDubboBaseService.update("smsTemplateManager",sms);
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
				System.out.println(field.getModifiers());
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
					if (!o1.toString().equals(o2.toString())) {
						if (!o1.toString().equals(o2.toString())) {
							 //获取字段中包含fieldMeta的注解  
			                FieldMeta meta = field.getAnnotation(FieldMeta.class);  
			                if(meta!=null){  
			                	list.add(meta.value());
			                }  
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

	/**
	 * ajax 根据商户号获取模版信息
	 * @return json 数据
	 */
	@ResponseBody
	@RequestMapping(value="/listForMerctCode",method = RequestMethod.GET)
	public List listForMerctCode(String merctCode) {
		List<Map> resultList = new ArrayList<Map>();
		try{
			SmsTemplateQuery smsTemplateQuery = new SmsTemplateQuery();
			smsTemplateQuery.setMerctCode(merctCode);
			smsTemplateQuery.setStatus("0");
			List<SmsTemplate> list = msgcenterDubboBaseService.find("smsTemplateManager",smsTemplateQuery);

			for (SmsTemplate smsTemplate : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("code",smsTemplate.getId());
				map.put("name",smsTemplate.getTempName());
				resultList.add(map);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return resultList;
	}
}
