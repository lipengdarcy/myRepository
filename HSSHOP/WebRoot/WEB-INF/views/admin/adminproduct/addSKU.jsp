<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<h2 class="contentTitle">${topMenu}</h2>
<div class="pageContent">
	<form method="post" action="${ctx }/admin/adminproduct.do?${getpost}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="#"><span>基本信息</span></a></li>
							<li><a href="#"><span>规格信息</span></a></li>
							<li><a href="#"><span>辅助信息</span></a></li>
							<li><a href="#"><span>详细描述</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent" style="height:300px">
					<div>
						<dl>
							<dt>SKU组名称</dt>
							<input type="text" id="Skugname" name="skuGroup.skugname"  size="80" value="">
						</dl>
						<dl style="display:none;">
							<dt>商品名称</dt>
							<input type="text" id="ProductName" name="product.name" size="80" value="">
							<input type="hidden" name="pageid" value="${pageid}">
						</dl>
						<dl style="display:none;">
							<dt>商品货号</dt>
							<input type="text" id="PSN" name="product.psn" size="35" value="">
						</dl>
						<dl class="sel-cate-infor-content" attr-val-url="category.do?method=getSKUCateAttrVals">
							<dt>选择分类</dt>
							<span class="button sel-cate-dlg-btn"><span class="show-cate-name">选择分类</span></span>
                            <input type="hidden" productCateid name="product.cateid" value="-1">
                           
						</dl>
						<dl style="display:none;">
							<dt>选择品牌</dt>
                            <!--input type="hidden" class="BrandId" id="BrandId" name="product.brandid" value="1"-->
						</dl>
						<dl>
							<dt>成本价</dt>
							<input type="text" name="product.costprice" class="required number" value="0">
						</dl>
						<dl>
							<dt>市场价</dt>
							<input type="text" name="product.marketprice" class="required number" value="0">
						</dl>
						<dl>
							<dt>本店价</dt>
							<input type="text" name="product.shopprice" class="required number" value="0">
						</dl>
					</div>
					<div class="attr-infor-content">请先选择属性信息</div>
					<div>
						<dl>
							<dt>重量</dt>
							<input type="text" name="product.weight" class="required number" value="0">
							<span>
								<select name="product.weightunitid">
									<c:forEach var="unitItem" items="${weightUnitList }">
										<option value="${unitItem.unitid }">${unitItem.unitname }</option>
									</c:forEach>
								</select>
							</span>
						</dl>
						<dl>
							<dt>排序</dt>
							<input type="text" name="product.displayorder" class="required digits" value="0">
						</dl>
						<dl>
							<dt>计量单位</dt>							
							<select name="product.quantityunitid">
								<c:forEach var="unitItem" items="${unitlist }">
									<option value="${unitItem.unitid }">${unitItem.unitname }</option>
								</c:forEach>
							</select>
						</dl>
						<div style="display:none;">
							<dl>
								<dt>库存数量</dt>
								<input type="text" name="productstocks.number" value="0">
							</dl>
							<dl>
								<dt>库存警戒线</dt>
								<input type="text" name="productstocks.limit" value="0">
							</dl>
						</div>
						
						<dl>
							<dt>是否上架</dt>
							<p>
	                            <label><input name="product.state" type="radio" value="0">是</label>
	                            <label><input checked="checked" name="product.state" type="radio" value="1">否</label>
                            <br>
                        </p>
						</dl>
						<dl>
							<dt>商品标签</dt>
							<p>
	                            <label><input name="product.ishot" type="checkbox" value="1">热销</label>
								<!-- 	                            <label><input name="product.isbest" type="checkbox" value="1">精品</label> -->
								<label><input name="product.isnew" type="checkbox" value="1">新品</label>
                            <br>
                        </p>
						</dl>
					</div>
					<div>
						<textarea class="editor" 
						upImgUrl="${ctx }/admin/uploadfile.do?method=xeupload"   
                        upImgExt="jpg,jpeg,gif,png"  
						name="product.description" style="height:270px" cols="80"></textarea>
					</div>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
			<div class="sku-infor-list">
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>

</div>
