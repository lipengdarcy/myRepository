<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<h2 class="contentTitle">${topMenu}</h2>
<div class="pageContent">
	<form method="post" action="${ctx }/admin/adminuser.do?${getpost}" class="pageForm required-validate" 
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<c:choose>
				<c:when test="${actionInfo.uid!=null}">
					<dl style="display:none;">
						<dt>uid：</dt>
						<dd>
							<input name="uid" type="text" size="30"
								value="${actionInfo.uid}" readonly="readonly" />
						</dd>
					</dl>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<dl>
				<dt>请输入新密码：</dt>
				<dd>
					<input name="password" type="text" value="" />
				</dd>
			</dl>
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
