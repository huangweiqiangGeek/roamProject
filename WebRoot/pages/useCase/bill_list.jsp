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
<title>选择话单模版</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/useCase/js/bill_list.js"></script>
</head>
<body marginheight="0" marginwidth="0" ">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;">
			<div class='searchBox' style="width:600px;">
				<input type="hidden" id="provinceName" name="provinceName" value="${provinceName }">
				<input type="hidden" id="templateStatus" name="templateStatus" value="${templateStatus }">
				<input type="hidden" id="templateType" name="templateType" value="${templateType }">
				<input class="searchInput" style="width:160px" id="templateName" maxlength="20"  placeholder="按模板名称搜索"
				onkeydown="if(event.keyCode==13){searchMethod()}"><a href="javascript:searchMethod()" 
				class="btn searchbutton">搜索</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'billTemplate/queryListPage?provinceName=${provinceName }',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th  align="center" data-options="field:'id',width:'120',formatter:formatCheckBox">选择</th>
					<th field="templateName" width="180">名称</th>
					<th field="templateTypeName" width="180">模板类型</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>
<script type="text/javascript">
function getTemplateInfo(data){
	window.parent.$("#billName").val($("#txt"+data).attr('name'));
	window.parent.$("#templateType").val($("#txt"+data).attr('latetype'));
	//var s = $("#txt"+data).attr('data').replace(/\|/g, "/").replace(/#/g, '"');
	var s = $("#txt"+data).attr('data').replace(/#/g, '"');
	window.parent.$("#attrJson").val(s);
	window.parent.$("#billId").val(data);
	window.parent.$("#dStepOne").css('display','block');
	window.parent.configBillTemplate();
	cancelMethod();
	
}
</script>
</body>
</html>