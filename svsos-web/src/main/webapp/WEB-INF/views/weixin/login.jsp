<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>随售—家电售后维修的平台</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<!--[if IE 7]>
		<link rel="stylesheet" href="${ctx}/static/font-awesome/css/font-awesome-ie7.css">
	<![endif]-->
	<!--<link href="${ctx}/static/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>-->
	<link href="${ctx}/static/bootstrap-switch/css/bootstrap-switch.min.css" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="${ctx}/static/css/login.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/static/css/components.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/static/css/plugins.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/static/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/static/layout/css/layout.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${ctx}/static/layout/css/custom.css" rel="stylesheet" type="text/css" id="style_color"/>
	<!-- END PAGE LEVEL STYLES -->
	<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">	
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="form-vertical login-form" method="post" action="${ctx}/wx/login/save">
		    <input type="text" name="idKey" value="${idKey}" style="display:none">
			<h3 class="form-title">登陆</h3>
			<c:if test="${not empty message}">
				<div id="message" class="alert alert-danger"><button data-dismiss="alert" class="close">×</button>${message}</div>
			</c:if>
			<div class="alert alert-danger display-hide" role="alert">
				<button class="close" data-close="alert"></button>
				<span>请输入用户名和密码.${errormsg}</span>
			</div>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="input-icon">
					<i class="fa fa-user"></i>
					<input class="form-control  placeholder-no-fix" type="text" placeholder="用户名" name="username" autocomplete="off"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i>
					<input class="form-control placeholder-no-fix" type="password" placeholder="密码" name="password" autocomplete="off"/>
				</div>
			</div>
			<div class="form-actions" style="margin-left:0px;">
			    <label class="checkbox"><input type="checkbox" name="remember" value="1"/>记住我</label>
				<button type="submit" class="btn green pull-right">
					登陆 <i class="m-icon-swapright m-icon-white"></i>
				</button>            
			</div>
		</form>
		<!-- END LOGIN FORM -->        
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->

	<!-- END COPYRIGHT -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${ctx}/static/jquery/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>      
	<script src="${ctx}/static/bootstrap/3.2/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${ctx}/static/js/metronic.js" type="text/javascript"></script>
	<script src="${ctx}/static/layout/js/layout.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/login.js" type="text/javascript"></script>      
	<!-- END PAGE LEVEL SCRIPTS --> 
	<script>
	/* <shiro:authenticated>window.location="${ctx}/main"</shiro:authenticated>
	jQuery(document).ready(function() {     
  		Metronic.init(); // init metronic core components
		Layout.init(); // init current layout
 		Login.init();
	}); */

	</script>
	<!-- END JAVASCRIPTS -->
<!-- END BODY -->
</html>