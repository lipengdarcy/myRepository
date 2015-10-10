<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../../includes/homenew/header.jsp"%>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/user.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/date/need/laydate.css" />
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine.js"></script>	
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>

<title>订单取消-红狮水泥商城</title>

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/uploadify/uploadify.css" />
<script type="text/javascript"
	src="${ctx }/scripts/uploadify/jquery.uploadify.min.js"></script>


</head>
<body>

	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>

	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>订单取消</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%-- <c:import url="${ctx }/ucenter.do?userType=${userType}&navId=3" /> --%>
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof content-->
			<div class="user-content right">
				<h1>订单取消</h1>
				<div class="user-order">
					<!--01-->
					<div class="order-list01">
						<ul>
							<li>
								<ul class="clearfix">
									<li>订单号：${orderinfo.osn}</li>
									<li>下单时间： ${formate.format(orderinfo.addtime)}</li>
									<li class="right">状态： <em class="green">${orderStateName}</em></li>
								</ul>
							</li>
						</ul>
					</div>
					<!--01-->
					<!--02-->
					<div class="order-list03">
						<div class="order-tab order-tab-grey">备注</div>
						<p>${orderinfo.remark}</p>
					</div>
					<!--02-->
					<!--03-->
					<div class="order-list04">
						<div class="order-tab order-tab-grey">商品清单</div>
						<div class="order-table">
							<div class="user-table-head">
								<table>
									<tbody>
										<tr>
											<th scope="col" class="product-info">订单信息</th>
											<th scope="col" class="table-wd01">数量</th>
											<th scope="col" class="table-wd01">金额</th>
											<th scope="col" class="table-wd01">合计</th>
										</tr>
									</tbody>
								</table>
							</div>
							<!--01-->
							<c:forEach items="${boplist}" var="bop" varStatus="vs">
								<div class="user-table-li">
									<table>
										<tbody>
											<tr class="table-sline">
												<td class="product-info"><div class="imghloder">
														<a href="product.html"><img
															src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
															alt="" /></a>
													</div>
													<div class="intro">
														<a href="${ctx }/upload/html/${bop.url}">${bop.name}</a>
													</div></td>
												<td class="table-wd01">${bop.sendcount}</td>
												<td class="table-wd01">￥${bop.marketprice}</td>
												<td class="table-wd01">￥${bop.marketprice.multiply(bop.sendcount)}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:forEach>
							<!--01-->
						</div>
					</div>
					<!--03-->
					<!--04-->
					<div class="order-list03">
						<div class="order-tab order-tab-grey">取消原因</div>
						<div class="order-cencel-box">
							<form id="form" method="post" action="" class="clearfix">

								<input type="hidden" name="orderid" value="${orderid}" /> <input
									type="hidden" name="oid" value="${orderid}" />

								<fieldset class="clearfix">
									<legend>取消原因：</legend>
									<select name="reasonid" class="validate[required]" >
										<option value>请选择</option>
										<option value="1">原因1</option>
										<option value="2">原因2</option>
										<option value="3">原因3</option>
									</select>
									<label class="red">*</label>
								</fieldset>
								<fieldset class="clearfix">
									<legend>取消说明：</legend>
									<textarea name="reason"></textarea>
								</fieldset>
								
								<fieldset class="clearfix upload-img">
									<legend>上传凭证：</legend>
									<div class="upload-box">
										<ul class=" clearfix">
											<li id="showimgs"></li>
										</ul>
										<div class="upload-btn">
											<input name="file1" type="hidden" id="imgUrl" /> <input
												id="file_upload" type="file" name="file_upload" />
										</div>
										<span class="clear">每张图片大小不超过5M，最多3张，支持GIF、JPG、PNG、BMP格式</span>
									</div>
								</fieldset>
								
								<fieldset class="clearfix cencel-btn">
									<input type="submit" class="submit-btn"
										value="提交" /> <input type="reset" class="reset-btn"
										value="重置" />
								</fieldset>
							</form>
						</div>
					</div>
					<!--04-->
				</div>
			</div>
			<!--eof content-->
		</div>
	</div>
	<!--eof main-->

	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../../includes/homenew/footer.jsp"%>

	<script type="text/javascript">
	var isSubmitted = false; //防止重复提交
	$(function() {
		//检查后提交
		$("#form").validationEngine({
			onValidationComplete : function(form, status) {
				if (status && !isSubmitted) {
					cancelOrder();
				}
			}
		});
	}); 
		//取消订单
		function cancelOrder() {	
			isSubmitted = true;
			var $form = $("#form");
			var postdata = $form.serialize();
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
				$.ajax({
						url : "${ctx }/business/OrderCancel.do",
						type : "post",
						data : postdata,
						//async : false,
						success : function(data, status) {
							if (data.success) {
								hsArtDialog.content(data.content).showModal();
								window.location.href = "${ctx }/business/OrderCancelSuccess.do?ordernum=${orderinfo.osn}";
							} else {
								hsArtDialog.content(data.content).showModal();
							}
						},
						error : function(xhr, errinfor, ex) {
							hsArtDialog.content(errinfor).showModal();
						}
					});
		}

		$(function() {
			var fileupload = $('#file_upload');
			fileupload
					.uploadify({
						'swf' : '${ctx }/scripts/uploadify/uploadify.swf',
						'uploader' : '${ctx }/ucenter/uploadfile.do?method=uploadimg',
						//按钮显示的文字
						'buttonText' : '上传图片',
						//显示的高度和宽度，默认 height 30；width 120
						'height': 106,
						'width': 106,
						//上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
						//在浏览窗口底部的文件类型下拉菜单中显示的文本
						'fileTypeDesc' : 'Image Files',
						//允许上传的文件后缀
						'fileTypeExts' : '*.gif; *.jpg; *.png',
						'multi' : false,
						'method' : 'post',
						'onUploadStart' : function(file) {
							var param = {};
							param.pathType = "8";//图片路径 order
							fileupload.uploadify("settings", "formData", param);
						},
						'onUploadSuccess' : function(file, data, response) {
							var obj = eval('(' + data + ')');
							if (obj.uploadState == "UPLOAD_SUCCSSS") {
								var outdata = obj.urlDateFileName;//.replace(/["""]/g, "");
								$("#imgUrl").val(outdata);// 返回的图片路径保存起来
								$("#showimgs")
										.html(
												"<a href=\"${ctx }/upload/order/source/"+outdata+"\" target=blank><img src=\"${ctx }/upload/order/source/"+outdata+"\" \/></a>");
							} else {
								alert("上传错误！");
							}
						},
						'onUploadError' : function(file, errorCode, errorMsg,
								errorString) {
							alert('The file ' + file.name
									+ ' could not be uploaded: ' + errorString);
						}
					// Your options here
					});
		});
	</script>
</body>
</html>