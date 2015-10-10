<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<style>
.table-button {
	display: inline-blcok;
	color: #15428B;
	font-weight: bold;
	margin-left: 8px;
}
</style>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="orderField" value="${orderField}" /> <input
		type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post" id="search-form">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>关键词：</label> <input type="text" name="keywords"
					value="${keywords}" /></li>
			</ul>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx }/admin/category.do?method=toAdd"
				target="navTab"><span>添加</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<!-- th width="50" align="center" orderField="uid" class="${orderDirection}">主键ID</th-->
				<th>父id</th>
				<th>分类名称</th>
				<th width="420">价格区间</th>
				<th>显示顺序</th>
				<th>层级</th>
				<th>是否有子节点</th>
				<th>路径</th>
				<th align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${categoryList}" varStatus="status">
				<tr target="sid_user" rel="${props.cateid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${props.cateid}" type="checkbox"></td>
					<td>${props.parentid}</td>
					<td>${props.name}</td>
					<td>${props.pricerange}</td>
					<td>${props.displayorder}</td>
					<td>${props.layer}</td>
					<td><c:if test="${props.haschild=='0'}">否</c:if> <c:if
							test="${props.haschild=='1'}">是</c:if></td>
					<td>${props.path}</td>
					<td><a
						href="${ctx }/admin/category.do?method=attributegrouplist&cateid=${props.cateid}&catename=${props.name}"
						target="navTab" rel="pageedit" class="editOperate table-button"><span>属性分组</span></a>
						<a
						href="${ctx }/admin/category.do?method=attributelist&cateid=${props.cateid}&catename=${props.name}"
						target="navTab" rel="pageattredit"
						class="editOperate table-button"><span>属性列表</span></a> <a
						title="编辑" target="navTab"
						href="${ctx }/admin/category.do?method=toUpdate&cateid=${props.cateid }"
						class="table-button"><span>编辑</span></a> <%-- <a title="删除无法恢复"
						target="ajaxTodo"
						href="${ctx }/admin/category.do?method=delete&cateid=${props.cateid }"
						class="table-button"><span>删除</span></a> --%></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <span>${numPerPage}条/页，共<font color=red>${totalCount}</font>条
			</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>

	</div>
</div>
