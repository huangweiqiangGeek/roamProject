<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>产品列表</title>
<link rel="stylesheet" type="text/css" href="framework/css/allStyle.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/plugins/jquery.tree.loader.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/productGroup/js/list.js?1226_8"></script>
<style type="text/css">
	#aSave{
		margin:0 30px;
	}
	
	.show_dailog{
		position:absolute;
		width: 788.021px;
	    left: 230px;
	    top: 26px;
	    z-index: 9005;
	}
	
	.dailog_body{
		overflow: hidden;
	    width: 786.042px;
	    height: 444.031px;
	    background-color : white;
	    background :white;
	}
	
	.cp_name{
		margin-left:40px;
	    font-size: 18px;
	    width:80%;
	    margin-bottom:30px;
    }
    
    .cp_name span{font-size: 18px;}
    .cp_content{
    	width:43%;
    	margin-left:6%;
    	display:inline-flex;
    	margin-bottom:30px;
    }
    .cp_content:nth-child(odd){
    	width:45%;
    	margin-left:4%;
    }
    
    .cp_content label{
    	float:left;
    	min-width:100px; 
		width: auto !important; 
		width: 100px; 
    	font-size: 17px;
    	line-height:26px;
    }
    
    .cp_content ol{
    	float: left;
	    margin: 0;
	    padding: 0;
	    line-height:26px;
	    max-width:200px; 
		width: auto !important; 
		width: 200px; 
    }
    
    .cp_content ol li{
    	display:inline-block;
    	margin-right:20px;
    	font-size: 17px;
    	color:red;
    }
    
    .cp_content ol.num_ol li{
    	display:block;
    	width:210px;
    }
    
    .icon_cp{
    	display:inline-block;
    	width:30px;
    	height:30px;
    	background:url("images/ck1.png") no-repeat fixed top;
    	background-size: 100% ;
    }
    
    .textAarea_cp textarea{
   		 height:26px;
   		 line-height:26px;
   		 resize:none;
   		 border-radius:8px;
   		 width: 60%;
    }
    
    .searchInput{
    	border: 1.5px solid #5F9EA0;
    }
    
     .combo .combo-text{
     border: 1px solid #95B8E7;
     }   
}
</style>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
  
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr valign="top">
	<td width="100%" valign="top">
		<div id="tb" style="padding:20px;height:auto">
			<form id="addUpateForm" action="productGroupController/save" method="post">
			<input  type="hidden" id="provinceSpell" name="provinceSpell" >
			<input  type="hidden" id="group_id" name="group_id" >
			<!--  产品组产品json -->
			<input type="hidden" id="groupProductJson" name="groupProductJson" value="${groupProductJson}">
			<!--  产品组电话号码json -->
			<input type="hidden" id="groupNoJson" name="groupNoJson" value="${groupNoJson}">
			<input  type="hidden" id="postName" name="postName" >
			<div id="tb" style="padding:20px 5px;height:auto" class="datagrid-toolbar">
				<input type="hidden" id="proType" name="proType" value="1">
				<input type="hidden" id="proProvince" name="proProvince" value="山东">
				<input type="hidden" id="homeProjectId" name="homeProjectId" value="">
				<div style="margin:0 20px 0 0;padding:0;text-align:center;width:180px;display:inline-block;">
					<a href="javascript:addMethod();" class="btn addBtn">新增</a><a href="javascript:autoAddMethod();" class="btn inBtn">自动获取</a>
				</div>
				<div class="searchBox">
					<input placeholder="按产品组名称搜索" class="searchInput" id="proName" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}"><a href="javascript:searchMethod()" class="btn searchbutton">搜索</a>
				</div>
				<div class="searchBox">
					<input placeholder="按手机号码搜索" class="searchInput" id="proNumber" maxlength="20" onkeydown="if(event.keyCode==13){searchMethod()}"><a href="javascript:searchMethod()" class="btn searchbutton">搜索</a>
				</div>	
			</div>
			</form>
		</div>
		<table id="dg" class="easyui-datagrid" title="" data-options="url:'productGroupController/queryProductGroupList',toolbar:'#tb',rownumbers:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10">
			<thead>
				<tr>
					<th field=name	 width="360" >产品组名称</th>
					<th field=productsMark width="240" >产品详情</th>
					<th field=phone_number_str width="240" >号码详情</th>
				    <th align="center" data-options="field:'proID',width:'120',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
		<div id="win_div" class="easyui-window" data-options="closed:true" style="overflow: hidden;width:850px;height:580px">
			<iframe id="win_div_iframe" src="" height="100%" width="100%" scrolling="no" frameborder="0" align="top" marginheight="0" marginwidth="0"></iframe>
		</div> 
	</td>
</tr>
</table>
	<div id="businessJson" style="display: none;">
	</div>
	
	<!-- 查看产品组信息 -->
	<div class='show_dailog window ' style='display:none;'>
		<div class="panel-header panel-header-noborder window-header" style="width: 788.021px;">
			<div class="panel-title panel-with-icon" style="">查看产品组详情 </div>
			<div class="panel-icon icon-save"></div>
			<div class="panel-tool">
				<a class="panel-tool-close" href="javascript:closeDailog();"></a>
			</div>
		</div>
		<div class='dailog_body'>
		</div>
	</div>
</div>  
	<div id="win" class="easyui-window" title="自动化获取产品组信息" style="width:600px;height:200px;"   
        data-options="iconCls:'icon-save',modal:true"> 
        <p style="margin: 20px 30px;;">
        <span style="float:left;margin-right:5px;width: 100px;text-align: right;">手机号码:</span>
        <input type="text" style="height:20px;width:220px;" class="cpz_num" id='cpz_num' placeholder="请输入手机号码">
        </p>
        <input  type="hidden" id="business_typeid" name="business_typeid" >
   		<p style="margin-left: 30px;">
		<span style="float:left;margin-top: 2px;margin-right: 5px;width: 100px;text-align: right;">选择业务类型:</span>
		<select name="business_type" id="business_type" class="easyui-combobox" data-options="editable:true,multiple:true,listHeight:'60px'" style="width: 220px;"></select>
		</p>
		<div align="center" style="padding:20px 0;border-top:none;margin:0 auto; ">
			<a id="aSave"  onclick="autoGet()" class="savebtn headerBtn" >自动获取</a> 
		</div>
	</div> 
</body>
</html>