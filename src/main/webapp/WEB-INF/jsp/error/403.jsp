<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>保单查询</title>
    <link rel="stylesheet" href="${ctx}/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/datepicker3.css">
    <link rel="stylesheet" href="${ctx}/resources/css/plugins/zTree/zTreeStyle.css">
    <link rel="stylesheet" href="${ctx}/resources/css/plugins/chosen/chosen.css">
    <link rel="stylesheet" href="${ctx}/resources/css/plugins/toastr/toastr.min.css">
    <style type="text/css">
        .ace-icon {
            font-size: 18px;
            margin: 5px
        }

        .green {
            color: #69aa46;
        }

        .red {
            color: #dd5a43;
        }

        .menuContent {
            z-index: 9999999999;
        }

        #department {
            cursor: pointer;
        }

        ul.ztree {
            margin-top: 10px;
            border: 1px solid #617775;
            background: #f0f6e4;
            width: 220px;
            height: 360px;
            overflow-y: scroll;
            overflow-x: auto;
        }

        .control-label {
            margin: 0 15px;
        }

        .btn {
            margin-left: 25px;
        }

        #form .row, .col-md-12 {
            min-height: 40px;
            line-height: 40px;
        }

        .div-agentCode {
            position: relative;
        }

        .agentCodeUl {
            position: absolute;
            background-color: #ffffff;
            width: 150px;
            z-index: 1001;
            max-height: 200px;
            overflow-y: scroll;
            padding: 0 0 0 5px;
            border-radius: 0 0 4px 4px;
            border: 1px solid #e5e6e7;
            margin: 0;
            top: 37px;
            display: none;
        }

        .agentCodeUl li {
            list-style-type: none;
            line-height: 15px;
            word-wrap: break-word;
            padding: 5px 6px;
        }

        .agentCodeUl li.highlighted {
            background-color: #3875d7;
            color: #ffffff;
        }
    </style>
</head>
<body class="gray-bg">
您未拥有权限!
</body>
</html>
