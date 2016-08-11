package org.ink.config.web;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ink.config.Service.IZookeeperPropertyService;
import com.ink.config.po.PropertyItem;
import org.apache.curator.utils.ZKPaths;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 *
 * Created by aiyungui on 2016/6/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-applicationContext.xml")
public class ZookeeperPropertyServiceTest {

    @Autowired
    private IZookeeperPropertyService zookeeperPropertyService;

    private String nodeName = "/config/yinker/test";
    @Test
    public void getVersion(){
        List<String> list = zookeeperPropertyService.listChildren(nodeName);
        if (list != null) {
            list = Lists.newArrayList(Iterables.filter(list, new Predicate() {
                @Override
                public boolean apply(Object arg0) {
                    String input = (String) arg0;
                    return !input.endsWith("$");
                }
            }));
        }
        System.out.println(list);
    }

    @Test
    public void createVersion(){
       boolean result = zookeeperPropertyService.createProperty(ZKPaths.makePath(nodeName, "1.0.1"),null);
        System.out.println(result);
    }

    @Test
    public void cloneVersion(){
        String selectedVersion = "1.0.1";
        String versionToClone = "1.0.2";
        cloneTree(ZKPaths.makePath(nodeName, selectedVersion),
                ZKPaths.makePath(nodeName, versionToClone));
        cloneTree(ZKPaths.makePath(nodeName, selectedVersion + "$"),
                ZKPaths.makePath(nodeName, versionToClone + "$"));

//        boolean result = zookeeperPropertyService.createProperty(ZKPaths.makePath(nodeName, "1.0.1"),null);
//        System.out.println(result);
    }

    @Test
    public void deleteVersion(){
        String selectedVersion = "1.0.2";
        zookeeperPropertyService.deleteProperty(ZKPaths.makePath(nodeName,selectedVersion));
        zookeeperPropertyService.deleteProperty(ZKPaths.makePath(nodeName,selectedVersion)+ "$");
    }

    private void cloneTree(String sourceVersionPath, String destinationVersionPath) {
        List<String> sourceGroups = zookeeperPropertyService.listChildren(sourceVersionPath);
        if (sourceGroups != null) {
            for (String sourceGroup : sourceGroups) {
                String sourceGroupFullPath = ZKPaths.makePath(sourceVersionPath, sourceGroup);
                String destinationGroupFullPath = ZKPaths.makePath(destinationVersionPath, sourceGroup);

                zookeeperPropertyService.createProperty(destinationGroupFullPath, null);
                List<PropertyItem> sourceProperties = zookeeperPropertyService.findProperties(sourceGroupFullPath);
                if (sourceProperties != null) {
                    for (PropertyItem sourceProperty : sourceProperties) {
                        zookeeperPropertyService.createProperty(
                                ZKPaths.makePath(destinationGroupFullPath, sourceProperty.getName()),
                                sourceProperty.getValue());
                    }
                }
            }
        }
    }
}
