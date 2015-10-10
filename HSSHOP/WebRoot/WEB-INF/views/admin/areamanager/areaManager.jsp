<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/includes/home/header.jsp"%>
	<title>行政区域管理</title>
	<script>
		$(document).ready(function(e){
			//变量声明
			var itemTmp="<li class='area-item-list'><span class='item-inner'></span></li>";
			var itemOpeButs="#item-operate-btns";
		    var tabContent="#area-tab-content";
		    var newTabId="new-tab-id";
		    var nowSaveUrl="";
		    var parItem=null;
		    //区域管理器的配置项
		    var areaVals = {
		            layerPreFix: "area-layer-",
		            maxLayer: 5,
		            getCitysUrl: url+"tool/city.do",
		            getProListUrl: url+"tool/province.do",
		            showEleSel: "#seled-name-show",
		            itemTmp: "<li><input type='checkbox' class='del-ids-box'><span class='item-inner'></span></li>",
		            afterWriteCookie: function (args) {
		            },
		            afetrClickItem: function (args) {
		            	 var event = args.event;
		            	 var src=event.target;
		            	 if($(src).is(".del-ids-box")){
		            		 return false;
		            	 }
		            	$(tabContent).hsAreaTabs("delTabs",newTabId);
		            },
		            lastItemClickFunc: function (args) {		            	
		            },
		            beWriteCookie: false
		        };	
		   	//生成区域管理器
			$(tabContent).hsAreaTabs(areaVals);
			//屏蔽 .area-item-list上的系统的右键菜单
			$(tabContent).on("contextmenu",".tab-pane",function(e){   
		         return false;   
		    });
			//响应 .area-item-list上的右键单击事件
			$(tabContent).on("mousedown",".area-item-list",function(e){
				if(3==e.which){
					var src=e.target;
					var liele=$(src).closest("li");
					var inele=liele.find(".item-inner");
					var itemInfor=$(inele).data("areaItem");
					//显示菜单所有条目
					$("#item-operate-btns .float-show-btn").closest("li").css("display","");
					//所有菜单条目生效
					$("#item-operate-btns .float-show-btn").removeAttr("disabled");
					//如果是最高层级不允许添加子级区域
					if(itemInfor.layer>=areaVals.maxLayer){
						$("#item-operate-btns .add-sub-btn").attr("disabled","disabled");
					}					
					//获取当前页签下被选中的区域
					var areaUl=$(src).closest(".tab-pane");
            		var chkboxs=areaUl.find("input.del-ids-box[type='checkbox']:checked");
            		if(chkboxs.length==0){
						$("#item-operate-btns .del-btn").attr("disabled","disabled");
					}
            		//显示右键菜单
					$(itemOpeButs).css("display","block");
					var top=$(liele).offset().top;
					var left=$(liele).offset().left;
					var lih=$(liele).height();
					$(itemOpeButs).css("top",top+lih+5+"px");
					$(itemOpeButs).css("left",left+8+"px");
					//将事件写入到右键菜单的data中
					$(itemOpeButs).data("listevent",e);
					return false;
				}
			});
			//响应 .tab-pane上的右键单击事件
			$(tabContent).on("mousedown",".tab-pane",function(e){
				if(3==e.which){
					var src=e.target;
					//获取选中项
					var areaUl=$(src).closest(".tab-pane");
            		var chkboxs=areaUl.find("input.del-ids-box[type='checkbox']:checked");
            		//
					$("#item-operate-btns .float-show-btn").closest("li").css("display","none");
					$("#item-operate-btns .del-btn").removeAttr("disabled");
					$("#item-operate-btns .del-btn").closest("li").css("display","");
					if(chkboxs.length==0){
						$("#item-operate-btns .del-btn").attr("disabled","disabled");
					}
					$(itemOpeButs).css("display","block");
					var top=e.pageY;
					var left=e.pageX;
					$(itemOpeButs).css("top",top+1+"px");
					$(itemOpeButs).css("left",left+1+"px");
					$(itemOpeButs).data("listevent",e);
					return false;
				}
			});
			//窗口单击隐藏自制的菜单
			$(window).bind("click",function(e){
				var src=e.target;
				var liele=$(src).closest(itemOpeButs);
				if(liele.length==0){
					$(itemOpeButs).css("display","none");
				}
			});
			//悬浮窗口
			var winh=$(window).height();
			var winw=$(window).width();
			var panelh=$("#operate-float-panel").height();
			var panelw=$("#operate-float-panel").width();
	        $("#operate-float-panel").hsFloatPanel({
	            top: 100,
	            left:winw/2-panelw/2,
	            showPanelSel: ".float-show-btn",
	            isModel:true,
	            afterHiddenFunc: function (targs) {
	            },
	            beforeShowFunc: function (targs) {
	            	$(itemOpeButs).css("display","none");
	            	var event=targs.event;
	            	var src=event.target;
	            	var btn=$(src).closest(".float-show-btn");
	            	$("#operate-float-panel").find("form").empty();
	            	//
	            	var menue=$(itemOpeButs).data("listevent");
	            	var msrc=null;
	            	var liele=null;
					var inele=null;
					var itemInfor=null;
	            	if(menue){
	            		msrc=menue.target;
	            		liele=$(msrc).closest(".area-item-list");
						inele=liele.find(".item-inner");
						itemInfor=$(inele).data("areaItem");
	            	}
					//
					if(btn.is(".add-btn")){
	            		nowSaveUrl="${ctx }/admin/areamanager.do?method=addNewItem";
	            		var cloneTmp=$("#oprate-tmp .add-tmp").clone(true);
	            		$("#operate-float-panel").find("form").append(cloneTmp);
	            		//
						if(itemInfor.layer>1){
							var inowl=parseInt(itemInfor.layer, 10);
							var tabA=$("[href=\"#area-layer-"+(inowl-1)+"\"]");
							parItem=tabA.data("areaItem");
							$(cloneTmp).find("[datafield='name']").html(parItem.name);
							$(cloneTmp).find("[datafield='id']").val(parItem.id);							
						}
	            		
	            	}else if(btn.is(".add-sub-btn")){
	            		nowSaveUrl="${ctx }/admin/areamanager.do?method=addNewItem";
	            		var cloneTmp=$("#oprate-tmp .add-tmp").clone(true);
	            		$("#operate-float-panel").find("form").append(cloneTmp);
	            		//
						if(itemInfor.layer>0){						
							parItem=itemInfor;
							$(cloneTmp).find("[datafield='name']").html(parItem.name);
							$(cloneTmp).find("[datafield='id']").val(parItem.id);							
						}	            		
	            	}else if(btn.is(".up-btn")){
	            		nowSaveUrl="${ctx }/admin/areamanager.do?method=editItem";
	            		var cloneTmp=$("#oprate-tmp .up-tmp").clone(true);
	            		$("#operate-float-panel").find("form").append(cloneTmp);
						//
						$(cloneTmp).find("[datafield='name']").html(itemInfor.name);
						$(cloneTmp).find("[datafield='id']").val(itemInfor.id);
	            		
	            	}else if(btn.is(".del-btn")){
	            		nowSaveUrl="${ctx }/admin/areamanager.do?method=delItem";
	            		var cloneTmp=$("#oprate-tmp .del-tmp").clone(true);
	            		$("#operate-float-panel").find("form").append(cloneTmp);
	            		var areaUl=$(msrc).closest(".area-ul");
	            		if(areaUl.length==0){
	            			areaUl=$(msrc).find(".area-ul");
	            		}
	            		var chkboxs=areaUl.find("input.del-ids-box[type='checkbox']:checked");
	            		var names="";
	            		var ids="0"
	            		parItem=null;	
	            		for(var chki=0;chki<chkboxs.length;chki++){
	            			var chkbox=chkboxs[chki];
	            			var itemEle=$(chkbox).closest("li").find(".item-inner");
	            			var itemEleInfor=itemEle.data("areaItem");
	            			names+="<span class='del-item-name'>"+itemEleInfor.name+"</span>";
	            			ids+=","+itemEleInfor.id;
	            			if(!parItem){
	            				if(itemEleInfor.layer>1){
	    							var inowl=parseInt(itemEleInfor.layer, 10);
	    							var tabA=$("[href=\"#area-layer-"+(inowl-1)+"\"]");
	    							parItem=tabA.data("areaItem");							
	    						}
	            			}
	            		}
	            		//
						$(cloneTmp).find("[datafield='name']").html(names);
						$(cloneTmp).find("[datafield='ids']").val(ids);		            		
	            	}
	            },
	            afterShowFunc: function (targs) {
	            },
	            beforeHiddenFunc:function(targs){
	                return true;
	            }	            
	        });	       
	        
	        $("#save-btn").bind("click",function(e){
	        	var postdata=$(".area-ope-form").serialize();
	        	$.ajax({
	        		url:nowSaveUrl,
	        		type:"post",
	        		data:postdata,
	        		dataType:"json",
	        		async:false,
	        		success:function(rsdata,status){
	        			if(rsdata.status=='success'){
	        				$("#operate-float-panel").hsFloatPanel("hiddenPanel",{
	        					panelSel:$("#operate-float-panel"),
	        					isModel:true
	        				});	        				
	        				var msgObjList=rsdata.msg;
	        				var menue=$(itemOpeButs).data("listevent");	            		
                    		var msrc=menue.target.closest(".area-item-list");
	        				if(rsdata.method=="add"){
	        					if(parItem){
	        						$(".tab-content [idflag='"+parItem.id+"']").trigger("click");
	        					}
	        					parItem=null;	        					
	        				}else if(rsdata.method=="edit"){	        					
	        					var menue=$(itemOpeButs).data("listevent");
	        	            	if(menue){
	        	            		msrc=menue.target;
	        	            		var liele=$(msrc).closest(".area-item-list");
	        						var inele=liele.find(".item-inner");
	        						$(inele).data("areaItem",rsdata.msg);
	        						inele.html(rsdata.msg.name);
	        	            	}
	        				}else if(rsdata.method=="del"){
	        					var delIds=$("#operate-float-panel").find("[name=\"ids\"]").val();
	        					if(parItem){
	        						$(".tab-content [idflag='"+parItem.id+"']").trigger("click");
	        					}else {
	        						var targs=$.extend({},areaVals,{layer: 1});
	        						 $(tabContent).hsAreaTabs("loadAreaInfor", targs);
	        					}  
	        					parItem=null;	
	        				}	        				
	        			}else{
	        				alert(rsdata.msg);
	        			}	        			
	        		},
	        		error:function(shr,errinfor,ex){
	        			
	        		}
	        	});
	        });
	        $("#cancle-btn").bind("click",function(e){
	        	$("#operate-float-panel").hsFloatPanel("hiddenPanel",{
					panelSel:$("#operate-float-panel"),
					isModel:true
				});
	        });
	        $("#oprate-tmp .add-new-item-btn").bind("click",function(e){
	        	var src=e.target;
	        	var parele=$(src).closest(".newitem");
	        	var itemTmp=$("#oprate-tmp .add-new-item-tmp").clone(true);
	        	$(parele).append(itemTmp);
	        });
	        $("#oprate-tmp .add-new-item-tmp .del-new-item-btn").bind("click",function(e){
	        	var src=e.target;
	        	var parele=$(src).closest(".add-new-item-tmp");	        	
	        	$(parele).remove();
	        });
		});		
	</script>
	<style>
		.menu-context-ul{
			position:absolute;
			border:1px solid lightgray;
			background-color:white;
			box-shadow: 1px 2px 5px rgba(0,0,0,0.2);
  			overflow:hidden;
  			display:none;
		}
		.menu-context-ul-show{
			height:105px;
		}
		.menu-context-ul li{
			display:block;
			list-style-type:none;
			margin:0px 5px;
		}
		.area-name-show{
			height:20px;		
		}
		.area-ope-form{
			height: 180px;
  			
		}
		.area-ope-form label{
			color:black;
			font-weight:bold;
			width:10em;
			display:inline-block;
		}
		.area-ope-btns{
		  padding-top: 16px;
		  border-top: 1px solid lightgray;
		  text-align: right;			 
		}
		.form-control{
			width:230px;
			line-height:23px;
			height:23px;
		}
		.area-name-show{
			color:black;
		}
		.title-bar{
			 border-bottom: 1px solid lightgray;
			 margin-top: -5px;
			 padding-bottom: 3px;
			 margin-bottom: 12px;
		}
		.float-panel {
		  position: absolute;
		  width: 450px;
		  height: 230px;
		  background-color: white;
		  border: 1px solid rgb(162, 162, 162);
		  box-shadow: 3px 3px 10px rgba(0,0,0,0.2);
		  padding: 20px;
		  display: none;
		  overflow: auto;
		  opacity: 0;
		  z-index: 999;
		}
		.content-panel{
			height: 130px;
  			overflow: auto;
		}
		.del-ids-box{
			margin-right:8px;
		}
		.del-item-name{
			display:inline-block;
			padding:3px 5px;
		}
		.note-infor{
			color:red;
			font-weight:bold;
		}
		.card-content{
			padding: 20px 20px 0px 20px;
		}
		.note-title{
			font-size:15px;
			font-weight:bold;
		}
	</style>
</head>
<body>
	<div class="card-content">
		<div class="note-title">使用说明:</div>
		<div>
			<ol>
				<li><span class="note-infor">添加同级行政区域:</span>在页签内的某个条目上单击右键，会弹出右键菜单，选择添加同级区域条目,弹出添加区域窗口，填写区域名称即可，可以添加多条信息</li>
				<li><span class="note-infor">添加子级行政区域:</span>在页签内的某个条目上单击右键，会弹出右键菜单，选择添加子级区域条目，弹出添加区域窗口，填写区域名称，可以添加多条区域信息，这些区域的父区域为被单击的区域</li>
				<li><span class="note-infor">修改区域名称:</span>在页签内需要修改名称的条目上单击右键，会弹出右键菜单，单击修改本区域，弹出修改区域按钮，填写修改后的区域名称，保存提交</li>
				<li><span class="note-infor">删除行政区域:</span>单击多选框，选中需要删除的条目，在页签内任意区域单击右键，弹出右键菜单，单击删除选中条目，弹出删除确认按钮，单击保存，删除相应的条目及其子条目</li>
			</ol>
		</div>
	</div>
	<div id="seled-name-show" class="area-name-show" style="display:none;"></div>
	<div style="padding:15px;">
		<div style="border:1px solid lightgray;padding:15px;" role="tabpanel" id="area-tab-content">
	         <ul class="nav nav-tabs" role="tablist">
	         </ul>
	         <div class="tab-content">
	         </div>
    	</div>
	</div>
	<div id="oprate-tmp" style="display:none;">
		<div class="add-tmp">
			<div class="title-bar"><span class="title-text">添加行政区域</span></div>
			<div class="content-panel">
				<div class="paritem">
				<label>上级区域名称:</label>
				<span class="area-name-show" datafield="name">无</span></div>
				<input type="hidden" name="parid" datafield="id"  value="0">
				<div class="newitem">
					<label>添加的区域名称为:</label>
					<div class="fixed-new-item-tmp">
						<input type="text" name="areaname" value="" class="form-control">
						<span class="btn btn-link add-new-item-btn">添加</span>	
					</div>
				</div>
			</div>
			
		</div>
		<div class="up-tmp">
			<div class="title-bar"><span class="title-text">修改行政区域</span></div>
			<div>
				<label>本级区域名称:</label><span class="area-name-show" datafield="name"></span>
				<input type="hidden" name="id" datafield="id"  value="0">
			</div>
			<div>
				<label>本级区域修改为:</label><input type="text" name="areaname" value="" class="form-control">
			</div>
		</div>
		<div class="del-tmp">
			<div class="title-bar"><span class="title-text">删除行政区域</span></div>
			<div><span class="note-infor">只包含当前页签下选中的区域。被删除的区域的子区域也会删除，且不可恢复！</span></div>
			<div><label>你确定删除以下区域:</label><span class="area-name-show" datafield="name"></span></div>
			<div>
				<input type="hidden" name="ids" datafield="ids"  value="0">
			</div>
		</div>
		<div class="add-new-item-tmp">
			<input type="text" name="areaname" value="" class="form-control">
			<span class="btn btn-link add-new-item-btn">添加</span>
			<span class="btn btn-link del-new-item-btn">删除</span>		
		</div>
	</div>
	<ul class="menu-context-ul" id="item-operate-btns">
		<li><span class="btn btn-link add-btn float-show-btn">添加同级区域</span></li>
		<li><span class="btn btn-link add-sub-btn float-show-btn">添加子区域</span></li>
		<li><span class="btn btn-link up-btn float-show-btn">修改本区域</span></li>
		<li><span class="btn btn-link del-btn float-show-btn">删除选中区域</span></li>
		<li style="clear:both;"></li>
	</ul>
	<div class="float-panel" id="operate-float-panel">
        <div class="panel-close-btn"><span class="glyphicon glyphicon-remove"></span></div>
        <div class="operate-infor-content">
            <form class="area-ope-form">
            </form>
            <div class="area-ope-btns">
            	<span class="btn btn-danger" id="save-btn">保存</span>
            	<span class="btn btn-danger" id="cancle-btn">取消</span>
            </div>
        </div>
	</div>
</body>
</html>
