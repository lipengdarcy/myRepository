/**
 * jquery插件模板
 */
(function ($) {
    var methods = {
        init: function (options) {
            var optVals = $.extend({}, $.fn.hsFloatPanel.defVal, options);
            if(optVals.isModel==true){
            	var defMaskid=optVals.defMaskid;
            	var mask=$("#"+defMaskid);
            	if(mask.length==0){
            		var $mask=$("<div class='float-mask' id='"+defMaskid+"'></div>");            		
            		$(document.body).append($mask);
            	}
            }
            return this.each(function () {
                var $this = $(this);
                var data = $this.data(optVals.dataName);
                // If the plugin hasn't been initialized yet
                if (!data) {
                    var objThis = this;
                    $(this).data(optVals.dataName, optVals.dataVal);
                    var closeBtn = $(this).find("." + optVals.closeBtnClass);
                    closeBtn.bind("click", function (e) {
                        hiddenFloatPanel($.extend({}, optVals, { event: e, panelSel: objThis }));
                    });
                    $(optVals.showPanelSel).bind("click", function (e) {
                        showFloatPanel($.extend({}, optVals, { event: e, panelSel: objThis }));
                    });
                }
            });
        },
        showPanel: function (args) {
        	if(!args.panelSel || args.panelSel.length==0){
        		args.panelSel=this;
        	}
            var optVals = $.extend({}, $.fn.hsFloatPanel.defVal, args);
            if(optVals.beforeShowFunc){
            	optVals.beforeShowFunc(optVals);
            }
            showFloatPanel(optVals);
        },
        hiddenPanel: function (args) {
        	if(!args.panelSel || args.panelSel.length==0){
        		args.panelSel=this;
        	}
            var optVals = $.extend({}, $.fn.hsFloatPanel.defVal, args);
           
            hiddenFloatPanel(optVals);
        },
        destroy: function (options) {
            return this.each(function () {
                //                var $this = $(this),
                //                    data = $this.data(optVals.dataName);
                //                // Namespacing FTW
                //                $(window).unbind('.tooltip');
                //                data.hstabs.remove();
                //                $this.removeData('tooltip');
            })

        }
    };

    /**
     * 
     * @param {type} args {<br>
     * panelSel:浮动层的选择器<br>
     * top:顶部位移<br>
     * left:左侧位移<br>
     * }<br>
     * @returns {undefined}
     */
    showFloatPanel = function (args) {
    	if(args.isModel==true){
        	var defMaskid=args.defMaskid;
        	var mask=$("#"+defMaskid);
        	var dw=$(document).width();
        	var dh=$(document).height();
        	$(mask).width(dw);
        	$(mask).height(dh);
        	mask.css("display","block");
        }
        //float-panel-id
        var afterShowFunc = args.afterShowFunc;
        var beforeShowFunc = args.beforeShowFunc;
        if (beforeShowFunc) {
            var isOk=beforeShowFunc(args);
            if(isOk==false){
            	return;
            }
        }
        var panelSel = args.panelSel;
        var top = args.top;
        var left = args.left;
        var panel = $(panelSel);
        $(panel).css("top", top + "px");
        $(panel).css("left", left + "px");
        $(panel).css("display", "block");
        panel.animate({ opacity: '1' }, 600);
        if (afterShowFunc) {
            afterShowFunc(args);
        }
    };
    /**
     * 
     * @param {type} args{<br>
     * panelSel:浮动层的选择器<br>
     * }<br>
     * @returns {undefined}
     */
    hiddenFloatPanel = function (args) {
    	 if(args.isModel==true){
         	var defMaskid=args.defMaskid;
         	var mask=$("#"+defMaskid);
         	mask.css("display","none");
         }
		//float-panel-id
        var afterHiddenFunc = args.afterHiddenFunc;
        var beforeHiddenFunc=args.beforeHiddenFunc;
        
        if(beforeHiddenFunc){
			if(!beforeHiddenFunc(args)){
				return;
			}
		}
        var panelSel = args.panelSel;
		var ev = args.event;
        var panel = $(panelSel);
        panel.animate({ opacity: '0' }, 500);
        setTimeout(function (e) {
            $(panel).css("display", "none");
            if (afterHiddenFunc) {
                afterHiddenFunc(args);
            }
        }, 600);
    };
    

    $.fn.hsFloatPanel = function (method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' 方法在$.fn.hsFloatPanel中不存在。');
        }
    };
    $.fn.hsFloatPanel.defVal = {
    	defMaskid:"float-panel-mask-00001",
    	isModel:false,	
        dataName: "hsFloatPanel",
        dataVal: "data-float-panel",
        closeBtnClass: "panel-close-btn",
        showPanelSel: "#hs-float-panel-btn",
        top: 0,
        left: 0,
        beforeHiddenFunc: function(args){     	
    		return true;
        },
        beforeShowFunc:function(optVals){
        	return true;
        }
    };
    $.fn.hsFloatPanel.verInfor = {
        version: "1.0000",
        verdate: "2015-06-08",
        verauthor: "lwt"
    };

})(jQuery);