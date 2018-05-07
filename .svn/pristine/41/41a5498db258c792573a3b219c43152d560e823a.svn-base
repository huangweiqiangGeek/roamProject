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
<title>网络连接测试</title>
<script type="text/javascript">
if("close"=="${close}"){
	window.parent.closeWin(true,"win_div_1");
}
function startTest(){
	var ip=trans($('#IPaddress').val());
	if(isNull(ip)){
		$.messager.alert('温馨提示','请输入测试的IP地址');
		return false;
	}
	$('.state').html('<p>IP地址连接测试中... ...</p>')
	$.ajax({
		type:"POST",
		url:"commConfig/testNet2",
		data:"ip="+ip,
		success:function(data){
			var msg=data.ipSets;
			console.log(data)
			$('.state').html(msg)
		}	
	});
}

</script>
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
</head>
<body style="overflow:no">
    	<table width="350px" border="0" cellspacing="0" cellpadding="0"
			class="formTable" id="formTable">
    		<tr>
	    		<td>
	    			IP地址：<input placeholder="输入您要测试的IP地址" type="text" id="IPaddress" class="IPaddress" name="IPaddress">&nbsp;&nbsp;&nbsp;
    				<a href="javascript:startTest();" class="savebtn commBtn">测试</a>			
	    		</td>
	    	</tr>
			<tr>
				<td class="state" style="height:120px">
					
				</td>
			</tr>
    	</table>	
</body>