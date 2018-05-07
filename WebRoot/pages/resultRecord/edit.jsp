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
<title>用例执行结果详情</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/resultRecord/js/edit.js?0108_02"></script>
</head>
<body style="overflow: no">
   		<input type="hidden" name="resultResource" id=resultResource value="${resultResource}"/>
    	<input type="hidden" name="resultTotal" id="resultTotal" value="${resultTotal}"/>
    	<input type="hidden" name="resultDetail" id="resultDetail" value="${resultDetail}"/>
    	<input type="hidden" name="uCUserID" id="uCUserID" value="${uCUserID}"/>
    	<input type="hidden" name="billType" id="billType" value="${billType}"/>
    	<div class="tbResultResource">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="formTable">
	    		<tr>
	    			<td class="tHead" colspan="6" style="text-align:center;background:#D1EEEE;font-size:1.2em;font-weight:blod">
						免费资源执行结果
					</td>
	    		</tr>
	    		<tr>
	    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbResultResource">
			    		<thead>
							<tr>
								<th  style="text-align: left;" align="center" width="100">产品</th>
								<th  style="text-align: left;" align="center" width="100">用户号码</th>
								<th  style="text-align: left;" align="center" width="60">类型</th>
								<th style="text-align: left;" align="center"  width="60">初始值</th>
								<th style="text-align: left;" align="center" width="60" >预期值</th>
								<th style="text-align: left;" align="center" width="120" >实际值</th>
							</tr>
						</thead>
			    	</table>
	    		</tr>
    	</table>
      </div>
      <div id="dResultTotal" >
      	<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="formTable">
			<tr>
	    			<td class="tHead" colspan="6" style="text-align:center;background:#D1EEEE;font-size:1.2em;font-weight:blod">
						累积量执行结果
					</td>
	    	</tr>
	    	<tr>
	    		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbResultTotal">
		    		<thead>
						<tr>
							<th  style="text-align: left;" align="center" width="100">累积量</th>
							<th style="text-align: left;" align="center"  width="60">初始值</th>
							<th style="text-align: left;" align="center" width="60" >预期值</th>
							<th style="text-align: left;" align="center" width="120" >实际值</th>
						</tr>
					</thead>
	    		</table>
	    	</tr>	
		</table>
      </div>
      <div>
      	<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="formTable">
			<tr>
	    			<td class="tHead" colspan="6" style="text-align:center;background:#D1EEEE;font-size:1.2em;font-weight:blod">
						详单执行结果
					</td>
	    	</tr>
	    	<tr>
	    		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbResultDetail">
		    		<thead>
						<tr>
							<th  style="text-align: left;" align="center" width="100">表</th>
							<th  style="text-align: left;" align="center" width="100">对比字段</th>
							<th  style="text-align: left;" align="center" width="100">预设值</th>
							<th  style="text-align: left;" align="center" width="100">实际值</th>
						</tr>
					</thead>
		    	</table>
	    	</tr>	
		</table>
      </div>
</body>