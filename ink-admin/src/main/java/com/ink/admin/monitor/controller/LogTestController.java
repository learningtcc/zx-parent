package com.ink.admin.monitor.controller;

import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.monitor.core.po.MonitorUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("test/log")
@Controller
public class LogTestController extends BaseController{
	
	YinkerLogger loger = YinkerLogger.getLogger(getClass());
	
	/**保存更新对象*/
	@RequestMapping(value="/error")
	@ResponseBody
	public String error(MonitorUser monitorUser) {
		
		String mcode = getRequest().getParameter("mcode");
		String incode = getRequest().getParameter("icode");
		
		if(StringUtils.isNotBlank(mcode)){
			
			if(StringUtils.isNotBlank(incode)){
				loger.error(mcode, incode, "压力业务告警测试", "12");
			}else{
				loger.error(mcode, "压力功能告警测试");
			}
			
		}
		return writeAjaxResponse("OK");
	}

}