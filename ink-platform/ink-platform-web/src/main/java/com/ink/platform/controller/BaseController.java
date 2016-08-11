package com.ink.platform.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.platform.api.model.SecLog;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecLogService;
import com.ink.platform.api.service.ISecUserService;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.realm.MyRealm;
import com.ink.sso.api.service.ISignOutService;


/**
 * 父级控制层:处理某些公共服务方法：类似id生成器，session获取，签退等
 * @author lww
 *
 */
@Controller("baseController")
public class BaseController  {
	@Autowired
	private ISecLogService logService;
	@Autowired
	private ISecUserService secUserService;
	@Autowired
	private IdCodeGenerator idworker;
	@Autowired
    private ISignOutService signOutService;
	@Autowired
	private MyRealm ream;
	
	protected static String key ="platform-shiro-userkey";
	protected static String resourceKey ="platform-shiro-resourcekey";
	protected static String roleKey ="platform-shiro-roleKey";
	protected static String singOutkey = "kkDSWKtAYfn8jhobwYpjhO3H1TBIi0YbMiJZeVf2qFyCvo3ittVnG5uURqg8ojzXPvkL8NgJI1QG8miEyTL79sWycgELS9nEwFd6";
	protected static String systemNo = "platform";    	
	
	
	YinkerLogger logger = YinkerLogger.getLogger(BaseController.class);
		/**
		 * 调用公共服务生成唯一id
		 * @return
		 */
		public long idwork(){
			
			return	Long.parseLong(idworker.getId());
		}
		/**
		 * sessionid
		 * @param request
		 * @return
		 */
		public String getSSOUserId(HttpServletRequest request){
			
		    String userId  = getSessionAttribute("USER_ID");
		    
	
			return userId;
		}
		/**
		 * 单点登录服务器获取session ,本项目存loginName
		 * @param request
		 * @return
		 */
		public String getSSOUserName(HttpSession session ){
			String userName = "";
			Assertion assertion = session != null ? (Assertion) session.getAttribute("ink_sso_user") : null;
			if(assertion!= null){
				userName = assertion.getPrincipal().getName();
			}
			return userName;
		
		}
		/**
		 * 从shiro框架获取session
		 * @version v1.0
		 */
		public String getSessionAttribute(String key){
			
			String result = null;
			
			Subject currentUser = SecurityUtils.getSubject();
			Object obj = currentUser.getSession().getAttribute(key);
			if(obj != null){
				result = obj.toString();
			}			
			return result;
		}
		/**
		 * 获取当前登录者对象
		 */
		public static String getPrincipal() {
			try {
				Subject subject = SecurityUtils.getSubject();
				String principal = (String) subject.getPrincipal();
				if (principal != null) {
					return principal;
				}
			} catch (UnavailableSecurityManagerException e) {

			} catch (InvalidSessionException e) {

			}
			return null;
		}
		/**
		 * 操作日志:新增
		 * @param action ：操作动作
		 * @param actionType :操作类型
		 * @param delFlag :删除标识
		 * @param status ：状态标识
		 * @param b ：操作结果
		 * @return
		 */
		public SecLog LogAdd( String action, String actionType ,int delFlag,int status,boolean b){
			 SecLog seclog=new SecLog();
		        seclog.setCreateTime(new Date());
		      //  seclog.setId(IdWorker.getNextId());
		        HttpServletRequest request =null;
		        //如果是通过单点登录，则取shiro的session
		        String operation = getSessionAttribute("USER_ID");
		        if(StringUtils.isBlank(operation)){
		        	operation =  getSSOUserId(request);
		        }		       
		        if(StringUtils.isNotBlank(operation)){
		        	 seclog.setUserId(Long.parseLong(operation));
		        }
		        seclog.setActionType(actionType);
		        seclog.setAction(action);
		        seclog.setDelFlag(delFlag);
		        seclog.setStatus(status);
		        String result =null;
				if(b){
					result = SecurityConstant.Result.SUCC;
				}else{
					result = SecurityConstant.Result.Erro;
				}
		        seclog.setResult(result);
		        logService.insertSelective(seclog);
				return seclog;
		}
		/**
		 * 查询个人信息
		 * @return
		 */
		public SecUser getuserMsg(String userId){		
			return secUserService.getUserText(userId);			
		}
		
		/**
		 * 签退用户:角色权限变更时
		 * @param roleId
		 */
		public void singOut(String roleId){
			logger.info("用户权限变更，开始签退","角色id"+roleId);
			Map<Object, Object> map = new HashMap<>();
			map.put("roleId", roleId);
			List<SecUser> usr =secUserService.selectUserParentOrgRole(map);
	       	for(SecUser user:usr){
		        boolean flag = signOutService.signOut(user.getLoginName(),DigestUtils.md5Hex(user.getLoginName()+singOutkey),systemNo,getPrincipal(),"");
		        logger.info(PlatformLoggerCnst.P,"签退结果"+flag,user.getLoginName());
		        ream.clearCachedAuthorizationInfo(user.getLoginName());
			}
		}
		/**
		 * 签退用户:角色权限变更时
		 * @param roleId
		 */
		public void singOuts(String userId){
			logger.info("用户权限变更，开始签退","用户id"+userId);
			SecUser user =	getuserMsg(userId);
		    boolean flag = signOutService.signOut(user.getLoginName(),DigestUtils.md5Hex(user.getLoginName()+singOutkey),systemNo,getPrincipal(),"");
		    logger.info(PlatformLoggerCnst.P,"签退结果"+flag,user.getLoginName());
		    ream.clearCachedAuthorizationInfo(user.getLoginName());
			
		}
}
