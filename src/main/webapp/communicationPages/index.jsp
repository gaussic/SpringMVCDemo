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


    <link rel="stylesheet" href="<c:url value='/resources/js/svg/jquery.svg.css'/>"/>
    <script src="<c:url value='/resources/js/svg/jquery.svg.js'/>"></script>
    <script src="<c:url value='/resources/js/communicationPage/main.js'/>"></script>
</head>
<body>

<jsp:include page="/common/commonTopMenuBody.jsp"></jsp:include>


<div id="linkStateSvg"  style="height:600px;"></div>
<script type="text/javascript">
    $(document).ready(function(){
        // var volocityRrelativeBar = getRelativeBar("container","风速","相对值");
    });

</script>

</body>
</html>
