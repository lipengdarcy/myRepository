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
				<li style="width: 30px"><img id="searchImg" width="22px"
					height="22px" src="../themes/default/images/search1.gif"></li>
				<li><label>订单编号：</label> <input type="text" name="orderNum"
					value="${orderNum}" /></li>
				<li><label>账号名称：</label> <input type="text" name="username"
					value="${username}" /></li>

				<li><label>收货人：</label> <input type="text" name="consignee"
					value="${consignee}" /></li>

				<li><label>手机号码：</label> <input type="text" name="mobile"
					value="${mobile}" /></li>
				<li><label>订单状态：</label> <select class="combox"
					name="orderstate">
						<option value="">所有状态</option>
						<c:forEach items="${orderstateList}" var="item">
						<option value="${item.code}" <c:choose>
							<c:when test="${item.code == orderstate }">
								selected
							</c:when>
							<c:otherwise>
								
							</c:otherwise>
						</c:choose>>${item.desc}</option>
						</c:forEach>
				</select></li>
			</ul>
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

			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				href="order/deleteSelect.do" class="delete"><span>批量删除</span> </a></li>
		</ul>
	</div>

	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="70" align="center">编号</th>
				<th align="center">订单编号</th>
				<th align="center">下单时间</th>
				<th align="center">收货人</th>
				<th align="center">订单金额</th>
				<th align="center">应付金额</th>
				<th width="70" align="center">订单状态</th>
				<th width="140" align="center">管理操作</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${orderList}" varStatus="status" var="item">
				<tr target="sid_user" rel="${item.bspOrders.oid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${item.bspOrders.oid}" type="checkbox">
					</td>
					<td>${item.bspOrders.oid}</td>
					<td><a target="navTab"
						href="${ctx }/admin/order/detail.do?oid=${item.bspOrders.oid }">
							${item.bspOrders.osn} </a></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
							value="${item.bspOrders.addtime}" /></td>
					<td>${item.bspOrders.consignee}</td>
					<td>${item.bspOrders.orderamount}</td>
					<td>${item.bspOrders.orderamount}</td>
					<td>${item.statename}</td>
					<td><span><a target="navTab"
							href="${ctx }/admin/order/detail.do?oid=${item.bspOrders.oid }"> 查看详情 </a></span>
						&nbsp;&nbsp; <span><a target="_blank"
							href="${ctx }/admin/order/print.do?oid=${item.bspOrders.oid }"> 打印 </a></span>&nbsp;&nbsp;

						<span><a title="删除无法恢复" target="ajaxTodo"
							href="${ctx }/admin/order/delete.do?oid=${item.bspOrders.oid }">删除</a></span>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">${numPerPage}</option>
			</select> <span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>

	</div>
</div>
