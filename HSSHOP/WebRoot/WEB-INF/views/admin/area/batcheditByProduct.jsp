<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域价格批量修改</title>
<%@ include file="../../../includes/home/header.jsp"%>
<script type="text/javascript">
	var saveEditForm = function() {
		var msg = validateForm();
		if (msg.length > 0) {
			alert(msg);
			return;
		}
		var $form = $("#area_form");
		var productId = $form.find("#productId").val();
		var price = $.trim($form.find("#price").val());
		var RegionsId = $("#area1").find("#lastName").val();//获取区域id
		var regionsName = $("#area1").find(".pshow-name-ele").text();//获取区域名称

		$.ajax({
			url : url + 'admin/area.do?method=priceBatchEdit',// 跳转到 action    
			data : {
				productId : productId,
				RegionsId:RegionsId,
				price : price,
				type: 4
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					alert(data.message);
				} else {
					alert(data.message);
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}
		});

	}, validateForm = function() {
		var s = "";
		var $form = $("#area_form");
		var val1 = $.trim($form.find("#price").val());

		if (val1 == '') {
			s = "价格不能为空！\n";
		}
		return s;
	}, reloadForm = function() {
		location.reload();
	};
</script>
</head>
<body>
	<h2 class="contentTitle">同一产品不同区域 价格批量修改</h2>
	<div id="area_form_ctx" class="pageContent">
		<form id="area_form" method="post" action=""
			class="pageForm required-validate">
			<input type="hidden" id="productId"
				value="${productId }" />
			<div class="pageFormContent nowrap" layoutH="97">
				<table>
					<tr>
						<th>产品名称：</th>
						<td>${productName }</td>
					</tr>	
					
					<tr>
					<th>区域选择：</th>
					<td>
						<!-- 区域选择 -->
						<div id="area1" class="cellCon">
							<div seled-name-show=".pshow-name-ele" cid="${cid }" min-layer="2"
							max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel" style="position: relative;">
								<div>
									<div show-hs-area-sel="" class="btn btn-success">请选择</div>
									<span class="pshow-name-ele"></span>
									<input class="pshow-name-ele-hidden" type="hidden" name="regions_name" value="">
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
						<!-- 区域选择end -->
					</td>
				</tr>							

					<tr>
						<th>价格：</th>
						<td><input id="price" type="text" value="${price }" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" onclick="saveEditForm();">提交</button>
						</td>
					</tr>
				</table>
			</div>
		</form>

	</div>


</body>
</html>
