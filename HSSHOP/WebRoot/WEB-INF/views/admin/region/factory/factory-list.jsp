<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post"
	action="${ctx}/regionfactory/factoryList.do">
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
					<label>品牌：</label>
					<select class="combox" name="brandId" value="">
						<option value="-1">所有品牌</option>
						<c:forEach var="item" items="${proregionbrand }">
							<option value="${item.attrvalueid }" <c:if test="${brandId==item.attrvalueid }">selected="selected"</c:if>>${item.attrvalue }</option>						
						</c:forEach>
					</select>
				</li>
				<li>
					<label>产地：</label>
					<select class="combox" name="placeId" value="">
						<option value="-1">所有产地</option>
						<c:forEach var="item" items="${placelist }">
							<c:if test="${item.attrvalueid!= 196 }">
								<option value="${item.attrvalueid }" <c:if test="${placeId==item.attrvalueid }">selected="selected"</c:if>>${item.attrvalue }</option>
							</c:if>		
						</c:forEach>
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
			<li><a class="add" href="${ctx }/regionfactory/toFactoryAdd.do"
				target="navTab" external="true" title="添加"><span>品牌产地-添加</span>
			</a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				href="${ctx }/regionfactory/factoryListDelete.do" class="delete"><span>批量删除</span>
			</a></li>
			<li><a class="icon" rel="page46" target="navTab"
				href="${ctx }/regionfactory/factoryList.do" target="navTab"
				title="品牌产地"><span>刷新</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="50" align="center">编号</th>				
				<th>品牌</th>
				<th>产地</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.placeId }">
					<td>
						<input name="ids" value="${s.placeId }_${s.brandId }" type="checkbox">
					</td>
					<td align="center">${status.index + 1 }</td>					
					<td>${s.brandname }</td>
					<td>${s.placename }</td>
					<td width="120" align="center"><a title="编辑" target="navTab"
						href="${ctx }/regionfactory/toFactoryEdit.do?place_id=${s.placeId }&brand_id=${s.brandId }"
						external="true" class="btnEdit">编辑</a> <a title="删除无法恢复"
						target="ajaxTodo"
						href="${ctx }/regionfactory/factoryDelete.do?place_id=${s.placeId }&brand_id=${s.brandId }"
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
