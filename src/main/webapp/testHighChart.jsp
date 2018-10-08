<%--
Created by IntelliJ IDEA.
User: admin
Date: 2018/6/27
Time: 13:18
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <script src="<c:url value='/resources/js/jquery-1.10.2.js'/>"></script>

    <script src="<c:url value='/resources/js/Highcharts/highcharts.src.js'/>"></script>
    <script src="<c:url value='/resources/js/Highcharts/modules/exporting.js'/>"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="<c:url value='/resources/js/Highcharts/themes/dark-unica.js'/>"></script>



    <style type="text/css">

    </style>
    <script type="text/javascript">

        $(document).ready(function () {

        })
    </script>
</head>
<body>


<div id="container" class="container container1 divShow" style=""></div>

<input type="button" id="changeBtn1" value="数据1"/>
<input type="button" id="changeBtn2" value="数据2"/>
<script type="text/javascript">
    var chart;
    var data1 = getRandomData();
    var data2 = getRandomData();
    var data3 = getRandomData();
    var data4 = getRandomData();
    function changeData1(){


        // var data = getRandomData();
        chart.series[0].setData(data1);
        chart.series[1].setData(data2);
    }
    function changeData2(  ){
        // var data = getRandomData();
        chart.series[0].setData(data3);
        chart.series[1].setData(data4);
    }

    function getRandomData(){
        var arr = [];
        for(var i=0;i<12;i++){
            arr.push(Math.round(Math.random() * 100));
        }
        return arr;
    }
    $(function () {
        $("#changeBtn1").bind("click",changeData1);
        $("#changeBtn2").bind("click",changeData2);


        $('#container').highcharts({
            chart: {
                //alignTicks: false,
                type: 'line'
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: [{
                title: {
                    text: 'Primary Axis'
                },
                gridLineWidth: 0
            }, {
                title: {
                    text: 'Secondary Axis'
                },
                opposite: true
            }],
            legend: {
                layout: 'vertical',
                backgroundColor: '#FFFFFF',
                floating: true,
                align: 'left',
                x: 100,
                verticalAlign: 'top',
                y: 70
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        this.x + ': ' + this.y;
                }
            },
            plotOptions: {
            },
            series: [{
                data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
            }, {
                data: [129.9, 271.5, 306.4, 29.2, 544.0, 376.0, 435.6, 348.5, 216.4, 294.1, 35.6, 354.4],
                yAxis: 1
            }]
        });

        chart = $('#container').highcharts();
    });
</script>
</body>
</html>
