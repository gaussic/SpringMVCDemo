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
    <script src="<c:url value='/resources/js/jquery-1.10.2.js'/>"></script>

    <script src="<c:url value='/resources/js/Highcharts/highcharts.src.js'/>"></script>
    <script src="<c:url value='/resources/js/Highcharts/modules/exporting.js'/>"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="<c:url value='/resources/js/Highcharts/themes/dark-unica.js'/>"></script>

    <link href="<c:url value='/resources/css/realTimePage/main.css'/>" rel="stylesheet"/>
    <style type="text/css">


    </style>
</head>
<body>

<jsp:include page="/common/commonTopMenuBody.jsp"></jsp:include>

<div id="container_Mill" class="container containerMill " style=""></div>
<div id="container" class="container containerCurve " style=""></div>
<div id="container_relativeBar" class="container containerRelativeBar " style="">R</div>
<div id="container_absoluteBar" class="container containerAbsoluteBar " style="">A</div>
实时曲线
<div id="container_V" class="container containerVCurve " style=""></div>
<div id="container_V_relativeBar" class="container containerVRelativeBar " style="">R</div>
<div id="container_V_absoluteBar" class="container containerVAbsoluteBar " style="">A</div>

<%--<script src="${ctx}/js/curveAndBar/curve.js" type="text/javascript"></script>
<script src="${ctx}/js/curveAndBar/absoluteBar.js" type="text/javascript"></script>
<script src="${ctx}/js/curveAndBar/relativeBar.js" type="text/javascript"></script>--%>

<div style="position:absolute;margin-left:950px;margin-top:50px;">

    <table cellpadding="0" cellspacing="0" id="realTimeTable">
        <tr>
            <th rowspan=5 style="width:100px;">磨煤<br/>机D</th>
            <th>状态</th>
            <th style="width:120px;">值</th>

            <th style="width:100px;">A</th>
            <th style="width:100px;">B</th>
            <th style="width:100px;">C</th>
            <th style="width:100px;">D</th>
        </tr>
        <tr>
            <th rowspan="2" style="width:60px;">运行中</th>
            <td>风速[绝对]</td>
            <td>0</td>
            <td>01</td>
            <td>02</td>
            <td>03</td>
        </tr>
        <tr>
            <td>风速[相对]</td>
            <td>0</td>
            <td>01</td>
            <td>02</td>
            <td>03</td>
        </tr>
        <tr>
            <td rowspan="2">0</td>
            <td>浓度[绝对]</td>
            <td>11</td>
            <td>12</td>
            <td>13</td>
            <td>14</td>
        </tr>
        <tr>
            <td>浓度[相对]</td>
            <td>21</td>
            <td>22</td>
            <td>23</td>
            <td>24</td>
        </tr>
    </table>
</div>

<script src="<c:url value='/resources/js/curveAndBar/commonHandleData.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/curveAndBar/curve_column.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/curveAndBar/absoluteBarForFreshRealTime.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/curveAndBar/relativeBarForFreshRealTime.js'/>" type="text/javascript"></script>


<script src="<c:url value='/resources/js/realTimePages/main.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/realTimePages/millD.js'/>" type="text/javascript"></script>
<script type="text/javascript">


</script>
</body>
</html>
