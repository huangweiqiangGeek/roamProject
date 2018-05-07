function saveMethod(){
	if(!checkUCExpect()){
		return false;
	}
	if(!checkUCExpAmount()){
		return false;
	}
	if(!checkUCExpDetail()){
		return false;
	}
	$("#addOrUpateForm").submit();
}

/**
 * 下一步验证，
 obj：
 0:话单主体时点击下一步
 1：第一步骤时点击下一步
 2：第二步骤时点击下一步
 */
function forNext(obj){
	if(obj==0){
		if(!checkSubject()){
		 return false;
		}
		$("#aPre").show();
		$("#aNext").attr('onclick',"forNext("+(obj+1)+")");
		$("#dStepOne").show();
		$("#dSubject").hide();
	}else if(obj==1){
		if(!checkStepOne()){
			return false;
		}
		$("#aPre").show();
		$("#aNext").attr('onclick',"forNext("+(obj+1)+")");
		$("#aPre").attr('onclick',"forPre("+(obj)+")");
		$("#dStepTwo").show();
		$("#dStepOne").hide();
	}else if(obj==2){
		if(!checkStepTwo()){
			return false;
		}
		loadExpAmount();
		loadExpect();
		$("#aSave").show();
		$("#aNext").hide();
		$("#dstepThree").show();
		$("#dStepTwo").hide();
		$("#aPre").attr('onclick',"forPre("+(obj)+")");
	}
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
function checkSubject(){
	var uCName =trans($('#uCName').val());
	if(isNull(uCName)){
		$.messager.alert('温馨提示','请输入用例名称','',function(){
			$("#uCName").focus();
		});
		return false;
	}
	var uCNumber =trans($('#uCNumber').val());
	if(isNull(uCNumber)){
		$.messager.alert('温馨提示','请输入用例编号','',function(){
			$("#uCNumber").focus();
		});
		return false;
	}
	var uCScene =trans($('#uCScene').val());
	if(isNull(uCScene)){
		$.messager.alert('温馨提示','请输入使用场景','',function(){
			$("#uCScene").focus();
		});
		return false;
	}
	var uCUserID =trans($('#uCUserID').val());
	if(isNull(uCUserID)){
		$.messager.alert('温馨提示','请输入话单归属用户标识','',function(){
			$("#uCUserID").focus();
		});
		return false;
	}
	return true;
}

/**
 * 验证第一步骤，话单信息是否完善
 */
function checkStepOne(){
	var f=true;
	//{"stepOnes":[{"name":"开始时间","choiceaway":"12","val":"123","sTime":"123"},{"name":"话单编号","choiceaway":"11","val":"123"}]}
	var json ='{"stepOnes":[';
	var billName = trans($('#billName').val());
	if(isNull(billName)){
		$.messager.alert('温馨提示','请选择模版','',function(){
		});
		f = false;
		return false;
	}
	$("#stepOnes tr").each(function(i,o){
		var choiceaway=$(o).attr('choiceaway');
		var fieldName = $(o).attr('fieldName');
		if(choiceaway==12){
			var sTime =$(o).find("select[name='sTime']").val();
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
			    BillTime =$("#spBillTime").html();
			}
			if(isNull(BillTime)){
				$.messager.alert('温馨提示','请输入时分','',function(){
				});
				f = false;
				return false;
			}
			json+='{"name":"'+fieldName+'","choiceaway":"'+choiceaway+'","val":"'+sTime+'","sTime":"'+BillTime+'"}';
			if((i+1)!=$("#stepOnes tr").length){
				json+=',';
			}
			
		}else{
			var fn = $(o).find("input[name='fieldName']").val();
			if(isNull(fn)){
				$.messager.alert('温馨提示','请输入'+fieldName,'',function(){
				});
				f = false;
				return false;
			}
			json+='{"name":"'+fieldName+'","choiceaway":"'+choiceaway+'","val":"'+fn+'"}';
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
/**
 * 验证第二步
 * @returns {Boolean}
 */
function checkStepTwo(){
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
	var item = $('#sProAway').val();
	if(item==1){
		if(isNull($('#txtProIdInit').val())){
			$.messager.alert('温馨提示','请输入初始化免费资源产品ID','',function(){
				$('#txtProIdInit').focus();
			});
			return false;
		}
		if(isNull($('#txtProInitVal').val())){
			$.messager.alert('温馨提示','请输入初始化免费资源产品ID初始值','',function(){
				$('#txtProInitVal').focus();
			});
			return false;
		}
		return true;
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
	//{"uCExpect":[{"name":"开始时间","type":"12","initVal":"123","val":"123"},{"name":"开始时间","type":"12","initVal":"123","val":"123"}]}
	var json ='{"uCExpect":[';
	$("#stepThree tr[class='uCExpect']").each(function(i,o){
		var v = trans($(o).find('input[type="number"]').val());
		var name = trans($(o).find('td').eq(0).html());
		var type = trans($(o).find('td').eq(1).html());
		var initVal = trans($(o).find('td').eq(2).html());
		if(isNull(v)){
			f=false;
			return false;
		}
		json+='{"name":"'+name+'","type":"'+type+'","initVal":"'+initVal+'","val":"'+v+'"}';
		if((i+1)!=$("#stepThree tr").length){
			json+=',';
		}
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
	$("#expectJson").val(json);
	return true;
}

/**
 * 步骤三中验证累积量结果
 */
function checkUCExpAmount(){
	var f = true;
	if($("#cbAccumulate").is(':checked')){
		//{"uCExpAmount":[{"name":"开始时间","initVal":"123","val":"123"},{"name":"开始时间","initVal":"123","val":"123"}]}
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
	var resultName = trans($('#resultName').val());
	if(isNull(resultName)){
		$.messager.alert('温馨提示','请选择详单预期结果','',function(){
		});
		return false;
	}
	var f = true;
	
	//{"expDetailJson":[{"tablename":"总累积量","fieldNames":"proId@23124545^proName@test"},{"tablename":"总累积量","fieldNames":"proId@23124545^proName@test"}]}
	var json='{"expDetailJson":[';
	$('#tbResultTemplate tr').each(function(i,o){
		var tableName = $(o).attr('tablename');
		var scripturl= $(o).attr('scripturl');
		var defaults= $(o).attr('defaults');
		var arrFieldName =  $(o).attr('fieldname').replace(/\，/g, ",").split(',');//字段之间逗号统一
		json+='{"tablename":"'+tableName+'",';
		json+='"scripturl":"'+scripturl+'",';
		json+='"defaults":"'+defaults+'",';
		var j="\"fieldNames\":\"";
		for(var x=0;x<arrFieldName.length;x++){
		  var vals=trans($(o).find('input[name="'+arrFieldName[x]+'"]').val());
		  if(isNull(vals)){
			  f=false;
			  return false;
		  }
		  j+=''+arrFieldName[x]+'@'+vals+'';
		   if((x+1)!=arrFieldName.length){
			   j+='^';
		   }
		 }
		j+='"';
		json+=j+'}';
		if((i+1)!=$('#tbResultTemplate tr').length){
			json+=',';
		}
		
	})
	if(!f){
		$.messager.alert('温馨提示','请完善详单预期结果中字段值设定',function(){
	    });
		return false;
	}
	json+=']}';
	$("#expDetailJson").val(json);
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
	showWin("billTemplate/queryListInit", "选择话单模版", 600, 450);
}

/**
 * 弹出查询话单时间条件页面
 */
function openBillTemplate(uCUserID,scripturl){
	showWin("regressionUseCase/billInit?scriptUrl="+scripturl+"&uCUserID="+uCUserID, "选择时间", 500, 250);
}


/**
 * 确定模版后加载选择的模版内容
 */
function configBillTemplate(){
	$("#stepOnes").html('');
	$("#spbillName").html("已选择话单模版："+$("#billName").val()).show();
	var json = JSON.parse($("#attrJson").val());
	var data = json.templateAttribute;
	var h="";
	 $.each(data, function(idx, v) {
		 var fieldName = v.fieldName;
		 var choiceAway =v.choiceAway;
		 var range = v.range;
		 var scriptUrl = v.scriptUrl;
		 h+='<tr fieldName="'+fieldName+'" choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scriptUrl+'"><th><font color="red">*</font>'+fieldName+'：</th><td colspan="6">';
		 if(choiceAway==12){
			 h+='<select name="sTime" id="sTime" choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scriptUrl+'" onchange="setSTime(this)" style="width: 120px;height: 24px;">'
				 h+='<option value="-1">--时间选取方式--</option>';
			 h+='<option value="1">填写</option>';
			 h+='<option value="2">选择</option>';
		     h+='</select>';
		     h+=' <span id="spBillTime"></span>';
		 }else{
			 h+='<input type="text" id="" choiceAway="'+choiceAway+'" range="'+range+'" scriptUrl="'+scriptUrl+'" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 260px" maxlength="20">';
		 }
		 
		 h+='</td></tr>';
	 });
	 $("#stepOnes").append(h);
	 

}


/**
 * 弹出详单预期结果模版选择
 */
function setResultTemplate(){
	showWin("resultTemplate/resultListInit", "选择预设结果模版", 800, 450);
}

/**
 * 加载选择模版
 */
function configResultTemplate(){
	$("#tbResultTemplate").html('');
	$("#spResultName").html("已选择预设结果模版："+$("#resultName").val()).show();
	var json = JSON.parse($("#resultAttrJson").val());
	var data = json.templateAttribute;
	var h="";
	 $.each(data, function(idx, v) {
		 var tableName = v.tableName;
		 var fieldName = v.fieldName.replace(/\，/g, ",");//字段之间逗号统一
		 var defaults =v.default;
		 var scriptUrl = v.scriptUrl;
		 h+='<tr tableName="'+tableName+'" fieldName="'+fieldName+'" defaults="'+defaults+'" scriptUrl="'+scriptUrl+'">';
		 h+='<td width="180" style="text-align: left;">'+tableName+'</td><td width="180" style="text-align: left;">';
		var arrFieldName =  fieldName.split(',');
		for(var i=0;i<arrFieldName.length;i++){
		 h+=''+arrFieldName[i]+':  <input type="text" name="'+arrFieldName[i]+'" >    ';
		}
		 h+='</td></tr>';
	 });
	 $("#tbResultTemplate").append(h);
	 

}


/**
 * 设定获取初始化免费资源方式
 */
function setProAway(obj){
	var item = $(obj).val();
	if(item==1){
		$("#trHM").show();
		$("#trChoice").hide();
	}else{
		var params ={
			uCUserID:trans($('#uCUserID').val())
			};
	$.ajax({
		type:"POST",
		url:"regressionUseCase/getBillProduct",
		dataType:"json",
		data:params,
		success:function(data){
			if(data.type==0){
				 var productList = data.productList;
				var h ='<tr ><td colspan="5">产品包内容	</td></tr>';
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
		    showSeconds: false
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
 * 加载初始化预期结果
 */
function loadExpAmount(){
	// {"ExpAmount":[{"fileName":"总累积量","val":"123"},{"fileName":"金额","val":"123"},{"fileName":"产品ID","val":"123"}]}
	if($("#cbAccumulate").is(':checked')){
		var json='{"ExpAmount":[';
		var h='<thead><tr>';
		h+='<th width="180" style="text-align: left;">累积量</th>';
		h+='<th width="180" style="text-align: left;">初始值</th>';
		h+='<th width="180" style="text-align: left;">预期值</th>';
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
function loadExpect(){
	//先获取第二步初始化免费资源的设定
	var sProAway =$('#sProAway').val();
	//拼接初始化资源填写内容JSON
	var json ='{"proAway":"'+sProAway+'","pro":';//{"pro":[{"proAway":"1","proName":"流量","type":"上月结转","val":"123"},{"proName":"流量","type":"上月结转","val":"123"}]}
	var h='<thead><tr><th  width="180" style="text-align: left;">产品</th><th  width="180" style="text-align: left;">类型</th>';
	//拼接为HTML
	h+='<th  width="180" style="text-align: left;">初始值</th><th  width="180" style="text-align: left;">预期值</th></tr></thead>'
	if(sProAway==1){
		json+='[{"proAway":"'+sProAway+'","proName":"'+$("#txtProIdInit").val()+'","type":"上月结转","val":"'+$('#txtProInitVal').val()+'"}]';
		h+='<tr class="uCExpect"><td>'+$("#txtProIdInit").val()+'</td><td>上月结转</td><td>'+$('#txtProInitVal').val()+'</td><td><input type="number" min="0"></td></tr>';
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
						h+='<tr class="uCExpect"><td>'+$(o).val()+'</td><td>'+$('#txtUP'+i).attr('data')+'</td><td>'+$('#txtUP'+i).val()+'</td><td><input type="number" min="0"></td></tr>';
					}
				}
				if($('#cbDown'+i).is(':checked')){
					if(!isNull($('#txtDown'+i).val())){
						if(isNull(j)){
							j+='{"proAway":"'+sProAway+'","proName":"'+$(o).val()+'","type":"'+$('#txtDown'+i).attr('data')+'","val":"'+$('#txtDown'+i).val()+'"},';
						}else{
							j+='{"proAway":"'+sProAway+'","proName":"'+$(o).val()+'","type":"'+$('#txtDown'+i).attr('data')+'","val":"'+$('#txtDown'+i).val()+'"}';
						}
						h+='<tr class="uCExpect"><td>'+$(o).val()+'</td><td>'+$('#txtDown'+i).attr('data')+'</td><td>'+$('#txtDown'+i).val()+'</td><td><input type="number" min="0"></td></tr>';
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
	billTemplateInit();
	accumulateJsonInit();
	resourceJsonInit();
	expDetailJsonInit();
	
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
		var arrFieldName = fieldNames.split('^');//proId@1
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
		 h+='<tr tableName="'+tablename+'" fieldName="'+fieldName+'" defaults="'+defaults+'" scripturl="'+scripturl+'">';
		 h+='<td width="180" style="text-align: left;">'+tablename+'</td><td width="180" style="text-align: left;">';
		for(var i=0;i<arrFieldName.length;i++){
			var arr = arrFieldName[i].split('@');
		   h+=''+arr[0]+':  <input type="text" name="'+arr[0]+'" value="'+arr[1]+'">    ';
		}
		 h+='</td></tr>';
		
	}) 
	$("#tbResultTemplate").html(h);
}


/**
 * 修改时加载 预期累积量结果赋值
 */
function expAmountJsonInit(){
	var expAmountJson = trans($("#expAmountJson").val());
	expAmountJson = JSON.parse(expAmountJson.replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）
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
	expectJson = JSON.parse(expectJson.replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）
	var dataJson= expectJson.uCExpect;
	$.each(dataJson, function(idx, v) {
		var val = v.val;
		$('#stepThree tbody tr').eq(idx).find("input[type='number']").val(val);
		
	})
	
}

/**
 * 修改时加载 初始化免费资源赋值
 */
function resourceJsonInit(){
	var resourceJson = trans($("#resourceJson").val());
	resourceJson = JSON.parse(resourceJson.replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）
	var dataJson = resourceJson.pro;//初始化免费资源初始化内容（"pro":[{"proAway":"1","proName":"流量","type":"上月结转","val":"123"},{"proName":"流量","type":"上月结转","val":"123"}]）
	var proAway = resourceJson.proAway;//初始化免费资源初始化方式（"proAway":"1"）
   $("#sProAway").val(proAway);
	if(proAway==1){
		 //初始化方式为手动填写
		 $("#trHM").show();
	     $("#trChoice").hide();
		 $("#txtProIdInit").val(proName);
		 $("#txtProInitVal").val(val);
	}else{
		 //系统选择
		 //此处必须重新产品包，否则无法达到更新数据的效果
		 var params ={uCUserID:trans($('#uCUserID').val())};
		 $.ajax({
				type:"POST",
				url:"regressionUseCase/getBillProduct",
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

/**
 * 修改时初始化累积量赋值，累积量非必填因此可能胃为空需要做验证
 */
function accumulateJsonInit(){
	var accumulateJson = trans($("#accumulateJson").val());
	if(!isNull(accumulateJson)){
		accumulateJson = JSON.parse(accumulateJson.replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）
		var data = accumulateJson.ExpAmount;
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
					 $('#proAccumulate'+item).find("input[name='initValue']" ).val(val)
				 }else{
					 //处理多个产品累积量
					 var h='<tr id="proAccumulate'+item+'" class="proAccumulate">';
					 h+='<th><font color="red">*</font>产品累积量：</th>';
					 h+='<td style="text-align: right">产品ID：</td>';
					 h+='<td><input id="" data="产品累积量" name="proID" value="'+fieldName+'" class="easyui-validatebox validatebox-text" style="width: 260px" maxlength="20" type="type"></td>';
					 h+='<td style="text-align: right">初始值：</td>';
					 h+='<td colspan="2">';
					 h+='<input min="0" id="" value="'+val+'" name="initValue" class="easyui-validatebox validatebox-text" style="width: 260px" maxlength="20" type="number">';
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
	$("#stepOnes").html('');
	$("#spbillName").html("已选择话单模版："+$("#billName").val()).show();
	var json = JSON.parse($("#ticketJson").val().replace(/＃/g, '"'));//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）
	 var data = json.stepOnes;
	var h="";
	var f=false;
	var sTimes = '';
	 $.each(data, function(idx, v) {
		 var fieldName = v.name;
		 var choiceAway =v.choiceaway;
		 var val = v.val;
		 h+='<tr fieldName="'+fieldName+'" choiceAway="'+choiceAway+'"><th><font color="red">*</font>'+fieldName+'：</th><td colspan="6">';
		 if(choiceAway==12){
			 var sTime= v.sTime;
			 h+='<select name="sTime" id="sTime" choiceAway="'+choiceAway+'"  onchange="setSTime(this)" style="width: 120px;height: 24px;">'
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
			 h+='<input type="text" id="" choiceAway="'+choiceAway+'" value="'+val+'" name="fieldName" class="easyui-validatebox validatebox-text" style="width: 260px" maxlength="20">';
		 }
		 
		 h+='</td></tr>';
	 });
	 $("#stepOnes").append(h);
	 if(f){
		 $("#spBillTime").html('<input id="txtBillTime" style="width:80px;"> ');
			$('#txtBillTime').timespinner({
			    showSeconds: false
		  });  
		  $("#spBillTime").show();
		  $('#txtBillTime').timespinner('setValue', sTimes);
	 }
	 
}