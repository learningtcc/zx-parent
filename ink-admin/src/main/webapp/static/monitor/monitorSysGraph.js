$(function () {
    var monitorContext = $("#monitorContext").val() + "/monitor";
    var sysCode = $("#sysCode").val();
    var gaugesPalette = ['#8dc63f', '#40bbea', '#ffba00', '#cc3f44'];
    // Memory Usage五分钟交易数据
    $("#fiveMinute").dxCircularGauge({
        scale: {
            startValue: 0,
            endValue: 100,
            majorTick: {
                tickInterval: 25 //把100分成4份每份25
            }
        },
        rangeContainer: {
            palette: 'pastel',
            width: 3,
            ranges: [{
                startValue: 0,
                endValue: 250,
                color: '#40bbea'
            }, {
                startValue: 250,
                endValue: 500,
                color: '#40bbea'
            }, {
                startValue: 500,
                endValue: 750,
                color: '#40bbea'
            }, {
                startValue: 750,
                endValue: 1000,
                color: '#40bbea'
            }]
        },
        value: 0,
        valueIndicator: {
            offset: 10,
            color: '#2c2e2f',
            spindleSize: 12
        }
    });

    // CPU Usage一小时
    $('#oneHour').dxCircularGauge({
        scale: {
            startValue: 0,
            endValue: 200,
            majorTick: {
                tickInterval: 25
            }
        },
        rangeContainer: {
            palette: 'pastel',
            width: 3,
            ranges: [{
                startValue: 0,
                endValue: 25,
                color: gaugesPalette[0]
            }, {
                startValue: 25,
                endValue: 50,
                color: gaugesPalette[1]
            }, {
                startValue: 50,
                endValue: 75,
                color: gaugesPalette[2]
            }, {
                startValue: 75,
                endValue: 100,
                color: gaugesPalette[3]
            }]
        },
        value: 0,
        valueIndicator: {
            offset: 10,
            color: '#2c2e2f',
            spindleSize: 12
        }
    });

    // Requests per second gauge一天
    $('#oneDay').dxCircularGauge({
        scale: {
            startValue: 0,
            endValue: 1000,
            majorTick: {
                tickInterval: 50
            }
        },
        rangeContainer: {
            palette: 'pastel',
            width: 3,
            ranges: [{
                startValue: 0,
                endValue: 50,
                color: gaugesPalette[0]
            }, {
                startValue: 50,
                endValue: 100,
                color: gaugesPalette[1]
            }, {
                startValue: 100,
                endValue: 150,
                color: gaugesPalette[2]
            }, {
                startValue: 150,
                endValue: 200,
                color: gaugesPalette[3]
            }]
        },
        value: 0,
        valueIndicator: {
            offset: 10,
            color: '#2c2e2f',
            spindleSize: 12
        }
    });

    var minuteUrl = monitorContext + "/graph/getMinuteCount.do?sysCode=" + sysCode;
    var hourUrl = monitorContext + "/graph/getHourCount.do?sysCode=" + sysCode;
    var dayUrl = monitorContext + "/graph/getDayCount.do?sysCode=" + sysCode;

    nowLoadFunction("fiveMinute","fiveMinuteShow",minuteUrl);
    nowLoadFunction("oneHour","oneHourShow",hourUrl);
    nowLoadFunction("oneDay","oneDayShow",dayUrl);

    timerLoadFunction("fiveMinute","fiveMinuteShow",60000,minuteUrl);
    timerLoadFunction("oneHour","oneHourShow",300000,hourUrl);
    timerLoadFunction("oneDay","oneDayShow",600000,dayUrl);
    //每分钟的错误变化曲线
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    var sysWaveGrapUrl =  monitorContext + "/graph/sysWaveGrap.do?sysCode=" + sysCode;
    var chart;
    $('#range-chart').highcharts({
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    // set up the updating of the chart each second
                    var series = this.series[0];
                    setInterval(
                        function () {
                            var trade = Number($.ajax({
                                url: sysWaveGrapUrl,
                                async: false
                            }).responseText);
                            var x = (new Date()).getTime(), // current time
                                y = trade;
                            series.addPoint([x, y], true, true);
                        },
                        1000);
                }
            }
        },
        title: {
            text: '报错波动图'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 300, //多少秒更新横坐标的
        },
        yAxis: {
            title: {
                text: 'Value'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function () {
                return '<b>'
                    + this.series.name
                    + '</b><br/>'
                    + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x)
                    + '<br/>'
                    + Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '报错波动数',
            data: (function () {
                // generate an array of random data
                var trade = Number($.ajax({
                    url:sysWaveGrapUrl,
                    async: false
                }).responseText);
                var data = [],
                    time = (new Date()).getTime(),
                    i;
                for (i = -10; i <= 0; i++) {
                    data.push({
                        x: time + i * 1000,
                        y: trade
                    });
                }
                return data;
            })()
        }]
    });
});

function nowLoadFunction(chartId,showId,url) {
    var count = $.ajax({
        url: url,
        async: false
    }).responseText;

    $("#" + showId).text(count);
    var gauge = $("#" + chartId).dxCircularGauge('instance');
    gauge.value(count);
}

function timerLoadFunction(chartId,showId,timer,url) {
    setInterval(
        function () {
            // var value = Math.round((Math.random()) * 20);
            // $("#" + showId).text(value);
            // var gauge = $("#" + chartId).dxCircularGauge('instance');
            // gauge.value(value);
            var count = $.ajax({
                url: url,
                async: false
            }).responseText;

            $("#" + showId).text(count);
            var gauge = $("#" + chartId).dxCircularGauge('instance');
            gauge.value(count);
        },timer);
}