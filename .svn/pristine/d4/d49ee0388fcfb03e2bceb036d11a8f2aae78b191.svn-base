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
<title>脚本配置管理</title>
<script type="text/javascript">
if("close"=="${close}"){
	window.parent.closeWin(true,"win_div_1");
}
</script>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
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
<script type="text/javascript" src="pages/commConfig/js/edit.js"></script>
</head>
<body style="overflow:no">
    <div style="height:400px;overflow-y:auto;margin:0;padding:0">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbconent">
	    	<tr><td>初始化免费资源,设置免费资源时调用的脚本:	</td></tr>
	    	<tr><td>
				<form action="" id="configuration" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					脚本路径<input name="field1" value="" />
					脚本名称<input name="field2" value="" />
					<input name="field3" value="shell_setResourceOfUserInput" 	type="hidden"/>
					<input name="field4" value="初始化免费资源,设置免费资源时调用的脚本" 	type="hidden"/>
					<input name="field5" value="0" 	type="hidden"/>
					<a href="javascript:saveconfiguration()" class="savebtn commBtn">保存</a>
				</form>
				<iframe id="configuration_iframe" name="configuration_iframe" style="display:none;"></iframe>    
			</td></tr>
			<tr><td>初始化免费资源,获取免费资源时调用的脚本:	</td></tr>
	    	<tr><td>
				<form action="" id="Resource" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					脚本路径<input name="field1" value="" />
					脚本名称<input name="field2" value="" />
					<input name="field4" value="初始化免费资源,获取免费资源时调用的脚本"  type="hidden"/>
					<input name="field3" value="shell_queryResourceByShell" 	type="hidden"/>
					<input name="field5" value="0" 	type="hidden"/>
					<a href="javascript:saveResource()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
			<tr><td>构造话单,生成标准话单时调用的脚本:</td></tr>
	    	<tr><td>
				<form action="" id="Standard" method="post" > 
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					脚本路径<input name="field1" value="" />
					脚本名称<input name="field2" value="" />
					<input name="field4" value="构造话单,生成标准话单时调用的脚本"  type="hidden"/>
					<input name="field3" value="shell_toStandardBill" 	type="hidden"/>
					<input name="field5" value="0" 	type="hidden"/>
					<a href="javascript:saveStandard()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
			<tr><td>执行用例,搬移标准话单时调用的脚本:</td></tr>
	    	<tr><td>
				<form action="" id="moveBill" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					脚本路径<input name="field1" value="" />
					脚本名称<input name="field2" value="" />
					<input name="field4" value="执行用例,搬移标准话单时调用的脚本"  type="hidden"/>
					<input name="field3" value="shell_moveBill" 	type="hidden"/>
					<input name="field5" value="0" 	type="hidden"/>
					<a href="javascript:savemoveBill()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
			<tr><td>执行后,获取免费资源执行结果时调用的脚本:</td></tr>
	    	<tr><td>
				<form action="" id="ResourceResult" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					脚本路径<input name="field1" value="" />
					脚本名称<input name="field2" value="" />
					<input name="field4" value="执行后,获取免费资源执行结果时调用的脚本"  type="hidden"/>
					<input name="field3" value="shell_getResourceResult" 	type="hidden"/>
					<input name="field5" value="0" 	type="hidden"/>
					<a href="javascript:saveResourceResult()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
    	</table>
    </div>
    <!-- <div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div> -->
   	
</body>