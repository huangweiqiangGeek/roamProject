$(document).ready(function() {

});

function validateMethod() {
	$("#addOrUpateForm").validate({
		rules: {
			province: {
				required: true
			},
			webservice_url: {
				required: true,
				isWebserviceUrl:true
			}
		}
	});


}

function saveMethod() {
	if ($("#addOrUpateForm").valid()) {
		$("#addOrUpateForm").submit();
	}
}

function saveMethod1() {
	validateMethod();
	if ($("#addOrUpateForm").valid()) {
		$.ajax({
			type: "post",
			url: "dccProvince/saveDccProvince",
			async: true,
			data: {
				id: $("#id").val(),
				province: $("#province").val(),
				webservice_url: $("#webservice_url").val()
			},
			cache: false,
			success: function(data) {
				if (data == 'success') {
					//					$.messager.alert('提示', '操作成功');
					window.parent.closeWin(true, "win_div");
				} else {
					//					$.messager.alert('提示', '操作失败');
				}
			}
		});
	}
}

function cancelMethod() {
	window.parent.closeWin(false, "win_div_1");
}