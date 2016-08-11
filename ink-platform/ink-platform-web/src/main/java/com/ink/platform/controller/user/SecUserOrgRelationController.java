package com.ink.platform.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ink.platform.util.StrSubStringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.model.SecUserOrgRelation;
import com.ink.platform.api.service.ISecOrgService;
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
 * 用户组织机构管理控制层
 * @author lww
 *
 */
@Controller("secUserOrgRelationController")
public class SecUserOrgRelationController  extends BaseController{

	@Autowired
	private ISecUserOrgRelationService userOrgRelationService;
	@Autowired
	private ISecUserService secUserService;
	@Autowired
	private ISecOrgService  iSecOrgService;
	@Autowired
	private  ISecRoleService  iSecRoleService;
	
	YinkerLogger logger = YinkerLogger.getLogger(SecUserOrgRelationController.class);

	/**
	 * 所有组织机构：菜单树。设置副岗使用
	 * @return
	 */
	@RequestMapping("addOrgVic")
	@ResponseBody
	public String addOrgVic(String userId){
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "勾选用户所属机构项"+userId);
		/*if(StringUtils.isBlank(userId)){
			return null;
		}*/
		
		Map<Object, Object> map  = new HashMap<>();
		map.put("status", SecurityConstant.Status_Type.Enable_Status);

		//所有的组织机构
		List<SecOrg> secOrgList =	iSecOrgService .selectOrgMsg(map);
		//查询该用户已有的组织机构
		map.put("userId", userId);
		List<SecUser> parentOrgList = secUserService.selectUserParentOrg(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "上级机构信息"+parentOrgList);

		for(SecOrg secOrgs : secOrgList){
			String orgIds = secOrgs.getId()+"";
			for(SecUser secUserOrgs : parentOrgList){
				String parentOrg =secUserOrgs.getOrgId();
				int titileType = secUserOrgs.getTitleIdentify();//组织机构类型：1主岗。副岗
				if(parentOrg.equals(orgIds)){
					secOrgs.setChecked(true);
					if(StringUtils.isNotBlank(String.valueOf(titileType)) ){
						secOrgs.setTitleIdentify(titileType);
					}
				}
			}
		}
		return JsonUtil.toCompatibleJSONString(secOrgList);
	}
	/**
	 * 设置副岗
	 * @return
	 */
	@RequestMapping("addOrgViceJob")
	@ResponseBody
	public String addOrgViceJob(String userId, String orgId,HttpServletRequest request){
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "设置副岗：针对用户"+userId);
		//结果集
		HashMap<Object, Object> result  = new HashMap<>();
		//遍历选中的组织机构id
		List<String> orgIdList = StrSubStringUtil.strSubStr(orgId);
		//忽略已拥有的组织机构
		//查询该用户已有的组织机构

		boolean b =false;
		//去重    		每次都去查询拥有的 有效组织机构
		Map<Object, Object> map  = new HashMap<>();
		map.put("userId", userId);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		//用户已拥有的组织机构
		List<SecUser> parentOrgList = secUserService.selectUserParentOrg(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "用户所属的上级机构"+parentOrgList);
		if(parentOrgList.size()>0){
			for (SecUser userOrg : parentOrgList) {
				String userOwnOrg = userOrg.getOrgId();
				//使用迭代器删除重复角色
				Iterator<String> iterator = orgIdList.iterator();
				while(iterator.hasNext()){  
					//如果勾选组织与已拥有组织匹配，则去重
					String id = iterator.next();
					if (userOwnOrg.equals(id)) {
						iterator.remove();   //注意这个地方
						userOwnOrg =null;
						break;
					}
				}
				//要删除的副岗
				if(StringUtils.isNotBlank(userOwnOrg)){
					Map<Object, Object> delMap =new HashMap<>();
					delMap.put("userId", userId);
					delMap.put("status", SecurityConstant.Status_Type.Disable_Status);
					delMap.put("delFlag", SecurityConstant.Delete_Type.yes);
					delMap.put("orgId", userOwnOrg);
					b = userOrgRelationService.updateUserOrgRelation(delMap);
					logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "删除用户所属的上级机构"+userOwnOrg);
					if(!b){
						result.put("rstType", SecurityConstant.Result.b_Erro);
						result.put("rstMsg", "删除副岗失败");
						return JsonUtil.toCompatibleJSONString(result);
					}
				}
			} 
		}

		for(String orgIds:orgIdList){
			SecUserOrgRelation secUserOrgRelation = new SecUserOrgRelation();
			secUserOrgRelation.setId(idwork());
			secUserOrgRelation.setOrgId(Long.parseLong(orgIds));
			secUserOrgRelation.setUserId(Long.parseLong(userId));
			secUserOrgRelation.setCreateTime(new Date());
			String createid = getSSOUserId(request);
			secUserOrgRelation.setCreatorId(Long.parseLong(createid));
			secUserOrgRelation.setDelFlag(SecurityConstant.Delete_Type.No);
			secUserOrgRelation.setStatus(SecurityConstant.Status_Type.Enable_Status);
			secUserOrgRelation.setRemark(SecurityConstant.REMARK.USR);
			secUserOrgRelation.setTitleIdentify(SecurityConstant.title_Type.deputy_Job);
			b = userOrgRelationService.addOrgViceJob(secUserOrgRelation);
			logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "新增用户所属的上级机构"+orgIds);

			if(!b){
				result.put("rstType", SecurityConstant.Result.b_Erro);
				result.put("rstMsg", "设置副岗失败");
				return JsonUtil.toCompatibleJSONString(result);
			}
		}

		LogAdd(SecurityConstant.Action.ADD, SecurityConstant.Action_Type.USER, 
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status,b );
		result.put("rstType", SecurityConstant.Result.b_SUCC);
		result.put("rstMsg", "操作成功");
		return JsonUtil.toCompatibleJSONString(result);		
	}
	/**
	 * 用户所属组织机构改变
	 * @return
	 */
	@RequestMapping("moveOrgView")
	@ResponseBody
	public ModelAndView moveOrgView( String userId){
		ModelAndView  mv =new ModelAndView("WEB-INF/templates/user/moveOrg");
		mv.addObject("userId", userId);
		return mv;

	}
	/**
	 * 移动所属组织机构：针对用户
	 * @return
	 */
	@RequestMapping("moveOrg")
	@ResponseBody
	public boolean moveOrg(String id ,String targetId,String parentOrgId){
		//id:用户id，改变组织id
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "移动所属组织机构：针对用户"+id);

		Map<Object, Object> map  =new HashMap<>();
		map.put("parentOrgId", parentOrgId);
		map.put("userId", id);
		map.put("targetId", targetId);//目标节点
		boolean b =userOrgRelationService.updateUserOrgRelation(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "移动所属组织机构：操作结果",b);
		LogAdd(SecurityConstant.Action.ADD, SecurityConstant.Action_Type.USER, 
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status,b );
		return b;

	}
	/**
	 * 显示：组织机构下所有用户
	 * @return
	 */
	@RequestMapping("allUserUderOrg")
	@ResponseBody
	public String allUserUderOrg(String orgId,int rows,int page){
		if(orgId.equals("undefined")){
			return null;
		}
		List<SecUser> data= new ArrayList<>();
		//查询：人员上机机构信息:有效的
		Map<Object, Object> maps  = new HashMap<>();
		maps.put("orgId", orgId);
		maps.put("delFlag", SecurityConstant.Delete_Type.No);
		maps.put("status", SecurityConstant.Status_Type.Enable_Status);
		SecOrg  org =iSecOrgService.selectChildOrg(maps);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "组织机构下所有用户"+org);

		List<SecOrg> getChildren  =org.getChildren();
		
		PageVO<SecOrg> pagevo=new PageVO<SecOrg>();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
		List<SecUser> allUserInfo = secUserService.selectAllUserByOrgList(null, orgId);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "用户组织信息"+org,allUserInfo);
		data.addAll(allUserInfo);
		//查询子用户
		getUser(getChildren,data,pagevo);
		for(SecUser user:data){
			HashMap<Object, Object> map = new HashMap<>();
			map.put("userId", user.getId().toString());
			map.put("titleCode", user.getTitleIdentify());
			//查询用户角色
			List<SecRole>  roleList  = iSecRoleService.selectUserRole(map);
			logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "用户角色信息"+roleList);

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
			logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "用户创建人信息"+creator);
			user.setCreator(creator.getUsername());	
		}
		List<SecUser> dataB= new ArrayList<>();
		if(data.size()>rows*page){
			for(int i= rows*(page-1); i<rows*page;i++){
				dataB.add(data.get(i));
			}
		}else{
			for(int i= rows*(page-1); i<data.size();i++){
				dataB.add(data.get(i));
			}
		}
		
		ObjectJson objectJson=new ObjectJson();
		objectJson.setRows(dataB);
		objectJson.setTotal(data.size());
		return JsonUtil.toCompatibleJSONString(objectJson);

	}
	/**
	 * 递归获取：下属组织机构
	 * @param getChildren
	 * @param data
	 * @param pagevo
	 */
	private void getUser(List<SecOrg> getChildren, List<SecUser> data, PageVO<SecOrg> pagevo) {
		for(SecOrg secOrg:getChildren){
			List<SecUser> allUser = secUserService.selectAllUserByOrgList(pagevo, secOrg.getId().toString());
			data.addAll(allUser);
			doGetChild(secOrg,data,pagevo);
		}
		
	}
	/**
	 * 递归获取：下属组织机构
	 * @param getChildren
	 * @param data
	 * @param pagevo
	 */
	private void doGetChild(SecOrg secOrg, List<SecUser> data, PageVO<SecOrg> pagevo) {
		for(SecOrg org:secOrg.getChildren()){
			List<SecUser> allUser = secUserService.selectAllUserByOrgList(pagevo, org.getId().toString());
			data.addAll(allUser);
			if(org.getChildren().size()>0){
				getUser(org.getChildren(), allUser, pagevo);
			}
		}
		
	}
}
