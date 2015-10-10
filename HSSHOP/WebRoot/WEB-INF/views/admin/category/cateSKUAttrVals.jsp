<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.pageFormContent .sku-attr-vals label{
		width:auto;
	}
</style>
</head>
<body>
	<div class="sku-attr-vals">
		<c:forEach var="attrvo" items="${attrValList}">
			<dl>
				<dt>${attrvo.attributes.name }:</dt>
				<c:forEach var="listItem" items="${attrvo.attrValList}">
				  <c:if test="${listItem.isinput==0}">
				 	 <label>
						<input type="checkbox"  name="AttrValueIdList" attrid="${listItem.attrid}" attrvalid="${listItem.attrvalueid}"
						attrval="${listItem.attrvalue}" isinput="${listItem.isinput}" value="${listItem.attrvalueid}">
						${listItem.attrvalue}
					</label>
				  </c:if>
				  <c:if test="${listItem.isinput==1}">
				 	 <label style="display:none;">
				 	 	${listItem.attrvalue}
						<input type="text"  name="AttrValueInputList" attrid="${listItem.attrid}" attrvalid="${listItem.attrvalueid}"
						attrval="${listItem.attrvalue}" isinput="${listItem.isinput}" value="">
					</label>
				  </c:if>
					
				</c:forEach>
			</dl>
		</c:forEach>
	</div>
</body>
</html>