<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/uploadify/uploadify.css">
<script type="text/javascript"
	src="${ctx }/scripts/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
	$(function() {
		var fileupload = $('#file_upload');
		fileupload.uploadify({
			'swf' : '${ctx }/scripts/uploadify/uploadify.swf',
			'uploader' : '${ctx }/admin/uploadfile.do?method=uploadimg',
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
			'onUploadStart' : function(file) {
				var param = {};
				param.pathType = "5";//图片路径
				fileupload.uploadify("settings", "formData", param);
			},
			'onUploadSuccess' : function(file, data, response) {
				var obj = eval('(' + data + ')');
				if (obj.uploadState == "UPLOAD_SUCCSSS") {
					var outdata = obj.urlDateFileName;//.replace(/["""]/g, "");
					$("#showimg").val(outdata);// 返回的图片路径保存起来
					$("#showimgs").attr("src",
							"${ctx }/upload/product/source/" + outdata);
				} else {
					alert("上传错误！");
				}
			},
			'onUploadError' : function(file, errorCode, errorMsg, errorString) {
				alert('The file ' + file.name + ' could not be uploaded: '
						+ errorString);
			}
		// Your options here
		});
	});
</script>
</head>
<body>
	<div class="up-product-img-content">
		<form action="${ctx }/admin/adminproduct.do?method=addProductImg"
			method="post" target="navTab">
			<table>
				<tr>
					<td>上传图片</td>
					<td><span id="product-upload-img-span" class="margin-ele">
							<img id="showimgs" alt="" src="">
					</span> <input type="hidden" name="showimg" id="showimg" value="">
						<input type="hidden" name="pid" value="${productid}"> <input
						type="hidden" name="ismain" value="0"> <input
						id="file_upload" type="file" name="file" /></td>
				</tr>
				<tr>
					<td>排序</td>
					<td><input type="text" name="displayorder" value="0"
						class="margin-ele"></td>
				</tr>
				<tr>
					<td></td>
					<td><span class="button add-product-img-btn margin-ele"><span>添加</span></span></td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>