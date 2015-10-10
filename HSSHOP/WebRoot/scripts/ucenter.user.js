//删除收藏夹中的商品
function delFavoriteProduct(recordid,pid) {
	Ajax.get("delfavoriteproduct.do?recordid=" + recordid +"& pid ="+pid, false,
			delFavoriteProductResponse);
}

// 处理删除收藏夹中的商品的反馈信息
function delFavoriteProductResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		removeNode(document.getElementById("favoriteProduct" + result.content));
		ZENG.msgbox.show("删除成功！", 4, 3000);
	} else {
		ZENG.msgbox.show(result.content, 5, 3000);
	}
}

// 打开添加配送地址层
function openAddShipAddressBlock() {
	var shipAddressForm = document.forms["shipAddressForm"];

	shipAddressForm.elements["saId"].value = "";
	shipAddressForm.elements["consignee"].value = "";
	shipAddressForm.elements["mobile"].value = "";
	shipAddressForm.elements["phone"].value = "";
	shipAddressForm.elements["email"].value = "";
	shipAddressForm.elements["zipcode"].value = "";
	shipAddressForm.elements["address"].value = "";
	shipAddressForm.elements["isDefault"].checked = true;

	document.getElementById("editShipAddressBut").style.display = "none";
	document.getElementById("addShipAddressBut").style.display = "";
	document.getElementById("shipAddressBlock").style.display = "";

	//document.getElementById("ProvinceId").value = "";
	//document.getElementById("CityId").value = "";
	//document.getElementById("CountyId").value = "";
	//document.getElementById("StreetId").value = "";
	//document.getElementById("TownshipId").value = "";

	$("#ProvinceId").val("");
	$("#CityId").val("");
	$("#CountyId").val("");
	$("#StreetId").val("");
	$("#TownshipId").val("");
	$("#lastName").val("");
	
	$("#shipAddressBlock").find(".pshow-name-ele").html("");
	//document.getElementById("lastName").value = "";
	$("#shipAddressBlock .panel-close-btn").click();
	$("#shipAddressBlock").find("[role=\"hs-area-sel\"]").attr("cid", "0");
}

// 打开编辑配送地址层
function openEditShipAddressBlock(saId) {
	Ajax.get("shipaddressinfo.do?saId=" + saId, false,
			openEditShipAddressBlockResponse);
}

// 处理打开编辑配送地址层的反馈信息
function openEditShipAddressBlockResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {

		var shipAddressForm = document.forms["shipAddressForm"];

		var info = result.content;
		shipAddressForm.elements["saId"].value = info.said;
		shipAddressForm.elements["consignee"].value = info.consignee;
		shipAddressForm.elements["mobile"].value = info.mobile;
		shipAddressForm.elements["phone"].value = info.phone;
		shipAddressForm.elements["email"].value = info.email;
		shipAddressForm.elements["zipcode"].value = info.zipcode;
		shipAddressForm.elements["address"].value = info.address;

		if (info.isDefault == 1) {
			shipAddressForm.elements["isDefault"].checked = true;
		} else {
			shipAddressForm.elements["isDefault"].checked = false;
		}

		// setSelectedOptions(document.getElementById("provinceId"),
		// info.provinceId);
		// bindCityList(info.provinceId, document.getElementById("cityId"),
		// info.cityId);
		// bindCountyList(info.cityId, document.getElementById("regionId"),
		// info.countyId);
		var attrArr = [ "provinceId", "cityId", "countyId", "streetId",
				"townshipId" ];
		var nameArr = [ "provinceName", "cityName", "countyName", "streetName",
				"townshipName" ];
		var lastName = "0";
		var thisCid = "";
		document.getElementById("ProvinceId").value = info.provinceId;
		document.getElementById("CityId").value = info.cityId;
		document.getElementById("CountyId").value = info.countyId;
		document.getElementById("StreetId").value = info.streetId;
		document.getElementById("TownshipId").value = info.townshipId;
		var allregName = "";
		for ( var atri = 0; atri < attrArr.length; atri++) {
			var regId = info[attrArr[atri]];
			var regName = info[nameArr[atri]];
			;
			if (regId !== "0") {
				lastName = regId;
				allregName = allregName + regName + "-";
				thisCid = thisCid + regId + "-";

			} else {
				thisCid = thisCid + "0";
				break;
			}
		}
		if (allregName.lastIndexOf("-") === (allregName.length - 1)) {
			allregName = allregName.substring(0, allregName.length - 1);
		}
		$("#shipAddressBlock").find(".pshow-name-ele").html(allregName);
		document.getElementById("lastName").value = lastName;
		if (thisCid) {
			thisCid += "-0";
			$("#shipAddressBlock").find("[role=\"hs-area-sel\"]").attr("cid",
					thisCid);

		}
		$("#shipAddressBlock .panel-close-btn").click();

		document.getElementById("addShipAddressBut").style.display = "none";
		document.getElementById("editShipAddressBut").style.display = "";
		document.getElementById("shipAddressBlock").style.display = "";
	} else {
		ZENG.msgbox.show(result.content, 5, 3000);
	}
}

// 关闭配送地址层
function closeShipAddressBlock() {

	var shipAddressForm = document.forms["shipAddressForm"];

	shipAddressForm.elements["saId"].value = "";
	shipAddressForm.elements["consignee"].value = "";
	shipAddressForm.elements["mobile"].value = "";
	shipAddressForm.elements["phone"].value = "";
	shipAddressForm.elements["email"].value = "";
	shipAddressForm.elements["zipcode"].value = "";
	shipAddressForm.elements["address"].value = "";
	shipAddressForm.elements["isDefault"].checked = true;

	document.getElementById("addShipAddressBut").style.display = "none";
	document.getElementById("editShipAddressBut").style.display = "none";
	document.getElementById("shipAddressBlock").style.display = "none";
}

// 验证配送地址
function verifyShipAddress(consignee, mobile, phone, regionId, address) {
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

// 添加配送地址
function addShipAddress1() {
	var shipAddressForm = document.forms["shipAddressForm"];
	var consignee = shipAddressForm.elements["consignee"].value;
	var mobile = shipAddressForm.elements["mobile"].value;
	var phone = shipAddressForm.elements["phone"].value;
	var email = shipAddressForm.elements["email"].value;
	var zipcode = shipAddressForm.elements["zipcode"].value;
	var regionId = shipAddressForm.elements["lastName"].value;
	var address = shipAddressForm.elements["address"].value;
	var isDefault = shipAddressForm.elements["isDefault"].checked == true ? 1
			: 0;

	if (!verifyShipAddress(consignee, mobile, phone, regionId, address)) {
		return;
	}

	Ajax.post("addshipaddress.do", {
		consignee : consignee,
		mobile : mobile,
		phone : phone,
		email : email,
		zipcode : zipcode,
		regionId : regionId,
		address : address,
		isDefault : isDefault
	}, false, addShipAddressResponse);
}

// 处理添加配送地址的反馈信息
function addShipAddressResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		closeShipAddressBlock();
		window.location.href = "shipaddresslist.do";
	} else if (result.state == "full") {
		ZENG.msgbox.show("配送地址的数量已经达到系统所允许的最大值！", 1, 3000);
	} else if (result.state == "error") {
		var msg = "";
		for ( var i = 0; i < result.content.length; i++) {
			msg += result.content[i].msg + "\n";
		}
		ZENG.msgbox.show(msg, 5, 3000);
	}
}

// 编辑配送地址
function editShipAddress() {
	var shipAddressForm = document.forms["shipAddressForm"];
	var saId = shipAddressForm.elements["saId"].value;
	var consignee = shipAddressForm.elements["consignee"].value;
	var mobile = shipAddressForm.elements["mobile"].value;
	var phone = shipAddressForm.elements["phone"].value;
	var email = shipAddressForm.elements["email"].value;
	var zipcode = shipAddressForm.elements["zipcode"].value;
	var regionId = shipAddressForm.elements["lastName"].value;
	var address = shipAddressForm.elements["address"].value;
	var isDefault = shipAddressForm.elements["isDefault"].checked == true ? 1
			: 0;

	if (saId < 1) {
		ZENG.msgbox.show("请选择配送地址！", 1, 3000);
		return;
	}
	if (!verifyShipAddress(consignee, mobile, phone, regionId, address)) {
		return;
	}

	Ajax.post("editshipaddress.do?saId=" + saId, {
		consignee : consignee,
		mobile : mobile,
		phone : phone,
		email : email,
		zipcode : zipcode,
		regionId : regionId,
		address : address,
		isDefault : isDefault
	}, false, editShipAddressResponse);
}

// 处理编辑配送地址的反馈信息
function editShipAddressResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		closeShipAddressBlock();
		window.location.href = "shipaddresslist.do";
	} else if (result.state == "noexist") {
		ZENG.msgbox.show("配送地址不存在！", 1, 3000);
	} else if (result.state == "error") {
		var msg = "";
		for ( var i = 0; i < result.content.length; i++) {
			msg += result.content[i].msg + "\n";
		}
		ZENG.msgbox.show(msg, 5, 3000);
	}
}

// 删除配送地址
function delShipAddress(saId) {
	Ajax.get("delshipaddress.do?saId=" + saId, false,
			delShipAddressResponse);
}

// 处理删除配送地址的反馈信息
function delShipAddressResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		removeNode(document.getElementById("shipAddress" + result.content));
		ZENG.msgbox.show("删除成功", 4, 3000);
	} else {
		ZENG.msgbox.show(result.content, 5, 3000);
	}
}

// 设置默认配送地址
function setDefaultShipAddress(saId, obj) {
	Ajax.get("setdefaultshipaddress.do?saId=" + saId, false, function(data) {
		setDefaultShipAddressResponse(data, obj);
	});
}

// 处理设置默认配送地址的反馈信息
function setDefaultShipAddressResponse(data, obj) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		var defaultShipAddress = document.getElementById("defaultShipAddress");
		if (defaultShipAddress != undefined) {
			defaultShipAddress.style.display = "";
			defaultShipAddress.id = "";
		}
		obj.style.display = "none";
		obj.id = "defaultShipAddress";
	} else {
		ZENG.msgbox.show(result.content, 5, 3000);
	}
}

// 编辑用户
function editUser() {
	var userInfoForm = document.forms["userInfoForm"];

	var userName = userInfoForm.elements["userName"] ? userInfoForm.elements["userName"].value
			: "";
	var nickName = userInfoForm.elements["nickName"].value;
	var realName = userInfoForm.elements["realName"].value;
	var avatar = userInfoForm.elements["avatar"] ? userInfoForm.elements["avatar"].value
			: "";
	var gender = getSelectedRadio(userInfoForm.elements["gender"]).value;
	var idCard = userInfoForm.elements["idCard"].value;
	var bday = userInfoForm.elements["bday"].value;
	var regionId = userInfoForm.elements["lastName"].value;
	var address = userInfoForm.elements["address"].value;
	var bio = userInfoForm.elements["bio"].value;

	if (!verifyEditUser(userName, nickName, realName, address, bio)) {
		return;
	}
	Ajax.post("edituser.do",
	{ userName: userName, nickName: nickName, realName: realName, avatar:
		avatar, gender: gender, idCard: idCard, bday: bday, regionId: regionId,
		address: address, bio: bio },
	false,
	editUserResponse);
}

// 验证编辑用户
function verifyEditUser(userName, nickName, realName, address, bio) {
	if (userName != undefined) {
		if (userName.length > 10) {
			ZENG.msgbox.show("用户名长度不能大于10！", 1, 3000);
			return false;
		}
	}
	if (nickName.length > 10) {
		ZENG.msgbox.show("昵称长度不能大于10！", 1, 3000);
		return false;
	}
	if (realName.length > 5) {
		ZENG.msgbox.show("真实姓名长度不能大于10！", 1, 3000);
		return false;
	}
	if (address.length > 75) {
		ZENG.msgbox.show("详细地址长度不能大于75！", 1, 3000);
		return false;
	}
	if (bio.length > 150) {
		ZENG.msgbox.show("简介长度不能大于150！", 1, 3000);
		return false;
	}
	return true;
}

// 处理编辑用户的反馈信息
function editUserResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		ZENG.msgbox.show(result.content, 4, 3000);
	} else if (result.state == "error") {
		ZENG.msgbox.show("更新出错！", 5, 3000);
	}
}