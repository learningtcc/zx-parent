package com.ink.balance.api.service;

import java.util.List;
import com.ink.balance.api.model.in.ChannelParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.ChannelParamOutput;
import com.ink.balance.api.model.out.CommonOutput;

/**
 * 
* <p>Title:IChannelParamService </p>
* <p>Description:渠道信息接口 </p>
* <p>Company: </p> 
* @author zhaojie
* @date 2016年7月18日 下午6:03:56
 */
public interface IChannelParamService {
	List<ChannelParamOutput> pageQuery(PageParamInput paramInput);
	CommonOutput<Object> pageChannel(ChannelParamInput param, PageParamInput pageParam);
	CommonOutput<ChannelParamOutput> getDetails(Long id);
	String insert(ChannelParamInput channelParam);
	String update(ChannelParamInput channelParam);
}
