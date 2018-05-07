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
<title>车辆申报管理</title>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo.css">
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/busDistManage/busDistManage.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<div id="tb" style="padding:5px;height:auto">
		<div>
			载货单编号: <input class="text" style="width:80px" id="distNo" maxlength="20" value=""/>
			主管海关：<select id="customsCode"></select>
			企业预录编号: <input class="text" style="width:80px" id="copNo" maxlength="20" value=""/>
			状态:  <select id="statusId" name="statusId">
			      </select>
			载货单类型：<select id="distTypeId" name="distTypeId">
			      </select>
			大宗散货：<select id="bulkFlag" name="bulkFlag" >
			      </select>
			<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			<a href="javascript:addMethod()" class="easyui-linkbutton" iconCls="icon-add">新增</a>
		</div>
	</div>
	<div style="margin:10px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="" data-options="
				toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,url:'distHead/queryDistList'">
		<thead>
			<tr>
				<th field="distSeq" width="100">序号</th>
				<th field="distNo" width="100">编号</th>
				<th field="distType" width="100">类型</th>
				<th field="customs_code_name" width="80">主管海关</th>
				<th field="customsCode" width="100">主管海关代码</th>
				<th field="copNo" width="80">预录编号</th>
				<th data-options="formatter:bulkFormatter" field="bulkFlag" width="60">大宗散货</th>
				<th field="countCar" width="40">车辆</th>
				<th field="countBus" width="60">业务单据</th>
				<th field="countCargo" width="60">货物信息</th>
				<th field="status" width="70">单据状态</th>
				<th field="appStatus" width="80">审批状态</th>
				<th data-options="formatter:nameFormatter" field="declaration" width="120">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win_div" class="easyui-window" title="载货单信息维护" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:880px;height:520px;padding:10px;">
		<iframe id="win_div_iframe" src="" height="100%" width="100%"  frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
	</div>
</body>
</html>