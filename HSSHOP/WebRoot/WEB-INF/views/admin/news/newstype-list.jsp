<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/newsMng/newstype.do">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${numPerPage }" /> 	
</form>
<script type="text/javascript">
	var removeTrById = function(id){
		$("#tr_"+id).remove();
	};
</script>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="${ctx }/admin/newsMng.do?method=toNewstypeAdd" target="navTab" external="true" title="添加" ref="page12-add" ><span>新闻类型-添加</span> </a>
			</li>
			<li>
				<a class="icon" rel="page12" target="navTab" href="${ctx }/admin/newsMng/newstype.do" target="navTab" title="新闻类型管理" ><span>刷新</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center"  >编号</th>
				<th>新闻类型名称</th>
				<th width="120" align="center">排序</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.newstypeid }" >
					<td width="50" align="center">${s.newstypeid }</td>
					<td><a title="编辑" href="${ctx }/admin/newsMng.do?method=toNewstypeEdit&id=${s.newstypeid }" target="navTab" external="true" >${s.name }</a></td>
					<td width="120" align="center">${s.displayorder }</td>
					<td width="120" align="center">
						<a title="编辑" target="navTab" href="${ctx }/admin/newsMng.do?method=toNewstypeEdit&id=${s.newstypeid }" external="true" class="btnEdit" >编辑</a>
						<a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/newsMng.do?method=newstypeDelete&id=${s.newstypeid }" class="btnDel" callback="removeTrById(${s.newstypeid})" >删除</a>
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
