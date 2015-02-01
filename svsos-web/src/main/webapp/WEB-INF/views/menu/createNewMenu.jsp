<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- BEGIN SIDEBAR -->
<div class="portlet light bg-inverse">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-equalizer font-red-sunglo"></i>
			<span class="caption-subject font-red-sunglo bold uppercase">创建菜单</span>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="${ctx}/menu/createNewMenu" method="post" class="form-horizontal">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-3 control-label">菜单名称</label>
					<div class="col-md-4">
						<input name="name" class="form-control" placeholder="Enter text" type="text" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">链接地址</label>
					<div class="col-md-4">
						<input name="linkAddress" class="form-control" placeholder="javascript or url" type="text" required>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3">是否子节点</label>
					<div class="radio-list col-md-2">
						<label class="radio-inline">
						<input type="radio" name="isLeafNode" value="1" checked> 是 </label>
						<label class="radio-inline">
						<input type="radio" name="isLeafNode" value="0"> 否 </label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">上级菜单</label>
					<div class="col-md-6">
						<select class="form-control col-md-2" name="pid">
							<option value="0" selected="selected">根目录</option>
							<c:forEach var="menuNode" items="${menuTree}">
								<c:if test="${menuNode.menuId>0}">
									<option value="${menuNode.menuId}">${menuNode.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">可操作role</label>
					<div class="col-md-9">
						<div class="checkbox-list">
							<c:forEach var="userRole" items="${roleList}" >
								<label class="checkbox-inline">
								<c:if test="${userRole.id==1}"><input name="checkedUserIds" type="checkbox" value="${userRole.id}" checked="checked" disabled="disabled">${userRole.name}</c:if>
								<c:if test="${userRole.id!=1}"><input name="checkedUserIds" type="checkbox" value="${userRole.id}">${userRole.name}</c:if>
								</label>
							</c:forEach>
						</div>
					</div>
				</div>
				
				
				
			</div>
			<div class="form-actions">
				<div class="row">
					<div class="col-md-offset-3 col-md-9">
						<button type="submit" class="btn green">Submit</button>
						<button type="button" class="btn default">Cancel</button>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
										
</div>