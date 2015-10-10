<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域价格-修改</title>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>
<script type="text/javascript">

$(function(){

	laydate({
		elem : '#planeffecttime',
		istime: true,
		format : 'YYYYMMDDhhmmss', // 分隔符可以任意定义，该例子表示只显示年月
		//max: laydate.now(),
		min: '1900-01-01 00:00:00', //最小日期
        max: '2099-12-31 23:59:59', //最大日期
		festival : true //显示节日
	});
});


	var saveEditForm = function() {
		var msg = validateForm();
		if (msg.length > 0) {
			alert(msg);
			return;
		}
		var $form = $("#area_form");
		var id = $form.find("#id").val();
		var regionId = $("#area1").find("#lastName").val();//获取区域id
		var regionsName = $("#area1").find(".pshow-name-ele").text();//获取区域名称
		var val = $.trim($form.find("#price").val());
		
		var effectnow = $('input:radio:checked').val();

		var planeffecttime = $.trim($form.find("#planeffecttime").val());
		$.ajax({
			url : url + 'regionprice/priceEdit.do',
			data : {
				id : id,
				regionId : regionId,
				regionsName : regionsName,
				price : val,
				effectnow:effectnow,
				planeffecttime:planeffecttime
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				} else {
					alert("异常！请重新尝试或者联系管理员！");
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}
		});

	}, validateForm = function() {
		var s = "";
		var $form = $("#area_form");
		var val = $.trim($form.find("#price").val());

		if (val == '') {
			s = "价格不能为空！\n";
		}
		return s;
	}, reloadForm = function() {
		location.reload();
	};
</script>
</head>
<body>
<h2 class="contentTitle">区域价格-修改</h2>
<div id="area_form_ctx" class="pageContent">
<form id="area_form" method="post" action=""
	class="pageForm required-validate">
<input type="hidden" id="id" value="${id }" /> <input type="hidden"
	id="product_id" value="${pid }" />
<div class="pageFormContent nowrap" layoutH="97">
<table>
<c:if test="${pid ne -1 }">
	<tr>
	<th>商品名称：</th>
	<td>${productName }</td>
	</tr>
</c:if>
<tr>
<th>区域：</th>
<td> 
<!-- 区域选择 --> <div id="area1" class="cellCon">
<div seled-name-show=".pshow-name-ele" cid="${cid }" min-layer="2"
	max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel"
	style="position: relative;">
<div>
<div show-hs-area-sel="" class="btn btn-success">请选择</div>
<span class="pshow-name-ele"></span>
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
</div> <!-- 区域选择end -->
</td>
</tr>

<tr>
<th>价格：</th>
<td><input id="price" type="text" value="${price}" /></td>
</tr>

<tr style="display: none;">
<td><input type="radio" name="effect" value="1" checked="checked" />立即生效</td>
</tr>

<tr style="display: none;">
<td> <input type="radio" name="effect" value="0" />将来生效
</td>
<td>生效时间：<input type="text" id="planeffecttime" /></td>
</tr>

<tr>
<td colspan="2"> <button type="button" onclick="saveEditForm();">提交</button>
</td>
</tr>
</table>
</div>
</form>

</div>

<div id="area_form_success_ctx" class="pageContent"
	style="display: none; padding-left: 20px;">
<div style="color: green; margin: 20px 0;">
<h3>修改成功！</h3>
</div>
</div>

</body>
</html>
