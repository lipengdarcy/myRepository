<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户车辆信息</title>
</head>
<body>
<div>        
	<form id="add-car-infor">
		<ul>
			<li class="clearfix">
				<strong>车号：</strong>
                <input type="text" name="carnum"  value=""  placeholder="请输入车牌号" />
                <span class="red">*</span>
            </li>
            <li class="clearfix">
				<strong>发动机号:</strong>
                <input type="text" name="motornum"  value=""  placeholder="请输发动机号" />
                <span class="red">*</span>
            </li>
            <li class="clearfix">
				<strong>手机号:</strong>
                <input type="text" name="mobilenum"  value=""  placeholder="请输手机号" />
            </li>
            <li class="clearfix">
				<strong>电话:</strong>
                <input type="text" name="telnum"  value=""  placeholder="请输电话" />
            </li>
            <li class="clearfix">
				<strong>邮编:</strong>
                <input type="text" name="zipcode"  value=""  placeholder="请输邮编" />
            </li>
		</ul>
		<div class="adress-list-btm clearfix">
			<a class="cancel" id="cancel-add-btn">取消</a>
			<a class="confirm " id="confirm-add-btn">确认</a>
		</div>		
	</form>
</div>
<script>
	$("#cancel-add-btn").bind("click",function(e){
		$("#carinfor-float-panel").hsFloatPanel("hiddenPanel",{
		    //isModel:true
		   });
	});
	//
	$("#confirm-add-btn").bind("click",function(e){
		var sendData=$("#add-car-infor").serialize();
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:100
		});
		$.ajax({
			url:url+"ucarinfor/addcarinfor.do",
			type:"post",
			data:sendData,
			async:false,
			dataType:"json",
			success:function(data,state){
				if(data){
					if(data.state && data.state=="success"){
						$("#carinfor-float-panel").hsFloatPanel("hiddenPanel",{
						    isModel:true
						 });
						hsArtDialog.content("保存成功").showModal();
						var carinfor=data.carinfor;
						if(carinfor){
							var cmnum=carinfor.carnum+"/"+carinfor.motornum;
							$("#ucarnum-motonum").val(cmnum);
							$("#self-pick-extinfor .car-num").val(carinfor.carnum);
							$("#self-pick-extinfor .moto-num").val(carinfor.motornum);
						}
					}else{
						hsArtDialog.content(data.msg).showModal();
					}
					
				}else{
					hsArtDialog.content("异常情况！").showModal();
				}
				
			}
		});
		
	});
</script>
</body>
</html>