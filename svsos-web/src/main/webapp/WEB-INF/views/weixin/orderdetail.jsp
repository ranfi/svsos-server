<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String selected = request.getParameter("num"); %>
<html>
<head>
    <title>工单详情</title>
    <meta id="viewport" name="viewport"
          content="width=device-width;initial-scale=1.0;minimum-scale=1.0; maximum-scale=1.0'user-scalable=no"/>
    <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h4><i class="glyphicon glyphicon-search"></i> 工单详情</h4>
    </div>
    <div class="panel-body">
        <div style="padding:10px;">
            <form class="form-horizontal" method="post" name="myform">
                <div class="form-group">
                    <label for="bdTime">报单时间：</label>
                    <span>${order.startTime}</span>
                </div>
                <div class="form-group">
                    <label for="customerName">客户姓名：</label>
                    <span>${order.name}</span>
                </div>
                <div class="form-group">
                    <label for="customerTel">客户电话：</label>
                    <span>${order.tel}</span>
                </div>
                <div class="form-group">
                    <label for="customerAddress">客户地址：</label>
                    <span>${order.address}</span>
                </div>
                <div class="form-group">
                    <label for="productCate">产品类型：</label>
                    <span>${order.productCategory}</span>
                </div>
                <div class="form-group">
                    <label for="remark">服务原因：</label>
                    <span>${order.remark}</span>
                </div>
                <div class="form-group">
                    <label for="orderChannel">工单来源：</label>
                    <span>${order.remark}</span>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">用户要求：</label>
                    <span>${order.remark}</span>
                </div>
                <div class="form-group">
                    <label for="orderStatus">工单状态：</label>
                <span> <c:if test="${order.status==2}">未接单</c:if>
                <c:if test="${order.status==3}">已接单</c:if>
                <c:if test="${order.status==4}">已完成</c:if>
                <c:if test="${order.status==5}">已回访</c:if>
                <c:if test="${order.status==6}">已结算</c:if></span>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
