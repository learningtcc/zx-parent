package com.ink.config.util;

import org.apache.curator.utils.ZKPaths;

/**
 * zookeeper路径分隔符工具类
 * Created by aiyungui on 2016/6/7.
 */
public class ZookeeperPathUtil {

    /**
     * 获取属性所在路径
     * @param nodePath
     * @param version
     * @param groupName
     * @param propertyName
     * @return
     */
    public static String getPropertyPath(String nodePath, String version, String groupName,String propertyName) {

        String propertyGroupPath = getPropertyGroupPath(nodePath.trim(), version.trim(),groupName.trim());
        return ZKPaths.makePath(propertyGroupPath,propertyName.trim());
    }

    /**
     * 获取属性组所在路径
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    public static String getPropertyGroupPath(String nodePath, String version,String groupName) {
        String versionPath = getVersionPath(nodePath.trim(), version.trim());
        return ZKPaths.makePath(versionPath,groupName.trim());
    }

    /**
     * 获取版本所在路径
     * @param nodePath
     * @param version
     * @return
     */
    public static String getVersionPath(String nodePath,String version) {
        return ZKPaths.makePath(nodePath.trim(),version.trim());
    }

    /**
     * 获取属性所在路径
     * @param nodePath
     * @param version
     * @param groupName
     * @param propertyName
     * @return
     */
    public static String getPropertyCommentPath(String nodePath, String version, String groupName,String propertyName) {

        String propertyGroupPath = getPropertyGroupCommentPath(nodePath.trim(), version.trim(), groupName.trim());
        return ZKPaths.makePath(propertyGroupPath,propertyName.trim());
    }

    /**
     * 获取属性组所在路径
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    public static String getPropertyGroupCommentPath(String nodePath, String version,String groupName) {
        String versionPath = getVersionCommentPath(nodePath.trim(),version.trim());
        return ZKPaths.makePath(versionPath,groupName.trim());
    }

    /**
     * 获取版本所在路径（备注信息记录）
     * @param nodePath
     * @param version
     * @return
     */
    public static String getVersionCommentPath(String nodePath,String version) {
        return ZKPaths.makePath(nodePath.trim(),version.trim() + "$");
    }
}
