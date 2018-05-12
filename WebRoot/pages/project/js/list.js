
function searchMethod(){
	var proType = $('#proType').val();
	var proProvince =$('#proProvince').val();
	var proName = trans($('#proName').val());
	var proNumber = trans($('#proNumber').val());
	var params ={proType:proType,proProvince:proProvince,proName:proName,proNumber:proNumber};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("project/addInit","新增任务",800,450);
}
function removeMethod(user_id){
	$.messager.confirm('谨慎操作提示', '确认删除任务?', function(r){
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
	showWin("project/updateInit?id="+user_id,"修改任务",750,450);
}
function formatOperate(value,rowDatas,rowIndex){
	var count=rowDatas.useCaseCount;
	var hadExe=rowDatas.hadExe;
	if(count>=1){
		var h='<a class="btn handleBtn" href="useCase/listInit?proID='+value+'">进入任务</a>&nbsp;&nbsp;'
		h+='<a class="btn handleBtn" href="javascript:projectExecuteCase('+value+');">执行任务</a>&nbsp;&nbsp;'
		//h+='<a class="btn handleBtn" href="useCase/addInit?proID='+value+'">添加用例</a>&nbsp;&nbsp;'
		if(hadExe=="是"){
			h+='<a class="btn handleBtn" href="resultRecord/listInit?proID='+value+'&resultType=1">查看结果</a>&nbsp;&nbsp;'
			h+='<a class="btn handleBtn" href="projectExecuteTraceController/toProjectExecuteTracePage?proID='+value+'">执行轨迹</a>&nbsp;&nbsp;'
		}
		h+='<a class="btn handleBtn" href="javascript:editMethod('+value+')">修改信息</a>&nbsp;&nbsp;'
		//+'<a href="javascript:projecRegression('+value+');">任务回归</a>&nbsp;&nbsp;'
		h+='<a class="btn handleBtn" href="project/listInit2?id='+value+'">查看详细</a>&nbsp;&nbsp;'
		h+='<a class="btn handleBtn" href="javascript:removeMethod('+value+')">删除任务</a>&nbsp;&nbsp;'
		return h;
	}else{
		return 	'<a class="btn handleBtn" href="useCase/listInit?proID='+value+'">进入任务</a>&nbsp;&nbsp;'
		+'<a class="btn handleBtn" href="javascript:editMethod('+value+')">修改信息</a>&nbsp;&nbsp;'
		//+'<a class="btn handleBtn" href="useCase/addInit?proID='+value+'">添加用例</a>&nbsp;&nbsp;'
		+'<a class="btn handleBtn" href="project/listInit2?id='+value+'">查看详细</a>&nbsp;&nbsp;'
		+'<a class="btn handleBtn" href="javascript:removeMethod('+value+')">删除任务</a>&nbsp;&nbsp;'
	}
}

/**
 * 任务执行
 * @returns {Boolean}
 */
function projectExecuteCase(id){
	$.messager.confirm('谨慎操作提示', '确认当前任务进行执行吗?', function(r){
		if (r){//true
			//要求：确认执行后，展现等待效果，异步请求成功后展示执行结果（饼状图任务），执行结果可以从下方data中获取	
			console.log(r,id);
			gainNumber(id);
			var params ={proID:id}
			$.ajax({
				type:"POST",
				url:"project/projectExecuteCase",
				dataType:"json",
				data:params,
				success:function(data){
					if(data.type==1){
						/*$.messager.alert('温馨提示',''+data.msg+'','',function(){
							//步请求成功后展示执行结果（饼状图项目）TODO
							//准备工作已完成，等待话单正确执行且成功后就可制作饼图
							var successRate= data.successRate;//通过率
							var successUrl = data.successUrl;//通过率地址
							var failureRate = data.failureRate;//失败率
							var failureUrl = data.failureUrl;
							var resultUrl = data.resultUrl
							window.location.href=resultUrl;
						});*/
						window.location.href=data.resultUrl;
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
//批量执行任务
function executeCaseAll(){
	var ids='';
	$("input[name='pId']").each(function(i,o){
		if($(o).is(':checked')){
			ids+=$(o).val()+',';
		}
	})
	if(/\,$/.test(ids)){
		ids=ids.substring(0,ids.length-1);
	}
	if(isNull(ids)){
		$.messager.alert('温馨提示','请选择需要执行的记录','',function(){
		});
		return false;
	}
	$.messager.confirm('谨慎操作提示', '确认执行所选用例', function(r){
		if (r){
			$('#loading').css('display','')
			var params =ids;
			$.ajax({
				type:"POST",
				url:"useCase/executeCasesBill?id="+params,
				success:function(data){
					$('#dg').datagrid('clearSelections'); 
					if(data.type==-1){
						$.messager.alert('温馨提示',data.msg,'',function(){
							$('#loading').css('display','none')
							return false
						});
					}
						var succ=data.successRate;
						var fair = data.failureRate;
						var cannot = data.cannot;
						var status = data.status;
						if(status==1){
							var h="本次批量执行用例成功，用例共计通过"+succ+"条"
							$.messager.alert('温馨提示',h,'',function(){
								$("#dg").datagrid("reload");
								$('#loading').css('display','none')
							});
						}else if(status==2){
							var h="本次批量执行用例失败，用例通过"+succ+"条，用例失败"+fair+"条，未完成执行用例"+cannot+"条";
							$.messager.alert('温馨提示',h,'',function(){
								$("#dg").datagrid("reload");
								$('#loading').css('display','none')
							});
						}
				},
				error:function(){
					$('#dg').datagrid('clearSelections'); 
					$.messager.alert('温馨提示','批量执行用例不成功',function(){
						console.log("123123");
						$('#loading').css('display','none')
						return false
					});
				}
			});
		}
	});
}
//批量导出
function exportAll(){
	var ids='';
	$("input[name='pId']").each(function(i,o){
		if($(o).is(':checked')){
			ids+=$(o).val()+',';
		}
	})
	if(/\,$/.test(ids)){
		ids=ids.substring(0,ids.length-1);
	}
	if(isNull(ids)){
		$.messager.alert('温馨提示','请选择需要导出的任务','',function(){
		});
		return false;
	}
	$.messager.confirm('谨慎操作提示', '确认导出所选任务', function(r){
		if (r){
			var params =ids;
			var urlStr1="useCase/downloadUseCaseDetail?proIds="+params;
			var form = $('<form></form>');
			form.attr('style', 'display:none');
			form.attr('method', 'post'); //form提交路径        
			form.attr('action', urlStr1);
			var input = $('<input type="text" name="params" id="params" />'); // 可以添加一些参数              
			input.attr('value',"任务统计执行统计表");
			form.append(input);
			$(document.body).append(form);
			form.submit();
			console.log(params);
			$.post(urlStr1,function(data){
				//console.log(data)
				//if(data.success=="true"){
					$.messager.confirm('下载提示', '文件下载成功！', function(){});
				/*}else{
					$.messager.confirm('下载提示', '文件下载失败！', function(){})
				}*/
			})
/*			$.ajax({
				type:"POST",
				url:urlStr1,
				success:function(data){
					console.log("成功",data);
				},
				error:function(){
					$.messager.alert('温馨提示','批量导出任务不成功',function(){
						return false
					});
				}
			});*/
		}
	});
}
/**
 * 项目执行进度数据获取
 * @returns {Boolean}
 */
var din;
var clock
function gainNumber(id){
	if(!isNull(id)){
		console.log(id);
		$("#vessel").css('display','')
		din="myInterval("+id+")";
		console.log(din);
		clock = setInterval(din,1500);
	}else{
		return false
	}
} 
var xhr;
function myInterval(id){
	console.log(111111)
		xhr=$.ajax({
			type:"POST",
			url:"project/projectExecuteCaseStatus",
			dataType:"json",
			data:"proID="+id,
			success:function(data){
				console.log("进度信息",data);
				var sum=data.sum;
				var now=data.now;
				var stop=data.stop;
				onloadp(sum,now,stop);
			}	
		});
 }
/**
 * 项目执行进度数据展示
 * @returns {Boolean}
 */
function onloadp(sum,now,stop){
	if(stop==true){
		clearInterval(clock)
		$("#vessel").css('display','none')
		xhr.abort();
		xhr= "";
		return false
	}
	var val=(now*10000)/(sum*10000);
	val =val*100;
	var num=now+1;
	console.log(val);
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
	$.messager.confirm('谨慎操作提示', '确认当前任务进行回归吗?', function(r){
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
						$.messager.alert('温馨提示',''+data.msg+'','',function(){
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
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
	
}
function fmtProType(value,rowDatas,rowIndex){
	if(value==1){
		return "日常任务";
	}else if(value==2){
		return "回归任务";
	}else{
		return "";
	}
}


function leadIn(){
	$('.prompt').html("&nbsp;&nbsp;仅支持后缀为.xls和.xlsx的Excel表格导入数据！")
	$('#insert').css("display","block")
}
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
var isnext = false;
function fileChange(target,id) {
    var fileSize = 0;
    var filetypes =".xls";
    var filepath = target.value;
    if(filepath){
        var fileend = filepath.substring(filepath.lastIndexOf("."));
//        alert(fileend);
        if(filetypes==fileend||fileend==".xlsx"){
             isnext = true;
        }
        if(!isnext){
        	$.messager.confirm('谨慎操作提示', '文件格式错误，只接受Excel文件');
            target.value ="";
            return false;
        }
    }else{
        return false;
    }
    if (isIE && !target.files) {
        var filePath = target.value;
        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
        if(!fileSystem.FileExists(filePath)){
        	$.messager.confirm('谨慎操作提示', '附件不存在，请重新选择！');
            return false;
        }
    } 
}
function filedsend(){
	if(isnext==false){
		$.messager.confirm('谨慎操作提示', '附件不存在，请重新选择！');
        return false;
	}else if(isnext==true){
		$.messager.confirm('谨慎操作提示', '确认上传Excel文件?', function(r){
			$('.prompt').html("&nbsp;&nbsp;用例正在导入中，请稍后... ...")
             var formData = new FormData();
             formData.append("file", document.getElementById("file1").files[0]);
			$.ajax({
				type: "POST",
				url:"useCase/uploadYuyin",
				data: formData,
                 contentType: false,
                 processData: false,
				 success:function(data){
					 console.log(data);
					if(data.status=="0"){
						var h="用例导入成功，导入用例数量："+data.number+"个";
						$.messager.confirm('导入成功', h);
					}else if(data.status=="1"){
						var h="部分用例导入失败，成功导入用例数量："+data.number+"个";
						$.messager.confirm('温馨提示', h);
					}else if(data.status=="2"){
						$.messager.confirm('导入失败', "话单模板不唯一,第F列检查话单模板名称是否正确,是否改话单模板已经录入");
					}else if(data.status=="3"){
						$.messager.confirm('导入失败', "详单模板不唯一,第AM列是否有这个模板名称或第E列业务类型有问题");
					}else if(data.status=="4"){
						var h = "第"+data.rowNumber+"行没有免费资源时,免费资源相关的列应该为空";
						$.messager.confirm('导入失败', h);
					}else if(data.status=="5"){
						$.messager.confirm('导入失败', "第AA列字段名称不匹配");
					}else if(data.status=="6"){
						$.messager.confirm('导入失败', "第P列字段名称不匹配");
					}else if(data.status=="7"){
						$.messager.confirm('导入失败', "第Q列字段名称不匹配");
					}else if(data.status=="8"){
						$.messager.confirm('导入失败', "第R列字段名称不匹配");
					}else if(data.status=="9"){
						$.messager.confirm('导入失败', "第S列字段名称不匹配");
					}else if(data.status=="10"){
						$.messager.confirm('导入失败', "第T列字段名称不匹配");
					}else if(data.status=="11"){
						var h = "导入的话单模板字段名称"+data.columName+"与话单模板字段不匹配";
						$.messager.confirm('导入失败', h);
					}else if(data.status=="12"){
						var h = "第C列,第"+data.rowName+"行产品群名称不匹配";
						$.messager.confirm('导入失败', h);
					}else if(data.status=="13"){
						var h = "详单模板字段"+data.detialName+"与导入详单字段名称不匹配";
						$.messager.confirm('导入失败', h);
					}else if(data.status=="14"){
						var h = "第"+data.rowName+"行详单模板不能为空";
						$.messager.confirm('导入失败', h);
					}
					$("#dg").datagrid("reload");
					$('#file1').val("")
					isnext=false;
					$('#insert').css("display","none")
				},
				error: function () {
					$.messager.confirm('',"上传失败！");
					$('.prompt').html("&nbsp;&nbsp;仅支持后缀为.xls的Excel表格导入数据！")
					$('#file1').val("")
					isnext=false;
				}
			})		
		})
	}
}
