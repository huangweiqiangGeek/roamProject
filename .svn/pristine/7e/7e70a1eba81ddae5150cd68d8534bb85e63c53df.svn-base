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
<title>添加用例</title>
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
<script type="text/javascript" src="pages/regressionUseCase/js/edit.js"></script>
<script type="text/javascript">
$(function(){
	if(!isNull($("#id").val())){
		updateInit();
	}
});



</script>
</head>
<body style="overflow: no">
	<form id="addOrUpateForm" action="regressionUseCase/save" method="post">
		<input type="hidden" name="proID" id="proID" value="${useCase.proID}" />
	    <!-- 话单内容 -->
	    <input type="hidden" name="ticketJson" id="ticketJson" value="${useCase.ticketJson }" />
	    <!-- 初始化累积量 -->
		<input type="hidden" name="accumulateJson" id="accumulateJson" value="${useCase.accumulateJson }" />
		<!-- 初始化免费资源 --> 
		<input type="hidden" name="resourceJson" id="resourceJson" value="${useCase.resourceJson }" /> 
		<!-- 免费资源预期结果 --> 
		<input type="hidden" name="expectJson" id="expectJson" value="${useCase.expectJson }" />
		<!-- 预期累积量 -->
		<input type="hidden" name="expAmountJson" id="expAmountJson" value="${useCase.expAmountJson }" />
		<!-- 详单预期结果 -->
		<input type="hidden" name="expDetailJson" id="expDetailJson" value="${useCase.expDetailJson }" /> 
		<input type="hidden" name="executeNum" id="executeNum" value="${useCase.executeNum }" /> 
		<input type="hidden" name="billName" id="billName" value="${useCase.billName }" /> 
		<input type="hidden" name="attrJson" id="attrJson" value="" /> 
		<input type="hidden" name="billId" id="billId" value="" /> 
		<input type="hidden" name="resultName" id="resultName" value="${useCase.resultName }" /> 
		<input type="hidden" name="resultAttrJson" id="resultAttrJson" value="" /> 
		<input type="hidden" name="resultId" id="resultId" value="" /> 
		<input type="hidden" name="id" id="id" value="${useCase.id}" />
		<div id="dSubject" >
		<h1>用例主体内容：</h1>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="formTable" id="subject">
			<tr>
				<th><font color="red">*</font>用例名称：</th>
				<td colspan="5"><input type="type" id="uCName" name="uCName" placeholder="请输入用例名称"
					data-options="required:true,missingMessage:'请输入用例名称'"
					class="easyui-validatebox" value="${useCase.uCName }"
					style="width: 260px" maxlength="20" /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>用例编号：</th>
				<td colspan="5"><input type="type" id="uCNumber" placeholder="请输入用例编号建议采用：项目编号+场景的形式"
					name="uCNumber"
					data-options="required:true,missingMessage:'请输入用例编号'"
					class="easyui-validatebox" value="${useCase.uCNumber }"
					style="width: 260px;" maxlength="20" /> <span>用例编号建议采用：项目编号+场景的形式</span>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>使用场景：</th>
				<td colspan="5"><input type="type" id="uCScene" name="uCScene" placeholder="请输入使用场景"
					data-options="required:true,missingMessage:'请输入使用场景'"
					class="easyui-validatebox" value="${useCase.uCScene }"
					style="width: 260px" maxlength="20" /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>话单归属用户标识：</th>
				<td colspan="5"><input type="type" id="uCUserID" name="uCUserID" placeholder="请输入话单归属用户标识"
					data-options="required:true,missingMessage:'请输入话单归属用户标识'"
					class="easyui-validatebox" value="${useCase.uCUserID }"
					style="width: 260px" maxlength="20" /> <span>用户号、手机号码</span></td>
					
			</tr>
			<tr>
				<th><font color="red">*</font>执行状态：</th>
				<td colspan="5"><select id="isPass" class="easyui-combobox"
					name="isPass" style="width: 60px;">
						<!-- <option value="-1">-请选择类型-</option> -->
						<c:forEach items="${typeList}" var="tl">
							<option value="${tl.id }"
								<c:if test="${useCase.isPass == tl.id}">selected</c:if>>${tl.commTypeName }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th><font color="red">*</font>用例状态：</th>
				<td colspan="5"><select id="uCStauts" class="easyui-combobox"
					name="uCStauts" style="width: 60px;">
						<!-- <option value="-1">-请选择状态-</option> -->
						<c:forEach items="${satusList}" var="sl">
							<option value="${sl.id }"
								<c:if test="${useCase.uCStauts == sl.id}">selected</c:if>>${sl.commTypeName }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td colspan="5"><input type="type" id="uCRemark"
					name="uCRemark" class="easyui-validatebox"
					value="${useCase.uCRemark }" style="width: 260px" maxlength="20" />
				</td>
			</tr>
		</table>
		</div>
		<div style="display: none;" id="dStepOne">
		<h2>步骤一： 构建话单 （备注说明）</h2>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="formTable" id="stepOne">
			<tr>
				<th><font color="red">*</font>选择模版：</th>
				<td colspan="6"><a href="javascript:setBillTemplate();"
					class="easyui-linkbutton" iconCls="icon-search">选择模版</a>
					<span id="spbillName" style="display: none;">已选择模版：</span></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="stepOnes">
			
		</table>
		</div>
		<div style="display: none;" id="dStepTwo">
		<h2>步骤二： 初始化资源 （备注说明）</h2>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="formTable" id="stepTwo">
				<tr>
					<th style="text-align: left;">是否初始化累积量：<input onclick="setAccumulate()" type="checkbox" id="cbAccumulate">
						初始化累积量说明
					</th>
				</tr>
				<tr>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="formTable" id="twoSubject" style="display:none; ">
						<tr>
							<th><font color="red">*</font>总累积量：</th>
							<td style="text-align: right">初始值：</td>
							<td colspan="4"><input type="number" min="0" data="总累积量"  id="totalAccumulate" name="totalAccumulate"
								class="easyui-validatebox" style="width: 260px" maxlength="20" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>金额：</th>

							<td style="text-align: right">初始值：</td>
							<td ><input type="number" min="0" data="金额"  id="totalAccumulateValue" name="totalAccumulateValue"
								class="easyui-validatebox" style="width: 260px" maxlength="20" />
							</td>
							<td colspan="3"><a id="addProAccumulate" onclick="addProAccumulate(0)"  
								class="easyui-linkbutton" data-options="iconCls:'icon-save'">增加产品累积量</a>
							</td>
						</tr>
						<tr id="proAccumulate0" class="proAccumulate">
							<th><font color="red">*</font>产品累积量：</th>
							<td style="text-align: right">产品ID：</td>
							<td>
							<input type="type" id="" data="产品累积量"  name="proID" class="easyui-validatebox" style="width: 260px" maxlength="20" />
							</td>
							<td style="text-align: right">初始值：</td>
							<td colspan="2">
							<input type="number" min="0"  id="" name="initValue" class="easyui-validatebox" style="width: 260px" maxlength="20" />
							</td>
							
						</tr>
					</table>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="formTable" id="">
				<tr>
					<th style="text-align: left;">初始化免费资源： 初始化免费资源说明</th>
				</tr>
				<tr>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="">
						<tr>
							<th><font color="red">*</font>初始化方式：</th>
							<td colspan="8"><select id="sProAway" onchange="setProAway(this)"  style="width: 120px;height: 24px;">
									<option value="1">手动填写</option>
									<option value="2">系统选择</option>
							</select></td>
						</tr>
						<tr id="trHM">
							<th><font color="red">*</font>产品ID：</th>
							<td><input type="type" id="txtProIdInit" name="txtProIdInit"
								class="easyui-validatebox" style="width: 260px" maxlength="20" />
							</td>
							<td style="text-align: right">初始值：</td>
							<td><input type="number" min="0" id="txtProInitVal" name="txtProInitVal" 
								class="easyui-validatebox" style="width: 160px" maxlength="20" />
							</td>
						</tr>
						<tr id="trChoice" style="display:none;">
							<th rowspan="2"><font color="red">*</font>产品包：</th>
							<td rowspan="2" colspan="5">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tbProduct">
							
							</table>
							</td>
							
						</tr>
					</table>
				</tr>
			</table>
		</div>
		<div style="display: none;" id="dstepThree">
		<h2>步骤三： 预期结果 （备注说明）</h2>
		<h1>免费资源预设结果 （备注说明）</h1>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="stepThree">
		<thead>
				<tr>
					<th  width="180" style="text-align: left;">产品</th>
					<th  width="180" style="text-align: left;">类型</th>
					<th  width="180" style="text-align: left;">初始值</th>
					<th  width="180" style="text-align: left;">预期值</th>
			   </tr>
		</thead>
          <tr>
          <td>2312312</td>
          <td>32131231</td>
          <td>32131231</td>
          <td><input type="number" min="0" ></td>
          </tr>
		</table>
		<h1>预期累积量结果 （备注说明）</h1>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbExpAmount">
			
		</table>
		<h1>详单预期结果 （备注说明）</h1>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="">
			<thead>
				<tr>
					<th  width="180" style="text-align: left;">表</th>
					<th  width="180" style="text-align: left;">字段值设定
					<a href="javascript:setResultTemplate();"
					class="easyui-linkbutton" iconCls="icon-search">选择模版</a>
					<span id="spResultName" style="display: none;">已选择模版：</span>
					</th>
			   </tr>
			   </thead>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable" id="tbResultTemplate">
		</table>
		</div>
		
	</form>
	<div align="center" style="padding: 5px;">
		<a id="aSave" style="display: none;cursor: pointer;" onclick="saveMethod()"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a> 
		<a id="aNext"  onclick="forNext(0)"  class="easyui-linkbutton" >下一步</a>
		<a id="aPre" onclick="forPre(0)" style="display: none;"  class="easyui-linkbutton" >上一步</a>
		<a href="regressionUseCase/listInit?proID=${useCase.proID}" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
	<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div>
</body>