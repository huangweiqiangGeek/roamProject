function searchMethod(){
	var code =$('#code').val();
	var name=$('#name').val();
	var params ={code:code,name:name};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("sysDic/addOrUpdateSysDicType","字典新增",600,300);
}
function removeMethod(){
	$.messager.confirm('谨慎操作提示', '删除字典可能引起系统级异常,需谨慎,确认删除字典?', function(r){
		if (r){
			ajaxRequest("sysDic/deleteSysDicType", {id:$("#dg").datagrid("getSelected").id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','字典删除成功!');
	$("#dg").datagrid("reload");
}
function editMethod(){
	showWin("sysDic/addOrUpdateSysDicType?id="+$("#dg").datagrid("getSelected").id,"字典修改",600,300);
}
function dicItemMethod(id,code_build_type){
	showWin("sysDic/sysDicItemManage?id="+id+"&code_build_type="+code_build_type,"字典明细管理",750,450);
}
function formatCodeBuildType(value,rowDatas,rowIndex){
	if(value==1){
		return "自动生成";
	}else{
		return "手工输入";
	}
}
function formatOperate(value,rowDatas,rowIndex){
	return '<a href="javascript:editMethod('+value+')">修改</a>&nbsp;&nbsp;'
			+'<a href="javascript:dicItemMethod('+value+','+rowDatas.code_build_type+')">字典取值</a>&nbsp;&nbsp;'
			+'<a href="javascript:removeMethod('+value+')">删除</a>';
}