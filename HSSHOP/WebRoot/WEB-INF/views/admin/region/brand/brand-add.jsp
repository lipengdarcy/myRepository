<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域品牌-新增</title>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>
<script type="text/javascript">
	var saveForm= function(){
		var msg = validateForm();
		if(msg.length > 0){
			alert(msg);
			return ;
		}
		var $form = $("#area_form");
		var regionId = $("#area1").find("#lastName").val();//获取区域id
		var regionsName = $("#area1").find(".pshow-name-ele").text();//获取区域名称
		var brandId = $form.find("#brand").val();
		$.ajax({
			url: url+'regionbrand/brandAdd.do',// 跳转到 action    
			data:{    
					 regions_id : regionId,
					 regions_name : regionsName,
					 brand_id : brandId
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				}else if(data.state == 'exists'){
					alert("该区域品牌关系已存在，请不要重复添加！");
				}else{
					alert("异常！请重新尝试或者联系管理员！");
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}    
		});  

	}, validateForm = function(){
		var s = "";
		var $form = $("#area_form");
		return s;
	}, reloadForm = function(){
		location.reload();
	};
</script>
</head>
<body>
<h2 class="contentTitle">区域品牌-添加</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr>
					<th>区域：</th>
					<td>
						<!-- 区域选择 -->
						<div id="area1" class="cellCon">
							<div seled-name-show=".pshow-name-ele" cid="192224-212131-214785-0-0" min-layer="2"
							max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel" style="position: relative;">
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
						<!-- 区域选择end -->
					</td>
				</tr>
				
				<tr>
					<th>品牌：</th>
					<td>
						<select id="brand" >
							<c:forEach var="s" items="${brandList }" varStatus="status">
							<option value="${s.attrvalueid }" >${s.attrvalue}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" >
						<button type="button" onclick="saveForm();" >提交</button>
					</td>
				</tr>
			</table>
		</div>
	</form>

</div>

<div id="area_form_success_ctx" class="pageContent" style="display:none;padding-left:20px;">
	<div style="color:green;margin:20px 0;"><h3>添加成功！</h3></div>
	<button type="button" onclick="reloadForm();" >继续添加</button>
</div>

</body>
</html>
