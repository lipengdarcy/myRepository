<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<div id="nav" class="bigBox clearfix">
	<div class="box">
		<div class="box1210" id="navItme">
			<ul>
				<li><a href="${ctx}/index.do">首页</a></li>

				<c:forEach items="${menuLink}" var="p">

					<li><a>${p.attrname}</a>
						<ul>
							<c:forEach items="${p.productlinklist}" var="pl">
								<c:if test="${pl.attrValue.attrvalueid !=null}">
									<li><a href="${ctx}/product/comboQuery.do?path=${pl.path}"
										<c:if test="${pl.pathcenterStr >0}"> class="hot"</c:if>><c:out
												value="${pl.attrValue.attrvalue}" /></a></li>
								</c:if>
							</c:forEach>
						</ul></li>
				</c:forEach>

				<li><a href="#">在线咨询</a>
					<ul>
					</ul></li>
			</ul>
		</div>
	</div>
</div>