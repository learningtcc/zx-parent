package com.ink.platform.controller.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.ink.platform.api.model.SecOperation;
import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.service.ISecOperationResourceRelationService;
import com.ink.platform.api.service.ISecResourceService;
import com.ink.platform.api.util.ObjectJson;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.util.JsonUtil;


/**
 * 资源管理控制层
 * @author lww
 *
 */
@Controller("secResourceController")
public class SecResourceController extends BaseController{
	@Autowired
	private ISecResourceService secResourceService;
	@Autowired
	private ISecOperationResourceRelationService operationResource;
	
	YinkerLogger LOGGER = YinkerLogger.getLogger(SecResourceController.class);

	/**
     * 页面跳转
     * @return
     */
	@RequestMapping("resource")
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/templates/resource/resource");
		return mv;		
	}
	/**
     * 资源菜单
     * @return
     */
	@RequestMapping("resourceTree")
	@ResponseBody
	public String resourceTree(){
	    List<SecResource> secResourceList =secResourceService.resourceTree();
		return 	JsonUtil.toCompatibleJSONString(secResourceList);
	}
	/**
     * 默认加载页:资源列表显示页面
     */
	@RequestMapping("resourcelist")
	public ModelAndView resourcelist(@ModelAttribute("id") Long id,Long pid ,HttpServletRequest request){
		String resourceName = request.getParameter("resourceName");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("WEB-INF/templates/plantform/plantformlist");
		modelAndView.addObject("id", id);
		modelAndView.addObject("pid", pid);
		modelAndView.addObject("resourceName", resourceName);
		return modelAndView;		
	}
	
	/**
     * 资源列表查询
     * @param secResource
     * @return
     */
	@RequestMapping("resourceInfo")
    @ResponseBody
    public String list(@ModelAttribute("secResource") SecResource secResource,Long id){
		List<SecResource> secResourceList=secResourceService.selectResource(secResource);
		//功能权限查询
		HashMap<Object, Object> map = new HashMap<>();
		for(SecResource resource :secResourceList){
			//创建人
			resource.setCreator(getuserMsg(resource.getOperatorId().toString()).getUsername());
			SecResource   pResource = secResourceService.selectByPrimaryKey(resource.getPid());
			//资源位置
			if(pResource !=null){
				resource.setPosition(pResource.getResourceName()+"/"+resource.getResourceName());
			}
			//资源id
			map.put("resourceId", resource.getId());
			//拥有的功能权限
			map.put("delFlag", SecurityConstant.Delete_Type.No);
			map.put("status", SecurityConstant.Status_Type.Enable_Status);
			List<SecOperation>   list =operationResource.selectOperations(map);
			if(list.size()>0){
				List<Object> operations = new ArrayList<>();
				//资源：下 功能 字符串拼接
				for(SecOperation secOperation : list){
					String operation =secOperation.getOperationName();
						operations.add(operation);
				}
				resource.setOperationTree(StrSubStringUtil.strMosaic(operations));
			}
		}
		return JsonUtil.toCompatibleJSONString(secResourceList); 
    }
	/**
     * 资源列表查询
     * @param secResource
     * @return
     */
	@RequestMapping("selectResource")
    @ResponseBody
    public String selectResource(int rows,int page,HttpServletRequest request){
		//List<SecResource> secResourceList=secResourceService.select(secResource);
		LOGGER.info(PlatformLoggerCnst.P_Resource, "查询资源列表");
		PageVO<SecOrg> pagevo=new PageVO<SecOrg>();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
    	HashMap<Object, Object> map =new HashMap<>();
    	map.put("status", request.getParameter("status"));
    	map.put("resourceName",request.getParameter("resourceName") );
    	map.put("createTime",request.getParameter("createTime"));
    	map.put("updateTime",request.getParameter("updateTime") );
    	map.put("resourceLevel",request.getParameter("resourceLevel") );
    	map.put("pageVO", pagevo);
    	List<SecResource>  secResourceList =secResourceService.selectResourceByMap(map);
    	int total = secResourceService.selectListCount(map);
		pagevo.setTotal(total);
		//功能权限查询
		HashMap<Object, Object> maps = new HashMap<>();
		for(SecResource resource :secResourceList){
			//资源id
			maps.put("resourceId", resource.getId());
			//拥有的功能权限
			maps.put("delFlag", SecurityConstant.Delete_Type.No);
			maps.put("status", SecurityConstant.Status_Type.Enable_Status);
			List<SecOperation>   list =operationResource.selectOperations(maps);
			if(list.size()>0){
				List<Object> operations = new ArrayList<>();
				//资源：下 功能 字符串拼接
				for(SecOperation secOperation : list){
					String operation =secOperation.getOperationName();
						operations.add(operation);
				}
				resource.setOperationTree(StrSubStringUtil.strMosaic(operations));
			}
		}
		ObjectJson objectJson=new ObjectJson();
		objectJson.setRows(secResourceList);
		objectJson.setTotal(pagevo.getTotal());
		return JsonUtil.toCompatibleJSONString(objectJson); 
    }
	/**
     * 资源添加
     * @param secResource
     * @return
     */
    @RequestMapping("addResource")
    @ResponseBody
    public boolean save( SecResource secResource,HttpServletRequest request) {
		LOGGER.info(PlatformLoggerCnst.P_Resource, "资源新增"+secResource);

    	SecResource presource =secResourceService.selectByPrimaryKey(secResource.getPid());
    	//获取父级资源：资源级别
        if(presource !=null){
        	secResource.setResourceLevel(presource.getResourceLevel()+1);
        }else{
        	secResource.setResourceLevel(0);
        }
    	//HashMap<Object, Object> maps =new HashMap<>();
		//验重,某平台下是否有相同名称，标识的资源
    	/*maps.put("resourceCode", secResource.getResourceCode());
    	maps.put("resourceName", secResource.getResourceName());
    	List<SecResource> lisy = secResourceService.selectResourceByMap(maps );
    	if(lisy.size() >0){
    		return false;
    	}*/
        LOGGER.debug("资源保存"+secResource);
        secResource.setId(idwork());
        secResource.setCreateTime(new Date());
        secResource.setOperatorId(Long.parseLong(getSSOUserId(request)));
        //三级权限表达式，查询路径
        //secResource.setPermission(SecurityConstant.resource_permision.permision);
        //添加操作日志
        boolean result=secResourceService.insertSelective(secResource);
     /*   if(result){
        	//新增操作项
        	for(String operationId :  list){
        		Map<Object, Object> map = new HashMap<>();
            	map.put("operationId", operationId);//操作id
            	map.put("resourceId", secResource.getId());//资源id
            	map.put("creatorId",getSSOUserId(request));//创建人id
            	result = operationResource.addOperation(map);
        	}
        	
        }*/
        LogAdd(SecurityConstant.Action.ADD, SecurityConstant.Action_Type.Resource,
        		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, result);
   
        return result;
    }
    /**
     * 资源删除
     * @param secResource
     * @return
     */
    @RequestMapping("deleteResource")
    @ResponseBody
    public boolean delete(@ModelAttribute("secResource") SecResource secResource){
    	LOGGER.info(PlatformLoggerCnst.P_Resource, "资源删除id"+secResource.getId());
    	
        boolean result=secResourceService.deleteByPrimaryKey(secResource);
        //若有子资源，则：注销子资源
        List<SecResource> data =  getChildResource(secResource.getId());
        if(data.size()>0){
        	 for(SecResource rs:data){
        		 result= secResourceService.deleteByPrimaryKey(rs);
             }
        }
        LogAdd(SecurityConstant.Action.DELLF, SecurityConstant.Action_Type.Resource,
        		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, result);
        return result;
    }
    /**
     * 递归查询子资源
     * @param id
     * @return
     */
    //查询子资源
    private List<SecResource> getChildResource(Long id) {
    	List<SecResource> list = new ArrayList<>();
    	 List<SecResource> secResourceList  = secResourceService.selectLowResource(id.toString());
    	 if(secResourceList.size()>0){
    		 list.addAll(secResourceList);
    		 for (SecResource secResource :secResourceList){
    			 getData(secResource.getId(),list);        		
        	 }
    	 }
    	
		return list;
	}
    /**
     * 递归查询子资源
     * @param id
     * @return
     */
	private void getData(Long id, List<SecResource> list) {
		 List<SecResource> secResourceList  = secResourceService.selectLowResource(id.toString());
		 if(secResourceList.size()>0){
			 list.addAll(secResourceList);
    		 for (SecResource secResource :secResourceList){
        			 getChildResource(secResource.getId());
        	
        	 }
    	 }
	}
	/**
     * 资源编辑
     * @param secResource
     * @return
     */
    @RequestMapping(value="editResource",method=RequestMethod.POST)
    @ResponseBody
    public boolean edit(@ModelAttribute("secResourceService") @Valid SecResource secResource,HttpServletRequest request){
    	LOGGER.info(PlatformLoggerCnst.P_Resource, "资源编辑"+secResource);

        secResource.setUpdateTime(new Date());
        //String operationTree = secResource.getOperationTree();

      //添加操作日志
        boolean result=secResourceService.updateByPrimaryKeySelective(secResource);
        LogAdd(SecurityConstant.Action.Edit, SecurityConstant.Action_Type.Resource,
        		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, result);
        return result;
    }
    /**
     * 下级资源查询列表
     * @return
     */
    @RequestMapping("lowResourceBaseInfo")
    @ResponseBody
    public String resourceBaseInfo(String parentId){
    	List<SecResource> secResourceList  = secResourceService.selectLowResource(parentId);
    	LOGGER.info(PlatformLoggerCnst.P_Resource,"父id"+parentId, "下级资源列表"+secResourceList);

    	//功能权限查询
    			HashMap<Object, Object> map = new HashMap<>();
    			for(SecResource resource :secResourceList){
    				//资源id
    				map.put("resourceId", resource.getId());
    				//拥有的功能权限
    				map.put("delFlag", SecurityConstant.Delete_Type.No);
    				map.put("status", SecurityConstant.Status_Type.Enable_Status);
    				List<SecOperation>   list =operationResource.selectOperations(map);
    				SecResource   pResource = secResourceService.selectByPrimaryKey(resource.getPid());
    				//资源位置
    				if(pResource!=null){
        				resource.setPosition(pResource.getResourceName()+"/"+resource.getResourceName());
    				}
    				if(list.size()>0){
    					List<Object> operations = new ArrayList<>();
    					//资源：下 功能 字符串拼接
    					for(SecOperation secOperation : list){
    						String operation =secOperation.getOperationName();
    							operations.add(operation);
    					}
    					resource.setOperationTree(StrSubStringUtil.strMosaic(operations));
    				}
    			}
		return JsonUtil.toCompatibleJSONString(secResourceList);
    	
    }
    /**
     * 资源详情
     * @param id
     * @param pid
     * @param request
     * @return
     */
    @RequestMapping("resourceDetails")
	public ModelAndView resourceDetail( Long id,Long pid){
    	LOGGER.info(PlatformLoggerCnst.P_Resource,"资源详情"+id);

		ModelAndView mv = new ModelAndView("WEB-INF/templates/plantform/resourceDetail");
	  	SecResource   secResource = secResourceService.selectByPrimaryKey(id);
		//功能权限查询
		HashMap<Object, Object> map = new HashMap<>();
	  //资源id
		map.put("resourceId", secResource.getId());
		//拥有的功能权限
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		List<SecOperation>   list =operationResource.selectOperations(map);
		if(list.size()>0){
			List<Object> operations = new ArrayList<>();
			//资源：下 功能 字符串拼接
			for(SecOperation secOperation : list){
				String operation =secOperation.getOperationName();
					operations.add(operation);
			}
			secResource.setOperationTree(StrSubStringUtil.strMosaic(operations));
		}
	  	SecResource   pResource = secResourceService.selectByPrimaryKey(pid);
    	mv.addObject("secResource", secResource);
    	mv.addObject("pResource", pResource);
		return mv;		
	}
    
}
