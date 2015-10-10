<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<style type="text/css">
body,td {font-size:13px;}
</style>

<h1 align="center">订单信息</h1>
<table width="100%" cellpadding="1">
    <tr>
        <td width="40px">顾客名称：</td><td>${user.nickname }</td>
        <td align="right">下单时间：</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.addtime}" /></td>
        <td align="right">订单编号：</td>
        <td>${order.osn }</td>
        <td align="right">支付方式：</td>
        <td>
        <c:choose>
			<c:when test="${order.shipfriendname == '工厂自提' }">
				到厂支付
			</c:when>
			<c:when test="${order.shipfriendname == '门店自提' }">
				门店支付
			</c:when>
			<c:otherwise>
				货到付款
			</c:otherwise>
		</c:choose>
		</td>
    </tr>
    <tr>
        <td>付款时间：</td><td> 未付款 </td>
        <td align="right">配送方式：</td>
        <td>
			${order.shipfriendname}
		</td>
        <td align="right">
        <c:choose>
			<c:when test="${order.shipfriendname == '货到付款' }">
				最佳配送时间:
			</c:when>
			<c:otherwise>
				自提时间:
			</c:otherwise>
		</c:choose>
		</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${order.besttime }" /> </td>
        <td align="right">发货单号：</td><td></td>
    </tr>
    <tr>
        <td>收货地址：</td>
        <td colspan="7">
           ${regions.provincename }${regions.cityname }${regions.countyname }${regions.streetname }${regions.name }${order.address }
 	
        收货人：${order.consignee } &nbsp;
        手机号：${order.mobile }&nbsp;
        固定电话：${order.phone}&nbsp;
        </td>
    </tr>
</table>
<table width="100%" border="1" style="border-collapse:collapse;border-color:#000;">
    <tr align="center">
        <td bgcolor="#cccccc">商品名称</td>
        <td bgcolor="#cccccc">货号</td>
        <td bgcolor="#cccccc">价格</td>
        <td bgcolor="#cccccc">数量</td>
        <td bgcolor="#cccccc">小计</td>
    </tr>
    

    <c:forEach  items="${orderproductsList}" varStatus="status" var="item">
    <tr>
        <td>${item.name }</td>
        <td>${item.psn }</td>
        <td align="right">￥${item.marketprice}</td>
        <td align="right">${item.buycount  }</td>
        <td align="right">￥${item.marketprice * item.buycount }</td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="4"></td>
        <td colspan="2" align="right">商品总金额：￥${order.productamount}</td>
    </tr>
</table>
<table width="100%" border="0">
    <tr align="right">
        <td>配送费用：￥${order.handlingcost } + 装卸费用：￥${order.shipfee } = 订单总金额：￥${order.orderamount }</td>
    </tr>
    <tr align="right">
        <td>= 应付款金额：￥${order.orderamount }</td>
    </tr>
</table>
<table width="100%" border="0">
    <tr>
        <td>${webinfor.shopName }(${webinfor.siteUrl })</td>
    </tr>
    <tr align="right">
        <td>打印时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${date}" /> &nbsp;&nbsp;&nbsp;操作者：管理员</td>
    </tr>
</table>
