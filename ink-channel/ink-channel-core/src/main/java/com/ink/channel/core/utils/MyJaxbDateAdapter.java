package com.ink.channel.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MyJaxbDateAdapter extends XmlAdapter<String, Date>{
	
	private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
	private static final DateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

	@Override
	public Date unmarshal(String v) throws Exception {
		return v == null ? null : formatter.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return v == null ? null : formatter.format(v);
	}


}
