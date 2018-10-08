// JS 代码
Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

/**
 * 刷新最新点到chart的末尾处 tooltip
 * @param chart
 */
function activeLastPointToolip(chart) {
    var points = chart.series[0].points;
    chart.tooltip.refresh(points[points.length - 1]);
}

//var chart1 = getChart("container");
/**
 * 曲线提示框
 * @type {{valueSuffix: string, split: boolean, distance: number, padding: number, style: {fontSize: string, fontFamily: string, fontWeight: string}, dateTimeLabelFormats: {hour: string}}}
 */
var curveToolTip = {
    valueSuffix: '',
    split: true,
    distance: 30,
    padding: 12,
    style: {
        fontSize: "16px",
        fontFamily: "微软雅黑",
        fontWeight: "light"
    },
    dateTimeLabelFormats: {
        hour: '%Y-%m-%d %H:%M'
    }
};
function getCurveChartOptions(titleName,subtitle){
    var chartOptions = {
        chart: {
            type: 'spline',
            marginRight: 10/*,
            events: getLoadFunction()*/
        },
        title: {text: titleName},
        subtitle: {text: subtitle},
        plotOptions: {
            series: {
                marker: {
                    radius: 0,  //曲线点半径，默认是4，设为0时隐藏点
                    symbol: 'diamond' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                }
            }
        },
        xAxis: {type: 'datetime', tickPixelInterval: 150},
        yAxis: {title: {text: ""}},
        tooltip: curveToolTip,
        legend: {enabled: true}
    };
    return chartOptions;
}

function getCurveChartWithOptionsAndSeries(divId, titleName, subtitle,chartOption,series) {
    var chartOptions = getCurveChartOptions(titleName,subtitle);
    $.extend(chartOptions,{series: series});
    $.extend(chartOptions,chartOption);
    return Highcharts.chart(divId,chartOptions);
}
function getCurveChartWithOptionsAndSerieNames(divId, titleName, subtitle,chartOption,seriesNames) {
    var series = [];
    for(var i=0;i<seriesNames.length;i++){
        var obj = {name:seriesNames[i],data:[]};
        series.push(obj);
    }
    var chart = getCurveChartWithOptionsAndSeries(divId, titleName, subtitle,chartOption,series);
    return chart;
}
function getCurveChartWithOptions(divId, titleName, subtitle,chartOption) {
    var series = getInitSeriesArray();
    var chart = getCurveChartWithOptionsAndSeries(divId, titleName, subtitle,chartOption,series);
    return chart;
}
function getCurveChart(divId, titleName, subtitle) {
    var chartOptions = getCurveChartOptions(titleName,subtitle);
    $.extend(chartOptions,{series: getInitSeriesArray()});
    var chartForCurve = Highcharts.chart(divId, chartOptions);
    //TODO 初始化chart的数据图,url需要指定
    var getInitDataUrl = "getInitTimeData";
    initCurveChart(getInitDataUrl,chartForCurve);
    return chartForCurve;
}

/**
 * 将曲线的数据，根据URL读取数据，填充曲线数据
 * @param divId
 * @param titleName
 * @param subtitle
 * @param url
 * @returns {Highcharts.Chart}
 */
function getCurveChartByUrl(divId,titleName,subtitle,url){
    var chartOptions = getCurveChartOptions(titleName,subtitle);
    $.extend(chartOptions,{series: getInitSeriesArray()});
    var chartForCurve = Highcharts.chart(divId, chartOptions);
    //TODO 初始化chart的数据图,url需要指定
    // var getInitDataUrl = "getInitTimeData";
    initCurveChart(url,chartForCurve);
    return chartForCurve;
}
/**
 * 初始化chart的数据图
 * @param url
 * @param chartForCurve
 */
function initCurveChart(url,chartForCurve){
    $.getJSON(url, function (initData) {
        var newData = getSeriesArray(initData);
        chartForCurve.series[0].setData(newData[0].data);
        chartForCurve.series[1].setData(newData[1].data);
        chartForCurve.series[2].setData(newData[2].data);
        chartForCurve.series[3].setData(newData[3].data);

        // chartForCurve.chart.events =getLoadFunction();
        var series = chartForCurve.series;
        //   chart = this;
        // activeLastPointToolip(chart);
        var seriesData = series[0].data;
        var latestTime = seriesData[seriesData.length - 1].x;
        //  console.log(latestTime)

        //定时刷新数据
        /*setInterval(function () {
            $.get("/wind5/getRealTimeData", {"latestTime": latestTime}, function (result) {
                var time = result.time;
                var valueData = result.data;
                series[0].addPoint([time, valueData[0]], true, true);
                series[1].addPoint([time, valueData[1]], true, true);
                series[2].addPoint([time, valueData[2]], true, true);
                series[3].addPoint([time, valueData[3]], true, true);
            }, "json")
        }, 5000);*/
    });
}
/**
 * 刷新曲线数据
 * @param chart
 * @param time
 * @param curveData
 */
function freshCurveChart(chart,time,curveData){
    var series = chart.series;
    series[0].addPoint([time,curveData.pipe1Data],true,true);
    series[1].addPoint([time,curveData.pipe2Data],true,true);
    series[2].addPoint([time,curveData.pipe3Data],true,true);
    series[3].addPoint([time,curveData.pipe4Data],true,true);
}
function freshCurveChartForSingleLine(chart,time,data){
    var series = chart.series;
    series[0].addPoint([time,data],true,true);
}

function getInitSeriesArray() {
    var AData = [], BData = [], CData = [], DData = [];
    var seriesArray = [{
        name: 'A',
        data: AData
    }, {
        name: 'B',
        data: BData
    }, {
        name: 'C',
        data: CData
    }, {
        name: 'D',
        data: DData
    }];
    return seriesArray;
}

function getCurveChart2(divId, titleName, subtitle) {
    var chartForCurve = null;
    var getInitDataUrl = "/wind5/getInitTimeData";
    $.getJSON(getInitDataUrl, function (initData) {
        chartForCurve = Highcharts.chart(divId, {
            chart: {
                type: 'spline',
                marginRight: 10,
                events: getLoadFunction()
            },
            title: {
                text: titleName
            },
            subtitle: {
                text: subtitle
            },
            plotOptions: {
                series: {
                    marker: {
                        radius: 0,  //曲线点半径，默认是4，设为0时隐藏点
                        symbol: 'diamond' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                    }
                }
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: ""
                }
            },
            tooltip: curveToolTip,
            legend: {
                enabled: true
            },
            series: getSeriesArray(initData)
        });
    });
    return chartForCurve;
}


function getChart(divId, titleName, subtitle) {
    var chart = Highcharts.chart(divId, {
        chart: {
            type: 'spline',
            marginRight: 10,
            events: getLoadFunction()
        },
        title: {
            text: titleName
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: ""
            }
        },
        tooltip: {
            /*formatter: function () {
                console.log(this)
                var returnStr = Highcharts.dateFormat(' %H:%M:%S', this.x) + '<br/>' +
                    '<b>' + this.series.name + ' : ' + Highcharts.numberFormat(this.y, 2) + "</b><br/>";
                return returnStr;
            }*/
            valueSuffix: '',
            split: true,
            distance: 30,
            padding: 12,
            style: {
                fontSize: "16px",
                fontFamily: "微软雅黑",
                fontWeight: "light"
            },
            dateTimeLabelFormats: {
                hour: '%Y-%m-%d %H:%M'
            }
        },
        legend: {
            enabled: true
        },
        series: getSeriesArray()
    });
    return chart;
}

/**
 * 曲线加载完成后，调用event.load方法进行5秒一次的刷新
 * @returns {{load: load}}
 */
function getLoadFunction() {
    var loadObj = {
        load: function () {
            var series = this.series;
            //   chart = this;
            // activeLastPointToolip(chart);
            var seriesData = series[0].data;
            var latestTime = seriesData[seriesData.length - 1].x;
            //  console.log(latestTime)
            setInterval(function () {
                //TODO 获取实时数据是的URL地址问题，需要解决。
                $.get("/wind5/getRealTimeData", {"latestTime": latestTime}, function (result) {
                    var time = result.time;
                    var valueData = result.data;
                    series[0].addPoint([time, valueData[0]], true, true);
                    series[1].addPoint([time, valueData[1]], true, true);
                    series[2].addPoint([time, valueData[2]], true, true);
                    series[3].addPoint([time, valueData[3]], true, true);

                    // var x = (new Date()).getTime(), // 当前时间
                    //     y = result.density;          // 随机值
                    // series.addPoint([x, y], true, true);
                    // activeLastPointToolip(chart);
                }, "json")
            }, 50000);
        }
    }
    return loadObj;
}

function freshChartFunction(chart) {
    // console.log(chart);
    var series = chart.series[0];
    // chart = this;
    activeLastPointToolip(chart);
    setInterval(function () {
        // var x = (new Date()).getTime(), // 当前时间
        //     y = Math.random();          // 随机值
        // series.addPoint([x, y], true, true);

        $.get("getRealTimeData", function (result) {
            // alert(result);
            var x = (new Date()).getTime(), // 当前时间
                y = result.density;          // 随机值
            series.addPoint([x, y], true, true);
            activeLastPointToolip(chart);
        }, "json")

        // activeLastPointToolip(chart);
    }, 5000);

}

function getSeriesArray(initData) {
    // console.log("initData:"+initData)
    var AData = [], BData = [], CData = [], DData = [];

    for (var i = 0; i < initData.length; i++) {
        var time = initData[i].time;
        var valueDataArr = initData[i].data;
        //    console.log(valueDataArr[0])

        AData.push({x: time, y: valueDataArr[0]});
        BData.push({x: time, y: valueDataArr[1]});
        CData.push({x: time, y: valueDataArr[2]});
        DData.push({x: time, y: valueDataArr[3]});

    }

    var seriesArray = [{
        name: 'A',
        data: AData
    }, {
        name: 'B',
        data: BData
    }, {
        name: 'C',
        data: CData
    }, {
        name: 'D',
        data: DData
    }];
    /*   var seriesArray = [{
           name: 'A',
           data: getRandomData()
       }, {
           name: 'B',
           data: getRandomData()
       }, {
           name: 'C',
           data: getRandomData()
       }, {
           name: 'D',
           data: getRandomData()
       }];*/
    return seriesArray;
}

/**
 * 生成随机数组
 * @returns {Array}
 */
function getRandomData() {

    // 生成随机值
    var data = [],
        time = (new Date()).getTime(),
        i;
    for (i = -200; i <= 0; i += 1) {
        data.push({
            x: time + i * 10000,
            y: Math.random() * 1000
        });
    }
    return data;

}
