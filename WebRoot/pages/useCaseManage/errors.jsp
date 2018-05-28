<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
response.setStatus(200);
%>
<!DOCTYPE html>
<html>
<head>
<%@ page isELIgnored="false"%>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用例</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css"
	href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript"
	src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">

<link rel="stylesheet" type="text/css" href="framework/bTabs/bootstrap.min.css">
<!-- <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css"> -->
<link rel="stylesheet" href="framework/bTabs/b.tabs.css" type="text/css">


</script>
<style>
	body{margin:0;font:14px/1.5 "Microsoft Yahei","Hiragino Sans GB",sans-serif;background-color:#fff}
a{color:#3d3d3d;text-decoration:none}
/*错误页面*/
#errorpage{width:600px;text-align:center;padding:80px 0;margin:0 auto;height:360px;}
#errorpage p{font-size:18px;font-weight:bold;padding-top:30px;}
#errorpage .bt a{background:#fe3a3b;padding:9px 46px 11px;*padding:7px 24px 9px;color:#fff;border:0;font-size:14px;}

.errortans{margin:0;text-align:left;float:left;z-index:5;height:300px;position:relative;padding:80px 0 0 24px;}
.errortans p{font-weight:normal;z-index:10;position:relative;}
.errortans p b{font-size:38px;}
.errortans div{margin-top:30px;}

#errorpage .logo{display:inline-block;float:left;height:303px; width:256px;background:url(images/tfans.jpg);}
</style>
</head>

<body>

<div id="errorpage">
	<div class="tfans_error">
		<div class="logo"></div>
		<div class="errortans clearfix">
			<div class="e404"></div>
			<p><b>出错啦！</b></p>
			<p>您访问的页面出错了</p>
			<!-- <div class="bt"><a href="#">返回</a></div> -->
		</div>
	</div>
</div>
</body>
</html>
