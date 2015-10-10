<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域产地-新增</title>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>
<script type="text/javascript">
			
	var saveForm= function(){
		var msg = validateForm();
		if(msg.length > 0){
			hsArtDialog.content(msg).showModal();
			return ;
		}
		var $form = $("#area_form");
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:100
		});
		
		var brandId = $form.find("#brand").val();
		var placeId = $form.find("#place").val();
		$.ajax({
			url: url+'regionfactory/factoryAdd.do',// 跳转到 action    
			data:{    
					 brand_id : brandId,
					 place_id : placeId
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					// alert("新增成功，删除条目：" + data.deleteCount);
					//alert("新增成功，影响条目：" + data.count);
					hsArtDialog.content("新增成功！").showModal();
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				}else if(data.state == 'exists'){
					hsArtDialog.content("该区域品牌关系已存在，请不要重复添加！").showModal();
				}else{
					hsArtDialog.content("该品牌未在各个区域设置，请先设置区域品牌").showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}    
		});

	}, validateForm = function(){
		var s = "";
		var $form = $("#area_form");
		var brandId = $form.find("#brand").val();
		if(brandId == null || brandId.length == 0){
			s += "请先在【区域品牌】中添加关系！\n";
		}
		return s;
	}, reloadForm = function(){
		location.reload();
	};
	
</script>
</head>
<body>
<h2 class="contentTitle">品牌产地-添加</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				
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
					<th>产地：</th>
					<td>
						<select id="place" >
							<c:forEach var="s" items="${placeList }" varStatus="status">
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
