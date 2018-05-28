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
<title>admin用户统计管理</title>
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
<script type="text/javascript" src="pages/statistics/js/super.js"></script>
<style>
	#pTestPassRate{
		width:60%;
		height:250px;
		float: left;
	}
	#pTestPassRate1{
		width:100%;
		height:160px;
		float: left;
	}
	#pTestPassRate2{
		width:100%;
		height:160px;
		float: left;
	}
	#pTestPassRate3{
		width:100%;
		height:160px;
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
		width:12%;
		height:190px;
		float: left;
		margin-top: 40px;
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
	.switchTabs{
		width:3%;
		height:140px;
		float:left;
		margin-top: 30px;
		margin-left: 5px;
		outline: 1px solid #cccccc;
	}
	.switchTab{
		width:100%;
		height:24.7%;
		border-bottom:1px solid #cccccc;
		float:left;
		text-align: center;
		cursor: pointer;
	}
	.switchTab:last-child{
		border-bottom:none;
	}
	.switchTab1{
		background-color:#5FA1FF;
		color: #ffffff;
	}
	.switchTab:hover{
		background-color:#5FA1FF;
		color: #ffffff;
	}
	.loading_img{
		position: absolute;
		left: 32%;
		top: 5%;
		text-align: center;
		color: #333333;
		font-weight: bold;
		display: none;
	}
</style>

</head>
<body marginheight="0" marginwidth="0" style="overflow: auto;padding:0">
	<input type="hidden" name="homeProjectNames" id="homeProjectNames" value="" />
	<input type="hidden" name="projectUseCasePassRates" id="projectUseCasePassRates" value="" />
	<div class="switchTabs">
		<div class="switchTab">第一轮</div>
		<div class="switchTab">第二轮</div>
		<div class="switchTab switchTab1">第三轮</div>
		<div class="switchTab">折线图</div>
	</div>
	<div id="pTestPassRate"></div>
	<div class="canvas"><canvas id="pTestPassRate1" width="142" height="170"></canvas><div class='canvasTxt'>用例通过数</div></div>
	<div class="canvas"><div id="pTestPassRate2"></div><div class='canvasTxt'>用例通过率</div></div>
	<div class="canvas"><canvas id="pTestPassRate3" width="142" height="170"></canvas><div class='canvasTxt'>用例执行数</div></div>
	<div style="width:96%;overflow:auto;padding:0 20px 0 20px;">
		<a class="btn handleBtn1" onClick="lookList()" style="margin-bottom:5px;">查看全部</a>
		<a class="btn handleBtn1" onClick="downLoad()" style="margin-bottom:5px;">导出数据</a>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'statisticsController/getAdminProvinceStatisticProject',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
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
	</div>
	<div class='loading_img'><img src="images/loading.gif"><br>数据加载中</div>
</body>
</html>