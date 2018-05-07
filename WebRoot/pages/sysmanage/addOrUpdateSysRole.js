$(document).ready(function(){
	$("#addOrUpateForm").validate({
		rules: {
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
