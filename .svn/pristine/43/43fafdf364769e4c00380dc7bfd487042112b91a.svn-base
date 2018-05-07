<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<title>资费检测工具</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<style>
	.login_table {            
            height: 250px;
            border: 0px;
            cellspacing: 0px;
            cellpadding: 0px;
            margin: 28% auto;
        }
        .login_table td{
            height:35px;
        }
        .login_table label{
            display:inline-block;
            width: 80px;
            font-size: 1.2em;
            color:#fff;
        }
        button{
            display: inline-block;
            width:120px;
            height:35px;
            font-size: 18px;
            text-align: center;
            letter-spacing: 5px;
            background:#3385ff;
            border: 1px solid #2d78f4;
            color: #fff;
            text-shadow: 1px 1px 0.5px #22629B;
            margin-left: 30px;
            cursor:pointer
        }
        button:hover{
         	background:#317ef3;
        }
</style>
<link rel="shortcut icon" href="images/image/tag.jpg" />
<script type="text/javascript" src="framework/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="framework/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="framework/js/md5-min.js"></script>
<script type="text/javascript">

function submitLogin(){
	if($("#login_form").valid()){
		var password = $("#user_pass").val();
		$("#user_pass").val(hex_md5(password));
		document.getElementById("login_form").submit();
	}
}

$(document).ready(function(){
	$("#login_form").validate({
		rules: {
			user_code: {required:true},
		}
	});
    //用户名密码错误提示
	if("1"=="<%=request.getAttribute("loginError")%>"){
		$("#login_table").css("margin-top","28%");
		$("#show").css("display","");
		myReload();
	}
	//验证码为空提示
	if("2"=="<%=request.getAttribute("nullIdfCode")%>"){       
		$("#login_table").css("margin-top","28%");
		$("#IdfCode").css("display","");
	}
	//验证码验证反馈
	if("3"=="<%=request.getAttribute("isRight")%>") {
					$("#login_table").css("margin-top", "28%");
					$("#isRight").css("display", "");
				}
				//重新加载验证码
				function myReload() {
					document.getElementById("identifyCode").src = document
							.getElementById("identifyCode").src
							+ "?nocache=" + new Date().getTime();
				}
			});
</script>
</head>
<body  onkeydown="if(event.keyCode==13){submitLogin();}"
	style="overflow: hidden; background-image: url('images/image/login_bg.jpg'); background-repeat: no-repeat;background-size:100%;">
	<form id="login_form" action="passageway/land" method="post">
		<table id="login_table" class="login_table">
			<tr>
				<td><font color="red" size="2" id="show" style="display: none">用户名或密码错误！！</font></td>
			</tr>
			<tr>
				<td><label for="user_code">用户名：</label>
				<input value="<%=request.getAttribute("user_name") == null ? "" : request.getAttribute("user_name")%>"
					id="user_code" name="user_code"  style="width: 260px; height: 25px"
					type="text"></td>
			</tr>
			<tr>
				<td><label for="user_pass" >密 码：</label>
				<input id="user_pass" name="user_pass"
					style="margin-top:5px;width: 260px; height: 25px"
					type="password"></td>
			</tr>
			<tr id="IdfCode" style="display: none">
				<td><font color="red" size="2">验证码不能为空！！</font></td>
			</tr>
			<tr id="isRight" style="display: none">
				<td><font color="red" size="2">验证码输入有误！！</font></td>		
			</tr>
			<tr>
				<td><span><label for="identifyingCode">验证码：</label>
				<input name="identifyingCode" type="text" 
						value="" style="margin-top:10px;width: 150px; height: 25px; vertical-align:middle" 
						type="text" /> <img src="PictureCheckCode" id="identifyCode" style="margin-top:5px; width: 100px;height:34px;vertical-align:middle"/>
						</span></td>
			</tr>
			<tr>
				<td rowspan="2" style="margin-top:10px">
					 <button onclick="myReload()">重置</button>
                	<button onclick="submitLogin()" >登录</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>