function searchMethod(){
	var provincSpell = $('#provincSpell').val();
	var params ={provincSpell:provincSpell};
	$("#dg").datagrid("reload",params);
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
	$.ajax({
		url: 'statisticsController/getProvinceStatistic2Project',
		dataType: 'json',  
		async: true,
		success: function (res) { 
			  console.log(res);
			  var homeProjectNames = [];//任务名称
			  var projectUseCasePassRates = [];//任务通过率
			  for(var i in res.rows){
				  homeProjectNames.push(res.rows[i].homeProjectName);
				  projectUseCasePassRates.push(parseInt(res.rows[i].projectUseCasePassRate));
			  }
			  drawStatistics(homeProjectNames,projectUseCasePassRates);
		},
		error: function (res){
			console.log(res);
		}
	}); 
});
//绘制统计图表
function drawStatistics(homeProjectNames,projectUseCasePassRates){
	var pTestPassRate = $("#pTestPassRate")[0];
    var pTestPassRate1 = $("#pTestPassRate1")[0];
	//得到echarts的实例对象
    var Chart = echarts.init(pTestPassRate);
    var Chart1 = echarts.init(pTestPassRate1);
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
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:["任务通过率"]
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
                name: "任务通过率",
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
                name:"任务通过率",
                type:'bar',
                data:projectUseCasePassRates
            }
        ]
    };
    Chart.setOption(option);
    
    
    
    //右上
    var dataStyle = { //作用不明
            normal: {
                label: {show:false},
                labelLine: {show:false},
                shadowBlur: 40,
                shadowColor: 'rgba(40, 40, 40, 0.5)',
            }
        };
        var placeHolderStyle = { //作用不明  原来是这是pie图里面的
            normal : {
                color: 'rgba(0,0,0,0)',
                label: {show:false},
                labelLine: {show:false}
            },
            emphasis : {
                color: 'rgba(0,0,0,0)'
            }
        };
        var option1 = {
            backgroundColor: '#fff', //图表的配置颜色
            color: ['#45e8bc', '#33d7fb','#ffdf34','#eeeeee'], //每一项对应的颜色
            tooltip : {  //提示框组件
                show: true,
                formatter: "{a} <br/>{b} : {c} ({d}%)"  //正则设置格式
            },
            legend: {  //图例组件
                orient:'vertical', //图例的布局朝向
                x:190,  //图例的位置--离左边的距离
                y:50,  //图例的位置--离上边的距离
                itemGap:20, //图例每项之间的间隔。横向布局时为水平间隔，纵向布局时为纵向间隔。
                data:['平均通过率', '通过的', '未通过的 ','用例总数']
            },
            series : [  //系列列表
                {
                    name:'平均通过率',  //名字--用于tooltip的显示
                    type:'pie',    //类型--饼状图
                    clockWise:false,   //饼图的扇区是否是顺时针排布。
                    radius : [60,85], //饼图的半径，数组的第一项是内半径，第二项是外半径。 内半径是0就是一个真正的饼
                    center:['35%', '50%'], //饼图片的中心
                    itemStyle : dataStyle, //样式
                    hoverAnimation: true, //是否开启 hover 在扇区上的放大动画效果。
                    zlevel:2,
                    data:[  //系列中的数据内容数组
                        {
                            value:360,     //数据值总的值
                            name:'平均通过率'  //数据项名称。
                        },
                        {
                            value:60,     //剩下的值
                            name:'invisible',
                            itemStyle : placeHolderStyle
                        }

                    ],
                },
                {
                    name:'通过的',
                    type:'pie',
                    clockWise:false,
                    radius : [40, 60],
                    center:['35%', '50%'], //饼图片的中心
                    itemStyle : dataStyle,
                    hoverAnimation: false,
                    zlevel:100,
                    data:[
                        {
                            value:360,   //数据值总的值
                            name:'通过的'
                        },
                        {
                            value:270,
                            name:'invisible',
                            itemStyle : placeHolderStyle
                        }
                    ]
                },
                {
                    name:'未通过的 ',
                    type:'pie',
                    clockWise:false,
                    hoverAnimation: false,
                    radius : [20, 40],
                    center:['35%', '50%'], //饼图片的中心
                    itemStyle : dataStyle,

                    data:[
                        {
                            value:360,
                            name:'未通过的 '
                        },
                        {
                            value:300,
                            name:'invisible',
                            itemStyle : placeHolderStyle
                        }
                    ]
                },
                {
                    name:'用例总数',
                    type:'pie',
                    clockWise:false,
                    hoverAnimation: false,
                    radius : [60,85],
                    center:['35%', '50%'], //饼图片的中心
                    itemStyle : dataStyle,
                    zlevel:1,
                    data:[
                        {
                            value:360,
                            name:'所有用例数'
                        },
                        {
                            value:0,
                            name:'invisible',
                            itemStyle : placeHolderStyle
                        }
                    ],
                },
            ]
        };
        Chart1.setOption(option1);
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
				//}else{
				//	$.messager.confirm('下载提示', '文件下载失败！', function(){})
				//}
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

