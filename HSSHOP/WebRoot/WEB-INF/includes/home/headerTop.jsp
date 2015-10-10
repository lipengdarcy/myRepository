<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script type="text/javascript">

//搜索
function search(keyword) {
	if (keyword == undefined || keyword == null || keyword.length < 1) {
		alert("请输入关键词");
		
	} else {
		//alert($("#keyword").val());
		window.location.href = "${ctx}/product/search.do?keyword=" + keyword;
		//alert(document.getElementById('keyword').value);
		//alert(keyword);
	}
}
</script>

<div id="header" class="bigBox clearfix">
	<div class="box1210 clearfix" id="header1">
		<a href="${ctx}/index.do" class="logo left" title="红狮水泥商城"><img
			src="${ctx}/themes/default/images/logo.jpg" alt="红狮水泥商城" /></a>
		<div class="search left">
			
			<div class="searchCon">
				<input type="text" id="keyword" name="keyword" class="Stext"
					autocomplete="off"
					onkeydown="javascript:if(event.keyCode==13) search(document.getElementById('keyword').value);"
					value="" /> <input name="" type="button" value="搜索"
					class="Sbutton"
					onclick="search(document.getElementById('keyword').value)" />
				<div class="clear"></div>
			</div>

			<div class="searchHot">
				热门搜索： <a href="${ctx}/product/search.do?keyword=红狮">红狮</a> <a
					href="${ctx}/product/search.do?keyword=水泥">水泥</a>
			</div>


		</div>

		<div class="shopping right" onmousemove="getCartSnap()"
			id="cartSnapBox" onmouseout="closeCartSnap(event)">
			<h2>
				<a href="${ctx }/cart.do">我的购物车<b id="cartSnapProudctCount" class="cartnum-ele">
					<c:if test="${empty sessionScope.cartCount }">0</c:if>
					<c:if test="${not empty sessionScope.cartCount }">${sessionScope.cartCount }</c:if>					
				</b></a>
			</h2>
			<div id="cartSnap" class="shoppingList" style="display: none;">
				<div class="loding">
					<img src="${ctx }/themes/default/images/loading.gif" /><br />加载中，请稍候...
				</div>
			</div>
		</div>

	</div>
</div>