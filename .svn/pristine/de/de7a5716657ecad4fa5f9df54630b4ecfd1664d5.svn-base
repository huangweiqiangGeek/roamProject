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
	$('.sysPhone').text(uCUserID)
	var proName=$('#proName').val();
	if(isNull(proName)){
		$.messager.alert('温馨提示','请输入产品ID','',function(){
			$("#proName").focus();
		});
		return false;
	}
	var type=$('#type').val();
	$('#load').css('display','')
	$.ajax({
		type:"POST",
		url:"commConfig/testCfg2?uCUserID="+uCUserID+"&proName="+proName+"&type="+type,
		success:function(data){
			$('#load').css('display','')
			$('#formTable').css('display','none')
			$('#ContrastTable').css('display','')
			$('.finalResult').text(data.failReasion)
			$('.sysID').text(data.proName)
			if(type==0){
				var h='<td colspan="2">产品ID：'+data.proName+'</td>'
				h='<td>初始值：'+data.val+'</td>';
				$('#data').append(h)
			}else if(type==1){
				var h='<td colspan="2">产品ID：'+data.proName+'</td>'
				h+='<td>上月结转：'+data.txtProCarrVal+'</td>';
				$('#data').append(h)
			}else{
				var h='<td >产品ID：'+data.proName+'</td>'
				h+='<td>初始值：'+data.val+'</td>'
				h+='<td>上月结转：'+data.txtProCarrVal+'</td>';
				$('#data').append(h)
			}	
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

