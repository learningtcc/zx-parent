package com.ink.base.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.*;

/**
 * 标签数据接口类
 * Created by Lenovo on 2016/7/20.
 */
public class TagDataInfo implements Serializable{

    private static final long serialVersionUID = 6350028475309350294L;
    private JSONArray jsonArray;
    private JSONObject jsonObject;

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(Object object) {
        if (object instanceof  JSONArray){
            this.jsonArray = (JSONArray) object;
        }else if (object instanceof Collection){
            this.jsonArray = (JSONArray) JSON.toJSON(object);
        }

    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(Object object) {
        if (object instanceof JSONObject){
            this.jsonObject = (JSONObject) object;
        }else{
            this.jsonObject = (JSONObject) JSON.toJSON(object);
        }
    }
}
