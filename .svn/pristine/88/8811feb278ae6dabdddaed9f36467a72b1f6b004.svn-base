function onLoadSuccess(){
	var node = $("#tt").tree("getRoot");
	$("#tt").tree('select',node.target);
	$('#org_id').val(node.id);
	var org_id = $('#org_id').val();
	var params ={org_id:org_id};
	$("#dg").datagrid({url:"sysUser/querySysUserList",queryParams:params});
}
$(document).ready(function(){
	$("#tree_div").css("height",document.documentElement.offsetHeight-40);
});
function searchMethod(){
	var org_id = $('#org_id').val();
	var user_code =$('#user_code').val();
	var cn_name=$('#cn_name').val();
	var mobile=$('#mobile').val();
	var org_name =$('#org_name').val();
	var post_name=$('#post_name').val();
	var role_name=$('#role_name').val();
	var params ={org_id:org_id,user_code:user_code,cn_name:cn_name,mobile:mobile,
			org_name:org_name,post_name:post_name,role_name:role_name};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	var org_id = $('#org_id').val();
	if(org_id==0){
		$.messager.alert('温馨提示','请先选择组织!','warning');
	}else{
		showWin("sysUser/addSysUser?org_id="+org_id,"新增人员",800,450);
	}
}
function removeMethod(user_id){
	$.messager.confirm('谨慎操作提示', '确认删除人员?', function(r){
		if (r){
			ajaxRequest("sysUser/deleteSysUser", {id:user_id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}
function onClickTree(treeNode){
	$('#org_id').val(treeNode.id);
	searchMethod();
}
function editMethod(user_id){
	showWin("sysUser/updateSysUser?id="+user_id,"修改人员",750,450);
}
function postsMethod(user_id,user_name){
	showWin("sysUser/sysUserPostsPage?user_id="+user_id,"人员岗位列表："+user_name,750,400);
}
function rolesMethod(user_id,user_name){
	showWin("sysUser/sysUserRolesPage?user_id="+user_id,"人员角色列表："+user_name,750,400);
}
function formatOperate(value,rowDatas,rowIndex){
	return '<a href="javascript:editMethod('+value+')">修改</a>&nbsp;&nbsp;'
			+'<a href="javascript:removeMethod('+value+')">删除</a>&nbsp;&nbsp;'
			+'<a href="javascript:postsMethod('+value+',\''+rowDatas.cn_name+'\')">岗位列表</a>&nbsp;&nbsp;'
			+'<a href="javascript:rolesMethod('+value+',\''+rowDatas.cn_name+'\')">角色列表</a>';
}