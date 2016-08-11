package com.ink.channel.core.jdPay.util;

import java.util.ResourceBundle;


public class JDConfigurationUtil {
	private static Object lock              = new Object();
	private static JDConfigurationUtil config     = null;
	private static ResourceBundle rb        = null;
	private static final String CONFIG_FILE = "jdPay/jdPay";
	
	private JDConfigurationUtil() {
		rb = ResourceBundle.getBundle(CONFIG_FILE);
	}
	
	public static JDConfigurationUtil getInstance() {
		synchronized(lock) {
			if(null == config) {
				config = new JDConfigurationUtil();
			}
		}
		return (config);
	}
	
	public String getValue(String key) {
		return (rb.getString(key));
	}
}
