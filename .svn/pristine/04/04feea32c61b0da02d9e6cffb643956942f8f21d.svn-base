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
function addMethod(){
	showWin("distHead/addOrUpdateDistItem");
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
function ajaxRequestSuccessBackInvokeMethod1(data) {
	$.messager.alert('操作提示','载货单申报成功!');
	$("#dg").datagrid("reload");
}
function removeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认删除载货单?', function(r){
		if (r){
			ajaxRequest("distHead/deleteDist", {id:id});
		}
	});
}


function xmlMethod(id){
	$.messager.confirm('谨慎操作提示', '确认申报载货单?', function(r){
		if (r){
			ajaxRequest1("distHead/xmlDistItem", {id:id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	$.messager.alert('操作提示','载货单删除成功!');
	$("#dg").datagrid("reload");
}

function nameFormatter(value,rowData,rowIndex){
	var reStr = "";
	 if(rowData.appStatusId != 46){
		 if( rowData.countCar>0 && rowData.countBus>0){
			 reStr += "<a href='javascript:xmlMethod("+rowData.id+")'><font color='blue'>申报</font></a>&nbsp;|&nbsp;";
	    }
		 reStr += "<a href='javascript:editMethod("+rowData.id+")'><font color='blue'>修改</font></a>&nbsp;|&nbsp;"
		 +"<a href='javascript:removeMethod("+rowData.id+")'><font color='blue'>删除</font></a>";
		 return reStr;
	 }else{
		 return "<a href='javascript:detailMethod("+rowData.id+")'><font color='blue'>明细</font></a>"
		 
	 }
}

function detailMethod(id){
	showWin("distHead/detailDistHead?id="+id);
}

function bulkFormatter(value,rowData,rowIndex){
	 if(value == 44){
		 return "是";
	 }else{
		 return "否";
	 }
}
function editMethod(id){
	showWin("distHead/addOrUpdateDistItem?id="+id);
}

$(function(){
	commonGetOptions("statusId",10015);
	commonGetOptions("distTypeId",10010);
	commonGetOptions("bulkFlag",10011);
	commonGetCustoms("customsCode",10016);
});