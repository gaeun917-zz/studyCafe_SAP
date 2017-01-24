<%@ page import="com.studycafe.model.dto.Member" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- -------------- READ ME ------------------------------------------------------ --%>

<!-- 1. Jquery and bootstrap link(below) are not imported on header.jsp -->

<!-- <link href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script src="/studyCafe/resources/bootstrap/js/bootstrap.min.js"></script> -->

<!-- 2. header tag(below) should be placed right under the <body> tag -->
<%-- <c:import url="/WEB-INF/views/include/header.jsp"/> --%>

<%-- ------------------------------------------------------------------------------ --%>

<body data-spy="scroll">
<script type="text/javascript">
    $('#pettype').css('display', "${ empty loginuser ? 'none' : 'block' }");
    $('#btnLogin1').css('display', "${ not empty loginuser ? 'none' : 'block' }");
    $(function () {
        $('#mystudyroom').on('click', function (event) {
            $.ajax({
                url: '/studyCafe/account/pageList.action',
                type: 'get',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                data: {},
                success: function (data, status, xhr) {
                    showResult(data);
                }
            })
        });

        function showResult(data) {
            if (data.length == 0)
                return;
            $('#dspan').html(data);
        }
    });
</script>

<header id="header">
    <nav class="navbar navbar-default" role="navigation">
        <div class="a">
            <div class="navbar-header">
                <a class="navbar-brand" href="/studyCafe/home.action"
                   style="font-weight: bolder; font-float:none; font-size: large;">스터디 카페</a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/studyCafe/board/write.action">
                        <span class="glyphicon glyphicon-pushpin"/>&nbsp;&nbsp;스터디 등록
                    </a>
                </li>
                <li>
                    <a href="/studyCafe/board/list.action">
                        <span class="glyphicon glyphicon-tree-deciduous"/>&nbsp;&nbsp;스터디 참여
                    </a>
                </li>
                <li class="dropdown">
                    <a id="mystudyroom" class="dropdown-toggle" data-toggle="dropdown"
                       href="javascript:window.open('/studyCafe/page/board/list.action?menuno=${pagemenu.menuNo}', '${ userpage.getName() }','width=1000, height=1200, toolbar=no, menubar=no, scrollbars=no, resizable=yes');">
                        <span class="glyphicon glyphicon-lamp"/>&nbsp;&nbsp;내 공부방 가기<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
						  <span id="dspan">
							  <hr>
								<li style='text-align: center'>No Search Your Room</li>
							  <hr>
						  </span>
                    </ul>
                </li>
                <!-- <li><a href="/studyCafe/calendar/list.action">캘린더</a></li> -->

                <li>
                    <c:choose>
                        <c:when test="${ not empty sessionScope.loginuser }">
                            <a id="btnLogin1" href="#" style="font-size: large;">${ loginuser.name }&nbsp;&nbsp;</a>
                        </c:when>

                        <c:when test="${ not empty sessionScope.facebookLoginuser}">
                            <a id="btnLogin1" href="#">${ facebookLoginuser }</a>
                        </c:when>

                        <c:otherwise>
                            <a id="btnLogin1" href="#" style="font-weight: bold;" data-toggle="modal"
                               data-target="#myModal">
                                <span class="glyphicon glyphicon-user"/>&nbsp;&nbsp;로그인
                            </a>
                        </c:otherwise>
                    </c:choose>

                    <button style="margin-top: 10px; size: 40px" type="button" data-toggle="dropdown"
                            id="pettype" aria-haspopup="true" aria-expanded="true"
                            class="btn btn-info dropdown-toggle">${loginuser.memberId}
                    </button>


                    <ul class="dropdown-menu" aria-labelledby="pettype">
                        <li class="dropdown-header"></li>
                        <li>
                            <a href="/studyCafe/member/mypage.action">
                                <span class="glyphicon glyphicon-home"/>&nbsp;&nbsp;마이페이지
                            </a>
                        </li>
                        <li>
                            <a href="#">Fish</a>
                        </li>
                        <li>
                            <a href="#">Amphibian</a>
                        </li>
                        <li>
                            <a href="#">Reptile</a>
                        </li>
                        <li>
                            <a href="/studyCafe/account/logout.action" id="logout">
                                <span class="glyphicon glyphicon-bookmark"/>&nbsp;&nbsp;로그아웃
                            </a>
                        </li>
                    </ul>

                </li>
            </ul>

        </div><!-- container -->
    </nav>
</header>
<!--  -----------------------HEADER ENDS--------------------------------- -->


