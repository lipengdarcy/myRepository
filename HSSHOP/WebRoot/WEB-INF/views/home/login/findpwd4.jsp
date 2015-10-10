<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/account.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="loginTop" class="box">
	    <a href="${ctx }/index.do" class="left"><img src="${ctx }/themes/default/images/logo.png" width="186" height="42" /></a>
	    <h2>找回密码</h2>
	    <div class="clear"></div>
	</div>
	
	<div class="findPassword box">
	<div class="step">
	<ul>
	    <li><s>1</s>验证身份</li>
	    <li><s>2</s>验证邮箱</li>
	    <li><s>4</s>设置新密码</li>
	    <li class="hot"><s>4</s>完成</li>
	    <div class="clear"></div>
	</ul>
	</div>
	
	<form id="changePwdForm" action="">
	<input type="hidden" id="uid_h" value="${uid }" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td>
	    	<dir style="text-align:center;">
	    		修改成功，<a href="${ctx }/index.do" style="color: green;">去首页</a>挑选喜欢的商品
	    	</dir>
	    </td>
	  </tr>
	</table>
	</form>
	</div>
		

	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>
