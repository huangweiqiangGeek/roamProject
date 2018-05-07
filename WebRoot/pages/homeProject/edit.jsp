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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加项目</title>
<script type="text/javascript">
if("close"=="${close}"){
	window.parent.closeWin(true,"win_div_1");
}
</script>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/homeProject/js/edit.js"></script>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="homeProject/saveHomeProject"  method="post">
   		<input type="hidden" name="id"  id="id" value="${homeProject.id}"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" >
    		<tr>
    			<th><font color="red">*</font>项目名称：</th>
    			<td colspan="5">
    				<input type="type"  id="name"  name="name"  data-options="required:true,missingMessage:'请输入项目名称'" 
    				class="easyui-validatebox" value="${homeProject.name}" style="width:260px" maxlength="20"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>项目说明：</th>
    			<td colspan="5">
    			<input type="text" id="introduction" name="introduction" data-options="required:true,missingMessage:'请输入项目说明'" 
    			style="width:260px" value="${homeProject.introduction}" maxlength="50">
    			</td>
    		</tr>
    		</table>
    </form>
   	<div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="savebtn headerBtn">保存</a>
		<a href="javascript:cancelMethod();" class="cancelbtn headerBtn">取消</a>
	</div>
</body>