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
<title>项目执行记录</title>
<link rel="stylesheet" type="text/css" href="../framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="../framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="../framework/js/common.js"></script>
<script type="text/javascript">
function removeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认删除执行结果?', function(r){
		if (r){
			ajaxRequest("delete", {id:id});
			$("#dg").datagrid("reload");
		}
	});
}
function formatOperate(value,rowDatas,rowIndex){
	var str="";
	if ((rowDatas.passCaseCount+rowDatas.failedCaseCount)>0) {
		str = str +'<a href="toRecordOfUseCaseExecuteWithBatch?id='+value+'">查看执行结果</a>&nbsp;&nbsp;'
	}
	str = str + '<a href="javascript:removeMethod('+value+');">删除</a>&nbsp;&nbsp;';
	return str;
}
function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
}
</script>

</head>
<body>







<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<%-- <div id="tb" style="padding:5px;height:auto">
			<div>
				<input type="hidden" id="resultType" name="resultType" value="${resultRecord.resultType}">
				<input type="hidden" id="proID" name="proID" value="${resultRecord.proID}">
				<input type="hidden" id="uCID" name="uCID" value="${resultRecord.uCID}">
				<input type="hidden" id="idPass" name="isPass" value="${resultRecord.isPass}">
			</div>
		</div> --%>
		
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'findExecuteTraceOfProject?proID=${projectExecuteTrace.proID}',toolbar:'#tb',rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th align="center" field="proName" width="300">项目名称</th>
					<th align="center" field="proExecuteBatch" width="80">执行批次</th>
					<th align="center" data-options="field:'proExecuteDate',width:'120',formatter:formatDt">执行时间</th>
					<th align="center" field="allCaseCount" width="100">执行时用例数</th>
					
					<th align="center" field="passCaseCount" width="100">通过用例数</th>
					
					<th align="center" field="failedCaseCount" width="100">不通过用例数</th>
					<th align="center" field="disableCaseCount" width="100">执行未完成用例数</th>
					<th align="center" data-options="field:'id',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		
		<!-- <div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div> -->
	</td>
</tr>
</table>







<!-- <table id="dg"></table>  
<script type="text/javascript">
$('#dg').datagrid({  
	pagination:'true',
	pageSize:10,
    url:'findExecuteTraceOfProject?proID=${projectExecuteTrace.proID}',    
    columns:[[    
        {field:'proName',title:'项目名称',width:300},    
        {field:'proExecuteBatch',title:'项目执行批次',width:80,align:'center'},    
        {field:'proExecuteDate',title:'执行时间',width:120,align:'center',formatter: 
        	function(value,row,index){
	        	if(!isNull(value)){
	        		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	        	}
			}
		},
        {field:'allCaseCount',title:'执行时用例数',width:80,align:'center'} ,
        {field:'passCaseCount',title:'通过用例数',width:70,align:'center'} ,
        {field:'failedCaseCount',title:'不通过用例数',width:80,align:'center'} ,
        {field:'disableCaseCount',title:'执行未完成用例数',width:100,align:'center'},
        {field:'id',title:'操作',width:120,align:'center',formatter: 
        	function(value,row,index){
	        	if(!isNull(value)){
	        		var str ='';
	        		if((row.passCaseCount + row.failedCaseCount) > 0) {
	        			str = str + '<a href="findRecordOfUseCaseExecuteOfOneBatchOfProject?id='+value+'">查看结果</a>&nbsp;&nbsp;'
	        		}
	    			str = str +'<a href="javascript:removeMethod('+value+');">删除</a>&nbsp;&nbsp;';
	        		return str;
	        	}
			}
		}
    ]]
});
</script> -->

</body>
</html>