package com.ink.user.ext.api;

import java.util.List;

import com.ink.user.ext.api.input.UserMsgReportInput;

public interface IUserMsgReportService {
	public void updateMsgLog(String fileId, List<UserMsgReportInput> reportList);
}
