function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','角色删除成功!');
	$("#dg").datagrid("reload");
}
function searchMethod(){
	var name =$('#name').val();
	var params ={name:name};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("sysRole/addOrUpdateSysRole","添加角色",700,300);
}
function removeMethod(role_id){
	$.messager.confirm('谨慎操作提示', '确认删除角色?', function(r){
		if (r){
			ajaxRequest("sysRole/deleteSysRole", {id:role_id});
		}
	});
}

function editMethod(role_id){
	showWin("sysRole/addOrUpdateSysRole?id="+role_id,"修改角色",700,300);
}
function roleMenuManage(role_id,role_name){
	showWin("sysRole/sysRoleMenuCheck?id="+role_id,"角色菜单管理："+role_name,400,450);
}
function formatOperate(value,rowDatas,rowIndex){
	return '<a href="javascript:roleMenuManage('+value+',\''+rowDatas.name+'\')">角色菜单</a>&nbsp;&nbsp;'
			+'<a href="javascript:editMethod('+value+')">修改</a>&nbsp;&nbsp;'
			+'<a href="javascript:removeMethod('+value+')">删除</a>';
}