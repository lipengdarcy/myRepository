
function DealOrder(resContent,sessionName){
	this.timerid=null;
	this.resContent=resContent;
	this.sessionName=sessionName;
}

DealOrder.prototype.getOrderSaveState=function(){
	var that=this;
	//弹窗提示配置
	var hsArtDialog=dialog({
	  	title: '提示',
	  	id:"hs-dialog",
	  	fixed:true,
	  	width:300,
	  	height:50
	});
	this.timerid=setInterval(function(e){
		 Ajax.get("getSaveOrderState.do?sessionName="+that.sessionName,null,function(data){
			var result = eval("(" + data + ")");
			if (result.state != "success" && result.state != "error") {
				 hsArtDialog.content(result.content).showModal();
			}else if (result.state == "error"){
				hsArtDialog.content(result.content).showModal();
				clearInterval(that.timerid);	
				$('#submitdealbtn').removeAttr("disabled");
			} else if (result.state == "success"){
				clearInterval(that.timerid);
				window.location.href = that.resContent;
				$('#submitdealbtn').removeAttr("disabled");
			}
		 });
	 },10);
}
//
function getDealStoreAndFactoryInfor(){
	//弹窗提示配置
	var hsArtDialog=dialog({
	  	title: '提示',
	  	id:"hs-dialog",
	  	fixed:true,
	  	width:300,
	  	height:50
	});
	var sendData="";
	var buyitems=$(".buyItme").find("[productId]");
	var itemids="";
	if(buyitems.length>0){
		var item=buyitems[0];
		itemids+=item.value;
	}
	sendData+="&pids="+itemids;
	$.ajax({
		url:"../salerorder/getFactoryInfor.do",
		type:"post",
		data:sendData,
		dataType:"json",
		async:false,
		success:function(rs,status){
			$("#factory").empty();
			$("#factory").attr("factoryPids","0");	
			
			$(".buyItme").attr("area-sale-ids","0");
			$("#factory").attr("saleid","0");
			if(rs.state=="error"){	
				hsArtDialog.content(rs.msg).showModal();
			}else if(rs.state=="success"){
				if(rs.factory){
					var factory=rs.factory;
					$("#factory").attr("saleid",factory.id);
					$("#factory").html(factory.regionname+" "+factory.address);
				}
			}
		}
	});
}


/**
 * 经销商订单提交
 * @param that
 */
function submitDealOrder(that) {
	//弹窗提示配置
	var hsArtDialog=dialog({
	  	title: '提示',
	  	id:"hs-dialog",
	  	fixed:true,
	  	width:300,
	  	height:50
	});
	hsArtDialog.content("正在提交......").showModal();
	var sendData={};
	//首先对页面有关联的元素进行处理
	var payTypeBox=$("#paytype-ckbox");
	var isCked=payTypeBox.prop("checked");
	if(isCked==true){
		$("#paymode").val(payTypeBox.val());
	}else{
		$("#paymode").val("0");
	}
	
	var salerid=$("#factory").attr("saleid");
	var saleraddress=$("#factory").html();
	$("#salerid").val(salerid);
	$("#factoryaddress").val(saleraddress);
	var shipid=$("#shipsystemname").val();
	var shipdate=$("#pack-date"+shipid).val();
	$("#besttime").val(shipdate);	
	var mainform=$("#addShipAddressForm1");
	//开始组装flatjson对象
	var inputeles= mainform.find("input");
	for(var ipi=0;ipi<inputeles.length;ipi++){
		var ipiele=inputeles[ipi];
		var tname=$(ipiele).attr("name");
		if(tname){
			sendData[tname]=$(ipiele).val();
		}		
	}
	var texteles= mainform.find("textarea");
	for(var tpi=0;tpi<texteles.length;tpi++){
		var tpiele=texteles[tpi];
		var tname=$(tpiele).attr("name");
		if(tname){
			sendData[tname]=$(tpiele).val();
		}
	}	
	$('#submitdealbtn').attr("disabled","disabled");//去掉a标签中的onclick事件
	
	Ajax.post("submitOrder.do",sendData , false, submitDealOrderResponse);
	
}


// 处理提交订单的反馈信息
//waiting
function submitDealOrderResponse(data) {
	//弹窗提示配置
	var hsArtDialog=dialog({
	  	title: '提示',
	  	id:"hs-dialog",
	  	fixed:true,
	  	width:300,
	  	height:100
	});
	var result = eval("(" + data + ")");
	if (result.state != "success" && result.state != "error") {
		 hsArtDialog.content(result.content).showModal();
		 dealOrderContent= result.content;
		 
		 var dealOrder=new DealOrder(result.content,result.sessionName);
		 dealOrder.getOrderSaveState();
		 
	}else if (result.state == "error"){
		 hsArtDialog.content(result.content).showModal();
		$('#submitdealbtn').removeAttr("disabled");
	} else if (result.state == "success"){
		window.location.href = result.content;
		$('#submitdealbtn').removeAttr("disabled");
	}
}
