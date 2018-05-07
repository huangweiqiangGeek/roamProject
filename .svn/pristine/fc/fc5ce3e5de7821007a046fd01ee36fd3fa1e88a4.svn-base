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
<title>免费资源获取测试</title>
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
<script type="text/javascript" src="pages/commConfig/js/gainResour.js"></script>
</head>
<body style="overflow:no">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="formTable" id="formTable">
    		<tr>
    		<th><font color="red">*</font>用户号码：</th>
				<td colspan="3"><input type="type" id="uCUserID" name="tuCUserID"
					class="easyui-validatebox" style="width: 220px" maxlength="20" />
				</td>
			</tr>
			<tr>	
				<th><font color="red">*</font>产品ID：</th>
				<td  colspan="3"><input type="type" id="proName" name="proName"
					class="easyui-validatebox" style="width: 220px" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>赋值方式</th>
				<td><select id="type" onchange="setAssignment(this)" name="type" 
				  style="width: 120px;height: 24px;">
							<option value="0">本月套餐</option>
							<option value="1">上月结转</option>
							<option value="2">全部填写</option>
					</select>
				</td>
			</tr>
    	</table>


	    <table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="formTable" id="ContrastTable" style="display:none">
			<tr>
    			<th>获取结果</th>
    			<td colspan="4"><span class="finalResult"></span></td>
			</tr>
			<tr id="data">
    			<th>系统获取</th>
    			<td>手机号码：<span class="sysPhone"></span></td>
			</tr>
    	</table>

    <div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();"  class="savebtn headerBtn">提交</a>
		<a href="#" onclick="cancelMethod();"  class="cancelbtn headerBtn">取消</a>
	</div>	
	<div id="load" align="center" style="padding:5px;display:none">
		<p style="color:red">免费资源正在设置中.......</p>
	</div>		
</body>