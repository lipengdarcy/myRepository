<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/area/product.do">
	<input type="hidden" name="prid" value="${prid }" /> <input
		type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" /> <input type="hidden"
		id="type" name="type" value="${type }" />
</form>
<script type="text/javascript">
	var removeProductRegions = function(prid) {
		$("#tr_" + prid).remove();
	};

	$(document).ready(function() {
		//保存表格信息按钮
		$(".save-table-btn").bind("click",function(e){
			var postDate=$("#price-list-form").serialize();
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
				}
				
			});
		});
		//
		$('#priceType').change(function() {
			var type = $(this).children('option:selected').val();
			$("#type").val(type);
			navTabPageBreak();
		});
		//
		var args = HsAreaUser.defVals;
		//HsAreaUser.defVals.roleSel="[role='hs-area-sel-no-init']";

		var tidlast=0;
    	var areaRoles=$("#area1").find(HsAreaUser.defVals.roleSel);
    	for(var ai=0;ai<areaRoles.length;ai++){
    		var areaRole=areaRoles[ai];
    		var targs=$.extend({},args,{areaRole:areaRole});
    		HsAreaUser.initAreaSelForOutPanel(targs,tidlast,ai,"1-area-layer-");
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
				href="${ctx }/admin/area.do?method=toProductAdd&pid=${prid }"
				target="navTab" external="true" title="添加"><span>区域价格-添加</span>
			</a></li>
			<li><a class="icon save-table-btn" title="保存" ><span>保存</span>
			</a></li>
			<li>
			<!-- 区域选择 -->
			<div id="area1" class="cellCon">
				<div seled-name-show=".pshow-name-ele-1"
					min-layer="2" max-layer="5" input-sel="[hidden-inputs-div]"
					role="hs-area-sel" style="position: relative;">
					<div>
						<a show-hs-area-sel="#hs-float-panel-1" class="icon"><span>筛选区域</span></a>
						<span class="pshow-name-ele-1"></span>
					</div>
				</div>
			</div> <!-- 区域选择end -->
			</li>
			<c:if test="${type!=4}">
				<li style="display:none;"><select id="priceType" class="valid" name="type">
						<option <c:if test="${type==1}">selected = "selected"</c:if>
							value="1">全部</option>
						<option <c:if test="${type==2}">selected = "selected"</c:if>
							value="2">区域产品已设置</option>
						<option <c:if test="${type==3}">selected = "selected"</c:if>
							value="3">区域产品未设置</option>
				</select></li>
			</c:if>
			<c:if test="${prid ne -1 }">
				<li style="display:none;"><a class="add"
					href="${ctx }/admin/area.do?method=toBatchEdit&batchType=${type}&pid=${prid }"
					target="navTab" external="true" title="批量更新"><span>单产品多区域区域价格-批量更新</span>
				</a></li>
			</c:if>
			<c:if test="${prid == -1 }">
				<li style="display:none;"><a class="add"
					href="${ctx }/admin/area.do?method=toBatchEdit&batchType=${type}"
					target="navTab" external="true" title="批量更新"><span>单区域多产品价格-批量更新</span>
				</a></li>
			</c:if>

			<c:if test="${type==1 || type== -1}">
				<li><a class="icon" rel="page43" target="navTab"
					href="${ctx }/admin/area/product.do?type=1&prid=${prid }"
					target="navTab" title="区域价格"><span>刷新</span> </a></li>
			</c:if>

			<c:if test="${type==2}">
				<li><a class="icon" rel="page43" target="navTab"
					href="${ctx }/admin/area/product.do?type=2&prid=${prid }"
					target="navTab" title="区域价格"><span>刷新</span> </a></li>
			</c:if>

			<c:if test="${type==4}">
				<li><a class="icon" rel="page43" target="navTab"
					href="${ctx }/admin/area/product.do?type=4&prid=${prid }"
					target="navTab" title="区域价格"><span>刷新</span> </a></li>
			</c:if>

			<c:if test="${type==3}">
				<li><a class="icon" rel="page43" target="navTab"
					href="${ctx }/admin/area/product.do?type=3&prid=${prid }"
					target="navTab" title="区域价格"><span>刷新</span> </a></li>
			</c:if>
		</ul>
	</div>
	<form id="price-list-form">
		<input type="hidden" name="pageid" value="page43">
		<table class="table " width="100%" layoutH="138">
		<thead>
			<tr>
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
					<td width="50" align="center">
						${s.id }
					</td>
					<td>${s.regionsName }</td>
					<c:if test="${type !=3 }">
						<td><a title="编辑"
							href="${ctx }/admin/area.do?method=toProductEdit&id=${s.productregionsId }"
							target="navTab" external="true">${s.pname }</a></td>
					</c:if>
					<td width="50" align="center">
						<input type="text" name="pricename" value="${s.price }">
					</td>

					<td width="120" align="center"><a title="编辑" target="navTab"
						href="${ctx }/admin/area.do?method=toProductEdit&id=${s.id }"
						external="true" class="btnEdit">编辑</a> <a title="删除无法恢复"
						target="ajaxTodo"
						href="${ctx }/admin/area.do?method=shipDelete&id=${s.id }"
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
	
<div  cid="${cid }" class="area-float-panel float-panel" id="hs-float-panel-1">
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


