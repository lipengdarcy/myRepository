<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/account.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var changePwd = function(){
		var $form = $("#changePwdForm");
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		var msg = validateForm($form);
		if(msg.length > 0){
			hsArtDialog.content(msg).showModal();
			return;
		}
		var uid = $form.find("#uid_h").val();
		var password = $.trim($form.find("#password").val());
		var verifyCode = $.trim($form.find("#verifyCode").val());
		$.ajax({
			url: url+'account.do?method=doFindpwd3',
			data:{
					uid : uid,
					verifyCode : verifyCode,
					password : password
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					location.href = url+"account/findpwd4.do";
				}else if(data.state == 'errorYzm'){
					hsArtDialog.content("验证码错误！").showModal();
				}else if(data.state == 'noUser'){
					hsArtDialog.content("该用户不存在！").showModal();
				}else{
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}    
		});  
	},  validateForm = function($form){
		var s = "";
		var password = $.trim($form.find("#password").val());
		var confirmPwd = $.trim($form.find("#confirmPwd").val());
		if(password == '' || password != confirmPwd){
			s += "确认密码不一致！\n";
		}
		var verifyCode = $.trim($form.find("#verifyCode").val());
		if(verifyCode == ''){
			s += "验证码不能为空!";
		}
		return s;
	};
</script>
</head>
<body>
	<div id="loginTop" class="box">
	    <a href="${ctx }/index.do" class="left"><img src="${ctx }/themes/default/images/logo.png" width="186" height="42" /></a>
	    <h2>找回密码</h2>
	    <div class="clear"></div>
	</div>
	
	<div class="findPassword box">
	<div class="step">
	<ul>
	    <li><s>1</s>验证身份</li>
	    <li><s>2</s>验证邮箱</li>
	    <li class="hot"><s>4</s>设置新密码</li>
	    <li><s>4</s>完成</li>
	    <div class="clear"></div>
	</ul>
	</div>
	
	<form id="changePwdForm" action="">
	<input type="hidden" id="uid_h" value="${uid }" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <th width="190">账户名：</th>
	    <td width="275">${userName }</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <th width="190"><em>*</em>新密码：</th>
	    <td width="275"><input type="password" id="password" value="" class="text passWord" /></td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <th width="190"><em>*</em>确认密码：</th>
	    <td width="275"><input type="password" id="confirmPwd" value="" class="text passWord" /></td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <th><em>*</em>验证码：</th>
	    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	    <td width="132"><input type="text" id="verifyCode"  class="YZM text" /></td>
	    <td><img id="verifyImage" style="cursor:hand" height="34" title="点击刷新验证码" onclick="this.src='${ctx}/tool/verifyimage.do?time=' + new Date()" src="${ctx }/tool/verifyimage.do" /></td>
	    </tr>
	    </table>
	    </td>
	    <td></td>
	  </tr>
	  <tr>
	    <td align="right">&nbsp;</td>
	    <td><a href="javascript:void(1);" class="passBt redBT" onclick="changePwd()">提交</a></td>
	    <td>&nbsp;</td>
	  </tr>
	</table>
	</form>
	</div>
		

	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>
