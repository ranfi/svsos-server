<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String selected = request.getParameter("num"); %>
<html>
<head>
	   <title>Bootstrap 实例 - 响应式表格</title>
   <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
   <style  type="text/css">  
    tr{ 
		cursor:pointer; 
		} 
   </style>
   <script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
   <script src="${ctx}/static/bootstrap/3.2/js/bootstrap.min.js" type="text/javascript"></script>
   <script>
	   function todetail(orderLsh){
		   window.location.href='./orderDetail?orderLsh='+orderLsh;
		  }
   </script>
   </head>
<body>	
<form action="" method="post" name="myform">
	<p></p>
	<p>&nbsp;&nbsp;&nbsp;共接单XX条，待完成</p>
	<p>&nbsp;&nbsp;&nbsp;请点击查看详情并及时完成:</p>
	<div class="table-responsive"> 		
		<table class="table table-striped"  >
		<thead>
			<tr >
			    <th>序号</th>
				<th>单号</th>
	            <th>日期</th>
	            <th>工单状态</th>
	            <th>用户</th>
	            <th>电话</th>
	            <th>地址</th>
	            <th>服务要求</th>
	         </tr>
		</thead>
		<tbody>
		<c:forEach items="${orderLists}" var="list" varStatus="status">
			<tr class="active" onclick="todetail('${list.orderLsh}')">
			    <td>${ status.index + 1}</td>
				<td>${list.orderLsh}&nbsp;</td>
				<td>${list.createTime}&nbsp;</td>
				 <c:choose>
				   <c:when test="${list.status==1}">
				     <td>待派单&nbsp;</td>
                   </c:when>
                   <c:when test="${list.status==2}">
				     <td>派单中&nbsp;</td>
                   </c:when>
                   <c:when test="${list.status==3}">
				     <td>已接单&nbsp;</td>
                   </c:when>
                   <c:when test="${list.status==4}">
				     <td>已完成&nbsp;</td>
                   </c:when>
                   <c:when test="${list.status==5}">
				     <td>已回访&nbsp;</td>
                   </c:when>
				   <c:otherwise>				
				    <td>已结算&nbsp;</td>
				   </c:otherwise>				
				</c:choose>
				<td>${list.name}&nbsp;</td>			
				<td>${list.tel}&nbsp;</td>
				<td>${list.address}&nbsp;</td>
				<td>${list.remark}&nbsp;</td>
			</tr>
		</c:forEach>
		</tbody>		
	</table>
	</div>
</form>
</body>
</html>
