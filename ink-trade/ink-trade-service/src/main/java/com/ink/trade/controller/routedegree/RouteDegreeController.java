package com.ink.trade.controller.routedegree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ink.trade.service.impl.AsileRouteImpl;
import com.ink.util.JobExecutorUtils;

@Controller
@RequestMapping("routedegree")
public class RouteDegreeController {
    @Autowired
    private AsileRouteImpl asileRoute;

    @RequestMapping("routedegree")
    public String degree(@RequestParam("key") String key, @RequestParam("unlockURL") String unlockURL, @RequestParam("jobSerialId")String jobSerialId) {
        if (JobExecutorUtils.executeJob(asileRoute, key, unlockURL,jobSerialId)) {
            return "SUCCESS";
        }
        return "";
    }
}
