package com.ink.channel.core.boofoopay;

import java.util.ResourceBundle;


public class BooFooConfigurationUtil {
	private static Object lock              = new Object();
	private static BooFooConfigurationUtil config     = null;
	private static ResourceBundle rb        = null;
	private static final String CONFIG_FILE = "boofoopay/boofoo";
	
	private BooFooConfigurationUtil() {
		rb = ResourceBundle.getBundle(CONFIG_FILE);
	}
	
	public static BooFooConfigurationUtil getInstance() {
		synchronized(lock) {
			if(null == config) {
				config = new BooFooConfigurationUtil();
			}
		}
		return (config);
	}
	
	public String getValue(String key) {
		return (rb.getString(key));
	}
}
