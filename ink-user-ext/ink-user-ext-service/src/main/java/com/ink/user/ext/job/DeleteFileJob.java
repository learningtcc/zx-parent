package com.ink.user.ext.job;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.job.AbstractBaseJob;
import com.ink.user.ext.api.IDeleteFileService;
import com.ink.user.ext.core.service.ISendInfoManager;

@Component
public class DeleteFileJob extends AbstractBaseJob {

	private static final YinkerLogger logger = YinkerLogger.getLogger(DeleteFileJob.class);
	@Autowired
	private ISendInfoManager sendInfoManager;
	@Autowired
	private IDeleteFileService deleteFileService;
	
	@Override
	public void execute() throws Exception {
		logger.info("自动删除文件定时任务开始!");

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -7);
		String end = DateUtil.formatToYYYYMMDDMMHHSS(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -1);
		String start = DateUtil.formatToYYYYMMDDMMHHSS(c.getTime());
		
		
		List<String> list = sendInfoManager.getFilePathListByDate(start, end);
		deleteFileService.deleteFileByDate(list);
	}

}
