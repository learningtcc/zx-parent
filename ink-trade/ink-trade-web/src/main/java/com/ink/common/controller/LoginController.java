package com.ink.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.platform.api.dubbo.IUserResourceService;
import com.ink.platform.api.model.SecUser;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("login")
@Controller
public class LoginController extends BaseController {

	@Autowired
	private IUserResourceService userResourceService;


	/** 登录页面*/
	@RequestMapping(value="/login")
	public ModelAndView login(String service,HttpSession session){
		ModelAndView model = new ModelAndView();
        model.setViewName("/index");
        session.setAttribute("service",service);
        return  model;
	}
    /** 主页面*/
    @RequestMapping(value="/main")
    public ModelAndView main(){
        return  new ModelAndView("/login/frame");
    }
    
    /**
	 * @Description: ssologin
	 * @param session
	 * @param refPath
	 * @return
	 */
	@RequestMapping(value = "/ssologin")
	public ModelAndView ssologin(HttpSession session, HttpServletRequest request, String username) {
		ModelAndView model = new ModelAndView();
		model.addObject("service",session.getAttribute("service"));
        model.addObject("username",username);
        model.setViewName("/login/ssologin");
        return model;
	}
    
    /** 校验用户名密码 */
	@RequestMapping(value = "/checkUser")
	@ResponseBody
	public Map<String, String> checkUser(HttpSession session, HttpServletRequest request, String userName, String password) {
		//返回结果map
        Map<String,String> resultMap = new HashMap<String, String>();
        //验证登录
        SecUser secUser = userResourceService.loginServeice(userName, password);
        if (null != secUser && null != secUser.getUsername() ) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "error");
        }
        return resultMap;
	}
	
}
