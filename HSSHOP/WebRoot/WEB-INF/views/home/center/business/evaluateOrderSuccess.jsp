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

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/date/need/laydate.css" />
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>

<title>订单评价结果显示-红狮水泥商城</title>

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
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>订单评价</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->

			<!--bof content-->
			<div class="user-content right">
				<h1>订单评价</h1>
				<div class="user-order">
					<!--01-->
					<div class="order-list01">
						<ul>
							<li>
								<ul class="clearfix">
									<li>订单号：${orderinfo.osn}</li>
									<li>下单时间：<fmt:formatDate value="${orderinfo.addtime}"
											pattern="yyyy-MM-dd hh:mm:ss" />
									
									 </li>
									<li class="right">状态： <em class="green">${orderStateName}</em></li>
								</ul>
							</li>
						</ul>
					</div>
					<!--01-->
					<!--02-->
					<div class="order-list03">
						<div class="order-tab order-tab-grey">备注</div>
						<p>送货前电话联系，主人不在家,词语的一般解释表册上供填写附注的栏目；第二种意思指在备注栏内所加的注解说明</p>
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
											<th scope="col" class="product-info">商品信息</th>
											<th scope="col" class="table-wd04">价格</th>
											<th scope="col" class="table-wd04">数量</th>
											<th scope="col" class="table-wd04">合计</th>
											<th scope="col" class="table-wd04">运费</th>
											<th scope="col" class="table-wd04">装卸费</th>
											<th scope="col" class="table-wd04">总金额</th>
										</tr>
									</tbody>
								</table>
							</div>
							<!--01-->
							<div class="user-table-li">
								<table>
									<tbody>
										
										
										<c:forEach items="${boplist}" var="bop" varStatus="vs">
											<tr class="table-sline">
												<td class="product-info"><div class="imghloder">
														<a href="product.html"><img
															src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
															alt="" /></a>
													</div>
													<div class="intro">
														<a href="${ctx }/upload/html/${bop.url}" target="_blank">${bop.name}</a>
													</div></td>
												<td class="table-wd04">￥${bop.marketprice}</td>
											<td class="table-wd04">${bop.buycount}吨</td>
											<td class="table-wd04">￥${bop.marketprice.multiply(bop.buycount)}</td>
											<td class="table-wd04">￥${orderinfo.orderamount}</td>
											<td class="table-wd04">￥${orderinfo.orderamount}</td>
											<td class="table-wd04">￥${orderinfo.orderamount}</td>
											
												<td class="table-wd01">${bop.buycount}</td>
												<td class="table-wd01">￥${bop.marketprice}</td>
												<td class="table-wd01">￥${bop.marketprice.multiply(bop.buycount)}</td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
							<!--01-->
						</div>
					</div>
					<!--03-->
					<!--04-->
					<div class="order-list03">
						<div class="order-tab order-tab-grey">评价信息</div>
						<div class="evaluation-detial clearfix">
							<div class="evaluation-detial-user">
								<em>客户评价</em>
								<div class="star">
									<span class="star-yellow" style="width: 20%;"></span>
								</div>
								<div class="evaluation-info clear">东西不错，强度很高！东西不错，强度很高！东西不错，强度很高！东西不错，强度
									很高！</div>
							</div>
							<div class="evaluation-detial-user evaluation-detial-seller">
								<em>您的评价</em>
								<div class="star">
									<span class="star-yellow"></span>
								</div>
								<div class="evaluation-info clear">东西不错，强度很高！东西不错，强度很高！东西不错，强度很高！东西不错，强度
									很高！</div>
							</div>
						</div>
					</div>
					<!--04-->
					<!--04-->
					<div class="order-list03">
						<div class="order-tab order-tab-grey">解释评价</div>
						<div class="evaluation-explain clear">
							<form>
								<fieldset class="clearfix">
									<legend>您的解释：</legend>
									<textarea></textarea>
								</fieldset>
								<fieldset class="clearfix evaluation-btn">
									<input type="submit" class="submit-btn" value="提交" /> <input
										type="reset" class="reset-btn" value="重置" />
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


	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../../includes/homenew/footer.jsp"%>

	<script type="text/javascript">
		//订单发货
		function sendOrder() {
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			var $form = $("#form");
			var postdata = $form.serialize();
			$
					.ajax({
						url : "${ctx }/business/OrderSend.do",
						type : "post",
						data : postdata,
						//async : false,
						success : function(data, status) {
							if (data.status == "success")
								window.location.href = "${ctx }/business/queryOrder.do?storeType=1&roleid=1";
							else
							hsArtDialog.content(data.content).showModal();

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
						//'height': 15,
						//'width': 80,
						//上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
						//在浏览窗口底部的文件类型下拉菜单中显示的文本
						'fileTypeDesc' : 'Image Files',
						//允许上传的文件后缀
						'fileTypeExts' : '*.gif; *.jpg; *.png',
						'multi' : false,
						'method' : 'post',
						'debug' : true,
						'onUploadStart' : function(file) {
							var param = {};
							param.pathType = "8";//图片路径 order
							fileupload.uploadify("settings", "formData", param);
						},
						'onUploadSuccess' : function(file, data, response) {
							var outdata = data.replace(/["""]/g, "");
							$("#imgUrl").val(outdata);// 返回的图片路径保存起来
							$("#showimgs")
									.html(
											"<a href=\"${ctx }/upload/order/source/"+outdata+"\" target=blank><img src=\"${ctx }/upload/order/source/"+outdata+"\" \/></a>");
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