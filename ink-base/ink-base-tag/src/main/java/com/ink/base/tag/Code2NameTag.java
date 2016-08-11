package com.ink.base.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.context.SpringApplicationContext;
import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.jdbc.core.JdbcTemplate;

public class Code2NameTag extends TagSupport {

//    private JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringApplicationContext.getBean("jdbcTemplate");

    private String tableName;

    private String columnName;

    private String where;

    private String paramValue;

    private String emptyTip = "&nbsp;";
    private String serviceName;


    public Code2NameTag() {
        super();
        init();
    }

    private void init() {
        tableName = null;
        columnName = null;
        where = null;
        paramValue = null;
        emptyTip = "&nbsp;";
    }

    public int doStartTag() throws JspException {


        JspWriter out = pageContext.getOut();

        StringBuilder sqlBuilder = new StringBuilder();
        StringBuilder resultBuilder = new StringBuilder();

        sqlBuilder.append("SELECT ").append(columnName).append(" AS NAME FROM ").append(tableName);

        String[] value = null;
        int paramLength = 0;
        if (StringUtils.isNotEmpty(where)) {
            sqlBuilder.append(" WHERE ").append(where);
            paramLength = where.length() - where.replaceAll("\\?", "").length();
        }

        if (StringUtils.isNotEmpty(paramValue)) {
            value = paramValue.split(",");
            for (int i = 0; i < value.length; i++) {
                value[i] = value[i].trim();
            }
        }

        try {
            if ((value != null && paramLength == value.length) || (value == null && paramLength == 0)) {
//                List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sqlBuilder.toString(),value);
                List<Map<String, Object>> resultList = null;
                if (StringUtils.isNotBlank(serviceName)){
                    DubboBaseService dubboBaseService = (DubboBaseService) SpringApplicationContext.getBean(serviceName);
                    resultList = (List<Map<String, Object>>) dubboBaseService.executeDynamicMethod("dynamicDataManager","queryData",sqlBuilder.toString(),value);
                }else{
                    JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringApplicationContext.getBean("jdbcTemplate");
                    resultList = jdbcTemplate.queryForList(sqlBuilder.toString(),value);
                }
                if (resultList != null && resultList.size() != 0) {
                    for (Map<String, Object> map : resultList) {
                        String s = (String) map.get("NAME");
                        if (StringUtils.isNotEmpty(s)) {
                            resultBuilder.append(s).append(",");
                        }
                    }
                }

                if (resultBuilder.length() > 0) {
                    resultBuilder.deleteCharAt(resultBuilder.length() - 1);
                } else {
                    resultBuilder.append(emptyTip);
                }
            } else {
                resultBuilder.append(emptyTip);
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getEmptyTip() {
        return emptyTip;
    }

    public void setEmptyTip(String emptyTip) throws JspException {
        this.emptyTip = String.valueOf(ExpressionEvaluatorManager.evaluate(
                "emptyTip", emptyTip, Object.class, this,
                pageContext));
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) throws JspException {
        this.paramValue = String.valueOf(ExpressionEvaluatorManager.evaluate(
                "paramValue", paramValue, Object.class, this,
                pageContext));
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
