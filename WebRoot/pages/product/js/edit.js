function saveMethod(){
	var productId = $('#productId').val();
	if(isNull(productId)){
		$.messager.alert('温馨提示','请输入产品ID','',function(){
			$("#productId").focus();
		});
		return false;
	}
	var productName = $('#productName').val();productType
	if(isNull(productName)){
		$.messager.alert('温馨提示','请输入产品名称','',function(){
			$("#productName").focus();
		});
		return false;
	}
	$("#addUpateForm").submit();
}
function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}