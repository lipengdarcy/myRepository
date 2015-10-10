<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账户安全-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<script type="text/javascript">
	uid = 1;
	isGuestSC = 1;
	scSubmitType = 0;
</script>
<link href="${ctx }/themes/default/css/ucenter.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/region.js" type="text/javascript"></script>
<script src="${ctx }/scripts/ucenter.user.js" type="text/javascript"></script>
<script type="text/javascript">
	function sendVerifyEmail(){
		var email = $("#email").val();
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		if(email.indexOf("@")<1){
			hsArtDialog.content("请先填写正确的邮箱！").showModal();
			return;
		}
		if(email.indexOf(".")<1){
			hsArtDialog.content("请先填写正确的邮箱！").showModal();
			return;
		}
		//document.getElementById("getV").style.display="none";
		//document.getElementById("getS").style.display="block";
		var o = $("#getV");
		time(o);
		if($("#getV").attr("class").indexOf("btn-link")!=-1){
		$.ajax({
			url: 'sendVerifyEmail.do',// 跳转到 action    
			data:{
				email : email
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					hsArtDialog.content("邮件已发送，请注意查收！").showModal();
					//$("#getS").hide();
				}else{
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}  
		});
		}
	}
	
	var wait=90;  
	function time(o) {  
	        if (wait == 0) {  
	           // o.removeAttribute("disabled");     
	           $(o).addClass("btn-link");
	           $(o).html("再次获取验证码");  
	            wait = 90;  
	        } else {  
	          //  o.setAttribute("disabled", true);  
	          	$(o).removeClass("btn-link");
	            $(o).html("(" + wait + "秒)后重新获取");  
	            wait--;  
	            setTimeout(function() {  
	                time(o)  
	            },  
	            1000)  
	        }  
	    }
	
	
	function verifyEmail(){
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		var code = $("#emailCode").val();
		var email = $("#email").val();
		if(code==null){
			hsArtDialog.content("验证码不能为空!").showModal();
			return;
		}
		if(email.indexOf("@")<1){
			hsArtDialog.content("请先填写正确的邮箱!").showModal();
			return;
		}
		if(email.indexOf(".")<1){
			hsArtDialog.content("请先填写正确的邮箱!").showModal();
			return;
		}
		$.ajax({
			url: 'verifyEmail.do',// 跳转到 action    
			data:{
				email : email,
				code : code
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					hsArtDialog.content("验证通过!").showModal();
					window.location.href = url+"/ucenter/goeditover.do";
					//window.location.href = "goeditpassword.do";
				}else{
					hsArtDialog.content("验证码错误!").showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}  
		});
	}
</script>
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
</head>

<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>

	<div class="bigBox" id="member"
		style="background:#f5f5f5;padding:15px 0;">
		<div class="box">
			<%@ include file="oldcentermenu.jsp"%>
			<div id="memberR">
				<h2 id="memberRT">验证身份</h2>

				<div class="step">
					<ul>
						<li class="hot"><s>1</s>验证身份</li>
						<li><s>2</s>完成</li>
						<div class="clear"></div>
					</ul>
				</div>

				<div class="safeYZ">

					<form name="verifyMobileForm" action="">
						<input type="hidden" name="act" value="updatepassword"/>
						<table width="600" border="0" cellpadding="0" cellspacing="0"
							class="memberTable">
							<tbody>
								<tr>
									<th width="130" height="50" align="right">已验证邮箱：</th>
									<td><strong style="font-size:16px; padding-right:10px;">${user.email}</strong>
										<input style="display:none" id="email" value="${user.email}"/>
									</td>
								</tr>
								<tr>
									<th height="50" align="right">请填写邮箱校验码：<br/>
									</th>
									<td><input type="text" name="emailCode" value="" id="emailCode"
										class="text left" maxlength="20" style="width:180px;"/><a
										id="getV" onclick='sendVerifyEmail()'
										style="height:30px; line-height:30px; margin-left:10px; padding:0 15px;cursor: pointer;">获取邮箱校验码</a>
										<span id="getS"style="height:30px; line-height:30px; margin-left:10px; padding:0 15px; display:none">邮件发送中，请稍后...</span>
									</td>
								</tr>
								<tr>
									<th align="right">&nbsp;</th>
									<td><a href="javascript:verifyEmail()" class="greenBT"
										style="font-size:14px; padding:3px 20px;">提交</a>
									</td>
								</tr>
							</tbody>
						</table>
					</form>

				</div>

			</div>

			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>