function searchMethod(){
	var plat_no =$('#plat_no').val();
	var customs_code=$('#customs_code').val();
	var tool_type_id =$('#tool_type_id').val();
	var type_id =$('#type_id').val();
	var status_id=$('#status_id').val();
	var appStatusId=$('#appStatusId').val();
	var params ={plat_no:plat_no,customs_code:customs_code,tool_type_id:tool_type_id,type_id:type_id,status_id:status_id,appStatusId:appStatusId};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("busVehicles/addOrUpdateBusVehicles");
}
function ajaxRequest1(url,params){
	if(url.indexOf("?")>0){
		url = url + "&noCacheDealWith="+new Date();
	}else{
		url = url + "?&noCacheDealWith="+new Date();
	}

	$.ajax(
		{	url: url,
			type: 'POST',
			data:params,
			dataType: 'json',
			timeout: 10000,
			error: function(event, XMLHttpRequest){
				ajaxRequestErrorBackInvokeMethod(event,XMLHttpRequest);
			},
			success: function(data) {
				ajaxRequestSuccessBackInvokeMethod1(data);
			}	
		}
	);
}
function removeMethod(id,parent_id){
	$.messager.confirm('谨慎操作提示', '确认删除车辆?', function(r){
		if (r){
			ajaxRequest("busVehicles/deleteBusVehicles", {id:id,parentId:parent_id});
		}
	});
}
function xmlMethod(id){
	$.messager.confirm('谨慎操作提示', '确认申报车辆?', function(r){
		if (r){
			ajaxRequest1("busVehicles/xmlBusVehicles", {id:id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	$.messager.alert('操作提示','车辆删除成功!');
	$("#dg").datagrid("reload");
}
function ajaxRequestSuccessBackInvokeMethod1(data) {
	$.messager.alert('操作提示','车辆申报成功!');
	$("#dg").datagrid("reload");
}
function editMethod(id){
	showWin("busVehicles/addOrUpdateBusVehicles?id="+id);
}

function nameFormatter1(value,rowData,rowIndex){
	if(value!=null){
		return "<div title='"+value+"'>"+value+"</div>" ;
	}
}

function nameFormatter(value,rowData,rowIndex){
	 if(rowData.appStatusId != 46){
		 return "<a href='javascript:xmlMethod("+rowData.id+")'><font color='blue'>申报</font></a>&nbsp;|&nbsp;"
		 +"<a href='javascript:editMethod("+rowData.id+")'><font color='blue'>修改</font></a>&nbsp;|&nbsp;"
		 +"<a href='javascript:removeMethod("+rowData.id+","+rowData.parentId+")'><font color='blue'>删除</font></a>";
	 }else{
		 return "<a href='javascript:detailMethod("+rowData.id+")'><font color='blue'>明细</font></a>";
	 }
}
function detailMethod(id){
	
	showWin("busVehicles/detailBusVehicles?id="+id);
}
$(function(){
	commonGetOptions("status_id",10007);
	commonGetOptions("type_id",10004);
	commonGetOptions("tool_type_id",10003);
	commonGetOptions("appStatusId",10013);
	commonGetCustoms("customs_code",10016);
});

