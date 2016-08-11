$(function () {
	//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
    function banBackSpace(e){   
        var ev = e || window.event;//获取event对象   
        var obj = ev.target || ev.srcElement;//获取事件源   
        var t = obj.type || obj.getAttribute('type');//获取事件源类型  
        //获取作为判断条件的事件类型
        var vReadOnly = obj.getAttribute('readonly');
        //处理null值情况
        vReadOnly = (vReadOnly == "") ? false : vReadOnly;
        //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
        //并且readonly属性为true或enabled属性为false的，则退格键失效
        var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
                    && vReadOnly=="readonly")?true:false;
        //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
        var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
                    ?true:false;        
        
        //判断
        if(flag2){
            return false;
        }
        if(flag1){   
            return false;   
        }   
    }

window.onload=function(){
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress=banBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown=banBackSpace;
}
    //注销
    $('.out_admin').on('click', function () {
        var url = 'login.php?action=out_login';
        $.get(url, function (data) {
            if (data == 1) {
                location.href = "login.php";
            } else {
                alert(data);
            }
        });
    });

    //tree 错误信息展示
    $('#menu_tree').tree({
        onLoadSuccess: function (node, data) {
            var vc = $('#menu_tree');
            vc.children('div.tree-empty').remove();
            if (data.length == 0) {
                var err = '没有菜单权限！',
                    d = $('<div class="tree-empty"></div>').html(err).appendTo(vc);
                d.css({
                    position: 'absolute',
                    left: 0,
                    top: 50,
                    width: '100%',
                    textAlign: 'center'
                });
            } else {
                vc.children('div.tree-empty').remove();
            }
        }
    });

   /* //按回车，搜索
    $(document).delegate('body', 'keyup', function (e) {
        if (e.keyCode == 13) {
            var activeIfr = $('#tt').tabs('getSelected').find('iframe')[0];
            if (activeIfr) {
                $(activeIfr.contentWindow.document.getElementById('btnQuery')).trigger('click');
            }
        }
    });*/

});

//左侧菜单，子节点点击，打开新tab
function menuClickHandler(node) {
    if (node.url) {
        open1(node.text, node.url);
    } else {
        $('#menu_tree').tree('toggle', node.target);
    }
}

(function($) {
    $.extend({
        myTime: {
            /**
             * 当前时间戳
             * @return <int>        unix时间戳(秒)  
             */
            CurTime: function(){
                return Date.parse(new Date())/1000;
            },
            /**              
             * 日期 转换为 Unix时间戳
             * @param <string> 2014-01-01 20:20:20  日期格式              
             * @return <int>        unix时间戳(秒)              
             */
            DateToUnix: function(string) {
                var f = string.split(' ', 2);
                var d = (f[0] ? f[0] : '').split('-', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(
                        parseInt(d[0], 10) || null,
                        (parseInt(d[1], 10) || 1) - 1,
                        parseInt(d[2], 10) || null,
                        parseInt(t[0], 10) || null,
                        parseInt(t[1], 10) || null,
                        parseInt(t[2], 10) || null
                        )).getTime() / 1000;
            },
            /**              
             * 时间戳转换日期              
             * @param <int> unixTime    待时间戳(秒)              
             * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)              
             * @param <int>  timeZone   时区              
             */
            UnixToDate: function(unixTime, isFull, timeZone) {
            	if (isNaN(unixTime)){
            		return '';
            	}
                if (typeof (timeZone) == 'number')
                {
                    unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
                }
                var time = new Date(unixTime);
                var ymdhis = "";
                ymdhis += time.getFullYear() + "-";
                ymdhis += (time.getMonth()+1) + "-";
                ymdhis += time.getDate();
                if (isFull === true)
                {
                    ymdhis += " " + time.getHours() + ":";
                    ymdhis += time.getMinutes() + ":";
                    ymdhis += time.getSeconds();
                }
                return ymdhis;
            }
        }
    });
})(jQuery);

//tab子页面中新增打开tab
function openTab(title,plugin){
    if (window.parent.$('#tt').tabs('exists', title)) {
         window.parent.$('#tt').tabs('select', title);
         var tab = window.parent.$('#tt').tabs('getSelected');
         window.parent.$('#tt').tabs('update', {
             tab: tab,
             options: {
                 content: '<iframe frameborder="0" src="' + plugin + '"></iframe>'
             }
         });
     } else {
         window.parent.$('#tt').tabs('add', {
             title: title,
             closable: true,
             bodyCls: 'p5',
             content: '<iframe frameborder="0" src="' + plugin + '"></iframe>'
         });
     }
  }


//表字典映射
function dictDataItem(url,params,property){
	var name;
	$.ajax({url:url,
		data: params,
		async:false,
		success:function(redata){
		name=redata[property];
            }});
	return name;
}
//数据字典表映射
function dictItemConvert(url,params,value){
	param={dictCode:params};
	$.ajax({url:url,
		data:param,
		cache:false,
		async:false,
		success:function(redata){
			for(var p in redata){
				if(redata[p].itemCode==value){
					name= redata[p].itemName;
				}
			}
            }});
	return name;
}

//表单格式化插件
$.fn.serializeObject = function()  
{  
   var o = {};  
   var a = this.serializeArray();  
   $.each(a, function() {  
       if (o[this.name]) {  
           if (!o[this.name].push) {  
               o[this.name] = [o[this.name]];  
           }  
           o[this.name].push(this.value || '');  
       } else {  
           o[this.name] = this.value || '';  
       }  
   });  
   return o;  
};  
//角色状态格式化
function convertRoleStatus(value){
	if(value=='1'){
		return "启用";
	}
	else if(value=='2'){
		return "停用";
	}
	else if(value=='9'){
		return "注销";
	}
}
//操作类型格式化
function convertAction(value){
		 if(value=='0'){
			return "用户";
	}
	else if(value=='1'){
		return "角色";
	}
	else if(value=='2'){
		return "资源";
	}
	else if(value=='3'){
		return "平台";
	}
	else if(value=='4'){
		return "操作";
	}	 
	else if(value=='5'){
		return "用户&角色关联";
	}
	else if(value=='6'){
		return "角色&资源关联";
	}
}
//操作结果格式化
function convertResult(value){
	if(value=='1'){
		return "成功";
	}
	else if(value=='0'){
		return "失败";
	}	
}
//z职位，主岗，副岗转换
function convertTitle(value){
	if(value=='1'){
		return "主岗";
	}
	else if(value=='2'){
		return "副岗";
	}
}
function convertSubjectType(value){
	if(value=='1'){
		return "机构";
	}
	else if(value=='2'){
		return "用户";
	}
}
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.dateFormat = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//时间格式化处理方法
function formatDate(date, format) {   
    if (!date) return;   
    if (!format) format = "yyyy-MM-dd";   
    switch(typeof date) {   
        case "string":   
            date = new Date(date.replace(/-/, "/"));   
            break;   
        case "number":   
            date = new Date(date);   
            break;   
    }    
    if (!date instanceof Date) return;   
    var dict = {   
        "yyyy": date.getFullYear(),   
        "M": date.getMonth() + 1,   
        "d": date.getDate(),   
        "H": date.getHours(),   
        "m": date.getMinutes(),   
        "s": date.getSeconds(),   
        "MM": ("" + (date.getMonth() + 101)).substr(1),   
        "dd": ("" + (date.getDate() + 100)).substr(1),   
        "HH": ("" + (date.getHours() + 100)).substr(1),   
        "mm": ("" + (date.getMinutes() + 100)).substr(1),   
        "ss": ("" + (date.getSeconds() + 100)).substr(1)   
    };       
    return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function() {   
        return dict[arguments[0]];   
    });                   
}   
  