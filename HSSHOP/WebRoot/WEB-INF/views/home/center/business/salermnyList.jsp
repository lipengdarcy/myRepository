<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="../../../../includes/homenew/header.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/user.css" />
		<link rel="stylesheet" type="text/css" href="${ctx }/scripts/date/need/laydate.css" />
		<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>
		<script>
			$(document).ready(function(e){
				var state=$("#selvostate").val();
				if(state){
					$("#selvostate-selcon").val(state);
				}
				var paytype=$("#selpaytype").val();
				if(paytype){
					$("#selpaytype-selcon").val(paytype);
				}
			});
		</script>
		<title>经销商资金管理页</title>
	</head>
	<body>
		<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
		<%@ include file="../../../../includes/homenew/headerTop.jsp"%>
		<div class="user-main">
		  <div class="wrap clearfix">
			   <div class="crumbs">
			    	<strong>| <a href="${ctx }/business/storeInfo.do">个人中心首页</a> </strong>&gt; 
			    	<a href="${ctx }dealproduct/salerIndexProList.do">经销商门户</a> &gt; <span>资金管理</span>
			   </div>
			    <div class="user-menu left"> 
			      <%-- <c:import url="${ctx }/ucenter.do?userType=${userType}&navId=7" /> --%>
			      <%@ include file="../centermenu.jsp"%>
			    </div>
			    <div class="user-content right">
			    	 <h1>资金管理</h1>
				     <div class="gathering-line"><i></i><a href="${ctx }/business/payOnlinePage.do">在线交款</a></div>
				     <div class="user-search clearfix">
				     	<input type="hidden" name="" id="selvostate" value="${selVO.state }">
				     	<input type="hidden" name="" id="selpaytype" value="${selVO.paytype }">
				     	 <form action="${ctx }/salermny/toSalermnyList.do">
					          <fieldset class="clearfix search-select">
					            <legend>状态：</legend>
					            <select id="selvostate-selcon" name="state">
					              <option value='-1'>请选择</option>
					              <option value='0'>未支付</option>
					              <option value='1'>已支付</option>
					            </select>
					          </fieldset>
					          <fieldset class="clearfix search-select">
					            <legend>类型：</legend>
					            <select id="selpaytype-selcon" name="paytype">
					              <option value='-1'>请选择</option>
					              <option value='2'>贷款</option>
					              <option value='3'>保证金</option>
					            </select>
					          </fieldset>
					          <fieldset class="clearfix search-date">
					            <legend>订单日期：</legend>
					            <input type="text" name="startDate" value="${selVO.startDate }" onclick="laydate()" class="laydate-icon" />
					            <label>到</label>
					            <input type="text" name="endDate" value="${selVO.endDate }" onclick="laydate()" class="laydate-icon" />
					          </fieldset>
					          <fieldset class="clearfix btn" >
					            <input type="submit"  value="查询" class="user-search-btn"/>
					          </fieldset>
				        </form>
				     </div>
				     <div class="money-table-head">
				          <table >
				            <tbody>				             
				            </tbody>
				          </table>
			        </div>
			         <div class="money-management">
				        <table>  
					        <tbody> 
					         <tr>
					            <th scope="col" class="table-wd03">日期</th>
					            <th scope="col">类型</th>
					            <th scope="col">金额(元)</th>
					            <th scope="col" class="table-wd03">备注</th>
					            <th scope="col">状态</th>
					            <th scope="col">操作</th>
					          </tr>   
						       <c:forEach var="payitem" items="${payList }">
						          	<tr>
							            <td class="table-wd03">
							            	<fmt:formatDate value="${payitem.createtime}" pattern="yyyy年MM月dd日" />
							            </td>
							            <td> ${payitem.paytypename }	</td>
							            <td>${payitem.payamount }</td>
							            <td class="table-wd03">${payitem.description }</td>
							            <td>
							            	<c:if test="${payitem.status==0 }">
							            		未支付
							            	</c:if>
							            	<c:if test="${payitem.status==1 }">
							            		已支付
							            	</c:if>
							            </td>
							            <td>
							            	<c:if test="${payitem.status==0 }">
							            		<a class="blue" href="${ctx }/business/payOnlineSelect.do?payorderid=${payitem.id}">付款</a>
							            	</c:if>						            
							            </td>
						          	</tr>
						       </c:forEach>    
					         </tbody>
				          </table>
				     </div>
				     ${pageDiv }
			    </div>
		   </div>
		</div>
		<!--bof Service-->
		<c:import url="${ctx }/tool/newService.do"></c:import>
		<!--eof Service-->
		<%@ include file="../../../../includes/homenew/footer.jsp"%>
	</body>
</html>