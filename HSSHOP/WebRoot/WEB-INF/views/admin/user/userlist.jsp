<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" />
</form>
<script type="text/javascript">
	var removeProductRegions = function(prid) {
		$("#tr_" + prid).remove();
	};
	function userdel(uid) {
		$
				.ajax({
					url : 'user/userdel.do',
					data : {
						uid : uid,
					},
					type : 'post',
					dataType : 'json',
					success : function(data) {
						if (data.state == 'success') {
							$("#user" + uid).remove();
							document.getElementById("background").style.display = "none";
							document.getElementById("progressBar").style.display = "none";
							document.getElementById("count").value = data.content;
							alert("已删除用户");
						} else {
							alert("异常！请重新尝试或者联系管理员！");
						}
					},
					error : function() {
						alert("异常！请重新尝试或者联系管理员！");
					}
				});
	}
</script>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li style="width: 30px"><img id="searchImg" width="22px"
					height="22px" src="../themes/default/images/search1.gif"></li>
				<li><label>账号名称：</label> <input type="text" name="username"
					value="${username}" /></li>
				<li><label>手机号码：</label> <input type="text" name="mobile"
					value="${mobile}" /></li>
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
			<li><a class="add" target="navTab"
				href="${ctx }/admin/user/useradd.do" external="true"><span>新增用户</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="70" align="center">编号</th>
				<th width="140" align="left">用户名</th>
				<th width="280" align="left">邮箱/手机</th>
				<th align="left">昵称</th>
				<!-- 				<th width="80" align="left">预存款</th> -->
				<th width="140" align="center">角色</th>
				<th width="300" align="center">注册访问</th>
				<th width="100" align="center">管理操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty userlist}">
					<c:forEach var="user" items="${userlist}" varStatus="status">
						<tr id="user${user.uid}">
							<td align="center">${user.uid}</td>
							<td>${user.username}</td>
							<td>${user.email}/${user.mobile}</td>
							<td>${user.nickname}</td>
							<%-- 							<td>${user.paycredits}</td> --%>
							<td></td>
							<td>${user.registertime}<c:if
									test="${user.registertime!=null}">
									<c:if test="${user.lastvisittime!=null}"> / </c:if>
								</c:if>${user.lastvisittime}
							</td>
							<td><a title="编辑" target="navTab"
								href="${ctx }/admin/user/userinfo.do?uid=${user.uid}"
								external="true" class="btnEdit">编辑</a> <a title="删除无法恢复"
								href="javascript:userdel(${user.uid})" class="btnDel">删除</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="main_info">
						<td colspan="100" class="center">没有相应用户</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
			</select> <span>条，共<span id="count">${totalCount}</span>条
			</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage }" pageNumShown="10"
			currentPage="${currentPage }"></div>

	</div>
</div>