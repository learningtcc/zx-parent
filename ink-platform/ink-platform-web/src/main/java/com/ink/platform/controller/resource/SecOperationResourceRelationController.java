package com.ink.platform.controller.resource;

import java.util.ArrayList;
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
import com.ink.platform.api.model.SecOperation;
import com.ink.platform.api.service.ISecOperationResourceRelationService;
import com.ink.platform.api.service.ISecOperationService;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.util.JsonUtil;

/**
 * 资源操作关联处理类
 * @author lww
 *
 */
@Controller("secOperationResourceRelationController")
public class SecOperationResourceRelationController extends BaseController{

	@Autowired
	private ISecOperationResourceRelationService operationResource;
	@Autowired
	private ISecOperationService secOperationService;
	YinkerLogger logger = YinkerLogger.getLogger(SecOperationResourceRelationController.class);
	/**
	 * 操作选项:默认勾选资源已拥有的属性
	 * 显示父级资源拥有的操作权限
	 * @return
	 */
	@RequestMapping("operationTCheck")
	@ResponseBody
	public String operationTCheck(String resourceId ,String pid){

		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "资源id"+resourceId,"父级资源id"+pid);

		//logger.info(module, content);
		HashMap<Object, Object> map = new HashMap<>();
		map.put("resourceId", resourceId);
		//拥有的功能权限
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		List<SecOperation>   list =operationResource.selectOperations(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "资源id"+resourceId,"资源拥有的操作"+list);

		
		List<SecOperation> secOperationList = new ArrayList<>();
		if(pid.equals("0")){
			//所有操作功能
			secOperationList	=secOperationService.selectAllOperatin(null);
		}else{
			//父级所有操作功能
			HashMap<Object, Object> pmap = new HashMap<>();
			pmap.put("resourceId", pid);
			pmap.put("delFlag", SecurityConstant.Delete_Type.No);
			pmap.put("status", SecurityConstant.Status_Type.Enable_Status);
			secOperationList = operationResource.selectOperations(pmap);
		}
		for(SecOperation secOperations :secOperationList){
			//	String id =secOperations.getId();
			String opertaName =secOperations.getOperationName();
			if(list.size()>0){
				for(SecOperation secOperation :list){
					if(opertaName.equals(secOperation.getOperationName())){
						//设置为勾选状态
						secOperations.setChecked(true);
					}
				}
			}
		}
		return JsonUtil.toCompatibleJSONString(secOperationList);

	}
	/**
	 * 授予资源操作功能：跳转页面
	 * @return
	 */
	@RequestMapping("ruthOperation")
	@ResponseBody
	public ModelAndView ruthOperation(String resourceId){
		ModelAndView mv =  new ModelAndView("WEB-INF/templates/plantform/ruthOperation");

		mv.addObject("resourceId", resourceId);
		return mv;
	}
	/**
	 * 授予资源操作功能:动作
	 * @return
	 */
	@RequestMapping("ruthResourceOperation")
	@ResponseBody
	public String ruthResourceOperation(String resourceId,String operationId,HttpServletRequest request){
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Resource, "资源id"+resourceId,"操作id"+operationId);

		//1.若取消勾选已拥有的操作则删除
		//2.去重，去掉已拥有的操作id
		//3.新增操作资源关系
		//结果集
		HashMap<Object, Object> result  = new HashMap<>();
		
		List<String>	opList = StrSubStringUtil.strSubStr(operationId);
		HashMap<Object, Object> map = new HashMap<>();
		map.put("resourceId", resourceId);
		map.put("status", SecurityConstant.Status_Type.Enable_Status);
		map.put("delFlag", SecurityConstant.Delete_Type.No);
		boolean b=false;

		//拥有的功能权限
		List<SecOperation>   list =operationResource.selectOperations(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Resource, "拥有的功能权限"+list);


		if(opList.size()>0){
			//使用迭代器删除重复角色
			for(SecOperation secOperation :list ){
				String operId =secOperation.getId().toString();
				Iterator<String> iterator = opList.iterator();
				while(iterator.hasNext()){  
					String id = iterator.next();
					if(operId.equals(id)){
						iterator.remove();   //注意这个地方
						operId=null;
						break;
					}
				}
				if(StringUtils.isNotBlank(operId)){
					HashMap<Object, Object> deleMap = new HashMap<>();
					//删除操作项
					deleMap.put("resourceId", resourceId);
					deleMap.put("operationId", operId);//操作id
					deleMap.put("delFlag", SecurityConstant.Delete_Type.yes);
					deleMap.put("status", SecurityConstant.Status_Type.Cancel_Status);
					b = operationResource.updateRelation(deleMap);	
					logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Resource, "资源id"+resourceId,"授权结果"+b);

					if(!b){
						result.put("rstType", SecurityConstant.Result.b_Erro);
						result.put("rstMsg", "删除操作项失败");
						return JsonUtil.toCompatibleJSONString(result);
					}
				}
			}
		}
		//开始授权
		if(opList.size()>0){
			//新增操作项
			for(String id :  opList){
				if(StringUtils.isNotBlank(id)){
					Map<Object, Object> maps = new HashMap<>();
					maps.put("operationId", id);//操作id
					//	map.put("resourceId", secResource.getId());//资源id
					maps.put("creatorId",getSSOUserId(request));//创建人id
					maps.put("resourceId", resourceId);
					b = operationResource.addOperation(maps);	
					logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Resource, "资源id"+resourceId,"授权结果"+b);

					if(!b){
						result.put("rstType", SecurityConstant.Result.b_Erro);
						result.put("rstMsg", "授权操作项失败");
						return JsonUtil.toCompatibleJSONString(result);
					}
				}
			}
		}
		result.put("rstType", SecurityConstant.Result.b_SUCC);
		result.put("rstMsg", "操作成功");
		return JsonUtil.toCompatibleJSONString(result);		
	}
	/**
	 * 查询父资源操作功能
	 * @param request
	 * @return
	 */
	public String selectOperation(HttpServletRequest request){
		//父级资源拥有的功能
				HashMap<Object, Object> pmap = new HashMap<>();
				pmap.put("resourceId", request.getParameter("pid"));
				pmap.put("status", SecurityConstant.Status_Type.Enable_Status);
				pmap.put("delFlag", SecurityConstant.Delete_Type.No);
				List<SecOperation>   plist =operationResource.selectOperations(pmap);
				Long id =Long.parseLong(request.getParameter("opeId"));
				if(!plist.contains(id)){
					
				}
		
		return JsonUtil.toCompatibleJSONString(plist);		
		
	}
}
