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
<title>环境路径管理</title>
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
	    	<tr><td>执行用例,上传txt话单的路径:	</td></tr>
	    	<tr><td>
				<form action="" id="upload" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					路径<input name="field1" value="" />
					<input name="field3" value="path_uploadTxtBillForShell" 	type="hidden"/>
					<input name="field4" value="执行用例,上传txt话单的路径" 	type="hidden"/>
					<input name="field5" value="2" 	type="hidden"/>
					<a href="javascript:saveupload()" class="savebtn commBtn">保存</a>
				</form>
				<iframe id="configuration_iframe" name="configuration_iframe" style="display:none;"></iframe>    
			</td></tr>
			<tr><td>执行用例后,获取详单结果调用的脚本所在的路径:	</td></tr>
	    	<tr><td>
				<form action="" id="DetialResult" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					服务器IP:<input name="ipAddress" value="" />
					用户名:<input name="hostName" value="" />
					用户密码<input name="hostPassWord" value="" /><br>
					路径<input name="field1" value="" />
					<input name="field4" value="执行用例后,获取详单结果调用的脚本所在的路径:"  type="hidden"/>
					<input name="field3" value="path_ShellOfDetialResult" 	type="hidden"/>
					<input name="field5" value="2" 	type="hidden"/>
					<a href="javascript:saveDetialResult()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
    	</table>
    </div>
    <!-- <div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="savebtn headerBtn">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div> -->
   	
</body>