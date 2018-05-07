<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>话单模版管理</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/resultRecord/js/list.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
				<input type="hidden" id="resultType" name="resultType" value="${resultRecord.resultType}">
				<input type="hidden" id="proID" name="proID" value="${resultRecord.proID}">
				<input type="hidden" id="uCID" name="uCID" value="${resultRecord.uCID}">
				<input type="hidden" id="idPass" name="isPass" value="${resultRecord.isPass}">
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'resultRecord/listPage?resultType=${resultRecord.resultType}&uCID=${resultRecord.uCID}&proID=${resultRecord.proID}&successRate=${successRate }&isPass=${ resultRecord.isPass}',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="productId" width="200">产品ID</th>
					<th field="uCUserID" width="200">用户号码</th>
					<th field="uCName" width="200">用例名称</th>
					<th field="uCNumber" width="180">用例编号</th>
					<th align="center" field="uCItemNumber" width="60">执行批次</th>
					<th align="center" width="60" data-options="field:'isPass',formatter:fmtIsPass">通过状态</th>
					<th align="center" data-options="field:'executeTime',width:'120',formatter:formatDt">执行时间</th>
					<th align="center" data-options="field:'id',width:'360',formatter:formatOperate">操作</th>
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