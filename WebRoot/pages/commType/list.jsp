<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>通用状态/类型管理</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/commType/js/list.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:20px;height:auto">
			<div class='searchBox' style="width:420px">
				<input class="searchInput" id="commTypeName"  style="border-radius:5px 0 0 5px "
					placeholder="按状态名称搜索"  onkeydown="if(event.keyCode==13){searchMethod()}"><select 
					id="affiliationType" onchange="searchMethod()" class="sele" style="border-radius:0;">
				<option value="-1">-请选择归属类型-</option>
				<option value="1">话单模版状态</option>
				<option value="2">预设结果模版状态</option>
				<option value="3">项目状态</option>
				<option value="4">用例状态</option>
				<option value="5">执行结果状态</option>
				<option value="6">选择方式状态</option>
				<option value="7">用例执行状态</option>
				<option value="8">预设结果模版类型</option>
				</select><a href="javascript:searchMethod()" style="left:350px;" class="btn searchbutton">查询</a>
			</div>
				<a href="javascript:addMethod()" class="btn headerBtn">新增类型</a>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'commType/listPage',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="commTypeName" width="280">名称</th>
					<th  data-options="field:'affiliationType',width:'280',formatter:formataffiliationType">归属类型</th>
					<th align="center" data-options="field:'id',width:'150',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>
</body>
</html>