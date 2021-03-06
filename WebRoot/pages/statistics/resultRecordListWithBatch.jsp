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
<title>执行记录</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/statistics/js/resultRecordListWithBatch.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<%-- <div id="tb" style="padding:5px;height:auto;">
			<div style="overflow:auto">
				<input type="hidden" id="resultType" name="resultType" value="${resultRecord.resultType}">
				<input type="hidden" id="proID" name="proID" value="${resultRecord.proID}">
				<input type="hidden" id="uCID" name="uCID" value="${resultRecord.uCID}">
				<input type="hidden" id="idPass" name="isPass" value="${resultRecord.isPass}">
				用例名称: <input class="text" style="width:160px" id="uCName" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				用例编号: <input class="text" style="width:160px" id="uCNumber" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				执行批次: <input type="number" min="0" max="9999" class="text" style="width:160px" id="uCItemNumber" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				通过状态: 
				<select id="isPass" onchange="searchMethod()" class="easyui-combobox">
				<option value="-1">-请选择通过状态-</option>
				<option value="0">是</option>
				<option value="1">否</option>
				</select>
				<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				<a href="JavaScript:history.go(-1)" style="background:#FE3E3E" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">返回</a>
			</div>
		</div> --%>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'projectExecuteTraceController/findRecordOfUseCaseExecuteOfOneBatchOfProject?id=${projectExecuteTrace.id }',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="uCName" width="160">用例名称</th>
					<th field="uCNumber" width="160">用例编号</th>
					<th align="center" field="uCItemNumber" width="60">累计批次</th>
					<th align="center" width="60" data-options="field:'isPass',formatter:fmtIsPass">通过状态</th>
					<th align="center" data-options="field:'executeTime',width:'120',formatter:formatDt">执行时间</th>
					<th align="center" data-options="field:'id',formatter:formatOperate">操作</th>
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