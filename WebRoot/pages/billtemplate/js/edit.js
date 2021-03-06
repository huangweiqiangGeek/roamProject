$(document).ready(function(){
	initBusinessTypeSelect();
});

$(function(){
	$("#templateType").combobox({ 
		onSelect: function (record) {
			var templateType1=$("#templateType").combobox('getValues');
			$("#templateType1").val(templateType1);
		}
	})
});

function saveMethod(){
	var templateName =trans($('#templateName').val());
	if(isNull(templateName)){
		$.messager.alert('温馨提示','请输入名称','',function(){
			$("#templateName").focus();
		});
		return false;
	}
	var templateStatus =trans($('#templateStatus').combobox('getValue'));
	if(isNull(templateStatus) || templateStatus==-1){
		$.messager.alert('温馨提示','请选择状态');
		return false;
	}
	//新增类型	
	var templateType =trans($('#templateType').combobox('getValue'));
	if(templateType==-1){
		$.messager.alert('温馨提示','请选择模板类型');
		f=false;
		return false;
	}
	//
	var templateAttribute='{"templateAttribute\":[';
	var f=true;
	//{"templateAttribute":[{"fieldName":"333","choiceAway":"7","range":444,"scriptUrl":"333","default":"0"}]}
	$(".TemplateAttribute").each(function(i,e){
		var fieldName = trans($(e).find("input[name='fieldName']" ).val());
		if(isNull(fieldName)){
			$.messager.alert('温馨提示','请填写字段名');
			f=false;
			return false;
		}
		templateAttribute+='{"fieldName":"'+fieldName+'"'+",";
		var choiceAway =$(e).find("select[name='choiceAway']" ).val();//启用
		if(choiceAway==-1){ 
			$.messager.alert('温馨提示','请选择选择方式');
			f=false;
			return false;
		}	
		templateAttribute+='"choiceAway":"'+choiceAway+'"'+",";
		
		var hide = trans($(e).find("input[name='hide']" ).val());
		if(hide==""){
			$.messager.alert('温馨提示','请填写是否隐藏');
			f=false;
			return false;
		}
		templateAttribute+='"hide":"'+hide+'"'+",";//
		var explain = trans($(e).find("input[name='explain']" ).val());
		if(explain==""){
			$.messager.alert('温馨提示','请填写此字段的备注说明');
			f=false;
			return false;
		}
		templateAttribute+='"range":"'+$(e).find("input[name='range']" ).val()+'"'+",";
		templateAttribute+='"scriptUrl":"'+$(e).find("input[name='scriptUrl']" ).val()+'"'+",";
		templateAttribute+='"explain":"'+$(e).find("input[name='explain']" ).val()+'"'+",";
		templateAttribute+='"default":"'+$(e).find("input[name='default']" ).val()+'"'+"}";
		if($(".TemplateAttribute").length!=i+1){
			templateAttribute+=",";
		}
	});
	if(!f){
		return false;
	}
	templateAttribute+=']}';
	$("#templateAttributes").val(templateAttribute);
	$("#addOrUpateForm").submit();
}
var idx = 0;
var num = 1;
function removeTR(obj){
	$('#trTemplateAttribute'+obj+'').remove();
	num--;
	$('.trLenght').html(num);
}

function setStatus(){
	var json = JSON.parse('${satusList}');
	$.each(json, function(idx, v) {
	    alert(v.commTypeName);
	});
}

function addTR(obj){
	if($("#trTemplateAttribute"+obj).find("input[name='fieldName']" ).val()==''){
		$.messager.alert('温馨提示','请填写字段名','',function(){
			$("#trTemplateAttribute"+obj).find("input[name='fieldName']").focus();
		});
		return;
	}
	if($("#trTemplateAttribute"+obj).find("select[name='choiceAway']" ).val()=='' || $("#trTemplateAttribute"+obj).find("select[name='choiceAway']" ).val()==-1){
		$.messager.alert('温馨提示','请选择选择方式');
		return;
	}
	idx++;
	num++;
	$('.trLenght').html(num);
	var c='<tr id="trTemplateAttribute'+idx+'" class="TemplateAttribute">'+$("#trTemplateAttribute0").html()+'</tr>';
	$("#trTemplateAttribute"+obj).after(c);
	$("#trTemplateAttribute"+idx+" #addContent").attr('href','javascript:addTR('+idx+');');
	$("#trTemplateAttribute"+idx+" #removeContent").attr('href','javascript:removeTR('+idx+');');
}


function initBusinessTypeSelect(){
	$.ajax({
		url: "productGroupController/queryProductBusinessTypeSelect",
		dataType: 'json',  
		async: false,
		mode: 'remote',
		success: function (jsonstr) {
			console.log(jsonstr);
			jsonstr.unshift({  
				'type_id': ' ',  
				'type_name': ' ',
			});//向json数组开头添加自定义数据 
			$('#templateType').combobox({  
				data: jsonstr,  
				valueField: 'type_id',  
				textField: 'type_name',  
				onLoadSuccess: function () { //加载完成后,设置选中第一项  
					var templateType1=$("#templateType1").val();
					if(templateType1 != null){
						$("#templateType").combobox('select',templateType1);
					}else{
						var val = $(this).combobox('getData');  
					}
				}
			});  
		} });

}

