package com.ink.channel.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * Jaxb中VO转XML针对Map的适配器
 * @author huohb
 *
 */
public class JaxbMapAdapter extends XmlAdapter<MyHashMapType, Map<String, String>>{

	@Override
	public Map<String, String> unmarshal(MyHashMapType v) throws Exception {
		Map<String,String> hashMap = new HashMap<String,String>();
		List<MyHashEntryType> entryList = v.getExtDate();
		for(MyHashEntryType hashEntry : entryList){
			hashMap.put(hashEntry.getKey(), hashEntry.getValue());
		}
		return hashMap;
	}

	@Override
	public MyHashMapType marshal(Map<String, String> v) throws Exception {
		if(v == null){
			return null;
		}
		MyHashMapType myHashMapType = new MyHashMapType();
		for (Entry<String,String> entry : (Set<Entry<String,String>>) v.entrySet()) {  
            MyHashEntryType myHashEntryType = new MyHashEntryType();  
            myHashEntryType.setKey(entry.getKey());
            myHashEntryType.setValue(entry.getValue());
            myHashMapType.getExtDate().add(myHashEntryType);
        }  
        return myHashMapType; 
	}


}
class MyHashMapType{
	
	private List<MyHashEntryType> extDate = new ArrayList<MyHashEntryType>();

	public List<MyHashEntryType> getExtDate() {
		return extDate;
	}

	public void setExtDate(List<MyHashEntryType> extDate) {
		this.extDate = extDate;
	}
}
class MyHashEntryType{
	
	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
