package com.ink.platform.controller.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecOrgService;
import com.ink.platform.api.service.ISecUserOrgRelationService;
import com.ink.platform.api.util.ObjectJson;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.util.JsonUtil;


/**
 * 组织机构管理
 * @author lww
 *
 */
@Controller("secOrgController")
public class SecOrgController extends BaseController{
	@Autowired
	private ISecOrgService  iSecOrgService;
	@Autowired
	private ISecUserOrgRelationService userOrgRelationService;
	
	YinkerLogger logger = YinkerLogger.getLogger(SecOrgController.class);

	
	/**
	 * 组织机构菜单树跳转页
	 * @param secOrg
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("orgInfo")
	public ModelAndView orgInfo(){
		return new ModelAndView("WEB-INF/templates/org/org");	
	}
	/**
	 * 组织机构菜单树生成
	 * @param id
	 * @return
	 */ 
	@RequestMapping("orgInfoMenu")
	@ResponseBody
	public String  orgInfoMenu(){
		logger.info("组织机构菜单");
		Map<Object, Object> map  = new HashMap<>();
		map.put("status", SecurityConstant.Status_Type.Enable_Status);

		List<SecOrg> secOrgList =	iSecOrgService.selectOrgMsg(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "组织机构菜单"+secOrgList);

		List<SecOrg>  secOrgUserList =  iSecOrgService.getOrgUserList();
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "用户菜单"+secOrgList);

		List<SecOrg> list = new ArrayList<>();
		list.addAll(secOrgList);
		list.addAll(secOrgUserList);
		return 	JsonUtil.toCompatibleJSONString(list); 
	}
	/**
	 * 组织机构基本信息页面
	 * @return
	 */
	@RequestMapping("baseOrgInfo")
	public ModelAndView baseOrgInfo(String id){
		ModelAndView mv =  new ModelAndView("WEB-INF/templates/org/orgInfo");
		 //组织机构信息
		SecOrg secOrg = iSecOrgService.selectByPrimaryKey(Long.parseLong(id));
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "组织机构基本信"+secOrg);

		if(secOrg != null){
			//上级机构信息
			SecOrg parentOrg = iSecOrgService.selectByPrimaryKey(secOrg.getParentOrgId());
			logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "上级组织机构基本信"+parentOrg);

			mv.addObject("parentOrg",parentOrg);
		}
		
		//组织机构下人员显示信息---查用户
		
		if(id != null){
			mv.addObject("id", id);
			mv.addObject("orgName",secOrg.getOrgName());
			mv.addObject("secOrg",secOrg);

		}
		return mv;	
	}
	/**
	 * 组织详情
	 * @return
	 */
	@RequestMapping("orgDetails")
	public ModelAndView orgDetails(String id){
		ModelAndView mv =  new ModelAndView("WEB-INF/templates/org/orgDetails");
		 //组织机构信息
		SecOrg secOrg = iSecOrgService.selectByPrimaryKey(Long.parseLong(id));
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "组织机构基本信"+secOrg);

		//上级机构信息
		SecOrg parentOrg = iSecOrgService.selectByPrimaryKey(secOrg.getParentOrgId());
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "上级组织机构基本信"+parentOrg);

		//组织机构下人员显示信息---查用户
		
		if(id != null){
			mv.addObject("id", id);
			mv.addObject("orgName",secOrg.getOrgName());
			mv.addObject("secOrg",secOrg);
			mv.addObject("parentOrg",parentOrg);
			mv.addObject("parentOrgId",parentOrg.getOrgName());

		}
		return mv;	
		
	}
	/**
	 * 查询组织机构基本信息
	 * @param secOrg
	 * @return
	 */
	@RequestMapping("orgBasicInfo")
	@ResponseBody
	public String getOrgInfo(String orgId){
		long id = 0;
		if(StringUtils.isNotBlank(orgId)){
			 id = Long.parseLong(orgId); 
		}	else{
			return null;
		}
		
		SecOrg secOrg  = iSecOrgService.selectByPrimaryKey(id);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "组织机构基本信"+secOrg);

	
		SecOrg secParentOrg  = iSecOrgService.selectByPrimaryKey(secOrg.getParentOrgId());
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "上级组织机构基本信"+secParentOrg);

		if(secParentOrg != null){
			String parentName = secParentOrg.getOrgName();
			if(!StringUtils.isBlank(parentName)){
				secOrg.setParentName(parentName);
			}	
		}
		List<SecOrg> secOrgList=new ArrayList<SecOrg>();
		secOrgList.add(secOrg);
		return JsonUtil.toCompatibleJSONString(secOrgList);
		
	}
	
	/**
	 * 查询下级组织机构信息:分页
	 * @param secOrg
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("orgInfoList")
	@ResponseBody
	public String orgInfoList(int rows,int page, String orgId,HttpServletRequest request){
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		long id = 0;
		if(StringUtils.isNotBlank(orgId)){
			id = Long.parseLong(orgId); 
		}	
		PageVO<SecOrg> pagevo=new PageVO<SecOrg>();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
		//查询下级机构信息
		List<SecOrg> secOrgList=iSecOrgService.selectOrgInfoById(pagevo, id);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "下级组织机构基本信"+secOrgList);

		//获取父级：组合机构名称
		for(SecOrg  list :secOrgList){
			Long parentId =list.getParentOrgId();
			SecOrg secParentOrg  = iSecOrgService.selectByPrimaryKey(parentId);
			logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "上级组织机构基本信"+secParentOrg);

			list.setParentName(secParentOrg.getOrgName());		
			SecUser user = getuserMsg(list.getCreatorId().toString());
			list.setUserName(user.getUsername());
		}
		return JsonUtil.toCompatibleJSONString(secOrgList);		
	}
	/**
	 * 添加组织机构
	 */
	@RequestMapping("addOrg")
	@ResponseBody
	public boolean addOrg(SecOrg secOrg ,HttpServletRequest request){

		logger.debug("添加机构"+secOrg);

		secOrg.setId(idwork());
		secOrg.setCreateTime(new Date());
		String url ="baseOrgInfo";
		secOrg.setUrl(url);
		Long creatId =Long.parseLong(getSSOUserId(request));
		secOrg.setCreatorId(creatId);
		boolean b = iSecOrgService.insertSelective(secOrg);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "下级组织机构:新增","结果"+b);

		LogAdd(SecurityConstant.Action.ADD, SecurityConstant.Action_Type.ORG, 
  	    		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status,b );
		return b;
	}
	/**
	 * 更新组织机构信息
	 */
	@RequestMapping("updateOrg")
	@ResponseBody
	public boolean updateOrg(SecOrg secOrg ,HttpServletRequest request){
		secOrg.setUpdateTime(new Date());
		boolean b = iSecOrgService.updateByPrimaryKeySelective(secOrg);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "下级组织机构:更新","结果"+b);

		if(secOrg.getStatus() !=SecurityConstant.Status_Type.Enable_Status){
			//同时删除本机构下用户的关联关系
			Map<Object, Object> map  =new HashMap<>();
			map.put("orgId", secOrg.getId());//目标节点
			map.put("status", secOrg.getStatus());
			userOrgRelationService.deleteUserOrgRelationByMap(map);
			logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "下级组织机构:更新子机构关系","结果"+b);

		}
		LogAdd(SecurityConstant.Action.Edit, SecurityConstant.Action_Type.ORG, 
  	    		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status,b );
		return b;
	}

	/**
	 * 删除组织机构
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteOrg")
	@ResponseBody
	boolean deleteOrg(String orgId){
		SecOrg secOrg = new SecOrg();
		secOrg.setId(Long.parseLong(orgId));
		secOrg.setStatus(SecurityConstant.Status_Type.Cancel_Status);
		secOrg.setDelFlag(SecurityConstant.Delete_Type.yes);
		boolean b =iSecOrgService.updateByPrimaryKeySelective(secOrg);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "下级组织机构:删除机构关系","结果"+b);

		//同时删除本机构下用户的关联关系
		Map<Object, Object> map  =new HashMap<>();
		map.put("orgId", Long.parseLong(orgId));//目标节点
		map.put("status", secOrg.getStatus());
		map.put("delFlag", secOrg.getDelFlag());
		map.put("updateTime", new Date());
		userOrgRelationService.deleteUserOrgRelationByMap(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "下级组织机构:更新子机构关系");

		LogAdd(SecurityConstant.Action.DELLF, SecurityConstant.Action_Type.ORG, 
  	    		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status,b );
		return b;
	}

	/**
	 * 所有组织机构：按条件查询
	 * @return
	 */
	@RequestMapping("fuzzyQueryOrg")
	@ResponseBody
	public String fuzzyQueryOrg(HttpServletRequest request,int rows,int page){
		String disable = request.getParameter("disable");
		String cancel = request.getParameter("cancel");
		String orgName = request.getParameter("orgName");
		HashMap<Object, Object> map = new HashMap<>();

		if(StringUtils.isNotBlank(disable) && disable.equals("on")){
			int statusA  =SecurityConstant.Status_Type.Disable_Status;
			map.put("statusA", statusA);
		}
		if(StringUtils.isNotBlank(cancel) && cancel.equals("on")){
			int statusB = SecurityConstant.Status_Type.Cancel_Status;
			map.put("statusB", statusB);
		}
		if(StringUtils.isNotBlank(orgName)){
			map.put("orgName", orgName);
		}		
		PageVO<SecOrg> pagevo=new PageVO<SecOrg>();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
		map.put("pageVO", pagevo);
		List<SecOrg> secOrg = iSecOrgService.selectOrgMsg(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "按条件查询分页",secOrg);

		int total = iSecOrgService.selectListCount(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "按条件查询总数",total);

		pagevo.setTotal(total);
		//上级机构信息
		//获取父级：组合机构名称
		for(SecOrg  list :secOrg){
			Long parentId =list.getParentOrgId();
			if(parentId!=null){
				SecOrg secParentOrg  = iSecOrgService.selectByPrimaryKey(parentId);
				logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Org, "按条件查询分页,上级机构信息",secParentOrg);

				String porg = secParentOrg.getOrgName();
				list.setParentName(porg);	

			}						
		}
		ObjectJson objectJson=new ObjectJson();
		objectJson.setRows(secOrg);
		objectJson.setTotal(pagevo.getTotal());
		return  JsonUtil.toCompatibleJSONString(objectJson);
		
	}
	
}
