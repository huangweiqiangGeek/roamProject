$(document).ready(function(){
	$("#addOrUpateForm").validate({
		rules: {
			plat_no: {required:true},
			certifacate_no: {required:true,number:true},
			type_id: {required:true},
			tool_type_id: {required:true},
			assist_plat_no: {required:true},
			customs_code:{required:true},
			displacement: {number:true}
		}
	});
	chanageSelect();
	var id =  $('#status_id').val();
	if(id != '' && id != 28){
		$('#customs_code').attr('disabled','disabled');
	}
});

function saveMethod(){
	if($("#addOrUpateForm").valid()){
		$("#addOrUpateForm").submit();
	}
}
function cancelMethod(){
	window.parent.closeWin(false);
}

function chanageSelect(){
	if($("#tool_type_id").val()==9){
		document.getElementById("hide_tr").style.display="";
		document.getElementById("hide_tr").style.display="";
	}else{
		document.getElementById("hide_tr").style.display="none";
		document.getElementById("hide_tr").style.display="none";
	}
}
