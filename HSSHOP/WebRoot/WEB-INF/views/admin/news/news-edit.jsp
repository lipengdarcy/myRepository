<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>

<script type="text/javascript">
	var saveForm= function(){
		var msg = validateForm();
		if(msg.length > 0){
			alert(msg);
			return ;
		}
		var $form = $("#area_form");
		
		var newsid = $form.find("#news_id_h").val();
		var newstypeId = $.trim($form.find("#newstype").val());
		var title = $.trim($form.find("#title").val());
		var isShow = $.trim($form.find("#is_show").val());
		var isTop = $.trim($form.find("#is_top").val());
		var isHome = $.trim($form.find("#is_home").val());
		var displayorder = $.trim($form.find("#displayorder").val());
		var bodyVal = $.trim($form.find("#body").val());
	
		$.ajax({
			url: url+'admin/newsMng.do?method=newsEdit',
			data:{
				news_id : newsid,
				newstype_id : newstypeId,
				is_show : isShow,
				is_top : isTop,
				is_home : isHome,
				displayorder : displayorder,
				title : title,
				body : bodyVal
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				}else{
					alert("异常！请重新尝试或者联系管理员！");
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}    
		});  

	}, validateForm = function(){
		var s = "";
		var $form = $("#area_form");
		var title = $.trim($form.find("#title").val());
		var displayorder = $.trim($form.find("#displayorder").val());
		var bodyVal = $.trim($form.find("#body").val());
		if(title == ''){
			s += "新闻标题不能为空！\n";
		}
		if(displayorder == ''){
			s += "排序不能为空！\n";
		}
		if(bodyVal == ''){
			s += "新闻内容不能为空！\n";
		}
		return s;
	};
</script>
<body>
<h2 class="contentTitle">新闻-修改</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action="" class="pageForm required-validate" >
		<input type="hidden" id="news_id_h" value="${news.newsid }" />
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr>
					<th>新闻类型：</th>
					<td>
						<select id="newstype" >
							<c:forEach var="t" items="${newstypeList }" >
							<option value="${t.newstypeid }" <c:if test="${t.newstypeid eq news.newstypeid }">selected="selected"</c:if> >${t.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>新闻标题：</th>
					<td>
						<input id="title" type="text" value="${news.title }" class="textInput" />
					</td>
				</tr>
				<tr>
					<th>是否显示：</th>
					<td>
						<select id="is_show" >
							<option value="1"  >是</option>
							<option value="0" <c:if test="${news.isshow eq 0 }">selected="selected"</c:if> >否</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>是否置顶：</th>
					<td>
						<select id="is_top" >
							<option value="0" >否</option>
							<option value="1" <c:if test="${news.istop eq 1 }">selected="selected"</c:if> >是</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>是否首页：</th>
					<td>
						<select id="is_home" >
							<option value="0" >否</option>
							<option value="1" <c:if test="${news.ishome eq 1 }">selected="selected"</c:if> >是</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>排序：</th>
					<td>
						<input id="displayorder" type="text" value="${news.displayorder }"  class="textInput" />
					</td>
				</tr>
				<tr>
					<th>新闻链接：</th>
					<td>
						${ctx }/news/detail.do?id=${news.newsid }
					</td>
				</tr>
				<tr>
					<th>新闻内容：</th>
					<td>
						<textarea id="body"  class="editor textInput"  upImgUrl="${ctx }/admin/uploadfile.do?method=xeupload"  upImgExt="jpg,jpeg,gif,png" rows="25" cols="100" >${news.body }</textarea>
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

<div id="area_form_success_ctx" class="pageContent" style="display:none;padding-left:20px;">
	<div style="color:green;margin:20px 0;"><h3>修改成功！</h3></div>
</div>

