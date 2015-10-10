<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<div class="pageContent" width="100%" height="100%">
<br>
		<table border="0" align="center" width="90%">
			 <tr>
                    <td width="30%" align="right">
                        所属分类：
                    </td>
                    <td width="70%" >
                    <input type="hidden" id="cateid" value="${cateid }" />
                    <input type="hidden" id="attrid" value="${props.attrid  }" />
                    <input type="text" name="CategoryName" readOnly  class = "input" value="${catename }" />
                    </td>
                </tr><tr><td>&nbsp;</td></tr>
                <tr>
                    <td align="right">
                        属性名称：
                    </td>
                    <td><input class="input" data-val="true" data-val-length="名称长度不能大于15" data-val-length-max="15" data-val-required="名称不能为空" id="name" name="AttributName"  type="text" value="${name }" /><span class="field-validation-valid" data-valmsg-for="AttributName" data-valmsg-replace="true"></span>
                    </td>
                </tr><tr><td>&nbsp;</td></tr>
                <tr id="attrGroupTr">
                    <td align="right">
                        属性分组：
                    </td>
                    <td>
                        <select data-val="true" data-val-number="字段 属性分组 必须是一个数字。" value='${props.attrgroupid}' data-val-range="请选择属性分组" data-val-range-max="2147483647" data-val-range-min="1" data-val-required="属性分组 字段是必需的。" id="attrgroupid" name="AttrGroupId">
                        <option value="0" >请选择分类</option>
						<option value="44" <c:if test="${props.attrgroupid=='44'}">selected = "selected" </c:if>>主体属性</option>
						<option value="45" <c:if test="${props.attrgroupid=='45'}">selected = "selected" </c:if>>厂家属性</option>
						</select>
                        <span class="field-validation-valid" data-valmsg-for="AttrGroupId" data-valmsg-replace="true"></span>
                    </td>
                </tr><tr><td>&nbsp;</td></tr><%--
                <tr>
                    <td align="right">
                        展示类型：
                    </td>
                    <td>
                        <p>
                            <label><input checked="checked" class="checkbox" data-val="true" data-val-number="字段 展示类型 必须是一个数字。" data-val-range="请选择展示类型" data-val-range-max="1" data-val-range-min="0" data-val-required="展示类型 字段是必需的。" id="ShowType" name="ShowType" type="radio" value="0" /> 文字</label>
                            <label><input class="checkbox" id="ShowType" name="ShowType" type="radio" value="1" /> 图片</label>
                        <span class="field-validation-valid" data-valmsg-for="ShowType" data-valmsg-replace="true"></span>
                            <br />
                        </p>
                    </td>
                </tr>
                --%><tr>
                    <td align="right">
                        筛选属性：
                    </td>
                    <td>
                        <p>
                            <label><input checked="checked" class="checkbox" data-val="true" data-val-number="字段 筛选条件 必须是一个数字。" data-val-range="请选择筛选条件" data-val-range-max="1" data-val-range-min="0" data-val-required="筛选条件 字段是必需的。" id="isfilter" name="IsFilter" type="radio" value="1" />是</label>
                            <label><input class="checkbox" id="IsFilter" name="IsFilter" type="radio" value="0" />否</label>
                        <span class="field-validation-valid" data-valmsg-for="IsFilter" data-valmsg-replace="true"></span>
                            <br />
                        </p>
                    </td>
                </tr><tr><td>&nbsp;</td></tr>
                <tr>
                    <td align="right">
                        显示排序：
                    </td>
                    <td><input class="input" data-val="true" data-val-number="字段 排序 必须是一个数字。" data-val-required="排序不能为空" id="displayorder" name="DisplayOrder" size="3" type="text" value="${props.displayorder}" /><span class="field-validation-valid" data-valmsg-for="DisplayOrder" data-valmsg-replace="true"></span>
                    </td>
                </tr>
                 <tr>
                    <td align="right">
                        SKU排序：
                    </td>
                    <td><input class="input" data-val="true" data-val-number="字段 排序 必须是一个数字。" data-val-required="排序不能为空" id="skuorder" name="SkuOrder" size="3" type="text" value="${props.skuorder}" /><span class="field-validation-valid" data-valmsg-for="DisplayOrder" data-valmsg-replace="true"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        &nbsp;
                    </td>
                    <td>
                        &nbsp;
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
		<br><br><br><br>
</div>
<script type="text/javascript">
	function submitAttrGroup(){
		var attrid = $("#attrid").val().trim();
		var attrgroupid = $("#attrgroupid").val().trim();
		var name = $("#name").val().trim();
		var cateid = $("#cateid").val().trim();
		var displayorder = $("#displayorder").val().trim();
		var isfilter = $("#isfilter").val().trim();
		var skuOrder=$("#skuorder").val().trim();
		$.ajax({
            type: "post",
            dataType:"json",
            url: "${ctx }/admin/category.do?method=updateAttr",
            data: {"attrgroupid":attrgroupid,"name":name,"cateid":cateid,"attrid":attrid,"displayorder":displayorder,"isfilter":isfilter,"skuorder":skuOrder},
            success: function(data){
           	 if(data){
           		$.pdialog.closeCurrent();
           	 }
           	 
            }
   });
	}
</script>
</div>
