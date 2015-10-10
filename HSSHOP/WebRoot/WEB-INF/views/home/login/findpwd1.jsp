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
	var findPwd = function(){
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		var $form = $("#findPwdForm");
		var msg = validateForm($form);
		if(msg.length > 0){
			hsArtDialog.content(msg).showModal();
			return;
		}
		var username = $.trim($form.find("#myname").val());
		var verifyCode = $.trim($form.find("#verifyCode").val());
		$.ajax({
			url: url+'account.do?method=doFindpwd1',
			data:{
					verifyCode : verifyCode,
					username : username
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					var uid = data.uid;
					location.href = url+"account/findpwd2.do?uid="+uid;
				}else if(data.state == 'errorYzm'){
					hsArtDialog.content("验证码错误！").showModal();
				}else if(data.state == 'noUser'){
					hsArtDialog.content("该账户不存在！").showModal();
				}else if(data.state == 'noMail'){
					hsArtDialog.content("该账户邮箱未认证或者邮箱不合法！").showModal();
				}else{	
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}    
		});  
	}, validateForm = function($form){
		var s = "";
		var name = $.trim($form.find("#myname").val());
		if(name == ''){
			s += "账户名不能为空！\n";
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
	    <li class="hot"><s>1</s>验证身份</li>
	    <li><s>2</s>验证邮箱</li>
	    <li><s>4</s>设置新密码</li>
	    <li><s>4</s>完成</li>
	    <div class="clear"></div>
	</ul>
	</div>
	
	<form id="findPwdForm" action="">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <th width="190"><em>*</em>账户名：</th>
	    <td width="275"><input type="text" id="myname" value="" class="text userName" /></td>
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
	    <td><a href="javascript:void(1);" class="passBt redBT" onclick="findPwd()">提交</a></td>
	    <td>&nbsp;</td>
	  </tr>
	</table>
	</form>
	</div>
		

	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>
