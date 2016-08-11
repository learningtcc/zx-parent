package junit.test;

import com.yinker.job.BalanceJob;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author haoyunfeng
 * @date 2016/4/19
 */
public class SpringAOPTest {
    @Test
    public void inteceptorTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beanAop.xml");
//        BalanceJob bean = (BalanceJob)ctx.getBean("balanceJob");
//        bean.execute("");
    }
}
