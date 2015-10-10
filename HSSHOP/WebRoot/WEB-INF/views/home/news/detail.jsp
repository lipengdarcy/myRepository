<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${entity.title }-红狮水泥商城</title>
<meta name="keywords" content="${entity.title }-红狮水泥商城" />
<meta name="description" content="${entity.title }-红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/help.css" rel="stylesheet"
	type="text/css" />
</head>
<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>

	<input type="hidden" id="newstype_id_h" value="${entity.newstypeid }" />
	<div class="box">
		<div id="helpL">
			<ul id="news_type">
				<c:forEach var="t" items="${newstypesList }">
					<li
						class="<c:if test="${t.newstypeid eq entity.newstypeid }">on</c:if>"><a
						href="${ctx }/news/list.do?newstypeid=${t.newstypeid }">${t.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="helpR">
			<div class="box">
				<div id="news" class="box">
					<h1 class="newsTitle">${entity.title }</h1>
					<div class="newsInfo">时间：${addtime }</div>
					<div class="edit">
						<p>${entity.body }</p>
					</div>

				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>

	<%@ include file="../../../includes/home/footer.jsp"%>

</body>
</html>