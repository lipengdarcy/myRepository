<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<style>
.table-button {
	display: inline-blcok;
	color: #15428B;
	font-weight: bold;
	margin-left: 8px;
}
</style>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${pageNum }" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" />
	<!--【可选】每页显示多少条-->
	<input type="hidden" name="orderField" value="${orderField}" /> <input
		type="hidden" name="orderDirection" value="${orderDirection}" /> <input
		type="hidden" name="skugid" value="${skugid}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post" id="search-form">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>关键词：</label><input type="text" name="keywords"
					value="${keywords}" /></li>
				<li><label>在售状态：</label> <select class="combox" name="status">
						<option value="1"
							<c:if test="${status=='1'}">selected = "selected"</c:if>>已下架</option>
						<option value="0"
							<c:if test="${status=='0'}">selected = "selected"</c:if>>已上架</option>
				</select> <input type="submit" name="submit" value="查找"></li>
			</ul>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<!-- th width="50" align="center" orderField="uid" class="${orderDirection}">主键ID</th-->
				<th>ID</th>
				<th>名称</th>
				<th>热销</th>
				<th>新品</th>
				<th>排序</th>
				<th>区域</th>
				<th width="220" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${actionlist}" varStatus="status">
				<tr target="sid_user" rel="${props.pid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${props.pid}" type="checkbox">
					</td>
					<td>${props.pid}</td>
					<td>${props.name}</td>
					<td><c:if test="${props.ishot=='0'}">否</c:if> <c:if
							test="${props.ishot=='1'}">是</c:if></td>
					<td><c:if test="${props.isnew=='0'}">否</c:if> <c:if
							test="${props.isnew=='1'}">是</c:if></td>
					<td>${props.displayorder}</td>
					<td><a title="区域价格" rel="page43" target="navTab"
						href="${ctx }/regionprice/priceList.do?type=4&pid=${props.pid }"
						class="table-button"><span>价格</span> </a> <a title="区域运费"
						rel="page41" target="navTab"
						href="${ctx }/regionprice/shipList.do?type=4&pid=${props.pid }"
						class="table-button"><span>运费</span> </a> <a title="区域装卸费"
						rel="page42" target="navTab"
						href="${ctx }/regionprice/handList.do?type=4&pid=${props.pid }"
						class="table-button"><span>装卸费</span> </a></td>
					<td><a title="商品预览" target="_blank"
						href="${ctx }/product/detail.do?id=${props.pid }"
						class="table-button"><span>商品预览</span> </a> <a title="图片"
						target="navTab" rel="productImgList"
						href="${ctx }/admin/adminproduct.do?method=toProductImg&pid=${props.pid }"
						class="table-button"><span>图片</span></a> <c:if
							test="${props.state==0 }">
							<a title="是否确定下架该产品" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=changeProductState&state=1&pid=${props.pid }"
								class="table-button"><span>下架</span></a>
						</c:if> <c:if test="${props.state==1 }">
							<a title="是否确定上架该产品" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=changeProductState&state=0&pid=${props.pid }"
								class="table-button"><span>上架</span></a>
						</c:if> <a title="编辑" target="navTab"
						href="${ctx }/admin/adminproduct.do?method=toUpdate&pid=${props.pid }&pageid=page11"
						class="table-button">编辑</a> <a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/adminproduct.do?method=delete&pid=${props.pid }&pageid=page11"
						class="table-button">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20"
					<c:if test="${numPerPage=='20'}">selected = "selected"</c:if>>20</option>
				<option value="30"
					<c:if test="${numPerPage=='30'}">selected = "selected"</c:if>>30</option>
				<option value="50"
					<c:if test="${numPerPage=='50'}">selected = "selected"</c:if>>50</option>
				<option value="100"
					<c:if test="${numPerPage=='100'}">selected = "selected"</c:if>>100</option>
			</select> <span> 条/页，共<font color=red>${totalCount}</font>条
			</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>

	</div>
</div>
