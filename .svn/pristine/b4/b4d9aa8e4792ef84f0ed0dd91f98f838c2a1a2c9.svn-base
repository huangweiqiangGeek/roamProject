<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加修改人员</title>
<script type="text/javascript">
if("close"=="${close}"){
	window.parent.closeWin(true,"winDiv_1");
}else if("re"=="${re}"){
	alert("该人名已存在");
}
</script>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="pages/sysmanage/addOrUpdateSysUser.js"></script>
</head>
<body style="overflow: no">
    <form id="addOrUpateForm" action="sysUser/saveSysUser" method="post">
    	<input type="hidden" name="id" value="${sysUser.id }"/>
    	<input type="hidden" name="org_id" value="${org_id }"/>
    	<input type="hidden" id ="oldPass" name="oldPass" value="${sysUser.user_pass }"/>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
    		<tr>
    			<th><font color="red">*</font>账号：</th>
    			<td>
    				<input type="type" id="user_code" name="user_code" value="${sysUser.user_code }" style="width:90%" maxlength="50"/>
    			</td>
    			<th><font color="red">*</font>密码：</th>
    			<td><input type="password" id="user_pass" name="user_pass" style="width:75%" value="${sysUser.user_pass}" maxlength="12" disabled>
    			<a href="javascript:editMethod()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>姓名：</th>
    			<td><input type="text" id="cn_name" name="cn_name" style="width:90%" value="${sysUser.cn_name }" maxlength="15"></td>
    			<th><font color="red">*</font>手机：</th>
    			<td><input type="text" id="mobile" name="mobile" style="width:91%" value="${sysUser.mobile }" maxlength="15"></td>
    		</tr>
    		<tr>
    			<th>英文名：</th>
    			<td><input type="text" id="en_name" name="en_name" style="width:90%" value="${sysUser.en_name }" maxlength="50"></td>
    			<th>电话：</th>
    			<td><input type="text" id="telephone" name="telephone" style="width:91%" value="${sysUser.telephone }" maxlength="15"></td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>出生日期：</th>
    			<td><input type="text" id="birth_date" name="birth_date" class="easyui-datebox" style="width:194%" value='<fmt:formatDate value="${sysUser.birth_date }" pattern="yyyy-MM-dd"/>' maxlength="50"></td>
    			<th>邮编：</th>
    			<td><input type="text" id="zipcode" name="zipcode" style="width:91%" value="${sysUser.zipcode }" maxlength="6"></td>
    		</tr>
    		<tr>
    			<th>详细地址：</th>
    			<td colspan="3"><input type="text" id="address" name="address" style="width:97%" value="${sysUser.address }" maxlength="50"></td>
    		</tr>
    		<tr>
    			<th>国籍：</th>
    			<td><input type="text" id="nationality" name="nationality" style="width:90%" value="${sysUser.nationality }" maxlength="20"></td>
    			<th>籍贯：</th>
    			<td><input type="text" id="birth_place" name="birth_place" style="width:91%" value="${sysUser.birth_place }" maxlength="50"></td>
    		</tr>
    		<tr>
    			<th>身份证：</th>
    			<td><input type="text" id="idcard_no" name="idcard_no" style="width:90%" value="${sysUser.idcard_no }" maxlength="20"></td>
    			<th>政治面貌：</th>
    			<td><input type="text" id="politicallandscape" name="politicallandscape" style="width:91%" value="${sysUser.politicallandscape }" maxlength="15"></td>
    		</tr>
    		<tr>
    			<th>民族：</th>
    			<td><input type="text" id="nation" name="nation" style="width:90%" value="${sysUser.nation }" maxlength="20"></td>
    			<th>EMAIL：</th>
    			<td><input type="text" id="email" name="email" style="width:91%" value="${sysUser.email }" maxlength="50"></td>
    		</tr>
    		<tr>
    			<th>备注：</th>
    			<td colspan="3"><input type="textArea" id="remark" name="remark" style="width:97%;height:40px" value="${sysUser.remark }" maxlength="50"></td>
    		</tr>
    	</table>
    </form>
   	<div align="center" style="padding:5px;">
		<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" onclick="cancelMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
</body>