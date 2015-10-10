<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域品牌产地-新增</title>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>
<script type="text/javascript">
	//HsAreaUser.lastItemClickCallbackFun = "changeArea";

	$(function() {
		//var regionsId = 214785;//默认兰溪市
		//changeArea(regionsId);
	});

	var saveForm = function() {
		var msg = validateForm();
		if (msg.length > 0) {
			alert(msg);
			return;
		}
		var $form = $("#area_form");
		//var regionId = $("#area1").find("#lastName").val();//获取区域id
		//var regionsName = $("#area1").find(".pshow-name-ele").text();//获取区域名称
		var brandId = $form.find("#brand").val();
		var placeId = $form.find("#place").val();
		var id=$("#prikey").val();
		$.ajax({
			url : url + 'admin/area.do?method=placeEdit',// 跳转到 action    
			data : {
				//regions_id : regionId,
				// regions_name : regionsName,
				id:id,
				brand_id : brandId,
				place_id : placeId
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				} else if (data.state == 'exists') {
					alert("该区域品牌关系已存在，请不要重复添加！");
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
		var brandId = $form.find("#brand").val();
		if (brandId == null || brandId.length == 0) {
			s += "请先在【区域品牌】中添加关系！\n";
		}
		return s;
	}, reloadForm = function() {
		location.reload();
	};

	var changeArea = function(itemId) {
		if (!itemId)
			return;
		var $form = $("#area_form");
		var $brand = $form.find("#brand");
		$brand.empty();
		$.ajax({
			url : url + 'admin/area.do?method=areaBrand',
			data : {
				regions_id : itemId
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				var list = data.brandList;
				if (list && list.length > 0) {
					$.each(list, function(i, n) {
						$brand.append("<option value=\""+n.brandId+"\" >"
								+ n.brandName + "</option>");
					});
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}
		});
	};
</script>
</head>
<body>
	<h2 class="contentTitle">区域品牌产地-编辑</h2>
	<div id="area_form_ctx" class="pageContent">
		<form id="area_form" method="post" action=""
			class="pageForm required-validate">
			<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="id" id="prikey" value="${brandplace.id }"></input>
				<table>

					<tr>
						<th>品牌：</th>
						<td><select id="brand">
								<c:forEach var="s" items="${brandList }" varStatus="status">
									<option value="${s.attrvalueid }"
									<c:if test="${brandplace.brandId==s.attrvalueid }">selected="selected"</c:if>
									>${s.attrvalue}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<th>产地：</th>
						<td><select id="place">
								<c:forEach var="s" items="${placeList }" varStatus="status">
									<option value="${s.attrvalueid }"
									<c:if test="${brandplace.placeId==s.attrvalueid }">selected="selected"</c:if>
									>${s.attrvalue}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td colspan="2">
							<button type="button" onclick="saveForm();">提交</button>
						</td>
					</tr>
				</table>
			</div>
		</form>

	</div>

	<div id="area_form_success_ctx" class="pageContent"
		style="display: none; padding-left: 20px;">
		<div style="color: green; margin: 20px 0;">
			<h3>添加成功！</h3>
		</div>
		<button type="button" onclick="reloadForm();">继续添加</button>
	</div>

</body>
</html>
