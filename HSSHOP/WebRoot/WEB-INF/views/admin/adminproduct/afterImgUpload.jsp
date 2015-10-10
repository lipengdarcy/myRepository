<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" name="imgname" id="img-errInfor" value="${errInfor}">
	<input type="hidden" name="imgname" id="img-name" value="${imgName}">
	<input type="hidden" name="imgname" id="img-url" value="${imgUrl}">
	<script>
		var imgErrInfor=document.getElementById("img-errInfor");
		if(imgErrInfor.value){
			parent.alert(imgErrInfor.value);
		}else{
			var showImgEle=document.getElementById("img-name");
			var showImgName=showImgEle.value;
			var showUrlEle=document.getElementById("img-url"); 
			var showImgUrl=showUrlEle.value;
			var parShowImg= parent.document.getElementById("product-upload-show-img"); 
			parShowImg.value=showImgName;
			var parImgUrl= parent.document.getElementById("product-upload-img-span"); 
			while(parImgUrl.hasChildNodes()){
				parImgUrl.removeChild(parImgUrl.firstChild); 
			}
			
			var nimg=document.createElement("img")
			nimg.src=showImgUrl;
			parImgUrl.appendChild(nimg);
		}
	

	</script>
</body>
</html>