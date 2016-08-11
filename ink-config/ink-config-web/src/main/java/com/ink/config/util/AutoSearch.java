package com.ink.config.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 自动搜索提示
 * Created by aiyungui on 2016/7/5.
 */
public class AutoSearch {

    private static List<Map<String,String>> dataList  = Lists.newArrayList();

    public static List<Map<String, String>> getDataList(String searchStr) {
        List<Map<String,String>> resultList  = Lists.newArrayList();
        for (Map<String,String> dataMap : dataList){
            String data = dataMap.get("data");
            if (data.contains(searchStr)){
                resultList.add(dataMap);
            }
        }
        return resultList;
    }

    public static void setDataList(String nodePath,List<String> nodeList) {
        dataList = Lists.newArrayList();
        if (nodeList == null || nodeList.isEmpty()){
            return;
        }
//        nodePath = nodePath + "/";
        for (int i = 0; i < nodeList.size(); i++) {
            Map<String,String> dataMap = Maps.newHashMap();
            dataMap.put("data",nodePath+nodeList.get(i));
            dataList.add(dataMap);
        }
    }
}
