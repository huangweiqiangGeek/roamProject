function searchMethod(){
	var resultType = $('#resultType').val();
	var proID =$('#proID').val();
	var uCID = trans($('#uCID').val());
	var uCName = trans($('#uCName').val());
	var uCNumber = trans($('#uCNumber').val());
	var uCItemNumber = trans($('#uCItemNumber').val());
	var isPass=$('#isPass').val(); //是否通过
	var params ={resultType:resultType,proID:proID,uCID:uCID,uCName:uCName,uCNumber:uCNumber,uCItemNumber:uCItemNumber,isPass:isPass};
	$("#dg").datagrid("reload",params);	
}	

function removeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认删除执行结果?', function(r){
		if (r){
			ajaxRequest("resultRecord/delete", {id:id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function formatOperate(value,rowDatas,rowIndex){
	//return '<a class="btn handleBtn" href="resultRecord/recordInit?id='+value+'">执行结果</a>&nbsp;&nbsp;'
	return '<a class="btn handleBtn" onclick="showResult('+value+')">执行结果</a>&nbsp;&nbsp;'
			+'<a  class="btn handleBtn" href="javascript:removeMethod('+value+');">删除</a>&nbsp;&nbsp;';
}

function showResult(id){
	 showWin("resultRecord/recordInit?id="+id,"用例执行结果详情",750,450);
}
function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
	
}
var succRate=0;
var failRate=0;
var jsondata=[];
function fmtIsPass(value,rowDatas,rowIndex){
	if(value==0){
		return "是";
	}else if(value==1){
		return "否"
	}else{
		return "";
	}
}

