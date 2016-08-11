package com.ink.msgcenter.cache;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.cache.object.TemplateCache;
import com.ink.msgcenter.core.po.EmailTemplate;
import com.ink.msgcenter.core.po.SmsTemplate;
import com.ink.msgcenter.core.service.IEmailTemplateManager;
import com.ink.msgcenter.core.service.ISmsTemplateManager;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component("templateUtil")
public class TemplateUtil {
	
	@Autowired
	private Yedis yedis;
	
	@Autowired
	private IEmailTemplateManager emailTemplateManager;
	@Autowired
	private ISmsTemplateManager smsTemplateManager;
	
	
	public TemplateCache getTemplateInfo(String tempId, String tempType){
		
		final String tempIdFinal = tempId;
		final String tempTypeFianl = tempType;
		
		CacheObject co = yedis.getObject(CacheConstant.MSG_TEMPLATE, tempId, TemplateCache.class, new Reader<Object>() {
			
			@Override
			public TemplateCache readerFromDatabase() {

				TemplateCache templateCache = null;
				
				if("2".equals(tempTypeFianl)){
					EmailTemplate et = emailTemplateManager.getById(Long.valueOf(tempIdFinal));
					if(et != null && et.getStatus().equalsIgnoreCase("0")){
						templateCache = new TemplateCache();
						templateCache.setId(et.getId());
						templateCache.setMerctCode(et.getMerctCode());
						templateCache.setMerctId(et.getMerctCode());
						templateCache.setParseMethod(et.getParseMethod());
						templateCache.setSubject(et.getMailSubject());
						templateCache.setTempContent(et.getTempContent());
						templateCache.setTempName(et.getTempName());
					}
				}else{
					SmsTemplate st = smsTemplateManager.getById(Long.valueOf(tempIdFinal));
					if(st != null && st.getStatus().equalsIgnoreCase("0")){
						templateCache = new TemplateCache();
						templateCache.setId(st.getId());
						templateCache.setMerctCode(st.getMerctCode());
						templateCache.setMerctId(st.getMerctCode());
						templateCache.setParseMethod(st.getParseMethod());
						templateCache.setTempContent(st.getTempContent());
						templateCache.setTempName(st.getTempName());
					}
				}
				
				return templateCache;
			}
			
		}); 
		
		return (TemplateCache)co.getValue();
		
	}
	
	/**
	 * 生成模板文件内容
	 */
	public String generateContent(TemplateCache template, String json){
		
		if("1".equalsIgnoreCase(template.getParseMethod())){//顺序解析
			return renderContentForSeq(template, json);
		}else{//freemarker解析
			return renderContentForFreeMarker(template, json);
		}
	}
	
	/**
	 * 顺序解析
	 * @param tc
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	private String renderContentForSeq(TemplateCache tc,String json){
		
		String templateStr = tc.getTempContent();
		try{
			if(StringUtils.isBlank(templateStr)) {
				return "";
			}
			List<String> paramList = JSON.parseArray(json, String.class);

			Pattern pattern = Pattern.compile("\\$\\{");
			Matcher matcher =  pattern.matcher(templateStr);
			int count = 0;
			char[] chars = templateStr.toCharArray();
			while (matcher.find()){
				int start = matcher.start();
				boolean isEnd = false;
				int mark;
				StringBuilder sb = new StringBuilder();
				for(mark = start + 2; mark < chars.length;mark++){

					if (chars[mark] == '}'){
						isEnd = true;
						break;
					}
					sb.append(chars[mark]);
				}
				if (isEnd){
					String regex = "\\$\\{"+sb.toString()+"\\}";
					templateStr = templateStr.replaceFirst(regex, paramList.get(count++));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			templateStr = "";
		}

		return templateStr;
	}
	
	/**
	 * Freemarker解析
	 * @param tc
	 * @param json
	 * @return
	 */
	private String renderContentForFreeMarker(TemplateCache tc,String json){
		String result = "";
		FreeMarkerConfigurer templateEngine = (FreeMarkerConfigurer)SpringApplicationContext.getBean("templateEngine");
		String templateStr = tc.getTempContent();
		
		if(StringUtils.isBlank(templateStr)) {
			return "";
		}
		
		try {
			templateStr = StringEscapeUtils.unescapeHtml(templateStr);
			JSONObject model = JSON.parseObject(json);
			Template template = new Template("name", new StringReader(templateStr), templateEngine.getConfiguration());
			
			result = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}