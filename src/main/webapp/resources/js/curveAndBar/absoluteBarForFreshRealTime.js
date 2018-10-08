// var absoluteBar_1 = getAbsoluteBar("container_absoluteBar");
/**
 * 刷新绝对柱图
 * @param chart
 * @param barData
 */
function freshAbsoluteBar(chart, barData) {
    var series = chart.series;
    series[0].setData([barData.pipe1Data]);
    series[1].setData([barData.pipe2Data]);
    series[2].setData([barData.pipe3Data]);
    series[3].setData([barData.pipe4Data]);
}

function getAbsoluteBarWithOptions(divId, titleName, subtitleName,chartOption) {
    var chartOptions = {
        chart: {
            type: 'column',
            events: {
                load: function () {
                }
            }
        },
        title: {
            text: titleName
        },
        subtitle: {
            text: subtitleName
        },
        xAxis: {
            categories: [
                ""
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        plotOptions: {
            column: {
                borderWidth: 0
            }
        },
        series: [{name: 'A', data: []}, {name: 'B', data: []}, {name: 'C', data: []}, {name: 'D', data: []}]
    };
    $.extend(chartOptions,chartOption);
    return Highcharts.chart(divId,chartOptions);
}


function getAbsoluteBar(divId, titleName, subtitleName) {
    var chart = Highcharts.chart(divId, {
        chart: {
            type: 'column',
            events: {
                load: function () {
                    /* var series = this.series;
                     var url = "/wind5/getMillAVelocityRealTimeDataForAbsolute";
                     setInterval(function () {
                         $.getJSON(url, function (result) {
                             var time = result.time;
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
            text: subtitleName
        },
        xAxis: {
            categories: [
                ""
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        /*tooltip: {
            // head + 每个 point + footer 拼接成完整的 table
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} t/h</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },*/
        plotOptions: {
            column: {
                borderWidth: 0
            }
        },
        series: [{
            name: 'A',
            data: [5]
        }, {name: 'B', data: []}, {name: 'C', data: []}, {name: 'D', data: []}]

    });
    return chart;
}
