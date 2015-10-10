<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收货地址-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/homenew/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/user.css"/>	
<script type="text/javascript">
	uid = 1;
	isGuestSC = 1;
	scSubmitType = 0;
	
	function addShipAddress(){
		var shipAddressForm = document.forms["shipAddressForm"];
		var consignee = shipAddressForm.elements["consignee"].value;
		var mobile = shipAddressForm.elements["mobile"].value;
		var phone = shipAddressForm.elements["phone"].value;
		var email = shipAddressForm.elements["email"].value;
		var zipcode = shipAddressForm.elements["zipcode"].value;
		var regionId = $("#area1").find("[name='lastName']").val();
		var address = shipAddressForm.elements["address"].value;
		var isDefault = shipAddressForm.elements["isDefault"].checked == true ? 1
				: 0;
	
		if (!verifyShipAddress(consignee, mobile, phone, regionId, address)) {
			return;
		}
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		$.ajax({
			url : 'addshipaddress.do',// 跳转到 action    
			data : {
				consignee : consignee,
				mobile : mobile,
				phone : phone,
				email : email,
				zipcode : zipcode,
				regionId : regionId,
				address : address,
				isDefault : isDefault
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					closeShipAddressBlock();
					window.location.href = "shipaddresslist.do";					
				} else{
					hsArtDialog.content(data.msg).showModal();
				}
				
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}
		});
	}
</script>
<script src="${ctx }/scripts/region.js" type="text/javascript"></script>
<script src="${ctx }/scripts/ucenter.user.js" type="text/javascript"></script>
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<style>
	.adress-btn .input-btn {
	  width: 80px;
	  height: 30px;
	  border: 1px solid #eed97c;
	  background: #fffceb;
	  float: right;
	  margin-left: 10px;
	  line-height: 30px;
	  /* display: inline-block; */
	  text-align: center;
	}	
</style>
</head>

<body>

	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>
	
	<div class="user-main">
  		<div class="wrap clearfix">
    		<div class="crumbs">
    			<strong>| <a href="seller_data.html">个人中心首页</a> </strong>&gt; 
    			<a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>安全中心</span>
    		</div>
    		<div class="user-menu left"> 
    			<%@ include file="centermenu.jsp"%>
    		</div>
    		<div class="user-content right">
		      <h1>收货地址</h1>
		      <div class="adress-list">
		        <div class="adress-line"><em>您已创建${size}个收货地址，最多可创建12个</em><a href="javascript:openAddShipAddressBlock();" class="right">+新增收货地址</a></div>
		      	 <ul class="clearfix">
		      	 	<c:choose>
						<c:when test="${not empty bsalist}">
							<c:forEach items="${bsalist}" var="bsa" varStatus="vs">
								<li id="shipAddress${bsa.said}">
						            <div class="address-head">收货地址
						            	<c:if test="${bsa.isdefault=='1'}">	
						            		(<span class="red">默认</span>)
						            	</c:if>
						            </div>
						            <ul>
						              <li><em>收货人：</em>${bsa.consignee}</li>
						              <li><em>所在地区：</em>
						              	<c:if test="${bsa.regions.provinceid !=0}">${bsa.regions.provincename}</c:if>
										<c:if test="${bsa.regions.cityid !=0}">${bsa.regions.cityname}</c:if>
										<c:if test="${bsa.regions.countyid !=0}">${bsa.regions.countyname}</c:if>
										<c:if test="${bsa.regions.streetid !=0}">${bsa.regions.streetname}</c:if>
										${bsa.regions.name}
						              </li>
						              <li><em>详细地址：</em> ${bsa.address}  </li>
						              <li><em>手机：</em>${bsa.mobile}</li>
						              <li><em>固定电话：</em> ${bsa.phone}</li>
						              <li><em>电子邮箱：</em>${bsa.email}</li>
						            </ul>
						            <div class="adress-btn">
						            	<a href="javascript:void(0)"
											onclick="delShipAddress(${bsa.said})" class="input-btn">删除</a>
						            	<a href="${ctx }/ucenter/shipaddressinfo.do?saId=${bsa.said}"
											class="input-btn">编辑</a>
										<c:if test="${bsa.isdefault!='1'}">						            	
					            			<a href="javascript:void(0)" onclick="setDefaultShipAddress(${bsa.said},this)"
											class="input-btn">设为默认</a>
										</c:if>				            	
						            </div>
						         </li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr class="main_info">
								<td colspan="100" class="center">没有商品信息</td>
							</tr>
						</c:otherwise>
					</c:choose>
		      	 </ul>
		      </div>
		      <div id="shipAddressBlock" style="display:none;">
				 <div class="sales-data user-data">
			       <form name="shipAddressForm" action="">
					  <input type="hidden" name="saId" value=""></input>		         
			          <fieldset class="clearfix">
			            <legend>收货人：</legend>
			            <input name="consignee" value=""  placeholder="" />
			            <label class="red">*</label>
			          </fieldset>
			          <fieldset class="clearfix">
			            <legend>手机号码：</legend>
			            <input name="mobile" value=""  placeholder="请输入手机号码" />
			            <label class="red">*</label>          
			          </fieldset>
			          <fieldset class="clearfix">
			            <legend>固定电话：</legend>
			            <input name="phone" value=""  placeholder="请输入手机号码" />
			          </fieldset>     
			          <fieldset class="clearfix">
			            <legend>电子邮箱：</legend>
			            <input name="email" value=""   placeholder="请输入电子邮箱" />
			          </fieldset>
			          <fieldset class="clearfix">
			            <legend>邮政编码：</legend>
			            <input name="zipcode" value=""  placeholder="请输入邮政编码" />
			          </fieldset>
			          <fieldset class="clearfix">
			            <legend>所在地区：</legend>
			            <div id="area1" class="cellCon">
							<div seled-name-show=".pshow-name-ele" cid=""
								max-layer="5" input-sel="[hidden-inputs-div]"
								role="hs-area-sel" style="position: relative;">
								<div>
									<span class="pshow-name-ele"></span>
									<div show-hs-area-sel="" class="btn btn-success">请选择</div>														
								</div>
								<div class="area-float-panel float-panel">
									<div class="panel-close-btn">
										<span class="glyphicon glyphicon-remove"></span>
									</div>
									<div role="tabpanel" class="area-tabs">
										<ul class="nav nav-tabs" role="tablist"></ul>
										<div class="tab-content"></div>
									</div>
									<div hidden-inputs-div=""></div>
								</div>
							</div>
						</div>
			          </fieldset>
			          <fieldset class="clearfix">
			            <legend>详细地址：</legend>
			            <input type="text" name="address"  placeholder="请输入详细地址" />
			            <label class="red">*</label>
			          </fieldset>
			          <fieldset class="clearfix">
			            <legend>默认地址：</legend>
			            <input type="checkbox"  name="isDefault" checked="checked"/>
			          </fieldset>
			          <fieldset class="clearfix btn" >
			            <input type="submit"  value="提交" class="submit-btn"
			            	onclick="addShipAddress()" id="addShipAddressBut"/>
			            <input type="reset"  value="重置" class="reset-btn"
			            	onclick="editShipAddress()" id="editShipAddressBut"/>
			          </fieldset>
			        </form>
			      </div>
			  </div>
		    </div>
    	</div>
    </div>
	
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<%@ include file="../../../includes/homenew/footer.jsp"%>
</body>
</html>