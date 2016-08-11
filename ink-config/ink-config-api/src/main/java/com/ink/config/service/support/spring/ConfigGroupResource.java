package com.ink.config.service.support.spring;

import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PreDestroy;

import org.springframework.core.env.PropertySource;

import com.ink.config.service.ConfigGroup;

/**
 * Spring Property Sources support
 *
 */
public class ConfigGroupResource extends PropertySource<ConfigGroup> implements Closeable {

	public ConfigGroupResource(ConfigGroup configNode) {
		super(UUID.randomUUID().toString(), configNode);
	}

	@Override
	public Object getProperty(String name) {
		return super.getSource().get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Closeable#close()
	 */
	@Override
	@PreDestroy
	public void close() throws IOException {
		super.getSource().close();
	}

}
