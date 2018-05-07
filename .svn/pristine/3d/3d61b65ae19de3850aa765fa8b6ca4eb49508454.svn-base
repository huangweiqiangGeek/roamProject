/*
 * 
 * 根据赋值方式显示不同赋值框    新增
 * 
 * 
 */

function setAssignment(obj){
	var item =$(obj).val();
	var h;
	if(item==2){
		$('.newTD').css('display','');
	}else{
		$('.newTD').css('display','none');
	}
}
/*
 * 
 * 验证数据是否完整
 * 
 * 
 */
function saveMethod(){
	if(!checkPhone()){//手机号码验证
		 return false;
		}
	var uCUserID=$('#uCUserID').val();
	$('.conPhone').text(uCUserID)
	var proName=$('#proName').val();
	if(isNull(proName)){
		$.messager.alert('温馨提示','请输入产品ID','',function(){
			$("#proName").focus();
		});
		return false;
	}
	$('.conID').text(proName)
	var val=$('#val').val();
	if(isNull(val)){
		$.messager.alert('温馨提示','请输入产品初始值','',function(){
			$("#val").focus();
		});
		return false;
	}
	$('.conVal').text(val)
	var type=$('#type').val();
	if(isNull(type)){
		$.messager.alert('温馨提示','请输入产品初始值','',function(){
			$("#val").focus();
		});
		return false;
	}
	var txtProCarrVal='';
	if(type==2){
		txtProCarrVal=$('#txtProCarrVal').val();
	}
	$('.conProCarrVal').text(txtProCarrVal);
	$('#load').css('display','')
	$.ajax({
		type:"POST",
		url:"commConfig/testCfg?uCUserID="+uCUserID+"&proName="+proName+"&type="+type+"&val="+val+"&txtProCarrVal="+txtProCarrVal,
		success:function(data){
			$('#load').css('display','none')
			$('#formTable').css('display','none')
			$('#ContrastTable').css('display','')
			if(data.success==true){
				$('.finalResult').text("免费资源设置成功")
			}else if(data.success==false){
				$('.finalResult').text("免费资源设置失败")
			}
			$('.conFailReasion').text(data.failReasion)
			$('.sysID').text(data.proName)
			$('.sysVal').text(data.val)
			$('.sysProCarrVal').text(data.txtProCarrVal)
		}	
	});
}
function checkPhone(){
	var uCUserID=$('#uCUserID').val();
	var reg=/^1(3|4|5|7|8)\d{9}$/;
	if(isNull(uCUserID)){
		$.messager.alert('温馨提示','请输入用户号码','',function(){
			$("#uCUserID").focus();
		});
		return false;
	} 
	if(!reg.test(uCUserID)){
		$.messager.alert('温馨提示','手机号码输入错误！','',function(){
			$("#uCUserID").focus();
		});
		return false;
	}
	return true;
}
function cancelMethod(){
	window.parent.closeWin(false,"winDiv_1");
}

