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
<title>其他系统配置管理</title>
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
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbconent">
			<tr><td>ftp服务器所支持的协议,请输入ftp或sftp:	</td></tr>
	    	<tr><td>
				<form action="" id="ShellOfDetialResult" method="post" >
					<input name="provinceID"  type="hidden"/>
					<input name="provinceName"	type="hidden"/>
					协议：<input name="field1" value="" placeholder="例：ftp/sftp"/>
					端口：<input name="field2" value="" placeholder="端口号"/>
					<input name="field4" value="ftp服务器协议配置，请输入ftp或sftp"  type="hidden"/>
					<input name="field3" value="ftp_ftptype" 	type="hidden"/>
					<input name="field5" value="3" 	type="hidden"/>
					<a href="javascript:saveShellOfDetialResult()"  class="savebtn commBtn">保存</a>
				</form>
			</td></tr>
    	</table>
    </div>
    <!-- <div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div> -->
   	
</body>