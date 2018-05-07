<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/JQuery zTree v3.5.12/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="framework/JQuery zTree v3.5.12/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="framework/JQuery zTree v3.5.12/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/sysmanage/sysMenuManage.js"></script>

<link rel="stylesheet" href="framework/JQuery zTree v3.5.12/css/demo.css" type="text/css">
<link rel="stylesheet" href="framework/JQuery zTree v3.5.12/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<style type="text/css">.ztree li span.button.add {margin-left: 2px;margin-right: -1px;background-position: -144px 0;vertical-align: top;*vertical-align: middle}</style>
</head>
<body>
	<form id="menuForm" name="menuForm">
		<table width="98%" height="100%" border="0" cellspacing="0"	cellpadding="0">
			<tr>
				<td width="28%"><ul id="treeDemo" class="ztree"></ul></td>
				<td valign="top" width="70%">
					<table valign="top" width="100%" border="0" cellspacing="0"	cellpadding="0" class="formTable">
						<tr valign="top">
							<th>菜单名称：</th>
							<td>
								<input type="hidden" id="parent_id"></input> 
								<input type="hidden" id="t_id"></input> 
								<input type="hidden" id="id"></input>
								<input type="hidden" id="order_no"></input> 
								<input type="text" class="required" size="40" id="name" value="请选择菜单"></input><font color="red">*</font>
							</td>
						</tr>
						<tr valign="top">
							<th>菜单类型：</th>
							<td><select id="menu_type" onchange="changeMenu_type()"><option value="0">中间菜单</option><option value="1">功能菜单</option></select></td>
						</tr>
						<tr>
							<th>禁用：</th>
							<td><select id="state"><option value="0">否</option><option value="1">是</option></select></td>
						</tr>
						<tr>
							<th>链接地址：</th>
							<td colspan="3"><input type="text" size="40" disabled="disabled" class="url" id="href"></input></td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>