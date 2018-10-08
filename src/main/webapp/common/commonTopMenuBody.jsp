<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="smoothmenu1" class="ddsmoothmenu" style="z-index:9999999">
    <ul>
        <li><a href="${ctx}/main.jsp">首页</a></li>
        <li><a href="${ctx}/readlTimePages/indexMillA.jsp">实时数据</a>
            <ul>
                <li><a href="${ctx}/readlTimePages/indexMillA.jsp">实时数据【A磨】</a></li>
                <li><a href="${ctx}/readlTimePages/indexMillB.jsp">实时数据【B磨】</a></li>
                <li><a href="${ctx}/readlTimePages/indexMillC.jsp">实时数据【C磨】</a></li>
                <li><a href="${ctx}/readlTimePages/indexMillD.jsp">实时数据【D磨】</a></li>
            </ul>
        </li>
        <li><a href="${ctx}/historyPages/historyA.jsp">历史曲线</a>
            <ul>
                <li><a href="${ctx}/historyPages/historyA.jsp">历史曲线【A磨】</a></li>
                <li><a href="${ctx}/historyPages/historyB.jsp">历史曲线【B磨】</a></li>
                <li><a href="${ctx}/historyPages/historyC.jsp">历史曲线【C磨】</a></li>
                <li><a href="${ctx}/historyPages/historyD.jsp">历史曲线【D磨】</a></li>
            </ul>
        </li>
        <li><a href="${ctx}/reportPages/index.jsp">历史报表</a></li>
        <li><a href="${ctx}/set/index">参数设置</a></li>
        <li><a href="${ctx}/alarmPages/index.jsp">告警信息</a></li>
        <li><a href="${ctx}/communicationPages/index.jsp">通讯状态</a></li>
        <li><a href="${ctx}/dcsConfig/dcsConfigIndex.jsp">DCS配置</a>
            <ul>
                <li><a href="${ctx}/dcsConfig/dcsConfigIndex.jsp">DCS参数配置</a></li>
                <li><a href="${ctx}/dcsConfig/getDataIndex.jsp">上传数据配置</a></li>
                <li><a href="${ctx}/dcsConfig/sendDataIndex.jsp">下载数据配置</a></li>
            </ul>
        </li>
    </ul>
    <br style="clear: left"/>
</div>