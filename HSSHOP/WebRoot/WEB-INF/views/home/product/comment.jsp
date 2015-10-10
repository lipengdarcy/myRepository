<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${productDetail.entity.name }-红狮水泥商城</title>
<meta name="keywords" content="${productDetail.entity.name }-红狮水泥商城" />
<meta name="description" content="${productDetail.entity.name }-红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/content.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<link href="${ctx }/scripts/magiczoom/magiczoom.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/magiczoom/magiczoom.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="${ctx }/scripts/jquery.toastmessage.js"></script>
<link href="${ctx }/themes/default/css/jquery.toastmessage.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
<div class="infoRCon" id="PJ">
<div class="PJinfo">
<h2>商品评价</h2>
<div class="rate"> 
<strong>${rate.good}<span>%</span></strong> <br>
  <span>好评度</span> 
</div>
  
<div class="percent">
  <dl>
    <dt>好评<span>(${rate.good}%)</span></dt>
    <dd>
      <div style="width: ${rate.good}%;"></div>
    </dd>
  </dl>
  <dl>
    <dt>中评<span>(${rate.middle}%)</span></dt>
    <dd class="d1">
      <div style="width: ${rate.middle}%;"> </div>
    </dd>
  </dl>
  <dl>
    <dt>差评<span>(${rate.bad}%)</span></dt>
    <dd class="d1">
      <div style="width: ${rate.bad}%;"> </div>
    </dd>
  </dl>
</div>

<div class="btns">
  <div>您可对已购商品进行评价</div>
  <a href="${ctx }/ucenter/orderlist.do" class="btn-comment" target="_blank">发表评论</a>
  <div></div>
</div>
<div class="clear"></div>
</div>
</div>


			<div class="infoRBox" style="border-bottom: 0;">
				<h2 class="infoRT infoBT2 height">
					<ul>
						<li id="show1" class="hot" onmouseover="selectCommentInfo(1, this)">全部评价</li>
						<li id="show2" onmouseover="selectCommentInfo(2, this)">好评</li>
						<li id="show3" onmouseover="selectCommentInfo(3, this)">中评</li>
						<li id="show4" onmouseover="selectCommentInfo(4, this)">差评</li>
						<div class="clear"></div>
					</ul>
				</h2>
				<div class="clear"></div>
			</div>
			
			<div class="infoRCon" id="commontInfo1">
<div id="productReviewList">

<c:choose>
	<c:when test="${not empty commentList}">
	<c:forEach items="${commentList}" var="comment" varStatus="vs">
<div class="itme" style="margin-bottom:10px">
  <div class="user">
      <img src="${ctx }/themes/default/images/${comment.avatar}"  onerror="nofind();" width="50" height="50"> 
      <div class="userName">${comment.username.substring(0,2)}**              </div>
 <!--     <div class="userLV">${comment.title}                                              </div>  -->
  </div>
<div class="user_i">
    <div class="itmeSJ"></div>
    <div class="user_i_t"><div class="star"><span class="star_yellow" style=" width:${19*comment.score}%;">&nbsp;</span></div><i>${forday.format(comment.creatDate)}&nbsp; ${fortime.format(comment.creatDate)}</i></div>
    <dl>
      <dt>心得：</dt>
      <dd>${comment.comment}</dd>
      <div class="clear"></div>
    </dl>
    <dl>
      <dt>购买时间：</dt>
      <dd>${forday.format(comment.addtime)}&nbsp; ${fortime.format(comment.addtime)}</dd>
      <div class="clear"></div>
    </dl>
</div>
</div>
	</c:forEach>
	</c:when>
	<c:otherwise>
	<div class="main_info">
		<p colspan="100" class="center">没有相关数据</p>
	</div>
	</c:otherwise>
</c:choose>
</div>
${pageDiv}
			</div>
			
			
			<div class="infoRCon hide" id="commontInfo2">

<div id="productReviewList">

<c:choose>
	<c:when test="${not empty commentList1}">
	<c:forEach items="${commentList1}" var="comment" varStatus="vs">
<div class="itme" style="margin-bottom:10px">
  <div class="user">
      <img src="${ctx }/themes/default/images/${comment.avatar}" onerror="nofind();" width="50" height="50"> 
      <div class="userName">${comment.username.substring(0,2)}**              </div>
 <!--     <div class="userLV">${comment.title}                                              </div>  -->
  </div>
<div class="user_i">
    <div class="itmeSJ"></div>
    <div class="user_i_t"><div class="star"><span class="star_yellow" style=" width:${19*comment.score}%;">&nbsp;</span></div><i>${forday.format(comment.creatDate)}&nbsp; ${fortime.format(comment.creatDate)}</i></div>
    <dl>
      <dt>心得：</dt>
      <dd>${comment.comment}</dd>
      <div class="clear"></div>
    </dl>
    <dl>
      <dt>购买时间：</dt>
      <dd>${forday.format(comment.addtime)}&nbsp; ${fortime.format(comment.addtime)}</dd>
      <div class="clear"></div>
    </dl>
</div>
</div>
	</c:forEach>
	</c:when>
	<c:otherwise>
	<div class="main_info">
		<p colspan="100" class="center">没有相关数据</p>
	</div>
	</c:otherwise>
</c:choose>
</div>
${pageDiv1}


</div>


			<div class="infoRCon hide" id="commontInfo3">

<div id="productReviewList">

<c:choose>
	<c:when test="${not empty commentList3}">
	<c:forEach items="${commentList3}" var="comment" varStatus="vs">
<div class="itme" style="margin-bottom:10px">
  <div class="user">
      <img src="${ctx }/themes/default/images/${comment.avatar}" onerror="nofind();" width="50" height="50"> 
      <div class="userName">${comment.username.substring(0,2)}**              </div>
 <!--     <div class="userLV">${comment.title}                                              </div>  -->
  </div>
<div class="user_i">
    <div class="itmeSJ"></div>
    <div class="user_i_t"><div class="star"><span class="star_yellow" style=" width:${19*comment.score}%;">&nbsp;</span></div><i>${forday.format(comment.creatDate)}&nbsp; ${fortime.format(comment.creatDate)}</i></div>
    <dl>
      <dt>心得：</dt>
      <dd>${comment.comment}</dd>
      <div class="clear"></div>
    </dl>
    <dl>
      <dt>购买时间：</dt>
      <dd>${forday.format(comment.addtime)}&nbsp; ${fortime.format(comment.addtime)}</dd>
      <div class="clear"></div>
    </dl>
</div>
</div>
	</c:forEach>
	</c:when>
	<c:otherwise>
	<div class="main_info">
		<p colspan="100" class="center">没有相关数据</p>
	</div>
	</c:otherwise>
</c:choose>
</div>
${pageDiv3}


</div>
			
			
			<div class="infoRCon hide" id="commontInfo4">
			
			<div id="productReviewList">

<c:choose>
	<c:when test="${not empty commentList2}">
	<c:forEach items="${commentList2}" var="comment" varStatus="vs">
<div class="itme" style="margin-bottom:10px">
  <div class="user">
      <img src="${ctx }/themes/default/images/${comment.avatar}" onerror="nofind();" width="50" height="50"> 
      <div class="userName">${comment.username.substring(0,2)}**              </div>
 <!--     <div class="userLV">${comment.title}                                              </div>  -->
  </div>
<div class="user_i">
    <div class="itmeSJ"></div>
    <div class="user_i_t"><div class="star"><span class="star_yellow" style=" width:${19*comment.score}%;">&nbsp;</span></div><i>${forday.format(comment.creatDate)}&nbsp; ${fortime.format(comment.creatDate)}</i></div>
    <dl>
      <dt>心得：</dt>
      <dd>${comment.comment}</dd>
      <div class="clear"></div>
    </dl>
    <dl>
      <dt>购买时间：</dt>
      <dd>${forday.format(comment.addtime)}&nbsp; ${fortime.format(comment.addtime)}</dd>
      <div class="clear"></div>
    </dl>
</div>
</div>
	</c:forEach>
	</c:when>
	<c:otherwise>
	<div class="main_info">
		<p colspan="100" class="center">没有相关数据</p>
	</div>
	</c:otherwise>
</c:choose>
</div>
${pageDiv2}
			
			</div>


		<script type="text/javascript">
			function selectCommentInfo(id, obj) {
				var liList = obj.parentNode.getElementsByTagName("li");
				for (var i = 0; i < liList.length; i++) {
					liList[i].className = "";
				}
				obj.className = "hot";
				//$("#productInfo1").style.display = "none";
				document.getElementById("commontInfo1").style.display = "none";
				document.getElementById("commontInfo2").style.display = "none";
				document.getElementById("commontInfo3").style.display = "none";
				document.getElementById("commontInfo4").style.display = "none";
				document.getElementById("commontInfo" + id).style.display = "block";
			}
		</script>
		<c:if test="${not empty type}">
		<script>
		$(
			setTimeout(function(){
			$(".hot").removeClass("hot"); 
			$("#show${type}").addClass("hot");
			document.getElementById("commontInfo1").style.display = "none";
			document.getElementById("commontInfo2").style.display = "none";
			document.getElementById("commontInfo3").style.display = "none";
			document.getElementById("commontInfo4").style.display = "none";
			document.getElementById("commontInfo${type}").style.display = "block";
			},100)
		)
		</script>
		</c:if>
</body>
</html>

