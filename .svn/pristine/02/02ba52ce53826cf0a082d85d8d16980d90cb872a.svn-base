var actionState="";
var setting = {
	view : {
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		selectedMulti : false
	},
	edit : {
		drag : {
			isCopy : false,
			isMove : true,
			prev : true,
			next : true,
			inner : true
		},
		enable : true,
		editNameSelectAll : true,
		showRemoveBtn : showRemoveBtn,
		showRenameBtn : showRenameBtn
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeDrag : beforeDrag,
		beforeDrop : beforeDrop,
		beforeEditName : beforeEditName,
		beforeRename : beforeRename,
		onRename : onRename,
		beforeRemove : beforeRemove,
		onRemove : onRemove,
		onNodeCreated: addSysMenu,//添加回调
		onClick : onClick
	}
};

function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	var treeNode = treeNodes[0];
	var order_no=0;
	var parent_id=0;
	if(moveType=='prev'){
		if(targetNode.id=='0'){
			return false;
		}else{
			order_no=targetNode.order_no-1;
			parent_id = targetNode.pId;
		}
	}else if(moveType=='next'){
		order_no=targetNode.order_no+1;
		parent_id = targetNode.pId;
		
	}else if(moveType=='inner'){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.transformToArray(targetNode);
		for(var i=0,size=nodes.length;i<size;i++){
			if(nodes[i].order_no>order_no){
				order_no = nodes[i].order_no;
			}
		}
		parent_id = targetNode.id;
	}
	actionState="drag";
	var params = {
			"id":treeNode.id,
			"parent_id":parent_id,
			"order_no":order_no
	};
	ajaxRequest("sysMenu/dragSysMenu",params);
}

function beforeDrag(treeId, treeNodes) {
	return true;
}

function beforeRemove(treeId, treeNode) {
	var message ='确认删除 菜单 --:'+treeNode.name;
	if(treeNode.isParent){
		message=message+"及其所有子菜单";
	}else{
		message=message+"";
	}
	message = message+"? 此操作可能使菜单对应功能不可用，请慎重确认！！！";
	return confirm(message);
}
function onRemove(e, treeId, treeNode) {
	actionState="delete";
	var params = {"id":treeNode.id};
	ajaxRequest("sysMenu/deleteSysMenu",params);
}
function beforeEditName(treeId, treeNode) {
	return true;
}
var treeNode_old_name = "";
function beforeRename(treeId, treeNode, newName) {
	if(treeNode.name!=newName){
		if(confirm("确认将菜单名称《"+treeNode.name+"》改为:"+newName+"？")){
			if (newName.length == 0) {
				alert("菜单名称不能为空.");
				return false;
			}else if (newName.length > 30) {
				alert("菜单名称过长.");
				return false;
			}else{
				actionState="updateName";
				var params = {
						"id":treeNode.id,
						"name":newName
				}
				ajaxRequest("sysMenu/renameSysMenu",params);
				$("#name").val(newName);
			}
		}else{
			treeNode_old_name = treeNode.name;
		}
	}
	return true;
}
function onRename(e, treeId, treeNode) {
	if(treeNode_old_name !=""){
		treeNode.name = treeNode_old_name;
	}
	return true;
}
function saveMethod(){
	if($('#id').val()==""){
		alert("请先选择菜单，单击菜单树菜单名称即选择菜单!");
	}else{
		actionState="updateSysMenu";
		var params = {
				"id":$('#id').val(),
				"name":$('#name').val(),
				"parent_id":$('#parent_id').val(),
				"order_no":$('#order_no').val(),
				"menu_type":$('#menu_type').val(),
				"state":$('#state').val(),
				"href":$('#href').val()
		}
		ajaxRequest("sysMenu/updateSysMenu",params);
	}
}

function showRemoveBtn(treeId, treeNode) {
	return true;//!treeNode.isFirstNode;
}
function showRenameBtn(treeId, treeNode) {
	return true;//!treeNode.isLastNode;
}
var newCount = 1;
var addState = false;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0)
		return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.id + "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_" + treeNode.id);
	if (btn)
		btn.bind("click", function() {
			addState = true;
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.addNodes(treeNode, {
				id : (100 + newCount),
				pId : treeNode.id,
				name : "新增菜单" + (newCount++)
			});
			return false;
		});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.id).unbind().remove();
};
function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
}
function onClick(event, treeId, treeNode, clickFlag) {

	$('#t_id').val(treeNode.tId);
	$('#id').val(treeNode.id);
	$('#name').val(treeNode.name);
	$('#menu_type').val(treeNode.menu_type);
	$('#parent_id').val(treeNode.pId);
	$('#order_no').val(treeNode.order_no);
	$('#state').val(treeNode.state);
	$('#href').val(treeNode.href);
	if(treeNode.menu_type=="0"){
		$('#href').attr("disabled","disabled");
	}else{
		$('#href').attr("disabled","");
	}
}
var currentMenuHref;
function changeMenu_type(){
	var menu_type = $('#menu_type').val();
	if(menu_type=="0"){
		$('#href').attr("disabled","disabled");
		currentMenuHref = $('#href').val();
		$('#href').val("");
	}else{
		$('#href').attr("disabled","");
		$('#href').val(currentMenuHref);
	}
	
}
function addSysMenu(event, treeId, treeNode) {
	actionState="addSysMenu";
	if(addState){
		addState = false;
		actionState="addSysMenu";
		var order_no = treeNode.tId.split("_")[1];
		var params = {
				"name":treeNode.name,
				"parent_id":treeNode.pId,
				"order_no":order_no
		}
	    ajaxRequest("sysMenu/addSysMenu",params);
	}
};
function ajaxRequestSuccessBackInvokeMethod(data) {
	if(actionState==""){
		$.fn.zTree.init($("#treeDemo"), setting, data);
		$("#selectAll").bind("click", selectAll);
		zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
		zTree_Menu.expandAll(true);
	}else if(actionState=="drag"){
		alert("移动菜单成功!");
		window.location.reload(true);
	}else if(actionState=="updateName"){
		alert("修改菜单名称成功!");
	}else if(actionState=="delete"){
		alert("删除菜单成功!");
	}else if(actionState=="updateSysMenu"){
		alert("修改菜单成功!");
		window.location.reload(true);
	}else if(actionState=="addSysMenu"){
		alert("添加菜单成功!");
		window.location.reload(true);
	}
}
function ajaxRequestErrorBackInvokeMethod(event, XMLHttpRequest) {
	alert("操作失败,请刷新页面，以免与真实数据不符!");
}
$(document).ready(function(){
	ajaxRequest("sysMenu/querySysMenuList", {});
	$("#treeDemo").css("height",document.documentElement.offsetHeight-25);
});