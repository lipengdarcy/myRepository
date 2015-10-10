<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@ include file="../../../includes/home/header.jsp"%>
		<%@ include file="../../../includes/admin/dwzjs-header.jsp"%>
	
		<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/jquery-ui.css" /> 
		<script type="text/javascript" src="${ctx }/scripts/jquery-ui.js"></script> 
		<script type="text/javascript" src="${ctx }/scripts/jquery-ui-timepicker-addon.js"></script> 
		<script type="text/javascript" src="${ctx }/scripts/jqPagination/js/jquery.jqpagination.js"></script> 
		<script type="text/javascript">
			var proSelEvent=null;
			$(function(){
				var mapsrc = "${ctx}/map/togetpointmap.do";
				$("#selcoor-float-panel")
						.hsFloatPanel(
								{
									showPanelSel : "#sel-point-btn",
									left : 0,
									top : 40,
									beforeShowFunc : function(args) {
										$("#coor-panel-mask").css("display", "block");
										var coorval = $("#work_time").val();
										var coorpar = "";
										if (coorval) {
											coorpar = "point=" + coorval;
										}
										$("#selcoor-float-panel").find("iframe")[0].src = mapsrc
												+ "?" + coorpar;
									},
									beforeHiddenFunc : function(args) {
										$("#coor-panel-mask").css("display", "none");
										return true;
									}
								});
				//单击遮罩关闭坐标选择器
				$("#coor-panel-mask").bind("click", function(e) {
					$("#selcoor-float-panel .panel-close-btn").trigger("click");
				});
				
				$("#type").val($("#type_h").val());
				$("#placeid").val($("#placeid_h").val());
				
				$("#type").bind("change",function(e){
					var src=e.target;
					if($(src).val()=='1'){
						$("#placeid-tr").css("display","none");				
					}else{
						$("#placeid-tr").css("display","");
						$("#saler-pro-table-tr").remove();
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
				//////
				$(".del-prolist-item").bind("click",function(e){
					var src=e.target;
					var partr=$(src).closest("tr");
					var delId=partr.find("input[itemid]").val();
					if(delId && delId>0){
						delId=parseInt(delId,10);
						var oldval=$("#del-proids").val();
						oldval+=delId+",";
						$("#del-proids").val(oldval);
					}			
					partr.remove();
				});
			
				$("#add-prolist-item").bind("click",function(e){
					var item=$("#saler-prolist-tmp tr").clone(true);
					$("#saler-pro-table").append(item);
				});
				//初始化产品信息选择对话框
				$("#sel-proinfor-dlg").dialog({
					autoOpen:false,
					width:800,
					height:700,
					buttons: [
			          {
			            text: "确定",
			            click: function() {
			            	if(proSelEvent){
			            		fillSeledInfor(proSelEvent,nowDlgTriggerE);
			            		proSelEvent=null;
					             $( this ).dialog( "close" );
			            	}			            	
			            }
			          },
			          {
			            text: "取消",
			            click: function() {
			              $( this ).dialog( "close" );
			            }
			          }
			        ]
				});
				//prolist-item
				$(".prolist-item").bind("dblclick",function(e){
					fillSeledInfor(e,nowDlgTriggerE);
					$("#sel-proinfor-dlg").dialog( "close" );
				});
				//
				$(".prolist-item").bind("click",function(e){
					proSelEvent=e;
					var src=proSelEvent.target;
					var parTable=$(src).closest("table");
	        		var partr=$(src).closest("tr");
	        		$(parTable).find("tr.active").removeClass("active");
					partr.addClass("active");					
				});
				
				//选择产品信息按钮
				var nowDlgTriggerE=null;
				$(".proinfor-sel-trigger").bind("click",function(e){
					$("#sel-proinfor-dlg").dialog( "open" );
					nowDlgTriggerE=e
				});
				//
				$("#search-prolist-btn").bind("click",function(e){
					var keyform=$("#sel-prolist-keyword-form");
					var pageform=$("#sel-prolist-page-form");
					var senddata=$(keyform).serialize()+"&"+$(pageform).serialize();
					$.ajax({
						url: url+'admin/adminproduct.do?method=allProList',// 跳转到 action    
						data:senddata,
						type:'post',
						dataType:'json',
						success:function(data) {   
							if(data){
								$("#sel-proinfor-dlg .prolist-content table tbody").empty();
								var prolist=data.prolist;
								if(prolist){									
									for(var pi=0;pi<prolist.length;pi++){
										var proinfor=prolist[pi];
										var tablerow=$("#proinfor-table-tmp tr").clone(true);
										MyTools.initEleDate({
											eleContent:tablerow,
											dateSource:proinfor,
											preFix:"p-"
										});
										//如果有NC编号则设置该cell为只读
										if(proinfor.ncpronum){
											var ncNumEle=$(tablerow).find("[name='ncpronum']");
											ncNumEle.prop("readonly","true");
											ncNumEle.addClass("readonly-text")
										}
										$("#sel-proinfor-dlg .prolist-content table tbody").append(tablerow);
										$("#sel-proinfor-dlg [data-max-page]").attr("data-max-page",data.totalnum);
										//填写分页信息
										var maxpage=data.totalPage;										
										$('.pagination').jqPagination({
											current_page:"1",
											max_page:maxpage,
											page_string		: '页码 {current_page} 总数 {max_page}',
										    paged: function(page) {
										    	var pageform=$("#sel-prolist-page-form");
										    	pageform.find("[name=\"pageNum\"]").val(page);
										        $("#search-prolist-btn").trigger("click");				        
										    }
										});
									}
								}
							}
						},
						error : function() {
							alert("异常！请重新尝试或者联系管理员！");
						}    
					});
				});				
				
				//////
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
				var proList=$("#saler-pro-table").find(".prolist-item");
				for(var pi=0;pi<proList.length;pi++){
					var pro=proList[pi];
					var proinps=$(pro).find("input");
					for(var pii=0;pii<proinps.length;pii++){
						var proinp=proinps[pii];
						var name=proinp.name;
						if(name){
							name=name.replace("[n]","["+pi+"]");
							proinp.name=name;
						}
					}
				}
				//work-time-tr
				var wtTrs=$(".work-time-tr");
				for(var pi=0;pi<wtTrs.length;pi++){
					var pro=wtTrs[pi];
					var proinps=$(pro).find("input");
					for(var pii=0;pii<proinps.length;pii++){
						var proinp=proinps[pii];
						var name=proinp.name;
						if(name){
							name=name.replace("[n]","["+pi+"]");
							proinp.name=name;
						}
					}
				}
				var postdata=$form.serialize();
				$.ajax({
					url: url+'admin/area.do?method=saleaddressEdit',// 跳转到 action    
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
			
			function setCoor(point) {
				$("#work_time").val(point);
			}
			//单击确定按钮后填充选择的NC产品信息到表格
			function fillSeledInfor(proSelEvent,nowDlgTriggerE){
				var src=proSelEvent.target;
        		var partr=$(src).closest("tr");
				//partr.addClass("active");
				var ncnumEle=partr.find("[name=\"ncpronum\"]");
				var isRead=$(ncnumEle).prop("readonly");
				var jsonObj=MyTools.formToJsonObj({formCon:partr});
				if(!isRead){
					//保存信息到后台数据库
					var sendData="pid="+jsonObj["pid"]+"&ncpronum="+jsonObj["ncpronum"];
					$.ajax({
						url: url+'/admin/adminproduct.do?method=updateProOnly',// 跳转到 action    
						data:sendData,
						type:'post',
						dataType:'json',
						success:function(data) {    
							if(data.statusCode=="200"){
								var event=nowDlgTriggerE;
								if(event){
									var psrc=event.target;
									var selpartr=$(psrc).closest("tr");
									MyTools.initEleDate({
										eleContent:selpartr,
										dateSource:jsonObj,
										preFix:"p-"
									});
								}
							}else{
								alert("NC编号无法保存，填充失败。！");
							}							
						},
						error : function() {
							alert("异常！请重新尝试或者联系管理员！");
						}    
					});  
				}else{
					var event=nowDlgTriggerE;
					if(event){
						var psrc=event.target;
						var selpartr=$(psrc).closest("tr");
						MyTools.initEleDate({
							eleContent:selpartr,
							dateSource:jsonObj,
							preFix:"p-"
						});
					}
				}
				
			}
		</script>
		<style type="text/css">	
			.btn-ele{
				  cursor: pointer;
				  color:blue;
				  font-weight:bold;
			}
			.prolist-content{
			 	height:450px;
			 	overflow: auto;
			}
			.prolist-content table{
			 	width:630px;
			 	margin-top:16px;
				border-collapse: collapse;
		  		border: 1px solid gray;
			}
			.prolist-content table th{
		  		border: 1px solid gray;
			}
			.prolist-content table td{
		  		border: 1px solid gray;
			}
			.prolist-content table .prolist-item:hover{
			  	cursor: pointer;
				font-weight:bold;
			}
			.prolist-content table .prolist-item.active{
				font-weight:bold;
				color:red;
			}
			.prolist-table{
				margin-top:16px;
				border-collapse: collapse;
		  		border: 1px solid gray;
		  		width:700px;
			}
			.prolist-table td,.prolist-table th{
		  		border: 1px solid gray;
		  		text-align:center;
			}
			.ui-timepicker-div .ui-widget-header { margin-bottom: 8px;}
			.ui-timepicker-div dl { text-align: left; }
			.ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
			.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
			.ui-timepicker-div td { font-size: 90%; }
			.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
			.ui_tpicker_hour_label,.ui_tpicker_minute_label,.ui_tpicker_second_label,.ui_tpicker_millisec_label,.ui_tpicker_time_label{padding-left:20px}
			.gigantic.pagination {
			  margin: 30px 0;
			}
			.ui-widget-header {
			  border: 1px solid #C6E1F6;
			  color: #ffffff;
			  background: -webkit-gradient(linear, 0 0, 0 100%, from(rgb(198, 225, 246)), to(rgb(222, 232, 246)));
			  background-color: #f3f3f3;
			  background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgb(198, 225, 246)), color-stop(100%, rgb(222, 232, 246)));
			  background-image: -webkit-linear-gradient(rgb(198, 225, 246), rgb(222, 232, 246));
			  background-image: linear-gradient(rgb(198, 225, 246), rgb(222, 232, 246));
			  font-weight: bold;
			}
			.page-controller{
				  text-align: right;
			}
			.pagination {
			  display: inline-block;
			  border: 1px solid #CDCDCD;
			  border-radius: 3px;
			}
			.gigantic.pagination a {
			  height: 30px;
			  width: 30px;
			  font-size: 25px;
			  line-height: 25px;
			}
			.pagination a:first-child {
			  border: none;
			  border-radius: 2px 0 0 2px;
			}
			.pagination a {
			  display: block;
			  float: left;
			  width: 20px;
			  height: 20px;
			  outline: none;
			  border-right: 1px solid #CDCDCD;
			  border-left: 1px solid #CDCDCD;
			  color: #555555;
			  vertical-align: middle;
			  text-align: center;
			  text-decoration: none;
			  font-weight: bold;
			  font-size: 16px;
			  font-family: Times, 'Times New Roman', Georgia, Palatino;
			  /* ATTN: need a better font stack; */
			  background-color: #f3f3f3;
			  background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f3f3f3), color-stop(100%, lightgrey));
			  background-image: -webkit-linear-gradient(#f3f3f3, lightgrey);
			  background-image: linear-gradient(#f3f3f3, lightgrey);
			}
			.gigantic.pagination input {
			  width: 180px;
			  height: 30px;
			  font-size: 15px;
			}
			.pagination input {
			  float: left;
			  margin: 0;
			  padding: 0;
			  width: 120px;
			  height: 20px;
			  outline: none;
			  border: none;
			  vertical-align: middle;
			  text-align: center;
			}
			.dlg-search-text{
			  line-height: 25px;
			  height: 25px;
			  width: 230px;
			}
			.readonly-text{
			 border:0;
			 background-color: transparent;
			}
		</style>
		<title>区域工厂/门店-修改</title>
	</head>
<body>
<h2 class="contentTitle">${title }</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<input type="hidden" name="saleAddress.id" id="id_h" value="${s.id }" />
		<input type="hidden" name="prid" id="prid_h" value="${prid }" />
		<input type="hidden" id="type_h" value="${s.type }" />
		<input type="hidden" id="placeid_h" value="${s.placeid }" />
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr style="display:none;">
					<th>类型：</th>
					<td>
						<select id="type" name="saleAddress.type">
							<option value="1">门店</option>
							<option value="2">工厂</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>名称：</th>
					<td>
						<input id="name" name="saleAddress.name" type="text" value="${s.name }" />
					</td>
				</tr>
				<tr>
					<th>NC企业编号：</th>
					<td>
						<input id="pkcorp" name="saleAddress.pkcorp" type="text" value="${s.pkcorp }" />
					</td>
				</tr>
				<tr id="placeid-tr">
					<th>生产厂家：</th>
					<td>
						<select id="placeid" name="saleAddress.placeid">
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
							<div seled-name-show=".pshow-name-ele" cid="${cid }" min-layer="2"
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
						<input type="hidden" id="regionid" name="saleAddress.regionid" value=""></input>
						<input type="hidden" id="regionname" name="saleAddress.regionname" value=""></input>
					</td>
				</tr>				
				<tr>
					<th>地址：</th>
					<td>
						<input id="address" name="saleAddress.address" type="text" value="${s.address }" />
					</td>
				</tr>
				<tr>
					<th>联系人：</th>
					<td>
						<input id="contacts"  name="saleAddress.contacts" type="text" value="${s.contacts }" />
					</td>
				</tr>
				<tr>
					<th>联系电话：</th>
					<td>
						<input id="tel" name="saleAddress.tel" type="text" value="${s.tel }" />
					</td>
				</tr>
				<tr>
					<th>手机号码：</th>
					<td>
						<input id="mobile" name="saleAddress.mobile" type="text" value="${s.mobile }" />
					</td>
				</tr>
				<tr>
					<th>坐标：</th>
					<td>
						<input id="work_time" name="saleAddress.worktime" type="text" value="${s.workTime }" />  
							<a id="sel-point-btn" class="btn btn-success">地图选择</a>
							<div class="float-panel" id="selcoor-float-panel"
								style="height: 500px; width: 800px;">
								<div class="panel-close-btn">
									<span class="glyphicon glyphicon-remove"></span>
								</div>
								<div class="carinfor-content">
									<iframe
										style="border: 0; width: 800px; height: 460px; margin-top: 24px;"
										frameborder="no"
										src="${ctx}/map/togetpointmap.do?point=${store.worktime }">
									</iframe>
								</div>
							</div>
					</td>
				</tr>
				<c:if test="${!empty wtlist}">
					<c:forEach var="wtitem" items="${wtlist }"  varStatus="status">
						<c:if test="${wtitem.wttype==1 }">
							<tr class="work-time-tr">
								<th>工作日工作时间：</th>
								<td>
									星期<input class="weekday" name="workTimeList[n].wtweekbegin" type="text" value="${wtitem.wtweekbegin}" size="5" readonly="true"/>-
									星期<input class="weekday"  name="workTimeList[n].wtweekend" type="text" value="${wtitem.wtweekend}" size="5" readonly="true"/>:
									<input class="timepicker"  name="workTimeList[n].wtbegin" type="text" value="${wtitem.wtbegin}" size="5" readonly="true"/>-
									<input class="timepicker"  name="workTimeList[n].wtend" type="text" value="${wtitem.wtend}" size="5" readonly="true"/>
									<input name="workTimeList[n].wtid" type="hidden" value="${wtitem.wtid}" />
									<input name="workTimeList[n].wttype" type="hidden" value="${wtitem.wttype}" />
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${empty wtlist }">
					<tr class="work-time-tr">
						<th>工作日工作时间：</th>
						<td>
							星期<input class="weekday" name="workTimeList[n].wtweekbegin" type="text" value=""  readonly="true"/>-
							星期<input class="weekday"  name="workTimeList[n].wtweekend" type="text" value="" readonly="true"/>:
							<input class="timepicker"  name="workTimeList[n].wtbegin" type="text" value="" readonly="true"/>-
							<input class="timepicker"  name="workTimeList[n].wtend" type="text" value="" readonly="true"/>
							<input name="workTimeList[n].wttype" type="hidden" value="1" />
						</td>
					</tr>
				</c:if>				
				<tr>
					<th>描述：</th>
					<td>
						<textarea id="content" name="saleAddress.content" class="editor textInput" style="height:330px"
						upImgUrl="${ctx }/admin/uploadfile.do?method=xeupload"  upImgExt="jpg,jpeg,gif,png"  rows="8" cols="100" >${s.content }</textarea>
					</td>
				</tr>
				<tr id="saler-pro-table-tr">
					<th>允销目录</th>
					<td>
						<input type="hidden" name="delProids" id="del-proids" value=""/>
						<table id="saler-pro-table" class="prolist-table">						
							<th>产品NC编号</th><th>产品名称</th><th>产品单价</th><th>操作</th>
							<c:forEach var="proItem" items="${proList }">
								<tr class="prolist-item">
									<td><span pid>${proItem.ncpronum }</span>
										<input type="hidden" itemid name="proList[n].id" value="${proItem.id }">
										<input type="hidden" pid name="proList[n].pid" value="${proItem.ncpronum }">
									</td>
									<td><span pname>${proItem.name }</span><input type="hidden"  pname name="proList[n].pname" value="${proItem.name }"></input></td>
									<td><input type="text" price name="proList[n].price" value="${proItem.ncprice }" size="5">元</td>
									<td><span class="del-prolist-item btn-ele">删除</span></td>
								</tr>
							</c:forEach>
						</table>
						<div>
							<span class="btn-ele" id="add-prolist-item">添加+</span>
						</div>
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
</div>
<table id="saler-prolist-tmp" style="display:none;" >
	<tr class="prolist-item">
		<td><span p-ncpronum class="proinfor-sel-trigger btn-ele">选择产品</span>
			<input type="hidden" itemid name="proList[n].id" value="-1">
			<input type="hidden" p-ncpronum name="proList[n].pid" value="">
		</td>
		<td><span p-name></span><input type="hidden" p-name name="proList[n].pname" value=""></input></td>
		<td><input type="text" p-price name="proList[n].price" value="0" size="5">元</td>
		<td><span class="del-prolist-item btn-ele">删除</span></td>
	</tr>
</table>
<div id="area_form_success_ctx" class="pageContent" style="display:none;padding-left:20px;">
	<div style="color:green;margin:20px 0;"><h3>修改成功！</h3></div>
</div>
<div id="sel-proinfor-dlg">
	<div class="infor-content">
		<form id="sel-prolist-keyword-form">
			<div>
				<span><input type="text" class="dlg-search-text" name="keywords" value=""/></span>
				<span class="btn btn-success" id="search-prolist-btn">搜索</span>
			</div>
		</form>
		<div class="prolist-content">
			<table>
				<thead>
					<tr>
						<th>pid</td>
						<th>名称</td>
						<th>nc编号</td>
						<th>在售状态</td>	
					</tr>					
				</thead>
				<tbody></tbody>
			</table>
		</div>
		<div class="page-controller">
			<div class="pagination gigantic">
			    <a href="#" class="first" data-action="first">&laquo;</a>
			    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
			    <input type="text" readonly="readonly" data-max-page="${totalPage}" />
			    <a href="#" class="next" data-action="next">&rsaquo;</a>
			    <a href="#" class="last" data-action="last">&raquo;</a>
			</div>
			<form id="sel-prolist-page-form" style="display:none;">			
				<input type="text" name="pageNum" value="1"></input>
				<input type="hidden" name="numPerPage" value="20"></input>
			</form>
		</div>
	</div>	
</div>

<table id="proinfor-table-tmp" style="display:none;">
	<tr class="prolist-item">
		<td p-pid name="pid"></td>
		<td p-name name="name"></td>
		<td >
			<input type="text" p-ncpronum name="ncpronum" value=""></input>
		</td>
		<td p-state map-value="0:在售##1:已下架" name="state"></td>		
	</tr>
</table>

<!-- dwzjs需要的元素 -->
<div id="navTab" style="display:none;" >
	<ul class="navTab-tab">
	</ul>
</div>
<!-- dwzjs需要的元素end -->

</body>
</html>
