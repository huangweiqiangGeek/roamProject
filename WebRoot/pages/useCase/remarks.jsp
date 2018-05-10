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
<title>问题描述</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/useCase/js/remarks.js"></script>
<style type="text/css">
	#aSave{
		margin:0 30px;
	}
	.datagrid-cell-group{
		text-align:left;
	}
	.remarks{
		font-size:20px;
	}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<form action="useCase/useCaseRemarkSave" method="post" id="remarks">
	<input type="hidden" name="id"  id="useCase_id" />
	<textarea class="remarks" rows="10" cols="37" name="uCRemark">${data.uCRemark}</textarea>
</form>
<div align="center" style="padding: 5px;">
	<a id="aSave" onclick="saveMethod()" class="savebtn headerBtn" >保存</a>
</div>
</body>
<script type="text/javascript">
	$(function(){
		
	})
</script>
</html>