function onLoadSuccess(){
	var node = $("#tt").tree("getRoot");
	$("#tt").tree('select',node.target);
	$('#org_id').val(node.id);
	var org_id = $('#org_id').val();
	var params ={org_id:org_id};
	$("#dg").datagrid({url:"sysPost/querySysPostList",queryParams:params});
}
$(document).ready(function(){
	$("#tree_div").css("height",document.documentElement.offsetHeight-40);
});
function onClickTree(treeNode){
	$('#org_id').val(treeNode.id);
	searchMethod();
}
function searchMethod(){
	var org_id = $('#org_id').val();
	var name =$('#name').val();
	var job=$('#job').val();
	var params ={name:name,job:job,org_id:org_id};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	if($('#org_id').val()==0){
		$.messager.alert('温馨提示','请先选择机构!','warning');
	}else{
		showWin("sysPost/addOrUpdateSysPost?org_id="+$('#org_id').val(),"添加岗位",600,360);
	}
}
function removeMethod(post_id){
	$.messager.confirm('谨慎操作提示', '确认删除岗位?', function(r){
		if (r){
			ajaxRequest("sysPost/deleteSysPost", {id:post_id});
		}
	});
}

function editMethod(post_id){
	showWin("sysPost/addOrUpdateSysPost?id="+post_id,"岗位修改",600,300);
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	$.messager.alert('操作提示','岗位删除成功!');
	$("#dg").datagrid("reload");
}
//岗位人员管理
function postUserManage(post_id,post_name){
	showWin("sysPost/postUserManage?id="+post_id,"岗位人员管理："+post_name,750,450);
}
//岗位角色管理
function postRoleManage(post_id,post_name){
	showWin("sysPost/postRoleManage?id="+post_id,"岗位角色管理："+post_name,750,450);
}
function formatOperate(value,rowDatas,rowIndex){
	return '<a href="javascript:postUserManage('+value+',\''+rowDatas.name+'\')">岗位人员</a>&nbsp;&nbsp;'
			+'<a href="javascript:postRoleManage('+value+',\''+rowDatas.name+'\')">岗位角色</a>&nbsp;&nbsp;'
			+'<a href="javascript:editMethod('+value+')">修改</a>&nbsp;&nbsp;'
			+'<a href="javascript:removeMethod('+value+')">删除</a>';
}