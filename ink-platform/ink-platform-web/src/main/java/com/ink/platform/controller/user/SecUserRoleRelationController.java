package com.ink.platform.controller.user;

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
import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUserRoleRelation;
import com.ink.platform.api.service.ISecRoleService;
import com.ink.platform.api.service.ISecUserRoleRelationService;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.realm.MyRealm;
import com.ink.platform.util.JsonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 用户角色关系处理层
 * @author lww
 *
 */
@Controller("secUserRoleRelationController")
public class SecUserRoleRelationController extends BaseController {

	@Autowired
	private ISecUserRoleRelationService iSecUserRoleRelationService;
	@Autowired
	private ISecRoleService secRoleService;
	@Autowired
	private JedisPool jedisPool;

	@Autowired
	private MyRealm ream;

	
	YinkerLogger logger = YinkerLogger.getLogger(SecUserRoleRelationController.class);
	/**
	 * 角色授权:选中已拥有的角色
	 * @param userId
	 * @param orgId
	 * @return
	 */
	@RequestMapping("userRoleSelect")
	@ResponseBody
	public String userRoleSelect(String subjectId,String titleCode){

		//查询所有角色：树
		List<SecRole >     roleList = secRoleService.selectAllRole();
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "角色树信息"+roleList);

		//查询已有角色：勾选
		Map<Object, Object> map  = new HashMap<>();
		map.put("userId", subjectId);
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		map.put("titleCode", titleCode);
		List<SecRole >     userRoleList  = secRoleService.selectUserRole(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "勾选已拥有角色"+subjectId+':'+userRoleList);
		for(SecRole secRole : roleList){
			String roleId  = secRole.getId().toString();
			for(SecRole secUserRoles : userRoleList){
				String userRoleId  =secUserRoles.getId().toString();
				if(StringUtils.isNotBlank(userRoleId)){
					if(userRoleId.equals(roleId) ){
						secRole.setChecked(true);
					}
				}
			}
		}
	
		return JsonUtil.toCompatibleJSONString(roleList);

	}
	/**
	 * 组织机构：角色授权
	 * @param orgId
	 * @param roleId
	 * @return
	 */
	@RequestMapping("addOrgUserRole")
	@ResponseBody
	public String  addOrgUserRole(String subjectId ,String roleId, String titleCode,HttpServletRequest request){
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "角色授权操作"+subjectId);


		//遍历选中的角色id
		List<String> roleIds = StrSubStringUtil.strSubStr(roleId);
		String remark;
		int subJectType =SecurityConstant.Subject_Type.USR;
		remark  =SecurityConstant.REMARK.USR;
		boolean b = false;
		//查询用户已拥有的角色:若角色重复勾选则：取消新增
		//查询已有角色：勾选
		Map<Object, Object> map  = new HashMap<>();
		map.put("userId", subjectId);
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		List<SecRole >     userRoleList  =secRoleService.selectUserRole(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "勾选已拥有角色"+subjectId+':'+userRoleList);

		//遍历拥有的角色属性，去重
		HashMap<Object, Object> result  = new HashMap<>();

		if(userRoleList.size()>0){
			for(SecRole secUserRoles : userRoleList){
				String userRoleId  =secUserRoles.getId().toString();
				//使用迭代器删除重复角色
				Iterator<String> iterator = roleIds.iterator();
				while(iterator.hasNext()){  
					String id = iterator.next();
					if(StringUtils.isNotBlank(userRoleId)){
						/*1.若拥有的角色匹配勾选的，则去重
						 * 2.若拥有的角色不能匹配勾选角色，则删除此角色
						 */        			
						if(userRoleId.equals(id) ){
							iterator.remove();   //注意这个地方
							userRoleId= null;
							break;
						}
					}
				}
				if(StringUtils.isNotBlank(userRoleId)){
					//开始删除未勾选角色
					//return deleteRoleRalation();
					SecUserRoleRelation  secUserRoleRelation = new SecUserRoleRelation(); 
					secUserRoleRelation.setRoleId(Long.parseLong(userRoleId));
					secUserRoleRelation.setSubjectId(Long.parseLong(subjectId));
					secUserRoleRelation.setSubjectType(subJectType);
					secUserRoleRelation.setDelFlag(SecurityConstant.Delete_Type.yes);
					secUserRoleRelation.setStatus(SecurityConstant.Status_Type.Cancel_Status);
					secUserRoleRelation.setUpdateTime(new Date());
					b =iSecUserRoleRelationService.updateUserRole(secUserRoleRelation);
					logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "删除角色授权操作"+subjectId,b);
					if(b==true){
						singOuts(subjectId);
					}
					if(b==false){
						result.put("rstType", SecurityConstant.Result.b_Erro);
						result.put("rstMsg", "取消角色授权失败");
						return JsonUtil.toCompatibleJSONString(result);
					}
					
				}
			}
		}
	
		//开始角色授权
		for(String id :roleIds){ 
			if(StringUtils.isNotBlank(id)){
				SecUserRoleRelation  secUserRoleRelation = new SecUserRoleRelation();    
				secUserRoleRelation.setSubjectId(Long.parseLong(subjectId));
				secUserRoleRelation.setSubjectType(subJectType);
				secUserRoleRelation.setRoleId(Long.parseLong(id));
				String  createid = getSSOUserId(request);
				secUserRoleRelation.setOperatorId(Long.parseLong(createid));
				secUserRoleRelation.setDelFlag(SecurityConstant.Delete_Type.No);
				secUserRoleRelation.setStatus( SecurityConstant.Status_Type.Enable_Status);
				secUserRoleRelation.setRemark(remark);
				secUserRoleRelation.setCreateTime( new Date());
				secUserRoleRelation.setTitleCode( Integer.parseInt(titleCode));
				b= iSecUserRoleRelationService.addOrgUserRole(secUserRoleRelation);
				logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "新增角色授权操作"+subjectId);

				if(!b){
					result.put("rstType", SecurityConstant.Result.b_Erro);
					result.put("rstMsg", id+"授权失败");
					return JsonUtil.toCompatibleJSONString(result);
				}
				//签退用户
				singOuts(subjectId);

			}
		}
		//角色授权
		Jedis jedis = jedisPool.getResource();
		jedis.del(roleKey);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_User, "角色授权变更，删除缓存");

		ream.clearCachedAuthorizationInfo(getuserMsg(subjectId).getLoginName());
		LogAdd(SecurityConstant.Action.AUTH, SecurityConstant.Action_Type.Role,
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);

		result.put("rstType", SecurityConstant.Result.b_SUCC);
		result.put("rstMsg", "操作成功");
		return JsonUtil.toCompatibleJSONString(result);
	}
}
