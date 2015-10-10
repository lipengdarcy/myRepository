<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域装卸费-新增</title>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>
<script type="text/javascript">

	$(document).ready(function(e){
		$("#price-table-row-tmp .del-row-btn").bind("click",function(e){
			var src=e.target;
			var srcRow=$(src).closest("tr");
			srcRow.remove();
		});
		
		$(".add-price-table-row").bind("click",function(e){
			var tmpClone=$("#price-table-row-tmp tr").clone(true);
			var priceTable=$(".price-table");
			priceTable.append(tmpClone);
		});
	});
	var saveForm= function(){
		var $form = $("#area_form");
		
		$("#def-uint-names").empty();
		var defUnits=$(".def-unit-list-td").find("[type='checkbox']");
		for(var di=0;di<defUnits.length;di++){
			var defUnit=defUnits[di];
			var unitName=$(defUnit).closest("label").find(".def-unit-name").html();
			$("#def-uint-names").append("<input type='hidden' name='defUnitList["+di+"].unitname' value='"+unitName+"'/>")
		}
		
		var regionId = $("#area1").find("#lastName").val();//获取区域id
		$form.find("#region_id").val(regionId);
		var regionsName = $("#area1").find(".pshow-name-ele").text();//获取区域名称
		$form.find("#region_name").val(regionsName);
		
		var priceTrs=$form.find(".price-table .price-table-row");
		for(var pi=0;pi<priceTrs.length;pi++){
			var priceTr=priceTrs[pi];
			var nameEles=$(priceTr).find("input[name]");
			for(var ni=0;ni<nameEles.length;ni++){
				var nameEle=nameEles[ni];
				if(pi==0){
					if($.trim(nameEle.value)==""){
						alert("你必须添加最起运最小值及其起运价格");
						return;
					}
				}else{
					if($.trim(nameEle.value)==""){
						$(nameEle).closest("tr").remove();
						continue;
					}
				}
				var name=nameEle.name;
				name=name.replace("[n]","["+pi+"]");
				nameEle.name=name;
			}
		}
		
		var postData=$form.serialize();
		$.ajax({
			url: url+'admin/area.do?method=handAdd',// 跳转到 action    
			data:postData,
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				}else{
					alert("异常！请重新尝试或者联系管理员！");
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}    
		});  

	}, reloadForm = function(){
		location.reload();
	};
</script>
</head>
<body>
<h2 class="contentTitle">区域装卸费-添加</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<input type="hidden" id="product_id" name="pid" value="${product.pid }" />
		<input type="hidden" id="region_id" name="regionId" value="" />
		<input type="hidden" id="region_name" name="regionName" value="" />
		<div style="display-none" id="def-uint-names"></div>
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<c:if test="${proRegion.productid>0}">
				<tr>
					<th>商品名称：</th>
					<td>${product.name }</td>
				</tr>
				</c:if>
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
					<th>默认计量单位:&nbsp;</th>
					<td  class="def-unit-list-td">
						<c:forEach var="unitItems" items="${unitlist }" varStatus="status">
							&nbsp;&nbsp;<label><input type="checkbox" name="defUnitList[${status.index }].unitid" value="${unitItems.unitid }"/><span class="def-unit-name">${unitItems.unitname }</span></label>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>价格区间:</th>
					<td>
						<table class="price-table">
							<tr class="price-table-head">
								<td>&nbsp;</td><td>购买数量</td><td>&nbsp;</td><td>运输费用</td>
								<td>&nbsp;</td><td>&nbsp;</td>
							</tr>
							<tr class="price-table-row">
								<td class="right-td">最小够买量:</td><td><input type='text' name="priceList[n].buyminquan" value=""></td><td>包及以上</td>
								<td><input type='text' name="priceList[n].price" value=""></td><td>元/包</td><td><span class="space-span">&nbsp;</span></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td>&nbsp</td><td><span class="table-button add-price-table-row">+增加价格区间</span></td></tr>
				
				<tr>
					<td colspan="2" class="right-td">
						<span class="btn btn-default" onclick="saveForm();">提交</span>
					</td>
				</tr>
			</table>
		</div>
	</form>

</div>
<table id="price-table-row-tmp" style="display:none;">
	<tr class="price-table-row">
		<td class="right-td">购买:</td>
		<td><input type='text' name="priceList[n].buyminquan" value=""></td><td>包及以上</td>
		<td><input type='text' name="priceList[n].price" value=""></td><td>元/包</td>
		<td class="center-td"><span class="table-button space-span del-row-btn">删除</span></td>
	</tr>
</table>
<div id="area_form_success_ctx" class="pageContent" style="display:none;padding-left:20px;">
	<div style="color:green;margin:20px 0;"><h3>添加成功！</h3></div>
	<button type="button" onclick="reloadForm();" >继续添加</button>
</div>

</body>
</html>
