package com.ink.trade.controller.fillorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ink.trade.service.impl.FillOrderServiceImpl;
import com.ink.util.JobExecutorUtils;

@Controller
@RequestMapping("fillOrder")
public class FillOrderController {
    
    @Autowired
    private FillOrderServiceImpl fillOrderService;
    @RequestMapping("fillOrder")
    public String fillOrder(@RequestParam("key")String key, @RequestParam("unlockURL")String unlockURL, @RequestParam("jobSerialId")String jobSerialId){
        if(JobExecutorUtils.executeJob(fillOrderService, key, unlockURL,jobSerialId)){
            return "SUCCESS";
        }
        return "";
    }

}
