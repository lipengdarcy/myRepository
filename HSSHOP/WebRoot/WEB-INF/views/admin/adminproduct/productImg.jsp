<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<style>
.up-product-img-content div {
	display: block;
}

.up-product-img-content .button {
	float: none;
}

.up-product-img-content input {
	
}

.up-product-img-content table {
	border: 0;
}

#product-upload-img-span {
	border: 1px solid gray;
	display: inline-block;
}

#product-upload-img-span div {
	padding: 100px 80px;
}

#product-upload-img-span img {
	width: 200px;
}

.margin-ele {
	margin: 8px !important;
}

#procuct-img-td div {
	height: auto !important;
}
</style>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post"
		id="search-form"></form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" id="add-product-img-item-btn"><span>添加图片</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<!-- th width="50" align="center" orderField="uid" class="${orderDirection}">主键ID</th-->
				<th>编号</th>
				<th>图片</th>
				<th>属性</th>
				<th>操作</th>
				<th>排序</th>
				<th width="80" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="props" items="${productPics}" varStatus="status">
				<tr target="sid_user" rel="${props.pimgid}"
					class="<c:choose>
							<c:when test="${status.count%2==0}">
								gvRowStyle
							</c:when>
							<c:otherwise>
								gvAlternatingRowStyle
							</c:otherwise>
						</c:choose>">
					<td><input name="ids" value="${props.pimgid}" type="checkbox">
					</td>
					<td>${props.pimgid}</td>
					<td id="procuct-img-td"><a
						href="${ctx }/upload/product/source/${props.showimg}"
						target="_blank"><img style="width: 100px;"
							src="${ctx }/upload/product/source/${props.showimg}" /></a></td>
					<td><c:if test="${props.ismain=='0'}">
							普通图片
						</c:if> <c:if test="${props.ismain=='1'}">
							主图
						</c:if> <c:if test="${props.ismain=='2'}">
							首页大图
						</c:if> <c:if test="${props.ismain=='3'}">
							首页小图
						</c:if></td>
					<td>
						<!--<c:if test="${props.ismain=='0'}">
							<a title="你确定设置该图片为主图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=1"
								style="cursor: pointer;" class="table-button">主图</a>
						</c:if>--> <c:if test="${props.ismain=='0'}">
							<a title="你确定设置该图片为首页大图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=2"
								style="cursor: pointer;" class="table-button">首页大图</a>
						</c:if> <c:if test="${props.ismain=='0'}">
							<a title="你确定设置该图片为首页小图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=3"
								style="cursor: pointer;" class="table-button">首页小图</a>
						</c:if> <!-- <c:if test="${props.ismain=='1'}">
							<a title="你确定设置该图片为普通图片？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=0"
								style="cursor: pointer;" class="table-button">普通</a>
						</c:if> <c:if test="${props.ismain=='1'}">
							<a title="你确定设置该图片为首页大图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=2"
								style="cursor: pointer;" class="table-button">首页大图</a>
						</c:if> <c:if test="${props.ismain=='1'}">
							<a title="你确定设置该图片为首页小图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=3"
								style="cursor: pointer;" class="table-button">首页小图</a>
						</c:if> --> <c:if test="${props.ismain=='2'}">
							<a title="你确定设置该图片为普通图片？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=0"
								style="cursor: pointer;" class="table-button">普通</a>
						</c:if> <!-- <c:if test="${props.ismain=='2'}">
							<a title="你确定设置该图片为主图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=1"
								style="cursor: pointer;" class="table-button">主图</a>
						</c:if>  --> <c:if test="${props.ismain=='2'}">
							<a title="你确定设置该图片为首页小图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=3"
								style="cursor: pointer;" class="table-button">首页小图</a>
						</c:if> <c:if test="${props.ismain=='3'}">
							<a title="你确定设置该图片为普通图片？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=0"
								style="cursor: pointer;" class="table-button">普通</a>
						</c:if> <!--<c:if test="${props.ismain=='3'}">
							<a title="你确定设置该图片为主图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=1"
								style="cursor: pointer;" class="table-button">主图</a>
						</c:if>   --> <c:if test="${props.ismain=='3'}">
							<a title="你确定设置该图片为首页大图？" target="ajaxTodo"
								href="${ctx }/admin/adminproduct.do?method=setMainImg&pimgid=${props.pimgid}&showimg=${props.showimg}&pid=${props.pid}&type=2"
								style="cursor: pointer;" class="table-button">首页大图</a>
						</c:if>
					</td>
					<td>${props.displayorder}</td>
					<td><a title="删除无法恢复" target="ajaxTodo"
						href="${ctx }/admin/adminproduct.do?method=deleteImg&pimgid=${props.pimgid}"
						class="btnDel">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div style="display: none;">
		<form action="${ctx }/admin/adminproduct.do?method=upload"
			method="post" enctype="multipart/form-data" target="productImgIframe">
			<input type="file" name="productImg" id="product-img-file"> <input
				type="submit" id="product-img-submit">
		</form>
		<iframe name="productImgIframe" style="display: none"> </iframe>
	</div>
</div>
<script>
	var picoptions = {
		width : 400,
		height : 500,
		max : false,
		mask : true,
		maxable : false,
		minable : false,
		resizable : true,
		drawable : true,
		fresh : true,
		close : function() {
			//alert("${ctx }/admin/adminproduct.do?method=toProductImg&pid=${productid}");
			navTab
					.reload("${ctx }/admin/adminproduct.do?method=toProductImg&pid=${productid}");
			return true;
		}
	}
	$("#add-product-img-item-btn")
			.bind(
					"click",
					function(e) {
						$.pdialog
								.open(
										"${ctx }/admin/adminproduct.do?method=addProductImgTmp&pid=${productid}",
										"add-product-img-item-dlg", "添加新的图片",
										picoptions);
						//借助该元素存储对话框触发事件
					});

	//function
</script>
