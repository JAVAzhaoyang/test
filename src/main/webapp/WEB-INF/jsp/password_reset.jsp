<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>重置密码</title>
    <link rel="stylesheet" href="${ctx}/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/plugins/toastr/toastr.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <style type="text/css">
    </style>
</head>
<body class="gray-bg">
<div class="row wrapper white-bg page-heading">
    <div class="col-sm-4">
        <ol class="breadcrumb">
            <li>主页</li>
            <li>系统管理</li>
            <li>
                <strong>重置密码</strong>
            </li>
        </ol>
    </div>
</div>
<div class="wrapper wrapper-content">
    <div class="ibox-content">
        <div class="row">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="oldPsw" placeholder="输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">新密码</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" id="newPsw" placeholder="输入新密码">
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" id="startNewPsw" placeholder="重新输入新密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" class="btn btn-success" id="save">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript">
    $(function() {
        var $oldPsw = $("#oldPsw"), $newPsw = $("#newPsw"), $startNewPsw = $("#startNewPsw");
        var html = ''
        $("#save").bind("click", function() {
            if ($oldPsw.val().length == 0) {
                $oldPsw.next().html('');
                $oldPsw.parent().parent().addClass("has-error");
                $oldPsw.after('<p style="color:#DC143C">>用户名必填</p>');
                return false;
            } else if ($newPsw.val().length == 0) {
                $newPsw.next().html('');
                $newPsw.parent().parent().addClass("has-error");
                $newPsw.after('<p style="color:#DC143C">密码必填</p>');
                return false;
            } else if ($startNewPsw.val().length == 0) {
                $startNewPsw.next().html('');
                $startNewPsw.parent().parent().addClass("has-error");
                $startNewPsw.after('<p style="color:#DC143C">密码必填</p>');
                return false;
            } else if ($newPsw.val() != $startNewPsw.val()) {
                $startNewPsw.next().html('');
                $newPsw.parent().parent().addClass("has-error");
                $startNewPsw.parent().parent().addClass("has-error");
                $startNewPsw.after('<p style="color:#DC143C">密码不一致</p>');
                return false;
            } else if ($newPsw.val().length < 6 || $newPsw.val().length > 20) {
                $newPsw.next().html('');
                $newPsw.parent().parent().addClass("has-error");
                $newPsw.after('<p style="color:#DC143C">请至少输入6个字符作为密码</p>');
                return false;
            }
            $(this).prop("disabled", true);
            var param = {
                "oldPass" : $oldPsw.val(),
                "newPass" : $newPsw.val()
            };
            $.post("/user/password/update", param, function(data) {
                $(this).prop("disabled", false);
                if (data.retCode == 0) {
                    bi.alert("success", '提示:', data.retMsg);
                    setTimeout(function() {
                        window.location.reload();
                    }, 1000);
                } else {
                    bi.alert("error", '提示:', data.retMsg);
                }
            });

        });

        $oldPsw.bind("focus", function() {
            $oldPsw.next().html('');
            $oldPsw.parent().parent().removeClass("has-error");
        });
        $newPsw.bind("focus", function() {
            $newPsw.next().html('');
            $newPsw.parent().parent().removeClass("has-error");
        });
        $startNewPsw.bind("focus", function() {
            $startNewPsw.next().html('');
            $startNewPsw.parent().parent().removeClass("has-error");
        });
    })
</script>
</body>
</html>
