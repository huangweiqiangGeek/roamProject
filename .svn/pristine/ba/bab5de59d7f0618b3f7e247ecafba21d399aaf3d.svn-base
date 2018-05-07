function saveMethod(){
	var proId =trans($('#proId').val());
	if(isNull(proId)){
		$.messager.alert('温馨提示','请输入产品标识');
		return false;
	}
	var sceneType = trans($('#sceneType').combobox('getValue'));
	if(isNull(sceneType) || sceneType==-1){
		$.messager.alert('温馨提示','请选择场景');
		return false;
	}
	var params ={
			uCUserID:trans($('#uCUserID').val()),
			proId:proId,
			sceneType:sceneType,
			ipAddress:trans($('#ipAddress').val()),
			hostName:trans($('#hostName').val()),
			hostPassWord:trans($('#hostPassWord').val()),
			scriptUrl:trans($('#scriptUrl').val()),
			field1:trans($('#field1').val())
			};
	$.ajax({
		type:"POST",
		url:"regressionUseCase/getBillTime",
		dataType:"json",
		data:params,
		success:function(data){
			window.parent.$("#spBillTime").html(data.sTime);
			cancelMethod();
		}
	});
	
	
}


function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}

