<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${pageNum}" /> 
	<input type="hidden" name="orderField" value="${orderField}" /> 
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" onclick="editAttrGroup('${cateid }','','${catename }','添加');" 
				><span>添加</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<%--<th width="10" align="left" ></th>
			    --%><th width="80" align="left" >编号</th>
			    <th width="250" align="left" >名称</th>
			    <th align="left" >所属分类</th>
			    <th width="80" align="left" >排序</th>  
			    <th width="100" align="left">管理操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${attrValList}" varStatus="status">
				<tr target="sid_user" rel="${props.attrgroupid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<%--<td><input name="ids" value="${props.attrgroupid}" type="checkbox"></td>
					--%><td>${props.attrgroupid}</td>
					<td>${props.name}</td>
					<td>${catename}</td>
					<td>${props.displayorder}</td>
					<td>
						<a title="编辑"  onclick="editAttrGroup('${props.cateid }','${props.attrgroupid }','${catename }','编辑');" 
						class="btnEdit">编辑</a>
						 <a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/category.do?method=deleteAttributeGroup&cateid=${props.attrgroupid }"
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
	<script type="text/javascript">
	function editAttrGroup(cateid,attrgroupid,catename,ac){
		var name = catename.trim();
		$.pdialog.open('${ctx }/admin/category.do?method=toUpdateAttrGroup&attrgroupid='+attrgroupid+'&catename='+name+'&cateid='+cateid, "editAttrGroup", ac+"属性组", {
		width:300,
		height:200,
		close:function(){
			navTab.reload('${ctx }/admin/category.do?method=attributegrouplist&cateid='+cateid+'&catename='+catename);  
			return true;
		}
		});
	}
	</script>
</div>
