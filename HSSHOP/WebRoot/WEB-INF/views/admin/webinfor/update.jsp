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
	<form method="post" action="${ctx }/admin/webinfor.do?${getpost}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<div class="addTable">
				<div>
					<label>商城名称：</label> <input class="input valid" id="ShopName"
						name="ShopName" size="35" type="text" value="${webinfor.shopName}">
				</div>
				<div>
					<label>网站网址：</label> <input class="input valid" id="SiteUrl"
						name="SiteUrl" size="35" type="text" value="${webinfor.siteUrl}">
				</div>
				<div>
					<label>网站标题：</label> <input class="input valid" id="SiteTitle"
						name="SiteTitle" size="35" type="text"
						value="${webinfor.siteTitle}">
				</div>
				<div>
					<label>SEO关键字：</label>
					<textarea cols="40" id="SEOKeyword" name="SEOKeyword" rows="6"
						class="valid">${webinfor.SEOKeyword}</textarea>
				</div>
				<div>
					<label>SEO描述：</label>
					<textarea cols="40" id="SEODescription" name="SEODescription"
						rows="6" class="valid">${webinfor.SEODescription}</textarea>
				</div>
				<div>
					<label>备案信息：</label> <input class="input valid" id="ICP" name="ICP"
						size="35" type="text" value="${webinfor.ICP}">
				</div>
				<div>
					<label>脚本代码：</label>
					<textarea cols="40" id="Script" name="Script" rows="6">${webinfor.script}</textarea>
				</div>
				<div>
					<label>版权：</label> <label> <input id="IsLicensed1"
						name="IsLicensed" type="radio"
						<c:if test="${webinfor.isLicensed=='1'}">checked="checked"</c:if>
						value="1">显示
					</label> <label> <input id="IsLicensed2" name="IsLicensed"
						type="radio"
						<c:if test="${webinfor.isLicensed=='0'}">checked="checked"</c:if>
						value="0">不显示
					</label>
					<div style="clear: both;"></div>
				</div>
				<div>
					<label>售后保障：</label>
					<textarea class="editor" id="AfterSale" name="AfterSale"
						upImgUrl="${ctx }/admin/uploadfile.do?method=xeupload"
						upImgExt="jpg,jpeg,gif,png" style="height: 270px" cols="80">${webinfor.afterSale}</textarea>
				</div>
				<div>
					<label>NC接口地址：</label> <input class="input valid" id="NcUrl"
						name="NcUrl" size="35" type="text" value="${webinfor.ncUrl}">
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
