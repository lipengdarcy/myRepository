<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
 <style type="text/css">
    .addTable table{ border-right:1px solid #d1d1d1;border-bottom:1px solid #d1d1d1; margin-top:20px; margin-left:16px; } 
    .addTable table th{ background:#dbeffa; font-weight:normal;height:26px; padding-left:4px;} 
    .addTable table th a{ padding-left:8px;} 
    .addTable table td{ border-left:1px solid #d1d1d1;border-top:1px solid #d1d1d1;height:26px; padding-left:10px;}
    .addTable table td.pImg{ padding:10px; }
    .addTable{overflow:auto}
    .tabsPage .tabsPageContent {
  display: block;
  overflow: auto;
  border-style: solid;
  border-width: 0 1px 1px 1px;
  position: relative;
}
 </style>

<form action="${ctx }/admin/order/orderinfo?oid=30" method="post"><div class="addTable">

<table width="97%">
  <tr><th colspan="4" align="left">评价信息</th></tr>
  <tr>
    <td width="150px">评论编码：</td>
    <td width="320px">${comment.cid }</td>
    <td width="150px">产品编码：</td>
    <td>${comment.pid }</td>
  </tr>
  <tr>
    <td>用户编码：</td>
    <td>${comment.uid }</td>
    <td>订单编码：</td>
    <td>${comment.oid }</td>
  </tr>
  <tr>
    <td>评价星级：</td>
    <td>${comment.score }&nbsp星</td>
    <td>评价时间：</td>
    <td>${forday.format(comment.creatDate)}&nbsp; ${fortime.format(comment.creatDate)}</td>
  </tr>
  <tr>
      <td>评价内容：</td>
    <td colspan="3">
    	    <p> ${comment.comment }</p>
    </td>
  </tr>
</table>

<table width="97%">
  <tr><th colspan="4" align="left">用户信息</th></tr>
  <tr>
    <td width="150px">用户编号：</td>
    <td width="320px">${user.uid }</td>
    <td width="150px">用户名：</td>
    <td>${user.username }</td>
  </tr>
  <tr>
    <td>真实姓名：</td>
    <td>${userdetails.realname}</td>
    <td>性别：</td>
    <td>${userdetails.gender  }</td>
  </tr>
  <tr>
    <td>用户昵称：</td>
    <td>${user.nickname }</td>
    <td>用户等级：</td>
    <td>${userrank.title }</td>
  </tr>
  <tr>
    <td>邮箱号：</td>
    <td>${user.email }</td>
    <td>手机号：</td>
    <td>${user.mobile }</td>
  </tr>
</table>

<table width="97%">
  <tr><th colspan="6" align="left">商品信息</th></tr>
  <tr>
    <td align="center" width="60">商品图片</td>
    <td>商品名称</td>
    <td width="140">货号</td>
    <td width="80">市场价格</td>
  </tr>
  <tr>
     <td align="center" class="pImg"><img width="60px" height="60px" src="${ctx }/upload/product/thumb/60_60/${product.showimg } "  onerror="nofind();"/></td>
     <td>${product.name }</td>
     <td>${product.psn }</td>
     <td>￥${product.marketprice}</td>
    </tr>
</table>

   <!--
<table width="97%">
  <tr><th colspan="4" align="left">审核操作</th></tr>
  <tr>
    <td width="100px">
    <div id="operate">
 	<c:if test="${comment.status=='0' }">
			<a class="button" onclick="pass(${comment.cid })"><span>审核通过</span></a> 
	</c:if>
	<c:if test="${comment.status=='1' }">
		已审核通过
	</c:if>
  		 	<a class="button" href="${ctx }/admin/comment/deleteOne.do?cid=${comment.cid }" target="ajaxTodo" title="确定删除评价？"><span>删除评价</span></a>
    </div>
    </td>
  </tr>
</table>
-->
 <script type="text/javascript">
 	function pass(cid){
 		Ajax.post("${ctx }/admin/comment/pass.do", { cid:cid}, false, function(data){
 			if(data=="ok"){
 				alert("操作成功");
 			$("#operate a").hide();
 			$("#operate").html("已审核通过");
 			}
 	    })
 	}
 	
 	function nofind(){

		var img=event.srcElement;

		img.src='${ctx }/upload/product/default.png';

		img.onerror=null; //控制不要一直跳动

		}
</script>
 
<br />
</div>    

</form>