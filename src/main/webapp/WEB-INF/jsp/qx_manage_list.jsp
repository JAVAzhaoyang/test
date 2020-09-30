<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>通知公告</title>
	<link rel="stylesheet" href="${ctx}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/resources/css/bootstrap-table.min.css">
	<style type="text/css">
		.table thead {
			background: #f2f2f2 linear-gradient(to bottom, #f8f8f8 0px, #ececec 100%) repeat-x scroll 0 0;
			color: #707070;
		}

		#toast-container {
			top: 50%;
		}

		#deleteBtn {
			cursor: pointer;
		}

		.red {
			color: #dd5a43;
		}

		.red:hover {
			color: #428bca;
		}
	</style>
</head>
<body>
<div class="wrapper wrapper-content" style="padding: 15px 0;">
	<div class="col-xs-12">
		<form class="form-horizontal" id="form-search">
			<div class="form-group">
				<div class="col-xs-6">
					<input class="form-control search-query" id="searchInp" onkeypress="if(event.keyCode==13) {searchBtn.click();return false;}" placeholder="输入姓名查找或者HR编码" type="text">
				</div>
				<div class="col-xs-6">
						<span>
							<button id="searchBtn" type="button" class="btn btn-primary">
								<i class="fa fa-search"></i>&nbsp;查找
							</button>
						</span>
				</div>
			</div>
		</form>
	</div>
	<div class="col-xs-12">
		<table id="table"></table>
	</div>
	<div class="col-xs-12 navbar-fixed-bottom" style="text-align: center; bottom: 10px;">
		<button class="btn btn-primary" id="selectBtn" style="display: none;">
			<i class="fa fa-check"></i> 保存
		</button>
	</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript">
    var id, olddata, newdata, type;
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg); // 获取url中"?"符后的字符串并正则匹配
        var context = "";
        if (r != null)
            context = r[2];
        reg = null;
        r = null;
        return context == null || context == "" || context == "undefined" ? "" : context;
    }
    $(document).on("click", "#searchBtn", function() {
        $.get('${ctx}/member/select?keyword=' + $("#searchInp").val(), function(data) {
            newdata = data;
            $('#table').bootstrapTable('hideColumn', 'deleteBtn');
            $('#table').bootstrapTable('showColumn', 'checkBtn');
            //$('#table').bootstrapTable('load', olddata);
            $('#table').bootstrapTable('append', newdata);
            console.log(olddata)
            if(olddata){
                for (var i = 0; i < olddata.length; i++) {
                    $(".selectradio[data-id='" + olddata[i].memberId + "']").eq(1).parents("tr").remove();
                    $(".selectradio[data-id='" + olddata[i].memberId + "']").attr('type', 'hidden');
                }
                $('#selectBtn').show();
            }

        });
    })
    $(document).on(
        "click",
        "#deleteBtn",
        function() {
            var thisdelete = $(this);
            layer.confirm('确定要删除么？', {
                title : false
            }, function(index) {
                //do something
                $.post('${ctx}/org/permission/delete?id=' + id + '&memberId=' + thisdelete.attr('data-id') + '&type=' + type,
                    function() {
                        window.parent.closeBtnClick();
                    });
                layer.close(index);
                layer.msg('删除成功！', {
                    time : 1000
                }, function() {
                    /* window.parent.closeBtnClick(); */
                    window.location.reload();
                });
            });

        })
    $(document).on(
        "click",
        "#selectBtn",
        function() {
            $.post('${ctx}/org/permission/set?id=' + id + '&memberId=' + $('.selectradio:checked').attr('data-id') + '&type='
                + type, function() {
                window.parent.closeBtnClick();
            });

            layer.msg('保存成功！', {
                time : 1000
            }, function() {
                /* window.parent.closeBtnClick(); */
                window.location.reload();
            });
        })
    $(function() {
        id = GetQueryString("id");
        type = GetQueryString("type");
        inputName = GetQueryString("inputName");
        $
            .get(
                "${ctx}/org/permission/query?id=" + id + '&type=' + type,
                function(data) {
                    olddata = data;
                    $('#table')
                        .bootstrapTable(
                            {
                                data : olddata,
                                cache : false,
                                height : 360,
                                columns : [
                                    {
                                        field : 'checkBtn',
                                        formatter : function(value, row, index) {
                                            return '<label><input name="selectradio" class="selectradio" type="radio" data-id="'+row.memberId+'"></label>';
                                        }
                                    },
                                    {
                                        field : 'memberName',
                                        title : '姓名',
                                        width : 60
                                    },
                                    {
                                        field : 'memberCode',
                                        title : '人员代码'
                                    },
                                    {
                                        field : 'memberComName',
                                        title : '归属机构'
                                    },
                                    {
                                        field : 'deleteBtn',
                                        title : '操作',
                                        width : 20,
                                        formatter : function(value, row, index) {
                                            return '<i class="fa fa-trash-o red" id="deleteBtn" data-id="'+row.memberId+'"></i>';
                                        }
                                    } ]
                            });
                    $('#table').bootstrapTable('hideColumn', 'checkBtn');
                    $('#table').bootstrapTable('showColumn', 'deleteBtn');
                });

    });
</script>
</body>
</html>
