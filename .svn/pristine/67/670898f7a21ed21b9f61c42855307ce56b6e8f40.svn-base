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
	.datagrid-row{
		height:20px;
	}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto;padding-top:0;padding-left:80px;padding-right:80px;">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'statisticsController/getAdminProvinceStatisticProject',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:50">
			<thead>
				<tr>
					<th align="center" field="provinceName" width="290">省名称</th>
					<th align="center" field="provinceUseCaseCount" width="105">用例总数</th>
					<th align="center" field="provinceUseCasePassCount" width="105">通过用例</th>
					<th align="center" field="provinceUseCaseFailedCount" width="105">不通过用例</th>
					<th align="center" field="provinceUseCaseNodoCount" width="105">未测试用例</th>
					<th align="center" field="provinceUseCaseDisableCount" width="105">未完成用例</th>
					<th align="center" field="provinceUseCasePassRate" width="105">通过率</th>
					<th align="center" data-options="field:'projectId',width:'100',formatter:formatOperate">操作</th>					
				</tr>
			</thead>
		</table>
	</td>
</tr>
</table>

</body>
</html>