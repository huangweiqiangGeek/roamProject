<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>人员岗位列表</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript">

function ajaxRequestSuccessBackInvokeMethod(data) {
	$("#dg").datagrid("reload",{});
}
</script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table id="dg" class="easyui-datagrid" title="" data-options="
			rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,
			url:'sysPost/querySysUserPostList?user_id=${user_id }'">
	<thead>
		<tr>
			<th field="name" width="100">岗位名称</th>
			<th field="job" width="120">职责</th>
			<th field="remark" width="120">备注</th>
			<th field="update_user_name" width="60" align="right">维护人</th>
			<th field="update_time_show" width="120" align="right">维护时间</th>
		</tr>
	</thead>
</table>
</body>
</html>