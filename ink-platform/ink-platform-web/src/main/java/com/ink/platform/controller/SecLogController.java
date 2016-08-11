package com.ink.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.log.util.YinkerLogger;
import com.ink.platform.api.model.SecLog;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecLogService;
import com.ink.platform.api.util.ObjectJson;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.PlatformLoggerCnst;


/**
 * 权限管理操作日志：控制层
 * @author lww
 *
 */
@Controller("secLogController")
public class SecLogController  extends BaseController{
	@Autowired
	private ISecLogService logService;
	YinkerLogger loger = YinkerLogger.getLogger(SecLogController.class);

	/**
     * 操作日志列表查询
     * @param secLog
     * @param rows
     * @param page
     * @return
     */
	@RequestMapping(value="/log/list")
    @ResponseBody
    public ObjectJson list(@ModelAttribute("secLog") SecLog secLog,int rows,int page){
    	loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Log,"分页查询条件"+secLog+"当前页"+page+"分页条数"+rows);
        PageVO<SecLog> pagevo=new PageVO<SecLog>();
        ObjectJson objectJson=new ObjectJson();
	    pagevo.setPageSize(rows);
	    pagevo.setPageNumber(page);
	    List<SecLog> secLogList=logService.selectList(pagevo, secLog);
	    for(SecLog log :secLogList ){
	    	SecUser user =  getuserMsg(log.getUserId().toString());
	    	log.setUserName(user.getUsername());
	    }
	    objectJson.setRows(secLogList);
	    objectJson.setTotal(pagevo.getTotal());
	    loger.info(PlatformLoggerCnst.P,PlatformLoggerCnst.P_Log,"分页查询结果"+secLogList+"当前页"+page+"分页条数"+rows+"总记录数"+secLogList.size());
	    return objectJson; 
    }
	@RequestMapping("log")
	public ModelAndView login(ModelAndView mv){
		mv.setViewName("WEB-INF/templates/log/seclog");
		return mv;		
	}
}
