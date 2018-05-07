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
<title>环境文件管理</title>
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
<script type="text/javascript" src="pages/commConfig/js/edit.js"></script>
</head>
<body style="overflow:no">
    <div style="height:400px;overflow-y:auto;margin:0;padding:0">
    		<input type="hidden" name="id" id="id" value="${commConfig.id}"/>
    		<input type="hidden" name="provinceID" id="provinceID" value="${commConfig.provinceID}"/>
    		<input type="hidden" name="provinceName" id="provinceName" value="${commConfig.provinceName}"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbconent">
	    	<tr><td>生成标准话单后,存放标准话单文件名的文件:</td></tr>
	    	<tr><td>
				<form action="" id="filename" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					文件路径<input name="field1" value="" />
					文件名称<input name="field2" value="" />
					<input name="field3" value="txt_cdr_filename" 	type="hidden"/>
					<input name="field4" value="生成标准话单后,存放标准话单文件名的文件:" 	type="hidden"/>
					<input name="field5" value="1" 	type="hidden"/>
					<a href="javascript:savefilename()" class="savebtn commBtn">保存</a>
				</form>
				<iframe id="configuration_iframe" name="configuration_iframe" style="display:none;"></iframe>    
			</td></tr>
			<tr><td>执行后,获取免费资源执行结果的文件:	</td></tr>
	    	<tr><td>
				<form action="" id="getResourceResult" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					文件路径<input name="field1" value="" />
					文件名称<input name="field2" value="" />
					<input name="field4" value="执行后,获取免费资源执行结果的文件"  type="hidden"/>
					<input name="field3" value="txt_getResourceResult" 	type="hidden"/>
					<input name="field5" value="1" 	type="hidden"/>
					<a href="javascript:savegetResourceResult()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
			<tr><td>执行后,获取语音详单计算结果的文件：</td></tr>
	    	<tr><td>
				<form action="" id="getDetialResultOfGsmr" method="post" > 
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					文件路径<input name="field1" value="" />
					文件名称<input name="field2" value="" />
					<input name="field4" value="执行后,获取语音详单计算结果的文件"  type="hidden"/>
					<input name="field3" value="txt_getDetialResultOfGsmr" 	type="hidden"/>
					<input name="field5" value="1" 	type="hidden"/>
					<a href="javascript:savegetDetialResultOfGsmr()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
			<tr><td>执行后,获取gprs详单计算结果的文件:</td></tr>
	    	<tr><td>
				<form action="" id="getDetialResultOfGprs" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					文件路径<input name="field1" value="" />
					文件名称<input name="field2" value="" />
					<input name="field4" value="执行后,获取gprs详单计算结果的文件"  type="hidden"/>
					<input name="field3" value="txt_getDetialResultOfGprs" 	type="hidden"/>
					<input name="field5" value="1" 	type="hidden"/>
					<a href="javascript:savegetDetialResultOfGprs()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
			<tr><td>执行后,获取短彩信详单计算结果的文件:</td></tr>
	    	<tr><td>
				<form action="" id="getDetialResultOfMsg" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					文件路径<input name="field1" value="" />
					文件名称<input name="field2" value="" />
					<input name="field4" value="执行后,获取短彩信详单计算结果的文件"  type="hidden"/>
					<input name="field3" value="txt_getDetialResultOfMsg" 	type="hidden"/>
					<input name="field5" value="1" 	type="hidden"/>
					<a href="javascript:savegetDetialResultOfMsg()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
			<tr><td>执行后,获取Wlan详单计算结果的文件:</td></tr>
	    	<tr><td>
				<form action="" id="getDetialResultOfWlan" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					文件路径<input name="field1" value="" />
					文件名称<input name="field2" value="" />
					<input name="field4" value="执行后,获取Wlan详单计算结果的文件"  type="hidden"/>
					<input name="field3" value="txt_getDetialResultOfWlan" 	type="hidden"/>
					<input name="field5" value="1" 	type="hidden"/>
					<a href="javascript:savegetDetialResultOfWlan()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
    	</table>
    </div>
    <!-- <div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div> -->
   	
</body>