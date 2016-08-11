package com.ink.platform.controller.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ink.platform.util.StrSubStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecResourceService;
import com.ink.platform.api.service.ISecRoleService;
import com.ink.platform.api.util.ObjectJson;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.util.JsonUtil;


/**
 * 权限管理：角色管理
 * @author lww
 *
 */
@Controller("secRoleController")
public class SecRoleController  extends BaseController{
	@Autowired
	private ISecRoleService secRoleService;

	@Autowired
	private ISecResourceService secResourceService;
	
	YinkerLogger LOGGER = YinkerLogger.getLogger(SecRoleController.class);
	
	 /**
     * 角色信息页面
     * @param mv
     * @return
     */
    @RequestMapping("role")
	public ModelAndView role(ModelAndView mv){
		mv.setViewName("WEB-INF/templates/role/roleInfo");
		return mv;		
	}
	/**
     * 角色列表查询
     * @param secRole
     * @param rows
     * @param page
     * @return
     */
	@RequestMapping(value="/role/list")
    @ResponseBody
    public String list( SecRole secRole,int rows,int page){
    	LOGGER.debug("分页查询条件"+secRole+"当前页"+page+"分页条数"+rows);
        PageVO<SecRole> pagevo=new PageVO<SecRole>();
        ObjectJson objectJson=new ObjectJson();
	    pagevo.setPageSize(rows);
	    pagevo.setPageNumber(page);
	    List<SecRole> secRoleList=secRoleService.selectList(pagevo, secRole);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "角色信息列表"+secRoleList);
	    for(SecRole secRoles :secRoleList){
	    	
	    	SecUser user = getuserMsg(secRoles.getOperatorId().toString());
	    	secRoles.setCreator(user.getUsername());
	    }
	    objectJson.setRows(secRoleList);
	    objectJson.setTotal(pagevo.getTotal());
	    return JsonUtil.toCompatibleJSONString(objectJson); 
    }
    /**
     * 角色添加
     * @param secRole
     * @return
     */
    @RequestMapping(value="/role/save",method=RequestMethod.POST)
    @ResponseBody
    public boolean save(@ModelAttribute("secRole") @Valid SecRole secRole,HttpServletRequest request) {
    	//应查询角色名称是否被使用
    	HashMap<Object, Object> map = new HashMap<>();
    	map.put("roleName", secRole.getRoleName());
    	map.put("roleCode", secRole.getRoleCode());
    	List<SecRole> list  =secRoleService.selecRoleByMap(map);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "查询角色是否被使用"+list);

	    if(list.size()>0){
	    	//错误信息
	    	return false;
	    }
        LOGGER.debug("角色保存"+secRole);
        secRole.setCreateTime(new Date());
        secRole.setDelFlag(SecurityConstant.Delete_Type.No);
        secRole.setId(idwork());
        secRole.setOperatorId(Long.valueOf(getSSOUserId(request)));
        //添加操作日志
      
        boolean b=secRoleService.insertSelective(secRole);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "角色新增结果"+b,secRole);

        LogAdd(SecurityConstant.Action.ADD, SecurityConstant.Action_Type.Role,
        		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
        return b;
    }
    /**
     * 角色编辑
     * @param secRole
     * @return
     */
    @RequestMapping(value="/role/edit",method=RequestMethod.POST)
    @ResponseBody
    public boolean edit(@ModelAttribute("secRole") @Valid SecRole secRole){
        LOGGER.debug("角色编辑"+secRole);
        secRole.setUpdateTime(new Date());
      //添加操作日志
       
        boolean b=secRoleService.updateByPrimaryKeySelective(secRole);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "角色编辑结果"+b,secRole);

        LogAdd(SecurityConstant.Action.Edit, SecurityConstant.Action_Type.Role,
        		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
        return b;
       
    }
    /**
     * 角色删除
     * @param secRole
     * @return
     */
    @RequestMapping("/role/delete")
    @ResponseBody
    public boolean delete(@ModelAttribute("secRole") SecRole secRole){
        LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role,"角色删除id"+secRole.getId());
      //添加操作日志
       
        boolean b=secRoleService.deleteByPrimaryKey(secRole);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "角色删除结果"+b,secRole);

        LogAdd(SecurityConstant.Action.DELLF, SecurityConstant.Action_Type.Role,
        		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
        return b;
    }
    /**
     * 跳转资源授权授权页
     * @param secRole
     * @return
     */
    @RequestMapping(value = "/role/resourceAuth")
    public ModelAndView empower(@ModelAttribute("id") Long id){
    	 ModelAndView mv = new ModelAndView();
	     mv.setViewName("WEB-INF/templates/role/resourceAuth");
	     mv.addObject("id", id);
	     return mv;	
	}
    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("allRole")
    @ResponseBody
    public String allRole(){
    	//String userId = getSessionAttribute("USER_ID");
    	   List<SecRole>  roleList  = secRoleService.selectAllRole();
		return JsonUtil.toCompatibleJSONString(roleList);
    	
    }
    /**
     * 角色详情
     * @return
     */
    @RequestMapping("roleDetail")
    @ResponseBody
    public ModelAndView roleDetail(String roleId){
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "角色详情"+roleId);

    	SecRole secRole =secRoleService.selectRoleById(roleId);
		LOGGER.info(PlatformLoggerCnst.P, PlatformLoggerCnst.P_Role, "角色id"+roleId,"角色详情"+secRole);

    	ModelAndView mv = new ModelAndView("WEB-INF/templates/role/roleDetail");
    	mv.addObject("secRole", secRole);
    	//查询创建人
    	SecUser  creator = getuserMsg(secRole.getOperatorId().toString());
    	mv.addObject("creator", creator.getUsername());
    	//查询资源：授权信息
    	Map<Object, Object> map = new HashMap<>();
    	map.put("roleId", roleId);
		List<SecResource> roleResourceList =secResourceService.getRoleResourceList(map);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Role, "角色id"+roleId,"角色资源详情"+roleResourceList);
		if(roleResourceList.size()>0){
			List<Object> list = new ArrayList<>();
			for(SecResource secResource: roleResourceList){
				list.add(secResource.getResourceName());
		}
			String roleResource   = StrSubStringUtil.strMosaic(list);
			mv.addObject("roleResource", roleResource);
		}
		mv.addObject("roleResourceList", roleResourceList);
		return mv;
    	
    }
}
