<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${isEmptySku!=null}">
		<dl style="display:none;">
			没有属性信息。
		</dl>
	</c:when>
	<c:otherwise>
	<ul>
<c:forEach var="props" items="${skuShowVOList}" varStatus="status">
	<li>
		<span>${props.attrName }:</span>
		<span>
		<c:if test="${props.isInput=='1' }">
			${props.inputVal }
		</c:if>
		<c:if test="${props.isInput!='1' }">
			${props.attrVal }
		</c:if>
		</span>
	</li>
</c:forEach>
</ul>
	</c:otherwise>
</c:choose>




</body>
</html>