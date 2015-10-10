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
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"
				href="${ctx }/admin/helps.do?method=toHelpstypeAdd" target="navTab"
				title="添加"><span>帮助类型-添加</span> </a></li>
			<li><a class="icon" rel="page13" target="navTab"
				href="${ctx }/admin/helps/helpstypelist.do" target="navTab" title="帮助类型管理"><span>刷新</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">编号</th>
				<th>帮助类型名称</th>
				<th>排序</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.helpstypeid }">
					<td width="50" align="center">${s.helpstypeid }</td>
					<td><a title="编辑"
						href="${ctx }/admin/helps.do?method=toHelpstypeEdit&id=${s.helpstypeid }"
						target="navTab">${s.name }</a></td>
					<td>${s.displayorder }</td>
					<td width="120" align="center"><a title="编辑" target="navTab"
						href="${ctx }/admin/helps.do?method=toHelpstypeEdit&id=${s.helpstypeid }"
						class="btnEdit">编辑</a> <a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/helps.do?method=helpstypeDelete&id=${s.helpstypeid }"
						class="btnDel" callback="removeTrById(${s.helpstypeid})">删除</a></td>
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
