<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="../framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="../framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="../framework/js/common.js"></script>
<script type="text/javascript">
$(function(){
	
})
function showOneProvience(n){
	var provenceName = n.value;
	var params ={provenceName:provenceName};
	$("#statistics").datagrid({
		url:'showOneProvience',
		queryParams:params
	});
}
</script>
<title>用例信息统计</title>
</head>
<body  marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
			<input type="hidden" id="provinceSpell" name="provinceSpell" value="${provinceSpell }">
				<span class="btn inBtn" style="width:100px;border-radius:5px 0 0 5px;">查询特定省份：</span><select 
				id="provienceList" name="dept" onchange="showOneProvience(this)" class="sele" style="border-radius:0 5px 5px 0;" >   
				    <option value="admin">全部省份</option> 
				    <option value="anhui">安徽</option>
				    <option value="beijing">北京</option>
				    <option value="chongqing">重庆</option>
				    <option value="fujian">福建</option>
				    <option value="gansu">甘肃</option>
				    <option value="guangdong">广东</option>
				    <option value="guangxi">广西</option>
				    <option value="guizhou">贵州</option>
				    <option value="hainan">海南</option>
				    <option value="hebei">河北</option>
				    <option value="heilongjiang">黑龙江</option>
				    <option value="henan">河南</option>
				    <option value="hubei">湖北</option>
				    <option value="hunan">湖南</option>
				    <option value="jiangsu">江苏</option>
				    <option value="jiangxi">江西</option>
				    <option value="jilin">吉林</option>
				    <option value="liaoning">辽宁</option>
				    <option value="neimeng">内蒙古</option>
				    <option value="ningxia">宁夏</option>
				    <option value="qinghai">青海</option>
				    <option value="shandong">山东</option>
				    <option value="shanghai">上海</option>
				    <option value="shanxi">山西</option>
				    <option value="shaanxi">陕西</option>
				    <option value="sichuan">四川</option>
				    <option value="tianjin">天津</option>
				    <option value="xinjiang">新疆</option>
				    <option value="xizang">西藏</option>
				    <option value="yunnan">云南</option>
				    <option value="zhejiang">浙江</option>
				    <option value="fujian">福建</option>
				    <option value="taiwan">台湾</option>
				    <option value="aomen">澳门</option>
				    <option value="xianggang">香港</option>
				</select> 
				<a class="btn headerBtn" href="downloadCountOfUseCaseGroupByProvienceAndStatus" style="width:160px;margin-left:30px">下载用例执行统计文件</a>
			</div>	
		</div>
		<table id="dg" class="easyui-datagrid"  id="statistics" title="" 
		data-options="url:'getCountOfUseCaseGroupByProvienceAndStatus',toolbar:'#tb',fitColumns:true,rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10"">   
			<thead>   
		        <tr>
		            <th data-options="field:'proProvince',width:150,align:'center'">省份</th>   
		            <th data-options="field:'total_count',width:150,align:'center'">总用例数量</th>   
		            <th data-options="field:'pass_count',width:150,align:'center'">测试通过用例数量</th>   
		            <th data-options="field:'nodo_count',width:150,align:'center'">未测试用例数量</th>   
		            <th data-options="field:'diable_count',width:150,align:'center'">测试未完成用例数量</th> 
		            <th data-options="field:'failed_count',width:150,align:'center'">测试不通过用例数量</th> 
		            <th data-options="field:'percent',width:150,align:'center'">测试通过用例占比</th> 
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