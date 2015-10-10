﻿<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/area/saleaddress.do">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${numPerPage }" />	
</form>
<script type="text/javascript">
	var removeProductRegions = function(prid, saleid){
		$("#tr_"+prid+"_"+saleid).remove();
	};
</script>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="${ctx }/admin/area.do?method=toSaleaddressAdd" target="navTab" external="true" title="添加" ><span>区域工厂/门店-添加</span> </a>
			</li>
			<li>
				<a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
					href="area.do?method=deleteSelectRegionsPlace" class="delete">
					<span>批量删除</span>
				</a>
			</li>
			<li>
				<a class="icon" rel="page44" target="navTab" href="${ctx }/admin/area/saleaddress.do" target="navTab" title="区域工厂/门店" ><span>刷新</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="70" align="center">类型</th>
				<th >区域</th>
				<th >名称</th>
				<th >地址</th>
				<th align="center">联系人</th>
				<th align="center">联系电话</th>
				<th align="center">工作时间</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.productregionsId }_${s.saleaddressId }" >
					<td>
						<input name="ids" value="${s.saleaddressId }" type="checkbox">
					</td>
					<td width="70" align="center">${my:getSaleaddressType(s.type) }</td>
					<td><a title="编辑" href="${ctx }/admin/area.do?method=toSaleaddressEdit&prid=${s.productregionsId }&saleid=${s.saleaddressId }" target="navTab" external="true" >${s.regionsName }</a></td>
					<td>${s.name }</td>
					<td>${s.address }</td>
					<td>${s.contacts }</td>
					<td>${s.tel }</td>
					<td>${s.workTime }</td>
					<td width="120" align="center">
						<a title="编辑" target="navTab" href="${ctx }/admin/area.do?method=toSaleaddressEdit&prid=${s.productregionsId }&saleid=${s.saleaddressId }" external="true" class="btnEdit" >编辑</a>
						<a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/area.do?method=saleaddressDelete&prid=${s.productregionsId }&saleid=${s.saleaddressId }" class="btnDel" callback="removeProductRegions(${s.productregionsId},${s.saleaddressId })" >删除</a>
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
