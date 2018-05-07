function searchMethod(){
	var user_code =$('#user_code').val();
	var user_name=$('#user_name').val();
	var ip=$('#ip').val();
	var stime = $('#stime').datebox('getValue');
	var etime = $('#etime').datebox('getValue');
	var params ={user_code:user_code,user_name:user_name,ip:ip,stime:stime,etime:etime};
	$("#dg").datagrid("reload",params);
}

function removeMethod(user_id){
	$.messager.confirm('谨慎操作提示', '确认强制用户退出?', function(r){
		if (r){
			ajaxRequest("sysAccessUser/quitSysAccessUser", {user_id:user_id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	$.messager.alert('操作提示','用户强制推出成功!');
	$("#dg").datagrid("reload");
}
function formatOperate(value,rowDatas,rowIndex){
	return '<a href="javascript:removeMethod('+value+')">强制退出</a>&nbsp;&nbsp;';
}