<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String selected = request.getParameter("num"); %>
<html>
<head>
    <title>工单详情页</title>
    <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootstrap/3.2/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<form action="" method="post" name="myform">
    <p></p>
    <h4>&nbsp;&nbsp;&nbsp;工单详情</h4>

    <div class="table-responsive">
        <p>&nbsp;&nbsp;&nbsp;报单时间：${order.startTime}</p>

        <p>&nbsp;&nbsp;&nbsp;用户姓名：${order.name}</p>

        <p>&nbsp;&nbsp;&nbsp;用户电话：${order.tel}</p>

        <p>&nbsp;&nbsp;&nbsp;用户地址：${order.address}</p>

        <p>&nbsp;&nbsp;&nbsp;产品类型：${order.productCategory}</p>

        <p>&nbsp;&nbsp;&nbsp;服务类型：${order.serviceType}</p>

        <p>&nbsp;&nbsp;&nbsp;服务原因：${order.remark}</p>

        <p>&nbsp;&nbsp;&nbsp;派单系统：${order.remark}</p>

        <p>&nbsp;&nbsp;&nbsp;用户要求：${order.remark}</p>
        <c:if test="${order.status==2}"><p>&nbsp;&nbsp;&nbsp;工单状态:&nbsp;&nbsp;未接单</p></c:if>
        <c:if test="${order.status==3}"><p>&nbsp;&nbsp;&nbsp;工单状态:&nbsp;&nbsp;已接单</p></c:if>
        <c:if test="${order.status==4}"><p>&nbsp;&nbsp;&nbsp;工单状态:&nbsp;&nbsp;已完成</p></c:if>
        <c:if test="${order.status==5}"><p>&nbsp;&nbsp;&nbsp;工单状态:&nbsp;&nbsp;已回访</p></c:if>
        <c:if test="${order.status==6}"><p>&nbsp;&nbsp;&nbsp;工单状态:&nbsp;&nbsp;已结算</p></c:if>
    </div>
</form>
</body>
</html>
