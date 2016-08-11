package com.ink.user.ext.service;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.user.ext.api.IUserMsgJobTriggerService;

/**
 * 触发发送任务实现
 * @author yangchen
 * @date 2016年6月22日 上午11:23:12
 */
@Service
public class UserMsgJobTriggerServiceImpl implements IUserMsgJobTriggerService
{
	private static final YinkerLogger logger = YinkerLogger.getLogger(UserMsgJobTriggerServiceImpl.class);
	@Autowired
	private Job userMsgJob;
	@Autowired
	private SimpleJobLauncher userMsgJobLauncher;
	@Override
	public void sendMessage(String filePath, Long fileId, Date sendTime, Integer sendType,
			String mchId) {
		if(sendType == null || sendType == null){
			sendType = 0;
		}
		// 启动任务，注意用异步处理器
		userMsgJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		try {
			userMsgJobLauncher.run(userMsgJob,  new JobParametersBuilder()
					.addString("inputFilePath",filePath)
                    .addDate("sendTime", sendTime)
                    .addString("sendType", String.valueOf(sendType))
                    .addString("mchId", mchId)
                    .addLong("fileId", fileId)
                    .addDate("newDate", new Date())//这个参数没有实际意义，只是保证任务不重复
                    .toJobParameters());
		} catch (Exception e) {
			logger.error("用户批量发消息任务启动异常", e);
		}
	}
}
