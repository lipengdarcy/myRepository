<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域工厂/门店-修改</title>
<%@ include file="../../../includes/home/header.jsp"%>
<%@ include file="../../../includes/admin/dwzjs-header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/jquery-ui.css" /> 
<script type="text/javascript" src="${ctx }/scripts/jquery-ui.js"></script> 
<script type="text/javascript" src="${ctx }/scripts/jquery-ui-timepicker-addon.js"></script> 
<script type="text/javascript" src="${ctx }/scripts/jqPagination/js/jquery.jqpagination.js"></script> 
<script type="text/javascript" src="${ctx }/scripts/myui/hsDlg.js"></script> 
<script type="text/javascript">
	$(function(){
		$('.refresh-btn').bind("click",function(e){
			 location.reload();
		});
		
		$('.pagination').jqPagination({
			current_page:"${currentPage}",
			page_string		: '页码 {current_page} 总数 {max_page}',
		    paged: function(page) {
		        // do something with the page variable
		        var toUrl="${ctx }/admin/area.do?method=toSalerefregionsEdit&saleid=${saleid }&type=2&pageNum="+page;
		        window.location.href=toUrl;
		        
		    }
		});
		$("#placeid").val($("#placeid_h").val());
		var palceText=$("#placeid  option:selected").text();
		$("#placeid_show").html(palceText);
		
		var args = HsAreaUser.defVals;
		HsAreaUser.defVals.roleSel="[role='hs-area-sel-no-init']";

		var tidlast=0;
    	var areaRoles=$(".region-list-table").find(HsAreaUser.defVals.roleSel);
    	for(var ai=0;ai<areaRoles.length;ai++){
    		var areaRole=areaRoles[ai];
    		var targs=$.extend({},args,{areaRole:areaRole});
        	HsAreaUser.initAreaSel(targs,tidlast,ai);
        	tidlast++;
    	}
    	
		//
		$(".del-refrow-btn").bind("click",function(e){
			var src=e.target;
			var delRowBtn=$(src).closest(".del-refrow-btn");
			
			if(delRowBtn.is("[delid]")){
				HsDlg.confirmDlg({
					text:"你确定要删除该条目？",
					okFunction:function(that){
						var said=$("#id_h").val();
						var delId=delRowBtn.attr("delid");
						$.ajax({
							url:"${ctx }/admin/area.do?method=delSealAddrRefRegionInfor&saleid="+said+"&delid="+delId,
							dadaType:"json",
							success:function(rsdata,staus){
								if(rsdata.status=='ok'){
									var srcRow=$(src).closest("tr");
									srcRow.remove();
								}else{
									
								}
								
							},
							error:function(xhr,errinfor,ex){
								
							}
						})
					}
				});
				
			}else{
				var srcRow=$(src).closest("tr");
				srcRow.remove();
			}
			
		});
		
		
		//增加新的一行 
		$(".add-region-table-row").bind("click",function(e){
			var cloneRow=$("#region-item-tmp tr").clone(true);
			$(".region-list-table").append(cloneRow);
			
			var args = HsAreaUser.defVals;

	    	var areaRole=$(cloneRow).find(HsAreaUser.defVals.roleSel);
	    	var targs=$.extend({},args,{areaRole:areaRole});
	    	HsAreaUser.initAreaSel(targs,tidlast,tidlast);
	    	tidlast++;

			
		});
	});

	function saveForm(){
		var $form = $("#area_form");
		var regNameHidden=$("#area_form .pshow-name-ele-hidden");
		var regnameTexts=$("#area_form .pshow-name-ele");
		for(var ri=0;ri<regnameTexts.length;ri++){
			var regnameText=regnameTexts[ri];
			var text=$(regnameText).html();
			regNameHidden[ri].value=text;
		}
		//var regionsName = $("#area1").find(".pshow-name-ele").text();//获取区域名称
		//$("#area1").find(".pshow-name-ele-hidden").val(regionsName);//
		var postdata=$form.serialize();
		$.ajax({
			url: url+'admin/area.do?method=salerefregionsEdit',// 跳转到 action    
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
					alert("异常！请重新尝试或者联系管理员！");
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}    
		});  

	}
</script>
<style type="text/css">	
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px;}
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	.ui_tpicker_hour_label,.ui_tpicker_minute_label,.ui_tpicker_second_label,.ui_tpicker_millisec_label,.ui_tpicker_time_label{padding-left:20px}
	.pshow-name-ele{
		display:inline-block;
		width:400px;
	}
	.show-infor-span{
		display:block;
		padding-bttom:3px;
		border-bottom:1px solid lightgray;
	}
	.region-list-table{
	 
		
	}
	.label-th{
		width:5em;
	}
	[show-hs-area-sel]{
		height: 24px;
	  	display: inline-block;
	  	line-height: 24px;
	}
	.gigantic.pagination {
  margin: 30px 0;
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
.region-name-span{
	display:inline-block;
	width:500px;
}
.ui-dialog {
  opacity: 1;
  -webkit-transform: scale(1); 
  transform: scale(1); 
  -webkit-transition: -webkit-transform .15s ease-in-out, opacity .15s ease-in-out; 
  transition: transform .15s ease-in-out, opacity .15s ease-in-out; 
 
}
 .ui-dialog-title {
   padding: 3px;
  }
</style>
</head>
<body>
<h2 class="contentTitle">增加区域关联</h2>
	
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<input type="hidden" name="id" id="id_h" value="${s.id }" />
		<input type="hidden" id="type_h" value="${s.type }" />
		<input type="hidden" id="placeid_h" value="${s.placeid }" />
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr>
					<th class="label-th">名称：</th>
					<td>
						<span class="show-infor-span">${s.name }</span>
					</td>
					<th class="label-th">名称：</th>
					<td>
						<span class="show-infor-span" id="placeid_show"></span>
					</td>
				</tr>
				<tr id="placeid-tr" style="display:none;">
					<th class="label-th">生产厂家：</th>
					<td colspan='3'>
						<select id="placeid" name="placeid">
							<c:forEach var="ps" items="${placeList }" varStatus="status">
								<option value="${ps.attrvalueid }">${ps.attrvalue}</option>
							</c:forEach>
						</select>
					</td>
				</tr>		
				
				<tr>
					<th class="label-th">联系人：</th>
					<td>
						<span class="show-infor-span">${s.contacts }</span>
					</td>
					<th class="label-th">联系电话：</th>
					<td>
						<span class="show-infor-span">${s.tel }</span>
					</td>
				</tr>
				<tr>
					<th class="label-th">地址：</th>
					<td colspan='3'>
						<span class="show-infor-span">${s.address }</span>
					</td>
				</tr>
				<tr>
					<th>区域：</th>
					<td colspan='3'>
						<table class="region-list-table">
						<c:forEach var="regionItem" items="${refProRegionList }">
							<tr class="price-table-row">
								<td>
									<span class="region-name-span">${regionItem.regionsName }</span>
								</td>
								<td class="center-td"><span delid="${regionItem.id }" class="table-button space-span del-refrow-btn">删除</span></td>
							</tr>
						</c:forEach>														
						</table>						
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan='3'><span class="table-button add-region-table-row">+增加区域关联</span></td>
				</tr>
				<tr>
					<td colspan="4" class="right-td">
						<span class="btn btn-default" onclick="saveForm();">提交</span>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<div class="pagination gigantic">
	    <a href="#" class="first" data-action="first">&laquo;</a>
	    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
	    <input type="text" readonly="readonly" data-max-page="${totalPage}" />
	    <a href="#" class="next" data-action="next">&rsaquo;</a>
	    <a href="#" class="last" data-action="last">&raquo;</a>
	</div>
</div>
<table id="region-item-tmp" style="display:none;">
	<tr class="price-table-row">
		<td>
			<div class="cellCon region-name-span">
				<div seled-name-show=".pshow-name-ele" cid="${cid }" min-layer="2"
					max-layer="5" input-sel="[hidden-inputs-div]" role="hs-area-sel-no-init" style="position: relative;">
					<div>
						<div show-hs-area-sel="" class="btn btn-link">请选择</div>
						<span class="pshow-name-ele"></span>
						<input class="pshow-name-ele-hidden" type="hidden" name="regions_name" value="">
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
		</td>
		<td class="center-td"><span class="table-button space-span del-refrow-btn">删除</span></td>
	</tr>
</table>

<div id="area_form_success_ctx" class="pageContent" style="display:none;padding-left:20px;">
	<div style="color:green;margin:20px 0;"><h3>修改成功！</h3></div>
	<div><span class="btn btn-danger refresh-btn">返回</span></div>
</div>

	<!-- dwzjs需要的元素 -->
	<div id="navTab" style="display:none;" >
		<ul class="navTab-tab">
		</ul>
	</div>
	<!-- dwzjs需要的元素end -->

</body>
</html>
