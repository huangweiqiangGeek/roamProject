
function searchMethod(){
	var templateName = $('#templateName').val();
	var provinceID =$('#provinceID').val();
	var templateStatus=$('#templateStatus').combobox('getValue'); 
	var params ={templateName:templateName,provinceID:provinceID,templateStatus:templateStatus};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("billTemplate/addInit","新增话单模版",800,450);
}
function removeMethod(user_id){
	$.messager.confirm('谨慎操作提示', '确认删除话单模版?', function(r){
		if (r){
			ajaxRequest("billTemplate/delete", {id:user_id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function editMethod(user_id){
	showWin("billTemplate/updateInit?id="+user_id,"修改话单模版",750,450);
}

function formatOperate(value,rowDatas,rowIndex){
	return '<a href="billTemplate/updateInit?id='+value	+'">修改</a>&nbsp;&nbsp;'
			+'<a href="javascript:removeMethod('+value+')">删除</a>&nbsp;&nbsp;';
}


function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		//var d=new Date(value); 
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
	
}
function formatAttrJson(value,rowDatas,rowIndex){
	var html='<div id="div'+rowDatas.id+'" style="display: none;">'+rowDatas.attrJson+'</div>';
	return value+html;
	
}