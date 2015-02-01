<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>工单详情</title>
   	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="format-detection" content="telephone=no" />
	<meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width" />
    <script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/ajaxfileupload.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/order.js" type="text/javascript"></script>
    <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
 <script>
 function ajaxFileUpload() {  
	 var url='${ctx}/wx/orderDetail/upload';
	 var fileToUpload = $("#fileToUpload");
	 if(fileToUpload.val() == "" || fileToUpload.val() == null){
		alert("请选择上传图片"); 
		return;
	 }else{
	     $.ajaxFileUpload({  
	             url:'./orderDetail/upload',  
	             secureuri:false,  
	             fileElementId:'fileToUpload',  
	             dataType: 'json',  
	             success: function (data, status) {  
	                 $('#img').css('display', 'display');
	                 $("#img").attr("src", data.imgPath);  
	                 $("#srcUrl").attr("value", data.url); 
	             },  
	             error: function (data, status, e)  
	             {  
	                 alert(data.message+" error:  " + e);  
	             }  
	               
	         }  
	     )
	 }
     return false;  
 }
 </script>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading" style="display:none">
        <input type="text" id ="message" name="message" value="${message}">
    </div>
    <div class="panel-heading">
        <h4><i class="glyphicon glyphicon-search"></i> 工单详情</h4>
    </div>
    <div class="panel-body">
        <div style="padding:10px;">
            <form class="form-horizontal" method="post" name="myform" action="${ctx}/wx/order/finish">               
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
                    <span>${order.productCate.name}</span>
                </div>
                <div class="form-group">
                    <label for="remark">服务原因：</label>
                    <span>${order.remark}</span>
                </div>
                <div class="form-group">
                    <label for="orderChannel">工单来源：</label>
                    <span>${order.orderChannelPo.name}</span>
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
                <c:if test="${order.status==3}">
                   <div class="form-group">
                   		<img id="img"  alt="..."  class="img-thumbnail" height="90" width="120"/> 
                   </div>
	               <div class="form-group">
					    <label for="fileToUpload">上传工单凭证</label>
					    <input type="file" id="fileToUpload" name="fileToUpload">
					    <input type="hidden" name="srcUrl" id="srcUrl">
		                <input type="hidden" name="orderLsh" id="orderLsh" value="${order.orderLsh}" >
					    <p class="help-block">上传完成工单的手机拍照,支持jpg和png格式</p>
					    <button class="btn btn-primary" id="buttonUpload" onclick="return ajaxFileUpload();">上传</button>  
				  </div>
                <div class="form-group">
                   <button class="btn btn-primary" id="buttonUpload" type="submit">工单完成</button>
                </div>
                </c:if>
                
                <c:if test="${order.status == 4}">
	                <div class="form-group">
	                    <label for="finishOrderPic">完成工单图片：</label>
	                    <img  alt=""  src="${sysurl}${order.finishPic}" class="img-thumbnail"  height="90" width="120"/>  
	                </div>
                </c:if>
            </form>
        </div>
    </div>
</div>
<script>
$(function(){   		
	var message =$("#message").val();
	if(message!="")
	{
		alert(message);
	}	
})
</script>
</body>
</html>
