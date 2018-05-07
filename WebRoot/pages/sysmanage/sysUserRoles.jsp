<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>人员角色列表</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div style="margin:1px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,
				url:'sysRole/querySysUserRoleList?user_id=${user_id }'">
		<thead>
			<tr>
				<th field="name" width="220">角色名称</th>
				<th field="remark" width="260">备注</th>
				<th field="update_user_name" width="80">维护人</th>
				<th field="update_time_show" width="140">维护时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>