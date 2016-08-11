package com.ink.user.ext.controller;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.ext.api.IUserMsgReportService;
import com.ink.user.ext.api.constant.UserLogConstant;
import com.ink.user.ext.api.input.UserMsgReportInput;
import com.ink.user.ext.core.service.IUserMessageLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信回执状态接收
 * 
 * @author yangchen
 * @date 2016年6月27日 上午10:50:26
 */
@Controller
@RequestMapping("UserMsgReportController")
public class UserMsgReportController {
	private static final YinkerLogger logger = YinkerLogger.getLogger(UserMsgReportController.class);
	@Autowired
	private IUserMsgReportService userMsgReportServie;
	@Autowired
	private IUserMessageLogManager userMessageLogManager;
	@RequestMapping(value = "/receiveUserMsgReport")
	@ResponseBody
	public String receiveUserMsgReport(HttpServletRequest request) {
		String fileId = null;
		String json = null;
		try {
			Map<String, Object> map = getParamsMap(request.getQueryString(), "UTF-8");
			fileId = String.valueOf( map.get("fileId"));
			json = request.getParameter("message");
		} catch (Exception e) {
			logger.error("参数解析错误");
		}
		if(fileId == null || json == null){
			return "fail";
		}
		logger.info(UserLogConstant.ModuleCode, "接收状态通知，fileId : " + fileId);
		logger.info(UserLogConstant.ModuleCode, "接收状态通知，json : " + json);
		List<UserMsgReportInput> reportList = JSON.parseArray(json, UserMsgReportInput.class);
		userMsgReportServie.updateMsgLog(fileId, reportList);
		return "success";
	}

	private Map<String, Object> getParamsMap(String queryString, String enc) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (queryString != null && queryString.length() > 0) {
			int ampersandIndex, lastAmpersandIndex = 0;
			String subStr, param, value;
			String[] paramPair, values, newValues;
			do {
				ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
				if (ampersandIndex > 0) {
					subStr = queryString.substring(lastAmpersandIndex,
							ampersandIndex - 1);
					lastAmpersandIndex = ampersandIndex;
				} else {
					subStr = queryString.substring(lastAmpersandIndex);
				}
				paramPair = subStr.split("=");
				param = paramPair[0];
				value = paramPair.length == 1 ? "" : paramPair[1];
				try {
					value = URLDecoder.decode(value, enc);
				} catch (UnsupportedEncodingException ignored) {
				}
				if (paramsMap.containsKey(param)) {
					values = (String[]) paramsMap.get(param);
					int len = values.length;
					newValues = new String[len + 1];
					System.arraycopy(values, 0, newValues, 0, len);
					newValues[len] = value;
				} else {
					newValues = new String[] { value };
				}
				paramsMap.put(param, newValues[newValues.length  - 1]);
			} while (ampersandIndex > 0);
		}
		return paramsMap;
	}
}