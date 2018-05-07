function searchMethod(state){
	var cn_name=$('#cn_name_'+state).val();
	var user_code =$('#user_code_'+state).val();
	var params ={user_code:user_code,cn_name:cn_name};
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
		$.messager.confirm('谨慎操作提示', '确认添加人员到岗位?', function(r){
			if(r){
				ajaxRequest("sysPost/addUserToPost", {id:$('#post_id').val(),entity_ids:ids+""});
			}
		});
	}else{
		$.messager.alert('温馨提示','请选择要添加到岗位的人员!','warning');
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
		$.messager.confirm('谨慎操作提示', '确认从岗位移除人员?', function(r){
			if(r){
				ajaxRequest("sysPost/deleteUserFromPost", {id:$('#post_id').val(),entity_ids:ids+""});
			}
		});
	}else{
		$.messager.alert('温馨提示','请选择要从岗位移除的人员!','warning');
	}
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	if(actionState==1){
		//$.messager.alert('操作提示','人员添加到岗位成功!');
		searchMethod(1);searchMethod(2);		
	}else if(actionState==2){
		//$.messager.alert('操作提示','岗位移除人员成功!');
		searchMethod(1);searchMethod(2);
	}else if(actionState==3){
		searchMethod(1);
	}
	
	$("#dg_1").datagrid("clearChecked");
	$("#dg_2").datagrid("clearSelections");
}
function formatIsFirst(value,rowDatas,rowIndex){
	var user_id = rowDatas.id;
	if(value==1){
		return '<a href="javascript:changeIsFirstPost('+value+","+user_id+')">是</a>';
	}else{
		return '<a href="javascript:changeIsFirstPost('+value+","+user_id+')">否</a>';
	}
}