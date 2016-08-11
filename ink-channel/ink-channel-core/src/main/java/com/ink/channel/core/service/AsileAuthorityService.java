package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileAuthorityInput;
import com.ink.channel.core.model.out.AsileAuthorityOutput;


/**
 * 
 *<pre>
 *<b>类描述:</b>(渠道鉴权接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月30日 上午10:09:05
 *</pre>
 */
public interface AsileAuthorityService {
   AsileAuthorityOutput authorize(AsileAuthorityInput authority);
}
