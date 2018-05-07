$(document).ready(function(){
	
	$("#addOrUpateForm").validate({
		rules: {
			code: {maxlength:50},
			name: {required:true}
		}
	});
});

function saveMethod(){
	if($("#addOrUpateForm").valid()){
		$("#addOrUpateForm").submit();
	}
}
function cancelMethod(){
	window.parent.closeWin(false,"win_div_1");
}