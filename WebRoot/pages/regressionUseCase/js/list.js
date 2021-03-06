
function searchMethod(){
	var templateName = $('#templateName').val();
	var provinceID =$('#provinceID').val();
	var uCUserID=$('#provinceID').val();
	var proType=$('#proType').val();
	var templateStatus=$('#templateStatus').combobox('getValue'); 
	var templateType=$('#templateType').combobox('getValue'); 
	var params ={templateName:templateName,provinceID:provinceID,proType:proType,templateStatus:templateStatus,templateType:templateType,uCUserID:uCUserID};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("regressionUseCase/addInit","新增项目用例",800,450);
}
function removeMethod(user_id){
	$.messager.confirm('谨慎操作提示', '确认删除项目用例?', function(r){
		if (r){
			ajaxRequest("regressionUseCase/delete", {id:user_id});
		}
	});
}
/**
 * 多选删除
 * @returns {Boolean}
 */
function removeAll(){
	var ids='';
	$("input[name='id']").each(function(i,o){
		if($(o).is(':checked')){
			ids+=$(o).val()+',';
		}
	})
	if(/\,$/.test(ids)){
		ids=ids.substring(0,ids.length-1);
	}
	if(isNull(ids)){
		$.messager.alert('温馨提示','请选择需要删除的记录','',function(){
		});
		return false;
	}
	$.messager.confirm('谨慎操作提示', '确认删除项目用例?', function(r){
		if (r){
			ajaxRequest("regressionUseCase/deleteAll", {ids:ids});
		}
	});
}

/**
 * 批量执行用例
 * @returns {Boolean}
 */
function executeCaseAll(){
	console.log("123123");
	var ids='';
	$("input[name='id']").each(function(i,o){
		if($(o).is(':checked')){
			ids+=$(o).val()+',';
		}
	})
	if(/\,$/.test(ids)){
		ids=ids.substring(0,ids.length-1);
	}
	if(isNull(ids)){
		$.messager.alert('温馨提示','请选择需要执行用例记录','',function(){
		});
		return false;
	}
	$.messager.confirm('谨慎操作提示', '确认执行所选的用例?', function(r){
		if (r){
			var params ={id:ids};
			$.ajax({
				type:"POST",
				url:"regressionUseCase/executeCase",
				dataType:"json",
				data:params,
				success:function(data){
					if(data.type==1){
						$.messager.alert('温馨提示','执行成功','',function(){
							$("#dg").datagrid("reload");
						});
					}else{
						$.messager.alert('温馨提示',''+data.msg+'','',function(){
						});
					}
				}	
			});
		}
	});
}


function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function editMethod(user_id){
	showWin("regressionUseCase/updateInit?id="+user_id,"修改预设结果模版",750,450);
}

function formatOperate(value,rowDatas,rowIndex){
	return '<a title="执行" style="cursor: pointer;" onclick="executeCase('+rowDatas.id+')">执行</a>&nbsp;&nbsp;'
	       +'<a title="查看执行结果" href="resultRecord/listInit?uCID='+rowDatas.id+'&proID='+rowDatas.proID+'&resultType=2">查看执行结果</a>&nbsp;&nbsp;'
	      +'<a title="编辑"  href="regressionUseCase/updateInit?id='+rowDatas.id+'">编辑</a>&nbsp;&nbsp;'
			+'<a title="删除" href="javascript:removeMethod('+rowDatas.id+')">删除</a>&nbsp;&nbsp;';
}

/**
 * 项目执行
 * @returns {Boolean}
 */
function executeProjectCase(id){
	$.messager.confirm('谨慎操作提示', '确认当前项目进行执行吗?', function(r){
		if (r){
			//此处需要增加等待效果，需要前端实现，要求：确认执行后，展现等待效果，异步请求成功后展示执行结果（饼状图项目），执行结果可以从下方data中获取 TODO
			var params ={proID:id};
			$.ajax({
				type:"POST",
				url:"regressionUseCase/executeProjectCase",
				dataType:"json",
				data:params,
				success:function(data){
					if(data.type==1){
						$.messager.alert('温馨提示','项目执行成功','',function(){
							//步请求成功后展示执行结果（饼状图项目）TODO
							var successRate= data.successRate;//通过率
							var successUrl = data.successUrl;//通过率地址
							var failureRate = data.failureRate;//失败率
							var failureUrl = data.failureUrl;//失败率地址
							window.location.href=successUrl;//打开成功通过的用例		
							//window.location.href=successUrl?suc=successRate&fai=failureRate;//带参传值?成功url可获取所有参数

							//饼状图
							
						});
					}else{
						$.messager.alert('温馨提示',''+data.msg+'','',function(){
						});
					}
				}	
			});
		}
	});
}


function executeCase(id){
	 var params ={id:id};
	 $.ajax({
			type:"POST",
			url:"regressionUseCase/executeCase",
			dataType:"json",
			data:params,
			success:function(data){
				if(data.type==1){
					$.messager.alert('温馨提示','执行成功','',function(){
						$("#dg").datagrid("reload");
					});
				}else{
					$.messager.alert('温馨提示',''+data.msg+'','',function(){
					});
				}
			}	
		});
}


function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		//var d=new Date(value); 
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
}