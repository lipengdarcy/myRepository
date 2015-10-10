<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!-- 添加允销目录条目 -->
<style>
	.pageFormContent label{
	 	width:auto;
	}
</style>
<h2 class="contentTitle">${topMenu}</h2> 
<div class="pageContent">
	<form method="post" action="${ctx }/admin/adminproduct.do?${getpost}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<div>
				<dl>
					<dt>企业NC编号:</dt>
					<input type="text" name="eid" value="">
					<!-- <a class="btnLook" href="${ctx }/admin/ncpro/ncprolookbacklist.do" lookupGroup="product" 
								lookupPk="pid" width="800" height="600" >选择NC编号</a> -->
				</dl>
				<dl>
					<dt>企业名称:</dt>
					<input type="text" name="ename" value="">
				</dl>
				<dl>
					<dt>公司NC编号:</dt>
					<input type="text" name="pkcorp" value="">
					<!-- <a class="btnLook" href="${ctx }/admin/ncpro/ncprolookbacklist.do" lookupGroup="product" 
								lookupPk="pid" width="800" height="600" >选择NC编号</a> -->
				</dl>
				<dl>
					<dt>公司名称:</dt>
					<input type="text" name="corpname" value="">
				</dl>				
				<dl>
					<dt>产品编号:</dt>
					<input type="text" name="pid" value="">
					<!-- <a class="btnLook" href="${ctx }/admin/ncpro/ncprolookbacklist.do" lookupGroup="product" 
								lookupPk="pid" width="800" height="600" >选择NC编号</a> -->
				</dl>
				<dl>
					<dt>产品名称:</dt>
					<input type="text" name="pname" value="">
				</dl>
				<dl>
					<dt>产品单价:</dt>
					<input type="text" name="price"  class="required number" value="">
				</dl>
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
