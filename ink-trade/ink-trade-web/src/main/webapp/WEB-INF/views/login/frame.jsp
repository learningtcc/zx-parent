<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/gen-commons/taglibs.jsp" %>
<script type="text/javascript" src="${yk}/gen-commons/dateUtils.js"></script>
<title>银客交易后台管理</title>
<script type="text/javascript">
function quit(){
	window.location = "${yk}/login/loginOut.do";
}
	function updatePassword(){
		window.open('${yk}/user/editPassword.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	}
</script>
</head>

<body style="background-color: #2281B3;">
<div class="header">
	<div class="logo">
		<img alt="" height="74" longdesc="银客监控报警系统" src="${yk}/gen-commons/images/common/logo_img3.png" width="156" /></div>
	<div class="userarea">
		<div>
			${sessionScope.SESSION_FULLNAME} <span id="hourZone"></span>欢迎你！</div>
		<div class="gggn">
			<!-- <a class="password" href="#" onclick="updatePassword();"><em></em>修改密码</a> -->
			<form method="post" id="form1" name="form1" action="https://sso.yinker.com:9443/sso/logout">
			    <input type="hidden" name="service" id="service" value="${loginOutService}"/>
			    <input type="submit" id="submit" value="退出" />
			</form>
			<!-- <a class="quit" href="#" onclick="quit();"><em></em>安全退出</a> -->
		</div>
	</div>
</div>
<div id="mainbody">
	<div id="leftmenu">
		<div class="panel-title">
			<span class="layout-button-hidden"></span><span id="dh">导航菜单</span>
		</div>
		<div id="switchBar"><img src="${yk}/gen-commons/images/common/layout-button-show.gif" alt="展开" height="17" width="19" /></div>
		<div class="panel-body"><iframe id="frame_menu" frameborder="0" scrolling="yes" src="${yk}/common/location.do?path=/login/left" target="_blank"></iframe></div>
	</div>
	<div id="rightContainer">
		<div class="tab-wrap" style="height:30px;overflow: hidden;">
			<ul class="tab-list clearfix" id="toptabs">
				<li class="tabs-selected" ><span id=yeqian_content onclick='javascript:iframehide("content");'>欢迎页</span></li>
			</ul>
		</div>
		<div id="iframCreater">
			<iframe id="frame_content" frameborder="0" scrolling="auto" src="${yk}/common/location.do?path=/login/main" target="_blank"></iframe>
		</div>
	</div>
</div>
<div class="footer">
	<div class="version">
		银客交易后台管理V1.0版本</div>
	<div class="date">
		当前日期：<span id="currDateShow"></span></div>
	<div class="brower">
		（推荐浏览配置：IE7.0+ 1280x800）</div>
</div>
</body>
</html>
<script type="text/javascript">

	//重置窗口内容
	var iframe_h = 1024;
	var iframe_w = 800;
	function resetContent(){
		setWindowSize();
		if(iframe_h>0 && iframe_w>0){
			document.getElementById('frame_menu').style.height = (iframe_h-145 )+ "px";
			document.getElementById('frame_content').style.height = (iframe_h-150 )+ "px";
			document.getElementById('frame_content').style.width = (iframe_w*1-203)+ "px";
		}else{
		 	window.setInterval("resetContent()", 203);
		}
		//窗口大小变化时，重新设置mainframe宽度
		var iw = 0;
		if($("#switchBar").css('visibility')!='hidden') iw = 203;
		else iwh = 40;
		resetMainFrameWidth(150,iw);
	}

	//窗口尺寸
	function setWindowSize(){
		var window_h=document.documentElement.clientHeight;
		var window_w=document.documentElement.clientWidth;
		if(window_h==0 || window_w==0){
			window_h=document.body.clientHeight;
			window_w=document.body.clientWidth;
		}
		iframe_h = window_h;
		iframe_w = window_w;
	}

	window.onload=resetContent;
	window.onresize=resetContent;

	//点击左侧菜单中的树节点会调用此方法 
	function treeClick(treeId, treeNode){
		var v = $("*").filter("li");  //原先按.tabs-selected获取
		for(var i =0;i<v.length;i++){
			var n = v[i];
			if($(n).children("span").attr("id")=="yeqian_"+treeNode.id){//存在此页签时则显示当前点击的页签内容，隐藏其他
				iframehide(treeNode.id);
				break;
			}else if(i ==v.length-1 && $(n).children("span").attr("id")!="yeqian_"+treeNode.id){//创建页签和iframe 并显示当前内容隐藏其他
				$("#toptabs").append("<li class='tabs-selected'><span id=yeqian_"+treeNode.id+" onclick='javascript:iframehide(\""+treeNode.id+"\");'>"+treeNode.name+"</span><a class='close' href='javascript:closeWindow(\""+treeNode.id+"\");'><img alt='close' height='11' src='${yk}/gen-commons/images/common/tab_close.png' width='11' /></a></li>");
				iframeadd(treeNode.srcurl,treeNode.id);
			}
		}
	}
	//创建页签所对应的iframe 并进行 高度等属性的设置 
	function iframeadd (src,id){
		$("#iframCreater").append("<iframe id='frame_"+id+"' frameborder=0 scrolling='auto' src='"+src+"' target=_blank></iframe>");
		resetNewContent('frame_'+id);
		iframehide(id);
	}
	//手动创建tab页
	function iframehandadd(name,src,id){
		var v = $("*").filter("li");  //原先按.tabs-selected获取
		for(var i =0;i<v.length;i++){
			var n = v[i];
			if($(n).children("span").attr("id")=="yeqian_"+id){//存在此页签时则显示当前点击的页签内容，隐藏其他
				iframehide(id);
				break;
			}else if(i ==v.length-1 && $(n).children("span").attr("id")!="yeqian_"+id){//创建页签和iframe 并显示当前内容隐藏其他
				$("#toptabs").append("<li class='tabs-selected'><span id=yeqian_"+id+" onclick='javascript:iframehide(\""+id+"\");'>"+name+"</span><a class='close' href='javascript:closeWindow(\""+id+"\");'><img alt='close' height='11' src='${yk}/gen-commons/images/common/tab_close.png' width='11' /></a></li>");
				iframeadd(src,id);
			}
		}
	}

	//根据 ID 显示此iframe 并隐藏其他 的Iframe
	function iframehide(id){
		var v = "frame_"+id;
		// 隐藏时间控件所在的div。
		$("body>div[lang]").hide();
		// 只对id以frame_开头的iframe进行处理。
		var f = $("iframe[id *= frame_][id!= frame_menu]");
		for(var i =0;i<f.length;i++){
			if(f[i].id==v){
				$(f[i]).show();
			}else{
				$(f[i]).hide();
			}
		}

		//遍历列表，对非当前选中页签置做焦点设置
		var p = $("*").filter("li");
		for(var i =0;i<p.length;i++){
			var n = p[i];
			if($(n).children("span").attr("id")=="yeqian_"+id){//存在此页签时则激活页签，隐藏其他
				$(n).removeClass("un_tabs-selected");
				$(n).addClass("tabs-selected");
				//$(n).toggleClass("tabs-selected");
			}else{
				$(n).removeClass("tabs-selected");
				$(n).addClass("un_tabs-selected");
			}
		}
	}

	//根据 ID 关闭页签 并 关闭对应的iframe的方法
	function closeWindow(id){
		var remiframe = "frame_"+id;
		$("#"+remiframe).remove();

		var v = $("*").filter("li"); //原先按.tabs-selected获取
		for(var i =0;i<v.length;i++){
			n = v[i];
			if($(n).children("span").attr("id")=="yeqian_"+id){
				$(n).remove();
				break;
			}
		}

		//删除到最后一个则显示欢迎页，其他的则显示最后一个页面信息
		if($("*").filter("li").length==1){  //$("*").filter(".tabs-selected").length==1
			iframehide("content");
		}else{
			//var l = $("*").filter(".tabs-selected").last();
			var l = $("*").filter("li").last();
			iframehide($(l).children("span").attr("id").split("_")[1]);
		}
	}

	//设置新设置的iframe的高度等属性
	function resetNewContent(id){
		setWindowSize();
		if(iframe_h>0 && iframe_w>0){
			document.getElementById(id).style.height = (iframe_h-150 )+ "px";
			document.getElementById(id).style.width = (iframe_w*1-203)+ "px";
		}else{
		 	window.setInterval("resetNewContent(id)", 203);
		}
	}

	//初始化时隐藏展开图标
	$(document).ready(function(){
		$("#switchBar").hide();
		$("#currDateShow").text(getCurDateStr());
		$("#hourZone").text(getCurHourZone());
	});
	//缩回图标可见时操作
	if($(".layout-button-hidden").show()){
		setWindowSize();
		$(".layout-button-hidden").click(function(){
			$(".panel-title").hide();
			$(".panel-body").hide();
			$("#leftmenu").width(15);
			$("#switchBar").show();
			$('#switchBar').height(iframe_h-125);
			$('#frame_content').width(iframe_w*1-40);
			resetMainFrameWidth(150,40);
		});
	}
	//展开图标可见时的操作
	if($("#switchBar").show()){
		setWindowSize();
		$("#switchBar").click(function(){
			$("#switchBar").hide();
			$("#leftmenu").width(175);
			$(".panel-title").show();
			$(".panel-body").show();
			$('#frame_content').width(iframe_w*1-203);
			resetMainFrameWidth(150,203);
		});
	}

	function resetMainFrameWidth(ih,iw){
		setWindowSize();
		var fi = $("#iframCreater iframe");
		for(var i =0;i<fi.length;i++){
			var nid = fi[i].id;
			if(nid != 'frame_menum' && nid != 'frame_content'){
				if(document.getElementById(nid)!=null){
					document.getElementById(nid).style.height = (iframe_h*1-ih )+ "px";
					document.getElementById(nid).style.width = (iframe_w*1-iw)+ "px";
				}
			}
		}
	}
	
  </script>
