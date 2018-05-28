function getWidth(percent){  
   return $(window).width() * percent;  
}  
function xssUtil(str){
	if(str){
		if(str.length == 0) return "";
		s = str.replace(/&/g,"&amp;");
		s = s.replace(/</g,"&lt;");
		s = s.replace(/>/g,"&gt;");
		s = s.replace(/ /g,"&nbsp;");
		//s = s.replace(/\'/g,"&#39;");
		//s = s.replace(/\"/g,"&quot;");
		return s;
	}else{
		return undefined;
	}
}

//xss安全过滤
$(function(){
	var forms = document.getElementsByTagName("form");
    for (var i=0; i<forms.length; i++) {
        var submit = forms[i].submit;
        forms[i].submit = function () {
            // 这个this应该就是  触发submit事件的form
            // jquery 这个表达式应该等价的
            // $(this)  ===  $("#form")
            //console.log("-----------",this)
            var  inps = this.getElementsByTagName("input");
            var texts = this.getElementsByTagName("textarea");
            for(var inp in inps){
            	$("input").eq(inp).val(xssUtil($("input").eq(inp).val()))
            }
            for(var text in texts){
            	$("textarea").eq(text).val(xssUtil($("textarea").eq(text).val()))
            }
            // 对数据做处理。。。
            // 调用之前的回调  并返回
            return (this.submit = submit).bind(this)();
        }.bind(forms[i])
    }
})
//$(document).ready(function(){
//	$(window).resize(function(){  
//	    //alert("change....");  
//	    $("#dg").datagrid({  
//	        width: getWidth(0.6)  
//	    });  
//	});  
//        
//	selectProvince();
//});

/*
 * 
 * 公共 loading
 */
var MaskUtil = (function() {
	var $mask, $maskMsg;
	var defMsg = '系统正在处理，请稍等...';
	function init() {
		if (!$mask) {
			$mask = $("<div class=\"datagrid-mask mymask\"></div>").appendTo(
					"body");
		}
		if (!$maskMsg) {
			$maskMsg = $(
					"<div class=\"datagrid-mask-msg mymask\" style='z-index:99999;'>" + defMsg
							+ "</div>").appendTo("body").css({
				'font-size' : '16px'
			});
		}
		$mask.css({
			width : "100%",
			height : $(document).height(),
		});
		$maskMsg.css({
			left : ($(document.body).outerWidth(true) - 190) / 2,
			top : ($(window).height() - 45) / 2,
		});
	}
	return {
		mask : function(msg) {
			init();
			$mask.show();
			$maskMsg.html(msg || defMsg).show();
		},
		unmask : function() {
			$mask.hide();
			$maskMsg.hide();
		}
	}
}()); 

function ajaxRequest(url,params,headers){
	$.ajax(
		{	url: url,
			type: 'POST',
			data:params,
			dataType: 'json',
			headers:headers,
			timeout: 10000,
			error: function(event, XMLHttpRequest){
				console.log(event,XMLHttpRequest);
				ajaxRequestErrorBackInvokeMethod(event,XMLHttpRequest);
			},
			success: function(data) {
				ajaxRequestSuccessBackInvokeMethod(data);
			}	
		}
	);
}
function ajaxRequest1(url,params){
	if(url.indexOf("?")>0){
		url = url + "&noCacheDealWith="+new Date();
	}else{
		url = url + "?&noCacheDealWith="+new Date();
	}

	$.ajax(
		{	url: url,
			type: 'POST',
			data:params,
			dataType: 'json',
			timeout: 10000,
			error: function(event, XMLHttpRequest){
				ajaxRequestErrorBackInvokeMethod(event,XMLHttpRequest);
			},
			success: function(data) {
				ajaxRequestSuccessBackInvokeMethod1(data);
			}	
		}
	);
}

function ajaxRequest2(url,params){
	if(url.indexOf("?")>0){
		url = url + "&noCacheDealWith="+new Date();
	}else{
		url = url + "?&noCacheDealWith="+new Date();
	}

	$.ajax(
		{	url: url,
			type: 'POST',
			data:params,
			dataType: 'json',
			timeout: 10000,
			error: function(event, XMLHttpRequest){
				ajaxRequestErrorBackInvokeMethod(event,XMLHttpRequest);
			},
			success: function(data) {
				ajaxRequestSuccessBackInvokeMethod2(data);
			}	
		}
	);
}
function ajaxRequest_(url,params){
	$.ajax(
		{	url: url,
			type: 'POST',
			data:params,
			dataType: 'xml',
			timeout: 10000,
			error: function(event, XMLHttpRequest){
				ajaxRequestErrorBackInvokeMethod(event,XMLHttpRequest);
			},
			success: function(data) {
				ajaxRequestSuccessBackInvokeMethod(data);
			}	
		}
	);
}
function trans(str){
	//去掉首空格
	while(str.indexOf(" ")==0){
		str = str.substring(1,str.length);
	}
	//去掉尾空格
	while(str.indexOf(" ")>str.length-1){
		str = str.replace(0,str.length-1);
	}
	return str;
}
function replaceStr(oldStr,oldChar,newChar){
	//去掉首空格
	while(oldStr.indexOf(oldChar)>-1){
		oldStr = oldStr.replace(oldChar,newChar);
	}
	return oldStr;
}

function showDialog(url){
	$('#dialogIframe').attr('src',url);
	$('#dialogDiv').dialog('open');
}

function closeDialog(isReload){
	if(isReload){
		if($('#dg').length>0){
			$('#dg').datagrid("reload");
		}
	}
	if($('#dialogDiv').length>0){
		$('#dialogDiv').dialog('close');
	}
	if($('#win_div').length>0){
		$('#win_div_iframe').attr('src',"");
		$('#win_div').window('close');
	}
}
/**
 * 弹出窗口
 * @param url
 * @param title
 * @param width
 * @param height
 */
function showWin(url,title,width,height){
	$('#win_div_iframe').attr('src',url);
	var $win;
	$win = $('#win_div').window({
	    title: title,
	    width: width,
	    height: height,
	    //top: 6,
	    top: $(document).scrollTop(),
	    left: ($(window).width() - width) * 0.5,
	    shadow: true,
	    modal: true,
	    closed: true,
	    iconCls: 'icon-save',
	    minimizable: false,
	    maximizable: true,
	    collapsible: true
	});
	$win.window('open');
}
function showTwoWin(url,title,width,height){
	
	$('#win_div_iframe').attr('src',url);
	var $win;
	$win = $('#win_div').window({
	    title: title,
	    width: width,
	    height: height,
	    top: ($(document.body).height() - height),
	    left: ($(window).width() - width) * 0.5,
	    shadow: true,
	    modal: true,
	    closed: true,
	    iconCls: 'icon-save',
	    minimizable: false,
	    maximizable: true,
	    collapsible: true
	});
	$win.window('open');
}
function closeWin(isReload,winDivId){
	if(isReload){
		if($('#dg').length>0){
			$('#dg').datagrid("reload");
		}
	}
	if($('#win_div').length>0){
		$('#win_div_iframe').attr('src',"");
		$('#win_div').window('close');
	}
}

$(function(){
	closeDialog(false);
});
function commonGetOptions(selectID,dict_type,selectValue){
	
	$.ajax({
		type:"get",
		url:"sysDic/queryDic",
		contentType : "application/json",
		dataType:"json",
		data:{"dict_type":dict_type},
		success:function(data){
			$("#"+selectID+"").append("<option value=''>请选择</option>");
			if(data!=null&&data!=""){
				$.each(data,function(index,item){
					if(selectValue!=null&&selectValue==item.code){
						$("#"+selectID+"").append("<option value='"+item.code+"' selected='selected'>"+item.name+"</option>");
					}else{
						$("#"+selectID+"").append("<option value='"+item.code+"'>"+item.name+"</option>");
					}
				});
			}		
		}
	});
}
function commonGetUser(selectID,selectValue){
	$.ajax({
		type:"get",
		url:"sysUser/queryUserWithoutId",
		contentType : "application/json",
		dataType:"json",
		success:function(data){
			$("#"+selectID+"").append("<option value=''>请选择</option>");
			if(data!=null&&data!=""){
				$.each(data,function(index,sysUser){
					if(selectValue!=null&&selectValue==sysUser.id){
						$("#"+selectID+"").append("<option value='"+sysUser.id+"' selected='selected'>"+sysUser.cn_name+"</option>");
					}else{
						$("#"+selectID+"").append("<option value='"+sysUser.id+"'>"+sysUser.cn_name+"</option>");
					}
				});
			}		
		}
	});
}

function commonGetCustoms(selectID,dict_type,selectValue){
	$.ajax({
		type:"get",
		url:"sysDic/queryDic",
		contentType : "application/json",
		dataType:"json",
		data:{"dict_type":dict_type},
		success:function(data){
			$("#"+selectID+"").append("<option value=''>请选择</option>");
			if(data!=null&&data!=""){
				$.each(data,function(index,item){
					if(selectValue!=null&&selectValue==item.code){
						$("#"+selectID+"").append("<option value='"+item.code+"' selected='selected'>"+item.code+"-"+item.name+"</option>");
					}else{
						$("#"+selectID+"").append("<option value='"+item.code+"'>"+item.code+"-"+item.name+"</option>");
					}
				});
			}		
		}
	});
}

function isNull(str){
	if(str==null || str=="" || str==undefined ){
		return true;
	}
	return false;
}

function formatDate(now) {
	var year=now.getYear();
	var month=now.getMonth()+1;
	var date=now.getDate();
	var hour=now.getHours();
	var minute=now.getMinutes();
	var second=now.getSeconds();
	return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
	} 

/** * 对Date的扩展，将 Date 转化为指定格式的String * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 
可以用 1-2 个占位符 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) * eg: * (new 
Date()).pattern("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423       
* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
*/          
Date.prototype.pattern=function(fmt) {           
var o = {           
"M+" : this.getMonth()+1, //月份           
"d+" : this.getDate(), //日           
"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
"H+" : this.getHours(), //小时           
"m+" : this.getMinutes(), //分           
"s+" : this.getSeconds(), //秒           
"q+" : Math.floor((this.getMonth()+3)/3), //季度           
"S" : this.getMilliseconds() //毫秒           
};           
var week = {           
"0" : "/u65e5",           
"1" : "/u4e00",           
"2" : "/u4e8c",           
"3" : "/u4e09",           
"4" : "/u56db",           
"5" : "/u4e94",           
"6" : "/u516d"          
};           
if(/(y+)/.test(fmt)){           
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
}           
if(/(E+)/.test(fmt)){           
    fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
}           
for(var k in o){           
    if(new RegExp("("+ k +")").test(fmt)){           
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
    }           
}           
return fmt;           
}
//xss安全防御
var HtmlUtil = {
			 /*1.用浏览器内部转换器实现html转码*/
			 htmlEncode:function (html){
			 //1.首先动态创建一个容器标签元素，如DIV
			 var temp = document.createElement ("div");
			 //2.然后将要转换的字符串设置为这个元素的innerText(ie支持)或者textContent(火狐，google支持)
			 (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
			 //3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
			 var output = temp.innerHTML;
			 temp = null;
			 return output;
			 },
			/*2.用浏览器内部转换器实现html解码*/
			htmlDecode:function (text){
			//1.首先动态创建一个容器标签元素，如DIV
			var temp = document.createElement("div");
			//2.然后将要转换的字符串设置为这个元素的innerHTML(ie，火狐，google都支持)
			temp.innerHTML = text;
			//3.最后返回这个元素的innerText(ie支持)或者textContent(火狐，google支持)，即得到经过HTML解码的字符串了。
			var output = temp.innerText || temp.textContent;
			temp = null;
			return output;
			},
			/*3.用正则表达式实现html转码*/
			htmlEncodeByRegExp:function (str){  
			var s = "";
			if(str.length == 0) return "";
			s = str.replace(/&/g,"&amp;");
			s = s.replace(/</g,"&lt;");
			s = s.replace(/>/g,"&gt;");
			s = s.replace(/ /g,"&nbsp;");
			s = s.replace(/\'/g,"&#39;");
			s = s.replace(/\"/g,"&quot;");
			return s;  
			},
		/*4.用正则表达式实现html解码*/
		htmlDecodeByRegExp:function (str){  
			var s = "";
			if(str.length == 0) return "";
			s = str.replace(/&amp;/g,"&");
			s = s.replace(/&lt;/g,"<");
			s = s.replace(/&gt;/g,">");
			s = s.replace(/&nbsp;/g," ");
			s = s.replace(/&#39;/g,"\'");
			s = s.replace(/&quot;/g,"\"");
			return s;  
			}
		};