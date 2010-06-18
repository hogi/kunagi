/*
Kill ie6 by jonk - http://kill-ie6.pirateboy.net
*/
if (document.all) {
	var ua = window.navigator.userAgent;
	var msie = ua.indexOf ( "MSIE " );
	var ieVer = parseInt ( ua.substring ( msie+5, ua.indexOf ( ".", msie ) ) );
	if (ieVer < 7) {
		if (typeof(killie6text)=='undefined') {
			killie6text = '<span class="kill-ie6_bold">Warning!</span>&nbsp;You\'re using Internet Explorer 6, that\'s a <span class="kill-ie6_bold">big security risk!</span> <a href="http://www.microsoft.com/ie" target="_blank">Upgrade</a> or download a better, faster &amp; safer browser: <a href="http://www.mozilla.com/firefox/" target="_blank">Firefox</a>, <a href="http://www.google.com/chrome/" target="_blank">Chrome</a> or <a href="http://www.apple.com/safari/" target="_blank">Safari</a>.';
		}
		document.write('<style type="text/css">');
		document.write('#kill-ie6_oldBrowserWarning * {');
			document.write('font-family:verdana,arial,sans-serif;font-size:10px;color:#000;border:0px;margin:0px;padding:0px;position:static;text-transform:none;');
		document.write('}');
		document.write('#kill-ie6_oldBrowserWarning {');
			document.write('display:block;width:100%;height:25px;line-height:25px;background-color:#F47777;border-bottom:1px solid #000;overflow:hidden;');
		document.write('}');
		document.write('#kill-ie6_oldBrowserWarning a {');
			document.write('text-decoration:underline;color:#0000ee;');
		document.write('}');
		document.write('#kill-ie6_oldBrowserWarning a#kill-ie6_close {');
			document.write('color:#000;text-decoration:none;font-weight:bold;float:right;margin-right:5px;height:23px;line-height:23px;');
		document.write('}');
		document.write('#kill-ie6_oldBrowserWarning #kill-ie6_text {');
			document.write('margin:0px 5px 0px 5px;text-align:left;');
		document.write('}');
		document.write('#kill-ie6_oldBrowserWarning #kill-ie6_text .kill-ie6_bold {');
			document.write('font-weight:bold;');
		document.write('}');
		document.write('</style>');
		document.write('<div id="kill-ie6_oldBrowserWarning">');
			document.write('<a id="kill-ie6_close" href="javascript:void(0);" onclick="javascript:document.getElementById(\'kill-ie6_oldBrowserWarning\').style.display=\'none\';">X</a>');
			document.write('<div id="kill-ie6_text">' + killie6text + '</div>');
		document.write('</div>');
	} 
}
	