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
<title>脚本配置管理</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/commConfig/js/list.js"></script>
<style type="text/css">
	#insert,#test{
		position:absolute;
		top:100px;
		left:160px;
		display:none;
	}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:20px;height:auto">
			<div>
			<input type="hidden" id="provinceName" name="provinceName" value="${provinceName }">
			<div class='searchBox' style="width:420px">
				 <input class="searchInput" placeholder="按服务器地址搜索" id="ipAddress"  style="border-radius:5px 0 0 5px "
				 onkeydown="if(event.keyCode==13){searchMethod()}"><input class="searchInput" id="hostName"  style="border-radius:0"
				 placeholder="按用户名搜索" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}"><a 
				 href="javascript:searchMethod()" class="btn searchbutton" style="left:352px">查询</a>
			</div>
				<a href="javascript:addinsert()" class="btn headerBtn">工具配置</a>
				<a href="javascript:addtest()" class="btn headerBtn">脚本测试</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'commConfig/listPage?provinceName=${provinceName }',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="ipAddress" width="120">服务器地址</th>
					<th field="hostName" width="120">用户名</th>
					<c:if test="${provinceID==1}">
					<th field="provinceName" width="80">省份</th>
					</c:if>
					<th field="field1" width="220">路径</th>
					<th field="field2" width="200">文件名</th>
					<th field="field4" width="280">文件说明</th>
					<th align="center" data-options="field:'id',width:'80',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>
<div  id="insert">
	<div class="easyui-panel" title="选择设置单项"  style="width:450px;height:130px;padding:10px;padding-top:30px;background:#fafafa;" 
		data-options="iconCls:'icon-save',tools:[{iconCls:'icon-cancel',handler:function(){$('#insert').css('display','none')}}]" >
			<a href="javascript:addMethod()" class="btn headerBtn" iconCls="icon-add" plain="false">脚本设置</a>
			<a href="javascript:addMethodOne()" class="btn headerBtn"  plain="false">环境路径设置</a>
			<a href="javascript:addMethodTwo()" class="btn headerBtn"  plain="false">环境文件设置</a>
			<a href="javascript:addOther()" class="btn headerBtn"  plain="false">其他设置</a>
	</div>
</div>
<div  id="test">
	<div class="easyui-panel" title="选择测试单项"  style="width:450px;height:130px;padding:10px;padding-top:30px;background:#fafafa;" 
		data-options="iconCls:'icon-save',tools:[{iconCls:'icon-cancel',handler:function(){$('#test').css('display','none')}}]" >
			<a href="javascript:setResour2()" style="width:120px" class="btn headerBtn"  plain="false">免费资源获取测试</a>
				<a href="javascript:setResour()"style="width:120px" class="btn headerBtn"  plain="false">免费资源设置测试</a>
				<a href="javascript:testNet()" class="btn headerBtn"  plain="false">网络连通测试</a>
	</div>
</div>
</body>
</html>