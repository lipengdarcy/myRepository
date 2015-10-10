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
	var sendFindPwdEmail = function(){
		var $form = $("#findPwdForm");
		var msg = validateForm($form);
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		if(msg.length > 0){
			hsArtDialog.content(msg).showModal();
			return;
		}
		$form.find("#msg").html("正在发送邮件......");
		var uid = $.trim($form.find("#uid_h").val());
		$.ajax({
			url: url+'account.do?method=doFindpwd2',
			data:{
					uid : uid
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					$form.find("#msg").html("发送成功,请到邮箱查看邮件");
				}else if(data.state == 'error'){
					$form.find("#msg").html("<a href=\"javascript:void(0)\" onclick=\"sendFindPwdEmail()\">重新发送验证邮件</a>");
					hsArtDialog.content("邮件发送出现错误，请重新尝试或者联系管理员！").showModal();
				}else{
					$form.find("#msg").html("<a href=\"javascript:void(0)\" onclick=\"sendFindPwdEmail()\">重新发送验证邮件</a>");
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}    
		});  
	}, validateForm = function($form){
		var s = "";
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
	    <li class="hot"><s>2</s>验证邮箱</li>
	    <li><s>4</s>设置新密码</li>
	    <li><s>4</s>完成</li>
	    <div class="clear"></div>
	</ul>
	</div>
	
	<form id="findPwdForm" action="">
	<input type="hidden" id="uid_h" value="${uid }" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	 	<tr>
			<th width="190"><em>*</em>已验证邮箱：</th>
			<td width="375"  >
				<c:if test="${!empty email }">
							<font size="+1" style="font-weight: bold;">${email }</font> &nbsp;&nbsp;&nbsp; <span
								id="msg"><a href="javascript:void(0)"
								onclick="sendFindPwdEmail()">发送验证邮件</a></span>
						</c:if>
					</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</form>
	</div>
		

	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>
