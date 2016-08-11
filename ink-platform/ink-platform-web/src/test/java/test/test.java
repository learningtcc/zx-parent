package test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.platform.api.dubbo.UserCacheService;
import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.model.SecUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/ApplicationContext.xml")
public class test {

	@Autowired
	private UserCacheService userCacheService;

	@Test
	public void a() throws Exception{
		 userCacheService.getCacheUser("mm");
	}
	
	@Test
	public void resource() throws Exception{
	}
	
}
