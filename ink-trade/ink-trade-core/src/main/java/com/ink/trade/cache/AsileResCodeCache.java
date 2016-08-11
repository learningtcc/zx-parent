package com.ink.trade.cache;

import com.ink.asile.core.po.AsileResCode;
/**
 * 
 *<pre>
 *<b>类描述:</b>(单例渠道响应码)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年5月23日 下午2:44:21
 *</pre>
 */
public class AsileResCodeCache {
    private volatile static SimpleCache<String, AsileResCode> simpleCache;
    private AsileResCodeCache(){}
    public static SimpleCache<String, AsileResCode> getInstance(){
        if(simpleCache == null){
            synchronized(AsileResCodeCache.class){
                if(simpleCache == null){
                    simpleCache = new SimpleCache<String, AsileResCode>(600);
                }
            }
        }
        return simpleCache;
    }
}
