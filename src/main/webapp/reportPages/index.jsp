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

    <jsp:include page="/common/commonTopMenuJsAndMenu.jsp"></jsp:include>
    <%--@*1、Jquery组件引用*@--%>
    <script type="text/javascript" src="<c:url value='/resources/js/jquery-1.10.2.js'/>"></script>
    <%--@*2、bootstrap组件引用*@--%>
    <script src="<c:url value='/resources/js/BootstrapV3/js/bootstrap.js'/>"></script>
    <link href="<c:url value='/resources/js/BootstrapV3/css/bootstrap.css'/>" rel="stylesheet"/>
    <%--@*3、bootstrap table组件以及中文包的引用*@--%>
    <script src="<c:url value='/resources/js/BootstrapTable/bootstrap-table.js'/>"></script>
    <link href="<c:url value='/resources/js/BootstrapTable/bootstrap-table.css'/>" rel="stylesheet"/>
    <script src="<c:url value='/resources/js/BootstrapTable/bootstrap-table-zh-CN.js'/>"></script>

    <link rel="stylesheet" href="<c:url value='/resources/js/bootstrap-datetimepicker/css/datetimepicker.css'/>">
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="<c:url value='/resources/js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh_CN.js'/>"></script>
    <style type="text/css">
        th, td {
            text-align: center;
        }

        td {
            font-size: 10px;
        }
    </style>
</head>
<body>

<jsp:include page="/common/commonTopMenuBody.jsp"></jsp:include>
<div class="from-inline,text-center" style="float:none">


    <%--<label >日期</label>
    <input type="text"  value="" size="16" id="startInputTime" name="startInputTime"/>
    <button  id="queryBtn" style="margin-bottom:10px;">查询</button>--%>
</div>
<div style="text-align: center;">
    <label style="margin-bottom:20px;margin-top:20px;">日期 :</label>
    <input type="text" value="" size="16" id="startInputTime" name="startInputTime"/>
    <button id="queryBtn" style="margin-bottom:20px;">查询</button>
    <div id="dataLoad" style="display:none;position:absolute;margin-top:-20px;margin-left:620px;"><!--页面载入显示-->
        <table width=100% height=100% border=0 align=center valign=middle>
            <tr height=50%><td align=center>&nbsp;</td></tr>
            <tr><td
                    align=center><img src="<c:url value='/resources/image/loading.gif'/>"
                                      style="width:50px;height:50px;"/></td></tr>
            <tr><td align=center style="font-weight: bold;color:red;">数据载入中，请稍后......</td></tr>
            <tr height=50%><td align=center>&nbsp;</td></tr>
        </table>
    </div>
    <table id="reportTable" class="table table-striped table-bordered table-hover table-condensed"
           style="width:90%;" align="center">
        <thead>
        <tr>
            <th colspan="33">历史报表</th>
        </tr>
        <tr>
            <th colspan="30" style="text-align: right">日期</th>
            <th colspan="3">2018-08-01</th>
        </tr>
        <tr>
            <th rowspan="3" style="vertical-align: middle">时间<br/>(HH:mm)</th>
            <th colspan="8">磨煤机A</th>
            <th colspan="8">磨煤机B</th>
            <th colspan="8">磨煤机C</th>
            <th colspan="8">磨煤机D</th>
        </tr>
        <tr>
            <c:forEach var="a" begin="1" end="4">
                <th colspan="4">浓度</th>
                <th colspan="4">风速</th>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach var="a" begin="1" end="8">
                <th>1</th>
                <th>2</th>
                <th>3</th>
                <th>4</th>
            </c:forEach>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="i" begin="1" end="288">
            <tr>
                <td>--:--</td>
                <c:forEach var="a" begin="1" end="32">
                    <td></td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var currentDate = new Date();

        initDateTimePicker("startInputTime", currentDate);

        $("#dataLoad").hide(); //页面加载完毕后即将DIV隐藏
        $("#queryBtn").bind("click",queryBtnFunction);
    })
    function queryBtnFunction(){
        //TODO 4台磨煤机分开读取数据
        // $("#reportTable").bootstrapTable();
        //TODO 当中间缺少数据的时候，需要间隔填充数据
        $("#dataLoad").show();
        var url = "../queryHistoryDataForReport";
        $.get(url, {"queryDate":$("#startInputTime").val()},function (result) {
            var $table = $("#reportTable");
            initMillData($table, result.millA, 1);
            initMillData($table, result.millB, 2);
            initMillData($table, result.millC, 3);
            initMillData($table, result.millD, 4);
            $("#dataLoad").hide();
        }, "json")
    }
    function initDateTimePicker(inputId, initDate) {
        $("#" + inputId).val(initDate.getFullYear()+"-"+(initDate.getMonth() +1)+"-" + initDate.getDate() );
        $("#" + inputId).datetimepicker({
            language: 'zh_CN',
            format: 'yyyy-mm-dd',//显示格式
            todayHighlight: 1,//今天高亮
            minView: "month",//设置只显示到月份
            startView: 2,
            forceParse: true,
            showMeridian: 1,
            initialDate: new Date(),
            autoclose: 1//选择后自动关闭
        });
    }

    function initMillData($table, millData, col) {
        for (var i = 0; i < millData.length; i++) {
            var pipeData = millData[i];
            var $tr = $table.find("tr").eq(5 + i);
            if (col == 1) {
                $tr.find("td").eq(0).html(pipeData.t);
            }
            $tr.find("td").eq(1 + (col - 1) * 8).html(pipeData.AD);
            $tr.find("td").eq(2 + (col - 1) * 8).html(pipeData.BD);
            $tr.find("td").eq(3 + (col - 1) * 8).html(pipeData.CD);
            $tr.find("td").eq(4 + (col - 1) * 8).html(pipeData.DD);
            $tr.find("td").eq(5 + (col - 1) * 8).html(pipeData.AV);
            $tr.find("td").eq(6 + (col - 1) * 8).html(pipeData.BV);
            $tr.find("td").eq(7 + (col - 1) * 8).html(pipeData.CV);
            $tr.find("td").eq(8 + (col - 1) * 8).html(pipeData.DV);
            // $table.find("tr").eq(5).find("td").eq(5).html("----");
        }
    }
</script>
</body>
</html>
