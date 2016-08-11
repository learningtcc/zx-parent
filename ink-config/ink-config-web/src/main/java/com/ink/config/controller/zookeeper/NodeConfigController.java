package com.ink.config.controller.zookeeper;

import com.google.common.collect.Maps;
import com.ink.base.BaseController;
import com.ink.base.log.util.YinkerLogger;
import com.ink.config.Service.IZookeeperNodeService;
import com.ink.config.Service.IZookeeperPropertyService;
import com.ink.config.util.AutoSearch;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * zookeeper配置管理中心
 * Created by aiyungui on 2016/6/6.
 */
@RequestMapping("zookeeper/node")
@Controller
public class NodeConfigController extends BaseController {

    @Autowired
    private IZookeeperNodeService zookeeperNodeService;
    @Autowired
    private IZookeeperPropertyService zookeeperPropertyService;

    /**
     * 进入节点配置管理页面
     * @return
     */
    @RequestMapping("/nodeConfig")
    public ModelAndView goConfigPage(){

        return new ModelAndView("/zookeeper/nodeConfig");
    }

    /**
     * 获取根节点下所有节点
     * @return
     */
    @RequestMapping("/findNodes")
    @ResponseBody
    public List<Map<String,String>> findNodes(String nodePath){

       try{
           if (StringUtils.isBlank(nodePath) || nodePath.length() < 2
                   || nodePath.length() - 1 != nodePath.lastIndexOf("/") ){
               return AutoSearch.getDataList(nodePath);
           }else{
               List<String> nodeList = zookeeperPropertyService.findChildNode(nodePath.substring(0,nodePath.length()-1));
               AutoSearch.setDataList(nodePath,nodeList);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return AutoSearch.getDataList(nodePath);
    }

    /**
     * 获取根节点下所有节点
     * @return
     */
    @RequestMapping("/cleanNodeData")
    @ResponseBody
    public void cleanNodeData(String nodePath){

        zookeeperPropertyService.cleanNodeData(nodePath);
    }

    /**
     * 进入属性配置管理页面
     * @return
     */
    @RequestMapping("/propertyConfig")
    public ModelAndView goPropertyConfigPage(){

        ModelAndView modelAndView =  new ModelAndView("/zookeeper/propertyConfig");
        modelAndView.addObject("nodePath",getRequest().getParameter("nodePath"));
        return modelAndView;
    }

    /**
     * 查询NODE
     * @return
     */
    @RequestMapping("/queryNode")
    @ResponseBody
    public Map queryNode(String nodePath,String pwd){

        Map<String, String> resultMap = Maps.newHashMap();
        try{
            resultMap = zookeeperNodeService.queryNode(nodePath,pwd);
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 添加NODE
     * @return
     */
    @ResponseBody
    @RequestMapping("/addNode")
    public Map addNode(String nodePath,String pwd){
        return zookeeperNodeService.addNode(nodePath, pwd);
    }

    /**
     * 更新NODE密码
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePwd")
    public String updatePwd(String nodePath,String pwd){

        if (StringUtils.isBlank(pwd)){
            pwd = "yk123456";
        }

        String result =  zookeeperNodeService.updatePwd(nodePath, pwd);
        return writeAjaxResponse(result);
    }

    /**
     * 删除NODE
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteNode")
    public String deleteNode(String nodePath,String pwd){

        String result =  zookeeperNodeService.deleteNode(nodePath,pwd);
        return writeAjaxResponse(result);
    }


}
