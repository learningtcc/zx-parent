package com.ink.config.Service.impl;

import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ink.base.log.util.YinkerLogger;
import com.ink.config.Service.BaseService;
import com.ink.config.Service.IZookeeperPropertyService;
import com.ink.config.po.PropertyItem;
import com.ink.config.po.PropertyItemVO;
import com.ink.config.util.ZookeeperPathUtil;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * zookeeper版本服务实现类
 * Created by aiyungui on 2016/6/7.
 */
@Service("zookeeperPropertyService")
public class ZookeeperPropertyServiceImpl implements IZookeeperPropertyService {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(ZookeeperPropertyServiceImpl.class);
    @Autowired
    private BaseService baseService;
    /**
     * 查找属性
     *
     * @param node
     * @return
     */
    @Override
    public List<PropertyItem> findProperties(String node) {
        List<PropertyItem> properties = Lists.newArrayList();
        try{
            Stat stat = baseService.getClient().checkExists().forPath(node);
            if (stat != null) {
                GetChildrenBuilder childrenBuilder = baseService.getClient().getChildren();
                List<String> children = childrenBuilder.forPath(node);
                GetDataBuilder dataBuilder = baseService.getClient().getData();
                if (children != null) {
                    for (String child : children) {
                        String propPath = ZKPaths.makePath(node, child);
                        PropertyItem item = new PropertyItem(child, new String((byte[])dataBuilder.forPath(propPath), Charsets.UTF_8));
                        properties.add(item);
                    }
                }
            }
        }catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return properties;
    }

    /**
     * 查找属性
     *
     * @param node
     * @return
     */
    @Override
    public List<String> findChildNode(String node) {
        List<String> children = Lists.newArrayList();
        try{
            Stat stat = baseService.getClient().checkExists().forPath(node);
            if (stat != null) {
                byte[] data = (byte[])baseService.getClient().getData().forPath(node);
                if (data == null || data.length < 1){
                    GetChildrenBuilder childrenBuilder = baseService.getClient().getChildren();
                    children = childrenBuilder.forPath(node);
                }
            }
        }catch (Exception e) {
            yinkerLogger.error("查找属性异常",e);
        }
        return children;
    }

    /**
     * 清理节点data
     *
     * @param node
     * @return
     */
    @Override
    public boolean cleanNodeData(String node) {

        try{
            Stat stat = baseService.getClient().checkExists().forPath(node);
            if (stat != null) {
                byte[] data = (byte[])baseService.getClient().getData().forPath(node);
                if (data != null){
                    baseService.getClient().setData().forPath(node,null);
                }
            }
            return true;
        }catch (Exception e) {
            yinkerLogger.error("查找属性异常",e);
        }

        return false;
    }

    /**
     * 列出子属性
     *
     * @param node
     * @return
     */
    @Override
    public List<String> listChildren(String node) {
        List<String> children = Lists.newArrayList();
        try{
            Stat stat = baseService.getClient().checkExists().forPath(node);
            if (stat != null){
                GetChildrenBuilder childrenBuilder = baseService.getClient().getChildren();
                children = childrenBuilder.forPath(node);
                Set<String> childrenSet  = Sets.newTreeSet();
                childrenSet.addAll(children);
                children.clear();
                children.addAll(childrenSet);
            }
        }catch (Exception e){
            throw Throwables.propagate(e);
        }
        return children;
    }



    /**
     * 创建属性
     *
     * @param nodeName
     * @param value
     * @return
     */
    @Override
    public boolean createProperty(String nodeName, String value) {
        boolean suc = false;
        try {
            Stat stat = (Stat)baseService.getClient().checkExists().forPath(nodeName);
            if (stat == null) {
                String opResult = null;
                if (Strings.isNullOrEmpty(value)) {
                    opResult = (String)baseService.getClient().create().creatingParentsIfNeeded().forPath(nodeName);
                } else {
                    opResult = (String)baseService.getClient().create().creatingParentsIfNeeded().forPath(nodeName, value.getBytes(Charsets.UTF_8));
                }
                suc = Objects.equal(nodeName, opResult);
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return suc;
    }

    /**
     * 更新属性
     *
     * @param nodeName
     * @param value
     * @return
     */
    @Override
    public boolean updateProperty(String nodeName, String value) {
        boolean suc = false;
        try {
            Stat stat = (Stat)baseService.getClient().checkExists().forPath(nodeName);
            if (stat != null) {
                Stat opResult = (Stat)baseService.getClient().setData().forPath(nodeName, value.getBytes(Charsets.UTF_8));
                suc = opResult != null;
            } else {
                String opResult = (String)baseService.getClient().create().creatingParentsIfNeeded().forPath(nodeName, value.getBytes(Charsets.UTF_8));
                suc = Objects.equal(nodeName, opResult);
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        return suc;
    }

    /**
     * 删除属性
     * @param nodeName
     */
    @Override
    public void deleteProperty(String nodeName) {
        try {
            Stat stat = (Stat)baseService.getClient().checkExists().forPath(nodeName);
            if (stat != null) {
                baseService.getClient().delete().deletingChildrenIfNeeded().forPath(nodeName);
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * 查询属性项
     *
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    @Override
    public List<PropertyItemVO> findPropertyItems(String nodePath, String version, String groupName) {
        List<PropertyItemVO> items = null;
        if ((!Strings.isNullOrEmpty(nodePath)) && (!Strings.isNullOrEmpty(version))
                && (!Strings.isNullOrEmpty(groupName))) {
            List<PropertyItem> propertyItems = findProperties(ZookeeperPathUtil.getPropertyGroupPath(nodePath, version, groupName));
            List<PropertyItem> propertyComments = findProperties(ZookeeperPathUtil.getPropertyGroupPath(nodePath, version + "$", groupName));
            if (propertyItems != null) {
                Map<String, String> comments = Maps.newHashMap();
                if (propertyComments != null) {
                    for (PropertyItem comment : propertyComments) {
                        comments.put(comment.getName(), comment.getValue());
                    }
                }
                items = Lists.newArrayList();
                for (PropertyItem propertyItem : propertyItems) {
                    PropertyItemVO vo = new PropertyItemVO(propertyItem);
                    String comment = (String) comments.get(propertyItem.getName());
                    vo.setComment(null == comment ? "":comment);
                    items.add(vo);
                }
                Collections.sort(items);
            }
        }
        return items;
    }

}
