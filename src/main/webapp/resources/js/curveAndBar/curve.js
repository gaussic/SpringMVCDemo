// JS 代码
Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

function activeLastPointToolip(chart) {
    var points = chart.series[0].points;
    chart.tooltip.refresh(points[points.length - 1]);
}

//var chart1 = getChart("container");

function getChart(divId, titleName, subtile) {
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
            text: subtile
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

            formatter: function () {
                console.log(this)
                var returnStr = Highcharts.dateFormat(' %H:%M:%S', this.x) + '<br/>' +
                    '<b>' + this.series.name + ' : ' + Highcharts.numberFormat(this.y, 2) + "</b><br/>";
                return returnStr;
            }
        },
        legend: {
            enabled: false
        },
        series: getSeriesArray()
    });
    return chart;
}

function getLoadFunction (){
    var loadObj = {
        load: function () {
            var series = this.series[0],
                chart = this;
            activeLastPointToolip(chart);
            setInterval(function () {
                $.get("getRealTimeData", function (result) {
                    var x = (new Date()).getTime(), // 当前时间
                        y = result.density;          // 随机值
                    series.addPoint([x, y], true, true);
                    activeLastPointToolip(chart);
                }, "json")
            }, 5000);
        }
    }
    return loadObj;
}

function freshChartFunction(chart) {
    console.log(chart);
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

function getSeriesArray() {
    var serriesArray = [{
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
    }];
    return serriesArray;
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
