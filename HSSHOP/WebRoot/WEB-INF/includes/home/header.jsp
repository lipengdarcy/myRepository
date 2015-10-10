<%@ include file="../commons/taglibs.jsp"%>
<link href="${ctx }/themes/default/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/themes/default/css/loading/msgbox.css" rel="stylesheet" type="text/css">
<link href="${ctx }/themes/default/css/commoncss.css" rel="stylesheet" type="text/css">
<link href="${ctx }/themes/default/css/introjs.css" rel="stylesheet" type="text/css">
<link href="${ctx }/scripts/art-dialog/ui-dialog.css" rel="stylesheet" type="text/css">
<link href="${ctx }/themes/default/css/validationEngine.jquery.css" rel="stylesheet" type="text/css">
<script src="${ctx }/scripts/jquery-1.11.3.js" type="text/javascript" ></script>
<script type="text/javascript">	
	(function(a, b) {
	    function c(a) {
	        a = a.toLowerCase();
	        var b = /(chrome)[ \/]([\w.]+)/.exec(a) || /(webkit)[ \/]([\w.]+)/.exec(a) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(a) || /(msie) ([\w.]+)/.exec(a) || a.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(a) || [];
	        return {
	            browser: b[1] || "",
	            version: b[2] || "0"
	        }
	    }
	    if (!a.browser) {
	        var d = c(navigator.userAgent),
	        e = {};
	        d.browser && (e[d.browser] = !0, e.version = d.version),
	        e.chrome ? e.webkit = !0 : e.webkit && (e.safari = !0),
	        a.browser = e
	    }
	}) (jQuery);

</script>

<script src="${ctx }/scripts/jquery-browser.js" type="text/javascript" ></script>
<script src="${ctx }/scripts/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx }/scripts/bootstrap3.3.4/js/bootstrap.js" type="text/javascript"></script>
<script src="${ctx }/scripts/intro.js" type="text/javascript"></script>
<script src="${ctx }/scripts/loading/msgbox.js" type="text/javascript"></script>
<script src="${ctx }/scripts/utils.js" type="text/javascript"></script>
<script src="${ctx }/scripts/common.js?v=1.0.2" type="text/javascript"></script>
<script src="${ctx }/scripts/myTools.js?v=1.0.2" type="text/javascript"></script>
<script src="${ctx }/scripts/myui/hsAreaTabs.js?v=1.0.3" type="text/javascript" ></script>
<script src="${ctx }/scripts/myui/hsFloatPanel.js?v=1.0.3" type="text/javascript" ></script>
<script src="${ctx }/scripts/hsAreaUser.js?v=1.0.3" type="text/javascript"></script>
<script src="${ctx }/scripts/jquery.validationEngine-zh_CN.js"></script> 
<script src="${ctx }/scripts/art-dialog/dialog-min.js"></script> 
<script src="${ctx }/scripts/jquery.validationEngine.js"></script>
<script src="${ctx }/scripts/date/laydate.js"></script>
<script src="${ctx }/scripts/jquery.form.js"></script>
