package com.ink.config.service.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.ink.config.service.ConfigProfile;

/**
 * 基本配置
 */
public class ZookeeperConfigProfile extends ConfigProfile {

	/**
	 * zookeeper地址
	 */
	private final String connectStr;

	/**
	 * 项目配置根目录
	 */
	private final String rootNode;

	/**
	 * 重试策略
	 */
	private final RetryPolicy retryPolicy;

	/**
	 * 一致性检查, 主动检查本地数据与zk中心数据的一致性, 防止出现因连接中断而丢失更新消息, 默认开启
	 */
	private boolean consistencyCheck = true;

	/**
	 * 检查频率, in milliseconds
	 */
	private long consistencyCheckRate = 60 * 1000;

	public ZookeeperConfigProfile(final String connectStr, final String rootNode, final boolean openLocalCache) {
		this(connectStr, rootNode, null, openLocalCache, new ExponentialBackoffRetry(100, 2));
	}

	public ZookeeperConfigProfile(final String connectStr, final String rootNode, final String version) {
		this(connectStr, rootNode, version, false, new ExponentialBackoffRetry(100, 2));
	}

	public ZookeeperConfigProfile(final String connectStr, final String rootNode, final String version, final boolean openLocalCache,
			final RetryPolicy retryPolicy) {
		super(version);
		this.connectStr = Preconditions.checkNotNull(connectStr);
		this.rootNode = Preconditions.checkNotNull(rootNode);
		this.retryPolicy = Preconditions.checkNotNull(retryPolicy);
	}

	public String getConnectStr() {
		return connectStr;
	}

	public String getRootNode() {
		return rootNode;
	}

	public RetryPolicy getRetryPolicy() {
		return retryPolicy;
	}

	public final boolean isConsistencyCheck() {
		return consistencyCheck;
	}

	public final void setConsistencyCheck(boolean consistencyCheck) {
		this.consistencyCheck = consistencyCheck;
	}

	public final long getConsistencyCheckRate() {
		return consistencyCheckRate;
	}

	public final void setConsistencyCheckRate(long consistencyCheckRate) {
		this.consistencyCheckRate = consistencyCheckRate;
	}

	public String getVersionedRootNode() {
		if (Strings.isNullOrEmpty(version)) {
			return rootNode;
		}
		return ZKPaths.makePath(rootNode, version);
	}

	@Override
	public String toString() {
		return "ZookeeperConfigProfile [connectStr=" + connectStr + ", rootNode=" + rootNode + ", retryPolicy=" + retryPolicy + ", consistencyCheck="
				+ consistencyCheck + ", consistencyCheckRate=" + consistencyCheckRate + "]";
	}

}