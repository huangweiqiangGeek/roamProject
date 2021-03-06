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
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/billtemplate/js/edit.js?04"></script>
<script type="text/javascript">
$(function(){
	if(!isNull($("#id").val())){
		updateInit();
	}
	$('.trLenght').html(num);
});
function updateInit(){
	 var json = JSON.parse('${templateAttributes}');
	 var data = json.templateAttribute;
	 idx =data.length-1;
	 num = data.length;
	 $('.trLenght').html(num);
	 $.each(data, function(idx, v) {
		   var fieldName = v.fieldName;
		   var choiceAway = v.choiceAway;
		   //var templateType = v.templateType;		   
		   var range=v.range;
		   var scriptUrl = v.scriptUrl;
		   var defaults= v.default;
		   var hide = v.hide;//
		   var explain = v.explain;//
		   if(idx!=0){
			   var c='<tr id="trTemplateAttribute'+idx+'" class="TemplateAttribute">'+$("#trTemplateAttribute0").html()+'</tr>';
			   $("#tbconent").append(c);
		   }
		   $("#trTemplateAttribute"+idx+" #addContent").attr('href','javascript:addTR('+idx+');');
		   $("#trTemplateAttribute"+idx+" #removeContent").attr('href','javascript:removeTR('+idx+');');
		   $("#trTemplateAttribute"+idx).find("input[name='fieldName']" ).val(fieldName);
		   $("#trTemplateAttribute"+idx).find("select[name='choiceAway']" ).val(choiceAway);
		   //$("#trTemplateAttribute"+idx).find("select[name='templateType']" ).val(templateType);
		   $("#trTemplateAttribute"+idx).find("input[name='range']" ).val(range);
		   $("#trTemplateAttribute"+idx).find("input[name='scriptUrl']" ).val(scriptUrl);
		   $("#trTemplateAttribute"+idx).find("input[name='default']" ).val(defaults);
		   $("#trTemplateAttribute"+idx).find("input[name='hide']" ).val(hide);  //
		   $("#trTemplateAttribute"+idx).find("input[name='explain']" ).val(explain);//
	 });	
}
</script>
<style>
	input{
		border:1px solid #CFCFCF;
		border-radius:5px;
		padding-left:5px;
	}
</style>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="billTemplate/saveBillTemplate"  method="post">
    	<input type="hidden" name="provinceID" id="provinceID" value="${billTemplate.provinceID}"/>
    	<input type="hidden" name="provinceName" id="provinceName" value="${billTemplate.provinceName }"/>
    	<input type="hidden" name="templateAttributes" id="templateAttributes" value='${templateAttributes }'/>
    	<input type="hidden" name="templateType1" id="templateType1" value='${billTemplate.templateType}'/>	
    		<input type="hidden" name="id" id="id" value="${billTemplate.id}"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" >
    		<tr>
    			<th><font color="red">*</font>名称：</th>
    			<td colspan="5">
    				<input type="type"  id="templateName"  name="templateName"  data-options="required:true,missingMessage:'请输入名称'" class="easyui-validatebox" value="${billTemplate.templateName }" style="width:260px"/>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>状态：</th>
    			<td colspan="5">
    			<select id="templateStatus" class="easyui-combobox" name="templateStatus" 
    			data-options="editable:false,panelHeight:'auto'" style="width: 100px;">
				<!-- <option value="-1">-请选择状态-</option> -->
				<c:forEach items="${commTypes}" var="ct">
				<option value="${ct.id }" <c:if test="${billTemplate.templateStatus == ct.id}">selected</c:if> >${ct.commTypeName }</option>
				</c:forEach>
				</select>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>模板类型：</th>
	    			<td colspan="5">
	    			<select id="templateType" class="easyui-combobox" name="templateType" 
	    			data-options="editable:false,panelHeight:'auto'" style="width:100px;panelHeight:80px">
					</select>
    			</td>
    		</tr>
    		<tr>
    			<th>备注：</th>
    			<td colspan="4">
    			<input type="text" id="templateRemark" name="templateRemark" style="width:260px" value="${billTemplate.templateRemark }">
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
	    		<td><font color="red">*</font>是否隐藏：</td>
	    		<td><font color="red">*</font>中文字段：</td>
	    		<td>操作</td>
    		</tr>
    		<tr id="trTemplateAttribute0" class="TemplateAttribute">
	    		<td><input type="text" id="fieldName" name="fieldName" placeholder="请输入字段" style="width:120px" value=""></td>
	    		<td>
	    		<select id="choiceAway0" name="choiceAway" style="height: 22px;">
					<option value="-1">-请选择方式-</option>
					<c:forEach items="${satusList}" var="sa">
					<option value="${sa.id }">${sa.commTypeName }</option>
					</c:forEach>
				</select>
	    		</td>
	    		<td><input type="text" id="range" name="range" placeholder="请输入值域" style="width:80px" value=""></td>
	    		<td><input type="text" id="scriptUrl" name="scriptUrl" placeholder="请输入脚本名称，多个脚本请用 | 隔开" style="width:220px" value=""></td>
	    		<td><input type="text" id="default" name="default" style="width:80px"  placeholder="请输入默认值"  value=""> </td>
	    		<td><input type="text" id="hide" name="hide" placeholder="请输入是或否" value=""  style="width:80px"> </td>
	    		<td><input type="text" id="explain" name="explain" placeholder="请输入此字段的中文说明" value=""  style="width:120px"> </td>
    			<td><a id="addContent" href="javascript:addTR(0)" class="savebtn handleBtn" >插入</a>
    				<a id="removeContent" href="javascript:removeTR(0);" id="addContent" class="cancelbtn handleBtn">删除</a>
    			</td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="padding:5px;">
   		共<span class="trLenght"></span>个字段&nbsp;&nbsp;
		<a onclick="saveMethod()" class="savebtn headerBtn">保存</a>
		<a href="billTemplate/listInit" class="cancelbtn headerBtn">取消</a>
	</div>
	<input type="hidden" id="hiddenValue" name="hiddenValue"
				value="${billTemplate.templateType }"></input>
</body>