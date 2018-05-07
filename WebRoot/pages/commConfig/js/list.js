function searchMethod() {
	var provinceName = trans($('#provinceName').val());
	var ipAddress = trans($('#ipAddress').val());
	var hostName = trans($('#hostName').val());
	var params = {
			provinceName :provinceName,
			ipAddress : ipAddress,
			hostName : hostName
	};
	$("#dg").datagrid("reload", params);
}

function ajaxRequestSuccessBackInvokeMethod(data) {
	$("#dg").datagrid("reload");
}

function formatOperate(value, rowDatas, rowIndex) {
	return  "<a class=' btn handleBtn '  href='javascript:editMethod("+value+") ' "+ " >修改</a>&nbsp;&nbsp;"
}
//新增页面
function addMethod() {
	showWin("commConfig/addShellInit", "脚本配置管理", 800, 450);
	$('#insert').css('display','none')
}
function addMethodOne() {
	showWin("commConfig/addPathInit", "环境路径管理", 800, 450);
	$('#insert').css('display','none')
}
function addMethodTwo() {
	showWin("commConfig/addFileInit", "环境文件管理", 800, 450);
	$('#insert').css('display','none')
}
function addOther() {
	showWin("commConfig/addOthers", "其他设置管理", 800, 450);
	$('#insert').css('display','none')
}
//修改
function editMethod(user_id) {
	showWin("commConfig/updateInit?id=" + user_id, "脚本配置管理", 750, 450);
}

function setResour(){
	showWin("commConfig/testCftInit", "免费资源设置测试", 750, 450);
	$('#test').css('display','none')
}
function setResour2(){
	showWin("commConfig/gainResour", "免费资源获取测试", 750, 450);
	$('#test').css('display','none')
}
function testNet(){
	showWin("commConfig/toLinkTest", "网络连接测试", 400, 250);
	$('#test').css('display','none')
}
/*
function removeMethod(user_id) {
	$.messager.confirm('谨慎操作提示', '确认删除?', function(r) {
		if (r) {
			ajaxRequest("commConfig/delete", {
				id : user_id
			});
		}
	});
}*/
function addinsert(){
	$('#insert').css('display','block');
	$('#test').css('display','none')
}

function addtest(){
	$('#test').css('display','block')
	$('#insert').css('display','none')
}


