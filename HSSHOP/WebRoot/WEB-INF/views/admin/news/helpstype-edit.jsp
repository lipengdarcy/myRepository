<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>

<script type="text/javascript">
	var saveForm= function(){
		var msg = validateForm();
		if(msg.length > 0){
			alert(msg);
			return ;
		}
		var $form = $("#area_form");
		
		var id = $form.find("#helps_id_h").val();
		var name = $.trim($form.find("#title").val());
		var displayorder = $.trim($form.find("#displayorder").val());
	
		$.ajax({
			url: 'helps.do?method=helpstypeEdit',
			data:{
				id : id,
				name : name,
				displayorder : displayorder,
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
		var name = $.trim($form.find("#title").val());
		var displayorder = $.trim($form.find("#displayorder").val());
		if(name == ''){
			s += "类型名不能为空！\n";
		}
		if(displayorder == ''){
			s += "排序不能为空！\n";
		}
		return s;
	};
</script>
<body>
<h2 class="contentTitle">帮助类型-修改</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<input type="hidden" id="helps_id_h" value="${type.helpstypeid }" />
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr>
					<th>帮助类型：</th>
					<td>
						<input id="title" type="text" value="${type.name }" class="textInput" />
					</td>
				</tr>
				<tr>
					<th>排序：</th>
					<td>
						<input id="displayorder" type="text" value="${type.displayorder }"  class="textInput" />
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
	<div style="color:green;margin:20px 0;"><h3>修改成功！</h3></div>
</div>

