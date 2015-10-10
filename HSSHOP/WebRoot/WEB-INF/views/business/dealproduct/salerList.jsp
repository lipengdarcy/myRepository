<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="红狮水泥商城" />
	<meta name="description" content="红狮水泥商城" />	
	<%@ include file="../../../includes/homenew/header.jsp"%>
	<script type="text/javascript" src="${ctx }/scripts/product.js?v=1.0.3"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/user.css"/>	
	<script>
		$(document).ready(function(e){
			$(".num-put .del").bind("click",function(e){
				var src=e.target;
				//
				var countInput=$(src).closest(".num-put").find("[buyCountinput]");
				tcutProductCount(countInput)
			});
			$(".num-put .add").bind("click",function(e){
				var src=e.target;
				//
				var countInput=$(src).closest(".num-put").find("[buyCountinput]");
				taddProuctCount(countInput);
			});
		});
		
		
		//增加商品数量
		function taddProuctCount(countInput) {
			var buyCountInput = document.getElementById("buyCount");			
			if(countInput){
				buyCountInput=countInput[0];
			}
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			
		    var buyCount = buyCountInput.value;
		    if (!isInt(buyCount)) {
		        hsArtDialog.content("请输入数字").showModal();
		        return false;
		    }
		    buyCountInput.value = parseInt(buyCount) + 1;
		    $(buyCountInput).trigger("change");
		}

		//减少商品数量
		function tcutProductCount(countInput) {
		    var buyCountInput = document.getElementById("buyCount");
		    if(countInput){
				buyCountInput=countInput[0];
			}
		  //弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			
		    var buyCount = buyCountInput.value;
		    if (!isInt(buyCount)) {	
		    	hsArtDialog.content("请输入数字").showModal();
		        return false;
		    }
		    var count = parseInt(buyCount);
		    if (count > 1) {
		        buyCountInput.value = count - 1;
		    }
		    $(buyCountInput).trigger("change");
		}
	</script>
	<title>经销商允销目录-红狮水泥商城</title>
</head>
<body>
	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>
	<!--bof main-->	
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="${ctx }/business/storeInfo.do">个人中心首页</a> </strong>&gt; 
				<a href="${ctx }dealproduct/salerIndexProList.do">经销商门户</a> &gt; <span>允销目录</span></div>
		 	<!--bof menu--> 
			 <div class="user-menu left"> 
			 	<%-- <c:import url="${ctx }/ucenter.do?userType=${userType}&navId=1"/> --%>
			 	<%@ include file="../../home/center/centermenu.jsp"%>
			 </div>
			 <!--eof menu-->
			 <!--bof content-->
			<div class="user-content right">
				<h1>允销目录</h1>
			    <div class="user-search clearfix">
			      <form id="search-salerpro-form" method="get" action="./salerProList.do">
			        <fieldset class="clearfix">
			          <legend>商品编码：</legend>
			          <input type="text" name="ncpronum" value="${parinfor.ncpronum }" placeholder="请输入商品编码" />
			        </fieldset>
			        <fieldset class="clearfix">
			          <legend>商品名称：</legend>
			          <input type="text" name="proname" value="${parinfor.proname }" placeholder="请输入商品名称" />
			        </fieldset>
			        <fieldset class="clearfix btn" >
			          <input type="submit"  value="提交" class="user-search-btn"/>
			        </fieldset>
			      </form>
			    </div>
			     <div class="sales-list">
				     <div class="user-table-head">
				        <table >
				          <tbody>
				            <tr>
				              <th scope="col" class="product-info">商品名称</th>
				              <th scope="col" class="table-wd01">品牌</th>
				              <th scope="col" class="table-wd01">生产厂家</th>
				              <th scope="col" class="table-wd01">价格</th>
				              <th scope="col" class="table-wd01">数量</th>
				              <th scope="col" class="table-wd01">操作</th>
				            </tr>
				          </tbody>
				        </table>
				      </div>
				      <div class="user-table-ul"> 
				    	<c:forEach items="${productList}" var="p" varStatus="vs">
					     	<div class="user-table-li">
					          <table>
					            <tbody>
					              <tr>
					                <td colspan="6" class="product-num">商品编号:${p.ncpronum}</td>
					              </tr>
					              <tr class="table-sline">
					                <td class="product-info">
					                	<div class="imghloder">
					                		<a href="${ctx}/dealproduct/detail.do?id=${p.pid}">
					                			<img src="${ctx}/upload/product/source/${p.imgName}" onerror="nofind();" alt=""/>
					                		</a>
					                	</div>
					                    <div class="intro">
					                    	<a href="${ctx}/dealproduct/detail.do?id=${p.pid}">${p.name}</a>
					                    </div>
					                </td>
					                <td class="table-wd01">${p.branname}</td>
					                <td class="table-wd01">${p.placename}</td>
					                <td class="table-wd01">￥${p.ncprice}</td>
					                <td class="">
					                	<div class="num-put" >
					                		<em class="del">-</em>
					                    	<input type="text" name="buyCount" id="buyCount${vs.index }" buyCountinput value="1" /> 
					                    	<em class="add">+</em>
					                   		 <span>吨</span>
					                    </div>
					                </td>
					                <td class="table-wd01">
					                	<a onclick="dealAddProductToBuy('${p.pid}','#buyCount${vs.index }')">立即购买</a>
					                </td>
					              </tr>
					            </tbody>
					          </table>
					        </div>
				        </c:forEach>
				        ${pageDiv }	
				    </div>
			     </div>
			</div> 
			<!--eof content-->
		</div>
	</div>
	<!--eof main-->
	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service--> 	
	<%@ include file="../../../includes/homenew/footer.jsp"%>
</body>
</html>