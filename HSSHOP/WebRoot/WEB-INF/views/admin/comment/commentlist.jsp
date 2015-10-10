<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<form id="pagerForm" method="post" action="${ctx}/admin/comment/commentlist.do">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${numPerPage }" />
	<input type="hidden" name="cid" value="${cid }" />
	<input type="hidden" name="username" value="${username }" />
	<input type="hidden" name="name" value="${name }" />
	<input type="hidden" name="score" value="${score }" />
	<input type="hidden" name="status1" value="${status }" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li style="width:30px"><img id="searchImg" width="22px" height="22px" src="../themes/default/images/search1.gif"></li>
				<li>
					<label>评论编号：</label> 
					<input type="text" name="cid" value="${cid}" />
				</li>
				<li>
					<label>评价用户：</label> 
					<input type="text" name="username" value="${username}" />
				</li>
				
				<li>
					<label>评价商品：</label> 
					<input type="text" name="name" value="${name}" />
				</li>
				
				<li>
					<label>评价星级：</label> 
					<input type="text" name="score" value="${score}" />
				</li>
				<li>
					<label>评价状态：</label>
					<select class="combox" name="status1">
						<option value="">所有状态</option>
						<option value="0">待审核</option>
						<option value="1">已审核</option>
					</select>
				</li>
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
				href="comment/deleteSelect.do" class="delete"><span>批量删除</span>
			</a></li>
			
			<li><a title="确实要审核这些记录吗?" target="selectedTodo" rel="ids"
				href="comment/passSelect.do" class="delete"><span>批量审核</span>
			</a></li>
		</ul>
	</div>

	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>

				<th width="22"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="70" align="center">编号</th>
				<th>评价用户</th>
				<th>评价商品</th>
				<th>评价星级</th>
				<th>评价内容</th>
				<th>评价时间</th>
				<th width="140" align="center">管理操作</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach  items="${commentlist}" varStatus="status" var="item">
				<tr target="sid_user" rel="${item.cid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${item.cid}" type="checkbox">
					</td>
					<td align="left">
					${item.cid}
					</td>
					<td>${item.username}</td>
					<td>${item.name}</td>
					<td>${item.score.toString().substring(0,1)} 星</td>
					<td>${item.comment}</td>
					<td>
						<fmt:formatDate
							pattern="yyyy-MM-dd HH:mm:ss" value="${item.creatDate}" />
					</td>
					<td>
					<span><a  target="navTab"
						href="${ctx }/admin/comment/detail.do?cid=${item.cid }">
						查看详情
					</a></span>&nbsp;&nbsp;
					 <c:if test="${item.status=='0'}">
					 <span><a style="color:red" title="确定要审核通过？" target="ajaxTodo"
						href="${ctx }/admin/comment/passOne.do?cid=${item.cid }"
						>审核</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 </c:if>
					  <c:if test="${item.status=='1'}">
					 <a style="color:blue" >已审核</a>&nbsp;&nbsp;
					 </c:if>
					<span><a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/comment/deleteOne.do?cid=${item.cid }"
						>删除</a></span>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">${numPerPage}</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="10" currentPage="${currentPage}"></div>

	</div>
</div>
