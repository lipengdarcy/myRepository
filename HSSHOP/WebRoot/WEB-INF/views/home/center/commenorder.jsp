<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的订单-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<script type="text/javascript">
	uid = 1;
	isGuestSC = 1;
	scSubmitType = 0;
</script>
<link href="${ctx }/themes/default/css/ucenter.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/order.js" type="text/javascript"></script>
<script src="${ctx }/scripts/ucenter.order.js" type="text/javascript"></script>
<link href="${ctx }/themes/default/css/home.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/themes/default/css/star.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/scripts/star.js"></script>
</head>

<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>

	<div class="bigBox" id="member">
		<div class="box">
			<%@ include file="oldcentermenu.jsp"%>
			<div id="memberR" style=" padding-bottom:0px;">
								<dl style="border-bottom:none;">
					<dt>商品清单</dt>
					<dd style="padding-top:10px;">
						<table width="100%" border="0" cellspacing="0" class="dingdan">
							<thead>
								<tr>
									<th>商品</th>
									<th>评价状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty boplist}">
										<c:forEach items="${boplist}" var="bop" varStatus="vs">
											<tr>
												<td>
													<div class="proList">
														<img
															src="${ctx }/upload/product/thumb/60_60/${bop.showimg}" onerror="nofind();"
															width="50" height="50" /> <a href="${ctx }/upload/html/${bop.url}" target="_blank">${bop.name}<br />
															<br /> <br /> </a>
														<div class="clear"></div>
													</div>
												</td>
												<td id="reviewState${bop.recordid}">
												<c:if test="${bop.isreview=='0'}">未评价</c:if>
												<c:if test="${bop.isreview=='1'}">已评价</c:if>
												</td>
												<td>
												<c:if test="${bop.isreview=='0'}">
												<a id="reviewOperate${bop.recordid}" href="javascript:toreviewProduct(${bop.uid},${bop.pid},${bop.oid},${bop.recordid})">评价</a>
												</c:if>
												</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr class="main_info">
											<td colspan="100" class="center">没有商品信息</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</dd>
				</dl>
				
				
	 <div id="reviewProductBlock" style="display:none">
       <form name="reviewProductFrom" action="">
       <input type="hidden" id="uid" value="">
       <input type="hidden" id="pid" value="">
       <input type="hidden" id="oid" value="">
       <input type="hidden" id="recordid" value="">
       <table width="100%" border="0" cellspacing="0" cellpadding="0" style=" margin:15px 0;">
          <tbody><tr>
            <td width="80" height="35">评价等级:</td>
            <td>
<!--        <div class="left"><input type="radio" value="1" name="star"></div><div class="star"><span class="star_yellow" style=" width:20%;">&nbsp;</span></div>
            <div class="left"><input type="radio" value="2" name="star"></div><div class="star"><span class="star_yellow" style=" width:40%;">&nbsp;</span></div>
            <div class="left"><input type="radio" value="3" name="star"></div><div class="star"><span class="star_yellow" style=" width:60%;">&nbsp;</span></div>
            <div class="left"><input type="radio" value="4" name="star"></div><div class="star"><span class="star_yellow" style=" width:78%;">&nbsp;</span></div>
            <div class="left"><input type="radio" value="5" name="star"></div><div class="star"><span class="star_yellow" style=" width:100%;">&nbsp;</span></div>
-->       	<div class="clear"></div>
            
            <div class="goods-comm">

				<div class="goods-comm-stars">
				
					<div id="rate-comm-1" class="rate-comm">
					<ul class="rater-star" style="height: 24px; width: 120px; background-image: url(/HSSNSHOPADMIN/themes/default/images/stars.jpg);">
					<li class="rater-star-item-current rater-star-happy" style="height: 24px; width: 96px; z-index: 6; display: list-item; background-image: url(/HSSNSHOPADMIN/themes/default/images/stars.jpg);"></li>
					<li id="star1" value="" class="rater-star-item" style="height: 24px; width: 24px; z-index: 5; background-image: url(/HSSNSHOPADMIN/themes/default/images/stars.jpg);"><div class="popinfo" style="left: 0px;"><div class="info-box">1分&nbsp;很不满意<div>商品样式和质量都非常差，太令人失望了！</div></div></div></li>
					<li id="star2" value="" class="rater-star-item" style="height: 24px; width: 48px; z-index: 4; background-image: url(/HSSNSHOPADMIN/themes/default/images/stars.jpg);"><div class="popinfo" style="left: 24px;"><div class="info-box">2分&nbsp;不满意<div>商品样式和质量不好，不能满足要求。</div></div></div></li>
					<li id="star3" value="" class="rater-star-item" style="height: 24px; width: 72px; z-index: 3; background-image: url(/HSSNSHOPADMIN/themes/default/images/stars.jpg);"><div class="popinfo" style="left: 48px;"><div class="info-box">3分&nbsp;一般<div>商品样式和质量感觉一般。</div></div></div></li>
					<li id="star4" value="" class="rater-star-item" style="height: 24px; width: 96px; z-index: 2; background-image: url(/HSSNSHOPADMIN/themes/default/images/stars.jpg);"><div class="popinfo" style="left: 72px; display: none;"><div class="info-box">4分&nbsp;满意<div>商品样式和质量都比较满意，符合我的期望。</div></div></div></li>
					<li id="star5" value="" class="rater-star-item" style="height: 24px; width: 120px; z-index: 1; cursor: pointer; position: absolute; left: 0px; top: 0px; background-image: url(/HSSNSHOPADMIN/themes/default/images/stars.jpg);"><div class="popinfo" style="left: 96px; display: none;"><div class="info-box">5分&nbsp;非常满意<div>我很喜欢！商品样式和质量都很满意，太棒了！</div></div></div></li>
					</ul></div>
				<input type="hidden" id="score" value="">
				</div>

			</div>
            </td>
          </tr>
          <tr><td height="100">评论内容:</td><td><textarea style="height:100px; width:80%" name="message" id="message"></textarea></td></tr>
          <tr><td height="35">&nbsp;</td><td><input onclick="submitreview()" type="button" class="redBT" value="评价" style=" padding:4px 6px;"></td></tr>
        </tbody></table>
        </form>
     </div>
				
				
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="../../../includes/home/footer.jsp"%>
</body>

</html>