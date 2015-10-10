<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="orderField" value="${orderField}" /> <input
		type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>关键词：</label> <input type="text" name="keywords"
					value="${keywords}" /></li>
				<li><select class="combox" name="province">
						<option value="">所有省市</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="天津">天津</option>
						<option value="重庆">重庆</option>
						<option value="广东">广东</option>
				</select></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="demo_page6.html" target="dialog"
						mask="true" title="查询框"><span>高级检索</span> </a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx }/admin/news.do?method=toAdd"
				target="navTab"><span>添加</span> </a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				href="demo/common/ajaxDone.html" class="delete"><span>批量删除默认方式</span>
			</a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				postType="string" href="demo/common/ajaxDone.html" class="delete"><span>批量删除逗号分隔</span>
			</a></li>
			<li><a class="edit"
				href="${ctx }/admin/news.do?method=toUpdate&pKID={sid_user}"
				target="navTab" warn="请选择一个"><span>修改</span> </a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls"
				target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>

				<th width="22"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="50" align="center" orderField="PKID"
					class="${orderDirection}">主键ID</th>
				<th>权限名字</th>
				<th>修改时间</th>
				<th align="center">状态</th>
				<th width="70" align="center">操作</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="props" items="${actionlist}" varStatus="status">
				<tr target="sid_user" rel="${props.PKID}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${props.PKID}" type="checkbox">
					</td>
					<td>${props.PKID}</td>
					<td>${props.name}</td>
					<td align="center"><fmt:formatDate
							pattern="yyyy-MM-dd HH:mm:ss" value="${props.modifyTime}" /></td>
					<td></td>
					<td><a title="编辑" target="navTab"
						href="${ctx }/admin/news.do?method=toUpdate&pKID=${props.PKID }"
						class="btnEdit">编辑</a> <a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/news.do?method=delete&pKID=${props.PKID }"
						class="btnDel">删除</a></td>
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
