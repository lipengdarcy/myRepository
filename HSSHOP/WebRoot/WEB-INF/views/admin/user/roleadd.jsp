<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>

<div class="pageContent">

	<form id="form" method="post" action="">
		<table class="table" width="100%">
			<tr align="left">
				<td align="center">角色名称</td>
				<td align="center"><input id="name" name="name" value=""
					type="text" /></td>
			</tr>

			<tr align="left">
				<td align="center">角色说明</td>
				<td align="center"><input id="memo" name="memo" value=""
					type="text" /></td>
			</tr>

			<tr>
				<td colspan="2">
					<button type="button" onclick="saveEditForm();">提交</button>
				</td>
			</tr>
			
		</table>
	</form>

</div>


<script type="text/javascript">
	var saveEditForm = function() {

		var sendData = $("#form").serialize();
	
		$.ajax({
			url : url + 'admin/user/roleadd.do',
			data : sendData,
			type : 'post',
			dataType : 'json',
			success : function(data) {
				//弹窗提示配置
				var hsArtDialog=dialog({
				  	title: '提示',
				  	id:"hs-dialog",
				  	fixed:true,
				  	width:300,
				  	height:100
				});				
				hsArtDialog.content(data.content).showModal();
			},
			error : function() {
				//弹窗提示配置
				var hsArtDialog=dialog({
				  	title: '提示',
				  	id:"hs-dialog",
				  	fixed:true,
				  	width:300,
				  	height:100
				});				
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}
		});

	};
</script>