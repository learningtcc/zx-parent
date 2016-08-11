package com.ink.platform.controller.role;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.model.SecRoleResourceRelation;
import com.ink.platform.api.service.ISecResourceService;
import com.ink.platform.api.service.ISecRoleResourceService;
import com.ink.platform.api.service.ISecRoleService;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.util.JsonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller("secRoleResourceCotroller")
public class SecRoleResourceCotroller extends BaseController{

	@Autowired
	private ISecResourceService secResourceService;
	@Autowired
	private ISecRoleResourceService roleResourceService;
	@Autowired
	private ISecRoleService secRoleService;
	@Autowired
	private JedisPool jedisPool;


	
	YinkerLogger logger = YinkerLogger.getLogger(SecRoleResourceCotroller.class);


	/**
	 * 查询，并勾选用户拥有的资源
	 * @param request
	 * @return
	 */
	@RequestMapping("getUserResource")
	@ResponseBody
	public String getUserResource( HttpServletRequest request){
		String userId  =  getSessionAttribute("USER_ID");
		String roleId = request.getParameter("roleId");
		if(StringUtils.isBlank(userId)){
			userId  = getSSOUserId(request);
		}
		//查询所有资源
		List<SecResource> secResourceList =secResourceService.selectAllResource();
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "所有资源"+secResourceList);

		//查询登录用户拥有的资源
		List<SecRoleResourceRelation>  secRoleResourceRelation = roleResourceService.getRoleResourceList(roleId);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "已拥有有资源"+secRoleResourceRelation);

		//遍历勾选角色已拥有的资源 ，并勾选
		if(secRoleResourceRelation.size()>0){
			for(SecRoleResourceRelation roleResourceRelation : secRoleResourceRelation){
				Long roId =roleResourceRelation.getResourceId();
				Long operationId = roleResourceRelation.getOperationId();
				for(SecResource allRe : secResourceList){
					Long allId = allRe.getId();
					Long pid =allRe.getPid();
					if(allId.longValue()== roId.longValue()){
						allRe.setChecked(true);
					}
					//如果父id为被勾选则，
					if(operationId !=null){
						Long userR =operationId+roId ;
						Long pr =allId +pid;
						if(userR.longValue() ==pr.longValue()){
							allRe.setChecked(true);
						}
					}
				}		
			}
		}		
		
		return 	JsonUtil.toCompatibleJSONString(secResourceList);
	}
	/**
	 * 角色授权：新增
	 * @param ids
	 * @return
	 */
	@RequestMapping("/role/roleAuth")
	@ResponseBody
	public boolean doempower(Long oprationId,Long resourceId, Long id,HttpServletRequest request){
		
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "新增角色授权操作"+id+"操作id"+oprationId+"资源id"+resourceId);
		boolean b=false;
		SecRoleResourceRelation secRoleResourceRelation=new SecRoleResourceRelation();
	//	secRoleService.deleteByRoleId(id);
		if( resourceId!=null){
			secRoleResourceRelation.setId(idwork());
			secRoleResourceRelation.setRoleId(id);
			secRoleResourceRelation.setStatus(SecurityConstant.Status_Type.Enable_Status);
			secRoleResourceRelation.setResourceId(resourceId);
			secRoleResourceRelation.setOperatorId(Long.valueOf(getSSOUserId(request)));
			secRoleResourceRelation.setCreateTime(new Date());
			secRoleResourceRelation.setDelFlag(SecurityConstant.Delete_Type.No);
			if(oprationId !=null){
				secRoleResourceRelation.setOperationId(oprationId);
			}
			b = secRoleService.roleAuth(secRoleResourceRelation);
		}
		if(b){
			logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, resourceId+"角色授权","结果"+b);
			//更新缓存数据
			Jedis jedis = jedisPool.getResource();
			jedis.del(roleKey);
			jedis.del(resourceKey);
			/**
			 * 此处：新增权限变更时及时通知到用户平台，并更新其权限
			 */
			//添加操作日志
			//查询某角色拥有哪些用户
			singOut(String.valueOf(id));
			
		}
		LogAdd(SecurityConstant.Action.AUTH, SecurityConstant.Action_Type.User_Org_Role_Relation,
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
		return b;
	}
	/**
	 * 角色授权：删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/role/deleteResource")
	@ResponseBody
	public boolean deleteResource(Long id ,HttpServletRequest request){
		secRoleService.deleteByRoleId(id);
		//更新缓存数据
				Jedis jedis = jedisPool.getResource();
				jedis.del(roleKey);
				jedis.del(resourceKey);
		return false;
		
	}
}
