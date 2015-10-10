<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/area/brand.do">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${numPerPage }" /> 	
</form>
<script type="text/javascript">
	var removeProductRegions = function(id){
		$("#tr_"+id).remove();
	};
</script>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="${ctx }/admin/area.do?method=toBrandAdd" target="navTab" external="true" title="添加" ><span>区域品牌-添加</span> </a>
			</li>
			<li>
				<a class="icon" rel="page45" target="navTab" href="${ctx }/admin/area/brand.do" target="navTab" title="区域品牌" ><span>刷新</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">编号</th>
				<th >区域</th>
				<th >品牌</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.id }" >
					<td width="50" align="center">${s.id }</td>
					<td>${s.regionsName }</td>
					<td>${s.attrvalue }</td>
					<td width="120" align="center">
						<a title="编辑" target="navTab"
						href="${ctx }/admin/area.do?method=toBrandEdit&id=${s.id }"
						external="true" class="btnEdit">编辑</a>
						<a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/area.do?method=brandDelete&id=${s.id }&prid=${s.productregionsid }" class="btnDel" callback="removeProductRegions(${s.id})" >删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>
