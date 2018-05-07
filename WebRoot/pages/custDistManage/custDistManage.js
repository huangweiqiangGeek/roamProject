function removeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认删除载货单?', function(r){
		if (r){
			ajaxRequest("custDist/deleteDist", {id:id});
		}
	});
}
function searchMethod(){
	var distNo =$('#distNo').val();
	var customsCode=$('#customsCode').val();
	var copNo=$('#copNo').val();
	var statusId=$('#statusId').val();
	var distTypeId=$('#distTypeId').val();
	var bulkFlag=$('#bulkFlag').val();
	var params ={distNo:distNo,customsCode:customsCode,copNo:copNo,statusId:statusId,distTypeId:distTypeId,bulkFlag:bulkFlag};
	$("#dg").datagrid("reload",params);
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	$.messager.alert('操作提示','载货单删除成功!');
	$("#dg").datagrid("reload");
}
function ajaxRequestSuccessBackInvokeMethod1(data) {
	$.messager.alert('操作提示','载货单变更成功!');
	$("#dg").datagrid("reload");
}
function ajaxRequestSuccessBackInvokeMethod2(data) {
	$.messager.alert('操作提示','载货单结案成功!');
	$("#dg").datagrid("reload");
}

function changeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认变更载货单?', function(r){
		if (r){
			ajaxRequest1("custDist/changeCustDist?id="+id);
		}
	});
}

function closeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认结案载货单?', function(r){
		if (r){
			ajaxRequest2("custDist/closeCustDist?id="+id);
		}
	});
}
function nameFormatter(value,rowData,rowIndex){
	 return "<a href='javascript:changeMethod("+value+")'><font color='blue'>变更</font></a>&nbsp;|&nbsp;"
		 +"<a href='javascript:detailMethod("+rowData.id+")'><font color='blue'>明细</font></a>";
}
function nameFormatter1(value,rowData,rowIndex){
	 return "<a href='javascript:detailMethod("+value+")'><font color='blue'>明细</font></a>"
}
function nameFormatter2(value,rowData,rowIndex){
	 return "<a href='javascript:removeMethod("+value+")'><font color='blue'>删除</font></a>&nbsp;|&nbsp;"
		 +"<a href='javascript:detailMethod("+rowData.id+")'><font color='blue'>明细</font></a>";
}

function nameFormatter4(value,rowData,rowIndex){
	 return "<a href='javascript:closeMethod("+value+")'><font color='blue'>结案</font></a>&nbsp;|&nbsp;"
		 +"<a href='javascript:detailMethod("+rowData.id+")'><font color='blue'>明细</font></a>";
}
function detailMethod(id){
	showWin("custDist/addOrUpdateDistItem?id="+id);
}

function bulkFormatter(value,rowData,rowIndex){
	 if(value == 44){
		 return "是";
	 }else{
		 return "否";
	 }
}
 
$(function(){
	commonGetOptions("statusId",10014);
	commonGetOptions("distTypeId",10010);
	commonGetOptions("bulkFlag",10011);
	commonGetCustoms("customsCode",10016);
});