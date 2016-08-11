package com.ink.config.controller.zookeeper;

import com.google.common.base.Strings;
import com.ink.base.BaseController;
import com.ink.config.Service.IZookeeperPropertyService;
import com.ink.config.po.PropertyItemVO;
import com.ink.config.util.ZookeeperPathUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 属性控制类
 * Created by aiyungui on 2016/6/7.
 */
@Controller
@RequestMapping("zookeeper/property")
public class PropertyConfigController extends BaseController {

    @Autowired
    private IZookeeperPropertyService zookeeperPropertyService;

    @RequestMapping("list")
    @ResponseBody
    public List<PropertyItemVO> list(String nodePath,String version,String groupName){

        List<PropertyItemVO> itemVOList = zookeeperPropertyService.findPropertyItems(nodePath,version,groupName);
        return itemVOList;
    }

    /**
     * 创建属性
     * @param propertyItem
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    @RequestMapping("create")
    @ResponseBody
    public String createProperty(PropertyItemVO propertyItem,String nodePath,String version,String groupName){

        String result = "0";
        try{
            String propertyPath = ZookeeperPathUtil.getPropertyPath(nodePath, version, groupName, propertyItem.getName());
            String value = propertyItem.getValue();
            if (StringUtils.isBlank(value)){
                value = " ";
            }
            boolean created = zookeeperPropertyService.createProperty(propertyPath, value);
            if (created) {
                if (!Strings.isNullOrEmpty(propertyItem.getComment())) {
                    String commentPath =ZookeeperPathUtil.getPropertyCommentPath(nodePath, version, groupName, propertyItem.getName());
                    zookeeperPropertyService.createProperty(commentPath , propertyItem.getComment());
                }
                result = "1";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return writeAjaxResponse(result);
    }

    /**
     * 更新属性
     * @param propertyItem
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public String updateProperty(PropertyItemVO propertyItem,String nodePath,String version,String groupName){

        String result = "0";
        try{
            String propertyPath = ZookeeperPathUtil.getPropertyPath(nodePath, version, groupName, propertyItem.getName());
            String commentPath =ZookeeperPathUtil.getPropertyCommentPath(nodePath, version, groupName, propertyItem.getName());
            boolean suc;
            if (StringUtils.isBlank(propertyItem.getValue())){
                propertyItem.setValue(" ");
            }
            if (propertyItem.getName().equals(propertyItem.getOriName())) {
                suc = zookeeperPropertyService.updateProperty(propertyPath, propertyItem.getValue());
                zookeeperPropertyService.updateProperty(commentPath, propertyItem.getComment());
            }else {
                String orgPropertyPath = ZookeeperPathUtil.getPropertyPath(nodePath, version, groupName, propertyItem.getOriName());
                String orgCommentPath =ZookeeperPathUtil.getPropertyCommentPath(nodePath, version, groupName, propertyItem.getOriName());

                zookeeperPropertyService.deleteProperty(orgPropertyPath);
                zookeeperPropertyService.deleteProperty(orgCommentPath);
                suc = zookeeperPropertyService.createProperty(propertyPath, propertyItem.getValue());

                if (suc) {
                    if (!Strings.isNullOrEmpty(propertyItem.getComment())) {
                        zookeeperPropertyService.createProperty(commentPath , propertyItem.getComment());
                    }
                }
            }
            if (suc){
                result = "1";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return writeAjaxResponse(result);
    }

    /**
     * 删除属性
     * @param propertyItem
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public String deleteProperty(PropertyItemVO propertyItem,String nodePath,String version,String groupName){
        String result = "0";
        try{
            String propertyPath = ZookeeperPathUtil.getPropertyPath(nodePath, version, groupName, propertyItem.getName());
            String commentPath =ZookeeperPathUtil.getPropertyCommentPath(nodePath, version, groupName, propertyItem.getName());
            zookeeperPropertyService.deleteProperty(propertyPath);
            zookeeperPropertyService.deleteProperty(commentPath);
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
        }

        return writeAjaxResponse(result);
    }
}
