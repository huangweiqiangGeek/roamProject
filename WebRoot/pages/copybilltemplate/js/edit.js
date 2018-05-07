
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
	var templateAttribute='{"templateAttribute":[';
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
		var choiceAway =$(e).find("select[name='choiceAway']" ).val();
		if(choiceAway==-1){
			$.messager.alert('温馨提示','请选择选择方式');
			f=false;
			return false;
		}
		templateAttribute+='"choiceAway":"'+choiceAway+'"'+",";
		templateAttribute+='"range":"'+$(e).find("input[name='range']" ).val()+'"'+",";
		templateAttribute+='"scriptUrl":"'+$(e).find("input[name='scriptUrl']" ).val()+'"'+",";
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






