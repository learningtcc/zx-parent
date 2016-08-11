package com.ink.config.Service.impl;

import com.google.common.hash.Hashing;
import com.ink.config.Service.BaseService;
import com.ink.config.Service.IZookeeperNodeService;
import com.ink.config.Service.IZookeeperPropertyService;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zookeeper节点操作服务实现类
 * Created by aiyungui on 2016/6/6.
 */
@Service("zookeeperNodeService")
public class ZookeeperNodeServiceImpl implements IZookeeperNodeService {

    @Autowired
    private IZookeeperPropertyService zookeeperPropertyService;
    @Autowired
    private BaseService baseService;
    /**
     * 查询节点
     * @param nodePath
     * @param pwd
     * @return
     */
    @Override
    public Map<String, String> queryNode(String nodePath, String pwd) {
        Map<String,String> resultMap = new HashMap<String,String>();
        String code = "300";
        String message =  "";

        String hash = sha1Digest(pwd);
        try{
            Stat stat = (Stat)baseService.getClient().checkExists().forPath(nodePath);
            if (stat != null){
                byte[] data = (byte[])baseService.getClient().getData().forPath(nodePath);
                if(hash.equals(new String(data))){//验证成功
                    code = "200";
                }else{//验证失败
                    message = "密码有误，请检查。";
                }
            }else{
                message = "节点路径有误，请检查。";
            }
        } catch (Exception e){
            e.printStackTrace();
            message = "服务器异常。";
        }
        resultMap.put("code",code);
        resultMap.put("message",message);
        return resultMap;
    }


    /**
     * 新增节点
     * @param nodePath
     * @param pwd
     * @return
     */
    @Override
    public Map<String, String> addNode(String nodePath, String pwd) {
        Map<String,String> resultMap = new HashMap<String,String>();
        String code = "400";
        String message = "";
        byte[] sha1Digest = sha1Digest(pwd).getBytes();
        try{
            Stat stat = (Stat)baseService.getClient().checkExists().forPath(nodePath);
            if (stat == null){//节点不存在
                baseService.getClient().create().creatingParentsIfNeeded().forPath(nodePath, sha1Digest);
                code = "200";
            }else{
                message = "节点已存在，请检查。";
            }
        }
        catch (Exception e){
            message = "服务器异常。";
            e.printStackTrace();
        }

        resultMap.put("code",code);
        resultMap.put("message",message);
        return resultMap;
    }

    /**
     * 更新节点密码
     * @param nodePath
     * @param pwd
     * @return
     */
    @Override
    public String updatePwd(String nodePath, String pwd) {
        String result = "0";
        byte[] sha1Digest = sha1Digest(pwd).getBytes();
        try{
//            Stat stat = (Stat)getClient().checkExists().forPath(nodePath);
//            byte[] data = (byte[])getClient().getData().forPath(nodePath);
//            if ((data == null) || ((data.length == 1) && (data[0] != 0))){
//
//            }
            baseService.getClient().setData().forPath(nodePath, sha1Digest);
            result = "1";
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 删除节点
     * @param nodePath
     * @param pwd
     * @return
     */
    @Override
    public String deleteNode(String nodePath, String pwd) {
        String result = "0";

        try {
            baseService.getClient().delete().deletingChildrenIfNeeded().forPath(nodePath);
            int lastSplit = nodePath.lastIndexOf("/");
            if (lastSplit > 0){
                nodePath = nodePath.substring(0,nodePath.lastIndexOf("/"));
                List<String> childrenList = zookeeperPropertyService.findChildNode(nodePath);
                if (childrenList == null || childrenList.isEmpty()){
                    deleteNode(nodePath,pwd);
                }
            }
            result = "1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对密码进行加密
     * @param text
     * @return
     */
    private String sha1Digest(String text)
    {
        return Hashing.sha1().hashBytes(text.getBytes()).toString();
    }
}
