package com.ink.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:yinker-channel-core.xml"})
public class TestDao {
//    @Autowired
//    private BaseRedis baseRedis;
//    @Autowired
//    private IBasicResCodeManager basicResCodeManager;
//    @Autowired
//    private IAsileResCodeManager asileResCodeManager;
//    @Test
//    public void test()
//    {
//        basicResCodeManager.getById(1l);
//    }
//    @Test
//    public void test1()
//    {
//        asileResCodeManager.getById(1l);
//    }
//    @Test
//    public void testRedis(){
//        try {
//            System.out.println(baseRedis.getCache("token"));
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        try {
//            baseRedis.setCache("token", "1231456",3);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        try {
//            System.out.println(baseRedis.getCache("token"));
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
