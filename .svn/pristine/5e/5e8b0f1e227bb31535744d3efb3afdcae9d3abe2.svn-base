<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加修改车辆</title>
<script type="text/javascript">
if("close"=="${close}"){
	window.parent.closeWin(true);
}else if("repeat"=="${repeat}"){
	alert('保存失败，该车牌号已存在');
}else if("re"=="${re}"){
	alert('保存失败，该车牌号已备案');
}else if("repeatAs"=="${repeatAs}"){
	alert('保存失败，该辅助车牌号已存在');
}else if("reAs"=="${reAs}"){
	alert('保存失败，该辅助车牌号已备案');
}
</script>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/busmanage/addOrUpdateBusVehicles.js"></script>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="busVehicles/saveBusVehicles" method="post">
    	<input type="hidden" id = "id" name="id" value="${busVehicles.id }"/>
    	<input type="hidden" id = "status_id" name="status_id" value="${busVehicles.status_id }"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
    		<tr>
    			<th style="width:100px;"><font color="red">*</font>车牌号：</th>
    			<td style="width:200px;">
    				<input type="type" id="plat_no" name="plat_no" value="${busVehicles.plat_no }" style="width:200px" maxlength="20"/>
    			</td>
    			<th  style="width:100px;"><font color="red">*</font>证件号：</th>
    			<td style="width:200px;"><input type="text" id="certifacate_no" name="certifacate_no" style="width:200px" value="${busVehicles.certifacate_no }" maxlength="10"></td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>车辆类型：</th>
    			<td><select id="type_id" name="type_id" >
    			</select>
    			</td>
    			<th><font color="red">*</font>工具类型：</th>
    			<td>
    			<select id="tool_type_id" name="tool_type_id" onchange="chanageSelect()">
    			</select>
    			</td>
    		</tr>
    		<tr>
    			 <th><font color="red">*</font>主管海关代码：</th>
    			<td><select id="customs_code" name="customs_code" >
    			</select></td>
    			<th>车辆自重：</th>
    			<td><input type="text" id="displacement" name="displacement" style="width:200px" value="${busVehicles.displacement }" maxlength="10">Kg</td>
 		    	</tr>
    		<script type="text/javascript">
	    		commonGetOptions("type_id",10004,'${busVehicles.type_id }');
				commonGetOptions("tool_type_id",10003,'${busVehicles.tool_type_id }');
				commonGetCustoms("customs_code",10016,'${busVehicles.customs_code }');
    		</script>
 		    <tr id='hide_tr'>
    		    <th><font color="red">*</font>辅助车牌号：</th>
    			<td><input type="text" id="assist_plat_no" name="assist_plat_no" style="width:200px;" value="${busVehicles.assist_plat_no }" maxlength="15"></td>
    	        <th></th>
    			<td></td>
 		    </tr>
    		<tr>
    			<th>企业编码：</th>
    			<td><font size="2">${busVehicles.cop_code }</font><input type="hidden" id="cop_code" name="cop_code" style="width:200px" value="${busVehicles.cop_code }" ></td>
    			<th>企业名称：</th>
    			<td><font size="2">${busVehicles.cop_name }</font><input type="hidden" id="cop_name" name="cop_name" style="width:200px" value="${busVehicles.cop_name }"></td>
    		</tr>
    		<tr>
     			<th>联系人：</th>
    			<td><font size="2">${busVehicles.contacts }</font><input type="hidden" id="contacts" name="contacts"  style="width:200px"value="${busVehicles.contacts }" maxlength="50" ></td>
    			<th>联系电话：</th>
    			<td><font size="2">${busVehicles.contact_phone }</font><input type="hidden" id="contact_phone" name="contact_phone" style="width:200px" value="${busVehicles.contact_phone }" maxlength="6" ></td>
 		    </tr>
    		<tr>
    			<th>备注：</th>
    			<td colspan="3"><textarea type="textArea" id="remark" name="remark" style="width:550px;height:60px" value="${busVehicles.remark }"></textarea></td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="mpadding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
	
</body>