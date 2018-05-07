function sumitMethod(){
	document.getElementById("submitForm").submit();
}
function resetMethod(){
	document.getElementById("span1").innerHTML="<input type='file' class='a-upload' name='file'>";
	document.getElementById("span2").innerHTML="<input type='file' class='a-upload' name='file'>";
	document.getElementById("span3").innerHTML="<input type='file' class='a-upload' name='file'>";
	document.getElementById("span4").innerHTML="<input type='file' class='a-upload' name='file'>";
	document.getElementById("span5").innerHTML="<input type='file' class='a-upload' name='file'>";
}
function resetMethod2(){
	document.getElementById("span1").innerHTML="<input type='file' class='a-upload2' name='file'>";
	document.getElementById("span2").innerHTML="<input type='file' class='a-upload2' name='file'>";
	document.getElementById("span3").innerHTML="<input type='file' class='a-upload2' name='file'>";
	document.getElementById("span4").innerHTML="<input type='file' class='a-upload2' name='file'>";
	document.getElementById("span5").innerHTML="<input type='file' class='a-upload2' name='file'>";
}
$(document).ready(function(){
	initProvinceSelect("province");
	$("#task").append("<option value=''>请选择测试任务</option>");
	var hiddenValue = $('#hiddenValue').val();
	if(hiddenValue!="null" && hiddenValue!=""){
		if("Please select the message and then upload!" == hiddenValue)
			$.messager.alert("操作提示", hiddenValue, "info");
		else{
			$.messager.show({
				title: "操作提示",
				msg: hiddenValue,
				width: 500,
				timeout: 0
			}); 
		}
	}
	//initProvinceSelectss("province");
});
//create AgentUsercase 
function initProvinceSelectss(selectID, selectValue){
	var random = Math.random();
	$.ajax({
		type:"get",
		url:"createAgentUsercase/createAllAgentUsercase",
		contentType : "application/json",
		dataType:"json",
		data: {random:random},
		success:function(data){
		}
	});
}
function aa(){
	var taskName = window.prompt("请输入新的测试任务","");
	var province = $('#province').val();
	$.ajax({
		type:"get",
		url:"dccCommon/addTaskSelect",
		contentType : "application/json",
		dataType:"json",
		data: {province:province,taskName:taskName},
		success:function(data){
			$("#task option").remove();
			$("#task").append("<option value=''>请选择测试任务</option>");
			if(data!=null&&data!=""){
				$.each(data,function(index,item){
					if(item.code == "")
						$("#task").append("<option value='"+item.code+"' selected='selected'>"+item.name+"</option>");
					else
						$("#task").append("<option value='"+item.code+"'>"+item.name+"</option>");
				});
			}
			$("#task").append("<option value='null' style='text-decoration:underline' onclick='aa()'>添加测试任务</option>");
		}
	});
}
function check(){
	var province = $('#province').val();
	if("" == province){
		$("#mylable").css({"display":"block"});
		$("#mylable").css({"color":"red"});
		$("#mylable").html("请选择省份");
		return false;
	}
	var task = $('#task').val();
	if("" == task){
		$("#mylable").css({"display":"block"});
		$("#mylable").css({"color":"red"});
		$("#mylable").html("请选择测试任务");
		return false;
	}
	var nf = $('#nf').val();
	if("" == nf){
		$("#mylable").css({"display":"block"});
		$("#mylable").css({"color":"red"});
		$("#mylable").html("请选择网元厂商");
		return false;
	}
	var nf_type = $('#nf_type').val();
	if("" == nf_type){
		$("#mylable").css({"display":"block"});
		$("#mylable").css({"color":"red"});
		$("#mylable").html("请选择网元类型");
		return false;
	}
	var nf_version = $('#nf_version').val();
	if("" == nf_version){
		$("#mylable").css({"display":"block"});
		$("#mylable").css({"color":"red"});
		$("#mylable").html("请选择网元版本");
		return false;
	}
	return true;
}
function selected(value,selectValue) {
	$("#task option").remove();
	$("#task").append("<option value=''>请选择测试任务</option>");
	if("" != value){
		var random = Math.random();
		$.ajax({
			type:"get",
			url:"dccCommon/queryTaskSelect",
			contentType : "application/json",
			dataType:"json",
			data: {province:value,random:random},
			success:function(data){
				if(data!=null&&data!=""){
					$.each(data,function(index,item){
						if(selectValue!=null&&selectValue==item.code){
							$("#task").append("<option value='"+item.code+"' selected='selected'>"+item.name+"</option>");
						}else{
							if(selectValue == null && item.code == "")
								$("#task").append("<option value='"+item.code+"' selected='selected'>"+item.name+"</option>");
							else
								$("#task").append("<option value='"+item.code+"'>"+item.name+"</option>");
						}
					});
				}
				//$("#task").append("<option value='null' style='text-decoration:underline' onclick='aa()'>添加测试任务</option>");
			}
		});
	}
}
function initTask(){
	var province = $('#province').val();
	if("" != province){
		selected(province);
	}
}