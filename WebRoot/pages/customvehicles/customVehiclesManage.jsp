<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>备案车辆管理</title>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo.css">
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/customvehicles/customVehiclesManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div id="tb" style="padding:5px;height:auto">
		<div>
			车牌号: <input class="text" style="width:80px" id="plat_no" maxlength="20">
			车辆名称: <select id="tool_type_id"></select>
			车辆类型: <select id="type_id"></select>
			主管海关：<select id="customs_code"></select>
			单据状态: <select id="status_id"></select>
			车辆状态: <select id="plat_status_id"></select>
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div style="margin:10px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'customVehicles/queryVehiclesList'">
		<tbody>
		<tr>
		<td field="toolType">
		   
		</td>
		</tr>
		</tbody>
	</table>
	<div id="win_div" title="车辆信息" class="easyui-dialog" data-options="iconCls:'icon-save'" style="width:800px;height:400px;padding:10px">
		<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
	</div>
</body>
</html>