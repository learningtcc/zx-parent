package com.ink.config.service.support.spring;

import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import com.google.common.base.Preconditions;
import com.ink.config.service.ConfigGroup;

/**
 * Factory to create PropertySource for configuration group
 * 
 *
 */
public class ConfigGroupSourceFactory {
	

	public static PropertySources create(ConfigGroup... configGroups) {
		Preconditions.checkNotNull(configGroups);
		final MutablePropertySources sources = new MutablePropertySources();
		for (ConfigGroup configGroup : configGroups) {
			sources.addLast(new ConfigGroupResource(configGroup));
		}
		return sources;
	}

}
