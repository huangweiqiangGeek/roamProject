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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加话单模版</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/billtemplate/js/edit.js"></script>
<script type="text/javascript">
$(function(){
	if(!isNull($("#id").val())){
		updateInit();
	}
});
function appTR(obj){
	if($("#trTemplateAttribute"+obj).find("input[name='fieldName']" ).val()==''){
		$.messager.alert('温馨提示','请填写字段名','',function(){
			$("#trTemplateAttribute"+obj).find("input[name='fieldName']").focus();
		});
		return;
	}
	if($("#trTemplateAttribute"+obj).find("select[name='choiceAway']" ).val()=='' || $("#trTemplateAttribute"+obj).find("select[name='choiceAway']" ).val()==-1){
		$.messager.alert('温馨提示','请选择选择方式');
		return;
	}
	obj++;
	var c='<tr id="trTemplateAttribute'+obj+'" class="TemplateAttribute">'+$("#trTemplateAttribute0").html()+'</tr>';
	$("#tbconent").append(c);
	$("#addContent").attr('href','javascript:appTR('+obj+');');
}

function updateInit(){
	 var json = JSON.parse('${templateAttributes}');
	 var data = json.templateAttribute;
	 $("#addContent").attr('href','javascript:appTR('+(data.length-1)+');');
	 $.each(data, function(idx, v) {
		   var fieldName = v.fieldName;
		   var choiceAway = v.choiceAway;
		   var range=v.range;
		   var scriptUrl = v.scriptUrl;
		   var defaults= v.default;
		   if(idx!=0){
			   var c='<tr id="trTemplateAttribute'+idx+'" class="TemplateAttribute">'+$("#trTemplateAttribute0").html()+'</tr>';
			   $("#tbconent").append(c);
		   }
		   $("#trTemplateAttribute"+idx).find("input[name='fieldName']" ).val(fieldName);
		   $("#trTemplateAttribute"+idx).find("select[name='choiceAway']" ).val(choiceAway);
		   $("#trTemplateAttribute"+idx).find("input[name='range']" ).val(range);
		   $("#trTemplateAttribute"+idx).find("input[name='scriptUrl']" ).val(scriptUrl);
		   $("#trTemplateAttribute"+idx).find("input[name='defaults']" ).val(defaults);
		});
}
function setStatus(){
	var json = JSON.parse('${satusList}');
	$.each(json, function(idx, v) {
	    alert(v.commTypeName);
	});
}
</script>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="billTemplate/saveBillTemplate"  method="post">
    	<input type="hidden" name="provinceID" id="provinceID" value="${billTemplate.provinceID}"/>
    	<input type="hidden" name="provinceName" id="provinceName" value="${billTemplate.provinceName }"/>
    	<input type="hidden" name="templateAttributes" id="templateAttributes" value="${templateAttributes }"/>
    		<input type="hidden" name="id" id="id" value="${billTemplate.id}"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" >
    		<tr>
    			<th><font color="red">*</font>名称：</th>
    			<td colspan="5">
    				<input type="type"  id="templateName"  name="templateName"  data-options="required:true,missingMessage:'请输入名称'" class="easyui-validatebox" value="${billTemplate.templateName }" style="width:260px" maxlength="20"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>状态：</th>
    			<td colspan="5">
    			<select id="templateStatus" class="easyui-combobox" name="templateStatus" style="width: 60px;">
				<!-- <option value="-1">-请选择状态-</option> -->
				<c:forEach items="${commTypes}" var="ct">
				<option value="${ct.id }" <c:if test="${billTemplate.templateStatus == ct.id}">selected</c:if> >${ct.commTypeName }</option>
				</c:forEach>
				</select>
    			</td>
    		</tr>
    		<tr>
    			<th>备注：</th>
    			<td colspan="3">
    			<input type="text" id="templateRemark" name="templateRemark" style="width:260px" value="${billTemplate.templateRemark }" maxlength="50">
    			</td>
    			<td >
    			<a href="javascript:appTR(0);" id="addContent" class="easyui-linkbutton" data-options="iconCls:'icon-save'">增加</a>
    			</td>
    		</tr>
    		</table>
    		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbconent">
    		<tr>
    		<td><font color="red">*</font>字段名：</td>
    		<td><font color="red">*</font>选择方式：</td>
    		<td>值域：</td>
    		<td>脚本名称：</td>
    		<td>默认值：</td>
    		</tr>
    		<tr id="trTemplateAttribute0" class="TemplateAttribute">
    		<td><input type="text" id="fieldName" name="fieldName" placeholder="请输入字段" style="width:160px" value="" maxlength="30"></td>
    		<td>
    		<select id="choiceAway0" name="choiceAway" style="height: 22px;">
				<option value="-1">-请选择状态-</option>
				<c:forEach items="${satusList}" var="sa">
				<option value="${sa.id }">${sa.commTypeName }</option>
				</c:forEach>
				</select>
    		</td>
    		<td><input type="text" id="range" name="range" placeholder="请输入值域" style="width:160px" value="" maxlength="30"></td>
    		<td><input type="text" id="scriptUrl" name="scriptUrl" placeholder="请输入脚本名称，多个脚本请用 | 隔开" style="width:250px" value="" maxlength="30"></td>
    		<td><input type="text" id="default" name="default" style="width:160px" value="0" maxlength="30"> </td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="padding:5px;">
   	  <!-- href="javascript:saveMethod();" -->
		<a onclick="saveMethod()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="history.go(-1);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
</body>