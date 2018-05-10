/***
 * 初始化左边的树形数据
***/
var trHMmax=0  , flag1=0 , flag2=0; var trHMnum=0; var proAways = 1; var pron = [];
function searchPrd(){
	onClickTree();
}

/*初始化树*/
function onLoadSuccess(){
	console.log("20180509版本");
	var task_id=$("#proID").val();
	tree = $('#tt').tree({
		 url:'userCaseTreeController/queryUsercaseTreeList?task_id='+task_id, 
		 idFiled:'tid',
		 textFiled:'productsMark',
		 parentField:'pid',
		 state:closed,
		 onClick:onClickTree,
		 animate:true,
		 onLoadSuccess:function (){
			 //var tgi=$("#tt .tree-node").attr("node-id");
			 var tgi=$("#task_group_id").val();
			 if ( tgi!= null && tgi!= "" && flag1==0) {  
				var selectNode = $("#tt").tree('find', tgi);
				if(selectNode!=null){
					selectNode.attributes.billType=$('#billType').val();
					var nodes=$('#tt').tree('getChildren',selectNode.target);//获取子节点
					var n = 0;
					for(var i=0; i<nodes.length ; i++){
						if($('#billType').val()==nodes[i].attributes.billType){
							n = i;
							$("#tt").tree("select", nodes[i].target); 
						}
					}
					if(nodes[n]){
						onClickTree(nodes[n]);
					}else{
						onClickTree(selectNode);
					}
					flag1++;
				}
			}
		 }
	});
};
//下拉选事件
$(function(){
//	$.ajax({
//		url: 'userCaseTreeController/listPage',
//		dataType: 'json',
//		data:{
//				task_group_id: 111,
//				uCName: "",
//				billType: 2,
//				page: 2,
//			    rows: 14
//			    },
//		async: false,
//		success: function (res) { 
//			  console.log("res",res);
//		} 
//	});
	
	
	
	$("#productCombobox").combobox({ 
		onSelect: function (record) {
			var cpIds=$("#cpIds").val();
			if(cpIds.length==0){
				$("#cpIds").val(record.type_id);
			}else{
				cpIds+=","+record.type_id;
				$("#cpIds").val(cpIds);
			}
		}, 
		onUnselect:function(record){
			var cpIds=$("#cpIds").val().split(",");
			var type_id=record.type_id;
			for(var i=0 ; i<cpIds.length-1; i++){
				if(cpIds[i]==type_id) {
					cpIds.splice(i,1);  
		             i--;  
				}
			}
			$("#cpIds").val(cpIds);
		}
	})  
});
function initProductCombobox(){
	var task_id=$("#proID").val();
	$.ajax({
		url: 'userCaseTreeController/queryUsercaseTreeList?task_id='+task_id,
		dataType: 'json',  
		async: false,
		mode: 'remote',
		success: function (jsonstr) { 
			var json=[];
			for(var i=0 ; i<jsonstr.length; i++){
				if(jsonstr[i].phone_number_str!=undefined){
					json.push({  
						'type_id': jsonstr[i].id,  
						'type_name': jsonstr[i].productsMark,
					});
				}
			}
			json.unshift({  
				'type_id': ' ',  
				'type_name': ' ',
			});//向json数组开头添加自定义数据 
			$('#productCombobox').combobox({  
				data: json,  
				valueField: 'type_id',  
				textField: 'type_name',  
				onLoadSuccess: function () { //加载完成后,设置选中第一项  
					var val = $(this).combobox('getData');
				}
			});  
		} 
	});
}

function onClickTree(treeNode){
	var task_group_id=treeNode.id;
	$('#task_group_id').val(task_group_id);
	var group_id=treeNode.attributes.group_id;
	$('#group_id').val(group_id);
	var billType=treeNode.attributes.billType;
	$('#billType').val(billType);
	var uCName=$('#projectName').val();
	var params ={task_group_id:task_group_id,uCName:uCName,billType:billType};
	$("#dg").datagrid({
		url:'userCaseTreeController/listPage',
		toolbar:'#tb',
		rownumbers:true,
		idField:'id',
		autoRowHeight:false,
		pagination:true,
		pageSize:100,
		queryParams:params,
		onClickRow: onclickDatagrid,
		columns:[[ 
		    {field:'id',checkbox:true},//{field:'ck',checkbox:true},
		    {field:'uCName',title:'用例名称',width:249},
		]],
		onLoadSuccess:function (){
			//var usercase_id=$("input[name='id']").val();
			var usercase_id=$("#id").val();
			if ( usercase_id!= null && usercase_id!= "" && flag2==0) { 
				var row=$("#dg").datagrid("getRowIndex",usercase_id);
				if(row!=-1){
					var index=$("#dg").datagrid("getRows")[row];
					$('#dg').datagrid('selectRow',row);
					onclickDatagrid(row,index);
					flag2++;
				}
			}
		}
	});
}
function onclickDatagrid(row,index){
	/*初始化右边的用例表单*/
	var usercase_id=index.id;
	getUseCase(index.id);
	function getUseCase(id){
		$.ajax({
			url: "userCaseTreeController/queryUseCaseValueByID?usercase_id="+usercase_id,
			type:"post",
			dataType: 'json',  
			async: false,
			success: function (result) {
				var jsonstr=result.data;
				$("#task_group_id").val(jsonstr.task_group_id);
				$('#Source').val(jsonstr.haveSource);
				$('#templateType').val(jsonstr.templateType);
				/*单击用例名展示初始化用例基础信息*/
				initUCaseBaseMe(jsonstr);	
				/*单击用例名展示初始化免费资源*/
				initUCaseResource(jsonstr);
				/*单击用例名展示免费资源预期结果*/
				initUCaseExpect(jsonstr);
			   /*单击用例名展示详单预期结果*/
				initUCaseExpDetail(jsonstr);
				
				$('#ticketJson').val(jsonstr.ticketJson);
				$('#resourceJson').val(jsonstr.resourceJson);
				$('#expectJson').val(jsonstr.expectJson);
			
				$('#expDetailJson').val(jsonstr.expDetailJson);
				$('#task_group_id').val(jsonstr.task_group_id);
				var copy_mark=2;
				$('#copy_mark').val(copy_mark);
			} 
		});
	}

}
/*单击用例名初始化用例基础信息*/
function initUCaseBaseMe(jsonstr){
	$("#ticket").html('');
	$('#uCName').val(jsonstr.uCName);
	$('#uCNumber').val(jsonstr.uCNumber);
	$('#uCScene').val(jsonstr.uCScene );
	$('#uCUserID').val(jsonstr.uCUserID );
	if(jsonstr.haveSource==1){
		$('#haveSource').combobox('select','有免费资源');
		$(".addSource").removeClass('disableColor').attr("href","javascript:addProduct()"); 
		$("#aNext").removeClass('disableColor').attr("onclick","forNext2()"); 
		$('#sProAway').removeClass('disableSelect').removeAttr("disabled");
		$('#Source').val(jsonstr.haveSource);
		 $("#explorer").html('<tr><th>产品名称</th> <th>初始值</th><th>资源类型</th><th>操作</th></tr><tr class="trHMs selVal" id="0"><td><select id="txtProIdInit" class="combo-text validatebox-text checkVal" name="txtProIdInit" style="width:120px;"></select></td><td><input type="number" id="txtProInitVal" name="txtProInitVal" value="" class="txtVal" style="width: 160px"></td><td><select name="setAssg" class="sel"><option value="0" selected>本月套餐</option><option value="1">上月结转</option><option value="2">套内与结转</option></select></td><td><span class="add1 delete"><a onclick="removeHM(0)"><i></i>删除</a></span></td></tr>');
	}else{
		$('#haveSource').combobox('select','没有免费资源');
		$('.addSource').addClass('disableColor').removeAttr('href');
		$('#sProAway').addClass('disableSelect').attr("disabled","disabled");
		$("#aNext").addClass('disableColor').removeAttr("onclick"); 
		$('#Source').val(jsonstr.haveSource);
		 $("#explorer").html('');
	}
	
	$("#expectedResultTemp").html(jsonstr.resultName);
	$('#spbillName').val(jsonstr.billName);
	//var ticketJson=JSON.parse(jsonstr.ticketJson);
	var ticketJson =  eval('(' + jsonstr.ticketJson + ')');
	var ticketHtml;
	
	$.each(ticketJson.stepOnes, function(idx,o){
		var choiceAway=o.choiceaway;//选择方式
		var fieldName = o.name;//字段名
		var hide = o.hide;//  新增项  是否隐藏
		var defaults = o.default;//  新增项 默认值
		var explain =o.explain;
		var range="range";
		var scripturl="scripturl";
		var h;
			if(hide=="否"){
				ticketHtml+='<tr fieldname="'+fieldName+'" choiceaway="'+choiceAway+'" default="'+defaults+'" range="'+range+'" scripturl="'+scripturl+'" hide="否" explain="'+explain+'"><th><font color="#ff0a29">*</font>'+explain+'</th><td colspan="6"><input type="text" id="chargingPho"  name="fieldName" value="'+this.val+'"></td></tr>';
			}else{
				ticketHtml+='<tr style="display:none;" fieldname="'+fieldName+'" choiceaway="'+choiceAway+'" default="'+defaults+'" range="'+range+'" scripturl="'+scripturl+'" hide="是" explain="'+explain+'"><th><font color="#ff0a29">*</font>'+explain+'</th><td colspan="6"><input type="text" id="chargingPho"  name="fieldName" value="'+this.val+'"></td></tr>';
			};
			if(choiceAway==12){
				 h+='<select name="sTime" id="sTime" choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scripturl+'" onchange="setSTime(this)" style="width: 120px;height: 24px;">'
				 h+='<option value="-1">--时间选取方式--</option>';
				 h+='<option value="1">填写</option>';
				 h+='<option value="2">选择</option>';	
				 h+='</select>';
				 h+=' <span id="spBillTime"></span>';
				 //增加按钮 进行各项操作
			 }else if(choiceAway==09){//对填写框进行占位符设置，为默认值
				 h+='<input type="text" id="" value="'+defaults+'" choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scripturl+
				 '"name="fieldName" class="easyui-validatebox validatebox-text" style="width: 150px" maxlength="100">'+
				 '&nbsp;&nbsp;&nbsp;<button onclick="dependOperation();" disabled="true"  class="easyui-linkbutton">依赖</button>';
			 }else if(choiceAway==10){
				 h+='<input type="text" id="" value="'+defaults+'"choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scripturl+
				 '" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 150px" maxlength="100">'+
				 '&nbsp;&nbsp;&nbsp;<button onclick="choiceOperation();"  disabled="true" class="easyui-linkbutton">选择</button>'; 
			 }else if(choiceAway==11){
				 h+='<input type="text" id="" value="'+defaults+'"choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scripturl+
				 '" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 150px" maxlength="100">'+
				 '&nbsp;&nbsp;&nbsp;<button onclick="conditionOperation();" disabled="true" class="easyui-linkbutton">条件</button>'; 
			 }else{
				 h+='<input type="text"  id="chargingPho" value="'+defaults+'" placeholder="'+defaults+'"choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scripturl+
				 '" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 150px" maxlength="100">';
			 }			
		});
	$("#ticket").append(ticketHtml);
	/*$('#ticketJson')val();*/
}
/*单击用例名展示初始化免费资源*/
function initUCaseResource(jsonstr){
	$('#explorer tbody').html('');
	if($('#Source').val()==0){
		return;
	}else{
		var resourceJson=JSON.parse(jsonstr.resourceJson);
		var dataJson = resourceJson.pro;//初始化免费资源初始化内容（"pro":[{"proAway":"1","proName":"流量","type":"上月结转","val":"123"},{"proName":"流量","type":"上月结转","val":"123"}]）
		var proAway = resourceJson.proAway;//初始化免费资源初始化方式（"proAway":"1"）
		var proName = dataJson[0].proName;
		var val = dataJson[0].val;
	    $("#sProAway").val(proAway);
	    var templateType=jsonstr.billType;
	    for(var i in resourceJson.pro){
			if(resourceJson.pro[i].status==undefined){
				resourceJson.pro[i].status = 1;
			}
		}
		if(proAway==1){
			 //初始化方式为手动填写
			 $(dataJson).each(function(i,o){
				 var proName=$(o).attr('proName'),type=$(o).attr('type'),val =$(o).attr('val'),status=$(o).attr('status'),txtProCarrVal;
					 if(i==0){
						 var h='<tr><th>产品名称</th><th>初始值</th><th>资源类型</th><th>操作</th></tr>';
						 if(status==1){
						 h+='<tr class="trHMs selVal" id="'+i+'"><td><select id="txtProIdInit"  name="txtProIdInit"  class="combo-text validatebox-text checkVal"  style="width:120px;" value='+this.proName+'></select>';//this.proName
						h+='</td><td><input type="number" id="txtProInitVal" name="txtProInitVal" value='+this.val+' class="txtVal" style="width: 160px"></td>';
						 $('#txtProIdInit').val(proName);
						 $('#txtProInitVal').val(val);
						 $('#setAssg').val(type);
						 if(type==2){
								h+='<td class="newTD"><select name="setAssg" class="sel"><option value="0">本月套餐</option><option value="1">上月结转</option><option value="2" selected>套内与结转</option></select></td>';
								h+='<td><span class="add1 delete"><a onclick="removeHM('+i+')"><i></i>删除</a></span></td>'
							}else if(type==1){
								h+='<td class="newTD"><select name="setAssg" class="sel"><option value="0">本月套餐</option><option value="1" selected>上月结转</option><option value="2">套内与结转</option></select></td>';
								h+='<td><span class="add1 delete"><a onclick="removeHM('+i+')"><i></i>删除</a></span></td>'
							}else if(type==0){
								h+='<td class="newTD"><select name="setAssg" class="sel"><option value="0" selected>本月套餐</option><option value="1">上月结转</option><option value="2">套内与结转</option></select></td>';
								h+='<td><span class="add1 delete"><a onclick="removeHM('+i+')"><i></i>删除</a></span></td>'
								}
								h+='</tr>';
								$('#explorer').append(h)
								 initCpId("#txtProIdInit");
								 $("#txtProIdInit").combobox('select',proName);
						 }
								
					 }else if(i>=1){
						 trHMmax++;
						 var h='';
						 if(status==1){
						 h+='<tr class="trHMs selVal" id="'+i+'">';
							h+='<td><select id="txtProIdInit'+i+'" class="combo-text validatebox-text checkVal" value='+this.proName+' name="txtProIdInit" style="width:120px;"></select></td>'
							h+='<td><input type="number" min="0" id="txtProInitVal" name="txtProInitVal" class="easyui-validatebox" style="width: 160px" maxlength="100" value="'+val+'"/></td>'
							
							if(type==2){
								h+='<td class="newTD"><select name="setAssg" class="sel"><option value="0">本月套餐</option><option value="1">上月结转</option><option value="2" selected>套内与结转</option></select></td>';
								h+='<td><span class="add1 delete"><a onclick="removeHM('+i+')"><i></i>删除</a></span></td>'
							}else if(type==1){
								h+='<td class="newTD"><select name="setAssg" class="sel"><option value="0">本月套餐</option><option value="1" selected>上月结转</option><option value="2">套内与结转</option></select></td>';
								h+='<td><span class="add1 delete"><a onclick="removeHM('+i+')"><i></i>删除</a></span></td>'
							}else if(type==0){
								h+='<td class="newTD"><select name="setAssg" class="sel"><option value="0" selected>本月套餐</option><option value="1">上月结转</option><option value="2">套内与结转</option></select></td>';
								h+='<td><span class="add1 delete"><a onclick="removeHM('+i+')"><i></i>删除</a></span></td>'
								}
								h+='</tr>';
								$('#explorer').append(h);
								initCpId("#txtProIdInit"+i);
								$("#txtProIdInit"+i).combobox('select',proName);
								
					 }
					}
					 
					 
			 })
		}else{
			 //系统选择
			 //此处必须重新产品包，否则无法达到更新数据的效果
			 var params ={uCUserID:trans($('#uCUserID').val())};
			 $.ajax({
					type:"POST",
					url:"useCase/getBillProduct",
					dataType:"json",
					data:params,
					success:function(data){
						//从加载取当前用例的所有产品包
						if(data.type==0){
							 var productList = data.productList;
							var h ='<tr ><td colspan="5">产品包内容</td></tr>';
							for(var i =0;i<productList.length;i++){
								h+='<tr class="loadLine"><td><input type="checkbox" onclick="setProduct('+i+')" id="cbPro'+i+'" name="productName" value="'+i+'">产品'+i+'：</td>';
								h+='<td colspan="4"><div style="display:none;" id="dProduct'+i+'">';
								h+='<span><input type="checkbox" id="cbUP'+i+'" name="" > 上月结转 <input type="number" min="0" id="txtUP'+i+'" data="上月结转"></span>';
								h+='<span> <input type="checkbox" id="cbDown'+i+'"> 本月套内  <input type="number" min="0" id="txtDown'+i+'" data="本月套内"></span>';
								h+='</div></td></tr>';
							}
						//从加载取当前用例的所有产品包
					$("#tbProduct").html(h);
					$("#trHM").hide();
				     $("#trChoice").show();
				     //对该用例产品包进行赋值
				     $.each(dataJson, function(idx, v) {
				    	 var proName = v.proName;//产品标识
						 var type = v.type;//预设类型 固定为 上月结转和本月套内
						 var val = v.val;
						 $('#cbPro'+proName+'').attr('checked','checked');
						 setProduct(proName);
						 if(type=='上月结转'){
							 $('#cbUP'+proName+'').attr('checked','checked');
							 $('#txtUP'+proName+'').val(val);
						 }else if(type=='本月套内'){
							 $('#cbDown'+proName+'').attr('checked','checked');
							 $('#txtDown'+proName+'').val(val);
						 }else{
							 
						 }
				     });
				     //对该用例产品包进行赋值
				 }
					}	
				});
		}
	}
}

//初始化产品ID
function initCpId(id){
	pron = [];
	var billType=$('#billType').val();
	var group_id=$('#group_id').val();
	$.ajax({
		url: "productGroupController/initProductsSelectByGroupid?group_id="+group_id+"&billType="+billType,
		dataType: 'json',  
		async: false,
		success: function (jsonstr) {
			if(jsonstr==null){
				jsonstr = [];
			}
			for(var i in jsonstr){
				pron.push(jsonstr[i].name);
			}
			/*jsonstr.unshift({  
				'id': ' ',  
				'name': ' ',
			});*///向json数组开头添加自定义数据 
			$(id).combobox({  
				data: jsonstr,  
				valueField: 'name',  
				textField: 'id',  
				onLoadSuccess: function () { //加载完成后,设置选中第一项  
					var val = $(this).combobox('getData'); 
				}
			});  
		} 
	});
}


/*单击用例名展示免费资源预期结果*/
function initUCaseExpect(jsonstr){
	var expectJson=JSON.parse(jsonstr.expectJson);
	var uCExpect=expectJson.uCExpect;
	$("#stepThree").html('');
	if($('#Source').val()==0){
		return;
	}else{
		var h='<tr><th class="head">产品ID</th><th>号码</th><th>资源类型</th><th>初始值</th><th>预期值</th></tr>';
		$.each(uCExpect,function(idx,v){
			var type=v.type;
			h+='<tr class="uCExpect"><td>'+v.name+'</td><td>'+$('#uCUserID').val()+'</td>';
			 if(type==2){
					h+='<td>套内与结转</td>';
				}else if(type==1){
					h+='<td>上月结转</td>';
				}else if(type==0){
					h+='<td>本月套餐</td>';					
					}
			 	h+='<td>'+v.initVal+'</td><td><input type="number" value="'+v.val+'"></td></tr>';
		});
	$("#stepThree").append(h);	
	}
};
/*单击用例名展示详单预期结果*/
function initUCaseExpDetail(jsonstr){
var expDetailJson=JSON.parse(jsonstr.expDetailJson).expDetailJson;
/*$("#expDetail").html('');*/
$("#tbResultTemplate").html('');
var expDetailHtml;
var h;
$.each(expDetailJson, function(idx, v) {
	 var tablename = v.tablename;
	 var defaults =v.defaults; 
	 var scripturl = v.scripturl;
	 var fieldNames = v.fieldNames;
	 var explain=v.explain;
	 arrFieldName=fieldNames.split('^');
	 arrFieldName.pop();
	 var arrExplain = explain.split(',');
	 $('#tbResultTemplate').attr('tablename',tablename).attr('fieldNames',fieldNames).attr('explain',explain).attr('defaults',defaults).attr('scripturl',scripturl);
	for(var i=0;i<arrFieldName.length;i++){
		if(i%2==0){
			h+='<tr><th>'+arrExplain[i]+':</th><td><input type="text" name="'+arrFieldName[i].split('@')[0]+'"  value="'+arrFieldName[i].split('@')[1]+'" /></td>';
		 }else{
			 h+='<th>'+arrExplain[i]+':</th><td><input type="text" name="'+arrFieldName[i].split('@')[0]+'"  value="'+arrFieldName[i].split('@')[1]+'" /></td>';
		 }				
	}
	 h+='</tr>';
});
$("#tbResultTemplate").append(h);
};
function initProvinceSelect(){
	$.ajax({
		url: "provinceURLContro/queryProvinceList",
		dataType: 'json',  
		async: false,
		mode: 'remote',
		success: function (jsonstr) {  
			jsonstr.list.unshift({  
				'province_id': '',  
				'province_name': '　'  
			});//向json数组开头添加自定义数据 
    		var province=jsonstr.user_id;
			$('#province').combobox({  
				data: jsonstr.list,  
				valueField: 'province_id',  
				textField: 'province_name',  
				onLoadSuccess: function () { //加载完成后,设置选中第一项  
					var val = $(this).combobox('getData');  
					for (var item in val[0]) {  
						if (item == 'province_id') { 						
							if(province!='admin'){
								$('#province').combobox('select',province);  
								$('#province').combobox('disable'); 
							}else{	
						  }	
						}  
					}  
				}
			});  
		} });
}
 

$(document).ready(function(){
	$('#win').window('close');
	initCpbCodeSelect();
	onLoadSuccess();
	initProductCombobox();
	$("#haveSource").combobox({
		onChange: function (n,o) {
			if(n==0){
				$('#Source').val('0');
				$('.addSource').addClass('disableColor').removeAttr('href');
				$('#sProAway').addClass('disableSelect').attr("disabled","disabled");
				$("#aNext").addClass('disableColor').removeAttr("onclick"); 
				$("#explorer").html('');
			}else if(n==1){
				$('#Source').val('1');
				$(".addSource").removeClass('disableColor').attr("href","javascript:addProduct()"); 
				$('#sProAway').removeClass('disableSelect').removeAttr("disabled");
				$("#aNext").removeClass('disableColor').attr("onclick","forNext2()"); 
				$("#explorer").html('<tr><th>产品名称</th> <th>初始值</th><th>资源类型</th><th>操作</th></tr>');
				addProduct();
				//$("#explorer").html('<tr><th>产品ID</th> <th>初始值</th><th>资源类型</th><th>操作</th></tr><tr class="trHMs selVal" id="2"><td><select id="txtProIdInit" class="combo-text validatebox-text checkVal" name="txtProIdInit" style="width:120px;" ></select></td><td><input type="number" id="txtProInitVal" class="txtVal" name="txtProInitVal" value="" style="width: 160px"></td><td><select name="setAssg" class="sel"><option value="0" selected>本月套餐</option><option value="1">上月结转</option><option value="2">套内与结转</option></select></td><td><span class="add1 delete"><a onclick="removeHM(2)"><i></i>删除</a></span></td></tr>');
			}else{
				$('#Source').val('-1');
			}
			if(n=0){
				$('#explorer tbody').html('');
			}
				
			}
		});
	$("#tree_div").css("height",100/*document.documentElement.offsetHeight-40*/);
});

function initCpbCodeSelect(){
	$.ajax({
		url: "project/queryTaskNameListByProvince",
		dataType: 'json',  
		async: true,
		success: function (jsonstr) {  
			$('#task').combobox({  
				data: jsonstr,  
				valueField: 'id',  
				textField: 'proName', 
				onLoadSuccess: function () { //加载完成后,设置选中第一项  
					var val = $(this).combobox('getData'); 
					var task_id = $("#proID").val();
					$.each(val, function(p1, p2) {
						if(p2.id==task_id){
							$('#task').combobox('select',p2.proName);  
							$('#task').combobox('disable'); 
						}
					});
					
				}
			});  
		} });
}
function showCumulant(){
	if($("#cbAccumulate:checked").val()=="on"){
		$("#twoSubject").css("display","");
	}else{
		$("#twoSubject").css("display","none");
	}
};
function removeMethod(user_id){
	$.messager.confirm('谨慎操作提示', '确认删除用例?', function(r){
		if (r){
			ajaxRequest("sysUser/deleteSysUser", {id:user_id});
		}
	});
}

function addMethod(){
	showWin("project/addInit?homeProjectId","新增任务",800,450);
}

function addUseCase(){
	$("#ticket").html('');
	$('#uCName').val('');
	$('#id').val('');
	$('#uCNumber').val('');
	$('#uCScene').val('');
	$('#uCUserID').val('' );
	$('#haveSource').combobox('select','-请选择免费资源-');
	$("#uCName").css("background-color","#FFFFCC");
	$("#expDetail").html('');
	$("#tbResultTemplate").html('');
	$("#stepThree").html('');
	$("#explorer").html('');//spbillName
	$("#spbillName").val('');
	$("#sProAway").combobox('select','手动填写');
	$("#uCName").focus();
	
	 
}

//修改用例
function editMethod(role_id){
	//初始化右边的表单
	showWin("productGroupController/toProductGroupList?homeProjectId=","新增任务",800,450);
}

function addProductLis(){
	var task_id=$("#proID").val();
	var  useCase_id=$("#id").val();
	//初始化右边的表单
	showWin("userCaseTreeController/taskGroupView?task_id="+task_id+"&useCase_id="+useCase_id,"新增任务下的产品组",800,450);
	 $("#tb").css("display","none");
}


/**
 * 下一步验证，
 obj：
 0:话单主体时点击下一步
 1：第一步骤时点击下一步
 2：第二步骤时点击下一步
 */

function forNext2(){
	if(!checkSubject()){
		return false;
	}//用例信息验证
	if(!checkStepOne()){
		return false;
	}//验证话单信息
	if(!checkStepTwo()){
		return false;
	}
	loadExpAmount();
	loadExpect();
}

/**
 * 上一步验证
 obj:
	 0：第一步骤时
	 1：第二步骤时
	 2：第三步骤时
 */
function forPre(obj){

	if(obj==0){
		$("#aPre").hide();
		$("#aNext").attr('onclick',"forNext("+obj+")");
		$("#dStepOne").hide();
		$("#dSubject").show();
	}else if(obj==1){
		$("#dStepOne").show();
		$("#dStepTwo").hide();
		$("#aNext").attr('onclick',"forNext("+(obj-1)+")");
		$("#aPre").attr('onclick',"forPre("+(obj-1)+")");
	}else if(obj==2){
		$("#dstepThree").hide();
		$("#dStepTwo").show();
		$("#aSave").hide();
		$("#aNext").attr('onclick',"forNext("+(obj)+")").show();
		$("#aPre").attr('onclick',"forPre("+(obj-1)+")");
	}
}

/**
 * 验证用例主体信息是否完善
 */
function checkPsd(p){
	var reg=/\d{4,}|[a-zA-Z]{4,}/;
	if(reg.test(p)){
		$.messager.alert('温馨提示','请输入正确的密码格式，不能四位连续的数字或字母','',function(){
			$("#user_pass").focus();
		});
		return false
	}else{
		return true
	}
}


function checkSubject(){
	var uCName =trans($('#uCName').val());
	if(isNull(uCName)){
		$.messager.alert('温馨提示','请输入用例名称','',function(){
			$("#uCName").focus();
		});
		return false;
	}
	//var uCNumber =trans($('#uCNumber').val());
	/*if(isNull(uCNumber)){
		$.messager.alert('温馨提示','请输入用例编号','',function(){
			$("#uCNumber").focus();
		});
		return false;
	}*/
	var uCScene =trans($('#uCScene').val());
	if(isNull(uCScene)){
		$.messager.alert('温馨提示','请输入使用场景','',function(){
			$("#uCScene").focus();
		});
		return false;
	}
	var uCUserID =trans($('#uCUserID').val());
	//0505
	$('#phone').val(uCUserID)
	if(isNull(uCUserID)){
		$.messager.alert('温馨提示','请输入话单归属用户标识','',function(){
			$("#uCUserID").focus();
		});
		return false;
	}
	var haveSource =trans($('#haveSource').combobox('getValue'));
	if(haveSource==-1){
		$.messager.alert('温馨提示','请选择产品是否包含免费资源','',function(){
		});
		return false;
	}
	/*var chargingPho =trans($('#chargingPho').val());
	if(isNull(chargingPho)){
		$.messager.alert('温馨提示','请输入计费号码','',function(){
			$("#chargingPho").focus();
		});
		return false;
	}*/
	return true;
}

/**
 * 验证第一步骤，话单信息是否完善
 */
function checkStepOne(){
	var f=true;
	var json ='{"stepOnes":[';
	var billName = trans($('#spbillName').val());
	if(isNull(billName)){
		$.messager.alert('温馨提示','请选择话单模版','',function(){
		});
		f = false;
		return false;
	}else{
		$('#billName').val(billName);
	}
	//第一步 构建话单table
	
	//添加验证   字段为隐藏的，信息可不填
	//如果此字段为隐藏项，填写的信息默认为设置模板时填写的默认值
	$("#ticket tr").each(function(i,o){
		var choiceaway=$(o).attr('choiceaway');//选择方式
		var fieldName = $(o).attr('fieldName');//字段名
		var hides = $(o).attr('hide');//  新增项  是否隐藏
		var defaults = $(o).attr('default');//  新增项 默认值
		var explain =$(o).attr('explain');
		var ss = $(o).attr('sTime');
		var scripturl =$(o).attr('scripturl');
		var range =$(o).attr('range');
		if(choiceaway=="12"){
			var sTime =$(o).find("select[name='sTime']").val();
			if(sTime!=undefined){
				if(isNull(sTime) || sTime==-1){
					$.messager.alert('温馨提示','请选择时间选取方式','',function(){
					});
					f = false;
					return false;
				}
				var BillTime='';
				if(sTime==1){
					BillTime = $("#txtBillTime").timespinner('getValue');
				}else if(sTime==2){
				    BillTime = $("#spBillTime").html();
				}
				if(isNull(BillTime)){
					$.messager.alert('温馨提示','请输入时分','',function(){
					});
					f = false;
					return false;
				}
				json+='{"name":"'+fieldName+'","choiceaway":"'+choiceaway+'","val":"'+sTime+'","sTime":"'+BillTime+'","hide":"'+hides+'","explain":"'+explain+'","default":"'+defaults+'"}';
				if((i+1)!=$("#stepOnes tr").length){
					json+=',';
				}
			}else{
				var fn = $(o).find("input[name='fieldName']").val();
				json+='{"name":"'+fieldName+'","choiceaway":"'+choiceaway+'","val":"'+fn+'","hide":"'+hides+'","explain":"'+explain+'","default":"'+defaults+'"}';
				if((i+1)!=$("#stepOnes tr").length){
					json+=',';
				}
			}
		}
		else{
			var fn = $(o).find("input[name='fieldName']").val();
			if(hides=="是"){
				var fn = $(o).find("input[name='fieldName']").val()
				if(!fn){fn=defaults}
				
			}else{
				 if(isNull(fn)){$.messager.alert('温馨提示','请输入'+explain+'',function(){
				})
				f = false;
				return false;
				}					
		}
			json+='{"name":"'+fieldName+'","choiceaway":"'+choiceaway+'","val":"'+fn+'","hide":"'+hides+'","explain":"'+explain+'","default":"'+defaults+'","scripturl":"'+scripturl+'","range":"'+range+'"}';
			if((i+1)!=$("#stepOnes tr").length){
				json+=',';
			}
		}
	
	});
	if(!f){
		return false;
	}
	json+=']}';
	$("#ticketJson").val(json);
	return true;
	
}

function clCancel(){
	$('#dStepOne').css('display','none');
	$('#stepOnes').html('')
	$('#spbillNames').html('')
	$('#spbillName').val('');
	$('#billName').val('');
	$('#templateType').val('');
	$('#attrJson').val('');
	$('#billId').val('');
	$('#templateType').val('');
	$('#templateType').val('');
}
function forBillSave(){
	loadTemplates();
	var templateType=$("#templateType").val();
	if( templateType==2){
		$("#setAssg").removeAttr("disabled")
	}
	$('#dStepOne').css('display','none');
}
/**
 * 验证第二步
 * @returns {Boolean}
 */
function checkStepTwo(){
	var templateType=$("#templateType").val();
	if( templateType==2){
		$("#etAssg").removeAttr("disabled")
	}
	/*判断累积量*/
	if($("#cbAccumulate").is(':checked')){
		var totalAccumulate =trans($('#totalAccumulate').val());
		if(isNull(totalAccumulate)){
			$.messager.alert('温馨提示','请输入总累积量初始值','',function(){
				$('#totalAccumulate').focus();
		    });
		  return false;
		}
		var totalAccumulateValue =trans($('#totalAccumulateValue').val());
		if(isNull(totalAccumulateValue)){
			$.messager.alert('温馨提示','请输入金额初始值','',function(){
				$('#totalAccumulateValue').focus();
		    });
		  return false;
		}
		var f = true;
		$('#twoSubject tr').find("input[name='proID']").each(function(i,o){
			if(isNull(trans($(o).val()))){
				f=false;
			  return false;
			}
		})
		
		$('#twoSubject tr').find("input[name='initValue']").each(function(i,o){
			if(isNull(trans($(o).val()))){
				f=false;
			  return false;
			}
		})
		if(!f){
			$.messager.alert('温馨提示','请完善产品累积量产品ID和初始值的信息！','',function(){
		    });
			 return false;
		}
	}
	//初始化免费资源
	var item = $('#sProAway').val();
	if(item==1){
		var flg=false;
		var f=false;
		$("input[name='txtProIdInit']").each(function(i,o){
			if(flg){
				return ;
			}
			if(isNull(this.value)){
				flg=true;
				$.messager.alert('温馨提示','请输入初始化免费资源产品ID','');
				return ;
			}
			
		});
		if(flg){
			return false;
		}
		$("input[name='txtProInitVal']").each(function(i,o){
			if(f){
				return;
			}
			if(isNull(this.value)){
				f=true;
				$.messager.alert('温馨提示','请输入初始化免费资源产品ID初始值','');
				
			}
			
		});
		if(f){
			return false;
		}
		return !(f&&flg);
	}else if(item==2){
		var f=0;
		var ff=true;
		$('#tbProduct input[name="productName"]').each(function(i,o){
			if($(o).is(':checked')){
				f++;
				if(!$('#cbUP'+i).is(':checked') && !$('#cbDown'+i).is(':checked')){
					ff=false;
					return false;
				}
				if($('#cbUP'+i).is(':checked')){
					if(isNull($('#txtUP'+i).val())){
						ff=false;
						return false;
					}
				}
				if($('#cbDown'+i).is(':checked')){
					if(isNull($('#txtDown'+i).val())){
						ff=false;
						return false;
					}
				}
			}
		})
		if(!ff){
			$.messager.alert('温馨提示','请完善产品上月结转或本月套内初始值！','',function(){
		    });
			 return false;
		}
		if(f==0){
			$.messager.alert('温馨提示','请选择需要进行免费资源设置的产品','',function(){
			});
			return false;
		}
		return true;
	}
}

/**
 * 步骤三中验证免费资源预设结果
 */
function checkUCExpect(){	
	var f = true;
	var json ='{"uCExpect":[';
	$("#stepThree tr[class='uCExpect']").each(function(i,o){
		var v = trans($(o).find('input[type="number"]').val());//本月预期值
		var name = trans($(o).find('td').eq(0).html());
		var type = trans($(o).find('td').eq(2).html());
		if(type=="本月套内" || type=="本月套餐"){
			type=0
		}else if(type=="上月结转"){
			type=1
		}else{
			type=2
		}
		if(type==2){
			var initVals = trans($(o).find('td').eq(3).html());//2中类型初始值
			var arr=initVals.split(",");
			var initVal=arr[0];//本月
			initVal=parseFloat(initVal);			
			var initCarrVal=arr[1];//上月
			initCarrVal=parseFloat(initCarrVal);
		}else{
			var initVal = trans($(o).find('td').eq(3).html());//初始值
			initVal=parseFloat(initVal);
		}
		
		if(isNull(v)){
			f=false;
			return false;
		}
		
		json+='{"name":"'+name+'","type":"'+type+'","initVal":"'+initVal+'","val":"'+v+'"}';
		
		if((i+1)!=$("#stepThree tr").length){
			json+=',';
		}
	})
	var source = $('#Source').val();
	var ht = document.getElementsByClassName("head");
	if(ht.length==0&&source==1){
		$.messager.alert('温馨提示','请完善免费资源预设结果 ！','',function(){
		});
		return false;
	}
	if(!f){
		$.messager.alert('温馨提示','请完善免费资源预设结果 ！','',function(){
		});
		return false;
	}
	if(/\,$/.test(json)){
		json=json.substring(0,json.length-1);
	}
	json +=']}';
	$("#expectJson").val(json);
	return true;
}

/**
 * 步骤三中验证累积量结果
 */
function checkUCExpAmount(){
	var f = true;
	if($("#cbAccumulate").is(':checked')){
		var json ='{"uCExpAmount":[';
		$("#tbExpAmount tr[class='uCExpAmount']").each(function(i,o){
			var v = trans($(o).find('input[type="number"]').val());
			var name = trans($(o).find('td').eq(0).html());
			var type = trans($(o).find('td').eq(1).html());
			var initVal = trans($(o).find('td').eq(2).find("input[type='number']").val());
			if(isNull(v)){
				f=false;
				return false;
			}
			json+='{"name":"'+name+'","type":"'+type+'","initVal":"'+initVal+'","val":"'+v+'"},';
		})
		if(!f){
			$.messager.alert('温馨提示','请完善免费资源预设结果 ！','',function(){
			});
			return false;
		}

		if(/\,$/.test(json)){
			json=json.substring(0,json.length-1);
		}
		json +=']}';
		$("#expAmountJson").val(json);
	}
	
	return true;
}


/**
 * 验证详单预期结果内容
 */
function checkUCExpDetail(){
	/*debugger;*/
	var resultName = trans($('#resultName').val());
	if(isNull(resultName)){
		$.messager.alert('温馨提示','请选择详单预期结果','',function(){
		});
		return false;
	}
	var f = true;
	//json字符串添加
	var json='{"expDetailJson":[';
	var tableName = $('#tbResultTemplate').attr('tableName');
	var scriptUrl= $('#tbResultTemplate').attr('scriptUrl');
	var defaults= $('#tbResultTemplate').attr('defaults');
	var arrExplain =$('#tbResultTemplate').attr('explain').replace(/\，/g, ",");//字段之间逗号统一
	json+='{"tablename":"'+tableName+'",';
	json+='"scripturl":"'+scriptUrl+'",';
	json+='"defaults":"'+defaults+'",';
	json+='"explain":"'+arrExplain+'",';
	var j="\"fieldNames\":\"";	

	$('#tbResultTemplate input').each(function(i,o){
		if(isNull($(o).val())){
			  f=false;
			  return false;
		  }
		  j+=''+$(o).attr('name')+'@'+$(o).val()+''+'^';
	})
	j+='"';
	json+=j+'}';
	if(!f){
		$.messager.alert('温馨提示','请完善详单预期结果中字段值设定',function(){
	    });
		return false;
	}
	json+=']}';
	/*debugger;*/
	$("#expDetailJson").val(json);
	/*debugger;*/
	return true;
}

/**
 * 增加初始化累积中的产品累计项
 */
function addProAccumulate(obj){
	var totalAccumulate =trans($('#totalAccumulate').val());
	if(isNull(totalAccumulate)){
		$.messager.alert('温馨提示','请输入总累积量初始值','',function(){
			$('#totalAccumulate').focus();
	    });
	  return false;
	}
	var totalAccumulateValue =trans($('#totalAccumulateValue').val());
	if(isNull(totalAccumulateValue)){
		$.messager.alert('温馨提示','请输入金额初始值','',function(){
			$('#totalAccumulateValue').focus();
	    });
	  return false;
	}
	var proID=trans($('#proAccumulate'+obj).find("input[name='proID']" ).val());
	if(isNull(proID)){
		$.messager.alert('温馨提示','请输入产品ID','',function(){
			$('#proAccumulate'+obj).find("input[name='proID']" ).focus();
	    });
	  return false;
	}
	var v=trans($('#proAccumulate'+obj).find("input[name='initValue']" ).val());
	if(isNull(v) || v<=0){
		$.messager.alert('温馨提示','请输入初始值',function(){
			$('#proAccumulate'+obj).find("input[name='initValue']" ).focus();
	    });
	  return false;
	}
	var h ='<tr id="proAccumulate'+(obj+1)+'" class="proAccumulate">';
	h+=$("#proAccumulate0").html();
	h+='</tr>';
	$("#addProAccumulate").attr('onclick','addProAccumulate('+(obj+1)+')');
	$("#twoSubject").append(h);
}

/**
 * 设置是否勾选初始化累积量
 */
function setAccumulate(obj){
	if($("#cbAccumulate").is(':checked')){
		$("#twoSubject").show();
	}else{
		$("#twoSubject").hide();
	}
}

/**
 * 步骤一时选择话单模版
 */
function setBillTemplate(){
	showWin("billTemplate/queryListInit", "选择话单模版", 800, 450);//
}

/**
 * 弹出查询话单时间条件页面
 */
function openBillTemplate(uCUserID,scripturl){
	showWin("useCase/billInit?scriptUrl="+scripturl+"&uCUserID="+uCUserID, "选择时间", 500, 250);
}


/**
 * 确定模版后加载选择的模版内容
 */
function configBillTemplate(){
	$("#stepOnes").html('');
	$("#ticket").html('');
	$("#spbillName").val($("#billName").val()).show();
	$("#spbillNames").html("话单模板："+$("#billName").val()).show();
	//将按钮设置为禁用，二期时添加其实际作用
	var json = JSON.parse($("#attrJson").val());//隐藏的input
	var data = json.templateAttribute;
	var h="";
	 $.each(data, function(idx, v) {
		 var fieldName = v.fieldName;
		 var choiceAway =v.choiceAway;
		 var range = v.range;
		 var scriptUrl = v.scriptUrl;
		 var hide = v.hide;//添加项
		 var explain = v.explain;//备注说明
		 var defaults = v.default;//默认值
		 if(hide=="是"){
			 h+='<tr style="display:none" fieldName="'+fieldName+'" choiceAway="'+choiceAway+'" default="'+defaults+
			 '" range="'+range+'" scriptUrl="'+scriptUrl+'" hide="'+hide+'" explain="'+explain+
			 '"><th>'+explain+'：</th><td colspan="6">';
		 }else{
			 h+='<tr fieldName="'+fieldName+'" choiceAway="'+choiceAway+'" default="'+defaults+
			 '" range="'+range+'" scriptUrl="'+scriptUrl+'" hide="'+hide+'" explain="'+explain+
			 '"><th><font color="red">*</font>'+explain+'：</th><td colspan="6">';
		 }
		 if(choiceAway==12){
			 h+='<select name="sTime" id="sTime" choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scriptUrl+'" onchange="setSTime(this)" style="width: 120px;height: 24px;">'
				 h+='<option value="-1">--时间选取方式--</option>';
			 h+='<option value="1">填写</option>';
			 h+='<option value="2">选择</option>';	
		     h+='</select>';
		     h+=' <span id="spBillTime"></span>';
			 //增加按钮 进行各项操作
		 }else if(choiceAway==09){//对填写框进行占位符设置，为默认值
			 h+='<input type="text" id="" value="'+defaults+'" choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scriptUrl+
			 '"name="fieldName" class="easyui-validatebox validatebox-text" style="width: 150px" maxlength="100">'+
			 '&nbsp;&nbsp;&nbsp;<button onclick="dependOperation();" disabled="true"  class="easyui-linkbutton">依赖</button>';
		 }else if(choiceAway==10){
			 h+='<input type="text" id="" value="'+defaults+'"choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scriptUrl+
			 '" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 150px" maxlength="100">'+
			 '&nbsp;&nbsp;&nbsp;<button onclick="choiceOperation();"  disabled="true" class="easyui-linkbutton">选择</button>'; 
		 }else if(choiceAway==11){
			 h+='<input type="text" id="" value="'+defaults+'"choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scriptUrl+
			 '" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 150px" maxlength="100">'+
			 '&nbsp;&nbsp;&nbsp;<button onclick="conditionOperation();" disabled="true" class="easyui-linkbutton">条件</button>'; 
		 }else{
			 h+='<input type="text"  id="chargingPho" value="'+defaults+'" placeholder="'+defaults+'"choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scriptUrl+
			 '" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 150px" maxlength="100">';
		 }
		 //增加按钮 进行各项操作
		  	//按钮功能等二期进行时添加操作功能
		 h+='</td></tr>';
	 });
	 $("#stepOnes").append(h);
	 $("#ticket").append(h);
	 
}



/**
 * 将隐藏的字段显示
 */

function setHide(){
	$("#stepOnes tr").css('display','');
	$('#getHide').css('display','none');
	$('#setHide').css('display','inline-block')
}

/**
 * 将隐藏的字段显示后隐藏
 */

function getHide(){
	$("#stepOnes tr[hide='是']").css('display','none')
	$('#setHide').css('display','none')
	$('#getHide').css('display','inline-block')
}
/**
 * 弹出详单预期结果模版选择
 */
function setResultTemplate(){
	showTwoWin("resultTemplate/resultListInit", "选择预设结果模版", 800, 450);
}
//加载模板类型
function loadTemplates(){
	$.ajax({
		url: 'resultTemplate/resultListPage?provinceID=',
		dataType: 'json',
		type:'post',
		async: true,
		success: function (res) {
			var s = res.rows[0].attrJson.replace(/\|/g, "/").replace(/#/g, '"');
			$("#resultAttrJson").val(s);
			$("#resultName").val(res.rows[0].templateName);
			$("#resultId").val(res.rows[0].id);
			$("#resultType").val(res.rows[0].templateType);
			configResultTemplate();
		}
	})
}

/**
 * 加载选择模版
 */
function configResultTemplate(){
	/*debugger;*/
	$("#tbResultTemplate").html('');
	$("#expDetail").html('');
	$("#expectedResultTemp").text($("#resultName").val())
	var templateType=$("#templateType").val();
	if(templateType=='1'){
		
		$("#spResultName").html("已选择预设结果模版："+"语音详单").show();
	}
	if(templateType=='2'){
		
		$("#spResultName").html("已选择预设结果模版："+"流量详单").show();
	}
	
	$("#spResultName").css("display","none");
	var json = JSON.parse($("#resultAttrJson").val());
	var data = json.templateAttribute;
	var h="";
	$.each(data, function(idx, v) {
		 var tableName = v.tableName;
		 var fieldName = v.fieldName.replace(/\，/g, ",");//字段之间逗号统一
		 var defaults =v.default;
		 var explain = v.explain.replace(/\，/g, ",");
		 var scriptUrl = v.scriptUrl;
		 $('#tbResultTemplate').attr('tableName',tableName).attr('fieldName',fieldName).attr('explain',explain).attr('defaults',defaults).attr('scriptUrl',scriptUrl);
		 var arrFieldName =  fieldName.split(',');
		 var arrExplain = explain.split(',');
		for(var i=0;i<arrFieldName.length;i++){
			if(i%2==0){
				h+='<tr><th>'+arrExplain[i]+':</th><td><input type="text"  name="'+arrFieldName[i]+'" /></td>';
			 }else{
				 h+='<th>'+arrExplain[i]+':</th><td><input type="text"  name="'+arrFieldName[i]+'" /></td>';
			 }				
		}
		 h+='</tr>';
	 });
	 $("#tbResultTemplate").append(h);
}


/**
 * 设定获取初始化免费资源方式   //未完善
 * 目前，只能一开始选定填写方式，添加产品后，更换另一种选择方式，无法清除添加的产品项
 */
function setProAway(obj){
	var item = $(obj).val();
	proAways = parseInt(item);
	if(item==1){
		$("#trHM").show();
		$("#trChoice").hide();
	}else{
		var params ={
			uCUserID:trans($('#uCUserID').val())
		};
	$.ajax({
		type:"POST",
		url:"useCase/getBillProduct",
		dataType:"json",
		data:params,
		success:function(data){
			if(data.type==0){
				 var productList = data.productList;
				var h ='<tr ><td colspan="5">产品包内容</td></tr>';
				for(var i =0;i<productList.length;i++){
					h+='<tr class="loadLine"><td><input type="checkbox" onclick="setProduct('+i+')" id="cbPro'+i+'" name="productName" value="'+i+'">产品'+i+'：</td>';
					h+='<td colspan="4"><div style="display:none;" id="dProduct'+i+'">';
					h+='<span><input type="checkbox" id="cbUP'+i+'" name="" > 上月结转 <input type="number" min="0" id="txtUP'+i+'" data="上月结转"></span>';
					h+='<span> <input type="checkbox" id="cbDown'+i+'"> 本月套内  <input type="number" min="0" id="txtDown'+i+'" data="本月套内"></span>';
					h+='</div></td></tr>';
				}
				$("#tbProduct").html(h);
				$(trChoice).show();
				$("#trHM").hide();
			}
		}
	});

	}
}



/*
 * 根据赋值方式显示不同赋值框    新增
 */

function setAssignment(obj){
	var item =$(obj).val();
	var par=$(obj).parent().parent();
	var h;
	if(item==2){
		h+='<td class="newTD"><input type="number" min="0" id="txtProCarrVal" name="txtProCarrVal" placeholder="上月结转"';
		h+='class="easyui-validatebox" style="width: 160px" maxlength="100" /></td>';
		$(par).append(h)
	}else{
		$(par).nextAll('tr.newTD').remove();
	}
}

/* 
* 点击新增一个产品    新增
*/
function addProduct(){
	trHMmax++;	
	var h;
	var ind=$('#sProAway').val();
	if(ind==1){
		h+='<tr class="trHMs selVal" id='+trHMmax+'><td><select id="txtProIdInit'+trHMmax+'" name="txtProIdInit" class="combo-text validatebox-text checkVal" style="width:120px;"></select></td><td><input type="number" id="txtProInitVal'+trHMmax+'" name="txtProInitVal" value="" class="txtVal" style="width: 160px"></td><td><select name="setAssg" class="sel"><option value="0" selected>本月套餐</option><option value="1">上月结转</option><option value="2">套内与结转</option></select></td><td><span class="add1 delete"><a onclick="removeHM('+trHMmax+')"><i></i>删除</a></span></td></tr>';
		$('#explorer').append(h);
	}else if(ind==2){
		$.messager.alert('温馨提示','已无更多产品','',function(){});
	}
	initCpId("#txtProIdInit"+trHMmax);
}
function removeHM(s){
	var t=document.getElementById(s);
	t.remove();
	trHMmax--;	
}
/**
 * 选择产品
 */
function setProduct(obj){
	if($('#cbPro'+obj).is(':checked')){
		$('#dProduct'+obj).show();
	}else{
		$('#dProduct'+obj).hide();
	}
}

/**
 * 选择话单模版后加载时间
 */
function setSTime(obj){
	var i = $(obj).val();
	if(i==1){
		$("#spBillTime").html('<input id="txtBillTime" style="width:80px;"> ');
		$('#txtBillTime').timespinner({
		    showSeconds: true
	  });  
	  $("#spBillTime").show();
	}else if(i==2){
		//当为选择是spBillTime显示为脚本查询出来的时间
		 $("#spBillTime").html('');
		//弹出查询时间的条件框
		var uCUserID = $("#uCUserID").val();
		var scripturl = $(obj).attr('scripturl');
		openBillTemplate(uCUserID,scripturl);
	}
}

/**
 * 加载初始化预期结果?加载初始化累积量
 */
function loadExpAmount(){
	if($("#cbAccumulate").is(':checked')){
		$('.tbExpAmount').css('display','')
		var json='{"ExpAmount":[';
		var h='<thead><tr>';
		h+='<th width="180" style="text-align: left;">累积量</th>';
		h+='<th width="180" style="text-align: left;">初始值</th>';
		h+='<th width="180" style="text-align: left;"></th>';
		h+='</tr></thead>';
	    h+='<tr class="uCExpAmount"><td width="180" style="text-align: left;">'+$("#totalAccumulate").attr('data')+'</td>';
		h+='<td width="180" style="text-align: left;">'+$("#totalAccumulate").val()+'</td>';
		h+='<td width="180" style="text-align: left;"><input type="number" min="0"></td></tr>';
		json+='{"fileName":"'+$("#totalAccumulate").attr('data')+'","val":"'+$("#totalAccumulate").val()+'"},';
		h+='<tr class="uCExpAmount"><td width="180" style="text-align: left;">'+$("#totalAccumulateValue").attr('data')+'</td>';
		h+='<td width="180" style="text-align: left;">'+$("#totalAccumulateValue").val()+'</td>';
		h+='<td width="180" style="text-align: left;"><input type="number" min="0"></td></tr>';
		json+='{"fileName":"'+$("#totalAccumulateValue").attr('data')+'","val":"'+$("#totalAccumulateValue").val()+'"}';
		$('#twoSubject tr[class="proAccumulate"]').each(function(i,o){
			h+='<tr class="uCExpAmount"><td width="180" style="text-align: left;">'+$(o).find("input[name='proID']").val()+'</td>';
			h+='<td width="180" style="text-align: left;">'+$(o).find("input[name='initValue']").val()+'</td>';
			h+='<td width="180" style="text-align: left;"><input type="number" min="0"></td></tr>';
			json+=',{"fileName":"'+$(o).find("input[name='proID']").val()+'","val":"'+$(o).find("input[name='initValue']").val()+'"}';
		})
		json+=']}';
		$("#tbExpAmount").html(h);
		$("#accumulateJson").val(json);
		if(!isNull($("#id").val())){
			expAmountJsonInit();//修改时激活预期累积量结果赋值
		}
		
	}else{
		$('#tbExpAmount').hide();
		$('#tbExpAmount').prev().hide();
	}
	
}

/**
 * 加载免费资源预设结果
 */
var u;
var t;
function loadExpect(){
	$("#stepThree").html('');
	//先获取第二步初始化免费资源的设定
	var sProAway =$('#sProAway').val();//手动填写/系统选择
	var lateType =$('#templateType').val();
	//$('#billType').val(lateType); 
	if(lateType==1){
		u = "分钟";
	}else if(lateType==2||lateType==4){
		u ="M"
	}else if(lateType==3){
		u ="条"
	}else if(lateType==0){
		u =""
	}
	//拼接初始化资源填写内容JSON
	var json ='{"proAway":"'+sProAway+'","pro":';//{"pro":[{"proAway":"1","proName":"流量","type":"上月结转","val":"123"},{"proName":"流量","type":"上月结转","val":"123"}]}
	var h='<thead><tr><th  width="180" style="text-align: center;" class="head">产品</th><th  width="180" style="text-align: center;">号码</th><th  width="180" style="text-align: center;">资源类型</th>';
	//拼接为HTML
	h+='<th  width="180" style="text-align: left;">初始值</th><th  width="180" style="text-align: left;">预期值</th></tr></thead>'
		if(sProAway==1){
		json+='[';
		var dex=0;
		$('#explorer .trHMs').each(function(i,o){
			var s=$($(o).find("select[name='setAssg']")).val();/* $("select[name='setAssg'] option:selected").val()*/
			var p=$('#uCUserID').val();
			if(s==0){
				t="本月套内"
			}else if(s==1){
				t="上月结转"
			}else{
				t="套内与结转"
			}
			json+='{"proAway":"'+sProAway+'","proName":"'+$(o).find(".combo-text").combobox("getText");//txtProIdInit .combo-text
			
			json+='","type":"'+$(o).find("select[name='setAssg'").val()+'","val":"'+$(o).find('input[name="txtProInitVal"]').val()+'"';
			
			if(s==2){//选择第三类型，既有本月套内 又有上月结转 
				json+=',"txtProCarrVal":"'+$(o).find(".combo-text").combobox("getText")+'"';//json追加上月结转数据初始值
				h+='<tr class="uCExpect"><td>'+$(o).find(".combo-text").combobox("getValue")+'</td><td>'+p+'</td><td>'+t+
				'</td><td>'+$(o).find('input[name="txtProInitVal"]').val()+u+'</td><td><input type="number" min="0"></td></tr>';
			}else{
				h+='<tr class="uCExpect"><td>'+$(o).find(".combo-text").combobox("getValue")+'</td><td>'+p+'</td><td>'+t+
				'</td><td>'+$(o).find('input[name="txtProInitVal"]').val()+u+'</td><td><input type="number" min="0"></td></tr>';
			}
			
			var sel = document.getElementsByClassName("sel");
			if(sel.length==1 || dex==sel.length-1){
				json+='}';
			}else{
				json+='},';
			}
			dex++;
		})
		json+=']';
	}else if(sProAway==2){
		json+='[';
		var j='';
		$('#tbProduct input[name="productName"]').each(function(i,o){
			if($(o).is(':checked')){
				if(!$('#cbUP'+i).is(':checked') && !$('#cbDown'+i).is(':checked')){
					return false;
				}
				if($('#cbUP'+i).is(':checked')){
					if(!isNull($('#txtUP'+i).val())){
						if(isNull(j)){
							j+='{"proAway":"'+sProAway+'","proName":"'+$(o).val()+'","type":"'+$('#txtUP'+i).attr('data')+'","val":"'+$('#txtUP'+i).val()+'"},';
						}else{
							j+='{"proAway":"'+sProAway+'","proName":"'+$(o).val()+'","type":"'+$('#txtUP'+i).attr('data')+'","val":"'+$('#txtUP'+i).val()+'"}';
						}
						h+='<tr class="uCExpect"><td>'+$(o).val()+'</td><td>'+$('#txtUP'+i).attr('data')+'</td><td>'+$('#txtUP'+i).val()+u+'</td><td><input type="number" min="0"></td></tr>';
					}
				}
				if($('#cbDown'+i).is(':checked')){
					if(!isNull($('#txtDown'+i).val())){
						if(isNull(j)){
							j+='{"proAway":"'+sProAway+'","proName":"'+$(o).val()+'","type":"'+$('#txtDown'+i).attr('data')+'","val":"'+$('#txtDown'+i).val()+'"},';
						}else{
							j+='{"proAway":"'+sProAway+'","proName":"'+$(o).val()+'","type":"'+$('#txtDown'+i).attr('data')+'","val":"'+$('#txtDown'+i).val()+'"}';
						}
						h+='<tr class="uCExpect"><td>'+$(o).val()+'</td><td>'+$('#txtDown'+i).attr('data')+'</td><td>'+$('#txtDown'+i).val()+u+'</td><td><input type="number" min="0"></td></tr>';
					}
				}
			}
		})
		if(/\,$/.test(j)){
			j=j.substring(0,j.length-1);
		}
		json+=j+']';
	}
	json+='}';
	
	$("#stepThree").html(h);
	$("#resourceJson").val(json);
	if(!isNull($("#id").val())){
		expectJsonInit();//修改时激活 免费资源预设结果赋值
	}
}

function updateInit(){
	var i=$('#Source').val();
	$('#haveSource').combobox("select",i);
	billTemplateInit();//修改时初始化话单模版模块赋值
	accumulateJsonInit();// 修改时初始化累积量赋值，累积量非必填因此可能为空需要做验证
	resourceJsonInit();//修改时加载 初始化免费资源赋值
	expDetailJsonInit();//修改时加载详单预期结果赋值
	expectJsonInit();//修改时加载免费资源预期结果
	
}

/**
 * 修改时加载详单预期结果赋值
 */
function expDetailJsonInit(){
	$("#tbResultTemplate").html('');
	$("#spResultName").html("已选择预设结果模版："+$("#resultName").val()).show();
	var expDetailJson = $("#expDetailJson").val().replace(/＃/g, '"');//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）	
	var dataJson = JSON.parse(expDetailJson).expDetailJson;	
	var h ="";
	$.each(dataJson, function(idx, v) {
		var tablename = v.tablename;
		var fieldNames = v.fieldNames;//"fieldNames":"proId@1^proName@2"
		var explain= v.explain;
		var arrFieldName = fieldNames.split('^');//proId@1
		var arrExplain = explain.split(',');
		var defaults = v.defaults;
		var scripturl = v.scripturl;
		var fieldName ='';
		for(var i=0;i<arrFieldName.length;i++){
			var arr = arrFieldName[i].split('@');
			fieldName+=arr[0];
			if((i+1)!=arrFieldName.length){
				fieldName+=",";
			}
		}
		h+='<tr tableName="'+tablename+'" fieldName="'+fieldName+'"explain="'+explain+'" defaults="'+defaults+'" scripturl="'+scripturl+'">';
		 h+='<td width="180" style="text-align: left;">'+tablename+'</td><td width="180" style="text-align: left;">';
		for(var i=0;i<arrFieldName.length;i++){
			var arr = arrFieldName[i].split('@');
		   h+=''+arrExplain[i]+':  &nbsp;&nbsp;&nbsp;<input type="text" name="'+arr[0]+'" value="'+arr[1]+'"><br>';
		}
		 h+='</td></tr>';
		
	}) 
	$("#tbResultTemplate").html(h);
}


/**
 * 修改时加载 详单预期结果赋值
 */
function expAmountJsonInit(){
	var expAmountJson = trans($("#expAmountJson").val());
	expAmountJson = JSON.parse(expAmountJson.replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号  转义问题
	var dataJson= expAmountJson.uCExpAmount;
	$.each(dataJson, function(idx, v) {
		var val = v.val;
		$('#tbExpAmount tbody tr').eq(idx).find("input[type='number']").val(val);
	}) 
}


/**
 * 修改时加载 预期累积量结果赋值
 */
function expectJsonInit(){
	var expectJson = trans($("#expectJson").val());
	expectJson = JSON.parse(expectJson.replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号 转义问题
	var dataJson= expectJson.uCExpect;
	$.each(dataJson, function(idx, v) {
	/*	debugger;*/
		var val = v.val;
		$('#stepThree tbody tr').eq(idx).find("input[type='number']").val(val);
	})
	
}

/**
 * 修改时加载 初始化免费资源赋值
 */
function resourceJsonInit(){
	var resourceJson = trans($("#resourceJson").val());
	resourceJson = JSON.parse(resourceJson.replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号
	var dataJson = resourceJson.pro;//初始化免费资源初始化内容（"pro":[{"proAway":"1","proName":"流量","type":"上月结转","val":"123"},{"proName":"流量","type":"上月结转","val":"123"}]）
	var proAway = resourceJson.proAway;//初始化免费资源初始化方式（"proAway":"1"）
	var proName = resourceJson.proName;
	var val = resourceJson.val;
	var templateType=$("#templateType").val();
    $("#sProAway").val(proAway);
	if(proAway==1){
		 //初始化方式为手动填写		
		 $("#trHM").show();
	     $("#trChoice").hide();
		 $(dataJson).each(function(i,o){
				 var proName=$(o).attr('proName')
				 var type=$(o).attr('type');
				 var  val =$(o).attr('val'); 
				 var txtProCarrVal;
				 if(i==0){
					 $('#txtProIdInit').val(proName)
					 $('#txtProInitVal').val(val)
					 $('#setAssg').val(type)
					 if(type==2){
						 txtProCarrVal =$(o).attr('txtProCarrVal');
						 var h=''
						 h+='<td class="newTD"><input type="number" min="0" id="txtProCarrVal" name="txtProCarrVal" placeholder="上月结转"';
						 h+='class="easyui-validatebox" style="width: 160px" maxlength="100" value="'+txtProCarrVal+'"/></td>';
						 $('#trHM').append(h)
					 }
				 }else if(i>=1){
					 trHMmax++;
					 var h='';
					 h+='<tr class="trHMs selVal" id="'+i+'">';
						h+='<td><select id="txtProIdInit'+i+'" name="txtProIdInit" class="combo-text validatebox-text checkVal" style="width:120px;"></select>'//proName
						h+='</td><td style="text-align: right">初始值：</td>'
						h+='<td><input type="number" min="0" id="txtProInitVal" name="txtProInitVal"class="easyui-validatebox" style="width: 160px" maxlength="100" value="'+val+'"/>'


						if(type==2){
							h+='<option value="0">本月套餐</option><option value="1">上月结转</option><option value="2" selected>套内与结转</option></select></td>'	
							h+='<td><a class="easyui-linkbutton 1-btn" onclick="removeHM('+i+')">删除</a></td>'
							h+='<td class="newTD"><input type="number" min="0" id="txtProCarrVal" name="txtProCarrVal" placeholder="上月结转"';
							h+='class="easyui-validatebox" style="width: 160px" maxlength="100" value="'+txtProCarrVal+'"/></td>';
						}else if(type==1){
							h+='<option value="0">本月套餐</option><option value="1" selected>上月结转</option><option value="2">套内与结转</option></select></td>'	
							h+='<td><a class="easyui-linkbutton 1-btn" onclick="removeHM('+i+')">删除</a></td>'
						}else if(type==0){
							h+='<option value="0"  selected>本月套餐</option><option value="1">上月结转</option><option value="2">套内与结转</option></select></td>'	
								h+='<td><a class="easyui-linkbutton 1-btn" onclick="removeHM('+i+')">删除</a></td>'
							}
							h+='</tr>'
							$('#explorer tbody').append(h);
				 }
				 
		 })
	}else{
		 //系统选择
		 //此处必须重新产品包，否则无法达到更新数据的效果
		 var params ={uCUserID:trans($('#uCUserID').val())};
		 $.ajax({
				type:"POST",
				url:"useCase/getBillProduct",
				dataType:"json",
				data:params,
				success:function(data){
					//从加载取当前用例的所有产品包
					if(data.type==0){
						 var productList = data.productList;
						var h ='<tr ><td colspan="5">产品包内容</td></tr>';
						for(var i =0;i<productList.length;i++){
							h+='<tr class="loadLine"><td><input type="checkbox" onclick="setProduct('+i+')" id="cbPro'+i+'" name="productName" value="'+i+'">产品'+i+'：</td>';
							h+='<td colspan="4"><div style="display:none;" id="dProduct'+i+'">';
							h+='<span><input type="checkbox" id="cbUP'+i+'" name="" > 上月结转 <input type="number" min="0" id="txtUP'+i+'" data="上月结转"></span>';
							h+='<span> <input type="checkbox" id="cbDown'+i+'"> 本月套内  <input type="number" min="0" id="txtDown'+i+'" data="本月套内"></span>';
							h+='</div></td></tr>';
						}
					//从加载取当前用例的所有产品包
				$("#tbProduct").html(h);
				$("#trHM").hide();
			     $("#trChoice").show();
			     //对该用例产品包进行赋值
			     $.each(dataJson, function(idx, v) {
			    	 var proName = v.proName;//产品标识
					 var type = v.type;//预设类型 固定为 上月结转和本月套内
					 var val = v.val;
					 $('#cbPro'+proName+'').attr('checked','checked');
					 setProduct(proName);
					 if(type=='上月结转'){
						 $('#cbUP'+proName+'').attr('checked','checked');
						 $('#txtUP'+proName+'').val(val);
					 }else if(type=='本月套内'){
						 $('#cbDown'+proName+'').attr('checked','checked');
						 $('#txtDown'+proName+'').val(val);
					 }else{
						 
					 }
			     });
			     //对该用例产品包进行赋值
			     
			 }
				}	
			});
	}
}
 //累积量 暂时不做验证
/**
 * 修改时初始化累积量赋值，累积量非必填因此可能为空需要做验证
*/
function accumulateJsonInit(){
	var accumulateJson = trans($("#accumulateJson").val());
	
	if(!isNull(accumulateJson)){
		accumulateJson = JSON.parse(accumulateJson.replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号
		var item=0;
		$("#cbAccumulate").attr('checked','checked');
		$("#twoSubject").show();
		 $.each(data, function(idx, v) {
			 var fieldName = v.fileName;
			 var val = v.val;
			 if(fieldName=='总累积量'){
				 //字段（总累积量）为固定内容
				 $("#totalAccumulate").val(val);
			 }else if(fieldName=='金额'){
				 //字段（金额）为固定内容
				 $("#totalAccumulateValue").val(val);
			 }else{
				 if(item==0){
					 //如果item为零，表示初始化累积量中的产品累积只有一个
					 $('#proAccumulate'+item).find("input[name='proID']" ).val(fieldName);
					 $('#proAccumulate'+item).find("input[name='initValue']" ).val(val);
				 }else{
					 //处理多个产品累积量
					 var h='<tr id="proAccumulate'+item+'" class="proAccumulate">';
					 h+='<th><font color="red">*</font>产品累积量：</th>';
					 h+='<td style="text-align: right">产品ID：</td>';
					 h+='<td><input id="" data="产品累积量" name="proID" value="'+fieldName+'" class="easyui-validatebox validatebox-text" style="width: 260px" maxlength="100" type="type"></td>';
					 h+='<td style="text-align: right">初始值：</td>';
					 h+='<td colspan="2">';
					 h+='<input min="0" id="" value="'+val+'" name="initValue" class="easyui-validatebox validatebox-text" style="width: 260px" maxlength="100" type="number">';
					 h+='</td>';
					 h+='</tr>';
					 $("#twoSubject").append(h);
				 }
				 item++;
			 }			 
		 })
		 $("#addProAccumulate").attr('onclick','addProAccumulate('+(item-1)+')');//重置增加产品累积按钮参数
		
	}else{
		$('#tbExpAmount').hide();
		$('#tbExpAmount').prev().hide();
	}
}
 
/**
 * 修改时初始化话单模版模块赋值
 */
function billTemplateInit(){
	$("#dStepOne").css('display','none');
	$("#stepOnes").html('');
	$("#spbillName").val($("#billName").val()).show();
	$("#spbillNames").html("话单模板："+$("#billName").val()).show();
	//var json = JSON.parse($("#ticketJson").val().replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）
	var json = eval('(' + $("#ticketJson").val().replace(/＃/g, '"') + ')');
	var data = json.stepOnes;
	var h="";
	var f=false;
	var sTimes = '';
	 $.each(data, function(idx, v) { 
		 var fieldName = v.name;
		 var choiceAway =v.choiceaway;
		 var val = v.val;
		 var hide = v.hide;
		 var explain=v.explain;
		 var defaults=v.default;
		 //if(defaults==""){defaults==" "};
		 if(hide=="是"){
			 h+='<tr style="display:none" fieldName="'+fieldName+'" choiceAway="'+choiceAway+'" hide="'+
			 hide+'" explain="'+explain+'" default="'+defaults+'"><th>'+explain+'：</th><td colspan="6">';
			 if(choiceAway==12){
				 var sTime= v.sTime;
				 h+='<select name="sTime" value="'+sTime+'" id="sTime" choiceAway="'+choiceAway+'"  onchange="setSTime(this)" style="width: 120px;height: 24px;">'
					 h+='<option value="-1">--时间选取方式--</option>';
					 if(val==1){
						 h+='<option value="1" selected>填写</option>'; 
						 h+='<option value="2">选择</option>';
						 f = true;
						 sTimes =sTime;
					 }else if(val==2){
						 h+='<option value="1">填写</option>'; 
						 h+='<option value="2" selected>选择</option>';
					 }else{
						 h+='<option value="1">填写</option>'; 
						 h+='<option value="2">选择</option>';
					 }
			     h+='</select>';
			     h+=' <span id="spBillTime">'+sTime+'</span>';
			 }else{
				 h+='<input type="text" id="" choiceAway="'+choiceAway+'" value="'+val+'" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 333px" maxlength="100">';
			 }
			 
			 h+='</td></tr>';
		 }else{
		 h+='<tr fieldName="'+fieldName+'" choiceAway="'+choiceAway+'" hide="'+
		 hide+'" explain="'+explain+'" default="'+defaults+'"><th><font color="red">*</font>'+explain+'：</th><td colspan="6">';
		 if(choiceAway==12){
			 var sTime= v.sTime;
			 h+='<select name="sTime" id="sTime" value="'+sTime+'" choiceAway="'+choiceAway+'"  onchange="setSTime(this)" style="width: 120px;height: 24px;">'
				 h+='<option value="-1">--时间选取方式--</option>';
				 if(val==1){
					 h+='<option value="1" selected>填写</option>';
					 h+='<option value="2">选择</option>';
					 f = true;
					 sTimes =sTime;
				 }else if(val==2){
					 h+='<option value="1">填写</option>'; 
					 h+='<option value="2" selected>选择</option>';
				 }else{
					 h+='<option value="1">填写</option>'; 
					 h+='<option value="2">选择</option>';
				 }
			 
		     h+='</select>';
		     h+=' <span id="spBillTime">'+sTime+'</span>';
		    
		 }else{
			 h+='<input type="text" id="" choiceAway="'+choiceAway+'" value="'+val+'" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 333px" maxlength="100">';
		 }
		 
		 h+='</td></tr>';
		 }
	 });
	 $("#stepOnes").append(h);
	 if(f){
		 $("#spBillTime").html('<input id="txtBillTime" style="width:80px;"> ');
			$('#txtBillTime').timespinner({
			    showSeconds: true
		  });
		  $("#spBillTime").show();
		  $('#txtBillTime').timespinner('setValue', sTimes);
	 }
}

/**
 * 多选删除
 * @returns {Boolean}
 */
function removeMethod(){
	var ids='';
	$("input[name='id']").each(function(i,o){
		if($(this).is(':checked')){
			ids+=$(this).val()+',';
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
	$.messager.confirm('谨慎操作提示', '确认删除任务用例?', function(r){
		if (r){
			ajaxRequest("useCase/deleteAll", {ids:ids});
		}
	});
}

function copyMethod(){
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
		$.messager.alert('温馨提示','请选择需要复制的用例','',function(){
		});
		return false;
	}
	$.messager.confirm('谨慎操作提示', '确认执行所选用例', function(r){
		
		if (r){			
			$('#win').window('open');
			$("#ylIds").val(ids);
		}
	});
}

//确认复制
function sureCopy(){
	var id=$("#ylIds").val();
	var taskgroupId=$("#cpIds").val();
	if(taskgroupId==''){
		$.messager.alert('提示','请选择产品组信息！');    
	}else{
		$.ajax({
			type:"POST",
			url:"useCase/copyMsg?id="+id+"&taskgroupId="+taskgroupId,
			success:function(data){
				$.messager.alert('温馨提示',data.msg,'',function(){
					//closeWin();
					location.reload();
				});
			}
		});
	}
}

/*function closeWin(){
	$('#win').window('close');
	$("#tt").tree("reload");
	$("#cpIds").val('');
	$("#dg").datagrid("reload");
	$("#dg").datagrid({pageNumber:1});
	$('#dg').datagrid('clearSelections');
}*/

function ajaxRequestSuccessBackInvokeMethod(data) {
	//$.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

//全选
function checkAll(){
	$("input[name='id']").attr("checked",'true');
}

function delProductLis(){
	var id=$("#task_group_id").val();
	if(isNull(id)){
		$.messager.alert('温馨提示','请选择需要删除的记录','',function(){
		});
		return false;
	}else{
		$.messager.confirm('谨慎操作提示', '确认删除产品组?', function(r){
			if (r){
				ajaxRequest("userCaseTreeController/deleteProductGroupToTask", {id:id}); 
				$('#tt').tree("reload");
			}
		});
	}
}
function saveMethod(){
	var billType=$('#billType').val();
	var group_id=$('#group_id').val();
	var id=$("#id").val();
	if(!checkSubject()){
		return false;
	}//用例信息验证
	if(!checkStepOne()){
		return false;
	}//验证话单信息
	if(!checkStepTwo()){
		return false;
	}//初始化免费资源
	if(!checkUCExpect()){
		return false;
	}
	if(!checkUCExpAmount()){
		return false;
	}
	if(!checkUCExpDetail()){
		return false;
	}
	if($("#resourceJson").val()){
		var resourceJson = JSON.parse($("#resourceJson").val());
		resourceJson.proAway = parseInt(resourceJson.proAway);
		resourceJson.pro = [];
		var pronArr = [];
		var sel = document.getElementsByClassName("sel");
		for(var i = 0; i<sel.length;i++){
			var params = {proName :"",val:"",proAway:proAways,type:"",status:1};
			resourceJson.pro.push(params);
		}
		 $(".selVal>td>span .combo-value").each(function(index){
			 resourceJson.pro[index].proName =$(this).val();
			 pronArr.push($(this).val());
			  });
		 $("input[name='txtProInitVal']").each(function(index){
			 resourceJson.pro[index].val = parseInt($(this).val());
			  });
		 $("select[name='setAssg'] option:selected").each(function(index){
			 resourceJson.pro[index].type = parseInt($(this).val())
		 })
		 $(".selVal").each(function(index,o){
			 resourceJson.pro[index].productName = $(o).find(".combo-text").combobox("getText");
			 console.log($(o).find(".combo-text").combobox("getText"));
		 })
		 var a = pron;  
	     var b = pronArr;  
	     var tmp = a.concat(b);  
	     var o = {};  
	     for (var i = 0; i < tmp.length; i ++) (tmp[i] in o) ? o[tmp[i]] ++ : o[tmp[i]] = 1;  
	     for (x in o) if (o[x] == 1) resourceJson.pro.push({proName:x,val:0,proAway:proAways,type:0,status:0});
		 $("#resourceJson").val(JSON.stringify(resourceJson));
	}
	$("#addOrUpateForm").submit();
}