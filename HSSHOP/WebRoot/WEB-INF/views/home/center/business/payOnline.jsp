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
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/default/css/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/date/need/laydate.css" />
<script type="text/javascript"
	src="${ctx }/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>

<title>在线交款-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>

	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>在线收款</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%-- <c:import url="${ctx }/ucenter.do?userType=${userType}&navId=2" /> --%>
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->

			<!--bof content-->
			<div class="user-content right">
				<h1>在线交款</h1>
				<!--04-->
				<div class="order-list03 online-pay-select">
					<div class="order-tab">收款信息</div>
					<div class="order-cencel-box order-collection-box">
						<form id="form" action="" class="clearfix">
							<fieldset class="clearfix">
								<legend>销售方：</legend>
								<select id="nccompany" name="nccompany" class="validate[required]">
									<option value=" ">请选择</option>
									<option value="-1">红狮销售公司</option>									
									<c:forEach items="${compantList}" var="p" varStatus="vs">
										<option value="${p.id}">${p.name}</option>
									</c:forEach>
								</select><label class="red">*</label>
							</fieldset>
							
							<fieldset class="clearfix">
								<legend>收款方式：</legend>
								<select id="paytype" name="paytype" class="validate[required]">
									<option value=" ">请选择</option>
									<option value="2">贷款</option>
									<option value="3">保证金</option>
								</select><label class="red">*</label>
							</fieldset>
							<fieldset class="clearfix">
								<legend>收款金额(元)：</legend>
								<input type="text" name="payamount" value="" placeholder="请输入金额"
									class="validate[custom[number]] validate[required]" /><label
									class="red">*</label>
							</fieldset>
							<fieldset class="clearfix collection-note">
								<legend>收款说明：</legend>
								<textarea name="description" rows="3" cols="20"></textarea>
							</fieldset>
							<fieldset class="clearfix cencel-btn clear">
								<input type="submit" class="submit-btn" value="提交" /> <input
									type="reset" class="reset-btn" value="重置" />
							</fieldset>
						</form>
					</div>
				</div>
				<!--04-->
			</div>
			<!--bof content-->
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
						gotoSelectPage();
					}
				}
			});
		});

		function gotoSelectPage() {
			var $form = $("#form");
			var postdata = $form.serialize();
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url : 'payOnlinePagePost.do',// 跳转到 action    
				data : postdata,
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						hsArtDialog.content(data.errorMsg).showModal();
						window.location.href = 'payOnlineSelect.do?payorderid='
								+ data.result;
					} else {
						hsArtDialog.content(data.errorMsg).showModal();
					}
				},
				error : function() {
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			});
		}
	</script>
</body>
</html>