<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="${selVO.pageNum}" /> 
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post" id="search-form">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>用户名：</label> 
					<input type="text" name="username" value="${selVO.username }" />						
				</li>
				<li><label>状态：</label> 
					<select id="selvostate-selcon" name="state">
		              <option value='-1' <c:if test="${selVO.state==-1 }">selected</c:if> >请选择</option>
		              <option value='0' <c:if test="${selVO.state==0 }">selected</c:if> >未支付</option>
		              <option value='1' <c:if test="${selVO.state==1 }">selected</c:if> >已支付</option>
		            </select>
				</li>
				<li><label>日期：</label> 
					<input type="text" name="startDate" value="${selVO.startDate }" class="date readonly" readonly="true"/>
					
				</li>
				<li>
					<label>到</label> 
					<input type="text" name="endDate" value="${selVO.endDate }" class="date readonly" readonly="true"/>
					
				</li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th>用户名</th>
				<th>订单号</th>
				<th>日期</th>
				<th>类型</th>
				<th>金额</th>
				<th width="320">备注</th>
				<th>状态</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="payitem" items="${payList}" varStatus="status">
				<tr>
					<td>${payitem.username}</td>
					<td>${payitem.osn}</td>
					<td><fmt:formatDate value="${payitem.createtime}" pattern="yyyy年MM月dd日" /></td>
					<td>${payitem.paytypename }</td>
					<td>${payitem.payamount }</td>
					<td>${payitem.description }</td>
					<td>
						<c:if test="${payitem.status==0 }">
		            		未支付
		            	</c:if>
		            	<c:if test="${payitem.status==1 }">
		            		已支付
		            	</c:if>
					</td>
					<td>	
						<c:if test="${payitem.status==0 }">
		            		 <a title="你确定修改该条目状态为已付款" target="ajaxTodo" href="${ctx }/admin/storemny.do?method=setStatus&id=${payitem.id}">设为付款</a>	
		            	</c:if>						
											
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <span>${selVO.numPerPage}条/页，共<font color=red>${totalCount}</font>条
			</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${selVO.numPerPage}" pageNumShown="10" currentPage="${selVO.pageNum}"></div>

	</div>
</div>
