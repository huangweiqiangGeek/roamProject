function searchMethod(){
	var plat_no =$('#plat_no').val();
	var mediumNo=$('#mediumNo').val();
	var type_id=$('#type_id').val();
	var availability_flag=$('#availability_flag').val();
	var startDate = $('#startDate').datebox('getValue');
	var endDate = $('#endDate').datebox('getValue');
	var customs_code=$('#customs_code').val();
	var params ={plat_no:plat_no,mediumNo:mediumNo,type_id:type_id,availability_flag:availability_flag,startDate:startDate,endDate:endDate,customs_code:customs_code};
	$("#dg").datagrid("reload",params);
}

function addMethod(){
	showDialog("busCards/add");
}

function detailsMethod(value){
	showDialog("busCards/queryBusCards?mediumNo="+value);
}
function nameFormatter(value,rowData,rowIndex){
	 return "<u onclick='detailsMethod(\""+value+"\");'><font color='blue'>"+value+"</font></u>"
}

function availabilityFormatter(value,rowData,rowIndex){
		 if(value == 5){
			 return "<font color='blue'>有效</font>";
		 }else{
			 return "<font color='blue'>无效</font>";
		 }
}

function operateFormatter(value,rowData,rowIndex){
	 if(rowData.availability_flag == 0){
		 return "<u onclick='updateMethod()'><font color='blue'>解绑</font></u>";
	 }else{
		 return "<u onclick='updateMethod()'><font color='blue'>绑定</font></u>";
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
	commonGetOptions("availability_flag",10002);
	commonGetCustoms("customs_code",10016);
});
