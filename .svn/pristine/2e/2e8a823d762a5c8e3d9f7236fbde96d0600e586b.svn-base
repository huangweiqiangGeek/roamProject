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
<script type="text/javascript" src="pages/buscardsic/historyBusCardsIC.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div id="tb" style="padding:5px;height:auto">
		<div>
			媒介编号: <input class="text" style="width:80px" id="mediumNo" maxlength="50">
			车牌号: <input class="text" style="width:80px" id="plat_no" maxlength="15">
			有效标识: <select style="width:80px" id="availability_flag" >
			</select>
			主管海关：<select id="customs_code"></select>
			开始日期<input type="text" id="startDate" name="startDate"  class="easyui-datebox" style="width:180px" >至
			<input type="text" id="endDate" name="endDate"  class="easyui-datebox"  style="width:180px" >
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div style="margin:10px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'busCards/queryBusICList1'">
		<thead>
			<tr>
				<th field="type" width="80">媒介类型</th>
				<th data-options="formatter:nameFormatter" field="mediumNo" width="120">媒介编号</th>
				<th field="period_date" width="65">有效期(月)</th>
				<th field="start_date_show" width="80">有效开始日期</th>
				<th field="plat_no" width="100">车牌号</th>
				<th field="customs_code_name" width="80">主管海关</th>
				<th field="customs_code" width="100">主管海关代码</th>
				<th data-options="formatter:availabilityFormatter" field="availability_flag" width="80">有效标识</th>
				<th field="update_user_name" width="60">更新者</th>
				<th field="copName" width="160">更新公司</th>
				<th field="update_time_show" width="120">更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="dialogDiv" title="媒介编号标识" class="easyui-dialog" data-options="iconCls:'icon-save'" style="width:800px;height:400px;padding:10px">
		<iframe id="dialogIframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
	</div>
</body>
</html>