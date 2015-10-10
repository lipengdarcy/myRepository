<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<div id="listSelect">
	<h1>商品筛选</h1>

	<div id="selectBox">
		<c:forEach items="${productCombo}" var="p">
			<c:if test="${p.attrid==52}">
				<!-- 品牌 -->
				<div class="selectItme">
					<h3>${p.attrname}:</h3>
					<ul>
						<c:forEach items="${p.productlinklist}" var="pl">

							<li><a href="comboQuery.do?path=${pl.path}"
								<c:if test="${pl.pathcenterStr >0}"> class="hot"</c:if>><c:out
										value="${pl.attrValue.attrvalue}" /></a></li>


						</c:forEach>
						<div class="clear"></div>
					</ul>

					<div class="clear"></div>
				</div>
				<!-- 产地 -->
				<div class="selectItme">
					<h3>生产厂家:</h3>
					<ul>
						<c:forEach items="${p.productlinklist}" var="pl">
							<c:forEach items="${pl.brand_factory}" var="bf">
								<c:if test="${bf.key==pl.attrValue.attrvalueid && pl.pathcenterStr >0}">
									<c:forEach items="${bf.value}" var="ps">
										<li><a href="comboQuery.do?path=${ps.path}"
											<c:if test="${ps.pathcenterStr >0}"> class="hot"</c:if>><c:out
													value="${ps.attrValue.attrvalue}" /></a></li>
									</c:forEach>

								</c:if>
							</c:forEach>
						</c:forEach>
						<div class="clear"></div>
					</ul>

					<div class="clear"></div>
				</div>
			</c:if>

			<c:if test="${p.attrid!=52 && p.attrid!=51}">
				<div class="selectItme">
					<h3>${p.attrname}:</h3>
					<ul>
						<c:forEach items="${p.productlinklist}" var="pl">




							<li><a href="comboQuery.do?path=${pl.path}"
								<c:if test="${pl.pathcenterStr >0}"> class="hot"</c:if>><c:out
										value="${pl.attrValue.attrvalue}" /></a></li>


						</c:forEach>
						<div class="clear"></div>
					</ul>

					<div class="clear"></div>
				</div>
			</c:if>
		</c:forEach>
	</div>

</div>




