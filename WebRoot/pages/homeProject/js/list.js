function searchMethod(){
	var name = $('#name').val();
	var params ={name:name};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("homeProject/addInit","新增项目",800,450);
}
function removeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认删除项目?', function(r){
		if (r){
			//ajaxRequest("homeProject/deleteHomeProject",{id:id});
			$.ajax({
				type:"POST",
				url:"homeProject/deleteHomeProject?id="+id
			})
			$("#dg").datagrid("reload");
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function editMethod(id,name,introduction){
	showWin("homeProject/editInit?id="+id+"&name="+name+"&introduction="+introduction,"修改项目",750,450);
}
function formatOperate(value,rowDatas,rowIndex){
		var id=rowDatas.id;
		var name=rowDatas.name;
		var introduction=rowDatas.introduction;
		var h='<a class="btn handleBtn" href="project/listInit?homeProjectId='+value+'">进入项目</a>&nbsp;&nbsp;'
		h+='<a  class="btn handleBtn" href="javascript:editMethod('+id+','+name+','+introduction+')">修改信息</a>&nbsp;&nbsp;'
		//+'<a  class="btn handleBtn" href="javascript:projecRegression('+value+');">项目回归</a>&nbsp;&nbsp;'
		//h+='<a  class="btn handleBtn" href="project/listInit2?id='+value+'">查看详细</a>&nbsp;&nbsp;'
		h+='<a  class="btn handleBtn" href="javascript:removeMethod('+value+')">删除项目</a>&nbsp;&nbsp;'
		return h;
}
