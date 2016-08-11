package com.ink.platform.controller.platform;

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
import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.model.SecPlantform;
import com.ink.platform.api.service.ISecPlantformService;
import com.ink.platform.api.util.ObjectJson;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.PlatformLoggerCnst;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.controller.BaseController;
import com.ink.platform.controller.resource.SecResourceController;
import com.ink.platform.util.JsonUtil;

/**
 * 平台管理
 * @author lww
 *
 */

@Controller("secPlantformController")
public class SecPlatformController extends  BaseController{
	@Autowired
	private ISecPlantformService secPlantformService;
	YinkerLogger LOGGER = YinkerLogger.getLogger(SecResourceController.class);
	/**
     * 平台查询页
     * @param secPlantform
     * @return
     */
	@RequestMapping("plantform")
	public ModelAndView plantformlist(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("WEB-INF/templates/plantform/plantform");
		//modelAndView.addObject("id", id);
		return modelAndView;		
	}
	/**
     * 平台列表查询
     * @param secPlantform
     * @return
     */
	@RequestMapping("plantformInfo")
    @ResponseBody
    public String list( int rows,int page,HttpServletRequest request){
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "查询平台列表");

		PageVO<SecOrg> pagevo=new PageVO<SecOrg>();
		pagevo.setPageSize(rows);
		pagevo.setPageNumber(page);
		HashMap<Object, Object> map = new HashMap<>();
		map.put("plantformName", request.getParameter("plantformName"));
		map.put("status", request.getParameter("status"));
		map.put("createTime", request.getParameter("createTime"));
		map.put("updateTime ", request.getParameter("updateTime"));
		map.put("pageVO", pagevo);
		List<SecPlantform> 	secPlantformList= secPlantformService.selectByMap(map);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "查询平台列表"+secPlantformList+"");

		int a =secPlantformService.selectTotal(map);
		ObjectJson objectJson=new ObjectJson();
		objectJson.setRows(secPlantformList);
		objectJson.setTotal(a);
		return JsonUtil.toCompatibleJSONString(objectJson); 
    }
	/**
     * 平台列表查询:s树
     * @param secPlantform
     * @return
     */
	@RequestMapping("plantTree")
    @ResponseBody
    public String plantTree( HttpServletRequest request){
		

		HashMap<Object, Object> map = new HashMap<>();
		map.put("status", "1");

		List<SecPlantform> 	secPlantformList= secPlantformService.selectByMap(map);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "查询平台列表树"+secPlantformList+"");

		return JsonUtil.toCompatibleJSONString(secPlantformList); 
    }
	/**
     * 平台添加
     * @param secPlantform
     * @return
     */
    @RequestMapping(value="addPlantform",method=RequestMethod.POST)
    @ResponseBody
    public boolean save(@ModelAttribute("SecPlantform") @Valid SecPlantform secPlantform ,HttpServletRequest request) {
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "平台保存"+secPlantform);

       //查询是否有无重复平台名称标识
        HashMap<Object, Object> map = new HashMap<>();
        map.put("plantformCode", secPlantform.getPlantformCode());
        map.put("plantformName", secPlantform.getPlantformName());
        List<SecPlantform> b = secPlantformService.selectByMap(map);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "按平台名标识查询平台列表是否被占用",b+"");

        //若b为true：则可以新增，无重复
        Boolean c= false;
        if(b.size()==0){
        	secPlantform.setCreateTime(new Date());
            secPlantform.setDelFlag(SecurityConstant.Ndelete);
            secPlantform.setId(idwork());
            secPlantform.setCreatorId(Long.parseLong(getSSOUserId(request)));
            //添加操作日志
            c= secPlantformService.insertSelective(secPlantform);
    		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "新增平台","新增结果"+c);

            LogAdd(SecurityConstant.Action.ADD, SecurityConstant.Action_Type.PlatForm,
             		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, c);
        }
        return c;
    }
    /**
     * 平台删除
     * @param secPlantform
     * @return
     */
    @RequestMapping("deletePlantform")
    @ResponseBody
    public boolean delete(@ModelAttribute("secPlantform") SecPlantform secPlantform){
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "平台删除id"+secPlantform.getId());

      //添加操作日志
       
        boolean b= secPlantformService.deleteByPrimaryKey(secPlantform);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "平台删除","新增结果"+b);

        LogAdd(SecurityConstant.Action.DELLF, SecurityConstant.Action_Type.PlatForm,
         		SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
        return b;
    }
    /**
     * 平台编辑
     * @param secPlantform
     * @return
     */
    @RequestMapping(value="editPlantform",method=RequestMethod.POST)
    @ResponseBody
    public boolean edit(@ModelAttribute("secPlantform") @Valid SecPlantform secPlantform){
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "平台编辑"+secPlantform);

    	secPlantform.setUpdateTime(new Date());
    	//添加操作日志

    	boolean b= secPlantformService.updateByPrimaryKeySelective(secPlantform);
		LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "平台编辑","编辑结果"+b);

    	LogAdd(SecurityConstant.Action.Edit, SecurityConstant.Action_Type.PlatForm,
    			SecurityConstant.Delete_Type.No, SecurityConstant.Status_Type.Enable_Status, b);
    	return b;
    }
    /**
     * 平台详情
     * @return
     */
    @RequestMapping("plantformDetails")
    public ModelAndView platformDetail(Long id){
    	LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "平台详情");
    	ModelAndView mv = new ModelAndView("WEB-INF/templates/plantform/plantformDetails");
    	SecPlantform secPlantform  =secPlantformService.selectPlantDetail(id);
    	LOGGER.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Plat, "平台详情"+secPlantform);

    	mv.addObject("secPlantform", secPlantform);
		return mv;
		
    }
}
