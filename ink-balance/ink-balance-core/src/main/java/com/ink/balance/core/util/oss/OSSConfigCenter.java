package com.ink.balance.core.util.oss;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * OSS配置中心
 * @author bo.chen
 * @date 2016年07月14日 下午12:03:34
 */
@Component("ossConfigCenter")
public class OSSConfigCenter {

	@Value("#{ossConfig}")
	private Map<String,String> ossConfig;

	public Map<String, String> getOssConfig() {
		return ossConfig;
	}

	public void setOssConfig(Map<String, String> ossConfig) {
		this.ossConfig = ossConfig;
	}
}
