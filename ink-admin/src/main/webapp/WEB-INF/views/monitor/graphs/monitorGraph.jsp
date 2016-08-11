<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Xenon Boostrap Admin Panel"/>
    <meta name="author" content=""/>

    <title>服务监控图表</title>

    <%@ include file="/gen-commons/taglibs.jsp" %>
    <link rel="stylesheet"
          href="${yk}/static/highcharts/css/font-awesome.min.css">
    <link rel="stylesheet" href="${yk}/static/highcharts/css/bootstrap.css">
    <link rel="stylesheet" href="${yk}/static/highcharts/css/xenon-core.css">
    <link rel="stylesheet" type="text/css"  href="${yk}/static/highcharts/css/index.css"/>
    <script src="${yk}/static/highcharts/js/jquery-1.11.1.min.js"></script>
    <!-- Bottom Scripts -->
    <script src="${yk}/static/highcharts/js/bootstrap.min.js"></script>
    <script src="${yk}/static/highcharts/js/resizeable.js"></script>
    <!-- Imported scripts on this page -->
    <!--折现图表-->
    <script type="text/javascript" src="${yk}/static/highcharts/js/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="${yk}/static/highcharts/js/highcharts/highcharts-more.js"></script>
    <!-- JavaScripts initializations and stuff -->
    <script src="${yk}/static/highcharts/js/xenon-custom.js"></script>
    <script src="${yk}/static/monitor/monitorGraph.js"></script>
</head>
<body class="page-body">
<input type="hidden" id="monitorContext" value="${yk}" />
<div class="page-container" id="MainCont">
    <div class="main-content">
        <div class="panel panel-default">
            <div class="panel-heading">服务状态监测图表</div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="Column-chart"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

