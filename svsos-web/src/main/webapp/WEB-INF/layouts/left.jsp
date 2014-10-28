<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
	<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
	<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
	<div class="page-sidebar navbar-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->
		
		
		
		
		
		
		
		
		<ul class="page-sidebar-menu " data-auto-scroll="true"
			data-slide-speed="200">
			
		</ul>
		
		
		
		
		
		
		
		
		
		
		
		<ul class="page-sidebar-menu " data-auto-scroll="true"
			data-slide-speed="200">
			<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
			<li class="sidebar-toggler-wrapper">
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				<div class="sidebar-toggler"></div> <!-- END SIDEBAR TOGGLER BUTTON -->
			</li>
			<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
			<li class="sidebar-search-wrapper">
				<form class="sidebar-search " action="extra_search.html"
					method="POST">
					<a href="javascript:;" class="remove"> <i class="icon-close"></i>
					</a>
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search...">
						<span class="input-group-btn"> <a href="javascript:;"
							class="btn submit"><i class="icon-magnifier"></i></a>
						</span>
					</div>
				</form> <!-- END RESPONSIVE QUICK SEARCH FORM -->
			</li>
			
			
			<li>
				<c:forEach var="menuTree" items="${menuTree}">
					<c:if test="${menuTree.nodeType==0}"><ul class="sub-menu"> <li></c:if>
					<c:if test="${menuTree.nodeType==1}">
						<a href="#">
							<i class="icon-basket"></i>
							<span class="title">${menuTree.name}</span> 
							<span class="arrow"></span>
						</a>
					</c:if>
					<c:if test="${menuTree.nodeType==2}">
						<a href="${ctx}${menuTree.linkAddress}">
							<i class="icon-basket"></i>
							<span class="title">${menuTree.name}</span> 
						</a>
					</c:if>
					<c:if test="${menuTree.nodeType==3}"></li></ul></c:if>
					
				</c:forEach>
			
			</li>
			
			
		</ul>
		<!-- END SIDEBAR MENU -->
	</div>
</div>
<!-- END SIDEBAR -->
