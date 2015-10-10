<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<div class="pageContent">
	
<a class="add" href="${ctx }/admin/user/roleaddPage.do" target="navTab" external="true" ><span>添加</span></a>

	<table class="table" width="100%">
		<thead>
			<tr>
				<th align="center">角色id</th>
				<th align="left">角色名称</th>
				<th align="left">角色说明</th>
				<th align="left">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty rolelist}">
					<c:forEach var="r" items="${rolelist}" varStatus="status">
						<tr id="${r.id}">
							<td align="center">${r.id}</td>
							<td align="center">${r.name}</td>
							<td align="center">${r.memo}</td>
							<td width="120" align="center"><a title="编辑" target="navTab"
								href="${ctx }/admin/user/roleeditPage.do?id=${r.id}"
								external="true" class="btnEdit">编辑</a> <a title="删除无法恢复"
								href="javascript:Delete(${r.id})" class="btnDel">删除</a></td>

						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="100" class="center">没有相应记录</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

</div>


<script type="text/javascript">

	function Delete(id) {
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:100
			});
			$.ajax({
					url : url + 'admin/user/roledelete.do',
					data : {
						id : id,
					},
					type : 'post',
					dataType : 'json',
					success : function(data) {
						hsArtDialog.content(data.content).showModal();
					},
					error : function() {
						hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
					}
				});
	}
</script>