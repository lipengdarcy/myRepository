<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" />
</form>

<script type="text/javascript">
	//审核通过
	function approve(id) {
		$.ajax({
			url : '${ctx }/permission/appproveStoreUserApply.do',
			data : {
				id : id
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					alertMsg.info(data.content);
				} else {
					alertMsg.error(data.content);
				}
			},
			error : function() {
				alertMsg.error("异常！请重新尝试或者联系管理员！");
			}
		});
	}

	//审核驳回 
	function reject(id) {
		$.ajax({
			url : '${ctx }/permission/rejectStoreUserApply.do',
			data : {
				id : id
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					alertMsg.info(data.content);
				} else {
					alertMsg.error(data.content);
				}
			},
			error : function() {
				alertMsg.error("异常！请重新尝试或者联系管理员！");
			}
		});
	}

	//删除申请
	function applyDelete(id) {
		$.ajax({
			url : '${ctx }/permission/deleteStoreUserApply.do',
			data : {
				id : id
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					alertMsg.info(data.content);
					//window.location.href = '${ctx }/permission/applylist.do';
				} else {
					alertMsg.error(data.content);
				}
			},
			error : function() {
				alertMsg.error("异常！请重新尝试或者联系管理员！");
			}
		});
	}
</script>


<div class="pageContent">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="70" align="center">编号</th>
				<th width="140" align="left">用户名</th>
				<th width="140" align="left">申请用户类型</th>
				<th width="150" align="left">申请状态</th>
				<th width="100" align="left">管理操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty applyList}">
					<c:forEach var="user" items="${applyList}" varStatus="status">
						<tr id="user${user.id}">
							<td>${user.id}</td>
							<td align="center">${user.username}</td>

							<td><c:if test="${user.roleid==1}">
							普通用户
							</c:if> <c:if test="${user.roleid==2}">
							经销商用户
							</c:if> <c:if test="${user.roleid==3}">
							工厂用户
							</c:if></td>

							<td><c:if test="${user.applystatus==0}">
							申请中
							</c:if> <c:if test="${user.applystatus==1}">
							审核通过
							</c:if> <c:if test="${user.applystatus==2}">
							审核驳回
							</c:if></td>


							<td width="120" align="center"><c:if
									test="${user.roleid!=1}">
									<a title="查看申请详情" target="dialog"
										href="${ctx }/permission/applyUserInfo.do?id=${user.id}&status=${user.applystatus}"
										class="btnLook">查看</a>
									<a href="javascript:approve(${user.id})">审核通过</a>
									<a href="javascript:reject(${user.id})">审核驳回</a>
									<a title="删除无法恢复" href="javascript:applyDelete(${user.id})"
										class="btnDel">删除</a>
								</c:if></td>
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