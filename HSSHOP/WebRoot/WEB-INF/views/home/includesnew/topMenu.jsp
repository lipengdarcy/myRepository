<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!-- 导航开始 -->
<div class="nav">
	<div class="wrap clearfix">
		<div class="categorys left">
			<div class="title">全部商品分类</div>
			<ul class="info nav-info hide">
				<c:forEach items="${menuLink}" var="p" varStatus="ovst">
					<li><a>${p.attrname}</a></li>
				</c:forEach>
			</ul>
			<div class="nav-detail">
				<c:forEach items="${menuLink}" var="p" varStatus="ivst">
					<ul class="son-info${ivst.index } hide">
						<c:forEach items="${p.productlinklist}" var="pl">
							<c:if test="${pl.attrValue.attrvalueid !=null}">
								<li>
									<a href="${ctx}/product/comboQuery.do?path=${pl.path}" <c:if test="${pl.pathcenterStr >0}"> class="hot"</c:if>>
									<c:out value="${pl.attrValue.attrvalue}" />
									</a>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</c:forEach>
			</div>
		</div>
		 <ul class="left">
		 	<c:forEach items="${menuLink}" var="p">
				<li><a>${p.attrname}</a>
					<ul class="son-info hide">
						<c:forEach items="${p.productlinklist}" var="pl">
							<c:if test="${pl.attrValue.attrvalueid !=null}">
								<li>
									<a href="${ctx}/product/comboQuery.do?path=${pl.path}" <c:if test="${pl.pathcenterStr >0}"> class="hot"</c:if>>
									<c:out value="${pl.attrValue.attrvalue}" />
									</a>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		 </ul>
	</div>
</div>
<!-- 导航结束 -->
	