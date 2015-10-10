<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/includes/home/header.jsp"%>

<div class="pageContent">

	<form id="form_edit" method="post" action="">
		<input id="id" name="id" value="${role.id}" type="hidden" />
		<table class="table" width="100%">
			<tr align="left">
				<td align="center">角色名称</td>
				<td align="center"><input id="name" name="name"
					value="${role.name}" type="text" /></td>
			</tr>

			<tr align="left">
				<td align="center">角色说明</td>
				<td align="center"><input id="memo" name="memo"
					value="${role.memo}" type="text" /></td>
			</tr>
		</table>

		<table class="table" width="100%">
			<thead>
				<tr>
					<th width="22"><input id="checkAll" type="checkbox" /></th>
					<th align="center">权限id</th>
					<th align="center">上级权限id</th>
					<th align="center">权限名称</th>
					<th align="center">权限菜单</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="r" items="${SysResourceList}" varStatus="status">
					<tr id="${r.id}">
						<td><input type="checkbox" name="resourceList"
							value="${r.id}" /></td>
						<td align="center">${r.id}</td>
						<td align="center">${r.pid}</td>
						<td align="center">${r.name}</td>
						<td align="center">${r.resourcestr}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<button type="button" onclick="saveEditForm();">提交</button>
	</form>

</div>


<script type="text/javascript">
	$(document).ready(
			function() {
				//全选
				$("#checkAll").click(
						function() {
							$('input[name="resourceList"]').prop("checked",
									$(this).is(':checked'));
						});
				//角色权限初始化
				$("input[name='resourceList']").each(function() {
					var list = '${SysRoleResourceList}';
					for (var i = 0; i < list.length; i++) {
						if (list[i].resourceid == $(this).attr('value'))
							$(this).attr("checked", "checked");
					}
				});
			});
	var saveEditForm = function() {
		//弹窗提示配置
		var hsArtDialog = dialog({
			title : '提示',
			id : "hs-dialog",
			fixed : true,
			width : 300,
			height : 100
		});
		var sendData = $("#form_edit").serialize();
		$.ajax({
			url : url + 'admin/user/roleedit.do',
			data : sendData,
			type : 'post',
			dataType : 'json',
			success : function(data) {
				hsArtDialog.content(data.content).showModal();
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}
		});

	};
</script>