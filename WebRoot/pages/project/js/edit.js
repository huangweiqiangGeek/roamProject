function saveMethod(){
	var proName =trans($('#proName').val());
	$('#proName').val(proName);
	if(isNull(proName)){
		$.messager.alert('温馨提示','请输入项目名称','',function(){
			$("#proName").focus();
		});
		return false;
	}
	var proExplain =trans($('#proExplain').val());
	$('#proExplain').val(proExplain)
	if(isNull(proExplain)){
		$.messager.alert('温馨提示','请输入项目说明','',function(){
			$("#proExplain").focus();
		});
		return false;
	}
	var proStatus =trans($('#proStatus').combobox('getValue'));
	if(isNull(proStatus) || proStatus==-1){
		$.messager.alert('温馨提示','请选择状态');
		return false;
	}
	var proRemark =trans($('#proRemark').val());//备注
	$('#proRemark').val(proRemark)
	$("#addOrUpateForm").submit();
}

function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}




