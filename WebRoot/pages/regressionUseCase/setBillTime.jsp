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
<title>获取时间</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/useCase/js/setBillTime.js"></script>
</head>
<body style="overflow: no">
    		<input type="hidden" id="uCUserID" name="uCUserID" value="${uCUserID}"/>
    		<input type="hidden" id="scriptUrl" name="scriptUrl" value="${scriptUrl}"/>
    		<input type="hidden" id="ipAddress" name="ipAddress" value="${commConfig.ipAddress}"/>
    		<input type="hidden" id="hostName" name="hostName" value="${commConfig.hostName}"/>
    		<input type="hidden" id="hostPassWord" name="hostPassWord" value="${commConfig.hostPassWord}"/>
    		<input type="hidden" id="field1" name="field1" value="${commConfig.field1}"/>
    		<input type="hidden" id="field2" name="field2" value="${commConfig.field2}"/>
    		<input type="hidden" id="field3" name="field3" value="${commConfig.field3}"/>
    		<input type="hidden" id="field4" name="field4" value="${commConfig.field4}"/>
    		<input type="hidden" id="field5" name="field5" value="${commConfig.field5}"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbconent">
    		<tr>
    			<th><font color="red">*</font>产品ID：</th>
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox"  id="proId"  data-options="required:true,missingMessage:'请输入产品ID'"  name="proId" style="width:260px" maxlength="20"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>场景: </th>
    			<td colspan="5">
				<select id="sceneType" name="sceneType" class="easyui-combobox">
				<option value="1" >套餐内</option>
				<option value="2" >跨套餐</option>
				</select>
    			</td>
    		</tr>
    	</table>
   	<div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
</body>