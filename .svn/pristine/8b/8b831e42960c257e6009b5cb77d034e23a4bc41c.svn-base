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
<title>添加产品</title>
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
<script type="text/javascript" src="pages/product/js/edit.js"></script>
</head>
<body style="overflow: no;padding:15px 20px 10px;">
	<form id="addUpateForm" action="productController/save" method="post">
		<input type="hidden" id="id" name="id" value="${product.id }">
		<div id="dSubject" style="width:600px;height:160px;display:inline-block;border:2px solid #5F9EA0;margin:0 75px;">
		<table width="98%" border="0" cellspacing="0" cellpadding="0"
			class="formTable" id="subject">
			<tr>
				<td class="tHead" colspan="4" style="background:#D1EEEE;font-size:1.1em;font-weight:blod;text-align:center">
					产品基础信息
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>产品id：</th>
				<td colspan="3"><input  id="productId" name="productId" placeholder="请输入产品id"
					data-options="required:true,missingMessage:'请输入产品id'"
					class="easyui-validatebox" value="${product.productId }"
					style="width: 220px" maxlength="100" /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>产品名称：</th>
				<td colspan="3"><input  id="productName" placeholder="请输入产品名称"
					name="productName"
					data-options="required:true,missingMessage:'请输入产品名称'"
					class="easyui-validatebox" value="${product.productName }"
					style="width: 220px;" maxlength="100" />
				</td>
			</tr>
		</table>
		</div>
	</form>
	<div align="center" style="padding: 5px;">
		<a id="aSave" onclick="saveMethod()" class="savebtn headerBtn" >保存</a> 
		<a href="useCase/listInit?proID=${useCase.proID}" class="cancelbtn headerBtn" >取消</a>
	</div>
	<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
</body>