function searchMethod(state){
	var name=$('#name_'+state).val();
	var params ={name:name};
	$("#dg_"+state).datagrid("reload",params);
}
var actionState = 1;
function addMethod(){
	actionState = 1;
	var ids = [];
	var rows = $('#dg_2').datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
	    ids.push(rows[i].id);
	}
	if(ids.length>0){
		$.messager.confirm('谨慎操作提示', '确认添加角色到岗位?', function(r){
			if(r){
				ajaxRequest("sysPost/addRoleToPost", {id:$('#post_id').val(),entity_ids:ids+""});
			}
		});
	}else{
		$.messager.alert('温馨提示','请选择要添加到岗位的角色!','warning');
	}
}
function removeMethod(){
	actionState=2;
	var ids = [];
	var rows = $('#dg_1').datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
	    ids.push(rows[i].id);
	}
	if(ids.length>0){
		$.messager.confirm('谨慎操作提示', '确认从岗位移除角色?', function(r){
			if(r){
				ajaxRequest("sysPost/deleteRoleFromPost", {id:$('#post_id').val(),entity_ids:ids+""});
			}
		});
	}else{
		$.messager.alert('温馨提示','请选择要从岗位移除的角色!','warning');
	}
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	if(actionState==1){
		//$.messager.alert('操作提示','角色添加到岗位成功!');
	}else{
		//$.messager.alert('操作提示','岗位移除角色成功!');
	}
	searchMethod(1);searchMethod(2);
}
