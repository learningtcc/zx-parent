package com.yinker.timer.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.yinker.base.utils.logUtil.YinkerLoger;

/**
 * User: king
 * Date: 16-3-31
 * Time: 上午9:28
 */
@Service
public class YinkerDemoListJob2 {
    YinkerLoger logger=  YinkerLoger.getLogger(YinkerDemoListJob2.class);

    //private static int ct=0;
 //   @Autowired
 //   private IUserMainManager userMainManager;
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void  excute(){
        logger.info("YinkerDemoListJob2  is loading...... " );
        try{
     //       List<UserMain> userList= userMainManager.find(null);

        }   catch (Exception e){
            logger.error("10001","YinkerDemoListJob2 error  " + e.getMessage());
        }
        System.out.println( Thread.currentThread().getName()+",当前开始时间 " + simpleDateFormat.format(new Date()));
        try {
            Thread.sleep(20*1000);
            System.out.println( Thread.currentThread().getName()+",当前结束时间: "  + simpleDateFormat.format(new Date()));
        } catch (InterruptedException e) {
            logger.error(e.getMessage()) ;
        }

    }
}
