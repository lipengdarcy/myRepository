function HsDlg(){
		
}
/**
 * 
 */
HsDlg.defConfirmVal={
	dlgHtml:"<div id='confirm-dlg-id-12012'></div>",
	width:300,
	height:200,
	okFunction:function(args){
		
	},
	cancelFunction:function(args){
		
	},
	text:"",
	title:"确认对话框"
}
/**
 * 
 */
HsDlg.confirmDlg=function(args){
	var dlgid="confirm-dlg-id-12012";
	$("#"+dlgid).remove();
	var option=$.extend({},HsDlg.defConfirmVal,args);
	var dlgCont=$("#"+dlgid);
	var text=option.text;
	var title=option.title;
	var height=option.height;
	var width=option.width;
	var okFunction=option.okFunction;
	var cancelFunction=option.cancelFunction;
	var dlgHtml=option.dlgHtml;
	var $dlgHtml=$(dlgHtml);
	$(document.body).append($dlgHtml);
	$( "#"+dlgid ).dialog({
		autoOpen:true,
		buttons: [
		          {
		            text: "确定",
		            click: function() {
		            	if(okFunction){
		            		okFunction(this);
		            	}
		              $( this ).dialog( "close" );
		            }
		          },
		          {
			            text: "取消",
			            click: function() {
			            	if(cancelFunction){
			            		cancelFunction(this);
			            	}
			              $( this ).dialog( "close" );
			            }
			          }
		        ]
	});
	$( "#"+dlgid ).html(text);
	$( "#"+dlgid ).dialog( "option", "title", title );
	$( "#"+dlgid ).dialog( "option", "width", width );
	$( "#"+dlgid ).dialog( "option", "height", height );
}