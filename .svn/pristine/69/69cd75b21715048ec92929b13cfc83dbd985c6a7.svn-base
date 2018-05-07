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
<title>产品列表</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/product/js/list.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:20px;height:auto">
			<div style="overflow:auto;padding:0 20px 10px">
				<input type="hidden" id="proID" name="proID" value="${proID }">
				<input type="hidden" id="proType" name="proType" value="1">
				<input type="hidden"  name="isPass" value="${isPass }">
				<div class='searchBox' style="width:450px">
					<input placeholder="按产品ID搜索" class="searchInput" id="productId" maxlength="20" 
					onkeydown="if(event.keyCode==13){searchMethod()}" style="border-radius: 5px 0 0 5px ;"><input placeholder="按产品名称搜索" 
					class="searchInput" id="productName" maxlength="20" style="border-radius:0;"
					onkeydown="if(event.keyCode==13){searchMethod()}"><a 
					href="javascript:searchMethod()" class="btn searchbutton"style="left:536px;">搜索</a>
				</div>
				<a href="javaScript:addMethod()" class="btn headerBtn">
					新增产品
				</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'productController/getProductList',toolbar:'#tb',rownumbers:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field=productId	 width="180">产品ID</th>
					<th field=productName width="220">产品名称</th>
				    <th align="center" data-options="field:'proID',width:'120',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>   
	</td>
</tr>
</table>
</body>
</html>