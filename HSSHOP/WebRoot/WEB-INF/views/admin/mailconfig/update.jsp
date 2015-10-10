<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<h2 class="contentTitle">${topMenu}</h2>
<div class="pageContent">
	<style>
.pageFormContent .addTable .textInput {
	float: none;
	margin-bottom: 8px;
}
</style>
	<form method="post" action="${ctx }/admin/mailconfig.do?${getpost}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<div class="addTable">
				<div>
					<label>邮箱主机：</label>
					<input class="input valid" id="Host" name="Host" size="35" type="text" value="${mailinfor.host}">
				</div>
				<div>
					<label>邮箱端口：</label>
					<input class="input valid" id="Port" name="Port" size="35" type="text" value="${mailinfor.port}">
				</div>
				<div>
					<label>发送邮箱：</label>
					<input class="input valid" id="From" name="From" size="35" type="text" value="${mailinfor.from}">
				</div>
				<div>
					<label>发件人名：</label>
					<input class="input valid" id="FromName" name="FromName" size="35" type="text" value="${mailinfor.fromName}">
				</div>
				<div>
					<label>邮箱账号：</label>
					<input class="input valid"  id="UserName" name="UserName" size="35" type="text" value="${mailinfor.userName}">
				</div>
				<div>
					<label>邮箱密码：</label>
					<input class="input" id="Password" name="Password"  size="35" type="password" value="${mailinfor.password}">
				</div>
				<div>
					<label>找回密码内容：</label>
					<textarea class="editor" rows="8" cols="100" name="FindPwdBody">${mailinfor.findPwdBody}</textarea>
				</div>
				<div>
					<label>验证邮箱内容：</label>
					<td><textarea class="editor" rows="8" cols="100" name="SCVerifyBody">${mailinfor.SCVerifyBody}</textarea>
				</div>
				<div>
					<label>更新邮箱内容：</label>
					<textarea class="editor" rows="8" cols="100" name="SCUpdateBody">${mailinfor.SCUpdateBody}</textarea>
				</div>
				<div>
					<label>注册欢迎内容：</label>
					<textarea class="editor"  rows="8" cols="100" name="WebcomeBody">${mailinfor.webcomeBody}</textarea>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>

</div>
