<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<h2 class="contentTitle">${topMenu}</h2>
<div class="pageContent">
	<form method="post" action="${ctx }/admin/category.do?${getpost}" class="pageForm required-validate" 
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<c:choose>
				<c:when test="${props.cateid!=null}">
					<dl style="display:none;">
						<dt>cateid：</dt>
						<dd>
							<input name="cateid" type="text" size="30"
								value="${props.cateid}" readonly="readonly" />
						</dd>
					</dl>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<dl>
				<dt>所属分类：</dt>
				<dd>
					<select name="parentid" value="${props.parentid}">
						<option value="0">顶层分类</option>
						<c:forEach var="paritem" items="${cateParents}" varStatus="status">
							<option value="${paritem.cateId }">${paritem.name }</option>
						</c:forEach>
					</select>
					<input type="hidden" name="oldParId" value="${props.parentid}">
				</dd>
			</dl>
			<dl>
				<dt>分类名称</dt>
				<dd>
					<input name="name" type="text" value="${props.name}" />
				</dd>
			</dl>
			<dl>
				<dt>价格区间</dt>
				<dd>
					<textarea name="pricerange" cols="25" rows="10" >${props.pricerange}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>排序</dt>
				<dd>
					<input name="displayorder" type="text" value="0" />
				</dd>
			</dl>
			<div style="display:none;">
				<dl>
					<dt>层级</dt>
					<dd>
						<input name="layer" type="text" value="${props.layer}" />
					</dd>
				</dl>
				<dl>
					<dt>是否有子节点</dt>
					<dd>
						<input name="haschild" type="text" value="${props.haschild}" />
					</dd>
				</dl>
				<dl>
					<dt>路径</dt>
					<dd>
						<input name="path" type="text" value="${props.path}" />
					</dd>
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
