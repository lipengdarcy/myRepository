<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
//搜索
function search(keyword) {
	if (keyword == undefined || keyword == null || keyword.length < 1) {
		alert("请输入关键词");		
	} else {
		window.location.href = "${ctx}/product/search.do?keyword=" + keyword;
	}
}
</script>
<!--bof header-->
<div class="header">
  <div class="wrap clearfix">
    <div class="logo left" title="红狮水泥商城">
    	<a href="${ctx}/index.do"  title="红狮水泥商城">红狮水泥商城</a>
    </div>
    <div class="adress left" id="add-hs-tabs" >     	
		<div role="hs-area-sel" write-to-cookie max-layer="5" min-layer="5"
			seled-name-show=".show-name-ele" cid="192224-212131-214785-0-0">
			<div show-hs-area-sel>
				<span>配送地址：</span><p class="show-name-ele">浙江 - 金华 - 兰溪</p>
			</div>
			<div class="area-float-panel float-panel">
				<div class="panel-close-btn">
					<span class="glyphicon glyphicon-remove"></span>
				</div>
				<div role="tabpanel" class="area-tabs">
					<ul class="nav nav-tabs" role="tablist"></ul>
					<div class="tab-content"></div>
				</div>
			</div>
		</div>
    </div>
    <div class="search right">
      <form>
        <fieldset class="clearfix" >
          <input type="text" id="keyword" name="keyword"   autocomplete="off"  value='' placeholder="请输入您要查找的内容" class="s-input" 
         	onkeydown="javascript:if(event.keyCode==13) search(document.getElementById('keyword').value);"/>
          <input type="button" name=""  value="搜索" class="s-button" onclick="search(document.getElementById('keyword').value)"/>
        </fieldset>
      </form>
      <p>热门搜索： 
      	<a href="${ctx}/product/search.do?keyword=红狮">红狮</a>
      	<a href="${ctx}/product/search.do?keyword=水泥">水泥</a>
      </p>
    </div>
  </div>
</div>
<!--eof header-->