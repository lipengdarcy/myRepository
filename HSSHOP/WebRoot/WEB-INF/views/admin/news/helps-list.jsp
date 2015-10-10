<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/newsMng/news.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" />
</form>
<script type="text/javascript">
	var removeTrById = function(id) {
		$("#tr_" + id).remove();
	};
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>帮助标题：<input type="text" name="title" />
					</td>
					<td><select class="combox" name="helpstype_id">
							<option value="">所有分类</option>
							<c:forEach var="t" items="${type }">
								<option value="${t.helpstypeid }" <c:if test="${t.helpstypeid eq helps.pid }">selected="selected"</c:if>>${t.name }</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"
				href="${ctx }/admin/helps.do?method=toHelpsAdd" target="navTab"
				title="添加"><span>帮助-添加</span> </a></li>
			<li><a class="icon" rel="page10" target="navTab"
				href="${ctx }/admin/helps/helpslist.do" target="navTab" title="帮助管理"><span>刷新</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">编号</th>
				<th>帮助类型</th>
				<th>帮助标题</th>
				<th>帮助url</th>
				<th>排序</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.id }">
					<td width="50" align="center">${s.id }</td>
					<td>${s.name }</td>
					<td><a title="编辑"
						href="${ctx }/admin/helps.do?method=toHelpsEdit&id=${s.id }"
						target="navTab">${s.title }</a></td>
					<td>${s.url }</td>
					<td>${s.displayorder }</td>
					<td width="120" align="center"><a title="编辑" target="navTab"
						href="${ctx }/admin/helps.do?method=toHelpsEdit&id=${s.id }"
						class="btnEdit">编辑</a> <a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/helps.do?method=helpsDelete&id=${s.id }"
						class="btnDel" callback="removeTrById(${s.id})">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
			</select> <span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage }" pageNumShown="10"
			currentPage="${currentPage }"></div>

	</div>
</div>
