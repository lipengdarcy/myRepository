<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息-新增</title>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>

<script type="text/javascript">
	function saveedituser(){
		var userInfoForm = document.forms["userInfoForm"];
		var username=$("#username").val();
		var uid=$("#uid").val();
		var email=$("#Email").val();
		var mobile=$("#Mobile").val();
		var nickName=$("#NickName").val();
		var password=$("#Password").val();
		var confirmPassword=$("#ConfirmPassword").val();
		var userRid=$("#UserRid").val();
		var realName=$("#realName").val();
		var regionId = $("#area1").find("#lastName").val();//获取区域id
		var gender = getSelectedRadio(userInfoForm.elements["gender"]).value;
		var idCard=$("#IdCard").val();
		var bid=$("#Bday").val();
		var address=$("#address").val();
		var bio=$("#Bio").val();
		if(password==""){
			alert("密码不能为空！");
			return;
		}
		if(password!=confirmPassword){
			alert("两次密码不一致！");
			return;
		}
		$.ajax({
			url: 'usersave.do',// 跳转到 action    
			data:{    
					 username : username,
					 uid : uid,
					 email : email,
					 mobile : mobile,    
					 nickName : nickName,
					 password : password,
					 userRid : userRid,
					 realName : realName,
					 regionId : regionId,
					 gender : gender,
					 idCard : idCard,
					 bid : bid,
					 address : address,
					 bio : bio
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				}else{
					alert(data.content);
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}  
		});
	}
</script>

</head>
<body>
	<h2 class="contentTitle">新增用户</h2>
	<form action="${ctx }/admin/user/useredit.do?uid=${userinfo.uid}" name="userInfoForm">
		<div class="addTable" style="display: block;">
			<table width="100%">
				<tbody>
					<tr>
						<td width="86px" align="right">用户名：</td>
						<td>
							<input id="username" name="username" value="" type="text"/>
						</td>
					</tr>

					<tr>
						<td align="right">用户邮箱：</td>
						<td><input class="input" data-val="true"
							data-val-email="不是有效的邮箱" id="Email" name="Email" size="35"
							type="text" value="" /><span class="field-validation-valid"
							data-valmsg-for="Email" data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td align="right">手机号码：</td>
						<td><input class="input" id="Mobile" name="Mobile" size="35"
							type="text" value="" /><span class="field-validation-valid"
							data-valmsg-for="Mobile" data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td align="right">昵称：</td>
						<td><input class="input" data-val="true"
							data-val-length="名称长度不能大于10" data-val-length-max="10"
							id="NickName" name="NickName" size="35" type="text"
							value="" /><span class="field-validation-valid"
							data-valmsg-for="NickName" data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td align="right">密码：</td>
						<td><input class="input" data-val="true"
							data-val-length="密码长度必须大于3且小于33" data-val-length-max="32"
							data-val-length-min="4" id="Password" name="Password" size="35"
							type="password" /><span class="field-validation-valid"
							data-valmsg-for="Password" data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td align="right">确认密码：</td>
						<td><input class="input" data-val="true"
							data-val-equalto="密码必须相同" data-val-equalto-other="*.Password"
							id="ConfirmPassword" name="ConfirmPassword" size="35"
							type="password" /><span class="field-validation-valid"
							data-valmsg-for="ConfirmPassword" data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td align="right">用户等级：</td>
						<td><select data-val="true"
							data-val-number="字段 用户等级 必须是一个数字。" data-val-range="请选择正确的用户等级"
							data-val-range-max="2147483647" data-val-range-min="1"
							data-val-required="用户等级 字段是必需的。" id="UserRid" name="UserRid">
								<option value="0">选择会员等级</option>
								<option value="1">禁止访问</option>
								<option value="2">禁止购买</option>
								<option value="3">禁止参加活动</option>
								<option value="4">禁止评论</option>
								<option value="5">禁止咨询</option>
								<option value="6">游客</option>
								<option value="7">铜牌会员</option>
								<option value="8">银牌会员</option>
						</select> <span class="field-validation-valid" data-valmsg-for="UserRid"
							data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td width="86px" align="right">真实名称：</td>
						<td><input type="text" name="realName"
									value="" class="text" maxlength="15"
									style="width:200px"></input>
						</td>
					</tr>
					<tr>
						<td align="right">性别：</td>
						<td>
								<label class="radio"> 
											<input type="radio" name="gender" value="1" checked="checked" />
										男 </label> <label class="radio">
											<input type="radio" name="gender" value="2" />
										女 </label> <label class="radio">
											<input type="radio" name="gender" value="0" />
										未知 </label>
						</td>
					</tr>
					<tr>
						<td align="right">身份证：</td>
						<td><input class="input" id="IdCard" name="IdCard" size="35"
							type="text" value="" /><span class="field-validation-valid"
							data-valmsg-for="IdCard" data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td align="right">出生日期：</td>
						<td><input class="input" id="Bday" name="Bday"
							size="18" type="text" value="" /> <span
							class="field-validation-valid" data-valmsg-for="Bday"
							data-valmsg-replace="true">例：生日1992年7月10日，请填写19920710</span>
						</td>
					</tr>
					<tr>
						<td align="right">所在区域：</td>
						<td>
							<div id="area1" class="cellCon">
							<div seled-name-show=".pshow-name-ele" cid="" max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel" style="position: relative;">
								<div>
									<div show-hs-area-sel="" class="btn btn-success">请选择</div>
									<span class="pshow-name-ele"></span>
								</div>
								<div class="area-float-panel float-panel">
									<div class="panel-close-btn"><span class="glyphicon glyphicon-remove"></span></div>
									<div role="tabpanel" class="area-tabs">
										<ul class="nav nav-tabs" role="tablist"></ul>
										<div class="tab-content"></div>
									</div>
									<div hidden-inputs-div=""></div>
								</div>
							</div>
						</div>
					</tr>
					<tr>
						<td align="right">详细地址：</td>
						<td><input class="input" data-val="true"
							id="Address" name="Address" size="35" type="text" value="" /><span
							class="field-validation-valid" data-valmsg-for="Address"
							data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td align="right">简介：</td>
						<td><textarea cols="40" data-val="true" id="Bio"
								name="Bio" rows="6"></textarea><span
							class="field-validation-valid" data-valmsg-for="Bio"
							data-valmsg-replace="true"></span>
						</td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
						<td><button type="button" onclick="saveedituser();" >提交</button></td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	<div id="area_form_success_ctx" class="pageContent" style="display:none;padding-left:20px;">
		<div style="color:green;margin:20px 0;"><h3>添加成功！</h3></div>
	</div>
</body>

</html>