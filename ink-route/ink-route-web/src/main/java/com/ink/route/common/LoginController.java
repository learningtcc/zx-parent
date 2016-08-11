package com.ink.route.common;

import com.ink.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("login")
@Controller
public class LoginController extends BaseController {


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


    
    /** 校验用户名密码 */
	@RequestMapping(value = "/checkUser")
	@ResponseBody
	public Map<String, String> checkUser(HttpSession session, HttpServletRequest request, String username, String password) {
		//返回结果map
        Map<String,String> resultMap = new HashMap<String, String>();
        //验证登录
//        SecUser secUser = userResourceService.loginServeice(userName, password);

        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            if ("admin".equals(username) && "admin".equals(password)){
                resultMap.put("result", "success");
            }
        }else{
            resultMap.put("result","error");
        }

        return resultMap;
	}
	
}
