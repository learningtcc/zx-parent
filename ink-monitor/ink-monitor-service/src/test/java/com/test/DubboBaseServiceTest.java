package com.test;

import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.SystermInfo;
import com.ink.monitor.core.query.SystermInfoQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * DubboBaseService 单元测试
 * Created by aiyungui on 2016/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-context.xml"})
public class DubboBaseServiceTest {

    @Autowired
    private DubboBaseService dubboBaseService;
    private String beanName="systermInfoManager";
    @Test
    public void getByIdTest(){

        int id = 10001;

        try {
            SystermInfo systermInfo = (SystermInfo) dubboBaseService.getById(beanName,id);
            System.out.println(systermInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findTest(){

        int id = 10001;

        try {
            SystermInfoQuery systermInfo = new SystermInfoQuery();
            systermInfo.setId(id);
            Page page = dubboBaseService.findPage(beanName,systermInfo);
            System.out.println(page);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void executeDynamicMethodTest(){

        int id = 10001;

        try {
            Object[] objects = {id,"0"};
            SystermInfo systermInfo = (SystermInfo) dubboBaseService.executeDynamicMethod(beanName,"deleteInfo",objects);
            System.out.println(systermInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
