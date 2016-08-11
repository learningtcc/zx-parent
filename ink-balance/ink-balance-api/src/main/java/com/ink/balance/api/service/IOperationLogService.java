package com.ink.balance.api.service;

import com.ink.balance.api.model.in.OperationLogInput;

/**
 * @author bo.chen
 * @Description 操作日志接口
 * @date 2016年5月23日 下午1:55:52
 */
public interface IOperationLogService {

    /**
     * @param operationLogInput
     * @return int
     * @Description 保存信息
     * @author bo.chen
     * @date 2016年5月23日 下午5:17:30
     */
    int save(OperationLogInput operationLogInput);
}
