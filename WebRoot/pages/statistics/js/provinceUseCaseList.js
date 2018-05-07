//每行添加的各项操作
function formatOperate(value,rowDatas,rowIndex){
	var str='<a class="btn handleBtn" title="执行" onclick="executeCase('+rowDatas.id+')">执行</a>&nbsp;&nbsp;';
	if(rowDatas.passName=="执行通过"||rowDatas.passName=="执行不通过"||rowDatas.executeNum>0){
		str+='<a class="btn handleBtn" onclick="showResult('+rowDatas.id+')">执行结果</a>&nbsp;&nbsp;'
		str+='<a class="btn handleBtn" title="执行轨迹" href="resultRecord/listInit?uCID='+rowDatas.id+'&proID='+rowDatas.proID+'&resultType=2" >执行轨迹</a>&nbsp;&nbsp;';
	}
	if(rowDatas.passName=="执行未完成"){
		str+='<a class="btn handleBtn" title="查看执行错误" href="useCase/toFailurelog?id='+rowDatas.id+'" >异常记录</a>&nbsp;&nbsp;';
	}
	str+='<a class="btn handleBtn" title="编辑"  href="useCase/updateInit?id='+rowDatas.id+'">编辑</a>&nbsp;&nbsp;';
	str+='<a class="btn handleBtn" title="同步"  href="javascript:synchro('+rowDatas.id+')">同步</a>&nbsp;&nbsp;';
	str+='<a class="btn handleBtn" title="删除" href="javascript:removeMethod('+rowDatas.id+')">删除</a>&nbsp;&nbsp;';
	return str;
}
//查看详情
function formatOperate(value,rowDatas,rowIndex){
	var h='<a class="btn handleBtn" href="useCase/listInit?proID='+value+'">查看详细</a>'
	return h;
}