<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<h2 class="contentTitle">${topMenu}</h2>
<div class="pageContent">
	<form method="post" action="${ctx }/admin/adminunit.do?${getpost}" class="pageForm required-validate" 
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<c:choose>
				<c:when test="${actionInfo.unitid!=null}">
					<dl style="display:none;">
						<dt>unitid：</dt>
						<dd>
							<input name="unitid" type="text" size="30"
								value="${actionInfo.unitid}" readonly="readonly" />
						</dd>
					</dl>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<dl>
				<dt>单位名称：</dt>
				<dd>
					<input name="unitname" type="text" value="${actionInfo.unitname }" />
				</dd>
			</dl>
			<dl>
				<dt>英文名称</dt>
				<dd>
					<input name="unitenname" type="text" value="${actionInfo.unitenname }" />
				</dd>
			</dl>
			<dl>
				<dt>中文简称：</dt>
				<dd>
					<input name="unitshort" type="text" value="${actionInfo.unitshort }" />
				</dd>
			</dl>
			<dl>
				<dt>英文简称</dt>
				<dd>
					<input name="unitenshort" type="text" value="${actionInfo.unitenshort }" />
				</dd>
			</dl>
			<dl>
				<dt>备注</dt>
				<dd>
					<input name="remarks" type="text" value="${actionInfo.remarks }" />
				</dd>
			</dl>
			<dl>
				<dt>类型</dt>
				<dd>
					<select name="unittype">
						<option value="1" 
						<c:if test="${unittype=='1' }"></c:if>
						>重量单位</option>
						<option value="2"
						<c:if test="${unittype=='2' }"></c:if>
						>数量单位</option>
					</select>
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
