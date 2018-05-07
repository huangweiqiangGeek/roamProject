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
<title>添加修改字典类型</title>
<script type="text/javascript">
if("close"=="${close}"){
	window.parent.closeWin(true,"win_div_1");
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
<script type="text/javascript" src="pages/sysmanage/addOrUpdateSysDicType.js"></script>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="sysDic/saveSysDicType" method="post">
    	<input type="hidden" name="id" value="${sysDicType.id }"/>
    	<input type="hidden" id="code_build_type" name="code_build_type" value="${sysDicType.code_build_type }"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
    		<tr>
    			<th>代码：</th>
    			<td>
    				<c:if test="${sysDicType.id != null }">
    					${sysDicType.code }
    					<input type="hidden" id="code" name="code" value="${sysDicType.code }"/>
    				</c:if>
    				<c:if test="${sysDicType.id == null }">
	    				<input type="type" id="code" name="code" style="width:200px" maxlength="50"/>
    				</c:if>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>字典名称：</th>
    			<td><input type="text" id="name" name="name" style="width:200px" value="${sysDicType.name }" maxlength="50"></td>
    		</tr>
    		<tr>
    			<th>编码生成方式：</th>
    			<td>
    				<select id="code_build_type_select" onchange="changeCodeBuildType()" >
	    				<option value="1">自动生成</option>
	    				<option value="2">手工输入</option>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<th>备注：</th>
    			<td><input type="text" id="remark" name="remark" style="width:200px" value="${sysDicType.remark }" maxlength="50"></td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
</body>