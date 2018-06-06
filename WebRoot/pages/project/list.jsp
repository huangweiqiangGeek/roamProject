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
<title>任务管理</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/project/js/list.js?01"></script>
<style type="text/css">
	#insert{
		position:absolute;
		top:100px;
		left:160px;
		display:none;
	}
	.leadInBtn{
		width: 65px;
	    line-height: 22px;
	}
</style>
<script>
function filedReset(){
	$('#file1').val('');
	isnext=false;
	}
</script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:20px 5px;height:auto">
			<div>
				<input type="hidden" id="proType" name="proType" value="${proType }">
				<input type="hidden" id="proProvince" name="proProvince" value="${proProvince}">
				<input type="hidden" id="homeProjectId" name="homeProjectId" value="${homeProjectId}">
				<input type='hidden' value='${CSRFToken}' id='csrftoken'>
				<div style="margin:0 20px 0 0;padding:0;text-align:center;width:350px;display:inline-block;">
					<a href="javascript:addMethod(${homeProjectId});" class="btn addBtn">新增</a><a 
					href="javascript:leadIn();" class="btn leadInBtn" iconCls="icon-add" plain="false">导入</a><a 
					href="javascript:executeCaseAll();" class="btn leadInBtn" iconCls="icon-add" plain="false">批量执行</a><a 
					href="javascript:exportAll();" class="btn leadInBtn" iconCls="icon-add" plain="false">批量导出</a><a 
					href="javascript:dispatchAll();" class="btn inBtn" iconCls="icon-add" plain="false">批量调度 </a>
				</div>
				<div class='searchBox'>
					<input placeholder="按任务名称搜索" class="searchInput" id="proName" maxlength="20" 
					onkeydown="if(event.keyCode==13){searchMethod()}"><a href="javascript:searchMethod()" class="btn searchbutton">搜索</a>
				</div>
				<div class='searchBox'>
					<input placeholder="按任务编号搜索" class="searchInput" id="proNumber" maxlength="20" 
					onkeydown="if(event.keyCode==13){searchMethod()}"><a href="javascript:searchMethod()" 
					class="btn searchbutton">搜索</a>
				</div>
			</div>	
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'project/listPage?proProvince=${proProvince}&homeProjectId=${homeProjectId }',toolbar:'#tb',rownumbers:true,singleSelect:false,idField:'id',autoRowHeight:false,pagination:true,pageSize:50">
			<thead>
				<tr>
					<th data-options="field:'pId',width:'40',checkbox:true">全选</th>
					<th field="proName" width="190">任务名称</th>
					<th field="proNumber" width="100">任务编号</th>
					<c:if test="${provinceID==1}">
					<th field="proProvince" width="80">省份</th>
					</c:if>
					<th field="proExplain" width="180">任务说明</th>
					<th align="center" field="useCaseCount" width="60">用例数量</th>
					<!-- <th align="center" field="hadExe" width="60">执行状态</th> -->
					<th align="center" field="passRate" width="60">通过率</th>
					<th align="left" data-options="field:'id',width:'410',formatter:formatOperate">操作</th>
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
<div id="vessel" title="进度条" style="display:none;width:100%;height:1200px;background:#fff;position:absolute;top:0;left:0">
<div id="progress" title="进度条val" class="easyui-progressbar" style="width:600px;margin-top:180px;position:absolute;left:280px"></div>
</div>

<div  id="insert">
	<div class="easyui-panel" title="导入任务用例"  style="width:560px;height:150px;padding:10px;background:#fafafa;" 
		data-options="iconCls:'icon-save',tools:[{iconCls:'icon-cancel',handler:function(){$('#insert').css('display','none')}}]" >
	<form method="POST" enctype="multipart/form-data" action="" id="uploadUsecase">
		<table width="530px" border="0" cellspacing="0" cellpadding="0" class="formTable" id="formTable">
    		<tr>
    			<th style="width:180px">选择待导入Excel文件:</th>
	    		<td>
	    			 <input id="file1" type="file" name="file" onchange="fileChange(this);">
	    		</td>
	    		<td>
	    			 <a class="handleBtn cancelbtn" type="button" href="javascript:filedReset()" 
	    			 style="padding:3px 5px;" >重置</a>
	    		<td>
	    			 <a class="handleBtn savebtn" type="button" onclick="javascript:filedsend()"
	    			 style="padding:3px 5px;">导入</a>
	    		</td>
	    	</tr>
		</table>
	</form>
	<p class="prompt" style="color:red">&nbsp;&nbsp;仅支持后缀为.xls的Excel表格导入数据！</p>
	<p class="prompt1" style="color:red">&nbsp;&nbsp;模板中不能含有"[ ]"、"/"、"\"、"|"、' "" '等特殊字符</p>
</div>
</div>
</body>
</html>