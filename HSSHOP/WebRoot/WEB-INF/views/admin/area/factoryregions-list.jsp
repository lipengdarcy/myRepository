<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/area/factoryregions.do">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${numPerPage }" /> 
	<input type="hidden" name="type" value="2" /> <!-- 2代表工厂 -->
</form>
<script type="text/javascript">
	var removeProductRegions = function(saleid){
		$("#tr_"+saleid).remove();
	};
</script>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li style="width:30px"><img id="searchImg" width="22px" height="22px" src="../themes/default/images/search1.gif"></li>
				<li>
					<label>工厂名称：</label> 
					<input type="text" name="companyName" value="${companyName}" />
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
			<li>
				<a class="icon" rel="page44" target="navTab" href="${ctx }/admin/area/factoryregions.do" target="navTab" title="区域工厂/门店" ><span>刷新</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
<!-- 			<th width="70" align="center">类型</th> -->
				<th width="70" align="center">编号</th>
				<th >名称</th>
<!-- 				<th >地址</th>
				<th align="center">联系人</th>
				<th align="center">联系电话</th>
				<th align="center">坐标</th> -->
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.id }" >
					<%-- <td width="70" align="center">${my:getSaleaddressType(s.type) }</td> --%>
					<td align="center">${s.id }</td>
					<td>${s.name }</td>
<%-- 					<td>${s.address }</td>
					<td>${s.contacts }</td>
					<td>${s.tel }</td>
					<td>${s.workTime }</td> --%>
					<td width="120" align="center">
						<a title="编辑" target="navTab" href="${ctx }/admin/area.do?method=toSalerefregionsEdit&saleid=${s.id }&type=2" external="true" class="btnEdit" >编辑</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${currentPage }"></div>

	</div>
</div>
