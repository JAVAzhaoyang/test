<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>保单下载</title>
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="/resources/css/datepicker3.css">
    <link rel="stylesheet" href="/resources/css/plugins/zTree/zTreeStyle.css">
    <link rel="stylesheet" href="/resources/css/plugins/chosen/chosen.css">
    <link rel="stylesheet" href="/resources/css/plugins/toastr/toastr.min.css">
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

        .menuContent {
            z-index: 3;
        }
        .color_green {
            color: #2eb45f;
        }

        .color_orange {
            color: #ee7102;
        }

        .color_gray {
            color: #999999;
        }
    </style>
</head>
<body class="gray-bg">
<div class="row wrapper white-bg page-heading">
    <div class="col-sm-4">
        <ol class="breadcrumb">
            <li>主页</li>
            <li>业务统计</li>
            <li><b>保单下载</b></li>
        </ol>
    </div>
</div>
<div class="wrapper wrapper-content">
    <div class="ibox-content">
        <form role="form" class="form-inline" id="form">


            <div class="form-group">
                <label class="control-label">渠道码:</label>
                <div class="form-group">
                    <input type="text" placeholder="请输入渠道码" id="agentcode" name="agentcode" class="form-control" style="width: 225px;">
                </div>
            </div>


            <div class="form-group">
                <label class="control-label">保单号:</label>
                <div class="form-group">
                    <input type="text" placeholder="请输入保单号" id="policyno" name="policyno" class="form-control" style="width: 225px;">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label">险种代码:</label>
                <div class="form-group">
                    <input type="text" placeholder="请输入险种代码" id="riskcode" name="riskcode" class="form-control" style="width: 225px;">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label">渠道码分支:</label>
                <label class="sr-only">渠道码分支:</label>
                <input type="text" placeholder="请选择渠道分支" id="agentcodename_tree" class="form-control" readonly="readonly">
                <input type="hidden" name="agentcodename">
            </div>


            <%--		<div class="form-group">
                        <label class="control-label">保险公司分支机构:</label>
                        <label class="sr-only">保险公司分支机构:</label>
                        <input type="text" placeholder="请选择分支机构" id="comSaleCode_tree" class="form-control" readonly="readonly">
                        <input type="hidden" name="comSaleCode">
                    </div>
        --%>
            <div class="form-group">
                <label class="control-label">保险公司地市机构:</label>
                <label class="sr-only">保险公司地市机构:</label>
                <input type="text" placeholder="请选择地市机构" id="comcode_tree" class="form-control" readonly="readonly">
                <input type="hidden" name="comcode">
            </div>

            <div class="form-group">
                <label class="control-label">车牌号:</label>
                <div class="form-group">
                    <input type="text" placeholder="请输入车牌号" id="licenseno" name="licenseno" class="form-control" style="width: 225px;">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label">推荐送修码:</label>
                <div class="form-group">
                    <input type="text" placeholder="请输入推荐送修码" id="monopolycode" name="monopolycode" class="form-control" style="width: 225px;">
                </div>
            </div>


            <div class="form-group">
                <label class="control-label">签单日期:</label>
                <div id="datepicker" class="input-daterange input-group">
                    <input id="GTE" type="text" placeholder="开始日期" name="startDate" class="form-control" autocomplete="off">
                    <span class="input-group-addon">到</span>
                    <input id="LTE" type="text" placeholder="结束日期" name="endDate" class="form-control" autocomplete="off">
                </div>
            </div>



            <button id="downloadBtn" class="btn btn-primary" type="button">
                <i class="fa fa-arrow-circle-o-down"></i>&nbsp;&nbsp;下载
            </button>
        </form>
    </div>


    <div class="ibox-content">

        <div class="row">
            <div class="col-sm-12">
                <table id="table"></table>
            </div>
        </div>
    </div>
    <div id="menuContent1" class="menuContent" style="display: none; position: absolute;">
        <ul id="treeDemo1" class="ztree" style="margin-top: 0; width: auto; height: 300px;"></ul>
    </div>
    <div id="menuContent2" class="menuContent" style="display: none; position: absolute;">
        <ul id="treeDemo2" class="ztree" style="margin-top: 0; width: auto; height: 300px;"></ul>
    </div>
    <div id="menuContent3" class="menuContent" style="display: none; position: absolute;">
        <ul id="treeDemo3" class="ztree" style="margin-top: 0; width: auto; height: 300px;"></ul>
    </div>
</div>
<form id="hform">
</form>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/plugins/zTree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/resources/js/plugins/zTree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/resources/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="/resources/js/plugins/toastr/toastr.min.js"></script>
<script type="text/javascript">
    $(function() {

        function isSearchNull() {
            var isNullTarget = 0;
            var isNullTarget = 0;
            if ($('#agentcode').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#monopolycode').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#policyno').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#appliname').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#riskcode').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#comSaleCode').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#licenseno').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#settlementstatus').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#businessType').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#GTE').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#LTE').val() !== '') {
                isNullTarget = 1;
            }
            if ($('#isV').val() !== '') {
                isNullTarget = 1;
            }
            if (isNullTarget == 0) {
                return false;
            } else {
                return true;
            }
        }



        $('#GTE').datepicker({
            format : 'yyyy-mm-dd 00:00:00',
            autoclose : true,
            clearBtn : true,
            todayBtn : 'linked'
        }).on('changeDate', function(e) {
            if (e.date > $('#LTE').datepicker('getDate')) {
                bi.alert('warning', '', '开始日期不能大于结束日期');
            }
        });
        $('#LTE').datepicker({
            format : 'yyyy-mm-dd 23:59:59',
            autoclose : true,
            clearBtn : true,
            todayBtn : 'linked'
        }).on('changeDate', function(e) {
            if ($('#GTE').datepicker('getDate') > e.date) {
                bi.alert('warning', '', '开始日期不能大于结束日期');
            }
        });



        var setting3 = {
            check : {
                enable : true,
                chkStyle: "radio",  //单选框
                chkboxType : {
                    "Y" : "",
                    "N" : ""
                }
            },
            view : {
                dblClickExpand : false
            },
            data : {
                simpleData : {
                    enable : true
                }
            },
            callback : {
                beforeClick : beforeClick3,
                onCheck : onCheck3,
                onAsyncSuccess : onAsyncSuccess3
            },
            async : {
                enable : true,
                url : "/org/tree",
                autoParam : [ "id" ]

            }
        };

        function beforeClick3(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo3");
            zTree.checkNode(treeNode, !treeNode.checked, null, true);
            return false;
        }
        function onCheck3(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo3"), nodes = zTree.getCheckedNodes(true), v = "", codes = "";
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
                codes += nodes[i].code + ",";
            }
            if (v.length > 0) {
                v = v.substring(0, v.length - 1);
                codes = codes.substring(0, codes.length - 1);
            }
            var cityObj = $("#agentcodename_tree");
            cityObj.val(v);
            cityObj.next().val(codes);
        }
        $.get('/org/tree', function(result) {
            $.fn.zTree.init($("#treeDemo3"), setting3, result);
            //展开文件
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo3");
            var nodes = treeObj.getNodes();
            for (var i = 0; i < nodes.length; i++) { //设置节点展开
                treeObj.expandNode(nodes[i], true, false, true);
            }
            setTimeout(function() {
                treeObj.expandAll(false);
            }, 1000);
        });
        $("#agentcodename_tree").on("click", function() {
            var cityObj = $("#agentcodename_tree");
            var cityOffset = $("#agentcodename_tree").offset();
            $("#menuContent3").css({
                left : cityOffset.left + "px",
                top : cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");
            $("body").bind("mousedown", onBodyDown2);
        });
        function hideMenu2() {
            $("#menuContent3").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown2);
        }
        function onBodyDown2(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "agentcodename_tree" || event.target.id == "menuContent3" || $(
                event.target).parents("#menuContent3").length > 0)) {
                hideMenu2();
            }
        }
        //每次加载节点触发的方法
        function onAsyncSuccess3(event, treeId, treeNode, msg) {
            //递归展开子节点
            if (treeNode != undefined) {
                expandNodes2(treeNode.children, "treeDemo3");
            }
        }

        //递归展开子节点
        function expandNodes3(nodes, treeId) {
            if (!nodes)
                return;
            var zTree = $.fn.zTree.getZTreeObj(treeId);
            for (var i = 0, l = nodes.length; i < l; i++) {
                zTree.expandNode(nodes[i], false, false, false);
                if (nodes[i].isParent && nodes[i].zAsync) {
                    expandNodes3(nodes[i].children, treeId);
                }
            }
        }


    });
</script>
</body>
</html>
