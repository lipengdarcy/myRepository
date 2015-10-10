/**
 * 
 */

$(function() {
		
		var options={
				width:400,
				height:500,
				max:false,
				mask:true,
				maxable:false,
				minable:false,
				resizable:true,
				drawable:true,
				fresh:false
				}
		$(document).on("click",".sel-cate-dlg-btn",function(e){
			$.pdialog.open("category.do?method=getCateTree", "sel-cate-dlg", "请选择商品分类", options);
			//借助该元素存储对话框触发事件
			$("#layout").data("sel-cate-dlg-event",e);

		});
		//简单的树形控件
		$(document).on("click",".tree-expand-icon",function(e){
			var src=e.target;
			var ftop=$(src).closest(".tree-expand-icon");
			var parLi=$(src).closest("li");
			if(ftop.is("[has-init]")){
				var sonUl=parLi.find("ul");
				if(ftop.is("[open]")){
					ftop.removeAttr("open");
					ftop.html("+");
					sonUl.css("display","none");
				}else{
					sonUl.css("display","block");
					ftop.attr("open","true");
					ftop.html("-");
				}
				return;
			}
			var parId=ftop.attr("par-id");
			
			var outCon=$(src).closest(".tree-out-content");
			var treeJson=$(outCon).data("tree-json");
			if(!treeJson){
				$.ajax({
					url:"category.do?method=getCateTreeJson",
					type:"post",
					data:"",
					async:false,
					dataType:"json",
					success:function(rs,status){
						$(outCon).data("tree-json",rs);
						treeJson=$(outCon).data("tree-json");
					}
				});
			}
			
			addTreeNodes(parLi,parId,treeJson);
			ftop.attr("has-init","true");
			ftop.attr("open","true");
			ftop.html("-");
		});
		//单击产品分类树节点的选择按钮后调用的方法
		$(document).on("click",".sel-node-btn",function(e){
			var src=e.target;
			var selBtn=$(src).closest(".sel-node-btn");
			var dataObj=selBtn.data("cate-obj");
			//从辅助元素取出对话框触发事件
			var dlgEvent=$("#layout").data("sel-cate-dlg-event");
			var dlgESrc=dlgEvent.target;
			var dlgEPar=$(dlgESrc).closest(".sel-cate-infor-content");
			var url=$(dlgEPar).attr("attr-val-url");
			dlgEPar.find(".show-cate-name").html(dataObj.name);
			dlgEPar.find("[productCateid]").val(dataObj.cateid);
			//dlgEPar.find("[name='CategoryName']").val(dataObj.name);
			var tabs=$(dlgEPar).closest(".tabs");
			var attrEle=$(tabs).find(".attr-infor-content");
			
			loadCateAttrVals(dataObj.cateid,attrEle,url);
			$.pdialog.closeCurrent();
		});
		//sku属性变化的监听
		$(document).on("change",".sku-attr-vals input",function(e){
			var src=e.target;
			var srcCon=$(src).closest(".pageFormContent");
			var skuListCon=$(srcCon).find(".sku-infor-list");
			skuListCon.empty();
			var skuContent=$(src).closest(".sku-attr-vals");
			var dls=$(skuContent).find("dl");
			var srcList=new Array();
			for(var di=0;di<dls.length;di++){
				var dl=dls[di];
				var ipeles=$(dl).find("input");
				var tmpList=new Array();
				for(var ipi=0;ipi<ipeles.length;ipi++){
					var ipele=ipeles[ipi];
					var isinput=$(ipele).attr("isinput");
					if(isinput=="0"){
						if(ipele.checked==true){
							tmpList.push(new Array(ipele));
						}
					}else{
						if($.trim(ipele.value)!=""){
							tmpList.push(new Array(ipele));
						}
					}
				}
				if(tmpList.length>0){
					srcList.push(tmpList);
				}
			}
			var nameList=new Array();
			if(srcList.length>0){
				nameList=srcList[0];
				for(var si=1;si<srcList.length;si++){
					var srcArr=srcList[si];
					var ttmpList=new Array();
					for(var ssi=0;ssi<srcArr.length;ssi++){
						var srcObj=srcArr[ssi];
						for(var nai=0;nai<nameList.length;nai++){
							var namObj=nameList[nai];
							var newStr=new Array();
							var newStr=newStr.concat(namObj,srcObj);
							ttmpList.push(newStr);
						}
					}
					nameList=ttmpList;
				}
			}
			var sumIndex=0;
			var sumPid=0;
			var listUl=$("<ul></ul>");
			for(var ni=0;ni<nameList.length;ni++){
				var tval=nameList[ni];
				//$("#sku-infor-list").append("<li>");
				var listLi=$("<li></li>");
				listLi.append("<span style='display:inline-block;padding:5px 8px;'> SKU:</span>");
				var skuName="";
				for(var tvi=0;tvi<tval.length;tvi++){
					var tvele=tval[tvi];
					var attrval=$(tvele).attr("attrval");
					var attrid=$(tvele).attr("attrid");
					var attrvalid=$(tvele).attr("attrvalid");
					var isinput=$(tvele).attr("isinput");
					var inputval="";
					if(isinput=="1"){
						inputval=$(tvele).val();
						attrval=$(tvele).val();
					}
					listLi.append("<span style='display:inline-block;padding:5px 8px;'> "+$.trim(attrval)+"</span>");
					skuName+=$.trim(attrval)+" ";
					listLi.append("<input type='hidden' name='skuList["+sumIndex+"].pid' value='"+sumPid+"'>");
					listLi.append("<input type='hidden' name='skuList["+sumIndex+"].attrid' value='"+attrid+"'>");
					listLi.append("<input type='hidden' name='skuList["+sumIndex+"].attrvalueid' value='"+attrvalid+"'>");
					listLi.append("<input type='hidden' name='skuList["+sumIndex+"].inputvalue' value='"+inputval+"'>");
					sumIndex++;
				}
				sumPid++;
				listLi.append("<input type='hidden' name='skuListName' value='"+skuName+"'>");
				listUl.append(listLi);
			}
			$(skuListCon).append(listUl);
			
		});
		
		//产品属性变化的监听
		$(document).on("change",".product-attr-vals input",function(e){
			var src=e.target;
			$("#product-infor-list").empty();
			var skuContent=$(src).closest(".product-attr-vals");
			var dls=$(skuContent).find("dl");
			var srcList=new Array();
			for(var di=0;di<dls.length;di++){
				var dl=dls[di];
				var ipeles=$(dl).find("input[isinput]");
				for(var ipi=0;ipi<ipeles.length;ipi++){
					var ipele=ipeles[ipi];
					var isinput=$(ipele).attr("isinput");
					if(isinput=="0"){
						if(ipele.checked==true){
							srcList.push(ipele);
						}
					}else{
						if($.trim(ipele.value)!=""){
							srcList.push(ipele);
						}
					}
				}
			}
	
			var sumIndex=0;
			var listUl=$("<ul></ul>");
			for(var ni=0;ni<srcList.length;ni++){
				var tval=srcList[ni];
				var listLi=$("<li></li>");
				var attrval=$(tval).attr("attrval");
				var attrid=$(tval).attr("attrid");
				var attrvalid=$(tval).attr("attrvalid");
				var isinput=$(tval).attr("isinput");
				var inputval="";
				if(isinput=="1"){
					inputval=$(tval).val();
					attrval=$(tval).val();
				}
				listLi.append("<input type='hidden' name='skuList["+sumIndex+"].attrid' value='"+attrid+"'>");
				listLi.append("<input type='hidden' name='skuList["+sumIndex+"].attrvalueid' value='"+attrvalid+"'>");
				listLi.append("<input type='hidden' name='skuList["+sumIndex+"].inputvalue' value='"+inputval+"'>");
				sumIndex++;
				
				listUl.append(listLi);
			}
			$("#product-infor-list").append(listUl);
			
		});
		//
		$(document).on("click",".radio-text-label",function(e){
			var src=e.target;
			var par=$(src).closest(".parent-span");
			var radio=$(par).find("[type='radio']");
			$(radio).trigger("click");
		});
	});

	function addTreeNodes(parNode,parId,treeJson){
		var sonUl="<ul></ul>";
		var $sonUl=$(sonUl);
		for(var ti=0;ti<treeJson.length;ti++){
			var treeNode=treeJson[ti];
			if(treeNode.parentid==parId){
				var sonLi="<li></li>";
				var $sonLi=$(sonLi);
				
				if(treeNode.haschild==1){
					$sonLi.append("<span class=\"tree-expand-icon\" par-id=\""+treeNode.cateid+"\">+</span>");
				}
				$sonLi.append("<span class=\"tree-text\">"+treeNode.name+"</span>");
				if(treeNode.haschild==0){
					$sonLi.find(".tree-text").addClass("last-node");
					var selBtn=$("<span class=\"sel-node-btn\">[选择]</span>");
					selBtn.data("cate-obj",treeNode);
					$sonLi.append(selBtn);
				}
				$sonUl.append($sonLi)
			}
		}
		parNode.append($sonUl);
	}
	
	function loadCateAttrVals(cateid,rsEle,url){
		$.ajax({
			url:url,
			type:"post",
			data:"cateid="+cateid,
			async:false,
			dataType:"text",
			success:function(rs,status){
				$(rsEle).html(rs);
			}
		});
	}
	
	function initProAttrs(pid,content,url){
		$.ajax({
			url:url,
			type:"get",
			data:"pid="+pid,
			async:false,
			dataType:"json",
			success:function(rs,status){
				var lastObj=null;
				for(var ri=0;ri<rs.length;ri++){
					var rsObj=rs[ri];
					var attrvalid=rsObj["attrvalueid"];
					var attreles=$(content).find("[attrvalid='"+attrvalid+"']");
					for(var ai=0;ai<attreles.length;ai++){
						var attrele=attreles[ai];
						if($(attrele).is("[type='radio']")){
							attrele.checked=true;
						}else{
							$(attrele).val(rsObj["inputvalue"]);
						}
						lastObj=attrele;
					}
				}
				if(lastObj!=null){
					$(lastObj).trigger("change");
				}
			}
		});
	}
	
	function initProSkuAttrs(skugid,content,url){
		$.ajax({
			url:url,
			type:"get",
			data:"skugid="+skugid,
			async:false,
			dataType:"json",
			success:function(rs,status){
				var lastObj=null;
				for(var ri=0;ri<rs.length;ri++){
					var rsObj=rs[ri];
					var attrvalid=rsObj["attrvalueid"];
					var attreles=$(content).find("[attrvalid='"+attrvalid+"']");
					for(var ai=0;ai<attreles.length;ai++){
						var attrele=attreles[ai];
						if($(attrele).is("[type='checkbox']")){
							attrele.checked=true;
							$(attrele).trigger("change");
						}else{
							$(attrele).val(rsObj["inputvalue"]);
							if(rsObj["inputvalue"]){
								$(attrele).trigger("change");
							}
						}
						
					}
				}
			}
		});
	}