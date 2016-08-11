package com.ink.config.service.zookeeper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.curator.utils.ZKPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Objects;

/**
 * 配置本地缓存
 *
 */
public class ConfigLocalCache {

	private String localCachePath;

	public ConfigLocalCache(String localCacheFolder, String rootNode) {
		super();
		this.localCachePath = ZKPaths.makePath(localCacheFolder, rootNode);
	}

	private static final String SUFFIX = ".cache";

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLocalCache.class);

	/**
	 * 缓存配置到本地
	 * 
	 * @param configNode
	 * @param node
	 */
	public void saveLocalCache(ZookeeperConfigGroup configNode, String node) {
		String[] nodeArray = node.split(",");
		for (String nodeInfo : nodeArray) {
			String localFilePath = genCacheFilePath(nodeInfo);
			LOGGER.debug("Saving cache to file: {}", localFilePath);

			Map<String, String> data = configNode.exportProperties();
			if (data != null && data.size() > 0) {
				Properties properties = new Properties();
				for (Entry<String, String> entry : data.entrySet()) {
					properties.put(entry.getKey(), entry.getValue());
				}
				Writer writer = null;
				try {
					writer = new OutputStreamWriter(new FileOutputStream(localFilePath), Charsets.UTF_8);
					properties.store(writer, String.format("Local cache of configs group: %s", node));
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				} finally {
					if (writer != null)
						try {
							writer.close();
						} catch (IOException e) {
							// DO NOTHING
						}
				}
			}
		}
	}

	/**
	 * 计算本地缓存文件位置
	 * 
	 * @param node
	 * @return
	 */
	private String genCacheFilePath(String node) {
		checkFolderExistence();
		StringBuilder builder = new StringBuilder();
		builder.append(localCachePath);
		builder.append(File.separatorChar);
		builder.append(node);
		builder.append(SUFFIX);

		return builder.toString();
	}

	/**
	 * 检查本地缓存文件的存在状态，如不存在，创建
	 */
	private void checkFolderExistence() {
		File file = new File(localCachePath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("localCachePath", localCachePath).toString();
	}
}
