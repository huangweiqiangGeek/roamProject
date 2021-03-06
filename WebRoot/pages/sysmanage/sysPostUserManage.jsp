<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>岗位人员管理</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/sysmanage/sysPostUserManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<input type="hidden" id="post_id" value="${post_id }">
	<div class="easyui-tabs" style="width:726px;height:400px">
		<div title="已选人员" style="padding:1px">
			<div id="tb_1" style="padding:5px;height:auto">
				<div>
					姓名: <input class="text" style="width:80px" id="cn_name_1" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod(1)}">
					账户: <input class="text" style="width:80px" id="user_code_1" maxlength="50" onkeydown="if(event.keyCode==13){searchMethod(1)}">
					<a href="javascript:searchMethod(1)" class="easyui-linkbutton" iconCls="icon-search">查询</a>
					<a href="javascript:removeMethod()" class="easyui-linkbutton" iconCls="">移除</a>
				</div>
			</div>
			<table id="dg_1" class="easyui-datagrid" title="" data-options="
						toolbar:'#tb_1',rownumbers:true,singleSelect:false,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,
						url:'sysPost/querySysPostUserIncludeList?post_id=${post_id }'">
				<thead>
					<tr>
						<th data-options="checkbox:true"></th>
						<th field="cn_name" width="100">姓名</th>
						<th field="user_code" width="100">账户</th>
						<th field="mobile" width="80">手机</th>
						<th field="telephone" width="100">电话</th>
						<th field="idcard_no" width="140">身份证</th>
						<th field="update_user_name" width="80" align="right">维护人</th>
						<th field="update_time_show" width="120" align="right">维护时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div title="未选人员" style="padding:1px">
			<div id="tb_2" style="padding:5px;height:auto">
				<div>
					姓名: <input class="text" style="width:80px" id="cn_name_2" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod(2)}">
					账户: <input class="text" style="width:80px" id="user_code_2" maxlength="50" onkeydown="if(event.keyCode==13){searchMethod(2)}">
					<a href="javascript:searchMethod(2)" class="easyui-linkbutton" iconCls="icon-search">查询</a>
					<a href="javascript:addMethod()" class="easyui-linkbutton" iconCls="">添加</a>
				</div>
			</div>
			<table id="dg_2" class="easyui-datagrid" title="" data-options="
						toolbar:'#tb_2',rownumbers:true,singleSelect:false,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,
						url:'sysPost/querySysPostUserUncludeList?post_id=${post_id }'">
				<thead>
					<tr>
						<th data-options="checkbox:true"></th>
						<th field="cn_name" width="100">姓名</th>
						<th field="user_code" width="100">账户</th>
						<th field="mobile" width="80">手机</th>
						<th field="telephone" width="100">电话</th>
						<th field="idcard_no" width="140">身份证</th>
						<th field="update_user_name" width="80" align="right">维护人</th>
						<th field="update_time_show" width="120" align="right">维护时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>