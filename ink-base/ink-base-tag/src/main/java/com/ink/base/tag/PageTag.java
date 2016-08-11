package com.ink.base.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ink.base.page.Page;

public class PageTag extends TagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7568798469000572985L;
	private String url;
	private String id;
	private String page;
	private String pageNumberName;
	private String pageSizeName;
	private String submitForm;
	private String pageSize;

	private boolean joy = false;
	
	@Override
	public int doStartTag() throws JspException {
		
		String path = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
		
		JspWriter out = pageContext.getOut();
		
		if(StringUtils.isEmpty(page)){
			page = "page";
		}
		
		if(StringUtils.isEmpty(pageNumberName)){
			pageNumberName = "pageNumber";
		}
		
		if(StringUtils.isEmpty(pageSizeName)){
			pageSizeName = "pageSize";
		}
		
		Object temp = pageContext.findAttribute(page);
		//避免产生异常
		if(temp == null){
			return super.doStartTag();
		}
		
		Page nativePage = (Page)temp;
		
		int pageNum = nativePage.getThisPageNumber();
		String pageSize = String.valueOf(nativePage.getPageSize());
		int pageCount = (int) Math.ceil(((float) nativePage.getTotalCount() * 1.0F)
				/ (float) nativePage.getPageSize());
		
		try {
			
			if(joy){
				//左侧
				StringBuilder sb = new StringBuilder("<div class=\"pages\"><span class=\"fr\">");
				sb.append("<a class=\"pagehome\" href=\"#\"");
				if(pageNum > 1){
					sb.append(" onclick=\"").append(id).append("Go(1,").append(nativePage.getPageSize()).append(")\"");
				}
				sb.append("></a><a class=\"pageup\" href=\"#\"");
				if(pageNum > 1){
					sb.append(" onclick=\"").append(id).append("Go(").append(pageNum-1).append(",").append(nativePage.getPageSize()).append(")\"");
				}
				sb.append("></a>|第");
				sb.append("<input type=\"text\" class=\"w30\" id=\"").append(id).append("Page\" value=\"").append(pageNum).append("\"");
				sb.append(" onkeydown=\"if(event.keyCode==13) ").append(id+"GoPage(").append(nativePage.getPageSize()).append(","+pageCount).append(")\">");
				sb.append("页/共").append(pageCount).append("页|");
				sb.append("<a class=\"pagedown\" href=\"#\" ");
				if(pageNum < pageCount){
					sb.append(" onclick=\"").append(id).append("Go(").append(pageNum+1).append(",").append(nativePage.getPageSize()).append(")\"");
				}
				sb.append("></a><a class=\"pageend\" href=\"#\"");
				if(pageNum < pageCount){
					sb.append(" onclick=\"").append(id).append("Go(").append(pageCount).append(",").append(nativePage.getPageSize()).append(")\"");
				}
				sb.append("></a>|每页显示记录条数：");
				
				sb.append("<select onchange=\"").append(id).append("changePageSize(this.value)\">");
	            //读取每页显示的条数
				String pageSizeString = "10,25,50,100";
				if(StringUtils.isNotBlank(getPageSize())){
					pageSizeString = getPageSize();
				}
				String[] pageSizeArray = pageSizeString.split(",");
				for (String pageSizeTemp : pageSizeArray) {
					sb.append("<option value=\"").append(pageSizeTemp.trim()).append("\" ");
					if (pageSizeTemp.trim().equals(pageSize)) {
						sb.append("selected");
					}
					sb.append(">").append(pageSizeTemp).append("</option>");
				}
				int firstNum = (pageNum-1)*nativePage.getPageSize()+1;
				int endNum = firstNum + nativePage.getPageSize() -1;
				if(nativePage.getTotalCount() - firstNum < nativePage.getPageSize()-1){
					endNum = nativePage.getTotalCount();
				}
				sb.append("</select></span>显示第").append(firstNum);
				sb.append("条到第").append(endNum).append("条记录");
				sb.append(",共").append(nativePage.getTotalCount()).append("条记录</a>");
				
				out.println(sb.toString());
			}else {
				// **************上一页***************
				if (1 == pageNum) {
					out.print("<span class=\"disabled\">上一页</span>");
				} else {
					out.print("<a href=\"#\" onclick=\""+id+"Go("+(pageNum-1)+","+ nativePage.getPageSize() +")\">上一页</a>");
				}
				// **************中间页***************
				if (pageCount > 6) {
					if (1 == pageNum) {
						out.print("<span class=\"current\">" + 1 + "</span>");
					} else {
						out.print("<a href=\"#\" onclick=\""+id+"Go(" + 1 +","+ nativePage.getPageSize()+ ")\">" + 1 + "</a>");
					}
					if ((1 + 1 + 3) < pageNum) {
						out.print("<span>...</span>");
					}
					for (int j = pageNum - 3; j <= pageNum + 3; j++) {
						if (j <= 1) {
							continue;
						} else if (j >= pageCount) {
							continue;
						} else if (j == pageNum) {
							out.print("<span class=\"current\">" + j + "</span>");
						} else {
							out.print("<a href=\"#\" onclick=\""+id+"Go(" + j +","+ nativePage.getPageSize()+ ")\">"
									+ j + "</a>");
						}
					}
					if ((pageCount - 4) > pageNum) {
						out.print("<span>...</span>");
					}
					if (pageCount == pageNum) {
						out.print("<span class=\"current\">"
								+ pageCount + "</span>");
					} else {
						out.print("<a href=\"#\" onclick=\""+id+"Go(" + pageCount+","+ nativePage.getPageSize() +")\">" + pageCount + "</a>");
					}
				} else {
					for (int i = 1; i <= pageCount; i++) {
						if (i == pageNum) {
							out.print("<span class=\"current\">" + i + "</span>");
						} else {
							out.print("<a href=\"#\" onclick=\""+id+"Go(" + i+","+ nativePage.getPageSize() + ")\">" + i + "</a>");
						}
					}
				}
				// **************下一页***************
				if (pageCount > pageNum) {
					out.print("<a href=\"#\" onclick=\""+id+"Go(" + +(pageNum+1)+","+ nativePage.getPageSize()+ ")\">下一页</a>");
				} else {
					out.print("<span class=\"disabled\">下一页</span>");
				}
			}
			
			out.println("<script type=\"text/javascript\">");
			out.println("function "+id+"Go(pageNum,pageSize){");
			out.println("$(\"input[name='"+pageNumberName+"']\").val(pageNum);");
			out.println("$(\"input[name='"+pageSizeName+"']\").val(pageSize);");
			if(StringUtils.isEmpty(submitForm)){
				out.println("$(\"#"+id+"\").submit();");
			}else{
				out.println("$(\"#"+submitForm+"\").submit();");
			}
			out.println("}");
			out.println("function "+id+"GoPage(pageSize,pageCount){");
			out.println("if(isNaN($(\"#"+id+"Page\").val())){alert('请输入数字！');return false;}else if($(\"#"+id+"Page\").val()>pageCount){alert('输入页码过大!');return false;}");
			out.println(id+"Go($(\"#"+id+"Page\").val(),pageSize);}");
			out.println("function "+id+"changePageSize(pageSize){");
			out.println("$(\"input[name='"+pageNumberName+"']\").val('1');$(\"input[name='"+pageSizeName+"']\").val(pageSize);");
			if(StringUtils.isEmpty(submitForm)){
				out.println("$(\"#"+id+"\").submit();");
			}else{
				out.println("$(\"#"+submitForm+"\").submit();");
			}
			out.println("}");
			out.println("</script>");
			
			//********************Form********************
			if(StringUtils.isEmpty(submitForm)){
				Map<String, String[]> paramMap = pageContext.getRequest().getParameterMap();
				List<String> paramName = new ArrayList<String>(paramMap.keySet());
				out.println("<form id=\""+id+"\" method=\"post\" action=\""+url+"\">");
				for (String name : paramName) {
					
					String[] valueArray = paramMap.get(name);
					
					for (String value : valueArray) {
						out.print("<input type=\"hidden\" name=\""+name+"\" value=\""+value+"\">");
					}
				}
				
				if(!paramMap.containsKey(pageNumberName)){
					out.println("<input type=\"hidden\" name=\""+pageNumberName+"\" value=\"1\"/>");
				}
				
				if(!paramMap.containsKey(pageSizeName)){
					out.println("<input type=\"hidden\" name=\""+pageSizeName+"\" value=\"10\"/>");
				}
				
				//out.print("</form>");
			}
			submitForm = "";
			page = "";
			pageNumberName = "";
			pageSizeName = "";
			out.flush();
		} catch (Exception e) {
			return SKIP_BODY;
		}
		return EVAL_BODY_INCLUDE;
		
	}

	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if (StringUtils.isEmpty(submitForm)) {
				out.println("</form>");
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) throws JspException {
		this.url =  String.valueOf(ExpressionEvaluatorManager.evaluate(  
				"url", url, Object.class, this,   
                pageContext));;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		try {
			this.id =  String.valueOf(ExpressionEvaluatorManager.evaluate(  
					"id", id, Object.class, this,   
			        pageContext));
		} catch (JspException e) {
			e.printStackTrace();
		};
	}

	public String getSubmitForm() {
		return submitForm;
	}

	public void setSubmitForm(String submitForm) throws JspException {
		this.submitForm =  String.valueOf(ExpressionEvaluatorManager.evaluate(  
				"submitForm", submitForm, Object.class, this,   
                pageContext));;
	}
	
	
	public void setJoy(String joy) throws JspException {
		this.joy =  Boolean.parseBoolean((String) ExpressionEvaluatorManager.evaluate(  
				"joy", joy, Object.class, this,   
                pageContext));;
	}
	
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageNumberName() {
		return pageNumberName;
	}

	public void setPageNumberName(String pageNumberName) {
		this.pageNumberName = pageNumberName;
	}

	public String getPageSizeName() {
		return pageSizeName;
	}

	public void setPageSizeName(String pageSizeName) {
		this.pageSizeName = pageSizeName;
	}


}
