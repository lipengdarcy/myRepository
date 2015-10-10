<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="orderField" value="${orderField}" /> <input
		type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader"></div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"
				onclick="editAttrVal('${attrid }','${attrname }',null);"><span>添加</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="74" align="left" name="sortTitle" column="attrid"
					direction="DESC">编号</th>
				<th align="left" name="sortTitle" column="name" direction="DESC">属性值</th>
				<th width="200" align="left" name="sortTitle" column="attrgroupid"
					direction="DESC">所属属性</th>
				<th width="100" align="left" name="sortTitle" column="isfilter"
					direction="DESC">排序</th>
				<th width="146" align="left">管理操作</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${attrValList}" varStatus="status">
				<tr target="sid_user" rel="${props.attrvalueid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<%--<td><input name="ids" value="${props.attrgroupid}" type="checkbox"></td>
					--%>
					<td>${props.attrvalueid}</td>
					<td>${props.attrvalue}</td>
					<td>${props.attrname}</td>
					<td>${props.attrvaluedisplayorder}</td>
					<td><a title="编辑"
						onclick="editAttrVal('${attrid }','${attrname }','${props.attrvalueid}');"
						class="btnEdit">编辑</a> <%-- <a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/category.do?method=delAttrVal&attrid=${props.attrvalueid}"
						class="btnDel">删除</a></td> --%>
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
	<script type="text/javascript">
		function editAttrVal(attrid, attrName, attrValId) {
			//添加地址
			var url = '${ctx }/admin/category.do?method=toAddAttrVal&attrid='
					+ attrid + '&attrname=' + attrName;
			var title = "添加属性值";
			if (attrValId) {
				url = '${ctx }/admin/category.do?method=toEditAttrVal&attrvalid='
						+ attrValId;
				title = "修改属性值";
			}
			$.pdialog
					.open(
							url,
							"to-add-attrval-dlg",
							title,
							{
								width : 300,
								height : 300,
								close : function() {
									navTab
											.reload("${ctx }/admin/category.do?method=attrValList&attrid="
													+ attrid
													+ "&attrname="
													+ attrName);
									return true;
								}
							});
		}
	</script>
</div>
