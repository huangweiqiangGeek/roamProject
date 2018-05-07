var curMenu = null, zTree_Menu = null;
var setting = {
	view : {
		showLine : true,
		selectedMulti : false,
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onNodeCreated : this.onNodeCreated,
		beforeClick : this.beforeClick,
		onClick : this.onClick
	}
};

function beforeClick(treeId, node) {
	return true;
}
function onClick(e, treeId, node) {
	if (node.menu_type == '1') {
		window.parent.document.getElementById("mainFrame").src=node.href;
	}else{
		zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
//		zTree_Menu.expandAll(false);
		zTree_Menu.expandNode(node);
	}
	return false;
}

function ajaxRequestSuccessBackInvokeMethod(data) {
	$.fn.zTree.init($("#treeDemo"), setting, data);
	zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree_Menu.getNodes();
	//zTree_Menu.expandAll(true);
	zTree_Menu.expandNode(nodes[0]);
}
function ajaxRequestErrorBackInvokeMethod(event, XMLHttpRequest) {
	alert("菜单生成失败，请刷新页面，还有问题请与管理员联系!");
}
$(document).ready(function(){
	var bodyHeight = document.documentElement.offsetHeight;
	var allMainPanelHeight = bodyHeight-13;
	$("#treeDemo").css("height",allMainPanelHeight+"px");
	ajaxRequest("sysMenu/queryByUserLimitsMenu", {});
});