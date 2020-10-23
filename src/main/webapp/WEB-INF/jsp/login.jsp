<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="description" content="BigitaLoginPage">

	<title>**管理系统</title>
	<link rel="stylesheet" href="${ctx }/resources/css/login.css">
	<link rel="stylesheet" href="${ctx }/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx }/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx }/resources/css/animate.css">
	<script type="text/javascript">
        if (top != this) {
            top.location.href="${ctx}/login";
        }
	</script>
</head>
<body>
<div class="login-header clearfix">
	<div class="login-logo">
		<%--<img class="header-logo pull-left" src="${ctx }/resources/images/loginLogo.png"/>--%>
		<p class="header-info pull-left">**公司业务管理系统</p></div>
</div>
<div class="login-content">
	<form role="form" id="login-form" action="${ctx}/login" method="post">
		<div class="content-logo">
			<i class="fa fa-user fa-4x"></i>
		</div>
		<div class="user-login">员工登录</div>
		<div class="form-group center-block">
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-user"></i></div>
				<input id="hrcode" class="form-control" value="${username}" name="username" type="text" placeholder="员工编号">
			</div>
		</div>
		<div class="form-group center-block">
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-lock"></i></div>
				<input id="password" class="form-control" name="password" type="password" placeholder="密码">
			</div>
		</div>
		<div class="form-group center-block">
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-keyboard-o"></i></div>
				<input class="form-control" id="code" maxlength="4" name="code" type="text" placeholder="验证码" style="width: 100px;margin-right: 5px;">
				<img id="captchaImg" src="${ctx}/captcha" width="80" height="44" style="border: none;cursor: pointer;"/>
			</div>
		</div>
		<button type="button" class="btn btn-primary center-block" id="login-submit">登录</button>
		<div class="checkbox">
			<label>
				<%--	<input type="checkbox" name="rememberMe">记住我--%>
			</label>
			<a class="pull-right hidden" href="javascript:;">忘记密码</a>
		</div>
	</form>
	<p class="error-tips" id="tips">${error}</p>
</div>
<div class="footer clearfix">
	<p class="line"></p>
	<p class="copyright" style="display: none;">中国*************公司  版权所有</p>
	<p class="support-tech ">技术支持：*****网络科技有限公司</p>
</div>

</body>
<script type="text/javascript" src="${ctx }/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
    (function () {

        $("#captchaImg").click(function(){
            $(this).hide().attr('src', '${ctx}/captcha?' +new Date().valueOf()).fadeIn();
        });

        if($("#tips").html()!=""){
            timeOut();
        }
        function clickButton() {
            if($("#hrcode").val().length==0) {
                $("#tips").html("请输入员工编号");
                timeOut();
                return;
            }
            if($("#password").val().length==0) {
                $("#tips").html("请输入登录密码");
                timeOut();
                return;
            }
            if($("#code").val().length==0) {
                $("#tips").html("请输入验证码");
                timeOut();
                return;
            }
            $("#login-form").submit();
        };
        $("#login-submit").click(clickButton);
        $(document).keyup(function(event){
            if(event.keyCode==13){
                $("#login-submit").trigger("click");
            }
        });
        function timeOut() {
            $("#tips").show();
            setTimeout(function () {
                $("#tips").hide()
            },2000);
        };
        $(".error-tips").addClass("animated shake");
    })();
</script>
</html>
