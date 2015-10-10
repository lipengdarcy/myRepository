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
<link href="${ctx }/themes/default/css/home.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<script type="text/javascript">
	function editpassword(){
		var password = $.trim($("#oldpassword").val());
		var newpassword = $.trim($("#newpassword").val());
		var confirmpassword = $.trim($("#confirmpassword").val());
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		if(newpassword == ""){
			hsArtDialog.content("新密码不可为空！").showModal();
			return;
		}
		if(newpassword != confirmpassword){
			hsArtDialog.content("两次密码不一致！").showModal();
			return;
		}
		if(password == ""){
			hsArtDialog.content("请输入原密码！").showModal();
			return;
		}
		$.ajax({
			url: 'editpassword.do',// 跳转到 action    
			data:{
				password : password,
				newpassword : newpassword
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					hsArtDialog.content("修改成功！").showModal();
					window.location.href = url+"/ucenter/goeditover.do";
				}else if(data.state == 'error'){
					hsArtDialog.content("修改失败，原密码错误！").showModal();
				}else{
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}  
		});
	}
</script>
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
						<li class="hot"><s>1</s>修改信息</li>
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
									<th height="50" align="right">原密码：</th>
									<td><input type="password" name="oldpassword" value="" id="oldpassword"
										class="text left" maxlength="15" style="width:180px;"/>
									</td>
								</tr>
								<tr>
									<th height="50" align="right">新密码：<br/>
									</th>
									<td><input type="password" name="newpassword" value="" id="newpassword"
										class="text left" maxlength="15" style="width:180px;"/>
									</td>
								</tr>
								<tr>
									<th height="50" align="right">确认密码：<br/>
									</th>
									<td><input type="password" name="confirmpassword" value="" id="confirmpassword"
										class="text left" maxlength="15" style="width:180px;"/>
									</td>
								</tr>
								<tr>
									<th align="right">&nbsp;</th>
									<td><a href="javascript:editpassword()" class="greenBT"
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