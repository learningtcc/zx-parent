package com.ink.trade.service;

import com.ink.trade.service.dto.PreCollectionCallBackDto;

/**
 * 代收回调接口
 * Created by Lenovo on 2016/5/26.
 */
public interface PreCollectionCallBackService {
    /**
     * 回调
     * @param dto
     * @return true代表回调成功，false回调失败
     */
    boolean callBack(PreCollectionCallBackDto dto);
}
