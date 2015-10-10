<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/admin/newsMng/news.do">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${numPerPage }" />
</form>
<script type="text/javascript">
	var removeTrById = function(id) {
		$("#tr_" + id).remove();
	};
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post">
		<div class="searchBar">
			<!--<ul class="searchContent">
			<li>
				<label>我的客户：</label>
				<input type="text"/>
			</li>
			<li>
			<select class="combox" name="province">
				<option value="">所有省市</option>
				<option value="北京">北京</option>
				<option value="上海">上海</option>
				<option value="天津">天津</option>
				<option value="重庆">重庆</option>
				<option value="广东">广东</option>
			</select>
			</li>
		</ul>
		-->
			<table class="searchContent">
				<tr>
					<td>新闻标题：<input type="text" name="keyword" />
					</td>
					<td><select class="combox" name="province">
							<option value="">所有分类</option>
							<option value="1">最新动态</option>
							<option value="2">促销活动</option>
							<option value="9">限时抢购</option>
					</select></td>
					<!-- <td>建档日期：<input type="text" class="date" readonly="true" />
					</td> -->
				</tr>
			</table>
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
			<li><a class="add"
				href="${ctx }/admin/newsMng.do?method=toNewsAdd" target="navTab"
				title="添加"><span>新闻-添加</span> </a></li>
			<li><a class="icon" rel="page11" target="navTab"
				href="${ctx }/admin/newsMng/news.do" target="navTab" title="新闻管理"><span>刷新</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">编号</th>
				<th>新闻类型</th>
				<th>新闻标题</th>
				<th>是否显示</th>
				<th>是否置顶</th>
				<th>是否置首</th>
				<th>排序</th>
				<th>添加时间</th>
				<th width="120" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${dataList}" varStatus="status">
				<tr id="tr_${s.newsid }">
					<td width="50" align="center">${s.newsid }</td>
					<td>${s.newstypename }</td>
					<td><a title="编辑"
						href="${ctx }/admin/newsMng.do?method=toNewsEdit&id=${s.newsid }"
						target="navTab">${s.title }</a></td>
					<td>${my:getYesOrNoDesc(s.isshow) }</td>
					<td>${my:getYesOrNoDesc(s.istop) }</td>
					<td>${my:getYesOrNoDesc(s.ishome) }</td>
					<td>${s.displayorder }</td>
					<td>${s.addtime }</td>
					<td width="120" align="center"><a title="编辑" target="navTab"
						href="${ctx }/admin/newsMng.do?method=toNewsEdit&id=${s.newsid }"
						class="btnEdit">编辑</a> <a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/newsMng.do?method=newsDelete&id=${s.newsid }"
						class="btnDel" callback="removeTrById(${s.newsid})">删除</a></td>
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
</div>
