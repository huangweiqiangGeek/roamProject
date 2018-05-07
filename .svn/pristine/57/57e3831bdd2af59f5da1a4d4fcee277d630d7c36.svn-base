<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆删除管理</title>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo.css">
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/custDistManage/custDistManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div id="tb" style="padding:5px;height:auto">
		<div>
			载货单编号: <input class="text" style="width:80px" id="distNo" maxlength="20" />
			主管海关：<select id="customsCode"></select>
			企业预录编号: <input class="text" style="width:80px" id="copNo" maxlength="20" />
			状态:  <select id="statusId">
			      </select>
			载货单类型：<select id="distTypeId">
			      </select>
			大宗散货：<select id="bulkFlag">
			      </select>
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div style="margin:10px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'custDist/queryDistHeadPassList'">
		<thead>
			<tr>
				<th field="distSeq" width="100">序号</th>
				<th field="distNo" width="100">编号</th>
				<th field="distType" width="100">类型</th>
				<th field="customs_code_name" width="100">主管海关</th>
				<th field="customsCode" width="100">主管海关代码</th>
				<th field="copNo" width="100">预录编号</th>
				<th data-options="formatter:bulkFormatter" field="bulkFlag" width="60">大宗散货</th>
				<th field="status" width="80">单据状态</th>
				<th data-options="formatter:nameFormatter2" field="id" width="80" >操作</th>
			</tr>
		</thead>
	</table>
	<div id="win_div" class="easyui-window" title="载货单信息维护" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:880px;height:520px;padding:10px;">
		<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="auto" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
	</div>
</body>
</html>