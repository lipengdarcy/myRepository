<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/account.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	$(function() {	
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				login();
			}
		};
		$("#loginForm").validationEngine({
			onValidationComplete: function(form, status){
			      if(status){
			    	  
			      }
			    }
				});

	});

	var login = function() {
		var $form = $("#loginForm");
		var msg = validateForm($form);
		if (msg.length > 0) {
			hsArtDialog.content(msg).showModal();
			return;
		}
		var username = $.trim($form.find("#myname").val());
		var password = $.trim($form.find("#password").val());
		var verifyCode = $.trim($form.find("#verifyCode").val());	
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:67
		});
		$.ajax({
			url : url + 'account.do?method=doLogin',// 跳转到 action    
			data : {
				verifyCode : verifyCode,
				username : username,
				password : password
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					var returnUrl = $("#return_url_h").val();
					if (returnUrl != '') {
						returnUrl = decodeURIComponent(returnUrl);
					} else {
						returnUrl = url + "index.do";
					}
					location.href = returnUrl;
				} else if (data.state == 'errorYzm') {
					hsArtDialog.content("验证码错误").showModal();
				} else if (data.state == 'noUser') {
					hsArtDialog.content("用户不存在或者密码错误").showModal();
				} else {
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			},
			error : function() {
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();	
			}
		});
	}, validateForm = function($form) {
		var s = "";
		var name = $.trim($form.find("#myname").val());
		if (name == '') {
			s += "用户名不能为空！\n";
		}
		var password = $.trim($form.find("#password").val());
		if (password == '') {
			s += "密码不能为空！\n";
		}
		var verifyCode = $.trim($form.find("#verifyCode").val());
		if (verifyCode == '') {
			s += "验证码不能为空!";
		}
		return s;
	};
</script>
</head>
<body>
	<input type="hidden" id="return_url_h" value="${returnUrl }" />
	<div id="loginTop" class="box">
		<a href="${ctx }/index.do" class="left"><img
			src="${ctx }/themes/default/images/logo.png" width="186" height="42" /></a>
		<h2>欢迎登陆</h2>
		<div class="clear"></div>
	</div>
	<div class="box login">
		<a href="${ctx }/account/register.do" class="loginREG">免费注册</a> <img
			src="${ctx }/upload/advert/ad_1411271729304777423.png" width="461"
			height="355" class="left" />

		<div class="right">
			<form id="loginForm" action="">

				<dl>
					<dt>邮箱/用户名/已验证手机</dt>
					<dd>
						<input type="text" id="myname" class="userName text validate[required]" value="" />
					</dd>
				</dl>

				<dl>
					<dt>密码</dt>
					<dd>
						<input type="password" id="password" class="passWord text validate[required]"
							value="" />
					</dd>
				</dl>


				<dl>
					<dt>验证码</dt>
					<dd>
						<input type="text" id="verifyCode" class="YZM text left validate[required]" /><img
							id="verifyImage" style="cursor: hand" height="34" title="点击刷新验证码"
							onclick="this.src='${ctx}/tool/verifyimage.do?time=' + new Date()"
							src="${ctx }/tool/verifyimage.do" />
						<div class="clear"></div>
					</dd>
				</dl>

				<div class="loginBt">
					<!-- 
		        <label><input name="isRemember" type="checkbox" style="vertical-align:-2px; margin-right:3px;" value="1" />自动登陆 &nbsp; &nbsp; </label>
		         -->
					<a href="${ctx }/account/findpwd.do">忘记密码?</a> <a
						href="javascript:void(0)" class="redBT loginIn" onclick="login()">登
						&nbsp; 录</a>
				</div>

			</form>
		</div>
		<div class="clear"></div>
	</div>


	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>
