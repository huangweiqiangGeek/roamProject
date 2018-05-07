<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>日志管理</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/sysmanage/sysLogManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div id="tb" style="padding:5px;height:auto">
		<div>
			姓名: <input class="text" style="width:80px" id="user_name" maxlength="20">
			模块: <input class="text" style="width:100px" id="module" maxlength="15">
			操作: <input class="text" style="width:100px" id="operate" maxlength="15">
			结果: 
				<select id="operate_result">
					<option value="失败">失败</option><option value="成功">成功</option>
				</select>
			时间: <input type="text" class="easyui-datebox" id="stime" style="width:80px">
			-<input class="easyui-datebox" type="text" id="etime" style="width:80px">
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'sysLog/querySysLogList'">
		<thead>
			<tr>
				<th field="user_name" width="100">姓名</th>
				<th field="module" width="140">模块</th>
				<th field="operate" width="140">操作</th>
				<th field="operate_result" width="120">操作结果</th>
				<th field="error_message" width="60">异常</th>
				<th field="operate_time" width="140" align="right">操作时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>