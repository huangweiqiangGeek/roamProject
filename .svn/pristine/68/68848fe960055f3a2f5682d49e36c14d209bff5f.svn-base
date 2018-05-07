$(document).ready(function(){
	$("#addOrUpateForm").validate({
		rules: {
			name: {required:true},
			job: {required:true}
		}
	});
	$('#provinceSpell').combobox({    
	    url:'sysProvince/queryAllSysprovinceList',    
	    valueField:'provinceSpell',    
	    textField:'provinceName'   
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
