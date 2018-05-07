function searchMethod(){
	var code =$('#code').val();
	var name=$('#name').val();
	var params ={code:code,name:name};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("sysDic/addOrUpdateSysDicItem?dic_type_id="+$('#dic_type_id').val()
			+"&code_build_type="+$('#code_build_type').val(),"字典明细添加",400,260);
}
function removeMethod(value){
	$.messager.confirm('谨慎操作提示', '删除字典明细可能引起系统级异常,需谨慎,确认删除字典?', function(r){
		if (r){
			ajaxRequest("sysDic/deleteSysDicItem", {id:value});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','字典明细删除成功!');
	$("#dg").datagrid("reload");
}
function editMethod(id){
	showWin("sysDic/addOrUpdateSysDicItem?id="+id+"&code_build_type="+$('#code_build_type').val(),"字典明细修改",400,260);
}
function formatOperate(value,rowDatas,rowIndex){
	return '<a href="javascript:editMethod('+value+')">修改</a>&nbsp;&nbsp;'
			+'<a href="javascript:removeMethod('+value+')">删除</a>';
}