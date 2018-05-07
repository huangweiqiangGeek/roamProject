function searchMethod() {
	var commTypeName = $('#commTypeName').val();
	var affiliationType = $('#affiliationType').val();
	var params = {
		commTypeName : commTypeName,
		affiliationType : affiliationType
	};
	$("#dg").datagrid("reload", params);
}

function ajaxRequestSuccessBackInvokeMethod(data) {
	// $.messager.alert('操作提示','人员删除成功!');
	$("#dg").datagrid("reload");
}

function formatOperate(value, rowDatas, rowIndex) {
	var h = '<a href="javascript:editMethod(' + value + ')" class="btn handleBtn">修改</a>&nbsp;&nbsp;'
			h+= '<a href="javascript:removeMethod(' + value
			h+= ')" class="btn handleBtn">删除</a>&nbsp;&nbsp;';
			//h+= '<span> id:'+ value +'</span>'
	return h;
}
function formataffiliationType(value, rowDatas, rowIndex) {
	if (value == 1) {
		return "话单模版状态";
	} else if (value == 2) {
		return "预设结果模版状态";
	} else if (value == 3) {
		return "项目状态";
	} else if (value == 4) {
		return "用例状态";
	} else if (value == 5) {
		return "执行结果状态";
	} else if (value == 6) {
		return "选择方式状态";
	} else if (value == 7) {
		return "用例执行状态";
	}else if(value==8){
		return "预设结果模版类型";
	}
	return '--';
}
function addMethod() {
	showWin("commType/addInit", "新增状态\类型管理", 800, 450);
}

function editMethod(user_id) {
	showWin("commType/updateInit?id=" + user_id, "修改新增状态\类型", 750, 450);
}

function removeMethod(user_id) {
	$.messager.confirm('谨慎操作提示', '确认删除?', function(r) {
		if (r) {
			ajaxRequest("commType/delete", {
				id : user_id
			});
		}
	});
}