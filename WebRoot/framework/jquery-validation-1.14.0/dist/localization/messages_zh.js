(function(factory) {
	if (typeof define === "function" && define.amd) {
		define(["jquery", "../jquery.validate"], factory);
	} else {
		factory(jQuery);
	}
}(function($) {

	/*
	 * Translated default messages for the jQuery validation plugin.
	 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
	 */
	$.extend($.validator.messages, {
		required: "这是必填字段",
		remote: "请修正此字段",
		email: "请输入有效的电子邮件地址",
		url: "请输入有效的网址",
		date: "请输入有效的日期",
		dateISO: "请输入有效的日期 (YYYY-MM-DD)",
		number: "请输入有效的数字",
		digits: "只能输入数字",
		creditcard: "请输入有效的信用卡号码",
		equalTo: "你的输入不相同",
		extension: "请输入有效的后缀",
		maxlength: $.validator.format("最多可以输入 {0} 个字符"),
		minlength: $.validator.format("最少要输入 {0} 个字符"),
		rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
		range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
		max: $.validator.format("请输入不大于 {0} 的数值"),
		min: $.validator.format("请输入不小于 {0} 的数值")
	});
	$.validator.addMethod("isMobile", function(value, element) {
		if (value != '') {
			var lengthStr = value.length;
			var mobile = /^1[0-9]{10}$/;
			return (lengthStr == 11 && mobile.exec(value)) ? true : false;
		} else {
			return true;
		}
	}, "@@@@@");
	
	$.validator.addMethod("isChinese", function(value, element) {
		if (value != '') {
			var Str = /^[a-zA-Z\d-_]+$/;
			return Str.exec(value) ? true : false;
		} else {
			return false;
		}
	},"用例名称不能包含中文！");
	
	$.validator.addMethod("isWebserviceUrl", function(value, element) {
		if (value != '') {
			//	^(http:\/\/)((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))(:[1-9]{2,})(\/(\w)+)+(\?wsdl)?$
			//	http://127.0.0.1:8080/agentProject/services/DCCConfigService?wsdl
			var WebserviceUrl=/^(http:\/\/)((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))(:[0-9]{2,})(\/(\w)+)+(\?wsdl)?$/;
			return WebserviceUrl.exec(value) ? true : false;
		}else{
			return false;
		}
	},"无效的Webservice地址");
	
	$.validator.addMethod("selectProvince", function(value) {
		return (value != '');
	}, "请选择省份！");

	$.validator.addMethod("selectSupper", function(value) {
		return (value != '');
	}, "请选择供应商！");
	
}));