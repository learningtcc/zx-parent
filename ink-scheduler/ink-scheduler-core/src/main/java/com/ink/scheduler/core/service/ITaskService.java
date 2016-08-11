

package com.ink.scheduler.core.service;


import com.ink.scheduler.core.po.TimerTaskJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * 任务调度接口
 * @author haoyunfeng
 */
public interface ITaskService {

	/**
	 * pauseJob <p>启动任务</p>
	 * @param timerTaskJob
	 * @return
	 */
	public boolean addJob(TimerTaskJob timerTaskJob) throws SchedulerException;
	
	/**    
	 * pauseJob <p>暂停任务</p>   
	 * @param timerTaskJob
	 * @return  
	*/
	public boolean pauseJob(TimerTaskJob timerTaskJob);
	
	/**    
	 * resumeJob <p>恢复任务  </p>   
	 * @param timerTaskJob
	 * @return  
	*/
	public boolean resumeJob(TimerTaskJob timerTaskJob);
	
	/**    
	 * deleteJob <p>删除任务</p>   
	 * @param timerTaskJob
	 * @return  
	*/
	public boolean deleteJob(TimerTaskJob timerTaskJob);
	
	/**    
	 * begainJob <p>立即执行一个任务   </p>
	 * @param timerTaskJob
	*/
	public void begainJob(TimerTaskJob timerTaskJob) throws Exception;
	
	/**    
	 * updateCronExpression <p>更新任务时间表达式   </p>   
	 * @param timerTaskJob
	*/
	public void updateCronExpression(TimerTaskJob timerTaskJob) throws Exception;
	
	
	/**    
	 * getRunningJob <p>正在运行的job</p>   
	 * @return
	 * @throws Exception  
	*/
	public List<TimerTaskJob> getRunningJob() throws Exception;

	/**
	 * 取得计划中的job
	 * @return
	 * @throws Exception
     */
	public List<TimerTaskJob> getSchedulerJob() throws Exception;

}
