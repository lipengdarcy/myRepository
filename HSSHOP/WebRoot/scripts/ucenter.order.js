
//取消订单
function cancelOrder(oid, cancelReason) {
    Ajax.post("cancelorder.do", { oid: oid, cancelReason: cancelReason }, false, cancelOrderResponse);
}

//处理取消订单的反馈信息
function cancelOrderResponse(data) {
    var result = eval("(" + data + ")");
    if (result.state == "success") {
        document.getElementById("orderState" + result.content).innerHTML = "取消";
        removeNode(document.getElementById("payOrderBut" + result.content));
        removeNode(document.getElementById("cancelOrderBut" + result.content));
        ZENG.msgbox.show("取消成功", 4, 3000);
    }
    else {
        ZENG.msgbox.show(result.content, 5, 3000);
    }
}

//打开评价商品层
function openReviewProductBlock(recordId) {
    var reviewProductFrom = document.forms["reviewProductFrom"];
    reviewProductFrom.elements["recordId"].value = recordId;
    document.getElementById("reviewProductBlock").style.display = "";
}

//评价商品
function reviewProduct() {
    var reviewProductFrom = document.forms["reviewProductFrom"];

    var oid = reviewProductFrom.elements["oid"].value;
    var recordId = reviewProductFrom.elements["recordId"].value;
    var star = getSelectedRadio(reviewProductFrom.elements["star"]).value;
    var message = reviewProductFrom.elements["message"].value;

    if (!verifyReviewProduct(recordId, star, message)) {
        return;
    }
    Ajax.post("/ucenter/reviewproduct?oid=" + oid + "&recordId=" + recordId, { 'star': star, 'message': message }, false, reviewProductResponse);
}

//验证评价商品
function verifyReviewProduct(recordId, star, message) {
    if (recordId < 1) {
        ZENG.msgbox.show("请选择商品", 1, 3000);
        return false;
    }
    if (typeof(star) == "undefined"||star < 1 || star > 5) {
        ZENG.msgbox.show("请选择正确的星星", 1, 3000);
        return false;
    }
    if (message.length == 0) {
        ZENG.msgbox.show("请输入评价内容", 1, 3000);
        return false;
    }
    if (message.length > 100) {
        ZENG.msgbox.show("评价内容最多输入100个字", 1, 3000);
        return false;
    }
    return true;
}

//处理评价商品的反馈信息
function reviewProductResponse(data) {
    var result = eval("(" + data + ")");
    if (result.state == "success") {
        var reviewProductFrom = document.forms["reviewProductFrom"];
        reviewProductFrom.elements["recordId"].value = 0;
        reviewProductFrom.elements["message"].value = "";

        document.getElementById("reviewProductBlock").style.display = "none";

        document.getElementById("reviewState" + result.content).innerHTML = "已评价";
        document.getElementById("reviewOperate" + result.content).innerHTML = "";

        ZENG.msgbox.show("评价成功", 4, 3000);
    }
    else {
    	 ZENG.msgbox.show(result.content, 5, 3000);
    }
}

//点击区评论
function toreviewProduct(uid,pid,oid,recordid) {
    $("#reviewProductBlock").show();
    $("#reviewProductBlock #uid").val(uid);
    $("#reviewProductBlock #pid").val(pid);
    $("#reviewProductBlock #oid").val(oid);
    $("#reviewProductBlock #recordid").val(recordid);
}

//提交评论
function submitreview() {
    var uid=$("#reviewProductBlock #uid").val();
    var pid=$("#reviewProductBlock #pid").val();
    var oid=$("#reviewProductBlock #oid").val();
    var recordid=$("#reviewProductBlock #recordid").val();
    var content=$("#reviewProductBlock #message").val();
    var score=$("#reviewProductBlock #score").val();
    if (!verifyReviewProduct(recordid, score, content)) {
        return;
    }
    Ajax.post("reviewProduct.do", { uid: uid, pid: pid,recordid:recordid ,content:content,score:score,oid:oid}, false, function(data){
    	if(data=0){
    		window.location.assign("/home/login/login");
    	}else{
    		document.getElementById("reviewState" + recordid).innerHTML = "已评价";
            document.getElementById("reviewOperate" + recordid).innerHTML = "";
            $("#reviewProductBlock input:radio").filter(':checked').attr("checked",false);
            $("#reviewProductBlock #message").val("");
    		$("#reviewProductBlock").hide();
    		 ZENG.msgbox.show("评价成功", 4, 3000);
//    	location.reload();
    	}
    });
    }

