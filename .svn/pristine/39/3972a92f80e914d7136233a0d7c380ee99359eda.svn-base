<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>车辆标识管理</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/buscardsic/busCardsIC.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div id="tb" style="padding:5px;height:auto">
		<div>
			媒介类型: <select style="width:120px" id="type_id" name="type_id">
			</select>
			主管海关：<select id="customs_code"></select>
			媒介编号: <input class="text" style="width:80px" id="mediumNo" maxlength="50">
			车牌号: <input class="text" style="width:80px" id="plat_no" maxlength="15">
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			<a href="javascript:addMethod()" class="easyui-linkbutton" iconCls="icon-add">新增</a>
		</div>
	</div>
	<div style="margin:10px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'busCards/queryBusICList'">
		<thead>
			<tr>
				<th field="type" width="80">媒介类型</th>
				<th data-options="formatter:nameFormatter" field="mediumNo" width="120">媒介编号</th>
				<th field="period_date" width="65">有效期(月)</th>
				<th field="start_date_show" width="80">有效开始日期</th>
				<th data-options="formatter:platNoFormatter" field="plat_no" width="100">车牌号</th>
				<th field="customs_code_name" width="70">主管海关</th>
				<th field="customs_code" width="100">主管海关代码</th>
				<th field="create_user_name" width="60">创建者</th>
				<th field="create_time_show" width="120">创建时间</th>
				<th field="update_user_name" width="60">更新者</th>
				<th field="update_time_show" width="120">更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="dialogDiv" title="媒介标识" class="easyui-dialog" data-options="iconCls:'icon-save'" style="width:800px;height:400px;padding:10px">
		<iframe id="dialogIframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
	</div>
</body>
</html>