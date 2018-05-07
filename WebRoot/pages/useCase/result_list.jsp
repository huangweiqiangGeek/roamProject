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
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/useCase/js/result_list.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div class='searchBox' style="width:600px;">
				<input type="hidden" id="provinceID" name="provinceID" value="${provinceID }">
				 <input class="searchInput"  style="width:160px" id="templateName" maxlength="20" placeholder="按模板名称搜索"  
				 onkeydown="if(event.keyCode==13){searchMethod()}"><select id="templateStatus"  class="sele" style="border-radius:0"
				 onchange="searchMethod()">
				<option value="-1">-请选择模板状态-</option>
				<c:forEach items="${satusList}" var="sl">
				<option value="${sl.id }">${sl.commTypeName }</option>
				</c:forEach>
				</select><select id="templateType" onchange="searchMethod()" class="sele" >
				<option value="-1">-请选择模板类型-</option>
				<c:forEach items="${typeList}" var="tl">
				<option value="${tl.id }">${tl.commTypeName }</option>
				</c:forEach>
				</select><a href="javascript:searchMethod()" class="btn searchbutton">搜索</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'resultTemplate/resultListPage?provinceID=${provinceID}',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pageSize:10">
			<thead>
				<tr>
				<th align="center" data-options="field:'id',width:'120',formatter:formatCheckBox">选择</th>
					<th field="templateName" width="320">名称</th>
					<th field="typeName" width="80">类型</th>
					<th field="statusName" width="80">状态</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:600px;height:450px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>
<script type="text/javascript">
function getResultTemplate(data,type){
	window.parent.$("#resultName").val($("#txt"+data).attr('name'));
	var s = $("#txt"+data).attr('data').replace(/\|/g, "/").replace(/#/g, '"');
	window.parent.$("#resultAttrJson").val(s);
	window.parent.$("#resultId").val(data);
	window.parent.$("#resultType").val(type);
	window.parent.configResultTemplate();
	cancelMethod();
}
</script>
</body>
</html>