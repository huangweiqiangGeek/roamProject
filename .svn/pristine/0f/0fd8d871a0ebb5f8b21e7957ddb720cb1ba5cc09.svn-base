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
<title>项目管理</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/homeProject/js/list.js"></script>
<style type="text/css">
	#insert{
		position:absolute;
		top:100px;
		left:160px;
		display:none;
	}
</style>
<script>
function filedReset(){
	$('#file1').val('');
	isnext=false;
	}
</script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb"  style="padding:20px;height:auto">
			<div>
			<!--
				<input type="hidden" id="proType" name="proType" value="${proType }">
				<input type="hidden" id="proProvince" name="proProvince" value="${proProvince}">-->
				<div class='searchBox'>
					<input placeholder="按项目名称搜索" class="searchInput" id="name" maxlength="20" 
					onkeydown="if(event.keyCode==13){searchMethod()}"><a href="javascript:searchMethod()" class="btn searchbutton">搜索</a>
				</div>		
				<a href="javascript:addMethod();" class="btn headerBtn">新增</a>
			</div>	
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'homeProject/listPage',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="name" width="140">项目名称</th>
					<th field="introduction" width="220">项目说明</th>
					 <th  align="center" data-options="field:'id',width:'380',formatter:formatOperate">操作</th>
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