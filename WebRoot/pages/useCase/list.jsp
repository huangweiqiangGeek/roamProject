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
<script type="text/javascript" src="pages/useCase/js/list.js?01"></script>
<style>
	.tableHeader{
		background: -moz-linear-gradient(top,#DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#DBDBDB),color-stop(50%,#EAEAEA), color-stop(100%,#DBDBDB));
	    background: -webkit-linear-gradient(top, #DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	    background: -o-linear-gradient(top, #DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	    background: -ms-linear-gradient(top, #DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	    background: linear-gradient(to bottom, #DBDBDB 0%,#EAEAEA 50%,#DBDBDB 100%);
	}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="height:120px;">
		<div style="margin-bottom: 10px;line-height:32px;border-bottom:1px solid #5F9EA0;border-radius:5px;" 
		class="tableHeader">
		 <span  style="font-weight: bold;display:inline-block;margin-left:20px;">任务名称：</span><span 
		 style="display:inline-block;margin-right:30px;color:#ff3030">&nbsp;&nbsp;&nbsp;${proName}</span>
		  <span  style="font-weight: bold;">任务编号：</span><span style="color:#ff3030">&nbsp;&nbsp;&nbsp;${proNumber}</span>
		</div>
			<div style="overflow:auto;padding:0 20px 10px">
				<input type="hidden" id="proID" name="proID" value="${proID }">
				<input type="hidden" id="proType" name="proType" value="1">
				<input type="hidden"  name="isPass" value="${isPass }">
				<div class='searchBox' style="width:600px;">
					<input placeholder="按产品ID搜索" class="searchInput" id="productId" maxlength="20" 
					onkeydown="if(event.keyCode==13){searchMethod()}" style="border-radius: 5px 0 0 5px ;"><input placeholder="按用户号码搜索" 
					class="searchInput" id="uCUserID" maxlength="20" style="border-radius:0;"
					onkeydown="if(event.keyCode==13){searchMethod()}"><input placeholder="按用例名称搜索" 
					class="searchInput" id="uCName" maxlength="20" style="border-radius:0;"
					 onkeydown="if(event.keyCode==13){searchMethod()}"><a href="javascript:searchMethod()" class="btn searchbutton"style="left:536px;">搜索</a>
				</div>
				<div style="display:inline-block;width:400px;height:30px;margin:3px 0">
					<select id="uCStauts" name="uCStauts"  onchange="searchMethod()" class="sele" style="border-radius:5px 0 0 5px">
						<option value="-1">-按用例状态查询-</option>
						<option value="15">启用</option>
						<option value="16">禁用</option>
					</select><select id="isPass" name="isPass" onchange="searchMethod()" class="sele">
						<option value="-1">-按用例执行状态查询-</option>
						<!-- <c:forEach items="${typeList}" var="ntl">
						<option value="${tl.id }">${tl.commTypeName }</option>
						</c:forEach>  -->
						<option value="17">待执行</option>
						<option value="18">执行通过</option>
						<option value="19">执行不通过</option>
						<option value="21">执行未完成</option>
					</select><a href="javascript:searchMethod()" class="btn searchbutton">查询</a>
				</div>
				<a href="useCase/addInit?proID=${proID}" class="btn headerBtn">
					新增用例
				</a><a onclick="copyUsecase()" title="复制用例" class="btn headerBtn">
					复制用例
				</a><a onclick="removeAll()" title="批量删除" class="btn headerBtn">
					批量删除
				</a><a onclick="executeCaseAll()" title="批量执行" class="btn headerBtn">
					批量执行
				</a><a href="useCase/listInit2?proID=${proID}" title="查看详细" class="btn headerBtn">
					详细信息
				</a><a href="JavaScript:history.go(-1)" style="background:#FE3E3E" class="btn headerBtn">
					返回
				</a>
				<!-- <a onclick="regressCaseAll()" title="批量回归" class="btn">批量回归</a> -->
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'useCase/listPage?proID=${proID }&uCUserID=${uCUserID }&isPass=${isPass }',toolbar:'#tb',rownumbers:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th  data-options="field:'id',width:'40',checkbox:true">全选</th>
					<th field=productId width="180">产品ID</th>
					<th field=uCUserID width="100">用户号码</th>
					<th field=uCName width="200">用例名称</th>
					<th align="center" field="statusName" width="70">用例状态</th>
					<th align="center" field="passName" width="80">执行状态</th>
					<th align="center" field="executeNum" width="60">执行次数</th>
				    <th align="center" data-options="field:'proID',width:'300',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>
<div id="loading" style="display:none;background:#fff url('images/image/load.jpg') no-repeat;background-position: center;width:100%;height:450px;position:absolute;top:0;">
	<p style="position:absolute;top:80px;left:45%;font-size:1.4em;">用例执行中. . . </p>
</div>
</body>
</html>