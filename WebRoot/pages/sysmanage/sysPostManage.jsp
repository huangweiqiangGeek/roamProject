<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>岗位管理</title>

<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/sysmanage/sysPostManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	<td width="22%">
		<div id="tree_div" style="width: 100%;overflow-y:auto ">
			<ul id="tt" class="easyui-tree" data-options="onClick:onClickTree,onLoadSuccess:onLoadSuccess,idFiled:'id',textFiled:'name',parentField:'parent_id',url:'sysOrg/querySysOrgList',animate:true"></ul>
		</div>
	</td>
	<td width="78%">
		<div id="tb" style="padding:1px;height:auto">
			<div>
				<input type="hidden" id="org_id" value="${root_org_id }">
				岗位名称: <input class="text" style="width:80px" id="name" maxlength="50">
				职责: <input class="text" style="width:80px" id="job" maxlength="50">
				<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				<a href="javascript:addMethod()" class="easyui-linkbutton" iconCls="icon-add">新增</a>
			</div>
		</div>
		<div style="margin:1px 0;"></div>
		<table id="dg" class="easyui-datagrid" title="" data-options="
					toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="name" width="100">岗位名称</th>
					<th field="job" width="120">职责</th>
					<th field="remark" width="120">备注</th>
					<th data-options="field:'id',width:'200',formatter:formatOperate">操作</th>
					<th field="update_user_name" width="60" align="right">维护人</th>
					<th field="update_time_show" width="120" align="right">维护时间</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>
</body>
</html>