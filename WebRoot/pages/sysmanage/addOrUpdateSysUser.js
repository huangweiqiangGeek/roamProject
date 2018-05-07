$(document).ready(function(){
	$("#addOrUpateForm").validate({
		rules: {
			user_code: {required:true},
			user_pass: {required:true,minlength:8},
			cn_name: {required:true},
			mobile: {required:true,isMobile:true},
			birth_date: {date:true},
			zipcode: {isZipCode:true},
			email: {email:true}
		}
	});
});
function saveMethod(){
	var birth_date = $('#birth_date').datebox('getValue');
	var telephone = $('#telephone').val();
	var psd=$('#user_pass').val();
    if(!checkPsd(psd)){
    	return false
    }
	if (checkDate(birth_date)) {
		if($("#addOrUpateForm").valid()){
			if(telephone!=null && telephone!=""){
				if(checkPhone(telephone)==true){
					$("#addOrUpateForm").submit();
				}
			}else{
				$("#addOrUpateForm").submit();
			}
		}
	}
}
function checkDate(s) {
	var reg = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
	if (!s.match(reg)) {
		alert("请输入yyyy-mm-dd格式的日期");
		return false;
	} else {
		return true;
	}
}

function checkPhone(t) {
	 var regPhone = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; 
	if(regPhone.test(t)){
		return true;
	}else{
		alert("请输入正确的手机号码");
		return false;
		
	}
}
function checkPsd(p){
	var reg=/\d{4,}|[a-zA-Z]{4,}/;
	if(reg.test(p)){
		$.messager.alert('温馨提示','请输入正确的密码格式，不能四位连续的数字或字母','',function(){
			$("#user_pass").focus();
		});
		return false
	}else{
		return true
	}
}
function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}
var flag=false;
function editMethod(){
	if(flag){
		$("#user_pass").val($("#oldPass").val());
		$("#user_pass").attr("disabled","disabled");
		flag = false;
	}else{
		$("#user_pass").val("");
		$("#user_pass").removeAttr("disabled");
		flag = true;
	}
}
