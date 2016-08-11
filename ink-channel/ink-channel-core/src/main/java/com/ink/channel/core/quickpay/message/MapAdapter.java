package com.ink.channel.core.quickpay.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 修改map属性节点名称
 * @author huohb
 *
 */
public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<String, String>> {
    
    public static class AdaptedMap {
         
        public List<Entry> extDate = new ArrayList<Entry>();
  
    }
     
    public static class Entry {
         
        public String key;
         
        public String value;
   
    }
 
    @Override
    public Map<String, String> unmarshal(AdaptedMap adaptedMap) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        for(Entry entry : adaptedMap.extDate) {
            map.put(entry.key, entry.value);
        }
        return map;
    }
 
    @Override
    public AdaptedMap marshal(Map<String, String> map) throws Exception {
    	if(map == null || map.isEmpty()){
    		return null;
    	}
        AdaptedMap adaptedMap = new AdaptedMap();
        for(Map.Entry<String, String> mapEntry : map.entrySet()) {
            Entry entry = new Entry();
            entry.key = mapEntry.getKey();
            entry.value = mapEntry.getValue();
            adaptedMap.extDate.add(entry);
        }
        return adaptedMap;
    }
 
}