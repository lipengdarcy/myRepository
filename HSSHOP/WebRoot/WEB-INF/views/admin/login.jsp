<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理后台管理系统</title>
<link href="${ctx }/themes/admin/dwz/themes/css/login.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#">
					<img src="${ctx }/themes/admin/dwz/themes/default/images/hsmall-logo.jpg" />
				</a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<!--ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#" target="_blank">帮助</a></li>
					</ul-->
				</div>
				<h2 class="login_title">
					<div style="display:none;">${webinfor.siteUrl }</div>
					<span class="title-text">水泥商城后台管理系统</span>
					<!--<img src="${ctx }/themes/admin/dwz/themes/default/images/login_title.png" />-->
				</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<div class="login-err">${loginErr}</div>
				<form id="form1" name="form1" action="?" method="post">
					<p>
						<label>用户名：</label> <input type="text" name="username" size="20"
							class="login_input" />
					</p>
					<p>
						<label>密码：</label> <input type="password" name="password"
							size="20" class="login_input" />
					</p>
					<!--  p>
						<label>验证码：</label> <input class="code" type="text" size="5"
							value="${sessioncodeimg }" /> <span><img
							src="${ctx }/service/checkcode" alt="验证码"
							title="看不清，点击刷新"
							style="cursor:pointer;vertical-align:text-bottom;"
							onclick="this.src=this.src+'?'+Math.random();" /> </span>
					</p>-->
					<div class="login_bar">
						<input class="sub" type="submit" value="登录" />
					</div>
				</form>
			</div>
			<div class="login_banner">
				<img
					src="${ctx }/themes/admin/dwz/themes/default/images/login_banner2.jpg" />
			</div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#"></a></li>
				</ul>
				<div class="login_inner">
					<p></p>
				</div>
			</div>
		</div>
		<div id="login_footer"><%@ include file="../../includes/commons/footer.jsp"%></div>
	</div>
</body>
</html>
