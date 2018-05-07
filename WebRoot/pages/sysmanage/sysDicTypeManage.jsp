<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>字典管理</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/sysmanage/sysDicTypeManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div id="tb" style="padding:5px;height:auto">
		<div>
			代码: <input class="text" style="width:80px" id="code" maxlength="50">
			字典名称: <input class="text" style="width:120px" id="name" maxlength="50">
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			<a href="javascript:addMethod()" class="easyui-linkbutton" iconCls="icon-add">新增</a>
		</div>
	</div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'sysDic/querySysDicTypeList'">
		<thead>
			<tr>
				<th field="code" width="160">代码</th>
				<th field="name" width="260">字典名称</th>
				<th data-options="field:'code_build_type',width:'160',formatter:formatCodeBuildType" >字典编码方式</th>
				<th data-options="field:'id',width:'160',formatter:formatOperate">操作</th>
				<th field="update_user_name" width="80" align="right">最近维护人</th>
				<th field="update_time_show" width="160" align="right">最近维护时间</th>
			</tr>
		</thead>
	</table>
	<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;">
		<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
	</div>
</body>
</html>