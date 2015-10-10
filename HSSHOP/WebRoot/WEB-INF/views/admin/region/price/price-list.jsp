<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post"
	action="${ctx}/regionprice/priceList.do">
	<input type="hidden" name="pid" value="${pid }" /> <input
		type="hidden" name="prid" value="${prid }" /> <input type="hidden"
		name="pageNum" value="1" /> <input type="hidden" name="numPerPage"
		value="${numPerPage }" /> <input type="hidden" id="type" name="type"
		value="${type }" />
</form>
<script type="text/javascript">
	var removeProductRegions = function(prid) {
		$("#tr_" + prid).remove();
	};

	$(document).ready(
			function() {
				//保存表格信息按钮
				$(".save-table-btn").bind("click", function(e) {
					var postDate = $("#price-list-form").serialize();
					$.ajax({
						url : url + '/regionprice/priceBatchEdit.do',
						type : 'post',
						data : postDate,
						dataType : 'json',
						success : function(data) {
							if (data.statusCode) {
								alertMsg.correct("保存成功");
							} else {
								alertMsg.error(data);
							}
						}

					});
				});
				//
				$('#priceType').change(function() {
					var type = $(this).children('option:selected').val();
					$("#type").val(type);
					navTabPageBreak();
				});
				//区域选择脚本开始
				var args = HsAreaUser.defVals;
				//HsAreaUser.defVals.roleSel="[role='hs-area-sel-no-init']";

				var tidlast = 0; 
				var areaRoles = $("#area_price").find(
						HsAreaUser.defVals.roleSel);
				for (var ai = 0; ai < areaRoles.length; ai++) {
					var areaRole = areaRoles[ai];
					var targs = $.extend({}, args, {
						areaRole : areaRole
					});
					HsAreaUser.initAreaSelForOutPanel(targs, tidlast, ai,
							"price-area-layer-");
					tidlast++;
				}
				//区域选择脚本结束
			});
</script>

<div class="pageContent">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
			method="post" class="search-form">
			<div class="searchBar">
				<ul class="searchContent">
					<li style="width: 30px"><img id="searchImg" width="22px"
						height="22px" src="../themes/default/images/search1.gif"></li>
					<li><label>产品名称：</label> <input type="text" name="productName"
						value="${productName}" /></li>
					<li>
						<!-- 区域选择 -->
						<div id="area_price" class="cellCon">
							<div seled-name-show=".pshow-name-ele-1" min-layer="2"
								max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel"
								style="position: relative;">
								<div>
									<a show-hs-area-sel="#hs-float-panel-price" class="button"><span>筛选区域</span></a>
									<span class="pshow-name-ele-1"></span>
								</div>
							</div>
						</div> <!-- 区域选择end -->
					</li>
					<li style="display: none;"><label>关键词：</label><input
						type="text" name="areaid" value="${areaid}" /></li>
					<li style="display: none;"><label>关键词：</label><input
						type="text" name="cid" value="${cid}" /></li>
				</ul>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
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
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${pid ne -1 and prid eq -1}">
				<li><a class="add"
					href="${ctx }/regionprice/toPriceAdd.do?pid=${pid }" target="navTab"
					external="true" title="添加"><span>区域价格-添加</span> </a></li>
			</c:if>

			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				href="${ctx }/regionprice/priceBatchDelete.do?navTabId=page43" class="delete"> <span>批量删除</span>
			</a></li>

			<li><a class="icon save-table-btn" title="保存"><span>保存</span>
			</a></li>
			

			<li><a class="icon" rel="page43" target="navTab"
				href="${ctx }/regionprice/priceList.do?&prid=${prid}&pid=${pid}&type=${type}"
				target="navTab" title="区域价格"><span>刷新</span> </a></li>
		</ul>
	</div>
	<form id="price-list-form">
		<input type="hidden" name="pageid" value="page43">
		<table class="table " width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids"
						class="checkboxCtrl"></th>
					<th width="50" align="center">编号</th>
					<th>区域</th>
					<c:if test="${type !=3 }">
						<th>商品名称</th>
					</c:if>
					<th>价格</th>
					<th width="120" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${shipList}" varStatus="status">
					<tr id="tr_${s.id }">
						<td><input name="ids" value="${s.id}" type="checkbox">
						</td>
						<td width="50" align="center">${s.id }</td>
						<td>${s.regionsName }</td>
						<c:if test="${type !=3 }">
							<td><a title="编辑"
								href="${ctx }/regionprice/toPriceEdit.do?id=${s.id }"
								target="navTab" external="true">${s.pname }</a></td>
						</c:if>
						<td width="50" align="center"><input type="text"
							name="pricename" value="${s.price }"> <input
							name="priceid" value="${s.id}" type="hidden"></td>
						<td width="120" align="center"><a title="编辑" target="navTab"
							href="${ctx }/regionprice/toPriceEdit.do?id=${s.id}"
							external="true" class="btnEdit">编辑</a> <a title="删除无法恢复"
							target="ajaxTodo"
							href="${ctx }/regionprice/priceDelete.do?priceType=1&id=${s.id }&pageid=page43"
							class="btnDel">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>


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
	<!-- 显示区域选择的浮窗begin -->
	<div cid="${cid }" class="area-float-panel float-panel"
		id="hs-float-panel-price">
		<div class="panel-close-btn">
			<span class="glyphicon glyphicon-remove"></span>
		</div>
		<div role="tabpanel" class="area-tabs">
			<ul class="nav nav-tabs" role="tablist"></ul>
			<div class="tab-content"></div>
		</div>
		<div hidden-inputs-div=""></div>
	</div>
	<!-- 显示区域选择的浮窗end -->
</div>


