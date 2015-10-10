<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新闻列表-红狮水泥商城</title>
    <meta name="keywords" content="红狮水泥商城" />
    <meta name="description" content="红狮水泥商城" />
	<%@ include file="../../../includes/home/header.jsp"%>
    <link href="${ctx }/themes/default/css/help.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    	#news_type .on{background-color:#ddd !important; }
    </style>
</head>

<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>
	
	<input type="hidden" id="newstype_id_h" value="${newstypeId }" />
	<div class="box">
		<div id="helpL">
		<ul id="news_type">
			<c:forEach var="t" items="${newstypesList }" >
			<li class="<c:if test="${t.newstypeid eq newstypeId }">on</c:if>" ><a href="${ctx }/news/list.do?newstypeid=${t.newstypeid }">${t.name }</a></li>
			</c:forEach>
		</ul>
		</div>
		<div id="helpR">
			<div id="news" class="box">
				<h1 class="newsListT">新闻中心</h1>
				<ul class="newsList">
					<c:forEach var="n" items="${newsList }" >
					<li><a href="${ctx }/news/detail.do?id=${n.newsid }">${n.title }</a><span>${n.addtime }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	
	<%@ include file="../../../includes/home/footer.jsp"%>

</body>
</html>
