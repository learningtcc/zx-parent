package com.ink.balance.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;

@RequestMapping("common")
@Controller
public class CommonController extends BaseController {

    /**
     * 访问JSP页面
     *
     * @param path
     * @return
     */
    @RequestMapping("/location")
    public ModelAndView location(String path) {
        return new ModelAndView(path);
    }

    /**
     * 访问JSP页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/menu")
    @ResponseBody
    public Object loadMenu() {
        String ctx = getContext();

        List<Map<String, Object>> urlList = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", 1);
//		map.put("pid", 0);
//		map.put("srcurl", ctx+"/user/list.do");
//		map.put("name", "用户管理");
//		urlList.add(map);
        //对账汇总
        map = new HashMap<String, Object>();
        map.put("id", 2);
        map.put("pid", 0);
        map.put("srcurl", "");
        map.put("name", "对账汇总");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 3);
        map.put("pid", 2);
        map.put("srcurl", ctx + "/CheckChannel/list.do");
        map.put("name", "对账状态查询");
        urlList.add(map);
        //对账差异
        map = new HashMap<String, Object>();
        map.put("id", 5);
        map.put("pid", 0);
        map.put("srcurl", "");
        map.put("name", "对账差异");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 6);
        map.put("pid", 5);
        map.put("srcurl", ctx + "/CheckDifference/list.do?handleStatus=0");
        map.put("name", "对账差异查询");
        urlList.add(map);
        //驻留 分平台 驻留和渠道驻留
        map = new HashMap<String, Object>();
        map.put("id", 9);
        map.put("pid", 0);
        map.put("srcurl", "");
        map.put("name", "交易数据查询");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 10);
        map.put("pid", 9);
        map.put("srcurl", ctx + "/ChannelData/list.do");
        map.put("name", "渠道交易查询");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 11);
        map.put("pid", 9);
        map.put("srcurl", ctx + "/PlatformData/list.do");
        map.put("name", "平台交易查询");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 20);
        map.put("pid", 0);
        map.put("srcurl", "");
        map.put("name", "渠道数据");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id",21);
        map.put("pid", 20);
        map.put("srcurl", ctx + "/ChannelParam/list.do");
        map.put("name", "渠道管理");
        urlList.add(map);
        //对账文件下载管理
//		map = new HashMap<String, Object>();
//		map.put("id", 12);
//		map.put("pid", 0);
//		map.put("srcurl", "");
//		map.put("name", "对账文件下载管理");
//		urlList.add(map);
//		map = new HashMap<String, Object>();
//		map.put("id", 13);
//		map.put("pid", 12);
//		map.put("srcurl", "");
//		map.put("name", "对账文件下载");
//		urlList.add(map);
        //调帐处理
        map = new HashMap<String, Object>();
        map.put("id", 14);
        map.put("pid", 0);
        map.put("srcurl", "");
        map.put("name", "调账处理");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 15);
        map.put("pid", 14);
        map.put("srcurl", ctx + "/CheckBalance/list.do");
        map.put("name", "调账信息查询");
        urlList.add(map);
        //手工对账
        map = new HashMap<String, Object>();
        map.put("id", 16);
        map.put("pid", 0);
        map.put("srcurl", "");
        map.put("name", "对账处理");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 18);
        map.put("pid", 16);
        map.put("srcurl", ctx + "/ManualReconciliation/toDown.do");
        map.put("name", "对账入库");
        urlList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 17);
        map.put("pid", 16);
        map.put("srcurl", ctx + "/ManualReconciliation/show.do");
        map.put("name", "手工对账");
        urlList.add(map);
        return urlList;
    }

}
