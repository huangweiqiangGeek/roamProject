$(document).ready(function(){
	$("#addOrUpateForm").validate({
		rules: {
			certifacate_no: {number:true},
			displacement: {number:true},
			plat_no: {required:true},
			certifacate_no: {required:true},
			type_id: {required:true},
			customs_code: {required:true},
			cop_code:{required:false},
			customs_code:{required:true},
			tool_type_id: {required:true}
		}
	});
	chanageSelect();
	
	
});
function saveMethod(){

	if($("#addOrUpateForm").valid()){
		$.messager.confirm('谨慎操作提示', '确认变更车辆?', function(r){
			if (r){
				$("#addOrUpateForm").submit();
			}
		});
	}
}

function ajaxRequestSuccessBackInvokeMethod(data) {
	$.messager.alert('操作提示','车辆变更成功!查看该车辆，请去查询页面');
	window.parent.closeDialog(false);
}

function cancelMethod(){
	window.parent.closeDialog(false);
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