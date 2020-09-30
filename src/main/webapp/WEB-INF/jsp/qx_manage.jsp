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
	<link rel="stylesheet" href="${ctx}/resources/css/style.css">
	<link rel="stylesheet" href="${ctx}/resources/css/datepicker3.css">
	<link rel="stylesheet" href="${ctx}/resources/css/plugins/zTree/zTreeStyle.css" type="text/css">
	<style type="text/css">
		.icon1 {
			color: blue;
			padding: 0 2px 0 10px;
		}

		.icon2 {
			color: red;
			padding: 0 2px 0 10px;
		}

		.blue {
			color: #478fca;
			border-bottom: 1px solid #d5e3ef;
			font-size: 18px;
			margin: 15px 0;
		}

		#div-org {
			display: none;
		}

		#div-orgAdd {
			display: none;
		}

		.org-list {
			cursor: pointer !important;
		}

		.lbl {
			width: 30px;
			display: inline;
			height: 13px;
			display: inline;
		}
	</style>
</head>
<body class="gray-bg">
<div class="row wrapper white-bg page-heading">
	<div class="col-sm-4">
		<ol class="breadcrumb">
			<li>主页</li>
			<li>系统管理</li>
			<li>
				<b>权限管理</b>
			</li>
		</ol>
	</div>
</div>
<div class="wrapper wrapper-content">
	<div class="ibox-content">
		<div class="row">
			<div class="col-sm-5">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="col-sm-7">
				<div id="div-org">
					<div class="row">
						<span class="blue col-sm-12 headt"></span>

					</div>
					<form class="form-horizontal" id="form-org">
						<div class="form-group">
							<label class="col-sm-2 control-label">系统管理员</label>
							<div class="col-sm-10">
								<input type="text" class="form-control org-list" name="qx0" data-type=0>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">业务岗</label>
							<div class="col-sm-10">
								<input type="text" class="form-control org-list" name="qx1" data-type=1>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">运维岗</label>
							<div class="col-sm-10">
								<input type="text" class="form-control org-list" name="qx2" data-type=2>
							</div>
						</div>


						<div style="text-align: center;">
							<button type="button" class="btn btn-success" id="orgSaveBtn">
								<i class="fa fa-save">&nbsp保存</i>
							</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/plugins/validate/jquery.validate.min.js"></script>

<script type="text/javascript" src="${ctx}/resources/js/plugins/zTree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
    var setting = {
        view : {
            dblClickExpand : false,
            showLine : false,
            addDiyDom : addDiyDom
        },
        data : {
            simpleData : {
                enable : true
            }
        },
        callback : {
            beforeExpand : beforeExpand,
            onExpand : onExpand,
            onClick : onClick,

        },
        async : {
            enable : true,
            url : "${ctx}/org/tree",
            autoParam : [ "id" ]

        }
    };

    var curExpandNode = null;
    function beforeExpand(treeId, treeNode) {
        var pNode = curExpandNode ? curExpandNode.getParentNode() : null;
        var treeNodeP = treeNode.parentTId ? treeNode.getParentNode() : null;
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        for (var i = 0, l = !treeNodeP ? 0 : treeNodeP.children.length; i < l; i++) {
            if (treeNode !== treeNodeP.children[i]) {
                zTree.expandNode(treeNodeP.children[i], false);
            }
        }
        while (pNode) {
            if (pNode === treeNode) {
                break;
            }
            pNode = pNode.getParentNode();
        }
        if (!pNode) {
            singlePath(treeNode);
        }

    }
    function addDiyDom(treeId, treeNode) {
        var aObj = $("#" + treeNode.tId + "_a");
        if (treeNode.isUnion == true) {
            var editStr = "<span class='xxx icon1'>联合党支部</span>";
            aObj.append(editStr);
        }
        if (treeNode.isContact == true) {
            var editStr = "<span class='xxx icon2'>党建联系点</span>";
            aObj.append(editStr);
        }
    }
    function singlePath(newNode) {
        if (newNode === curExpandNode)
            return;

        var zTree = $.fn.zTree.getZTreeObj("treeDemo"), rootNodes, tmpRoot, tmpTId, i, j, n;

        if (!curExpandNode) {
            tmpRoot = newNode;
            while (tmpRoot) {
                tmpTId = tmpRoot.tId;
                tmpRoot = tmpRoot.getParentNode();
            }
            rootNodes = zTree.getNodes();
            for (i = 0, j = rootNodes.length; i < j; i++) {
                n = rootNodes[i];
                if (n.tId != tmpTId) {
                    zTree.expandNode(n, false);
                }
            }
        } else if (curExpandNode && curExpandNode.open) {
            if (newNode.parentTId === curExpandNode.parentTId) {
                zTree.expandNode(curExpandNode, false);
            } else {
                var newParents = [];
                while (newNode) {
                    newNode = newNode.getParentNode();
                    if (newNode === curExpandNode) {
                        newParents = null;
                        break;
                    } else if (newNode) {
                        newParents.push(newNode);
                    }
                }
                if (newParents != null) {
                    var oldNode = curExpandNode;
                    var oldParents = [];
                    while (oldNode) {
                        oldNode = oldNode.getParentNode();
                        if (oldNode) {
                            oldParents.push(oldNode);
                        }
                    }
                    if (newParents.length > 0) {
                        zTree.expandNode(oldParents[Math.abs(oldParents.length - newParents.length) - 1], false);
                    } else {
                        zTree.expandNode(oldParents[oldParents.length - 1], false);
                    }
                }
            }
        }
        curExpandNode = newNode;
    }

    function onExpand(event, treeId, treeNode) {
        curExpandNode = treeNode;
    }
    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.expandNode(treeNode, null, null, null, true);
        var org = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0].id;
        $("#div-org").show();
        $('#div-org .headt').html($.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0].name);
        $('#div-org .org-list').val('');
        $.get("${ctx}/org/permission/query?id=" + org + '&type=0', function(data) {
            if (data != '') {
                var dataarr = [];
                for (var i = 0; i < data.length; i++) {
                    dataarr.push(data[i].memberName);
                }
                $('#div-org .org-list').eq(0).val(dataarr.join());
            }
        });
        $.get("${ctx}/org/permission/query?id=" + org + '&type=1', function(data) {
            if (data != '') {
                var dataarr = [];
                for (var i = 0; i < data.length; i++) {
                    dataarr.push(data[i].memberName);
                }
                $('#div-org .org-list').eq(1).val(dataarr.join());
            }
        });
        $.get("${ctx}/org/permission/query?id=" + org + '&type=2', function(data) {
            if (data != '') {
                var dataarr = [];
                for (var i = 0; i < data.length; i++) {
                    dataarr.push(data[i].memberName);
                }
                $('#div-org .org-list').eq(2).val(dataarr.join());
            }
        });
    }
    function closeBtnClick() {
        $('.curSelectedNode').click();
    }
    $(document).on(
        "click",
        ".org-list",
        function() {
            layer.open({
                type : 2,
                title : [ $(this).parent().prev().html(), 'color: #478fca;font-size:18px;' ],
                area : [ '640px', '520px' ],
                content : '${ctx}/qx_manage_list?id=' + $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0].id + '&type='
                    + $(this).attr('data-type')
            });

        });
    $(function() {
        $.get('${ctx}/org/tree', function(data) {
            $.fn.zTree.init($("#treeDemo"), setting, eval(data));
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getNodes();
            for (var i = 0; i < nodes.length; i++) { //设置节点展开
                treeObj.expandNode(nodes[i], true, false, true);
            }
        });
    });
</script>
</body>
</html>
