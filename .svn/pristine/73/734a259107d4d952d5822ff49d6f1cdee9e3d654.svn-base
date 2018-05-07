<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构树</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript">
function saveMethod(){
	$.messager.confirm('谨慎操作提示', '确认保存角色菜单列表?', function(r){
		if (r){
			var nodes = $('#tt').tree('getChecked');
			var menu_ids = "";
			var menu_parent_ids="";
			for(var i=0; i<nodes.length; i++){
				if (menu_ids != '') menu_ids += ',';
				menu_ids += nodes[i].id;
			}
			ajaxRequest("sysRole/saveSysRoleMenu", {id:${role_id},menu_ids:menu_ids});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	$.messager.alert('操作提示','角色菜单保存成功!');
}

function checkRoleMenuHave(node, data){
	var nodes = $('#tt').tree('getChildren');
	var s = '';
	var menu_ids = ","+'${menu_ids}';
	for(var i=0; i<nodes.length; i++){
		if(menu_ids.indexOf(","+nodes[i].id+",")>-1){
			if($('#tt').tree('isLeaf', nodes[i].target)==true){
				$('#tt').tree('check', nodes[i].target);
			}
		}
	}
}
function checkAll(){
	var nodes = $('#tt').tree('getChildren');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if($('#tt').tree('isLeaf', nodes[i].target)==true){
			$('#tt').tree('check', nodes[i].target);
		}
	}
}
function UncheckAll(){
	var nodes = $('#tt').tree('getChildren');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if($('#tt').tree('isLeaf', nodes[i].target)==true){
			$('#tt').tree('uncheck', nodes[i].target);
		}
	}
}
</script>
</head>
<body>
<a href="javascript:saveMethod()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
<a href="javascript:checkAll()" class="easyui-linkbutton" iconCls="">全选</a>
<a href="javascript:UncheckAll()" class="easyui-linkbutton" iconCls="">全清</a>
<a href="javascript:window.parent.closeWin(false)" class="easyui-linkbutton" iconCls="">关闭</a>
<ul id="tt" class="easyui-tree" data-options="idFiled:'id',textFiled:'name',parentField:'parent_id',
url:'sysMenu/querySysMenuList',animate:true,checkbox:true,onLoadSuccess:checkRoleMenuHave"></ul>
<a href="javascript:saveMethod()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
<a href="javascript:checkAll()" class="easyui-linkbutton" iconCls="">全选</a>
<a href="javascript:UncheckAll()" class="easyui-linkbutton" iconCls="">全清</a>
<a href="javascript:window.parent.closeWin(false)" class="easyui-linkbutton" iconCls="">关闭</a>
</body>
</html>