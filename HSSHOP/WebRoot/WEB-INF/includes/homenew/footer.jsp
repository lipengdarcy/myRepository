<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<style>
	/**隐藏在线客服浮动层**/
	.lim_float_icon {
	  display: none;
	}
</style>
<div id="footer" class="footer">
	<div class="wrap">
		 <ul class="clearfix footer-menu">
		 	<li>
		 		<ul>
		          <li><strong>| 用户注册</strong></li>
		          <li><a href="${ctx}/help/1.html">手机注册</a></li>
		          <li><a href="${ctx}/help/2.html">邮箱/手机验证</a></li>
		          <li><a href="${ctx}/help/3.html">用户登录</a></li>
		          <li><a href="${ctx}/help/4.html">忘记密码</a></li>
		        </ul>
		 	</li>
		 	<li>
		 		<ul>
		          <li><strong>| 下单购物</strong></li>
		          <li><a href="${ctx}/help/5.html">下单规则</a></li>
		          <li><a href="${ctx}/help/6.html">下单流程</a></li>
		          <li><a href="${ctx}/help/7.html">商品搜索</a></li>
		          <li><a href="${ctx}/help/8.html">商品发布规则</a></li>
		          <li><a href="${ctx}/help/9.html">订单状态</a></li>
		          <li><a href="${ctx}/help/10.html">取消订单</a></li>
		        </ul>
		 	</li>
		 	<li>
		 		<ul>
		          <li><strong>| 支付方式</strong></li>
		          <li><a href="${ctx}/help/11.html">在线支付</a></li>
		          <li><a href="${ctx}/help/12.html">工厂支付</a></li>
		          <li><a href="${ctx}/help/13.html">门店支付</a></li>
		          <li><a href="${ctx}/help/14.html">经销商垫资</a></li>
		          <li><a href="${ctx}/help/15.html">经销商结算规则</a></li>
		          <li><a href="${ctx}/help/16.html">定价/调节规则</a></li>
		        </ul>
		 	</li>
		 	<li>
		 		<ul>
		          <li><strong>| 配送政策</strong></li>
		          <li><a href="${ctx}/help/17.html">门店自提</a></li>
		          <li><a href="${ctx}/help/18.html">工厂自提</a></li>
		          <li><a href="${ctx}/help/19.html">门店配送</a></li>
		          <li><a href="${ctx}/help/20.html">运费规则</a></li>
		        </ul>
		 	</li>
		 	<li>
		 		<ul>
		          <li><strong>| 服务咨询</strong></li>
		          <li><a href="${ctx}/help/21.html">退货规则</a></li>
		          <li><a href="${ctx}/help/22.html">售后服务</a></li>
		          <li><a href="${ctx}/help/23.html">投诉与建议</a></li>
		        </ul>
		 	</li>
		 	<li>
		 		<ul>
		          <li><strong>| 关于红狮</strong></li>
		          <li><a href="${ctx}/help/24.html">商城介绍</a></li>
		          <li><a href="${ctx}/help/25.html">联系我们</a></li>
		          <li><a href="${ctx}/help/26.html">注册协议</a></li>
		          <li><a href="${ctx}/help/27.html">短信模版</a></li>
		          <li><a href="${ctx}/help/28.html">用户合同</a></li>
		          <li><a href="${ctx}/help/29.html">公司招聘</a></li>
		          <li><a href="${ctx}/help/30.html">联系客服</a></li>
		        </ul>
		 	</li>
		 	<li class="f-er">
	        	<div class="imghloder"><img src="${ctx}/themes/grey/images/er.jpg"   alt=""/></div>
	        	<em>扫描微信二维码下载APP</em> 
	         </li>
		 </ul>	
		 <div class="certification">
		 	<a href="###"><img src="${ctx}/themes/grey/images/certification/certification01.jpg"  alt=""/></a>
		 	<a href="###"><img src="${ctx}/themes/grey/images/certification/certification02.jpg"  alt=""/></a>
		 	<a href="###"><img src="${ctx}/themes/grey/images/certification/certification03.jpg"  alt=""/></a>
		 	<a href="###"><img src="${ctx}/themes/grey/images/certification/certification04.jpg"  alt=""/></a>
		 </div>	 
	</div>	
</div>
<!--eof footer-->
<div class="copyright">浙ICP证000000号  |  互联网信息服务资格证编号(浙)-经营性-2015  红狮水泥商城 版权所有 @2015, runlion.com Inc. </div>
<!--bof fixed-->
<!--右侧浮动条开始-->
<div class="fixed-menu">
	<div class="fixed-user">
		<c:choose>
			<c:when test="${! empty account_session_id}">
				<a href="${ctx }/ucenter/userinfo.do" class="A_b"><i></i>个人中心</a>
				<a href="javascript:void(1);" onclick="logout();">退出</a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(1);" onclick="toLogin();"><i></i>登录</a>
				<a href="${ctx }/account/register.do">注册</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="fixed-cart">
		<a href="${ctx }/cart.do"><i></i>购物车</a>
		<span class="cartnum-ele">
			<c:if test="${empty sessionScope.cartCount }">0</c:if>
			<c:if test="${not empty sessionScope.cartCount }">${sessionScope.cartCount }</c:if>		
		</span>
	</div>
	<div class="fixed-service"><a href="###"><i></i>在线客服</a></div>
	<div class="fixed-feedback"><a href="###"><i></i>反馈</a></div>
	<div class="fixed-backtop"><a href="#"><i></i>TOP</a></div>
	<div class="fixed-close"><a href="###"><i></i>close</a></div>
</div>
<!--右侧浮动条结束-->
<!-- Live800在线客服图标:默认图标[浮动图标] 开始-->
<div style='display: none;'>
	<a href='http://www.live800.com'>live800Link.customerservicesoftware</a>
</div>
<script language="javascript"
	src="http://chat16.live800.com/live800/chatClient/floatButton.js?jid=2993211091&companyID=543577&configID=80434&codeType=custom"></script>
<div style='display: none;'>
	<a href='http://en.live800.com'>live chat</a>
</div>
<!-- 在线客服图标:默认图标 结束-->
<script>
	//是右侧浮动栏的在线客服可用，实质是触发在线客服图片的单击事件
	$(".fixed-service").on({
		click:function(e){
			$(".lim_float_icon img").trigger("click");
		}
	});
</script>