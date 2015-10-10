<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/area/ship.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" />
</form>
<script type="text/javascript">
	var removeProductRegions = function(prid) {
		$("#tr_" + prid).remove();
	};

	$(document).ready(function() {
		//保存表格信息按钮
		$(".ship-save-table-btn").bind("click",function(e){
			var postDate=$("#ship-price-list-form").serialize();
			$.ajax({
				url : url + 'admin/area/saveProductList.do',
				type : 'post',
				data:postDate,
				dataType : 'json',
				success : function(data) {
					if(data.statusCode){
						alertMsg.correct("保存成功");
					}else{
						alertMsg.error(data);
					}
					
					//location.reload();
				}
				
			});
		});
		
		var args = HsAreaUser.defVals;
		//HsAreaUser.defVals.roleSel="[role='hs-area-sel-no-init']";

		var tidlast=0;
    	var areaRoles=$("#area3").find(HsAreaUser.defVals.roleSel);
    	for(var ai=0;ai<areaRoles.length;ai++){
    		var areaRole=areaRoles[ai];
    		var targs=$.extend({},args,{areaRole:areaRole});
    		HsAreaUser.initAreaSelForOutPanel(targs,tidlast,ai,"2-area-layer-");
        	tidlast++;
    	}
	});
</script>

<div class="pageContent">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post" id="search-form" style="display:none;" class="search-form">
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

			<li><a class="add"
				href="${ctx }/admin/area.do?method=toShipAdd&pid=${prid }"
				target="navTab" external="true" title="添加"><span>区域运费-添加</span>
			</a></li>
			<li><a class="icon ship-save-table-btn" external="true" title="保存" ><span>保存</span>
			</a></li>
			<li>
			<!-- 区域选择 -->
			<div id="area3" class="cellCon">
				<div seled-name-show=".pshow-name-ele-3"
					min-layer="2" max-layer="5" input-sel="[hidden-inputs-div]"
					role="hs-area-sel" style="position: relative;">
					<div>
						<a show-hs-area-sel="#hs-float-panel-3" class="icon"><span>筛选区域</span></a>
						<span class="pshow-name-ele-3"></span>
					</div>
				</div>
			</div> <!-- 区域选择end -->
			</li>
			<li><a class="icon" rel="page41" target="navTab"
				href="${ctx }/admin/area/ship.do?&prid=${prid }" target="navTab"
				title="区域运费"><span>刷新</span> </a></li>
		</ul>
	</div>
	<form id="ship-price-list-form">
	<input type="hidden" name="pageid" value="page41">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">编号</th>
				<th>区域</th>
				<c:if test="${prid>0}">
				<th>商品名称</th>
				</c:if>
				<th>数量</th>
				<th width="180">价格</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${shipList}" varStatus="status">
				<tr id="tr_${s.productregionsId }">
					<td width="50" align="center">
					${s.productregionsId }
					<input type="hidden" name="priceid" value="${s.id }">
					</td>
					<td><a title="编辑"
						href="${ctx }/admin/area.do?method=toShipEdit&id=${s.productregionsId }"
						target="navTab" external="true">${s.regionsName }</a></td>
					<c:if test="${prid>0}">
					<td>${s.pname }</td>
					</c:if>
					<td>${s.buyminquan }</td>
					<td width="180">
					<input type="text" name="pricename" value="${s.price }">
					</td>
					<td width="120" align="center"><a title="编辑" target="navTab"
						href="${ctx }/admin/area.do?method=toShipEdit&id=${s.productregionsId }"
						external="true" class="btnEdit">编辑</a> <a title="删除无法恢复"
						target="ajaxTodo"
						href="${ctx }/admin/area.do?method=shipDelete&id=${s.productregionsId }"
						class="btnDel"
						callback="removeProductRegions(${s.productregionsId})">删除</a></td>
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
	
	<div  cid="${cid }" class="area-float-panel float-panel" id="hs-float-panel-3">
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



