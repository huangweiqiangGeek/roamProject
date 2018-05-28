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
<title>用例查看</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/statistics/js/provinceTaskList.js?01"></script>
<style>
	.tableHeader{
		background: -moz-linear-gradient(top,#DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#DBDBDB),color-stop(50%,#EAEAEA), color-stop(100%,#DBDBDB));
	    background: -webkit-linear-gradient(top, #DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	    background: -o-linear-gradient(top, #DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	    background: -ms-linear-gradient(top, #DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	    background: linear-gradient(to bottom, #DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	}
	.backBtn{
		background: red;
		color: #ffffff;
		position:absolute;
		width: 80px;
	    border-radius: 5px;
	    line-height: 22px;
		display: inline-block;
	    text-decoration: none;
	    cursor: pointer;
	    text-align: center;
	    vertical-align: bottom;
	    font-size: 10px;
	    left: 50%;
	}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto;">
<div style="position: relative;">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 15px">
<tr valign="top">
	<td width="100%" valign="top">
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'useCase/listPage?proID=${projectId}&uCUserID=&isPass=',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:30">
			<thead>
				<tr>
					<th align="center" field=productId width="150">产品名称</th>
					<th align="center" field=uCUserID width="150">用户号码</th>
					<th align="center" field=uCName width="280">用例名称</th>
					<th align="center" field="statusName" width="150">用例状态</th>
					<th align="center" field="passName" width="150">执行状态</th>
					<th align="center" field="executeNum" width="150">执行次数</th>
				</tr>
			</thead>
		</table>
	</td>
</tr>
</table>
<a href="javaScript:window.history.back()" class="backBtn">返回</a>
</div>
</body>
</html>