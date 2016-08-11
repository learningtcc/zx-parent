package test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.platform.api.dubbo.IUserResourceService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/ApplicationContext.xml")
public class ACC30010Test {

	@Autowired
	private IUserResourceService platformDubboService;

	@Test
	public void Acc30010Test() throws Exception{
		platformDubboService.loginServeice("ff", "12345");
	//	platformDubboService.loginServeice("lisi", "12345");
		//platformDubboService.resourceService("742649464904617984", "lisi");
	}
	@Test
	public void ss() throws Exception{
		platformDubboService.loginServeice("mm", "12345");
	//	platformDubboService.loginServeice("lisi", "12345");
		//platformDubboService.resourceService("742649464904617984", "lisi");
	}
	@Test
	public void A1() throws Exception{
	
		platformDubboService.resourceService("lisi","shiro_manager");
	}
	@Test
	public void Acc30011Test() throws Exception{
		//platformDubboService.loginServeice("lisi", "12345");
		platformDubboService.resourceService( "wanghao","yinker-acc-web");
		//platformDubboService.resourceService("747619731779227648", "wanghao","ssss");
	}
	@Test
	public void Aest() throws Exception{
		//platformDubboService.loginServeice("lisi", "12345");
		//platformDubboService.resourceService("747619731779227648", "wanghao","yinker-user-ext");
		platformDubboService.resourceService( "wanghao","ssss");
	}
	@Test
	public void cache() throws Exception{
	//	redisCache.remove("wangwu");
	//	redisCache.clear();
		//redisCache.remove("shiro_redis_session");
	//	redisCache.clear();
	}
	
}
