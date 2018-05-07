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
<title>添加修改岗位</title>
<script type="text/javascript">
if("close"=="${close}"){
	window.parent.closeWin(true,"win_div_1");
}else if("re"=="${re}"){
	alert("该岗位已存在");
}
</script>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="pages/sysmanage/addOrUpdateSysPost.js"></script>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="sysPost/saveSysPost" method="post">
    	<input type="hidden" name="id" value="${sysPost.id }"/>
    	<input type="hidden" name="org_id" value="${org_id }"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
    		<tr>
    			<th><font color="red">*</font>岗位名称：</th>
    			<td>
    				<input type="type" id="name" name="name" value="${sysPost.name }" style="width:260px" maxlength="30"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>所属省份：</th>
    			<td>
    				<input class="easyui-combobox" editable="false" id="provinceSpell" name="provinceSpell">
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>职责：</th>
    			<td><input type="text" id="job" name="job" style="width:260px" value="${sysPost.job }" maxlength="30"></td>
    		</tr>
    		<tr>
    			<th>备注：</th>
    			<td colspan="3"><input type="text" id="remark" name="remark" style="width:260px" value="${sysPost.remark }" maxlength="120"></td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
</body>