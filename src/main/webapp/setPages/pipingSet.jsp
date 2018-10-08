<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/7/24
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/resources/js/BootstrapV3/css/bootstrap.css'/>">

    <jsp:include page="/common/commonTopMenuJsAndMenu.jsp"></jsp:include>
    <script src="<c:url value='/resources/js/jquery-1.10.2.js'/>"></script>

    <script src="<c:url value='/resources/js/BootstrapV3/js/bootstrap.js'/>"></script>
</head>
<body>
<jsp:include page="/common/commonTopMenuBody.jsp"></jsp:include>

<form role="form" class="form-horizontal" action="updatePipingSet">
    <%--<input type="hidden" name="id" value="${coalPipingSet.id}"/>--%>
    <%--<input type="hidden" name="coalPipingEntity.id" value="${coalPipingSet.coalPipingEntity.id}"/>--%>
    <form:hidden path="coalPipingSet.id"></form:hidden>
    <form:hidden path="coalPipingSet.coalPipingEntity.id"></form:hidden>
    <form:hidden path="coalPipingSet.coalPipingEntity.coalMillEntity.id"></form:hidden>
    <div class="form-group">
        <label for="millName" class="col-lg-5  control-label">磨煤机名称</label>
        <div class="col-lg-2">
            <input type="text" class="form-control" id="millName" placeholder="磨煤机名称"
                   name="coalPipingEntity.coalMillEntity.cName"
                   value="${coalPipingSet.coalPipingEntity.coalMillEntity.cName}">
        </div>
    </div>
    <div class="form-group">
        <label for="pipeName" class="col-lg-5  control-label">管道名称</label>
        <div class="col-lg-2">
            <input type="text" class="form-control" id="pipeName" placeholder="管道名称"
                   name="coalPipingEntity.pNameUserDefined"
                   value="${coalPipingSet.coalPipingEntity.pNameUserDefined}">
        </div>
    </div>
    <div class="form-group">
        <label for="pipeNum" class="col-lg-5  control-label">管道编号</label>
        <div class="col-lg-2">
            <input type="text" class="form-control" id="pipeNum" placeholder="管道编号"
                   name="coalPipingEntity.pNumUserDefined"
                   value="${coalPipingSet.coalPipingEntity.pNumUserDefined}">
        </div>
    </div>
    <div class="form-group">
        <label for="pipeLocation" class="col-lg-5  control-label">管道位置</label>
        <div class="col-lg-2">
            <input type="text" class="form-control" id="pipeLocation" placeholder="管道位置"
                   name="coalPipingEntity.pLocationUserDefined"
                   value="${coalPipingSet.coalPipingEntity.pLocationUserDefined}">
        </div>
    </div>
    <div class="form-group">
        <label for="pipeParam1" class="col-lg-5  control-label">管道参数一</label>
        <div class="col-lg-2">
            <input type="text" class="form-control" id="pipeParam1" placeholder="管道参数一" name="sParam1"
                   value="${coalPipingSet.sParam1}">
        </div>
    </div>
    <div class="form-group">
        <label for="pipeParam2" class="col-lg-5  control-label">管道参数二</label>
        <div class="col-lg-2">
            <input type="text" class="form-control" id="pipeParam2" placeholder="管道参数二" name="sParam2"
                   value="${coalPipingSet.sParam2}">
        </div>
    </div>
    <div class="form-group">
        <label for="pipeParam3" class="col-lg-5  control-label">管道参数三</label>
        <div class="col-lg-2">
            <input type="text" class="form-control" id="pipeParam3" placeholder="管道参数三" name="sParam3"
                   value="${coalPipingSet.sParam3}">
        </div>
    </div>

    <div class="form-group">
        <label for="pipeParam4" class="col-lg-5  control-label">管道参数四</label>
        <div class="col-lg-2">
            <input type="text" class="form-control" id="pipeParam4" placeholder="管道参数四" name="sParam4"
                   value="${coalPipingSet.sParam4}">
        </div>
    </div>
    <div class="form-group">
        <label for="pipeAddress" class="col-lg-5  control-label">设备地址</label>
        <div class="col-lg-3">
            <input type="text" class="form-control" id="pipeAddress" placeholder="设备地址" name="sUrl"
                   value="${coalPipingSet.sUrl}">
        </div>
    </div>
    <div class="form-group row">

        <div class="col-lg-offset-5 col-lg-10 ">
            <%--<button id="testBtn">测试</button>--%>
            <input type="submit"/>
        </div>
    </div>
</form>

</body>
</html>

