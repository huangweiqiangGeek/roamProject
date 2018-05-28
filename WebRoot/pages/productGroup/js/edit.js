var gp=1;
var gn=1;
var vp=1;
var gprs=1;
$(document).ready(function(){ 
	initBusinessTypeSelect();//初始化业务类型下拉选
	var group_id=$("#group_id").val();
	var zd_telephone=$("#zd_telephone").val();//判断是不是自动获取
	var zd_bt=$("#zd_bt").val();
	if((group_id != null && "" != group_id)||zd_telephone!=null){//编辑
		//加载基本信息
		initProductGroupInfo(group_id,zd_telephone,zd_bt);
		//加载子产品信息
		//initSubproductGroupInfo(group_id);
		
	}else{//添加
		
	}
   }); 
//下拉选事件
$(function(){
	$("#business_type").combobox({ 
		onSelect: function (record) {
			addBusinessTypeDIV();
		}, 
		onUnselect:function(record){
			$("#table"+record.type_id).remove();
		}
	})  
});

function initProductGroups(id){
	$.ajax({
	type:"get",
	url:"productGroupController/showProductGroupByIdForEdit?group_id="+id,
	async:true,
	error:function(){
		$.messager.alert('溫馨提示','加载产品组信息失败!');
	},
	success:function(data){
		for(var i=0;i<data.length;i++){
			
		}
	}	
})
}
function gpMethod(){
	gp++;
	console.log(gp);
	var h="<tr id='groupProduct"+gp+"' class='gp'><td>"+gp+"</td><td><input class='pro'></td><td><input class='pro'></td>"
	h+="<td><a href='javascript:trsRemove(groupProduct"+gp+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td></tr>"
	$('#groupProducts').append(h)
}
function gnMethod(){
	gn++;
	console.log(gn);
	var h="<tr id='groupNo"+gn+"' class='gn'><td>"+gn+"</td><td><input class='pro'></td>"
	h+="<td><a href='javascript:trsRemove(groupNo"+gn+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td></tr>"
	$('#groupNos').append(h)
}
function vpMethod(){
	vp++;
	var h="<tr id='voiceProduct"+vp+"' class='vp'><td>"+vp+"</td><td><input class='pro'></td><td><input class='pro'></td>"
	h+="<td><a href='javascript:trsRemove(voiceProduct"+vp+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td></tr>"
	$('#voiceProducts').append(h)
}
function gprsMethod(){
	gprs++;
	var h="<tr id='gprsProduct"+gprs+"' class='gprs'><td>"+gprs+"</td><td><input class='pro'></td><td><input class='pro' ></td>"
	h+="<td><a href='javascript:trsRemove(gprsProduct"+gprs+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td></tr>"
	$('#gprsProducts').append(h)
}
function trsRemove(d){
	var n=d.getAttribute("class");
	if(n=="gp"){
		gp--;
	}else if(n=="gn"){
		gn--;
	}else if(n=="vp"){
		vp--;
	}else{
		gprs--;
	}
	$(d).remove();
	sortMethod(n);
}
//删除后对表格数列重新排序
function sortMethod(classname){
	$("."+classname).each(function(index,element){
		$(this).children("td:first-child").text(index+1);
		$(this).attr("id",$(this).attr("id").replace(/[\d]+/,index+1));
		var href=$(this).children("td:last-child").find("a").attr("href").replace(/[\d]+/,index+1);
		$(this).children("td:last-child").find("a").attr("href",href)
	});
}


function nextMethod(){
	var f =true;
	if(!testGP()){
		f = false;
		return false;
	}
	if(!testGN()){
		f = false;
		return false;
	}
	if(!f){
		return false;
	}
	$('.pro').removeAttr("disabled")
	return true;
}
function saveMethod(){
	var testvp = true;
	var testgprs = true;
	if(!nextMethod()){
		return false;
	}
	if(!testVP()){
		testvp = false;
		//return false;
	}
	if(!testGPRS()){
		testgprs = false;
		//return false;
	}
	if(testvp==true||testgprs==true){
		window.parent.configProduct();
		cancelMethod();
	}
}
function testGP(){
	var f=true; 
	var groupProductJson='{"groupProductJson":[';
	$('#groupProducts td input').each(function(i,o){
		var v=trans($(o).val());
		var num=i+1;
		var reg = /[\|&\/（）“”"]/;
		if(isNull(v)){
			$.messager.alert('温馨提示','请输入产品信息','',function(){
				$(o).focus();
			});
			f = false;
			return false;
		}else if (reg.test(v)) {
			$.messager.alert('温馨提示','产品信息不能包含特殊字符','',function(){
				$(o).focus();
			});
			f = false;
			return false;
		}
		if(i%2==0){
			groupProductJson+='{"productName":"'+v+'"';
		}else{
			groupProductJson+='"productId":"'+v+'"}';
		}
		if((num/2)!=$("#groupProducts tbody tr").length){
			groupProductJson+=',';
		}
	})
	if(!f){
		return false;
	}
	groupProductJson+=']}'
	window.parent.$("#groupProductJson").val(groupProductJson);
	//产品组合拼接
	var dataJson = JSON.parse(groupProductJson).groupProductJson;
	var productsMark ="";
	$.each(dataJson, function(i, v) {
		var productName=v.productName;
		productsMark+=productName;
		if(i+1<dataJson.length){
			productsMark+='+';
		}
	})
	$("#groupProductJson").val(groupProductJson);
	$("#productsMark").val(productsMark);
	return true;
}
function testGN(){
	var f=true; 
	var groupNoJson='{"groupNoJson":[';
	$('#groupNos td input').each(function(i,o){
		var v=trans($(o).val());
		if(isNull(v)){
			$.messager.alert('温馨提示','请输入测试号码','',function(){
				$(o).focus();
			});
			f = false;
			return false;
		}
		groupNoJson+='{"phoneNumber":"'+v+'"}';
		if((i+1)!=$("#groupNos tbody tr").length){
			groupNoJson+=',';
		}
	})
	if(!f){
		return false;
	}
	groupNoJson+=']}'
	window.parent.$("#groupNoJson").val(groupNoJson);
	var numberJson = JSON.parse(groupNoJson).groupNoJson;
	var phoneNumberStr ="";
	$.each(numberJson, function(i, v) {
		var number=v.phoneNumber;
		phoneNumberStr+=number;
		if(i+1<numberJson.length){
			phoneNumberStr+='+';
		}
	})
	$("#groupNoJson").val(groupNoJson);
	$("#phone_number_str").val(phoneNumberStr);
	return true;
}
function testVP(){
	var f=true; 
	var voiceProductJson='{"voiceProductJson":[';
	$('#voiceProducts td input').each(function(i,o){
		var reg = /[\|&\/（）“”"]/;
		var v=trans($(o).val());
		var num=i+1;
		if(isNull(v)){
			$.messager.alert('温馨提示','请输入语音产品信息','',function(){
				$(o).focus();
			});
			f = false;
			return false;
		}else if (reg.test(v)) {
			$.messager.alert('温馨提示','语音产品信息不能包含特殊字符','',function(){
				$(o).focus();
			});
			f = false;
			return false;
		}
		if(i%2==0){
			voiceProductJson+='{"productName":"'+v+'"';
		}else{
			voiceProductJson+='"productId":"'+v+'"}';
		}
		if((num/2)!=$("#voiceProducts tbody tr").length){
			voiceProductJson+=',';
		}
	})
	if(!f){
		return false;
	}
	voiceProductJson+=']}'
	window.parent.$("#voiceProductJson").val(voiceProductJson);
	return true;
}
function testGPRS(){
	var f=true; 
	var gprsProductJson='{"gprsProductJson":[';
	$('#gprsProducts td input').each(function(i,o){
		var reg = /[\|&\/（）“”"]/;
		var v=trans($(o).val());
		var num=i+1;
		if(isNull(v)){
			$.messager.alert('温馨提示','请输入流量产品信息','',function(){
				$(o).focus();
			});
			f = false;
			return false;
		}else if (reg.test(v)) {
			$.messager.alert('温馨提示','流量产品信息不能包含特殊字符','',function(){
				$(o).focus();
			});
			f = false;
			return false;
		}
		if(i%2==0){
			gprsProductJson+='{"productName":"'+v+'"';
		}else{
			gprsProductJson+='"productId":"'+v+'"}';
		}
		if((num/2)!=$("#gprsProducts tbody tr").length){
			groupProductJson+=',';
		}
	})
	if(!f){
		return false;
	}
	gprsProductJson+=']}'
	window.parent.$("#gprsProductJson").val(gprsProductJson);
	return true;
}
function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}

function initBusinessTypeSelect(){
	$.ajax({
		url: "productGroupController/queryProductBusinessTypeSelect",
		dataType: 'json',  
		async: false,
		mode: 'remote',
		success: function (jsonstr) {  
			jsonstr.unshift({  
				'type_id': ' ',  
				'type_name': ' ',
			});//向json数组开头添加自定义数据 
			$('#business_type').combobox({  
				data: jsonstr,  
				valueField: 'type_id',  
				textField: 'type_name',  
				onLoadSuccess: function () { //加载完成后,设置选中第一项  
					var val = $(this).combobox('getData');  
				}
			});  
		} });

}



//点击下一步的时候根据业务类型动态添加模块
function addBusinessTypeDIV(){
	var selectTypeID = $('#business_type').combobox('getValues');
	var selectTypeName = $('#business_type').combobox('getText');
	if(selectTypeName==""){
		$.messager.alert('溫馨提示','请选择业务类型');
		return false;
	}
	var TypeNameAry=selectTypeName.split(",");
	for(var i=0;i<TypeNameAry.length;i++){

		if($("#table"+selectTypeID[i]).length==0){
			var table="<table id='table"+""+selectTypeID[i]+"'  border='0' cellspacing='0' cellpadding='0' width='350px' class='formTable' height='135px'"+
			"style='display:inline-block;margin:0;text-align:center;overflow:auto;' align='left'>"+
		"<thead>"+
		"<tr>"+
		"<th width='350px' colSpan=4>"+TypeNameAry[i]+"</th>"+
	"</tr>"+
			"<tr>"+
				"<th width='70px'>编号</th>"+
				"<th width='110px'>产品名称</th>"+
				"<th width='110px'>产品ID</th>"+
				"<th width='70px'><a href='javascript:addMethod("+selectTypeID[i]+")' class='btn headerBtn' style='width:40px'>添加</a></th>"+
			"</tr>"+
		"</thead>"+
		"<tr id=a"+selectTypeID[i]+"b1 class='gn'>"+
			"<td>1</td>"+
			"<td><input class='pro' ></td>"+
			"<td><input class='pro'></td>"+
			"<td><a href='javascript:nodeRemove(a"+selectTypeID[i]+"b1)' class='cancelbtn headerBtn' style='width:40px'>删除</a></td>"+
		"</tr>"+
		"</table>";
			$("#businessTypeDIV").append(table);
		}
	}
}

function addMethod(id){
	gn++;
	var t_length=$("#table"+id+" tbody tr").length+1;
	var idstr="a"+id+"b"+t_length;
	var h="<tr id='"+idstr+"' class='gn'><td>"+t_length+"</td><td><input class='pro'></td><td><input class='pro'></td>"
	h+="<td><a href='javascript:nodeRemove("+idstr+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td></tr>"
	$('#table'+id).append(h)
	
}

function nodeRemove(d){
	var tableId=d.getAttribute("id");
	console.log(d,tableId);
	$(d).remove();
	tableId=tableId.substring(1,tableId.indexOf("b"));
	console.log("tableIdtableId",tableId)
	sortnodeMethod(tableId);
}

//服务类型删除后对表格数列重新排序
function sortnodeMethod(tableId){
	$("#table"+tableId+" tbody tr").each(function(index,element){
			$(this).children("td:first-child").text(index+1);
			var trid="a"+tableId+"b"+(index+1);
			$(this).attr("id",trid);
			$(this).children("td:last-child").find("a").attr("href","javascript:nodeRemove("+trid+")");
	});
}

function getProductsJson(){
	var selectTypeID = $('#business_type').combobox('getValues');
	$("#business_type_str").val(selectTypeID);
	window.parent.$("#business_type").val(selectTypeID);//先把选择好的业务类型保存到父页面
	var selectTypeName = $('#business_type').combobox('getText');
	var TypeNameAry=selectTypeName.split(",");
	var productJson="{";
	for(var j=0;j<TypeNameAry.length;j++){
		var tableId="table"+selectTypeID[j];
		var json="{\""+tableId+"\":[";
		$("#"+tableId+" td input").each(function(i,o){
			var v=trans($(o).val());
			var num=i+1;
			if(isNull(v)){
				$.messager.alert('温馨提示','请输入产品信息','',function(){
					$(o).focus();
				});
				f = false;
				return false;
			}
			if(i%2==0){
				json+='{\"productName\":\"'+v+'\"';
			}else{
				json+='\"productId\":\"'+v+'\"}';
			}
			if((num/2)!=$("#"+tableId+" tbody tr").length){
				json+=',';
			}
		})
		json+=']}';
		//动态在父页面写个隐藏域放入拼接好的JSON
		var input="<input type='hidden' id='"+tableId+"' name='"+tableId+"' value='${"+tableId+"}'>";
		window.parent.$("#addUpateForm").append(input);
		window.parent.$("#"+tableId).val(productJson);
		productJson+="\""+selectTypeID[j]+"\":"+json;
		if(j<selectTypeID.length-1){
			productJson+=",";
		}
	}
	productJson+="}";
	return productJson;

}
function quC(idName) {
	var arr=[],json=[];
	$("#"+idName+" tr").each(function(index,ele){
		if(index>0){
			arr.push($(this).find("td").eq(2).find("input").val());
		}
	})
	for(var i=0 ; i<arr.length ; i++){
		if(!json[arr[i]]){
			json[arr[i]]=true;
		}else{
			return 1;
		}
	}
	return 0;
}


function saveMethodBefore(){
	$.messager.confirm('确认对话框', '您确定保存产品组信息吗？', function(r){
		if (r){
			saveMethod2();
		}
	});
}

function saveMethod2(){
	var flag_arr=[],flag=0;
	flag_arr.push(quC("groupProducts"));
	for(var i=0 ; i<$("#businessTypeDIV table").length ; i++){
		flag_arr.push(quC($("#businessTypeDIV table").eq(i).attr("id")));
	}
	for(var i=0 ; i<flag_arr.length ; i++){
		if(1==flag_arr[i]) flag=1;
	}
	if(flag==0){
		var testvp = true;
		var testgprs = true;
		if(!nextMethod()){
			return false;
		}
		var group_id=$("#group_id").val();
		var productJson=$(".flag_edit").text();
		window.parent.$("#group_id").val(group_id);
		//获取业务类型信息
		var productJson=getProductsJson();
		//获取产品信息
		var groupProductJson=$("#groupProductJson").val();
		//获取电话号码信息
		var groupNoJson=$("#groupNoJson").val();
		//获取产品组名称
		var name=$(".productsNM").val();
		//产品组合拼接
		var productsMark =$("#productsMark").val();
		//号码拼接
		var phoneNumberStr = $("#phone_number_str").val()
		///获取选择好的业务类型
		var business_type = $("#business_type_str").val()
		if(isNull(name)){
			$.messager.alert('温馨提示','请输入产品组名称','',function(){
				$(".productsNM").focus();
			});
			return false;
		}
		//判断请求路径是新增还是编辑
		var url;
		if(group_id==null || group_id==""){
			url="productGroupController/saveProductGroupInfo";//新增
		}else{
			url="productGroupController/updateProductGroupById";//编辑
		}
		var param={productJson:productJson,groupProductJson:groupProductJson,groupNoJson:groupNoJson,
				business_type:business_type,name:name,productsMark:productsMark,phoneNumberStr:phoneNumberStr,id:group_id}
		$.ajax({
			type:"POST",
			url:url,
			data:param,
			dataType:'json',
			async:true,
			error:function(){
				$.messager.alert('溫馨提示','添加失败!');
			},
			success:function(data){
				console.log(data);
				var msg = group_id==null || group_id=="" ? "添加":"编辑";
				if(data=="0"){
					$.messager.alert('溫馨提示',msg+'成功!');
					
				}else{
					$.messager.alert('溫馨提示',msg+'失败!');
				}
				window.parent.$('#dg').datagrid('reload');
            	window.parent.location.reload();
				cancelMethod();
			}
		})
		
	}else{
		alert("产品ID重复。。。")
	}
	
}
//加载产品组信息
function initProductGroupInfo(group_id,zd_telephone,zd_bt){
//	if(zd_telephone!=null){
	if(group_id==null){
		var url='productGroupController/autoProductGroupInfo';
		var data={phoneNumber:zd_telephone,business_type:zd_bt};
	}else{
		var url='productGroupController/initProductGroupInfo';
		var data={group_id:group_id};
	}
	var headers={};  
    headers['CSRFToken']=$("#csrftoken").val();
	$.ajax(
			{	url:url,
				type: 'POST',
				data:data,
				dataType: 'json',
				timeout: 10000,
				headers:headers,
				error: function(){
					$.messager.alert('溫馨提示','加载产品组信息失败!');
				},
				success: function(data) {
					console.log("data",data);
					var data1=data[0];
					var data2=data[1];
					var productJson;
					var noJson;
					var business_type;
					for(var key in data1){
							productJson=data1.groupProductJson;
							noJson=data1.groupNoJson;
							business_type=data1.business_type;
						       }
					//获取下拉选选项选中下拉选
					var ary=new Array();
					$(".productsNM").val(data1.name);
					 ary=business_type.split(",");
					 for(var t=0;t<ary.length;t++){
						 $("#business_type").combobox('select',ary[t]);
					 }
					 $("#businessTypeDIV").html('');
					//获取产品json 拼接产品列表
					var json1 = eval('(' + productJson + ')');
					json1=json1.groupProductJson;
					gp = json1.length;
					$("#productName1").val(json1[0].productName);
					$("#productId1").val(json1[0].productId);
					for(var i=1;i<json1.length;i++){
						var json=json1[i];
						var h="<tr id='groupProduct"+eval(i+1)+"' class='gp'><td>"+eval(i+1)+"</td><td><input value='"+json.productName+"' class='pro'></td><td><input value='"+json.productId+"' class='pro' ></td>"
						h+="<td><a href='javascript:trsRemove(groupProduct"+eval(i+1)+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td></tr>"
						$('#groupProducts').append(h)
						
					}
					//获取电话号码json 拼接号码列表
					var json2 = eval('(' + noJson + ')');
					json2=json2.groupNoJson;
					$("#phoneNumber1").val(json2[0].phoneNumber);
					for(var j=1;j<json2.length;j++){
						var json=json2[j];
						var h="<tr id='groupNo"+eval(j+1)+"' class='gn'><td>"+eval(j+1)+"</td><td><input value='"+json.phoneNumber+"'class='pro'></td>"
						h+="<td><a href='javascript:trsRemove(groupNo"+eval(j+1)+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td></tr>"
						$('#groupNos').append(h)
						
					}
				//处理子产品信息
					for(var i=0;i<data2.length;i++){
						var products_json;
						var business_type;
						var business_type_name;
						console.log("data2",data2);
						for(var key in data2[i]){
							business_type=data2[i].business_type;
							products_json=data2[i].products_json;
							business_type_name=data2[i].business_type_name;
						}
						var json = eval('(' + products_json + ')');
						var keys="table"+business_type;
						//console.log(keys);
						json=json[keys];
						
						var table="<table id='table"+""+business_type+"'  border='0' cellspacing='0' cellpadding='0' width='350px' class='formTable' height='135px'"+
						"style='display:inline-block;margin:0;text-align:center;overflow:auto;' align='left'>"+
					"<thead>"+
					"<tr>"+
					"<th width='350px' colSpan=4>"+business_type_name+"</th>"+
				"</tr>"+
						"<tr>"+
							"<th width='70px'>编号</th>"+
							"<th width='110px'>产品名称</th>"+
							"<th width='110px'>产品ID</th>"+
							"<th width='70px'>操作</th>"+
						"</tr>"+
					"</thead>"+
					"<tr>"+
					"<td>1</td>"+
					"<td><input value='"+json[0].productName+"' class='pro' ></td>"+
					"<td><input value='"+json[0].productId+"' class='pro' ></td>"+
					"<td><a href='javascript:addMethod("+business_type+")' class='btn headerBtn' style='width:40px'>添加</a></td>"+
				"</tr>"+
				"</table>";
					$("#businessTypeDIV").append(table);
						//再遍历子产品画上去
						for(var j=1;j<json.length;j++){
							var num = j+1;
							 table=
						"<tr id='a"+business_type+"b"+num+"'>"+
							"<td>"+eval(j+1)+"</td>"+
							"<td><input value='"+json[j].productName+"' class='pro'></td>"+
							"<td><input value='"+json[j].productId+"' class='pro' ></td>"+
							"<td><a href='javascript:nodeRemove(a"+business_type+"b"+num+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td>"+
						"</tr>"+
						"</table>";
							$("#"+keys+"").append(table);
						}
					}
					
				
					
					
				}	
			}
		);
}
//加载子产品信息
function initSubproductGroupInfo(group_id){
	var headers={};  
    headers['CSRFToken']=$("#csrftoken").val();
	$.ajax(
			{	url: 'productGroupController/initSubproductGroupInfo',
				type: 'POST',
				data:{group_id:group_id},
				dataType: 'json',
				timeout: 10000,
				headers:headers,
				error: function(){
					$.messager.alert('溫馨提示','加载产品组信息失败!');
				},
				success: function(data) {
					for(var i=0;i<data.length;i++){
						var products_json;
						var business_type;
						var business_type_name;
						for(var key in data[i]){
							business_type=data[i].business_type;
							products_json=data[i].products_json;
							business_type_name=data[i].business_type_name;
						}
						var json = eval('(' + products_json + ')');
						var keys="table"+business_type;
						//console.log(keys);
						json=json[keys];
						
						var table="<table id='table"+""+business_type+"'  border='0' cellspacing='0' cellpadding='0' width='350px' class='formTable' height='135px'"+
						"style='display:inline-block;margin:0;text-align:center;overflow:auto;' align='left'>"+
					"<thead>"+
					"<tr>"+
					"<th width='350px' colSpan=4>"+business_type_name+"</th>"+
				"</tr>"+
						"<tr>"+
							"<th width='70px'>编号</th>"+
							"<th width='110px'>产品名称</th>"+
							"<th width='110px'>产品ID</th>"+
							"<th width='70px'>操作</th>"+
						"</tr>"+
					"</thead>"+
					"<tr>"+
					"<td>1</td>"+
					"<td><input value='"+json[0].productName+"' class='pro' ></td>"+
					"<td><input value='"+json[0].productId+"' class='pro' ></td>"+
					"<td><a href='javascript:addMethod("+business_type+")' class='btn headerBtn' style='width:40px'>添加</a></td>"+
				"</tr>"+
				"</table>";
					$("#businessTypeDIV").append(table);
						
						//再遍历子产品画上去
						
						for(var j=1;j<json.length;j++){
							var num = j+1;
							 table=
						"<tr id='a"+business_type+"b"+num+"'>"+
							"<td>"+eval(j+1)+"</td>"+
							"<td><input value='"+json[j].productName+"' class='pro'></td>"+
							"<td><input value='"+json[j].productId+"' class='pro' ></td>"+
							"<td><a href='javascript:nodeRemove(a"+business_type+"b"+num+")' class='cancelbtn headerBtn' style='width:40px'>删除</a></td>"+
						"</tr>"+
						"</table>";
							$("#"+keys+"").append(table);
						}
					}
					
				}	
			}
		);
}

