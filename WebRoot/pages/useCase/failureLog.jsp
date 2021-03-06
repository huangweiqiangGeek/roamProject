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
<title>查看错误日志</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/useCase/js/failureLog.js"></script>
<style type="text/css">
#showMessage{
	width:750px;
	height:380px;
	padding:0;
	border:1px solid #ddd;
	border-radius:10px;
	color:red;
	position:absolute;
	top:60px;
	left:150px;
	background:#fff;
}
.top{
	width:730px;
	height:35px;
	margin:0;
	padding:0 10px;
	border-bottom:1px solid #ddd;
}
.top a{
	display:inline-block;
	margin-top:5px;
	margin-left:660px;
}
.state,.message{
	width:720px;
	height:320px;
	overflow:auto;
	padding:15px 10px;
}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow:auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="100%" valign="top">
			<input type="hidden" id="id" name="id" value="${id }">
			<table id="dg" border="0" cellspacing="0" cellpadding="0" class="formTable" title="错误日志">
				<thead>
					<tr>
						<th field=productId width="320">执行步骤</th>
						<th field=productId width="120">是否通过</th>
						<th field=productId width="180">异常路径</th>
						<th field=productId width="320">异常描述信息</th>
					</tr>
				</thead>
				<tbody>
					<tr id="step1">
						<td>第1步,验证执行环境</td>
					</tr>
					<tr id="step2" style="display:none">
						<td>第2步,备份免费资源</td>
					</tr>
					<tr id="step3">
						<td>第3步,设置免费资源</td>
					</tr>
					<tr id="step4">
						<td>第4步,设置累积量</td>
					</tr>
					<tr id="step5">
						<td>第5步,上传本地话单</td>
					</tr>
					<tr id="step6">
						<td>第6步,调用脚本生成标准话单</td>
					</tr>
					<tr id="step7">
						<td>第7步,搬移标准话单开始批单</td>
					</tr>
					<tr id="step8">
						<td>第8步,获取详单执行结果</td>
					</tr>
					<tr id="step9">
						<td>第9步,获取免费资源执行结果</td>
					</tr>
					<tr id="step10">
						<td>第10步,获取累积量执行结果</td>
					</tr>
					<tr id="step11">
						<td>第11步,比较详单计算结果,并处理结果</td>
					</tr>
					<tr id="step12">
						<td>第12步,比较免费资源计算结果,并处理结果</td>
					</tr>
					<tr id="step13">
						<td>第13步,比较累积量计算结果,并处理结果</td>
					</tr>
					<tr id="step14">
						<td>第14步,统计执行结果</td>
					</tr>
					<tr id="step15">
						<td>第15步,免费资源恢复</td>
					</tr>
				</tbody>
			</table>
			<div id="showMessage" title="My Dialog" style="display:none" >
    			 <div class="top">
    			 	<a href="javascript:cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
    			 </div>
				<div class="state" style="display:none"></div>
				<div class="message" style="display:none"></div>
			</div>
			<div align="center" style="padding: 5px;"> 
				<a href="JavaScript:history.go(-1)" style="background:#FE3E3E" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">返回</a>
			</div>
			<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:550px">
				<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
			</div>
		</td>
	</tr>
</table>
</body>
</html>