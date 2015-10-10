<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<div class="itme infoList1">
	<h2>推荐商品</h2>
	<div class="proList">
		<ul>
			<c:forEach items="${rProductList}" var="rp">
				<li><a href="${ctx}/product/detail.do?id=${rp.pid}"><img onerror="nofind();"
						src="${ctx}/upload/product/source/${rp.showimg}"
						width="100%" /><em><c:out value="${rp.name}" /></em></a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="clear"></div>
</div>