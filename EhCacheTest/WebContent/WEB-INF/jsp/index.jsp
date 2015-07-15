<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>jqGrid Demo</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="../css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/ui.jqgrid.css" />
<script src="../js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="../js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="../js/jquery.jqGrid.min.js" type="text/javascript"></script>

<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery("#list2").jqGrid({
			url : 'jqgrid.do',
			datatype : "json",
			colNames : [ 'psn', 'pid', '产品名称', '图片' ],
			colModel : [ {

				name : 'psn',
				index : 'psn',
				width : 80,
				align : "right"
			}, {

				name : 'pid',
				index : 'pid',
				width : 80,
				align : "right"
			}, {

				name : 'name',
				index : 'name',
				width : 150,
				align : "right"
			}, {
				name : 'showimg',
				index : 'showimg',
				width : 200,
				sortable : false
			} ],
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : '#pager2',
			sortname : 'id',
			viewrecords : true,
			sortorder : "desc",
			caption : "产品详情"
		});
		jQuery("#list2").jqGrid('navGrid', '#pager2', {
			edit : false,
			add : false,
			del : false
		});
	});
</script>
</head>
<body>

	<table id="list2"></table>
	<div id="pager2"></div>

</body>
</html>