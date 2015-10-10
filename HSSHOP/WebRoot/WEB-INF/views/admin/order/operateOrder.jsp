<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="order/processOrder.do" class="pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
		<input type="hidden" name="optType" value="${optType}"/>
		<input type="hidden" name="orderId" value="${orderId}"/>
		<input type="hidden" name="admin" value="1"/>
		<div class="pageFormContent" layoutH="58">
			
			<c:if test="${not empty notpay }">
				<span class="not-pay-online">注意:还未完成在线支付！</span>
			</c:if>
			<c:if test="${optType == 3 }">
				<div class="unit">
					<label>配送单号：</label>
					<input type="text" name="shipNo" class="required textInput" />
				
				</div>
			</c:if>
			<div class="unit">
				<label>操作备注：</label>
				<textarea cols="40" id="actionDes" name="actionDes" rows="6"></textarea>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>





	<script>
	function dialogReloadNavTab(json){  

     DWZ.ajaxDone(json);  

     var tabId = $("ul.navTab-tab li.selected").attr("tabid");  
    
     if (json.statusCode == DWZ.statusCode.ok){  

         if (json.navTabId || tabId!=null){  

             navTab.reload(json.forwardUrl, {navTabId: json.navTabId});  

         } else if (json.rel) {  

             var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());  

             var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}  

             navTabPageBreak(args, json.rel);  

         }  

         if ("closeCurrent" == json.callbackType) {  

             $.pdialog.closeCurrent();  

        }  

     }  

 } 
	</script>