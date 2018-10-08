historyA.jsp<%--
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
    <script src="<c:url value='/resources/js/jquery-1.10.2.js'/>"></script>

    <script src="<c:url value='/resources/js/Highcharts/highcharts.src.js'/>"></script>
    <script src="<c:url value='/resources/js/Highcharts/modules/exporting.js'/>"></script>

    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="<c:url value='/resources/js/Highcharts/themes/dark-unica.js'/>"></script>


    <link rel="stylesheet" href="<c:url value='/resources/js/BootstrapV2/css/bootstrap.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/js/bootstrap-datetimepicker/css/datetimepicker.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/radioAndCheckBox.css'/>">

    <script src="<c:url value='/resources/js/BootstrapV2/js/bootstrap.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh_CN.js'/>"></script>
    <style type="text/css">
        th {
            border: 1px solid black;
        }

        td {
            border: 1px solid black;
            text-align: center;
        }

    </style>

</head>
<body>

<jsp:include page="/common/commonTopMenuBody.jsp"></jsp:include>
<div align="center">
    <DIV>
        磨煤机B
    </DIV>
    <div class="row from-inline">
        <form id="queryForm" role="form" class="form-inline" style="margin-top:10px;margin-bottom:10px;">

            <label for="startInputTime">开始时间</label>
            <input type="text" class="form-control" value="" size="16" id="startInputTime" name="startInputTime"/>
            <label for="endInputTime">结束时间</label>
            <input type="text" class="form-control" id="endInputTime" name="endInputTime"/>

        </form>
        <div>
            <label for="densityRadio" class="radio_label" id="densityRadioLabel">密度
                <input type="radio" value="density" id="densityRadio" checked="true" style="display: none" name="densityOrVelocityRadio"/>
            </label>

            <label for="velocityRadio" class="radio_label" id="velocityRadioLabel">风速
                <input type="radio" value="velocity" id="velocityRadio" style="display:none" name="densityOrVelocityRadio"/>
            </label>

            <button class="btn btn-primary form-inline" id="queryBtn" style="margin-bottom:10px;">查询</button>
        </div>

    </div>
    <div id="containerDensity"
         style="min-width:400px;height:500px;position: relative;width:90%;margin-top:0px;"></div>
    <%--<div style="height:50px;">&nbsp;</div>--%>
    <%--<div id="containerVelocity" style="min-width:400px;height:400px;position: relative;width:90%;"></div>--%>
</div>
<div style="text-align: center;margin-top:10px;">
    <table id="dataTable" style="width:92%;margin:auto;border:1px solid black;">

        <tr>
            <th>名称</th>
            <th>左侧时间</th>
            <th>值</th>
            <th>单位</th>
            <th>右侧时间</th>
            <th>值</th>
            <th>单位</th>
            <th>最大值时间</th>
            <th>最大值</th>
            <th>最小值时间</th>
            <th>最小值</th>
            <th>平均值</th>
        </tr>
        <tr>
            <td>磨煤机</td>
            <c:forEach begin="1" end="12">
                <td></td>
            </c:forEach>
        </tr>
        <tr>
            <td>pipe1</td>
            <c:forEach begin="1" end="12">
                <td></td>
            </c:forEach>
        </tr>
        <tr>
            <td>pipe2</td>
            <c:forEach begin="1" end="12">
                <td></td>
            </c:forEach>
        </tr>
        <tr>
            <td>pipe3</td>
            <c:forEach begin="1" end="12">
                <td></td>
            </c:forEach>
        </tr>
        <tr>
            <td>pipe4</td>
            <c:forEach begin="1" end="12">
                <td></td>
            </c:forEach>
        </tr>
    </table>

</div>


<script src="<c:url value='/resources/js/historyPages/indexForDensityContrast.js'/>"></script>
<script src="<c:url value='/resources/js/historyPages/indexForDensityDetail.js'/>"></script>
<%--<script src="${ctx}/js/historyPages/indexForVelocityForReal.js"></script>--%>
<script src="${ctx}/js/historyPages/indexForDensity.js"></script>

<script src="<c:url value='/resources/js/historyPages/historyCurve.js'/>"></script>
<script src="<c:url value='/resources/js/historyPages/main.js'/>"></script>
<script src="<c:url value='/resources/js/historyPages/historyB.js'/>"></script>


<script type="text/javascript">


</script>
</body>
</html>
