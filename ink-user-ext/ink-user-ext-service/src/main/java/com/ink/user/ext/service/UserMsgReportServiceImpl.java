package com.ink.user.ext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.ext.api.IUserMsgReportService;
import com.ink.user.ext.api.constant.UserMessageConstant;
import com.ink.user.ext.api.input.UserMsgReportInput;
import com.ink.user.ext.core.dao.IUserMessageLogDao;

/**
 * 短信状态回执更新
 * @author yangchen
 * @date 2016年6月27日 下午2:26:52
 */
@Service("userMsgReportService")
public class UserMsgReportServiceImpl implements IUserMsgReportService{
	@Autowired
	private IUserMessageLogDao userMessageLogDao;
	public void updateMsgLog(String fileId, List<UserMsgReportInput> reportList){
		for(UserMsgReportInput report : reportList ){
			int status = UserMessageConstant.USERMESSAGELOG_STATUS_SENT_FAIL;
			if(report.getRetCode().equals("MS2002")){
				status = UserMessageConstant.USERMESSAGELOG_STATUS_SENT_SUCCESS;
			}
			userMessageLogDao.updateStatusByFileIdAndPhone(fileId, report.getMobile(), status);
		}
	}
}
