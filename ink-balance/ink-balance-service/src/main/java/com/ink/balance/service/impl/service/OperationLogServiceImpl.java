package com.ink.balance.service.impl.service;

import com.ink.balance.api.constants.LoggerCnst;
import com.ink.balance.api.model.in.OperationLogInput;
import com.ink.balance.api.service.IOperationLogService;
import com.ink.balance.core.manager.IOperationLogManager;
import com.ink.balance.core.po.OperationLog;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author bo.chen
 * @Description 操作日志的接口实现类
 * @date 2016年5月23日 上午11:12:20
 */
@Service("operationLogService")
public class OperationLogServiceImpl implements IOperationLogService {

    YinkerLogger log = YinkerLogger.getLogger(OperationLogServiceImpl.class);

    @Autowired
    @Qualifier("operationLogManager")
    private IOperationLogManager operationLogManager;

    /**
     * @param operationLogInput
     * @return int
     * @Description 保存信息
     * @author bo.chen
     * @date 2016年5月23日 下午5:17:30
     */
    @Override
    public int save(OperationLogInput operationLogInput) {
        log.info(LoggerCnst.BALANCE_QUERY, LoggerCnst.DIFFERENCE_HANDLE, "保存操作日志信息 operationLogInput:" + operationLogInput.toString(), null);
        OperationLog operationLog = BeanCopyConverter.converterClass(operationLogInput, OperationLog.class);
        return operationLogManager.save(operationLog);
    }
}
