<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>党建工作平台</title>
	<link rel="stylesheet" href="${ctx}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/resources/css/style.css">
	<link rel="stylesheet" href="${ctx}/resources/css/bootstrap-table.min.css">
	<link rel="stylesheet" href="${ctx}/resources/css/datepicker3.css">
	<link rel="stylesheet" href="${ctx}/resources/css/plugins/zTree/zTreeStyle.css">
	<link rel="stylesheet" href="${ctx}/resources/css/plugins/webuploader/webuploader.css">
	<link rel="stylesheet" href="${ctx}/resources/css/plugins/toastr/toastr.min.css">
</head>

<body class="">
<div class="wrapper wrapper-content">
	<div class="ibox-content">
		<div class="content">
			<a href="${ctx }/resources/personnel_bd.xlsx" target="_blank" class="btn btn-success down-file">模版下载</a>
			<!--用来存放文件信息-->
			<div id="picker" class="picker" style="display: inline-block; vertical-align: top;">选择文件</div>
			<div id="div1">请注意:上传文件不能超过5万数据</div>
			<div id="thelist" class="thelist"></div>
			<a href="" target="_blank" class="btn btn-success down-file" id="failFile" style="display:none ">导入错误信息下载</a>
		</div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/webuploader/webuploader.nolog.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/toastr/toastr.min.js"></script>
<script type="text/javascript">

    var obj = document.getElementById("div1")
    obj.style.color="red"
    //上传附件
    $(function() {


        var $ = jQuery, $list = $('#thelist'), $btn = $('#ctlBtn'), state = 'pending', uploader;
        uploader = WebUploader.create({

            // 不压缩image
            resize : false,

            // swf文件路径
            swf : '${ctx}/resources/css/webuploader/Uploader.swf',
            timeout: 0,
            // 文件接收服务端。
            server : '${ctx}/car/import',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick : '#picker',
            fileNumLimit : 1,
            accept : {
                title : 'Excel',
                extensions : 'xls,xlsx',
            },
            auto : true

        });

        // 当有文件添加进来的时候
        uploader.on('fileQueued', function(file) {
            $list.append('<div id="' + file.id + '" class="item">' + '<h4 class="info" style="display:inline">' + file.name
                + '</h4>&nbsp;&nbsp;' + '<button class="del btn btn-danger btn-xs" style="display:inline">删除</button>'
                + '<p class="state">等待上传...</p>' + '</div>');
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on('uploadProgress', function(file, percentage) {
            var $li = $('#' + file.id), $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if (!$percent.length) {
                $percent = $(
                    '<div class="progress progress-striped active">'
                    + '<div class="progress-bar" role="progressbar" style="width: 0%">' + '</div>' + '</div>')
                    .appendTo($li).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css('width', percentage * 100 + '%');
        });

        uploader.on('uploadSuccess', function(file) {
            $('#' + file.id).find('p.state').text('已选择');
        });

/*

        uploader.on('uploadError', function(file) {
            $('#' + file.id).find('p.state').text('上传出错');
        });

*/

        uploader.on('uploadError', function(file) {
            $('#' + file.id).find('p.state').text('上传出错,导入Excel需要与模板格式一致');
        });

        uploader.on("error", function(type) {
            if (type == "Q_TYPE_DENIED") {
                bi.alert("warning", "提示", "请上传Excel文件(.xls;.xlsx)")
            }
        })

        uploader.on('uploadComplete', function(file) {
            $('#' + file.id).find('.progress').fadeOut();
        });

        uploader.on('all', function(type) {
            if (type === 'startUpload') {
                state = 'uploading';
            } else if (type === 'stopUpload') {
                state = 'paused';
            } else if (type === 'uploadFinished') {
                state = 'done';
            }

            if (state === 'uploading') {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });
        var wenjianId;
        uploader.on('uploadSuccess', function(file, data) {
            if(data.retCode==1){
                bi.alert("warning", "提示", data.retMessage);
                $("#failFile").attr("href","${ctx}/error/export?id=" + data.retData);
                $("#failFile").show();
            }else if(data.retCode==0){
                bi.alert("success", "提示", data.retMessage);
                $("#failFile").hide();
            }else {
                bi.alert("warning", "提示", data.retMessage);
                $("#failFile").hide();
            }
        });
        $btn.on('click', function() {
            if (state === 'uploading') {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });
        $(document).on("click", ".del", function() {
            $(this).parent().remove();
            var Id = $(this).parent().attr('id');
            uploader.removeFile(Id, true);
        });

        $(".submitBtn").on("click", function() {
        })
    });


</script>
</body>
</html>
