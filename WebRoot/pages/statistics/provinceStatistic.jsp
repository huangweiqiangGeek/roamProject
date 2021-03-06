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
<script type="text/javascript" src="pages/statistics/js/province.js"></script>
<style>
	#pTestPassRate{
		width:60%;
		height:250px;
		float: left;
	}
	#pTestPassRate1{
		width:100%;
		height:170px;
		float: left;
	}
	#pTestPassRate2{
		width:100%;
		height:170px;
		float: left;
	}
	#pTestPassRate3{
		width:100%;
		height:170px;
		float: left;
	}
	.handleBtn1{
		width:120px;
		height:30px;
		line-height:30px;
		text-align:center;
		border-radius:5px;
	}
	.canvas{
		width:13%;
		height:190px;
		float: left;
	}
	.canvasTxt{
		width:80%;
		height:20px;
		margin:0 auto;
		text-align:center;
		line-height:20px;
		font-size:16px;
		color:#000000;
		font-style:italic;
		float: left;
		font-weight:bold;
	}
</style>

</head>
<body marginheight="0" marginwidth="0" style="overflow: auto;padding:0">
	<input type="hidden" name="homeProjectNames" id="homeProjectNames" value="" />
	<input type="hidden" name="projectUseCasePassRates" id="projectUseCasePassRates" value="" />
	<div id="pTestPassRate"></div>
	<div class="canvas"><canvas id="pTestPassRate1" width="142" height="170"></canvas><div class='canvasTxt'>用例通过数</div></div>
	<div class="canvas"><div id="pTestPassRate2"></div><div class='canvasTxt'>用例通过率</div></div>
	<div class="canvas"><canvas id="pTestPassRate3" width="142" height="170"></canvas><div class='canvasTxt'>用例执行数</div></div>
	<div style="width:96%;overflow:auto;padding:0 20px 0 20px;">
		<a class="btn handleBtn1" onClick="lookList()" style="margin-bottom:5px;">查看全部</a>
		<a class="btn handleBtn1" onClick="downLoad()" style="margin-bottom:5px;">导出数据</a>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'statisticsController/getProvinceStatistic2Project',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th align="center" field="projectName" width="290">任务名</th>
					<th align="center" field="projectUseCaseCount" width="105">用例总数</th>
					<th align="center" field="projectUseCasePassCount" width="105">通过用例</th>
					<th align="center" field="projectUseCaseFailedCount" width="105">不通过用例</th>
					<th align="center" field="projectUseCaseNodoCount" width="105">未测试用例</th>
					<th align="center" field="projectUseCaseDisableCount" width="105">未完成用例</th>
					<th align="center" field="projectUseCasePassRate" width="105">通过率</th>
					<th align="center" data-options="field:'projectId',width:'100',formatter:formatOperate">操作</th>					
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>