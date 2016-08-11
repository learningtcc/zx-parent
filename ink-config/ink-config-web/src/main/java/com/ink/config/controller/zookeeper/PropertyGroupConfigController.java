package com.ink.config.controller.zookeeper;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.ink.base.BaseController;
import com.ink.config.Service.IZookeeperPropertyService;
import com.ink.config.po.PropertyItemVO;
import com.ink.config.util.PropertyUtil;
import com.ink.config.util.ZookeeperPathUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.utils.ZKPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 属性组控制类
 * Created by aiyungui on 2016/6/7.
 */
@Controller
@RequestMapping("zookeeper/propertyGroup")
public class PropertyGroupConfigController extends BaseController {

    @Autowired
    private IZookeeperPropertyService zookeeperPropertyService;

    /**
     * 查询属性组
     * @param nodePath
     * @param version
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public List<String> findPropertyGroup(String nodePath,String version){
        return zookeeperPropertyService.listChildren(ZookeeperPathUtil.getVersionPath(nodePath, version));
    }

    /**
     * 创建属性组
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    @RequestMapping("create")
    @ResponseBody
    public String createPropertyGroup(String nodePath,String version,String groupName){
        String result = "0";
        try{
            boolean created = zookeeperPropertyService.createProperty(ZookeeperPathUtil.getPropertyGroupPath(nodePath, version, groupName), null);
            if (created)
                result = "1";
        }catch (Exception e){
            e.printStackTrace();
        }

        return writeAjaxResponse(result);
    }

    /**
     * 创建属性组
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public String deletePropertyGroup(String nodePath,String version,String groupName){
        String result = "0";
        try{

            zookeeperPropertyService.deleteProperty(ZookeeperPathUtil.getPropertyGroupPath(nodePath,version, groupName));
            zookeeperPropertyService.deleteProperty(ZookeeperPathUtil.getPropertyGroupPath(nodePath, version + "$", groupName));
            result = "1";
        }catch (Exception e){
            e.printStackTrace();
        }

        return writeAjaxResponse(result);
    }

    /**
     * 创建属性组
     * @param nodePath
     * @param version
     * @param groupName
     * @return
     */
    @RequestMapping("export")
    public void exportPropertyGroup(String nodePath,String version,String groupName,HttpServletResponse response){

        List<PropertyItemVO> items = zookeeperPropertyService.findPropertyItems(nodePath, version, groupName);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            String fileName = groupName + ".properties";
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/plain");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            if (!items.isEmpty()) {
                List<String> lines = PropertyUtil.formatPropertyLines(nodePath,version,groupName, items);
                IOUtils.writeLines(lines, "\r\n", out, Charsets.UTF_8.displayName());
            }
        } catch (IOException e)  {
            e.printStackTrace();
        } finally{
            if (out != null) {
                try{
                    out.flush();
                    out.close();
                }catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * 上传文件
     * @param nodePath
     * @param version
     * @return
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public String uploadFile(@RequestParam(value = "uploadFile") MultipartFile uploadFile,
                             String nodePath,String version){

        if (null == uploadFile || uploadFile.isEmpty()){
            return "0";
        }

        String result = "0";
        String fileName = uploadFile.getOriginalFilename();
        String group = Files.getNameWithoutExtension(fileName);
        InputStream inputstream = null;
        try{
            inputstream = uploadFile.getInputStream();
            List<PropertyItemVO> items = PropertyUtil.parseInputFile(inputstream);

            if (!items.isEmpty()){
                String groupFullPath = ZookeeperPathUtil.getPropertyGroupPath(nodePath,version,group);
                String commentFullPath = ZookeeperPathUtil.getPropertyGroupCommentPath(nodePath, version, group);
                boolean created = zookeeperPropertyService.createProperty(groupFullPath, null);
                if (created){
                    for (PropertyItemVO item : items){
                        if (StringUtils.isBlank(item.getValue())){
                            item.setValue(" ");
                        }
                        zookeeperPropertyService.createProperty(ZKPaths.makePath(groupFullPath, item.getName()), item.getValue());
                        if (!Strings.isNullOrEmpty(item.getComment())) {
                            zookeeperPropertyService.createProperty(ZKPaths.makePath(commentFullPath, item.getName()), item.getComment());
                        }
                    }
                    result = "1";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inputstream != null){
                try {
                    inputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
