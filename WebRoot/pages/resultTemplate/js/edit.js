function saveMethod(){
	var templateType =trans($('#templateType').combobox('getValue'));
	if(isNull(templateType) || templateType==-1){
		$.messager.alert('温馨提示','请选择类型');
		return false;
	}
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
		var tableName = trans($(e).find("input[name='tableName']" ).val());
		if(isNull(tableName)){
			$.messager.alert('温馨提示','请填写表名');
			f=false;
			return false;
		}
		templateAttribute+='{"tableName":"'+tableName+'"'+",";
		var fieldName=$(e).find("input[name='fieldName']" ).val();
		if(isNull(fieldName)){
			$.messager.alert('温馨提示','请填写查询字段');
			f=false;
			return false;
		}
		templateAttribute+='"fieldName":"'+fieldName+'"'+",";
		var explain=$(e).find("input[name='explain']" ).val();
		if(isNull(explain)){
			$.messager.alert('温馨提示','请填写中文字段');
			f=false;
			return false;
		}
		templateAttribute+='"explain":"'+explain+'"'+",";
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
