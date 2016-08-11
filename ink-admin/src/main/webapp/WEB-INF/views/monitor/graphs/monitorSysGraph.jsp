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

    <title>系统监控图表</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
    <link rel="stylesheet" href="${yk}/static/highcharts/css/linecons.css">
    <link rel="stylesheet" href="${yk}/static/highcharts/css/font-awesome.min.css">
    <link rel="stylesheet" href="${yk}/static/highcharts/css/bootstrap.css">
    <link rel="stylesheet" href="${yk}/static/highcharts/css/xenon-core.css">
    <link rel="stylesheet" type="text/css" href="${yk}/static/highcharts/css/index.css"/>
    <script src="${yk}/static/highcharts/js/jquery-1.11.1.min.js"></script>
    <!-- Bottom Scripts -->
    <script src="${yk}/static/highcharts/js/bootstrap.min.js"></script>
    <script src="${yk}/static/highcharts/js/TweenMax.min.js"></script>
    <script src="${yk}/static/highcharts/js/resizeable.js"></script>
    <script src="${yk}/static/highcharts/js/joinable.js"></script>
    <script src="${yk}/static/highcharts/js/xenon-api.js"></script>
    <script src="${yk}/static/highcharts/js/xenon-toggles.js"></script>
    <!-- Imported scripts on this page -->
    <script src="${yk}/static/highcharts/js/xenon-widgets.js"></script>
    <script src="${yk}/static/highcharts/js/globalize.min.js"></script>
    <script src="${yk}/static/highcharts/js/dx.chartjs.js"></script>
    <!-- JavaScripts initializations and stuff -->
    <script src="${yk}/static/highcharts/js/xenon-custom.js"></script>
    <%--<!--折现图表-->--%>
    <script src="${yk}/static/highcharts/js/highcharts/highcharts.js"></script>
    <script src="${yk}/static/highcharts/js/highcharts/highcharts-more.js"></script>
    <script src="${yk}/static/monitor/monitorSysGraph.js"></script>
</head>
    <body class="page-body">
    <input type="hidden" id="monitorContext" value="${yk}" />
    <input type="hidden" id="sysCode" value="${sysCode}" />
        <div class="page-container" id="MainCont">
            <div class="main-content">
                <!-- User Info, Notifications and Menu Bar -->
                <div class="panel panel-default">
                    <div class="panel-heading">每分钟的错误变化曲线</div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <div id="range-chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">5分钟的错误次数</div>
                    <div class="panel-body">

                        <div class="row">
                            <div class="col-sm-6 col-xs-12">
                                <div class="super-large text-info" id="fiveMinuteShow" >20</div>
                            </div>
                            <div class="col-sm-6 col-xs-12">
                                <div id="fiveMinute" style="height: 150px;"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">一个小时的错误次数</div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="super-large text-purple" id="oneHourShow" >
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div id="oneHour" style="height: 150px;"></div>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">一天的错误次数</div>
                    <div class="panel-body">

                        <div class="row">
                            <div class="col-sm-6 ">
                                <div class="super-large text-secondary" id="oneDayShow">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div id="oneDay" style="height: 150px;"></div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="page-loading-overlay">
            <div class="loader-2"></div>
        </div>
    </body>
</html>

