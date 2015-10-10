<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script src="${ctx }/scripts/jquery-1.11.3.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=qE7WeL0nXXn15dkDZiboaGkw"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<title>坐标选择器</title>
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#allmap {
	width: 100%;
	height: 100%;
}

.mappop h4 {
	font-size: 14px;
	line-height: 24px;
	margin-bottom: 10px;
}

.mappop .info h4 {
	color: #2083cf;
	font-weight: bold;
}

.mappop p {
	margin-bottom: 10px;
}

.mappop .info {
	padding-right: 10px;
	padding: 10px;
}
</style>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	var map = new BMap.Map("allmap");
	var mapx = "${mapx}";
	var mapy = "${mapy}";
	var point = new BMap.Point(mapx, mapy);
	var top_right_navigation = new BMap.NavigationControl({
		anchor : BMAP_ANCHOR_TOP_RIGHT,
		type : BMAP_NAVIGATION_CONTROL_SMALL
	}); //右上角，仅包含平移和缩放按钮
	map.addControl(top_right_navigation);
	var marker = new BMap.Marker(point);

	map.centerAndZoom(point, 15);
	map.enableScrollWheelZoom();
	var content = '<div style="margin:0;line-height:20px;padding:2px;">'
			+ '联系人：${store.contacts}<br/>' + '联系电话：${store.tel }<br/>'
			+ '手机号码：${store.mobile }<br/>' + '详细地址：${store.address }<br/>'
			+ '</div>';

	//创建检索信息窗口对象
	var searchInfoWindow = null;
	searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
		title : "${store.name}", //标题
		width : 200, //宽度
		height : 105, //高度
		panel : "panel", //检索结果面板
		enableAutoPan : true, //自动平移
		searchTypes : []
	});

	//marker.enableDragging(); //marker可拖拽
	marker.addEventListener("click", function(e) {
		searchInfoWindow.open(marker);
	});
	searchInfoWindow.open(marker);
	map.addOverlay(marker); //在地图中添加marker
</script>