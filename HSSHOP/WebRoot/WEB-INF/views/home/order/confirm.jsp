<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>确认订单-红狮水泥商城</title>
		<meta name="keywords" content="红狮水泥商城" />
		<meta name="description" content="红狮水泥商城" />
		<%@ include file="../../../includes/home/header.jsp"%>
	
		<link href="${ctx }/themes/default/css/buy.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx }/scripts/order.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx }/scripts/date/need/laydate.css" />
		<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/jquery-ui.css" />
		<script type="text/javascript" src="${ctx }/scripts/jquery-ui.js"></script>
		<script type="text/javascript" src="${ctx }/scripts/jquery-ui-timepicker-addon.js"></script>
		<style>
		.not-able {
			background-color: lightgray;
		}
		.warning-infor{
			color:red;
			font-size:15px;
			display:none;
		}
		.not-able .warning-infor{
			display:block;
		}
		em {
			font-size:14px;
			font-weight: bold;
			color: #333;
		}
		.shrCell .text {
			  line-height: 23px;
			  border: 1px solid #ccc;
			  height:25px;
			  width: 150px;
			  padding:0;
		}
	
		input.timepicker{
			line-height: 23px;
		  	border: 1px solid #ccc;
		  	height:25px;
		  	width: 80px;
		  	font-size:15px;
		}
	
		.hs-text{
			line-height: 23px;
			  border: 1px solid #ccc;
			  height:25px;
			  width: 260px;
		}
	
		.hs-select{
			font-size: 16px;
		  height: 25px;
		  padding: 0 0 0 6px;
		}
		.content-line{
		 	padding:4px 4px;
		}
		.delivery {
			padding: 15px;
		}
		
		#delivery-tab {
			margin: 15px 0;
		}
		
		#delivery-tab>li {
			display: block;
			width: 120px;
			float: left;
			height: 30px;
			line-height: 30px;
			margin-right: 10px;
			text-align: center;
			font-size: 14px;
			font-weight: bold;
			cursor: pointer;
			border: #ddd 1px solid;
			box-sizing: content-box;
		}
		
		#delivery-tab>li:hover {
			background: #ed464d;
			color: #fff;
			border-color: #ed464d;
		}
		
		#delivery-tab>li.active {
			background: #ed464d;
			color: #fff;
			border-color: #ed464d;
		}
		
		#delivery-way>div {
			background: #eee;
			padding: 10px;
			font-size: 14px;
			display: none;
		}
		
		.delivery-tab>li {
			display: block;
			width: 120px;
			float: left;
			height: 30px;
			line-height: 30px;
			margin-right: 10px;
			text-align: center;
			font-size: 14px;
			font-weight: bold;
			cursor: pointer;
			border: #ddd 1px solid;
			box-sizing: content-box;
		}
		
		.delivery-tab>li:hover {
			background: #ed464d;
			color: #fff;
			border-color: #ed464d;
		}
		
		.delivery-tab>li.active {
			background: #ed464d;
			color: #fff;
			border-color: #ed464d;
		}
		
		#delivery-way>div {
			background: #eee;
			padding: 10px;
			font-size: 14px;
			display: none;
		}
		
		#delivery-way>div p {
			margin-top: 10px;
		}
		
		#delivery-way>div.show {
			display: block;
		}
		
		.pack-date {
			display: inline-block;
			width: 120px;
			height: 24px;
			border: none;
			font: 14px/22px 'Microsoft Yahei';
			padding-left: 10px;
			cursor: pointer;
			color: #999;
		}
		
		.pay-way {
			padding: 15px;
		}
		
		.pay-way>div {
			display: none;
		}
		
		.pay-way>div.show {
			display: block;
		}
		
		.ui-timepicker-div .ui-widget-header {
			margin-bottom: 3px;
		}
		
		.ui-timepicker-div dl {
			text-align: left;
		}
		
		.ui-timepicker-div dl dt {
			height: 25px;
			margin-bottom: -25px;
		}
		
		.ui-timepicker-div dl dd {
			margin: 0 10px 10px 65px;
		}
		
		.ui-timepicker-div td {
			font-size: 90%;
		}
		
		.ui-tpicker-grid-label {
			background: none;
			border: none;
			margin: 0;
			padding: 0;
		}
		
		.ui_tpicker_hour_label, .ui_tpicker_minute_label,
			.ui_tpicker_second_label, .ui_tpicker_millisec_label,
			.ui_tpicker_time_label {
			padding-left: 20px
		}
		
		.invoice-content{
			margin-top: 8px;
		  	overflow: auto;
		  	height: 420px;
		}
		.invoice-panel-close-btn {
		  position: absolute;
		  right: 16px;
		  top: 16px;
		  cursor: pointer;
		  background: url(../themes/default/images/close.jpg) 0 0 no-repeat;
		  width: 15px;
		  height: 15px;
		}
		.use-transitions{
			transition: all 0.3s linear;
			overflow:hidden;
		}
		.ver-infor-show{
			height:110px;
		}
		.ver-infor-hidden{
			height:0px;
			padding:0px;
			border: 0;
		}
	</style>	
	<script>
	
	$(document).ready(function(e) {
		$("#addShipAddressForm1").validationEngine({
			validateNonVisibleFields:false,
			onValidationComplete: function(form, status){
			      if(status){
			    	  submitOrder();
			      }
			    }
		});
		//
		$("#addShipAddressForm2").validationEngine({
			onValidationComplete: function(form, status){
				 if(status){
					    	  
					}
				}
		});
		//
		$(".paymode-btn").bind("click",function(e){
			var src=e.target;			
			var btn=$(src).closest(".paymode-btn");
			var payMode=$(btn).attr("pay-mode");
			$("#paymode").val(payMode);
			$(".paymode-btn").removeClass("active");
			$(src).addClass("active");
		});
	
		// myCalculateShipFeeOfAddress();
		$("[pick-way]").click(function(e) {
			var src = e.target;
			var par = $(src).closest("[pick-way]");
			var pickId = par.attr("pick-way");
			var panel = $("[pen='" + pickId + "']");
			var paynow = $("[pay-way='" + pickId + "']");
			$("[pick-way]").removeClass("active");
			par.addClass("active");
			$("[pen]").removeClass("show");
			panel.addClass("show");
			$("[pay-way]").removeClass("show");
			paynow.addClass("show");
			$("#shipType").val(pickId);
			
			$("#payPluginShowBlock [pay-way]").css("display","none");
			$("#payPluginShowBlock [pay-way='"+pickId+"']").css("display","inline-block");
			var nowVal=$("#paymode").val();
			if(nowVal!=4){
				if(nowVal==5){
					if(pickId=='2'){
						$("#payPluginShowBlock [pay-way='"+pickId+"']").trigger("click");
					}else{
						$("#payPluginShowBlock [pay-way='"+pickId+"'][pay-mode='5']").trigger("click");	
					}
				}else{
					$("#payPluginShowBlock [pay-way='"+pickId+"']")[0].click();
				}			
			}		
			
			var storeradios = document.getElementsByName("storeAddress");
			var factoryradios = document.getElementsByName("factoryAddress");
			if (pickId == "1" || pickId == "2") {
				$("#buyDT .freightT").css("display", "none");
				$("#buyDT .carryT").css("display", "none");
				$(".buyItme .cell.freightC").css("display", "none");
				$(".buyItme .cell.carryC").css("display", "none");
				$("#result .freight").css("display", "none");
				$("#result .carry").css("display", "none");
				$("#self-pick-extinfor").removeClass("ver-infor-hidden");
				$("#self-pick-extinfor").addClass("ver-infor-show");
				$("#self-pick-extinfor").find(".carIdVali").addClass("validate[required]");
				$("#self-pick-extinfor").find(".userIdVali").addClass("validate[required,funcCall[checkCardNum]]");
				$(".formError").remove();
				//$("#self-pick-extinfor").css("display", "block");
				myCalculateShipFeeOfAddress(pickId);
			} else {
				$("#buyDT .freightT").css("display", "block");
				$("#buyDT .carryT").css("display", "block");
				$(".buyItme .cell.freightC").css("display", "block");
				$(".buyItme .cell.carryC").css("display", "block");
				$("#result .freight").css("display", "block");
				$("#result .carry").css("display", "block");
				$("#self-pick-extinfor").removeClass("ver-infor-show");
				$("#self-pick-extinfor").addClass("ver-infor-hidden");
				$(".formError").remove();
				$("#self-pick-extinfor").find(".carIdVali").removeClass("validate[required]");
				$("#self-pick-extinfor").find(".userIdVali").removeClass("validate[required,funcCall[checkCardNum]]");
				//$("#self-pick-extinfor").css("display", "none");
				myCalculateShipFeeOfAddress(pickId);				
			}
			var buyIds="";
			if (pickId == "1") {
				buyIds=$("#store").attr("stroePids");
			} else if (pickId == "2") {
				buyIds=$("#factory").attr("factoryPids");
			}else if (pickId == "3"){
				if($("#store").html()){
					buyIds=$("#store").attr("stroePids");
				}else if($("#factory").html()){		
					buyIds=$("#factory").attr("factoryPids");
				}else{
					$(".buyItme [itempid='"+hpid+"']").addClass("not-able");
					buyIds="0";
				}
					
			}
			$(".buyItme [itempid]").addClass("not-able");
			if(buyIds){
				var pidArr=buyIds.split(",");
				for(var pi=0;pi<pidArr.length;pi++){
					var hpid=pidArr[pi];
					$(".buyItme [itempid='"+hpid+"']").removeClass("not-able");
				}
			}
		});
		//选择配送方式
		var pickway = "${pickway}";
		if (pickway) {
			//$("[pick-way='" + pickway + "']").trigger("click");
			$("[pick-way='" + pickway + "']").addClass("active");
		} else {
			$("[pick-way='1']").addClass("active");
		}
		var said=document.getElementById("saId").value;
		var regionId= document.getElementById("regionId").value;
		var address=document.getElementById("shipAddressShowBlock").innerHTML; 
		selectShipAddress(said, regionId, "", address, pickway);
		//
		var wh=$(window).height();
		var ww=$(window).width();
		$("#invoice-float-panel").hsFloatPanel({
			closeBtnClass: "invoice-panel-close-btn",
			isModel:true
		});
		$(".isneed-inv-radio").bind("click",function(e){
			var panelh=$("#invoice-float-panel").height();
			var panelw=$("#invoice-float-panel").width();
			var src=e.target;
			var parele=$(src).closest(".need-invoice");
			var seled=$(parele).find("input[type='radio']:checked");
			if(seled.length>0){
				if($(seled).val()==1){//需要发票
					$("#invoice-float-panel").hsFloatPanel("showPanel",{
						top: 24,
					    left: (ww-panelw)/2,
					    isModel:true
					   });
					if($("#invoice-tab-content").length==0){//需要首先加载后台信息
						$.ajax({
							url:"${ctx }/order/getInvoiceTmp.do",
							type:"get",
							//data:"",
							async:false,
							dataType:"html",
							success:function(data,status){
								$("#invoice-float-panel .invoice-content").html(data);
							},
							error:function(xhr,errinfor,ex){
								
							},
							complete:function(xhr, ts){
								
							}
						});
					}
				}else{//不需要发票
					$("#invoice-float-panel").hsFloatPanel("hiddenPanel",{isModel:true});
				}
			}
		});
	});
	function checkCardNum(field, rules, i, options){
		var selele=$("#card-type-select");
		var seltype=selele.val();
		var fieldval=field.val();
		if(seltype==1){//身份证
			var res=IdCardValidate(fieldval);
			if(res!=true){
				return "请输入正确的身份证号码！";
			}
		}else if(seltype==2){//行驶证
			
		}
	};
	</script>
	</head>

<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<div id="buyTop" class="box">
		<a href="${ctx }/index.do"><img
			src="${ctx }/themes/default/images/logo.jpg" /></a>
		<div class="buyStep">
			<ul>
				<li><s>1</s>1.我的购物车</li>
				<li class="hot"><s>2</s>2.填写核对订单信息</li>
				<li><s>3</s>3.成功提交订单</li>
				<div class="clear"></div>
			</ul>
		</div>
	</div>

	<div id="order" class="box">
		<h1>填写并核对订单信息</h1>
		<dl class="orderItme">
			<dt>
				收货人信息<a href="javascript:getShipAddressList()" class="shrSelectBt">[修改]</a>
			</dt>
			<dd class="showOrder" id="shipAddressShowBlock">
				<!-- 收货人地址 -->
				<p>${defaultAddress.consignee }&nbsp;${defaultAddress.mobile }</p>
				<p>${regions.provincename }${regions.cityname }${regions.countyname }${regions.streetname }${regions.name }
					${defaultAddress.address }</p>
			</dd>
			<dd class="shrSelect" id="shipAddressListBlock"></dd>
			<form id="addShipAddressForm2" name="addShipAddressForm" action="">
				<div id="addShipAddressBlock" style="display: none;">
					<dd class="shrEdit">
						<div class="shrCell ">
							<span><em>*</em>收货人：</span>
							<div class="cellCon">
								<input type="text" name="consignee" value="" class="text validate[required]" />
							</div>
						</div>
						<div class="shrCell">
							<span><em>*</em>所在地区：</span>
							<div class="cellCon">
								<div role="hs-area-sel" input-sel="[hidden-inputs-div]"
									max-layer="5" seled-name-show=".pshow-name-ele">
									<div>
										<div class="btn btn-success" show-hs-area-sel>请选择</div>
										<span class="pshow-name-ele"></span>
									</div>
									<div class="area-float-panel float-panel">
										<div class="panel-close-btn">
											<span class="glyphicon glyphicon-remove"></span>
										</div>
										<div role="tabpanel" class="area-tabs">
											<ul class="nav nav-tabs" role="tablist">
											</ul>
											<div class="tab-content"></div>
										</div>
										<div hidden-inputs-div></div>
									</div>
								</div>
							</div>
						</div>

						<div class="shrCell">
							<span><em>*</em>详细地址：</span>
							<div class="cellCon">
								<input type="text" name="address" value="" class="text validate[required]" />
							</div>
						</div>

						<div class="shrCell">
							<span><em>*</em>手机号码：</span>
							<div class="cellCon">
								<input type="text" name="mobile" value="" class="text" />
								&nbsp;&nbsp;或 &nbsp;&nbsp;固定电话：<input type="text" name="phone"
									value="" class="text" />
							</div>
						</div>

						<div class="shrCell">
							<span><em></em>电子邮箱：</span>
							<div class="cellCon">
								<input type="text" name="email" value="" class="text" />
								&nbsp;&nbsp;用来接收订单提醒邮件，便于您及时了解订单状态
							</div>
						</div>

						<div class="shrCell">
							<span><em></em>邮政编码：</span>
							<div class="cellCon">
								<input type="text" name="zipcode" value="" class="text" />
							</div>
						</div>
					</dd>
					<dd>
						<div class="shrBT">
							<a href="javascript:void(0)"
								onclick="javascript:addShipAddress()" class="redBT">保存收货人信息</a>
						</div>
					</dd>
				</div>
			</form>
		</dl>

	<form id="addShipAddressForm1" action="">
		<dl class="orderItme">
			<dt>配送方式</dt>
			<div role="hs-pikc-goods" id="four-buts">
				<div class="delivery">
					<ul id="delivery-tab" class="clearfix">
						<li pick-way='2'>工厂自提</li>
						<li pick-way='1'>门店自提</li>						
						<li pick-way='3'>配送到家</li>
					</ul>
					<div id="delivery-way">
						<div class="show" pen="1">
							<em>自提地点：</em> <span id="store"> <c:forEach
									items="${store}" var="list">
									</br>
									<input type="radio" id="storeAddress" name="storeAddress"
										value="${list.address}" checked="checked"> 
										${list.address}  &nbsp;&nbsp; ${list.name} &nbsp;&nbsp;${list.tel}
		    						<input type="hidden" id="id-zt-1${status.index}"
										value="${list.id}">
								</c:forEach>
							</span>
							<p>
								<em>自提时间：</em><input id="pack-date1"
									class="laydate-icon pack-date " readOnly="readonly"
									value="选取自提日期" /> 
							<span id="stime1">
								<input type="input"
									class="timepicker1 timepicker validate[required]" value="" id="beginDate1"
									readOnly="readonly">
							</span> -
							<span id="etime1">
									<input type="input"
									class="timepicker1 timepicker validate[required]" id="endDate1" value=""
									readOnly="readonly">
							</span>
							</p>
						</div>
						<div pen="2">
							<em>自提地点： </em><span id="factory"> 
								<c:forEach items="${factory}" var="list" varStatus="status">
									</br>
									<input type="radio" id="factoryAddress" name="factoryAddress"
										value="${list.address}"
										<c:if test="${status.index==0 }">checked="checked"</c:if>>${list.address}  &nbsp;&nbsp; ${list.contacts} &nbsp;&nbsp;${list.tel}
		    						<input type="hidden" id="id-zt-2${status.index}"
										value="${list.id}">
								</c:forEach>
							</span>
							<p>
								<em>自提时间：</em>
								<input id="pack-date2" class="laydate-icon pack-date " readOnly="readonly"
									value="选取自提日期" /> 
								<span id="stime2">
									<input type="input" class="timepicker2 timepicker validate[required]" id="beginDate2" value=""
									readOnly="readonly">
								</span>	 - 
								<span id="etime2">
								<input type="input" class="timepicker2 timepicker validate[required]" id="endDate2" value=""
									readOnly="readonly">
								</span>
							</p>
						</div>
						<div pen="3">
							<em>配送时间：</em>工作日、双休日与节假日均可送货。在道路运输情况允许下可配送到家。
							<p>
								<em>配送时间：</em>
								<input id="pack-date3" class="laydate-icon pack-date " readOnly="readonly"
									value="选取配送日期" /> 
							<span id="stime3">
								<input type="input" class="timepicker3 timepicker validate[required]" id="beginDate3" value=""
									readOnly="readonly">
							</span> - 
							<span id="etime3">
								<input type="input" class="timepicker3 timepicker validate[required]" id="endDate3" value=""
									readOnly="readonly">
							</span>
							</p>
						</div>
						提示：<font color=red>提货时如遇厂家价格调整，按照厂家调价政策执行</font>
					</div>
					<input type="hidden" id="shipType" value="" />
				</div>
			</div>
		</dl>
		
		<dl class="orderItme  ver-infor-show use-transitions" id="self-pick-extinfor">
			<dt>自提证件信息</dt>
			<div class="self-pick-contet">
				<div class="content-line">
					<em>车牌号:</em>
					<input type="text" name="orderExtList[0].oextvalue" value="" class="hs-text carIdVali validate[required]">
					<input type="hidden" name="orderExtList[0].oexttype" value="1" >
					<input type="hidden" name="orderExtList[0].oextlabeltype" value="1" >
					<input type="hidden" name="orderExtList[0].oextlabelvalue" value="1" >
					<input type="hidden" name="orderExtList[0].oextlabelname" value="车牌号" >
				</div>
				<div class="content-line">
					<em>证件号:</em>
					<span>
						<select id="card-type-select" name="orderExtList[1].oextlabelvalue" class="hs-select">
							<option value="1">身份证</option>
							<option value="2">行驶证</option>
						</select>
						<input type="text" name="orderExtList[1].oextvalue" value="" class="hs-text userIdVali validate[required,funcCall[checkCardNum]]">
						<input type="hidden" name="orderExtList[1].oexttype" value="2" >
						<input type="hidden" name="orderExtList[1].oextlabeltype" value="2" >
						<input type="hidden" name="orderExtList[1].oextlabelname" value="证件号" >
					</span>
				</div>
				<div>
					提示:<span style="color:red;">提货时，请携带相关证件，证件不全将影响您的提货！</span>
				</div>
			</div>
		</dl>

		<dl class="orderItme">
			<dt>支付方式</dt>
			<dd class="showOrder" id="payPluginShowBlock">
				<ul class="delivery-tab clearfix">
					<li class="paymode-btn" pay-way='1' pay-mode="1">到店支付</li>
					<li class="paymode-btn" pay-way='2' pay-mode="2">到厂支付</li>
					<li class="paymode-btn" pay-way='3' pay-mode="3">货到付款</li>
					<li class="paymode-btn" pay-way='1' pay-mode="5">垫资</li>
					<li class="paymode-btn" pay-way='3' pay-mode="5">垫资</li>
					<li class="paymode-btn" pay-mode="4">在线支付</li>
				</ul>
			</dd>
		</dl>

		<dl class="orderItme">
			<dt>
				<div class="left">商品清单</div>
				<div class="right">
					<a href="${ctx }/cart.do">返回修改购物车</a>
				</div>
				<div class="clear"></div>
			</dt>
			<dd style="padding-left: 0;">
				<div id="buyDT">
					<ul>
						<li class="checkT">&nbsp;</li>
						<li class="productT">商品</li>
						<li class="priceT">价格</li>
						<li class="freightT">运费</li>
						<li class="carryT">装卸费</li>
						<li class="numberT">数量</li>
						<!-- li class="companyT">重量</li> -->
						<li class="totalMnyT">总额</li>
					</ul>
				</div>
				<div class="buyItme">
					<c:forEach items="${orderProductList}" varStatus="i" var="item">
						<div class="buyItmeC" itempid="${item.pid}">
							<div class="cell checkC">&nbsp;</div>
							<div class="cell productC">
								<div class="productC1">
									<img
										src="${ctx }/upload/product/thumb/60_60/${item.showimg}" onerror="nofind();"
										width="50" height="50" /> <a
										href="../product/detail.do?id=${item.pid}">${item.name}</a>
									<div class="warning-infor">注：该商品该区域未提供</div>
									<div class="clear"></div>
								</div>
							</div>
							<c:if test="${item.quantityunitid == 4}">
								<!--  <div class="cell priceC" id="marketPriceEle${item.pid}">¥${item.originalPrice}元/吨</div>-->
								<div class="cell priceC" id="marketPriceEle${item.pid}">¥${item.marketprice}元/吨</div>
							</c:if>
							<c:if test="${item.quantityunitid == 3}">
								<div class="cell priceC" id="marketPriceEle${item.pid}">¥${item.marketprice}元/包</div>
							</c:if>
							<div class="cell freightC" id="freightMoney${item.pid}">¥${item.freight}</div>
							<div class="cell carryC" id="carryMoney${item.pid}">¥${item.carry}</div>
							<div class="cell numberC">${item.count}包</div>
							<!--<c:if test="${item.quantityunitid == 4}">
								<div class="cell companyC" id="">${item.weight}吨</div>
							</c:if>-->
							<li class="cell totalMnyC" id="itemMnyEle${item.pid}">¥${item.itemTotalMny }</li>
							<div class="clear"></div>
							<input type="hidden" name="productId" id="productId"
								value="${item.productId}">
						</div>

					</c:forEach>
				</div>
				<div style="border-top: 1px solid #ddd;"></div>
			</dd>

			<dd>
				<div class="orderSum" id="result">
					<ul>
						<li><em>${count} 件商品，总商品金额：</em><b class="amount-mny">￥${productPrice}</b></li>
						<li class="freight"><em>运费：</em><b>￥<span
								id="shipFeeShowBlock">${shipFee}</span></b></li>
						<li class="carry"><em>装卸费：</em><b>￥<span
								id="handlingCostShowBlock">${handlingCost}</span></b></li>
						<li><em>应付总额：</em><b>￥<span id="orderAmountShowBlock1">${orderPrice}</span></b></li>
					</ul>
					<div class="clear"></div>
				</div>
			</dd>
		</dl>
		<dl class="orderItme" >
			<dt>发票信息</dt>
			<div class="need-invoice">
				<div>	
					<em>是否需要发票:</em>	
					<label>
						<input type="radio" name="needinv" value="0" class="isneed-inv-radio" checked="true"> 不需要
					</label>
					<label>
						<input type="radio" name="needinv" class="isneed-inv-radio" value="1"> 需要
					</label>
				</div>
				<div id="">
				</div>
			</div>
		</dl>
		<div class="orderSum1 borDDD" style="border-top: 0;">
			<div class="itme">
				<h3>
					<a href="javascript:;" onclick="openBuyerRemark(this)">添加订单备注</a>
				</h3>


				<div class="sumCon" id="buyerRemarkSumCon">
					<div class="itmeCon">
						<p>
							<textarea name="buyerRemark" id="buyerRemark" rows="10" cols="50"
								style="width: 890px;"></textarea>
						</p>
						<p>提示：请勿填写与支付、发票、收货方面的信息</p>
					</div>
				</div>
				<script type="text/javascript">
					function openBuyerRemark(obj) {
						if (obj.className == "down") {
							obj.className = "";
							document.getElementById("buyerRemarkSumCon").style.display = "none";
						} else {
							obj.className = "down";
							document.getElementById("buyerRemarkSumCon").style.display = "block";
						}
					}
				</script>
				<input type="checkbox" class="validate[minCheckbox[1]]" name="group[group]" id="contract"> <label for="contract">
					同意红狮水泥商城<a href="${ctx }/help/28.html"  target= _blank>《电子合同》</a></label>
					
			</div>
		</div>
		<input type="hidden" name="paymode" id="paymode" value="0" /> <input
			type="hidden" name="shipFee" id="shipFee" value="0" /> <input
			type="hidden" name="handlingCost" id="handlingCost" value="0" /> <input
			type="hidden" id="fullCut" value="0" /> <input type="hidden"
			id="orderAmount" value="0" /> <input type="hidden" name="saId"
			id="saId" value="${defaultAddress.said}" /> <input type="hidden"
			name="regionId" id="regionId" value="${regions.regionid}" />
		<div class="buySumBt borDDD" style="border-top: 0px;">
			总计： <b>¥<span id="orderAmountShowBlock2">${orderPrice}</span></b>
				<input type="submit" id="submitbtn" class="redBT" style="position: absolute; height: 44px;
				line-height: 44px;width: 150px;text-align: center;right: 0;top: 0;
  				font-size: 24px;" value="提交订单"/>
		</div>
	</form>
	</div>
	<div class="float-panel" id="invoice-float-panel" style="height:450px;width:770px;position:fixed;">
		<!-- <div class="invoice-panel-close-btn">
			<span class="glyphicon glyphicon-remove"></span>
		</div> -->
		<div class="invoice-content">
			<div>正在加载，请稍等...</div>
		</div>
	</div>
	
	<div id="footer" class="bigBox">
		<div class="bigBox footerB">
			<div id="footerB">
				<div class="links"></div>
				<div>
					浙ICP证000000号&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" target="_blank">互联网信息服务资格证编号(浙)-经营性-2015</a>&nbsp;&nbsp;<br />红狮水泥商城
					版权所有 @2015, runlion.com Inc.
				</div>
				<div>
					<a href="#"><img width="108" height="40"
						src="${ctx }/themes/default/images/power_jy.gif"></a> <a
						href="#"><img width="108" height="40"
						src="${ctx }/themes/default/images/power_kx.gif"></a> <a
						href="#"><img width="108" height="40"
						src="${ctx }/themes/default/images/power_wj.png"></a> <a
						href="#"><img width="112" height="40"
						src="${ctx }/themes/default/images/power_cx.png"></a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	
		$(".timepicker").bind("click",function(e) {
			var src = e.target;
			var par = $(src).closest("p");
			var packDate = $(par).find(".pack-date");
			var val = packDate.val();
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:200,
			  	height:50
			});
			//简单的日期正则表达式验证
			var regexp = /^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/ig;
			if (!regexp.test(val)) {
				hsArtDialog.content("请先选择日期").showModal();
				packDate.trigger("click");
			}
		});
		laydate({
			elem : '#pack-date1',
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			min : laydate.now(), //设定最小日期为当前日期
			festival : true, //显示节日
			choose : function(dates) { //选择好日期的回调
				var storeradios = document.getElementsByName("storeAddress");
				var num;
				for (var i = 0; i < storeradios.length; i++) {
					if (storeradios[i].checked == true) {
						num = i;
						break;
					}
				}
				var sid = $("#store").attr("saleid");

				var sendData = "pid=" + sid + "&" + "date=" + dates;
				$.ajax({
					url : '${ctx }/order/' + "worktime.do",
					data : sendData,
					async : false,
					success : function(data) {
						//后台执行成功后的回调函数
						var Min = data[0].wtbegin.split(":");
						var Max = data[0].wtend.split(":");
						hourMin = parseInt(Min[0], 10);
						minuteMin = parseInt(Min[1], 10);
						hourMax = parseInt(Max[0], 10);
						minuteMax = parseInt(Max[1], 10);
//						$('#beginDate1').timepicker();
//						$('#beginDate1').timepicker('remove');
						$('#beginDate1').remove();
						$("#stime1").html("<input type='input' class='timepicker1 timepicker validate[required]' value='' id='beginDate1' readOnly='readonly'>");
						//这里是时间选择控件
						$('#beginDate1').val(data[0].wtbegin);
						$('#endDate1').val(data[0].wtend);
						$('#beginDate1').timepicker({
							timeFormat : 'hh:mm',
							hourMin : hourMin,
							minuteMin : minuteMin,
							hourMax : hourMax,
							minuteMax : minuteMax
						});
						$('#beginDate1').change(function(){
//							$('#endDate1').timepicker();
//							$('#endDate1').timepicker('remove');
							var beginDate1 = $('#beginDate1').val();
							var hourmin1 = parseInt("0"+beginDate1.split(":")[0],10);
							var minuteMin1 = parseInt(beginDate1.split(":")[1],10);
							$('#endDate1').remove();
							$("#etime1").html("<input type='input' class='timepicker1 timepicker validate[required]' value='' id='endDate1' readOnly='readonly'>");
							$('#endDate1').val(beginDate1);
							$('#endDate1').timepicker({
								timeFormat : 'hh:mm',
								hourMin : hourmin1,
								minuteMin : minuteMin1,
								hourMax : 23,
								minuteMax : 59
							});
						});
					},
					error : function(xhr, errinfor, ex) {
						//弹窗提示配置
						var hsArtDialog=dialog({
						  	title: '提示',
						  	id:"hs-dialog",
						  	fixed:true,
						  	width:300,
						  	height:50
						});
						hsArtDialog.content("未设置当前区域的配送时间！").showModal();
					}
				});
			}
		});
		laydate({
			elem : '#pack-date2',
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			min : laydate.now(), //设定最小日期为当前日期
			festival : true, //显示节日
			choose : function(dates) { //选择好日期的回调
				var factoryradios = document
						.getElementsByName("factoryAddress");
				var num;
				for (var i = 0; i < factoryradios.length; i++) {
					if (factoryradios[i].checked == true) {
						num = i;
						break;
					}
				}
				var fid = $("#factory").attr("saleid");
				var sendData = "pid=" + fid + "&" + "date=" + dates;
				$.ajax({
					url : '${ctx }/order/' + "worktime.do",
					data : sendData,
					async : false,
					success : function(data) {
						//后台执行成功后的回调函数
						var Min = data[0].wtbegin.split(":");
						var Max = data[0].wtend.split(":");
						hourMin = parseInt(Min[0], 10);
						minuteMin = parseInt(Min[1], 10);
						hourMax = parseInt(Max[0], 10);
						minuteMax = parseInt(Max[1], 10);
//						$('#beginDate2').timepicker();
//						$('#beginDate2').timepicker('remove');
						$('#beginDate2').remove();
						$("#stime2").html("<input type='input' class='timepicker2 timepicker validate[required]' value='' id='beginDate2' readOnly='readonly'>");
						//这里是时间选择控件
						$('#beginDate2').val(data[0].wtbegin);
						$('#endDate2').val(data[0].wtend);
						$('#beginDate2').timepicker({
							timeFormat : 'hh:mm',
							hourMin : hourMin,
							minuteMin : minuteMin,
							hourMax : hourMax,
							minuteMax : minuteMax
						});
						$('#beginDate2').change(function(){
//							$('#endDate2').timepicker();
//							$('#endDate2').timepicker('remove');
							var beginDate2 = $('#beginDate2').val();
							var hourmin2 = parseInt(beginDate2.split(":")[0],10);
							var minuteMin2 = parseInt(beginDate2.split(":")[1],10);
							$('#endDate2').remove();
							$("#etime2").html("<input type='input' class='timepicker2 timepicker validate[required]' value='' id='endDate2' readOnly='readonly'>");
							$('#endDate2').val(beginDate2);
							$('#endDate2').timepicker({
								timeFormat : 'hh:mm',
								hourMin : hourmin2,
								minuteMin : minuteMin2,
								hourMax : 23,
								minuteMax : 59
							});
						});
					},
					error : function(xhr, errinfor, ex) {
						//后台发生异常后的回调函数
					}
				});
			}
		});
		laydate({
			elem : '#pack-date3',
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			min : laydate.now(), //设定最小日期为当前日期
			festival : true, //显示节日
			choose : function(dates) { //选择好日期的回调
				var sid = $("#store").attr("saleid");
				var fid = $("#factory").attr("saleid");
				var salerid=null;
				if(sid){
					salerid=sid;
				}else{
					salerid=fid;
				}
				var sendData = "pid=" + salerid + "&" + "date=" + dates;
				$.ajax({
					url : '${ctx }/order/' + "worktime.do",
					data : sendData,
					async : false,
					success : function(data) {
						var Min = data[0].wtbegin.split(":");
						var Max = data[0].wtend.split(":");
						hourMin = parseInt(Min[0], 10);
						minuteMin = parseInt(Min[1], 10);
						hourMax = parseInt(Max[0], 10);
						minuteMax = parseInt(Max[1], 10);
						//这里是时间选择控件
						$('#beginDate3').remove();
						$("#stime3").html("<input type='input' class='timepicker3 timepicker validate[required]' value='' id='beginDate3' readOnly='readonly'>");
						$('#beginDate3').val(data[0].wtbegin);
						$('#endDate3').val(data[0].wtend);
						var myDate = new Date();
						$('#beginDate3').timepicker({
							timeFormat : 'hh:mm',
							hourMin : hourMin,
							minuteMin : minuteMin,
							hourMax : hourMax,
							minuteMax : minuteMax
						});
						$('#beginDate3').change(function(){
//							$('#endDate3').timepicker();
//							$('#endDate3').timepicker('remove');
							var beginDate3 = $('#beginDate3').val();
							var hourmin3 = parseInt(beginDate3.split(":")[0],10);
							var minuteMin3 = parseInt(beginDate3.split(":")[1],10);
							$("#endDate3").remove();
							$("#etime3").html("<input type='input' class='timepicker3 timepicker validate[required]' value='' id='endDate3' readOnly='readonly'>");
							$('#endDate3').val(beginDate3);
							$('#endDate3').timepicker({
								timeFormat : 'hh:mm',
								hourMin : hourmin3,
								minuteMin : minuteMin3,
								hourMax : 23,
								minuteMax : 59
							});
						});
					},
					error : function(xhr, errinfor, ex) {
						//弹窗提示配置
						var hsArtDialog=dialog({
						  	title: '提示',
						  	id:"hs-dialog",
						  	fixed:true,
						  	width:300,
						  	height:50,
						});
						hsArtDialog.content("未设置当前区域的配送时间！").show();
					}
				});

			}
		});
		
	</script>
</body>
</html>

