var data = [],
    detailChart, //四条曲线数据
    contrastChart,//对比曲线数据
    container = document.getElementById('containerDensity'),
    detailContainer = null,
    contrastContainer = null,
    masterContainer = null,
    masterChart = null;


// JS 代码
Highcharts.setOptions({
    global: {
        useUTC: false,
        animation: false,
        enableMouseTracking: false,

    }
});


/**
 * 历史曲线的toolTip
 * @type {{valueSuffix: string, split: boolean, distance: number, padding: number, style: {fontSize: string, fontFamily: string, fontWeight: string}, dateTimeLabelFormats: {millisecond: string, second: string, minute: string, hour: string, day: string}}}
 */
var curveToolTip = {
    valueSuffix: '',
    // split: true,
    distance: 20,
    padding: 10,
    shared: true,
    style: {
        fontSize: "14px",
        fontFamily: "微软雅黑",
        fontWeight: "light"
    },
    dateTimeLabelFormats: {
        millisecond: '%Y-%m-%d %H:%M:%S',
        second: '%Y-%m-%d %H:%M:%S',
        minute: '%Y-%m-%d %H:%M:%S',
        hour: '%Y-%m-%d %H:%M:%S',
        day: '%Y-%m-%d %H:%M:%S'
    }
};
/**
 * 磨煤机莫煤量曲线提示
 * @type {{valueSuffix: string, distance: number, padding: number, style: {fontSize: string, fontFamily: string, fontWeight: string}, dateTimeLabelFormats: {millisecond: string, second: string, minute: string, hour: string, day: string}}}
 */
var curveToolTipForContrast = {
    valueSuffix: '',
    // split: true,
    distance: 20,
    padding: 10,
    // shared:true,
    style: {
        fontSize: "14px",
        fontFamily: "微软雅黑",
        fontWeight: "light"
    },
    dateTimeLabelFormats: {
        millisecond: '%Y-%m-%d %H:%M:%S',
        second: '%Y-%m-%d %H:%M:%S',
        minute: '%Y-%m-%d %H:%M:%S',
        hour: '%Y-%m-%d %H:%M:%S',
        day: '%Y-%m-%d %H:%M:%S'
    }
};
/**
 * masterChart 鼠标选择中区域事件
 * @type {{selection: mastreClickEvent.selection}}
 */
//TODO 当detialchart中含有plotLine时，改变区域，需要将plotline去除。plotline包含millchart与detailchart
var mastreClickEvent = function(startTime,endTime){
    return {
        // listen to the selection event on the master chart to update the
        // extremes of the detail chart
        selection: function (event) {
            var extremesObject = event.xAxis[0],
                min = extremesObject.min,
                max = extremesObject.max,
                /*detailData = [],*/
                xAxis = this.xAxis[0];
            var seriesArr = this.series;
            var detailSeriesArr = detailChart.series;
            for (var x in seriesArr) {
                var detailData = [];
                var masterSeries = seriesArr[x];
                Highcharts.each(masterSeries.data, function (d) {
                    if (d.x > min && d.x < max) {
                        detailData.push([d.x, d.y]);
                    }
                });
                var masterSeriesName = masterSeries.name;
                for (var m in detailSeriesArr) {
                    var detailSeries = detailSeriesArr[m];
                    var detailSeriesName = detailSeries.name;
                    if (detailSeriesName === masterSeriesName) {
                        detailSeries.setData(detailData, false);
                    }
                }

            }
            detailChart.redraw();


            //对锅炉负荷曲线进行重绘
            var seriesForBe = detailSeriesArr[0];
            var seriesDataForBe = [];
            Highcharts.each(seriesForBe.data, function (d) {
                if (d.x > min && d.x < max) {
                    seriesDataForBe.push([d.x, d.y]);
                }
            });
            contrastChart.series[0].setData(seriesDataForBe);
            /* Highcharts.each(this.series[0].data, function(d) {
                 if(d.x > min && d.x < max) {
                     detailData.push([d.x, d.y]);
                 }
             });*/
            // move the plot bands to reflect the new detail span
            xAxis.removePlotBand('mask-before');
            xAxis.addPlotBand({
                id: 'mask-before',
                // from: Date.UTC(2006, 0, 1),
                from: startTime,
                to: min,
                color: 'rgba(0, 255, 255, 0.2)'
            });
            xAxis.removePlotBand('mask-after');
            xAxis.addPlotBand({
                id: 'mask-after',
                from: max,
                // to: Date.UTC(2008, 11, 31),
                to: endTime,
                color: 'rgba(0, 255, 255, 0.2)'
            });
            /*detailChart.series[0].setData(detailData);*/
            initTable();
            return false;
        }
    }
};







/**
 * 磨煤机煤量曲线
 * @param master
 * @param startTime
 * @param endTime
 * @param seriesData
 */
function createContrastChart(master, startTime, endTime, seriesData,chartName) {
    var seriesDataForBe = [], max = 0, min = 0;
    Highcharts.each(master.series[0].data, function (d) {
        if (d.x >= startTime) {
            seriesDataForBe.push(d.y);
            if (d.y > max) {max = d.y;}
            if (d.y < min) {min = d.y;}
        }

    });
    var series = {
        name: '锅炉负荷',
        color: 'red',
        pointStart: startTime,
        pointInterval: 5 * 1000,
        lineWidth: 0.5,
        data: seriesDataForBe
    };
    // if(min === max){
        //创建一个没有数据的contrastChart
        // contrastChart = generatorNoDataContrastChart();
    // }else {
        //创建一个有数据的contraschart
        contrastChart = generatorWithDataContrastChart(series,chartName);
    // }
}

/**
 * 创建详细数据图
 * @param masterChart
 */
function createDetail(masterChart, startTime, endTime, seriesData,chartName) {

    var detailStart = startTime;
    var seriesDataArr = [];
    var masterChartSeriesArr = masterChart.series;
    var max = 0, min = 0;
    for (var x in masterChartSeriesArr) {
        var masterChartSeries = masterChartSeriesArr[x];
        var detailData = [];
        Highcharts.each(masterChartSeries.data, function (d) {
            if (d.x >= detailStart) {
                detailData.push(d.y);
                if (d.y > max) {max = d.y;}
                if (d.y < min) {min = d.y;}
            }
        });
        var series = {
            yAxis: 0,
            name: masterChartSeries.name,
            color: masterChartSeries.color,
            pointStart: detailStart,
            pointInterval: 5 * 1000,
            lineWidth: 0.5,
            data: detailData,
            // data: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
            events: detailClickEvent
        };
        seriesDataArr.push(series);
    }
    // if (min === max) {
        //生成一个没有数据的detailChart
        // detailChart = generatorNoDataDetailChart();
    // } else {
        //生成一个有数据的detailChart
        // detailChart = generatorWithDataDetailChart(seriesDataArr);
    // }
    detailChart = generatorWithDataDetailChart(seriesDataArr,chartName);
}

/**
 * 创建导航图
 * @returns {Highcharts.Chart}
 */
function createMaster(startTime, endTime, pipeHistoryDataArr,chartName) {
    var seriesObjArr = [];
    var pipeHistoryDataArrCopy = $.extend(true,{},pipeHistoryDataArr);


    for (var x in pipeHistoryDataArrCopy) {
        var p = pipeHistoryDataArrCopy[x];
        var targetData = [];
        var sourceData = p.data;
        for(var m in sourceData){
            targetData.push(sourceData[m]);
        }

        var series = {
            type: 'area',
            name: p.name,
            color: p.color,
            pointInterval: 5 * 1000,
            pointStart: startTime,
            data: targetData
        };
        seriesObjArr.push(series);
    }
    /*    [{
            type: 'area',
            name: 'USD to EUR',
            pointInterval: 5 * 1000,
            pointStart: startTime,
            data: seriesData
        }]*/
    return Highcharts.chart(masterContainer, {
        chart: {
            reflow: false,
            borderWidth: 0,
            backgroundColor: null,
            marginLeft: 50,
            marginRight: 20,
            zoomType: 'x',
            events:mastreClickEvent(startTime,endTime)
        },
        title: {
            text: null
        },
        xAxis: {
            type: 'datetime',
            showLastTickLabel: true,
            maxZoom: 10 * 5 * 1000, // 50秒
            plotBands: [{
                id: 'mask-before',
                /*  from: Date.UTC(2006, 0, 1),
                  to: Date.UTC(2008, 7, 1),*/
                from: startTime,
                to: endTime,
                color: 'rgba(0, 0, 0, 0.2)'
            }],
            title: {
                text: null
            }
        },
        yAxis: {
            gridLineWidth: 0,
            labels: {
                enabled: false
            },
            title: {
                text: null
            },
            min: 0.6,
            showFirstLabel: false
        },
        tooltip: {
            formatter: function () {
                return false;
            }
        },
        legend: {
            enabled: false,
            itemStyle: {
                fontFamily: '微软雅黑',
                fontSize: 6,
                fontWeight: 'light'
            }
        },
        credits: {
            enabled: false
        },
        plotOptions: {
            series: {
                fillColor: {
                    linearGradient: [0, 0, 0, 70],
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, 'rgba(255,255,255,0)']
                    ]
                },
                lineWidth: 1,
                animation: false,
                marker: {
                    enabled: false
                },
                shadow: false,
                states: {
                    hover: {
                        enabled: false,
                        lineWidth: 0.2
                    }
                },
                enableMouseTracking: false
            }
        },
        series: seriesObjArr,
        exporting: {
            enabled: false
        }
    }, function (masterChart) {
        createDetail(masterChart, startTime, endTime, pipeHistoryDataArr,chartName);
        createContrastChart(masterChart, startTime, endTime, pipeHistoryDataArr,chartName);
    });
}

/*
 * 创建 detailContainer 并 append 到 container 中
 */
detailContainer = document.createElement('div');
container.appendChild(detailContainer);
/*
 * 创建 masterContainer 并 append 到 container 中
 */
masterContainer = document.createElement('div');
masterContainer.style.position = 'absolute';
masterContainer.style.top = '400px';
masterContainer.style.height = '100px';
masterContainer.style.width = '100%';
container.appendChild(masterContainer);

contrastContainer = document.createElement("div");
contrastContainer.style.position = "absolute";
contrastContainer.style.top = '0px';
contrastContainer.style.height = '200px';
contrastContainer.style.width = '100%';
container.appendChild(contrastContainer);


/*
 * 开始创建导航图，详细的图是在导航图的回调函数中创建的
 * 代码入口
 */
//var masterChart = createMaster();


$("#queryBtn").bind("click", initHistoryData);

function PipeHistoryData(type, name, color, data) {
    this.type = type;
    this.name = name;
    this.color = color;
    this.data = data;
    this.getData = function(){
      return this.data.concat();
    };
}

function initTable() {
    var detailXAxis = detailChart.xAxis[0];
    var seriesArr = detailChart.series;
    var xMax = detailXAxis.dataMax;
    var xMin = detailXAxis.dataMin;
    for (var x in seriesArr) {
        var series = seriesArr[x];
        var currentSeriesName = series.name;

        var data, left, right, totalCount, avg, totalValues, max, maxValue, min, minValue = 0, leftValue, rightValue;
        data = series.data;
        maxValue = data[0].y;
        minValue = data[0].y;
        max = data[0].x;
        min = data[0].x;

        left = xMin;
        right = xMax;
        totalCount = 0;
        avg = 0;
        totalValues = 0;
        leftValue = data[0].y;
        rightValue = data[data.length - 1].y;


        Highcharts.each(data, function (d) {

            totalCount += 1;
            totalValues += d.y;
            if (d.y > maxValue) {
                max = d.x;
                maxValue = d.y;
            }
            if (d.y < minValue) {
                min = d.x;
                minValue = d.y;
            }
        });
        avg = totalValues / totalCount;
        initTableRow(currentSeriesName, left, leftValue, right, rightValue, max, maxValue, min, minValue, avg)
    }

}

function initTableRow(currentSeriesName, left, leftValue, right, rightValue, xMax, maxValue, xMin, minValue, avg) {
    var $table = $("#dataTable");
    $table.find("tr:gt(0)").each(function () {
        var firstTdHtml = $(this).find("td:eq(0)");
        if (firstTdHtml[0].innerHTML === currentSeriesName) {
            $(this).find("td:eq(1)").html(dateFtt("yyyy-MM-dd hh:mm:ss", new Date(left)));
            $(this).find("td:eq(2)").html(leftValue);
            $(this).find("td:eq(4)").html(dateFtt("yyyy-MM-dd hh:mm:ss", new Date(right)));
            $(this).find("td:eq(5)").html(rightValue);
            $(this).find("td:eq(7)").html(dateFtt("yyyy-MM-dd hh:mm:ss", new Date(xMax)));
            $(this).find("td:eq(8)").html(maxValue);
            $(this).find("td:eq(9)").html(dateFtt("yyyy-MM-dd hh:mm:ss", new Date(xMin)));
            $(this).find("td:eq(10)").html(minValue);
            $(this).find("td:eq(11)").html(Highcharts.numberFormat(avg, 2));


        }
    });
}

var pipe1ForDensity, pipe2ForDensity, pipe3ForDensity, pipe4ForDensity;
var pipe1ForVelocity, pipe2ForVelocity,pipe3ForVelocity, pipe4ForVelocity;
var densityDataArr = [pipe1ForDensity,pipe2ForDensity,pipe3ForDensity,pipe4ForDensity];
var velocityDataArr = [pipe1ForVelocity,pipe2ForVelocity,pipe3ForVelocity,pipe4ForVelocity];

function initHistoryData() {
    // var url = "../getAMillHistoryData";
    var url = getQueryUrl();
    var queryData = $("#queryForm").serialize();
    var chartName = "密度历史曲线";
    $.getJSON(url, queryData, function (result) {

        var densityType = "density",
            velocityType = "velocity";
        var pipe1Color = "#ff0000",
            pipe2Color = "#00ff00",
            pipe3Color = "#1200ff",
            pipe4Color = "#00eaff";

         pipe1ForDensity = new PipeHistoryData(densityType, "pipe1", pipe1Color, result.pipe1Density);
         pipe2ForDensity = new PipeHistoryData(densityType, "pipe2", pipe2Color, result.pipe2Density);
         pipe3ForDensity = new PipeHistoryData(densityType, "pipe3", pipe3Color, result.pipe3Density);
         pipe4ForDensity = new PipeHistoryData(densityType, "pipe4", pipe4Color, result.pipe4Density);

         pipe1ForVelocity = new PipeHistoryData(velocityType, "pipe1", pipe1Color, result.pipe1Velocity);
         pipe2ForVelocity = new PipeHistoryData(velocityType, "pipe2", pipe2Color, result.pipe2Velocity);
         pipe3ForVelocity = new PipeHistoryData(velocityType, "pipe3", pipe3Color, result.pipe3Velocity);
         pipe4ForVelocity = new PipeHistoryData(velocityType, "pipe4", pipe4Color, result.pipe4Velocity);

        densityDataArr = [pipe1ForDensity,pipe2ForDensity,pipe3ForDensity,pipe4ForDensity];
        velocityDataArr = [pipe1ForVelocity,pipe2ForVelocity,pipe3ForVelocity,pipe4ForVelocity];

        masterChart = createMaster(result.startTime, result.endTime, [pipe1ForDensity, pipe2ForDensity, pipe3ForDensity, pipe4ForDensity],chartName);


        initTable();
        return false;

    });
}