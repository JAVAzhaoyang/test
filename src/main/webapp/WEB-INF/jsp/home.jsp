<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>home</title>
    <link rel="stylesheet" href="${ctx}/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <style type="text/css">
        .count-hint, .count-hint-all {
            padding-left: 35px;
        }

        .count-hint b, .count-hint-all b {
            color: #f8ac59;
            margin-right: 20px;
        }

        .count-hint span, .count-hint-all span {
            color: #e32e1c;
            margin: 0 10px 0 10px;
            width: 80px;
            display: inline-block;
        }

        .count-hint span.fee, .count-hint-all span.fee {
            width: 120px;
        }

        .alert {
            margin: 0;
        }

        .div-rank {
            text-align: center;
            margin: 25px 0 35px 30px;
            border: 1px solid #e6e6e6;
            display: inline-block;
            width: 95%;
        }

        .div-rank .div-header {
            color: #ffffff;
            font-size: 20px;
            height: 48px;
            line-height: 48px;
        }

        #bdRank .div-header {
            background-color: #f8ac59;
        }

        #totalRank .div-header {
            background-color: #1ab394;
        }

        .div-rank table {
            width: 100%;
        }

        .div-rank table thead tr {
            color: #676a6c;
            height: 38px;
            line-height: 38px;
            font-size: 15px;
            font-weight: 600;
        }

        #bdRank table thead tr {
            background-color: #feeede;
        }

        #totalRank table thead tr {
            background-color: #d1f0ea;
        }

        .div-rank table tbody tr {
            background-color: #ffffff;
            color: #676a6c;
            height: 38px;
            line-height: 38px;
            font-size: 15px;
            border-bottom: 1px solid #e6e6e6;
        }

        .detail {
            color: #e32e1c;
            cursor: pointer;
        }

        .div-rank tbody tr:nth-child(2n) {
            background-color: #ebebeb;
        }

        .tabs-container {
            padding: 20px;
        }

        .tab-content {
            background-color: #ffffff;
            border: 1px solid #e7eaec;
            border-top: none;
        }
    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="ibox-content">
        <div class="alert alert-warning">
            欢迎您:&nbsp;&nbsp;${sessionScope.CURRENT_USER.name}【${sessionScope.CURRENT_ROLE.name}】 上次登录时间:&nbsp;&nbsp;${sessionScope.CURRENT_LOGINLOG.userId},&nbsp;&nbsp;&nbsp;登录IP：${sessionScope.CURRENT_LOGINLOG.ip}
            <button type="button" class="btn btn-primary" onclick="loadAll(this)">加载累计数据</button>
        </div>
    </div>
    <div class="ibox-content count-hint">
        <b>今日</b> 新增笔数<span name="num">0笔</span> 含税保费<span class="fee" name="sumpremium">¥0</span> 不含税保费<span class="fee" name="sumnetpremium">¥0</span> 渠道新增数量<span name="count">0名</span>
    </div>
    <div class="ibox-content count-hint-all">
        <b>累计</b> 总笔数&nbsp&nbsp<span name="num"></span> 含税保费<span class="fee" name="sumpremium"></span> 不含税保费<span class="fee" name="sumnetpremium"></span> 渠道总数&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span
            name="count"></span>
    </div>

    <div class="tabs-container">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">昨日数据</a></li>
            <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">累计数据</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab-1">
                <div class="div-rank" id="bdRank">
                    <div class="div-header">昨日数据统计</div>
                    <table>
                        <thead>
                        <tr>
                            <td width="30%">机构名称</td>
                            <td width="15%">新增保单</td>
                            <td width="20%">含税保费</td>
                            <td width="20%">不含税保费</td>
                            <td width="15%">新增渠道数</td>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="tab-2" class="tab-pane">
                <div class="div-rank" id="totalRank">
                    <div class="div-header">累计数据统计</div>
                    <table>
                        <thead>
                        <tr>
                            <td width="30%">机构名称</td>
                            <td width="15%">新增保单</td>
                            <td width="20%">含税保费</td>
                            <td width="20%">不含税保费</td>
                            <td width="15%">新增渠道数</td>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/layer/layer.min.js"></script>


<script type="text/javascript">

    var sysOrgCode = '${user.sysOrg.code}'.substring(0, 5);
    var regtest = new RegExp(/0+/g);
    var codelength = '${user.sysOrg.code}'.match(regtest)[0].length;

</script>

</body>
</html>
