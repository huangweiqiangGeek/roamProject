function searchMethod(){
	var user_name=$('#user_name').val();
	var module=$('#module').val();
	var operate = $('#operate').datebox('getValue');
	var operate_result = $('#operate_result').datebox('getValue');
	var etime = $('#etime').datebox('getValue');
	var params ={user_name:user_name,module:module,operate:operate,operate_result:operate_result,stime:stime,etime:etime};
	$("#dg").datagrid("reload",params);
}