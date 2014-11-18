<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String selected = request.getParameter("num"); %>
<html>
<head>
	   <title>Bootstrap 实例 - 响应式表格</title>
   <link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
   <style>  
    .styled-select select {  
   width: 60px;  
   padding: 5px;  
   font-size: 14px;  
   line-height: 1;  
   border: 0;  
   border-radius: 0;  
   height: 34px;  
   -webkit-appearance: none;  
   }  
     
   .styled-select {  
   width: 60px;  
   height: 24px;  
   overflow: hidden;     border: 1px solid #ccc;  
   }  
</style> 
   <script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
   <script src="${ctx}/static/bootstrap/3.2/js/bootstrap.min.js" type="text/javascript"></script>
   <script type="text/javascript">
	window.onload=function(){
	  var theSelect=document.getElementsByName("num");
	  var theForm=document.getElementsByName("myform");
	  theSelect[0].onchange=function(){
	     theForm[0].submit()
	  }
	}
  </script>
</head>

<body>	
<form action="${ctx}/wx/order" method="post" name="myform">

<div class="table-responsive"> 
	<table class="table" >
	<tr>
		<td>
			<select class="styled-select" name="num" id="num">
			  <option value="1" <%= null != selected && "1".equals(selected) ? " selected" : "" %>>今日</option>
			  <option value="2" <%= null != selected && "2".equals(selected) ? " selected" : "" %>>本周</option>
			  <option value="3" <%= null != selected && "3".equals(selected) ? " selected" : "" %>>本月</option>
			  <option value="4" <%= null != selected && "4".equals(selected) ? " selected" : "" %>>今年</option>
			</select>
	   </td>
	   <td>
			共收到派单XX条，接单XX条,已完成XX条,XX条待完成
	   </td>
	   </tr>
	</table>		
	<table class="table table-striped">
		<thead>
			<tr>
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
		<c:forEach items="${orderLists}" var="list">
			<tr class="active">
				<td>${list.name}&nbsp;</td>
				<td>${list.createTime}&nbsp;</td>
				<td>${list.tel}&nbsp;</td>
				<td>${list.productBrand}&nbsp;</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>
		</tbody>		
	</table>
</div>
</form>
</body>
</html>
