<%@page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>文件上传</title>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/dccmanage/UploadDccFile.js"></script>
<script type="text/javascript" src="pages/dccmanage/DccCommon.js"></script>
</head>
<body>
	<form id="submitForm" name="submitForm" action="dccUpload/uploadDcc" enctype="multipart/form-data"
		method="post">
		<div align="center">
			<fieldset style="width: 740px; height: 310px;">
				<legend>DCC消息上传</legend>
				<div style="width: 730px; height: 300px;">
					<table style="height: 100%;">
						<tr>
							<td colspan="6" align="center">
								<div style="height: 100%;">
									<table style="height: 100%;">
										<tr>
											<td><span id=span1><input type="file" class="a-upload" name="file"></span>
											</td>
										</tr>
										<tr>
											<td><span id=span2><input type="file" class="a-upload" name="file"></span>
											</td>
										</tr>
										<tr>
											<td><span id=span3><input type="file" class="a-upload" name="file"></span>
											</td>
										</tr>
										<tr>
											<td><span id=span4><input type="file" class="a-upload" name="file"></span>
											</td>
										</tr>
										<tr>
											<td><span id=span5><input type="file" class="a-upload" name="file"></span>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div>
									<select name="province" id="province" class="select3"
										onchange="selected(this.value)">
									</select>
								</div>
							</td>
							<td>
								<div>
									<select name="task" id="task" class="select_Task"
										onchange="selectedTask(this.value)">
									</select>
								</div>
							</td>
							<td>
								<div>
									<select id="nf" name="nf" class="select3"
										onchange="selectedNf(this.value)">
										<option value="">请选择厂商</option>
									</select>
								</div>
							</td>
							<td>
								<div>
									<select id="nf_type" name="nf_type" class="select1"
										onchange="selectedNFType(this.value)">
										<option value="">请选择网元类型</option>
									</select>
								</div>
							</td>
							<td>
								<div>
									<select id="nf_version" name="nf_version" class="select1">
										<option value="">请选择网元版本</option>
									</select>
								</div>
							</td>
							<td>
								<div>
									<select id="is_supply" name="is_supply" class="select1">
										<option value="">是否补充测试</option>
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
						<td colspan="3" align="center"><a href="javascript:sumitMethod()" class="easyui-linkbutton" iconCls="icon-upload">上传</a>
							</td>
							<td colspan="3" align="center"><a href="javascript:resetMethod()" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
							</td>
						</tr>
					</table>
				</div>
			</fieldset>
			<input type="hidden" id="hiddenValue" name="hiddenValue"
				value="<%=request.getAttribute("info")%>"></input>
		</div>

	</form>
</body>
</html>