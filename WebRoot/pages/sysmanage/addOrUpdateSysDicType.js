$(document).ready(function(){
	
	$("#addOrUpateForm").validate({
		rules: {
			code: {maxlength:50},
			name: {required:true},
			remark: {maxlength:50}
		}
	});
	$("#code_build_type_select").val($("#code_build_type").val());
});

function saveMethod(){
	if($("#addOrUpateForm").valid()){
		$("#addOrUpateForm").submit();
	}
}
function changeCodeBuildType(){
	$("#code_build_type").val($("#code_build_type_select").val());
}
function cancelMethod(){
	window.parent.closeWin(false,"win_div_1");
}
