<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String selected = request.getParameter("num"); %>
<html>
<head>
    <title>Bootstrap 实例 - 响应式表格</title>
    <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootstrap/3.2/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<form action="${ctx}/wx/order/change" method="post" name="myform">
    <input type="text" name="orderLsh" value="${order.orderLsh}" style="display:none">

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

        <p>&nbsp;&nbsp;&nbsp;工单状态:派单中</p>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-success" name="button" value="accept">接单</button>
        点击接单后工单状态跳转为已接单

    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-danger" name="button" value="refuse">拒绝</button>
    </div>
</form>
</body>
</html>
