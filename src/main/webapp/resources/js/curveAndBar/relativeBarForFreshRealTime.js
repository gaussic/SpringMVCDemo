// var relativeBar_1 = getRelativeBar("container_relativeBar");
/**
 * 刷新相对柱图
 * @param chart
 * @param barData
 */
function freshRelativeBar(chart, barData) {
    var series = chart.series;
    series[0].setData([barData.pipe1Data]);
    series[1].setData([barData.pipe2Data]);
    series[2].setData([barData.pipe3Data]);
    series[3].setData([barData.pipe4Data]);
}

function getRelativeBarWithOptions(divId, titleName, subTile, chartOption) {
    var chartOptions = {
        chart: {
            type: 'column',
            credits: {enabled: false},
            events: {
                load: function () {
                }
            }
        },
        title: {text: titleName},
        subtitle: {text: subTile},
        xAxis: {categories: [""]},
        yAxis: {title: {text:""}},
        series: [{name: 'A', data: []}, {name: 'B', data: []}, {name: 'C', data: []}, {name: 'D', data: []}]
    };
    $.extend(chartOptions, chartOption);
    return Highcharts.chart(divId, chartOptions);
}


function getRelativeBar(divId, titleName, subTile) {
    var chart = Highcharts.chart(divId, {
        chart: {
            type: 'column',
            credits: {
                enabled: false
            },
            events: {
                load: function () {
                    /*var series = this.series;
                    var url = "/wind5/getMillAVelocityRealTimeData";
                    setInterval(function () {
                        $.getJSON(url, function (result) {
                            var time = result.time;
                            // console.log(result)
                            var valueData = result.data;
                            var s1Arr = new Array();
                            s1Arr.push(valueData[0]);
                            var s2Arr = new Array();
                            s2Arr.push(valueData[1]);
                            var s3Arr = new Array();
                            s3Arr.push(valueData[2]);
                            var s4Arr = new Array();
                            s4Arr.push(valueData[3]);
                            series[0].setData(s1Arr);
                            series[1].setData(s2Arr);
                            series[2].setData(s3Arr);
                            series[3].setData(s4Arr);
                        });
                    }, 50000);*/
                }
            }
        },
        title: {
            text: titleName
        },
        subtitle: {
            text: subTile
        },
        yAxis: {title: {text:""}},
        exporting:false,
        xAxis: {
            categories: [""]
        },
        series: [{name: 'A', data: []}, {name: 'B', data: []}, {name: 'C', data: []}, {name: 'D', data: []}]

    });
    return chart;
}