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

<title>订单发货-红狮水泥商城</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/default/css/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/uploadify/uploadify.css" />
<script type="text/javascript"
	src="${ctx }/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.validationEngine.js"></script>
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
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>订单发货</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->

			<!--bof content-->
			<div class="user-content right">
				<h1>订单发货</h1>
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
						<div class="order-tab order-tab-grey">配送方式</div>
						<ul>
							<li><em>配送方式：</em>${orderinfo.shipfriendname}</li>
							<li><em>自提时间：</em> <c:if
									test="${orderinfo.shipsystemname == '1' or orderinfo.shipsystemname == '2'}">
									<fmt:formatDate value="${orderinfo.besttime}"
										pattern="yyyy年MM月dd日" />
									<c:if test="${!empty orderinfo.begindate}">
								   从${orderinfo.begindate}到${orderinfo.enddate}之间
							</c:if>
								</c:if></li>

							<c:forEach var="extitem" items="${orderext }">
								<c:if test="${extitem.oexttype==1 }">
									<li><em>车牌号：</em>${extitem.oextvalue }</li>
								</c:if>
								<c:if test="${extitem.oexttype==2 }">
									<li><em>证件号：</em> <c:if
											test="${extitem.oextlabelvalue==1 }">
											<span>身份证 </span>
										</c:if> <c:if test="${extitem.oextlabelvalue==2 }">
											<span>驾驶证 </span>
										</c:if> <span>${extitem.oextvalue }</span></li>
								</c:if>
								<c:if test="${extitem.oexttype==3 }">
									<li><em>发动机号：</em>${extitem.oextvalue }</li>
								</c:if>
							</c:forEach>
						</ul>
						<div class="order-tab order-tab-grey">备注</div>
						<p>${orderinfo.buyerremark}</p>
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
											<th scope="col" class="table-wd04">数量</th>
										</tr>
									</tbody>
								</table>
							</div>

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
												<td class="table-wd01">${bop.buycount}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:forEach>
						</div>
					</div>
					<!--03-->

					<!--04 发货清单 &发货form-->
					<div class="order-list04">
						<div class="order-tab order-tab-grey">发货清单</div>

						<c:if test="${ not (param.storeType==2 &&  param.roleid == 2)}">
							<div class="order-delivery-box">
						</c:if>
						<c:if test="${param.storeType==2 &&  param.roleid == 2}">
							<div class="order-delivery-box factory-delivery-box">
						</c:if>

						<!-- step 3 ：发货form-->
						<form class="clearfix" id="form" method="post" action="">

							<input type="hidden" name="oid" value="${orderinfo.oid }" /> <input
								type="hidden" name="ordernum" value="${orderinfo.osn }" />
							<fieldset>
								<div class="order-table">
									<div class="user-table-head">
										<table>
											<tbody>
												<tr>
													<th scope="col" class="product-info product-info-long">商品信息</th>
													<th scope="col" class="table-wd03">数量</th>
													<th scope="col" class="table-wd03">水泥编号</th>
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

														<td class="table-wd03"><div class="num-put">
																<em class="del" onclick="cutProductCount()">-</em> <input name="recordidList"
																	type="hidden" value="${bop.recordid}" /><input
																	name="sendCountList" id="buyCount" type="text"
																	value="${bop.sendcount}" /> <em class="add" onclick="addProuctCount()">+</em><span>吨</span><label
																	class="red">*</label>
															</div></td>

														<td class="table-wd03"><input type="text"
															name="sendbatchList" value=""
															class="order-list-input01 validate[required]" /><label
															class="red">*</label></td>

													</tr>
												</tbody>
											</table>
										</div>
									</c:forEach>
									<!--01-->


								</div>
							</fieldset>

							<div class="order-cencel-box order-delivery-box">

								<!-- 订单确认：工厂B2C，经销商卖出订单 -->
								<c:if test="${ not (param.storeType==2 &&  param.roleid == 2)}">
									<fieldset class="clearfix">
										<legend>提货人：</legend>
										<input name="sendername" type="text" />
									</fieldset>

									<fieldset class="clearfix">
										<legend>身份证号：</legend>
										<input name="senderidno" type="text" />
									</fieldset>

									<fieldset class="clearfix">
										<legend>车牌号：</legend>
										<input name="carno" type="text" class="validate[required]" /><label
											class="red">*</label>

									</fieldset>
								</c:if>
								<!-- 订单发送：工厂B2B-->
								<c:if test="${param.storeType==2 &&  param.roleid == 2}">
									<fieldset class="clearfix">
										<legend>发动机号：</legend>

										<input name="engineno" type="text" class="validate[required]" /><label
											class="red">*</label>

									</fieldset>
									<fieldset class="clearfix">
										<legend>最大载重量：</legend>
										<input name="maxload" type="text" />
									</fieldset>
								</c:if>

								<fieldset class="clearfix txtarea">
									<legend>发货说明：</legend>
									<textarea name="description"></textarea>
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
									<input type="submit" class="submit-btn" value="提交" /> <input
										type="reset" class="reset-btn" value="重置" />
								</fieldset>

							</div>
						</form>
					</div>

				</div>
				<!--04-->
			</div>
		</div>
	</div>


	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../../includes/homenew/footer.jsp"%>

	<script type="text/javascript">
		$(function() {
			//检查后提交
			$("#form").validationEngine({
				onValidationComplete : function(form, status) {
					var flag = true;
					var sendcountArray = $("input[name='sendCountList']");
					//弹窗提示配置
					var hsArtDialog=dialog({
					  	title: '提示',
					  	id:"hs-dialog",
					  	fixed:true,
					  	width:300,
					  	height:50
					});
			            for(var i=0;i<sendcountArray.length;i++){
			                 var val1= sendcountArray[i].value;
			                 if(val1==0){
			                	 flag = false;
			                	 hsArtDialog.content("发货数量不能不0，请输入后再提交！").showModal();
			                	 break;
			                 }
			            }
										
					if (status && flag ) {
						sendOrder();
					}
				}
			});
		});
		//订单发货
		function sendOrder() {
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
			$
					.ajax({
						url : "${ctx }/business/OrderSend.do",
						type : "post",
						data : postdata,
						async : true,//false无效果  
						beforeSend : function() {
							var height = document.body.scrollWidth + "px";//滚动位置可能大于屏幕高度  
							$('#bg')
									.css("display", "block")
									.css("height", height)
									.css("text-align", "center")
									.html(
											'<img style="padding-top:300px;"    src="/myTrac/chrome/projectmanage/images/wait.gif" />');
						},
						complete : function(data) {
							$('#bg').css("display", "none");
						},//#bg是遮蔽层div  

						success : function(data, status) {
							if (data.state == "success") {
								hsArtDialog.content(data.content).showModal();
								window.location.href = "${ctx }/business/OrderStateQuery.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${param.ordernum}";
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
						'height' : 106,
						'width' : 106,
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