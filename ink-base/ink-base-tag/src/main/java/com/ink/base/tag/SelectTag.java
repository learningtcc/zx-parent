package com.ink.base.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ink.base.service.DubboBaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ink.base.utils.context.SpringApplicationContext;

public class SelectTag extends TagSupport {
	

	private static final long serialVersionUID = -1091667481530035466L;

	private String id;
	
	private String name;
	
	private String codeName;
	
	private String codeValue;
	
	private String table;
	
	private String where;
	
	private String paramValue;
	
	private String style;
	
	private String className;
	
	private String select;

	private String serviceName;
	
	/**
	 * 用于标识数据源是初始化的还是传入的
	 */
	private String init = "0";
	
	// 页面展现的形式：radio：单选框；checkbox：多选框；其它：下拉列表。
	private String showType = "select";
	
	public SelectTag() {
		super();
		init();
	}
	
	private void init(){
		id = null;
		name = null;
		codeName = null;
		codeValue = null;
		table = null;
		where = null;
		paramValue = null;
		style = null;
		className = null;
		select = null;
		init = "0";
	}

	public int doStartTag() throws JspException{
		
		JspWriter out = pageContext.getOut();
		
		StringBuilder sqlBuilder = new StringBuilder();
		
		
		if(StringUtils.isBlank(id)){
			id = name;
		}
		
		sqlBuilder.append("SELECT ").append(codeValue).append(" AS VALUE,").append(codeName).append(" AS NAME FROM ").append(table);
		
		String[] paramValues = null;
		int paramLength = 0;
		if (StringUtils.isNotEmpty(where)) {
			sqlBuilder.append(" WHERE ").append(where);
			paramLength = where.length() - where.replaceAll("\\?", "").length();
		}
		
		if (StringUtils.isNotEmpty(paramValue)) {
			paramValues = paramValue.split(",");
			for (int i = 0; i < paramValues.length; i++) {
				paramValues[i] = paramValues[i].trim();
			}
		}
		
		
		try {
			StringBuilder resultBuilder = new StringBuilder();
			
			if ((paramValues != null && paramLength==paramValues.length) || (paramValues==null && paramLength==0)) {
				List<Map<String, Object>> resultList = null;
				if (StringUtils.isNotBlank(serviceName)){
					DubboBaseService dubboBaseService = (DubboBaseService) SpringApplicationContext.getBean(serviceName);
					resultList = (List<Map<String, Object>>) dubboBaseService.executeDynamicMethod("dynamicDataManager","queryData",sqlBuilder.toString(),paramValues);
				}else{
					JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringApplicationContext.getBean("jdbcTemplate");
					resultList = jdbcTemplate.queryForList(sqlBuilder.toString(),paramValues);
				}

				
				String key = null;
				String value = null;
				boolean cssFlag = StringUtils.isNotBlank(className);
				
				// 根据传入的类型组织页面显示的形式。
				if("radio".equalsIgnoreCase(showType)){
					Map<String, Object> map = null;
					String radioId = null;
					if(resultList != null && resultList.size() != 0){
						for(int i = 0, len = resultList.size(); i < len; i ++){
							map = resultList.get(i);
							key = String.valueOf(map.get("NAME"));
							value = String.valueOf(map.get("VALUE"));
							radioId = id + i;
							
							resultBuilder.append("<input type=\"radio\" name=\"").append(name).append("\" ");
							resultBuilder.append("id=\"").append(radioId).append("\" ");
							resultBuilder.append("value=\"").append(value).append("\" ");
							if (value.equals(select)) {
								resultBuilder.append("checked=\"checked\" ");
							}
							if(cssFlag){
								resultBuilder.append("class=\"").append(className).append("\" ");
							}
							resultBuilder.append("/>");
							resultBuilder.append("<label for=\"").append(radioId).append("\" ");
							if(cssFlag){
								resultBuilder.append("class=\"").append(className).append("\" ");
							}
							resultBuilder.append(">").append(key).append("</label> ");
						}
					}
				}else if("checkbox".equalsIgnoreCase(showType)){
					Map<String, Object> map = null;
					String checkboxId = null;
					if(resultList != null && resultList.size() != 0){
						for(int i = 0, len = resultList.size(); i < len; i ++){
							map = resultList.get(i);
							key = String.valueOf(map.get("NAME"));
							value = String.valueOf(map.get("VALUE"));
							checkboxId = id + i;
							
							resultBuilder.append("<input type=\"checkbox\" name=\"").append(name).append("\" ");
							resultBuilder.append("id=\"").append(checkboxId).append("\" ");
							resultBuilder.append("value=\"").append(value).append("\" ");
							if (value.equals(select)) {
								resultBuilder.append("checked=\"checked\" ");
							}
							if(cssFlag){
								resultBuilder.append("class=\"").append(className).append("\" ");
							}
							resultBuilder.append("/>");
							resultBuilder.append("<label for=\"").append(checkboxId).append("\" ");
							if(cssFlag){
								resultBuilder.append("class=\"").append(className).append("\" ");
							}
							resultBuilder.append(" >").append(key).append("</label> ");
						}
					}
				}else{
					resultBuilder.append("<select name=\"").append(name).append("\" id=\"").append(id).append("\"");
					if (StringUtils.isNotBlank(style)) {
						resultBuilder.append(" style=\"").append(style).append("\"");
					}
					if(cssFlag){
						resultBuilder.append("class=\"").append(className).append("\" ");
					}
					resultBuilder.append(">");
					resultBuilder.append("<option value=\"\">请选择...</option>");
					
					if(resultList != null && resultList.size() != 0){
						for (Map<String, Object> map : resultList) {
							key = String.valueOf(map.get("NAME"));
							value = String.valueOf(map.get("VALUE"));
							resultBuilder.append("<option value=\"").append(value).append("\"");
							if (value.equals(select)) {
								resultBuilder.append("selected=\"selected\"");
							}
							resultBuilder.append(">").append(key).append("</option>");
						}
					}
					resultBuilder.append("</select>");
				}
				
			}
		
			
			out.println(resultBuilder.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.doStartTag();
	}
	
	@Override
	public int doEndTag() throws JspException {
		id = null;
		name = null;
		codeName = null;
		codeValue = null;
		table = null;
		where = null;
		paramValue = null;
		style = null;
		select = null;
		return super.doEndTag();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) throws JspException {
		this.where =  String.valueOf(ExpressionEvaluatorManager.evaluate(  
				"where", where, Object.class, this,   
                pageContext));
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) throws JspException {
		this.paramValue =  String.valueOf(ExpressionEvaluatorManager.evaluate(  
				"paramValue", paramValue, Object.class, this,   
                pageContext));
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) throws JspException {
		this.select =  String.valueOf(ExpressionEvaluatorManager.evaluate(  
				"select", select, Object.class, this,   
                pageContext));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
