//增加商品数量
function addProuctCount() {
	var buyCountInput = document.getElementById("buyCount");
    
    var buyCount = buyCountInput.value;
    if (!isNumber(buyCount)) {
        ZENG.msgbox.show("请输入数字", 1, 3000);
        return false;
    }
    buyCountInput.value = parseFloat(buyCount) + 1;
    $(buyCountInput).trigger("change");
}

//减少商品数量
function cutProductCount() {
    var buyCountInput = document.getElementById("buyCount");

    var buyCount = buyCountInput.value;
    if (!isNumber(buyCount)) {
    	ZENG.msgbox.show("请输入数字", 1, 3000);
        return false;
    }
    var count = parseFloat(buyCount);
    if (count > 1) {
        buyCountInput.value = count - 1;
    }
    $(buyCountInput).trigger("change");
}

//咨询商品
function consultProduct(uid, pid) {
    var consultProductFrom = document.forms["consultProductFrom"];

    var consultTypeId = 0;
    var consultTypeIdObj = getSelectedRadio(consultProductFrom.elements["consultTypeId"]);
    if (consultTypeIdObj != undefined && consultTypeIdObj != null) {
        consultTypeId = consultTypeIdObj.value;
    }
    var consultMessage = consultProductFrom.elements["consultMessage"].value;
    var verifyCode = consultProductFrom.elements["verifyCode"] ? consultProductFrom.elements["verifyCode"].value : undefined;

    if (!verifyConsultProduct(uid, pid, consultTypeId, consultMessage, verifyCode)) {
        return;
    }
    Ajax.post("/catalog/consultproduct", { 'pid': pid, 'consultTypeId': consultTypeId, 'consultMessage': consultMessage, 'verifyCode': verifyCode }, false, consultProductResponse)
}

//验证咨询商品
function verifyConsultProduct(uid, pid, consultTypeId, consultMessage, verifyCode) {
    if (uid < 1) {
        ZENG.msgbox.show("请登录", 1, 5000);
        return false;
    }
    if (pid < 1) {
        ZENG.msgbox.show("请选择商品", 1, 3000);
        return false;
    }
    if (consultTypeId < 1) {
        ZENG.msgbox.show("请选择咨询类型", 1, 3000);
        return false;
    }
    if (consultMessage.lenth < 1) {
        ZENG.msgbox.show("请填写咨询内容", 1, 3000);
        return false;
    }
    if (consultMessage.length > 100) {
        ZENG.msgbox.show("咨询内容内容太长", 1, 3000);
        return false;
    }
    if (verifyCode != undefined && verifyCode.length == 0) {
        ZENG.msgbox.show("请输入验证码", 1, 3000);
        return false;
    }
    return true;
}

//处理咨询商品的反馈信息
function consultProductResponse(data) {
    var result = eval("(" + data + ")");
    if (result.state == "success") {
        ZENG.msgbox.show("您的咨询我们已经收到，我们会尽快给您回复", 4, 3000);
        window.location.href = result.content;
    }
    else {
        ZENG.msgbox.show(result.content, 5, 3000);
    }
}

//获得商品评价列表
function getProductReviewList(pid, reviewType, page) {
    Ajax.get("/catalog/ajaxproductreviewlist?pid=" + pid + "&reviewType=" + reviewType + "&page=" + page, false, getProductReviewListResponse)
}

//处理获得商品评价的反馈信息
function getProductReviewListResponse(data) {
    document.getElementById("productReviewList").innerHTML = data;
}

//获得商品咨询列表
function getProductConsultList(pid, consultTypeId, consultMessage, page) {
    Ajax.get("/catalog/ajaxproductconsultlist?pid=" + pid + "&consultTypeId=" + consultTypeId + "&consultMessage=" + encodeURIComponent(consultMessage) + "&page=" + page, false, getProductConsultListResponse)
}

//处理获得商品咨询的反馈信息
function getProductConsultListResponse(data) {
    document.getElementById("productConsultList").innerHTML = data;
}