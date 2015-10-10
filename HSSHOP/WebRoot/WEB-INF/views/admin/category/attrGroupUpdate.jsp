<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<div class="pageContent" width="100%" height="100%">
<br>
		<table border="0" align="center">
			<tr>
				<td width="30%" align="right">所属分类：</td>
				<td>
					<input id="catename" type="text" readOnly value="${catename}" />
					<input type="hidden" id="cateid" value="${cateid}">
					<input type="hidden" id="attrgroupid" value="${props.attrgroupid}">
				</td>
			</tr><tr><td>&nbsp;</td></tr>
			<tr>
				<td width="30%" align="right">分类名称：</td>
				<td>
					<input id="name" type="text" value="${props.name}" />
				</td>
			</tr><tr><td>&nbsp;</td></tr>
			<tr>
				<td width="30%" align="right">排序：</td>
				<td>
					<input id="displayorder" type="text" value="${props.displayorder}" />
				</td>
			</tr>
			</table>
		<br>
		<table width="80%" align="center">
		  <tr>
		    <th width="50%" align="center"><button  type="button" onclick="submitAttrGroup();">提交</button></th>
		    <th width="50%" align="center"><button type="button" class="close">取消</button></th>
		  </tr>
		  </table>
		<br><br>
</div>
<script type="text/javascript">
	function submitAttrGroup(){
		var attrgroupid = $("#attrgroupid").val().trim();
		var name = $("#name").val().trim();
		var cateid = $("#cateid").val().trim();
		var displayorder = $("#displayorder").val().trim();
		$.ajax({
            type: "post",
            dataType:"json",
            url: "${ctx }/admin/category.do?method=updateAttrGroup",
            data: {"attrgroupid":attrgroupid,"name":name,"cateid":cateid,"displayorder":displayorder},
            success: function(data){
           	 if(data){
           		$.pdialog.closeCurrent();
           	 }
           	 
            }
   });
	}
</script>
</div>
