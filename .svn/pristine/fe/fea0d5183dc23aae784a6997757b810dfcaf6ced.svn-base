function saveMethod(){
	var name =trans($('#name').val());
	if(isNull(name)){
		$.messager.alert('温馨提示','请输入项目名称','',function(){
			$("#name").focus();
		});
		return false;
	}
	var introduction =trans($('#introduction').val());
	if(isNull(introduction)){
		$.messager.alert('温馨提示','请输入项目说明','',function(){
			$("#introduction").focus();
		});
		return false;
	}
	$("#addOrUpateForm").submit();
}
function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}




