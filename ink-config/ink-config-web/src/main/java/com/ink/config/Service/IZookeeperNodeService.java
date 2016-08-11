package com.ink.config.Service;

import java.util.Map;
/**
 * zookeeper节点操作服务
 * Created by aiyungui on 2016/6/6.
 */
public interface IZookeeperNodeService {

    Map<String,String> queryNode(String nodePath, String pwd);

    Map<String,String> addNode(String nodePath, String pwd);

    String updatePwd(String nodePath, String pwd);

    String deleteNode(String nodePath, String pwd);
}
