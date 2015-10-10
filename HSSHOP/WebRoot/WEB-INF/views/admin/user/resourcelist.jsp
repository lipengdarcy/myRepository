<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<link href="${ctx }/themes/tree_themes/SimpleTree.css" rel="stylesheet"
	type="text/css" media="screen" />
<script src="${ctx }/scripts/SimpleTree.js" type="text/javascript"></script>


<script type="text/javascript">
$(function(){
    $(".st_tree").SimpleTree({
        click:function(a){
            if(!$(a).attr("hasChild"))
                alert($(a).attr("ref"));
        }
    });
});
</script>


<div class="pageContent">
	<table class="table" width="100%">
		<thead>
			<tr>
				<th align="center">权限id</th>
				<th align="left">上级权限id</th>
				<th align="left">权限名称</th>
				<th align="left">权限菜单</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty resourcelist}">
					<c:forEach var="r" items="${resourcelist}" varStatus="status">
						<tr id="${r.id}">
							<td align="center">${r.id}</td>
							<td align="center">${r.pid}</td>
							<td align="center">${r.name}</td>
							<td align="center">${r.resourcestr}</td>
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