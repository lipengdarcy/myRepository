<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<h2 class="contentTitle">${topMenu}</h2>
<div class="pageContent">
	<style>
		.pageFormContent .addTable .textInput {
			float: none;
			margin-bottom:8px;
		}
	</style>
	<form method="post" action="${ctx }/admin/webinfor.do?${getpost}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">
			<div class="addTable">
			
   				 	<dl>
   				 		<dt>上传服务器：</dt>
   				 		<input class="input" name="UploadServer" size="35" type="text" value="${uploadinfor.uploadServer }">
   					  		如果上传到本机请留空
   				 	</dl>   					

        			 <dl style="display:none;">
					    <dt align="right">图片类型：</dt>
					    
					    	<input class="input valid" data-val="true" data-val-required="图片类型不能为空" id="UploadImgType" name="UploadImgType" size="35" type="text" value=".jpg,.jpeg,.png,.gif,.flash">如果有多个值用","分割 <span class="field-validation-valid" data-valmsg-for="UploadImgType" data-valmsg-replace="true"></span>
					   
    				</dl>

					 <dl style="display:none;">
					    <dt align="right">图片大小：</dt>
					    
					    	<input class="input valid" data-val="true" data-val-number="字段 图片大小 必须是一个数字。" data-val-range="图片大小必须大于0" data-val-range-max="2147483647" data-val-range-min="1" data-val-required="图片大小 字段是必需的。" id="UploadImgSize" name="UploadImgSize" size="35" type="text" value="900">单位为字节 <span class="field-validation-valid" data-valmsg-for="UploadImgSize" data-valmsg-replace="true"></span>
					   
					  </dl>

            		 <dl>
					    <dt>水印类型：</dt>
					    <span>
					      <label>
							<input name="WatermarkType"  type="radio" value="0" <c:if test="${uploadinfor.watermarkType=='0' }">checked="checked"</c:if> >无</label>
					      <label>
							<input name="WatermarkType" type="radio" value="1" <c:if test="${uploadinfor.watermarkType=='1' }">checked="checked"</c:if> >文字</label>
					      <label>
							<input name="WatermarkType" type="radio" value="2" <c:if test="${uploadinfor.watermarkType=='2' }">checked="checked"</c:if> >图片</label>
						</span>
   					 </dl>

                    <dl style="display:none;">
                      <dt>水印质量：</dt>
                      <span>
                         <input name="WatermarkQuality" size="35" type="text" value="${uploadinfor.watermarkQuality}"> 
                      </span>
                    </dl>
         			<tr>
    					<dt>水印位置：</dt>
    					<span>
						      <label>
						        <input class="checkbox"  name="WatermarkPosition" type="radio" value="1" 
						        <c:if test="${uploadinfor.watermarkPosition=='1' }">checked="checked"</c:if>>左上</label>
						      <label>
						        <input class="checkbox"  name="WatermarkPosition" type="radio" value="2"
						        <c:if test="${uploadinfor.watermarkPosition=='2' }">checked="checked"</c:if>>左下</label>
						      <label>
						        <input class="checkbox"  name="WatermarkPosition" type="radio" value="3"
						        <c:if test="${uploadinfor.watermarkPosition=='3' }">checked="checked"</c:if>>右上</label>
						      <label>
						        <input class="checkbox"  name="WatermarkPosition" type="radio" value="4"
						        <c:if test="${uploadinfor.watermarkPosition=='4' }">checked="checked"</c:if>>右下</label>
						      <label>
						        <input class="checkbox"  name="WatermarkPosition" type="radio" value="5"
						        <c:if test="${uploadinfor.watermarkPosition=='5' }">checked="checked"</c:if>>中间</label>
    					</span>
    				</dl>
         			<dl class="watermark image">
    					<dt>水印图片：</dt>
    					<span>
   							<input name="WatermarkImg" size="35" type="text" value="${uploadinfor.watermarkImg }"><span>必须是"/WEB-INF/classes/img/watermarks/"文件夹中图片 </span>
    					</span>
    				</dl>
         			<dl class="watermark image" >
    					<dt>水印透明度：</dt>
    					<span>
   							<input name="WatermarkImgOpacity" size="35" type="text" value="${uploadinfor.watermarkImgOpacity }">
    						请输入0-1的小数
    					</span>
    				</dl>
         			<dl class="watermark text" >
    					<dt>水印文字：</dt>
    					<span>
   							<input name="WatermarkText" size="35" type="text" value="${uploadinfor.watermarkText }"></span>
    					</span>
    				</dl>
             		<dl class="watermark text">
	    				<dt>文字字体：</dt>
	   					<span><select id="WatermarkTextFont" name="WatermarkTextFont">
						    	<option value="04b_21">04b_21</option>
								<option value="Agency FB">Agency FB</option>
								<option value="Aharoni">Aharoni</option>
								<option value="Algerian">Algerian</option>
								<option value="Andalus">Andalus</option>
								<option value="Angsana New">Angsana New</option>
								<option value="AngsanaUPC">AngsanaUPC</option>
								<option value="Aparajita">Aparajita</option>
								<option value="Arabic Typesetting">Arabic Typesetting</option>
								<option value="Arial">Arial</option>
								<option value="Arial Black">Arial Black</option>
								<option value="Arial Narrow">Arial Narrow</option>
								<option value="Arial Rounded MT Bold">Arial Rounded MT Bold</option>
								<option value="Arial Unicode MS">Arial Unicode MS</option>
								<option value="Baskerville Old Face">Baskerville Old Face</option>
								<option value="Batang">Batang</option>
								<option value="BatangChe">BatangChe</option>
								<option value="Bauhaus 93">Bauhaus 93</option>
								<option value="Bell MT">Bell MT</option>
								<option value="Berlin Sans FB">Berlin Sans FB</option>
								<option value="Berlin Sans FB Demi">Berlin Sans FB Demi</option>
								<option value="Bernard MT Condensed">Bernard MT Condensed</option>
								<option value="Blackadder ITC">Blackadder ITC</option>
								<option value="Bodoni MT">Bodoni MT</option>
								<option value="Bodoni MT Black">Bodoni MT Black</option>
								<option value="Bodoni MT Condensed">Bodoni MT Condensed</option>
								<option value="Bodoni MT Poster Compressed">Bodoni MT Poster Compressed</option>
								<option value="Book Antiqua">Book Antiqua</option>
								<option value="Bookman Old Style">Bookman Old Style</option>
								<option value="Bookshelf Symbol 7">Bookshelf Symbol 7</option>
								<option value="Bradley Hand ITC">Bradley Hand ITC</option>
								<option value="Britannic Bold">Britannic Bold</option>
								<option value="Broadway">Broadway</option>
								<option value="Browallia New">Browallia New</option>
								<option value="BrowalliaUPC">BrowalliaUPC</option>
								<option value="Brush Script MT">Brush Script MT</option>
								<option value="Buxton Sketch">Buxton Sketch</option>
								<option value="Calibri">Calibri</option>
								<option value="Calibri Light">Calibri Light</option>
								<option value="Californian FB">Californian FB</option>
								<option value="Calisto MT">Calisto MT</option>
								<option value="Cambria">Cambria</option>
								<option value="Cambria Math">Cambria Math</option>
								<option value="Candara">Candara</option>
								<option value="Castellar">Castellar</option>
								<option value="Centaur">Centaur</option>
								<option value="Century">Century</option>
								<option value="Century Gothic">Century Gothic</option>
								<option value="Century Schoolbook">Century Schoolbook</option>
								<option value="Chiller">Chiller</option>
								<option value="Colonna MT">Colonna MT</option>
								<option value="Comic Sans MS">Comic Sans MS</option>
								<option value="Consolas">Consolas</option>
								<option value="Constantia">Constantia</option>
								<option value="Cooper Black">Cooper Black</option>
								<option value="Copperplate Gothic Bold">Copperplate Gothic Bold</option>
								<option value="Copperplate Gothic Light">Copperplate Gothic Light</option>
								<option value="Corbel">Corbel</option>
								<option value="Cordia New">Cordia New</option>
								<option value="CordiaUPC">CordiaUPC</option>
								<option value="Courier New">Courier New</option>
								<option value="Curlz MT">Curlz MT</option>
								<option value="DaunPenh">DaunPenh</option>
								<option value="David">David</option>
								<option value="DFKai-SB">DFKai-SB</option>
								<option value="DilleniaUPC">DilleniaUPC</option>
								<option value="DokChampa">DokChampa</option>
								<option value="Dotum">Dotum</option>
								<option value="DotumChe">DotumChe</option>
								<option value="Ebrima">Ebrima</option>
								<option value="Edwardian Script ITC">Edwardian Script ITC</option>
								<option value="Elephant">Elephant</option>
								<option value="Engravers MT">Engravers MT</option>
								<option value="Eras Bold ITC">Eras Bold ITC</option>
								<option value="Eras Demi ITC">Eras Demi ITC</option>
								<option value="Eras Light ITC">Eras Light ITC</option>
								<option value="Eras Medium ITC">Eras Medium ITC</option>
								<option value="Estrangelo Edessa">Estrangelo Edessa</option>
								<option value="EucrosiaUPC">EucrosiaUPC</option>
								<option value="Euphemia">Euphemia</option>
								<option value="Felix Titling">Felix Titling</option>
								<option value="Footlight MT Light">Footlight MT Light</option>
								<option value="Forte">Forte</option>
								<option value="Franklin Gothic Book">Franklin Gothic Book</option>
								<option value="Franklin Gothic Demi">Franklin Gothic Demi</option>
								<option value="Franklin Gothic Demi Cond">Franklin Gothic Demi Cond</option>
								<option value="Franklin Gothic Heavy">Franklin Gothic Heavy</option>
								<option value="Franklin Gothic Medium">Franklin Gothic Medium</option>
								<option value="Franklin Gothic Medium Cond">Franklin Gothic Medium Cond</option>
								<option value="FrankRuehl">FrankRuehl</option>
								<option value="FreesiaUPC">FreesiaUPC</option>
								<option value="Freestyle Script">Freestyle Script</option>
								<option value="French Script MT">French Script MT</option>
								<option value="Gabriola">Gabriola</option>
								<option value="Garamond">Garamond</option>
								<option value="Gautami">Gautami</option>
								<option value="Georgia">Georgia</option>
								<option value="Gigi">Gigi</option>
								<option value="Gill Sans MT">Gill Sans MT</option>
								<option value="Gill Sans MT Condensed">Gill Sans MT Condensed</option>
								<option value="Gill Sans MT Ext Condensed Bold">Gill Sans MT Ext Condensed Bold</option>
								<option value="Gill Sans Ultra Bold">Gill Sans Ultra Bold</option>
								<option value="Gill Sans Ultra Bold Condensed">Gill Sans Ultra Bold Condensed</option>
								<option value="Gisha">Gisha</option>
								<option value="Gloucester MT Extra Condensed">Gloucester MT Extra Condensed</option>
								<option value="Goudy Old Style">Goudy Old Style</option>
								<option value="Goudy Stout">Goudy Stout</option>
								<option value="Gulim">Gulim</option>
								<option value="GulimChe">GulimChe</option>
								<option value="Gungsuh">Gungsuh</option>
								<option value="GungsuhChe">GungsuhChe</option>
								<option value="Haettenschweiler">Haettenschweiler</option>
								<option value="hakuyoxingshu7000">hakuyoxingshu7000</option>
								<option value="Harlow Solid Italic">Harlow Solid Italic</option>
								<option value="Harrington">Harrington</option>
								<option value="High Tower Text">High Tower Text</option>
								<option value="Impact">Impact</option>
								<option value="Imprint MT Shadow">Imprint MT Shadow</option>
								<option value="Informal Roman">Informal Roman</option>
								<option value="IrisUPC">IrisUPC</option>
								<option value="Iskoola Pota">Iskoola Pota</option>
								<option value="JasmineUPC">JasmineUPC</option>
								<option value="Jokerman">Jokerman</option>
								<option value="Juice ITC">Juice ITC</option>
								<option value="Kalinga">Kalinga</option>
								<option value="Kartika">Kartika</option>
								<option value="Khmer UI">Khmer UI</option>
								<option value="KodchiangUPC">KodchiangUPC</option>
								<option value="Kokila">Kokila</option>
								<option value="Kristen ITC">Kristen ITC</option>
								<option value="Kunstler Script">Kunstler Script</option>
								<option value="Lao UI">Lao UI</option>
								<option value="Latha">Latha</option>
								<option value="Leelawadee">Leelawadee</option>
								<option value="Levenim MT">Levenim MT</option>
								<option value="LilyUPC">LilyUPC</option>
								<option value="Lucida Bright">Lucida Bright</option>
								<option value="Lucida Calligraphy">Lucida Calligraphy</option>
								<option value="Lucida Console">Lucida Console</option>
								<option value="Lucida Fax">Lucida Fax</option>
								<option value="Lucida Handwriting">Lucida Handwriting</option>
								<option value="Lucida Sans">Lucida Sans</option>
								<option value="Lucida Sans Typewriter">Lucida Sans Typewriter</option>
								<option value="Lucida Sans Unicode">Lucida Sans Unicode</option>
								<option value="Magneto">Magneto</option>
								<option value="Maiandra GD">Maiandra GD</option>
								<option value="Malgun Gothic">Malgun Gothic</option>
								<option value="Mangal">Mangal</option>
								<option value="Marlett">Marlett</option>
								<option value="Matura MT Script Capitals">Matura MT Script Capitals</option>
								<option value="Meiryo">Meiryo</option>
								<option value="Meiryo UI">Meiryo UI</option>
								<option value="Microsoft Himalaya">Microsoft Himalaya</option>
								<option value="Microsoft JhengHei">Microsoft JhengHei</option>
								<option value="Microsoft MHei">Microsoft MHei</option>
								<option value="Microsoft NeoGothic">Microsoft NeoGothic</option>
								<option value="Microsoft New Tai Lue">Microsoft New Tai Lue</option>
								<option value="Microsoft PhagsPa">Microsoft PhagsPa</option>
								<option value="Microsoft Sans Serif">Microsoft Sans Serif</option>
								<option value="Microsoft Tai Le">Microsoft Tai Le</option>
								<option value="Microsoft Uighur">Microsoft Uighur</option>
								<option value="Microsoft Yi Baiti">Microsoft Yi Baiti</option>
								<option value="MingLiU">MingLiU</option>
								<option value="MingLiU-ExtB">MingLiU-ExtB</option>
								<option value="MingLiU_HKSCS">MingLiU_HKSCS</option>
								<option value="MingLiU_HKSCS-ExtB">MingLiU_HKSCS-ExtB</option>
								<option value="Miriam">Miriam</option>
								<option value="Miriam Fixed">Miriam Fixed</option>
								<option value="Mistral">Mistral</option>
								<option value="Modern No. 20">Modern No. 20</option>
								<option value="Mongolian Baiti">Mongolian Baiti</option>
								<option value="Monotype Corsiva">Monotype Corsiva</option>
								<option value="MoolBoran">MoolBoran</option>
								<option value="MS Gothic">MS Gothic</option>
								<option value="MS Mincho">MS Mincho</option>
								<option value="MS Outlook">MS Outlook</option>
								<option value="MS PGothic">MS PGothic</option>
								<option value="MS PMincho">MS PMincho</option>
								<option value="MS Reference Sans Serif">MS Reference Sans Serif</option>
								<option value="MS Reference Specialty">MS Reference Specialty</option>
								<option value="MS UI Gothic">MS UI Gothic</option>
								<option value="MT Extra">MT Extra</option>
								<option value="MV Boli">MV Boli</option>
								<option value="Narkisim">Narkisim</option>
								<option value="Niagara Engraved">Niagara Engraved</option>
								<option value="Niagara Solid">Niagara Solid</option>
								<option value="Nyala">Nyala</option>
								<option value="OCR A Extended">OCR A Extended</option>
								<option value="Old English Text MT">Old English Text MT</option>
								<option value="Onyx">Onyx</option>
								<option value="Palace Script MT">Palace Script MT</option>
								<option value="Palatino Linotype">Palatino Linotype</option>
								<option value="Papyrus">Papyrus</option>
								<option value="Parchment">Parchment</option>
								<option value="Perpetua">Perpetua</option>
								<option value="Perpetua Titling MT">Perpetua Titling MT</option>
								<option value="Plantagenet Cherokee">Plantagenet Cherokee</option>
								<option value="Playbill">Playbill</option>
								<option value="PMingLiU">PMingLiU</option>
								<option value="PMingLiU-ExtB">PMingLiU-ExtB</option>
								<option value="Poor Richard">Poor Richard</option>
								<option value="Pristina">Pristina</option>
								<option value="Raavi">Raavi</option>
								<option value="Rage Italic">Rage Italic</option>
								<option value="Ravie">Ravie</option>
								<option value="Rockwell">Rockwell</option>
								<option value="Rockwell Condensed">Rockwell Condensed</option>
								<option value="Rockwell Extra Bold">Rockwell Extra Bold</option>
								<option value="Rod">Rod</option>
								<option value="Sakkal Majalla">Sakkal Majalla</option>
								<option value="Script MT Bold">Script MT Bold</option>
								<option value="Segoe Marker">Segoe Marker</option>
								<option value="Segoe Print">Segoe Print</option>
								<option value="Segoe Script">Segoe Script</option>
								<option value="Segoe UI">Segoe UI</option>
								<option value="Segoe UI Light">Segoe UI Light</option>
								<option value="Segoe UI Semibold">Segoe UI Semibold</option>
								<option value="Segoe UI Symbol">Segoe UI Symbol</option>
								<option value="Segoe WP">Segoe WP</option>
								<option value="Segoe WP Black">Segoe WP Black</option>
								<option value="Segoe WP Light">Segoe WP Light</option>
								<option value="Segoe WP Semibold">Segoe WP Semibold</option>
								<option value="Segoe WP SemiLight">Segoe WP SemiLight</option>
								<option value="Shonar Bangla">Shonar Bangla</option>
								<option value="Showcard Gothic">Showcard Gothic</option>
								<option value="Shruti">Shruti</option>
								<option value="Simplified Arabic">Simplified Arabic</option>
								<option value="Simplified Arabic Fixed">Simplified Arabic Fixed</option>
								<option value="SimSun-ExtB">SimSun-ExtB</option>
								<option value="SketchFlow Print">SketchFlow Print</option>
								<option value="Snap ITC">Snap ITC</option>
								<option value="Stencil">Stencil</option>
								<option value="Sylfaen">Sylfaen</option>
								<option value="Symbol">Symbol</option>
								<option value="Tahoma">Tahoma</option>
								<option value="Tempus Sans ITC">Tempus Sans ITC</option>
								<option value="Times New Roman">Times New Roman</option>
								<option value="Traditional Arabic">Traditional Arabic</option>
								<option value="Trebuchet MS">Trebuchet MS</option>
								<option value="Tunga">Tunga</option>
								<option value="Tw Cen MT">Tw Cen MT</option>
								<option value="Tw Cen MT Condensed">Tw Cen MT Condensed</option>
								<option value="Tw Cen MT Condensed Extra Bold">Tw Cen MT Condensed Extra Bold</option>
								<option value="Utsaah">Utsaah</option>
								<option value="Vani">Vani</option>
								<option value="Verdana">Verdana</option>
								<option value="Vijaya">Vijaya</option>
								<option value="Viner Hand ITC">Viner Hand ITC</option>
								<option value="Vivaldi">Vivaldi</option>
								<option value="Vladimir Script">Vladimir Script</option>
								<option value="Vrinda">Vrinda</option>
								<option value="Webdings">Webdings</option>
								<option value="Wide Latin">Wide Latin</option>
								<option value="Wingdings">Wingdings</option>
								<option value="Wingdings 2">Wingdings 2</option>
								<option value="Wingdings 3">Wingdings 3</option>
								<option value="Yu Gothic">Yu Gothic</option>
								<option value="仿宋">仿宋</option>
								<option value="华文中宋">华文中宋</option>
								<option value="华文仿宋">华文仿宋</option>
								<option value="华文宋体">华文宋体</option>
								<option value="华文彩云">华文彩云</option>
								<option value="华文新魏">华文新魏</option>
								<option value="华文楷体">华文楷体</option>
								<option value="华文琥珀">华文琥珀</option>
								<option value="华文细黑">华文细黑</option>
								<option value="华文行楷">华文行楷</option>
								<option value="华文隶书">华文隶书</option>
								<option value="叶根友毛笔行书2.0版">叶根友毛笔行书2.0版</option>
								<option value="宋体">宋体</option>
								<option value="幼圆">幼圆</option>
								<option value="微软雅黑">微软雅黑</option>
								<option value="新宋体">新宋体</option>
								<option value="方正姚体">方正姚体</option>
								<option value="方正等线">方正等线</option>
								<option value="方正舒体">方正舒体</option>
								<option value="楷体">楷体</option>
								<option value="隶书">隶书</option>
								<option value="黑体">黑体</option>
							</select>
					    </span>
					   </dl>
         				<dl class="watermark text" >
    						<dt>文字大小：</dt>
    						<span>
   								<input name="WatermarkTextSize" size="35" type="text" value="${uploadinfor.watermarkTextSize }">
    						</span>
   						 </dl>

         				<dl>
						    <dt>品牌缩略图：</dt>
						    <span>
						   		<input name="BrandThumbSize" size="35" type="text" value="${uploadinfor.brandThumbSize }">格式为"宽_高",如果有多个用","分割 
						   		<span class="field-validation-valid" data-valmsg-for="BrandThumbSize" data-valmsg-replace="true"></span>
						    </span>
    					</dl>

         				<dl>
    						<dt>商品展示缩略图：</dt>
    						<span>
   								<input name="ProductShowThumbSize" size="35" type="text" value="${uploadinfor.productShowThumbSize }">格式为"宽_高",如果有多个用","分割 
   								<span class="field-validation-valid" data-valmsg-for="ProductShowThumbSize" data-valmsg-replace="true"></span>
    						</span>
    					</dl>

        				<dl>
    						<dt>用户头像缩略图：</dt>
    						<span>
   								<input  name="UserAvatarThumbSize" size="35" type="text" value="${uploadinfor.userAvatarThumbSize }">格式为"宽_高",如果有多个用","分割 
   								<span class="field-validation-valid" data-valmsg-for="UserAvatarThumbSize" data-valmsg-replace="true"></span>
    						</span>
    					</dl>

         				<dl>
						    <dt>用户等级头像缩略图：</dt>
						    <span>
						   		<input name="UserRankAvatarThumbSize" size="35" type="text" value="${uploadinfor.userRankAvatarThumbSize }">格式为"宽_高",如果有多个用","分割 
						   		<span class="field-validation-valid" data-valmsg-for="UserRankAvatarThumbSize" data-valmsg-replace="true"></span>
						    </span>
						 </dl>
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
	<script type="text/javascript">
		$("#WatermarkTextFont").find("option[value='${uploadinfor.watermarkTextFont }']").attr("selected","selected");
	</script>
</div>
