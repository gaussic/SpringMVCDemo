<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main/coalMillTable.css'/>"/>

<div id="coalMillTableADiv" class="coalMillTableDivClass">
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