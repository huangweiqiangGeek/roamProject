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
<title>增加车辆标识</title>
<script type="text/javascript">
if("close"=="${close}"){
	var parentId = window.parent.document.getElementById("parentId");
	if(parentId!=null){
		alert("保存成功");
	}
		window.parent.closeDialog(true);
}
</script>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/buscardsic/addBusCardsIC.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="overflow: no">
    <form id="addForm" name="addForm" action="busCards/addBusCardsIC" method="post">
    <input type="hidden" name="id" value="${busCardsIC.id }"/>
    <input type="hidden" name="type_name" id="type_name" />
    <input type="hidden" name="copCode" id="${busCardsIC.copCode }" />
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
    		<tr>
    			<th>媒介类型：</th>
    			<td>
    				<select type="text" id="type_id" name="type_id" value ="1" style="width:200px" disabled="disabled">
    					<option value="1">C-IC卡</option>
    				</select>
    			</td>
    			<th><font color="red">*</font>媒介编号：</th>
    			<td><input type="text" id="mediumNo" name="mediumNo" style="width:200px" maxlength="7"></td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>有效开始日期：</th>
    			<td><input id="start_date_in" name="start_date_in"  class="easyui-datebox"  style="width:200px" required="true"></td>
    			<th><font color="red">*</font>有效期：</th>
    			<td><input type="text" id="period_date" name="period_date" style="width:200px" maxlength="2">月</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>绑定车牌号码：</th>
    			<td><input type="text" id="plat_no" name="plat_no" style="width:200px"  maxlength="15"></td>
    			<th><font color="red">*</font>主管海关代码：</th>
    			<td><select id="customs_code" name="customs_code" >
    			</select></td>
 		    	
    		</tr>
    		<tr>
    			 <th>申报人：</th>
    			<td><font size="2">${session_user.cn_name}</font><input type="hidden" id="update_user_name" name="update_user_name" style="width:200px" value="${session_user.cn_name}"></td>
    		<th></th>
    			<td></td>
 		    </tr>
    		<tr>
    			<th>备注：</th>
    			<td colspan="4"><textarea type="textArea" id="remark" name="remark" style="width:550px;height:40px"  maxlength="200"></textarea></td>
    		</tr>
    	</table>
   	<div align="center" style="mpadding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="javascript:addForm.reset();" class="easyui-linkbutton" data-options="">重置</a>
	</div>
	 </form>
</body>
