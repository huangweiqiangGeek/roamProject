function searchMethod(){
	var provincSpell = $('#provincSpell').val();
	var params ={provincSpell:provincSpell};
	$("#dg").datagrid("reload",params);
	console.lod(pro);
}

function formatOperate(value,rowDatas,rowIndex){
	console.log(value,rowDatas,rowIndex)
}

function formatDt(value,rowDatas,rowIndex){
	if(!isNull(value)){
		return new Date(value).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return "";
	
}
//省级任务统计
$(function(){
	drawStatistics1();
	drawStatistics2();
	detail();
});
function detail(){
	$.ajax({
		url: 'statisticsController/projectOfGroupUseCaseList?projectId=773',
		dataType: 'json',  
		async: true,
		success: function (res) { 
			  console.log("res",res);
		}
	})
}
//绘制任务柱状图
function drawStatistics1(){
	var homeProjectNames = [];
	var projectUseCasePassRates = [];
	$.ajax({
		url: 'statisticsController/getProvinceStatisticProjectEchart',
		dataType: 'json',  
		async: true,
		success: function (res) { 
			  console.log(res);
			  var homeProjectNames = [];//任务名称
			  var projectUseCasePassRates = [];//任务通过率
			  var provinceName = res.data[0].provinceName;
			  for(var i in res.data){
				  homeProjectNames.push(res.data[i].projectName);
				  projectUseCasePassRates.push(parseInt(res.data[i].projectUseCasePassRate));
			  }
			  var pTestPassRate = $("#pTestPassRate")[0];
				//得到echarts的实例对象
			    var Chart = echarts.init(pTestPassRate);
			    //pTestPassRate.title = '多 Y 轴示例';
			    var colors = ['#5793f3'];
			    option = {
			        color: colors,

			        tooltip: {
			            trigger: 'axis',
			            axisPointer: {
			                type: 'cross'
			            }
			        },
			        grid: {
			            right: '2%'
			        },
			        toolbox: {
			            feature: {
			                dataView: {show: true, readOnly: false},
			                restore: {show: true},
			                saveAsImage: {show: true}
			            }
			        },
			        legend: {
			            data:["通过率"+"（"+provinceName+"）"]
			        },
			        xAxis: [
			            {
			                type: 'category',
			                axisTick: {
			                    alignWithLabel: true
			                },
			                data: homeProjectNames
			            }
			        ],
			        yAxis: [
			            {
			                type: 'value',
			                name: "通过率"+"（"+provinceName+"）",
			                min: 0,
			                max: 100,
			                position: 'left',
			                axisLine: {
			                    lineStyle: {
			                        color: colors[0]
			                    }
			                },
			                axisLabel: {
			                    formatter: '{value}%'
			                }
			            }
			        ],
			        series: [
			            {
			                name:"通过率"+"（"+provinceName+"）",
			                type:'bar',
			                data:projectUseCasePassRates
			            }
			        ]
			    };
			    Chart.setOption(option);
		},
		error: function (res){
			console.log(res);
		}
	}); 
}
function degToRad(degree) {
    var factor = Math.PI / 180;
    return degree * factor;
}
//绘制任务饼状图
function drawStatistics2(){
	$.ajax({
		url: 'statisticsController/getProvinceStatisticProjectCircle',
		dataType: 'json',  
		async: true,
		success: function (res) { 
			  console.log(res);
			  var useCaseCount = res.data.provinceUseCaseCount;//任务用例总数	
			  var passCount = res.data.provinceUseCasePassCount;//任务用例通过数												
			  var nodoCount = res.data.provinceUseCaseNodoCount; //任务未测试用例数					
			  var disableCount = res.data.provinceUseCaseDisableCount;//任务下未完成用例数			
			  var failedCount = res.data.provinceUseCaseFailedCount+disableCount+nodoCount;//任务下不通过用例数
			  var passRate = (passCount/useCaseCount*100).toFixed(2); //平均通过率
				var pTestPassRate1 = $("#pTestPassRate1")[0];
				var chart = $("#pTestPassRate2")[0];
				var myChart = echarts.init(chart);
				var pTestPassRate3 = $("#pTestPassRate3")[0];
				var ctx = pTestPassRate1.getContext("2d");
				//var ctx1 = pTestPassRate2.getContext("2d");
				var ctx2 = pTestPassRate3.getContext("2d");
				ctx.lineWidth = 6;
				//ctx1.lineWidth = 6;
				ctx2.lineWidth = 6;

			    //Background
			    /*gradient = ctx.createRadialGradient(250, 250, 5, 250, 250, 300);
			    gradient.addColorStop(0, "#03303a");
			    gradient.addColorStop(1, "black");
			    ctx.fillStyle = gradient;
			    ctx.fillStyle = 'blue';
			    ctx.fillRect(0, 0, 500, 500);*/
			    //用例数
			    //Hours
			    ctx.beginPath();
			    ctx.strokeStyle = '#FE47FC'
			    ctx.arc(60, 100, 50, degToRad(120), degToRad(300)); //总数50  300 480 50*6 180/50=3.6 3.6*50+120
			    ctx.stroke();
			    //Minutes
			    ctx.beginPath();
				ctx.strokeStyle = '#3AF8F8';
			    ctx.arc(60, 100, 43, degToRad(120), degToRad(180/useCaseCount*passCount + 120));	//通过30  30*3.6 + 120
			    ctx.stroke();
			    //Seconds
			    ctx.beginPath();
				ctx.strokeStyle = '#f00';
			    ctx.arc(60, 100, 36, degToRad(120), degToRad(180/useCaseCount*failedCount + 120)); //未通过20 20*3.6 + 120
			    ctx.stroke();
			    //font
			    ctx.font = "oblique 12px Helvetica";
			    ctx.fillStyle = '#3AF8F8'
			    ctx.fillText(passCount+" 通过数", 42, 80);
			    ctx.fillText("______", 26, 82);
			    ctx.fillStyle = 'red'
				ctx.fillText(failedCount+" 未通过数", 42, 110);
			    ctx.fillText("_____", 33, 112);
				ctx.fillStyle = '#FE47FC'
			    ctx.fillText(useCaseCount+" 总用例数", 42, 140);
			    ctx.fillText("_____", 33, 142);
			    
			    //通过率
			    options = {
		                tooltip: {
		                    formatter: "{a} <br/>{c} {b}%"
		                },
		                series: [{
		                    name: '通过率',
		                    type: 'gauge',
		                    center: ['42%', '60%'],
		                    min: 0,
		                    max: 100,
		                    splitNumber: 10,
		                    radius: '80%',
		                    detail: {formatter:'{value}%'},
		                    axisLine: { // 坐标轴线
		                        lineStyle: { // 属性lineStyle控制线条样式
		                            color: [
		                                [0.5, '#FF895C'],
		                                [0.8, '#FE47FC'],
		                                [1, '#3AF8F8']
		                            ],
		                            width: 6,
		                        }
		                    },
		                    axisLabel: { // 坐标轴小标记
		                        textStyle: { // 属性lineStyle控制线条样式
		                        	fontSize:8,
		                            color: 'auto'
		                        }
		                    },
		                    axisTick: { // 坐标轴小标记
		                        length: 10, // 属性length控制线长
		                        lineStyle: { // 属性lineStyle控制线条样式
		                            color: 'auto'
		                        }
		                    },
		                    splitLine: { // 分隔线
		                        length: 15, // 属性length控制线长
		                        lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
		                            width: 1,
		                            color: 'auto'
		                        }
		                    },
		                    pointer: { // 分隔线
		                        length:25,
		                        width:5,
		                        shadowColor: 'auto', //默认透明
		                        shadowBlur: 5
		                    },
		                    detail: {
		                        offsetCenter: [0, '80%'], // x, y，单位px
		                        textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                            color: 'auto',
		                            fontSize:20
		                        },
		                        formatter:'{value}%'
		                    },
		                    data: [{value: passRate}]
		                }]
		            };
			    	myChart.setOption(options, true);
			    			   
			    
			    //执行数
			  //Hours
			    ctx2.beginPath();
			    ctx2.strokeStyle = '#FE47FC'
			    ctx2.arc(60, 100, 50, degToRad(120), degToRad(300));
			    ctx2.stroke();
			    //Minutes
			    ctx2.beginPath();
				ctx2.strokeStyle = '#3AF8F8';
			    ctx2.arc(60, 100, 43, degToRad(120), degToRad(180/useCaseCount*(useCaseCount-nodoCount) + 120));
			    ctx2.stroke();
			    //Seconds
			    ctx2.beginPath();
				ctx2.strokeStyle = '#f00';
			    ctx2.arc(60, 100, 36, degToRad(120), degToRad(180/useCaseCount*nodoCount + 120));
			    ctx2.stroke();
			    //Date
			    ctx2.font = "oblique 12px Helvetica";
			    ctx2.fillStyle = '#3AF8F8'
			    ctx2.fillText(useCaseCount-nodoCount+" 执行数", 42, 80);
			    ctx2.fillText("______", 26, 82);
			    ctx2.fillStyle = 'red'
				ctx2.fillText(nodoCount+" 未执行数", 42, 110);
			    ctx2.fillText("_____", 33, 112);
				ctx2.fillStyle = '#FE47FC'
			    ctx2.fillText(useCaseCount+" 总用例数", 42, 140);
			    ctx2.fillText("_____", 33, 142);
		},
		error: function (res){
			console.log(res);
		}
	});
}

//查看全部
function lookList(){
	window.open("statisticsController/LookList");
	//window.location.href="statisticsController/LookList";
}
//查看详情
function formatOperate(value,rowDatas,rowIndex){
	var h='<a class="btn handleBtn" href="statisticsController/projectOfGroupUseCaseList?projectId='+value+'">查看详细</a>'
	return h;
}
/**
 * 下载统计数据
 * @returns {Boolean}
 */
function downLoad(){
	var province=$('.sele').val();
	//var province="shandong";
	$.messager.confirm('下载提示', '确认下载当前省份的统计数据吗?', function(r){
		if (r){
			var urlStr1="statisticsController/downloadProvinceStatistic2HomeProject?province="+province;
			var form = $('<form></form>');
			form.attr('style', 'display:none');
			form.attr('method', 'post'); //form提交路径        
			form.attr('action', urlStr1);
			var input = $('<input type="text" name="params" id="params" />'); // 可以添加一些参数              
			input.attr('value',province );
			form.append(input);
			$(document.body).append(form);
			form.submit();
			$.post(urlStr1,function(data){
				//console.log(data)
				//if(data.success=="true"){
				$.messager.confirm('下载提示', '文件下载成功！', function(){});
				/*}else{
					$.messager.confirm('下载提示', '文件下载失败！', function(){})
				}*/
			})
			/*
			$.ajax({
				type:"POST",
				url:"statisticsController/downloadProvinceStatistic2HomeProject",
				data:province,
				success:function(data){
					if(data.success=="true"){
						$.messager.confirm('下载提示', '文件下载成功！', function(){})
					}else{
						$.messager.confirm('下载提示', '文件下载失败！', function(){})
					}
				}
			})*/
		}
	});
}

function formatName(value,rowDatas,rowIndex){
	var str='<input type="text" value="'+value+'" style="width:120px;border:1px solid #CFCFCF;border-radius:5px" readonly="readonly">'
	return str;
}

function formatProName(value,rowDatas,rowIndex){
	var str='<input type="text" value="'+value+'" style="width:120px;border:1px solid #CFCFCF;border-radius:5px" readonly="readonly">'
	return str;
}
