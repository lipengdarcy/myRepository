<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../../includes/homenew/header.jsp"%>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/user.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/date/need/laydate.css" />
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine.js"></script>	
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>

<title>申请为工厂用户-红狮水泥商城</title>

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/uploadify/uploadify.css" />
<script type="text/javascript"
	src="${ctx }/scripts/uploadify/jquery.uploadify.min.js"></script>


</head>

<body>

	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>

	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>申请为工厂用户</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof content-->
			<div class="user-content right">
				<h1>申请为工厂用户</h1>
				<div class="user-order">

					<!--04-->
					<div class="order-list03">
						<div class="order-cencel-box">

							<form id="form" method="post" action="" class="clearfix">

								<input type="hidden" name="uid" id="uid" value="${uid }" /> <input
									type="hidden" name="type" id="type" value="2" /> <input
									type="hidden" name="userType" id="userType"
									value="${userType }" />

								<fieldset class="clearfix">
									<legend>工厂名称：</legend>
									<input type="text" name="name" id="name" value=""
										class="text validate[required]" maxlength="15"
										style="width: 200px"></input><label style="color: red">*</label>
								</fieldset>

								<fieldset class="clearfix">
									<legend>工厂地址：</legend>
									<input type="text" name="address" id="address" value=""
										class="text validate[required]" maxlength="15"
										style="width: 200px"></input><label style="color: red">*</label>
								</fieldset>

								<fieldset class="clearfix">
									<legend>联系电话：</legend>
									<input type="text" name="tel" id="tel" value=""
										class="text validate[required,custom[mobilephone]]"
										maxlength="15" style="width: 200px"></input><label
										style="color: red">*</label>
								</fieldset>

								<fieldset class="clearfix">
									<legend>联系人：</legend>
									<input type="text" name="contacts" id="contacts" value=""
										class="text validate[required]" maxlength="15"
										style="width: 200px"></input><label style="color: red">*</label>
								</fieldset>

								<fieldset class="clearfix cencel-btn">
									<input type="submit" class="submit-btn" value="提交" /> <input
										type="reset" class="reset-btn" value="重置" />
								</fieldset>
							</form>

						</div>
					</div>
					<!--04-->
				</div>
			</div>
			<!--eof content-->
		</div>
	</div>
	<!--eof main-->

	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../../includes/homenew/footer.jsp"%>


	<script type="text/javascript">	
	$(function() {
		//检查后提交
		$("#form").validationEngine({
			onValidationComplete : function(form, status) {
				if (status) {
					apply();
				}
			}
		});
	});

	function apply() {		
		var $form = $("#form");		
		var postdata=$form.serialize();
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		$.ajax({
			url: url+'business/applyStoreUser.do',
			data:postdata,			
			type : 'post',
			dataType : 'json',
			success : function(data) {
				hsArtDialog.content(data.content).showModal();				
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}
		});
	}
	
</script>
</body>
</html>