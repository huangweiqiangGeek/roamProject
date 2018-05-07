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
			<span>车牌号: ${plat_no }</span>
		</div>
	</div>
	<div style="margin:10px 0;"></div>
	<table id="dgd" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'busCards/queryNo?plat_no=${plat_no }'">
		<thead>
			<tr>
				<th field="mediumNo" width="60">媒介编号</th>
				<th field="type" width="80">媒介类型</th>
				<th data-options="formatter:availabilityFormatter" field="availability_flag" width="60">有效标识</th>
				<th field="customs_code_name" width="60">主管海关</th>
				<th field="customs_code" width="60">海关代码</th>
				<th field="create_user_name" width="80" align="right">创建者</th>
				<th field="create_time_show" width="120" align="right">创建时间</th>
				<th field="update_user_name" width="80" align="right">更新者</th>
				<th field="update_time_show" width="120" align="right">更新时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>