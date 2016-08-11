package com.ink.trade.service;


import com.ink.trade.service.dto.QuerySignInfoDto;

/**
 * 签约信息服务接口
 * Created by huohb on 2016/5/6.
 */
public interface ISignInfoService {

    String getSignIdByUserIdAndCardNo(QuerySignInfoDto dto);
}
