<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>手机号绑定验证-红狮水泥商城</title>
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
	function bindTelnum(){
		var verCode = $("#verCode").val();
		var telnum = $("#telnum").val();
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		if(!verCode){
			hsArtDialog.content("验证码不能为空!").showModal();
			return;
		}
		if(!telnum){		
			hsArtDialog.content("手机号不能为空!").showModal();
		}
		
		$.ajax({
			url: 'bindTelnum.do',// 跳转到 action    
			data:{
				telnum : telnum,
				verCode : verCode
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){				
					hsArtDialog.content("绑定成功！").showModal();
					window.location.href = url+"/ucenter/goeditover.do";
				}else{
					hsArtDialog.content(data.msg).showModal();
				}
			},
			error : function() {			
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}  
		});
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
	
	$(document).ready(function(e){
		$("#get-vercode-btn").bind("click",function(e){	
			var telnum = $("#telnum").val();
			if(telnum){
			if($(this).attr("class").indexOf("btn-link")!=-1){
			var src=e.target;
			//$(src).html("正在发送......");
			time(this);
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url: 'sendVerifyTelcode.do',// 跳转到 action    
				data:{
					telnum : telnum
				},
				type:'post',
				dataType:'json',
				success:function(data) {    
					if(data.state == 'success'){					
						hsArtDialog.content("验证码已发送，请注意查收！").showModal();
					}else{
						hsArtDialog.content(data.msg).showModal();
					}
					//$(src).html("重新发送");
				},
				error : function() {
					//$(src).html("重新发送");
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}  
			});
			}
			}else{
				hsArtDialog.content("请输入手机号码！").showModal();
			}
		});
	});
		
</script>
<link href="${ctx }/themes/default/css/home.css" rel="stylesheet"
	type="text/css" />
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
				<h2 id="memberRT">手机号绑定</h2>
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
									<th width="130" height="50" align="right">手机号码：</th>
									<td><strong style="font-size:16px; padding-right:10px;">${telnum}</strong>
										<input style="display:none" id="telnum" value="${telnum}"/>
									</td>
								</tr>
								<tr>
									<th height="50" align="right">手机验证码：<br/>
									</th>
									<td><input type="text" name="verCode" value="" id="verCode"
										class="text left" maxlength="20" style="width:120px;"/>
										<span class="btn btn-link" id="get-vercode-btn">获取验证码</span>
									</td>
								</tr>
								<tr>
									<th align="right">&nbsp;</th>
									<td><a href="javascript:bindTelnum()" class="greenBT"
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