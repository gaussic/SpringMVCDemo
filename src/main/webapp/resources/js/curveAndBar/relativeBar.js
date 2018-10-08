// var relativeBar_1 = getRelativeBar("container_relativeBar");


function getRelativeBar(divId,titleName,subTile) {
    var chart = Highcharts.chart(divId, {
        chart: {
            type: 'column'
        },
        title: {
            text: titleName
        },
        subtitle: {
            text: subTile
        },



        xAxis: {
            // categories: ['苹果', '橘子', '梨', '葡萄', '香蕉']
            categories: ['A', 'B', 'C', 'D']
        },
        series:[{
            name:'密度',
            data:[5,-3,2,-4]
        }]
       /* series: [{
            name: '小张',
            data: [5, 3, 4, 7, 2]
        }, {
            name: '小彭',
            data: [2, -2, -3, 2, 1]
        }, {
            name: '小潘',
            data: [3, 4, 4, -2, 5]
        }]*/
    });
    return chart;
}