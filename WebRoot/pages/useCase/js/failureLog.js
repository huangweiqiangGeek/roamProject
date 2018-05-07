$(function(){
	var id=trans($('#id').val());
	if(!isNull(id)){
		failure(id)
	}
})
function failure(id){
	$.ajax({
		type:"POST",
		url:"useCase/getById",
		data:"id="+id,
		success:function(data){
			var value=data.exceptionList;
			$(value).each(function(i,o){
				var succ='';
				var step="step"+$(o).attr('step');
				var msg=$(o).attr('exceptionMessage');
				var path=$(o).attr('exceptionTrace');
				if($(o).attr('success')==2){
					succ="不通过";
					var h='<td>'+succ+'</td><td><a href="javascript:showState()" class="easyui-linkbutton">查看异常路径</a></td>'
					h+='<td><a href="javascript:showMsg()" class="easyui-linkbutton">查看异常信息</a></td>'
					$('.state').append(path)
					$('.message').append(msg)
				}else if($(o).attr('success')==1){
					succ="通过";
					var h='<td>'+succ+'</td><td>无</td>'
					h+='<td>无</td>'
				}else if($(o).attr('success')==3){
					succ="未执行";
					var h='<td>'+succ+'</td><td>无</td>'
					h+='<td>无</td>'
				}
				var t=document.getElementById(step);
				$(t).append(h)
			})
		}
	})
}
function showState(){
	$('#showMessage').css('display','');
	$('.message').css('display','none');
	$('.state').css('display','');
}
function showMsg(){
	$('#showMessage').css('display','');
	$('.state').css('display','none');
	$('.message').css('display','');
}
function cancel(){
	$('#showMessage').css('display','none');
}