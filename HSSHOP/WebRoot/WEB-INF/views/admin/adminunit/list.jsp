﻿<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
			<li><a class="add" href="${ctx }/admin/adminunit.do?method=toAdd"
				target="navTab"><span>添加</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<!-- th width="50" align="center" orderField="uid" class="${orderDirection}">主键ID</th-->
				<th>单位名称</th>
				<th>单位英文名</th>
				<th>单位简称</th>
				<th>单位英文简称</th>
				<th>备注</th>
				<th>类型</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${actionlist}" varStatus="status">
				<tr target="sid_user" rel="${props.unitid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${props.unitid}" type="checkbox">
					</td>
					<td>${props.unitname}</td>
					<td>${props.unitenname}</td>
					<td>${props.unitshort}</td>
					<td>${props.unitenshort}</td>
					<td>${props.remarks}</td>
					<td>
						<c:if test="${props.unittype==1}">重量单位</c:if>
						<c:if test="${props.unittype==2}">数量单位</c:if>
					</td>
					<td>
						<a title="编辑" target="navTab" href="${ctx }/admin/adminunit.do?method=toUpdate&uid=${props.unitid }"
						class="btnEdit">编辑</a>
						 <a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/adminunit.do?method=delete&uid=${props.unitid }"
						class="btnDel">删除</a>
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
