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
<div class="up-product-img-content">
		<form action="" method="post" target="navTab">
			<table>
				<tr>
					<td>短信验证码</td>
					<td>
						<input type="hidden" name="phone" id="phone" value="${order.mobile }" >
						<input type="text" id="VerificationCode" value="" size="5" maxlength="5"/>
					</td>
				</tr>
				<tr><td>短信内容</td>
					<td>
						<textarea rows="8" cols="" id="content" >${content }</textarea>
					</td></tr>
				<tr>
					<td>
					    <input type="button" onclick="verification('${order.osn }');" value="验证码验证" >
					</td>
				</tr>
			</table>
		</form>
		
	</div>
</body>
<script type="text/javascript">
	function verification(orderNum){
		var VerificationCode=$("#VerificationCode").val();
		var phone=$("#phone").val();
		var textobj=$("#content").val();
		if(VerificationCode==""){
			alert("验证码不能为空！");
			return;
		}
		$.ajax({
			url:'${ctx }'+'/admin/order/verification.do',// 跳转到 action    
			data:{    
				VerificationCode : VerificationCode,
				orderNum : orderNum,
				mobile : phone,
				textobj : textobj,
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					alert("验证成功！");
				}else{
					alert(data.content);
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}  
		});
	}
</script>
</html>