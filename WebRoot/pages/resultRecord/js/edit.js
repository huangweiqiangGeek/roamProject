$(function(){
	updateInit();
});

function updateInit(){
	//按类型append数据   免费资源-累积量-详单结果   
	
	var resultResource = $("#resultResource").val().replace(/＃/g, '"');//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）;
	if(isNull(resultResource)){
		$('#tbResultResource').css('display','none')
		$('.tbResultResource').css('display','none')
	}else if(!isNull(resultResource)){
		var dataJson = JSON.parse(resultResource).uCExpect;
		var h='';
		var num=$('input[name="uCUserID"').val();
		var type=$('#billType').val();
		var unit;
		if(type==1){
			unit="分钟"
		}else if(type==2||type==4){
			unit="M"
		}else if(type==3){
			unit="条"
		}
		$.each(dataJson, function(idx, v) {
			h+='<tr><td>'+v.name+'</td>';
			h+='<td>'+num+'</td>';
			//h+='<td>'+v.type+'</td>';
			if(v.type==0){
				h+='<td>本月套餐</td>';
			}else if(v.type==1){
				h+='<td>上月结转</td>';
			}else{
				h+='<td>全部填写</td>';
			}

			if(v.type==2){
				h+='<td>'+v.initVal+unit+','+v.initCarrVal+unit+'</td>';
				h+='<td>'+v.val+unit+','+v.carryVal+unit+'</td>';
			}else{
				h+='<td>'+v.initVal+unit+'</td>';
				h+='<td>'+v.val+unit+'</td>';
			}
			//h+='<td>'+v.initVal+'</td>';
			//h+='<td>'+v.val+'</td>';
			//if(v.val!=v.realityVal||v.carryVal!=v.realityTextProCarrVal){
			if(v.val!=v.realityVal){	
				if(v.type==2){h+='<td><span style="color: red;">'+v.realityVal+unit+','+v.realityTextProCarrVal+unit+'</span></td></tr>'
					}else{
					h+='<td><span style="color: red;">'+v.realityVal+unit+'</span></td></tr>';
				}
				
			}else{
				if(v.type==2){
					h+='<td>'+v.realityVal+unit+','+v.realityTextProCarrVal+unit+'</td></tr>';
				}else{
					h+='<td>'+v.realityVal+unit+'</td></tr>';
				}
		
			}
			
		})
		$("#tbResultResource").append(h);//免费资源执行结果
		
	}
	
	/*
	
	//对上月结转进行修改
	if(!isNull(resultResource)){
		var dataJson = JSON.parse(resultResource).uCExpect;
		var h='';
		$.each(dataJson, function(idx, v) {
			h+='<tr><td>'+v.name+'</td>';
			h+='<td>'+v.type+'</td>';
			if(v.type==2){
				h+='<td>'+v.initVal+','+v.initCarrVal+'</td>';
				h+='<td>'+v.val+','+v.carryVal+'</td>';
				/*
				if(v.val!=v.realityVal||v.carryVal!=v.realityTextProCarrVal){
					h+='<td><span style="color: red;">'+v.realityVal+','+v.realityTextProCarrVal+'</span></td></tr>';
				}else{
					h+='<td>'+v.realityVal+','+v.realityTextProCarrVal'</td></tr>';
				}
			}else{
				h+='<td>'+v.initVal+'</td>';
				h+='<td>'+v.val+'</td>';
			}
			if(v.val!=v.realityVal){
				h+='<td><span style="color: red;">'+v.realityVal+'</span></td></tr>';
			}else{
				h+='<td>'+v.realityVal+'</td></tr>';
			}
			/*
			if(v.val!=v.realityVal){
				h+='<td><span style="color: red;">'+v.realityVal+'</span></td></tr>';
			}else{
				h+='<td>'+v.realityVal+'</td></tr>';
			}
			
		})
		$("#tbResultResource").append(h);//免费资源执行结果
		
	}
	*/
	var resultTotal=$("#resultTotal").val().replace(/＃/g, '"');//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）;
	if(isNull(resultTotal)){
		$('#dResultTotal').css('display','none')
	}else if(!isNull(resultTotal)){
		var dataJson = JSON.parse(resultTotal).uCExpAmount;
		var h='';
		$.each(dataJson, function(idx, v) {
			h+='<tr><td>'+v.name+'</td>';
			h+='<td>'+v.type+'</td>';
			h+='<td>'+v.val+'</td>';
			if(v.val!=v.realityVal){//实际值与预设值不一致
				h+='<td><span style="color: red;">'+v.realityVal+'</span></td></tr>';
			}else{
				h+='<td>'+v.realityVal+'</td></tr>';
			}
			
		})
		$("#tbResultTotal").append(h);//累积量执行结果
		$("#dResultTotal").show();
	}
	
	var resultDetail=$("#resultDetail").val().replace(/＃/g, '"');//将JSON字符窜中全角＃替换为"号（因为前端隐藏文本域控件负责JSON时可能会出现转义问题）;
	if(!isNull(resultDetail)){
		var dataJson = JSON.parse(resultDetail).expDetailJson;
		var h='';
		console.log(dataJson);
		$.each(dataJson, function(idx, v) {
		    var fieldNames= v.fieldNames;//proId@23431231|1^proName@流量叠加包|
		    var explain = v.explain;
		    var arrFieldNames  =fieldNames.split('^');//proId@23431231|1
		    var arrExplain =explain.split(',')
		    h+='<tr><td rowspan="'+arrFieldNames.length+'">'+v.tablename+'</td>';
		    for(var i=0;i<arrFieldNames.length;i++){
		    	 var arr = arrFieldNames[i].split('@');
		    	 var v = arr[1];
		    	 var vArray = v.split('|');
		    	 var val = vArray[0];
		    	 var realityVal = vArray[1];
		    	 if(val!=realityVal){
		    		 h+='<td>'+arr[0]+'</td><td>'+val+'</td><td><span style="color: red;">'+realityVal+'</span></td>';
		    	 }else{
		    		 h+='<td>'+arr[0]+'</td><td>'+val+'</td><td><span>'+realityVal+'</span></td>';
		    	 }
		    	 if(i+1!==arrFieldNames.length){
		    		 h+="</tr><tr>";
		    	 }else{
		    		 h+="</tr>";
		    	 }
		    }
		})
		$("#tbResultDetail").append(h);//详单执行结果
	}
}



