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
<title>产品列表</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/productGroup/js/list.js"></script>
<style type="text/css">
	#aSave{
		margin:0 30px;
	}
	.datagrid-cell-group{
		text-align:left;
	}
	p{
		margin::0;
		padding:0;
	}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<input type="hidden" name="proID" id="proID" value="${task_id}" />
<input type="hidden" name="useCase_id"  id="useCase_id"   value="${useCase_id}" />
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'',toolbar:'#tb',idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th colspan="6">产品组合详情表</th>
				</tr>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th field=name width="150" align="center">产品组名称</th>
					<th field=productsMark width="250" align="center">产品详情</th>
					<th field=phone_number_str width="150" align="center">号码详情</th>
				    <th align="center" width="140" data-options="field:'proID',width:'120',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		<div align="center" style="padding-top:20px;">
						<a id="aSave" onclick="savePrdList()" class="savebtn headerBtn" >保存</a> 
		</div>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>   
	</td>
</tr>
</table>
<script>
	var task_id=$("#proID").val();
	console.log(task_id);
	var params={task_id:task_id};
	$("#dg").datagrid({url:"productGroupController/queryProductGroupListToTask",queryParams:params});
</script>
</body>
</html>