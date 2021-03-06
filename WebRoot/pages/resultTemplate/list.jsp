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
<title>预设结果模版管理</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/resultTemplate/js/list.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:20px;height:auto">
			<div>
				<input type="hidden" id="provinceName" name="provinceName" value="${provinceName }">
				<div class='searchBox' style="width:600px">
					<input class="searchInput" id="templateName" maxlength="20" style="border-radius: 5px 0 0 5px ;"
					 placeholder="按详单名称搜索" onkeydown="if(event.keyCode==13){searchMethod()}"><select 
					 	id="templateStatus" onchange="searchMethod()" class="sele" style="border-radius:0;">
					<option value="-1">-请选择详单状态-</option>
					<c:forEach items="${satusList}" var="sl">
					<option value="${sl.id }">${sl.commTypeName }</option>
					</c:forEach>
					</select><select id="templateType" onchange="searchMethod()" class="sele" style="border-radius: 0;">
					<option value="-1">-请选择详单类型-</option>
					<c:forEach items="${typeList}" var="tl">
					<option value="${tl.id }">${tl.commTypeName }</option>
					</c:forEach>
					</select><a href="javascript:searchMethod()" class="btn searchbutton" style="left:528px;">搜索</a>
				</div>
				<a href="resultTemplate/addInit" class="btn headerBtn">新增详单</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'resultTemplate/listPage?provinceName=${provinceName}',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="templateName" width="180">名称</th>
					<th field="typeName" width="80">类型</th>
					<c:if test="${provinceID==1}">
					<th field="provinceName" width="80">省份</th>
					</c:if>
					<th field="statusName" width="80">状态</th>
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