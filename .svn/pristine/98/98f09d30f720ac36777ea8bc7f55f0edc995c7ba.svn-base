<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加产品</title>
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
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/productGroup/js/edit.js?1226_08"></script>
<style>
	 .formTable th{
	 	text-align:center;
	 }
	 td>input{
	 	border:1px solid #C1DAD7;
	 	border-radius:5px;
	 }
	 .pro{
	 	width:120px;
	 }
	 table{
	 	box-sizing:border-box;
	 	margin:0;
	 }
	 .cpz_name{
	    margin-left: 20px;
	    height: 26px;
	    padding-left: 5px;
	    border: 1px solid #C1DAD7;
	    border-radius: 5px;
	    width: 220px;
    }
    .formTable{ border-left: none;}
</style>
<script>

</script>
</head>
<body style="overflow: auto;padding:6px 20px 6px;">
 <%  
        //获得number  
        String telephone=request.getParameter("telephone"); 
 		String zd_bt=request.getParameter("business_type"); 
     %>  
     <input  type="hidden" id="zd_telephone" name="zd_telephone" value='<%=telephone %>' />
     <input  type="hidden" id="zd_bt" name="zd_bt" value='<%=zd_bt %>' />
	<div style="height:430px;">
	<input type="hidden" id="productGroupDetails" name="productGroupDetails" value="">
	<input type="hidden" id="productGroupDetails" name="productGroupDetails" value="${productGroupDetails}">
	<input type="hidden" id="list" name="list" value="${list}">
	<input type="hidden" id="group_id" name="group_id" value="${productGroup.id}">
	<input type="hidden" id="name" name="name" value="${productGroup.name}">
	<input type="hidden" id="productsMark" name="productsMark" value="${productGroup.productsMark}">
	<input type="hidden" id="phone_number_str" name="phone_number_str" value="${productGroup.phone_number_str}">
	<input type="hidden" id="business_type_str" name="business_type_str" value="${productGroup.business_type}">
		<!--  产品组产品json -->
		<input type="hidden" id="groupProductJson" name="groupProductJson" value="${productGroup.groupProductJson}">
		<!--  产品组电话号码json -->
		<input type="hidden" id="groupNoJson" name="groupNoJson" value="${productGroup.groupNoJson}">
		<!--  语音产品json -->
		<input type="hidden" id="voiceProductJson" name="voiceProductJson" value="${voiceProductJson}">
		<!--  流量产品json -->
		<input type="hidden" id="gprsProductJson" name="gprsProductJson" value="${gprsProductJson}">
		<div style="width:720px;border:1px solid #C1DAD7;margin:0 auto;">
			<p style="margin:0;padding-left:5px;border-bottom:1px solid #C1DAD7;font-weight:blod; padding-top: 5px;padding-bottom: 5px;">
				<span class='flag_edit' style="display:none;">添加产品及号码</span>
				<input type="text" class="cpz_name productsNM"  placeholder="请输入产品组名称" />
				<span style="width: 260px;float:right;">
					选择业务类型:
					<select name="business_type" id="business_type" class="easyui-combobox"
					data-options="editable:true,multiple:true,listHeight:'60px'"
					style="width: 160px;">
					</select>
				</span>
			</p>
			<p style="line-height:25px;margin:0;text-algin:center">
				<span style="display:inline-block;padding-left:169px;width:250px;border-right:2px solid #C1DAD7">产品
				</span><span style="display:inline-block;padding-left:130px;">
				号码</span>
			</p>
			<table id="groupProducts" border="0" cellspacing="0" cellpadding="0" width="420px" class="formTable" height="135px"
					style="display:inline-block;margin:0;text-align:center;overflow:auto;" align="left">
				<thead>
						<tr>
						<th width="60px">编号</th>
						<th width="150px">产品名称</th>
						<th width="150px">产品ID</th>
						<th width="60px">操作</th>	
					</tr>
				</thead>
				<tr>
					<td>1</td>
					<td><input id="productName1" class="pro"></td>
					<td><input id="productId1" class="pro"></td>
					<td><a href="javascript:gpMethod()" class="btn headerBtn" style="width:40px">添加</a></td>
				</tr>
			</table>
			<table id="groupNos" border="0" cellspacing="0" cellpadding="0"  class="formTable" height="135px"
					style="width:300px;display:inline-block;margin:0;text-align:center;overflow:auto;">
				<thead>
					<tr>
						<th width="80px">编号</th>
						<th width="140px">号码</th>
						<th width="80px">操作</th>	
					</tr>
				</thead>
				<tr>
					<td>1</td>
					<td><input id='phoneNumber1' class="pro"></td>
					<td><a href="javascript:gnMethod()" class="btn headerBtn" style="width:40px">添加</a></td>
				</tr>
			</table>
		</div>

		<div id="minute" style="width:720px;border:1px solid #C1DAD7;margin:10px auto 0;">
			<p style="line-height:25px;margin:0;padding-left:5px;border-bottom:1px solid #C1DAD7;font-weight:blod;margin-top: 5px;">
				添加业务类型
			</p>
			<div id="businessTypeDIV" style="overflow:auto;height:150px;">
		</div>
		</div>
		<div align="center" style="width:720px;margin-top:30px;padding:3px 0;border-top:none;margin:0 auto;">
				<a id="aSave"  onclick="saveMethodBefore()" class="savebtn headerBtn" >保存</a> 
		</div>
		
	
	<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
</body>