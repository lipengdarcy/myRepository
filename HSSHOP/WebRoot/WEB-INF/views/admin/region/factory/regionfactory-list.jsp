<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post"
	action="${ctx}/regionfactory/RegionfactoryList.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" />
</form>
<script type="text/javascript">
	$(document).ready(
			function() {

				//
				var args = HsAreaUser.defVals;
				//HsAreaUser.defVals.roleSel="[role='hs-area-sel-no-init']";

				var tidlast = 0;
				var areaRoles = $("#area_place").find(HsAreaUser.defVals.roleSel);
				for (var ai = 0; ai < areaRoles.length; ai++) {
					var areaRole = areaRoles[ai];
					var targs = $.extend({}, args, {
						areaRole : areaRole
					});
					HsAreaUser.initAreaSelForOutPanel(targs, tidlast, ai,
							"place-area-layer-");
					tidlast++;
				}
			});
	
</script>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li style="width:30px"><img id="searchImg" width="22px" height="22px" src="../themes/default/images/search1.gif"></li>
				<li>
					<label>订单编号：</label> 
					<input type="text" name="orderNum" value="${orderNum}" />
				</li>
				<li>
					<label>账号名称：</label> 
					<input type="text" name="username" value="${username}" />
				</li>
				
				<li>
					<label>收货人：</label> 
					<input type="text" name="consignee" value="${consignee}" />
				</li>
				
				<li>
					<label>手机号码：</label> 
					<input type="text" name="mobile" value="${mobile}" />
				</li>
				<li>
					<label>订单状态：</label>
					<select class="combox" name="orderstate">
						<option value="">所有状态</option>
						<option value="0">待确认</option>
						<option value="1">已确认</option>
						<option value="2">备货中</option>
						<option value="3">已发货</option>
						<option value="4">已完成</option>
						<option value="5">锁定</option>
						<option value="6">已取消</option>
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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post" id="search-form" style="display: none;"
		class="search-form">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>关键词：</label><input type="text" name="areaid"
					value="${areaid}" /></li>
				<li><label>关键词：</label><input type="text" name="cid"
					value="${cid}" /></li>
			</ul>
		</div>
	</form>

	<div class="panelBar">
		<ul class="toolBar">
			
			<li>
				<!-- 区域选择 -->
				<div id="area_place" class="cellCon">
					<div seled-name-show=".pshow-name-ele-1" min-layer="2"
						max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel"
						style="position: relative;">
						<div>
							<a show-hs-area-sel="#hs-float-panel-place" class="icon"><span>筛选区域</span></a>
							<span class="pshow-name-ele-1"></span>
						</div>
					</div>
				</div> <!-- 区域选择end -->
			</li>
			<li><a class="icon" rel="page46" target="navTab"
				href="${ctx }/regionfactory/RegionfactoryList.do" target="navTab"
				title="区域品牌产地"><span>刷新</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">编号</th>
				<th>区域</th>
				<th>品牌</th>
				<th>产地</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.id }">
					<td align="center">${s.id }</td>
					<td>${s.regionsName }</td>
					<td>${s.brandName }</td>
					<td>${s.attrvalue }</td>
					<td width="120" align="center"><a title="编辑" target="navTab"
						href="${ctx }/regionfactory/toRegionFactoryEdit.do?id=${s.id }"
						external="true" class="btnEdit">编辑</a> <a title="删除无法恢复"
						target="ajaxTodo"
						href="${ctx }/regionfactory/RegionfactoryDelete.do?id=${s.id }&prid=${s.productregionsid }"
						class="btnDel">删除</a></td>
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

	<div cid="${cid }" class="area-float-panel float-panel"
		id="hs-float-panel-place">
		<div class="panel-close-btn">
			<span class="glyphicon glyphicon-remove"></span>
		</div>
		<div role="tabpanel" class="area-tabs">
			<ul class="nav nav-tabs" role="tablist"></ul>
			<div class="tab-content"></div>
		</div>
		<div hidden-inputs-div=""></div>
	</div>

</div>
