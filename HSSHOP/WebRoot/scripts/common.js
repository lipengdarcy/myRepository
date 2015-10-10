﻿﻿var uid = -1; //用户id
var isGuestSC = 0; //是否允许游客使用购物车(0代表不可以，1代表可以)
var scSubmitType = 0; //购物车的提交方式(0代表跳转到提示页面，1代表跳转到列表页面，2代表ajax提交)
var url="http://localhost:8080/HSSNSHOPADMIN/"; //网站url 

//获得购物车快照
var isAlreadyLoadCartSnap = false;
function getCartSnap() {
    if (isGuestSC == 0 && uid < 1) {
        return;
    }
    var cartSnap = document.getElementById("cartSnap");
    //获取cookie中的区域id
    var areaId= $.cookie('lastarea'); 
    if(areaId == undefined) {
    	areaId = 0;
    }

    cartSnap.style.display = "";
   
    if (!isAlreadyLoadCartSnap) {
       
        isAlreadyLoadCartSnap = true;
        Ajax.get(url+"cart/snap.do?areaId="+areaId, false, function (data) {
            getCartSnapResponse(data);
        })
    }
}

//处理获得购物车快照的反馈信息
function getCartSnapResponse(data) {
	var cartSnap = document.getElementById("cartSnap");
	try {
		var result = eval("(" + data + ")");
		ZENG.msgbox.show(result.content, 1, 3000);
	} catch (ex) {
		cartSnap.innerHTML = data;
		//document.getElementById("cartSnapProudctCount").innerHTML = document
				//.getElementById("csProudctCount").innerHTML;
		
		document.getElementById("cartSnapProudctCount").innerHTML = document.getElementById("productCount").value;
	}
}

//关闭购物车快照
function closeCartSnap(event) {
	if (Browser.isFirefox()
			&& document.getElementById('cartSnapBox').contains(
					event.relatedTarget))
		return;
	var cartSnap = document.getElementById("cartSnap");
	cartSnap.style.display = "none";
}

//添加商品到收藏夹
function addToFavorite(pid) {
	uid = 100;
	if (pid < 1) {
		ZENG.msgbox.show("请选择商品", 1, 3000);
	} else if (uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"account/login.do?return_url="+encodeURI(window.location.href);
	} else {
		Ajax.get(url+"/product/addToFavorite.do?pid=" + pid, false,
				addToFavoriteResponse)
	}
}

//处理添加商品到收藏夹的反馈信息
function addToFavoriteResponse(data) {
	var result = eval("(" + data + ")");
	ZENG.msgbox.show(result.content, 1, 3000);
}

function testCado(){
	if($("#has-factory").length==0||$("#has-store").length==0){
		return true;
	}
	var ifac=$("#has-factory").attr("hasit");
	var isto=$("#has-store").attr("hasit");
	var pickway=$("[pick-way].active").attr("pick-way");
	if(pickway=="1"){//判断门店是否存在
		if(!isto){
			ZENG.msgbox.show("该区域没有门店，无法添加或购买", 1, 3000);
			return false;
		}
	}else if(pickway=="2"){//判断工厂是否存在
		if(!ifac){
			ZENG.msgbox.show("该区域没有工厂，无法添加或购买", 1, 3000);
			return false;
		}
	}else{
		if(!ifac && !isto){
			ZENG.msgbox.show("该区域没有工厂和门店，无法添加或购买", 1, 3000);
			return false;
		}
	}
	return true;
}

//添加商品到购物车
function addProductToCart(pid, buyCount) {
	if(testCado()==false){		
		return;
	}
	var areaParam="";
	var areaId=$.cookie('lastarea'); 
	if($("#has-factory").length==0||$("#has-store").length==0){
		areaParam="&areaId="+areaId;
	}
	
	if (pid < 1) {
		ZENG.msgbox.show("请选择商品", 1, 3000);
	} else if (buyCount < 1) {
		ZENG.msgbox.show("请填写购买数量", 1, 3000);
	} else if (areaId == undefined || areaId == null || areaId == ''){
		ZENG.msgbox.show("请先选择区域", 1, 3000);
	}else {
		Ajax.get(url+"cart/addproduct.do?pid=" + pid + "&buyCount=" + buyCount+areaParam,
				false, addProductToCartResponse)
	}
}


//处理添加商品到购物车的反馈信息
function addProductToCartResponse(data) {
	var result = eval("("+data+")");
	 	result = eval("("+result+")");
	if(result.status == "ok"){
		window.location.href = url+"/cart/addproductSucess.do?pid="+result.pid;
	}else{
		ZENG.msgbox.show(result.msg, 1, 3000);
	}
}

//直接购买商品
function directBuyProduct(pid, buyCount) {	
	if (pid < 1) {
		ZENG.msgbox.show("请选择商品", 1, 3000);
	} else if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
	} else if (buyCount < 1) {
		ZENG.msgbox.show("请填写购买数量", 1, 3000);
	} else {
		Ajax.get("/cart/directbuyproduct?pid=" + pid + "&buyCount=" + buyCount,
				false, directBuyProductResponse)
	}
}

//处理直接购买商品的反馈信息
function directBuyProductResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		window.location.href = result.content;
	} else {
		ZENG.msgbox.show(result.content, 1, 3000);
	}
}

//添加套装到购物车
function addSuitToCart(pmId, buyCount) {
	if (pmId < 1) {
		ZENG.msgbox.show("请选择套装", 1, 3000);
	} else if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
	} else if (buyCount < 1) {
		ZENG.msgbox.show("请填写购买数量", 1, 3000);
	} else if (scSubmitType != 2) {
		window.location.href = "/cart/addsuit?pmId=" + pmId + "&buyCount="
				+ buyCount;
	} else {
		Ajax.get("/cart/addsuit?pmId=" + pmId + "&buyCount=" + buyCount, false,
				addSuitToCartResponse)
	}
}

//处理添加套装到购物车的反馈信息
function addSuitToCartResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state != "stockout") {
		ZENG.msgbox.show(result.content, 1, 3000);
	} else {
		ZENG.msgbox.show("商品库存不足", 1, 3000);
	}
}

//直接购买套装
function directBuySuit(pmId, buyCount) {
	if (pmId < 1) {
		ZENG.msgbox.show("请选择套装", 1, 3000);
	} else if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
	} else if (buyCount < 1) {
		ZENG.msgbox.show("请填写购买数量", 1, 3000);
	} else {
		Ajax.get("/cart/directbuysuit?pmId=" + pmId + "&buyCount=" + buyCount,
				false, directBuySuitResponse)
	}
}

//处理直接购买套装的反馈信息
function directBuySuitResponse(data) {
	var result = eval("(" + data + ")");
	if (result.state == "success") {
		window.location.href = result.content;
	} else {
		ZENG.msgbox.show(result.content, 1, 3000);
	}
}

//获得选中的购物车项键列表
function getSelectedCartItemKeyList() {
	var inputList = document.getElementById("cartBody").getElementsByTagName(
			"input");

	var valueList = new Array();
	for (var i = 0; i < inputList.length; i++) {
		if (inputList[i].type == "checkbox"
				&& inputList[i].name == "cartItemCheckbox"
				&& inputList[i].checked) {
			valueList.push(inputList[i].value);
		}
	}

	if (valueList.length < 1) {
		//当取消全部商品时,添加一个字符防止商品全部选中
		return "_";
	} else {
		return valueList.join(',');
	}
}

//设置选择全部购物车项复选框
function setSelectAllCartItemCheckbox() {
	var inputList = document.getElementById("cartBody").getElementsByTagName(
			"input");

	var flag = true;
	for (var i = 0; i < inputList.length; i++) {
		if (inputList[i].type == "checkbox"
				&& inputList[i].name == "cartItemCheckbox"
				&& !inputList[i].checked) {
			flag = false;
			break;
		}
	}

	if (flag) {
		document.getElementById("selectAllBut_top").checked = true;
		document.getElementById("selectAllBut_bottom").checked = true;
	} else {
		document.getElementById("selectAllBut_top").checked = false;
		document.getElementById("selectAllBut_bottom").checked = false;
	}
}

//删除购物车中商品
function delCartProduct(pid, pos) {
    if (isGuestSC == 0 && uid < 1) {
    	ZENG.msgbox.show("请先登录", 1, 5000);
        window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
        return;
    }
    if (pos == 0) {
        Ajax.get(url+"/cart/delpruduct.do?pid=" + pid + "&pos=" + pos + "&selectedCartItemKeyList=" + getSelectedCartItemKeyList(), false, function (data) {
            try {
                ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
            }
            catch (ex) {
                document.getElementById("cartBody").innerHTML = data;
                setSelectAllCartItemCheckbox();
            }
        })
    }
    else {
    	
        Ajax.get(url+"cart/delpruduct.do?pid=" + pid + "&pos=" + pos, false, function (data) {
            try {
                ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
            }
            catch (ex) {
                document.getElementById("cartSnap").innerHTML = data;
               // document.getElementById("cartSnapProudctCount").innerHTML = document.getElementById("csProudctCount").innerHTML; csProudctCount
               //alert( document.getElementById("productCount").value);
                document.getElementById("cartSnapProudctCount").innerHTML =document.getElementById("productCount").value;
            }
        })
    }
}

//删除购物车中套装
function delCartSuit(pmId, pos) {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
		return;
	}
	if (pos == 0) {
		Ajax.get("/cart/delsuit?pmId=" + pmId + "&pos=" + pos
				+ "&selectedCartItemKeyList=" + getSelectedCartItemKeyList(),
				false, function(data) {
					try {
						 ZENG.msgbox.show(eval("(" + data + ")").content, 1, 3000);
					} catch (ex) {
						document.getElementById("cartBody").innerHTML = data;
						setSelectAllCartItemCheckbox();
					}
				})
	} else {
		Ajax
				.get(
						"/cart/delsuit?pmId=" + pmId + "&pos=" + pos,
						false,
						function(data) {
							try {
								ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
							} catch (ex) {
								document.getElementById("cartSnap").innerHTML = data;
								document.getElementById("cartSnapProudctCount").innerHTML = document
										.getElementById("csProudctCount").innerHTML;
							}
						})
	}
}

//删除购物车中满赠
function delCartFullSend(pmId, pos) {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
		return;
	}
	if (pos == 0) {
		Ajax.get("/cart/delfullsend?pmId=" + pmId + "&pos=" + pos
				+ "&selectedCartItemKeyList=" + getSelectedCartItemKeyList(),
				false, function(data) {
					try {
						ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
					} catch (ex) {
						document.getElementById("cartBody").innerHTML = data;
						setBatchSelectCartItemCheckbox();
					}
				})
	} else {
		Ajax
				.get(
						"/cart/delfullsend?pmId=" + pmId + "&pos=" + pos,
						false,
						function(data) {
							try {
								ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
							} catch (ex) {
								document.getElementById("cartSnap").innerHTML = data;
								document.getElementById("cartSnapProudctCount").innerHTML = document
										.getElementById("csProudctCount").innerHTML;
							}
						})
	}
}

//清空购物车
function clearCart(pos) {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
		return;
	}
	Ajax
			.get(
					url+"cart/clear.do?pos=" + pos,
					false,
					function(data) {
						try {
							ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
						} catch (ex) {
							if (pos == 0) {
								document.getElementById("cartBody").innerHTML = data;
							} else {
								document.getElementById("cartSnap").innerHTML = data;
								document.getElementById("cartSnapProudctCount").innerHTML = "0";
							}
						}
					})
}

//改变商品数量
function changePruductCount(pid, buyCount) {
	if (!isInt(buyCount)) {
		ZENG.msgbox.show("请输入数字", 1, 3000);
	} else if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
	} else {
		var key = "0_" + pid;
		var inputList = document.getElementById("cartBody")
				.getElementsByTagName("input");
		for (var i = 0; i < inputList.length; i++) {
			if (inputList[i].type == "checkbox" && inputList[i].value == key) {
				inputList[i].checked = true;
				break;
			}
		}
		
		//获取cookie中的区域id
	    var areaId= $.cookie('lastarea'); 
	    if(areaId == undefined) {
	    	areaId = 0;
	    }
		Ajax.get(url+"cart/changepruductcount.do?pid=" + pid + "&buyCount="
				+ buyCount + "&selectedCartItemKeyList="
				+ getSelectedCartItemKeyList()+"&areaId="+areaId, false, function(data) {
			try {
				ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
			} catch (ex) {
				document.getElementById("cartBody").innerHTML = data;
				setSelectAllCartItemCheckbox();
			}
		})
	}
}

//改变套装数量
function changeSuitCount(pmId, buyCount) {
	if (!isInt(buyCount)) {
		ZENG.msgbox.show("请输入数字", 1, 3000);
	} else if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
	} else {
		var key = "1_" + pmId;
		var inputList = document.getElementById("cartBody")
				.getElementsByTagName("input");
		for (var i = 0; i < inputList.length; i++) {
			if (inputList[i].type == "checkbox" && inputList[i].value == key) {
				inputList[i].checked = true;
				break;
			}
		}
		Ajax.get("cart/changesuitcount.do?pmId=" + pmId + "&buyCount="
				+ buyCount + "&selectedCartItemKeyList="
				+ getSelectedCartItemKeyList(), false, function(data) {
			try {
				ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
			} catch (ex) {
				document.getElementById("cartBody").innerHTML = data;
				setSelectAllCartItemCheckbox();
			}
		})
	}
}

//获取满赠商品
function getFullSend(pmId) {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
		return;
	}
	Ajax.get("/cart/getfullsend?pmId=" + pmId, false, function(data) {
		getFullSendResponse(data, pmId);
	})
}

//处理获取满赠商品的反馈信息
var selectedFullSendPid = 0;
function getFullSendResponse(data, pmId) {
	var result = eval("(" + data + ")");
	if (result.state != "success") {
		ZENG.msgbox.show(result.content, 4, 3000);
	} else {
		if (result.content.length < 1) {
			ZENG.msgbox.show("满赠商品不存在", 1, 3000);
			return;
		}
		var html = "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
		for (var i = 0; i < result.content.length; i++) {
			html += "<tr><td width='30' align='center'><input type='radio' name='fullSendProduct' value='"
					+ result.content[i].pid
					+ "' onclick='selectedFullSendPid=this.value'/></td><td width='70'><img src='/upload/product/show/thumb50_50/"
					+ result.content[i].showImg
					+ "' width='50' height='50' /></td><td valign='top'><a href='"
					+ result.content[i].url
					+ "'>"
					+ result.content[i].name
					+ "</a><em>¥"
					+ result.content[i].shopPrice
					+ "</em></td></tr>";
		}
		html += "</table>";
		selectedFullSendPid = 0;
		document.getElementById("fullSendProductList" + pmId).innerHTML = html;
		document.getElementById("fullSendBlock" + pmId).style.display = "block";
	}
}

//关闭满赠层
function closeFullSendBlock(pmId) {
	selectedFullSendPid = 0;
	document.getElementById("fullSendProductList" + pmId).innerHTML = "";
	document.getElementById("fullSendBlock" + pmId).style.display = "none";
}

//添加满赠商品
function addFullSend(pmId) {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
	} else if (selectedFullSendPid < 1) {
		ZENG.msgbox.show("请先选择商品", 1, 3000);
	} else {
		Ajax.get("/cart/addfullsend?pmId=" + pmId + "&pid="
				+ selectedFullSendPid + "&selectedCartItemKeyList="
				+ getSelectedCartItemKeyList(), false, function(data) {
			try {
				ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
			} catch (ex) {
				document.getElementById("cartBody").innerHTML = data;
				setSelectAllCartItemCheckbox();
			}
		})
		closeFullSendBlock(pmId);
	}
}

//取消或选中购物车项
function cancelOrSelectCartItem() {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
		return;
	}
	var v = getSelectedCartItemKeyList();
	Ajax.get("cart/cancelorselectcartitem.do?selectedCartItemKeyList="
			+ getSelectedCartItemKeyList(), false, function(data) {
		try {
			ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
		} catch (ex) {
			document.getElementById("cartBody").innerHTML = data;
			setSelectAllCartItemCheckbox();
		}
	})
}

//取消或选中全部购物车项
function cancelOrSelectAllCartItem(obj) {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
		return;
	}
	if (obj.checked) {
		Ajax.get("cart/selectallcartitem.do", false, function(data) {
			try {
				ZENG.msgbox.show(val("(" + data + ")").content, 1, 3000);
			} catch (ex) {
				document.getElementById("cartBody").innerHTML = data;
			}
		})
	} else {
		var inputList = document.getElementById("cartBody")
				.getElementsByTagName("input");
		for (var i = 0; i < inputList.length; i++) {
			if (inputList[i].type == "checkbox") {
				inputList[i].checked = false;
			}
		}
		document.getElementById("totalCount").innerHTML = "0";
		document.getElementById("productAmount").innerHTML = "0.00";
		document.getElementById("fullCut").innerHTML = "0";
		document.getElementById("orderAmount").innerHTML = "0.00";
	}
}

//前往确认订单
function goConfirmOrder() {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
		return;
	}
	var inputList = document.getElementById("cartBody").getElementsByTagName(
			"input");

	var checkboxList = new Array();
	for (var i = 0; i < inputList.length; i++) {
		if (inputList[i].type == "checkbox"
				&& inputList[i].name != "selectAllBut_top"
				&& inputList[i].name != "selectAllBut_bottom") {
			checkboxList.push(inputList[i]);
		}
	}

	var valueList = new Array();
	for (var i = 0; i < checkboxList.length; i++) {
		if (checkboxList[i].checked) {
			valueList.push(checkboxList[i].value);
		}
	}

	if (valueList.length < 1) {
		ZENG.msgbox.show("请先选择购物车商品", 1, 3000);
	} else {
		if (valueList.length != checkboxList.length) {
			document.getElementById("selectedCartItemKeyList").value = valueList
					.join(',');
		}
		document.forms[0].submit();
	}
	
}

//前往确认订单
function goConfirmOrder1() {
	if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
		return;
	}

		document.forms[0].submit();
	
}

//立即购买
function addProductToBuy(pid, buyCount) {
	if(testCado()==false){
		return;
	}	
	if (pid < 1) {
		ZENG.msgbox.show("请先选择商品", 1, 3000);
	} else if (isGuestSC == 0 && uid < 1) {
		ZENG.msgbox.show("请先登录", 1, 5000);
		window.location.href=url+"/account/login.do?return_url="+encodeURI(window.location.href);
	} else if (buyCount < 1) {
		ZENG.msgbox.show("请填写购买数量", 1, 3000);
	} else {
//		Ajax.get(url+"order/addproductToBuy.do?pid=" + pid + "&buyCount=" + buyCount,
//				false, addProductToCartResponse)
		Ajax.get(url+"cart/addproduct.do?pid=" + pid + "&buyCount=" + buyCount,
				false, addProductToBuyResponse)
	}
}

//处理添加商品到购物车的反馈信息
function addProductToBuyResponse(data) {
	var pickway=$("[pick-way].active").attr("pick-way");
	var result = eval("("+data+")");
 	result = eval("("+result+")");
	if(result.status == "ok"){
		window.location.href = url+"/order/confirmorder.do?selectedCartItemKeyList="+result.pid+"&pickway="+pickway;
	}
}

//代理商的立即购买
function dealAddProductToBuy(pid, buyCountSel) {
	var buyCount=parseInt(buyCountSel, 10);
	if(isNaN(buyCount)==true){
		buyCount=$(buyCountSel).val();
	}
	
	if (pid < 1) {
		ZENG.msgbox.show("请先选择商品", 1, 3000);
	} else if (buyCount < 1) {
		ZENG.msgbox.show("请填写购买数量", 1, 3000);
	} else {
		window.location.href = url+"salerorder/confirmorder.do?pid="+pid+"&pickway=2&buycount="+buyCount;
	}
}


function nofind(){

	var img=event.srcElement;

	img.src=url+'themes/default/images/show/default.png';

	img.onerror=null; //控制不要一直跳动

	}
/**
 * 扩充日期类,使之具有格式化的功能
 */
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} 

	if(/(y+)/.test(format)) { 
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 

	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	} 
	} 
	return format; 
} 