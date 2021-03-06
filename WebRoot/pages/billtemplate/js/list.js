
 $(document).ready(function(){ 
	 $('#win').window('close');  // close a window
    });  

function searchMethod(){
	var templateName = $('#templateName').val();
	var provinceName =$('#provinceName').val();
	var templateStatus=$('#templateStatus').val(); 
	var params ={templateName:templateName,provinceName:provinceName,templateStatus:templateStatus};
	$("#dg").datagrid("reload",params);
}
function addMethod(){
	showWin("billTemplate/addInit","新增话单模版",800,450);
}
function removeMethod(user_id){
	$.messager.confirm('谨慎操作提示', '确认删除话单模版?', function(r){
		if (r){
			ajaxRequest("billTemplate/delete", {id:user_id});
		}
	});
}
function ajaxRequestSuccessBackInvokeMethod(data) {
	$("#dg").datagrid("reload");
}

function editMethod(user_id){
	showWin("billTemplate/updateInit?id="+user_id,"修改话单模版",750,450);
}
/**
 * 多选删除
 * @returns {Boolean}
 */
function removeAll(){
	var ids='';
	$("input[name='pid']").each(function(i,o){
		if($(o).is(':checked')){
			ids+=$(o).val()+',';
		}
	})
	if(/\,$/.test(ids)){
		ids=ids.substring(0,ids.length-1);
	}
	if(isNull(ids)){
		$.messager.alert('温馨提示','请选择需要删除的话单模板','',function(){
		});
		return false;
	}
	$.messager.confirm('谨慎操作提示', '确认删除话单模板?', function(r){
		if (r){
			ajaxRequest("billTemplate/deleteList", {ids:ids});
		}
	});
}
function formatOperate(value,rowDatas,rowIndex){
	var h='<a  class="btn handleBtn" href="billTemplate/updateInit?id='+value	+'">修改</a>&nbsp;&nbsp;'
		h+='<a class="btn handleBtn" href="javascript:removeMethod('+value+')">删除</a>&nbsp;&nbsp;';
		h+='<a class="btn handleBtn" href="javascript:copyMethod('+value+')">复制</a>&nbsp;&nbsp;';
		h+='<a class="btn handleBtn" href="javascript:synchro('+value+')">同步</a>&nbsp;&nbsp;';
		return h;
}

function synchro(id){
	$.messager.confirm('谨慎操作提示', '确认同步用例数据?（同步数据后，所有选用该模板的用例话单数据均会更新为修改后的数据）', function(r){
		if (r){
			$.ajax({
				type:"POST",
				url:"billTemplate/updateUseCaseTemplate",
				data:"id="+id,
				success:function(data){
					var msg;
					if(data.success==true){
						msg="数据同步失败"
					}else if(data.success==false){msg="数据同步成功"}
				}
			})
		}
	})
}
function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		//var d=new Date(value); 
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
	
}
function formatAttrJson(value,rowDatas,rowIndex){
	var html='<div id="div'+rowDatas.id+'" style="display: none;">'+rowDatas.attrJson+'</div>';
	return value+html;
	
}
function copyMethod(id){
		$.messager.confirm('谨慎操作提示', '确认复制话单模版?', function(r){
			if (r){
				$.ajax({
					type:"POST",
					url:"billTemplate/copyTemp",
					data:"id="+id,
					success:function(data){
						var msg;
						if(data.success==0){
							msg="复制模板失败"
						}else if(data.success==1){msg="复制模板成功"}
						$.messager.confirm('', msg, function(r){})
							$("#dg").datagrid("reload");
					}
				})
			}
		})
}
function leadIn(){
	$('.prompt').html("&nbsp;&nbsp;仅支持后缀为.xls和.xlsx的Excel表格导入数据！")
	$('#insert').css("display","block")
}

function leadIn2(){
	$('.prompt2').html("&nbsp;&nbsp;仅支持后缀为.xls的Excel表格导入数据！")
	$('#insert2').css("display","block")
	alert(1)
}

var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
var isnext = false;
function fileChange(target,id) {
    var fileSize = 0;
    var filetypes =".xls";
    var filepath = target.value;
    if(filepath){
        var fileend = filepath.substring(filepath.lastIndexOf("."));
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
			$('.prompt').html("&nbsp;&nbsp;模板正在导入中，请稍后... ...")
             var formData = new FormData();
             formData.append("file", document.getElementById("file1").files[0]);
			$.ajax({
				type: "POST",
				url:"billTemplate/uploadBillTemplate",
				data: formData,
                 contentType: false,
                 processData: false,
				 success:function(data){
					/*$('#file1').val("");
					var length = Object.keys(data).length;
					var h="";
					if(length==1){
						for(var i in data){
							h +="成功导入模板"+data[i]+"个";
						} 
						$.messager.confirm('谨慎操作提示', h);
					}else{
						$('#fileBox').css("display","")
						for(var i in data){
							if(i==-1){
								h +="<tr><th>成功导入模板数量：</th><td>"+data[i]+"</td></tr>";
							}else{
								h +="<tr><th>错误行:"+i+"</th><td>错误列："+data[i]+"</td></tr>";
							}
						} 
						$('#errMsg').append(h);
					}
					$('#insert').css("display","none")*/
					if(data.status=="true"){
						var h="模板导入成功，导入模板数量："+data.number+"个";
						$.messager.confirm('温馨提示', h);
					}else if(data.status=="false"){
						var h="部分模板导入失败，成功导入模板数量："+data.number+"个";
						$.messager.confirm('温馨提示', h);
					}else if(data.err=="false"){
						$.messager.confirm('',data.errMsg);
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

var isnext2 = false;
function fileChange2(target,id) {
    var fileSize = 0;
    var filetypes =".xls";
    var filepath = target.value;
    alert("lllll");
    if(filepath){
        var fileend = filepath.substring(filepath.lastIndexOf("."));
        if(filetypes==fileend||fileend==".xlsx"){
             isnext2 = true;
        }
        if(!isnext2){
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
function filedsend2(){
	if(isnext2==false){
		$.messager.confirm('谨慎操作提示', '附件不存在，请重新选择！');
        return false;
	}else if(isnext2==true){
		$.messager.confirm('谨慎操作提示', '确认上传Excel文件?', function(r){
			$('.prompt2').html("&nbsp;&nbsp;模板正在导入中，请稍后... ...")
             var formData = new FormData();
             formData.append("file", document.getElementById("file2").files[0]);
			$.ajax({
				type: "POST",
				url:"billTemplate/uploadBillTemplate2",
				data: formData,
                 contentType: false,
                 processData: false,
				 success:function(data){
					/*$('#file1').val("");
					var length = Object.keys(data).length;
					var h="";
					if(length==1){
						for(var i in data){
							h +="成功导入模板"+data[i]+"个";
						} 
						$.messager.confirm('谨慎操作提示', h);
					}else{
						$('#fileBox').css("display","")
						for(var i in data){
							if(i==-1){
								h +="<tr><th>成功导入模板数量：</th><td>"+data[i]+"</td></tr>";
							}else{
								h +="<tr><th>错误行:"+i+"</th><td>错误列："+data[i]+"</td></tr>";
							}
						} 
						$('#errMsg').append(h);
					}
					$('#insert').css("display","none")*/
					if(data.status=="true"){
						var h="模板导入成功，导入模板数量："+data.number+"个";
						$.messager.confirm('温馨提示', h);
					}else if(data.status=="false"){
						var h="部分模板导入失败，成功导入模板数量："+data.number+"个";
						$.messager.confirm('温馨提示', h);
					}else if(data.err=="false"){
						$.messager.confirm('',data.errMsg);
					}
					$("#dg").datagrid("reload");
					$('#file2').val("")
					isnext2=false;
					$('#insert2').css("display","none")
				},
				error: function () {
					$.messager.confirm('',"上传失败！");
					$('.prompt2').html("&nbsp;&nbsp;仅支持后缀为.xls的Excel表格导入数据！")
					$('#file2').val("")
					isnext2=false;
				}
			})		
		})
	}
}

function addBusinessType(){
	$('#win').window('open');
}
function saveBusinessType(){
	$.messager.progress();	// 显示进度条
	$('#ff').form('submit', {    
		url:"productGroupController/addProductBusinessType",    
		onSubmit: function(){    
			
		},    
		success:function(data){ 
			$.messager.progress('close');
			//console.log(data);
			if("0"== data){
				$.messager.alert('溫馨提示','添加成功！'); 
				$('#win').window('close');
				$("#ff").form('clear');
			}else if("1"==data){
				$.messager.alert('溫馨提示','添加失敗！'); 
			}else if("2"==data){
				$.messager.alert('溫馨提示','改类型已经存在了！'); 
			}   
		}    
	});  
}



function cancel(){
	$('#fileBox').css('display','none');
	$('#errMsg tbody').empty();
}