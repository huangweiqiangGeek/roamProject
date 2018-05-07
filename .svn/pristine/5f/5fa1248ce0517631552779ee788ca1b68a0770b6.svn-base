<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织管理</title>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/JQuery zTree v3.5.12/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="framework/JQuery zTree v3.5.12/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="framework/JQuery zTree v3.5.12/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/sysmanage/sysOrgManage.js"></script>

<link rel="stylesheet" href="framework/JQuery zTree v3.5.12/css/demo.css" type="text/css">
<link rel="stylesheet" href="framework/JQuery zTree v3.5.12/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<style type="text/css">.ztree li span.button.add {margin-left: 2px;margin-right: -1px;background-position: -144px 0;vertical-align: top;*vertical-align: middle}</style>
</head>
<body>
		<table width="98%">
			<tr>
				<td width="28%"><ul id="treeDemo" class="ztree"></ul></td>
				<td width="68%" style="padding-left:5px">
					<form id="menuForm" name="menuForm">
						<table valign="top" width="100%" border="0" cellspacing="0"	cellpadding="0" class="formTable" style="white-space: nowrap">
							<tr valign="top">
								<th>名称：</th>
								<td>
									<input type="hidden" id="parent_id"></input> 
									<input type="hidden" id="t_id"></input> 
									<input type="hidden" id="id"></input>
									<input type="hidden" id="order_no"></input> 
									<input type="text" class="required" size="15" id="name" onfocus="if(this.value=='请选择菜单')this.value=''" 
									onblur="if(this.value=='')this.value='请选择菜单'" value="请选择菜单"></input><font color="red">*</font>
								</td>
								<th>编码：</th>
								<td><input type="text" class="required" size="15" id="code"></input></td>
							</tr>
							<tr valign="top">
								<th>类型：</th>
								<td><select id="org_type"><option value="0">政府部门</option><option value="1">企业单位</option></select></td>
								<th>职责：</th>
								<td><input type="text" class="required" size="15" id="business_desc"></td>
							</tr>
							<tr>
								<th>地址：</th>
								<td colspan="3"><input type="text" class="required" size="55" id="address"></td>
							</tr>
							<tr>
								<th>邮编：</th>
								<td><input type="text" class="required" size="15" id="zipcode"></td>
								<th>电话：</th>
								<td><input type="text" class="required" size="15" id="telephone"></td>
							</tr>
							<tr>
								<th>区划：</th>
								<td><input type="text" class="required" size="15" id="zone_code"></td>
								<th>邮件：</th>
								<td><input type="text" class="required" size="15" id="email"></td>
							</tr>
							<tr>
								<th>负责人：</th>
								<td><input type="text" class="required" size="15" id="leader_name"></td>
								<th>手机：</th>
								<td><input type="text" class="required" size="15" id="leader_mobile"></td>
							</tr>
							<tr>
								<th>联系人：</th>
								<td><input type="text" class="required" size="15" id="contacter_name"></td>
								<th>手机：</th>
								<td><input type="text" class="required" size="15" id="contacter_mobile"></td>
							</tr>
							<tr>
								<th>备注：</th>
								<td colspan="3"><input type="text" size="55" id="remark"></input></td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<a href="javascript:saveMethod();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
</body>
</html>