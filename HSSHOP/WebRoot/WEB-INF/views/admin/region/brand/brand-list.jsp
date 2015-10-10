<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/regionbrand/brandList.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" />
</form>
<script type="text/javascript">
	var removeProductRegions = function(id) {
		$("#tr_" + id).remove();
	};

	$(document).ready(
			function() {

				//
				var args = HsAreaUser.defVals;
				//HsAreaUser.defVals.roleSel="[role='hs-area-sel-no-init']";

				var tidlast = 0;
				var areaRoles = $("#area_brand").find(HsAreaUser.defVals.roleSel);
				for (var ai = 0; ai < areaRoles.length; ai++) {
					var areaRole = areaRoles[ai];
					var targs = $.extend({}, args, {
						areaRole : areaRole
					});
					HsAreaUser.initAreaSelForOutPanel(targs, tidlast, ai,
							"brand-area-layer-");
					tidlast++;
				}
			});
</script>

<div class="pageContent">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
			method="post" class="search-form">
			<div class="searchBar">
				<ul class="searchContent">
					<li style="width:30px"><img id="searchImg" width="22px" height="22px" src="../themes/default/images/search1.gif"></li>
	
					<li>
						<!-- 区域选择 -->
						<div id="area_brand" class="cellCon">
							<div seled-name-show=".pshow-name-ele-1" min-layer="2"
								max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel"
								style="position: relative;">
								<div>
									<a show-hs-area-sel="#hs-float-panel-brand" class="button"><span>筛选区域</span></a>
									<span class="pshow-name-ele-1"></span>
								</div>
							</div>
						</div> <!-- 区域选择end -->
					</li>
					
					<li style="display:none;"><label>关键词：</label><input type="text" name="areaid"
						value="${areaid}" /></li>
					<li style="display:none;"><label>关键词：</label><input type="text" name="cid"
						value="${cid}" /></li>
					<li>
						<label>品牌：</label>
						<select class="combox" name="brandId" value="">
							<option value="-1">所有品牌</option>
							<c:forEach var="item" items="${proregionbrand }">
								<option value="${item.attrvalueid }" <c:if test="${brandId==item.attrvalueid }">selected="selected"</c:if>>${item.attrvalue }</option>
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

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"
				href="${ctx }/regionbrand.do?method=toBrandAdd" target="navTab"
				external="true" title="添加"><span>区域品牌-添加</span> </a></li>
			<li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				href="${ctx }/regionbrand/brandListDelete.do" class="delete"><span>批量删除</span>
			</a>
			<li><a class="icon" rel="page45" target="navTab"
				href="${ctx }/regionbrand/brandList.do" target="navTab" title="区域品牌"><span>刷新</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="70" align="center">编号</th>
				<th>区域</th>
				<th>品牌</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.id }">
					<td>
						<input name="ids" value="${s.id}_${s.productregionsid }" type="checkbox">
					</td>
					<td align="center">${s.id }</td>
					<td>${s.regionsName }</td>
					<td>${s.attrvalue }</td>
					<td width="120" align="center"><a title="编辑" target="navTab"
						href="${ctx }/regionbrand.do?method=toBrandEdit&id=${s.id }"
						external="true" class="btnEdit">编辑</a> <a title="删除无法恢复"
						target="ajaxTodo"
						href="${ctx }/regionbrand/brandDelete.do?id=${s.id }&prid=${s.productregionsid }"
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
		id="hs-float-panel-brand">
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
