<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用例</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css"
	href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript"
	src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/useCase/js/edit.js"></script>
<script type="text/javascript">

</script>
</head>
<body style="overflow: no">
	<div style="" id="dStepOne">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="formTable" id="stepOne">
			<tr>
				<td class="tHead" colspan="12" style="text-align:center;background:#D1EEEE;font-size:1.2em;font-weight:blod">
					构建话单
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>选择模版：</th>
				<td colspan="6"><a href="javascript:setBillTemplate();"
					class="btn headerBtn">选择模版</a>
					<span id="spbillName" style="display: none;">已选择模版：</span></td>
					<td colspan="6"><a class="btn headerBtn" href="javascript:setHide();" id="setHide" 
					>显示</a><a class="btn headerBtn" href="javascript:getHide();" id="getHide" style="display:none"
					>隐藏</a></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="stepOnes">
		</table>
		</div>
	<div align="center" style="padding: 5px;">
		<a id="aSave" onclick="saveMethod()" class="savebtn headerBtn">保存</a>
		<a href="useCase/listInit?proID=${useCase.proID}" class="cancelbtn headerBtn">取消</a>
	</div>
	<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
</body>