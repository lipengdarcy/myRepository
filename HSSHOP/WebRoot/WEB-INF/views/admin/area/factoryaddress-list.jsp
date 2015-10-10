<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/area/factoryaddress.do">
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
				<li style="width:30px">
					<img id="searchImg" width="22px" height="22px" src="../themes/default/images/search1.gif"></li>
				<li>
					<label>公司名称：</label> 
					<input type="text" name="companyName" value="${companyName}" />
				</li>
				<li>
					<label>公司地址：</label> 
					<input type="text" name="companyAddr" value="${companyAddr}" />
				</li>
				
				<li>
					<label>联系人：</label> 
					<input type="text" name="companyCont" value="${companyCont}" />
				</li>
				
				<li>
					<label>NC编号：</label> 
					<input type="text" name="ncNum" value="${ncNum}" />
				</li>
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
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="${ctx }/admin/area.do?method=toSaleaddressAdd&type=2&title=添加工厂" target="navTab" external="true" title="添加" ><span>区域工厂-添加</span> </a>
			</li>
			<!--li>
				<a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
					href="area.do?method=deleteSelectFacList" class="delete">
					<span>批量删除</span>
				</a>
			</li>  -->
			<li>
				<a class="icon" rel="page44" target="navTab" href="${ctx }/admin/area/factoryaddress.do" target="navTab" title="区域工厂/门店" ><span>刷新</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<!--th width="22">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th-->
				<!-- <th width="70" align="center">类型</th> -->
				<th width="70" align="center">编号</th>
				<th >名称</th>
				<th >地址</th>
				<th align="center">联系人</th>
				<th align="center">联系电话</th>
				<th align="center">坐标</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.id }" >
					<!--  td>
						<input name="ids" value="${s.id }" type="checkbox">
					</td>-->
					<%-- <td width="70" align="center">${my:getSaleaddressType(s.type) }</td> --%>
					<td align="center">${s.id }</td>
					<td>${s.name }</td>
					<td>${s.address }</td>
					<td>${s.contacts }</td>
					<td>${s.tel }</td>
					<td>${s.workTime }</td>
					<td width="120" align="center">
						<a title="编辑" target="navTab" href="${ctx }/admin/area.do?method=toSaleaddressEdit&saleid=${s.id }&type=2&title=区域工厂编辑" external="true" class="btnEdit" >编辑</a>
						<a title="删除无法恢复" target="ajaxTodo" href="${ctx }/admin/area.do?method=saleaddressDelete&saleid=${s.id }" class="btnDel" callback="removeProductRegions(${s.id})" >删除</a>
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
