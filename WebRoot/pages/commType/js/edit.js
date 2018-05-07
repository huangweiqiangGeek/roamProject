function saveMethod(){
	var commTypeName =trans($('#commTypeName').val());
	if(isNull(commTypeName)){
		$.messager.alert('温馨提示','请输入名称');
		return false;
	}
	var affiliationType = trans($('#affiliationType').val());
	if(isNull(affiliationType) || affiliationType==-1){
		$.messager.alert('温馨提示','请选择归属类型');
		return false;
	}
	$("#addOrUpateForm").submit();
}


function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}

