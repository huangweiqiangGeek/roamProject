function searchMethod(){
	var templateName = $('#templateName').val();
	var provinceName =$('#provinceName').val();
	var templateStatus=$('#templateStatus').val(); 
	var templateType=$('#templateType').val(); 
	var params ={templateName:templateName,provinceName:provinceName,templateStatus:templateStatus,templateType:templateType};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("resultTemplate/addInit","新增预设结果模版",800,450);
}
function removeMethod(user_id){
	var headers={};  
    headers['CSRFToken']=$("#csrftoken").val();
	$.messager.confirm('谨慎操作提示', '确认删除预设结果模版?', function(r){
		if (r){
			ajaxRequest("resultTemplate/delete", {id:user_id},headers);
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function editMethod(user_id){
	showWin("resultTemplate/updateInit?id="+user_id,"修改预设结果模版",750,450);
}

function formatOperate(value,rowDatas,rowIndex){
	return '<a class="btn handleBtn" href="resultTemplate/updateInit?id='+value	+'">修改</a>&nbsp;&nbsp;'
			+'<a class="btn handleBtn" href="javascript:removeMethod('+value+')">删除</a>&nbsp;&nbsp;';
}


function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		//var d=new Date(value); 
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
	
}