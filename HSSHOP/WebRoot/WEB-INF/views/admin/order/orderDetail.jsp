<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
 <style type="text/css">
    .addTable table{ border-right:1px solid #d1d1d1;border-bottom:1px solid #d1d1d1; margin-top:20px; margin-left:16px; } 
    .addTable table th{ background:#dbeffa; font-weight:normal;height:26px; padding-left:4px;} 
    .addTable table th a{ padding-left:8px;} 
    .addTable table td{ border-left:1px solid #d1d1d1;border-top:1px solid #d1d1d1;height:26px; padding-left:10px;}
    .addTable table td.pImg{ padding:10px; }
    .addTable{overflow:auto}
    .tabsPage .tabsPageContent {
  display: block;
  overflow: auto;
  border-style: solid;
  border-width: 0 1px 1px 1px;
  position: relative;
}
.has-pay-online{
	color:green;
	font-weight:bold;
}
.not-pay-online{
	color:red;
	font-weight:bold;
}
 </style>
<script type="text/javascript">
	function verification(orderNum){
		var VerificationCode=$("#VerificationCode").val();
		if(VerificationCode==""){
			alert("验证码不能为空！");
			return;
		}
		$.ajax({
			url:'${ctx }'+'/admin/order/verification.do',// 跳转到 action    
			data:{    
				VerificationCode : VerificationCode,
				orderNum : orderNum,
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					alert("验证成功！");
				}else{
					alert(data.content);
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}  
		});
	}
	
	function nofind(){

		var img=event.srcElement;

		img.src='${ctx }'+'/upload/product/default.png';

		img.onerror=null; //控制不要一直跳动

		}
</script>
<form action="${ctx }/admin/order/orderinfo?oid=30" method="post"><div class="addTable">
<table width="97%">
  <tr><th colspan="6" align="left">基本信息</th></tr>
  <tr>
    <td width="150px">订单序号：</td>
    <td width="320px">${order.oid}</td>
    <td width="150px">订单编号：</td>
    <td colspan="3">${order.osn }</td>
    <!-- td width="150px">验证码校验：</td>
    <td>
    <input type="button" value="验证码验证" class="add" id="add-product-verification">
    </td> -->
  </tr>
  <tr>
    <td width="150px">订单状态：</td>
    <td>${enumoderState }</td>
    <td >下单时间：</td>
    <td colspan="3">
    	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.addtime}" />
	</td>
  </tr>
</table>

<table width="97%">
  <tr><th colspan="4" align="left">用户信息</th></tr>
  <tr>
    <td width="150px">用户编号：</td>
    <td width="320px">${user.uid }</td>
    <td width="150px">用户名：</td>
    <td>${user.username }</td>
  </tr>
  <tr>
    <td>真实姓名：</td>
    <td>${userdetails.realname}</td>
    <td>性别：</td>
    <td>${userdetails.gender  }</td>
  </tr>
  <tr>
    <td>用户昵称：</td>
    <td>${user.nickname }</td>
    <td>用户等级：</td>
    <td>${userrank.title }</td>
  </tr>
  <tr>
    <td>邮箱号：</td>
    <td>${user.email }</td>
    <td>手机号：</td>
    <td>${user.mobile }</td>
  </tr>
</table>

<table width="97%">
  <tr><th colspan="4" align="left">配送信息</th></tr>
  <tr>
    <td width="150px">配送方式：</td>
    <td width="320px">${order.shipfriendname }
	</td>
    <td width="150px">
 	<c:choose>
		<c:when test="${order.shipfriendname == '门店自提' }">
			自提时间：
		</c:when>
		<c:otherwise>
			配送时间：
		</c:otherwise>
	</c:choose>   
    
    </td>
    <td>
    <fmt:formatDate pattern="yyyy-MM-dd" value="${order.shiptime }" />
   </td>
  </tr>
  <tr>
    <td>配送单号：</td>
    <td>${order.shipsn }</td>
    <td>配送费用：</td>
    <td>￥${order.shipfee }</td>
  </tr>
</table>

<table width="97%">
  <tr><th colspan="4" align="left">支付信息</th></tr>
  <tr>
    <td width="150px">支付方式：</td>
    <td width="320px">
		<c:choose>
			<c:when test="${order.paymode == '1' }">
				到厂支付
			</c:when>
			<c:when test="${order.paymode == '2' }">
				门店支付
			</c:when>
			<c:when test="${order.paymode == '3' }">
				货到付款
			</c:when>
			<c:otherwise>
				在线支付
			</c:otherwise>
		</c:choose>
	</td>
    <td width="150px">支付时间：</td>
    <td>
        <c:if test="${empty order.paytime }">
        	未支付
        </c:if>
         <c:if test="${!empty order.paytime }">
        	${order.paytime }
        </c:if>
   </td>
   
  </tr>
  <c:if test="${order.paymode=='4' && order.paystate!='0'}">
	  <tr>
		  <td>支付状态：</td>
		  <td colspan="3" class="has-pay-online">支付已完成！</td>
	  </tr>
  </c:if>	
  <c:if test="${order.paymode=='4' && order.paystate=='0'}">
	  <tr>
		  <td>支付状态：</td>
		  <td colspan="3" class="not-pay-online">未支付！</td>
	  </tr>
  </c:if>
  <tr>
    <td>支付单号：</td>
    <td></td>
    <td>手续费用：</td>
    <td>￥0.00</td>
  </tr>
</table>

<table width="97%">
  <tr><th colspan="4" align="left">收货地址</th></tr>
  <tr>
    <td width="150px">收货人：</td>
    <td width="320px">${order.consignee }</td>
    <td width="150px">邮箱：</td>
    <td>${order.email }</td>
  </tr>
  <tr>
    <td>手机号：</td>
    <td>${order.mobile }</td>
    <td>固话号：</td>
    <td>${order.phone }</td>
  </tr>
  <tr>
    <td>邮编：</td>
    <td>${order.zipcode }</td>
    <td>最佳时间：</td>
    <td></td>
  </tr>
  <tr>
      <td>配送地址：</td>
    <td colspan="3">
    	     ${regions.provincename }${regions.cityname }${regions.countyname }${regions.streetname }${regions.name } ${order.address }</p>
    </td>
  </tr>
</table>
<c:if test="${order.shipsystemname < 3}">
	<table width="97%">
	<tr><th colspan="2" align="left">自提证件信息</th></tr>
		<c:forEach var="extitem" items="${orderext }">
		<tr>
			<c:if test="${extitem.oexttype==1 }">
				<div>
					<td width="150px">车牌号:</td>
					<td>${extitem.oextvalue }</td>
				</div>
			</c:if>
			<c:if test="${extitem.oexttype==2 }">
				<div>
					<td width="150px">证件号					
						<c:if test="${extitem.oextlabelvalue==1 }">
							<span>(身份证): </span>
						</c:if>
					</td>
					<td>
						<c:if test="${extitem.oextlabelvalue==2 }">
							<span>驾驶证 </span>
						</c:if>
						<span>${extitem.oextvalue }</span>
					</td>
				</div>									
			</c:if>
		</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${order.needinv ==1}">
	<table width="97%">
		<tr><th  colspan="4">发票信息</th></tr>		
			<c:if test="${not empty invoice}">
				<c:if test="${invoice.invtype==1}">
				<tr>
					<td width="150px">发票类型:</td><td>普通发票</td>
					<td width="150px">发票抬头:</td><td>${invoice.invtitle}</td>
				</tr>
				<tr>
					<td width="150px">发票内容:</td><td colspan="3">${invoice.invcontent}</td>
				</tr>
				</c:if>
				<c:if test="${invoice.invtype==2}">
				<tr>
					<td width="150px">发票类型:</td><td>增值税发票</td>
					<td width="150px">单位名称:</td><td>${invoice.invcomname}</td>
				</tr>
				<tr>
					<td width="150px">纳税人识别码:</td><td>${invoice.invtaxnum}</td>
					<td width="150px">注册地址:</td><td>${invoice.invaddress}</td>
				</tr>
				<tr>
					<td width="150px">注册电话:</td><td>${invoice.invtel}</td>
					<td width="150px">开户银行:</td><td>${invoice.invbankname}</td>
				</tr>
				<tr>
					<td width="150px">银行账户:</td><td colspan="3">${invoice.invbankno}</td>
				</tr>
				<tr>
					<td width="150px">发票内容:</td><td colspan="3">${invoice.invcontent}</td>
				</tr>
				<tr>
					<td width="150px">收票人姓名:</td><td>${invoice.invrecname}</td>
					<td width="150px">收票人手机:</td><td>${invoice.invrectel}</td>
				</tr>
				<tr>
					<td width="150px">收票人省份:</td><td colspan="3">${invoice.regionname}</td>
				</tr>
				<tr>
					<td width="150px">详细地址:</td><td colspan="3">${invoice.invdetailaddr}</td>
				</tr>	
				</c:if>
			</c:if>
	</table>
</c:if>

<table width="97%">
  <tr><th colspan="6" align="left">订单商品</th></tr>
  <tr>
    <td align="center" width="60">商品图片</td>
    <td>商品名称</td>
    <td width="140">货号</td>
    <td width="80">价格</td>
    <td width="50">数量</td>
    <td width="80">小计</td>
  </tr>
  <c:forEach  items="${orderproductsList}" varStatus="status" var="item">
    <tr>
        <td align="center" class="pImg"><img width="60px" height="60px" src="${ctx }/upload/product/thumb/245_330/${item.showimg } " onerror="nofind();"/></td>
        <td>${item.name }</td>
        <td>${item.psn }</td>
        <td>￥${item.marketprice}</td>
        <td>${item.buycount  }</td>
        <td>￥${item.marketprice * item.buycount }</td>
    </tr>
  </c:forEach>
</table>


<table width="97%">
  <tr><th colspan="4" align="left">订单金额</th></tr>
  <tr>
    <td width="150px">商品合计：</td>
    <td>￥${order.productamount}</td>
    <td width="150px">满减：</td>
    <td>0</td>
  </tr>
  <tr>
    <td width="150px">订单合计：</td>
    <td width="320px">￥${order.orderamount }</td>
   <td>优惠劵金额：</td>
    <td>0</td>
  </tr>
  
  <tr>
    <td width="150px">运费：</td>
    <td>￥${order.shipfee }</td>
    <td>装卸费：</td>
    <td>￥${order.handlingcost }</td>
  </tr>
    <tr>
    <td width="150px">应付金额：</td>
    <td>￥${order.surplusmoney }</td>
    
    <td>订单折扣：</td>
    <td>￥0.00</td>
  </tr>
</table>

<table width="97%">
  <tr><th align="left">备注信息</th></tr>
  <tr><td style="padding:0px 10px;">${order.buyerremark }</td></tr>
</table>

<table width="97%">
  <tr><th colspan="6" align="left">发货信息</th></tr>
  <tr>
    <td align="center" width="60">商品图片</td>
    <td>商品名称</td>
    <td width="140">货号</td>
    <td width="80">水泥编号</td>
    <td width="50">发货数量</td>
    <td width="80">小计</td>
  </tr>
  <c:forEach  items="${orderproductsList}" varStatus="status" var="item">
    <tr>
        <td align="center" class="pImg"><img width="60px" height="60px" src="${ctx }/upload/product/thumb/245_330/${item.showimg } " onerror="nofind();"/></td>
        <td>${item.name }</td>
        <td>${item.sendbatch }</td>
        <td>￥${item.marketprice}</td>
        <td>${item.sendcount  }</td>
        <td>￥${item.marketprice * item.sendcount }</td>
    </tr>
  </c:forEach>
</table>

<table width="97%">
  <tr><th colspan="4" align="left">管理操作</th></tr>
  <tr>
    <td width="100px">
    <div id="operate">
        <c:choose>
			<c:when test="${order.orderstate == 0 }">
		    	<a class="button" href="${ctx }/admin/order/operate.do?orderId=${order.oid}&optType=6" target="dialog" rel="dlg_page10" mask="true" title="确认订单" id="confirm-bill-btn"><span>确认订单</span></a>
 					<a class="button" href="${ctx }/admin/order/operate.do?orderId=${order.oid}&optType=12" target="dialog" rel="dlg_page10" mask="true" title="锁定订单"><span>锁定订单</span></a>
 					<a class="button" href="${ctx }/admin/order/operate.do?orderId=${order.oid}&optType=14" target="dialog" rel="dlg_page10" mask="true" title="锁定订单"><span>取消订单</span></a>
			</c:when>
			
		</c:choose>
    </div>
    </td>
  </tr>
</table>

<table width="97%">
  <tr>
    <td width="80px">操作人</td>
    <td width="120px">管理员组</td>
    <td width="150px">操作时间</td>
    <td>操作备注：</td>
  </tr>
 <c:forEach  items="${orderactionsList}" varStatus="status" var="item">
  <tr>
    <td>${item.realname }</td>
    <td>${item.admingtitle }</td>
    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.actiontime}" /></td>
    <td>${item.actiondes }</td>
  </tr>
  </c:forEach>
  
</table>

<br />
</div>    
<script>
	var picoptions={
		width:300,
		height:300,
		max:false,
		mask:true,
		maxable:false,
		minable:false,
		resizable:true,
		drawable:true,
		fresh:true,
		close:function(){
			$.pdialog.close("add-product-verification");
			return true;
		}
		}
	$("#add-product-verification").bind("click",function(e){
		$.pdialog.open("${ctx }/admin/order/getVerification.do?oid=${order.oid}", "add-product-verification", "短信验证", picoptions);
		//借助该元素存储对话框触发事件
	});
	var notPay=$(".not-pay-online");
	if(notPay.length>0){
		var conmbtn=$("#confirm-bill-btn");
		if(conmbtn.length>0){
			var oldHref=conmbtn.attr("href");
			conmbtn.attr("href",oldHref+"&notpay=true");
		}
	}
	//function 
</script>
</form>