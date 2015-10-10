<%@ page language="java" pageEncoding="UTF-8" 	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="${ctx }/admin/news.do?method=toAdd" target="navTab"><span>添加</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center" orderField="PKID" class="${orderDirection}">类型编号</th>
				<th>类型名称</th>
				<th>排序</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="nt" items="${newstypeList}" varStatus="status">
				<tr id="tr_${nt.newstypeid}">
					<td>${nt.newstypeid}</td>
					<td>${nt.name}</td>
					<td>${nt.displayorder}</td>
					<td>
						<a title="编辑" target="navTab" href="${ctx }/admin/news.do?method=toUpdate&pKID=${props.PKID }" class="btnEdit">编辑</a> 
						<a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/news.do?method=delete&pKID=${props.PKID }" class="btnDel">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
