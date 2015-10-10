<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${pageNum}" /> 
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dwzSearch(this, 'dialog');" 
		action="${ctx }/admin/ncpro/ncprolookbacklist.do" method="post" id="search-form">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>关键词：</label> 
				<input type="text" name="keywords" value="${keywords}" /></li>
			</ul>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<!-- th width="50" align="center" orderField="uid" class="${orderDirection}">主键ID</th-->
				<th>企业编号</th>
				<th>企业名称</th>
				<th>产品编号</th>
				<th>产品名称</th>				
				<th width="60" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${list}" varStatus="status">
				<tr target="sid_user" rel="${props.pid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">					
					<td>${props.eid}</td>
					<td>${props.ename}</td>
					<td>${props.pid}</td>
					<td>${props.pname}</td>
					<td>
						<a class="btnSelect"
						 href="javascript:$.bringBack({ncpronum:'${props.pid}'})" title="查找带回">选择</a>
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

		<div class="pagination" targetType="dialog" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>

	</div>
</div>
