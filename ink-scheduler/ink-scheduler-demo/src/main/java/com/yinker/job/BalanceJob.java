package com.yinker.job;

import org.springframework.stereotype.Component;

/**
 * @author haoyunfeng
 * @date 2016/4/19
 */
@Component("balanceJob")
public class BalanceJob extends AbstractBaseJob {

    @Override
    public void execute() {
        System.out.println("************执行job*************");
    }


}
