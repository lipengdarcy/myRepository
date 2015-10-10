<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>发票信息页面</title>
<style>
	.red{
		color:red;
	}
	.tab-nav-item {
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
	.tab-nav-item.active {
	  background: #ed464d;
	  color: #fff;
	  border-color: #ed464d;
	}
	.fapiao{
		display:none;
		padding: 16px 8px 8px 8px;
		border-top:1px solid lightgrey;
		margin-top:8px;
	}
	.fapiao.active{
		display:block;
	}
	.invoice-thickbox .invoice-status {
	  display: inline-block;
	  display: block;
	  margin-bottom: 10px;
	}
	.invoice-thickbox .invoice-status li.fore.curr {
  background: #ebebeb;
  color: #333;
}
.invoice-thickbox .invoice-status li.fore1 {
  background: #f5f5f5;
  border-color: #f5f5f5;
  color: #ccc;
}
.invoice-thickbox .invoice-status li {
  float: left;
  position: relative;
  padding: 0 20px 0 10px;
  height: 26px;
  line-height: 26px;
  border: solid #ebebeb;
  border-width: 1px 0;
  color: #b3b3b3;
}
.invoice-thickbox .invoice-status li b {
  display: block;
  position: absolute;
  right: 0;
  top: -1px;
  width: 10px;
  height: 28px;
  vertical-align: middle;
  overflow: hidden;
  background: url(http://misc.360buyimg.com/user/purchase/2.0.0/css/i/invoice-status.png) -20px 0 no-repeat;
}
.invoice-thickbox .invoice-status li.fore.curr b {
  height: 26px;
  top: 0;
  background-position: 0 -1px;
}
.step{
	display:none;
}
.step.curr{
	display:block;
}
span.label {
  width: 9em;
  display: inline-block;
  /* font-size: 14px; */
  font-weight: bold;
}
.item {
  padding: 4px 16px;
}
</style>
</head>
<body>
	<div id="invoice-tab-content" class="shrCell">
		<div class="tab-nav">
			<ul>	
				<li id="click_1" class="tab-nav-item active" value="1">普通发票<b></b></li>
				<li id="click_2" class="tab-nav-item" value="2">增值税发票<b></b></li>
			</ul>
			<div style="clear:both;"></div>
		</div>
		<div id="fapiao-1" class="form fapiao active" >
		    <form id="invoiceForm">
				<div class="item">
					<span class="label"><em class="red">*</em>发票抬头：</span>
					<span class="fl">
						<input type="text" name="invoice.invtitle" value="个人" class="text validate[required]">
						<input type="hidden" name="invoice.invtype" value="1">							
					</span>									
				</div>	
				<div class="item">
					<span class="label"><em class="red">*</em>发票内容：</span>
					<span class="fl">
						<label><input type="radio" name="invoice.invcontent" value="水泥" checked="true" >水泥</label>		
					</span>									
				</div>							
				<div class="item">
					<span class="btn btn-danger" id="invoid-save-one">保存发票信息</span>	
					<span class="btn btn-default invoid-cancel-btn">取消</span>					
				</div>
			</form>
		</div>
		<div id="fapiao-2" class="form fapiao invoice-thickbox" >
			<ul class="invoice-status">
				<li class="fore1 fore curr">1.填写公司信息<b></b></li>
				<li class="fore2 fore">2.填写收票人信息<b></b></li>
			</ul>
			<div style="clear:both;"></div>
			<form id="invoiceForm1">				
				<div class="step step1 curr">
					<div class="item">
						<span class="label"><em class="red">*</em>单位名称：</span>
						<span class="fl">
							<input type="text" name="invoice.invcomname" value="" class="text validate[required]">	
							<input type="hidden" name="invoice.invtype" value="2" class="text validate[required]">			
						</span>									
					</div>	
					<div class="item">
						<span class="label"><em class="red">*</em>纳税人识别码：</span>
						<span class="fl">
							<input type="text" name="invoice.invtaxnum" value="" class="text validate[required]">			
						</span>									
					</div>	
					<div class="item">
						<span class="label"><em class="red">*</em>注册地址：</span>
						<span class="fl">
							<input type="text" name="invoice.invaddress" value="" class="text validate[required]">			
						</span>									
					</div>	
					<div class="item">
						<span class="label"><em class="red">*</em>注册电话：</span>
						<span class="fl">
							<input type="text" name="invoice.invtel" value="" class="text validate[required,custom[phone]]">			
						</span>									
					</div>
					<div class="item">
						<span class="label"><em class="red">*</em>开户银行：</span>
						<span class="fl">
							<input type="text" name="invoice.invbankname" value="" class="text validate[required]">			
						</span>									
					</div>	
					<div class="item">
						<span class="label"><em class="red">*</em>银行账户：</span>
						<span class="fl">
							<input type="text" name="invoice.invbankno" value="" class="text validate[required]">			
						</span>									
					</div>
					<div class="item">
						<span class="btn btn-danger" id="btn-next-step">下一步</span>	
						<span class="btn btn-default invoid-cancel-btn">取消</span>								
					</div>	
				</div>				
			</form>
			<form id="invoiceForm2">
				<div class="step step2">
					<div class="item">
						<span class="label"><em class="red">*</em>发票内容：</span>
						<span class="fl">
							<input type="text" name="invoice.invcontent" value="水泥" class="text validate[required]">			
						</span>									
					</div>	
					<div class="item">
						<span class="label"><em class="red">*</em>收票人姓名：</span>
						<span class="fl">
							<input type="text" name="invoice.invrecname" value="" class="text validate[required]">			
						</span>									
					</div>	
					<div class="item">
						<span class="label"><em class="red">*</em>收票人手机：</span>
						<span class="fl">
							<input type="text" name="invoice.invrectel" value="" class="text validate[required,custom[phone]]">			
						</span>									
					</div>	
					<div class="item">
						<span class="label" style="float:left;"><em class="red">*</em>收票人省份：</span>
						<span class="fl" id="invoice-area-sel">
							<!-- 区域选择 -->
							<div id="area_brand" class="cellCon">
								<div seled-name-show=".pshow-name-ele-invoice" min-layer="2"
									max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel"
									style="position: relative;">
									<div>
										<span show-hs-area-sel="#hs-float-panel-invoice" class="btn btn-success"><span>选择省份</span></span>
										<span class="pshow-name-ele-invoice"></span>
									</div>
								</div>
							</div> <!-- 区域选择end -->
						</span>	
						<div style="clear:both;"></div>								
					</div>
					<div class="item">
						<span class="label"><em class="red">*</em>详细地址：</span>
						<span class="fl">
							<input type="text" name="invoice.invdetailaddr" value="" class="text validate[required]">			
						</span>									
					</div>	
					<div class="item">
						<span class="btn btn-danger" id="invoid-save-two">保存发票信息</span>	
						<span class="btn btn-default invoid-cancel-btn">取消</span>	
						<span class="btn btn-link " id="btn-pre-step">上一步</span>								
					</div>	
				</div>
			</form>			
		</div>
		<div id="fapiao-extinfor" class="fapiao-extinfor">
			<div class="item">
				<span>
					<label class="seltype-radio">
							<input type="radio" name="invoice.shiptype"  value="1">
							随货同行
						</label>
						<label class="seltype-radio">
							<input type="radio" name="invoice.shiptype" checked="checked"  value="2">
							配送到收货地址
						</label>
					<label class="shipaddress-radio">
						<input type="radio" name="invoice.shiptype"  value="3">
						配送到发票地址
					</label>
				</span>									
			</div>
			<div class="item input-shipaddress" style="display:none;">
				<span class="label">
					发票接收地址:
				</span>
				<span class="fl">
					<input type="text" class="text" name="invoice.shipaddress" value="">		
				</span>									
			</div>
		</div>		
	</div>
	<div cid="" class="area-float-panel float-panel" id="hs-float-panel-invoice">
		<div class="panel-close-btn">
			<span class="glyphicon glyphicon-remove"></span>
		</div>
		<div role="tabpanel" class="area-tabs">
			<ul class="nav nav-tabs" role="tablist"></ul>
			<div class="tab-content"></div>
		</div>
		<div hidden-inputs-div=""></div>
	</div>
	<script>
		$(function(){
			$("#invoiceForm").validationEngine({
				addFailureCssClassToField:'verfaile',
				onValidationComplete: function(form, status){
				      if(status){
						$("#invoice-float-panel").hsFloatPanel("hiddenPanel",{isModel:true});
				      }
				    }
					});
			$("#invoiceForm1").validationEngine({
				addFailureCssClassToField:'verfaile',
				onValidationComplete: function(form, status){
				      if(status){
				    	$(".fore").removeClass("curr");
						$(".step").removeClass("curr");
						$(".fore2").addClass("curr");
						$(".step2").addClass("curr");
				      }
				    }
					});
			$("#invoiceForm2").validationEngine({
				addFailureCssClassToField:'verfaile',
				onValidationComplete: function(form, status){
				      if(status){
						$("#invoice-float-panel").hsFloatPanel("hiddenPanel",{isModel:true});
				      }
				    }
					});
		});
		//////////////////////////////////////////
		$(".tab-nav-item").bind("click",function(e){
			var src=e.target;
			$("#invoice-tab-content").find(".tab-nav-item").removeClass("active");
			$(src).addClass("active");
			var attrval=$(src).attr("value");
			$("#invoice-tab-content .fapiao").removeClass("active");
			$("#fapiao-"+attrval).addClass("active");
		});
		$("#btn-next-step").bind("click",function(e){
			$("#invoiceForm1").submit();
		});
		$("#btn-pre-step").bind("click",function(e){
			$(".fore").removeClass("curr");
			$(".step").removeClass("curr");
			$(".fore1").addClass("curr");
			$(".step1").addClass("curr");
		});
		$(".invoid-cancel-btn").bind("click",function(e){
			$("#invoice-float-panel").hsFloatPanel("hiddenPanel",{isModel:true});
			$(".isneed-inv-radio[value='0']").prop("checked","true");			
		});
		//		
		$("#invoid-save-one").bind("click",function(e){
			$("#invoiceForm").submit();
		});
		$("#invoid-save-two").bind("click",function(e){
			$("#invoiceForm2").submit();
		});
		var args = HsAreaUser.defVals;
		//HsAreaUser.defVals.roleSel="[role='hs-area-sel-no-init']";

		var tidlast = 10;
		var areaRoles = $("#invoice-area-sel").find(HsAreaUser.defVals.roleSel);
		for (var ai = 0; ai < areaRoles.length; ai++) {
			var areaRole = areaRoles[ai];
			var targs = $.extend({}, args, {
				areaRole : areaRole
			});
			HsAreaUser.initAreaSelForOutPanel(targs, tidlast, ai,
					"invoice-area-layer-");
			tidlast++;
		}
		//////////////////////////
		$(".shipaddress-radio").bind("click",function(e){
			var src=e.target;
			var fapiao=$(src).closest(".fapiao-extinfor");
			$(fapiao).find(".input-shipaddress").css("display","block");
		});
		$(".seltype-radio").bind("click",function(e){
			var src=e.target;
			var fapiao=$(src).closest(".fapiao-extinfor");
			$(fapiao).find(".input-shipaddress").css("display","none");
		});
	</script>	
</body>
</html>