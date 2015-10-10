<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账户安全-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/homenew/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/user.css"/>
<script type="text/javascript">
	uid = 1;
	isGuestSC = 1;
	scSubmitType = 0;
	function editmobilediv(){
		document.getElementById("editM").style.display="block";
	}
	function editmobile(num){
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:100
		});
		
		if(num==1){
			var oldmobile = $("#oldmobile").val();//document.getElementById("oldmobile").value;
			var nowmobile = $("#nowmobile").val();//document.getElementById("nowmobile").value;
			if (oldmobile == "" || oldmobile.length!=11) {
				hsArtDialog.content("请填写正确的电话号码！").showModal();
				document.getElementById("oldmobile").value="";
				$("#oldmobile").focus();
				return false;
			}
			if (nowmobile == "" || nowmobile.length!=11) {
				hsArtDialog.content("请填写正确的电话号码！").showModal();
				document.getElementById("nowmobile").value="";
				$("#nowmobile").focus();
				return false;
			}
			Ajax.post("editmobile.do",{oldmobile:oldmobile,nowmobile:nowmobile},false,editMobileResponse);
		}
		document.getElementById("oldmobile").value="";
		document.getElementById("nowmobile").value="";
		document.getElementById("editM").style.display="none";
	}
	function editMobileResponse(data){
		var result = eval("(" + data + ")");
		if (result.state == "success") {			
			document.getElementById("mobile").value = result.mobile;
			hsArtDialog.content(result.content).showModal();
		} else if (result.state == "error") {
			hsArtDialog.content(result.content).showModal();
		}
	}
</script>
<script src="${ctx }/scripts/region.js" type="text/javascript"></script>
<script src="${ctx }/scripts/ucenter.user.js" type="text/javascript"></script>
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
</head>

<body>

	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>
	
	<div class="user-main">
		<div class="wrap clearfix">
	  		<div class="crumbs">
	  			<strong>| <a href="seller_data.html">个人中心首页</a> </strong>&gt; 
	  			<a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>安全中心</span>
	  		</div>
		  	<div class="user-menu left">
		  		 <%@ include file="centermenu.jsp"%>
		  	</div>
		  	<div class="user-content right">
    			<h1>安全中心</h1>
			    <div class="safe-center">
			      <ul>
			        <li>
			        	<i class="yes"></i>
			        	<strong>登录密码</strong><span>互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</span>
			        	<a href="${ctx }/ucenter/goeditpassword.do">修改</a>
			        </li>
			        <li>
			        	<i class="no"></i>
			        	<strong>邮箱验证</strong><span>邮箱还未验证，验证后可用于快速找回登录密码，接收账户余额变动提醒。</span>
			        	<a href="${ctx }/ucenter/safeverify.do?act=updateEmail"> 修改 </a>
			        </li>
			        <li>
			        	<i class="yes"></i><strong>手机验证</strong><span>您验证的手机：${mobile} 若已丢失或停用，请立即更换，<em class="red">避免账户被盗</em>。</span>
			        	<a href="${ctx }/ucenter/safeverify.do?act=updateMobile"> 修改 </a>
			        </li>
			      </ul>
			      <div id="editM" style="border-bottom:1px solid #ddd; position:relative; margin-bottom:20px; padding-bottom:10px;display: none;">
					<table>
						<tr>
							<th width="130" height="20" align="right">原手机号：</th>
							<td><input type="text" name="oldmobile" id="oldmobile"
									value="" class="text" maxlength="15"
									style="width:200px"/>
							</td>
						</tr>
						<tr>
							<th width="130" height="20" align="right">现手机号：</th>
							<td><input type="text" name="nowmobile" id="nowmobile"
									value="" class="text" maxlength="15"
									style="width:200px"/>
							</td>
						</tr>
						<tr>
							<th align="right">&nbsp;</th>
							<td><a href="#" class="greenBT" onclick="editmobile(1)"
									style="font-size:12px; padding:3px 10px;">提交</a>
								<a href="#" class="greenBT" onclick="editmobile(2)"
									style="font-size:12px; padding:3px 10px;">取消</a>
							</td>
						</tr>
					</table>
				</div>			      
			    <div class="safe-note">
			        <h2>安全服务提示</h2>
			        <p>1.确认您登录的网址，注意防范进入钓鱼网站，不要轻信各种即时通讯工具发送的商品或支付链接，谨防网购诈骗。<br />
			          2.建议您安装杀毒软件，并定期更新操作系统等软件补丁，确保账户及交易安全。</p>
			    </div>
    			</div>
  			</div>
  		</div>
  	</div>


	<c:import url="${ctx }/tool/newService.do"></c:import>
	<%@ include file="../../../includes/homenew/footer.jsp"%>
</body>
</html>