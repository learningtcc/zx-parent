package com.ink.platform.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ink.platform.util.StrSubStringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.model.SecUserOrgRelation;
import com.ink.platform.api.service.ISecRoleService;
import com.ink.platform.api.service.ISecUserOrgRelationService;
import com.ink.platform.api.service.ISecUserService;
import com.ink.platform.api.util.ObjectJson;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.util.JsonUtil;

/**
 * 用户信息控制层
 * @author lww
 *
 */
@Controller("secUserController")
public class SecUserController  extends BaseController{

	@Autowired
	private ISecUserService secUserService;
	@Autowired
	private ISecUserOrgRelationService secUserOrgRelationService;
	@Autowired
	private  ISecRoleService  iSecRoleService;
	
	@Autowired
	private IdCodeGenerator idworker;
	YinkerLogger loger = YinkerLogger.getLogger(SecUserController.class);

	/**
	 * 组织机构树：点击用户信息控制方法
	 * @param id
	 * @return
	 */
	@RequestMapping("userInfo")
	@ResponseBody
	public ModelAndView userInfo(String  id){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User,"用户userid="+id +"组织机构下，用户基本信息");
		ModelAndView mv =  new ModelAndView("WEB-INF/templates/org/orgInfo");
		//查询人员基本信息
		SecUser  secUser =secUserService.getUserText(id);	
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User,"用户userid="+id +"用户基本信息"+secUser);

		//查询：人员上机机构信息:有效的
		Map<Object, Object> map  = new HashMap<>();
		map.put("userId", secUser.getId().toString());
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		List<SecUser> parentOrgList = secUserService.selectUserParentOrg(map);
		loger.info(PlatformLoggerCnst.P_User,"用户userid="+id +"用户基本信息+上级机构"+parentOrgList);

		mv.addObject("secUser", secUser);
		mv.addObject("parentOrg", parentOrgList);
		return mv ;
	}
	/**
	 * 用户详情
	 * @param id
	 * @return
	 */
	@RequestMapping("userDetails")
	@ResponseBody
	public ModelAndView  userDetails(String  id ,int titleCode){
		loger.info(PlatformLoggerCnst.P_User, "用户详情");

		//查询人员基本信息
		SecUser  secUser =secUserService.getUserText(id);
		loger.info(PlatformLoggerCnst.P_User,"用户userid="+id ,"用户基本信息"+secUser);

		//查询：人员上机机构信息
		Map<Object, Object> map  = new HashMap<>();
		map.put("userId", id);
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		//查询用户组织
		List<SecUser> parentOrgList = secUserService.selectUserParentOrg(map);
		loger.info(PlatformLoggerCnst.P_User,"用户userid="+id ,"用户基本信息，组织机构"+secUser);

		//查询用户角色
		map.put("titleCode", titleCode);
		List<SecRole>  roleList  = iSecRoleService.selectUserRole(map);
		loger.info(PlatformLoggerCnst.P_User,"用户userid="+id ,"用户基本信息，角色信息"+roleList);

		List<Object> list  =new ArrayList<>();
		List<Object> roles  =new ArrayList<>();
		for(SecRole secRole :roleList){
			String roleName = secRole.getRoleName();
			roles.add(roleName);
		}
		for(SecUser secUsers:parentOrgList){	
			SecUser	 creator = secUserService.selectCreatorInfo(secUsers);
			loger.info(PlatformLoggerCnst.P_User,"用户userid="+id ,"本用户创建者信息"+creator);

			secUser.setCreator(creator.getUsername());	
			list.add(secUsers.getOrgName());	
		}
		String parenOrg  = StrSubStringUtil.strMosaic(list);
		ModelAndView mv = new ModelAndView("WEB-INF/templates/user/userDetails");
		mv.addObject("secUser", secUser);
		mv.addObject("roles", roles);
		mv.addObject("parenOrg", parenOrg);
		return mv;
	}
	/**
	 * 组织机构下：人员信息（个人信息，上级机构信息,角色信息，创建人信息）
	 * @param id
	 * @return
	 */
	@RequestMapping("orgUserBasicInfo")
	@ResponseBody
	public String  orgUserBasicInfo(String  userId,String parentOrgId){

		Map<Object, Object> map  = new HashMap<>();
		map.put("userId", userId);
		map.put("parentOrgId", parentOrgId);
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		List<SecUser> parentOrgList = secUserService.selectUserParentOrg(map);
		loger.info(PlatformLoggerCnst.P_User, "组织机构下，用户基本信息"+parentOrgList);

		List<Object> list = new ArrayList<>();

		for(SecUser secUser:parentOrgList){
			//创建人
			SecUser	 creator = secUserService.selectCreatorInfo(secUser);
			loger.info(PlatformLoggerCnst.P_User,"用户userid="+userId ,"本用户创建者信息"+creator);

			secUser.setCreator(creator.getUsername());	

			map.put("titleCode", secUser.getTitleIdentify());
			//查询用户角色
			List<SecRole>  roleList  = iSecRoleService.selectUserRole(map);
			for(SecRole secRole :roleList){
				String roleName = secRole.getRoleName();
				list.add(roleName);
			}
			loger.info(PlatformLoggerCnst.P_User, "组织机构下，用户基本信息：角色信息"+roleList);

			//角色
			if(list.size()>0){
				secUser.setRoleName(StrSubStringUtil.strMosaic(list));
			}
		}
		return JsonUtil.toCompatibleJSONString(parentOrgList);
	}
	/**
	 * 组织机构下： 新增用户
	 * @param user
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public String addUser(SecUser user ,HttpServletRequest request){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，用户新增"+user);
		HashMap<Object, Object> result = new HashMap<>();
		Map<Object, Object> map  = new HashMap<>();
		map.put("loginName", user.getLoginName());
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		SecUser a  =secUserService.queryUserByMap(map);
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "查询信息是否已被使用"+a);
		if(a!=null){
			result.put("rstType", SecurityConstant.Result.b_Erro);
			result.put("errMsg", "登录名已被使用");
			return JsonUtil.toCompatibleJSONString(result);
		}
		//获取默认组织机构id
		
		user.setId(Long.parseLong(idworker.getId()));
		user.setCreateTime(new Date());
		user.setDelFlag(SecurityConstant.Delete_Type.No);
		String url ="userInfo";
		user.setUrl(url);
		Long sessionId = Long.parseLong(getSSOUserId(request) );
		user.setCreatorId(sessionId);
		user.setPassword(DigestUtils.md5Hex("12345"));//默认新增用户密码
		boolean b =false;
		 b =secUserService.insertUser(user);
			loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "新增用户结果"+b);

		//新增用户所属组织机构
		SecUserOrgRelation secUserOrgRelation = new SecUserOrgRelation();
		if(b){
			secUserOrgRelation.setId(idwork());
			secUserOrgRelation.setUserId(user.getId());
			secUserOrgRelation.setOrgId( Long.parseLong(user.getOrgId()));
			secUserOrgRelation.setCreateTime(new Date());
			secUserOrgRelation.setDelFlag(SecurityConstant.Delete_Type.No);
			secUserOrgRelation.setCreatorId(sessionId);
			secUserOrgRelation.setStatus(SecurityConstant.Status_Type.Enable_Status);;
			secUserOrgRelation.setRemark(SecurityConstant.REMARK.USR);
			secUserOrgRelation.setTitle(user.getTitle());
			//新增为主岗
			secUserOrgRelation.setTitleIdentify(SecurityConstant.title_Type.major_Job);
			b=	secUserOrgRelationService.addOrgViceJob(secUserOrgRelation);
			loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "新增用户组织机构关联关系结果"+b);

		}
		if(b){
			result.put("rstType", SecurityConstant.Result.b_SUCC);
			result.put("errMsg", "操作成功");
		}else{
			result.put("rstType", SecurityConstant.Result.b_Erro);
			result.put("errMsg", "新增异常");
		}
		return JsonUtil.toCompatibleJSONString(result);

	}
	/**
	 * 编辑用户:角色，上机机构
	 * @param user
	 * @return
	 */
	@RequestMapping("editUserMsg")
	@ResponseBody
	public boolean editUserMsg(SecUser user,HttpServletRequest request){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，用户编辑"+user);
		//若有删除标识，则拒绝更新操作
		user.setUpdateTime(new Date());
		boolean b = secUserService.updateUserByMap(user);
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "用户编辑结果"+b);
		LogAdd(SecurityConstant.Action.Edit, SecurityConstant.Action_Type.USER, 
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
		return b;

	}
	/**
	 * 逻辑删除用户
	 * @param user
	 * @return
	 */

	@RequestMapping("deleteUser")
	@ResponseBody
	public boolean deleteUser(String userId,String orgId,int titleIdentify){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，用户删除"+"用户id"+userId+"组织id"+orgId);
		boolean b = false;
		Map<Object, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("orgId",orgId );
		map.put("delFlag", SecurityConstant.Delete_Type.yes);
		map.put("status", SecurityConstant.Status_Type.Cancel_Status);
		//若删除的是主岗，则删除用户,并删除关联关系
		if(titleIdentify==SecurityConstant.title_Type.major_Job){
			SecUser user  =new SecUser();
			user.setId(Long.parseLong(userId));
			user.setDelFlag(SecurityConstant.Delete_Type.yes);
			user.setStatus(SecurityConstant.Status_Type.Disable_Status);
			b=secUserService.updateUserByMap(user);
			loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，主岗用户用户删除"+b);

			LogAdd(SecurityConstant.Action.DELLF, SecurityConstant.Action_Type.USER, 
					SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
			b =secUserOrgRelationService.updateUserOrgRelation(map);
			loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，主岗用户删除关联关系"+b);

		}
		//若删除的额是副岗，则只删除关联关系
		if(titleIdentify==SecurityConstant.title_Type.deputy_Job){			
			b =secUserOrgRelationService.updateUserOrgRelation(map);
			loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，副岗用户删除关联关系"+b);

		}
		return b;

	}
	/**
	 * 密码重置
	 * @param userId
	 * @return
	 */
	@RequestMapping("resetPwd")
	@ResponseBody
	public boolean resetPwd(String userId){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，密码重置"+userId);

		SecUser user = new SecUser();
		user.setId(Long.parseLong(userId));
		user.setPassword(DigestUtils.md5Hex("12345"));
		boolean b = secUserService.updateUserByMap(user);
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，密码重置结果"+b);

		LogAdd(SecurityConstant.Action.ResetPWD, SecurityConstant.Action_Type.USER, 
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
		return b;
	}
	/**
	 * 密码修改
	 * @param userId
	 * @return
	 */
	@RequestMapping("editPwd")
	@ResponseBody
	public boolean editPwd(HttpServletRequest request){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "用户修改密码");

		String pwd =request.getParameter("pwd");
		SecUser user = new SecUser();
		user.setId(Long.parseLong(getSSOUserId(request)));
		user.setPassword(DigestUtils.md5Hex(pwd));
		boolean b = secUserService.updateUserByMap(user);
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "用户修改密码结果"+b);

		LogAdd(SecurityConstant.Action.ResetPWD, SecurityConstant.Action_Type.USER, 
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
		return b;
	}
	/**
	 * 设置副岗
	 * @return
	 */
	@RequestMapping("setVicOrg")
	public ModelAndView setVicOrg(String userId){
		ModelAndView mv = new ModelAndView("WEB-INF/templates/user/setViceJob");
		mv.addObject("userId", userId);
		return mv;

	}
	/**
	 * 显示：组织机构下所有用户
	 * @return
	 */
	@RequestMapping("allUserInfo")
	@ResponseBody
	public String allUserInfo(String orgId,int rows,int page){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，所有用户"+orgId);

		if(StringUtils.isBlank(orgId)){
			return null;
		}
		PageVO<SecOrg> pagevo=new PageVO<SecOrg>();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
		List<SecUser> allUserInfo = secUserService.selectAllUserByOrgList(pagevo, orgId);
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，所有用户"+allUserInfo);

		for(SecUser user:allUserInfo){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("userId", user.getId().toString());
			map.put("titleCode", user.getTitleIdentify());
			//查询用户角色
			List<SecRole>  roleList  = iSecRoleService.selectUserRole(map);
		//	loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下，用户角色"+allUserInfo);

			List<Object> list = new ArrayList<>();

			for(SecRole secRole :roleList){
				String roleName = secRole.getRoleName();
				list.add(roleName);
			}
			//把所有角色转成字符串拼接
			if(list.size()>0){
				user.setRoleName(StrSubStringUtil.strMosaic(list));
			}
			SecUser	 creator = secUserService.selectCreatorInfo(user);
			user.setCreator(creator.getUsername());	
		}

		return JsonUtil.toCompatibleJSONString(allUserInfo);

	}	
	/**
	 * 全部用户：分页显示，此方法由于用可能有多机构多角色，故分页方法重写
	 * @param rows
	 * @param page
	 * @param userP
	 * @return
	 */
	@RequestMapping("allUser")
	@ResponseBody
	public String allUser(int rows,int page, SecUser userP){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "所有用户,未删除的");
		PageVO<SecUser> pagevo=new PageVO<SecUser>();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
		Map<Object, Object> map  = new HashMap<>();
		map.put("statusA", userP.getStatus());
		if(userP.getStatus()==null){
			int statusA  =SecurityConstant.Status_Type.Disable_Status;
			int statusB = SecurityConstant.Status_Type.Cancel_Status;
			map.put("statusA", statusA);
			map.put("statusB", statusB);
		}
		List<SecUser> use =new ArrayList<>();
		List<SecUser> us =secUserService.selectAllUser(map);
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "所有用户,未删除的"+us);

		for(SecUser allUser: us){
			map.put("userId", allUser.getId());
			if(allUser.getStatus().equals(SecurityConstant.Status_Type.Cancel_Status)){
				use.add(allUser);
				continue;
			}
			List<SecUser> parentOrgList = secUserService.selectUserParentOrg(map);
			if(parentOrgList.size()>0){
				for(SecUser user:parentOrgList){
					//查询用户角色
					map.put("userId", user.getId().toString());
					map.put("titleCode", user.getTitleIdentify());
					List<SecRole>  roleList  = iSecRoleService.selectUserRole(map);
					List<Object> list = new ArrayList<>();
					for(SecRole secRole :roleList){
						String roleName = secRole.getRoleName();
						list.add(roleName);
					}
					//把所有角色转成字符串拼接
					if(list.size()>0){
						user.setRoleName(StrSubStringUtil.strMosaic(list));
					}
					SecUser	 creator = secUserService.selectCreatorInfo(user);
					user.setCreator(creator.getUsername());	
					use.add(user);
				}
			}
		}
		/**
		 * 处理分页返回数据
		 */
		List<SecUser> dataB= new ArrayList<>();
		if(use.size()>rows*page){
			for(int i= rows*(page-1); i<rows*page;i++){
				dataB.add(use.get(i));
			}
		}else{
			for(int i= rows*(page-1); i<use.size();i++){
				dataB.add(use.get(i));
			}
		}
		
		ObjectJson objectJson=new ObjectJson();
		objectJson.setRows(dataB);
		objectJson.setTotal(use.size());
		return JsonUtil.toCompatibleJSONString(objectJson);

	}
	/**
	 *用户信息： 查询创建人信息
	 * @return
	 */
	@RequestMapping("queryCreator")
	@ResponseBody
	public String queryCreator(String userId){
		SecUser  secUser =secUserService.getUserText(userId);

		return JsonUtil.toCompatibleJSONString(secUser.getUsername());

	}
	/**
	 * 个人信息
	 * @return
	 */
	@RequestMapping("userMsg")
	@ResponseBody
	public String userMsg(String userId){
		SecUser  secUser =secUserService.getUserText(userId);

		return JsonUtil.toCompatibleJSONString(secUser.getUsername());

	}

	/**
	 * 按条件查询：用户信息
	 * @return
	 */
	@RequestMapping("fuzzyQueryUser")
	@ResponseBody
	public String fuzzyQueryUser(HttpServletRequest request,int rows,int page){
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "请您条件查询用户信息");

		String disable = request.getParameter("disable");
		String cancel = request.getParameter("cancel");
		String queryName = request.getParameter("queryName");
		String title = request.getParameter("title");
		PageVO<SecUser> pagevo=new PageVO<SecUser>();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
		Map<Object, Object> map  = new HashMap<>();

		if(StringUtils.isNotBlank(disable) && disable.equals("on")){
			int statusA  =SecurityConstant.Status_Type.Disable_Status;
			map.put("statusA", statusA);
		}
		if(StringUtils.isNotBlank(cancel) && cancel.equals("on")){
			int statusB = SecurityConstant.Status_Type.Cancel_Status;
			map.put("statusB", statusB);
		}
		if(StringUtils.isNotBlank(queryName)){
			map.put("userName", queryName);
		}	
		if(StringUtils.isNotBlank(title)){
			map.put("title", title);
		}	
		map.put("pageVO", pagevo);
		List<SecUser> parentOrgList = secUserService.selectUserParentOrg(map);
		loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "请您条件查询用户信息"+parentOrgList);

		int total = secUserService.selectListCount(map);
		pagevo.setTotal(total);
		if(parentOrgList.size()>0){
			for(SecUser user:parentOrgList){
				//查询用户角色
				map.put("userId", user.getId().toString());
				List<SecRole>  roleList  = iSecRoleService.selectUserRole(map);
				List<   Object> list = new ArrayList<>();
				for(SecRole secRole :roleList){
					String roleName = secRole.getRoleName();
					list.add(roleName);
				}
				//把所有角色转成字符串拼接
				if(list.size()>0){
					user.setRoleName(StrSubStringUtil.strMosaic(list));
				}
				SecUser	 creator = secUserService.selectCreatorInfo(user);
				user.setCreator(creator.getUsername());	
			}
		}else{
			parentOrgList=	secUserService.selectAllUser(map);
		}
		
		ObjectJson objectJson=new ObjectJson();
		objectJson.setRows(parentOrgList);
		objectJson.setTotal(pagevo.getTotal());
		return JsonUtil.toCompatibleJSONString(objectJson);

	}
}
