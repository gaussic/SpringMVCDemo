<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">


    <title>图表切换</title>


    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->


    <script type="text/javascript" src="js/jquery-1.8.3.js">
    </script>
    <script type="text/javascript" src="js/highcharts.js">
    </script>
    <script type="text/javascript" src="js/exporting.js">
    </script>
    <script type="text/javascript">
        var arr = ${jscore};
        var num="[";
        var str="[";
        for(var i = 0;i<arr.length;i++){
            if(i>0){
                num+=","+arr[i].chineseScore;
                str+=","+arr[i].matchScore;
            }else{
                num+=arr[i].chineseScore;
                str+=arr[i].matchScore;
            }
        } num+="]";
        str+="]";
        var chinese =  eval('(' + num +')');
        //alert(typeof(chinese));
        //alert(chinese);
        var match = eval('(' + str +')');
        //alert(typeof(match));
        //alert(match);
        function fn(d) {


            $('#container').highcharts( { //图表展示容器，与div的id保持一致
                chart : {
                    type : d
//指定图表的类型，默认是折线图（line）
                },
                title : {
                    text : 'Score Table' //指定图表标题
                },
                xAxis : {
                    tickmarkPlacement : 'on',
                    categories : [ 'sun', 'kele', 'fenda','coffe', 'milk', 'tea','water' ]
//指定x轴分组
                },
                yAxis : {
                    title : {
                        text : 'something' //指定y轴的标题
                    }
                },
                series : [{
                    name : 'MatchScore',
                    data :match
                },{
                    name : 'ChineseScore',
                    data :chinese
                } ]
            });
        };
    </script>
</head>
<body>
<div>
    <select id="sel" name="test" onchange="demo()">
        <option value="column" selected>
            柱状图
        </option>
        <option value="line">
            折线图
        </option>
        <option value="spline">
            曲线图
        </option>
        <option value="bar">
            条形图
        </option>
    </select>
    <input id="tex" type="text" name="text1" id="text1">
</div>
<div id="container" style="min-width: 400px; height: 400px"></div>
<script>
    document.getElementById("sel").value = "column";
    var obj = document.getElementById("sel");
    if (obj.fireEvent) {
        obj.fireEvent('onchange');
    } else {
        obj.onchange();
    }
    function demo() {
        var d = document.getElementById("sel").value;
        document.getElementById("tex").value = d;
        fn(d);


    }
</script>


</body>
</html>