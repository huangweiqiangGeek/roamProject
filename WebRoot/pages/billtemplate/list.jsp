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
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/billtemplate/js/list.js"></script>
<style type="text/css">
	#insert{
		position:absolute;
		top:100px;
		left:160px;
		display:none;
	}
	#insert2{
		position:absolute;
		top:100px;
		left:160px;
		display:none;
	}
	td>input{
	 	border:1px solid #C1DAD7;
	 	border-radius:5px;
	 }
</style>
<script>
function filedReset(){
	$('#file1').val('');
	isnext=false;
	}
function filedReset2(){
	$('#file2').val('');
	isnext2=false;
	}
</script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:20px;height:auto">
			<div>
				<input type="hidden" id="provinceName" name="provinceName" value="${provinceName}">
				<div class='searchBox' style="width:420px;">
					 <input class="searchInput" style="width:160px;border-radius: 5px 0 0 5px;" id="templateName" maxlength="20" 
					 placeholder="按模板名称搜索"onkeydown="if(event.keyCode==13){searchMethod()}"><select id="templateStatus" 
					 onchange="searchMethod()" class="sele"><option value="-1">-请选择模板状态-</option>
					<c:forEach items="${commTypes}" var="ct">
					<option value="${ct.id }">${ct.commTypeName }</option>
					</c:forEach>
					</select><a href="javascript:searchMethod()"
					  class="btn searchbutton" style="left:349px;">查询</a>
				</div>
				<a href="billTemplate/addInit"  class="btn headerBtn">新增</a><a href="javascript:leadIn()"  class="btn headerBtn">导入</a><a href="javascript:leadIn2()"  class="btn headerBtn">带场景导入</a><a href="javascript:addBusinessType()"  class="btn headerBtn">新增业务类型</a>
				<a onclick="removeAll()" title="批量删除" class="btn headerBtn">
					批量删除
				</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'billTemplate/listPage?provinceName=${provinceName }',toolbar:'#tb',rownumbers:true,singleSelect:false,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th data-options="field:'pid',width:'40',checkbox:true">全选</th>
					<th field="templateTypeName" width="80">类型</th>
					<th field="templateName" width="150">名称</th>
					<c:if test="${provinceID==1}">
					<th field="provinceName" width="80">省份</th>
					</c:if>
					<th field="commTypeName" width="60">状态</th>
					<th data-options="field:'templateRemark',width:'180',formatter:formatAttrJson">备注</th>
					<th data-options="field:'createTime',width:'160',formatter:formatDt">创建时间</th>
					<th data-options="field:'editTime',width:'160',formatter:formatDt">编辑时间</th>
					<th align="center" data-options="field:'id',width:'180',formatter:formatOperate">操作</th>
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
	<div class="easyui-panel" title="导入话单模板"  style="width:560px;height:150px;padding:10px;background:#fafafa;" 
		data-options="iconCls:'icon-save',tools:[{iconCls:'icon-cancel',handler:function(){$('#insert').css('display','none')}}]" >
	<form method="POST" enctype="multipart/form-data" action="billTemplate/uploadBillTemplate" id="uploadBillTemplate">
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
	    			<a class="handleBtn savebtn" type="button" href="javascript:filedsend()" 
	    			 style="padding:3px 5px;" >导入</a>
	    		</td>
	    	</tr>
		</table>
	</form>
	<p class="prompt" style="color:red">&nbsp;&nbsp;仅支持后缀为.xls的Excel表格导入数据！</p>
</div>
</div>

<div  id="insert2">
	<div class="easyui-panel" title="导入话单模板"  style="width:560px;height:150px;padding:10px;background:#fafafa;" 
		data-options="iconCls:'icon-save',tools:[{iconCls:'icon-cancel',handler:function(){$('#insert2').css('display','none')}}]" >
	<form method="POST" enctype="multipart/form-data" action="billTemplate/uploadBillTemplate2" id="uploadBillTemplate2">
		<table width="530px" border="0" cellspacing="0" cellpadding="0" class="formTable" id="formTable2">
    		<tr>
    			<th style="width:180px">选择待导入Excel文件:</th>
	    		<td>
	    			 <input id="file2" type="file" name="file" onchange="fileChange2(this);">
	    		</td>
	    		<td>
	    			  <a class="handleBtn cancelbtn" type="button" href="javascript:filedReset2()" 
	    			 style="padding:3px 5px;" >重置</a>
	    		<td>
	    		 <a class="handleBtn savebtn" type="button" href="javascript:filedsend2()" 
	    			 style="padding:3px 5px;" >导入</a>
	    		</td>
	    	</tr>
		</table>
	</form>
	<p class="prompt2" style="color:red">&nbsp;&nbsp;仅支持后缀为.xls的Excel表格导入数据！</p>
</div>
</div>

<div id="win" class="easyui-window" title="新增业务类型" style="width:300px;height:200px"
        data-options="iconCls:'icon-save',modal:true">
     <form id="ff" method="post" >
     <div style="height:15px"></div>
    	<div>
       	 	<label for="id" style="margin-left:20px;">&nbsp;业务类型ID&nbsp;:</label>
        	<input style="margin-left:5px;" class="easyui-validatebox" id="id" type="text" name="type_id" data-options="required:true" />
    	</div>
    	<div style="height:25px"></div>
    	<div>
        	<label for="name" style="margin-left:20px;">业务类型名称:</label>
        	<input class="easyui-validatebox" type="text" id="name" name="type_name" data-options="required:true" />
    	</div>
    	<div style="height:30px"></div>
    	<tr>
    	<td style="text-align:right">
					<a  style="margin-left:50px; margin-right:10px;" class="easyui-linkbutton" iconCls="icon-save" onclick="javascript:saveBusinessType()">Save</a>
					<a  class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript: $('#win').window('close')">Close</a>
				</td>
				</tr>
	</form>
</div>

</body>
</html>