
var contrastChartOptions = {
    reflow: false,
    borderWidth: 0,
    backgroundColor: null,
    marginLeft: 50,
    marginRight: 50/*,
    zoomType: 'x'*/
};
var contrastPlotOptions =  {
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
        enableMouseTracking: true
    }
};
var contrastYAxis = {
    gridLineWidth: 1,
    labels: {
        enabled: true
    },
    title: {
        text: null
    },
    // min: 0.6,
    // max : 10,
    showFirstLabel: false,
    opposite: true,
    tickPositioner: function () {
        var positions = [],
            tick = Math.floor(this.dataMin),
            increment = Math.ceil((this.dataMax - this.dataMin) / 6);
        for (tick; tick - increment <= this.dataMax; tick += increment) {
            positions.push(tick);
        }
        return positions;
    }
};
var contrastXAxis = {
    visible: false,
    type: 'datetime',
    showLastTickLabel: true,
    maxZoom: 10 * 5 * 1000, // 50秒
    title: {
        text: null
    }
};
var contrastYAxis =  [{
    title: {
        text: null
    },
    // min: 0.6,
    // max: 10,
    startOnTick: false,
    opposite: true,
    tickPositioner: function () {
        console.log(this.dataMin,this.dataMax)
        // if(this.dataMin)
        // this.dataMin = 0.1;
        // this.dataMax = 0.1;
        var positions = [],
            tick = Math.floor(this.dataMin),
            increment = Math.ceil((this.dataMax - this.dataMin) / 6);
        if(increment == 0){
            // positions.push(tick - 1);
            positions.push(tick);
            positions.push(Math.ceil(tick + 1));
            positions.push(Math.ceil(tick + 2));
        }else {
            for (tick; tick - increment <= this.dataMax; tick += increment) {
                positions.push(tick);
            }
        }
        return positions;
    }
    /*,
    maxZoom: 0.1*/

}];

/**
 * 创建一个有数据的contrastChart
 * @param series
 * @returns {Highcharts.Chart}
 */
function generatorWithDataContrastChart(series,chartName) {
    return Highcharts.chart(contrastContainer, {
        chart: contrastChartOptions,
        title: {text: chartName,margin:0},
        xAxis: contrastXAxis,
        yAxis: contrastYAxis,
        tooltip: curveToolTipForContrast,
        legend: {enabled: false},
        credits: {enabled: false},
        plotOptions:contrastPlotOptions,
        series: [series],
        exporting: {enabled: false}

    })
}

function generatorNoDataContrastChart() {

}