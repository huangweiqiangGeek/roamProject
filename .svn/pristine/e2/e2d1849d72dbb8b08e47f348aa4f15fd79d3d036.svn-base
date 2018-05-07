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
<script type="text/javascript" src="pages/regressionUseCase/js/list.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
				<input type="hidden" id="proID" name="proID" value="${proID }">
				<input type="hidden" id="proType" name="proType" value="1">
				用例名称: <input class="text" style="width:140px" id="uCName" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				用户号: <input class="text" style="width:140px" id="uCUserID" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				用例编号: <input class="text" style="width:140px" id="uCNumber" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				用例状态: 
				<select id="templateStatus" onchange="searchMethod()" class="easyui-combobox">
				<option value="-1">-请选择状态-</option>
				<c:forEach items="${satusList}" var="sl">
				<option value="${sl.id }">${sl.commTypeName }</option>
				</c:forEach>
				</select>
				执行状态: 
				<select id="templateType" onchange="searchMethod()" class="easyui-combobox">
				<option value="-1">-请选择类型-</option>
				<c:forEach items="${typeList}" var="tl">
				<option value="${tl.id }">${tl.commTypeName }</option>
				</c:forEach>
				</select>
				<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				<a href="useCase/addInit?proID=${proID}" class="easyui-linkbutton" iconCls="icon-add" plain="false">新增</a>
				<a onclick="removeAll()" title="批量删除" style="cursor: pointer;" class="easyui-linkbutton"  plain="false">批量删除</a>
				<a onclick="executeCaseAll()" title="批量执行" style="cursor: pointer;" class="easyui-linkbutton"  plain="false">批量执行</a>
				<a onclick="executeProjectCase(${proID})" title="项目执行" style="cursor: pointer;" class="easyui-linkbutton"  plain="false">项目执行</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'regressionUseCase/listPage?proID=${proID}&uCUserID=${uCUserID}',toolbar:'#tb',rownumbers:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th  data-options="field:'id',width:'40',checkbox:true">全选</th>
					<th field=productId width="180">产品 ID</th>
					<th field=uCUserID width="180">用户号码</th>
					<th field=uCName width="180">名称</th>
					<th field=uCNumber width="180">用例编号</th>
					<th field=uCScene width="240">业务场景</th>
					<th align="center" field="statusName" width="60">用例状态</th>
					<th align="center" field="passName" width="60">执行状态</th>
					<th align="center" field="executeNum" width="60">执行次数</th>
					<th align="center" data-options="field:'createTime',width:'120',formatter:formatDt">创建时间</th>
					<th align="center" data-options="field:'editTime',width:'120',formatter:formatDt">编辑时间</th>
				    <th align="center" data-options="field:'proID',width:'280',formatter:formatOperate">操作</th> 
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