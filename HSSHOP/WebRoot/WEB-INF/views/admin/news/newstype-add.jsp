<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻类型-新增</title>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>
<script type="text/javascript">
	var saveForm= function(){
		var msg = validateForm();
		if(msg.length > 0){
			alert(msg);
			return ;
		}
		var $form = $("#area_form");
		var name = $.trim($form.find("#name").val());
		var order = $.trim($form.find("#order").val());
		$.ajax({
			url: url+'admin/newsMng.do?method=newstypeAdd',
			data:{    
					 name : name,
					 order : order
			},
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

	}, validateForm = function(){
		var s = "";
		var $form = $("#area_form");
		var name = $.trim($form.find("#name").val());
		var order = $.trim($form.find("#order").val());
		if(name == ''){
			s += "名称不能为空！\n";
		}
		if(order == ''){
			s += "排序不能为空！\n";
		}
		return s;
	}, reloadForm = function(){
		location.reload();
	};
</script>
</head>
<body>
<h2 class="contentTitle">新闻类型-添加</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr>
					<th>新闻类型名称：</th>
					<td>
						<input id="name" type="text" value="" />
					</td>
				</tr>
				<tr>
					<th>排序：</th>
					<td>
						<input id="order" type="text" value="0" />
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
