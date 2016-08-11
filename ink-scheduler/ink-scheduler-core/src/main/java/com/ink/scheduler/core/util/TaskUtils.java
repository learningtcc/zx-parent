/**    
 *
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.ink.scheduler.core.util;

import java.text.ParseException;
import java.util.Date;

import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.ApplicationContext;

/**    
 * <p>类描述</p>
 * <p>于2016年3月31日 由 guokemeng 创建 </p>
 * @author  <p>当前负责人 guokemeng</p>     
 * @since <p>项目版本号</p> 
 */
public class TaskUtils {

	public static ApplicationContext APPLICATIONCONTEXT;
	
	/**     
	 * 判断cron时间表达式正确性     
	 * @param cronExpression     
	 * @return      
	 */     
	public static boolean isValidExpression(final String cronExpression){     
		CronTriggerImpl trigger = new CronTriggerImpl();        
        try {     
			trigger.setCronExpression(cronExpression);     
			Date date = trigger.computeFirstFireTime(null);       
	        return date != null && date.after(new Date());        
		} catch (ParseException e) {

		}      
        return false;     
	} 
	
}
