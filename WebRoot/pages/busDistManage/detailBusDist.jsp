<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加修改载货单</title>
<script type="text/javascript">
if("close"=="${close}"){
	window.parent.closeWin(true);
}
</script>
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="framework/jquery-easyui-1.3.2/demo.css">
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="framework/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="framework/css/common.css">
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validatePlugin.js"></script>
<script type="text/javascript" src="framework/js/common.js"></script>
<script type="text/javascript" src="pages/busDistManage/addOrUpdateBusDist.js"></script>
</head>
<body marginheight="0" marginwidth="0" style="overflow: auto">
	<input type="hidden" id="dist_id" value="${distHead.id}">
	<input type="hidden" id="select_tab" value="${select_tab}">
	<input type="hidden" id="hide01" value="${distHead.distTypeId}">
	<input type="hidden" id="hide02" value="${distHead.bulkFlag}">
	<input type="hidden" id="hide03" value="${distHead.customsCode}">
	<div id="main_tabs" class="easyui-tabs">
	<div id="d0" title="载货单" style="padding:1px">    	
	
    <form id="headForm" name="headForm" action="distHead/saveDistHead" method="post">    	
    <input type="hidden" name="id" value="${distHead.id }"/>
    
			<div id="title" style="padding:5px;height:auto">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
    		<tr>
    			<th><font color="red">*</font>载货单类型 ：</th>
    			<td>
    				<select id="distTypeId"  name="distTypeId" value="${distHead.distTypeId}" onchange="chanageDistTypeId(this)" disabled>
			      </select>
    			</td>
    			<th><font color="red">*</font>主管海关代码：</th>
    			<td><select id="customsCode" name="customsCode"  disabled>
    			</select></td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>大宗散货：</th>
    			<td>
			      <select id="bulkFlag" name="bulkFlag" value="${distHead.bulkFlag }" disabled>
    			</select>
    			</td>
    			<th>企业预录编号：</th>
    			<td><input type="text" id="copNo" name="copNo" style="width:180px" value="${distHead.copNo }" maxlength="20" disabled>
    			</td>
    		</tr>
    		<tr>
    			<th><font color="red">*</font>目的场站：</th>
    			<td><input type="text" id="cfsNo" name="cfsNo" style="width:180px" value="${distHead.cfsNo }" maxlength="10" disabled></td>
    			<th><font id="startCfsNo_font" color="red"></font>起始场站：</th>
    			<td><input type="text" id="startCfsNo" name="startCfsNo" style="width:180px" value="${distHead.startCfsNo }" maxlength="10" disabled></td>
    		</tr>
    		<tr>
     			<th>币制：</th>
    			<td><input type="text" id="curr" name="curr"  style="width:180px"value="${distHead.curr }" maxlength="10" disabled></td>
    			<th>货物名称：</th>
    			<td><input type="text" id="gName" name="gName" style="width:180px" value="${distHead.gName }" maxlength="70" disabled></td>
 		    </tr>
    		<tr>
     			<th>总价值：</th>
    			<td><input type="text" id="gValue" name="gValue"  style="width:180px"value="${distHead.gValue }" maxlength="18" disabled></td>
    			<th>总件数：</th>
    			<td><input type="text" id="packNum" name="packNum"  style="width:180px"value="${distHead.packNum }" maxlength="18" disabled>件</td>
    			</tr>
    		<tr>
     			<th>总净重：</th>
    			<td><input type="text" id="netWt" name="netWt"  style="width:180px"value="${distHead.netWt }" maxlength="18" disabled> Kg</td>
    			<th>总重量：</th>
    			<td><input type="text" id="grossWt" name="grossWt" style="width:180px" value="${distHead.grossWt }" maxlength="18" disabled> Kg</td>
 		    </tr>
    		<tr>
     			<th>创建人：</th>
    			<td><input type="text" id="create_user_name" name="create_user_name"  style="width:180px"value="${distHead.create_user_name }" maxlength="50" disabled></td>
    			<th>创建时间：</th>
    			<td><input type="text" id="create_time_show" name="create_time_show" style="width:180px" value="${distHead.create_time_show}" maxlength="6" disabled></td>
 		    </tr>
    		<tr>
     			<th>企业代码：</th>
    			<td><input type="text" id="copCode" name="copCode"  style="width:180px"value="${distHead.copCode }" maxlength="50" disabled></td>
    			<th>企业名称：</th>
    			<td><input type="text" id="copName" name="copName" style="width:180px" value="${distHead.copName }" maxlength="6" disabled></td>
 		    </tr>
    		<tr>
    			<th>备注：</th>
    			<td colspan="3"><input type="textArea" id="remark" name="remark" style="width:550px;height:50px" value="${distHead.remark }" maxlength="200" disabled></td>
    		</tr>
    		
    	</table>
    	
			</div>
			<div style="margin:10px 0;"></div>
	      </form>
		</div>
		<div id="d2" title="业务单据" style="padding:1px;">
			<div id="tb_2" style="padding:5px;height:auto">
		    <form id="businussForm" name="businussForm" action="distHead/saveDistBusinuss" method="post">	
            <input type="hidden" id="id" name="id" class="businussClass" value=""/>
            <input type="hidden" id="distId" name="distId" class="businussClass" value="${distHead.id }"/>
			   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
		    		<tr>
		     			<th style="width:110px;"><font color="red">*</font>单据业务编号：</th>
		    			<td style="width:140px;"><input type="text" id="busNo" name="busNo" class="businussClass" style="width:120px" value="" maxlength="10" disabled/></td>
		    			<th style="width:110px;"><font color="red">*</font>单据业务类型：</th>
		    			<td style="width:140px;"><select id="busType"  name="busType" class="businussClass" disabled></select></td>
		 		        <th style="width:110px;">出入区标志：</th>
		    			<td style="width:140px;"><select id="rcFlag"  name="rcFlag" class="businussClass" disabled></select></td>
		 		    </tr>
		    		<tr>
		     			<th>转关单号：</th>
		    			<td><input type="text" id="transId" name="transId" class="businussClass"  style="width:120px" value="" maxlength="18" disabled></td>
		    			<th>报关单号：</th>
		    			<td><input type="text" id="entryId" name="entryId" class="businussClass" style="width:120px"  value="" maxlength="18" disabled></td>
		 		        <th>进出口岸：</th>
		    			<td><input type="text" id="iEPort" name="iEPort" class="businussClass" style="width:120px" value="" maxlength="10" disabled></td>
		 		    </tr>
		    		<tr>
		     			<th>货物名称：</th>
		    			<td><input type="text" id="gName" name="gName" class="businussClass" style="width:120px" value="" maxlength="30" disabled></td>
		    			<th>币种：</th>
		    			<td><input type="text" id="busCurr" name="busCurr" class="businussClass" style="width:120px" value="" maxlength="10" disabled></td>
		 		        <th>件数：</th>
		    			<td><input type="text" id="busPackNum" name="busPackNum" class="businussClass"  style="width:120px"  value="" maxlength="9" disabled>件</td>
		 		    </tr>
		    		<tr>
		     			<th>总价值：</th>
		    			<td><input type="text" id="gValue" name="gValue" class="businussClass" style="width:120px" value="" maxlength="18" disabled></td>
		    			<th>毛重：</th>
		    			<td><input type="text" id="busGrossWt" name="busGrossWt" class="businussClass" style="width:120px" value="" maxlength="18" disabled>Kg</td>
		 		        <th>净量：</th>
		    			<td><input type="text" id="busNetWt" name="busNetWt" class="businussClass"  style="width:120px"  value="" maxlength="18" disabled>Kg</td>
		 		    </tr>
		    		<tr>
		     			<th>经营单位代码：</th>
		    			<td><input type="text" id="tradeCode" name="tradeCode" class="businussClass" style="width:120px" value="" maxlength="10" disabled></td>
		    			<th>经营单位名称：</th>
		    			<td><input type="text" id="tradeName" name="tradeName" class="businussClass" style="width:120px" value="" maxlength="20" disabled></td>
		 		        <th></th>
		    			<td></td>
		 		    </tr>
		    		<tr>
		     			<th>收发单位编码：</th>
		    			<td><input type="text" id="ownerCode" name="ownerCode" class="businussClass" style="width:120px" value="" maxlength="10" disabled></td>
		    			<th>收发单位名称：</th>
		    			<td><input type="text" id="ownerName" name="ownerName" class="businussClass" style="width:120px" value="" maxlength="20" disabled></td>
		 		        <th></th>
		    			<td></td>
		 		    </tr>
		    		<tr>
		    			<th>备注：</th>
		    			<td colspan="5"><input type="textArea" id="busRemark" name="busRemark" class="businussClass" style="width:550px" value="" maxlength="200" disabled></td>
		    		</tr>
			   </table>
			</form>
			</div>
			<div style="margin:10px 0;"></div>
			<table id="dg_2" class="easyui-datagrid" title="" data-options="
						toolbar:'#tb_2',onClickRow:onClickRowBus,rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,
						url:'distHead/queryDistBusinussList?dist_id=${dist_id }'">
				<thead>
					<tr>
						<th field="busNo" width="120" >业务编号</th>
						<th field="busType_show" width="120">业务类型 </th>
						<th field="transId" width="120">转关单号</th>
						<th field="entryId" width="120">报关单号</th>
						<th field="rcFlag_show" width="80">出入区</th>
						<th field="iEPort" width="100">进出口岸</th>
						<th field="gName" width="120">货物名称</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="d1" title="车辆" style="padding:1px">
			<div id="tb_1" style="padding:5px;height:auto">
    <form id="vehiclesForm" name="vehiclesForm" action="distHead/saveDistVehicles" method="post">	
    <input type="hidden" id="id" name="id" class="vehiclesClass" value=""/>
    <input type="hidden" id="distId" name="distId" class="vehiclesClass" value="${distHead.id }"/>
			   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
		    		<tr>
		     			<th style="width:110px;"><font color="red">*</font>车牌号：</th>
		    			<td style="width:140px;"><input type="text" id="platNo" name="platNo" class="vehiclesClass" style="width:120px" value="" maxlength="10" disabled></td>
		    			<th style="width:110px;">货物重量：</th>
		    			<td style="width:140px;"><input type="text" id="grossWt" name="grossWt" class="vehiclesClass" style="width:120px"  value="" maxlength="18" disabled>Kg</td>
		 		        <th style="width:110px;"></th>
		    			<td style="width:140px;"></td>
		 		    </tr>
		    		<tr>
		     			<th>集装箱规格1：</th>
		    			<td><input type="text" id="contaModel1" name="contaModel1" class="vehiclesClass"  style="width:120px" value="" maxlength="1" disabled></td>
		    			<th>集装箱关封1：</th>
		    			<td><input type="text" id="contaSeal1" name="contaSeal1" class="vehiclesClass" style="width:120px"  value="" maxlength="20" disabled></td>
		 		        <th>集装箱净重1：</th>
		    			<td><input type="text" id="netWt1" name="netWt1" class="vehiclesClass" style="width:120px" value="" maxlength="18" disabled>Kg</td>
		 		    </tr>
		    		<tr>
		     			<th>集装箱规格2：</th>
		    			<td><input type="text" id="contaModel2" name="contaModel2" class="vehiclesClass" style="width:120px" value="" maxlength="10" disabled></td>
		    			<th>集装箱关封2：</th>
		    			<td><input type="text" id="contaSeal2" name="contaSeal2" class="vehiclesClass" style="width:120px" value="" maxlength="20" disabled></td>
		 		        <th>集装箱净重2：</th>
		    			<td><input type="text" id="netWt2" name="netWt2" class="vehiclesClass"  style="width:120px"  value="" maxlength="18" disabled>Kg</td>
		 		    </tr>
		    		<tr>
		    			<th>备注：</th>
		    			<td colspan="5"><input type="textArea" id="vehRemark" name="vehRemark" class="vehiclesClass" style="width:550px" value="" maxlength="200" disabled></td>
		    		</tr>
			   </table>
			</form>
			</div>
			<div style="margin:10px 0;"></div>
			<table id="dg_1" class="easyui-datagrid" title="" data-options="
						toolbar:'#tb_1',onClickRow:onClickRowVeh,rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,
						url:'distHead/queryDistVehiclesList?dist_id=${dist_id}'">
				<thead>
					<tr >
						<th field="platNo" width="100" >车牌号</th>
						<th field="grossWt" width="100">货物重量</th>
						<th field="contaModel1" width="80">集装箱规格1</th>
						<th field="contaSeal1" width="80">集装箱关封1</th>
						<th field="netWt1" width="80">集装箱净重1</th>
						<th field="contaModel2" width="80">集装箱规格2</th>
						<th field="contaSeal2" width="80">集装箱关封2</th>
						<th field="netWt2" width="80">集装箱净重2</th>
						<th field="vehRemark" width="120">备注</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div  id="d3" title="货物信息" style="padding:1px">
			<div id="tb_3" style="padding:5px;height:auto">
	    <form id="cargoForm" name="cargoForm" action="distHead/saveDistCargo" method="post">	
		    <input type="hidden" id="id" name="id"  class="cargoClass" value=""/>	
		    <input type="hidden" id="distId" name="distId"  class="cargoClass" value="${distHead.id }"/>
			   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
		    		<tr>
		     			<th style="width:110px;"><font color="red">*</font>车牌号：</th>
		    			<td style="width:140px;"><select id="vehId_" name="vehId" class="cargoClass" style="width:120px" disabled/></select></td>
		    			<th style="width:110px;"><font color="red">*</font>料号：</th>
		    			<td style="width:140px;"><input type="text" id="gNo" name="gNo" class="cargoClass" style="width:120px" value="" maxlength="20" disabled/></td>
		 		        <th style="width:110px;"><font color="red">*</font>商品编码：</th>
		    			<td style="width:140px;"><input type="text" id="gCode" name="gCode" class="cargoClass" style="width:120px" value="" maxlength="20" disabled/></td>
		 		    </tr>
		    		<tr>
		     			<th><font color="red">*</font>商品名称：</th>
		    			<td><input type="text" id="gName" name="gName" class="cargoClass"  style="width:120px" value="" maxlength="30" disabled></td>
		    			<th><font color="red">*</font>数量：</th>
		    			<td><input type="text" id=qty name="qty" class="cargoClass" style="width:120px"  value="" maxlength="18" disabled></td>
		 		        <th>计量单位：</th>
		    			<td><input type="text" id="unit" name="unit" class="cargoClass" style="width:120px" value="" maxlength="10" disabled></td>
		 		    </tr>
		    		<tr>
		     			<th>单据业务编号：</th>
		    			<td><select type="text" id="businessId_" name="businessId" class="cargoClass" style="width:120px" onchange="chanageBusData(this)" disabled></select></td>
		     			<th>单据业务类型 ：</th>
		    			<td><select id="businessTypeId"  name="businessTypeId" class="cargoClass" disabled></select></td>
		 		        <th>商品规格型号：</th>
		    			<td><input type="text" id="gModel" name="gModel" class="cargoClass"  style="width:120px"  value="" maxlength="30" disabled></td>
		 		    </tr>
		    		<tr>
		     			<th>单价：</th>
		    			<td><input type="text" id="price" name="price" class="cargoClass" style="width:120px" value="" maxlength="18" disabled></td>
		    			<th>货值：</th>
		    			<td><input type="text" id="value" name="value" class="cargoClass" style="width:120px" value="" maxlength="18" disabled></td>
		 		        <th>币种：</th>
		    			<td><input type="text" id="curr" name="curr" class="cargoClass"  style="width:120px"  value="" maxlength="10" disabled></td>
		 		    </tr>
		    		<tr>
		     			<th>起抵国：</th>
		    			<td><input type="text" id="country" name="country" class="cargoClass" style="width:120px" value="" maxlength="10" disabled></td>
		    			<th></th>
		    			<td></td>
		 		        <th></th>
		    			<td></td>
		 		    </tr>
		    		<tr>
		    			<th>备注：</th>
		    			<td colspan="5"><input type="textArea" id="remark" name="remark" class="cargoClass" style="width:550px" value="" maxlength="100" disabled></td>
		    		</tr>
			   </table>
			</form>
			</div>
			<div style="margin:10px 0;"></div>
			<table id="dg_3" class="easyui-datagrid" title="" data-options="
						toolbar:'#tb_3',onClickRow:onClickRowCargo,rownumbers:true,singleSelect:true,idField:'id',autoRowHeight:false,pagination:true,pageSize:10,
						url:'distHead/queryDistCargoList?dist_id=${dist_id }'">
				<thead>
					<tr>
						<th field="platNo" width="100" >车牌号</th>
						<th field="gNo" width="100">料号 </th>
						<th field="gCode" width="80">商品编码</th>
						<th field="gName" width="80">商品名称</th>
						<th field="qty" width="80">数量</th>
						<th field="unit" width="80">计量单位</th>
						<th field="businessType" width="80">业务类型 </th>
						<th field="price" width="80">单价</th>
						<th field="value" width="80">货值</th>
						<th field="curr" width="80">币种</th>
						<th field="country" width="80">起抵国</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>