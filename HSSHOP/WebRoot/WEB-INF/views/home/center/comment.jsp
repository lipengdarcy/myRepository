<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/homenew/header.jsp"%>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/user.css" />

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/date/need/laydate.css" />
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>

<title>评论列表-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>

	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong> &gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%@ include file="centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof content-->
			<div class="user-content right">
				<h1>买入商品</h1>
				<ul class="user-content-tab clearfix">
					<li class="active"><a>我的评价</a></li>
					<li ><a>来自他人的评价</a></li>
				</ul>
				<!--bof product-list-->
				<div class="sales-list evaluation-list">
					<div class="user-table-head">
						<table>
							<tbody>
								<tr>
									<th scope="col" class="product-info">订单信息</th>
									<th scope="col" class="table-wd03">评价内容</th>
									<th scope="col" class="table-wd01">被评价人</th>
									<th scope="col" class="table-wd02">评价等级</th>
									<th scope="col" class="table-wd01">操作</th>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="user-table-ul">
						<c:forEach items="${commentlist}" var="comment" varStatus="vs">
							<!--01-->
							<div class="user-table-li">
								<table>
									<tbody>
										<tr>
											<td colspan="6" class="product-num">评价时间：${forday.format(comment.creatdate)}</td>
										</tr>
										<tr class="table-sline">
											<td class="product-info"><div class="imghloder">
													<a href="${ctx }/product/detail.do?id=${comment.pid}"><img
														src="${ctx }/upload/product/thumb/60_60/${comment.showimg}"
														alt="" /></a>
												</div>
												<div class="intro">
													<a href="${ctx }/product/detail.do?id=${comment.pid}">${comment.name}</a>
												</div></td>
											<td class="table-wd03">${comment.comment}</td>
											<td class="table-wd01">${comment.companyname}</td>
											<td class="table-wd02"><div class="star">
													<span class="star_yellow"
														style=" width:${19*comment.score}%;">&nbsp;</span>
												</div></td>
											<td class="table-wd01 order-operate"><a
												href="${ctx }/product/detail.do?id=${comment.pid}">查看详情</a><a>修改</a></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--01-->

						</c:forEach>
						${pageDiv}
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../includes/homenew/footer.jsp"%>
</body>
</html>