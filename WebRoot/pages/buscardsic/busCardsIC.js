function searchMethod(){
	var plat_no =$('#plat_no').val();
	var mediumNo=$('#mediumNo').val();
	var type_id=$('#type_id').val();
	var customs_code=$('#customs_code').val();
	var params ={plat_no:plat_no,mediumNo:mediumNo,type_id:type_id,customs_code:customs_code};
	$("#dg").datagrid("reload",params);
}

function addMethod(){
	showDialog("busCards/add?parentFrom="+"");
}

function detailsMethod(value){
	showDialog("busCards/queryBusCards?mediumNo="+value);
}
function nameFormatter(value,rowData,rowIndex){
	 return '<u onclick="javascript:detailsMethod(\''+value+'\');"><font color="blue">'+value+'</font></u>';
}

function platNoDetailsMethod(value){
	showDialog("busCards/queryBusCardsNo?plat_no="+value);
}

function platNoFormatter(value,rowData,rowIndex){
	if(value!=null){
		value = value + "";
		 return "<u onclick='javascript:platNoDetailsMethod(\""+value+"\");'><font color='blue'>"+value+"</font></u>"
	}
}

function availabilityFormatter(value,rowData,rowIndex){
	if(value != null){
		 if(value == 5){
			 return "<font color='blue'>有效</font>";
		 }else{
			 return "<font color='blue'>无效</font>";
		 }
	}
	
}

function updateMethod(){
	showDialog("busCards/updateBusCardsIC?id="+$("#dg").datagrid("getSelected").id);
}

function cancelMethod(){
	window.parent.closeDialog(false);
}

$(function(){
	commonGetOptions("type_id",10001);
	commonGetCustoms("customs_code",10016);
});
