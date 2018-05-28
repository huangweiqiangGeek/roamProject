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
<title>脚本配置管理-修改页面</title>
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
<script type="text/javascript" src="framework/js/base64.min.js"></script>
<script type="text/javascript" src="pages/commConfig/js/amend.js"></script>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="commConfig/save"  method="post">
    		<input type="hidden" name="id" id="id" value="${commConfig.id}"/>
    		<input type="hidden" name="provinceID" id="provinceID" value="${commConfig.provinceID}"/>
    		<input type="hidden" name="provinceName" id="provinceName" value="${commConfig.provinceName}"/>
    		<input type='hidden' value='${CSRFToken}' id='csrftoken'>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbconent">
    		<tr>
    			<th><font color="red">*</font>服务器地址：</th>
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox"  id="ipAddress" placeholder="脚本服务器IP地址或者域名"  data-options="required:true,missingMessage:'请输入服务器地址'"  name="ipAddress" value="${commConfig.ipAddress }" style="width:260px" maxlength="50"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>用户名：</th>
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox"  id="hostName" placeholder="登录服务器用户名" data-options="required:true,missingMessage:'请输入用户名'"  name="hostName" value="${commConfig.hostName }" style="width:260px" maxlength="20"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>密码：</th>
    			<td colspan="5">
    				<input type="password" class="easyui-validatebox"  id="hostPassWord"  data-options="required:true,missingMessage:'请输入密码'"  name="hostPassWord" value="${commConfig.hostPassWord }" style="width:260px" maxlength="100"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>路径：</th>
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox" placeholder="例如：/home/shell/" id="field1"  data-options="required:true,missingMessage:'请输入脚本路径'"  name="field1" value="${commConfig.field1 }" style="width:260px" maxlength="200"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>名称：</th>
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox" placeholder="名称" id="field2"  data-options="required:true,missingMessage:'请输入脚本路径'"  name="field2" value="${commConfig.field2 }" style="width:260px" maxlength="200"/>
    			</td>
    		</tr>
    		<tr style="display:none">
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox" id="field3"  name="field3" value="${commConfig.field3 }" style="width:260px" maxlength="200"/>
    			</td>
    		</tr>
    		<tr style="display:none">
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox" id="field4"  name="field4" value="${commConfig.field4 }" style="width:260px" maxlength="200"/>
    			</td>
    		</tr>
    		<tr style="display:none">
    			<td colspan="5">
    				<input type="type" class="easyui-validatebox" id="field5"  name="field5" value="${commConfig.field5 }" style="width:260px" maxlength="200"/>
    			</td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="savebtn handleBtn">保存</a>
		<a href="#" onclick="cancelMethod();" class="cancelbtn handleBtn">取消</a>
	</div>
</body>