<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/uploadify/uploadify.css">
	<script src="${ctx }/scripts/jquery-1.11.3.js" type="text/javascript"></script>
	<script type="text/javascript"
		src="${ctx }/scripts/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var fileupload = $('#file_upload');
			fileupload.uploadify({
				'swf' : '${ctx }/scripts/uploadify/uploadify.swf',
				'uploader' : '${ctx }/uploadfile/upload.do?method=uploadimg',
				//按钮显示的文字
				'buttonText' : '上传图片',
				//显示的高度和宽度，默认 height 30；width 120
				//'height': 15,
				//'width': 80,
				//上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
				//在浏览窗口底部的文件类型下拉菜单中显示的文本
				'fileTypeDesc' : 'Image Files',
				//允许上传的文件后缀
				'fileTypeExts' : '*.gif; *.jpg; *.png',
				'multi' : false,
				'method' : 'post',
				'debug' : true,
				'onUploadStart' : function(file) {
					var param = {};
					param.pathType = "5";//图片路径
					fileupload.uploadify("settings", "formData", param);
				},
				'onUploadSuccess' : function(file, data, response) {
					var obj = eval('(' + data + ')');
					if (obj.uploadState == "UPLOAD_SUCCSSS") {
						var outdata = obj.urlDateFileName;//.replace(/["""]/g, "");
						$("#imgUrl").val(outdata);// 返回的图片路径保存起来
						$("#showimgs").attr("src",
								"${ctx }/upload/product/source/" + outdata);
					} else {
						alert("上传错误！");
					}

				},
				'onUploadError' : function(file, errorCode, errorMsg,
						errorString) {
					alert('The file ' + file.name + ' could not be uploaded: '
							+ errorString);
				}
			// Your options here
			});
		});
	</script>
</head>
<body>
	枚举信息：
	<c:forEach items="${OrderStateEnum}" var="item">
${item.code}-${item.desc}<br />
	</c:forEach>
	<br />
	<input id="imgUrl" type="text" />
	<input id="file_upload" type="file" name="file" />
</body>
</html>