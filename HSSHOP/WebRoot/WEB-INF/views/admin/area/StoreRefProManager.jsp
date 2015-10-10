<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/adminproduct.do?method=storeAndNcProinforList">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="20" />
</form>
<script type="text/javascript">
	var removeProductRegions = function(prid) {
		$("#tr_" + prid).remove();
	};

	$(document).ready(
			function() {
				//保存表格信息按钮
				$(".proinfor-save-table-btn").bind("click", function(e) {
					var postDate = $("#proinfor-price-list-form").serialize();
					$.ajax({
						url : url + '/admin/adminproduct.do?method=saveNcenterinforList',
						type : 'post',
						data : postDate,
						dataType : 'json',
						success : function(data) {
							if (data.statusCode==200) {
								alertMsg.correct("保存成功");
								//$("#page49-refresh-btn").trigger("click");
							} else {
								alertMsg.error(data);
							}
						}

					});
				});
			});
</script>
<div class="pageContent">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action=""
		method="post" id="search-form" class="search-form">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>NC门店名称或编号：</label>
					<input type="text" name="storeKeyWord" value="${selVo.storeKeyWord}" /></li>
				<li><label>NC工厂名称或编号：</label>
					<input type="text" name="facKeyWord" value="${selVo.facKeyWord}" /></li>
				<li><label>NC产品名称或编号：</label>
					<input type="text" name="proKeyWord" value="${selVo.proKeyWord}" /></li>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="icon" target="navTab"  
					href="${ctx}/admin/adminproduct.do?method=toAddNcProinfor" title="新建允销目录条目">
					<span>新建</span> 
				</a>
			</li>
			<li><a class="icon proinfor-save-table-btn"  title="保存"><span>保存</span> </a></li>
			<li><a class="icon" rel="page48" target="navTab" id="page48-refresh-btn"
				href="${ctx}/admin/adminproduct.do?method=storeAndNcProinforList"
				title="允销目录管理"><span>刷新</span> </a></li>
		</ul>
	</div>
	<form id="proinfor-price-list-form">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="22">
						<input type="checkbox" group="ids" class="checkboxCtrl">
					</th>
					<th>门店NC编号</th>
					<th>门店名称</th>
					<th>公司NC编号</th>
					<th>公司名称</th>
					<th>产品NC编号</th>
					<th>产品名称</th>
					<th>产品单价</th>
					<th width="120" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${rsvo.resList}" varStatus="status">
					<tr id="tr_${s.id }">
						<td><input name="ids" value="${s.id }" type="checkbox">
						</td>
						<td >${s.eid }</td>
						<td><a title="编辑"
							href="${ctx }/admin/area.do?method=toSaleaddressEdit&saleid=${s.sid }&type=1&title=区域门店编辑"
							target="navTab" external="true">${s.ename }</a></td>
						<td>${s.pkcorp }</td>
						<td>${s.corpname }</td>
						<td>${s.pid }</td>
						<td>${s.pname }</td>
						<td >
							<input type="text" name="ncproList[${status.index }].price" value="${s.price }" size="10">
							<input type="hidden" name="ncproList[${status.index }].id" value="${s.id}" >
						</td>
						<td width="120" align="center">
							<a title="编辑" target="navTab" href="${ctx}/admin/adminproduct.do?method=toUpNcProinfor&id=${s.id}"
							class="btnEdit">编辑</a>
							 <a title="删除无法恢复" target="ajaxTodo"  class="btnDel"
							 href="${ctx}/admin/adminproduct.do?method=delNcProinforById&id=${s.id}"
							>删除</a>
						</td>
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
			</select> <span>条，共${rsvo.totalnum}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${rsvo.totalnum}"
			numPerPage="${rsvo.pagenum }" pageNumShown="10"
			currentPage="${rsvo.curpage }">
		</div>
	</div>
</div>



