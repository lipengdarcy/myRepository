//获得配送地址列表
function getShipAddressList() {
	var pickId = $("#shipType").val();
	Ajax.get("shipaddresslist.do?pickId=" + pickId, false,
			getShipAddressListResponse);
}
var nowExtPrice=null;
// 处理获得配送地址列表的反馈信息
function getShipAddressListResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		var defAddrId = document.getElementById("saId").value;
		var shipAddressList = "<ul class='orderList'>";
		for (var i = 0; i < result.content.count; i++) {
			if (result.content.list[i].saId == defAddrId) {
				shipAddressList += "<li style='color:red;'><label><b><input type='radio'  checked='checked' class='radio' name='shipAddressItem' value='"
						+ result.content.list[i].saId
						+ "' onclick=\"selectShipAddress("
						+ result.content.list[i].saId
						+ ","
						+ result.content.list[i].regionId
						+ ",'"
						+ result.content.list[i].user
						+ "','"
						+ result.content.list[i].address
						+ "','"
						+ result.pickId
						+ "')\" />"
						+ result.content.list[i].user
						+ "</b><i>"
						+ result.content.list[i].address + "</i></label></li>";
			} else {
				shipAddressList += "<li><label><b><input type='radio' class='radio' name='shipAddressItem' value='"
						+ result.content.list[i].saId
						+ "' onclick=\"selectShipAddress("
						+ result.content.list[i].saId
						+ ","
						+ result.content.list[i].regionId
						+ ",'"
						+ result.content.list[i].user
						+ "','"
						+ result.content.list[i].address
						+ "','"
						+ result.pickId
						+ "')\" />"
						+ result.content.list[i].user
						+ "</b><i>"
						+ result.content.list[i].address + "</i></label></li>";

			}
		}
		shipAddressList += "<li id='newAdress'><label><input type='radio' class='radio' name='shipAddressItem' onclick='openAddShipAddressBlock()' />使用新地址</label></li></ul>";
		document.getElementById("shipAddressShowBlock").style.display = "none";
		document.getElementById("shipAddressListBlock").style.display = "";
		document.getElementById("shipAddressListBlock").innerHTML = shipAddressList;
	} else {
		 ZENG.msgbox.show(result.content, 1, 3000);
	}
}

// 选择配送地址
function selectShipAddress(saId, regionId, user, address, pickId) {
	if(user){
		 ZENG.msgbox.show("地址修改可能会导致价格改变！",1, 3000);
	}	
	document.getElementById("saId").value = saId;
	document.getElementById("regionId").value = regionId;
	document.getElementById("shipAddressListBlock").style.display = "none";
	document.getElementById("addShipAddressBlock").style.display = "none";
	document.getElementById("shipAddressShowBlock").style.display = "";
	document.getElementById("shipAddressShowBlock").innerHTML = "<p>" + user
			+ "</p><p>" + address + "</p>";
	
	//判断区域下是没有的产品的id	
	var sendData="";
	var regionId=$("#regionId").val();
	sendData+="regionId="+regionId;
	var buyitems=$(".buyItme").find("[name=\"productId\"]");
	var itemids="";
	if(buyitems.length>0){
		var item=buyitems[0];
		itemids+=item.value;
	}
	sendData+="&productIds="+itemids;
	$.ajax({
		url:"getRegionProducts.do",
		type:"post",
		data:sendData,
		dataType:"json",
		async:false,
		success:function(rs,status){
			$("#factory").empty();
			$("#factory").attr("factoryPids","0");
			$("#store").empty();
			$("#store").attr("stroePids","0");
			$(".buyItme").attr("area-sale-ids","0");
			$("#store").attr("saleid","0");
			$("#factory").attr("saleid","0");
			if(rs.status==false){
				$(".buyItme .buyItmeC").addClass("not-able");				
				for(var hi=0;hi<rs.hadPids.length;hi++){
					var hid=rs.hadPids[hi];
					var areaIds=$(".buyItme").attr("area-sale-ids");
					if(areaIds!=0){
						areaIds=areaIds+","+hid;
					}else{
						areaIds=hid;
					}
					$(".buyItme").attr("area-sale-ids",areaIds);
					//$(".buyItme [itempid='"+hid+"']").removeClass("not-able");
				}
				ZENG.msgbox.show(rs.msg, 1, 3000);
			}else{
				var buyItems=$(".buyItme .buyItmeC");
				for(var bi=0;bi<buyItems.length;bi++){
					var buyItem=buyItems[bi];
					var buyPid=$(buyItem).attr("itempid");
					var areaIds=$(".buyItme").attr("area-sale-ids");
					if(areaIds!=0){
						areaIds=areaIds+","+buyPid;
					}else{
						areaIds=buyPid;
					}
					$(".buyItme").attr("area-sale-ids",areaIds);
				}
			}
			//else{		
				//门店只要有就可以
				if(rs.mdSaleaddrList && rs.mdSaleaddrList.length>0){
					var md=rs.mdSaleaddrList[0];
					$("#store").html(md.address+"&nbsp;&nbsp;"+md.name+"&nbsp;&nbsp;"+md.contacts+"&nbsp;&nbsp;"+md.tel);
					var tareaIds=$(".buyItme").attr("area-sale-ids");
					$("#store").attr("stroePids",tareaIds);
					$("#store").attr("saleid",md.id);
					//工厂需要判断区域可售产品是否有工厂
				}
				if(rs.gcSaleaddrList && rs.gcSaleaddrList.length>0){
					var gc=rs.gcSaleaddrList[0];
					$("#factory").html(gc.address+"&nbsp;&nbsp;"+gc.name+"&nbsp;&nbsp;"+gc.contacts+"&nbsp;&nbsp;"+gc.tel);
					var gcidPidstr=rs.gcIdPidsMap;
					var gcidPidArr=gcidPidstr.split(";");
					var gcId=gc.id;
					$("#factory").attr("factoryPids","0");
					$("#factory").attr("saleid",gcId);
					for(var gi=0;gi<gcidPidArr.length;gi++){
						var gcid=gcidPidArr[gi];
						var gcidArr=gcid.split(":");
						if(gcidArr.length>1){
							//灰色所有，逐条高亮
							//$(".buyItme .buyItmeC").addClass("not-able");
							if(gcId==gcidArr[0]){
								var ids=gcidArr[1];
								var idArr=ids.split(",");
								for(var idi=0;idi<idArr.length;idi++){
									var idiid=idArr[idi];
									//$(".buyItme [itempid='"+idiid+"']").removeClass("not-able");
									var faPids=$("#factory").attr("factoryPids");
									if(faPids!=0){
										faPids=faPids+","+idiid;
									}else{
										faPids=idiid;
									}
									$("#factory").attr("factoryPids",faPids);
								}							
								return;
							}
						}
						
					}
				}				
			//}
			
		}
	});
	//
	//getProductExtPrice
	$.ajax({
		url:"getProductExtPrice.do",
		type:"post",
		data:sendData,
		dataType:"json",
		async:false,
		success:function(rs,status){
			nowExtPrice=rs;
		}
	});
	//	
	$("#delivery-tab [pick-way].active").trigger("click");
	// 获取自提地址
	//getStoreAddress(regionId);
	if(user){
		myCalculateShipFeeOfAddress(pickId);
	}
	
	// calculateShipFeeOfAddress();

}

function getStoreAndFactoryInfor(){
	var sendData="";
	var regionId=$("#regionId").val();
	sendData+="regionId="+regionId;
	var buyitems=$(".buyItme").find("[name=\"productId\"]");
	var itemids="";
	if(buyitems.length>0){
		var item=buyitems[0];
		itemids+=item.value;
	}
	sendData+="&productIds="+itemids;
	$.ajax({
		url:"getRegionProducts.do",
		type:"post",
		data:sendData,
		dataType:"json",
		async:false,
		success:function(rs,status){
			$("#factory").empty();
			$("#factory").attr("factoryPids","0");
			$("#store").empty();
			$("#store").attr("stroePids","0");
			$(".buyItme").attr("area-sale-ids","0");
			$("#store").attr("saleid","0");
			$("#factory").attr("saleid","0");
			if(rs.status==false){
				$(".buyItme .buyItmeC").addClass("not-able");				
				for(var hi=0;hi<rs.hadPids.length;hi++){
					var hid=rs.hadPids[hi];
					var areaIds=$(".buyItme").attr("area-sale-ids");
					if(areaIds!=0){
						areaIds=areaIds+","+hid;
					}else{
						areaIds=hid;
					}
					$(".buyItme").attr("area-sale-ids",areaIds);
					//$(".buyItme [itempid='"+hid+"']").removeClass("not-able");
				}
				ZENG.msgbox.show(rs.msg, 1, 3000);
			}else{
				var buyItems=$(".buyItme .buyItmeC");
				for(var bi=0;bi<buyItems.length;bi++){
					var buyItem=buyItems[bi];
					var buyPid=$(buyItem).attr("itempid");
					var areaIds=$(".buyItme").attr("area-sale-ids");
					if(areaIds!=0){
						areaIds=areaIds+","+buyPid;
					}else{
						areaIds=buyPid;
					}
					$(".buyItme").attr("area-sale-ids",areaIds);
				}
			}
			//else{		
				//门店只要有就可以
				if(rs.mdSaleaddrList && rs.mdSaleaddrList.length>0){
					var md=rs.mdSaleaddrList[0];
					$("#store").html(md.address+"&nbsp;&nbsp;"+md.name+"&nbsp;&nbsp;"+md.contacts+"&nbsp;&nbsp;"+md.tel);
					var tareaIds=$(".buyItme").attr("area-sale-ids");
					$("#store").attr("stroePids",tareaIds);
					$("#store").attr("saleid",md.id);
					//工厂需要判断区域可售产品是否有工厂
				}
				if(rs.gcSaleaddrList && rs.gcSaleaddrList.length>0){
					var gc=rs.gcSaleaddrList[0];
					$("#factory").html(gc.address+"&nbsp;&nbsp;"+gc.name+"&nbsp;&nbsp;"+gc.contacts+"&nbsp;&nbsp;"+gc.tel);
					var gcidPidstr=rs.gcIdPidsMap;
					var gcidPidArr=gcidPidstr.split(";");
					var gcId=gc.id;
					$("#factory").attr("factoryPids","0");
					$("#factory").attr("saleid",gcId);
					for(var gi=0;gi<gcidPidArr.length;gi++){
						var gcid=gcidPidArr[gi];
						var gcidArr=gcid.split(":");
						if(gcidArr.length>1){
							//灰色所有，逐条高亮
							//$(".buyItme .buyItmeC").addClass("not-able");
							if(gcId==gcidArr[0]){
								var ids=gcidArr[1];
								var idArr=ids.split(",");
								for(var idi=0;idi<idArr.length;idi++){
									var idiid=idArr[idi];
									//$(".buyItme [itempid='"+idiid+"']").removeClass("not-able");
									var faPids=$("#factory").attr("factoryPids");
									if(faPids!=0){
										faPids=faPids+","+idiid;
									}else{
										faPids=idiid;
									}
									$("#factory").attr("factoryPids",faPids);
								}							
								return;
							}
						}
						
					}
				}				
			//}
			
		}
	});
}

// 获取自提地址
function getStoreAddress(regionsId) {
	Ajax.get("getStoreAddress.do?regionsId=" + regionsId, false,
			getStoreAddressResponse);
}

function getStoreAddressResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		if (result.factory == undefined && result.store != undefined) {
			$("#factory").empty();
			var storeList = result.store;
			$("#store").empty();
			for (var fi = 0; fi < storeList.length; fi++) {
				var store = storeList[fi];
				var storenum = fi;
				if (fi == 0) {
					var storeradioHtml = "</br><input type=\"radio\" name=\"storeAddress\" checked=\"checked\" value=\""
							+ store["address"]
							+ "\"><span>"
							+ store["address"]
							+ "&nbsp;&nbsp; "
							+ store["name"]
							+ "&nbsp;&nbsp; "
							+ store["tel"]
							+ "</span><input type=\"hidden\" id=\"id-zt-1"
							+ storenum + "\" value=\"" + store["id"] + "\"> ";
				} else {
					var storeradioHtml = "</br><input type=\"radio\" name=\"storeAddress\"  value=\""
							+ store["address"]
							+ "\"><span>"
							+ store["address"]
							+ "&nbsp;&nbsp; "
							+ store["name"]
							+ "&nbsp;&nbsp; "
							+ store["tel"]
							+ "</span><input type=\"hidden\" id=\"id-zt-1"
							+ storenum + "\" value=\"" + store["id"] + "\"> ";
				}
				var storeradioHtml = "</br><input type=\"radio\" name=\"storeAddress\" checked=\"checked\" value=\""
						+ store["address"]
						+ "\"><span>"
						+ store["address"]
						+ "&nbsp;&nbsp; "
						+ store["name"]
						+ "&nbsp;&nbsp; "
						+ store["tel"]
						+ "</span><input type=\"hidden\" id=\"id-zt-1"
						+ storenum + "\" value=\"" + store["id"] + "\"> ";
				var $storeradioHtml = $(storeradioHtml);
				$("#store").append($storeradioHtml);
			}
			 ZENG.msgbox.show("该区域没有设置工厂！",1, 3000);
		} else if (result.store == undefined && result.factory != undefined) {
			$("#store").empty();
			var factoryList = result.factory;
			$("#factory").empty();
			for (var fi = 0; fi < factoryList.length; fi++) {
				var factory = factoryList[fi];
				var factorynum = fi;
				if (fi == 0) {
					var radioHtml = "</br><input type=\"radio\" name=\"factoryAddress\" checked=\"checked\" value=\""
							+ factory["address"]
							+ "\"><span>"
							+ factory["address"]
							+ "&nbsp;&nbsp; "
							+ factory["contacts"]
							+ "&nbsp;&nbsp; "
							+ factory["tel"]
							+ "</span><input type=\"hidden\" id=\"id-zt-2"
							+ factorynum
							+ "\" value=\""
							+ factory["id"]
							+ "\">";
				} else {
					var radioHtml = "</br><input type=\"radio\" name=\"factoryAddress\"  value=\""
							+ factory["address"]
							+ "\"><span>"
							+ factory["address"]
							+ "&nbsp;&nbsp; "
							+ factory["contacts"]
							+ "&nbsp;&nbsp; "
							+ factory["tel"]
							+ "</span><input type=\"hidden\" id=\"id-zt-2"
							+ factorynum
							+ "\" value=\""
							+ factory["id"]
							+ "\">";
				}
				var $radioHtml = $(radioHtml);
				$("#factory").append($radioHtml);
			}
			ZENG.msgbox.show("该区域没有设置门店！",1, 3000);
		} else {
			var factoryList = result.factory;
			$("#factory").empty();
			for (var fi = 0; fi < factoryList.length; fi++) {
				var factory = factoryList[fi];
				var factorynum = fi;
				if (fi == 0) {
					var radioHtml = "</br><input type=\"radio\" name=\"factoryAddress\" checked=\"checked\" value=\""
							+ factory["address"]
							+ "\"><span>"
							+ factory["address"]
							+ "&nbsp;&nbsp; "
							+ factory["contacts"]
							+ "&nbsp;&nbsp; "
							+ factory["tel"]
							+ "</span><input type=\"hidden\" id=\"id-zt-2"
							+ factorynum
							+ "\" value=\""
							+ factory["id"]
							+ "\">";
				} else {
					var radioHtml = "</br><input type=\"radio\" name=\"factoryAddress\"  value=\""
							+ factory["address"]
							+ "\"><span>"
							+ factory["address"]
							+ "&nbsp;&nbsp; "
							+ factory["contacts"]
							+ "&nbsp;&nbsp; "
							+ factory["tel"]
							+ "</span><input type=\"hidden\" id=\"id-zt-2"
							+ factorynum
							+ "\" value=\""
							+ factory["id"]
							+ "\">";
				}
				var $radioHtml = $(radioHtml);
				$("#factory").append($radioHtml);
			}
			var storeList = result.store;
			$("#store").empty();
			for (var fi = 0; fi < storeList.length; fi++) {
				var store = storeList[fi];
				var storenum = fi;
				if (fi == 0) {
					var storeradioHtml = "</br><input type=\"radio\" name=\"storeAddress\" checked=\"checked\" value=\""
							+ store["address"]
							+ "\"><span>"
							+ store["address"]
							+ "&nbsp;&nbsp; "
							+ store["name"]
							+ "&nbsp;&nbsp; "
							+ store["tel"]
							+ "</span><input type=\"hidden\" id=\"id-zt-1"
							+ storenum + "\" value=\"" + store["id"] + "\"> ";
				} else {
					var storeradioHtml = "</br><input type=\"radio\" name=\"storeAddress\"  value=\""
							+ store["address"]
							+ "\"><span>"
							+ store["address"]
							+ "&nbsp;&nbsp; "
							+ store["name"]
							+ "&nbsp;&nbsp; "
							+ store["tel"]
							+ "</span><input type=\"hidden\" id=\"id-zt-1"
							+ storenum + "\" value=\"" + store["id"] + "\"> ";
				}
				var $storeradioHtml = $(storeradioHtml);
				$("#store").append($storeradioHtml);
			}
		}
	} else {
		$("#store").empty();
		$("#factory").empty();
		ZENG.msgbox.show("该区域没有设置门店和工厂！",1, 3000);
	}
}

// 打开添加配送地址块
function openAddShipAddressBlock() {
	document.getElementById("addShipAddressBlock").style.display = "";
}

// 添加配送地址
function addShipAddress() {
	ZENG.msgbox.show("使用新增地址可能会导致价格改变！", 1, 3000);
	var addShipAddressForm = document.forms["addShipAddressForm"];

	var consignee = addShipAddressForm.elements["consignee"].value;
	var mobile = addShipAddressForm.elements["mobile"].value;
	var phone = addShipAddressForm.elements["phone"].value;
	var email = addShipAddressForm.elements["email"].value;
	var zipcode = addShipAddressForm.elements["zipcode"].value;
	var regionId = addShipAddressForm.elements["lastName"].value;
	var address = addShipAddressForm.elements["address"].value;
	var isDefault = addShipAddressForm.elements["isDefault"] == undefined ? 0
			: addShipAddressForm.elements["isDefault"].checked ? 1 : 0;
	isDefault = 1;

	if (!verifyAddShipAddress(consignee, mobile, phone, regionId, address)) {
		return;
	}

	Ajax.post(url + "/ucenter/addshipaddress.do", {
		'alias' : "",
		'consignee' : consignee,
		'mobile' : mobile,
		'phone' : phone,
		'email' : email,
		'zipcode' : zipcode,
		'regionId' : regionId,
		'address' : address,
		'isDefault' : isDefault
	}, false, addShipAddressResponse)
}

// 验证添加的收货地址
function verifyAddShipAddress(consignee, mobile, phone, regionId, address) {
	if (consignee == "") {
		ZENG.msgbox.show("请填写收货人！", 1, 3000);
		return false;
	}
	if (mobile == "" && phone == "") {
		ZENG.msgbox.show("手机号和固定电话必须填写一项！", 1, 3000);
		return false;
	}
	if (!regionId || parseInt(regionId) < 1) {
		ZENG.msgbox.show("请选择区域！", 1, 3000);
		return false;
	}
	if (address == "") {
		ZENG.msgbox.show("请填写详细地址！", 1, 3000);
		return false;
	}
	return true;
}

// 处理添加配送地址的反馈信息
function addShipAddressResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		var addShipAddressForm = document.forms["addShipAddressForm"];

		var regionid=addShipAddressForm.elements["lastName"].value;
		document.getElementById("regionId").value = regionid;

		var html = "<p>";
		html += addShipAddressForm.elements["consignee"].value + "&nbsp;&nbsp;";
		if (addShipAddressForm.elements["mobile"].value.length > 0) {
			html += addShipAddressForm.elements["mobile"].value
					+ "&nbsp;&nbsp;";
		} else {
			html += addShipAddressForm.elements["phone"].value + "&nbsp;&nbsp;";
		}

		html += "</p><p>";

		// html +=
		// getSelectedOption(addShipAddressForm.elements["provinceId"]).text +
		// "&nbsp;&nbsp;";
		// html += getSelectedOption(addShipAddressForm.elements["cityId"]).text
		// + "&nbsp;&nbsp;";
		// html +=
		// getSelectedOption(addShipAddressForm.elements["regionId"]).text +
		// "&nbsp;&nbsp;";		
		var showEleName=$(addShipAddressForm).find(".pshow-name-ele").html();
		var addressName=showEleName+ "&nbsp;&nbsp;"+addShipAddressForm.elements["address"].value + "&nbsp;&nbsp;";
		html += addressName;
		html += "</p>";

		document.getElementById("shipAddressShowBlock").style.display = "";
		document.getElementById("shipAddressShowBlock").innerHTML = html;

		document.getElementById("saId").value = result.saId;
		document.getElementById("shipAddressListBlock").style.display = "none";
		document.getElementById("addShipAddressBlock").style.display = "none";

		addShipAddressForm.elements["consignee"].value = "";
		addShipAddressForm.elements["mobile"].value = "";
		addShipAddressForm.elements["phone"].value = "";
		addShipAddressForm.elements["email"].value = "";
		addShipAddressForm.elements["zipcode"].value = "";
		addShipAddressForm.elements["address"].value = "";

		var pickId = $("#shipType").val();

		selectShipAddress(result.saId, regionid, "", html,pickId);
		
		myCalculateShipFeeOfAddress(pickId);
	} else {
		var msg = result.msg + "\n";
		ZENG.msgbox.show(msg, 1, 3000);
	}
}

// 计算地址的配送费用
function calculateShipFeeOfAddress() {
	return;
	var saId = document.getElementById("saId").value;
	var shipName = document.getElementById("shipName").value;
	var selectedCartItemKeyList = document.getElementById("selectedCartItemKeyList").value
	Ajax.get("/order/changeshipaddress?saId=" + saId + "&shipName="
							+ shipName + "&selectedCartItemKeyList="
							+ selectedCartItemKeyList,
					false,
					function(data) {
						var result = eval("(" + data + ")");
						if (result.state == "success") {
							var oldShipFee = document.getElementById("shipFee").value;
							document.getElementById("shipFee").value = result.content;
							var changeMoney = parseFloat(result.content)
									- parseFloat(oldShipFee);
							changeMoney = 0;
							var oldOrderAmount = document
									.getElementById("orderAmount").value;
							var newOrderAmount = parseFloat(oldOrderAmount)
									+ parseFloat(changeMoney);
							document.getElementById("orderAmount").value = newOrderAmount;
							if (document.getElementById("shipFeeShowBlock") != undefined) {
								document.getElementById("shipFeeShowBlock").innerHTML = result.content;
							}
							if (document
									.getElementById("orderAmountShowBlock1") != undefined) {
								document
										.getElementById("orderAmountShowBlock1").innerHTML = newOrderAmount;
							}
							if (document
									.getElementById("orderAmountShowBlock2") != undefined) {
								document
										.getElementById("orderAmountShowBlock2").innerHTML = newOrderAmount;
							}
						} else if (result.state == "emptyship"
								|| result.state == "faraddress") {
							 ZENG.msgbox.show(result.content, 1, 3000);
							getShipPluginList();
						} else {
							 ZENG.msgbox.show(result.content, 1, 3000);
						}
					});
}

// 展示配送插件列表
function showShipPluginList() {
	document.getElementById("shipPluginShowBlock").style.display = "none";
	document.getElementById("shipPluginListBlock").style.display = "";
}

// 选择配送方式
function selectShipPlugin(shipSystemName, shipFriendName) {
	Ajax.get("/order/selectshipplugin?shipName=" + shipSystemName + "&payName="
			+ document.getElementById("payName").value + "&saId="
			+ document.getElementById("saId").value
			+ "&selectedCartItemKeyList="
			+ document.getElementById("selectedCartItemKeyList").value, false,
			function(data) {
				selectShipPluginResponse(data, shipSystemName, shipFriendName);
			});
}

// 处理选择配送方式的反馈信息
function selectShipPluginResponse(data, shipSystemName, shipFriendName) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		document.getElementById("shipName").value = shipSystemName;
		document.getElementById("shipPluginShowBlock").style.display = "";
		document.getElementById("shipPluginShowBlock").innerHTML = shipFriendName;
		document.getElementById("shipPluginListBlock").style.display = "none";

		var oldShipFee = document.getElementById("shipFee").value;
		document.getElementById("shipFee").value = result.content;
		var changeMoney = parseFloat(result.content) - parseFloat(oldShipFee);
		var oldOrderAmount = document.getElementById("orderAmount").value;
		var newOrderAmount = parseFloat(oldOrderAmount)
				+ parseFloat(changeMoney);
		document.getElementById("orderAmount").value = newOrderAmount;
		if (document.getElementById("shipFeeShowBlock") != undefined) {
			document.getElementById("shipFeeShowBlock").innerHTML = result.content;
		}
		if (document.getElementById("orderAmountShowBlock1") != undefined) {
			document.getElementById("orderAmountShowBlock1").innerHTML = newOrderAmount;
		}
		if (document.getElementById("orderAmountShowBlock2") != undefined) {
			document.getElementById("orderAmountShowBlock2").innerHTML = newOrderAmount;
		}
	} else {
		 ZENG.msgbox.show(result.content, 1, 3000);
	}
}

// 展示支付插件列表
function showPayPluginList() {
	document.getElementById("payPluginShowBlock").style.display = "none";
	document.getElementById("payPluginListBlock").style.display = "";
}

// 选择支付方式
function selectPayPlugin(paySystemName, payFriendName) {
	Ajax.get("/order/selectpayplugin?payName=" + paySystemName + "&shipName="
			+ document.getElementById("shipName").value + "&saId="
			+ document.getElementById("saId").value
			+ "&selectedCartItemKeyList="
			+ document.getElementById("selectedCartItemKeyList").value, false,
			function(data) {
				selectPayPluginResponse(data, paySystemName, payFriendName);
			});
}

// 处理选择支付方式的反馈信息
function selectPayPluginResponse(data, paySystemName, payFriendName) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		document.getElementById("payName").value = paySystemName;
		document.getElementById("payPluginShowBlock").style.display = "";
		document.getElementById("payPluginShowBlock").innerHTML = payFriendName;
		document.getElementById("payPluginListBlock").style.display = "none";

		// var oldPayFee = document.getElementById("payFee").value;
		// document.getElementById("payFee").value = result.content;
		var changeMoney = parseFloat(result.content) - parseFloat(0);
		var oldOrderAmount = document.getElementById("orderAmount").value;
		var newOrderAmount = 0;// parseFloat(oldOrderAmount) +
		// parseFloat(changeMoney);
		// document.getElementById("orderAmount").value = newOrderAmount;
		// if (document.getElementById("payFeeShowBlock") != undefined) {
		// document.getElementById("payFeeShowBlock").innerHTML =
		// result.content;
		// }
		// if (document.getElementById("orderAmountShowBlock1") != undefined) {
		// document.getElementById("orderAmountShowBlock1").innerHTML =
		// newOrderAmount;
		// }
		// if (document.getElementById("orderAmountShowBlock2") != undefined) {
		// document.getElementById("orderAmountShowBlock2").innerHTML =
		// newOrderAmount;
		// }
	} else {
		 ZENG.msgbox.show(result.content, 1, 3000);
	}
}

// 验证支付积分
function verifyPayCredit(hasPayCreditCount, maxUsePayCreditCount) {
	var obj = document.getElementById("payCreditCount");
	var usePayCreditCount = obj.value;
	if (isNaN(usePayCreditCount)) {
		obj.value = 0;
		ZENG.msgbox.show("请输入数字！", 1, 3000);
	} else if (usePayCreditCount > hasPayCreditCount) {
		obj.value = hasPayCreditCount;
		ZENG.msgbox.show("积分不足！", 1, 3000);
	} else if (usePayCreditCount > maxUsePayCreditCount) {
		obj.value = maxUsePayCreditCount;
		ZENG.msgbox.show("最多只能使用" + maxUsePayCreditCount + "个", 1, 3000);
	}
}

// 获得有效的优惠劵列表
function getValidCouponList() {
	Ajax.get("/order/getvalidcouponlist?selectedCartItemKeyList="
			+ document.getElementById("selectedCartItemKeyList").value, false,
			getValidCouponListResponse);
}

// 处理获得有效的优惠劵列表的反馈信息
function getValidCouponListResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		if (result.content.length < 1) {
			document.getElementById("validCouponList").innerHTML = "<p>此订单暂无可用的优惠券</p>";
		} else {
			var itemList = "<p class='chooseYH'>";
			for (var i = 0; i < result.content.length; i++) {
				itemList += "<label><input type='checkbox' name='couponId' value='"
						+ result.content[i].couponId
						+ "' useMode='"
						+ result.content[i].usemode
						+ "' onclick='checkCouponUseMode(this)'/>"
						+ result.content[i].name + "</label>";
			}
			itemList += "</p>";
			document.getElementById("validCouponList").innerHTML = itemList;
		}
		document.getElementById("validCouponCount").innerHTML = result.content.length;
	} else {
		 ZENG.msgbox.show(result.content, 1, 3000);
	}
}

// 检查优惠劵的使用模式
function checkCouponUseMode(obj) {
	if (!obj.checked) {
		return;
	}
	var useMode = obj.getAttribute("useMode");
	if (useMode == "0") {
		return;
	}
	var checkboxList = document.getElementById("validCouponList")
			.getElementsByTagName("input");
	for (var i = 0; i < checkboxList.length; i++) {
		checkboxList[i].checked = false;
	}
	obj.checked = true;
}

// 验证优惠劵编号
function verifyCouponSN(couponSN) {
	if (couponSN == undefined || couponSN == null || couponSN.length == 0) {
		ZENG.msgbox.show("请输入优惠劵编号！", 1, 3000);
	} else if (couponSN.length != 16) {
		ZENG.msgbox.show("优惠劵编号不正确！", 1, 3000);
	} else {
		Ajax.get("/order/verifycouponsn?couponSN=" + couponSN, false,
				verifyCouponSNResponse);
	}
}

// 处理验证优惠劵编号的反馈信息
function verifyCouponSNResponse(data) {
	var result = eval("(" + data + ")");
	 ZENG.msgbox.show(result.content, 1, 3000);
}

// 提交订单
function submitOrder(that) {	
	if($(".buyItme .not-able").length>0){
		ZENG.msgbox.show("有些产品不在该区域或者门店销售。请修改购物车！", 1, 3000);
		return false;
	}
	
	var productids = getOrderProduct();
	var vsaId = document.getElementById("saId").value;
	var regionId = document.getElementById("regionId").value;
	// 配送方式
	var shipType = $("#shipType").val();

	var factoryradios = document.getElementsByName("factoryAddress");
	var factoryAddress = "";

	var storeradios = document.getElementsByName("storeAddress");
	var storeAddress = "";

	var date = $("#pack-date" + shipType).val();
	var beginDate = $("#beginDate" + shipType).val();
	var endDate = $("#endDate" + shipType).val();
	var paymode=$("#paymode").val();
	var buyerRemark = $("#buyerRemark").val();
	var isneedEles=$("#order").find(".isneed-inv-radio");
	var needinv=$("#order").find("input.isneed-inv-radio[type='radio']:checked").val();

	var sendData={
			productids : productids,
			saId : vsaId,
			regionId : regionId,
			shipType : shipType,
			date : date,
			buyerRemark : buyerRemark,
			beginDate : beginDate,
			endDate : endDate,
			paymode:paymode,
			factoryAddress : factoryAddress,
			storeAddress : storeAddress,
			needinv:needinv
		}
	
	if(shipType!=3){//是自提，需要证件信息
		var inputeles=$("#self-pick-extinfor").find("input");
		for(var ipi=0;ipi<inputeles.length;ipi++){
			var ipiele=inputeles[ipi];
			var tname=$(ipiele).attr("name");
			sendData[tname]=$(ipiele).val();
		}
		var seleles=$("#self-pick-extinfor").find("select");
		for(var si=0;si<seleles.length;si++){
			var siele=seleles[si];
			var tname=$(siele).attr("name");
			sendData[tname]=$(siele).val();
		}
		
	}
	if(needinv==1){//需要发票
		var invtype=$("#invoice-tab-content").find(".tab-nav-item.active").attr("value");
		var parele=null;
		if(invtype==1){//普通发票
			parele=$("#fapiao-1");			
		}else if(invtype==2){//增值税发票
			parele=$("#fapiao-2");
			sendData["invoice.regionname"]=$(parele).find(".pshow-name-ele-invoice").html();
			
			var areapanel=$("#hs-float-panel-invoice");
			sendData["invoice.regionid"]=$(areapanel).find("[name='lastName']").val();
			sendData["invoice.regioncid"]=$(areapanel).attr("cid");
		}
		if(parele){
			var inputeles=$(parele).find("input[type='text']");
			for(var ipi=0;ipi<inputeles.length;ipi++){
				var ipiele=inputeles[ipi];
				var tname=$(ipiele).attr("name");
				sendData[tname]=$(ipiele).val();
			}
			var hiddeneles=$(parele).find("input[type='hidden']");
			for(var ipi=0;ipi<hiddeneles.length;ipi++){
				var ipiele=hiddeneles[ipi];
				var tname=$(ipiele).attr("name");
				sendData[tname]=$(ipiele).val();
			}
			var inputeles=$(parele).find("input[type='radio']:checked");
			for(var ipi=0;ipi<inputeles.length;ipi++){
				var ipiele=inputeles[ipi];
				var tname=$(ipiele).attr("name");
				sendData[tname]=$(ipiele).val();
			}
		}
		/////////////
		var fpExtinfor=$("#fapiao-extinfor");
		var inputeles=$(fpExtinfor).find("input[type='text']");
		for(var ipi=0;ipi<inputeles.length;ipi++){
			var ipiele=inputeles[ipi];
			var tname=$(ipiele).attr("name");
			sendData[tname]=$(ipiele).val();
		}
		////////////////
		var inputeles=$(fpExtinfor).find("input[type='radio']:checked");
		for(var ipi=0;ipi<inputeles.length;ipi++){
			var ipiele=inputeles[ipi];
			var tname=$(ipiele).attr("name");
			sendData[tname]=$(ipiele).val();
		}
	}
	if (!verifySubmitOrder(vsaId, shipType, buyerRemark)) {
		return;
	}

	var salerid="-1";
	if(shipType==1){//门店自提
		salerid=$("#store").attr("saleid");
		sendData["storeAddress"]=$("#store").html();	
	}else if(shipType==2){//工厂自提
		salerid=$("#factory").attr("saleid");
		sendData["factoryAddress"]=$("#factory").html();
	}else if(shipType==3){//配送到家,优先选择门店
		var tid=$("#store").attr("saleid");
		if(!tid || tid<=0){
			tid=$("#factory").attr("saleid");
		}
		salerid=tid;
	}
	sendData.salerid=salerid;
	//sendData["order.salerid"]=salerid;
	//
	ZENG.msgbox.show("正在提交。。。。。。", 6);
	$('#submitbtn').attr("disabled","disabled");//去掉a标签中的onclick事件
	Ajax.post("submitOrder.do",sendData , false, submitOrderResponse);
}


function getOrderProduct() {
	// 订单中的产品
	// var productIds =document.getElementsByName('productId');

	// var valueList = new Array();
	// if(productIds != undefined && productIds != null)
	// for (var i = 0; i < productIds.length; i++) {
	// valueList.push(productIds[i].value);
	// }
	//	
	// if (valueList.length < 1) {
	// return "";
	// } else {
	// return valueList.join(',');
	// }
	var productId = document.getElementById("productId").value;
	return productId;
}

// 验证提交订单
function verifySubmitOrder(saId, shipType, buyerRemark) {

	if (saId < 1) {
		ZENG.msgbox.show("请填写收货人信息！", 1, 3000);
		return false;
	}

	if (shipType == undefined || "" == shipType) {
		ZENG.msgbox.show("请选择配送方式！", 1, 3000);
		return false;
	}

	var date = $("#pack-date" + shipType).val();
	if (date == undefined || date == "" || date.indexOf("日期") != -1) {
		if (shipType == 1 || shipType == 2) {
			ZENG.msgbox.show("请选择自提日期！", 1);
		} else {
			ZENG.msgbox.show("请选择配送日期！", 1, 3000);
		}
		return false;
	}

	if (buyerRemark.length > 125) {
		ZENG.msgbox.show("最多只能输入125个字！", 1, 3000);
		return false;
	}

	if ($("#contract").is(':checked') == false) {
		ZENG.msgbox.show("请选择同意红狮水泥商城《电子合同》！", 1, 3000);
		return false;
	}

	return true;
}

// 处理提交订单的反馈信息
function submitOrderResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state != "success") {
		 ZENG.msgbox.show(result.content, 4, 3000);
	} else {
		window.location.href = result.content;
	}
}

// 计算地址的配送费用
function myCalculateShipFeeOfAddress(pickId) {
	// 收货地址id
	var regionId = document.getElementById("regionId").value;
	// 订单中的产品
	var productId = document.getElementById("productId").value;

	if ((regionId == undefined || regionId == null || regionId.length == 0)
			|| (productId == undefined || productId == null || productId.length == 0)) {
		return;
	}
	// regionId = 1316;
	Ajax.get("getShipAddressInfo.do?regionId=" + regionId
							+ "&productId=" + productId + "&pickId=" + pickId,
					false,
					function(data) {
						//
						var result = eval("(" + data + ")");

						// 运费
						var price = result.price;
						document.getElementById("shipFee").value = price;
						if (document.getElementById("shipFeeShowBlock") != undefined) {
							document.getElementById("shipFeeShowBlock").innerHTML = price;
						}

						// 装卸费
						var handlingCost = result.handlingCost;
						document.getElementById("handlingCost").value = handlingCost;
						if (document.getElementById("handlingCostShowBlock") != undefined) {
							document.getElementById("handlingCostShowBlock").innerHTML = handlingCost;
						}
						var cartproduct = result.cartproduct;
						// 循环计算购物车商品费用
						var newOrderAmount = 0;
						for (var ci = 0; ci < cartproduct.length; ci++) {
							var quantityunitid = cartproduct[ci].quantityunitid;
							var product = cartproduct[ci];
							var prodid = product.Pid;

							var freight = "¥" + cartproduct[ci].freight;
							var carry = "¥" + cartproduct[ci].carry;
							var itemMny="¥"+cartproduct[ci].itemTotalMny;//

							if (quantityunitid == 4) {
								var marketPrice = "¥" + product.originalPrice
										+ "元/吨";
							} else if (quantityunitid == 3) {
								var marketPrice = "¥" + product.MarketPrice
										+ "元/包";
							}
							var marketPriceEle = $("#marketPriceEle" + prodid);
							var freightMoney = $("#freightMoney" + prodid);
							var carryMoney = $("#carryMoney" + prodid);
							var itemMnyEle = $("#itemMnyEle" + prodid);
							marketPriceEle.html(marketPrice);
							freightMoney.html(freight);
							carryMoney.html(carry);
							itemMnyEle.html(itemMny);

						}

						// 显示购物车的商品的购物额
						$("#order .amount-mny").html(result.totalProductPrice);
						// 计算总的应付款额，包括运费，并写入到表单

						document.getElementById("orderAmount").value = newOrderAmount;

						if (document.getElementById("orderAmountShowBlock1") != undefined) {
							document.getElementById("orderAmountShowBlock1").innerHTML = result.totalOrderPrice;
						}
						if (document.getElementById("orderAmountShowBlock2") != undefined) {
							document.getElementById("orderAmountShowBlock2").innerHTML = result.totalOrderPrice;

						}

					});
}
