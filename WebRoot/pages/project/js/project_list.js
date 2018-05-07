
function searchMethod(){
	var proType = $('#proType').val();
	var proProvince =$('#proProvince').val();
	var proName = trans($('#proName').val());
	var proNumber = trans($('#proNumber').val());
	var proStatus= $('#proStatus').val();
	var params ={proType:proType,proProvince:proProvince,proName:proName,proNumber:proNumber,proStatus:proStatus};
	$("#dg").datagrid("reload",params);
}

function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function fmtHref(value,rowDatas,rowIndex){
	return '<a href="useCase/listInit?proID='+rowDatas.id+'" style="cursor: pointer;color: #000000;text-decoration: none;">'+value+'</a>';
}