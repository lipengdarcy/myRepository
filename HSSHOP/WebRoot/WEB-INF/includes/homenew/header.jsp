<%@ include file="../commons/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/loading/msgbox.css">
<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/global.css"/>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/commoncss.css">
<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/jquery.jqzoom.css"/>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/responsive.css"/>
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/art-dialog/ui-dialog.css"/>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/css/validationEngine.jquery.css">
<link rel="shortcut icon" href="${ctx }/themes/grey/images/logo.ico" />

<script type="text/javascript" src="${ctx }/scripts/grey/jquery-1.11.3.js"></script>
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

<script type="text/javascript" src="${ctx }/scripts/grey/jquery.jslides.js"></script>
<script type="text/javascript" src="${ctx }/scripts/grey/main.js"></script>

<script type="text/javascript" src="${ctx }/scripts/jquery.cookie.js" ></script>
<script type="text/javascript" src="${ctx }/scripts/bootstrap3.3.4/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx }/scripts/loading/msgbox.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine-zh_CN.js"></script> 
<script type="text/javascript" src="${ctx }/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx }/scripts/utils.js" ></script>
<script type="text/javascript" src="${ctx }/scripts/common.js?v=1.0.2"></script>
<script type="text/javascript" src="${ctx }/scripts/myui/hsAreaTabs.js?v=1.0.3"></script>
<script type="text/javascript" src="${ctx }/scripts/myui/hsFloatPanel.js?v=1.0.3" ></script>
<script type="text/javascript" src="${ctx }/scripts/hsAreaUser.js?v=1.0.3"></script>
<script type="text/javascript" src="${ctx }/scripts/art-dialog/dialog-min.js"></script> 
<script type="text/javascript" src="${ctx }/scripts/jquery.form.js"></script>
