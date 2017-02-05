/**
 * 
 */

/**
 * ajax预处理
 */
jQuery.extend({ajaxPretrate:function(){
	$(document).ajaxSend(function(event,XMLHttpRequest,options){
		XMLHttpRequest.setRequestHeader(HTTP_REQUEST_TYPE_KEY, HTTP_REQUEST_TYPE_AJAX);
	}).ajaxStart(function() {
//		$.forbidALL();
	}).ajaxSuccess(function(event, XMLHttpRequest, ajaxOptions) {
		$.ajaxSuccessException(event, XMLHttpRequest, ajaxOptions);
	}).ajaxError(function(event, XMLHttpRequest, ajaxOptions, thrownError) {
		$.ajaxErrorException(event, XMLHttpRequest, ajaxOptions);
	}).ajaxComplete(function() {
//		$.allowAll();
	});
}});

/**
 * 递归循环来对一个js对象进行转义
 */
jQuery.extend({objectConvert:function(o){
	for(var name in o){
		if(o[name]==null){
			o[name] = "";
		}else{
			if(typeof o[name] =="object"){
				$.objectConvert(o[name]);
			}else{
				o[name] = $.stringConvert(o[name]);
			}
		}
	}
	return o;
}});

/**
 * 对一个字符串进行转义
 */
jQuery.extend({stringConvert:function(string){
	if (string != null&&(typeof string === "string")) {
		string = string.replace(/&/ig, "&amp;");
		string = string.replace(/ /ig, "&nbsp;");
		string = string.replace(/</ig, "&lt;");
		string = string.replace(/>/ig, "&gt;");
		string = string.replace(/'/ig, "&apos;");
		string = string.replace(/"/ig, "&quot;");
        string = string.replace(/\r\n|\r|\n|\n\r/ig, "<br>");
		string = string.replace(/'\'/g, "&#92;");
	}
	if(string==null){
		string = "";
	}
	return string;
}});