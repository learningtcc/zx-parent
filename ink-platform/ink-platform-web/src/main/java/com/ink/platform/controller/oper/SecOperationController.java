package com.ink.platform.controller.oper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecOperation;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecOperationResourceRelationService;
import com.ink.platform.api.service.ISecOperationService;
import com.ink.platform.api.util.ObjectJson;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.util.JsonUtil;

/**
 * 操作管理
 * @author lww
 *
 */

@Controller("secOperationController")
public class SecOperationController extends BaseController{
	@Autowired
	private ISecOperationService secOperationService;


	@Autowired
	private ISecOperationResourceRelationService operationResource;
	YinkerLogger logger = YinkerLogger.getLogger(SecOperationController.class);


	/**
	 * 操作列表查询
	 * @param secOperation
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/operation/list")
	@ResponseBody
	public String list(@ModelAttribute("secOperation") SecOperation secOperation,int rows,int page){
		logger.debug("分页查询条件"+secOperation+"当前页"+page+"分页条数"+rows);
		PageVO<SecOperation> pagevo=new PageVO<SecOperation>();
		ObjectJson objectJson=new ObjectJson();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
		List<SecOperation> secOperationList=secOperationService.selectList(pagevo, secOperation);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作列表"+secOperationList);

		for (SecOperation secOperations:secOperationList){
			SecUser  secUser =getuserMsg(secOperations.getCreatorId().toString());
			secOperations.setCreator(secUser.getUsername());
		}
		objectJson.setRows(secOperationList);
		objectJson.setTotal(pagevo.getTotal());
		logger.info(PlatformLoggerCnst.P,"分页查询结果"+secOperationList+"当前页"+page+"分页条数"+rows+"总记录数"+secOperationList.size());
		return JsonUtil.toCompatibleJSONString(objectJson); 
	}
	/**
	 * 操作添加
	 * @param secOperation
	 * @return
	 */
	@RequestMapping(value="/operation/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("secOperation") @Valid SecOperation secOperation,HttpServletRequest request) {
		//验重
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作：新增"+secOperation);
		//结果集
		HashMap<Object, Object> result  = new HashMap<>();
		HashMap<Object, Object> map = new HashMap<>();
		map.put("operationCode", secOperation.getOperationCode());
		map.put("operationName", secOperation.getOperationName());
		List<SecOperation> list = secOperationService.selectAllOperatin(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作列表"+list);

		if(list.size() >0){
			result.put("rstType", SecurityConstant.Result.b_Erro);
			result.put("rstMsg", "操作名称或操作标识已存在");
			return JsonUtil.toCompatibleJSONString(result);
		}
		logger.debug("操作保存"+secOperation);
		secOperation.setCreateTime(new Date());
		secOperation.setDelFlag(SecurityConstant.Delete_Type.No);
		secOperation.setId(idwork());
		secOperation.setCreatorId(Long.parseLong(getSSOUserId(request)));
		secOperation.setDelFlag(SecurityConstant.Delete_Type.No);
		//添加操作日志
		boolean b=secOperationService.insertSelective(secOperation);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作：新增结果"+b);

		LogAdd(SecurityConstant.Action.ADD, SecurityConstant.Action_Type.operation,
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
		if(!b){
			result.put("rstType", SecurityConstant.Result.b_Erro);
			result.put("rstMsg", "操作失败");
			return JsonUtil.toCompatibleJSONString(result);
		}else{
			result.put("rstType", SecurityConstant.Result.b_SUCC);
			result.put("rstMsg", "操作成功");
			return JsonUtil.toCompatibleJSONString(result);		
		}

	}
	/**
	 * 操作编辑
	 * @param secOperation
	 * @return
	 */
	@RequestMapping(value="/operation/edit",method=RequestMethod.POST)
	@ResponseBody
	public String edit(@ModelAttribute("secOperation") @Valid SecOperation secOperation){
		logger.debug("操作编辑"+secOperation);
		secOperation.setUpdateTime(new Date());
		//添加操作日志
		//结果集
		HashMap<Object, Object> result  = new HashMap<>();
		boolean b=secOperationService.updateByPrimaryKeySelective(secOperation);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作：编辑结果"+b);

		LogAdd(SecurityConstant.Action.Edit, SecurityConstant.Action_Type.operation,
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
		if(!b){
			result.put("rstType", SecurityConstant.Result.b_Erro);
			result.put("rstMsg", "操作失败");
			return JsonUtil.toCompatibleJSONString(result);
		}else{
			result.put("rstType", SecurityConstant.Result.b_SUCC);
			result.put("rstMsg", "操作成功");
			return JsonUtil.toCompatibleJSONString(result);		
		}
	}
	/**
	 * 操作删除
	 * @param secOperation
	 * @return
	 */
	@RequestMapping("/operation/delete")
	@ResponseBody
	public boolean delete(@ModelAttribute("secOperation") SecOperation secOperation){
		logger.debug("操作删除id"+secOperation.getId());
		//添加操作日志

		boolean b=secOperationService.deleteByPrimaryKey(secOperation);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作：删除结果"+b);

		LogAdd(SecurityConstant.Action.DELLF, SecurityConstant.Action_Type.operation,
				SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
		return b;
	}
	/**
	 * 操作管理跳转页
	 * @param mv
	 * @return
	 */
	@RequestMapping("operation")
	public ModelAndView login(ModelAndView mv){
		mv.setViewName("WEB-INF/templates/operation/operation");
		return mv;		
	}

	/**
	 * 操作选项:
	 * @return
	 */
	@RequestMapping("operationTree")
	@ResponseBody
	public String operationTree(String resourceId){
		HashMap<Object, Object> map = new HashMap<>();
		map.put("resourceId", resourceId);
		//拥有的功能权限
		List<SecOperation>   list =operationResource.selectOperations(map);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作：功能"+list,"资源id"+resourceId);

		//所有操作功能
		List<SecOperation> secOperationList=secOperationService.selectAllOperatin(null);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作：所有功能"+secOperationList);

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
	 * 操作详情
	 * @param operationId
	 * @return
	 */
	@RequestMapping("operateDetail")
	@ResponseBody
	public ModelAndView operateDetail(String operationId){
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作：详情"+operationId);

		ModelAndView mv = new ModelAndView("WEB-INF/templates/operation/operateDetail");
		SecOperation  secOperation  =secOperationService.selectOperationDetail(operationId);
		logger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Operation, "操作：详情"+secOperation);

		//创建人姓名
		SecUser user  =getuserMsg(secOperation.getCreatorId().toString());
		mv.addObject("secOperation", secOperation);
		mv.addObject("creator", user.getUsername());
		return mv;

	}
}
