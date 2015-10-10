<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/default/css/jquery-ui.css" />
<%@ include file="../../../../includes/homenew/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/user.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/validationEngine.jquery.css" />
<script type="text/javascript"
	src="${ctx }/themes/admin/xheditor/xheditor-1.2.1.min.js"></script>
<script type="text/javascript"
	src="${ctx }/themes/admin/xheditor/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript" src="${ctx }/scripts/region.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ucenter.user.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-ui.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript">
	$(function() {
		//初始化xhEditor编辑器插件  
		$('#content').xheditor({
			tools : 'full',
			skin : 'default',
			width : 824,
			height : 400,
			upImgUrl : "${ctx }/ucenter/uploadfile.do?method=xeupload",
			upImgExt : "jpg,jpeg,png,gif",
			html5Upload : false,
			onUpload : insertUpload
		});

		//图片上传回调函数  
		function insertUpload(arrMsg) {
			//xheditor返回的arrMsg是一个Object数组
			var msg = arrMsg[0];

			//(1)其中url插入到编辑器,这样xheditor才能正常显示图片
			$("#content").append(msg);
		}

		var mapsrc = "${ctx}/map/togetpointmap.do";
		$("#selcoor-float-panel")
				.hsFloatPanel(
						{
							showPanelSel : "#sel-point-btn",
							left : 0,
							top : 40,
							beforeShowFunc : function(args) {
								$("#coor-panel-mask").css("display", "block");
								var coorval = $("#work_time").val();
								var coorpar = "";
								if (coorval) {
									coorpar = "point=" + coorval;
								}
								$("#selcoor-float-panel").find("iframe")[0].src = mapsrc
										+ "?" + coorpar;
							},
							beforeHiddenFunc : function(args) {
								$("#coor-panel-mask").css("display", "none");
								return true;
							}
						});
		//单击遮罩关闭坐标选择器
		$("#coor-panel-mask").bind("click", function(e) {
			$("#selcoor-float-panel .panel-close-btn").trigger("click");
		});
		//检查后提交
		$("#factoryInfoForm").validationEngine({
			onValidationComplete : function(form, status) {
				if (status) {
					edit();
				}
			}
		});
		//初始化一些值
		$("#type").val($("#type_h").val());
		$("#placeid").val($("#placeid_h").val());
		//
		$("#type").bind("change", function(e) {
			var src = e.target;
			if ($(src).val() == '1') {
				$("#placeid-tr").css("display", "none");
			} else {
				$("#placeid-tr").css("display", "");
			}
		});

		$("#type").trigger("change");
		//
		$('.timepicker').timepicker({
			timeFormat : 'hh:mm'
		});
		$(".weekday").spinner({
			min : 1,
			max : 7,
			step : 1
		});
	});
	function setCoor(point) {
		$("#work_time").val(point);
	}
	function edit() {
		var $form = $("#factoryInfoForm");
		var regionsName = $("#area1").find(".pshow-name-ele").text();//获取区域名称
		$("#regionname").val(regionsName);//
		var regionsID = $("#area1").find("[name='lastName']").val();//获取区域id
		$("#regionid").val(regionsID);//
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
			url : 'factoryInfoEdit.do',// 跳转到 action    
			data : postdata,
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					hsArtDialog.content(data.content).showModal();
					window.location.reload();
				} else {
					hsArtDialog.content(data.content).showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}
		});
	}
</script>
<style>
.main-mask {
	position: fixed;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	display: none;
	z-index: 100;
}
</style>
</head>

<body>
<div id="now-page-id" navid=1 style="display:none;"></div>
	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="${ctx }/business/storeInfo.do">个人中心首页</a>
				</strong>&gt; <a href="${ctx }dealproduct/salerIndexProList.do">经销商门户</a> &gt; <span>基础资料</span>
			</div>
			<div class="user-menu left">
				<%-- <c:import url="${ctx }/ucenter.do?navId=0" /> --%>
				<%@ include file="../centermenu.jsp"%>
			</div>
			<div class="user-content right">
				<h1>基础资料</h1>
				<div class="sales-data">
					<form id="factoryInfoForm" name="factoryInfoForm" action="">
						<input type="hidden" name="id" id="id" value="${store.id }" />
						<fieldset class="clearfix">
							<legend>经销商名称：</legend>
							<input type="text" name="name" id="name" value="${store.name }"
								placeholder="请输入经销商名称" class="validate[required]"></input> <label class="red">*</label>
						</fieldset>
						<fieldset class="clearfix">
							<legend>联系人：</legend>
							<input type="text" name="contacts" id="contacts"
								value="${store.contacts }" placeholder="请输入联系人" class="validate[required]"></input> <label
								class="red">*</label>
						</fieldset>
						<fieldset class="clearfix">
							<legend>联系电话：</legend>
							<input type="text" name="tel" id="tel" value="${store.tel }"
								placeholder="请输入联系电话"></input>
						</fieldset>
						<fieldset class="clearfix">
							<legend>手机号码：</legend>
							<input type="text" name="mobile" id="mobile"
								value="${store.mobile }" placeholder="请输入手机号码" class="validate[required,custom[phone]]"></input><label
								class="red">*</label>
						</fieldset>
						<fieldset class="clearfix">
							<legend>所在区域：</legend>
							<div id="area1" class="cellCon">
								<div seled-name-show=".pshow-name-ele" cid="${cid }"
									min-layer="2" max-layer="5" input-sel="[hidden-inputs-div]"
									role="hs-area-sel" style="position: relative;">
									<div>
										<span class="pshow-name-ele fleft-label"></span> <a
											show-hs-area-sel="" class="btn btn-success">请选择</a>
									</div>
									<div class="area-float-panel float-panel">
										<div class="panel-close-btn">
											<span class="glyphicon glyphicon-remove"></span>
										</div>
										<div role="tabpanel" class="area-tabs">
											<ul class="nav nav-tabs" role="tablist"></ul>
											<div class="tab-content"></div>
										</div>
										<div hidden-inputs-div=""></div>
									</div>
								</div>
							</div>
							<!-- 区域选择end -->
							<input type="hidden" id="regionid" name="regionid" value=""></input>
							<input type="hidden" id="regionname" name="regionname" value=""></input>
						</fieldset>
						<fieldset class="clearfix">
							<legend>详细地址：</legend>
							<input type="text" name="address" id="address"
								value="${store.address }" placeholder="请输入详细地址" class="validate[required]" /> <label
								class="red">*</label>
						</fieldset>
						<c:if test="${!empty wtlist}">
							<c:forEach var="wtitem" items="${wtlist }" varStatus="status">
								<c:if test="${wtitem.wttype==1 }">
									<fieldset class="clearfix">
										<legend>工作时间：</legend>
										<span class="fleft-label">星期</span><input
											class="weekday data-put01"
											name="workTimeList${status.index }.wtweekbegin" type="text"
											value="${wtitem.wtweekbegin}" size="5" readonly="true" /> <span
											class="fleft-label">-</span> <span class="fleft-label">星期</span><input
											class="weekday data-put01"
											name="workTimeList${status.index }.wtweekend" type="text"
											value="${wtitem.wtweekend}" size="5" readonly="true" /> <span
											class="fleft-label">: </span> <input
											class="timepicker data-put01"
											name="workTimeList${status.index }.wtbegin" type="text"
											value="${wtitem.wtbegin}" size="5" readonly="true" /> <span
											class="fleft-label">-</span> <input
											class="timepicker data-put01"
											name="workTimeList${status.index }.wtend" type="text"
											value="${wtitem.wtend}" size="5" readonly="true" /> <input
											name="workTimeList${status.index }.wtid" type="hidden"
											value="${wtitem.wtid}" /> <input
											name="workTimeList${status.index }.wttype" type="hidden"
											value="${wtitem.wttype}" />
									</fieldset>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${empty wtlist }">
							<fieldset class="clearfix">
								<legend>工作时间：</legend>
								<span class="fleft-label">星期</span><input
									class="data-put01 weekday" name="workTimeList0.wtweekbegin"
									type="text" value="" size="5" readonly="true" /> <span
									class="fleft-label">-</span> <span class="fleft-label">星期</span><input
									class="data-put01 weekday" name="workTimeList0.wtweekend"
									type="text" value="" size="5" readonly="true" /> <span
									class="fleft-label">:</span> <input
									class="timepicker data-put01" name="workTimeList0.wtbegin"
									type="text" value="" size="5" readonly="true" /> <span
									class="fleft-label">-</span> <input
									class="timepicker data-put01" name="workTimeList0.wtend"
									type="text" value="" size="5" readonly="true" /> <input
									name="workTimeList0.wttype" type="hidden" value="1" />
							</fieldset>
						</c:if>

						<fieldset class="clearfix" style="position: relative;">
							<legend>GPS坐标：</legend>
							<input type="text" id="work_time" name="work_time"
								class="data-put03" value="${store.worktime }" /> <a
								id="sel-point-btn">地图选择</a>
							<div class="float-panel" id="selcoor-float-panel"
								style="height: 500px; width: 800px;">
								<div class="panel-close-btn">
									<span class="glyphicon glyphicon-remove"></span>
								</div>
								<div class="carinfor-content">
									<iframe
										style="border: 0; width: 800px; height: 460px; margin-top: 24px;"
										frameborder="no"
										src="${ctx}/map/togetpointmap.do?point=${store.worktime }">
									</iframe>
								</div>
							</div>
						</fieldset>

						<fieldset class="clearfix">
							<legend>订单默认有效期：</legend>
							<input id="orderexpire" name="orderexpire" class="weekday"
								type="text" size="5" readonly="true"
								value="${store.orderexpire }" />天
						</fieldset>

						<fieldset class="clearfix">
							<legend>经销商介绍：</legend>
							<textarea id="content" name="content">${store.content }</textarea>
						</fieldset>

						<fieldset class="clearfix btn">
							<input type="submit" value="提交" class="submit-btn" /> <input
								type="reset" value="重置" class="reset-btn" />
						</fieldset>
					</form>
					<div class="coords-map map02">
						<iframe style="border: 0; width: 480px; height: 425px;"
							frameborder="no"
							src="${ctx}/map/togetcenterbusiness.do?id=${store.id }">
						</iframe>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="main-mask" id="coor-panel-mask"></div>
	<%@ include file="../../../../includes/homenew/footer.jsp"%>
</body>
</html>