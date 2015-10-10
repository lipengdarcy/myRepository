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
		
		var pid = $.trim($form.find("#pid").val());
		var title = $.trim($form.find("#title").val());
		var url = $.trim($form.find("#url").val());
		var displayorder = $.trim($form.find("#displayorder").val());
		var description = $.trim($form.find("#description").val());
	
		$.ajax({
			url: 'helps.do?method=helpsAdd',
			data:{
				pid : pid,
				title : title,
				url : url,
				description : description,
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
		var title = $.trim($form.find("#title").val());
		var displayorder = $.trim($form.find("#displayorder").val());
		var bodyVal = $.trim($form.find("#description").val());
		if(title == ''){
			s += "标题不能为空！\n";
		}
		if(displayorder == ''){
			s += "排序不能为空！\n";
		}
		if(bodyVal == ''){
			s += "内容不能为空！\n";
		}
		return s;
	};
</script>
<body>
<h2 class="contentTitle">帮助-修改</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<input type="hidden" id="helps_id_h" value="" />
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr>
					<th>帮助类型：</th>
					<td>
						<select id="pid" >
							<c:forEach var="t" items="${type }">
								<option value="${t.helpstypeid }">${t.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>帮助标题：</th>
					<td>
						<input id="title" type="text" value="" class="textInput" />
					</td>
				</tr>
				<tr>
					<th>排序：</th>
					<td>
						<input id="displayorder" type="text" value=""  class="textInput" />
					</td>
				</tr>
				<tr>
					<th>帮助链接：</th>
					<td>
						<input id="url" type="text" value=""  class="textInput" />
					</td>
				</tr>
				<tr>
					<th>帮助内容：</th>
					<td>
						<textarea id="description"  class="editor textInput"  upImgUrl="${ctx }/admin/uploadfile.do?method=xeupload"  upImgExt="jpg,jpeg,gif,png" rows="25" cols="100" ></textarea>
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

