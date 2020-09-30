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

<div class="row wrapper white-bg page-heading">
    <div class="col-sm-4">
        <ol class="breadcrumb">
            <li>主页</li>
            <li>数据同步</li>
            <li><b>定时任务1</b></li>
        </ol>
    </div>
</div>


<div class="wrapper wrapper-content">
    <div class="ibox-content">

        <form role="form" class="form-inline" id="form">
            <div class="row">

                <div>
                    <button id="searchBtn" class="btn btn-success" type="button">
                        <i class="fa fa-search"></i>&nbsp;&nbsp;查询
                    </button>

                    <a class="btn btn-info add-btn" type="button" data-toggle="modal" data-target="#msg-add">
                        <i class="fa fa-plus" aria-hidden="true"></i>&nbsp;添加&nbsp;&nbsp;&nbsp;
                    </a>

                    <button id="downloadBtn" class="btn btn-warning" type="button">
                        <i class="fa fa-arrow-circle-o-down"></i>&nbsp;&nbsp人员信息下载
                    </button>

                    <button id="importBtn" class="btn btn-primary" type="button">
                        <i class="fa fa-upload"></i>&nbsp;&nbsp;导入
                    </button>
                </div>
            </div>
        </form>
    </div>

    <div class="ibox-content">
        <div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <table id="table"></table>
            </div>
        </div>
    </div>
</div>

<!-- modify开始-->
<div class="modal fade" tabindex="-1" role="dialog" id="msg-add">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增</h4>
            </div>
            <div class="modal-body">
                <form id="add-msg">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label>姓名</label>
                            <input type="text" class="form-control required" name="name">
                        </div>
                        <div class="form-group col-md-6">
                            <label>年龄</label>
                            <input type="text" class="form-control required" name="age">
                        </div>
                        <div class="form-group col-md-6">
                            <label>手机号码</label>
                            <input type="text" class="form-control required" name="phoneNumber">
                        </div>
                        <div class="form-group col-md-6">
                            <label>车牌号</label>
                            <input type="text" class="form-control required" name="licenseno">
                        </div>
                        <div class="form-group col-md-6">
                            <label>交易金额</label>
                            <input type="text" class="form-control required" name="cost">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
                <a type="button" class="btn btn-primary save-add">保存</a>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- modify开始-->
<div class="modal fade" tabindex="-1" role="dialog" id="msg-modify">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改</h4>
            </div>
            <div class="modal-body">
                <form id="modify-msg">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label>姓名</label>
                            <input type="text" class="form-control required" name="name">
                        </div>
                        <div class="form-group col-md-6">
                            <label>年龄</label>
                            <input type="text" class="form-control required" name="age">
                        </div>
                        <div class="form-group col-md-6">
                            <label>手机号码</label>
                            <input type="text" class="form-control required" name="phoneNumber">
                        </div>
                        <div class="form-group col-md-6">
                            <label>车牌号</label>
                            <input type="text" class="form-control required" name="licenseno">
                        </div>
                        <div class="form-group col-md-6">
                            <label>交易金额</label>
                            <input type="text" class="form-control required" name="cost">
                        </div>
                        <%--<div class="form-group col-md-6">
                            <label>起保日期</label>
                            <input id="G3" type="text" placeholder="选择日期" name="startdate" class="form-control required" autocomplete="off" required="">
                        </div>
                        <div class="form-group col-md-6">
                            <label>终保日期</label>
                            <input id="G4" type="text" placeholder="选择日期" name="enddate" class="form-control required" autocomplete="off" required="">
                        </div>--%>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
                <a type="button" class="btn btn-primary save-modify">保存</a>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/zTree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/zTree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/toastr/toastr.min.js"></script>
<script type="text/javascript">
    $(function() {

        $("#searchBtn").click(function() {
            $("#table").bootstrapTable("destroy");
            setTable();
        });


        $(document).on('click', '#importBtn', function() {
            layer.open({
                type : 2,
                content : [ '${ctx}/picc_dr', 'no' ],
                area : [ '400px', '250px' ]
            });
        });

        $(document).on('click', '#downloadBtn', function() {

          /*  if( $('#name').val() == ''){
                bi.alert('warning', '请选择名称下载');
                return;
            }*/
            window.open('/export/carExporter?' + $("#form").serialize());
        });


        window.titleEvents = {
            //删除
            'click .del-line' : function(e, value, row, index) {
                var _self = this;
                bi.confirm("确定要删除吗？", function() {
                    parent.layer.closeAll('dialog');
                    $.post("${ctx}/delete/car", {
                        "id" : row.id
                    }, function(data) {
                        if (data.status == 0) {
                            $(_self).parents("tr").remove();
                        }else if(data.status == 1){
                            bi.confirm("删除失败")
                        }else {
                            bi.confirm("无操作权限")
                        }


                    });
                });
            },
            'click .update' : function(e, value, row, index) {
                rowId = row.id;
                $.get("${ctx}/view/car", {
                    id : row.id
                }, function(data) {
                    $("#msg-modify").find(".form-control").each(function() {
                        $(this).val(data[$(this).attr("name")]);
                    })
                });
                $("#msg-modify").modal("show");
            }
        };

        //新增
        $(".save-add").on("click", function() {
            var shut = 0;
            $('#add-msg input.required').each(function() {
                if ($(this).val() == '' && shut == 0) {
                    bi.alert("warning", $(this).parents('.col-md-6').find('label').html() + '为空或输入有误,请重新输入!');
                    shut = 1;
                }
            });
            if (shut == 0) {
                var param = $("#add-msg").serialize();//获取相关字段
                bi.showLoading();
                $.post("${ctx}/insert/car?" + param, function(res) {
                    bi.hideLoading();
                    var status = 'error';
                    if (res.retCode == 0) {
                        $("#table").bootstrapTable('refresh');
                        $("#msg-add").modal("hide");
                        status = 'success';
                    }
                    bi.alert(status, "", res.retMessage);
                });
            }

        });

        //修改
        $(".save-modify").on("click", function() {
            var shut = 0;
            $('#modify-msg input.required').each(function() {
                if ($(this).val() == '' && shut == 0) {
                    bi.alert("warning", $(this).parents('.col-md-6').find('label').html() + '为空或输入有误,请重新输入!');
                    shut = 1;
                }
            });
            if (shut == 0) {
                var mParam = $("#modify-msg").serialize();
                $.post("${ctx}/update/car?" + mParam + "&id=" + rowId, function(res) {
                    var status = 'error';
                    if (res.retCode == 0) {
                        $("#table").bootstrapTable('refresh');
                        $("#msg-modify").modal("hide");
                        status = 'success';
                        bi.alert(status, "", res.retMessage);
                    }else if(res.retCode == 1){
                        bi.alert(status, "", res.retMessage);
                    }else {
                        bi.alert(status, "", "无操作权限");
                    }

                })
            }

        });


        //添加元素到列中
        function titleFormatter(value, row, index) {
            return [ '<a class="green update">', '<i class="ace-icon fa fa-edit bigger-130"></i>', '</a>',
                '<a class="red del-line" href="javascript:void(0);">', '<i class="ace-icon fa fa-trash-o bigger-130"></i>',
                '</a>' ].join('');
        }

        function setTable() {
            $("#table").bootstrapTable({
                url : "${ctx}/select/carInfo?",
                queryParams : function(params) {
                    return $("#form").serialize()
                },
                cache : false,
                striped : true,
                pagination : true,
                sidePagination : "server",
                pageNumber : 1,
                pageList : [ 50 ],
                pageSize : 50,
                queryParamsType : "",
                columns : [
                    {
                        title : "姓名",
                        field : "name"
                    }, {
                        title : "年龄",
                        field : "age"
                    }, {
                        title : "手机号码",
                        field : "phoneNumber"
                    },{
                        title : "车牌",
                        field : "licenseno"
                    },{
                        title : "维修金额",
                        field : "cost"
                    },{
                        title : "操作",
                        events : titleEvents,
                        formatter : titleFormatter
                    }
                ]
            });
        }
        setTable();


    });
</script>
</body>
</html>
