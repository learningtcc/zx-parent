package com.ink.balance.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("login")
@Controller
public class LoginController extends BaseController {


    /**
     * 框架页面
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/frame")
    public ModelAndView frame(HttpSession session) {
        String userName = "...";
//		String userName =  (String)session.getAttribute("SESSION_USERNAME");
        if (userName != null) {
            return new ModelAndView("/login/frame");
        } 
        return new ModelAndView("/index");
//        else {
//            return new ModelAndView("/index");
//        }
    }

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("/index");
    }

    /**
     * 校验用户名密码
     */
    @RequestMapping(value = "/checkUser")
    @ResponseBody
    public Map<String, String> checkUser() {
        //返回结果map
        Map<String, String> resultMap = new HashMap<String, String>();

//		MonitorUserQuery monitorUserQuery = new MonitorUserQuery();
//		monitorUserQuery.setUserName(monitorUser.getUserName());
//		monitorUserQuery.setPassword(monitorUser.getPassword());
//		List<MonitorUser> userList = monitorUserManager.find(monitorUserQuery);
//
//		if (null == userList || userList.isEmpty()){
//			resultMap.put("result", "error");
//		}else{
//			MonitorUser user = userList.get(0);
//			session.setAttribute("SESSION_USERNAME", user.getUserName());
//			session.setAttribute("SESSION_USERID", String.valueOf(user.getUserId()));
//			session.setAttribute("SESSION_FULLNAME", user.getFullName());
//			resultMap.put("result","success");
//
//		}

        return resultMap;
    }

    /**
     * 登录页面
     */
    @RequestMapping(value = "/loginOut")
    public ModelAndView loginOut() {

//		session.removeAttribute("SESSION_USERNAME");
//		session.removeAttribute("SESSION_USERID");
//		session.removeAttribute("SESSION_FULLNAME");

        return new ModelAndView("/index");
    }
}
