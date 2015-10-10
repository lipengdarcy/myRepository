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
<div>
	<form action="${ctx }/admin/category.do?method=addAttrVal" method="post" target="navTab" >
		<dl>
		<dt>属性名:</dt>
			<input type="text" name="attrname" value="${attrname }" readonly="readonly">
			<input type="hidden" name="attrid" value="${attrid }" >
			<input type="hidden" name="isinput" value="0" >
		</dl>
		<dl>
			<dt>属性值</dt>
			<input type="text" name="attrvalue" >
		</dl>
		<dl>
			<dt>排序</dt>
			<input type="text" name="attrvaluedisplayorder" value="0">
		</dl>
		<dl>
			<span class="button" id="save-attrval-btn"><span>添加属性值</span></span>
		</dl>
	</form>
</div>
</body>
</html>