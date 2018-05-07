<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>人员管理</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/sysmanage/sysUserManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="22%" valign="top">
		<div id="tree_div" style="width: 100%;overflow-y:auto ">
			<ul id="tt" class="easyui-tree" data-options="onClick:onClickTree,onLoadSuccess:onLoadSuccess,idFiled:'id',textFiled:'name',parentField:'parent_id',url:'sysOrg/querySysOrgList',animate:true"></ul>
		</div>
	</td>
	<td width="78%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
				<input type="hidden" id="org_id" value="${root_org_id }">
				姓名: <input class="text" style="width:160px" id="cn_name" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				手机: <input class="text" style="width:140px" id="mobile" maxlength="15" onkeydown="if(event.keyCode==13){searchMethod()}">
				账户: <input class="text" style="width:140px" id="user_code" maxlength="50" onkeydown="if(event.keyCode==13){searchMethod()}">
				<br>
				机构: <input class="text" style="width:160px" id="org_name" maxlength="50" onkeydown="if(event.keyCode==13){searchMethod()}">
				岗位: <input class="text" style="width:140px" id="post_name" maxlength="50" onkeydown="if(event.keyCode==13){searchMethod()}">
				角色: <input class="text" style="width:140px" id="role_name" maxlength="50" onkeydown="if(event.keyCode==13){searchMethod()}">
				<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				<a href="javascript:addMethod()" class="easyui-linkbutton" iconCls="icon-add" plain="false">新增</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="
					toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="cn_name" width="100">姓名</th>
					<th field="mobile" width="100">手机</th>
					<th field="user_code" width="100">账户</th>
					<th field="org_name" width="80">所属组织</th>
					<th data-options="field:'id',width:'180',formatter:formatOperate">操作</th>
					<th field="update_user_name" width="80" align="right">维护人</th>
					<th field="update_time_show" width="140" align="right">维护时间</th>
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