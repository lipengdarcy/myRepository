<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<h2 class="contentTitle">${topMenu}</h2>
<div class="pageContent">

	<form method="post" action="news.do?${getpost}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">

			<c:choose>
				<c:when test="${actionInfo.PKID!=null}">
					<dl>
						<dt>PKID：</dt>
						<dd>
							<input name="PKID" type="text" size="30"
								value="${actionInfo.PKID }" readonly="readonly" />
						</dd>
					</dl>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<dl>
				<dt>权限名字：</dt>
				<dd>
					<input name="name" type="text" value="${actionInfo.name }" />
				</dd>
			</dl>
			<dl>
				<dt>权限标志代码(用于功能的判定)：</dt>
				<dd>
					<input name="code" type="text" value="${actionInfo.code }" />
				</dd>
			</dl>
			<dl>
				<dt>权限类型：</dt>
				<dd>
					
				</dd>
			</dl>
			<dl>
				<dt>备注：</dt>
				<dd>
					<TextArea id="memo" name="memo" type="text" cols="45" rows="5">${actionInfo.memo }</TextArea>
				</dd>
			</dl>
			<dl>
				<dt>审核状态：</dt>
				<dd>
					
				</dd>
			</dl>
			<dl>
				<dt>InsertTime：</dt>
				<dd>
					<input name="insertTime" type="text"
						value="${actionInfo.insertTime }" />
				</dd>
			</dl>
			<dl>
				<dt>ModifyTime：</dt>
				<dd>
					<input name="modifyTime" type="text"
						value="${actionInfo.modifyTime }" />
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>

</div>
