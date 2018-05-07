$(document).ready(function(){
	commonGetOptions("rcFlag",10009);
	commonGetOptions("busType",10008);
	commonGetOptions("businessTypeId",10008);
	$("#headForm").validate({
		rules: {
			distTypeId: {required:true},
			customsCode: {required:true},
			cfsNo: {required:true},
			bulkFlag: {required:true},
			gValue: {number:true},
			packNum: {number:true},
			netWt: {number:true},
			customs_code:{required:true},
			grossWt: {number:true},
			startCfsNo:{required:function(){
				if($('#distTypeId').val()==43)return true;
				return false;
			}
			}
		}
	});
	 
	$("#vehiclesForm").validate({
		rules: {
			platNo: {required:true},
			grossWt: {number:true}
		}
	});
	$("#businussForm").validate({
		rules: {
			busNo: {required:true},
			busPackNum: {number:true},
			gValue: {number:true},
			busGrossWt: {number:true},
			busNetWt: {number:true},
	        busType: {required:true}
		}
	});

	$("#cargoForm").validate({
		rules: {
			platNo: {required:true},
			gNo: {required:true},
			gCode: {required:true},
			gName: {required:true},
			qty: {required:true,number:true},
			price: {number:true},
			value: {number:true}
		}
	});
	if($('#select_tab').val()==1){
		$('#main_tabs').tabs('select',1);
	}else if($('#select_tab').val()==2){
		$('#main_tabs').tabs('select',2);
	}else if($('#select_tab').val()==3){
		$('#main_tabs').tabs('select',3);
	}else{
		$('#main_tabs').tabs('select',0);
	}

	commonGetOptions("distTypeId",10010,$('#hide01').val());
	commonGetOptions("bulkFlag",10011,$('#hide02').val());
	commonGetCustoms("customsCode",10016,$('#hide03').val());
	VehiclesGetOptions("vehId_",$("#distId").val());
	businessNoGetOptions("businessId_",$("#distId").val());
	window.parent.searchMethod();
	var dis = $('#hide01').val();
	if(dis==43){
		$("#startCfsNo_font").text("*")
	}else{
		$("#startCfsNo_font").text("")
	}
	var id =  $('#statusId').val();
	if(id != '' && id != 54){
		$('#customsCode').attr('disabled','disabled');
	}
});

function onClickRowVeh(index,rowData){
	$.each(rowData, function(key, val) {
		if($("#"+key+".vehiclesClass").length>0){
			$("#"+key+".vehiclesClass").val(val);
		}
 　　});
}
function onClickRowBus(index,rowData){
	$.each(rowData, function(key, val) {
		if($("#"+key+".businussClass").length>0){
			$("#"+key+".businussClass").val(val);
		}
 　　});
}
function onClickRowCargo(index,rowData){
	$.each(rowData, function(key, val) {
		if($("#"+key+".cargoClass").length>0){
			$("#"+key+".cargoClass").val(val);
		}else if($("#"+key+"_.cargoClass").length>0){
			$("#"+key+"_.cargoClass").val(val);
		}
 　　});
}
function saveHead(){
	if($("#headForm").valid()){
		$("#headForm").submit();
	}
}
function saveVehicles(){
	if($("#vehiclesForm").valid()){
		var distId = $('#distId').val();
		if(distId == null || distId == ''){
			alert("请先输入载货单");
			return;
		}
		$("#vehiclesForm").submit();
	}
}
function saveBusinuss(){
	if($("#businussForm").valid()){
		var distId = $('#distId').val();
		if(distId == null || distId == ''){
			alert("请先输入载货单");
			return;
		}
		$("#businussForm").submit();
	}
}

function saveCargo(){
	if($("#cargoForm").valid()){
		var distId = $('#distId').val();
		if(distId == null || distId == ''){
			alert("请先输入载货单");
			return;
		}
		$("#cargoForm").submit();
	}
}


function deleteVehicles(){
	$("#vehiclesForm").attr("action","distHead/deleteDistVehicles");
	$("#vehiclesForm").submit();
}
function removeBusinuss(){
	$("#businussForm").attr("action","distHead/deleteDistBusinuss");
	$("#businussForm").submit();
}
function removeCargo(){
	$("#cargoForm").attr("action","distHead/deleteDistCargo");
	$("#cargoForm").submit();
}
function cancelMethod(){
	window.parent.closeWin();
}

function chanageDistTypeId(DistTypeId){
	var dis = DistTypeId.value;
	if(dis==43){
		$("#startCfsNo_font").text("*")
	}else{
		$("#startCfsNo_font").text("")
	}
	
}




function VehiclesGetOptions(selectID,dist_id){
	var url = "distHead/queryDistVehiclesList?nocathce="+new Date();
	if(dist_id!=null&dist_id!=''){
		$.ajax({
			type:"get",
			url:url,
			contentType : "application/json",
			dataType:"json",
			data:{dist_id:dist_id},
			timeout: 10000,
			success:function(data){
				$("#"+selectID).empty();
				$("#"+selectID).append("<option value=''>请选择</option>");
				if(data!=null&&data!=""){
					$.each(data,function(index,item){
						$("#"+selectID).append("<option value='"+item.id+"'>"+item.platNo+"</option>");
					});
				}
			}
		});
	}
}
var busData;
function businessNoGetOptions(selectID,dist_id,selectValue){
	var url = "distHead/queryDistBusinussList?nocathce="+new Date();
	if(dist_id!=null&dist_id!=''){
		$.ajax({
			type:"get",
			url:url,
			contentType : "application/json",
			dataType:"json",
			data:{"dist_id":dist_id},
			success:function(data){
				$("#"+selectID).empty();
				$("#"+selectID+"").append("<option value=''>请选择</option>");
				if(data!=null&&data!=""){
					busData = data;
					$.each(data,function(index,item){
						if(selectValue!=null&&selectValue==item.id){
							$("#"+selectID+"").append("<option value='"+item.id+"' selected='selected'>"+item.busNo+"</option>");
						}else{
							$("#"+selectID+"").append("<option value='"+item.id+"'>"+item.busNo+"</option>");
						}
					});
				}		
			}
		});
	}
}
function resetForm(form){
	form.reset();
	form.id.value = "";
}

function chanageBusData(id){
	for(var i = 0;i<busData.length;i++){
		if(busData[i].id==id.value){
			var businessType = busData[i].busType;
		}
	}
	$("#businessTypeId").val(businessType);
}
