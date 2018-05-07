function searchMethod(){
	var tool_type_id =$('#tool_type_id').val();
	var type_id =$('#type_id').val();
	var plat_no =$('#plat_no').val();
	var customs_code=$('#customs_code').val();
	var plat_status_id=$('#plat_status_id').val();
	
	var status_id="";
	if($('#status_id')!=null){
		status_id=$('#status_id').val();
	}
	var params ={tool_type_id:tool_type_id,
			type_id:type_id,plat_no:plat_no,customs_code:customs_code
			,status_id:status_id,plat_status_id:plat_status_id};
	$("#dg").datagrid("reload",params);
}

function editMethod(id){
	$.messager.confirm('谨慎操作提示', '确认变更车辆?', function(r){
		if (r){
			ajaxRequest1("customVehicles/UpdateCustomVehicles", {id:id});
		}
	});
}

function ajaxRequestSuccessBackInvokeMethod1(data) {
	$.messager.alert('操作提示','车辆变更成功!');
	$("#dg").datagrid("reload");
}

function removeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认删除车辆?', function(r){
		if (r){
			ajaxRequest("customVehicles/deleteCustomVehicles", {id:id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	$.messager.alert('操作提示','车辆删除成功!');
	$("#dg").datagrid("reload");
}



function nameFormatter(value,rowData,rowIndex){
	var return_value = "<a href='javascript:detailMethod("+value+")'><font color='blue'>明细</font></a>";
	if(rowData.status_id==23&&rowData.plat_status_id){
		return_value=return_value+ "&nbsp;|&nbsp;<a href='javascript:removeMethod("+value+")'><font color='blue'>删除</font></a>";
	}
	return return_value;
}

function nameFormatter1(value,rowData,rowIndex){
	return "<a href='javascript:detailMethod("+value+")'><font color='blue'>明细</font></a>";
}
function nameFormatter2(value,rowData,rowIndex){
	var return_value = "<a href='javascript:detailMethod("+value+")'><font color='blue'>明细</font></a>";
	if(rowData.status_id==23&&rowData.plat_status_id){
		return_value=return_value+ "&nbsp;|&nbsp;<a href='javascript:editMethod("+value+")'><font color='blue'>变更</font></a>";
	}
	return return_value;
}
function detailMethod(id){
	showWin("customVehicles/detailCustomVehicleForId?id="+id);
}

$(function(){
	commonGetOptions("tool_type_id",10003);
	commonGetOptions("type_id",10004);
	commonGetOptions("plat_status_id",10005);
	commonGetOptions("status_id",10006);
	commonGetCustoms("customs_code",10016);
});