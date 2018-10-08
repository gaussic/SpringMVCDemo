<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/6/27
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <jsp:include page="/common/commonTopMenuJsAndMenu.jsp"></jsp:include>
    <style type="text/css">
        table{
            width:85%;
        }
        th{
            height:50px;
            font-weight:bold;
        }
        table, th, td {
            border: 1px solid black;
        }

        td {
            height:30px;
            text-align: center;
            vertical-align: middle;
        }
    </style>


</head>
<body>

<jsp:include page="/common/commonTopMenuBody.jsp"></jsp:include>
<div align="center">
    参数设置

    <table cellspacing="0px" cellpadding="0px">
        <thead>
        <tr>
            <th>序号</th>

            <th>名称</th>
            <th>粉管名称</th>
            <th>粉管编号</th>

            <th>粉管位置</th>
            <th>粉管参数一</th>
            <th>粉管参数二</th>
            <th>粉管参数三</th>
            <th>粉管参数四</th>
            <th>设备地址</th>
            <th>测试</th>
            <th>修改</th>
        </tr>

        </thead>
        <c:forEach items="${coalMillList}" var="coalMill">
            <c:set var="pipingListLength" value="${fn:length(coalMill.coalPipingEntityList)}"/>
            <tr>
            <%--"--%>
            <td rowspan="${pipingListLength}">${coalMill.id}</td>
            <td rowspan="${pipingListLength}">${coalMill.cName}</td>
            <c:forEach items="${coalMill.coalPipingEntityList}" var="coalPiping" varStatus="status">
                <c:set var="pipingSet" value="${coalPiping.coalPipingSetEntity}"/>
                <c:if test="${status.index !=0}">
                    <tr>
                </c:if>
                <td>${coalPiping.pNameUserDefined}</td>
                <td> ${coalPiping.pNumUserDefined} </td>

                <td>${coalPiping.pLocationUserDefined}</td>
                <td>${pipingSet.sParam1}</td>
                <td>${pipingSet.sParam2}</td>
                <td>${pipingSet.sParam3}</td>
                <td>${pipingSet.sParam4}</td>
                <td>${pipingSet.sUrl}</td>
                <td><input type="button" id="testBtn" value="测试"></td>
                <td><a href="editPipingSet?piping.id=${coalPiping.id}">修改</a></td>
                <%--<td>${coalPiping.pName}</td>--%>
                <c:if test="${status.index !=0}">
                    </tr>

                </c:if>
            </c:forEach>
            </tr>

        </c:forEach>


    </table>
</div>
</body>
</html>
