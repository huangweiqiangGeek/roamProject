
function searchMethod(){
	var templateName = $('#templateName').val();
	var provinceID =$('#provinceID').val();
	var templateStatus=$('#templateStatus').val();
	var params ={templateName:templateName,provinceID:provinceID,templateStatus:templateStatus};
	$("#dg").datagrid("reload",params);
}

function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function formatCheckBox(value,rowDatas,rowIndex){
	return '<input type="button" id="txt'+value+'" data="'+rowDatas.attrJson+'" name="'+rowDatas.templateName+'"  onclick="getTemplateInfo('+value+')" value="选择" style="cursor: pointer;">';
	//<input type="checkbox" id="cb'+value+'" name="cbId" value="'+value+'">
	
}

function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}