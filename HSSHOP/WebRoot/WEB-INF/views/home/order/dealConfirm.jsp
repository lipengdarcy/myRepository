<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />		
		<meta name="keywords" content="红狮水泥商城" />
		<meta name="description" content="红狮水泥商城" />
		<%@ include file="../../../includes/homenew/header.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/user.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/buy.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx }/scripts/date/need/laydate.css" />
		<script type="text/javascript" src="${ctx }/scripts/dealOrder.js"></script>		
		<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>
		<style>
			.not-able {background-color: lightgray;}
			.warning-infor{color:red;font-size:15px;display:none;}
			.not-able .warning-infor{display:block;}
			.delivery-infom>.form fieldset {
			  float: left;
			  margin-bottom: 16px;
			  position: relative;
			  width: 420px;
			  display: block;
			}
			.delivery-infom .form legend {
			  padding-right: 10px;
			  float: left;
			  font-size: 14px;
			  line-height: 30px;
			}
			.delivery-infom .form input {
			  float: left;
			  width: 175px;
			  height: 28px;
			  border: solid 1px #e6e6e6;
			  padding-left: 10px;
			  color: #999;
			}	
			.delivery-infom .form label.red {
			  font-size: 18px;
			  padding: 0 10px 0 5px;
			  color: #ff3c3c;
			  line-height: 28px;
			  float: left;
			}	
			.delivery-infom>.form.p-way>fieldset.pay-fac-check {
			  width: 170px;
			}
			.delivery-infom>.form.p-way>fieldset {
			  width: 320px;
			}
			.delivery-infom>.form>fieldset {
			  float: left;
			  margin-bottom: 16px;
			  position: relative;
			  width: 420px;
			  display: block;
			}	
			.delivery-infom>.form.p-way fieldset input {
			  width: 100px;
			}
			.delivery-infom>.form.p-way fieldset label {
			  padding: 0 0 0 8px;
			  line-height: 28px;
			  font-size: 14px;
			}
			.delivery-infom>.form.p-way>fieldset.pay-fac-check input {
			  width: 24px;
			}
			.user-table-li td .num-put input {
			  height: 25px;
			}
			.user-table-head tr{			
    			line-height: 42px;			
			}
			.deal-margin-top{
				margin-top: -16px;
			}
			.deal-margin-bottom{
				margin-bottom: -16px;
			}
			.alike-btn{
			  display: block;
			  color: #fff;
			  background: #ff3c3c;
			  width: 170px;
			  height: 48px;
			  line-height: 48px;
			  text-align: center;
			  margin-left: 20px;
			  border: 0;
			}
			.order-check a.right[disabled]{
				background: grey;
			}
			.adress-list-over {
			  width: 398px;
			  height: 400px;
			  border: solid 1px #dbdbdb;
			  background: #fff;
			  position: absolute;
			  top: 35px;
			  left: 0;
			  z-index: 20;
			  -webkit-box-shadow: -2px 1px 6px rgba(6,0,1,.2);
			  -moz-box-shadow: -2px 1px 6px rgba(6,0,1,.2);
			  box-shadow: -2px 1px 6px rgba(6,0,1,.2);
			  display:none;
			  opacity: 0;
			  z-index: 999;
			}
			
			.item-seled a{
				 color: #ff3c3c;
				 font-weight:bold;
			}
			
			.carnum-sel-btn{
			  line-height: 28px;
			  margin-left: 16px;
			  cursor: pointer;
			  color: rgb(0, 117, 255);
			  font-size: 13px;
			}
			
		</style>
		<script>
		$(document).ready(function(e) {
			//$('#id').validationEngine('validate')
			$("#submitdealbtn").bind("click",function(e){
				var src=e.target;
				var isnotable=$(src).attr("disabled");
				if(!isnotable){
					var valRes=$("#addShipAddressForm1").validationEngine('validate');
					if(valRes==true){
						submitDealOrder();
					}
				}
				
			});
			/*$("#addShipAddressForm1").validationEngine({
				onValidationComplete: function(form, status){
			      if(status){
			    	  submitDealOrder();
			      }
			    }
			});*/
			//
			$("#addShipAddressForm1").bind("keydown",function(e){
				if(e.keyCode==13){
					e.preventDefault();
					e.stopPropagation();
					return false;
				}
				
			});
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
					$("#payPluginShowBlock [pay-way='"+pickId+"']").trigger("click");
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
				} else {
					$("#buyDT .freightT").css("display", "block");
					$("#buyDT .carryT").css("display", "block");
					$(".buyItme .cell.freightC").css("display", "block");
					$(".buyItme .cell.carryC").css("display", "block");
					$("#result .freight").css("display", "block");
					$("#result .carry").css("display", "block");			
				}
				var buyIds="";
			});
			//只能使用到厂自提
			var pickway = "2";
			if (pickway) {
				$("[pick-way='" + pickway + "']").addClass("active");
				//
			} else {
				$("[pick-way='1']").addClass("active");
			}
			var said=document.getElementById("saId").value;
			var regionId= document.getElementById("regionId").value;
			getDealStoreAndFactoryInfor();
			if(pickway){
				$("[pick-way='" + pickway + "']").trigger("click");
			}
			//当购买数量变化时修改相关的信息
			$("#buy-pro-count").bind("change",function(e){
				var src=e.target;
				var srcval=$(src).val();		
				var proPrice=$("#pro-price").html();
				proPrice=parseFloat(proPrice);
				srcval=parseFloat(srcval);
				var summny=proPrice*srcval;
				$("#item-sum-mny").html(summny);
				$("#sum-pro-count").html(srcval);
				$("#sum-pro-mny").html(summny);
				$("#orderAmountShowBlock2").html(summny);
			});
			$("#buy-pro-count").trigger("change");
			//添加车辆信息按钮
			$("#carinfor-float-panel").hsFloatPanel({});
			
			$("#add-usercarinfor").bind("click",function(e){
				var panelh=$("#carinfor-float-panel").height();
				var panelw=$("#carinfor-float-panel").width();
				var src=e.target;
				//先隐藏选择车辆窗口
				$("#carlist-float-panel").hsFloatPanel("hiddenPanel",{
				    //isModel:true
				   });
				$("#carinfor-float-panel").hsFloatPanel("showPanel",{
					top: 30,
				    left: 8
				   });
				if($("#add-car-infor").length==0){//需要首先加载后台信息
					$.ajax({
						url:"${ctx }/ucarinfor/addcarpage.do",
						type:"get",
						//data:"",
						async:false,
						dataType:"html",
						success:function(data,status){
							$("#carinfor-float-panel .carinfor-content").html(data);
						},
						error:function(xhr,errinfor,ex){	
						},
						complete:function(xhr, ts){
						}
					});
				}else{
					$("#add-car-infor")[0].reset();
				}
			});
			//弹出选择车号发动机号对话框
			$("#sel-usercarinfor").bind("click",function(e){
				var panelh=$("#carlist-float-panel").height();
				var panelw=$("#carlist-float-panel").width();
				var src=e.target;
				//先隐藏车辆信息添加窗口
				$("#carinfor-float-panel").hsFloatPanel("hiddenPanel",{
				    //isModel:true
				   });
				$("#carlist-float-panel").hsFloatPanel("showPanel",{
					top: 30,
				    left: 8
				});
				$("#sel-carlist-btn").trigger("click");
			});
			//
			$("#sel-carlist-btn").bind("click",function(e){
				var src=e.target;
				var parele=$(src).closest(".search-form");
				var inputeles=$(parele).find("[name]");
				var sendData="";
				for(var i=0;i<inputeles.length;i++){
					var ipele=inputeles[i];
					var eleval=$(ipele).val();
					var elename=$(ipele).attr("name");
					sendData+=elename+"="+encodeURIComponent(eleval)+"&";
				}
				sendData+="1=1";
				$.ajax({
					url:"${ctx }/ucarinfor/selcarinforlist.do",
					type:"get",
					data:sendData,
					async:false,
					dataType:"json",
					success:function(data,status){
						if(data){
							$("#car-infor-content").empty();
							if(data.carlist){						
								var carlist=data.carlist;
								for(var ci=0;ci<carlist.length;ci++){
									var itemtmp=$("#car-infor-list-tmp li").clone(true);
									var carinfor=carlist[ci];							
									for(var key in carinfor){
										var eles=$(itemtmp).find("[data-field='"+key+"']");
										eles.val(carinfor[key]);
									}
									var showele=$(itemtmp).find("[data-field='carmotonum']");
									showele.html(carinfor.carnum+"/"+carinfor.motornum);
									$("#car-infor-content").append(itemtmp);
								}
							}
						}
					},
					error:function(xhr,errinfor,ex){
						
					},
					complete:function(xhr, ts){
						
					}
				});
			});
			//
			$("#car-infor-list-tmp li").bind("click",function(e){
				var src=e.target;
				var parli=$(src).closest("li");
				$("#car-infor-content").find("li").removeClass("item-seled");
				parli.addClass("item-seled");
			});
			//
			$("#car-infor-list-tmp li").bind("dblclick",function(e){
				var src=e.target;
				var parli=$(src).closest("li");
				$("#car-infor-content").find("li").removeClass("item-seled");
				parli.addClass("item-seled");
				$("#confirm-sel-caritem").trigger("click");
			});
			//
			$("#cancle-carlist").bind("click",function(e){
				$("#carlist-float-panel").hsFloatPanel("hiddenPanel",{
				    //isModel:true
				   });
			});
			//
			$("#confirm-sel-caritem").bind("click",function(e){
				var selli= $("#car-infor-content").find("li.item-seled");
				var carinfor=formToFlatJsonObj({
					formCon:selli
				});
				var cmnum=carinfor.carnum+"/"+carinfor.motornum;
				$("#ucarnum-motonum").val(cmnum);
				$("#self-pick-extinfor .car-num").val(carinfor.carnum);
				$("#self-pick-extinfor .moto-num").val(carinfor.motornum);
				$("#carlist-float-panel").hsFloatPanel("hiddenPanel",{
				    //isModel:true
				   });
			});
			//计算默认的订单过期时间
			var daynum=$("#salerinfor-orderexpire").html();
			daynum=parseInt(daynum, 10);
			if(isNaN(daynum)){
				daynum=1;
			}
			var today=new Date();
			var expdate = today.valueOf()
			expdate = expdate + daynum * 24 * 60 * 60 * 1000;
			expdate=new Date(expdate);
			expdate=expdate.format("yyyy-MM-dd");
			$("#pack-date2").val(expdate);
			//计算收填的单价总价
			$("#hand-order-price").bind("change",function(e){
				var src=e.target;
				var srcval=$(src).val();
				var summny=$("#hand-order-sunmny");
				var count=$("#buy-pro-count").val();
				srcval=parseFloat(srcval);
				count=parseFloat(count);
				var sum=count*srcval;
				if(!isNaN(sum)){
					summny.val(sum);
				}
				
			});
			$("#hand-order-sunmny").bind("change",function(e){
				var src=e.target;
				var srcval=$(src).val();
				var price=$("#hand-order-price");
				var count=$("#buy-pro-count").val();
				srcval=parseFloat(srcval);
				count=parseFloat(count);
				var price=srcval/count;
				if(!isNaN(price)){
					summny.val(price);
				}
			});
			$("#buy-pro-count").bind("change",function(e){
				var src=e.target;
				var srcval=$(src).val();
				var price=$("#hand-order-price").val();
				var summny=$("#hand-order-sunmny");
				srcval=parseFloat(srcval);
				price=parseFloat(price);
				var sum=srcval*price;
				if(!isNaN(sum)){
					summny.val(sum);
				}
			});
		});
	
		//增加商品数量
		function addProuctCount() {
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:100
			});
		    var buyCountInput = document.getElementById("buy-pro-count");
		    var buyCount = buyCountInput.value;
		    if (!isNumber(buyCount)) {
		    	hsArtDialog.content("请输入数字").showModal();
		        return false;
		    }
		    buyCountInput.value = parseFloat(buyCount) + 1;
		    $(buyCountInput).trigger("change");
		}
		
		//减少商品数量
		function cutProductCount() {
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:100
			});
		    var buyCountInput = document.getElementById("buy-pro-count");
		    var buyCount = buyCountInput.value;
		    if (!isNumber(buyCount)) {
		    	hsArtDialog.content("请输入数字").showModal();
		        return false;
		    }
		    var count = parseFloat(buyCount);
		    if (count > 1) {
		        buyCountInput.value = count - 1;
		    }
		    $(buyCountInput).trigger("change");
		}
		//将给定的容器内带有name属性的元素的值转化成扁平化的json对象
		function formToFlatJsonObj(args){
			var jsonObj={};
			var formCon=args.formCon;
			var nameEles=$(formCon).find("[name]");
			for(var ni=0;ni<nameEles.length;ni++){
				var nameEle=nameEles[ni];
				var $nameEle=$(nameEle);
				var nameVal=$nameEle.attr("name");
				if($nameEle.is("input")){			
					if($nameEle.is("input[type='radio']:checked")){
						//如果是单选按钮
						jsonObj[nameVal]=$nameEle.val();			
					}else if($nameEle.is("input[type='checkbox']:checked")){
						//如果是多选按钮，存入数组
						var ckboxVals=jsonObj[nameVal];
						if(ckboxVals){
							ckboxVals[ckboxVals.length]=$nameEle.val();
						}else{
							ckboxVals[0]=$nameEle.val();
						}			
					}else if($nameEle.is("input")){
						//如果是文本
						jsonObj[nameVal]=$nameEle.val();
					}
				}else if($nameEle.is("select")){
					//如果是选择框,这里假定为单选
					jsonObj[nameVal]=$nameEle.val();			
				}else if($nameEle.is("textarea")){
					//如果是文本
					jsonObj[nameVal]=$nameEle.val();	
				}else{
					jsonObj[nameVal]=$nameEle.html();	
				}
			}
			return jsonObj;
		}
		</script>
		<title>经销商确认订单-红狮水泥商城</title>
	</head>
<body>
	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>

	<c:import url="${ctx }/tool/newTopMenu.do"></c:import>
	
	<!--eof nav-->
	<div class="crumbs wrap"><strong>| <a href="seller_data.html">商城首页</a> </strong>&gt; <span>订单结算</span></div>
	<!--bof order_settlement-->
	<div class="order-settlement wrap">
		<form id="addShipAddressForm1" action="">
			<h1>填写并核对订单信息</h1>
			 <div class="order-list03">
			 	 <div class="order-tab order-tab-grey">收货人信息</div>
			 	 <ul>
			      	<li id="shipAddressShowBlock">
			      		<em>客户：</em>
			      		${salerinfor.contacts }&nbsp;${salerinfor.tel }&nbsp;
			      		${salerinfor.regionname }-${salerinfor.address }
			      	</li>
			     </ul>			    
			    　<div class="order-tab order-tab-grey deal-margin-top">配送信息</div>
			    　<div class="delivery-infom deal-margin-bottom deal-margin-top">
			      <div class="form clearfix">
	    			 <ul id="delivery-tab" class="clearfix" style="display:none;">
						 <li pick-way='2'>工厂自提</li>
					 </ul>
					 <div pen="2" >
					 	<div style="display:none;">
					 		<em style="display:none;">自提地点:</em><span id="factory"></span>
					 	</div>
						<fieldset class="clearfix">				    		 	
			    		 	<legend>车号 / 发动机号:</legend>
			    		 	<input type="text" class="validate[required]" id="ucarnum-motonum" name="order.remark" />
			    		 	<label class="red">*</label>
	          				<a class="delivery-icon01" id="sel-usercarinfor"></a> 
	          				<a class="delivery-icon02" id="add-usercarinfor"></a> 
	          				<div class="adress-list-over" id="carlist-float-panel" style="height:400px;width:398px;">
								<div class="carlist-panel-close-btn headline">
									地址列表
								</div>								
								<div class="adress-list-over-search clearfix">
									<div class="search-form">
										<input type="text" class="txt" name="carnumOrMotonum" value="">
										<span class="carnum-sel-btn" id="sel-carlist-btn">查找</span>
									</div>
								</div>
								<ul id="car-infor-content">
								</ul>								
								 <div class="adress-list-btm clearfix">
								 	<a class="cancel" id="cancle-carlist">取消</a>
								 	<a class="confirm " id="confirm-sel-caritem">确认</a>
								 </div>
							</div>
							<div class="adress-list-over  adress-list-over02" id="carinfor-float-panel" style="height:355px;width:398px;">
								<div class="headline">新增地址</div>
								<div class="carinfor-content">
									<div>正在加载，请稍等...</div>
								</div>
							</div>
			    		 </fieldset>
			    		 <fieldset class="clearfix">
					         <legend>订单有效期至：</legend>
					         <input id="pack-date2" class="laydate-icon pack-date validate[required]" readOnly="readonly"  value="选取有效期"  />
					     	 <div id="salerinfor-orderexpire" style="display:none">${salerinfor.orderexpire }</div>
					     </fieldset>
						 </div>		    		 
			    		 <input type="hidden" id="shipType" value="" />
			    	</div>
			    　</div>
			   　 <div id="self-pick-extinfor" style="display:none;">
					<div>车牌号发动机号</div>
					<div class="self-pick-contet">
						<div class="content-line">
							<em>车牌号:</em>
							<input type="text" name="orderExtList[0].oextvalue" value="" class="car-num">
							<input type="hidden" name="orderExtList[0].oexttype" value="1" >
							<input type="hidden" name="orderExtList[0].oextlabeltype" value="1" >
							<input type="hidden" name="orderExtList[0].oextlabelvalue" value="1" >
							<input type="hidden" name="orderExtList[0].oextlabelname" value="车牌号" >
						</div>
						<div class="content-line">
							<em>发动机号:</em>
							<span>													
								<input type="text" name="orderExtList[1].oextvalue" value=""  class="moto-num">
								<input type="hidden" name="orderExtList[1].oexttype" value="3" >
								<input type="hidden" name="orderExtList[1].oextlabeltype" value="3" >
								<input type="hidden" name="orderExtList[1].oextlabelvalue" value="3">	
								<input type="hidden" name="orderExtList[1].oextlabelname" value="发动机号" >
							</span>
						</div>
					</div>
				　</div>
				  <div class="order-tab order-tab-grey deal-margin-top">代收货款</div>
				  <div class="delivery-infom">
				      <div class="form clearfix p-way">
				        <fieldset class="clearfix pay-fac-check">
				          <input type="checkbox" id="paytype-ckbox" name="orderPricemny.paytype" value="2"/>
				          <label>到厂支付</label>
				        </fieldset>
				        <fieldset class="clearfix">
				          <legend>收款单价：</legend>
				          <input type="text" name="orderPricemny.payprice" id="hand-order-price" placeholder="请输入收款单价" />
				          <label>元</label>
				        </fieldset>
				        <fieldset class="clearfix">
				          <legend>收款金额：</legend>
				          <input type="text" name="orderPricemny.paysummny" id="hand-order-sunmny" placeholder="请输入收款金额" />
				          <label>元</label>
				        </fieldset>
				      </div>
				  </div>			    	   
			 </div>
			 <div class="order-list04">
			 	<div class="order-tab order-tab-grey">商品清单</div>
			 	<div class="order-table">
			 	 	 <div class="user-table-head">
				        <table >
				          <tbody>
				            <tr>
				              <th scope="col" class="table-wd03">商品编码</th>
				              <th scope="col" class="product-info product-info-long">商品信息</th>
				              <th scope="col" class="table-wd01">价格</th>
				              <th scope="col" class="table-wd02">数量 </th>
				              <th scope="col" class="table-wd01">金额</th>
				            </tr>
				          </tbody>
				        </table>
				     </div>
				      <div class="user-table-li">
				        <table>
				          <tbody>
				            <tr class="table-sline buyItme" itempid="${entity.pid}">
				              <td class="table-wd03">${entity.ncpronum}</td>
				              <td class="product-info product-info-long">
				              	<div class="imghloder">
				              		<a href="../dealproduct/detail.do?id=${entity.pid}">
				              			<img src="${ctx }/upload/product/thumb/60_60/${entity.showimg}" onerror="nofind();" alt=""/>
				              		</a>
				              	</div>
				                <div class="intro">
				                	<a href="../dealproduct/detail.do?id=${entity.pid}">${entity.name}</a>
									<input type="hidden" name="productList[0].pid" productId value="${entity.pid}">
									<input type="hidden" name="productList[0].psn" value="${entity.ncpronum}">
									<input type="hidden" name="productList[0].cateid" value="${entity.cateid}">									
									<input type="hidden" name="productList[0].name" value="${entity.name}">	
									<input type="hidden" name="productList[0].showimg" value="${entity.showimg}">	
				                </div>
				              </td>
				              <td class="table-wd01" id="marketPriceEle${entity.pid}">
				              	¥<span id="pro-price">
				              	<fmt:formatNumber type="number" value="${entity.marketprice}" pattern="0.00" maxFractionDigits="2"/> 
				              	</span>元/吨
				              </td>
				              <td class="table-wd02">
				              	<div class="num-put">
				              		<em class="del" onclick="cutProductCount()">-</em>
				                    <input type="text"  class="validate[required] " id="buy-pro-count" name="productList[0].buycount" value="${entity.salecount}" />
				                    <em class="add" onclick="addProuctCount()">+</em>
				                    <span>吨</span>
				                 </div>
				              </td>
				              <td class="table-wd01">¥<span id="item-sum-mny"></span></td>
				            </tr>
				          </tbody>
				        </table>
				     </div>
				     <div class="user-table-li-note">
				     	<span class="left">注：散装水泥结算数量以实际过磅量为准！</span>
				     	<span class="right">
				     		<span id="sum-pro-count">${count}</span>
				     		件商品，总商品金额：￥
				     		<span id="sum-pro-mny">${productPrice}</span>
				     	</span>
				     </div>
			 	</div>
			 	<div class="order-tab order-tab-grey">备注</div>
    			<div class="order-settlement-note">
    				<textarea name="order.buyerremark" id="buyerRemark"></textarea>
    			</div>
    			<input type="hidden" name="order.shipsystemname" id="shipsystemname" value="2">
				<input type="hidden" name="order.shipfriendname" id="shipfriendname" value="工厂自提">
				<input type="hidden" name="order.paymode" id="paymode" value="0" /> 
				<input type="hidden" name="order.regionid" id="regionId" value="${salerinfor.regionid }" /> 
				<input type="hidden" name="order.consignee" id="consignee" value="${salerinfor.contacts }" /> 
				<input type="hidden" name="order.mobile" id="mobile" value="${salerinfor.tel }" />
				<input type="hidden" name="order.zipcode" id="zipcode" value="" /> 
				<input type="hidden" name="order.address" id="address" value="${salerinfor.regionname }-${salerinfor.address }" />
				<input type="hidden" name="besttime" id="besttime" value="" /> 
				<input type="hidden" name="order.factoryAddress" id="factoryaddress" value="" />
				<input type="hidden" name="order.salerid" id="salerid" value="0" /> 
				<input type="hidden" name="order.needinv" value="0">
			
				<input type="hidden" name="saId" id="saId" value="${salerinfor.regionid }" /> 
				
    			<div class="order-check">合计金额: 
    				<strong>¥<span id="orderAmountShowBlock2">${productPrice}</span> </strong>
    				<a class="alike-btn right" id="submitdealbtn" value="提交订单">提交订单</a>
    			</div>
			 </div>
		 </form>	
	</div>
		 
	<ul id="car-infor-list-tmp">
		<li>
			<a data-field="carmotonum" ></a>
			<input type="hidden" data-field="id" name="id" value="">
			<input type="hidden" data-field="carnum" name="carnum" value="">
			<input type="hidden" data-field="motornum" name="motornum" value="">
		</li>
	</ul>
	<script type="text/javascript">	
		laydate({
			elem : '#pack-date2',
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			min : laydate.now(), //设定最小日期为当前日期
			festival : true, //显示节日
			choose : function(dates) { //选择好日期的回调
				
			},
			error : function(xhr, errinfor, ex) {
						//后台发生异常后的回调函数
					}
				});

	</script>
	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service--> 	
	<%@ include file="../../../includes/homenew/footer.jsp"%>
</body>
</html>

