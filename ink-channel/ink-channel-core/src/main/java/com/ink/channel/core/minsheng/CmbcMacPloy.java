package com.ink.channel.core.minsheng;
/**
 * 民生电商获取摘要策略类
 * @author huohb
 *
 */
public interface CmbcMacPloy {
	/**
	 * 根据xml内容进行加密获得摘要
	 * @param xml
	 * @return
	 */
	public String getMac(String xml);
//	/**
//	 * 用来获取密钥
//	 * @return
//	 */
//	public String getKey();

}
