package com.ink.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    
    /** 框架页面 */
	@RequestMapping(value = "/frame")
	public ModelAndView frame(HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("loginOutService", session.getAttribute("service"));
		return new ModelAndView("/login/frame");
	}

    /**
     * 访问JSP页面
     * 
     * @param path
     * @return
     */
    @RequestMapping(value = "/menu")
    @ResponseBody
    public Object loadMenu() {
    	
    	 String ctx = getContext();

         List<Map<String, Object>> urlList = new ArrayList<Map<String, Object>>();

         Map<String, Object> map = new HashMap<String, Object>();

         // 基础数据
         map = new HashMap<String, Object>();
         map.put("id", 1);
         map.put("pid", 0);
         map.put("srcurl", "");
         map.put("name", "基础数据");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 2);
         map.put("pid", 1);
         map.put("srcurl", ctx + "/BasicBank/list.do");
         map.put("name", "银行信息");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 3);
         map.put("pid", 1);
         map.put("srcurl", ctx + "/AsileInfo/list.do");
         map.put("name", "渠道信息");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 4);
         map.put("pid", 1);
         map.put("srcurl", ctx + "/AsileResCode/list.do");
         map.put("name", "渠道响应码");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 5);
         map.put("pid", 1);
         map.put("srcurl", ctx + "/MchBank/list.do");
         map.put("name", "商户银行管理");
         urlList.add(map);
         
         /*渠道信息*/
         map = new HashMap<String, Object>();
         map.put("id", 100);
         map.put("pid", 0);
         map.put("srcurl", "");
         map.put("name", "渠道配置");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 101);
         map.put("pid", 100);
         map.put("srcurl", ctx+"/BasicResCode/list.do");
         map.put("name", "平台响应码");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 102);
         map.put("pid", 100);
         map.put("srcurl", ctx + "/BankcardBin/list.do");
         map.put("name", "cardbin管理");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 103);
         map.put("pid", 100);
         map.put("srcurl", ctx + "/AsileBusiness/list.do");
         map.put("name", "渠道接口调用方式");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 104);
         map.put("pid", 100);
         map.put("srcurl", ctx + "/AsileBusinessSupport/list.do");
         map.put("name", "渠道支持支付方式");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 105);
         map.put("pid", 100);
         map.put("srcurl", ctx + "/AuthChannelPriority/list.do");
         map.put("name", "鉴权渠道优先级");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 106);
         map.put("pid", 100);
         map.put("srcurl", ctx + "/MchAuth/list.do");
         map.put("name", "商户支付方式权限控制");
         urlList.add(map);
         
         
         //路由配置
         map = new HashMap<String, Object>();
         map.put("id", 1000);
         map.put("pid", 0);
         map.put("srcurl", "");
         map.put("name", "路由规则配置");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 1001);
         map.put("pid", 1000);
         map.put("srcurl", ctx + "/AsileBank/list.do");
         map.put("name", "渠道支持银行");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 1002);
         map.put("pid", 1000);
         map.put("srcurl", ctx + "/AsileBankRuntime/list.do");
         map.put("name", "渠道服务时间");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 1003);
         map.put("pid", 1000);
         map.put("srcurl", ctx + "/AsileBankTemp/list.do");
         map.put("name", "临时路由配置");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 1004);
         map.put("pid", 1000);
         map.put("srcurl", ctx + "/AsileBank/priority.do");
         map.put("name", "路由降级");
         urlList.add(map);
         
         //单据管理
         map = new HashMap<String, Object>();
         map.put("id", 1100);
         map.put("pid", 0);
         map.put("srcurl", "");
         map.put("name", "交易管理");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 1101);
         map.put("pid", 1100);
         map.put("srcurl", ctx + "/TradeOrder/list.do");
         map.put("name", "订单查询");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 1102);
         map.put("pid", 1100);
         map.put("srcurl", ctx + "/Auth/list.do");
         map.put("name", "绑卡信息查询");
         urlList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("id", 1103);
         map.put("pid", 1100);
         map.put("srcurl", ctx + "/AuthOrder/list.do");
         map.put("name", "银行卡验证查询");
         urlList.add(map);
         
         return urlList;
    }

}
