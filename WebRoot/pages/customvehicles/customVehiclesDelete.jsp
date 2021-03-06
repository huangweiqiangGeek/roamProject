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
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div style="margin:10px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'customVehicles/queryOrUpdateList'">
		<thead>
			<tr>
				<th field="tool_type" width="140">车辆名称</th>
				<th field="plat_no" width="100">车牌号</th>
				<th field="type" width="80">车辆类型</th>
				<th field="plat_status" width="80" >车辆状态</th>
				<!-- <th field="certifacate_no" width="100">证件号</th>-->
				<!--<th field="contacts" width="80">联系人</th>
				<th field="contact_phone" width="100" >联系电话</th>-->
				<th field="customs_code_name" width="70">主管海关</th>
				<th field="customs_code" width="100">主管海关代码</th>
				<th field="busic_no" width="60" >IC编号</th>
				<th field="status" width="80" >单据状态</th>
				<th field="create_time_show" width="120" >备案时间</th>
				<th data-options="formatter:nameFormatter" field="id" width="80" >操作</th>
			</tr>
		</thead>
	</table>
	<div id="win_div" title="车辆信息" class="easyui-dialog" data-options="iconCls:'icon-save'" style="width:800px;height:400px;padding:10px">
		<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
	</div>
</body>
</html>