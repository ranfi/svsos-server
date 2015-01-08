<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>个人信息</title>
    <meta id="viewport" name="viewport"
          content="width=device-width;initial-scale=1.0;minimum-scale=1.0; maximum-scale=1.0'user-scalable=no"/>
    <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h4> 个人信息</h4>
    </div>
    <div class="panel-body">
        <div style="padding:10px;">
            <form class="form-horizontal" method="post" name="myform">
                <div class="form-group">
                    <label for="bdTime">姓名：</label>
                    <span>${user.name}</span>
                </div>
                <div class="form-group">
                    <label for="customerName">联系电话：</label>
                    <span>${user.mobilephone}</span>
                </div>
                <div class="form-group">
                    <label for="customerTel">所属网点：</label>
                    <span>${user.spId}</span>
                </div>
                <div class="form-group">
                    <label for="customerAddress">网点地址：</label>
                    <span>${user.address}</span>
                </div>
                <div class="form-group">
                    <label for="productCate">网点电话：</label>
                    <span>${user.mobilephone}</span>
                </div>
                <div class="form-group">
                    <label for="remark">本月工单：</label>
                    <span>${user.acceptedOrderNum}</span>
                </div>
                <div class="form-group">
                    <label for="orderChannel">投诉次数：</label>
                    <span>${user.complaintNum}</span>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>