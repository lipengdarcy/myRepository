<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>水泥-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/list.css" rel="stylesheet"
	type="text/css" />
</head>

<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>
	<%-- <%@ include file="../includes/topMenu.jsp"%> --%>
	
	<div class="breadcrumb box1210"></div>
	<div class="box1210">

		<div id="listL">
			<c:import url="${ctx }/tool/leftMenu.do"></c:import> 
			<%-- <%@ include file="../includes/leftMenu.jsp"%> --%>
			<%@ include file="../../../includes/home/recommended.jsp"%>
		</div>
		<div id="listR">
		<%@ include file="../includes/rightMenu.jsp"%>
		
			<div class="proList">
				<ul class="cate-list">
					<c:forEach items="${productList}" var="p">


						<li><a href="${ctx}/product/detail.do?id=${p.pid}"><img
								src="${ctx}/upload/product/source/${p.showimg}" onerror="nofind();"
								width="218" height="218" /><em><c:out value="${p.name}" /></em></a>
							
							<div class="info2">
								<span onclick="addToFavorite('${p.pid}')"> + 加入收藏</span><a
									href="javascript:addProductToCart(${p.pid}, 1)" class="addShopping">加入购物车</a>
							</div></li>

					</c:forEach>
					<div class="clear"></div>
				</ul>
				${pageDiv }
				
			</div>
		</div>
	</div>
	<div class="clear"></div>
	</div>
	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>