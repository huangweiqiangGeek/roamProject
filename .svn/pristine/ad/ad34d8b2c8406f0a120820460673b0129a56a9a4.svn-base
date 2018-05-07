<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>在线用户管理</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/sysmanage/sysAccessUserManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div id="tb" style="padding:5px;height:auto">
		<div>
			账户: <input class="text" style="width:120px" id="user_code" maxlength="50">
			姓名: <input class="text" style="width:80px" id="user_name" maxlength="20">
			IP: <input class="text" style="width:140px" id="ip" maxlength="15">
			登陆时间: <input type="text" class="easyui-datebox" id="stime" style="width:80px">
			-<input class="easyui-datebox" type="text" id="etime" style="width:80px">
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'sysAccessUser/querySysAccessUserList'">
		<thead>
			<tr>
				<th field="user_code" width="140">账户</th>
				<th field="user_name" width="100">姓名</th>
				<th field="mobile" width="120">手机</th>
				<th field="telephone" width="120">电话</th>
				<th field="ip" width="120" align="right">IP</th>
				<th field="access_time" width="120" align="right">登陆时间</th>
				<th data-options="field:'user_id',width:'100',formatter:formatOperate">操作</th>
			</tr>
		</thead>
	</table>
</body>
</html>