<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的预存款-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/ucenter.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/region.js" type="text/javascript"></script>
<script src="${ctx }/scripts/ucenter.user.js" type="text/javascript"></script>
<link href="${ctx }/themes/default/css/home.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
</head>

<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>

	<div class="bigBox" id="member"
		style="background:#f5f5f5;padding:15px 0;">
		<div class="box">
			<c:import url="${ctx }/ucenter.do"/>
			<div id="memberR" style=" padding-bottom:0px;">
				<h2 id="memberRT">
					<strong>预存款<font color="#e4393c">${bu.paycredits}</font>元</strong>&nbsp;
					<div class="clear"></div>
				</h2>

				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tb-void">
					<thead>
						<tr>
							<th>日期</th>
							<th>收入/支出</th>
							<th>详细说明</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty bcllist}">
								<c:forEach items="${bcllist}" var="bcl" varStatus="vs">
									<tr>
										<td>${formate.format(bcl.actiontime)}</td>
										<td>${bcl.action}</td>
										<td>${bcl.actiondes}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="100" class="center">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
						
					</tbody>
				</table>
				<div class="page">
					${pageDiv }
				</div>
			</div>

			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>