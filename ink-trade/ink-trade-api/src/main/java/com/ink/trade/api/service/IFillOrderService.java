package com.ink.trade.api.service;

import com.ink.trade.api.model.in.FillOrderInput;
import com.ink.trade.api.model.out.FillOrderOutput;

/**
 * 补单服务接口（可以是人工补单，也可以是定时任务补单）
 * Created by huohb on 2016/5/11.
 */
public interface IFillOrderService {
    /**
     * 补单操作
     * @param input
     * @return
     */
    FillOrderOutput fillOrder(FillOrderInput input);
}
