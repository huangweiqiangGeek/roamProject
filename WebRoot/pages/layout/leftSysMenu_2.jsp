<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'westSysMenu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" href="framework/JQuery zTree v3.5.12/css/demo.css" type="text/css">
<link rel="stylesheet" href="framework/JQuery zTree v3.5.12/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="framework/JQuery zTree v3.5.12/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="framework/JQuery zTree v3.5.12/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="pages/layout/leftSysMenu_2.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<style type="text/css">
.ztree li a.level0 {width:200px;height: 25px;  display:block; background-image:url('images/image/nav_bg.png'); border:1px silver solid;}
.ztree li a.level0.cur {background-color: RGB(193,229,255); }
.ztree li a.level0 span {display: block; color: RGB(29,92,139); padding-top:5px;padding-left:40px; font-size:12px; font-weight: bold;word-spacing: 2px;}
.ztree li a.level0 span.button {float:right; margin-left: 10px; visibility: visible;display:none;}
.ztree li span.button.switch.level0 {display:none;}
</style>
  </head>
  
  <body>
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
  </body>
</html>
