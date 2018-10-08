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


    <link rel="stylesheet" href="<c:url value='/resources/js/BootstrapV3/css/bootstrap.css'/>"/>
    <link href="<c:url value='/resources/js/BootstrapTable/bootstrap-table.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/resources/js/bootstrap3-editable/css/bootstrap-editable.css'/>" rel="stylesheet"/>

    <script type="text/javascript" src="<c:url value='/resources/js/BootstrapTable/bootstrap-table.js'/>"></script>
    <script type="text/javascript"  src="<c:url value='/resources/js/BootstrapTable/bootstrap-table-zh-CN.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/BootstrapV3/js/bootstrap.js'/>"></script>
    <script type="text/javascript"  src="<c:url value='/resources/js/bootstrap3-editable/js/bootstrap-editable.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap3-editable/bootstrap-table-edittable.js'/>"></script>



    <script src="<c:url value='/resources/js/dcsConfig/common.js'/>"></script>
    <%--<script src="<c:url value='/resources/js/alarmHistory/index.js'/>"></script>--%>
    <script src="<c:url value='/resources/js/dcsConfig/getDataIndex.js'/>"></script>
</head>
<body>
<jsp:include page="/common/commonTopMenuBody.jsp"></jsp:include>
远程接收数据配置

<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default" style="display:none;">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <label class="control-label col-sm-1" for="txt_search_departmentname">部门名称</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="txt_search_departmentname">
                    </div>
                    <label class="control-label col-sm-1" for="txt_search_statu">状态</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="txt_search_statu">
                    </div>
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">查询
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div id="toolbar" class="btn-group" style="display:none;">
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
        <button id="btn_edit" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
        </button>
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
    </div>
    <table id="tb_departments"></table>
</div>

<script type="text/javascript">


</script>
</body>
</html>
