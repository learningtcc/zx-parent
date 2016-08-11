package com.ink.config.service;

import java.io.Closeable;
import java.util.Map;

/**
 * Configuration Group
 *
 */
public interface ConfigGroup extends Map<String, String>, Closeable {
	
	String get(String key);
	
}
