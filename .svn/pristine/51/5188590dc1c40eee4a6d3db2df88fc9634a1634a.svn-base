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
			enable : true,
			idKey: "id",
			pIdKey: "parent_id",
			rootPId: 0
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
		onNodeCreated: addSysOrg,//添加回调
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
	ajaxRequest("sysOrg/dragSysOrg",params);
}

function beforeDrag(treeId, treeNodes) {
	return true;
}

function beforeRemove(treeId, treeNode) {
	var message ='确认删除 组织:'+treeNode.name;
	if(treeNode.isParent){
		message=message+"，及其所有子组织及组织下的人员、岗位信息";
	}else{
		message=message+"，及组织下的所有人员、岗位信息";
	}
	message = message+"? 此操作会清空组织及组织下人员、岗位等信息，请慎重确认！！！";
	return confirm(message);
}
function onRemove(e, treeId, treeNode) {
	actionState="delete";
	var params = {"id":treeNode.id};
	ajaxRequest("sysOrg/deleteSysOrg",params);
}
function beforeEditName(treeId, treeNode) {
	return true;
}
var treeNode_old_name = "";
function beforeRename(treeId, treeNode, newName) {
	treeNode_old_name = "";
	if(treeNode.name!=newName){
		if(confirm("确认将组织名称《"+treeNode.name+"》改为:"+newName+"？")){
			if (newName.length == 0) {
				alert("组织名称不能为空.");
				return false;
			}else if (newName.length > 30) {
				alert("组织名称过长.");
				return false;
			}else{
				actionState="updateName";
				var params = {
						"id":treeNode.id,
						"name":newName
				}
				ajaxRequest("sysOrg/renameSysOrg",params);
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
		alert("请先选择组织，单击组织树组织名称即选择组织!");
	}else{
		actionState="updateSysOrg";
		var params = {
				"id":$('#id').val(),
				"name":$('#name').val(),
				"code":$('#code').val(),
				"org_type":$('#org_type').val(),
				"business_desc":$('#business_desc').val(),
				"address":$('#address').val(),
				"zone_code":$('#zone_code').val(),
				"telephone":$('#telephone').val(),
				"zipcode":$('#zipcode').val(),
				"email":$('#email').val(),
				"leader_name":$('#leader_name').val(),
				"leader_mobile":$('#leader_mobile').val(),
				"contacter_name":$('#contacter_name').val(),
				"contacter_mobile":$('#contacter_mobile').val(),
				"remark":$('#remark').val(),
				"parent_id":$('#parent_id').val(),
				"order_no":$('#order_no').val()
		}
		ajaxRequest("sysOrg/updateSysOrg",params);
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
				name : "新增组织" + (newCount++)
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
	$('#code').val(treeNode.code);
	$('#org_type').val(treeNode.org_type);
	$('#business_desc').val(treeNode.business_desc);
	$('#address').val(treeNode.address);
	$('#zone_code').val(treeNode.zone_code);
	$('#telephone').val(treeNode.telephone);
	$('#zipcode').val(treeNode.zipcode);
	$('#email').val(treeNode.email);
	$('#code').val(treeNode.code);
	$('#leader_name').val(treeNode.leader_name);
	$('#leader_mobile').val(treeNode.leader_mobile);
	$('#contacter_name').val(treeNode.contacter_name);
	$('#contacter_mobile').val(treeNode.contacter_mobile);
	$('#remark').val(treeNode.remark);
	$('#parent_id').val(treeNode.parent_id);
	$('#order_no').val(treeNode.order_no);
	
	document.getElementById("postFrame").src="sysOrg/sysOrgPostManage?id="+treeNode.id;
}

function addSysOrg(event, treeId, treeNode) {
	if(addState){
		addState=false;
		actionState="addSysOrg";
		var order_no = treeNode.tId.split("_")[1];
		var params = {
				"name":treeNode.name,
				"parent_id":treeNode.pId,
				"order_no":order_no
		}
	    ajaxRequest("sysOrg/addSysOrg",params);
	}
};
function ajaxRequestSuccessBackInvokeMethod(data) {
	if(actionState==""){
		$.fn.zTree.init($("#treeDemo"), setting, data);
		$("#selectAll").bind("click", selectAll);
		zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
		zTree_Menu.expandAll(true);
	}else if(actionState=="drag"){
		alert("移动组织成功!");
		window.location.reload(true);
	}else if(actionState=="updateName"){
		alert("修改组织名称成功!");
	}else if(actionState=="delete"){
		alert("删除组织成功!");
	}else if(actionState=="updateSysOrg"){
		alert("修改组织成功!");
		window.location.reload(true);
	}else if(actionState=="addSysOrg"){
		alert("添加组织成功!");
		window.location.reload(true);
	}
}

function ajaxRequestErrorBackInvokeMethod(event, XMLHttpRequest) {
	alert("操作失败,请刷新页面，以免与真实数据不符!");
}
$(document).ready(function(){
	ajaxRequest("sysOrg/querySysOrgList", {});
	$("#treeDemo").css("height",document.documentElement.offsetHeight-25);
});