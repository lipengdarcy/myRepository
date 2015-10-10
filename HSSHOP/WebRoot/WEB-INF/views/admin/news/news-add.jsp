<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>

<script type="text/javascript">
	var saveForm = function() {
		var msg = validateForm();
		if (msg.length > 0) {
			alert(msg);
			return;
		}
		var $form = $("#area_form");

		var newstypeId = $.trim($form.find("#newstype").val());
		var title = $.trim($form.find("#title").val());
		var isShow = $.trim($form.find("#is_show").val());
		var isTop = $.trim($form.find("#is_top").val());
		var isHome = $.trim($form.find("#is_home").val());
		var displayorder = $.trim($form.find("#displayorder").val());
		var bodyVal = $.trim($form.find("#body").val());

		$.ajax({
			url : url + 'admin/newsMng.do?method=newsAdd',
			data : {
				newstype_id : newstypeId,
				is_show : isShow,
				is_top : isTop,
				is_home : isHome,
				displayorder : displayorder,
				title : title,
				body : bodyVal
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					$("#area_form_ctx").hide();
					$("#area_form_success_ctx").show();
				} else {
					alert("异常！请重新尝试或者联系管理员！");
				}
			},
			error : function() {
				alert("异常！请重新尝试或者联系管理员！");
			}
		});

	}, validateForm = function() {
		var s = "";
		var $form = $("#area_form");
		var title = $.trim($form.find("#title").val());
		var displayorder = $.trim($form.find("#displayorder").val());
		var bodyVal = $.trim($form.find("#body").val());
		if (title == '') {
			s += "新闻标题不能为空！\n";
		}
		if (displayorder == '') {
			s += "排序不能为空！\n";
		}
		if (bodyVal == '') {
			s += "新闻内容不能为空！\n";
		}
		return s;
	};

	/* 	$(pageInit);
	 function pageInit()
	 {
	 $.extend(XHEDITOR.settings,{shortcuts:{'ctrl+enter':submitForm}});
	 $('#elm1').xheditor({tools:'full'});
	 $('#elm2').xheditor({tools:'mfull'});
	 $('#elm3').xheditor({tools:'simple'});
	 $('#elm4').xheditor({tools:'mini'});
	 $('#elm5').xheditor({tools:'Cut,Copy,Paste,Pastetext,|,Source,Fullscreen,About'});
	 $('#elm6').xheditor({tools:'Cut,Copy,Paste,Pastetext,/,Source,Fullscreen,About'});
	 }
	 function submitForm(){$('#saveForm').submit();} */
</script>
<h2 class="contentTitle">新闻-添加</h2>
<div id="area_form_ctx" class="pageContent">
	<form id="area_form" method="post" action=""
		class="pageForm required-validate">
		<div class="pageFormContent nowrap" layoutH="97">
			<table>
				<tr>
					<th>新闻类型：</th>
					<td><select id="newstype">
							<c:forEach var="t" items="${newstypeList }">
								<option value="${t.newstypeid }">${t.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>新闻标题：</th>
					<td><input id="title" type="text" value="" class="textInput" />
					</td>
				</tr>
				<tr>
					<th>是否显示：</th>
					<td><select id="is_show">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
				</tr>
				<tr>
					<th>是否置顶：</th>
					<td><select id="is_top">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
				</tr>
				<tr>
					<th>是否首页：</th>
					<td><select id="is_home">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
				</tr>
				<tr>
					<th>排序：</th>
					<td><input id="displayorder" type="text" value="0"
						class="textInput" /></td>
				</tr>
				<tr>
					<th>新闻内容：</th>
					<td>
						<div class="unit">
							<textarea id="body" name="body" class="editor textInput"
								upImgUrl="${ctx }/admin/uploadfile.do?method=xeupload"
								upImgExt="jpg,jpeg,gif,png" rows="25" cols="100"></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="button" onclick="saveForm();">提交</button>
					</td>
				</tr>
			</table>
		</div>
	</form>

</div>

<div id="area_form_success_ctx" class="pageContent"
	style="display: none; padding-left: 20px;">
	<div style="color: green; margin: 20px 0;">
		<h3>添加成功！</h3>
	</div>
	<a title="添加" target="navTab"
		href="${ctx}/admin/newsMng.do?method=toNewsAdd" class="add"><span>继续添加</span>
	</a>
</div>
