<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
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
			<li><a class="add" href="${ctx }/admin/adminuser.do?method=toAdd"
				target="navTab"><span>添加</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<!-- th width="50" align="center" orderField="uid" class="${orderDirection}">主键ID</th-->
				<th>用户名</th>
				<th>姓名</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${actionlist}" varStatus="status">
				<tr target="sid_user" rel="${props.uid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${props.uid}" type="checkbox">
					</td>
					<td>${props.username}</td>
					<td>${props.realname}</td>
					<td>
						<a title="编辑" target="navTab" href="${ctx }/admin/adminuser.do?method=toUpdate&uid=${props.uid }"
						class="btnEdit">编辑</a>
						 <a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/adminuser.do?method=delete&uid=${props.uid }"
						class="btnDel">删除</a>
						<a title="修改密码" target="navTab" href="${ctx }/admin/adminuser.do?method=toUpPassword&uid=${props.uid }"
						class="btnAttach">修改密码</a>
					</td>
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
