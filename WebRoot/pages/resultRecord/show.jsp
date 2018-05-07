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
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/resultRecord/js/show.js"></script>
<style>
	#chart{
		position:absolute;
		top:50px;
		left:300px;
		cursor:pointer;
	}
	#chartShow{width:100%;margin:0;border-collapse:collapse;box-shadow:0 0 1em rgba(0, 0, 0, 0.5);-moz-box-shadow:0 0 1em rgba(0, 0, 0, 0.5);-webkit-box-shadow:0 0 1em rgba(0, 0, 0, 0.5);background-position:0 -100px;}
    #chartShow th, #chartShow td{padding:0.5em;border:1px dotted #666;text-algin:center;}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:5px;height:auto">
			<div>
				<input type="hidden" id="resultType" name="resultType" value="${resultRecord.resultType}">
				<input type="hidden" id="proID" name="proID" value="${resultRecord.proID}">
				<input type="hidden" id="uCID" name="uCID" value="${resultRecord.uCID}">
			</div>
		</div>
		
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
	</td>
</tr>
</table>
<%--  <canvas id="chart" width="500" height="400">
 您的浏览器版本过低，请及时升级，谢谢！
 </canvas> --%>
        <!-- <table id="chartData" style="display:none">
            <tr style="color:#0DA068">
                <td>通过率</td><td class="succ"></td>
            </tr>
            <tr style="color:#ED5713">
                <td>失败率</td><td class="fair"></td>
            </tr>
            <tr style="color:#ff9224">
                <td>未完成执行</td><td class="cannot"></td>
            </tr>
        </table> -->
        <table id="chartShow">
        	<thead>
        		<th>项目执行情况</th>
        		<th>用例数量</th>
        		<th>比例</th>
        	</thead>
            <tr style="color:#0DA068" align="center" class="succClick">
                <td>用例通过</td><td class="succ"></td><td class="succ1"></td>
            </tr>
            <tr style="color:#ED5713" align="center" class="fairClick">
                <td>用例失败</td><td class="fair"></td><td class="fair1"></td>
            </tr>
            <tr style="color:#ff9224" align="center" class="cannotClick">
                <td>未完成执行</td><td class="cannot"></td><td class="cannot1"></td>
            </tr>
        </table>
</body>
</html>