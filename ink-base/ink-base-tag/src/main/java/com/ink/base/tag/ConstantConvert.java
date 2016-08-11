package com.ink.base.tag;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.model.TagDataInfo;
import com.ink.base.utils.fileUtil.ReadPropertiesUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 常量数据类型转换标签
 * 根据传输的值、类型，从配置文件中读取配置的字典信息
 * Created by aiyungui on 2016/5/11.
 */
public class ConstantConvert extends TagSupport {

    /*数据标签*/
    private String dataTag ;
    /*参数值*/
    private String paramValue;
    /*参数分割方式*/
    private String paramSplit;
    /*html标签*/
    private String htmlTag;
    /*html标签名称*/
    private String name;
    /*默认值*/
    private String emptyTip ;
    private String codeTag;
    private String nameTag;
    private String sysTag;
    /*值对象*/
    private Object data;

    /*select是否自动添加"请选择项"*/
    private boolean isAutoItem;
    /*配置文件路径*/
    private String filePath = "classpath:constant.json";

    public ConstantConvert(){
        super();

        paramValue = "";
        isAutoItem = true;
        emptyTip = "&nbsp;";
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        try {
            String resultHtml = null;
            if ("text".equalsIgnoreCase(htmlTag)){//文本获
                resultHtml = textWriter();
            }else if ("checkBox".equalsIgnoreCase(htmlTag)){//选择框
                resultHtml = checkBoxWriter();
            }else if ("select".equalsIgnoreCase(htmlTag)){//下拉列表
                resultHtml = selectWriter();
            }
            if (StringUtils.isBlank(resultHtml)){
                resultHtml = emptyTip;
            }
            out.println(resultHtml);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.doStartTag();
    }

    /**
     * 下拉列表方式
     * @return
     */
    private String selectWriter() {
        JSONArray dictDataArray = getConvertData();
        if (null == dictDataArray || dictDataArray.isEmpty()){
            return null;
        }
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("<select id = \"").append(name).append("\"")
                .append(" name = \"").append(name).append("\"").append(">");

        if (isAutoItem){
            resultBuilder.append("<option value=\"\">").append("请选择...").append("</option>");
        }
        for (int i = 0; i < dictDataArray.size(); i++) {
            JSONObject jsonObject = dictDataArray.getJSONObject(i);
            String dictCode = jsonObject.getString(getCodeTag());
            String dictText = jsonObject.getString(getNameTag());
            resultBuilder.append("<option value=\"").append(dictCode).append("\"");
            if (paramValue.equals(dictCode)){
                resultBuilder.append(" selected");
            }
            resultBuilder.append(" >").append(dictText).append("</option>");
        }
        resultBuilder.append("</select>");
        return resultBuilder.toString();
    }

    /**
     * 多项选择框方式
     * @return
     */
    private String checkBoxWriter() {
        JSONArray dictDataArray = getConvertData();
        if (null == dictDataArray || dictDataArray.isEmpty()){
            return null;
        }
        StringBuilder resultBuilder = new StringBuilder();
        String[] values = null;
        if (StringUtils.isBlank(paramSplit)){
            values = new String[]{paramValue};
        }else{
            values = paramValue.split(paramSplit);
        }
        for (int i = 0; i < dictDataArray.size(); i++) {
            JSONObject jsonObject = dictDataArray.getJSONObject(i);
            String dictCode = jsonObject.getString(getCodeTag());
            String dictText = jsonObject.getString(getNameTag());
            resultBuilder.append("<input type=\"checkbox\" value=\"").append(dictCode).append("\"")
                    .append(" name = \"").append(name).append("\"")
                    .append(" id = \"").append(name).append("\"");
            for (String value : values){
                if (value.equals(dictCode)){
                    resultBuilder.append("checked");
                }
            }
            resultBuilder.append(" >").append(dictText).append("</input>");
        }

        return resultBuilder.toString();
    }

    /**
     * 文件标签获取方式
     * @return
     */
    private String textWriter() {

        JSONArray dictDataArray = getConvertData();
        if (null == dictDataArray || dictDataArray.isEmpty()){
            return null;
        }

        StringBuilder resultBuilder = new StringBuilder();
        String[] values = null;
        if (StringUtils.isBlank(paramSplit)){
            paramSplit = "";
            values = new String[]{paramValue};
        }else{
            values = paramValue.split(paramSplit);
        }
        for (String value : values){
            for (int i = 0; i < dictDataArray.size(); i++) {
                JSONObject jsonObject = dictDataArray.getJSONObject(i);
                String dictCode = jsonObject.getString(getCodeTag());
                if (value.equals(dictCode)){
                    String dictText = jsonObject.getString(getNameTag());
                    resultBuilder.append(paramSplit).append(dictText);
                }
            }
        }
        if (resultBuilder.length() > 0 && StringUtils.isNotBlank(paramSplit) && resultBuilder.indexOf(paramSplit) == 0) {
            resultBuilder.deleteCharAt(0);
        }

        return resultBuilder.toString();
    }

    private JSONArray getConvertData(){
        JSONArray jsonArray = null;
        try{
            boolean isProperties = true;
            if (data != null){
                TagDataInfo dataAble = (TagDataInfo) data;
                jsonArray = dataAble.getJsonArray();
                if (!(jsonArray == null || jsonArray.isEmpty())){
                        isProperties = false;
                }
            }
            if (isProperties){
                jsonArray = readProperties();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * 读取配置文件信息，已jsonArray的方式返回
     * @return
     */
    private JSONArray readProperties() {
        JSONArray jsonArray = null;
        try{
            String defaultFilePath = "classpath:constant/"+sysTag+"Constant.json";
            if (StringUtils.isBlank(sysTag)){
                defaultFilePath = filePath;
            }

            String fileContent = ReadPropertiesUtil.getPropertiesContent(defaultFilePath,filePath);
            if (StringUtils.isBlank(fileContent)){
                return null;
            }

            fileContent = fileContent.trim();
            JSONObject jsonObject = JSON.parseObject(fileContent);
            jsonArray = jsonObject.getJSONArray(dataTag);
        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public boolean isAutoItem() {
        return isAutoItem;
    }

    public void setIsAutoItem(boolean isAutoItem) {
        this.isAutoItem = isAutoItem;
    }

    public String getDataTag() {
        return dataTag;
    }

    public void setDataTag(String dataTag) {
        this.dataTag = dataTag;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamSplit() {
        return paramSplit;
    }

    public void setParamSplit(String paramSplit) {
        this.paramSplit = paramSplit;
    }

    public String getHtmlTag() {
        return htmlTag;
    }

    public void setHtmlTag(String htmlTag) {
        this.htmlTag = htmlTag;
    }

    public String getEmptyTip() {
        return emptyTip;
    }

    public void setEmptyTip(String emptyTip) {
        this.emptyTip = emptyTip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCodeTag() {
        if (StringUtils.isBlank(codeTag)){
            codeTag = "code";
        }
        return codeTag;
    }

    public void setCodeTag(String codeTag) {
        this.codeTag = codeTag;
    }

    public String getNameTag() {
        if (StringUtils.isBlank(nameTag)){
            nameTag = "name";
        }
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

    public String getSysTag() {
        return sysTag;
    }

    public void setSysTag(String sysTag) {
        this.sysTag = sysTag;
    }
}
