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
<title>话单模版管理</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/billtemplate/js/list.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
				<input type="hidden" id="provinceID" name="provinceID" value="${provinceID }">
				名称: <input class="text" style="width:160px" id="templateName" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				状态: 
				<select id="templateStatus" onchange="searchMethod()" class="easyui-combobox">
				<option value="-1">-请选择状态-</option>
				<c:forEach items="${commTypes}" var="ct">
				<option value="${ct.id }">${ct.commTypeName }</option>
				</c:forEach>
				</select>
				<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				<a href="billTemplate/addInit" class="easyui-linkbutton" iconCls="icon-add" plain="false">新增</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'billTemplate/listPage',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="templateName" width="180">名称</th>
					<c:if test="${provinceID==1}">
					<th field="provinceName" width="80">省份</th>
					</c:if>
					<th field="commTypeName" width="80">状态</th>
					<th data-options="field:'templateRemark',width:'200',formatter:formatAttrJson">备注</th>
					<th data-options="field:'createTime',width:'180',formatter:formatDt">创建时间</th>
					<th   data-options="field:'editTime',width:'180',formatter:formatDt">编辑时间</th>
					<th  align="center" data-options="field:'id',width:'180',formatter:formatOperate">操作</th>
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