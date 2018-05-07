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
<title>添加通用状态\类型</title>
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
<script type="text/javascript" src="pages/commType/js/edit.js"></script>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="commType/save"  method="post">
    		<input type="hidden" name="id" value="${commType.id}"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbconent">
    		<tr>
    			<th><font color="red">*</font>名称：</th>
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox"  id="commTypeName"  data-options="required:true,missingMessage:'请输入名称'"  name="commTypeName" value="${commType.commTypeName }" style="width:260px" maxlength="20"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>归属类型: </th>
    			<td colspan="5">
				<select id="affiliationType" name="affiliationType" class="easyui-combobox">
				<option value="1" <c:if test="${commType.affiliationType == 1}">selected</c:if>>话单模版状态</option>
				<option value="2" <c:if test="${commType.affiliationType == 2}">selected</c:if>>预设结果模版状态</option>
				<option value="3" <c:if test="${commType.affiliationType == 3}">selected</c:if>>项目状态</option>
				<option value="4" <c:if test="${commType.affiliationType == 4}">selected</c:if>>用例状态</option>
				<option value="5" <c:if test="${commType.affiliationType == 5}">selected</c:if>>执行结果状态</option>
				<option value="6" <c:if test="${commType.affiliationType == 6}">selected</c:if>>选择方式状态</option>
				<option value="7" <c:if test="${commType.affiliationType == 7}">selected</c:if>>用例执行状态</option>
				<option value="8" <c:if test="${commType.affiliationType == 8}">selected</c:if>>预设结果模版类型</option>
				</select>
    			</td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="savebtn headerBtn">保存</a>
		<a href="#" onclick="cancelMethod();" class="cancelbtn headerBtn">取消</a>
	</div>
</body>