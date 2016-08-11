<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>节点配置管理</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>

    <link rel="stylesheet" type="text/css" href="${yk}/static/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${yk}/static/css/propertyConfig.css"/>
    <script src="${yk}/static/config/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${yk}/gen-commons/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${yk}/static/config/ajaxfileupload.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="${yk}/static/config/maintain.js"></script>
    <script src="${yk}/static/config/propertyConfig.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
<div class="warp">
    <input type="hidden" name="nodePath" id="nodePath" value="${nodePath}"/>
    <input type="hidden" name="groupName" id="groupName" value=""/>
    <input type="hidden" name="content" id="content" value="${yk}"/>
    <div class="top_x_cont brs5 clearfix">
        <ul class="top_x_right fr">
            <li>
                版本:
                <select name="version" id="version" class="sltB sltB2" >
                    <option value="">请选择...</option>
                </select>
                <span class="sG"></span>
            </li>
            <li>
                <a href="#X" data-toggle="modal"  id="addVersion" data-target=".bs-example-modal-sm">新建版本</a>
                <a href="#X" id = "cloneVersion" data-toggle="modal" data-target=".bs-example-modal-sm" >克隆版本</a>
                <a href="#X" id = "deleteVersion" >删除版本</a>
                <span class="sG"></span>
            </li>
            <li>
                <a href="#X" onclick="exportVersion()">导出(ZIP)</a>
                <a href="#X" class="LeadIn" id="versionLoadZip" data-toggle="modal" data-target="#fileModel">导入(ZIP)</a>
                <%--<a href="#X">导入旧版数据(ZIP)</a>--%>
            </li>
        </ul>
    </div>
    <div class="cont_group clearfix">
        <div class="cont_group_L fl">
            <table border="1" cellspacing="0" cellpadding="0" class="tabCo">
                <thead><tr><td colspan="2" height="24">属性组</td></tr></thead>
                <thead>
                <tr>
                    <td>组名称</td>
                    <td>操作</td>
                </tr>
                <tbody id="propertyGroupBody">
                <tr id="nonePropertyGroup"><td colspan="2" align="left">没有数据</td></tr>   <!--当没有内容时显示-->
                </tbody>
            </table>
            <div class="group_L_Input mt20">
                <input type="text" name="" class="InputTxt brs5" value=""  id="propertyGroupName"/>
                <input type="button" name="" id="addPropertyGroup" class="InputBtn brs5" value="创建" />
                <input type="button" name="" id="loadPropertyGroup" class="InputBtn brs5" data-toggle="modal" data-target="#fileModel" value="上传" />
            </div>
        </div>
        <div class="cont_group_R fl">
            <div class="group_R_top clearfix">
                <div class="fl">属性名称:<input type="text" name="propertyKey" id="propertyKey" class="InputTxt" value="" /></div>
                <div class="fl">属性值:<input type="text" name="propertyValue" id="propertyValue" class="InputTxt" value="" /></div>
                <div class="fl">注释:<input type="text" name="propertyDes" id="propertyDes" class="InputTxt" value="" /></div>
                <div class="fl"><input type="button" class="InputBtn" id="queryProperty"  value="查询" /></div>
                <div class="fl"><input type="button" class="InputBtn" id="createProperty"  value="创建" /></div>
            </div>
            <%--<div style="overflow: auto;height: 350px"> </div>--%>
            <table border="1" cellspacing="0" cellpadding="0" class="tabRt mt20">
                <thead>
                <tr>
                    <td height="24" width="86">&nbsp;</td>
                    <td width="86">&nbsp;</td>
                    <td width="290">属性名称</td>
                    <td width="500">属性值</td>
                    <td width="500">注释</td>
                </tr>
                </thead>
                <tbody id="propertyBody">
                <tr id="noneProperty"><td colspan="5" align="left">没有数据</td></tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- /.modal -->
<!-- 模态框（Modal） 上传文件properties格式-->
<div class="modal fade " id="fileModel" tabindex="-1"  role="dialog" aria-labelledby="fileModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content ">
            <div class="modal-header fadeHead brn">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title " id="fileModalLabel">
                    Configuration Group Upload
                </h4>
            </div>
            <div class="modal-body">
                <div class="modal-content modal-content-boxn ">
                    <div class="modal-header fadeHead2">
                        <div class="form-group">
                            <label >File input</label>
                            <input type="hidden" name="uploadFlag" id="uploadFlag" class="Bipt" value="" />
                            <input type="file" id="uploadFile" name="uploadFile" >
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="closeFileModel" data-dismiss="modal">
                        关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="saveLoadFile">
                        提交更改
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--创建版本-->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header fadeHead brn">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="closeVersionModel">
                    &times;
                </button>
                <h4 class="modal-title " id="versionLabel">  Version Creation </h4>
            </div>
            <div class="modal-body">
                <input type="hidden" name="versionFlag" id="versionFlag" class="Bipt" value="" />
                <input type="text" name="versionName" id="versionName" class="Bipt" value="" />
                <button type="button" class="btn btn-default btn-sm" id="saveVersion">
                    <span class="glyphicon glyphicon-ok"></span>
                </button>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>
</body>
</html>


