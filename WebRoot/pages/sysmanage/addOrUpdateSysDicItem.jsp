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
<title>添加修改字典明细</title>
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
<script type="text/javascript" src="pages/sysmanage/addOrUpdateSysDicItem.js"></script>
</head>
<body>
    <form id="addOrUpateForm" action="sysDic/saveSysDicItem" method="post">
    	<input type="hidden" name="id" value="${sysDicItem.id }"/>
    	<input type="hidden" name="dic_type_id" value="${sysDicItem.dic_type_id }"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
    		<tr>
    			<th>代码：</th>
    			<td>
    				<c:if test="${sysDicItem.code_build_type == 1 }">
    					${sysDicItem.code }<input type="hidden" id="code" name="code" value="${sysDicItem.code }"/>
    				</c:if>
    				<c:if test="${sysDicItem.code_build_type == 2 }">
	    				<input type="text" id="code" name="code" value="${sysDicItem.code }" style="width:200px" maxlength="50"/>
    				</c:if>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>字典明细名称：</th>
    			<td><input type="text" id="name" name="name" style="width:200px" value="${sysDicItem.name }" maxlength="50"></td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
</body>