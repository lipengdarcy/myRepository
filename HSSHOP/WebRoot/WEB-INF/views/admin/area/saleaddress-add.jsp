<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域工厂/门店-新增</title>
<%@ include file="../../../includes/home/header.jsp"%>
<%@ include file="../../../includes/admin/dwzjs-header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/jquery-ui.css" /> 
<script type="text/javascript" src="${ctx }/scripts/jquery-ui.js"></script> 
<script type="text/javascript" src="${ctx }/scripts/jquery-ui-timepicker-addon.js"></script> 
<script type="text/javascript">
	var wtIndex=1;
	var nowWtCount=0;
	$(document).ready(function(e){
		$("#type").bind("change",function(e){
			var src=e.target;
			if($(src).val()=='1'){
				$("#placeid-tr").css("display","none");
			}else{
				$("#placeid-tr").css("display","");
			}
		});
		
		$("#type").trigger("change");
		
		$('.timepicker').timepicker({
			timeFormat: 'hh:mm' 	
		});
		
		$(".weekday").spinner({
			  min:1,
			  max:7,
			  step:1
		});
		
		$("#add-wt-tmp").find(".add-wt-item").bind("click",function(e){
			var src=e.target;
			var srcBtn=$(src).closest(".add-wt-item");
			srcBtn.css("display","none");
			
			var addWtTmpClone=$("#add-wt-tmp li").clone(true);
			$("#add-wt-times").append(addWtTmpClone);
			nowWtCount++;
			
			$('#add-wt-times .timepicker').timepicker({
				timeFormat: 'hh:mm' 	
			});
		});
		
		$("#add-wt-tmp").find(".del-wt-item").bind("click",function(e){
			var tlastLi=$("#add-wt-tmp li:last");
			if(nowWtCount==1){
				return;
			}
			var src=e.target;
			var srcRow=$(src).closest("li");
			srcRow.remove();
			nowWtCount--;	
			var lastLi=$("#add-wt-tmp li:last");
			var addBtn=$(lastLi).find(".add-wt-item");
			addBtn.css("display","block");	
		});
	});
	var saveForm= function(){
		var msg = validateForm();
		if(msg.length > 0){
			alert(msg);
			return ;
		}
		var $form = $("#area_form");
		
		var regionsName = $("#area1").find(".pshow-name-ele").text();//获取区域名称
		$("#regionname").val(regionsName);//
		var regionsID = $("#area1").find("[name='lastName']").val();//获取区域id
		$("#regionid").val(regionsID);//
		var postdata=$form.serialize();
		$.ajax({
			url: url+'admin/area.do?method=saleaddressAdd',// 跳转到 action    
			data:postdata,
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				}else if(data.state == 'exists'){
					alert("该工厂产地关系已存在，请不要重复添加！");
				}else{
					 if(data.msg){
						 alert(data.msg);
					 }else{
						 alert("异常！请重新尝试或者联系管理员！");
					 }
					
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}    
		});  

	}, validateForm = function(){
		var s = "";
		var $form = $("#area_form");
		var name = $.trim($form.find("#name").val());
		var address = $.trim($form.find("#address").val());
		if(name == ''){
			s += "名称不能为空！\n";
		}
		if(address == ''){
			s += "地址不能为空！\n";
		}
		return s;
	}, reloadForm = function(){
		location.reload();
	};
</script>
<style type="text/css">
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px;}
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	.ui_tpicker_hour_label,.ui_tpicker_minute_label,.ui_tpicker_second_label,.ui_tpicker_millisec_label,.ui_tpicker_time_label{padding-left:20px}
</style>
</head>
<body>
<h2 class="contentTitle">${title }</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr style="display:none;">
					<th>类型：</th>
					<td>
						<select id="type" name="type">
							<option value="1" <c:if test="${type==1 }">selected='selected'</c:if>>门店</option>
							<option value="2" <c:if test="${type==2 }">selected='selected'</c:if>>工厂</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>名称：</th>
					<td>
						<input id="name" name="name" type="text" value="" />
					</td>
				</tr>
				<tr>
					<th>NC企业编号：</th>
					<td>
						<input id="pkcorp" name="pkcorp" type="text" value="" />
					</td>
				</tr>
				<tr id="placeid-tr">
					<th>生产厂家：</th>
					<td>
						<select id="placeid"  name="placeid">
							<c:forEach var="ps" items="${placeList }" varStatus="status">
								<option value="${ps.attrvalueid }">${ps.attrvalue}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>区域：</th>
					<td>
						<!-- 区域选择 -->
						<div id="area1" class="cellCon">
							<div seled-name-show=".pshow-name-ele" cid="192224-212131-214785-0-0" min-layer="2"
							 max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel" style="position: relative;">
								<div>
									<div show-hs-area-sel="" class="btn btn-success">请选择</div>
									<span class="pshow-name-ele"></span>
								</div>
								<div class="area-float-panel float-panel">
									<div class="panel-close-btn"><span class="glyphicon glyphicon-remove"></span></div>
									<div role="tabpanel" class="area-tabs">
										<ul class="nav nav-tabs" role="tablist"></ul>
										<div class="tab-content"></div>
									</div>
									<div hidden-inputs-div=""></div>
								</div>
							</div>
						</div>
						<!-- 区域选择end -->
						<input type="hidden" id="regionid" name="regionid" value=""></input>
						<input type="hidden" id="regionname" name="regionname" value=""></input>
					</td>
				</tr>				
				<tr>
					<th>地址：</th>
					<td>
						<input id="address" name="address" type="text" value="" />
					</td>
				</tr>
				<tr>
					<th>联系人：</th>
					<td>
						<input id="contacts" name="contacts" type="text" value="" />
					</td>
				</tr>
				<tr>
					<th>联系电话：</th>
					<td>
						<input id="tel" name="tel" type="text" value="" />
					</td>
				</tr>
				<tr>
					<th>坐标：</th>
					<td>
						<input id="work_time" name="work_time" type="text" value="" /><span>填写方式为 精度,纬度 如 116.404,39.915</span>
					</td>					
				</tr>
				<tr>
					<th>工作时间设置：</th>
					<td>
						<ul id="add-wt-times">
							<li >
								星期<input class="weekday" name="workTimeList0.wtweekbegin" type="text" value="1"  size="5" readonly="true"/>-
								星期<input class="weekday"  name="workTimeList0.wtweekend" type="text" value="7" size="7" readonly="true"/>:
								<input class="timepicker" name="workTimeList0.wtbegin" type="text" value="8:00" size="5"  readonly="true"/>-
								<input class="timepicker"  name="workTimeList0.wtend" type="text" value="20:00" size="5" readonly="true"/>
								<input name="workTimeList0.wttype" type="hidden" value="1" />
							</li>
							<!--  <li>
								星期<input class="weekday" name="workTimeList1.wtweekbegin" type="text" value="6" size="5"  readonly="true"/>-
								星期<input class="weekday"  name="workTimeList1.wtweekend" type="text" value="7" size="5" readonly="true"/>:
								<input class="timepicker" name="workTimeList1.wtbegin" type="text" value="8:00" size="5"  readonly="true"/>-
								<input class="timepicker"  name="workTimeList1.wtend" type="text" value="20:00" size="5" readonly="true"/>
								<input name="workTimeList1.wttype" type="hidden" value="2" />
							</li>-->
						</ul>
					</td>
				</tr>
				<tr>
					<th>描述：</th>
					<td>
						<textarea id="content" name="content" class="editor textInput" style="height:330px"
						upImgUrl="${ctx }/admin/uploadfile.do?method=xeupload"  upImgExt="jpg,jpeg,gif,png"  rows="8" cols="100" ></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" >
						<button type="button" onclick="saveForm();" >提交</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<div style="display:none;" id="add-wt-tmp">
		<li >
			星期<input class="weekday" name="workTimeList[n].wtbegin" type="text" value=""  />-
			星期<input class="weekday"  name="workTimeList[n].wtend" type="text" value="" />:
			<input class="timepicker" name="workTimeList[n].wtbegin" type="text" value=""  readonly="true"/>-
			<input class="timepicker"  name="workTimeList[n].wtend" type="text" value="" readonly="true"/>
			<input name="workTimeList[n].wttype" type="hidden" value="1" />
			<span>
				<span class="button add-wt-item"><span>添加</span></span>
				<span class="button del-wt-item"><span>删除</span></span>
			</span>
		</li>
		
	</div>
</div>

<div id="area_form_success_ctx" class="pageContent" style="display:none;padding-left:20px;">
	<div style="color:green;margin:20px 0;"><h3>添加成功！</h3></div>
	<button type="button" onclick="reloadForm();" >继续添加</button>
</div>

	<!-- dwzjs需要的元素 -->
	<div id="navTab" style="display:none;" >
		<ul class="navTab-tab">
		</ul>
	</div>
	<!-- dwzjs需要的元素end -->

</body>
</html>
