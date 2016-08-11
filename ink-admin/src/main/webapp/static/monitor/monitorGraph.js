$(function () {
    var sysCode = new Array();
    var unSoveleCount = new Array();
    var soveleCount = new Array();
    var soveServiceCount = new Array();
    var unSoveServiceCount = new Array();
    var unSoveleInfo = new Array();
    var soveleInfo = new Array();
    var soveServiceInfo = new Array();
    var unSoveServiceInfo = new Array();
    var monitorContext = $("#monitorContext").val() + "/monitor";

    $.post(monitorContext + "/graph/getSysMsg.do", function (str) {
        //分别取得已解决数据，未解决数据
        $.each(str.soveleErro, function (i, data) {
            sysCode[i] = data.sysCode;
            soveleInfo[i] = data.errorDesc;
            soveleCount[i] = data.errorCount;

        });
        $.each(str.unSoveled, function (i, data) {
            unSoveleCount[i] = data.errorCount;
            if (!soveleInfo.length > 0) {
                soveleInfo[i] = data.errorDesc;
            }
        });
        var soveData = [];
        var unSoveData = [];
        $.each(str.soveleErro, function (i, data) {
            soveData.push({
                y: data.errorCount,
                color: Highcharts.getOptions().colors[i],
                //根据横轴坐标查询子集数据
                drilldown: {
                    name: '已解决',   //子集图表名称
                    categories: soveServiceInfo,  //每个子集的名称
                    data: soveServiceCount,      //其纵坐标的值
                    color: Highcharts.getOptions().colors[i],
                    code: data.sysCode,
                },
                code: data.sysCode,
            })
        });
        $.each(str.unSoveled, function (i, data) {
            unSoveData.push({
                y: data.errorCount,
                color: Highcharts.getOptions().colors[i + 2],
                //根据横轴坐标查询子集数据
                drilldown: {
                    name: '未解决',   //子集图表名称
                    categories: unSoveServiceInfo,  //每个子集的名称
                    data: unSoveServiceCount,      //其纵坐标的值
                    color: Highcharts.getOptions().colors[i + 2],
                    code: data.sysCode,
                },
                code: data.sysCode,
            })
        });

        var colors = Highcharts.getOptions().colors,
            categories = soveleInfo,   //横坐标的值:平台
            name = '系统本日监控图表',
            soveData = soveData,
            unSoveData = unSoveData;

        function setChart(name, categories, data, color) {
            chart.xAxis[0].setCategories(categories, false);
            chart.series[0].remove(false);
            //chart.series[0].remove(false);
            chart.addSeries({
                name: name,
                data: data,
                color: color || 'white'
            }, false);
            chart.redraw();   //重绘
        };
        function setChartR(name, categories, data, unSoveData, color) {
            chart.xAxis[0].setCategories(categories, false);
            chart.series[0].remove(false);
            chart.series[0].remove(false);
            chart.addSeries({
                    name: name,
                    data: data,
                    color: color || 'white'
                },
                {
                    name: name,
                    data: unSoveData,
                    color: color || 'white'
                }, false);
            chart.redraw();   //重绘
        };
        var chart = $('#Column-chart').highcharts({
            chart: {
                type: 'column',
            },
            title: {
                text: '服务监控日志记录列表'              //标题
            },
            /*  subtitle: {
             text: '服务监控列表.'        //子标题
             },  */
            xAxis: {
                categories: categories
            },
            yAxis: {
                title: {
                    text: "错误次数"  //纵坐标显示值
                }
            },
            credits: {
                enabled: false
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    cursor: 'pointer',    //鼠标指针变成手
                    point: {		//点击后发生事件
                        events: {
                            click: function (e) {
                                var xname = this.category;
                                var cc = this.drilldown;
                                var series = chart.series;
                                var code = this.code;
                                var aa = 'ff';
                                if (code != undefined) {
                                    $.ajaxSetup({async: false});
                                    $.post(monitorContext + "/graph/getSysSource.do", {sysCode: code}, function (rs) {
                                        soveServiceInfo.splice(0, soveServiceInfo.length);
                                        soveServiceCount.splice(0, soveServiceCount.length);
                                        unSoveServiceCount.splice(0, unSoveServiceCount.length);
                                        unSoveServiceInfo.splice(0, unSoveServiceInfo.length);
                                        $.each(rs, function (i, data) {

                                            if (data.status != '2') {
                                                soveServiceInfo.push(data.errorDesc);
                                                unSoveServiceInfo.push(data.errorDesc);
                                            }
                                            if (data.status == '1') {
                                                //已解决de
                                                soveServiceCount.push(data.errorCount);
                                                unSoveServiceCount.push(0);
                                            }
                                            if (data.status == '0') {

                                                soveServiceCount.push(0);
                                                unSoveServiceCount.push(data.errorCount);
                                            }
                                        });
                                    }, 'json')
                                }
                                console.info(soveServiceInfo)
                                $.each(categories, function (i, dd) {
                                    if (xname == dd) {
                                        if (cc) {
                                            if (soveData.length > 0) {
                                                var drilldown = soveData[i].drilldown;
                                                setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);

                                            }
                                            if (unSoveData.length > 0) {
                                                var drilldown1 = unSoveData[i].drilldown;
                                                setChart(drilldown1.name, drilldown1.categories, drilldown1.data, drilldown1.color);
                                            }
                                            aa = null;
                                        }
                                    }
                                });
                                if (aa != null) { // restore
                                    history.go(0)
                                    //location.reload() ;
                                    // setChart(name, categories, soveData);
                                    // setChart(name, categories, unSoveDat);
                                    // setChartR(name, categories, soveData, unSoveData);
                                    // setChartR(name, categories, unSoveDat);
                                }
                            }
                        }
                    },
                    dataLabels: {
                        enabled: true,   //启用或禁用数据标签。---是否在柱状图内显示纵坐标的值
                        color: colors[0],
                        style: {
                            fontWeight: 'bold'
                        },
                        formatter: function () {    //回调JavaScript函数格式数据标签
                            return this.y;
                            /* if(this.y=0){
                             return null;
                             }else{
                             return this.y;
                             } */

                        }
                    }
                }
            }
            , legend: {
                align: 'right',
                x: -70,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: { //鼠标移到图形上时显示的提示框
                formatter: function () {

                    var point = this.point,
                        // s = this.x +':<b>'+ this.y +'% market share</b><br/>';
                        s = this.series.name + '报错数:<b>'
                            + this.y
                            + '</b><br/>';
                    if (point.drilldown) {
                        s += '点击 '
                            + point.category
                            + ':查看服务数据';
                    } else {
                        s += '(当天时间段报错数据)';
                    }
                    return s;
                }
            },
            series: [{
                name: '已解决',
                data: soveData,
                color: 'green'
            },
                {
                    name: '未解决',
                    data: unSoveData,
                    color: 'red'
                }],
            exporting: {
                enabled: false
            }
        }).highcharts(); // return chart
    }, 'json');
});