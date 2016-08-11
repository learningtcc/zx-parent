package com.ink.config.service.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * 监听器
 * 
 *
 */
public final class ConfigNodeEventListener implements CuratorListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigNodeEventListener.class);

	private final ZookeeperConfigGroup configNode;

	public ConfigNodeEventListener(ZookeeperConfigGroup configNode) {
		super();
		this.configNode = Preconditions.checkNotNull(configNode);
	}

	@Override
	public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(event.toString());
		}

		final WatchedEvent watchedEvent = event.getWatchedEvent();
		if (watchedEvent != null) {
			LOGGER.debug("Watched event: {}" + watchedEvent);

			if (watchedEvent.getState() == KeeperState.SyncConnected) {
				boolean someChange = false;
				switch (watchedEvent.getType()) {
				case NodeChildrenChanged:
					configNode.loadNode();
					someChange = true;
					break;
				case NodeDataChanged:
					configNode.reloadKey(watchedEvent.getPath());
					someChange = true;
					break;
				default:
					break;
				}

				if (someChange && configNode.getConfigLocalCache() != null) {
					configNode.getConfigLocalCache().saveLocalCache(configNode, configNode.getNode());
				}
			}
		}
	}
}
