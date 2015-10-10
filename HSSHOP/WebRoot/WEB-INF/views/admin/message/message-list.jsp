<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post"
	action="">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" />
</form>
<script type="text/javascript">
	var removeTrById = function(id) {
		$("#tr_" + id).remove();
	};
</script>

<div class="pageContent">
	<table class="table" width="100%" layoutH="68">
		<thead>
			<tr>
				<th width="70" align="center">编号</th>
				<th width="150" align="center">订单号</th>
				<th width="70" align="center">验证码</th>
				<th width="100" align="center">手机号码</th>
				<th>发送内容</th>
				<th width="120" align="center">有效时间</th>
				<th width="120" align="center">发送时间</th>
				<th width="120" align="center">是否有效</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${messageList}" varStatus="status">
				<tr>
					<td>${s.id }</td>
					<td>${s.orderNumber }</td>
					<td>${s.code }</td>
					<td>${s.mobile }</td>
					<td>${s.message }</td>
					<td>${s.valid }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${s.sendingTime }" /></td>
					<td><c:if test="${s.state == 0 }">未使用</c:if> <c:if
							test="${s.state == 1 }">已使用</c:if></td>
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
