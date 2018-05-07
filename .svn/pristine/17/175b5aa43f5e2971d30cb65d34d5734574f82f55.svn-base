jQuery.extend(jQuery.validator.messages, {
    required: "",
    remote: "",
    email: "",
	url: "",
	date: "",
	dateISO: "",
	number: "",
	digits: "",
	creditcard: "",
	equalTo: "",
	accept: "",
	maxlength: jQuery.validator.format(""),
	minlength: jQuery.validator.format(""),
	rangelength: jQuery.validator.format(""),
	range: jQuery.validator.format(""),
	max: jQuery.validator.format(""),
	min: jQuery.validator.format("")
});
jQuery.validator.addMethod("isMobile", function(value, element) {
	 if(value!=''){
		 var length = value.length;
		 var mobile = /^1[0-9]{10}$/;
	     return (length == 11 && mobile.exec(value))? true:false;
	 }else{
		 return true;
	 }
 }, "");
//邮编验证  
jQuery.validator.addMethod("isZipCode", function(value, element) {
    if(value!=''){
    	var tel = /^[0-9]{6}$/;
    	return (tel.exec(value))?true:false;
    }else{
    	return true;
    }
}, "");





