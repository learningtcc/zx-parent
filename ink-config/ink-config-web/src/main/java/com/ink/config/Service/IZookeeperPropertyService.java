package com.ink.config.Service;

import com.ink.config.po.PropertyItem;
import com.ink.config.po.PropertyItemVO;

import java.util.List;

/**
 * zookeeper版本服务类
 * Created by aiyungui on 2016/6/7.
 */
public interface IZookeeperPropertyService {

    /**
     * 查找属性
     * @param node
     * @return
     */
    List<PropertyItem> findProperties(String node);

    /**
     * 查找下级节点
     * @param node
     * @return
     */
    List<String> findChildNode(String node);
    /**
     * 列出子属性
     * @param node
     * @return
     */
    List<String> listChildren(String node);

    /**
     * 清理节点data
     * @param node
     * @return
     */
    boolean cleanNodeData(String node);

    /**
     * 创建属性
     * @param nodeName
     * @param value
     * @return
     */
    boolean createProperty(String nodeName, String value);

    /**
     * 更新属性
     * @param nodeName
     * @param value
     * @return
     */
    boolean updateProperty(String nodeName, String value);

    /**
     * 删除属性
     * @param nodeName
     */
    void deleteProperty(String nodeName);

    /**
     * 查询属性项
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    List<PropertyItemVO> findPropertyItems(String nodePath, String version, String groupName);
}
