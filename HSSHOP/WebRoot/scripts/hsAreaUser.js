/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function HsAreaUser() {

}


HsAreaUser.defVals = {
    roleSel: "[role='hs-area-sel']",
    ////////////////////
    floatPanelSel: ".area-float-panel",
    showPanelSel: "[show-hs-area-sel]",
    ////////
    areaTabsSel: ".area-tabs",
    cidAttr: "cid",
    inputSelAttr: "input-sel",
    writeToCookieAttr: "write-to-cookie",
    maxLayerAttr: "max-layer",
    showEleSel: "seled-name-show",
    //修改下面的地址为实际的地址
    getCitysUrl: url+"tool/city.do",
    getProListUrl: url+"tool/province.do"
}

HsAreaUser.initAreaSel=function(args,idLast,index){
    var areaVals = {
            layerPreFix: "area-layer-",
            maxLayer: 5,
            getCitysUrl: args.getCitysUrl,
            getProListUrl: args.getProListUrl,
            showEleSel: args.showEleSel,
            afterWriteCookie: function (args) {
            },
            afetrClickItem: function (args) {            	
				var event = args.event;
                var src = event.target;
				var $parent = $(src).parents(".cellCon");
                var itemId = args.itemId;
                $parent.find("[name='lastName']").val(itemId);
                //$("#lastName").val(itemId);
                var layer = args.layer;
                var cookieAttrs = $.fn.hsAreaTabs.defVal.cookieItemAttrs;
                for (var li = layer; li < cookieAttrs.length; li++) {
                    var tipId = cookieAttrs[li];
                    //$parent.find("#" + tipId).val("");
                    $parent.find("[name='" + tipId+"']").val("");
                }
                //向隐藏控件填写选择的区域数据
                var ipId = $.fn.hsAreaTabs.defVal.cookieItemAttrs[layer - 1];
                //$("#" + ipId).val(itemId);
                $parent.find("[name='" + ipId+"']").val(itemId);
                
                var seler = $(src).closest(HsAreaUser.defVals.roleSel);
                $(seler).attr("now-layer",layer)
                if (seler.is("[cid]")) {
                    var attrid = seler.attr("cid");
                    var tspStrs = attrid.split("-");
                   
                    if (tspStrs.length >= layer) {
                        tspStrs[layer - 1] = itemId;
                        for (var layi = layer; layi < tspStrs.length; layi++) {
                            tspStrs[layi] = "0";
                        }
                    }
                    var attrVal = "";
                    for (var atri = 0; atri < tspStrs.length; atri++) {
                        attrVal = attrVal + tspStrs[atri] + "-";
                    }
                    attrVal += "0";

                    seler.attr("cid", attrVal);

                }               
            },
            lastItemClickFunc: function (args) {
                var event = args.event;
                if (typeof (event) === 'undefined') {
                    return;
                }
				var src = event.target;
				var $parent = $(src).parents(".cellCon");

				var $floatPanelSel = $(floatPanelSel);
				if($parent.find(floatPanelSel).length > 0)
					$floatPanelSel = $parent.find(floatPanelSel);
				
				
				$(".panel-close-btn").click();

                var item = null;
                if ($(src).is(".area-item-list")) {
                    item = $(src).find(".item-inner");
                } else {
                    item = $(src).closest(".item-inner");
                }

                var itemdate = item.data("areaItem");
				var itemId = itemdate["id"];
                $parent.find("#lastName").val(itemId);
				if(HsAreaUser.lastItemClickCallbackFun){
					var callbackFunName = HsAreaUser.lastItemClickCallbackFun;
					eval(callbackFunName+"("+itemId+");");
				}
				
            },
            beWriteCookie: true
        };

        var areaRole = args.areaRole;
        $(areaRole).css("position", "relative");
        var floatPanelSel = $(areaRole).find(args.floatPanelSel);
        var showBtn = $(areaRole).find(args.showPanelSel);
        var outH = $(showBtn).outerHeight();
        $(floatPanelSel).hsFloatPanel({
            top: outH + 2,
            showPanelSel: showBtn,
            afterHiddenFunc: function (targs) {
                $("#add-hs-tabs").removeClass("downrow");
            },
            beforeShowFunc: function (targs) {
            	if($("#region-item-tmp").length>0){
            		 //float-panel-id
                    var te=targs.event;
                    var tesrc=te.target;
                    var srcPanel=$(tesrc).closest(".cellCon");
                    var tpanelSel = srcPanel.find(args.floatPanelSel);
                    targs.panelSel=tpanelSel;	
            	}
            	
                var cookie = $.cookie("allarea");
                if (cookie!=null) {
                    return;
                }
                var panelSel = targs.panelSel;
                if (panelSel) {
                    var parRole = $(panelSel).closest(HsAreaUser.defVals.roleSel);
                    if ($(parRole).is("[cid]")) {
                        var tcid = $(parRole).attr("cid");
                        if (tcid) {
                            if (tcid.lastIndexOf("0") !== (tcid.length - 1)) {
//                                tcid += "0";
                            }
                            var tspStrs = tcid.split("-");
                            var tlargs = { spStrs: tspStrs };
                            tlargs = $.extend({}, $(parRole).data("areaVals"), tlargs);
                            var parTabSel = $(parRole).find(args.areaTabsSel);
                            $(parTabSel).hsAreaTabs("loadAreaTabs", tlargs);
                        }
                    }
                }
            },
            afterShowFunc: function (targs) {
                $("#add-hs-tabs").addClass("downrow");
            },
            beforeHiddenFunc:function(targs){
            	
            	 
            	if($("#region-item-tmp").length>0){
           		 //float-panel-id
                   var te=targs.event;
                   var tesrc=te.target;
                   var srcPanel=$(tesrc).closest(".cellCon");
                   var tpanelSel = srcPanel.find(args.floatPanelSel);
                   targs.panelSel=tpanelSel;	
            	}
            	var layer = targs.layer;
            	var panelSel = targs.panelSel;
                if (panelSel) {
                    var parRole = $(panelSel).closest(HsAreaUser.defVals.roleSel);
                    if ($(parRole).is("[min-layer]")) {
                        var sminlayer = $(parRole).attr("min-layer");
                        var iminlayer=parseInt(sminlayer,10);
                        if(!isNaN(iminlayer)){
                        	var nowLayer=$(parRole).attr("now-layer");
                        	 nowLayer=parseInt(nowLayer,10);
                        	 if(!isNaN(nowLayer)){
                        		 if(nowLayer<iminlayer){
                        			 alert("你最少需要选择"+iminlayer+"级")
                        			 return false;
                        		 }
                        	 }else{
                        		 return true;
                        	 }
                        	
                        }
                    }
                }
                var itemId=$(panelSel).find("[name='lastName']").val();
                if(itemId){
                	if(HsAreaUser.beforeHiddenFuncCallbackFun){
     					var callbackFunName = HsAreaUser.beforeHiddenFuncCallbackFun;
     					eval(callbackFunName+"("+itemId+");");
     				}
                }
                
                return true;
            }
            
        });


        areaVals.layerPreFix = index + "-" + areaVals.layerPreFix;
        $(areaRole).data("areaVals", areaVals);
        ////////////////////////////////////////////
        if ($(areaRole).is("[" + args.writeToCookieAttr + "]")) {
            areaVals.beWriteCookie = true;
            areaVals.afterWriteCookie = function (targs) {
            	//区域改变，更新缓存的菜单信息
            	$.ajax({
    				url : url + 'product/refreshMenu.do?',
    				type : 'get',
    				dataType : 'json',
    				success : function(data) {
    					location.reload(true);
    					//alert(data.content);
    				}
    				
    			});
    			

                //location.reload(true);
                //价格与之前的不一致，提示用户

            };
        } else {
            areaVals.beWriteCookie = false;
        }
        ///////////////////////////////////////////////////////
        if ($(areaRole).is("[" + args.showEleSel + "]")) {
            if ($(areaRole).attr(args.showEleSel)) {
                areaVals.showEleSel = $(areaRole).attr(args.showEleSel);
            }
        }
		
        if ($(areaRole).is("[" + args.maxLayerAttr + "]")) {
            if ($(areaRole).attr(args.maxLayerAttr)) {
                areaVals.maxLayer = $(areaRole).attr(args.maxLayerAttr);
            }
        }
        var areaTabsSel = $(areaRole).find(args.areaTabsSel);
        $(areaTabsSel).hsAreaTabs(areaVals);

        //循环加载区域信息
        var cid = null;
        if (areaVals.beWriteCookie === true) {
            cid = $.cookie($.fn.hsAreaTabs.defVal.allAreaCookie);
        }
        if (!cid) {
            if ($(areaRole).is("[" + args.cidAttr + "]")) {
                cid = $(areaRole).attr(args.cidAttr);
            }
        }
		
        if (cid) {
            var cspStrs = cid.split("-");
            var largs = { spStrs: cspStrs };
            largs = $.extend({}, areaVals, largs);
            $(areaTabsSel).hsAreaTabs("loadAreaTabs", largs);
        }

        ////////////
        if ($(areaRole).is("[" + args.inputSelAttr + "]")) {
            var ipAttrVal = $(areaRole).attr(args.inputSelAttr);
            var strLast="";
            if(idLast!=0){
            	strLast=idLast;
            }
            if (ipAttrVal) {
                var ipContent = $(areaRole).find(ipAttrVal);
                var spStrs = new Array();
                if (cid) {
                    spStrs = cid.split("-");
                }
                var lastVal = "";
                for (var ti = 0; ti < $.fn.hsAreaTabs.defVal.cookieItemAttrs.length; ti++) {
                    if (ti > areaVals.maxLayer) {
                        break;
                    }
                    var itemAttr = $.fn.hsAreaTabs.defVal.cookieItemAttrs[ti];
                    var val = "0";
                    if (ti < spStrs.length) {
                        val = spStrs[ti];
                    }
                    var ipEle = "<input type='hidden' name='" + itemAttr + "' id='" + itemAttr +strLast+ "' value='" + val + "'>";
                    $(ipContent).append(ipEle);
                    if (val !== "0") {
                        lastVal = val;
                    }
                }
                var lipEle = "<input type='hidden' name='lastName' id='lastName"+strLast+"' value='" + lastVal + "'>";
                $(ipContent).append(lipEle);
            }
        }
      
}

function HsPickGoodsCard() {

}

HsPickGoodsCard.defVal = {
    roleSel: "[role='hs-pikc-goods']",
    buttonAttr: "link-panel",
    panelAttr: "panel-id",
    pickWayAttr: "pick-way",
    pickWayDesAttr: "pic-way-des",
    pickWayInputAttr: "pic-way-input"
};
HsPickGoodsCard.prototype.initCard = function(args) {
    var pargs = $.extend({}, HsPickGoodsCard.defVal, args);
    var pgCardSel = pargs.roleSel;
    var pgRoles = $(document).find(pgCardSel);
    var butAttr = pargs.buttonAttr;
    var panelAttr = pargs.panelAttr;
    var pickWayAttr = pargs.pickWayAttr;
    var pickWayInputAttr = pargs.pickWayInputAttr;
    var pickWayDesAttr = pargs.pickWayDesAttr;
    for (var pgi = 0; pgi < pgRoles.length; pgi++) {
        var pgRole = pgRoles[pgi];
        var buttons = $(pgRole).find("[" + butAttr + "]");
        var pickWayButs = $(pgRole).find("[" + pickWayAttr + "]");
        $(buttons).bind("click", function(e) {
            var src = e.target;
            var npgrole = $(src).closest(pgCardSel);
            $(npgrole).find("[" + butAttr + "]").removeClass("active");
            $(npgrole).find("[" + panelAttr + "]").removeClass("active");
            var but = $(src).closest("[" + butAttr + "]");
            but.addClass("active");
            var id = but.attr(butAttr);
            $(npgrole).find("[" + panelAttr + "='" + id + "']").addClass("active");
        });
        $(pickWayButs).bind("click", function(e) { //这里添加单击后的函数如向后台取单价，向隐藏域写入值
            var src = e.target;
            var npgrole = $(src).closest(pgCardSel);
            $(npgrole).find("[" + pickWayAttr + "]").removeClass("active");
            $(npgrole).find("[" + pickWayDesAttr + "]").removeClass("active");
            var but = $(src).closest("[" + pickWayAttr + "]");
            but.addClass("active");
            var id = but.attr(pickWayAttr);
            $(npgrole).find("[" + pickWayDesAttr + "='" + id + "']").addClass("active");
            $(npgrole).find("[" + pickWayInputAttr + "]").val(id);
        });
    }
};

HsPickGoodsCard.setPickWay = function (args) {
    var setRole = args.setRole;
    var setId = args.setId;
    var pargs = $.extend({}, HsPickGoodsCard.defVal, args);
    var pickWayAttr = pargs.pickWayAttr;
    var pickWayBut = $(setRole).find("[" + pickWayAttr + "='" + setId + "']");
    $(pickWayBut).click();
}


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function (e) {
	//没有cookie进行一些引导
	var isFirst=$.cookie('firstflag'); 
	if(!isFirst && typeof(introJs) !== "undefined"){
		introJs().goToStep(1).start();
		$.cookie('firstflag','hasvisited',{path: '/',expires:365});
	}
	
    var args = HsAreaUser.defVals;
    var areaSel = args.roleSel;
    var areaRoles = $(document).find(areaSel);
    
    var idLast=0;

    for (var i = 0; i < areaRoles.length; i++) {
 
    	var targs=$.extend({},args,{areaRole:areaRoles[i]});
    	HsAreaUser.initAreaSel(targs,idLast,i);
    	idLast++;
    	  ////////////////////////////////////////////////
        var pickCard = new HsPickGoodsCard();
        pickCard.initCard();

  /*      HsPickGoodsCard.setPickWay({
            setRole: "#four-buts",
            setId: "1"
        })*/

    }
    $("#psdj-float-panel").hsFloatPanel({
    	showPanelSel: ".pick-panel [pick-way=\"3\"]",
    	top:78,
    	left:10
    })
    //
    $(window).bind("click",function(e){
    	var src=e.target;
    	var flatPanel=$(src).closest("#psdj-float-panel");
    	var pickBtn=$(src).closest("[pick-way=\"3\"]");
    	if(pickBtn.length>0 || flatPanel.length>0){
    		return;
    	}

    	$("#psdj-float-panel").find(".panel-close-btn").trigger("click");
    	
    });
  //门店自提与工厂自提
    $("[pick-way]").click(function(e){
		var src=e.target;
		var par=$(src).closest("[pick-way]");
		var pickId=par.attr("pick-way");
		$("[pick-way]").removeClass("active");
		par.addClass("active");				
       });
   
});


HsAreaUser.initAreaSelForOutPanel=function(args,idLast,index,layerPreFix){
    var areaVals = {
            layerPreFix:layerPreFix,// "area-layer-",
            maxLayer: 5,
            getCitysUrl: args.getCitysUrl,
            getProListUrl: args.getProListUrl,
            showEleSel: args.showEleSel,
            afterWriteCookie: function (args) {
            },
            afetrClickItem: function (args) {
				var event = args.event;
                var src = event.target;
				var $parent = $(src).parents(".area-float-panel");
                var itemId = args.itemId;
                $parent.find("[name='lastName']").val(itemId);
                //$("#lastName").val(itemId);
                var layer = args.layer;
                var cookieAttrs = $.fn.hsAreaTabs.defVal.cookieItemAttrs;
                for (var li = layer; li < cookieAttrs.length; li++) {
                    var tipId = cookieAttrs[li];
                    //$parent.find("#" + tipId).val("");
                    $parent.find("[name='" + tipId+"']").val("");
                }
                //向隐藏控件填写选择的区域数据
                var ipId = $.fn.hsAreaTabs.defVal.cookieItemAttrs[layer - 1];
                //$("#" + ipId).val(itemId);
                $parent.find("[name='" + ipId+"']").val(itemId);
                
                var seler = $(src).closest(".area-float-panel");
                $(seler).attr("now-layer",layer);
                var cidpanel = $(src).closest("[cid]");
                if (seler.is("[cid]")) {
                    var attrid = seler.attr("cid");
                    var tspStrs = attrid.split("-");
                   
                    if (tspStrs.length >= layer) {
                        tspStrs[layer - 1] = itemId;
                        for (var layi = layer; layi < tspStrs.length; layi++) {
                            tspStrs[layi] = "0";
                        }
                    }
                    var attrVal = "";
                    for (var atri = 0; atri < tspStrs.length; atri++) {
                        attrVal = attrVal + tspStrs[atri] + "-";
                    }
                    attrVal += "0";

                    seler.attr("cid", attrVal);

                }
            },
            lastItemClickFunc: function (args) {
                var event = args.event;
                if (typeof (event) === 'undefined') {
                    return;
                }
				var src = event.target;
				var $parent = $(src).parents(".area-float-panel");

				var $floatPanelSel = $(floatPanelSel);
				if($parent.find(floatPanelSel).length > 0)
					$floatPanelSel = $parent.find(floatPanelSel);
				
				
				$(".panel-close-btn").click();

                var item = null;
                if ($(src).is(".area-item-list")) {
                    item = $(src).find(".item-inner");
                } else {
                    item = $(src).closest(".item-inner");
                }

                var itemdate = item.data("areaItem");
				var itemId = itemdate["id"];
				
            },
            beWriteCookie: true
        };

        var areaRole = args.areaRole;
        var showBtn = $(areaRole).find(args.showPanelSel);
        var outH = $(showBtn).outerHeight();
        var floatId=$(showBtn).attr("show-hs-area-sel");
        var floatPanelSel=floatId;
        $(floatPanelSel).hsFloatPanel({
            top: outH -5,
            showPanelSel: showBtn,
            afterHiddenFunc: function (targs) {
            },
            beforeShowFunc: function (targs) {            	
                var panelSel = floatPanelSel;
                if (panelSel) {
                    var parRole = $(showBtn).closest(HsAreaUser.defVals.roleSel);
                    if ($(panelSel).is("[cid]")) {
                        var tcid = $(panelSel).attr("cid");
                        if (tcid) {
                            if (tcid.lastIndexOf("0") !== (tcid.length - 1)) {
//                                tcid += "0";
                            }
                            var tspStrs = tcid.split("-");
                            var tlargs = { spStrs: tspStrs };
                            tlargs = $.extend({}, $(floatPanelSel).data("areaVals"), tlargs);
                            var parTabSel = $(floatPanelSel).find(args.areaTabsSel);
                            $(parTabSel).hsAreaTabs("loadAreaTabs", tlargs);
                        }
                    }
                }
            },
            afterShowFunc: function (targs) {
                $("#add-hs-tabs").addClass("downrow");
            },
            beforeHiddenFunc:function(targs){
                if (floatPanelSel) {
                    var parRole = $(showBtn).closest(HsAreaUser.defVals.roleSel);
                    if ($(parRole).is("[min-layer]")) {
                        var sminlayer = $(parRole).attr("min-layer");
                        var iminlayer=parseInt(sminlayer,10);
                        if(!isNaN(iminlayer)){
                        	var nowLayer=$(floatPanelSel).attr("now-layer");
                        	 nowLayer=parseInt(nowLayer,10);
                        	 if(!isNaN(nowLayer)){
                        		 if(nowLayer<iminlayer){
                        			 alert("你最少需要选择"+iminlayer+"级")
                        			 return false;
                        		 }
                        	 }else{
                        		 return true;
                        	 }
                        	
                        }
                    }
                }
                //这里写向后台发送查询，使用最后一级id
                var lastid=$(floatPanelSel).find("[name=\"lastName\"]").val();
                var tcid=$(floatPanelSel).attr("cid");
                var parPanel=$(floatPanelSel).closest(".pageContent");
                parPanel.find("[name='areaid']").val(lastid);
                parPanel.find("[name='cid']").val(tcid);
                parPanel.find(".search-form").submit();
                
                return true;
            }
            
        });


        areaVals.layerPreFix = index + "-" + areaVals.layerPreFix;
        $(floatPanelSel).data("areaVals", areaVals);
        ////////////////////////////////////////////
        areaVals.showEleSel = $(areaRole).attr(args.showEleSel);
     
        areaVals.beWriteCookie = false;
        
        ///////////////////////////////////////////////////////
        var areaTabsSel = $(floatPanelSel).find(args.areaTabsSel);
        $(areaTabsSel).hsAreaTabs(areaVals);

        //循环加载区域信息
        var cid = null;
        if (areaVals.beWriteCookie === true) {
            cid = $.cookie($.fn.hsAreaTabs.defVal.allAreaCookie);
        }
        if (!cid) {
            if ($(floatPanelSel).is("[" + args.cidAttr + "]")) {
                cid = $(floatPanelSel).attr(args.cidAttr);
            }
        }
		
        if (cid) {
            var cspStrs = cid.split("-");
            var largs = { spStrs: cspStrs };
            largs = $.extend({}, areaVals, largs);
            $(areaTabsSel).hsAreaTabs("loadAreaTabs", largs);
        }

        ////////////
        if ($(areaRole).is("[" + args.inputSelAttr + "]")) {
            var ipAttrVal = $(areaRole).attr(args.inputSelAttr);
            var strLast="";
            if(idLast!=0){
            	strLast=idLast;
            }
            if (ipAttrVal) {
                var ipContent = $(floatPanelSel).find(ipAttrVal);
                var spStrs = new Array();
                if (cid) {
                    spStrs = cid.split("-");
                }
                var lastVal = "";
                for (var ti = 0; ti < $.fn.hsAreaTabs.defVal.cookieItemAttrs.length; ti++) {
                    if (ti > areaVals.maxLayer) {
                        break;
                    }
                    var itemAttr = $.fn.hsAreaTabs.defVal.cookieItemAttrs[ti];
                    var val = "0";
                    if (ti < spStrs.length) {
                        val = spStrs[ti];
                    }
                    var ipEle = "<input type='hidden' name='" + itemAttr + "' id='" + itemAttr +strLast+ "' value='" + val + "'>";
                    $(ipContent).append(ipEle);
                    if (val !== "0") {
                        lastVal = val;
                    }
                }
                var lipEle = "<input type='hidden' name='lastName' id='lastName"+strLast+"' value='" + lastVal + "'>";
                $(ipContent).append(lipEle);
            }
        }
      
}
