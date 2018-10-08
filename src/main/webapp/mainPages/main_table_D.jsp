<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<style type="text/css" href="<c:url value='/resources/css/main/coalMillTable.css'/>"></style>
<div id="coalMillTableDDiv" class="coalMillTableDivClass">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <th rowspan=5 style="width:50px;">磨煤<br/>机A</th>
            <th>状态</th>
            <th style="width:10px;">值</th>

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
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>浓度[相对]</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
    </table>

</div>