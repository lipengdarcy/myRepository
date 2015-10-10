/**
 * jquery插件模板
 */
(function ($) {
    var methods = {
        init: function (options) {
            var optVals = $.extend({}, $.fn.hsAreaTabs.defVal, options);
            return this.each(function () {
                var $this = $(this);
                var data = $this.data(optVals.dataName);
                // If the plugin hasn't been initialized yet
                if (!data) {
                    $(this).data(optVals.dataName, optVals.dataVal);
                    $(this).data("args", optVals);
                    //加载省份信息
                    var tabName = $(this).hsAreaTabs("getNameOfLayer", $.extend({}, optVals, { layer: 1 }));
                    var args = {
                        fromClicked: false,
                        tabName: tabName,
                        layer: 1,
                        url: optVals.getProListUrl
                    };
                    args = $.extend({}, optVals, args);
                    $(this).hsAreaTabs("loadAreaInfor", args);
                }
            });
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

        },
        /**
         * 添加一个标签页。该标签基于bootstrap框架。被选择的元素必须为如下格式
         *  &lt;div role="tabpanel" id="hs-tab-test"&gt;
         *    &lt;ul class="nav nav-tabs" role="tablist"&gt;
         *   &lt;/ul&gt;
         *   &lt;div class="tab-content"&gt;
         *   &lt;/div&gt;
         * &lt;/div&gt;
         * @params options {<br>
         * idVal:字符串 要添加的标签的内容层的模板的id。<br>
         * navTabTmp:导航标签的模板,默认为&lt;li role='presentation'&gt;&lt;a href='#def_tmp_idhref' aria-controls='def_tmp_idhref' role='tab' data-toggle='tab'&gt;Home&lt;/a&gt;&lt;/li&gt;<br>
         * contentTabTmp:内容层的模板，默认为&lt;div role='tabpanel' class='tab-pane active' id='def_tmp_idhref'&gt;20101&lt;/div&gt;<br>
         * navLiClass:额外加在导航标签li的样式<br>
         * navAClass:额外加在导航标签a的样式<br>
         * navTabHtml：导航标签的内容<br>
         * navContentSel：导航层选择器,默认为 .nav-tabs<br>
         * contentTabClass:内容层的外加样式<br>
         * tabsContentSel：内容层的选择器，默认为 .tab-content<br>
         * }<br>
         */
        addTab: function (options) {
            var optVals = $.extend({}, $.fn.hsAreaTabs.defVal, options);
            var idVal = optVals.tabId;
            var navTabTmp = optVals.navTabTmp;
            var contentTabTmp = optVals.contentTabTmp;
            var navLiClass = optVals.navLiClass;
            var navAClass = optVals.navAClass;
            var navTabHtml = optVals.navTabHtml;
            var navContentSel = optVals.navContentSel;
            var contentTabClass = optVals.contentTabClass;
            var tabsContentSel = optVals.tabsContentSel;

            if (!idVal) {
                var date = new Date();
                idVal = date.getMilliseconds();
                var rand = Math.random() * 100;
                rand = Math.round(rand);
                idVal = idVal + "" + rand;
            }

            navTabTmp = navTabTmp.replace(/def_tmp_idhref/g, idVal);
            var $navTab = $(navTabTmp);
            if (navLiClass) {
                $navTab.addClass(navLiClass);
            }
            if (navAClass) {
                $navTab.find("a").addClass(navLiClass);
            }
            if (navTabHtml) {
                $navTab.find("a").html(navTabHtml);
            }

            contentTabTmp = contentTabTmp.replace(/def_tmp_idhref/g, idVal);
            var $contentTab = $(contentTabTmp);
            if (contentTabClass) {
                $contentTab.addClass(contentTabClass);
            }
            return this.each(function () {
                var $this = $(this);
                var navCont = $this.children(navContentSel);
                $(navCont).children("li").removeClass("active");
                $(navCont).append($navTab);
                $navTab.addClass("active");

                var tabsCont = $this.children(tabsContentSel);
                $(tabsCont).children(".tab-pane").removeClass("active");
                $(tabsCont).append($contentTab);
                $contentTab.addClass("active");
            });
        },
        /**
         * 删除一个或者一组标签
         * @params options 传入要删除的标签的内容层的id或者id值得数组
         */
        delTabs: function (options) {

            var isArray = Object.prototype.toString.call(options) === '[object Array]';
            if (isArray) {
                for (var i = 0; i < options.length; i++) {
                    var id = options[i];
                    var tab = $(this).find("[href='#" + id + "']").closest("li");
                    $(tab).remove();
                    $("#" + id).remove();
                }
            } else {
                var id = options;
                var tab = $(this).find("[href='#" + id + "']").closest("li");
                $(tab).remove();
                $("#" + id).remove();
            }
            return this;
        },
        /**
         * 加载区域列表信息
         * @param {type} options {<br>
         * itemContTmp:条目列表的外部包含层，默认为"<ul></ul>"<br>
         * itemTmp:条目信息的展示层，及数据存储元素层，必须包含一个class='item-inner'的元素，用来存储和展示条目信息，默认为"<li><span class='item-inner'></span></li>"<br>
         * itemContClass:包含层外加的样式""<br>
         * itemClass:条目外加的样式""<br>
         * itemClickFunc:条目被单击是触发的函数""<br>
         * tabContId:需要添加到哪个id的标签下""<br>
         * areaList:信息条目，Array类型""<br>
         * clearPre:是否清除以前的数据，默认清除""<br>
         * }<br>
         * @returns {hsAreaTabs_L4.methods}
         */
        addAreaList: function (options) {
            var optVals = $.extend({}, $.fn.hsAreaTabs.defVal, options);
            var itemContTmp = optVals.itemContTmp;
            var itemTmp = optVals.itemTmp;
            var itemContClass = optVals.itemContClass;
            var itemClass = optVals.itemClass;
            var itemClickFunc = optVals.itemClickFunc;
            var tabContId = optVals.tabContId;
            var areaList = optVals.areaList;
            var clearPre = optVals.clearPre;

            var objThis = this;

            if (tabContId) {
                var tabCont = $("#" + tabContId);
                if (!(false === clearPre)) {//不是false的时候清除原来的内容
                    tabCont.empty();
                }
                var itemCont = $(itemContTmp);
                itemCont.addClass(itemContClass);

                var item = $(itemTmp);
                item.addClass(itemClass);
                item.bind("click", function (e) {
                    var targs = {
                        event: e
                    }
                    targs = $.extend({}, options, targs);
                    objThis.hsAreaTabs("itemClickFunc", targs);
                });
                if (areaList) {
                    for (var i = 0; i < areaList.length; i++) {
                        var aitem = areaList[i];
                        var itemClone = item.clone(true);
                        var $itemClone = $(itemClone);
                        $itemClone.find(".item-inner").data("areaItem", aitem);
                        $itemClone.find(".item-inner").attr("idflag", aitem["id"]);
                        $itemClone.find(".item-inner").html(aitem["name"]);
                        itemCont.append($itemClone);
                    }
                }
                tabCont.append(itemCont);

            }
            return this;
        },
        /**
         * 加载区域信息
         * @param args {<br>
         * areaItem:字符串 条目信息<br>
         * fromClicked:布尔型 是否因单击某个条目进行的加载行为,默认为true
         * tabName:字符串 标签的名字<br>
         * url:字符串 加载信息的地址<br>
         * itemClickFunc:函数 单击条目触发的事件<br>
         * layer: 整数 需要加载的信息的区域层级<br>
         * }<br>
         */
        loadAreaInfor: function (args) {
            var options = $.extend({}, $.fn.hsAreaTabs.defVal, args);
            var areaItem = options.areaItem;
            var fromClicked = true;
            if (options.fromClicked !== null && options.fromClicked !== undefined) {
                fromClicked = options.fromClicked;
            }
            var tabName = options.tabName;
            var url = options.url;
            //var itemClickFunc = options.itemClickFunc;
            var layer = options.layer;
            var preFix = options.layerPreFix;
            var lastItemClickFunc = options.lastItemClickFunc;
            var $this = $(this);
            //
            var delIds = new Array();
            for (var i = layer; i <= 5; i++) {
                delIds.push(preFix + i);
            }
            $this.hsAreaTabs("delTabs", delIds);
            var tabid = preFix + layer;
            //navLiClass: "",navAClass: "", contentTabClass: "", navContentSel: ".nav-tabs",tabsContentSel: ".tab-content",
            var optVals = {
                navTabHtml: tabName,
                tabId: tabid
            };
            optVals = $.extend({}, options, optVals)
            var objThis = this;
            $.ajax({
                type: 'get',
                url: url,
                async: false,
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    if (data.state === 'success') {
                        var list = data.content;
                        if (list.length > 0) {
                            $this.hsAreaTabs("addTab", optVals);
                            var itemVals = {
                                // itemClickFunc:itemClickFunc,
                                tabContId: tabid,
                                areaList: list
                            };
                            itemVals = $.extend({}, options, itemVals);
                            $this.hsAreaTabs("addAreaList", itemVals);
                        } else {//若果获取不到数据说明是末级地址，将其存入cookie，并向后台发送一条消息
                            //,"ProvinceId":"1","CityId":"35","CountyId":"0","StreetId":"0"
                            if (fromClicked === true) {
                                var witemVals = { areaItem: areaItem };
                                witemVals = $.extend({}, options, witemVals);
                                $this.hsAreaTabs("writeSelAreaToCookie", witemVals);
                                //writeSelAreaToCookie({areaItem: areaItem});
                            }
                            if (lastItemClickFunc) {
                                lastItemClickFunc(options);
                            }
                        }

                    }
                }
            });
            return this;
        },
        loadAreaTabs: function (args) {
            var options = $.extend({}, $.fn.hsAreaTabs.defVal, args);
            var spStrs = options.spStrs;
            var maxLayer = options.maxLayer;
            var getCitysUrl = options.getCitysUrl;
            var afetrClickItem = options.afetrClickItem;
            // var lastItemClickFunc = options.lastItemClickFunc;
            if (spStrs.length === 1 && spStrs[0] === "0") {

                //加载省份信息
                var tabName = $(this).hsAreaTabs("getNameOfLayer", $.extend({}, options, { layer: 1 }));
                var targs = {
                    fromClicked: false,
                    tabName: tabName,
                    layer: 1,
                    url: options.getProListUrl
                };
                targs = $.extend({}, options, targs);
                $(this).hsAreaTabs("loadAreaInfor", targs);
            }

            for (var i = 0; i < spStrs.length; i++) {
                var itemId = spStrs[i];

                if (itemId === "0") {
                    break;
                } else {
                    var cargs = {
                        itemId: itemId,
                        layer: i + 1
                    }
                    cargs = $.extend({}, options, cargs);
                    $(this).hsAreaTabs("changeTabName", cargs);
                    if ((i + 1) >= maxLayer) {//最多获取4级
                        //                        if (lastItemClickFunc) {
                        //                            lastItemClickFunc(options);
                        //                        }
                        break;
                    }
                    var atArgs = { layer: i + 2 };
                    atArgs = $.extend({}, options, atArgs);
                    var itabName = this.hsAreaTabs("getNameOfLayer", atArgs);

                    var largs = {
                        areaItem: {},
                        fromClicked: false,
                        tabName: itabName,
                        url: getCitysUrl + "?provinceId=" + itemId,
                        afetrClickItem: afetrClickItem,
                        layer: i + 2
                    };
                    largs = $.extend({}, options, largs);
                    $(this).hsAreaTabs("loadAreaInfor", largs);
                    //shopArea.loadAreaInfor(largs);
                }
            }
        },
        /**
         * 单击某个条目时触发的函数
         * @param e 
         */
        itemClickFunc: function (args) {
            var options = $.extend({}, $.fn.hsAreaTabs.defVal, args);
            var event = options.event;
            var maxLayer = options.maxLayer;
            var getCitysUrl = options.getCitysUrl;
            var afetrClickItem = options.afetrClickItem;
            var lastItemClickFunc = options.lastItemClickFunc;
            var src = event.target;
            var par = $(src).closest("li");
            var areaItem = $(par).find(".item-inner").data("areaItem");
	
            var id = areaItem["id"];
            var layer = areaItem["layer"];
            layer = parseInt(layer, 10);
            var largs = { layer: layer + 1 };
            largs = $.extend({}, options, largs);
            var tabName = this.hsAreaTabs("getNameOfLayer", largs);
            //var tabName = getNameOfLayer(layer + 1);
            var cargs = {
                itemId: id,
                layer: layer
            }
            cargs = $.extend({}, options, cargs);
            this.hsAreaTabs("changeTabName", cargs);
            var isgo=true;
            if (afetrClickItem) {
            	isgo=afetrClickItem(cargs);
            	if(isgo==false){
            		return;
            	}
            }
            if (layer >= maxLayer) {//最多获取maxLayer级,如果当前是限定的最终级，写cookie
                var atargs = $.extend({}, options, { areaItem: areaItem });
                this.hsAreaTabs("writeSelAreaToCookie", atargs);
                if (lastItemClickFunc) {
                    lastItemClickFunc(options);
                }
                return;
            }
            var targs = {
                areaItem: areaItem,
                tabName: tabName,
                url: getCitysUrl + "?provinceId=" + id,
                // itemClickFunc: itemClickFunc,
                layer: layer + 1,
                fromClicked: true
            };
            targs = $.extend({}, options, targs);
            this.hsAreaTabs("loadAreaInfor", targs);
            // loadAreaInfor(targs);
        },
        /**
         * 获取标题
         * @param {整数} layer
         * @returns {String}
         */
        getNameOfLayer: function (args) {
            var options = $.extend({}, $.fn.hsAreaTabs.defVal, args);
            var layer = options.layer;
            var tabTextClass = options.tabTextClass;
            var tabRightIcon = options.tabRightIcon;
            var name = "";
            if (layer === 1) {
                name = "省份";
            } else if (layer === 2) {
                name = "城市";
            } else if (layer === 3) {
                name = "县区";
            } else if (layer === 4) {
                name = "乡镇";
            } else if (layer === 5) {
                name = "街道";
            }
            return "<span class='" + tabTextClass + "'>" + name + "</span> " + tabRightIcon;
        },
        /**
         * 
         * @param {type} args {<br>
         *      areaItem:{条目的信息<br>
         *      ProvinceId:<br>
         *      CityId:<br>
         *      CountyId:<br>
         *      StreetId:<br>
         *      }<br>
         * }<br>
         * @returns {undefined}
         */
        writeSelAreaToCookie: function (args) {
            var options = $.extend({}, $.fn.hsAreaTabs.defVal, args);
            var beWriteCookie = options.beWriteCookie;
            if (!beWriteCookie) {
                return;
            }
            var itemAttrs = options.cookieItemAttrs;
            var areaItem = options.areaItem;
            var allAreaCookie = options.allAreaCookie;
            var lastAreaCookie = options.lastAreaCookie;
            var maxCookieLife = options.maxCookieLife;
            var afterWriteCookie = options.afterWriteCookie;
            var cookiePath = options.cookiePath;
			
			

            var cookInfor = "";
            for (var i = 0; i < itemAttrs.length; i++) {
                var itemAttr = itemAttrs[i];
                var itemVal = areaItem[itemAttr];
                if (itemVal === null || itemVal === "0") {
                    cookInfor += areaItem["id"] + "-0";
                    break;
                } else {
                    cookInfor += itemVal + "-";
                }
            }

            if (cookInfor.lastIndexOf("0") !== (cookInfor.length - 1)) {
                cookInfor += "0";
            }
			

            $.cookie(allAreaCookie, cookInfor, { expires: maxCookieLife, path: cookiePath });
            $.cookie(lastAreaCookie, areaItem["id"], { expires: maxCookieLife, path: cookiePath });
			
            if (afterWriteCookie) {
                afterWriteCookie(args);
            }
        },
        /**
         * 获取指定层的选中的区域信息
         * @param {type} args
         * @returns {unresolved}
         */
        getNowLayerAreaInfor: function (args) {
            var $this = $(this);
            var options = $.extend({}, $.fn.hsAreaTabs.defVal, args);
            var layerPreFix = options.layerPreFix;
            var layer = options.layer;
            var tabA = $this.find("[href='#" + layerPreFix + (layer) + "']");
            var itemInfor = tabA.data("areaItem");
            return itemInfor;
        },
        /**
         * 在标签上设置当前的选中条目的信息
         * @param {type} args
         * @returns {undefined}
         */
        changeTabName: function (args) {
            var $this = $(this);
            var options = $.extend({}, $.fn.hsAreaTabs.defVal, args);
            var itemId = options.itemId;
            var layer = options.layer;
            var layerPreFix = options.layerPreFix;
            var tabTextClass = options.tabTextClass;
            var showEleSel = options.showEleSel;
            var itemEle = $this.find("[idflag='" + itemId + "']");
            var itemInfor = itemEle.data("areaItem");
            var selName = itemInfor["name"];
            var tabA = $this.find("[href='#" + layerPreFix + (layer) + "']");
            tabA.data("areaItem", itemInfor);
            var tabInner = tabA.find("." + tabTextClass);
            tabInner.html(selName);
            var areaEle = $(showEleSel);
            //多个区域选择
			if($this.parents(".cellCon").find(showEleSel).length > 0)
				areaEle = $this.parents(".cellCon").find(showEleSel);

			areaEle.empty();
            var names = "";
            for (var i = 1; i <= layer; i++) {
                var nameEle = $this.find("[href='#" + layerPreFix + (i) + "'] ." + tabTextClass);
                var name = nameEle.html();
                names += name + (i === layer ? "" : "-");
            }
            areaEle.html(names);
        }
    };

    $.fn.hsAreaTabs = function (method) {

        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' 方法在$.fn.hstabs中不存在。');
        }
    };
    $.fn.hsAreaTabs.defVal = {
        dataName: "hstabs",
        dataVal: "data-hstabs",
        navTabTmp: "<li role='presentation'><a href='#def_tmp_idhref' aria-controls='def_tmp_idhref' role='tab' data-toggle='tab'>新标签页</a></li>",
        contentTabTmp: "<div role='tabpanel' class='tab-pane active' id='def_tmp_idhref'>空条目</div>",
        navTabHtml: "",
        navLiClass: "",
        navAClass: "",
        contentTabClass: "",
        navContentSel: ".nav-tabs",
        tabsContentSel: ".tab-content",
        tabId: "",
        itemContTmp: "<ul></ul>",
        itemTmp: "<li><span class='item-inner'></span></li>",
        itemContClass: "area-ul",
        itemClass: "area-item-list",
        layerPreFix: "area-layer-",
        maxLayer: 5,
        getCitysUrl: "./city.do",
        getProListUrl: "./province.do",
        lastAreaCookie: "lastarea",
        allAreaCookie: "allarea",
        tabTextClass: "tab-text",
        showEleSel: "#seled-area-name",
        cookieItemAttrs: ["ProvinceId", "CityId", "CountyId", "StreetId", "id"],
        afterWriteCookie: function (args) {
            var cid = $.cookie("allarea");
            //alert("cid:" + cid);
        },
        afetrClickItem: function (args) {
            //alert("ddddd");
        },
        maxCookieLife: 30,
        cookiePath: '/',
        tabRightIcon: "<span class='glyphicon glyphicon-menu-down'></span>",
        beWriteCookie: true
    };
    $.fn.hsAreaTabs.verInfor = {
        version: "1.0000",
        verdate: "2015-06-01",
        verauthor: "lwt"
    };

})(jQuery);