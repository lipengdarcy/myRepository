<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/homenew/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/user.css"/>	
<script type="text/javascript">
	uid = 1;
	isGuestSC = 1;
	scSubmitType = 0;
</script>
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/date/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/jquery-ui.css" />
<script src="${ctx }/scripts/region.js" type="text/javascript"></script>
<script src="${ctx }/scripts/ucenter.user.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-ui.js"></script>
<script type="text/javascript">

	$(function(){
		$("#userInfoForm").validationEngine({
			onValidationComplete: function(form, status){
			      if(status){
			    	  editUser() 
			      }
			    }
				});
	/* 	$( "#bday" ).datetimepicker({ 
		      changeYear: true, 
		      regional:"zh-CN", 
		      dateFormat:"yy-mm-dd"
		});  */
		laydate({
			elem : '#bday',			
			format : 'YYYY-MM-DD',// 分隔符可以任意定义，该例子表示只显示年月
			max: laydate.now(),
			festival : true //显示节日
		});
	});
		 
		 
	function editUser() {
		var userInfoForm = document.forms["userInfoForm"];

		var userName = userInfoForm.elements["userName"] ? userInfoForm.elements["userName"].value
				: "";
		var nickName = userInfoForm.elements["nickName"].value;
		var realName = userInfoForm.elements["realName"].value;
		var avatar = userInfoForm.elements["avatar"] ? userInfoForm.elements["avatar"].value
				: "";
		var gender = getSelectedRadio(userInfoForm.elements["gender"]).value;
		var idCard = userInfoForm.elements["idCard"].value;
		var email = userInfoForm.elements["email"].value;
		var mobile = userInfoForm.elements["mobile"].value;
		var bday = userInfoForm.elements["bday"].value;
		var regionId = $("#area1").find("[name=\"lastName\"]").val();
		var address = userInfoForm.elements["address"].value;
		var bio = userInfoForm.elements["bio"].value;
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		if(regionId==""){
			hsArtDialog.content("请选择地区！！").showModal();
			return;
		}
		if (!verifyEditUser(userName, nickName, realName, address, bio)) {
			return;
		}
		$.ajax({
			url : 'edituser.do',// 跳转到 action    
			data : {
				userName : userName,
				nickName : nickName,
				realName : realName,
				avatar : avatar,
				gender : gender,
				idCard : idCard,
				email : email,
				mobile : mobile,
				bday : bday,
				regionId : regionId,
				address : address,
				bio : bio
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					hsArtDialog.content(data.content).showModal();
				}else {
					hsArtDialog.content(data.content).showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}
		});
	}
	$(document).ready(function(e){
		$("#mobile-num-input").bind("change",function(e){
			var src=e.target;
			var srcVal=src.value;
			var parTd=$(src).closest("td");
			var linkBtn=parTd.find("[telnum]");
			linkBtn.attr("telnum",srcVal);
		});
		$(".bind-telnum").bind("click",function(e){
			var src=e.target;
			var parLink=$(src).closest("a");
			var linkBtn=parLink.find("[telnum]");
			var telNum=linkBtn.attr("telnum");
			var oh=$(parLink).attr("href");
			$(parLink).attr("href",oh+"&telnum="+telNum);
		});
	})
</script>
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<style>
	.input-like{
	  display: inline-block;
	  float: left;
	  width: 290px;
	  height: 30px;
	  border: 1px solid #e3e3e3;
	  padding-left: 10px;
	  font-size: 14px;
	  float: left;
	  margin-right: 12px;
	  color: #999;
	}
	.input-like.data-put03{
		width: 207px;
	}
	.sales-data input.pack-date{
		width: 270px;
	}
</style>
</head>

<body>

	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>
	
	<div class="user-main">
  		<div class="wrap clearfix">
  			<div class="crumbs">
  				<strong>| <a href="seller_data.html">个人中心首页</a> </strong>&gt; 
  				<a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>安全中心</span>
  			</div>
  			
  			<div class="user-menu left"> 
  				<%@ include file="centermenu.jsp"%>
  			</div>
  			
  			<div class="user-content right">
  				<h1>安全中心</h1>
			    <div class="sales-data user-data">
			    	<form id="userInfoForm" name="userInfoForm" action="">
			    		 <fieldset class="clearfix">
				            <legend>用户名：</legend>
				            <label>${userinfo.username }</label>
				         </fieldset>
				         <fieldset class="clearfix">
				            <legend>真实姓名：</legend>
				            <input type="text" name="realName" id="realName"
									value="${userdetails.realname }"  placeholder="" />
				            <label class="red">*</label>
				         </fieldset>
				          <fieldset class="clearfix">
				            <legend>性别：</legend>
				              <label>
				                <input type="radio" name="gender" value="0" id="xb_0" 
				                <c:if test="${userdetails.gender==0 }">checked="checked"</c:if> />
				                保密</label>
				              <label>
				                <input type="radio" name="gender" value="1" id="xb_1" 
				                <c:if test="${userdetails.gender==1 }">checked="checked"</c:if> />
				                男</label>
				              <label>
				                <input type="radio" name="gender" value="2" id="xb_2" 
				                <c:if test="${userdetails.gender==2 }">checked="checked"</c:if> />
				                女</label>
				          </fieldset>
				          <fieldset class="clearfix">
				            <legend>手机号码：</legend>
				            <c:if test="${userinfo.verifymobile==0}">
								<input type="text" name="mobile" id="mobile-num-input"
									value="${userinfo.mobile}" class="data-put03 validate[custom[phone]]" 
									placeholder="请输入您的手机号码"></input>
								<a href="${ctx }/ucenter/safeverify.do?act=bindMobile" class="bind-telnum">
									<span telnum="${userinfo.mobile}">绑定手机号</span>
								</a>
							</c:if>
							<c:if test="${userinfo.verifymobile==1}">
								<input type="hidden" name="mobile" id="mobile-num-input"
									value="${userinfo.mobile}"></input>
								<span class="data-put03 input-like">${userinfo.mobile}</span>
								<a href="${ctx }/ucenter/safeverify.do?act=updateMobile" class="bind-telnum">
									<span telnum="${userinfo.mobile}">修改手机号</span>
								</a>
							</c:if>				            
				          </fieldset>
				          <fieldset class="clearfix" style="display:none">
				            <legend>昵称：</legend>
				            <input type="text" name="nickName" id="nickName"
									value="${userinfo.nickname }" placeholder="请输入你的身份证号" />
				          </fieldset>				          
				          <fieldset class="clearfix">
				            <legend>身份证号：</legend>
				            <input type="text" name="idCard" value="${userdetails.idcard }" placeholder="请输入你的身份证号" />
				          </fieldset>
				          <fieldset class="clearfix">
				            <legend>出生日期：</legend>
				            <input type="text" name="bday" id="bday"  readOnly="readonly" 
								value="${formate.format(userdetails.bday) }"
								class="laydate-icon pack-date validate[required,custom[date]]"  
								placeholder="请选择您的出生日期" />
				          </fieldset>
				          <fieldset class="clearfix">
				            <legend>电子邮箱：</legend>
				            <input type="text" name="email" value="${userinfo.email }" 
									class="validate[custom[email]]"  placeholder="请输入您的电子邮箱" />
				          </fieldset>
				          <fieldset class="clearfix">
				            <legend>所在地区：</legend>
				            <div id="area1" class="cellCon">
								<div seled-name-show=".pshow-name-ele" cid="${cid }"
									max-layer="5" input-sel="[hidden-inputs-div]"
									role="hs-area-sel" style="position: relative;">
									<div>										
										<span class="pshow-name-ele"></span>
										<a show-hs-area-sel="" >请选择</a>
									</div>
									<div class="area-float-panel float-panel">
										<div class="panel-close-btn">
											<span class="glyphicon glyphicon-remove"></span>
										</div>
										<div role="tabpanel" class="area-tabs">
											<ul class="nav nav-tabs" role="tablist"></ul>
											<div class="tab-content"></div>
										</div>
										<div hidden-inputs-div=""></div>
									</div>
								</div>
							</div>				            
				          </fieldset>
				          <fieldset class="clearfix">
				            <legend>详细地址：</legend>
				            <input type="text" name="address"
									value="${userdetails.address }" placeholder="请输入详细地址" />
				            <label class="red">*</label>
				          </fieldset>
				          <fieldset class="clearfix">
				            <legend>个人介绍：</legend>
				            <textarea name="bio" class="text" placeholder="请输入您的简介">${userdetails.bio }</textarea>
				          </fieldset>
				           <fieldset class="clearfix btn" >
				            <input type="submit"  value="提交" onclick="jQuery('#userInfoForm').submit();" class="submit-btn"/>
				            <input type="reset"  value="重置" class="reset-btn"/>
				          </fieldset>
			    	</form>
			    </div>
  			</div>
  		</div>
  	</div>
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<%@ include file="../../../includes/homenew/footer.jsp"%>
</body>
</html>