function saveconfiguration(){
	var provinceID=$('#configuration input[name="provinceID"]').val();
	var provinceName=$('#configuration input[name="provinceName"]').val();
	var ipAddress=$('#configuration input[name="ipAddress"]').val();
	var hostName=$('#configuration input[name="hostName"]').val();
	var hostPassWord=$('#configuration input[name="hostPassWord"]').val();
	var field1=$('#configuration input[name="field1"]').val();
	var field2=$('#configuration input[name="field2"]').val();
	var field3=$('#configuration input[name="field3"]').val();
	var field4=$('#configuration input[name="field4"]').val();
	var field5=$('#configuration input[name="field5"]').val();
	var data='[commCofig:{"provinceID":"'+provinceID+'","ipAddress":"'+ipAddress+'","hostName":"'+hostName+'","hostPassWord":"'+
	hostPassWord+'","field1":"'+field1+'","field2":"'+field2+'","field3":"'+field3+'","field4":"'+field4+'","field5":"'+field5+'"}]';
	$('#configuration input[name="ipAddress"]').attr("value","")
	$('#configuration input[name="hostName"]').attr("value","")
	$('#configuration input[name="hostPassWord"]').attr("value","")
	$('#configuration input[name="field1"]').attr("value","")
	$('#configuration input[name="field2"]').attr("value","")
}

function saveResource(){
	var provinceID=$('#Resource input[name="provinceID"]').val();
	var provinceName=$('#Resource input[name="provinceName"]').val();
	var ipAddress=$('#Resource input[name="ipAddress"]').val();
	var hostName=$('#Resource input[name="hostName"]').val();
	var hostPassWord=$('#Resource input[name="hostPassWord"]').val();
	var field1=$('#Resource input[name="field1"]').val();
	var field2=$('#Resource input[name="field2"]').val();
	var field3=$('#Resource input[name="field3"]').val();
	var field4=$('#Resource input[name="field4"]').val();
	var field5=$('#Resource input[name="field5"]').val();
	var data='[commCofig:{"provinceID":"'+provinceID+'","ipAddress":"'+ipAddress+'","hostName":"'+hostName+'","hostPassWord":"'+
	hostPassWord+'","field1":"'+field1+'","field2":"'+field2+'","field3":"'+field3+'","field4":"'+field4+'","field5":"'+field5+'"}]';
	$('#Resource input[name="ipAddress"]').attr("value","")
	$('#Resource input[name="hostName"]').attr("value","")
	$('#Resource input[name="hostPassWord"]').attr("value","")
	$('#Resource input[name="field1"]').attr("value","")
	$('#Resource input[name="field2"]').attr("value","")
}

function saveStandard(){
	var provinceID=$('#Standard input[name="provinceID"]').val();
	var provinceName=$('#Standard input[name="provinceName"]').val();
	var ipAddress=$('#Standard input[name="ipAddress"]').val();
	var hostName=$('#Standard input[name="hostName"]').val();
	var hostPassWord=$('#Standard input[name="hostPassWord"]').val();
	var field1=$('#Standard input[name="field1"]').val();
	var field2=$('#Standard input[name="field2"]').val();
	var field3=$('#Standard input[name="field3"]').val();
	var field4=$('#Standard input[name="field4"]').val();
	var field5=$('#Standard input[name="field5"]').val();
	var data='[commCofig:{"provinceID":"'+provinceID+'","ipAddress":"'+ipAddress+'","hostName":"'+hostName+'","hostPassWord":"'+
	hostPassWord+'","field1":"'+field1+'","field2":"'+field2+'","field3":"'+field3+'","field4":"'+field4+'","field5":"'+field5+'"}]';
	$('#Standard input[name="ipAddress"]').attr("value","")
	$('#Standard input[name="hostName"]').attr("value","")
	$('#Standard input[name="hostPassWord"]').attr("value","")
	$('#Standard input[name="field1"]').attr("value","")
	$('#Standard input[name="field2"]').attr("value","")
}

function savemoveBill(){
	var provinceID=$('#moveBill input[name="provinceID"]').val();
	var provinceName=$('#moveBill input[name="provinceName"]').val();
	var ipAddress=$('#moveBill input[name="ipAddress"]').val();
	var hostName=$('#moveBill input[name="hostName"]').val();
	var hostPassWord=$('#moveBill input[name="hostPassWord"]').val();
	var field1=$('#moveBill input[name="field1"]').val();
	var field2=$('#moveBill input[name="field2"]').val();
	var field3=$('#moveBill input[name="field3"]').val();
	var field4=$('#moveBill input[name="field4"]').val();
	var field5=$('#moveBill input[name="field5"]').val();
	var data='[commCofig:{"provinceID":"'+provinceID+'","ipAddress":"'+ipAddress+'","hostName":"'+hostName+'","hostPassWord":"'+
	hostPassWord+'","field1":"'+field1+'","field2":"'+field2+'","field3":"'+field3+'","field4":"'+field4+'","field5":"'+field5+'"}]';
	$('#moveBill input[name="ipAddress"]').attr("value","")
	$('#moveBill input[name="hostName"]').attr("value","")
	$('#moveBill input[name="hostPassWord"]').attr("value","")
	$('#moveBill input[name="field1"]').attr("value","")
	$('#moveBill input[name="field2"]').attr("value","")
}

function saveResourceResult(){
	var provinceID=$('#ResourceResult input[name="provinceID"]').val();
	var provinceName=$('#ResourceResult input[name="provinceName"]').val();
	var ipAddress=$('#ResourceResult input[name="ipAddress"]').val();
	var hostName=$('#ResourceResult input[name="hostName"]').val();
	var hostPassWord=$('#ResourceResult input[name="hostPassWord"]').val();
	var field1=$('#ResourceResult input[name="field1"]').val();
	var field2=$('#ResourceResult input[name="field2"]').val();
	var field3=$('#ResourceResult input[name="field3"]').val();
	var field4=$('#ResourceResult input[name="field4"]').val();
	var field5=$('#ResourceResult input[name="field5"]').val();
	var data='[commCofig:{"provinceID":"'+provinceID+'","provinceName":"'+provinceName+'","ipAddress":"'+ipAddress+'","hostName":"'+hostName+'","hostPassWord":"'+
	hostPassWord+'","field1":"'+field1+'","field2":"'+field2+'","field3":"'+field3+'","field4":"'+field4+'","field5":"'+field5+'"}]';
	$('#ResourceResult input[name="ipAddress"]').attr("value","")
	$('#ResourceResult input[name="hostName"]').attr("value","")
	$('#ResourceResult input[name="hostPassWord"]').attr("value","")
	$('#ResourceResult input[name="field1"]').attr("value","")
	$('#ResourceResult input[name="field2"]').attr("value","")
}

/*
function cancelMethod(){
	//window.parent.closeWin(false,"winDiv_1");
}
*/
