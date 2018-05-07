$(document).ready(function(){
	commonGetOptions("rcFlag",10009);
	commonGetOptions("busType",10008);
	commonGetOptions("businessTypeId",10008);
	commonGetCustoms("customs_code",10016,$('#hide03').val());
	$("#headForm").validate({
		rules: {
			distTypeId: {required:true},
			customsCode: {required:true},
			bulkFlag: {required:true}
		}
	});
	$("#vehiclesForm").validate({
		rules: {
			platNo: {required:true}
		}
	});
	$("#businussForm").validate({
		rules: {
			busNo: {required:true},
	        busType: {required:true}
		}
	});
	$("#cargoForm").validate({
		rules: {
			platNo: {required:true},
			gNo: {required:true},
			gCode: {required:true},
			gName: {required:true},
			qty: {required:true}
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
	$("#customsCode_font").text($('#hide03').val());
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
		$("#vehiclesForm").submit();
	}
}
function saveBusinuss(){
	if($("#businussForm").valid()){
		$("#businussForm").submit();
	}
}

function saveCargo(){
	if($("#cargoForm").valid()){
		$("#cargoForm").submit();
	}
}


function deleteVehicles(){
	$("#vehiclesForm").attr("action","custDist/deleteDistVehicles");
	$("#vehiclesForm").submit();
}
function removeBusinuss(){
	$("#businussForm").attr("action","custDist/deleteDistBusinuss");
	$("#businussForm").submit();
}
function removeCargo(){
	$("#cargoForm").attr("action","custDist/deleteDistCargo");
	$("#cargoForm").submit();
}
function cancelMethod(){
	window.parent.closeWin(); 
}
function nameFormatter(value,rowData,rowIndex){
	 if(value == 16){
		 return "<a><font color='blue'>自由车</font></a>";
	 }else if(value == 17){
		 return "<a><font color='blue'>非自由车</font></a>";
	 }else{
		 return "<a><font color='blue'>未备案</font></a>";
	 }
}

