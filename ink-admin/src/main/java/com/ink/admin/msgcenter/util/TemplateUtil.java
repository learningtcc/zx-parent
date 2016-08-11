package com.ink.admin.msgcenter.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.utils.context.SpringApplicationContext;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository("templateUtil")
public class TemplateUtil {

	/**
	 * 顺序解析
	 * @param tc
	 * @param paramList
     * @return
     */
	public static String renderContentForSeq(TemplateCache tc,List<String> paramList){
		
		String templateStr = tc.getTempContent();
		
		if(StringUtils.isBlank(templateStr)) {
			return "";
		}
		boolean isHaveParam = false;
		String templateView = templateStr;
		if (!(null == paramList || paramList.isEmpty())){
			isHaveParam = true;
		}else if (null == paramList){
			paramList = new ArrayList<String>();
		}

		try{
			if(StringUtils.isBlank(templateStr)) {
				return "";
			}
			Pattern pattern = Pattern.compile("\\$\\{");
			Matcher matcher =  pattern.matcher(templateStr);
			int count = 0;
			char[] chars = templateStr.toCharArray();
			while (matcher.find()){
				int start = matcher.start();
				boolean isEnd = false;
				int mark;
				StringBuilder sb = new StringBuilder();
				for(mark = start+2; mark < chars.length;mark++){
					if (chars[mark] == '}'){
						isEnd = true;
						break;
					}
					sb.append(chars[mark]);
				}
				String regex = "\\$\\{"+sb.toString()+"\\}";
				if (isEnd){
					if (isHaveParam){
						templateView = templateView.replaceFirst(regex, "<font color='red'>" + paramList.get(count++) + "</font>");
					}else{
						paramList.add(sb.toString());
						String replaceStr =  templateStr.substring(start,mark+1);
						templateView = templateView.replace("${" + sb.toString() + "}", "<font color='red'>" + replaceStr + "</font>");
					}

				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return templateView;
	}

	/**
	 * Freemarker解析
	 * @param tc
	 * @param json
	 * @return
	 */
	public static String renderContentForFreeMarker(TemplateCache tc,String json){
		String result = "";
		FreeMarkerConfigurer templateEngine = (FreeMarkerConfigurer)SpringApplicationContext.getBean("templateEngine");
		String templateStr = tc.getTempContent();
		
		if(StringUtils.isBlank(templateStr)) {
			return "";
		}
		
		try {
			JSONObject model = JSON.parseObject(json);
			Template template = new Template("name", new StringReader(templateStr), templateEngine.getConfiguration());
			
			result = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}