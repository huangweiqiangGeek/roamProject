function searchMethod(){
	var productId =$('#productId').val();
	var productName =$('#productName').val();
	var productType=$('#productType').val();
	var params ={productId:productId,productName:productName,productType:productType};
	$("#dg").datagrid({
		url:'productController/getProductList',
		queryParams:params,
	});
}
function addMethod(){
	showWin("productController/toAddNew","新增产品信息",800,250);
}
function removeMethod(id){
	$.messager.confirm('谨慎操作提示', '确认删除产品信息?', function(r){
		if (r){
			ajaxRequest("useCase/delete", {id:user_id});
		}
	});
}

function editMethod(id){
	showWin("productController/toUpdate?id="+id,"修改产品信息",800,250);
}
//每行添加的各项操作
function formatOperate(value,rowDatas,rowIndex){
	var str='<a class="btn handleBtn" title="编辑"  href="javascript:editMethod('+rowDatas.id+')">编辑</a>&nbsp;&nbsp;';
	str+='<a class="btn handleBtn" title="删除" href="javascript:removeMethod('+rowDatas.id+')">删除</a>&nbsp;&nbsp;';
	return str;
}
function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		//var d=new Date(value); 
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
}

