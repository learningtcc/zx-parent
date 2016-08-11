package com.ink.config.controller.zookeeper;

import com.google.common.base.Charsets;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.ink.base.BaseController;
import com.ink.config.Service.IZookeeperPropertyService;
import com.ink.config.po.PropertyItem;
import com.ink.config.po.PropertyItemVO;
import com.ink.config.util.PropertyUtil;
import com.ink.config.util.ZookeeperPathUtil;
import org.apache.commons.io.IOUtils;
import org.apache.curator.utils.ZKPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 版本管理控制类
 * Created by aiyungui on 2016/6/7.
 */
@Controller
@RequestMapping("zookeeper/version")
public class VersionConfigController extends BaseController {

    @Autowired
    private IZookeeperPropertyService zookeeperPropertyService;

    /**
     * 查找版本
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public List<String> findVersion(String nodePath){
        List<String> versions = zookeeperPropertyService.listChildren(nodePath);
        if (versions != null) {
            versions = Lists.newArrayList(Iterables.filter(versions, new Predicate() {
                @Override
                public boolean apply(Object arg0) {
                    String input = (String) arg0;
                    return !input.endsWith("$");
                }
            }));
        }

        return versions;
    }

    /**
     * 创建版本
     * @param nodePath
     * @return
     */
    @ResponseBody
    @RequestMapping("createVersion")
    public String createVersion(String nodePath,String version){
        String result = "0";
        try{
            boolean isSuccess = zookeeperPropertyService.createProperty(ZookeeperPathUtil.getVersionPath(nodePath,version),null);
            if (isSuccess){
                result = "1";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return writeAjaxResponse(result);
    }

    /**
     * 克隆版本
     * @param nodePath
     * @param selectedVersion
     * @param versionToClone
     * @return
     */
    @ResponseBody
    @RequestMapping("cloneVersion")
    public String cloneVersion(String nodePath,String selectedVersion, String versionToClone){
        String result = "0";
        try{
            cloneTree(ZookeeperPathUtil.getVersionPath(nodePath,selectedVersion),
                    ZookeeperPathUtil.getVersionPath(nodePath,versionToClone));
            cloneTree(ZookeeperPathUtil.getVersionPath(nodePath, selectedVersion + "$"),
                    ZookeeperPathUtil.getVersionPath(nodePath, versionToClone + "$"));
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
        }

        return writeAjaxResponse(result);

    }

    /**
     * 删除版本
     * @param nodePath
     * @param version
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteVersion")
    public String deleteVersion(String nodePath,String version){
        String result = "0";
        try{
            zookeeperPropertyService.deleteProperty(ZookeeperPathUtil.getVersionPath(nodePath, version));
            zookeeperPropertyService.deleteProperty(ZookeeperPathUtil.getVersionPath(nodePath, version) + "$");
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
        }

        return writeAjaxResponse(result);
    }

    /**
     *导出文件
     * @param nodePath
     * @param version
     * @return
     */
    @RequestMapping("export")
    public void exportVersion(String nodePath,String version){
        HttpServletResponse response = getResponse();
        String versionPath = ZookeeperPathUtil.getVersionPath(nodePath, version);
        List<String> children = zookeeperPropertyService.listChildren(versionPath);

        ZipOutputStream zipOutputStream = null;
        OutputStream out = null;
        try{
            out  = response.getOutputStream();
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/zip");

            if ((children != null) && (!children.isEmpty())) {
                zipOutputStream = new ZipOutputStream(out);
                for (String groupName : children){
                    String groupPath = ZookeeperPathUtil.getPropertyGroupPath(nodePath,version, groupName);
                    String fileName = ZKPaths.getNodeFromPath(groupPath) + ".properties";

                    List<PropertyItemVO> items = zookeeperPropertyService.findPropertyItems(nodePath,version, groupName);
                    List<String> lines = PropertyUtil.formatPropertyLines(nodePath, version, groupName, items);
                    if (!lines.isEmpty()){
                        ZipEntry zipEntry = new ZipEntry(fileName);
                        zipOutputStream.putNextEntry(zipEntry);
                        IOUtils.writeLines(lines, "\r\n", zipOutputStream, Charsets.UTF_8.displayName());
                        zipOutputStream.closeEntry();
                    }
                }
            }

            String fileName = versionPath.replace('/', '-') + ".zip";
            response.setHeader("content-disposition", "attachment;filename=" + fileName);

        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (zipOutputStream != null){
                    zipOutputStream.close();
                }
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * version 上传zip文件
     * @param uploadFile
     * @param nodePath
     * @return
     */
    @ResponseBody
    @RequestMapping("uploadZip")
    public String uploadZip(@RequestParam(value = "uploadFile") MultipartFile uploadFile,
                            String nodePath,String version){
        if (null == uploadFile || uploadFile.isEmpty()){
            return "0";
        }

        String result = "0";
        ZipInputStream zipInputStream = null;
        try{
            zipInputStream = new ZipInputStream(uploadFile.getInputStream());
            ZipEntry nextEntry = null;
            while ((nextEntry = zipInputStream.getNextEntry()) != null){
                String entryName = nextEntry.getName();
                String group = Files.getNameWithoutExtension(entryName);
                List<PropertyItemVO> items = PropertyUtil.parseInputFile(zipInputStream);

                if (!items.isEmpty()){
                    String groupFullPath = ZookeeperPathUtil.getPropertyGroupPath(nodePath,version,group);
                    String commentFullPath = ZookeeperPathUtil.getPropertyGroupCommentPath(nodePath, version, group);
                    boolean created = zookeeperPropertyService.createProperty(groupFullPath, null);
                    if (created){
                        for (PropertyItemVO item : items){
                            zookeeperPropertyService.createProperty(ZKPaths.makePath(groupFullPath, item.getName()), item.getValue());
                            if (!Strings.isNullOrEmpty(item.getComment())) {
                                zookeeperPropertyService.createProperty(ZKPaths.makePath(commentFullPath, item.getName()), item.getComment());
                            }
                        }
                    }
                }
            }
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zipInputStream != null){
                try {
                    zipInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 克隆版本
     * @param sourceVersion
     * @param destinationVersion
     */
    private void cloneTree(String sourceVersion, String destinationVersion) {
        List<String> sourceGroups = zookeeperPropertyService.listChildren(sourceVersion);
        if (sourceGroups != null) {
            for (String sourceGroup : sourceGroups) {
                String sourceGroupFullPath = ZKPaths.makePath(sourceVersion, sourceGroup);
                String destinationGroupFullPath = ZKPaths.makePath(destinationVersion, sourceGroup);

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
