<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<title><sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">

<link href="${ctx}/static/bootstrap-plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/bootstrap-plugins/bootstrap-switch/css/bootstrap-switch.min.css" type="text/css"/>
<link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/layout/css/layout.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctx}/static/css/tasks.css" rel="stylesheet"
	type="text/css" />
	
	
<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/static/font-awesome/css/font-awesome-ie7.css">
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctx}/static/jquery/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>      
<script src="${ctx}/static/bootstrap/3.2/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap-plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap-plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap-plugins/uniform/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap-plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="/lianxi-web/static/bootstrap-plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${ctx}/static/bootstrap-plugins/respond.min.js"></script>
<script src="${ctx}/static/bootstrap-plugins/excanvas.min.js"></script> 
<![endif]-->

<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
	});
</script>
<script src="/lianxi-web/static/js/metronic.js" type="text/javascript"></script>
<script src="/lianxi-web/static/layout/js/layout.js" type="text/javascript"></script>

<sitemesh:head />
</head>
<body class="page-header-fixed page-quick-sidebar-over-content">
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
   <div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<%@ include file="/WEB-INF/layouts/left.jsp"%>
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
		    <sitemesh:body />
	    </div>
	</div>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>