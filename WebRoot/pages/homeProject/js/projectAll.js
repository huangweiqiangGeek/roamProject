$(function(){
	var proID = trans($('#proID').val());
	var params={proID:proID}
	$("#dg").datagrid("reload",params);
})
function addMethod(){
	showWin("project/addInit","新增项目",800,450);
}
function removeMethod(user_id){
	$.messager.confirm('谨慎操作提示', '确认删除项目?', function(r){
		if (r){
			ajaxRequest("project/delete", {id:user_id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function editMethod(user_id){
	showWin("project/updateInit?id="+user_id,"修改项目",750,450);
}

function formatOperate(value,rowDatas,rowIndex){
	var count=rowDatas.useCaseCount;
	if(count>=1){
		return '<a href="useCase/listInit?proID='+value+'">查看用例</a>&nbsp;&nbsp;'
		+'<a href="javascript:editMethod('+value+')">修改</a>&nbsp;&nbsp;'
		+'<a href="useCase/addInit?proID='+value+'">添加用例</a>&nbsp;&nbsp;'
		+'<a href="javascript:projectExecuteCase('+value+');">执行</a>&nbsp;&nbsp;'
		+'<a href="resultRecord/listInit?proID='+value+'&resultType=1">查看结果</a>&nbsp;&nbsp;'
		//+'<a href="javascript:projecRegression('+value+');">项目回归</a>&nbsp;&nbsp;'
		+'<a href="javascript:removeMethod('+value+')">删除项目</a>&nbsp;&nbsp;'
	}else{
		return 	'<a href="javascript:editMethod('+value+')">修改</a>&nbsp;&nbsp;'
		+'<a href="useCase/addInit?proID='+value+'">添加用例</a>&nbsp;&nbsp;'
		+'<a href="javascript:removeMethod('+value+')">删除项目</a>&nbsp;&nbsp;'
	}
}

/**
 * 项目执行
 * @returns {Boolean}
 */
function projectExecuteCase(id){
	
	$.messager.confirm('谨慎操作提示', '确认当前项目进行执行吗?', function(r){
		if (r){//true
			//要求：确认执行后，展现等待效果，异步请求成功后展示执行结果（饼状图项目），执行结果可以从下方data中获取	
			gainNumber(id);
			var params ={proID:id}
			$.ajax({
				type:"POST",
				url:"project/projectExecuteCase",
				dataType:"json",
				data:params,
				success:function(data){
					if(data.type==1){
						$.messager.alert('温馨提示','项目执行成功','',function(){
							//步请求成功后展示执行结果（饼状图项目）TODO
							//准备工作已完成，等待话单正确执行且成功后就可制作饼图
							var successRate= data.successRate;//通过率
							var successUrl = data.successUrl;//通过率地址
							var failureRate = data.failureRate;//失败率
							var failureUrl = data.failureUrl;
							window.location.href=successUrl;
						});
					}else{
						$.messager.alert('温馨提示',''+data.msg+'','',function(){
						});
					}
				}	
			});
		//请求成功
		}
	});
}


/**
 * 项目执行进度数据获取
 * @returns {Boolean}
 */
var din;
function gainNumber(id){
	$("#vessel").css('display','')
	din="myInterval("+id+")";
	setInterval(din,1000);
} 
var xhr;
function myInterval(id){
		xhr=$.ajax({
			type:"POST",
			url:"project/projectExecuteCaseStatus",
			dataType:"json",
			data:"proID="+id,
			success:function(data){
				var sum=data.sum;
				var now=data.now;
				var stop=data.stop;
				onloadp(sum,now,stop)
			}	
		});
 }
/**
 * 项目执行进度数据展示
 * @returns {Boolean}
 */
function onloadp(sum,now,stop){
	if(stop==true){
		clearInterval(din)
		$("#vessel").css('display','none')
		xhr.abort();
		return false
	}
	var val=(now*10000)/(sum*10000);
	val =val*100;
	var num=now+1;
	$('#progress').progressbar({
	   value:val,
	    text:'正在执行第'+num+'条用例，共计'+sum+'条'
	});
}

/**
 * 项目回归
 * @returns {Boolean}
 */
function projecRegression(id){
	$.messager.confirm('谨慎操作提示', '确认当前项目进行回归吗?', function(r){
		if (r){
			//此处需要增加等待效果，需要前端实现
			var params ={pID:id};
			$.ajax({
				type:"POST",
				url:"project/projecRegression",
				dataType:"json",
				data:params,
				success:function(data){
					if(data.type==1){
						$.messager.alert('温馨提示','项目回归','',function(){
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


function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		//var d=new Date(value); 
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
	
}
function fmtProType(value,rowDatas,rowIndex){
	if(value==1){
		return "日常项目";
	}else if(value==2){
		return "回归项目";
	}else{
		return "";
	}
}