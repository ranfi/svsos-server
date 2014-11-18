<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String selected = request.getParameter("num"); %>
<html>
<head>
	   <title>Bootstrap 实例 - 响应式表格</title>
   <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
   <script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
   <script src="${ctx}/static/bootstrap/3.2/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>	
<form action="${ctx}/wx/order" method="post" name="myform">
	<p></p>
	<p>&nbsp;&nbsp;&nbsp;共接单XX条，待完成</p>
	<p>&nbsp;&nbsp;&nbsp;请点击查看详情并及时完成:</p>
	<div class="table-responsive"> 		
		<table class="table table-striped">
			<c:forEach items="${orderLists}" var="list" varStatus="status">
				<tr class="active">
				    <td>${ status.index + 1}</td>  
					<td>${list.name}&nbsp;</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
	</div>
</form>
</body>
</html>
