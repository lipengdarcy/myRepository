<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${pageNum }" /> 
	 <input type="hidden" name="numPerPage" value="${numPerPage}" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="orderField" value="${orderField}" /> 
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<style>
.grid .gridTbody .sub-table-tr td div {
  display: block;
  overflow: auto;
  height: 150px;
  white-space: nowrap;
}
</style>
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
			<li><a class="add" href="${ctx }/admin/adminproduct.do?method=toAddSKU&pageid=page11"
				target="navTab"><span>添加SKU</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<!-- th width="50" align="center" orderField="uid" class="${orderDirection}">主键ID</th-->
				<th width="22">编号</th>
				<th>名称</th>
				<th>产品类别</th>				
				<th>备注</th>
				<th width="180" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${actionlist}" varStatus="status">
				<tr target="sid_user" rel="${props.skugid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${props.skugid}" type="checkbox">
					</td>
					<td>${props.skugid}</td>
					<td>${props.skugname}</td>
					<td>${props.cateName}</td>
					<td>${props.skugremarks}</td>
					<td>
						<a title="编辑" target="navTab" href="${ctx }/admin/adminproduct.do?method=toUpdateSkugroup&skugid=${props.skugid }&pageid=page14"
						class="table-button">编辑</a>
						 <a target="navTab" href="${ctx }/admin/adminproduct.do?method=skugProList&skugid=${props.skugid }" 
						class="table-button">相关产品</a>
						<a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/adminproduct.do?method=delSkugroup&skugid=${props.skugid }&pageid=page14"
						class="table-button">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> 
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" <c:if test="${numPerPage=='20'}">selected = "selected"</c:if>>20</option>
				<option value="30" <c:if test="${numPerPage=='30'}">selected = "selected"</c:if>>30</option>
				<option value="50" <c:if test="${numPerPage=='50'}">selected = "selected"</c:if>>50</option>
				<option value="100" <c:if test="${numPerPage=='100'}">selected = "selected"</c:if>>100</option>
			</select>
			<span>
				条/页，共<font color=red>${totalCount}</font>条
			</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>

	</div>
</div>
