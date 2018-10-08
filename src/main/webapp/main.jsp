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
    <jsp:include page="common/commonTopMenuJsAndMenu.jsp"></jsp:include>
    <script src="<c:url value='/resources/js/jquery-1.10.2.js'/>"></script>

    <script src="<c:url value='/resources/js/Highcharts/highcharts.js'/>"></script>
    <script src="<c:url value='/resources/js/Highcharts/modules/exporting.js'/>"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="<c:url value='/resources/js/Highcharts/themes/dark-unica.js'/>"></script>

    <script src="${pageContext.request.contextPath}/resources/js/main/Group.js"></script>

    <link rel="stylesheet" href="<c:url value='/resources/css/main/main.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/radioAndCheckBox.css'/>">

    <style type="text/css">

    </style>
    <script type="text/javascript">

        $(document).ready(function () {

        })
    </script>
</head>
<body>

<jsp:include page="common/commonTopMenuBody.jsp"></jsp:include>

<div id="container1" class="container container1 divShow" style=""></div>
<div id="container1_relativeBar" class="container container1 divHide" style="">R</div>
<div id="container1_absoluteBar" class="container container1 divHide" style="">A</div>

<div id="container1V" class="container container1 divHide" style=""></div>
<div id="container1V_relativeBar" class="container container1 divHide" style="">R</div>
<div id="container1V_absoluteBar" class="container container1 divHide" style="">A</div>
<jsp:include page="mainPages/main_table_A.jsp" flush="true"></jsp:include>


<div id="container2" class="container container2 divShow" style=""></div>
<div id="container2_relativeBar" class="container container2 divHide" style="">R</div>
<div id="container2_absoluteBar" class="container container2 divHide" style="">A</div>

<div id="container2V" class="container container2 divShow" style=""></div>
<div id="container2V_relativeBar" class="container container2 divHide" style="">R</div>
<div id="container2V_absoluteBar" class="container container2 divHide" style="">A</div>
<jsp:include page="mainPages/main_table_B.jsp"></jsp:include>
<div id="container3" class="container container3 divShow" style=""></div>
<div id="container3_relativeBar" class="container container3 divHide" style="">R</div>
<div id="container3_absoluteBar" class="container container3 divHide" style="">A</div>

<div id="container3V" class="container container3 divShow" style=""></div>
<div id="container3V_relativeBar" class="container container3 divHide" style="">R</div>
<div id="container3V_absoluteBar" class="container container3 divHide" style="">A</div>

<jsp:include page="mainPages/main_table_C.jsp"></jsp:include>
<%--<input type="button" value="切换曲线" id="changeToCurveBtn_3" class="changeBtnClass curveBtnClass"/>--%>
<%--<input type="button" value="切换相对柱状图" id="changeToBarRelativeBtn_3" class="changeBtnClass barRelativeBtnClass"/>--%>
<%--<input type="button" value="切换绝对柱状图" id="changeToBarAbsoluteBtn_3" class="changeBtnClass absoluteBtnClass"/>--%>
<div id="container4" class="container container4 divShow" style=""></div>
<div id="container4_relativeBar" class="container container4 divHide" style="">R</div>
<div id="container4_absoluteBar" class="container container4 divHide" style="">A</div>

<div id="container4V" class="container container4 divShow" style=""></div>
<div id="container4V_relativeBar" class="container container4 divHide" style="">R</div>
<div id="container4V_absoluteBar" class="container container4 divHide" style="">A</div>
<jsp:include page="mainPages/main_table_D.jsp"></jsp:include>

<script src="<c:url value='/resources/js/curveAndBar/commonHandleData.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/curveAndBar/curve_column.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/curveAndBar/absoluteBarForFreshRealTime.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/curveAndBar/relativeBarForFreshRealTime.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/main/main.js'/>" type="text/javascript"></script>

<script type="text/javascript">

</script>
</body>
</html>
