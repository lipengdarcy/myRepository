<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
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
<title>根据关键字本地搜索</title>
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
	var windowsInfos = new Array();
	var markers = new Array();

	//门店样式
	var markIcoRed = new BMap.Icon(
			"${ctx }/themes/default/images/coordinate.gif", new BMap.Size(24,
					31));// 红色
	markIcoRed.setAnchor(new BMap.Size(14, 32));
	var markIcoBlue = new BMap.Icon(
			"${ctx }/themes/default/images/coordinate.gif", new BMap.Size(24,
					31)); // 蓝色
	markIcoBlue.setAnchor(new BMap.Size(14, 32));
	markIcoBlue.setImageOffset(new BMap.Size(0, -31));

	var markLableStyle = {
		display : "block",
		background : "transparent",
		fontSize : "14px/24px",
		width : "24px",
		height : "31px",
		padding : "0",
		border : "none",
		color : "#fff",
		textAlign : "center",
		lineHeight : "24px",
		fontFamily : "Microsoft YaHei"
	};

	//回调函数
	var searchComplete = function(results) {
		// 创建当前位置的标注图标
		var myIcon = new BMap.Icon("${ctx }/themes/default/images/floorB.gif", //图片地址
		new BMap.Size(40, 64), // 标注显示大小
		{
			offset : new BMap.Size(20, 64), // 标注底部小尖尖的偏移量
			imageOffset : new BMap.Size(0, 0)
		// 这里相当于CSS sprites
		});
		//获取位置
		var point = results.getPoi(0).point;
		var marker1 = new BMap.Marker(point, {
			icon : myIcon
		});
		marker1.setTitle("当前位置");
		map.addOverlay(marker1);
		//创建检索信息窗口对象
		var searchInfoWindow1 = null;
		var content1 = '';
		content1 += '<div class="mappop">';
		content1 += '<div class="info">';
		content1 += '	<h4>当前位置 </h4>';
		content1 += '    <p>地址：${address}</p>';
		content1 += '</div>';
		content1 += '<div class="c"></div>';
		content1 += '</div>';

		searchInfoWindow1 = new BMapLib.SearchInfoWindow(map, content1, {
			title : '${address}', //标题
			width : 340, //宽度
			panel : "panel", //检索结果面板
			enableAutoPan : true, //自动平移
			searchTypes : [ BMAPLIB_TAB_TO_HERE, //到这里去
			BMAPLIB_TAB_FROM_HERE //从这里出发
			]
		});

		//绑定点击点击事件
		marker1.addEventListener("click", function() {
			searchInfoWindow1.open(marker1.point);
		});

		map.panTo(point);
		//添加覆盖物
		var circle = new BMap.Circle(point, 5000, {
			fillColor : "blue",
			strokeWeight : 1,
			fillOpacity : 0.3,
			strokeOpacity : 0.3
		}); //创建圆
		map.addOverlay(circle);

		//根据搜索坐标，获取坐标附近门店信息并显示
		$.ajax({
			url : "showDoor.do",
			type : "post",
			data : {
				left : point.lng,
				right : point.lat
			},
			dataType : "json",
			success : function(rs) {

				$.each(rs, function(i, n) {
					//门店节点
					var marker = new BMap.Marker(
							new BMap.Point(n.left, n.right), {
								icon : markIcoRed
							});
					marker.setTitle(n.name);

					var lable = new BMap.Label(i + 1);
					lable.setStyle(markLableStyle);
					marker.setLabel(lable);

					map.addOverlay(marker);
					markers[i] = marker;

					//绑定悬浮事件
					marker.addEventListener("mouseover", function() {
						marker.setIcon(markIcoBlue);
					});
					marker.addEventListener("mouseout", function() {
						marker.setIcon(markIcoRed);
					});

					//创建检索信息窗口对象
					var searchInfoWindow = null;
					var content = '';
					content += '<div class="mappop">';
					content += '<div class="info">';
					content += '	<h4>' + n.name + '</h4>';
					content += '    <p>地址：' + n.address + '</p>';
					content += '    <p>联系人：' + n.contacts + '</p>';
					content += '    <p>电话：' + n.tel + '</p>';
					content += '</div>';
					content += '<div class="c"></div>';
					content += '</div>';

					searchInfoWindow = new BMapLib.SearchInfoWindow(map,
							content, {
								title : n.name, //标题
								width : 340, //宽度
								panel : "panel", //检索结果面板
								enableAutoPan : true, //自动平移
								searchTypes : [ BMAPLIB_TAB_TO_HERE, //到这里去
								BMAPLIB_TAB_FROM_HERE //从这里出发
								]
							});

					//绑定点击点击事件
					marker.addEventListener("click", function() {
						searchInfoWindow.open(marker.point);
					});

				})

			}
		})

	}

	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(119.4333, 29.1825), 13);
	var local = new BMap.LocalSearch(map, {
		onSearchComplete : searchComplete
	});
	local.search("${address}");
</script>