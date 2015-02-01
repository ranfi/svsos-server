<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>个人信息</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="format-detection" content="telephone=no" />
	<meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width" />
    <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet"/>
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
                    <span>${user.servicePoint.spName}</span>
                </div>
                <div class="form-group">
                    <label for="customerAddress">网点地址：</label>
                    <span>${user.servicePoint.address}</span>
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
    <footer>
		    <nav>
		        <div class="bottom">
		            <ul class="bottomNav" >
		                <li class="bottomIco bottomIco1 "><a href="${ctx}/wx/order"><i class="bottomBg"></i>我的工单</a></li>
		                <li class="bottomIco bottomIco3"><a href="${ctx}/wx/signin"><i class="bottomBg"></i>每日签到</a></li>
		                <li class="bottomIco bottomIco4 selected"><a href="${ctx}/wx/user/profile"><i class="bottomBg"></i>个人中心</a></li>
		            </ul>
		        </div>
		    </nav>
		</footer>
</div>
</body>
</html>
