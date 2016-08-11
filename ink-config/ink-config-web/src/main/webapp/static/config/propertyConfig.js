var content;
var propertiesData;
$(function () {
    content = $("#content").val();
    function functionTab(obj1, obj2) {
        var tr = $(obj1),
            tbody = $(obj2),
            tleght = tbody.find("tr").length;

        if (tleght == 0) {
            tbody.append("<tr><td colspan=" + "2" + " align=" + "left" + ">No records found.</td></tr>");
        } else {
            $(document).on("click", ".tabCo tbody tr", function () {
                $(this).addClass("on").siblings().removeClass("on");
            })
        }
    }

    function functionTab2(obj1, obj2) {
        var tr = $(obj1);

        tr.each(function (index, item) {
            if (index % 2 == 0) {
                $(this).css('background', '#fff')
            } else {
                $(this).css('background', '#f2f5f9')
            }
        })
    }

    functionTab(".tabCo tbody tr", ".tabCo tbody");
    functionTab2('.tabRt tbody tr');
    function resetWidth() {
        var widows = $(window).width(),
            widR = $(".cont_group_R").width(),
            widR2 = $(".group_R_top").width(),
            wtRt = $(".tabRt").width();

        wtRt = widR2 = widR = $(".cont_group_R").width(widows - 460 + 'px');

    }

    resetWidth();
    window.onresize = resetWidth;

    versionChangeForStyle();
    //刷新版本
    syncVersion();

    //添加属性
    $("#createProperty").click(function () {
        var nodePath = $("#nodePath").val();
        var version = $("#version").val();
        var groupName = $("#groupName").val();
        var propertyKey = $("#propertyKey").val();
        var propertyValue = $("#propertyValue").val();
        var propertyDes = $("#propertyDes").val();

        if(groupName.length<1){
            alert("请选择属性组信息");
            return false;
        }
        if (propertyKey.length < 1 || propertyValue.length < 1) {
            alert("请输入正确的属性信息");
            return false;
        }

        $.post(content + "/zookeeper/property/create.do", {
                "nodePath": nodePath, "version": version, "groupName": groupName,
                "name": propertyKey, "value": propertyValue, "comment": propertyDes
            },
            function (data) {
                if (data == "1") {
                    showProperty();
                    $("#propertyGroupName").val("");
                    $("#propertyKey").val("");
                    $("#propertyValue").val("");
                    $("#propertyDes").val("");
                } else {
                    alert('创建属性失败');
                }
            }
        );
    });
    //查询属性
    $("#queryProperty").click(function () {
        var propertyKey = $("#propertyKey").val();
        var propertyValue = $("#propertyValue").val();
        var propertyDes = $("#propertyDes").val();
        var optionStr = "";
        $.each(propertiesData, function (i, item) {
            var itemName =  item.name;
            var itemValue =  item.value;
            var itemDes = item.comment;
            var ifFind = true;
            if(propertyKey.length >0 && itemName.indexOf(propertyKey) == -1){
                ifFind = false;
            }
            if(propertyValue.length >0 && itemValue.indexOf(propertyValue) == -1){
                ifFind = false;
            }
            if(propertyDes.length >0 && itemDes.indexOf(propertyDes) == -1){
                ifFind = false;
            }
            if(ifFind){
                optionStr += "<tr name='propertyTr'><td>";
                optionStr += "<a href='javascript:;' class='bj'><span class='glyphicon glyphicon-pencil' style='font-size: 14px;'></span></a>";
                optionStr += "<p class='btnAll'>";
                optionStr += "<a href='javascript:;' class='bc'><span class='glyphicon glyphicon-ok' style='font-size: 14px;'></span></a>";
                optionStr += "<a href='javascript:;' class='qx'><span class='glyphicon glyphicon-remove' style='font-size: 14px;'></span></a>";
                optionStr += "</p></td>";
                optionStr += "<td><a href='javascript:;' class='btnremove' id='btnRemoveProperty' onclick='removeProperty(this)' data='" + itemName + "'><span class='glyphicon glyphicon-remove' style='font-size: 14px;'></span></a></td>";
                optionStr += "<td><span class='tabVal'>" + itemName + "</span>";
                optionStr += "<input type='text' name='name' class='tabInput' value='" + itemName + "' /><input type='hidden' name='oriName' class='tabInput' value='" + itemDes + "' />";
                optionStr += "</td> <td>";
                optionStr += "<span class='tabVal'>" + itemValue + "</span>";
                optionStr += "<input type='text' name='value' class='tabInput' value='" + itemValue + "' />";
                optionStr += "</td><td>";
                optionStr += "<span class='tabVal'>" + itemDes + "</span>";
                optionStr += "<input type='text' name='comment' class='tabInput' value='" + itemDes + "' />";
                optionStr += "</td></tr>";
            }

        });
        $("tr[name='propertyTr']").remove();

        if (optionStr.length < 1) {
            $("#noneProperty").show();
        } else {
            $("#noneProperty").hide();
            $("#propertyBody").append(optionStr);
        }
        reLoadFun();
    });

    //添加属性组
    $("#addPropertyGroup").click(function () {
        var nodePath = $("#nodePath").val();
        var version = $("#version").val();
        var groupName = $("#propertyGroupName").val();
        if(version.length <1){
            alert("请选择版本信息");
            return false;
        }
        if (groupName.length < 1) {
            alert("请输入属性组名称");
            return false;
        }

        $.post(content + "/zookeeper/propertyGroup/create.do", {
                "nodePath": nodePath,
                "version": version,
                "groupName": groupName
            },
            function (data) {
                if (data == "1") {
                    $("#propertyGroupName").val("");
                    showPropertyGroup(version);
                } else {
                    alert('创建属性组失败');
                }
            }
        );
    });

    $("#version").change(function () {
        var version = $(this).val();
        showPropertyGroup(version);
        versionChangeForStyle();
        showProperty();
    });

    $("#addVersion").click(function () {
        $("#versionLabel").text("新建版本");
        $("#versionFlag").val("0");
    });

    $("#cloneVersion").click(function () {
        $("#versionLabel").text("克隆版本");
        $("#versionFlag").val("1");
    });
    $("#versionLoadZip").click(function () {
        $("#fileModalLabel").text("导入(ZIP)");
        $("#uploadFlag").val("0");
    });

    $("#loadPropertyGroup").click(function () {
        $("#fileModalLabel").text("上传属性组");
        $("#uploadFlag").val("1");
    });

    $("#deleteVersion").click(function () {
        if(!window.confirm("确认删除版本吗？")){
                return ;
        }
        var nodePath = $("#nodePath").val();
        var version = $("#version").val();
        $.post(content + "/zookeeper/version/deleteVersion.do", {"nodePath": nodePath, "version": version},
            function (data) {
                if (data == "1") {
                    syncVersion();
                    showPropertyGroup("");
                    versionChangeForStyle();
                    showProperty();
                } else {
                    alert('删除版本失败');
                }
            }
        );
    });

    $("#saveVersion").click(function () {
        var versionFlag = $("#versionFlag").val();
        var versionName = $("#versionName").val();
        var nodePath = $("#nodePath").val();
        var version = $("#version").val();
        var url = content + "";
        if (versionName.length < 1) {
            alert("请输入版本名称");
        }
        if (versionFlag == 0) {//新建版本
            url += "/zookeeper/version/createVersion.do?nodePath=" + nodePath + "&version=" + versionName;
        } else {//克隆版本

            url += "/zookeeper/version/cloneVersion.do?nodePath=" + nodePath + "&selectedVersion=" + version + "&versionToClone=" + versionName;
        }
        $.post(url,
            function (data) {
                if (data == "1") {
                    syncVersion();
                    showPropertyGroup("");
                    versionChangeForStyle();
                    showProperty();
                    $("#versionName").val("");
                    $("#closeVersionModel").trigger("click");
                } else {
                    alert('version操作失败');
                }
            }
        );

    });

    //保存上传文件
    $("#saveLoadFile").click(function () {
        var uploadFlag = $("#uploadFlag").val();
        var uploadFile = $("#uploadFile").val();
        var nodePath = $("#nodePath").val();
        var version = $("#version").val();
        if (uploadFile.length < 1) {
            alert("请选择上传文件");
            return false;
        }
        var url = content;
        if (uploadFlag == 0) {
            url += "/zookeeper/version/uploadZip.do?nodePath=" + nodePath + "&version=" + version;
            if (uploadFile.indexOf(".zip") < 1) {
                alert("请选择正确的版本文件。版本文件后缀为：.zip");
                return false;
            }
        } else {
            url += "/zookeeper/propertyGroup/uploadFile.do?nodePath=" + nodePath + "&version=" + version;
            if (uploadFile.indexOf(".properties") < 1) {
                alert("请选择正确的属性组文件。属性组文件后缀为：.properties");
                return false;
            }

        }
        $.ajaxFileUpload({
            //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
            url: url,
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'uploadFile',           //文件选择框的id属性
            dataType: 'json',                       //服务器返回的格式,可以是json或xml等
            success: function (data, status) {        //服务器响应成功时的处理函数
                if (data == "1") {
                    showPropertyGroup(version);
                    versionChangeForStyle();
                    alert("上传成功");
                    $("#closeFileModel").trigger("click");
                } else {
                    alert('上传失败');
                }
            },
            error: function (data, status, e) { //服务器响应失败时的处理函数
                console.log(e);
                console.log(data);
                alert("系统出错，请联系管理员," + e)
            }
        });
    });

});

function syncProperty(obj) {
    var groupName = $(obj).attr("data");
    $("#groupName").val(groupName);
    //$(this).css({ color: "#fff", background: "#95bcd2" });
    showProperty();
}
//显示属性
function showProperty() {

    var nodePath = $("#nodePath").val();
    var version = $("#version").val();
    var groupName = $("#groupName").val();
    if (version.length < 1 || groupName.length < 1) {
        $("tr[name='propertyTr']").remove();
        $("#noneProperty").show();
        return;
    }
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: content + "/zookeeper/property/list.do?nodePath=" + nodePath + "&version=" + version + "&groupName=" + groupName,
        dataType: 'json',
        success: function (data) {
            var optionStr = "";
            propertiesData = data;
            $.each(data, function (i, item) {

                optionStr += "<tr name='propertyTr'><td>";
                optionStr += "<a href='javascript:;' class='bj'><span class='glyphicon glyphicon-pencil' style='font-size: 14px;'></span></a>";
                optionStr += "<p class='btnAll'>";
                optionStr += "<a href='javascript:;' class='bc'><span class='glyphicon glyphicon-ok' style='font-size: 14px;'></span></a>";
                optionStr += "<a href='javascript:;' class='qx'><span class='glyphicon glyphicon-remove' style='font-size: 14px;'></span></a>";
                optionStr += "</p></td>";
                optionStr += "<td><a href='javascript:;' class='btnremove' id='btnRemoveProperty' onclick='removeProperty(this)' data='" + item.name + "'><span class='glyphicon glyphicon-remove' style='font-size: 14px;'></span></a></td>";
                optionStr += "<td><span class='tabVal'>" + item.name + "</span>";
                optionStr += "<input type='text' name='name' class='tabInput' value='" + item.name + "' /><input type='hidden' name='oriName' class='tabInput' value='" + item.name + "' />";
                optionStr += "</td> <td>";
                optionStr += "<span class='tabVal'>" + item.value + "</span>";
                optionStr += "<input type='text' name='value' class='tabInput' value='" + item.value + "' />";
                optionStr += "</td><td>";
                optionStr += "<span class='tabVal'>" + item.comment + "</span>";
                optionStr += "<input type='text' name='comment' class='tabInput' value='" + item.comment + "' />";
                optionStr += "</td></tr>";
            });
            $("tr[name='propertyTr']").remove();

            if (optionStr.length < 1) {
                $("#noneProperty").show();
            } else {
                $("#noneProperty").hide();
                $("#propertyBody").append(optionStr);
            }
            reLoadFun();
        },
        error: function () {
            alert("系统出错，请联系管理员")
        }
    });

}

//显示属性组
function showPropertyGroup(version) {
    var nodePath = $("#nodePath").val();
    $("#groupName").val("");
    if (version.length < 1) {
        $("tr[name='propertyGroupTr']").remove();
        $("#nonePropertyGroup").show();
    } else {
        $("#nonePropertyGroup").hide();
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: content + "/zookeeper/propertyGroup/list.do?nodePath=" + nodePath + "&version=" + version,
            dataType: 'json',
            success: function (data) {
                var optionStr = "";
                $.each(data, function (i, item) {
                    optionStr += "<tr name='propertyGroupTr' onclick='syncProperty(this)' data='" + item + "'><td >" + item + "</td>";
                    optionStr += "<td><a href='#' class='btnremove' id='btnRemoveGroup' onclick='removePropertyGroup(this)' data='" + item + "'>删除</a>" +
                        "<a href='#' onclick='exportPropertyGroup(this)' data='" + item + "'>导出</a></td></tr>";
                });
                $("tr[name='propertyGroupTr']").remove();

                if (optionStr.length < 1) {
                    $("#nonePropertyGroup").show();
                } else {
                    $("#propertyGroupBody").append(optionStr);
                }
            },
            error: function () {
                alert("系统出错，请联系管理员")
            }
        });

    }
}

//异步获取版本信息
function syncVersion() {
    var nodePath = $("#nodePath").val();
    var url = content + "/zookeeper/version/list.do?nodePath=" + nodePath;
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        success: function (data) {
            var optionStr = "<option value=''>请选择...</option>";
            $.each(data, function (i, item) {
                optionStr += "<option value='" + item + "'>" + item + "</option>";

            });
            $("#version").empty();
            $("#version").append(optionStr);
        },
        error: function () {
            alert("系统出错，请联系管理员")
        }
    });
}
//操作按钮根据版本选择隐藏或显示
function versionChangeForStyle() {

    var result = $("#version").find("option:selected").attr("value");
    if (result.length < 1) {
        $(".top_x_right a:gt(0)").addClass('off');
        $(".group_L_Input input:gt(0)").addClass('off');
    } else {
        $(".top_x_right a:gt(0)").removeClass('off');
        $(".group_L_Input input:gt(0)").removeClass('off');
    }
}
//移除属性组
function removePropertyGroup(obj) {

    if(window.confirm("确认删除属性组吗")){
        var nodePath = $("#nodePath").val();
        var version = $("#version").val();
        var groupName = $(obj).attr("data");
        //删除前先导出一份到本地
        exportPropertyGroup(obj);
        $.post(content + "/zookeeper/propertyGroup/delete.do", {
                "nodePath": nodePath,
                "version": version,
                "groupName": groupName
            },
            function (data) {
                if (data == "1") {
                    $(obj).parent().parent().remove();
                    var len = $('#btnRemoveGroup').length;
                    if (len == 0) {
                        $("#nonePropertyGroup").show();
                    }
                } else {
                    alert('删除失败');
                }
            }
        );
    }
}
//移除属性
function removeProperty(obj) {
    var nodePath = $("#nodePath").val();
    var version = $("#version").val();
    var groupName = $("#groupName").val();
    var name = $(obj).attr("data");
    $.post(content + "/zookeeper/property/delete.do", {
            "nodePath": nodePath,
            "version": version,
            "groupName": groupName,
            "name": name
        },
        function (data) {
            if (data == "1") {
                $(obj).parent().parent().remove();
                //$(this).parent().parent().remove();
                var len = $('#btnRemoveProperty').length;
                if (len == 0) {
                    $("#noneProperty").show();
                }
            } else {
                alert('删除失败');
            }
        }
    );
}

//导出属性组
function exportPropertyGroup(obj) {
    var nodePath = $("#nodePath").val();
    var version = $("#version").val();
    var groupName = $(obj).attr("data");
    var url = content + "/zookeeper/propertyGroup/export.do?nodePath=" + nodePath + "&version=" + version + "&groupName=" + groupName;
    window.open(url);

}
//导出版本
function exportVersion() {
    var nodePath = $("#nodePath").val();
    var version = $("#version").val();
    var url = content + "/zookeeper/version/export.do?nodePath=" + nodePath + "&version=" + version;
    window.open(url);
}

//重新加载事件
function reLoadFun() {
    $(".bj").click(function () {
        var Tspan = $(this).parent().parent().children().find('.tabVal');
        var Tipnut = $(this).parent().parent().children().find('.tabInput');

        $(this).hide();
        $(this).next('.btnAll').show();
        Tspan.hide();
        Tipnut.show();

        $('.tabInput').bind('input propertychange', function () {
            var a = $(this);
            $(".bc").click(function () {
                a.prev().html(a.val());
            });
        });

        $(".bc").click(function () {

            $(this).parent(".btnAll").hide();
            $(this).parent(".btnAll").prev(".bj").show();
            var Tspan = $(this).parent().parent().parent().children().find('.tabVal');
            var Tipnut = $(this).parent().parent().parent().children().find('.tabInput');
            var name = "";
            var value = "";
            var comment = "";
            var oriName = "";
            Tipnut.each(function () {
                var objName = this.name;
                if (objName == "name") {
                    name = this.value;
                } else if (objName == "value") {
                    value = this.value;
                } else if (objName == "comment") {
                    comment = this.value;
                } else if (objName == "oriName") {
                    oriName = this.value;
                    this.value = name;
                }
            });

            var nodePath = $("#nodePath").val();
            var version = $("#version").val();
            var groupName = $("#groupName").val();
            $.post(content + "/zookeeper/property/update.do", {
                    "nodePath": nodePath, "version": version, "groupName": groupName,
                    "name": name, "value": value, "comment": comment, "oriName": oriName
                },
                function (data) {
                    if (data == "1") {
                    } else {
                        alert('更新属性失败');
                    }
                }
            );

            Tspan.show();
            Tipnut.hide();

        });

        $(".qx").click(function () {
            $(this).parent(".btnAll").hide();
            $(this).parent(".btnAll").prev(".bj").show();
            var Tspan = $(this).parent().parent().parent().children().find('.tabVal');
            var Tipnut = $(this).parent().parent().parent().children().find('.tabInput');
            Tspan.show();
            Tipnut.hide();
        });
    });
}
