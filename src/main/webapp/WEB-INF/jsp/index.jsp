<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>


<!DOCTYPE html>
<head>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>PICC销售公司业务管理系统</title>

    <!-- CSS文件 -->
    <link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/resources/css/animate.css" rel="stylesheet">
    <link href="${ctx}/resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/css/plugins/webuploader/webuploader.css">
    <style type="text/css">
        .header {
            vertical-align: 200%;
        }

        .info-detail {
            display: inline-block;
            list-style: outside none none;
            padding-left: 15px;
            color: #dfe4ed;
        }

        .info-detail li {
            margin-bottom: 5px;
            width: 113px;
        }

        .nav-label {
            padding-left: 7px;
        }

        .img-circle {
            cursor: pointer;
        }

        .div-toggleV {
            height: 40px;
        }

        .div-toggle {
            display: inline-block;
            width: 75px;
            height: 40px;
            margin: 0 5px;
            cursor: pointer;
        }

        .toggleBtn {
            display: block;
            height: 35px;
            line-height: 35px;
            width: 75px;
            font-size: 14px;
            color: #999999;
            background-color: #f0f0f0;
            text-align: center;
            border-radius: 3px;
            border: none;
            padding: 0;
        }

        .div-toggle.active .toggleBtn, .div-toggle .toggleBtn:hover {
            color: #bf9640;
            background-color: #fef8d8;
        }

        #tradition {
            margin: 0 0 0 25px;
        }

        .div-toggle.active .toggleBtn:after {
            width: 8px;
            height: 0;
            border-left: 8px solid transparent;
            border-right: 8px solid transparent;
            border-top: 8px solid #fef8d8;
            display: block;
            content: '';
            position: relative;
            left: 30px;
        }
    </style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close">
            <i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
							<span class="header"> <img alt="image" class="img-circle" width="64" src="${ctx }/resources/images/head.jpg" />
							</span>
                        <ul class="info-detail" data-code="${code}">
                            <li class="text-muted">${sessionScope.CURRENT_USER.loginName}</li>
                            <li class="text-muted">江苏省公司</li>
                            <li class="font-bold">${sessionScope.CURRENT_USER.name}</li>
                        </ul>
                    </div>
                    <div class="logo-element">PICC</div>
                </li>
                <li><a class="J_menuItem" href="${ctx}/home">
                    <i class="fa fa-desktop"></i> <span class="nav-label">平台首页1</span>
                </a></li>

               <shiro:hasAnyRoles name="超级管理员,系统管理员,业务岗,运维岗">
                <li><a href="#">
                    <i class="fa fa-edit"></i> <span class="nav-label">业务清单 </span> <span class="fa arrow"></span>
                </a>
                    <ul class="nav nav-second-level">

                       <%-- <shiro:lacksRole name="系统管理员">--%>
                        <li><a class="J_menuItem" href="${ctx}/picc">页面2</a></li>
                       <%-- </shiro:lacksRole>--%>
                        <li><a class="J_menuItem" href="${ctx}/test">页面2</a></li>

                    </ul>
                </li>
               </shiro:hasAnyRoles>

                <li><a href="#">
                    <i class="fa fa-group"></i> <span class="nav-label">系统管理</span><span class="fa arrow"></span>
                </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="${ctx}/password_update">修改密码</a></li>
                        <shiro:hasAnyRoles name="超级管理员,系统管理员">
                        <li><a class="J_menuItem" href="/password_reset">重置密码</a></li>
                        <li><a class="J_menuItem" href="/user_info">人员管理</a></li>
                        <li><a class="J_menuItem" href="/qx_manage">人员权限管理</a></li>
                        </shiro:hasAnyRoles>

                        <shiro:hasRole name="超级管理员">
                        <li><a class="J_menuItem" href="/role_info">角色权限管理</a></li>
                        </shiro:hasRole>
                    </ul>
                </li>

            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${ctx}/index">
                        <b style="color: #e8390e;">PICC</b>销售公司业务管理系统
                    </a>
                </div>
                <ul class="nav navbar-top-links navbar-right">

                    <li><a href="${ctx}/logout">
                        <i class="fa fa-sign-out"></i>&nbsp;退出
                    </a></li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left navbar-minimalize">
                <i class="fa fa-bars"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="${ctx}/home">首页</a>
                    <!--默认主页需在对应的选项卡a元素上添加data-id="默认主页的url"-->
                </div>
            </nav>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">
                    关闭操作 <span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
                </ul>
            </div>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/home" frameborder="0" data-id="${ctx}/home" seamless></iframe>
        </div>
        <div class="footer hidden">
            <div class="pull-right">© 2014-2015</div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>
<div class="pic">
    <div class="pic-presonal"></div>
    <div id="uploader-demo">
        <!--用来存放item-->
        <div id="fileList" class="uploader-list"></div>
        <div id="filePicker"></div>
    </div>
</div>
<!-- 全局js -->
<script src="${ctx }/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx }/resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx }/resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${ctx }/resources/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plugins/webuploader/webuploader.nolog.min.js"></script>

<!-- 自定义js -->
<script src="${ctx }/resources/js/hplus.js?v=4.1.0"></script>
<script type="text/javascript" src="${ctx}/resources/js/contabs.js"></script>

<!-- 第三方插件 -->
<script src="${ctx}/resources/js/plugins/pace/pace.min.js"></script>

<script>
    function menuItemChild(e) {
        // 获取标识数据
        var dataUrl = $(e).attr('data-href'), dataIndex = $(e).data('index'), menuName = $(e).attr('data-text'), flag = true;
        if (dataUrl == undefined || $.trim(dataUrl).length == 0)
            return false;
        var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '"><i class="fa fa-refresh"></i> '
            + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.J_menuTab').removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl
            + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);
        $('.J_menuTabs .page-tabs-content').append(str);
        return false;
    }
    function closeThisPage(dataid) {
        $('.J_menuTab[data-id*="' + dataid + '"] i.fa-times-circle').click();
    }

</script>
</body>
