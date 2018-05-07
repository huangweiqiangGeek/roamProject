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
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
				<input type="hidden" id="provinceID" name="provinceID" value="${provinceID }">
				<input type="hidden" id="templateStatus" name="templateStatus" value="1">
				名称: <input class="text" style="width:160px" id="templateName" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'billTemplate/queryListPage',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pageSize:10">
			<thead>
				<tr>
					<th  align="center" data-options="field:'id',width:'120',formatter:formatCheckBox">选择</th>
					<th field="templateName" width="180">名称</th>
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
	var s = $("#txt"+data).attr('data').replace(/\|/g, "/").replace(/#/g, '"');
	window.parent.$("#attrJson").val(s);
	window.parent.$("#billId").val(data);
	window.parent.configBillTemplate();
	cancelMethod();
	
}
</script>
</body>
</html>