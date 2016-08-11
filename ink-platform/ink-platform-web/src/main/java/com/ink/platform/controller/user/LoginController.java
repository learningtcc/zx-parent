package com.ink.platform.controller.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.octo.captcha.service.CaptchaServiceException;
import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecUserService;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.imgCode.JCaptchaServiceSingleton;

/**
 * 登录操作相关控制层
 * @author lww
 *
 */
@Controller("loginController")
public class LoginController extends BaseController{
	
	@Autowired
	private ISecUserService secUserService;
	
	YinkerLogger loger = YinkerLogger.getLogger(LoginController.class);
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv =  new ModelAndView("WEB-INF/templates/index");
		String loginOutService = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/index";
		mv.addObject("loginOutService", loginOutService);
		return mv;
		
	}
	/**
	 * 登录页
	 * @param service
	 * @return
	 */
	
	@RequestMapping("index")
	public ModelAndView login(String service,HttpServletRequest request){
		loger.info(PlatformLoggerCnst.P_User, "欢迎进入登录页");
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isNotBlank(service)){
			String loginTicket = service.substring(service.indexOf("loginTicket=")+12, service.length());
			if(loginTicket != null && !"".equals(loginTicket)){
				mv.addObject("loginTicket",loginTicket);
			}
			loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "获取到单点登录许可"+loginTicket);
			mv.addObject("service",service);
			mv.setViewName("WEB-INF/templates/login");
			//此处用来控制session失效或签退后跳转页面
			if(!service.endsWith("login")){
				mv.setViewName("WEB-INF/templates/404");
			}
		}else{
			mv.setViewName("WEB-INF/templates/404");
		}
		
		return mv;		
	}
	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doLogin")	
	private ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "后台进行登录操作");

		
		ModelAndView mv  = new ModelAndView("WEB-INF/templates/success");
		String service = request.getParameter("service");
		mv.addObject("service",service);
		String loginTicket = service.substring(service.indexOf("loginTicket=")+12, service.length());
		if(loginTicket != null && !"".equals(loginTicket)){
			mv.addObject("loginTicket",loginTicket);
		}
		mv.addObject("service",service);
		/*if(StringUtils.isNotBlank(getSSOUserId(request))){
			return mv;
		}*/
		String loginName = request.getParameter("userName");
		String password =request.getParameter("password");
		password =DigestUtils.md5Hex(password);
		
		//获取验证码
		String enCode = request.getParameter("enCode");
		//获取sessionId
		HttpSession session=request.getSession(false);
		boolean c = false;
		try {
			loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "验证码校验"+ enCode);
			c = JCaptchaServiceSingleton.getInstance().validateResponseForID(session.getId(), enCode.toLowerCase());
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		if(!c){
			mv.setViewName("WEB-INF/templates/login");
			mv.addObject("errMsg", "验证码错误");
			return mv;
		}
		if(StringUtils.isAnyBlank(loginName,password)){
			mv.setViewName("WEB-INF/templates/login");
			mv.addObject("errMsg", "用户名或密码为空");
			return mv;
		}
		SecUser user  = new SecUser();
		user.setLoginName(loginName);
		user.setPassword(password);
		
		SecUser dbUser = secUserService.queryUserByuser(user);
		if(dbUser == null){
			mv.setViewName("WEB-INF/templates/login");
			mv.addObject("errMsg", "用户名或密码错误");
    		return mv;
		}
		//单点登录，登录认证
		
		mv.addObject("userName",dbUser.getLoginName());
		
		String name =dbUser.getUsername();
		mv.addObject("name",name );
		mv.addObject("userId",dbUser.getId() );
		//登录权限控制
		Subject currentUser = SecurityUtils.getSubject(); 	
		UsernamePasswordToken token = new UsernamePasswordToken(dbUser.getLoginName(),dbUser.getPassword());  			
	    try {
            currentUser.login(token);  
        }catch(UnknownAccountException uae){  
            request.setAttribute("message_login", "未知账户");  
        }catch(IncorrectCredentialsException ice){  
            request.setAttribute("message_login", "密码不正确");  
        }catch(LockedAccountException lae){  
            request.setAttribute("message_login", "账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
            request.setAttribute("message_login", "用户名或密码错误次数过多");  
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            ae.printStackTrace();  
            request.setAttribute("message_login", "用户名或密码不正确");  
        } 
	    boolean b = currentUser.isAuthenticated();
		  
	    //5. shiro验证是否登录成功  
        if(b){
	            currentUser.getSession().setAttribute("userName", dbUser.getUsername());
	            currentUser.getSession().setAttribute("USER_ID", dbUser.getId());
	            currentUser.getSession().setAttribute("service", service);
	            LogAdd(SecurityConstant.Action.LOGIN, SecurityConstant.Action_Type.USER, 
			    		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
        		return mv;
        }else{
            token.clear();
            currentUser.logout();
			return mv;
        } 
	}
	/**
	 * 错误页
	 * @return
	 */
	@RequestMapping("error")
	public ModelAndView error(){

		return new ModelAndView("WEB-INF/templates/404");
		
	}
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("logOut")
	public ModelAndView logOut(){
		Subject currentUser = SecurityUtils.getSubject(); 	
		currentUser.logout();
		return new ModelAndView("WEB-INF/templates/404");
	}

}
