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
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/commConfig/js/list.js"></script>
<script type="text/javascript">


</script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
			<input type="hidden" id="provinceID" name="provinceID" value="${provinceID }">
				服务器地址: <input class="text" style="width:160px" id="ipAddress" required="true" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				用户名: <input class="text" style="width:160px" id="hostName" required="true" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}">
				<a href="javascript:searchMethod()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				<a href="javascript:addMethod()" class="easyui-linkbutton" iconCls="icon-add" plain="false">新增</a>
			</div>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'commConfig/listPage',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field="ipAddress" width="280">服务器地址</th>
					<th field="hostName" width="280">用户名</th>
					<c:if test="${provinceID==1}">
					<th field="provinceName" width="80">省份</th>
					</c:if>
					<th field="field1" width="280">脚本目录</th>
					<th align="center" data-options="field:'id',width:'70',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>






<!-- 运行环境脚本配置div -->
<div>
<table>
<!-- 注释:名称为以下的Input需要隐藏:provinceID, provinceName,field3,field4,field5-->
<tr>
<td>
初始化免费资源,设置免费资源时调用的脚本:<br>
<form action="#"  ip="shell_setResourceOfUserInput">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
脚本路径:<input name="field1"  />
脚本名称:<input name="field2"  />
<input name="field3" value="初始化免费资源,设置免费资源时调用的脚本" type="hidden" />
<input name="field4" value="shell_setResourceOfUserInput" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(shell_setResourceOfUserInput)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
初始化免费资源,获取免费资源时调用的脚本:<br>
<form action="#" ip="shell_queryResourceByShell">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
脚本路径:<input name="field1"  />
脚本名称:<input name="field2" value="" />
<input name="field3" value="初始化免费资源,获取免费资源时调用的脚本" type="hidden"/>
<input name="field4" value="shell_queryResourceByShell" type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(shell_queryResourceByShell)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
构造话单,生成标准话单时调用的脚本:<br>
<form action="#" ip="shell_toStandardBill">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord" />
脚本路径:<input name="field1"  />
脚本名称:<input name="field2"  />
<input name="field3" value="shell_toStandardBill" type="hidden"/>
<input name="field4" value="构造话单,生成标准话单时调用的脚本" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(shell_toStandardBill)" value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
执行用例,搬移标准话单时调用的脚本:<br>
<form action="#" ip="shell_moveBill">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
脚本路径:<input name="field1"  />
脚本名称:<input name="field2"  />
<input name="field3" value="shell_moveBill" type="hidden" />
<input name="field4" value="执行用例,搬移标准话单时调用的脚本" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(shell_moveBill)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
执行后,获取免费资源执行结果时调用的脚本:<br>
<form action="#" ip="shell_getResourceResult">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
脚本路径:<input name="field1"  />
脚本名称<input name="field2"  />
<input name="field3" value="shell_getResourceResult" type="hidden" />
<input name="field4" value="执行后,获取免费资源执行结构时调用的脚本" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(shell_getResourceResult)"  value="提交"/>
</form>
</td>
</tr>
</table>
</div>



<!-- 运行环境路径配置div -->
<div>
<table>
<tr>
<td>
执行用例,上传txt话单的路径:<br>
<form action="#" ip="path_uploadTxtBillForShell">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
路径:<input name="field1"  />
<input name="field3" value="path_uploadTxtBillForShell" type="hidden" />
<input name="field4" value="执行用例,上传txt话单的路径" 	type="hidden"/>
<input name="field5" value="2" 	type="hidden"/>
<Input type="button" onclick="javascript:test(path_uploadTxtBillForShell)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
执行用例后,获取详单结果调用的脚本所在的路径:<br>
<form action="#" ip="path_ShellOfDetialResult">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
路径:<input name="field1"  />
<input name="field3" value="path_ShellOfDetialResult" type="hidden" />
<input name="field4" value="执行用例,上传txt话单的路径" 	type="hidden"/>
<input name="field5" value="2" 	type="hidden"/>
<Input type="button" onclick="javascript:test(path_ShellOfDetialResult)"  value="提交"/>
</form>
</td>
</tr>
</table>
</div>


<!-- 运行环境文件配置div -->
<div>
<table>
<tr>
<td>
生成标准话单后,存放标准话单文件名的文件:<br>
<form action="#" ip="txt_cdr_filename">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
文件路径:<input name="field1"  />
文件名称<input name="field2"  />
<input name="field3" value="txt_cdr_filename" type="hidden" />
<input name="field4" value="生成标准话单后,存放标准话单文件名的文件" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(txt_cdr_filename)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
执行后,获取免费资源执行结果的文件:<br>
<form action="#" ip="txt_getResourceResult">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
文件路径:<input name="field1"  />
文件名称<input name="field2"  />
<input name="field3" value="txt_getResourceResult" type="hidden" />
<input name="field4" value="执行后,获取免费资源执行结果的文件" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(txt_getResourceResult)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
执行后,获取语音详单计算结果的文件:<br>
<form action="#" ip="txt_getDetialResultOfGsmr">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
文件路径:<input name="field1"  />
文件名称<input name="field2"  />
<input name="field3" value="txt_getDetialResultOfGsmr" type="hidden" />
<input name="field4" value="执行后,获取语音详单计算结果的文件" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(txt_getDetialResultOfGsmr)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
执行后,获取gprs详单计算结果的文件:<br>
<form action="#" ip="txt_getDetialResultOfGprs">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
文件路径:<input name="field1"  />
文件名称<input name="field2"  />
<input name="field3" value="txt_getDetialResultOfGprs" type="hidden" />
<input name="field4" value="执行后,获取gprs详单计算结果的文件" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(txt_getDetialResultOfGprs)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
执行后,获取短彩信详单计算结果的文件:<br>
<form action="#" ip="txt_getDetialResultOfMsg">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
文件路径:<input name="field1"  />
文件名称<input name="field2"  />
<input name="field3" value="txt_getDetialResultOfMsg" type="hidden" />
<input name="field4" value="执行后,获取短彩信详单计算结果的文件" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(txt_getDetialResultOfMsg)"  value="提交"/>
</form>
</td>
</tr>

<tr>
<td>
执行后,获取Wlan详单计算结果的文件:<br>
<form action="#" ip="txt_getDetialResultOfWlan">
<input name="provinceID"  type="hidden"/>
<input name="provinceName"	type="hidden"/>
主机IP:<input name="ipAddress"  />
用户名:<input name="hostName"  />
用户密码:<input name="hostPassWord"  />
文件路径:<input name="field1"  />
文件名称<input name="field2"  />
<input name="field3" value="txt_getDetialResultOfWlan" type="hidden" />
<input name="field4" value="执行后,获取Wlan详单计算结果的文件" 	type="hidden"/>
<input name="field5" value="0" 	type="hidden"/>
<Input type="button" onclick="javascript:test(txt_getDetialResultOfWlan)"  value="提交"/>
</form>
</td>
</tr>
</table>
</div>















</body>
</html>