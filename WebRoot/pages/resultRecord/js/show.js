function searchMethod(){
	var resultType = $('#resultType').val();
	var proID =$('#proID').val();
	var uCID = trans($('#uCID').val());
}	
window.onload=function(){
	var id=$('#proID').val();
	if(id!=""){		
		reloadSucc(id)
	}
}
var successUrl = "";
var failureUrl = "";
var cannotUrl = "";
function reloadSucc(id){
	$.ajax({
		url:"resultRecord/getRateOfSuccess",
		type:"POST",	
		dataType:"json",
		data:"proID="+id,		
		success:function(data){
				var succRate= data.resultRecord;//通过率resultRecord
				var failRate = data.failureRate;//失败率	
				var cannotRate = data.cannotRate;//未执行用例
				successUrl = data.successUrl;
				failureUrl = data.failureUrl;
				cannotUrl = data.cannotUrl;
				 $('.succ').append(succRate);
				 $('.fair').append(failRate);
				 $('.cannot').append(cannotRate);
				var totalNum = succRate + failRate + cannotRate;
				 $('.succ1').append((succRate/totalNum*100).toFixed(1)+"%");
				 $('.fair1').append((failRate/totalNum*100).toFixed(1)+"%");
				 $('.cannot1').append((cannotRate/totalNum*100).toFixed(1)+"%");
				 //pieChart(successUrl,failureUrl,cannotUrl);
		}
	});	
}
$(document).on('click','.succClick',function(){
	window.location.href = successUrl;
})
$(document).on('click','.fairClick',function(){
	window.location.href = failureUrl;
})
$(document).on('click','.cannotClick',function(){
	window.location.href = cannotUrl;
})
 /*function pieChart(successUrl,failureUrl,cannotUrl) {
        var sliceBorderWidth = 1;                         // 切片边框的宽度
        var sliceBorderStyle = "#fff";                    // 边框的颜色
        var sliceGradientColour = "#ddd";                 // 用于图表渐变一端的颜色
        var maxPullOutDistance = 25;                      // 以像素为单位，点击时将片拔出
        var pullOutFrameStep = 4;                         // 多少像素移动片与每个动画帧
        var pullOutFrameInterval = 40;                    // 每个动画帧之间有多长（毫秒）
        var chartStartAngle = -.5 * Math.PI;              // 开始的角度
        var sURL =successUrl;
        var fURL = failureUrl;
        var cURL = cannotUrl;
        // 为图表声明一些变量
        var canvas;                       // The canvas element in the page
        var currentPullOutSlice = -1;     // 切片目前拉出 (-1 = no slice)
        var currentPullOutDistance = 0;   // 目前在动画中拉出的像素数是多少
        var animationId = 0;              // 轨道由setinterval()动画间隔的ID
        var chartData = [];               // Chart data (labels, values, and angles)
        var chartColours = [];            // Chart colours (pulled from the HTML table)
        var totalValue = 0;               // Total of all the values in the chart
        var canvasWidth;                  // Width of the canvas, in pixels
        var canvasHeight;                 // Height of the canvas, in pixels
        var centreX;                      // X-coordinate of centre of the canvas/chart
        var centreY;                      // Y-coordinate of centre of the canvas/chart
        var chartRadius;                  // Radius of the pie chart, in pixels

        // 把事情整理好并画出图表
        init();
        *//**
         * 设置图表数据和颜色，以及图表和表格单击处理程序，
         * 绘制初始图
         *//*

        function init() {
            canvas = document.getElementById('chart');
            if ( typeof canvas.getContext === 'undefined' ) return;
            canvasWidth = canvas.width;
            canvasHeight = canvas.height;
            centreX = canvasWidth / 2;
            centreY = canvasHeight / 2;
            chartRadius = Math.min( canvasWidth, canvasHeight ) / 2 ;

            // 从表格中抓取数据
            // 并将单击处理程序分配给表数据单元

            var currentRow = -1;
            var currentCell = 0;
            //
            $('#chartData td').each( function() {
                currentCell++;//1
                if ( currentCell % 2 != 0 ) {//当前为单数td时
                    currentRow++;
                    chartData[currentRow] = [];//开始角度 结束角度 文字 数字
                    chartData[currentRow]['label'] = $(this).text();//label 通过率 失败率  执行
                } else {
                    var value = parseFloat($(this).text());
                    totalValue += value;
                    value = value.toFixed(2);
                    chartData[currentRow]['value'] = value;
                }

                // 将切片索引存储在此单元格中，并向其附加单击处理程序
                $(this).data( 'slice', currentRow );

                // 提取和存储单元格颜色
                if ( rgb = $(this).css('color').match( /rgb\((\d+), (\d+), (\d+)/) ) {
                    chartColours[currentRow] = [ rgb[1], rgb[2], rgb[3] ];
                } else if ( hex = $(this).css('color').match(/#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/) ) {
                    chartColours[currentRow] = [ parseInt(hex[1],16) ,parseInt(hex[2],16), parseInt(hex[3], 16) ];
                } else {
                    alert( "Error: Colour could not be determined! Please specify table colours using the format '#xxxxxx'" );
                    return;
                }
            } );
            //
            // 现在计算并存储图表数据中每个切片的开始和结束角度

            var currentPos = 0; // 切片中当前切片的位置（从0到1）
            
            for ( var slice in chartData ) {
                chartData[slice]['startAngle'] = 2 * Math.PI * currentPos;
                chartData[slice]['endAngle'] = 2 * Math.PI * ( currentPos + ( chartData[slice]['value'] / totalValue ) );
                currentPos += chartData[slice]['value'] / totalValue;
                if(totalValue!=0){
                	var num =chartData[slice]['value']*10000/ totalValue;
                num = (num/100).toFixed(2);
                }else{
                	var num=0.00;
                }
                num=num+"%";
                num=chartData[slice]['label']+":"+num;
                $('.'+slice+'').append(num)
            }

            // 现在绘制饼图，并向其添加单击处理程序
            drawChart();
            $('#chart').click ( handleChartClick );
        }


        *//**
         * 在图表区域中处理鼠标单击。
         *
         * 如果点击一个切片，切换它在或出。
         * 如果用户点击饼外，推任何片回。
         *
         * @param 点击事件
         *//*

        function handleChartClick ( clickEvent ) {

            // 获取鼠标光标位置的点击时间，相对于画布
            var mouseX = clickEvent.pageX - this.offsetLeft;
            var mouseY = clickEvent.pageY - this.offsetTop;

            // 在途中点击？
            var xFromCentre = mouseX - centreX;
            var yFromCentre = mouseY - centreY;
            var distanceFromCentre = Math.sqrt( Math.pow( Math.abs( xFromCentre ), 2 ) + Math.pow( Math.abs( yFromCentre ), 2 ) );

            if ( distanceFromCentre <= chartRadius ) {
                // Yes,
                // 通过比较相对于图表中心的角度来找到被单击的切片.

                var clickAngle = Math.atan2( yFromCentre, xFromCentre ) - chartStartAngle;
                if ( clickAngle < 0 ) clickAngle = 2 * Math.PI + clickAngle;

                for ( var slice in chartData ) {
                    if ( clickAngle >= chartData[slice]['startAngle'] && clickAngle <= chartData[slice]['endAngle'] ) {
                        //做跳转处理
                        var str=chartData[slice]['label'];
                        if(str=="通过率"){                        	
                        	window.location.href=sURL;
                        }else if(str=="失败率"){            	
                        	window.location.href=fURL;
                        }else if(str=="未完成执行"){            	
                        	window.location.href=cURL;
                        }
                        
                    }
                }
            }
        }

        function drawChart() {
            var context = canvas.getContext('2d');

            //清除画布，为新帧做好准备
            context.clearRect ( 0, 0, canvasWidth, canvasHeight );

            // 绘制图表的每一个切片，跳过拉出的切片（如果有的话）
            for ( var slice in chartData ) {
                drawSlice( context, slice );
            }
            
        }

        function drawSlice ( context, slice ) {

            // Compute the adjusted start and end angles for the slice
            var startAngle = chartData[slice]['startAngle']  + chartStartAngle;
            var endAngle = chartData[slice]['endAngle']  + chartStartAngle;

            if ( slice == currentPullOutSlice ) {
                var midAngle = (startAngle + endAngle) / 2;
                var actualPullOutDistance = currentPullOutDistance * easeOut( currentPullOutDistance/maxPullOutDistance, .8 );
                startX = centreX + Math.cos(midAngle) * actualPullOutDistance;
                startY = centreY + Math.sin(midAngle) * actualPullOutDistance;
                context.fillStyle = 'rgb(' + chartColours[slice].join(',') + ')';
                context.textAlign = "center";
                context.font = pullOutLabelFont;
                context.fillText( chartData[slice]['label'], centreX + Math.cos(midAngle) * ( chartRadius + maxPullOutDistance + pullOutLabelPadding ), centreY + Math.sin(midAngle) * ( chartRadius + maxPullOutDistance + pullOutLabelPadding ) );
                context.font = pullOutValueFont;
                context.fillText( pullOutValuePrefix + chartData[slice]['value'] + " (" + ( parseInt( chartData[slice]['value'] / totalValue * 100 + .5 ) ) +  "%)", centreX + Math.cos(midAngle) * ( chartRadius + maxPullOutDistance + pullOutLabelPadding ), centreY + Math.sin(midAngle) * ( chartRadius + maxPullOutDistance + pullOutLabelPadding ) + 20 );
                context.shadowOffsetX = pullOutShadowOffsetX;
                context.shadowOffsetY = pullOutShadowOffsetY;
                context.shadowBlur = pullOutShadowBlur;

            } else {

                // 这片没有拔出来，所以从馅饼中心画出来
                startX = centreX;
                startY = centreY;
            }

            // S设置切片的渐变填充
            var sliceGradient = context.createLinearGradient( 0, 0, canvasWidth*.45, canvasHeight*.45 );
            sliceGradient.addColorStop( 0, sliceGradientColour );
            sliceGradient.addColorStop( 1, 'rgb(' + chartColours[slice].join(',') + ')' );

            // 绘制切片
            context.beginPath();
            context.moveTo( startX, startY );
            context.arc( startX, startY, chartRadius, startAngle, endAngle, false );
            context.lineTo( startX, startY );
            context.closePath();
            context.fillStyle = sliceGradient;
            context.shadowColor = ( slice == currentPullOutSlice ) ? pullOutShadowColour : "rgba( 0, 0, 0, 0 )";
            context.fill();
            context.shadowColor = "rgba( 0, 0, 0, 0 )" +
            		"";

             //适当地选择切片边框
            if ( slice == currentPullOutSlice ) {
                context.lineWidth = pullOutBorderWidth;
                context.strokeStyle = pullOutBorderStyle;
            } else {
            	if(chartData[0]['value']!=0&&chartData[1]['value']!=0){sliceBorderWidth=1}else{sliceBorderWidth=0}
                context.lineWidth = sliceBorderWidth;
                context.strokeStyle = sliceBorderStyle;
            }

            // 绘制边框
             //context.stroke();
        }

    };*/