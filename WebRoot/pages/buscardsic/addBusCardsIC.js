$(document).ready(function() {
	$("#addForm").validate( {
		rules : {
			mediumNo : {
				required : true
			},
			start_date_in : {
				required : true,
				isDate : true
			},
			period_date : {
				required : true,
				number : true
			},
			customs_code : {
				required : true
			},
			plat_no : {
				required : true
			}
		}
	});
	commonGetCustoms("customs_code", 10016);

});

function saveMethod() {
	var start_date_in = $('#start_date_in').datebox('getValue');
	if (checkDate(start_date_in)) {

		if ($("#addForm").valid()) {
			$("#addForm").submit();
		}
	}
}

function checkDate(s) {
	var reg = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
	if (!s.match(reg)) {
		alert("请输入yyyy-mm-dd格式的日期");
		return false;
	} else {
		return true;
	}
}
function cancelMethod() {
	window.parent.closeDialog(false);
}
