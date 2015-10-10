<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<div id="sortlist" class="m itme infoList">

	<div class="mc">


		<c:forEach items="${menuLink}" var="p">
			<div class="item hot">
				<h3>
					<b></b>${p.attrname}
				</h3>
				<ul>
					<c:forEach items="${p.productlinklist}" var="pl" varStatus="status">
						<c:if test="${pl.attrValue.attrvalueid !=null && status.count<8}">

							<li><a href="${ctx}/product/comboQuery.do?path=${pl.path}"
								<c:if test="${pl.pathcenterStr >0}"> class="hot"</c:if>><c:out
										value="${pl.attrValue.attrvalue}" /></a></li>
						</c:if>

					</c:forEach>
					<div class="clear" />
				</ul>
				<div class="clear"></div>
			</div>
		</c:forEach>
	</div>
</div>