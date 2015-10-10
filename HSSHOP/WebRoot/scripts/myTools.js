/*
构建命名空间对象  该对象是唯一全局对象
主要用于构建命名空间并注册至命名空间中。
 */
var NameSpace = new Object();
/*全局对象仅仅存在register函数，
 *@nameSpaceName 名称空间全路径 如Grandsoft.GEA
 * */
NameSpace.register=function(nameSpaceName){
    //使用.切割命名空间名称
    var nsArray=nameSpaceName.split('.');
    var sEval="";
    var sNS="";
    //循环命名空间的各个部分依次构建命名空间的各个部分
    for(var i=0;i<nsArray.length;i++){
        if(i!=0) sNS+=".";       
        sNS+=nsArray[i];      
        sEval+="if (typeof("+sNS+")=='undefined')"+"{ "+sNS+"=new Object(); }";
        //eval是将参数作为代码来执行的。
        continue;
    }
    if(sEval.length>0)
        eval(sEval);
}
/*
 *给函数原型增加一个extend函数，实现继承
 */  
Function.prototype.extend = function(superClass){  
    if(typeof superClass !== 'function'){  
        throw new Error('fatal error:Function.prototype.extend expects a constructor of class');  
    }            
    var F = function(){}; //创建一个中间函数对象以获取父类的原型对象  
    F.prototype = superClass.prototype; //设置原型对象  
    this.prototype = new F(); //实例化F, 继承父类的原型中的属性和方法，而无需调用父类的构造函数实例化无关的父类成员  
    this.prototype.constructor = this; //设置构造函数指向自己  
    this.superClass = superClass; //同时，添加一个指向父类构造函数的引用，方便调用父类方法或者调用父类构造函数  
          
    return this;  
}   

function MyTools(){
    
}
MyTools.keyValue=function(){
    this.key;
    this.val;
}
MyTools.exceptionTypeId={
    EleNotFoundId:1,
    EleNotMatchedId:2
}
MyTools.JsonErrMsg={
    Json_Err_Msg:"errMsg"
}
MyTools.TextSplitor="_123_SPLIT_FLAG_321_";
/**
 * 放置在js文件的文件中，在加载到该js文件时执行该函数
 * 返回js文件的文件路径
 */
MyTools.getJsFileSlefDir=function(){
    var js = document.scripts;
    var src = js[js.length - 1].src
    js = src.substring(0, src.lastIndexOf("/"));
    return js;
}
/**
 *获取发送事件的对象
 *@param e 事件源
 *@return 发送事件的dom对象
 */
MyTools.getEventSrc=function(e){
    var targ;
    if (!e){//早期的ie
        targ = window.event.srcElement;
    }else if (e.srcElement) {//ie7 以上
        targ = e.srcElement;
    }else if(e.target) {//非ie浏览器
        targ = e.target;
    }
    if (targ.nodeType == 3) // defeat Safari bug
        targ = targ.parentNode;
    return targ;
}

/*
 *创建并为element出放出事件eventName
 *@param element 发送事件的控件<br>
 *@param eventName 事件名称(不带on)
 */
MyTools.eleFireHTMLEvent=function(element,eventName){
    var evt = document.createEvent('HTMLEvents');  
    evt.initEvent(eventName,true,true);  
    element.dispatchEvent(evt);
}
/**
 *描述:生成一个描述异常信息的json字符串
 *@param args{<br>
 *exid: String 标示异常性质的id<br>
 *exmsg: String 异常的描述信息<br>
 *}<br>
 *@return String json格式字符串
 */
MyTools.ExceptionJSON=function(args){
    var defParam={
        exid:0,
        exmsg:"未描述异常"
    }
    var param=$.extend({}, defParam, args);
    var exid=param.exid;
    var exmsg=param.exmsg;
    return "{\"exid\":"+exid+",\"exmsg\":\""+exmsg+"\"}";
}
/**
 *json数据转化成js object
 *@param json json字符串
 *@return 生成的js object
 */
MyTools.jsonToObj=function(json){
    var start=json.indexOf("{");
    var end=json.lastIndexOf("}");            
    if(start!=-1&&end!=-1){
        json=json.substring(start,end+1);
        var tmpJsons=json.split(MyTools.TextSplitor);      
        if(tmpJsons.length>2){            
            var subStr="";
            for(var ti=0;ti<tmpJsons.length;ti+=2){ 
                var preStr=tmpJsons[ti];
                subStr+=preStr.replace(/(\n|\r\n)/ig,"<br/>");
                if(ti+1<tmpJsons.length){
                    var stri=tmpJsons[ti+1];
                    stri=stri.replace(/"/ig,"'");
                    stri=stri.replace(/\s/ig," ");
                    subStr+=stri;
                }
            }
            json=subStr;
        }else{
            json=json.replace(/(\n|\r\n)/ig,"<br/>");
        }
        var rsObj=null;
        try{
            rsObj=JSON.parse(json);
        }catch(err){
            alert("json is:"+"\\r\\nerr is:"+err);
        }
        if(MyTools.JsonErrMsg.Json_Err_Msg in rsObj){
            alert(rsObj[MyTools.JsonErrMsg.Json_Err_Msg]);
            return null;
        }
        return rsObj;
    }else{
        alert("无可用数据。");
    }
    return null;
}
/**
 *描述：在html的元素中装入数据<br>
 *@param args{<br>
 *     preFix:查找子元素时使用的前缀，默认为""<br>
 *     preFixType:"+"或者"-",加前缀还是减去前缀，默认为"+"<br>
 *     eleContent:被赋值区域的包含层<br>
 *     dateSource:用于填充元素值得数据源。json转化的object<br>
 *     attrName:是否使用attrName="value"来查找子元素，当为""时不启用，不为空串时启用，默认为""<br>
 *     attrMapValueName: 当原值需要映射成指定值时使用，该标志为一个属性名，属性值为可以分解为映射值得字符串 "map-value"<br>
 *     mapValuesSPFlag:分解字符串成为价值串的分割符 "##"<br>
 *     keyValueSPFlag:键值字符串之间的分割符 ":"<br>
 *     notFillItemList:[] 不填充数值的item值得list<br>
 *     my-set-attr="attrName,dsName,mapVal"
 *}<br>
 */

MyTools.initEleDate=function(args){
    var defParam={
        preFix:"",
        preFixType:"+",
        eleContent:null,
        dateSource:null,
        attrName:"",
        attrMapValueName:"map-value",
        mapValuesSPFlag:"##",
        keyValueSPFlag:":",
        mySetAttrName:"my-set-attr",
        mySetAttrArgsSpliter:",",
        notFillItemList:[]
    }
    //my-set-attr="attrName,dsName,mapVal"
    var param=$.extend({}, defParam, args);
    ///////////////////////////////////////////////////////
    if(param.eleContent&&param.dateSource){
        var eleContent=param.eleContent;
        var $eleContent=$(eleContent);
        var dateSource=param.dateSource;
        var preFixType=param.preFixType;
        var preFix=param.preFix;
        var attrName=param.attrName;
        var notFillItemList=param.notFillItemList;
        var dsItem;
        for(dsItem in dateSource){
            var goOn=true;
            if(MyTools.isArray(notFillItemList)){
                for(var ni=0;ni<notFillItemList.length;ni++){
                    var notFillItem=notFillItemList[ni];
                    if(dsItem==notFillItem){
                        goOn=false;
                        break;
                    }
                }   
            }
            if(!goOn){
                continue;
            }
            var findItem=dsItem;
            if(preFixType=="+"){
                findItem=preFix+dsItem;
            }else if(preFixType=="-"){
                var starIndex=preFix.length;
                findItem=dsItem.substring(starIndex,dsItem.length);
            }
            if(attrName!=""){
                findItem=attrName+"=\""+findItem+"\"";
            }
            var children=$eleContent.find("["+findItem+"]");
            for(var chi=0;chi<children.length;chi++){
                var child=children[chi];
                if($(child).is("input")){
                    if($(child).is("[type=\"radio\"]")||$(child).is("[type=\"checkbox\"]")){
                        if(child.value==dateSource[dsItem]){
                            child.checked=true;
                        }else{
                            child.checked=false;
                        }
                    }else{
                        if($(child).is("["+param.attrMapValueName+"]")){
                            var mvStr=$(child).attr(param.attrMapValueName);
                            var mvPar=$.extend({},param,{
                                mvStr:mvStr
                            });
                            var mvList=MyTools.getStrKeyVals(mvPar);
                            var val=MyTools.getMatchVals({
                                mvList:mvList,
                                matchKey:dateSource[dsItem]
                            });
                            if(val){
                                $(child).val(val);
                            }else{
                                $(child).val(dateSource[dsItem]);
                            }
                        }else{
                            $(child).val(dateSource[dsItem]);
                        }
                        
                    }
                }else if($(child).is("select")||$(child).is("textarea")){
                    //child.value=dateSource[dsItem];
                    var oriItemVal=dateSource[dsItem];
                    if(oriItemVal && $(child).is("textarea")){
                        oriItemVal=oriItemVal.replace(/<br\/>/ig,"\n");
                    }
                    if($(child).is("["+param.attrMapValueName+"]")){
                        mvStr=$(child).attr(param.attrMapValueName);
                        mvPar=$.extend({},param,{
                            mvStr:mvStr
                        });
                        mvList=MyTools.getStrKeyVals(mvPar);
                        val=MyTools.getMatchVals({
                            mvList:mvList,
                            matchKey:dateSource[dsItem]
                        });
                        if(val){
                            $(child).val(val);
                        }else{
                            $(child).val(oriItemVal);
                        }
                    }else{
                        $(child).val(oriItemVal);
                    }
                }else if($(child).is("img")){
                    var fsrc=child.src;
                    var olastSp=fsrc.lastIndexOf("/");
                    var opath="";
                    if(olastSp!=-1){
                        opath=fsrc.substring(0,olastSp+1);
                    }
                    child.src=opath+dateSource[dsItem];
                }else if($(child).is("a")){
                    var fhref=child.href;
                    var oflastSp=fhref.lastIndexOf("/");
                    var ofpath="";
                    if(oflastSp!=-1){
                        ofpath=fhref.substring(0,oflastSp+1);
                    }
                    child.href=ofpath+dateSource[dsItem];
                }else{
                    if($(child).is("["+param.attrMapValueName+"]")){
                        mvStr=$(child).attr(param.attrMapValueName);
                        mvPar=$.extend({},param,{
                            mvStr:mvStr
                        });
                        mvList=MyTools.getStrKeyVals(mvPar);
                        val=MyTools.getMatchVals({
                            mvList:mvList,
                            matchKey:dateSource[dsItem]
                        });
                        if(val){
                            $(child).html(val);
                        }else{
                            $(child).html(dateSource[dsItem]);
                        }
                    }else{
                        $(child).html(dateSource[dsItem]);
                    }
                }
            }
        }
        var setAttrEles=$eleContent.find("["+param.mySetAttrName+"]");
        for(var si=0;si<setAttrEles.length;si++){
            var setEle=setAttrEles[si];
            var setEleVal=$(setEle).attr(param.mySetAttrName);
            var argsList=setEleVal.split(param.mySetAttrArgsSpliter);
            var targs=argsList[0];
            if($.trim(targs)==""){
                continue;
            }
            if(argsList.length==2){
                var varg=argsList[1];
                var itemVal=dateSource[varg];
                if(itemVal){
                    if(argsList.length==3){
                        var mapVal=argsList[2];
                        var argsPar=$.extend({},param,{
                            mvStr:mapVal
                        });
                        var getList=MyTools.getStrKeyVals(argsPar);
                        var getVal=MyTools.getMatchVals({
                            mvList:getList,
                            matchKey:varg
                        });
                        if(val){
                            $(setEle).attr(targs,getVal);
                        }else{
                            $(setEle).attr(targs,varg);
                        }
                    }else{
                        $(setEle).attr(targs,itemVal);
                    }
                }
            }
        }
    }
}
/**
 *绑定一个函数到选择的元素上，同时添加一个绑定标示属性<br>
 *@param args {<br>
 *selector:"";选择器<br>
 *bindFlag:"";绑定标示<br>
 *type:"";事件名称<br>
 *fun:""事件函数<br>
 *}<br>
 */
MyTools.bind=function(args){
    if(args){
        var selector=args.selector;  
        if(selector){
            var bindFlag=args.bindFlag;
            if(bindFlag && $(selector).is("["+bindFlag+"]")){
                return;
            }
            var type=args.type;
            if(type){
                var fun=args.fun;
                var data=args.data;
                if(fun){
                    $(selector).bind(type, data, fun);
                    if(bindFlag){
                        $(selector).attr(bindFlag, "");   
                    }
                }
            }
        }
    }

}
/**
 *描述：在html的元素中装入数据<br>
 *@param args{<br>
 *     eleSel:被赋值元素的选择器<br>
 *     data:被赋予的值<br>
 *     attrName:是否使用attrName="value"来查找子元素，当为""时不启用，部位空串时启用，默认为""<br>
 *     attrMapValueName: 当原值需要映射成指定值时使用，该标志为一个属性名，属性值为可以分解为映射值得字符串 "map-value",
 *     mapValuesSPFlag:分解字符串成为价值串的分割符 "##",
 *     keyValueSPFlag:键值字符串之间的分割符 ":"
 *}<br>
 */
MyTools.initSelEleData=function(args){
    var defParam={
        attrName:"",
        attrMapValueName:"map-value",
        mapValuesSPFlag:"##",
        keyValueSPFlag:":"
    }
    var param=$.extend({}, defParam, args);
    var eleSel=args.eleSel;
    var data=args.data;
    var $eles=$(eleSel);
    for(var i=0;i<$eles.length;i++){
        var ele=$eles[i];
        var child=ele;
        if($(child).is("input")){
            if($(child).is("[type=\"radio\"]")||$(child).is("[type=\"checkbox\"]")){
                if(child.value==data){
                    child.checked=true;
                }else{
                    child.checked=false;
                }
            }else{
                if($(child).is("["+param.attrMapValueName+"]")){
                    var mvStr=$(child).attr(param.attrMapValueName);
                    var mvPar=$.extend({},param,{
                        mvStr:mvStr
                    });
                    var mvList=MyTools.getStrKeyVals(mvPar);
                    var val=MyTools.getMatchVals({
                        mvList:mvList,
                        matchKey:data
                    });
                    if(val){
                        $(child).val(val);
                    }else{
                        $(child).val(data);
                    }
                }else{
                    $(child).val(data);
                }
                        
            }
        }else if($(child).is("select")||$(child).is("textarea")){
            child.value=data;
        }else if($(child).is("img")){
            var fsrc=child.src;
            var olastSp=fsrc.lastIndexOf("/");
            var opath="";
            if(olastSp!=-1){
                opath=fsrc.substring(0,olastSp+1);
            }
            child.src=opath+data;
        }else{
            if($(child).is("["+param.attrMapValueName+"]")){
                mvStr=$(child).attr(param.attrMapValueName);
                mvPar=$.extend({},param,{
                    mvStr:mvStr
                });
                mvList=MyTools.getStrKeyVals(mvPar);
                val=MyTools.getMatchVals({
                    mvList:mvList,
                    matchKey:data
                });
                if(val){
                    $(child).html(val);
                }else{
                    $(child).html(data);
                }
            }else{
                $(child).html(data);
            }
        }
  
    }
}

/*
 *描述:将一个字符串分割并计算成一个键值对数组，数组中的值为null或者mvObj={<br>key:key,<br>val:val<br>}
 *@param args{<br>
 *mvStr:需要计算的String 默认为null,
 *mapValuesSPFlag:各个键值对的分割标志 默认为"##",
 *keyValueSPFlag: 键值对本身的分割标志 默认为":"
 *}<br>
 *@return js Array 
 *
 */
MyTools.getStrKeyVals=function(args){
    var list=new Array();
    var defValues={
        mvStr:null,
        mapValuesSPFlag:"##",
        keyValueSPFlag:":"
    }
    var param=$.extend({}, defValues,args);
    if(!param.mvStr){
        return list;
    }
    var mvKeyVals=param.mvStr.split(param.mapValuesSPFlag);
    for(var mvi=0;mvi<mvKeyVals.length;mvi++){
        var mvKeyVal=mvKeyVals[mvi];
        var keyVal=mvKeyVal.split(param.keyValueSPFlag);
        if(keyVal.length>=2){
            var key=keyVal[0];
            var val=keyVal[1];
            var mvObj={
                key:key,
                val:val
            }
            list.push(mvObj);
        }else{
            list.push(null);
        }
    }
    return list;
}
/**
 *从键值对的数组中找到匹配的建值的val值
 *@param args{<br>
 *mvList Array() 存储{<br>key:key,<br>val:val<br>}的数组<br>
 *matchKey 需要匹配的字符串
 *}<br>
 *@return 返回映射的字符串
 */
MyTools.getMatchVals=function(args){
    var mvList=args.mvList;
    var key=args.matchKey;
    for(var mvi=0;mvi<mvList.length;mvi++){
        var mvKeyVal=mvList[mvi];
        if(mvKeyVal){
            if(mvKeyVal.key==key){
                return mvKeyVal.val;
            }
        }
    }
    return null;
}
/**
 *
 */
MyTools.getPos=function(node){
    var x = 0, y = 0;
    var wsx=0,wsy=0;
    if (node) {
        if (node.getBoundingClientRect) {
            var box = node.getBoundingClientRect();
            wsx = window.scrollX;
            wsy=window.scrollY;
            x = box.left + wsx;
            y = box.top + wsy;
        } else {
            while (node) {
                x += node.offsetLeft;
                y += node.offsetTop;
                node = node.offsetParent;
            }
        }
    }
    return {
        x : Math.round(x), 
        y : Math.round(y)
    };
}
/**
 * 打印函数<br>
 * @param args {<br>
 * printSelector:打印区域选择器 默认为"#print-div" ,<br>
 *      styleStr: 样式表字符串 "",<br>
 *      baseUri:base 基准rul 默认为null<br>
 *}
 */
MyTools.printDiv=function(args)
{
    var defVal={
        printSelector:"#print-div",
        styleStr:"",
        baseUri:null
    }
    var param=$.extend({},defVal,args);
    //var head=document.getElementsByTagName("head")[0].cloneNode(true);
    var win=window.open("about:blank");
    
    var headstr = "<html><head>"+
    "<title></title><style>"+param.styleStr+"</style>";
    if(param.baseUri){
        headstr+="<base href=\""+param.baseUri+"\"></base>";
    }
    headstr+="</head><body>";
    var footstr = "</body>";
    var printBody=$(param.printSelector).html();//document.getElementById(printpage).innerHTML;
    win.document.body.innerHTML = headstr+ printBody+footstr;
    //var newstr = document.all.item(printpage).innerHTML;
    // win.document.getElementsByTagName("head")[0].appendChild(head);
    
    win.print(); 
    win.close();
    return false;
}
/**
 * 通过给定的class解析类名（给定类名则直接返回）
 * @author cuiweiqing  2011-10-9
 * @param  clzss class对象
 * @return 类名
 */
MyTools.getClassName=function (clzss){
    if(typeof clzss == "string"){
        return clzss;
    }
    var s = clzss.toString();
    if(s.indexOf('function') == -1){
        return null;
    }else{
        s = s.replace('function','');
        var idx = s.indexOf('(');
        s = s.substring(0, idx);
        s = s.replace(" ", "");
    }
    return s;
}

MyTools.getInsByClsName=function(clsName){
    try{
        var obj = eval("new "+clsName+"()");
        return obj;
    }catch(e){
        alert("未找到"+clsName+"对象！");
        return null;
    }
}

/**
 * 判断obj对象是否为Array类
 * @args obj 需要判断的对象
 * return boolean
 */

MyTools.isArray = function(obj) { 
    return Object.prototype.toString.call(obj) === '[object Array]'; 
} 


MyTools.getElePos={

    left:function(ele){
        var actualLeft = ele.offsetLeft;
        var current = ele.offsetParent;
        while (current !== null){
            actualLeft += current.offsetLeft;
            current = current.offsetParent;
        }
        //一个准确获取网页客户区的宽高、滚动条宽高、滚动条Left和Top的代码

        if (document.compatMode == "BackCompat"){
            var elementScrollLeft=document.body.scrollLeft;
        } else {
            elementScrollLeft=document.documentElement.scrollLeft;
        }
        return actualLeft-elementScrollLeft;
    },

    top:function(ele){
        var actualTop = ele.offsetTop;
        var current = ele.offsetParent;
        while (current !== null){
            actualTop += current.offsetTop;
            current = current.offsetParent;
        }
        if (document.compatMode == "BackCompat"){
            var elementScrollTop=document.body.scrollTop;
        } else {
            elementScrollTop=document.documentElement.scrollTop;
        }
        return actualTop-elementScrollTop;
    },
 

    bot:function(ele){
        var actualBottom = document.body.offsetHeight - this.top(ele);//浏览器当前的高度减去当前元素的窗口位置，注意是相对的位置，不包括滚动条里的高度
        return actualBottom;
    }
 

};
/**
 * 检测数字输入的工具函数<br>
 * @param args{
 *  event 引起函数的键盘事件<br>
 *  isDecimal 是否是小数<br>
 *  dotBackNum 小数点后的位数<br>
 *  }
 */
MyTools.numKeyCheck=function(args){
    var e=args.event;
    e = e || window.event; 
    var src=MyTools.getEventSrc(e);
    var canDot=true;
    
    //验证输入的是否是数字或者小数点
    var keycode = e.which ? e.which : e.keyCode; 
    
    if(keycode!=8  &&  keycode!=9  
        &&  keycode!=46 &&  keycode!=109 
        &&  keycode!=189  
        &&  (keycode<37||keycode>40)){
        //判断是否已经输入过小数点，并限定小时点后的位数
        if(args.isDecimal==true){
            if(src.value.indexOf(".")==-1){
                canDot=true;
            }else{
                canDot=false;  
            }
            if(canDot==false){
                var dotIndex=src.value.indexOf(".");
                if(src.value.length>=dotIndex+args.dotBackNum+1){
                    if(window.event){
                        e.keycode=0;                
                    }
                    e.returnValue=false;
                    e.cancel = true; 
                    return false;
                }
            }
        }else{
            canDot=false;
        }
        if(canDot){
            if(keycode==110||keycode==190){
                return true;
            }
        }
        if((keycode < 48 || keycode > 57) && ((keycode < 96 || keycode > 105))){  
            if(window.event){
                e.keycode=0;                
            }
            e.returnValue=false;
            e.cancel = true; 
            return false;
        } 
    }
}

/**
 *来自网络的js版hashmap
 */
var JsHashmap = function() {
    this.initialize();
}
JsHashmap.hashmap_instance_id = 0;//全局变量

JsHashmap.prototype = {
    hashkey_prefix: "<#HashMapHashkeyPerfix>",
    hashcode_field: "<#HashMapHashcodeField>",
    /**
     *
     */
    initialize: function() {
        this.backing_hash = {};
        this.code = 0;
        JsHashmap.hashmap_instance_id += 1;
        this.instance_id = JsHashmap.hashmap_instance_id;
    },
    /**
     *获取jsHashm实例的实例名称
     */
    hashcodeField: function() {
        return this.hashcode_field + this.instance_id;
    },

    /*
     *maps value to key returning previous assocciation
     */
    put: function(key, value) {
        var prev;
        if (key && value) {
            var hashCode;
            if (typeof(key) === "number" || typeof(key) === "string") {
                hashCode = key;
            } else {
                hashCode = key[this.hashcodeField()];
            }
            if (hashCode) {
                prev = this.backing_hash[hashCode];
            } else {
                this.code += 1;
                hashCode = this.hashkey_prefix + this.code;
                key[this.hashcodeField()] = hashCode;
            }
            this.backing_hash[hashCode] = [key, value];
        }
        return prev === undefined ? undefined : prev[1];
    },

    /*
     *returns value associated with given key
     */
    get: function(key) {
        var value;
        if (key) {
            var hashCode;
            if (typeof(key) === "number" || typeof(key) === "string") {
                hashCode = key;
            } else {
                hashCode = key[this.hashcodeField()];
            }
            if (hashCode) {
                value = this.backing_hash[hashCode];
            }
        }
        return value === undefined ? undefined : value[1];
    },

    /*
     *deletes association by given key.<br>
     *Returns true if the assocciation existed, false otherwise
     */
    del: function(key) {
        var success = false;
        if (key) {
            var hashCode;
            if (typeof(key) === "number" || typeof(key) === "string") {
                hashCode = key;
            } else {
                hashCode = key[this.hashcodeField()];
            }
            if (hashCode) {
                var prev = this.backing_hash[hashCode];
                this.backing_hash[hashCode] = undefined;
                if (prev !== undefined){
                    key[this.hashcodeField()] = undefined; //let's clean the key object
                    success = true;
                }
            }
        }
        return success;
    },
    empty:function(){
        var key;
        for (key in this.backing_hash){
            this.del(key);
        }
    },
    /*
     *iterate over key-value pairs passing them to provided callback
     *the iteration process is interrupted when the callback returns false.
     *the execution context of the callback is the value of the key-value pair
     *@ returns the HashMap (so we can chain)
     */
    each: function(callback, args) {
        var key;
        for (key in this.backing_hash){
            //一下两行是原来网络代码
            // if (callback.call(this.backing_hash[key][1], this.backing_hash[key][0], this.backing_hash[key][1]) === false)
            //         break;
            if(this.backing_hash[key]&&this.backing_hash[key][0]){
                callback.call(this.backing_hash[key][1], this.backing_hash[key][0],this.backing_hash[key][1]);
            // break;
            }
            
                
        }
        return this;
    },

    toString: function() {
        return "JsHashmap"
    }

}
/**
 *日期格式化 <br>
 * @param formatStr
 * * 格式 YYYY/yyyy/YY/yy 表示年份 
 * MM/M 月份 
 * W/w 星期 
 * dd/DD/d/D 日期 
 * hh/HH/h/H 时间 
 * mm/m 分钟 
 * ss/SS/s/S 秒  
 * 其中/可以被任意非上述字符代替
 */ 
Date.prototype.format = function(formatStr)   
{   
    var str = formatStr;   
    var Week = ['日','一','二','三','四','五','六'];  
  
    str=str.replace(/yyyy|YYYY/,this.getFullYear());   
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));   
  
    str=str.replace(/MM/,(this.getMonth()+1)>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));   
    str=str.replace(/M/g,this.getMonth()+1);   
  
    str=str.replace(/w|W/g,Week[this.getDay()]);   
  
    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   
    str=str.replace(/d|D/g,this.getDate());   
  
    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());   
    str=str.replace(/h|H/g,this.getHours());   
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());   
    str=str.replace(/m/g,this.getMinutes());   
  
    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());   
    str=str.replace(/s|S/g,this.getSeconds());   
  
    return str;   
}   

//将给定的容器内带有name属性的元素的值转化成扁平化的json对象
MyTools.formToFlatJsonObj=function(args){
	var jsonObj={};
	var formCon=args.formCon;
	var nameEles=$(formCon).find("[name]");
	for(var ni=0;ni<nameEles.length;ni++){
		var nameEle=nameEles[ni];
		var $nameEle=$(nameEle);
		var nameVal=$nameEle.attr("name");
		if($nameEle.is("input")){			
			if($nameEle.is("input[type='radio']:checked")){
				//如果是单选按钮
				jsonObj[nameVal]=$nameEle.val();			
			}else if($nameEle.is("input[type='checkbox']:checked")){
				//如果是多选按钮，存入数组
				var ckboxVals=jsonObj[nameVal];
				if(ckboxVals){
					ckboxVals[ckboxVals.length]=$nameEle.val();
				}else{
					ckboxVals[0]=$nameEle.val();
				}			
			}else if($nameEle.is("input")){
				//如果是文本
				jsonObj[nameVal]=$nameEle.val();
			}
		}else if($nameEle.is("select")){
			//如果是选择框,这里假定为单选
			jsonObj[nameVal]=$nameEle.val();			
		}else if($nameEle.is("textarea")){
			//如果是文本
			jsonObj[nameVal]=$nameEle.val();	
		}else{
			jsonObj[nameVal]=$nameEle.html();	
		}
	}
	return jsonObj;
}

/**
* 将给定的容器内带有name属性的元素的值转化成json对象
* @param {Object} args {<br>
* formCon:需要转化的表单包含层<br>
* }<br>
* @returns 返回组装成的json对象
*/
MyTools.formToJsonObj=function(args){
	var jsonObj={};
	var formCon=args.formCon;
	var nameEles=$(formCon).find("[name]");
	for(var ni=0;ni<nameEles.length;ni++){
          var nameEle=nameEles[ni];
          var $nameEle=$(nameEle);
          var nameVal=$nameEle.attr("name");
          if(nameVal){
              var eleval=null;
              //开始取元素值
              if($nameEle.is("input")){			
                  if($nameEle.is("input[type='radio']:checked")){
                      //如果是单选按钮
                      eleval=$nameEle.val();			
                  }else if($nameEle.is("input[type='checkbox']:checked")){
                      //如果是多选按钮
                      eleval=$nameEle.val();   			
                  }else if($nameEle.is("input")){
                      //如果是文本
                      eleval=$nameEle.val();
                  }
              }else if($nameEle.is("select")){
                  //如果是选择框,这里假定为单选
                  eleval=$nameEle.val();			
              }else if($nameEle.is("textarea")){
                  //如果是文本
                  eleval=$nameEle.val();	
              }else{
                  eleval=$nameEle.html();	
              }
              //检测是否是复合对象
              if(nameVal.indexOf(".")>=0){
                  //是复合对象
                  var spnameVals=nameVal.split(".");
                  MyTools.setJsObjVal(jsonObj,spnameVals,0,eleval);   
              }else{
                  //不是复合对象
                  var arrInfor=MyTools.getIndexAndName(nameVal);
                  if(arrInfor){
                     var arrname=arrInfor.name;
                     var arri=arrInfor.index;
                     jsonObj[arrname][arri]=eleval;
                  }else{
                      jsonObj[nameVal]=eleval;
                  }
              }   
          }
		
	}
	return jsonObj;
}
/**
* 
* @param {String} spnameVal 分割后的属性名，可能包含数组的索引
* @returns {MyTools.getIndexAndName.myToolsAnonym$25}
*/
MyTools.getIndexAndName=function(spnameVal){
  var reg=new RegExp("\[[0-9]+\]$");
  var spr = spnameVal.match(reg); 
  var spir=0;
  var rsname="";
  //是数组
  if(spr!=null && spr.length>0){
      spr=spr[0];
      var sprl=spr.length;
      var spnl=spnameVal.length;
      spir=spr.substring(1,sprl-1);
      spir=parseInt(spir,10);
      if(spnl-sprl>0){
          rsname=spnameVal.substring(0,spnl-sprl);
      }
      return {
          index:spir,
          name:rsname
      }
  }else{
      return null;
  }
  
}
/**
* 使用递归的方法组装json复合对象
* @param {Object} jsObj 需要组装的对象
* @param {Array} proArr 字符数组
* @param {int} nowDeep 整数，数组循环开始的地方
* @param {Object} lastval 本次组装结束时对象获取到的值
* @returns 
*/
MyTools.setJsObjVal=function (jsObj,proArr,nowDeep,lastval){
  var nextindex=nowDeep+1;
  var nextpro=proArr[nowDeep];
  var nextobj={};
  var arrInfor=MyTools.getIndexAndName(nextpro);
  if(arrInfor){
      var rsname=arrInfor.name;
      var rsi=arrInfor.index;
      if(!jsObj[rsname] || jsObj[rsname]==={}){
          jsObj[rsname]=[];
       }
       if(!jsObj[rsname][rsi]){
           jsObj[rsname][rsi]={};
       }
       nextobj=jsObj[rsname][rsi];
  }else if(!jsObj[nextpro]){
      jsObj[nextpro]={};
      if(proArr.length===nextindex){
          jsObj[nextpro]=lastval;
      }
      nextobj=jsObj[nextpro];
  }                
   
  if(proArr.length===nextindex){
      return;
  }
  MyTools.setJsObjVal(nextobj,proArr,nextindex,lastval);
}
/////////////////////////身份证验证相关函数
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X   
function IdCardValidate(idCard) { 
    idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格                     
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");                // 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
            return true;   
        }else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0;                             // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];            // 加权求和   
    }   
    valCodePosition = sum % 11;                // 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  }   
//去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}  
