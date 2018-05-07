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
<title>省用户统计管理</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script src="framework/echarts/echarts.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/statistics/js/puTong.js"></script>
<style>
	#pTestPassRate{
		width:760px;
		height:250px;
		float: left;
	}
	#pTestPassRate1{
		width:285px;
		height:250px;
		float: left;
	}
</style>

</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<input type="hidden" name="homeProjectNames" id="homeProjectNames" value="" />
	<input type="hidden" name="projectUseCasePassRates" id="projectUseCasePassRates" value="" />
	<div id="pTestPassRate"></div>
	<div id="pTestPassRate1"></div>
	<div style="width:100%;overflow:auto;">
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'statisticsController/getProvinceStatistic2Project',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th align="center" field="homeProjectName" width="140">任务名</th>
					<th align="center" field="projectUseCaseCount" width="80">用例总数</th>
					<th align="center" field="projectUseCasePassCount" width="80">通过用例</th>
					<th align="center" field="projectUseCaseFailedCount" width="80">不通过用例</th>
					<th align="center" field="projectUseCaseNodoCount" width="80">未测试用例</th>
					<th align="center" field="projectUseCaseDisableCount" width="80">未完成用例</th>
					<th align="center" field="projectUseCasePassRate" width="80">通过率</th>					
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>