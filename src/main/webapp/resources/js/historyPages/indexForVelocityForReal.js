var dataForVelocity = [100,85,100,85,120],

    detailChartForVelocity, //
    containerForVelocity = document.getElementById('containerVelocity'),
    detailContainerForVelocity = null,
    masterContainerForVelocity = null;
var dataForVelocity2 = new Array();
for (var i = 0; i < 5; i++) {
    dataForVelocity2.push(Math.random() * 100)
}


// 创建详细数据图
function createDetailForVelocity(masterChart) {
    var detailData = [],
        detailDataB = [],
        detailStart = Date.UTC(2008, 7, 1);
    Highcharts.each(masterChart.series[0].data, function (d) {
        if (d.x >= detailStart) {
            detailData.push(d.y);
        }
    });
    Highcharts.each(masterChart.series[1].data, function (d) {
        if (d.x >= detailStart) {
            detailDataB.push(d.y);
        }
    });
    detailChartForVelocity = Highcharts.chart(detailContainerForVelocity, {
        chart: {
            marginBottom: 120,
            reflow: false,
            marginLeft: 50,
            marginRight: 20,
            style: {
                position: 'absolute'
            }
        },
        credits: {
            enabled: false
        },
        title: {
            text: '速度历史曲线'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: null
            },
            maxZoom: 0.1
        },
        tooltip: {
            formatter: function () {
                var pointA = this.points[0];
                var pointB = this.points[1];
                var returnStr = Highcharts.dateFormat('%Y %b %e', this.x) + ':<br/>';
                returnStr += '<b>' + pointA.series.name + ':</b>' + Highcharts.numberFormat(pointA.y, 2) + '<br/>';
                returnStr += '<b>' + pointB.series.name + ':</b>' + Highcharts.numberFormat(pointB.y, 2) + '<br/>';
                return returnStr;
            },
            shared: true
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            radius: 3
                        }
                    }
                }
            }
        },
        series: [{
            name: 'A',
            pointStart: detailStart,
            pointInterval: 24 * 3600 * 1000,
            data: detailData
        }, {
            name: 'B',
            pointStart: detailStart,
            pointInterval: 24 * 3600 * 1000,
            data: detailDataB
        }],
    });
}

/**
 * 创建导航图
 * @returns {Highcharts.Chart}
 */
function createMasterForVelocity() {
    return Highcharts.chart(masterContainerForVelocity, {
        chart: {
            reflow: false,
            borderWidth: 0,
            backgroundColor: null,
            marginLeft: 50,
            marginRight: 20,
            zoomType: 'x',
            events: {
                // listen to the selection event on the master chart to update the
                // extremes of the detail chart
                selection: function (event) {
                    var extremesObject = event.xAxis[0],
                        min = extremesObject.min,
                        max = extremesObject.max,
                        detailData = [],
                        detailData2 = [],
                        xAxis = this.xAxis[0];
                    Highcharts.each(this.series[0].data, function (d) {
                        if (d.x > min && d.x < max) {
                            detailData.push([d.x, d.y]);
                        }
                    });
                    Highcharts.each(this.series[1].data, function (d) {
                        if (d.x > min && d.x < max) {
                            detailData2.push([d.x, d.y]);
                        }
                    });
                    // move the plot bands to reflect the new detail span
                    xAxis.removePlotBand('mask-before');
                    xAxis.addPlotBand({
                        id: 'mask-before',
                        from: Date.UTC(2006, 0, 1),
                        to: min,
                        color: 'rgba(0, 0, 0, 0.2)'
                    });
                    xAxis.removePlotBand('mask-after');
                    xAxis.addPlotBand({
                        id: 'mask-after',
                        from: max,
                        to: Date.UTC(2008, 11, 31),
                        color: 'rgba(0, 0, 0, 0.2)'
                    });
                    detailChartForVelocity.series[0].setData(detailData);
                    detailChartForVelocity.series[1].setData(detailData2);
                    return false;
                }
            }
        },
        title: {
            text: null
        },
        xAxis: {
            type: 'datetime',
            showLastTickLabel: true,
            maxZoom: 14 * 24 * 3600000, // fourteen days
            plotBands: [{
                id: 'mask-before',
                from: Date.UTC(2006, 0, 1),
                to: Date.UTC(2008, 7, 1),
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
            enabled: false
        },
        credits: {
            enabled: false
        },
        plotOptions: {
            series: [{
                fillColor: {
                    linearGradient: [0, 0, 0, 70],
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, 'rgba(255,255,255,0)']
                    ]
                },
                lineWidth: 1,
                marker: {
                    enabled: false
                },
                shadow: false,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                enableMouseTracking: false
            }, {
                fillColor: {
                    linearGradient: [0, 0, 0, 70],
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, 'rgba(255,255,255,0)']
                    ]
                },
                lineWidth: 1,
                marker: {
                    enabled: false
                },
                shadow: false,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                enableMouseTracking: false
            }],
        },
        series: [{
            type: 'area',
            name: 'A',
            pointInterval: 24 * 3600 * 1000,
            pointStart: Date.UTC(2006, 0, 1),
            data: dataForVelocity
        }, {
            type: 'area',
            name: 'B',
            pointInterval: 24 * 3600 * 1000,
            pointStart: Date.UTC(2006, 0, 1),
            data: dataForVelocity2
        }],
        exporting: {
            enabled: false
        }
    }, function (masterChartForVelocity) {
        createDetailForVelocity(masterChartForVelocity);
    });
}

/*
 * 创建 detailContainer 并 append 到 container 中
 */
detailContainerForVelocity = document.createElement('div');
containerForVelocity.appendChild(detailContainerForVelocity);
/*
 * 创建 masterContainer 并 append 到 container 中
 */
masterContainerForVelocity = document.createElement('div');
masterContainerForVelocity.style.position = 'absolute';
masterContainerForVelocity.style.top = '300px';
masterContainerForVelocity.style.height = '100px';
masterContainerForVelocity.style.width = '100%';
containerForVelocity.appendChild(masterContainerForVelocity);
/*
 * 开始创建导航图，详细的图是在导航图的回调函数中创建的
 * 代码入口
 */
 // createMasterForVelocity();