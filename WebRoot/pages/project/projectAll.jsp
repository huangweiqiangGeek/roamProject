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
<script type="text/javascript" src="pages/project/js/list.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
				<input type="hidden" id="proType" name="proType" value="${proType }">
				<input type="hidden" id="proProvince" name="proProvince" value="${proProvince}">
				<input type="hidden" id="proID" name="proID" value="${id}">
			</div>	
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'project/listPage?proID=${id}',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="proName" width="140">任务名称</th>
					<th field="proNumber" width="100">任务编号</th>
					<c:if test="${provinceID==1}">
					<th field="proProvince" width="80">省份</th>
					</c:if>
					<th field="proExplain" width="220">任务说明</th>
					<th align="center" field="statusName" width="60">状态</th>
					<th align="center" field="useCaseCount" width="60">用例数量</th>
					<th align="center" field="hadExe" width="60">执行状态</th>
					<th align="center" field="successRate" width="60">通过率</th>
					<th field="proRemark" width="60">备注</th>
					<th align="center" data-options="field:'createTime',width:'120',formatter:formatDt">创建时间</th>
					<th align="center"  data-options="field:'editTime',width:'120',formatter:formatDt">编辑时间</th>
					<th  align="center" data-options="field:'id',width:'480',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		<div align="center" style="padding: 5px;">
			<a href="JavaScript:history.go(-1)" style="background:#FE3E3E" class="cancelbtn headerBtn">返回</a>
		</div>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>
<div id="vessel" tittle="进度条" style="display:none;width:100%;height:1200px;background:#fff;position:absolute;top:0;left:0">
<div id="progress" tittle="进度条val" class="easyui-progressbar" style="width:600px;margin-top:180px;position:absolute;left:280px"></div>
</div>
</body>
</html>