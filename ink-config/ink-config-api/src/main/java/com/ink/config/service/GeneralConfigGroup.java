package com.ink.config.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

public abstract class GeneralConfigGroup extends ConcurrentHashMap<String, String> implements ConfigGroup {

	private static final long serialVersionUID = 1L;

	private ConfigGroup internalConfigGroup;

	protected GeneralConfigGroup(ConfigGroup internalConfigGroup) {
		this.internalConfigGroup = internalConfigGroup;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralConfigGroup.class);

	@Override
	public final String get(String key) {
		String val = super.get(key);
		if (val == null && internalConfigGroup != null) {
			val = internalConfigGroup.get(key);
		}
		return val;
	}

	@Override
	public final String get(Object key) {
		return get(key.toString());
	}

	@Override
	public final void putAll(Map<? extends String, ? extends String> configs) {
		if (configs != null && configs.size() > 0) {
			// clear
//			if (this.size() > 0) {
//				final Set<String> newKeys = Sets.newHashSet();
//				newKeys.addAll(configs.keySet());
//				final Iterable<String> redundances = Iterables.filter(Sets.newHashSet(this.keySet()), new Predicate<String>() {
//
//					@Override
//					public boolean apply(String input) {
//						return !newKeys.contains(input);
//					}
//				});
//				for (String redundance : redundances) {
//					super.remove(redundance);
//				}
//			}

			// update
			for (Map.Entry<? extends String, ? extends String> entry : configs.entrySet()) {
				this.put(entry.getKey(), entry.getValue());
			}

		}
	}

	@Override
	public final String put(String key, String value) {
		String preValue = super.get(key);
		if (!Objects.equal(preValue, value)) {
			LOGGER.debug("Key {} change from {} to {}", key, preValue, value);
			super.put(key, value);
		}
		return preValue;
	}
}