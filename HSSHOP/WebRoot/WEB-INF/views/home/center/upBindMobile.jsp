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
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		var verCode = $("#verCode").val();
		var telnum = $("#telnum").val();
		if(!verCode){
			hsArtDialog.content("验证码不能为空!").showModal();
			return;
		}
		if(!telnum){
			hsArtDialog.content("手机号不能为空!").showModal();
			return;
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
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			//var count=2;
			var src=e.target;
			if(telnum){
				if($(src).attr("class").indexOf("btn-link")!=-1 && $("#telnum").attr("class").indexOf("verfaile")==-1){
				 $.ajax({
					url: 'verifyMessage.do',// 跳转到 action    
					data:{
						telnum : telnum
					},
					type:'post',
					dataType:'json',
					success:function(result) {
						if(result.state == 'success'){
							alert(result.countSame);
							if(result.countSame<3){
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
									time(src);
							}else{
								$(src).html("正在发送。。。。");
								 setTimeout(function() {  
									 $(src).html("获取验证码");
						            },100);  
							}
						}else{
							hsArtDialog.content(result.content).showModal();
						}
					}
					});
				}
			}else{
				hsArtDialog.content("请输入手机号码！").showModal();
			}
			
		});
		
		$("#verifyMobileForm").validationEngine({
			addFailureCssClassToField:'verfaile',
			onValidationComplete: function(form, status){
			      if(status){
			    	 
			      }
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
						<li class="hot"><s>1</s>修改信息</li>
						<li><s>2</s>完成</li>
						<div class="clear"></div>
					</ul>
				</div>

				<div class="safeYZ">

					<form id="verifyMobileForm" name="verifyMobileForm" action="">
						<input type="hidden" name="act" value="updatepassword"/>
						<table width="600" border="0" cellpadding="0" cellspacing="0"
							class="memberTable">
							<tbody>
								<tr>
									<th width="130" height="50" align="right">手机号码：</th>
									<td><input type="text" name="telnum" value="" id="telnum"
										class="text left validate[required,custom[phone]]" maxlength="20" style="width:120px;"/>

									</td>
								</tr>
								<tr>
									<th height="50" align="right">手机验证码：<br/>
									</th>
									<td><input type="text" name="verCode" value="" id="verCode"
										class="text left validate[required]" maxlength="20" style="width:120px;"/>
										<span class="btn btn-link" id="get-vercode-btn">获取验证码</span>
									</td>
								</tr>
								<tr>
									<th align="right">&nbsp;</th>
									<td>
									<input type="submit" class="greenBT"
										style="font-size:14px; padding:3px 20px;" />
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