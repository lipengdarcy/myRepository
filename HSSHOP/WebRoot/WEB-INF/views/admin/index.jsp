<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../includes/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理后台管理系统</title>

<link href="${ctx }/themes/admin/dwz/themes/azure/style.css"
	rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx }/themes/admin/dwz/themes/css/core.css"
	rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx }/themes/admin/dwz/themes/css/print.css"
	rel="stylesheet" type="text/css" media="print" />
<link href="${ctx }/themes/admin/uploadify/css/uploadify.css"
	rel="stylesheet" type="text/css" media="screen" />
<!--[if IE]>
<link href="${ctx }/themes/admin/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${ctx }/themes/admin/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="${ctx }/themes/admin/dwz/js/jquery-1.7.2.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/jquery.cookie.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/jquery.validate.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/jquery.bgiframe.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/xheditor/xheditor-1.2.1.min.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/xheditor/xheditor_lang/zh-cn.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/uploadify/scripts/jquery.uploadify.js"
	type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript"
	src="${ctx }/themes/admin/chart/raphael.js"></script>
<script type="text/javascript"
	src="${ctx }/themes/admin/chart/g.raphael.js"></script>
<script type="text/javascript" src="${ctx }/themes/admin/chart/g.bar.js"></script>
<script type="text/javascript"
	src="${ctx }/themes/admin/chart/g.line.js"></script>
<script type="text/javascript" src="${ctx }/themes/admin/chart/g.pie.js"></script>
<script type="text/javascript" src="${ctx }/themes/admin/chart/g.dot.js"></script>

<script src="${ctx }/themes/admin/dwz/js/dwz.core.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.util.date.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.validate.method.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.barDrag.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.drag.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.tree.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.accordion.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.ui.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.theme.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.switchEnv.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.alertMsg.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.contextmenu.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.navTab.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.tab.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.resize.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.dialog.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.dialogDrag.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.sortDrag.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.cssTable.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.stable.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.taskBar.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.ajax.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.pagination.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.database.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.datepicker.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.effects.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.panel.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.checkbox.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.history.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.combox.js"
	type="text/javascript"></script>
<script src="${ctx }/themes/admin/dwz/js/dwz.print.js"
	type="text/javascript"></script>

<script src="${ctx }/themes/admin/hsjs/productJs.js"
	type="text/javascript"></script>

<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换是下面dwz.regional.zh.js还需要引入)
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx }/themes/admin/dwz/js/dwz.regional.zh.js"
	type="text/javascript"></script>

<link href="${ctx }/themes/default/css/commoncss.css" rel="stylesheet"
	type="text/css">
	<script src="${ctx }/scripts/common.js?v=1.0.1" type="text/javascript"></script>
	<script src="${ctx }/scripts/myui/hsAreaTabs.js?v=1.0.3"
		type="text/javascript"></script>
	<script src="${ctx }/scripts/myui/hsFloatPanel.js?v=1.0.3"
		type="text/javascript"></script>
	<script src="${ctx }/scripts/hsAreaUser.js?v=1.0.3"
		type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			DWZ.init("${ctx }/themes/admin/dwz/dwz.frag.xml", {
				loginUrl : "${ctx }/admin/login.do",
				//loginTitle : "登录", // 弹出登录对话框
				//		loginUrl:"login.html",	// 跳到登录页面
				statusCode : {
					ok : 200,
					error : 300,
					timeout : 301
				}, //【可选】
				pageInfo : {
					pageNum : "pageNum",
					numPerPage : "numPerPage",
					orderField : "orderField",
					orderDirection : "orderDirection"
				}, //【可选】
				keys : {
					statusCode : "statusCode",
					message : "message"
				}, //【可选】
				debug : false, // 调试模式 【true|false】
				callback : function() {
					initEnv();
					$("#themeList").theme({
						themeBase : "${ctx }/themes/admin/dwz/themes"
					}); // themeBase 相对于index页面的主题base路径
				}
			});

			var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
			function fileChange(target) {
				var fileSize = 0;
				var filetypes = [ ".jpg", ".png", "jpeg" ];
				var filepath = target.value;
				var filemaxsize = 1024;//1M 
				if (filepath) {
					var isnext = false;
					var fileend = filepath.substring(filepath.lastIndexOf("."));
					if (filetypes && filetypes.length > 0) {
						for (var i = 0; i < filetypes.length; i++) {
							if (filetypes[i] == fileend) {
								isnext = true;
								break;
							}
						}
					}
					if (!isnext) {
						alert("不接受此文件类型！");
						target.value = "";
						return false;
					}
				} else {
					return false;
				}
				if (isIE && !target.files) {
					var filePath = target.value;
					var fileSystem = new ActiveXObject(
							"Scripting.FileSystemObject");
					if (!fileSystem.FileExists(filePath)) {
						alert("文件不存在，请重新输入！");
						return false;
					}
					var file = fileSystem.GetFile(filePath);
					fileSize = file.Size;
				} else {
					fileSize = target.files[0].size;
				}

				var size = fileSize / 1024;
				if (size > filemaxsize) {
					alert("大小不能大于" + filemaxsize / 1024 + "M");
					target.value = "";
					return false;
				}
				if (size <= 0) {
					alert("ؽݾ大小不能为0M！");
					target.value = "";
					return false;
				}
			}

			//绑定上传按钮的单击事件
			$(document).on("click", "#upload-show-btn", function(e) {
				$("#product-img-file").val("");
				$("#product-img-file").trigger("click");
			});
			//监听文件上传控件的值的变化
			$(document).on("change", "#product-img-file", function(e) {
				var src = e.target;
				if (fileChange(src) != false) {
					var srcVal = $(src).val();
					if (srcVal) {
						$("#product-img-submit").trigger("click");
					}
				}

			});
			//监听文件上传控件的值的变化
			/*$(document).on("change", ".xheFile", function(e) {
				var src = e.target;
				fileChange(src)
				
			});*/
			//物品图片上传对话框的添加图片按钮的事件
			$(document).on("click", ".add-product-img-btn", function(e) {
				var src = e.target;
				var pform = $(src).closest("form");
				var url = pform.attr("action");
				var postData = $(pform).serialize();
				$.ajax({
					url : url,
					type : "post",
					data : postData,
					async : false,
					dataType : "json",
					success : function(rs, status) {
						if (rs.statusCode) {
							alertMsg.correct(rs.message)
							$.pdialog.close("add-product-img-item-dlg");
						} else {
							alertMsg.error(rs.message)
						}

					}
				})
			});

			//属性值对话框的保存按钮的事件
			$(document).on("click", "#save-attrval-btn", function(e) {
				var src = e.target;
				var pform = $(src).closest("form");
				var url = pform.attr("action");
				var postData = $(pform).serialize();
				$.ajax({
					url : url,
					type : "post",
					data : postData,
					async : false,
					dataType : "json",
					success : function(rs, status) {
						if (rs.statusCode) {
							alertMsg.correct(rs.message)
							$.pdialog.close("to-add-attrval-dlg");
						} else {
							alertMsg.error(rs.message)
						}

					}
				})
			});
		});
		//因为bootstrap需要更高版本的jquery 所以需要手写代码控制其tab插件的部分行为
		$(document).on("click", "[role=\"presentation\"]", function(e) {
			var src = e.target;
			var panel = $(src).closest("[role=\"tabpanel\"]");
			var tab = $(src).closest("[role=\"presentation\"]");
			$(panel).find("[role=\"presentation\"]").removeClass("active");
			tab.addClass("active");
			var tabPanelId = tab.find("[aria-controls]").attr("aria-controls");

			$(panel).find("[role=\"tabpanel\"]").removeClass("active");
			$("#" + tabPanelId).addClass("active");
		});
		
		var saveEditForm = function() {

			var sendData = $("#form-12121").serialize();

			$.ajax({
				url : url + '/business/appproveStoreUserApply.do',
				data : sendData,
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if (data.state == 'success') {
						alertMsg.info(data.content);
					} else {
						alertMsg.error(data.content);
					}
				},
				error : function() {
					alertMsg.error("异常！请重新尝试或者联系管理员！");
				}
			});

		};
	</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="###">标志</a>
				<ul class="nav">
					<li>欢迎（<a href="javascript:" style="display: inline-block;">${adminuser.username}</a>）
					</li>
					<li><a href="logout.do">退出登陆</a></li>
				</ul>
				<!-- 				<ul class="themeList" id="themeList">
					<li theme="default"><div>蓝色</div>
					</li>
					<li theme="green"><div>绿色</div>
					</li>
					<li theme="red"><div>红色</div></li>
					<li theme="purple"><div>紫色</div>
					</li>
					<li theme="silver"><div>银色</div>
					</li>
					<li theme="azure"><div class="selected">天蓝</div>
					</li>
				</ul> -->
			</div>

			<!-- navMenu -->

		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>收缩</div>
				</div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>所有功能
						</h2>
					</div>

					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>系统设置</a>
								<ul>
									<li><a href="webinfor.do?method=toUpdate" target="navTab"
										rel="page6">网站配置</a></li>
									<li><a href="webinfor.do?method=toUpdateUpload"
										target="navTab" rel="page13">上传配置</a></li>
									<li><a href="mailconfig.do?method=toUpdate"
										target="navTab" rel="page7">邮箱配置</a></li>
									<li><a href="adminunit.do" target="navTab" rel="page14">计量单位</a></li>
									<li><a href="message.do?method=toMessageList"
										target="navTab" rel="page14">短信查看</a></li>
									<li><a href="areamanager.do" target="navTab"
										rel="areamanager" external="true">行政区域管理</a></li>
								</ul></li>

							<!-- 管理员信息管理 -->
							<li><a>管理员信息管理</a>
								<ul>
									<li><a href="adminuser.do" target="navTab" rel="page5">管理员信息管理</a></li>
									<li><a href="${ctx }/permission/resourcelist.do" target="navTab" rel="resourcelist">权限列表</a></li>
									<li><a href="${ctx }/permission/rolelist.do" target="navTab" rel="rolelist">角色列表</a></li>
								</ul></li>
							<!-- 管理员信息管理 -->
							<!-- 文章管理 -->
							<li><a>产品管理</a>
								<ul>
									<li><a href="category.do" target="navTab" rel="page8">产品分类</a></li>
									<li style="display: none;"><a
										href="adminproduct.do?method=toAdd" target="navTab"
										rel="page9">添加商品</a></li>
									<li><a href="adminproduct.do?method=toAddSKU"
										target="navTab" rel="page10">添加SKU</a></li>
									<li><a href="adminproduct.do?method=skugroupList"
										target="navTab" rel="page14">SKU管理</a></li>
									<li><a href="adminproduct.do?method=onList"
										target="navTab" rel="page11">在售商品</a></li>
									<li><a href="adminproduct.do?method=downList"
										target="navTab" rel="page12">下架商品</a></li>
								</ul></li>
							<!-- 文章管理 -->
							<li><a>文章管理</a>
								<ul>
									<li><a href="${ctx }/admin/helps/helpslist.do"
										target="navTab" rel="page10">帮助管理</a></li>
									<li><a href="${ctx }/admin/helps/helpstypelist.do"
										target="navTab" rel="page13">帮助类型管理</a></li>
									<li><a href="${ctx }/admin/newsMng/news.do"
										target="navTab" rel="page11">新闻管理</a></li>
									<li><a href="${ctx }/admin/newsMng/newstype.do"
										target="navTab" rel="page12">新闻类型管理</a></li>
								</ul></li>
							<!-- 用户管理 -->
							<li><a>用户管理</a>
								<ul>
									<li style="display: none;"><a href="${ctx }/admin/user/resourcelist.do"
										target="navTab" rel="resourcelist">权限列表</a></li>

									<li style="display: none;"><a href="${ctx }/admin/user/rolelist.do"
										target="navTab" rel="rolelist">角色列表</a></li>

									<li><a href="${ctx }/admin/user/userlist.do"
										target="navTab" rel="userpage1">用户列表</a></li>
									<li><a href="${ctx }/permission/applylist.do"
										target="navTab" rel="applypage">申请列表</a></li>
									<%-- <li>
									<a href="${ctx }/admin/user/ranklist.do" target="navTab" rel="userpage2">用户等级</a>
								</li> --%>
								</ul></li>
							<!-- 区域管理 -->
							<li><a>区域管理</a>
								<ul>
									<li><a href="${ctx }/regionprice/shipList.do"
										target="navTab" rel="page41">区域运费</a></li>
									<li><a href="${ctx }/regionprice/handList.do"
										target="navTab" rel="page42">区域装卸费</a></li>
									<li><a href="${ctx }/regionprice/priceList.do"
										target="navTab" rel="page43">区域价格</a></li>
									<li style="display: none;"><a
										href="${ctx }/admin/area/saleaddress.do" target="navTab"
										rel="page44">区域工厂/门店</a></li>
									<li><a href="${ctx }/admin/area/factoryaddress.do"
										target="navTab" rel="page49">工厂管理</a></li>
									<li><a href="${ctx }/admin/area/factoryregions.do"
										target="navTab" rel="page50">工厂区域管理</a></li>
									<li><a href="${ctx }/admin/area/storeaddress.do"
										target="navTab" rel="page51">门店管理</a></li>
									<li><a href="${ctx }/admin/area/storeregions.do"
										target="navTab" rel="page52">门店区域管理</a></li>
									<li><a href="${ctx }/regionbrand/brandList.do"
										target="navTab" rel="page45">区域品牌</a></li>
									<li><a href="${ctx }/regionfactory/factoryList.do"
										target="navTab" rel="page46">品牌产地</a></li>

									<li style="display: none;"><a
										href="${ctx }/regionfactory/RegionfactoryList.do"
										target="navTab" rel="page47">区域产地</a></li>
									<li><a href="${ctx }/admin/adminproduct.do?method=storeAndNcProinforList"
										target="navTab" rel="page48">允销目录管理</a></li>
									<li><a href="${ctx }/admin/storemny.do"
										target="navTab" rel="page49">资金管理</a></li>
								</ul></li>
							<!-- 区域管理end -->


							<!-- 订单管理 -->
							<li><a>订单管理</a>
								<ul>
									<li><a href="${ctx }/admin/order/index.do?roleid=1" target="navTab"
										rel="page50">b2c订单列表</a></li>
										<li><a href="${ctx }/admin/order/index.do?roleid=2" target="navTab"
										rel="page51">b2b订单列表</a></li>
									<li><a href="${ctx }/admin/comment/commentlist.do"
										target="navTab" rel="page40">评价列表</a></li>
								</ul></li>
							<!-- 订单管理end -->

						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span
										class="home_icon">我的主页</span> </span> </a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<p>1</p>
							</div>
							<p>
								<span>框架</span>
							</p>
							<p>2</p>
						</div>
						<div class="pageFormContent" layoutH="80"
							style="margin-right: 230px">


							<h2>内容1:</h2>
							<div class="unit">内容1</div>

							<div class="divider"></div>
							<h2>内容2:</h2>
							<pre style="margin: 5px; line-height: 1.4em">内容2</pre>

						</div>

					</div>

				</div>
			</div>
		</div>

	</div>

	<%@ include file="../../includes/commons/footer.jsp"%>

</body>
</html>
