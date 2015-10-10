<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<style>
.realContent{
	height: 242px;
  	padding: 8px;
  	background-color: white;
  	border-top: 1px solid #99bbe8;
  	border-bottom: 1px solid #99bbe8;
	}
.realContent h2{
	font-size: 16px;
  	text-align: center;
  	margin-top: 8px;
  	}
.realContent table{
	border-color: lightgray;
  	border-width: 1px;
}
.realContent table tr{
	height:24px;
	line-hieght:24px;
}
.realContent .unit{
    margin: 16px 0px 8px 0px;
}
span.sava-btn {
  padding: 5px 16px;
  font-weight: bold;
  border: 1px solid rgb(153, 187, 232);
  cursor: pointer;
  background-color: rgb(229, 242, 254);
  float:right;
}
</style>

<div class="pageContent">
	<div class="realContent">
		<h2>用户申请资料</h2>

		<div class="unit">
			<label>工厂名称：</label><label>${apply.name}</label>
		</div>
	
		<table width="100%" border="1" cellpadding="2" cellspacing="3"
			class="memberTable">
			<tbody>
				<tr>
					<th width="100" align="right"><c:if test="${apply.type==2}">工厂名称：</c:if>
						<c:if test="${apply.type==1}">门店名称：</c:if></th>
					<td>${apply.name}</td>
				</tr>
	
				<tr>
					<th width="100" align="right"><c:if test="${apply.type==2}">工厂地址：</c:if>
						<c:if test="${apply.type==1}">门店地址：</c:if></th>
					<td>${apply.address}</td>
				</tr>
	
				<tr>
					<th width="100" align="right">联系电话：</th>
					<td>${apply.tel}</td>
				</tr>
	
				<tr>
					<th width="100" align="right">联系人：</th>
					<td>${apply.contacts}</td>
				</tr>
	
			</tbody>
		</table>
	
		<form id="form" method="post" action="">
			<div class="unit">
				<input type="hidden" name="id" id="id" value="${id }" />
				<c:if test="${status.applystatus!=1}">
					<c:if test="${apply.type==1}">
						<label>NC客户编号：</label>
					</c:if>
					<c:if test="${apply.type==2}">
						<label>NC公司编号：</label>
					</c:if>					
					<label>请输入要绑定到客户的工厂或者门店的NC编号:</label>
					<input id="clientNo" name="clientNo" value="" type="text" />
	
					<div class="unit">						
						<span class="sava-btn" onclick="saveEditForm();">提交</span>											
					</div>
				</c:if>
	
				<c:if test="${status.applystatus==1}">
					<c:if test="${apply.type==1}">
						<label>NC客户编号：${NC}</label>
					</c:if>
					<c:if test="${apply.type==2}">
						<label>NC公司编号：${NC}</label>
					</c:if>
					<label>绑定客户信息到经销商或者工厂</label>
				</c:if>
	
			</div>
	
	
		</form>
	</div>
</div>

<script type="text/javascript">
	var saveEditForm = function() {

		var sendData = $("#form").serialize();

		$.ajax({
			url : url + 'permission/appproveStoreUserApply.do',
			data : sendData,
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					alertMsg.info(data.content);
				} else {
					alertMsg.error(data.content);
				}
			},
			error : function() {
				alertMsg.error("异常！请重新尝试或者联系管理员！");
			}
		});

	};
</script>
