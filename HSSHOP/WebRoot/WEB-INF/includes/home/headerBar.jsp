<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<script type="text/javascript">
<!--
	var temp = "${sessionScope.account_session_id}";
	if (temp != null || temp != "") {
		uid = temp;
	}

	url = "${ctx }/";
//-->

</script>

<div id="headerBar" class="bigBox">
	<div class="box1210">
		<!--bof location-->
		<div class="adress left" id="add-hs-tabs" data-step="2"
			data-intro="这里可以选择区域地址。" data-tooltipClass="cus-intro-class">
			<div role="hs-area-sel" write-to-cookie max-layer="5" min-layer="5"
				seled-name-show=".show-name-ele" cid="192224-212131-214785-0-0">
				<div show-hs-area-sel>
					<span>收货地址：</span><span class="show-name-ele"></span>
				</div>
				<div class="area-float-panel float-panel">
					<div class="panel-close-btn">
						<span class="glyphicon glyphicon-remove"></span>
					</div>
					<div role="tabpanel" class="area-tabs">
						<ul class="nav nav-tabs" role="tablist"></ul>
						<div class="tab-content"></div>
					</div>
				</div>
			</div>

			<!--eof location-->
		</div>


		<div id="headerLink" class="headerLink" data-step="1"
			data-intro="这里可以注册信息。" data-tooltipClass="cus-intro-class">
			<c:choose>
				<c:when test="${! empty account_session_id}">
				您好：${account_login_name }&nbsp;&nbsp;<a href="javascript:void(1);"
						onclick="logout();">[退出]</a>
					<a href="${ctx }/ucenter/userinfo.do?&navid=10" class="A_b">[用户中心]</a>
					<c:if test="${account_session_isstoreuser==1}">
						<a href="${ctx }/dealproduct/salerIndexProList.do" class="red">[
							经销商门户 ]</a>
					</c:if>


				</c:when>
				<c:otherwise>
					<a href="javascript:void(1);" onclick="toLogin();">[登录]</a>
					<a href="${ctx }/account/register.do" class="A_b">[注册]</a>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</div>
<script type="text/javascript">
	var logout = function() {
		var $div = $("#headerLink");
		$div.empty();
		$.ajax({
					url : url + 'account.do?method=doLogout',
					data : {},
					type : 'post',
					dataType : 'json',
					success : function(data) {
						if (data.state == 'success') {
							var returnUrl = encodeURIComponent(location.href);
							location.href = url + "account/login.do?return_url=" + returnUrl;
							/*$div
									.append("<a href=\"javascript:void(1);\" onclick=\"toLogin();\">[登录]</a>&nbsp;");
							$div
									.append("<a href=\""+url+"account/register.do\" class=\"A_b\">[注册]</a>");
							if (uid != undefined) {
								uid = -1;
							}*/
						}
					},
					error : function() {
					}
				});

	}, toLogin = function() {
		var returnUrl = encodeURIComponent(location.href);
		location.href = url + "account/login.do?return_url=" + returnUrl;
	};
	$(document).ready(function(e){
		$.ajax({
			url : url + '/cart/countCartProQuantity.do',
			type : 'get',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					$(".cartnum-ele").html(data.quantity);
				}
			},
			error : function() {
			}
		});
	});
</script>
