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
				onclick="editAttr('${cateid }','','','','${catename }','添加');"><span>添加</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="74" align="left" name="sortTitle" column="attrid"
					direction="DESC">编号</th>
				<th align="left" name="sortTitle" column="name" direction="DESC">属性名称</th>
				<th width="200" align="left" name="sortTitle" column="attrgroupid"
					direction="DESC">属性分组</th>
				<th width="100" align="left" name="sortTitle" column="isfilter"
					direction="DESC">筛选属性</th>
				<th width="100" align="left" name="sortTitle" column="displayorder"
					direction="ASC">排序</th>
				<th width="146" align="left">管理操作</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${attrValList}" varStatus="status">
				<tr target="sid_user" rel="${props.attrid}"
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
					<td>${props.attrid}</td>
					<td>${props.name}</td>
					<td>${props.attrgroupname}</td>
					<td><c:if test="${props.isfilter=='1'}">是</c:if>
						<c:if test="${props.isfilter!='1'}">否</c:if></td>
					<td>${props.displayorder}</td>
					<td><a title="值列表"
						href="${ctx }/admin/category.do?method=attrValList&attrid=${props.attrid}&attrname=${props.name}"
						target="navTab" rel="pageattrval" class=>值列表</a> <a title="编辑"
						onclick="editAttr('${cateid }','${props.attrid }','${props.name }','${props.attrgroupid }','${catename }','编辑');"
						class="btnEdit">编辑</a> <%-- <a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/category.do?method=deleteAttribute&attrid=${props.attrid }"
						class="btnDel">删除</a> --%></td>
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
		function editAttr(cateid, attrid, name, attrgroupid, catename, ac) {
			if (name) {
				name = name.trim();
			}
			if (catename) {
				catename = catename.trim();
			}
			$.pdialog
					.open(
							'${ctx }/admin/category.do?method=toUpdateAttr&attrgroupid='
									+ attrid + '&catename=' + catename
									+ '&cateid=' + cateid + '&name=' + name,
							"editAttrGroup",
							ac + "属性组",
							{
								width : 300,
								height : 300,
								close : function() {
									navTab
											.reload('${ctx }/admin/category.do?method=attributelist&cateid='
													+ cateid
													+ '&catename='
													+ catename);
									return true;
								}
							});
		}
	</script>
</div>
